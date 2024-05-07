package demo.reservation.reservation.dao;

import demo.reservation.reservation.entity.ReservationInfo;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ReservationInfoRepository extends Repository<ReservationInfo,Long>,
    ReservationInfoRepositoryQuery {
  void save(ReservationInfo reservationInfo);
  void saveAndFlush(ReservationInfo reservationInfo);
  Optional<ReservationInfo> findById(Long storeReservationInfoId);
  Optional<ReservationInfo> findReservationInfoByYearsAndMonths(Short years, Byte months);

}
