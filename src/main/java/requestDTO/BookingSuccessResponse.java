package requestDTO;

import com.abg.rentalreservationservices.entity.ServicableCity;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookingSuccessResponse {
    private String carName;
    private String carPlate;
    private Integer seatingCapacity;

    private String reserverName;
    private String reserverEmail;
    private String reserverPhoneNumber;

    private String sourceCity;
    private String destinationCity;
    private String pickUpAddress;
    private String dropOffAddress;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private BigDecimal amountRecieved;
}
