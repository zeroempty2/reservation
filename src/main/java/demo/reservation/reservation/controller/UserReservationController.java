package demo.reservation.reservation.controller;

import static demo.reservation.util.HttpResponse.BAD_REQUEST;
import static demo.reservation.util.HttpResponse.RESPONSE_OK;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.service.interfaces.UserReservationService;
import demo.reservation.security.UserDetailsImpl;
import demo.reservation.user.dto.UserLoginRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/resevations")
public class UserReservationController {
  private final UserReservationService userReservationService;

  @PostMapping("/request/{storeReservationInfoId}")
  public ResponseEntity<StatusResponseDto> requestReservation(@RequestBody RequestReservationDto requestReservationDto,@PathVariable Long storeReservationInfoId, @AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    boolean isPossible = userReservationService.requestReservation(requestReservationDto,storeReservationInfoId,userDetails.getUserId());
    return isPossible ? RESPONSE_OK : BAD_REQUEST;
  }
}
