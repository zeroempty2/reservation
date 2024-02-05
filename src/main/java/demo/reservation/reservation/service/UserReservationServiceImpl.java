package demo.reservation.reservation.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import demo.reservation.reservation.dao.UserReservationRepository;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.dto.ReservationCompleteDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import demo.reservation.reservation.service.interfaces.UserReservationService;
import demo.reservation.store.entity.Store;
import demo.reservation.store.service.interfaces.StoreService;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.enums.ReservationStatus;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserReservationServiceImpl implements UserReservationService {

  private final UserService userService;
  private final StoreReservationService storeReservationService;
  private final UserReservationRepository userReservationRepository;
  private final StoreService storeService;
  //달 정보 조회할때 String형식의 역정규화된 형태 그대로 전송
  //일 정보 가능/ 불가 조회할때 정규화 시켜 확인 후 전송
 //만약 데이터 변경이 이뤄져야 할 경우 정규화 후 수정/ 다시 역정규화 시켜 저장시켜야함
  @Override
  @Transactional
  public boolean requestReservation(Long userId, Long storeId,Long storeReservationInfoId,RequestReservationDto requestReservationDto) {
    User user = userService.findById(userId);
    StoreReservationInfo storeReservationInfo = storeReservationService.findById(storeReservationInfoId);
    Store store = storeService.findById(storeId);
    String updateReservationDayInfo = normalizationStoreReservationDayInfos(storeReservationInfo.getStoreReservationDayInfos(),requestReservationDto);
    if(updateReservationDayInfo.equals("imPossible")) return false;
    UserReservation reservation = UserReservation.builder()
        .store(store)
        .user(user)
        .years(storeReservationInfo.getYears())
        .times(requestReservationDto.times())
        .months(storeReservationInfo.getMonths())
        .days(requestReservationDto.days())
        .reservationStatus(ReservationStatus.Processing)
        .build();
    storeReservationInfo.update(updateReservationDayInfo);
    userReservationRepository.save(reservation);
    return true;
  }

  @Override
  @Transactional
  public void cancelReservation(Long userId, Long userReservationId) {
    User user = userService.findById(userId);
    UserReservation reservation = findById(userReservationId);
    Store store = reservation.getStore();
    Short years = reservation.getYears();
    Byte months = reservation.getMonths();
    Byte days = reservation.getDays();
    String times = reservation.getTimes();
    if(!reservation.isReservationOwner(user.getId())){
      throw new IllegalArgumentException("사용자 정보가 일치하지 않습니다");
    }
    reservation.updateUserReservation(ReservationStatus.Cancelled);
    StoreReservationInfo storeReservationInfo = storeReservationService.getStoreReservationInfo(store.getId(),years,months);
    String cancelUpdateReservationDayInfo = normalizationAndCancelStoreReservationDayInfos(storeReservationInfo.getStoreReservationDayInfos(),days,times);
    storeReservationInfo.update(cancelUpdateReservationDayInfo);
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
    if(!userReservation.getStore().isStoreManager(userId)){
      throw new IllegalArgumentException("점주가 아닙니다");
    }
    updateUserReservation(userReservation,ReservationStatus.Completed);
  }

  private String normalizationStoreReservationDayInfos(String storeReservationDayInfos,RequestReservationDto requestReservationDto){
    Gson gson = new Gson();
    //데이터 정규화
    Set<StoreReservationDayInfo> storeReservationDayInfoSet = gson.fromJson(storeReservationDayInfos, new TypeToken<Set<StoreReservationDayInfo>>() {}.getType());

    StoreReservationDayInfo storeReservationDayInfo = storeReservationDayInfoSet.stream()
        .peek(info -> System.out.println("Comparing with: " + info))
        .filter(info -> Objects.equals(info.getDays(), requestReservationDto.days()) && info.getTimes().equals(requestReservationDto.times()) && info.getIsPossible())
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("유효한 정보가 아닙니다")
        );

    storeReservationDayInfoSet.remove(storeReservationDayInfo);
    storeReservationDayInfo.updateStoreReservationDayInfo();
    storeReservationDayInfoSet.add(storeReservationDayInfo);
    return gson.toJson(storeReservationDayInfoSet);
  }

  private String normalizationAndCancelStoreReservationDayInfos(String storeReservationDayInfos,Byte days,String times){
    Gson gson = new Gson();
    //데이터 정규화
    Set<StoreReservationDayInfo> storeReservationDayInfoSet = gson.fromJson(storeReservationDayInfos, new TypeToken<Set<StoreReservationDayInfo>>() {}.getType());

    StoreReservationDayInfo foundInfo = storeReservationDayInfoSet.stream()
        .filter(info -> info.getDays().equals(days) && info.getTimes().equals(times))
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("유효한 정보가 아닙니다")
        );

    storeReservationDayInfoSet.remove(foundInfo);
    foundInfo.cancelUpdateStoreReservationDayInfo();
    storeReservationDayInfoSet.add(foundInfo);
    return gson.toJson(storeReservationDayInfoSet);
  }

}
