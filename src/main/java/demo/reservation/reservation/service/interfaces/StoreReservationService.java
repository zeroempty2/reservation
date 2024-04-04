//package demo.reservation.reservation.service.interfaces;
//
//import demo.reservation.reservation.dto.StoreReservationAddDto;
//import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
//import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
//import demo.reservation.reservation.dto.StoreReservationUpdateDto;
//import demo.reservation.reservation.entity.ReservationInfo;
//import demo.reservation.reservation.entity.ReservationDayInfo;
//import java.util.Set;
//
//public interface StoreReservationService {
//  void addStoreReservationDayInfoMonth(StoreReservationAddDto storeReservationAddDto,Long StoreId);
//  void updateStoreReservationMonthInfo(StoreReservationUpdateDto storeReservationUpdateDto,Long StoreId);
//  ReservationInfo getStoreReservationInfo(Long storeId, Short years, Byte months);
//  StoreReservationInfoResponseDto getStoreReservationInfos(Long storeId, StoreReservationInfoRequestDto storeReservationInfoRequestDto);
//  ReservationInfo findStoreReservationInfoById(Long storeReservationInfoId);
//  ReservationInfo findStoreReservationInfoByYearsAndMonths(Short years,Byte months);
//  ReservationInfo findById(Long storeReservationInfoId);
//  Set<ReservationDayInfo> normalizationStoreReservation(String storeReservationMonthInfo);
//  String toJsonStoreReservation(Set<ReservationDayInfo> reservationDayInfos);
//}
