package demo.reservation.store.entity;

import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.util.TimeStamped;
import jakarta.persistence.CascadeType;
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

  private Long storeManagerId;

  //생성자
  @Builder
  public Store(Long storeManagerId){
    this.storeManagerId = storeManagerId;
  }

  //메서드

  public boolean isStoreManager(Long userId){
    return this.storeManagerId.equals(userId);
  }
  

  //연관관계
  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<StoreReservationInfo> storeReservationInfos = new LinkedHashSet<>();

}
