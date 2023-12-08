package demo.reservation.store.dao;

import demo.reservation.store.entity.Store;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface StoreRepository extends Repository<Store,Long> {
  void save(Store store);
  Optional<Store> findById(Long StoreId);
}
