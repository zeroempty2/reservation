package demo.reservation.reservation.dao;

import demo.reservation.reservation.entity.StoreReservationDayInfo;
import java.util.Optional;

public interface StoreReservationInfoRepositoryQuery {
  Optional<StoreReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
}
