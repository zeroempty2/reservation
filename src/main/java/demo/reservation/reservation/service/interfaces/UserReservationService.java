//package demo.reservation.reservation.service.interfaces;
//
//import demo.reservation.reservation.dto.RequestReservationDto;
//import demo.reservation.reservation.dto.ReservationCompleteDto;
//import demo.reservation.reservation.entity.UserReservation;
//import demo.reservation.util.enums.ReservationStatus;
//
//public interface UserReservationService {
//  boolean requestReservation(Long userId,Long storeId,Long storeReservationInfoId,RequestReservationDto requestReservationDto);
//  void cancelReservation(Long userId,Long userReservationId);
//  UserReservation findById(Long userReservationId);
//  void updateUserReservation(UserReservation userReservation,ReservationStatus reservationStatus);
//  void completeReservation(ReservationCompleteDto reservationCompleteDto,Long userId);
//}
