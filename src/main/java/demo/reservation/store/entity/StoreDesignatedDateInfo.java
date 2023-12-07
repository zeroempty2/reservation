package demo.reservation.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class StoreDesignatedDateInfo {
  @Column
  private Byte openStart;

  @Column
  private Byte openEnd;
}
