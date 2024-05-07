package demo.reservation.reservation.entity;

import demo.reservation.reservationItem.entity.ReservationItem;
import demo.reservation.util.TimeStamped;
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
public class ReservationInfo extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Short years;

  @Column
  private Byte months;

  @Column
  private String reservationDayInfos;

  @Builder
  public ReservationInfo(Short years, Byte months, ReservationItem reservationItem,String reservationDayInfos) {
    this.years = years;
    this.months = months;
    this.reservationItem = reservationItem;
    this.reservationDayInfos = reservationDayInfos;
  }

  //메서드
  public void update(String storeReservationDayInfos){
    this.reservationDayInfos = storeReservationDayInfos;
  }

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reservationItem_id")
  private ReservationItem reservationItem;


}
