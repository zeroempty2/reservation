package demo.reservation.reservation.dao;

import demo.reservation.reservation.entity.StoreReservationInfo;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StoreReservationInfoRepository extends Repository<StoreReservationInfo,Long>,StoreReservationInfoRepositoryQuery {
  void save(StoreReservationInfo storeReservationInfo);
  void saveAndFlush(StoreReservationInfo storeReservationInfo);
  Optional<StoreReservationInfo> findById(Long storeReservationInfoId);
  Optional<StoreReservationInfo> findStoreReservationInfoByYearsAndMonths(Short years, Byte months);

}
