package demo.reservation.reservation.service;

import demo.reservation.reservation.dao.StoreReservationDayInfoRepository;
import demo.reservation.reservation.dao.StoreReservationInfoRepository;
import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreReservationServiceImpl implements StoreReservationService {
  private final StoreReservationInfoRepository storeReservationInfoRepository;
  private final StoreReservationDayInfoRepository storeReservationDayInfoRepository;


  @Override
  public List<StoreReservationDayInfoResponseDto> getStoreReservationDayInfoMonth(
      StoreReservationInfoRequestDto storeReservationInfoRequestDto, Long storeId) {
    return storeReservationInfoRepository.findStoreMonthReservationByStoreIdAndMonth(storeId,
        storeReservationInfoRequestDto.year(), storeReservationInfoRequestDto.month());
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


}
