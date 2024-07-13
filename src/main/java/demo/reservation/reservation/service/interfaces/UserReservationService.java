package demo.reservation.reservation.service.interfaces;

import demo.reservation.common.dto.PageDto;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.dto.ReservationCompleteDto;
import demo.reservation.reservation.dto.UserReservationResponseDto;
import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.util.enums.ReservationStatus;
import org.springframework.data.domain.Page;

public interface UserReservationService {
  boolean requestReservation(Long userId,Long storeId,Long storeReservationInfoId,RequestReservationDto requestReservationDto);
  void cancelReservation(Long userId,Long userReservationId);
  UserReservation findById(Long userReservationId);
  void updateUserReservation(UserReservation userReservation,ReservationStatus reservationStatus);
  void completeReservation(ReservationCompleteDto reservationCompleteDto,Long userId);
  Page<UserReservationResponseDto> getUserReservations(Long userI, PageDto pageDto);
}
