package demo.reservation.store.service.interfaces;

import demo.reservation.store.entity.Store;

public interface StoreService {
  void AddStore(Long userId);
  Store findById(Long StoreId);
}
