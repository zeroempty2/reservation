package demo.reservation.user.controller;

import static demo.reservation.util.HttpResponse.RESPONSE_CREATED;
import static demo.reservation.util.HttpResponse.RESPONSE_OK;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  @PostMapping("/signUp")
  public ResponseEntity<StatusResponseDto>  signUp(@RequestBody UserSignUpRequestDto userRequestDto){
    userService.signUp(userRequestDto);
    return RESPONSE_CREATED;
  }

  @PostMapping("/login")
  public ResponseEntity<StatusResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto, HttpServletResponse response) {
    userService.login(loginRequestDto,response);
    return RESPONSE_OK;
  }
}
