package demo.reservation.reservation.entity;

import demo.reservation.user.entity.User;
import demo.reservation.util.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserReservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private ReservationStatus reservationStatus;

  //생성자
  @Builder
  public UserReservation(ReservationStatus reservationStatus){
    this.reservationStatus = reservationStatus;
  }

  //메서드
  public void updateUserReservation(ReservationStatus reservationStatus){
    this.reservationStatus = reservationStatus;
  }

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

}
