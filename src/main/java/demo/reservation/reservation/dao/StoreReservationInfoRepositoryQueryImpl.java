package demo.reservation.reservation.dao;



import static demo.reservation.reservation.entity.QReservationInfo.reservationInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.entity.ReservationDayInfo;
import demo.reservation.reservation.entity.ReservationInfo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreReservationInfoRepositoryQueryImpl implements StoreReservationInfoRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional
  public Optional<ReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(
      Long storeReservationInfoId, String time) {
    return Optional.empty();
  }

  @Override
  @Transactional
  public Optional<ReservationInfo> findStoreMonthReservationByStoreIdAndMonth(
      Long storeId, Short year, Byte month) {
    return Optional.ofNullable(jpaQueryFactory
        .select(
            reservationInfo
        )
        .from(reservationInfo)
        .where(reservationInfo.reservationItem.id.eq(storeId)
            , reservationInfo.years.eq(year)
            , reservationInfo.months.eq(month)
        )
        .leftJoin(reservationInfo.reservationItem)
        .fetchOne());
  }

  @Override
  @Transactional
  public Optional<StoreReservationInfoResponseDto> findStoreMonthReservationAndResponse(
      Long storeId, Short year, Byte month) {
    return Optional.ofNullable(jpaQueryFactory
        .select(
            Projections.constructor(
                StoreReservationInfoResponseDto.class
                , reservationInfo.id
                , reservationInfo.years
                , reservationInfo.months
                , reservationInfo.storeReservationDayInfos
            )
        )
        .from(reservationInfo)
        .where(reservationInfo.reservationItem.id.eq(storeId)
            , reservationInfo.years.eq(year)
            , reservationInfo.months.eq(month)
        )
        .leftJoin(reservationInfo.reservationItem)
        .fetchOne());
  }

  @Override
  public boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months) {
    return jpaQueryFactory.from(reservationInfo).where(reservationInfo.years.eq(years),reservationInfo.months.eq(months)).select(reservationInfo.years,reservationInfo.months)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }


}
