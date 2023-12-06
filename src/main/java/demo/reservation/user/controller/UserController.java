package demo.reservation.user.controller;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.user.dto.UserLoginRequestDto;
import demo.reservation.user.dto.UserSignUpRequestDto;
import demo.reservation.user.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    StatusResponseDto statusResponseDto = userService.signUp(userRequestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }

  @PostMapping("/login")
  public ResponseEntity<StatusResponseDto> login(@RequestBody UserLoginRequestDto loginRequestDto, HttpServletResponse response) {
    StatusResponseDto statusResponseDto = userService.login(loginRequestDto,response);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(statusResponseDto);
  }
}
