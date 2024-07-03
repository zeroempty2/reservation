package demo.reservation.reservationItem.dao;

import demo.reservation.common.dto.PageDto;
import demo.reservation.reservationItem.dto.ReservationItemResponseDto;
import org.springframework.data.domain.Page;

public interface ReservationItemRepositoryQuery {
  Page<ReservationItemResponseDto> getReservationItems(PageDto pageDto);

}
