package demo.reservation.user.service;

import demo.reservation.user.dao.UserRepository;
import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private  final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  @Override
  public void signUp(UserSignUpRequestDto signUpRequestDto) {

  }

  @Override
  public void login(UserLoginRequestDto loginRequestDto) {

  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("중복된 이름입니다")
    );
  }

  @Override
  public boolean existByUsername(String username) {
    return false;
  }
}
