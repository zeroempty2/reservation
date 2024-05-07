package demo.reservation.reservation.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import demo.reservation.reservation.dao.ReservationInfoRepository;
import demo.reservation.reservation.dto.ReservationAddDto;
import demo.reservation.reservation.dto.ReservationInfoRequestDto;
import demo.reservation.reservation.dto.ReservationInfoResponseDto;
import demo.reservation.reservation.dto.ReservationUpdateDto;
import demo.reservation.reservation.entity.ReservationDayInfo;
import demo.reservation.reservation.entity.ReservationInfo;
import demo.reservation.reservation.service.interfaces.ReservationService;
import demo.reservation.reservationItem.entity.ReservationItem;
import demo.reservation.reservationItem.service.interfaces.ReservationItemService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final ReservationInfoRepository reservationInfoRepository;
  private final ReservationItemService reservationItemService;


  @Override
  @Transactional
  public void addReservationDayInfoMonth(
      ReservationAddDto reservationAddDto,Long reservationItemId) {
    ReservationItem reservationItem = reservationItemService.findById(reservationItemId);

    if(reservationInfoRepository.existsReservationInfoByYearsAndMonths(
        reservationAddDto.years(),reservationAddDto.months())){
      throw new IllegalArgumentException("이미 존재하는 예약입니다");
    }

    ReservationInfo reservationInfo = ReservationInfo.builder()
        .reservationItem(reservationItem)
        .months(reservationAddDto.months())
        .years(reservationAddDto.years())
        .reservationDayInfos(reservationAddDto.reservationDayinfos())
        .build();

    reservationInfoRepository.save(reservationInfo);
  }

  @Override
  @Transactional
  public void updateReservationMonthInfo(ReservationUpdateDto reservationUpdateDto,
      Long reservationItemId) {
    ReservationInfo reservationInfo = getReservationInfo(reservationItemId, reservationUpdateDto.years(),
        reservationUpdateDto.months());

    Set<ReservationDayInfo> reservationDayInfos = normalizationReservation(
        reservationInfo.getReservationDayInfos());
    Set<ReservationDayInfo> newReservationInfos = normalizationReservation(
        reservationUpdateDto.ReservationDayinfo());

    //기존 set과 새로운 set의 데이터를 비교해서 다른 데이터가 있으면 교체
    newReservationInfos
        .forEach(updateReservationDayInfo -> {
          reservationDayInfos.stream()
              .filter(reservationDayInfo ->
                  updateReservationDayInfo.getDays().equals(reservationDayInfo.getDays()) &&
                      updateReservationDayInfo.getTimes().equals(reservationDayInfo.getTimes()))
              .filter(reservationDayInfo ->
                  !updateReservationDayInfo.getIsPossible().equals(reservationDayInfo.getIsPossible()) ||
                      !updateReservationDayInfo.getCapacity().equals(reservationDayInfo.getCapacity()))
              .findFirst()
              .ifPresent(reservationDayInfo -> {
                reservationDayInfos.remove(reservationDayInfo);
                reservationDayInfos.add(updateReservationDayInfo);
              });
        });

    String updateReservation = toJsonReservation(reservationDayInfos);

    reservationInfo.update(updateReservation);
  }

  @Override
  @Transactional
  public ReservationInfo getReservationInfo(Long reservationItemId, Short years,
      Byte months) {
    return reservationInfoRepository.findMonthReservationByIdAndMonth(reservationItemId,years,months).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }

  @Override
  @Transactional
  public ReservationInfoResponseDto getReservationInfos(
       Long reservationItemId, ReservationInfoRequestDto reservationInfoRequestDto) {
    return reservationInfoRepository.findMonthReservationAndResponse(reservationItemId,
        reservationInfoRequestDto.years(),
        reservationInfoRequestDto.months()).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }


  @Override
  @Transactional
  public ReservationInfo findReservationInfoById(Long reservationInfoId) {
    return reservationInfoRepository.findById(reservationInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 ID입니다")
    );
  }
  @Override
  @Transactional
  public ReservationInfo findReservationInfoByYearsAndMonths(Short years, Byte months) {
    return reservationInfoRepository.findReservationInfoByYearsAndMonths(years,months).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  @Transactional
  public ReservationInfo findById(Long reservationInfoId) {
    return reservationInfoRepository.findById(reservationInfoId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다")
    );
  }

  @Override
  @Transactional
  public Set<ReservationDayInfo> normalizationReservation(
      String reservationMonthInfo) {
    Gson gson = new Gson();
    //데이터 정규화
    return gson.fromJson(reservationMonthInfo, new TypeToken<Set<ReservationDayInfo>>() {}.getType());
  }

  @Override
  @Transactional
  public String toJsonReservation(Set<ReservationDayInfo> reservationDayInfos) {
    Gson gson = new Gson();
    return gson.toJson(reservationDayInfos);
  }


}
