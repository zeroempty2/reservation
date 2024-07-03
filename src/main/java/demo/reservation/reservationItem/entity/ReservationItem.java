package demo.reservation.reservationItem.entity;

import demo.reservation.reservation.entity.ReservationInfo;
import demo.reservation.util.TimeStamped;
import demo.reservation.util.enums.ReservationItemCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ReservationItem extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String itemName;

  @Column
  private Long ownerId;

  @Column
  private ReservationItemCategory reservationItemCategory;

  @Builder
  public ReservationItem(Long id,String itemName,Long ownerId,ReservationItemCategory reservationItemCategory){
    this.itemName = itemName;
    this.ownerId = ownerId;
    this.reservationItemCategory = reservationItemCategory;
  }

  //메서드
  public boolean isItemOwner(Long userId){
    return userId.equals(ownerId);
  }
  //연관관계
  @OneToMany(mappedBy = "reservationItem", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ReservationInfo> reservationInfos = new LinkedHashSet<>();

}
