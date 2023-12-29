package demo.reservation.reservation.dao;

import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.Optional;

public interface StoreReservationInfoRepositoryQuery {
  Optional<StoreReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
  StoreReservationInfo findStoreMonthReservationByStoreIdAndMonth(Long storeId,Short year,Byte month);
  StoreReservationInfoResponseDto findStoreMonthReservationAndResponse(Long storeId,Short year,Byte month);
  boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months);
}
