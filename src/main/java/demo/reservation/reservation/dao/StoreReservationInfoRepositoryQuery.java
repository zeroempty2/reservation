//package demo.reservation.reservation.dao;
//
//import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
//import demo.reservation.reservation.entity.ReservationInfo;
//import demo.reservation.reservation.entity.ReservationDayInfo;
//import java.util.Optional;
//
//public interface StoreReservationInfoRepositoryQuery {
//  Optional<ReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
//  Optional<ReservationInfo> findStoreMonthReservationByStoreIdAndMonth(Long storeId,Short year,Byte month);
//  Optional<StoreReservationInfoResponseDto> findStoreMonthReservationAndResponse(Long storeId,Short year,Byte month);
//  boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months);
//}
