package demo.reservation.store.service.interfaces;

import demo.reservation.common.dto.StatusResponseDto;
import demo.reservation.store.dto.AddStoreRequestDto;
import demo.reservation.store.entity.Store;
import java.util.Optional;

public interface StoreService {

  Store findById(Long StoreId);
}
