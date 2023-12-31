package demo.reservation.reservation.controller;

import static demo.reservation.util.HttpResponse.BAD_REQUEST;
import static demo.reservation.util.HttpResponse.RESPONSE_OK;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.service.interfaces.UserReservationService;
import demo.reservation.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/reservations")
public class UserReservationController {
  private final UserReservationService userReservationService;

  @PostMapping("/{storeId}/{storeReservationInfoId}")
  public ResponseEntity<StatusResponseDto> requestReservation(@AuthenticationPrincipal
  UserDetailsImpl userDetails,@PathVariable Long storeId,@PathVariable Long storeReservationInfoId,@RequestBody RequestReservationDto requestReservationDto) {
    boolean isPossible = userReservationService.requestReservation(userDetails.getUserId(),storeId,storeReservationInfoId,requestReservationDto);
    return isPossible ? RESPONSE_OK : BAD_REQUEST;
  }

  @PutMapping("/cancel/{userReservationId}")
  public ResponseEntity<StatusResponseDto> cancelReservation(@AuthenticationPrincipal
  UserDetailsImpl userDetails,@PathVariable Long userReservationId) {
     userReservationService.cancelReservation(userDetails.getUserId(),userReservationId);
    return RESPONSE_OK;
  }
}
