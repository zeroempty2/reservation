package demo.reservation.reservation.entity;

import demo.reservation.util.TimeStamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class StoreReservationDayInfo extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Byte days;

  @Column
  private String times;

  @Column
  private Boolean isPossible;

  @Column
  private Integer capacity;

  public StoreReservationDayInfo(Byte days, String times, Boolean isPossible, Integer capacity,
      StoreReservationInfo storeReservationInfo) {
    this.days = days;
    this.times = times;
    this.isPossible = isPossible;
    this.capacity = capacity;
    this.storeReservationInfo = storeReservationInfo;
  }

  //메서드
  public void updateStoreReservationDayInfo(){
    this.capacity -= 1;
    if(capacity < 1){
      this.isPossible = false;
    }
  }

//연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "storeReservationInfo_id")
  private StoreReservationInfo storeReservationInfo;
}
