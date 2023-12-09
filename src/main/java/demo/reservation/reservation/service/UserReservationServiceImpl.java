package demo.reservation.reservation.service;


import demo.reservation.reservation.dao.UserReservationRepository;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import demo.reservation.reservation.service.interfaces.UserReservationService;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.enums.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserReservationServiceImpl implements UserReservationService {

  private final UserService userService;
  private final StoreReservationService storeReservationService;
  private final UserReservationRepository userReservationRepository;

  @Override
  @Transactional
  public boolean requestReservation(Long userId, Long storeReservationDayInfoId) {
    User user = userService.findById(userId);

    StoreReservationDayInfo storeReservationDayInfo = storeReservationService.findStoreReservationDayInfoById(storeReservationDayInfoId);

    if(!storeReservationDayInfo.getIsPossible()) return false;

    UserReservation userReservation = UserReservation.builder()
        .reservationStatus(ReservationStatus.Processing)
        .user(user)
        .build();

    userReservationRepository.save(userReservation);

    storeReservationDayInfo.updateStoreReservationDayInfo();

    return true;
  }
}
