package demo.reservation.reservation.dao;

import static demo.reservation.reservation.entity.QStoreReservationDayInfo.storeReservationDayInfo;
import static demo.reservation.reservation.entity.QStoreReservationInfo.storeReservationInfo;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class StoreReservationInfoRepositoryQueryImpl implements StoreReservationInfoRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional
  public Optional<StoreReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(
      Long storeReservationInfoId, String time) {
    return Optional.ofNullable(
        jpaQueryFactory.selectFrom(storeReservationDayInfo)
            .where(storeReservationDayInfo.storeReservationInfo.id.eq(storeReservationInfoId)
                .and(storeReservationDayInfo.times.eq(time)))
            .fetchOne());
  }

  @Override
  @Transactional
  public StoreReservationInfo findStoreMonthReservationByStoreIdAndMonth(
      Long storeId, Short year, Byte month) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreReservationInfo.class
                , storeReservationInfo.id
                , storeReservationInfo.years
                , storeReservationInfo.months
                , storeReservationInfo.storeReservationDayInfos
                , storeReservationInfo.store
                )
        )
        .from(storeReservationInfo)
        .where(storeReservationInfo.store.id.eq(storeId)
              ,storeReservationInfo.years.eq(year)
              ,storeReservationInfo.months.eq(month)
        )
        .leftJoin(storeReservationInfo.store).fetchJoin()
        .fetchOne();
  }

  @Override
  @Transactional
  public StoreReservationInfoResponseDto findStoreMonthReservationAndResponse(
      Long storeId, Short year, Byte month) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreReservationInfoResponseDto.class
                , storeReservationInfo.id
                , storeReservationInfo.years
                , storeReservationInfo.months
                , storeReservationInfo.storeReservationDayInfos
            )
        )
        .from(storeReservationInfo)
        .where(storeReservationInfo.store.id.eq(storeId)
            ,storeReservationInfo.years.eq(year)
            ,storeReservationInfo.months.eq(month)
        )
        .leftJoin(storeReservationInfo.store).fetchJoin()
        .fetchOne();
  }

  @Override
  public boolean existsStoreReservationInfoByYearsAndMonths(Short years, Byte months) {
    return jpaQueryFactory.from(storeReservationInfo).where(storeReservationInfo.years.eq(years),storeReservationInfo.months.eq(months)).select(storeReservationInfo.years,storeReservationInfo.months)
        .setHint("org.hibernate.readOnly", true).fetchFirst() != null;
  }


}
