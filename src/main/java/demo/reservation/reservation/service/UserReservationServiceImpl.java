package demo.reservation.reservation.service;


import demo.reservation.common.dto.PageDto;
import demo.reservation.reservation.dao.UserReservationRepository;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.dto.ReservationCompleteDto;
import demo.reservation.reservation.dto.UserReservationResponseDto;
import demo.reservation.reservation.entity.ReservationDayInfo;
import demo.reservation.reservation.entity.ReservationInfo;
import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.reservation.service.interfaces.ReservationService;
import demo.reservation.reservation.service.interfaces.UserReservationService;
import demo.reservation.reservationItem.dao.ReservationItemRepository;
import demo.reservation.reservationItem.entity.ReservationItem;
import demo.reservation.reservationItem.service.interfaces.ReservationItemService;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.enums.ReservationStatus;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserReservationServiceImpl implements UserReservationService {

  private final UserService userService;
  private final ReservationService reservationService;
  private final UserReservationRepository userReservationRepository;
  private final ReservationItemService reservationItemService;

  //달 정보 조회할때 String형식의 역정규화된 형태 그대로 전송
  //일 정보 가능/ 불가 조회할때 정규화 시켜 확인 후 전송
 //만약 데이터 변경이 이뤄져야 할 경우 정규화 후 수정/ 다시 역정규화 시켜 저장시켜야함
  @Override
  @Transactional
  public boolean requestReservation(Long userId, Long storeId,Long storeReservationInfoId,RequestReservationDto requestReservationDto) {
    User user = userService.findById(userId);
    ReservationInfo reservationInfo = reservationService.findById(storeReservationInfoId);
    ReservationItem reservationItem = reservationInfo.getReservationItem();
    String updateReservationDayInfo = updateReservationDayInfos(reservationInfo.getReservationDayInfos(),requestReservationDto);

    if(updateReservationDayInfo.equals("imPossible")) return false;
    UserReservation reservation = UserReservation.builder()
        .reservationItemId(reservationItem.getId())
        .user(user)
        .years(reservationInfo.getYears())
        .times(requestReservationDto.times())
        .months(reservationInfo.getMonths())
        .days(requestReservationDto.days())
        .reservationStatus(ReservationStatus.Processing)
        .build();
    reservationInfo.update(updateReservationDayInfo);
    userReservationRepository.save(reservation);

    return true;
  }

  @Override
  @Transactional
  public void cancelReservation(Long userId, Long userReservationId) {
    User user = userService.findById(userId);
    UserReservation reservation = findById(userReservationId);
    ReservationItem reservationItem = reservationItemService.findById(reservation.getReservationItemId());
    Short years = reservation.getYears();
    Byte months = reservation.getMonths();
    Byte days = reservation.getDays();
    String times = reservation.getTimes();

    if(!reservation.isReservationOwner(user.getId())){
      throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다");
    }

    reservation.updateUserReservation(ReservationStatus.Canceled);

    ReservationInfo reservationInfo = reservationService.getReservationInfo(reservation.getReservationItemId(),years,months);

    String cancelUpdateReservationDayInfo = normalizationAndCancelReservationDayInfos(
        reservationInfo.getReservationDayInfos(),days,times);

    reservationInfo.update(cancelUpdateReservationDayInfo);
  }

  @Override
  public UserReservation findById(Long userReservationId) {
    return userReservationRepository.findById(userReservationId).orElseThrow(
        () -> new IllegalArgumentException("유효한 id가 아닙니다")
    );
  }

  @Override
  public void updateUserReservation(UserReservation userReservation, ReservationStatus reservationStatus) {
    userReservation.updateUserReservation(reservationStatus);
  }

  @Override
  public void completeReservation(ReservationCompleteDto reservationCompleteDto, Long userId) {
    UserReservation userReservation = findById(reservationCompleteDto.userReservationId());
    ReservationItem reservationItem = reservationItemService.findById(userReservation.getReservationItemId());

    if(!reservationItem.isItemOwner(userId)){
      throw new IllegalArgumentException("권한이 없습니다");
    }

    updateUserReservation(userReservation,ReservationStatus.Completed);
  }

  @Override
  public Page<UserReservationResponseDto> getUserReservations(Long userId, PageDto pageDto) {
    return userReservationRepository.findUserReservationByUserId(userId,pageDto);
  }

  private String updateReservationDayInfos(String reservationDayInfos,RequestReservationDto requestReservationDto){
    Set<ReservationDayInfo> reservationDayInfoSet = reservationService.normalizationReservation(reservationDayInfos);

    ReservationDayInfo reservationDayInfo = reservationDayInfoSet.stream()
        .peek(info -> System.out.println("Comparing with: " + info))
        .filter(info -> Objects.equals(info.getDays(), requestReservationDto.days()) && info.getTimes().equals(requestReservationDto.times()) && info.getIsPossible())
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("유효한 정보가 아닙니다")
        );

    reservationDayInfoSet.remove(reservationDayInfo);
    reservationDayInfo.updateStoreReservationDayInfo();
    reservationDayInfoSet.add(reservationDayInfo);
    return reservationService.toJsonReservation(reservationDayInfoSet);
  }

  private String normalizationAndCancelReservationDayInfos(String storeReservationDayInfos,Byte days,String times){
    Set<ReservationDayInfo> reservationDayInfoSet = reservationService.normalizationReservation(storeReservationDayInfos);

    ReservationDayInfo foundInfo = reservationDayInfoSet.stream()
        .filter(info -> info.getDays().equals(days) && info.getTimes().equals(times))
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("유효한 정보가 아닙니다")
        );

    reservationDayInfoSet.remove(foundInfo);
    foundInfo.cancelUpdateStoreReservationDayInfo();
    reservationDayInfoSet.add(foundInfo);
    return reservationService.toJsonReservation(reservationDayInfoSet);
  }

}
