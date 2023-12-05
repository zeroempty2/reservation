package demo.reservation.user.service.interfaces;

import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.entity.User;

public interface UserService {
  void signUp(UserSignUpRequestDto signUpRequestDto);
  void login(UserLoginRequestDto loginRequestDto);
  User findByUsername(String username);
  boolean existByUsername(String username);

}
