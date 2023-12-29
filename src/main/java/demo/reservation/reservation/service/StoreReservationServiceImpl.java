package demo.reservation.reservation.service;

import demo.reservation.reservation.dao.StoreReservationDayInfoRepository;
import demo.reservation.reservation.dao.StoreReservationInfoRepository;
import demo.reservation.reservation.dto.AddStoreReservationDayInfoMonthRequestDto;
import demo.reservation.reservation.dto.StoreReservationAddDto;
import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import demo.reservation.store.entity.Store;
import demo.reservation.store.service.interfaces.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReservationServiceImpl implements StoreReservationService {
  private final StoreReservationInfoRepository storeReservationInfoRepository;
  private final StoreReservationDayInfoRepository storeReservationDayInfoRepository;
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
  public StoreReservationInfo getStoreReservationInfoByStoreIdAndYearsAndMonths(Short years,
      Byte months, Long storeId) {
    return storeReservationInfoRepository.findStoreMonthReservationByStoreIdAndMonth(storeId,years,months);
  }

  @Override
  public StoreReservationInfoResponseDto getStoreReservationInfo(
      StoreReservationInfoRequestDto storeReservationInfoRequestDto, Long storeId) {
    return storeReservationInfoRepository.findStoreMonthReservationAndResponse(storeId,storeReservationInfoRequestDto.year(),
        storeReservationInfoRequestDto.month());
  }


  @Override
  public StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId) {
    return storeReservationInfoRepository.findById(storeReservationInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 ID입니다")
    );
  }

  @Override
  public StoreReservationDayInfo findStoreReservationDayInfoByTime(String time) {
    return storeReservationDayInfoRepository.findByTimes(time).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 시간입니다")
    );
  }

  @Override
  public StoreReservationDayInfo findStoreReservationDayInfoById(Long storeReservationDayInfoId) {
    return storeReservationDayInfoRepository.findById(storeReservationDayInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 시간입니다")
    );
  }

  @Override
  public StoreReservationDayInfo findStoreReservationDayInfoByStoreReservationInfoIdAndTime(
      Long storeReservationInfoId, String time) {
    return storeReservationInfoRepository.findStoreReservationDayInfoByStoreReservationInfoIdAndTime(storeReservationInfoId,time).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
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


}
