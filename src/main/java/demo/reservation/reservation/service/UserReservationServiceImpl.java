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
  //달 정보 조회할때 String형식의 역정규화된 형태 그대로 전송
  //일 정보 가능/ 불가 조회할때 정규화 시켜 확인 후 전송
 //만약 데이터 변경이 이뤄져야 할 경우 정규화 후 수정/ 다시 역정규화 시켜 저장시켜야함
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
