//package demo.reservation.reservation.service;
//
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import demo.reservation.reservation.dao.StoreReservationInfoRepository;
//import demo.reservation.reservation.dto.StoreReservationAddDto;
//import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
//import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
//import demo.reservation.reservation.dto.StoreReservationUpdateDto;
//import demo.reservation.reservation.entity.ReservationInfo;
//import demo.reservation.reservation.entity.ReservationDayInfo;
//import demo.reservation.reservation.service.interfaces.StoreReservationService;
//import demo.reservation.store.entity.Store;
//import demo.reservation.store.service.interfaces.StoreService;
//import java.util.Set;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class StoreReservationServiceImpl implements StoreReservationService {
//  private final StoreReservationInfoRepository storeReservationInfoRepository;
//
//  private final StoreService storeService;
//
//
////  @Override
////  public void addStoreReservationDayInfoMonth(
////      StoreReservationAddDto storeReservationAddDto,Long storeId) {
////    Store store = storeService.findById(storeId);
////
////    if(storeReservationInfoRepository.existsStoreReservationInfoByYearsAndMonths(
////        storeReservationAddDto.years(),storeReservationAddDto.months())){
////      throw new IllegalArgumentException("이미 존재하는 예약입니다");
////    }
////
////    ReservationInfo reservationInfo = ReservationInfo.builder()
////        .store(store)
////        .months(storeReservationAddDto.months())
////        .years(storeReservationAddDto.years())
////        .storeReservationDayInfos(storeReservationAddDto.StoreReservationDayinfos())
////        .build();
////
////    storeReservationInfoRepository.save(reservationInfo);
////  }
//
//  @Override
//  public void updateStoreReservationMonthInfo(StoreReservationUpdateDto storeReservationUpdateDto,
//      Long storeId) {
//    ReservationInfo reservationInfo = getStoreReservationInfo(storeId,storeReservationUpdateDto.years(),storeReservationUpdateDto.months());
//
//    Set<ReservationDayInfo> reservationDayInfos = normalizationStoreReservation(
//        reservationInfo.getStoreReservationDayInfos());
//    Set<ReservationDayInfo> newStoreReservationInfos = normalizationStoreReservation(storeReservationUpdateDto.StoreReservationDayinfo());
//
//    //기존 set과 새로운 set의 데이터를 비교해서 다른 데이터가 있으면 교체
//    newStoreReservationInfos
//        .forEach(updateReservationDayInfo -> {
//          reservationDayInfos.stream()
//              .filter(reservationDayInfo ->
//                  updateReservationDayInfo.getDays().equals(reservationDayInfo.getDays()) &&
//                      updateReservationDayInfo.getTimes().equals(reservationDayInfo.getTimes()))
//              .filter(reservationDayInfo ->
//                  !updateReservationDayInfo.getIsPossible().equals(reservationDayInfo.getIsPossible()) ||
//                      !updateReservationDayInfo.getCapacity().equals(reservationDayInfo.getCapacity()))
//              .findFirst()
//              .ifPresent(reservationDayInfo -> {
//                reservationDayInfos.remove(reservationDayInfo);
//                reservationDayInfos.add(updateReservationDayInfo);
//              });
//        });
//
//    String updateStoreReservation = toJsonStoreReservation(reservationDayInfos);
//
//    reservationInfo.update(updateStoreReservation);
//  }
//
//  @Override
//  public ReservationInfo getStoreReservationInfo(Long storeId, Short years,
//      Byte months) {
//    return storeReservationInfoRepository.findStoreMonthReservationByStoreIdAndMonth(storeId,years,months).orElseThrow(
//        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
//    );
//  }
//
//  @Override
//  public StoreReservationInfoResponseDto getStoreReservationInfos(
//       Long storeId, StoreReservationInfoRequestDto storeReservationInfoRequestDto) {
//    return storeReservationInfoRepository.findStoreMonthReservationAndResponse(storeId,storeReservationInfoRequestDto.years(),
//        storeReservationInfoRequestDto.months()).orElseThrow(
//        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
//    );
//  }
//
//
//  @Override
//  public ReservationInfo findStoreReservationInfoById(Long storeReservationInfoId) {
//    return storeReservationInfoRepository.findById(storeReservationInfoId).orElseThrow(
//        () -> new IllegalArgumentException("유효하지 않은 ID입니다")
//    );
//  }
//  @Override
//  public ReservationInfo findStoreReservationInfoByYearsAndMonths(Short years, Byte months) {
//    return storeReservationInfoRepository.findStoreReservationInfoByYearsAndMonths(years,months).orElseThrow(
//        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
//    );
//  }
//
//  @Override
//  public ReservationInfo findById(Long storeReservationInfoId) {
//    return storeReservationInfoRepository.findById(storeReservationInfoId).orElseThrow(
//        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
//    );
//  }
//
//  @Override
//  public Set<ReservationDayInfo> normalizationStoreReservation(
//      String storeReservationMonthInfo) {
//    Gson gson = new Gson();
//    //데이터 정규화
//    return gson.fromJson(storeReservationMonthInfo, new TypeToken<Set<ReservationDayInfo>>() {}.getType());
//  }
//
//  @Override
//  public String toJsonStoreReservation(Set<ReservationDayInfo> reservationDayInfos) {
//    Gson gson = new Gson();
//    return gson.toJson(reservationDayInfos);
//  }
//
//
//}
