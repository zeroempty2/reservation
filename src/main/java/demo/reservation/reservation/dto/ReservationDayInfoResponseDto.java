package demo.reservation.reservation.dto;

public record ReservationDayInfoResponseDto(Long ReservationDayInfoId, Byte day, String time, Boolean isPossible, Integer capacity) {

}
