package demo.reservation.reservationItem.service;

import demo.reservation.reservationItem.dao.ReservationItemRepository;
import demo.reservation.reservationItem.dto.ReservationItemAddDto;
import demo.reservation.reservationItem.entity.ReservationItem;
import demo.reservation.reservationItem.service.interfaces.ReservationItemService;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.enums.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationItemServiceImpl implements ReservationItemService {
  private final ReservationItemRepository reservationItemRepository;
  private final UserService userService;

  @Override
  @Transactional
  public void addReservationItem(ReservationItemAddDto reservationItemAddDto, Long userId) {
    if(!userService.findById(userId).getRole().equals(UserRoleEnum.MANAGER)) throw new IllegalArgumentException("관리자가 아닙니다");
    ReservationItem reservationItem =  ReservationItem.builder()
        .itemName(reservationItemAddDto.itemName())
        .build();
    reservationItemRepository.save(reservationItem);
  }

}
