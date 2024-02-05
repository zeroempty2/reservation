package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.StoreReservationAddDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationUpdateDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.Set;

public interface StoreReservationService {
  void addStoreReservationDayInfoMonth(StoreReservationAddDto storeReservationAddDto,Long StoreId);
  void updateStoreReservationMonthInfo(StoreReservationUpdateDto storeReservationUpdateDto,Long StoreId);
  StoreReservationInfo getStoreReservationInfo(Long storeId, Short years, Byte months);
  StoreReservationInfoResponseDto getStoreReservationInfos(Long storeId, StoreReservationInfoRequestDto storeReservationInfoRequestDto);
  StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId);
  StoreReservationInfo findStoreReservationInfoByYearsAndMonths(Short years,Byte months);
  StoreReservationInfo findById(Long storeReservationInfoId);
  Set<StoreReservationDayInfo> normalizationStoreReservation(String storeReservationMonthInfo);
  String toJsonStoreReservation(Set<StoreReservationDayInfo> storeReservationDayInfos);
}
