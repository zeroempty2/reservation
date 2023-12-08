package demo.reservation.reservation.dao;

import demo.reservation.reservation.entity.StoreReservationDayInfo;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StoreReservationDayInfoRepository extends Repository<StoreReservationDayInfo,Long> {
  Optional<StoreReservationDayInfo> findByTime(String time);
}