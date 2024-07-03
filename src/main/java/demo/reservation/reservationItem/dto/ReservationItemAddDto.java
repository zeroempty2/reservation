package demo.reservation.reservationItem.dto;

import demo.reservation.util.enums.ReservationItemCategory;

public record ReservationItemAddDto(String itemName, ReservationItemCategory reservationItemCategory) {

}
