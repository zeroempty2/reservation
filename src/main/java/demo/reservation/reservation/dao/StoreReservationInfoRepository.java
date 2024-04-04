//package demo.reservation.reservation.dao;
//
//import demo.reservation.reservation.entity.ReservationInfo;
//import java.util.Optional;
//import org.springframework.data.repository.Repository;
//
//public interface StoreReservationInfoRepository extends Repository<ReservationInfo,Long>,StoreReservationInfoRepositoryQuery {
//  void save(ReservationInfo reservationInfo);
//  void saveAndFlush(ReservationInfo reservationInfo);
//  Optional<ReservationInfo> findById(Long storeReservationInfoId);
//  Optional<ReservationInfo> findStoreReservationInfoByYearsAndMonths(Short years, Byte months);
//
//}
