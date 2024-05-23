package requestDTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    private String sourceCity;
    private String destinationCity;
    private String pickUpAddress;
    private String dropOffAddress;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer seatingCapacity;
}
