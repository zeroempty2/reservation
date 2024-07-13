package demo.reservation.reservation.dto;

import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.util.enums.ReservationStatus;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReservationResponseDto {
  private ReservationStatus reservationStatus;
  private Short years;
  private Byte months;
  private String times;
  private Byte days;
  private Long reservationItemId;
  private LocalDateTime createdAt;

  public static UserReservationResponseDto from(UserReservation userReservation){
    return new UserReservationResponseDto(userReservation.getReservationStatus(),userReservation.getYears(),userReservation.getMonths(),userReservation.getTimes(),
    userReservation.getDays(),userReservation.getReservationItemId(),userReservation.getCreatedAt());
  }
}
