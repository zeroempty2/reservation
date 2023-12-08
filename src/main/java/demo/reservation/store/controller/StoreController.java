package demo.reservation.store.controller;

import static demo.reservation.util.HttpResponse.RESPONSE_CREATED;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.store.dto.AddStoreRequestDto;
import demo.reservation.store.service.interfaces.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
  private final StoreService storeService;

  @PostMapping("/add")
  public ResponseEntity<StatusResponseDto> addStore(@RequestBody AddStoreRequestDto addStoreRequestDto){
    storeService.addStore(addStoreRequestDto);
    return RESPONSE_CREATED;
  }

}
