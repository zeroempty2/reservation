package demo.reservation;

import demo.reservation.store.dao.StoreRepository;
import demo.reservation.store.entity.Store;
import demo.reservation.user.dao.UserRepository;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.UserServiceImpl;
import demo.reservation.util.enums.UserRoleEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {

  private final UserRepository userRepository;
  private final UserServiceImpl userService;
  private final PasswordEncoder passwordEncoder;
  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {
      User user = User.builder()
          .role(UserRoleEnum.OWNER)
          .accountName("OWNER1")
          .username("OWNER1")
          .password(passwordEncoder.encode("Password!23"))
          .build();
      userRepository.save(user);
      Store store = new Store();
      storeRepository.save(store);
    }

  }

