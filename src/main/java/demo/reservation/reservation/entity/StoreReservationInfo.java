package demo.reservation.reservation.entity;

import demo.reservation.store.entity.Store;
import demo.reservation.util.TimeStamped;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class StoreReservationInfo extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Short years;

  @Column
  private Byte months;

  @Column
  private String storeReservationDayInfos;

  @Builder
  public StoreReservationInfo(Short years, Byte months, Store store,String storeReservationDayInfos) {
    this.years = years;
    this.months = months;
    this.store = store;
    this.storeReservationDayInfos = storeReservationDayInfos;
  }

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;


}
