package shop.gaship.coupon.coupontype.repository.impl;

import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import shop.gaship.coupon.coupongenerationissue.entity.QCouponGenerationIssue;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.entity.QCouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepositoryCustom;
import shop.gaship.coupon.recommendmembercoupontype.entity.QRecommendMemberCouponType;

/**
 * couponType 의 repository(crud 를 위한)의 커스텀 클래스 구현체 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public class CouponTypeRepositoryImpl
    extends QuerydslRepositorySupport implements CouponTypeRepositoryCustom {

    public CouponTypeRepositoryImpl() {
        super(CouponType.class);
    }

    QCouponType couponType = QCouponType.couponType;
    QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;
    QRecommendMemberCouponType recommendMemberCouponType = QRecommendMemberCouponType.recommendMemberCouponType;

    /**
     * 모든 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 모든 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypes(Pageable pageable) {

        List<CouponTypeDto> couponTypeDtoList = from(couponType)
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class,
                couponType.couponTypeNo,
                couponType.name,
                couponType.discountRate,
                couponType.discountAmount,
                couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoList, pageable,
            () -> from(couponType).fetchCount());

    }

    /**
     * 아직 생성, 발급 되지 않은 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 아직 생성, 발급 되지 않은 쿠폰 타입의 Page 타입.
     */
    public Page<CouponTypeDto> findAllCouponTypesCanDelete(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListCanDelete = from(couponType).leftJoin(couponGenerationIssue)
                                                                         .on(couponType.couponTypeNo.eq(
                                                                             couponGenerationIssue.couponType.couponTypeNo))
                                                                         .where(
                                                                             couponGenerationIssue.couponGenerationIssueNo.isNull())
                                                                         .offset(pageable.getOffset())
                                                                         .limit(Math.min(pageable.getPageSize(), 10))
                                                                         .orderBy(couponType.couponTypeNo.desc())
                                                                         .select(Projections.constructor(
                                                                             CouponTypeDto.class,
                                                                             couponType.couponTypeNo,
                                                                             couponType.name,
                                                                             couponType.discountRate,
                                                                             couponType.discountAmount,
                                                                             couponType.isStopGenerationIssue))
                                                                         .distinct()
                                                                         .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListCanDelete, pageable,
            () -> from(couponType).leftJoin(couponGenerationIssue)
                                  .on(couponType.couponTypeNo.eq(
                                      couponGenerationIssue.couponType.couponTypeNo))
                                  .where(
                                      couponGenerationIssue.couponGenerationIssueNo.isNull())
                                  .fetchCount());
    }

    /**
     * 이미 생성, 발급 된 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 이미 생성, 발급 된 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesCannotDelete(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListCannotDelete = from(couponType).innerJoin(couponGenerationIssue)
                                                                            .on(couponType.couponTypeNo.eq(
                                                                                couponGenerationIssue.couponType.couponTypeNo))
                                                                            .offset(pageable.getOffset())
                                                                            .limit(Math.min(pageable.getPageSize(), 10))
                                                                            .orderBy(couponType.couponTypeNo.desc())
                                                                            .select(Projections.constructor(
                                                                                CouponTypeDto.class,
                                                                                couponType.couponTypeNo,
                                                                                couponType.name,
                                                                                couponType.discountRate,
                                                                                couponType.discountAmount,
                                                                                couponType.isStopGenerationIssue))
                                                                            .distinct()
                                                                            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListCannotDelete, pageable,
            () -> from(couponType).innerJoin(couponGenerationIssue)
                                  .on(couponType.couponTypeNo.eq(
                                      couponGenerationIssue.couponType.couponTypeNo))
                                  .fetchCount());
    }

    /**
     * 정액 할인 정책을 가진 coupon type 의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정액 할인 정책인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesFixedAmount(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListFixedAmount = from(couponType)
            .where(couponType.discountRate.isNull(), couponType.discountAmount.isNotNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class,
                couponType.couponTypeNo,
                couponType.name,
                couponType.discountRate,
                couponType.discountAmount,
                couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListFixedAmount, pageable,
            () -> from(couponType)
                .where(couponType.discountRate.isNull(), couponType.discountAmount.isNotNull())
                .fetchCount());
    }

    /**
     * 정률 할인 정책을 가진 coupon type 의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정률 할인 정책인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesFixedRate(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListFixedRate = from(couponType)
            .where(couponType.discountRate.isNotNull(), couponType.discountAmount.isNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class,
                couponType.couponTypeNo,
                couponType.name,
                couponType.discountRate,
                couponType.discountAmount,
                couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListFixedRate, pageable,
            () -> from(couponType)
                .where(couponType.discountRate.isNotNull(), couponType.discountAmount.isNull())
                .fetchCount());
    }

    /**
     * 추천인 coupon type 의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 추천인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesRecommend(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListRecommend = from(couponType)
            .innerJoin(recommendMemberCouponType)
            .on(couponType.couponTypeNo.eq(recommendMemberCouponType.couponTypeNo))
            .where(couponType.discountRate.isNotNull(), couponType.discountAmount.isNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class,
                couponType.couponTypeNo,
                couponType.name,
                couponType.discountRate,
                couponType.discountAmount,
                couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListRecommend, pageable,
            () -> from(couponType)
                .innerJoin(recommendMemberCouponType)
                .on(couponType.couponTypeNo.eq(recommendMemberCouponType.couponTypeNo))
                .where(couponType.discountRate.isNotNull(), couponType.discountAmount.isNull())
                .fetchCount());
    }
}
