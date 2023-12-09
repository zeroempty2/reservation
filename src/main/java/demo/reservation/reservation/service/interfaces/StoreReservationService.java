package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.List;
import java.util.Optional;

public interface StoreReservationService {
  List<StoreReservationDayInfoResponseDto> getStoreReservationDayInfoMonth(StoreReservationInfoRequestDto storeReservationInfoRequestDto,Long storeId);
  StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId);
  StoreReservationDayInfo findStoreReservationDayInfoByTime(String time);
  StoreReservationDayInfo findStoreReservationDayInfoById(Long storeReservationDayInfoId);
  StoreReservationDayInfo findStoreReservationDayInfoByStoreReservationInfoIdAndTime(Long storeReservationInfoId,String time);
}
