package demo.reservation.reservation.service.interfaces;

import demo.reservation.reservation.dto.RequestReservationDto;

public interface UserReservationService {
  boolean requestReservation(RequestReservationDto requestReservationDto,Long userId,Long storeReservationInfoId);
}