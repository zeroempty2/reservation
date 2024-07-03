package demo.reservation.reservationItem.dto;

import demo.reservation.util.enums.ReservationItemCategory;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationItemResponseDto {
public String itemName;
public LocalDateTime createdAt;
public ReservationItemCategory reservationItemCategory;

}
