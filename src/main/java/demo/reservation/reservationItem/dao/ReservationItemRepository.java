package demo.reservation.reservationItem.dao;

import demo.reservation.reservationItem.entity.ReservationItem;
import org.springframework.data.repository.Repository;

public interface ReservationItemRepository extends Repository<ReservationItem,Long> {
  void save(ReservationItem reservationItem);
}
