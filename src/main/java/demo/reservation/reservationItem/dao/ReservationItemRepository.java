package demo.reservation.reservationItem.dao;

import demo.reservation.reservationItem.entity.ReservationItem;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReservationItemRepository extends Repository<ReservationItem,Long>,ReservationItemRepositoryQuery {
  void save(ReservationItem reservationItem);

  Optional<ReservationItem> findById(Long reservationItemId);
}
