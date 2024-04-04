//package demo.reservation.reservation.controller;
//
//
//import static demo.reservation.util.HttpResponse.RESPONSE_CREATED;
//import static demo.reservation.util.HttpResponse.RESPONSE_OK;
//
//import demo.reservation.common.dto.StatusResponseDto;
//import demo.reservation.reservation.dto.StoreReservationAddDto;
//import demo.reservation.reservation.dto.StoreReservationInfoRequestDto;
//import demo.reservation.reservation.dto.StoreReservationInfoResponseDto;
//import demo.reservation.reservation.service.interfaces.StoreReservationService;
//import java.nio.charset.StandardCharsets;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//  @RestController
//  @RequiredArgsConstructor
//  @RequestMapping("/stores/reservations")
//  public class StoreReservationController {
//    private final StoreReservationService storeReservationService;
//
//    @PostMapping("/{storeId}")
//    public ResponseEntity<StatusResponseDto> addStoreReservationMonthInfo(@RequestBody
//    StoreReservationAddDto storeReservationAddDto,@PathVariable Long storeId) {
//      storeReservationService.addStoreReservationDayInfoMonth(storeReservationAddDto,storeId);
//      return RESPONSE_CREATED;
//    }
//    @PatchMapping("/{storeId}")
//    public ResponseEntity<StatusResponseDto> updateStoreReservationMonthInfo(@RequestBody
//    StoreReservationAddDto storeReservationUpdateDto,@PathVariable Long storeId) {
//      storeReservationService.addStoreReservationDayInfoMonth(storeReservationUpdateDto, storeId);
//      return RESPONSE_OK;
//    }
//  @GetMapping("/info/{storeId}")
//  public ResponseEntity<StoreReservationInfoResponseDto> getStoreReservationMonthInfo(@RequestBody
//  StoreReservationInfoRequestDto storeReservationInfoRequestDto,@PathVariable Long storeId) {
//    StoreReservationInfoResponseDto storeReservationDayInfoResponseDto = storeReservationService.getStoreReservationInfos(storeId,storeReservationInfoRequestDto);
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
//    return ResponseEntity.ok().headers(headers).body(storeReservationDayInfoResponseDto);
//  }
//
//}
