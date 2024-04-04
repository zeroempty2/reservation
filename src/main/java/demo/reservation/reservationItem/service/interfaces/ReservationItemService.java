package demo.reservation.reservationItem.service.interfaces;

import demo.reservation.reservationItem.dto.ReservationItemAddDto;

public interface ReservationItemService {
  void addReservationItem(ReservationItemAddDto reservationItemAddDto,Long userId);
}
