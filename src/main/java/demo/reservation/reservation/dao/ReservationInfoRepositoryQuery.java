package demo.reservation.reservation.dao;

import demo.reservation.reservation.dto.ReservationInfoResponseDto;
import demo.reservation.reservation.entity.ReservationInfo;
import demo.reservation.reservation.entity.ReservationDayInfo;
import java.util.Optional;

public interface ReservationInfoRepositoryQuery {
  Optional<ReservationDayInfo> findReservationDayInfoByReservationInfoIdAndTime(Long reservationInfoId,String time);
  Optional<ReservationInfo> findMonthReservationByIdAndMonth(Long reservationItemId,Short year,Byte month);
  Optional<ReservationInfoResponseDto> findMonthReservationAndResponse(Long reservationItemId,Short year,Byte month);
  boolean existsReservationInfoByYearsAndMonths(Short years, Byte months);
}
