package demo.reservation.reservationItem.service.interfaces;

import demo.reservation.common.dto.PageDto;
import demo.reservation.reservationItem.dto.ReservationItemAddDto;
import demo.reservation.reservationItem.dto.ReservationItemResponseDto;
import org.springframework.data.domain.Page;

public interface ReservationItemService {
  void addReservationItem(ReservationItemAddDto reservationItemAddDto,Long userId);

  Page<ReservationItemResponseDto> getReservationItems(PageDto pageDto);

}
