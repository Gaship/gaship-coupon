package shop.gaship.coupon.coupongenerationissue.repository.impl;

import static shop.gaship.coupon.coupongenerationissue.entity.QCouponGenerationIssue.couponGenerationIssue;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.entity.QCouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepositoryCustom;
import shop.gaship.coupon.coupontype.entity.QCouponType;

/**
 * couponGenerationIssue 의 repository(crud 를 위한) 의 custom 구현체 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public class CouponGenerationIssueRepositoryImpl
    extends QuerydslRepositorySupport implements CouponGenerationIssueRepositoryCustom {

    public CouponGenerationIssueRepositoryImpl() {
        super(CouponGenerationIssue.class);
    }

    /**
     * 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰이 존재하는지 체크하는 메서드 입니다.
     *
     * @param couponTypeNo 쿠폰 타입 번호 입니다.
     * @return 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰의 존재 여부 반환.
     */
    @Override
    public Boolean existCouponHasCouponTypeNo(Integer couponTypeNo) {
        QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.getEntityManager());

        Integer fetchOne = jpaQueryFactory
            .selectOne()
            .from(couponGenerationIssue)
            .where(couponGenerationIssue.couponType.couponTypeNo.eq(couponTypeNo))
            .fetchFirst();

        return fetchOne != null;
    }

    /**
     * 생성, 발급된 전체 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssues(Pageable pageable) {
        QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;
        QCouponType couponType = QCouponType.couponType;

        List<CouponGenerationIssueResponseDto> couponGenerationIssueResponseDtoList = from(couponGenerationIssue)
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
            .select(
                Projections.constructor(CouponGenerationIssueResponseDto.class, couponGenerationIssue.couponType.name,
                    couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
            .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueResponseDtoList, pageable,
            couponGenerationIssueResponseDtoList::size);
    }

    /**
     * 생성, 발급된 전체 쿠폰 중 사용된 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUsed(Pageable pageable) {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoList = from(couponGenerationIssue)
            .where(couponGenerationIssue.usedDatetime.isNotNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
            .select(
                Projections.constructor(CouponGenerationIssueResponseDto.class, couponGenerationIssue.couponType.name,
                    couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
            .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueUsedResponseDtoList, pageable,
            couponGenerationIssueUsedResponseDtoList::size);
    }

    /**
     * 생성, 발급된 전체 쿠폰 중 사용되지 않은 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnused(Pageable pageable) {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedResponseDtoList = from(couponGenerationIssue)
            .where(couponGenerationIssue.usedDatetime.isNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
            .select(
                Projections.constructor(CouponGenerationIssueResponseDto.class, couponGenerationIssue.couponType.name,
                    couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
            .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueUnusedResponseDtoList, pageable,
            couponGenerationIssueUnusedResponseDtoList::size);
    }

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesByMemberNo(Pageable pageable,
        Integer memberNo) {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueResponseDtoByMemberNoList =
            from(couponGenerationIssue)
                .where(couponGenerationIssue.memberNo.eq(memberNo))
                .offset(pageable.getOffset())
                .limit(Math.min(pageable.getPageSize(), 10))
                .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
                .select(
                    Projections.constructor(CouponGenerationIssueResponseDto.class,
                        couponGenerationIssue.couponType.name,
                        couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
                .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueResponseDtoByMemberNoList, pageable,
            couponGenerationIssueResponseDtoByMemberNoList::size);
    }

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용된 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo) {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoByMemberNoList =
            from(couponGenerationIssue)
                .where(couponGenerationIssue.usedDatetime.isNotNull(), couponGenerationIssue.memberNo.eq(memberNo))
                .offset(pageable.getOffset())
                .limit(Math.min(pageable.getPageSize(), 10))
                .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
                .select(
                    Projections.constructor(CouponGenerationIssueResponseDto.class,
                        couponGenerationIssue.couponType.name,
                        couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
                .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueUsedResponseDtoByMemberNoList, pageable,
            couponGenerationIssueUsedResponseDtoByMemberNoList::size);
    }

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용되지 않은 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo) {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedResponseDtoByMemberNoList =
            from(couponGenerationIssue)
                .where(couponGenerationIssue.usedDatetime.isNull(), couponGenerationIssue.memberNo.eq(memberNo))
                .offset(pageable.getOffset())
                .limit(Math.min(pageable.getPageSize(), 10))
                .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
                .select(
                    Projections.constructor(CouponGenerationIssueResponseDto.class,
                        couponGenerationIssue.couponType.name,
                        couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
                .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueUnusedResponseDtoByMemberNoList, pageable,
            couponGenerationIssueUnusedResponseDtoByMemberNoList::size);
    }

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용되지 않고 만료된 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        LocalDateTime now = LocalDateTime.now();

        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredResponseDtoByMemberNoList =
            from(couponGenerationIssue)
                .where(couponGenerationIssue.usedDatetime.isNull(), couponGenerationIssue.memberNo.eq(memberNo),
                    couponGenerationIssue.expirationDatetime.before(now))
                .offset(pageable.getOffset())
                .limit(Math.min(pageable.getPageSize(), 10))
                .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
                .select(
                    Projections.constructor(CouponGenerationIssueResponseDto.class,
                        couponGenerationIssue.couponType.name,
                        couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
                .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueUnusedAndExpiredResponseDtoByMemberNoList, pageable,
            couponGenerationIssueUnusedAndExpiredResponseDtoByMemberNoList::size);
    }

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용되지 않고 만료되지 않은 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        LocalDateTime now = LocalDateTime.now();

        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredResponseDtoByMemberNoList =
            from(couponGenerationIssue)
                .where(couponGenerationIssue.usedDatetime.isNull(), couponGenerationIssue.memberNo.eq(memberNo),
                    couponGenerationIssue.expirationDatetime.after(now))
                .offset(pageable.getOffset())
                .limit(Math.min(pageable.getPageSize(), 10))
                .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
                .select(
                    Projections.constructor(CouponGenerationIssueResponseDto.class,
                        couponGenerationIssue.couponType.name,
                        couponGenerationIssue.memberNo, couponGenerationIssue.expirationDatetime))
                .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueUnusedAndUnexpiredResponseDtoByMemberNoList,
            pageable,
            couponGenerationIssueUnusedAndUnexpiredResponseDtoByMemberNoList::size);
    }
}
