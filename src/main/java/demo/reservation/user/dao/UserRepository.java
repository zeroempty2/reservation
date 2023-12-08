package demo.reservation.user.dao;

import demo.reservation.user.entity.User;
import java.util.Optional;
import org.springframework.data.repository.Repository;


public interface UserRepository extends Repository<User,Long> {
 void save(User user);
 Optional<User> findByUsername(String username);
 Optional<User> findById(Long Id);
}
