package demo.reservation.reservation.dto;

import java.time.Month;
import java.util.List;
//역정규화 고려
public record AddStoreReservationDayInfoMonthRequestDto(Short years, Byte months,String dayList) {

}
