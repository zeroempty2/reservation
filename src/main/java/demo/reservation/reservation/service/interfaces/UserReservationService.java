package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.entity.UserReservation;

public interface UserReservationService {
  boolean requestReservation(Long userId,Long storeId,Long storeReservationInfoId,RequestReservationDto requestReservationDto);
  void cancelReservation(Long userId,Long userReservationId);
  UserReservation findById(Long userReservationId);
}
