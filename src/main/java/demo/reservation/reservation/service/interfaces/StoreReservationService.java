package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.AddStoreReservationDayInfoMonthRequestDto;
import demo.reservation.reservation.dto.StoreReservationAddDto;
import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.List;
import java.util.Optional;

public interface StoreReservationService {
  void addStoreReservationDayInfoMonth(StoreReservationAddDto storeReservationAddDto,Long StoreId);
  StoreReservationInfo getStoreReservationInfoByStoreIdAndYearsAndMonths(Short years, Byte months, Long storeId);

  StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId);
  StoreReservationDayInfo findStoreReservationDayInfoByTime(String time);
  StoreReservationDayInfo findStoreReservationDayInfoById(Long storeReservationDayInfoId);
  StoreReservationDayInfo findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
  StoreReservationInfo findStoreReservationInfoByYearsAndMonths(Short years,Byte months);
  StoreReservationInfo findById(Long storeReservationInfoId);
}
