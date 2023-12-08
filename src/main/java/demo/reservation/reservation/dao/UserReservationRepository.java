package demo.reservation.reservation.dao;

import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.user.entity.User;
import org.springframework.data.repository.Repository;

public interface UserReservationRepository extends Repository<UserReservation,Long>{
  void save(UserReservation userReservation);
}
