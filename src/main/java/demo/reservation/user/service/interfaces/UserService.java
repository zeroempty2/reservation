package demo.reservation.user.service.interfaces;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
  StatusResponseDto signUp(UserSignUpRequestDto signUpRequestDto);
  StatusResponseDto login(UserLoginRequestDto loginRequestDto, HttpServletResponse response);
  User findByUsername(String username);
  boolean existByUsername(String username);

}
