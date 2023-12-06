package demo.reservation.user.service;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.user.dao.UserRepository;
import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
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
  public StatusResponseDto signUp(UserSignUpRequestDto signUpRequestDto) {
    String password =  passwordEncoder.encode(signUpRequestDto.password());
    User user = User.builder()
        .username(signUpRequestDto.username())
        .password(password)
        .accountName(signUpRequestDto.accountName())
        .build();
    userRepository.save(user);
    return new StatusResponseDto(201,"Created");
  }

  @Override
  public StatusResponseDto login(UserLoginRequestDto request, HttpServletResponse response) {
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

    return new StatusResponseDto(200,"OK");
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }

  @Override
  public boolean existByUsername(String username) {
    return false;
  }
}
