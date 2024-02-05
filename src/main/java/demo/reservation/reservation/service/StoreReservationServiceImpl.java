package demo.reservation.reservation.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import demo.reservation.reservation.dao.StoreReservationInfoRepository;
import demo.reservation.reservation.dto.StoreReservationAddDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationUpdateDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import demo.reservation.store.entity.Store;
import demo.reservation.store.service.interfaces.StoreService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReservationServiceImpl implements StoreReservationService {
  private final StoreReservationInfoRepository storeReservationInfoRepository;

  private final StoreService storeService;


  @Override
  public void addStoreReservationDayInfoMonth(
      StoreReservationAddDto storeReservationAddDto,Long storeId) {
    Store store = storeService.findById(storeId);

    if(storeReservationInfoRepository.existsStoreReservationInfoByYearsAndMonths(
        storeReservationAddDto.years(),storeReservationAddDto.months())){
      throw new IllegalArgumentException("이미 존재하는 예약입니다");
    }

    StoreReservationInfo storeReservationInfo = StoreReservationInfo.builder()
        .store(store)
        .months(storeReservationAddDto.months())
        .years(storeReservationAddDto.years())
        .storeReservationDayInfos(storeReservationAddDto.StoreReservationDayinfos())
        .build();

    storeReservationInfoRepository.save(storeReservationInfo);
  }

  @Override
  public void updateStoreReservationMonthInfo(StoreReservationUpdateDto storeReservationUpdateDto,
      Long storeId) {
    StoreReservationInfo storeReservationInfo = getStoreReservationInfo(storeId,storeReservationUpdateDto.years(),storeReservationUpdateDto.months());

    Set<StoreReservationDayInfo> storeReservationDayInfos = normalizationStoreReservation(storeReservationInfo.getStoreReservationDayInfos());
    Set<StoreReservationDayInfo> newStoreReservationInfos = normalizationStoreReservation(storeReservationUpdateDto.StoreReservationDayinfo());

    //기존 set과 새로운 set의 데이터를 비교해서 다른 데이터가 있으면 교체
    newStoreReservationInfos
        .forEach(updateReservationDayInfo -> {
          storeReservationDayInfos.stream()
              .filter(reservationDayInfo ->
                  updateReservationDayInfo.getDays().equals(reservationDayInfo.getDays()) &&
                      updateReservationDayInfo.getTimes().equals(reservationDayInfo.getTimes()))
              .filter(reservationDayInfo ->
                  !updateReservationDayInfo.getIsPossible().equals(reservationDayInfo.getIsPossible()) ||
                      !updateReservationDayInfo.getCapacity().equals(reservationDayInfo.getCapacity()))
              .findFirst()
              .ifPresent(reservationDayInfo -> {
                storeReservationDayInfos.remove(reservationDayInfo);
                storeReservationDayInfos.add(updateReservationDayInfo);
              });
        });

    String updateStoreReservation = toJsonStoreReservation(storeReservationDayInfos);

    storeReservationInfo.update(updateStoreReservation);
  }

  @Override
  public StoreReservationInfo getStoreReservationInfo(Long storeId, Short years,
      Byte months) {
    return storeReservationInfoRepository.findStoreMonthReservationByStoreIdAndMonth(storeId,years,months).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }

  @Override
  public StoreReservationInfoResponseDto getStoreReservationInfos(
       Long storeId, StoreReservationInfoRequestDto storeReservationInfoRequestDto) {
    return storeReservationInfoRepository.findStoreMonthReservationAndResponse(storeId,storeReservationInfoRequestDto.years(),
        storeReservationInfoRequestDto.months()).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }


  @Override
  public StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId) {
    return storeReservationInfoRepository.findById(storeReservationInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 ID입니다")
    );
  }
  @Override
  public StoreReservationInfo findStoreReservationInfoByYearsAndMonths(Short years, Byte months) {
    return storeReservationInfoRepository.findStoreReservationInfoByYearsAndMonths(years,months).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  public StoreReservationInfo findById(Long storeReservationInfoId) {
    return storeReservationInfoRepository.findById(storeReservationInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  public Set<StoreReservationDayInfo> normalizationStoreReservation(
      String storeReservationMonthInfo) {
    Gson gson = new Gson();
    //데이터 정규화
    return gson.fromJson(storeReservationMonthInfo, new TypeToken<Set<StoreReservationDayInfo>>() {}.getType());
  }

  @Override
  public String toJsonStoreReservation(Set<StoreReservationDayInfo> storeReservationDayInfos) {
    Gson gson = new Gson();
    return gson.toJson(storeReservationDayInfos);
  }


}
