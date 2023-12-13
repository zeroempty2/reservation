package demo.reservation.reservation.dao;

import static demo.reservation.reservation.entity.QStoreReservationDayInfo.storeReservationDayInfo;
import static demo.reservation.reservation.entity.QStoreReservationInfo.storeReservationInfo;
import static demo.reservation.store.entity.QStore.store;


import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import java.util.List;
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
  public List<StoreReservationDayInfoResponseDto> findStoreMonthReservationByStoreIdAndMonth(
      Long storeId, Short year, Byte month) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                StoreReservationDayInfoResponseDto.class
                , storeReservationDayInfo.id
                , storeReservationDayInfo.days
                , storeReservationDayInfo.times
                , storeReservationDayInfo.isPossible
                , storeReservationDayInfo.capacity

                )
        )
        .from(store)
        .where(store.id.eq(storeId)
              ,storeReservationInfo.years.eq(year)
              ,storeReservationInfo.months.eq(month)
              ,storeReservationDayInfo.isPossible.eq(true)
        )
        .leftJoin(store.storeReservationInfos).fetchJoin()
        .leftJoin(storeReservationInfo.storeReservationDayInfos).fetchJoin()
        .fetch();
  }


}
