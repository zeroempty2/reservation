package demo.reservation.reservationItem.controller;

import static demo.reservation.util.HttpResponse.RESPONSE_CREATED;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.reservationItem.dto.ReservationItemAddDto;
import demo.reservation.reservationItem.service.interfaces.ReservationItemService;
import demo.reservation.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservationItems")
public class ReservationItemController {
  private final ReservationItemService reservationItemService;
  @PostMapping
  public ResponseEntity<StatusResponseDto> addReservationItem(@RequestBody
      ReservationItemAddDto reservationItemAddDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
    reservationItemService.addReservationItem(reservationItemAddDto,userDetails.getUserId());
    return RESPONSE_CREATED;
  }
}
