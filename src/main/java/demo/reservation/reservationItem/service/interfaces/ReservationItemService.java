package demo.reservation.reservationItem.service.interfaces;

import demo.reservation.common.dto.PageDto;
import demo.reservation.reservationItem.dto.ReservationItemAddDto;
import demo.reservation.reservationItem.dto.ReservationItemResponseDto;
import demo.reservation.reservationItem.entity.ReservationItem;
import org.springframework.data.domain.Page;

public interface ReservationItemService {
  void addReservationItem(ReservationItemAddDto reservationItemAddDto,Long userId);
  Page<ReservationItemResponseDto> getReservationItems(PageDto pageDto);
  ReservationItem findById(Long reservationItemId);

}
