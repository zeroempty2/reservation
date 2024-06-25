package demo.reservation.reservationItem.service;

import demo.reservation.common.dto.PageDto;
import demo.reservation.reservationItem.dao.ReservationItemRepository;
import demo.reservation.reservationItem.dto.ReservationItemAddDto;
import demo.reservation.reservationItem.dto.ReservationItemResponseDto;
import demo.reservation.reservationItem.entity.ReservationItem;
import demo.reservation.reservationItem.service.interfaces.ReservationItemService;
import demo.reservation.user.service.interfaces.UserService;
import demo.reservation.util.enums.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    if(!userService.findById(userId).getRole().equals(UserRoleEnum.MANAGER)) throw new IllegalArgumentException("권한이 없습니다");
    ReservationItem reservationItem =  ReservationItem.builder()
        .itemName(reservationItemAddDto.itemName())
        .ownerId(userId)
        .build();
    reservationItemRepository.save(reservationItem);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ReservationItemResponseDto> getReservationItems(PageDto pageDto) {
    return reservationItemRepository.getReservationItems(pageDto);
  }

  @Override
  public ReservationItem findById(Long reservationItemId) {
    return reservationItemRepository.findById(reservationItemId).orElseThrow(
        () -> new IllegalArgumentException("존재하지 않는 정보입니다")
    );
  }

}
