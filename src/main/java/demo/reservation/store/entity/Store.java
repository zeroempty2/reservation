package demo.reservation.store.entity;

import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.util.TimeStamped;
import demo.reservation.util.enums.ReservationPolicy;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@Entity
public class Store extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private ReservationPolicy reservationPolicy;

  @Embedded
  private StoreDesignatedDateInfo storeDesignatedDateInfo;

  //생성자
  @Builder
  public Store(ReservationPolicy reservationPolicy,StoreDesignatedDateInfo storeDesignatedDateInfo){
    this.reservationPolicy = reservationPolicy;
    this.storeDesignatedDateInfo = storeDesignatedDateInfo;
  }
  //메서드
  public void updateStoreReservationPolicy(ReservationPolicy reservationPolicy){
    this.reservationPolicy = reservationPolicy;
  }

  public void updateStoreDesignatedDateInfo(StoreDesignatedDateInfo storeDesignatedDateInfo){
    this.storeDesignatedDateInfo = storeDesignatedDateInfo;
  }

  //연관관계
  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<StoreReservationInfo> storeReservationInfos = new LinkedHashSet<>();

}