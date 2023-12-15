package demo.reservation.reservation.dao;

import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.List;
import java.util.Optional;

public interface StoreReservationInfoRepositoryQuery {
  Optional<StoreReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
  List<StoreReservationDayInfoResponseDto> findStoreMonthReservationByStoreIdAndMonth(Long storeId,Short year,Byte month);
  boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months);
}
