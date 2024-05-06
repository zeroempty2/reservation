package demo.reservation.reservationItem.dao;

import static demo.reservation.reservationItem.entity.QReservationItem.reservationItem;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.reservation.common.dto.PageDto;
import demo.reservation.reservationItem.dto.ReservationItemResponseDto;
import demo.reservation.reservationItem.entity.ReservationItem;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReservationItemRepositoryQueryImpl implements ReservationItemRepositoryQuery{
  private final JPAQueryFactory jpaQueryFactory;
  @Override
  @Transactional(readOnly = true)
  public Page<ReservationItemResponseDto> getReservationItems(PageDto pageDto) {
    Pageable pageable = pageDto.toPageable();
    List<ReservationItemResponseDto> dtoList;

    if(Objects.nonNull(pageDto.getSortBy())) dtoList = getReservationItemsAndSortByKeyword(pageable, pageDto);
    else dtoList = sortByCreatedAtDesc(pageable);

    long totalSize = storeCountQuery().fetch().get(0);

    return PageableExecutionUtils.getPage(dtoList, pageable, () -> totalSize);
  }
  private List<ReservationItemResponseDto> getReservationItemsAndSortByKeyword(Pageable pageable,PageDto pageDto) {
    OrderSpecifier<?> orderSpecifier = getOrderSpecifier(pageDto.getSortBy(),
        pageDto.isAsc());

    return query()
        .orderBy(orderSpecifier)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }

  private List<ReservationItemResponseDto>sortByCreatedAtDesc(Pageable pageable) {
    return query()
        .orderBy(reservationItem.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();
  }

  private JPAQuery<ReservationItemResponseDto> query(){
    return jpaQueryFactory
        .select(
            Projections.bean(
                ReservationItemResponseDto.class
                , reservationItem.itemName
            )
        )
        .from(reservationItem)
        .setHint("org.hibernate.readOnly", true);
  }

  private JPAQuery<Long> storeCountQuery() {
    return jpaQueryFactory.select(Wildcard.count)
        .from(reservationItem);
  }

  private OrderSpecifier<?> getOrderSpecifier(String sortBy, boolean isAsc) {
    PathBuilder<Object> defaultPath = new PathBuilder<>(ReservationItem.class, ReservationItem.class.getSimpleName());
    return isAsc ? defaultPath.getString(sortBy).asc() : defaultPath.getString(sortBy).desc();
  }
}
