package demo.reservation.reservation.dto;

public record StoreReservationDayInfoResponseDto(Long ReservationDayInfoId,Byte day, String time,Boolean isPossible,Integer capacity) {

}
