package demo.reservation.store.service;

import demo.reservation.store.dao.StoreRepository;
import demo.reservation.store.dto.AddStoreRequestDto;
import demo.reservation.store.entity.Store;
import demo.reservation.store.entity.StoreDesignatedDateInfo;
import demo.reservation.store.service.interfaces.StoreService;
import demo.reservation.util.enums.ReservationPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public void addStore(AddStoreRequestDto addStoreRequestDto) {
    Store store = storeHasFixedDate();
    storeRepository.save(store);
  }

  @Override
  @Transactional
  public Store findById(Long storeId) {
    return storeRepository.findById(storeId).orElseThrow(
        () -> new IllegalArgumentException("유효하지 않은 정보입니다.")
    );
  }


//  private Store storeHasDesignatedDate(AddStoreRequestDto addStoreRequestDto) {
//    return Store.builder()
//        .reservationPolicy(ReservationPolicy.DesignatedDateOpen)
//        .storeDesignatedDateInfo(new StoreDesignatedDateInfo(addStoreRequestDto.openStart(),addStoreRequestDto.openEnd()))
//        .build();
//  }

  private Store storeHasFixedDate() {
    return Store.builder()
        .reservationPolicy(ReservationPolicy.FixedDateOpen)
        .build();
  }

}
