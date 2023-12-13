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


//연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id")
  private Store store;

  @OneToMany(mappedBy = "storeReservationInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<StoreReservationDayInfo> storeReservationDayInfos = new LinkedHashSet<>();
}
