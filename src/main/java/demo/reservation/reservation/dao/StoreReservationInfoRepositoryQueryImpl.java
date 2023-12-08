package demo.reservation.reservation.dao;

import static demo.reservation.store.entity.QStoreReservationDayInfo.storeReservationDayInfo;
import static demo.reservation.store.entity.QStoreReservationInfo.storeReservationInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.reservation.reservation.entity.StoreReservationDayInfo;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StoreReservationInfoRepositoryQueryImpl implements StoreReservationInfoRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  public Optional<StoreReservationDayInfo> findStoreReservationDayInfoByStoreReservationInfoIdAndTime(
      Long storeReservationInfoId, String time) {
    return Optional.ofNullable(
        jpaQueryFactory.selectFrom(storeReservationDayInfo)
            .where(storeReservationDayInfo.storeReservationInfo.id.eq(storeReservationInfoId)
                .and(storeReservationDayInfo.time.eq(time)))
            .fetchOne());
  }
}
