package demo.reservation.reservation.service;

import demo.reservation.reservation.dao.StoreReservationDayInfoRepository;
import demo.reservation.reservation.dao.StoreReservationInfoRepository;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreReservationServiceImpl implements StoreReservationService {
  private final StoreReservationInfoRepository storeReservationInfoRepository;
  private final StoreReservationDayInfoRepository storeReservationDayInfoRepository;


  @Override
  public StoreReservationInfo findStoreReservationInfoById(Long storeReservationInfoId) {
    return storeReservationInfoRepository.findById(storeReservationInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 ID입니다")
    );
  }

  @Override
  public StoreReservationDayInfo findStoreReservationDayInfoByTime(String time) {
    return storeReservationDayInfoRepository.findByTime(time).orElseThrow(
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
