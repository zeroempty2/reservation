package demo.reservation.reservation.dao;

import demo.reservation.common.dto.PageDto;
import demo.reservation.reservation.dto.UserReservationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserReservationRepositoryQuery {
  Page<UserReservationResponseDto> findUserReservationByUserId(Long userId, PageDto pageDto);
}
