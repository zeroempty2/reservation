package demo.reservation.reservation.controller;


import demo.reservation.reservation.dto.StoreReservationDayInfoResponseDto;
import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
import demo.reservation.reservation.service.interfaces.StoreReservationService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

  @RestController
  @RequiredArgsConstructor
  @RequestMapping("/stores")
  public class StoreReservationController {
    private final StoreReservationService storeReservationService;

    @PostMapping("/{storeId}/resevations")
  public ResponseEntity<List<StoreReservationDayInfoResponseDto>> getStoreReservationMonthInfo(@RequestBody
  StoreReservationInfoRequestDto storeReservationInfoRequestDto,@PathVariable Long storeId) {
    List<StoreReservationDayInfoResponseDto> storeReservationDayInfoResponseDtoList = storeReservationService.getStoreReservationDayInfoMonth(storeReservationInfoRequestDto,storeId);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    return ResponseEntity.ok().headers(headers).body(storeReservationDayInfoResponseDtoList);
  }
}
