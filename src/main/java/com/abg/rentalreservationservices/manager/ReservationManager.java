package com.abg.rentalreservationservices.manager;

import com.abg.rentalreservationservices.entity.Car;
import com.abg.rentalreservationservices.entity.Reservation;
import com.abg.rentalreservationservices.entity.ReservationSummary;
import com.abg.rentalreservationservices.entity.User;
import com.abg.rentalreservationservices.repository.ReservationRepository;
import com.abg.rentalreservationservices.requestDTO.BookingUpdationRequest;
import com.abg.rentalreservationservices.service.*;
import exceptions.AccessDeniedException;
import exceptions.BadRequestsException;
import exceptions.NotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.abg.rentalreservationservices.requestDTO.ReservationRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationManager implements ReservationService {

    private final CarService carService;
    private final UserService userService;
    private final ServicableCityService servicableCityService;
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation makeNewReservation(Long carId, ReservationRequest reservationRequest, Authentication authentication) {
        Car car = carService.getCarById(carId);
        User currentLoggedInUser = userService.getCurrentLoggedInUser(authentication);

        BigDecimal amountRecieved = car.getBasePrice();

        Reservation reservation = new Reservation();
        reservation.setSourceCity(servicableCityService.getServicableCityByName(reservationRequest.getSourceCity()));
        reservation.setDestinationCity(servicableCityService.getServicableCityByName(reservationRequest.getDestinationCity()));
        reservation.setCar(car);
        reservation.setUser(currentLoggedInUser);
        reservation.setReserverName(currentLoggedInUser.getName());
        reservation.setReserverPhoneNumber(currentLoggedInUser.getPhoneNumber());
        reservation.setPickUpAddress(reservationRequest.getPickUpAddress());
        reservation.setDropOffAddress(reservationRequest.getDropOffAddress());
        reservation.setSeatingCapacity(reservationRequest.getSeatingCapacity());
        reservation.setStartDateTime(reservationRequest.getStartDateTime());
        reservation.setEndDateTime(reservationRequest.getEndDateTime());
        reservation.setAmountRecieved(amountRecieved);

        if(reservationRequest.getReserverName()!=null && !reservationRequest.getReserverName().isEmpty()){
            reservation.setReserverName(reservation.getReserverName());
        }

        if(reservationRequest.getReserverPhoneNumber()!=null && !reservationRequest.getReserverPhoneNumber().isEmpty()){
            reservation.setReserverPhoneNumber(reservation.getReserverPhoneNumber());
        }

        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public ReservationSummary generateReservationSummary(Reservation reservation) {
                ReservationSummary reservationSummary = ReservationSummary.builder()
                        .reservationId(reservation.getId())
                        .carName(reservation.getCar().getName())
                        .carPlate(reservation.getCar().getPlate())
                        .carType(reservation.getCar().getType())
                        .carSeatingCapacity(reservation.getCar().getSeatingCapacity())
                        .reserverName(reservation.getReserverName())
                        .reserverEmail(reservation.getUser().getEmail())
                        .reserverPhoneNumber(reservation.getReserverPhoneNumber())
                        .reserverDrivingLicenceNumber(reservation.getUser().getDrivingLicenceNumber())
                        .sourceCity(reservation.getSourceCity().getName())
                        .destinationCity(reservation.getDestinationCity().getName())
                        .pickUpAddress(reservation.getPickUpAddress())
                        .dropOffAddress(reservation.getDropOffAddress())
                        .startDateTime(String.valueOf(reservation.getStartDateTime()))
                        .endDateTime(String.valueOf(reservation.getEndDateTime()))
                        .reservationAmount(reservation.getAmountRecieved())
                        .build();
                log.info("reservation made. Details: {}", reservationSummary);
                return reservationSummary;
    }

    @Override
    public Reservation updateReservation(Long reservationId, BookingUpdationRequest bookingUpdationRequest) {
      Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new NotFound("reservation not found"));

        LocalDate updatedStartDate = bookingUpdationRequest.getStartDateTime() != null ? bookingUpdationRequest.getStartDateTime().toLocalDate() : null;
        LocalDate updatedEndDate = bookingUpdationRequest.getEndDateTime() != null ? bookingUpdationRequest.getEndDateTime().toLocalDate() : null;

      if(!isUpdationPossible(reservation.getCar(),reservation,updatedStartDate,updatedEndDate)){
          throw new BadRequestsException("The updation cannot be done.The selected car is already reserved by some other user for the selected duration.Please consider contacting customer care for the possible duration updates");
      }

      if(bookingUpdationRequest.getStartDateTime()!=null)reservation.setStartDateTime(bookingUpdationRequest.getStartDateTime());
      if(bookingUpdationRequest.getEndDateTime()!=null)reservation.setEndDateTime(bookingUpdationRequest.getEndDateTime());
      if(bookingUpdationRequest.getReserverName()!=null && !bookingUpdationRequest.getReserverName().isEmpty())reservation.setReserverName(bookingUpdationRequest.getReserverName());
      if(bookingUpdationRequest.getPhoneNumber()!=null && !bookingUpdationRequest.getPhoneNumber().isEmpty())reservation.setReserverPhoneNumber(bookingUpdationRequest.getPhoneNumber());
      if(bookingUpdationRequest.getPickUpAddress()!=null && !bookingUpdationRequest.getPickUpAddress().isEmpty())   reservation.setPickUpAddress(bookingUpdationRequest.getPickUpAddress());
      if(bookingUpdationRequest.getDropOffAddress()!=null && !bookingUpdationRequest.getDropOffAddress().isEmpty()) reservation.setDropOffAddress(bookingUpdationRequest.getDropOffAddress());

      reservationRepository.save(reservation);
      return reservation;
}

    public Boolean isUpdationPossible(Car car, Reservation reservation, LocalDate updatedStartDate,LocalDate updatedEndDate){
        List<Reservation> upcomingReservationsForTheCar = car.getReservations();
        for(Reservation currentReservation : upcomingReservationsForTheCar){
            if(currentReservation.equals(reservation))continue;

            LocalDate currentReservationStart = currentReservation.getStartDateTime().toLocalDate();
            LocalDate currentReservationEnd = currentReservation.getEndDateTime().toLocalDate();

            if((updatedStartDate.isBefore(currentReservationEnd.plusDays(1))) && (updatedEndDate.isAfter(currentReservationStart.minusDays(1)))){
                return false;
            }

        }
        return true;
    }
}
