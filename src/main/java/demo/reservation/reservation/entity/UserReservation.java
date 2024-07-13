package demo.reservation.reservation.entity;


import demo.reservation.user.entity.User;
import demo.reservation.util.TimeStamped;
import demo.reservation.util.enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserReservation extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private ReservationStatus reservationStatus;

  @Column
  private Short years;

  @Column
  private Byte months;

  @Column
  private String times;

  @Column
  private Byte days;

  @Column
  private Long reservationItemId;

  //생성자
  @Builder
  public UserReservation(ReservationStatus reservationStatus, Short years, Byte months, String times, User user,Byte days, Long reservationItemId) {
    this.reservationStatus = reservationStatus;
    this.years = years;
    this.months = months;
    this.times = times;
    this.user = user;
    this.days = days;
    this.reservationItemId = reservationItemId;
  }

  //메서드
  public void updateUserReservation(ReservationStatus reservationStatus){
    this.reservationStatus = reservationStatus;
  }

  public boolean isReservationOwner(Long userId){
    return Objects.equals(this.user.getId(), userId);
  }

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;


}
