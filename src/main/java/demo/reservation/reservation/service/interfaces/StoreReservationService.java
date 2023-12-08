package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.Optional;

public interface StoreReservationService {
  StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId);
  StoreReservationDayInfo findStoreReservationDayInfoByTime(String time);
  StoreReservationDayInfo findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
}
