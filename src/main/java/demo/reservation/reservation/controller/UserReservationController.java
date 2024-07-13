package demo.reservation.reservation.controller;

import static demo.reservation.util.HttpResponse.BAD_REQUEST;
import static demo.reservation.util.HttpResponse.RESPONSE_OK;

import demo.reservation.common.dto.PageDto;
import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.reservation.dto.RequestReservationDto;
import demo.reservation.reservation.dto.ReservationCompleteDto;
import demo.reservation.reservation.dto.UserReservationResponseDto;
import demo.reservation.reservation.service.interfaces.UserReservationService;
import demo.reservation.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
  @GetMapping("/reservations")
  public ResponseEntity<Page<UserReservationResponseDto>> getUserReservations(PageDto pageDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
    Page<UserReservationResponseDto> dtoList = userReservationService.getUserReservations(userDetails.getUserId(),pageDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(dtoList);

  }
  @PutMapping("/cancel/{userReservationId}")
  public ResponseEntity<StatusResponseDto> cancelReservation(@AuthenticationPrincipal
  UserDetailsImpl userDetails,@PathVariable Long userReservationId) {
     userReservationService.cancelReservation(userDetails.getUserId(),userReservationId);
    return RESPONSE_OK;
  }
  @PutMapping("/complete")
  public ResponseEntity<StatusResponseDto> completeReservation(@RequestBody
  ReservationCompleteDto reservationCompleteDto,@AuthenticationPrincipal
  UserDetailsImpl userDetails) {
    userReservationService.completeReservation(reservationCompleteDto,userDetails.getUserId());
    return RESPONSE_OK;
  }
}
