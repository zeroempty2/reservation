package demo.reservation.user.service;


import demo.reservation.user.dao.UserRepository;
import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.JwtUtil;
import demo.reservation.util.enums.UserRoleEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private  final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  @Override
  @Transactional
  public void signUp(UserSignUpRequestDto signUpRequestDto) {
    String password =  passwordEncoder.encode(signUpRequestDto.password());
    User user = User.builder()
        .username(signUpRequestDto.username())
        .password(password)
        .accountName(signUpRequestDto.accountName())
        .role(UserRoleEnum.CUSTOMER)
        .build();
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void login(UserLoginRequestDto request, HttpServletResponse response) {
    String username = request.username();
    String password = request.password();

    // 사용자 확인
    User user = findByUsername(username);
    // 비밀번호 확인
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("입력한 정보가 틀립니다.");
    }
    // token 발급
    String accessToken = jwtUtil.createAccessToken(user.getUsername(), user.getRole());
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
  }

  @Override
  @Transactional
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }

  @Override
  @Transactional
  public User findById(Long userId) {
    return userRepository.findById(userId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }

  @Override
  public boolean existByUsername(String username) {
    return false;
  }
}
