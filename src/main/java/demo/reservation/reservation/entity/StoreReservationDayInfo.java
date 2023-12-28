package demo.reservation.reservation.entity;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreReservationDayInfo {

  private Byte days;

  private String times;

  private Boolean isPossible;

  private Integer capacity;

  public StoreReservationDayInfo(Byte days, String times, Boolean isPossible, Integer capacity) {
    this.days = days;
    this.times = times;
    this.isPossible = isPossible;
    this.capacity = capacity;
  }

  //메서드
  public void updateStoreReservationDayInfo(){
    this.capacity -= 1;
    if(this.capacity < 1){
      this.isPossible = false;
    }
  }

  public void cancelUpdateStoreReservationDayInfo(){
    this.capacity += 1;
    if(this.capacity > 0){
      this.isPossible = true;
    }
  }

  @Override
  public String toString(){
    return "StoreReservationDayInfo{" +
        "days=" + days +
        ", times=" + times +
        ", isPossible=" + isPossible +
        ", capacity=" + capacity +
        '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StoreReservationDayInfo storeReservationDayInfo = (StoreReservationDayInfo) o;
    return Objects.equals(days, storeReservationDayInfo.days) && Objects.equals(times, storeReservationDayInfo.times) && storeReservationDayInfo.isPossible == isPossible && Objects.equals(
        storeReservationDayInfo.capacity, capacity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(days,times,isPossible,capacity);
  }
}
