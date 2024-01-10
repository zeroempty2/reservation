package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.StoreReservationAddDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.entity.StoreReservationInfo;

public interface StoreReservationService {
  void addStoreReservationDayInfoMonth(StoreReservationAddDto storeReservationAddDto,Long StoreId);
  StoreReservationInfo getStoreReservationInfoByStoreIdAndYearsAndMonths(Short years, Byte months, Long storeId);
  StoreReservationInfoResponseDto getStoreReservationInfo(StoreReservationInfoRequestDto storeReservationInfoRequestDto,Long storeId);
  StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId);
  StoreReservationInfo findStoreReservationInfoByYearsAndMonths(Short years,Byte months);
  StoreReservationInfo findById(Long storeReservationInfoId);
}
