package demo.reservation.store.service;

import demo.reservation.store.dao.StoreRepository;
import demo.reservation.store.entity.Store;
import demo.reservation.store.service.interfaces.StoreService;
import demo.reservation.user.entity.User;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.enums.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;

  private final UserService userService;

  @Override
  @Transactional
  public void AddStore(Long userId) {
    User user = userService.findById(userId);
    if(!user.getRole().equals(UserRoleEnum.OWNER)){
      throw new IllegalArgumentException("권한이 없습니다");
    }
    Store store = new Store();
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



}
