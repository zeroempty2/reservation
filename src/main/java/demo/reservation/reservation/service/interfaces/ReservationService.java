package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.ReservationAddDto;
import demo.reservation.reservation.dto.ReservationUpdateDto;
import demo.reservation.reservation.dto.ReservationInfoRequestDto;
import demo.reservation.reservation.dto.ReservationInfoResponseDto;
import demo.reservation.reservation.entity.ReservationDayInfo;
import demo.reservation.reservation.entity.ReservationInfo;
import java.util.Set;

public interface ReservationService {
  void addReservationDayInfoMonth(ReservationAddDto reservationAddDto,Long reservationItemId);
  void updateReservationMonthInfo(ReservationUpdateDto reservationUpdateDto,Long reservationItemId);
  ReservationInfo getReservationInfo(Long reservationItemId, Short years, Byte months);
  ReservationInfoResponseDto getReservationInfos(Long reservationItemId, ReservationInfoRequestDto reservationInfoRequestDto);
  ReservationInfo findReservationInfoById(Long reservationInfoId);
  ReservationInfo findReservationInfoByYearsAndMonths(Short years,Byte months);
  ReservationInfo findById(Long reservationInfoId);
  Set<ReservationDayInfo> normalizationReservation(String ReservationMonthInfo);
  String toJsonReservation(Set<ReservationDayInfo> reservationDayInfos);
}
