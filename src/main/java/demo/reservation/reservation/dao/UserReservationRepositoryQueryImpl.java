package demo.reservation.reservation.dao;

import static demo.reservation.reservation.entity.QUserReservation.userReservation;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import demo.reservation.common.dto.PageDto;
import demo.reservation.reservation.dto.UserReservationResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class UserReservationRepositoryQueryImpl implements UserReservationRepositoryQuery {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  @Transactional(readOnly = true)
  public Page<UserReservationResponseDto> findUserReservationByUserId(Long userId,
      PageDto pageDto) {
    Pageable pageable = pageDto.toPageable();
    long totalSize = countQuery().fetch().get(0);

    // 정렬기준에 따른 분기 나눔 필요
    List<UserReservationResponseDto> userReservationResponseDto =
         query(userId)
        .orderBy(userReservation.createdAt.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();

    return PageableExecutionUtils.getPage(userReservationResponseDto, pageable, () -> totalSize);
  }

  private JPAQuery<UserReservationResponseDto> query(Long userId) {
    return jpaQueryFactory
        .select(
            Projections.bean(
                UserReservationResponseDto.class
                , userReservation.reservationStatus
                , userReservation.years
                , userReservation.months
                , userReservation.times
                , userReservation.days
                , userReservation.reservationItemId
                , userReservation.createdAt
            )
        )
        .from(userReservation)
        .where(userReservation.user.id.eq(userId));
  }

  private JPAQuery<Long> countQuery() {
    return jpaQueryFactory.select(Wildcard.count)
        .from(userReservation);
  }
}
