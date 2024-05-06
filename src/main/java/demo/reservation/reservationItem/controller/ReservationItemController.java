package demo.reservation.reservationItem.controller;

import static demo.reservation.util.HttpResponse.RESPONSE_CREATED;

import demo.reservation.common.dto.PageDto;
import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.reservationItem.dto.ReservationItemAddDto;
import demo.reservation.reservationItem.dto.ReservationItemResponseDto;
import demo.reservation.reservationItem.service.interfaces.ReservationItemService;
import demo.reservation.security.UserDetailsImpl;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public ResponseEntity<Page<ReservationItemResponseDto>> getReservationItems(PageDto pageDto){
    Page<ReservationItemResponseDto> itemList = reservationItemService.getReservationItems(pageDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(itemList);
  }
}
