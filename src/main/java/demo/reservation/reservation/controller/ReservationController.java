package demo.reservation.reservation.controller;


import static demo.reservation.util.HttpResponse.RESPONSE_CREATED;
import static demo.reservation.util.HttpResponse.RESPONSE_OK;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.reservation.dto.ReservationAddDto;
import demo.reservation.reservation.dto.ReservationInfoRequestDto;
import demo.reservation.reservation.dto.ReservationInfoResponseDto;
import demo.reservation.reservation.service.interfaces.ReservationService;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

  @RestController
  @RequiredArgsConstructor
  @RequestMapping("/reservations")
  public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/{reservationItemId}")
    public ResponseEntity<StatusResponseDto> addStoreReservationMonthInfo(@RequestBody
    ReservationAddDto reservationAddDto,@PathVariable Long reservationItemId) {
      reservationService.addReservationDayInfoMonth(reservationAddDto,reservationItemId);
      return RESPONSE_CREATED;
    }
    @PatchMapping("/{storeId}")
    public ResponseEntity<StatusResponseDto> updateStoreReservationMonthInfo(@RequestBody
    ReservationAddDto reservationUpdateDto,@PathVariable Long storeId) {
      reservationService.addReservationDayInfoMonth(reservationUpdateDto, storeId);
      return RESPONSE_OK;
    }
  @GetMapping("/info/{storeId}")
  public ResponseEntity<ReservationInfoResponseDto> getReservationMonthInfo(@RequestBody
  ReservationInfoRequestDto reservationInfoRequestDto,@PathVariable Long storeId) {
    ReservationInfoResponseDto reservationDayInfoResponseDto = reservationService.getReservationInfos(storeId,
        reservationInfoRequestDto);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(reservationDayInfoResponseDto);
  }

}
