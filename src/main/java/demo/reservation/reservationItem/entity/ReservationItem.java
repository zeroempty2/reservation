package demo.reservation.reservationItem.entity;

import demo.reservation.reservation.entity.ReservationInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity
@NoArgsConstructor
@Getter
public class ReservationItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String itemName;

  @Builder
  public ReservationItem(String itemName){
    this.itemName = itemName;
  }
  //연관관계
  @OneToMany(mappedBy = "reservationItem", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ReservationInfo> reservationInfos = new LinkedHashSet<>();
}
