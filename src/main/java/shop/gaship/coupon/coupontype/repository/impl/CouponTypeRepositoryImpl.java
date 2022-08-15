package shop.gaship.coupon.coupontype.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import shop.gaship.coupon.couponissue.entity.QCouponGenerationIssue;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.entity.QCouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepositoryCustom;

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
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoList, pageable,
            couponTypeDtoList::size);
    }

    /**
     * 아직 생성, 발급 되지 않은 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 아직 생성, 발급 되지 않은 쿠폰 타입의 Page 타입.
     */
    public Page<CouponTypeDto> findAllCouponTypesCanDelete(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListCanDelete = from(couponType)
            .where(JPAExpressions.select(couponType).
                    from(couponType).
                    innerJoin(couponGenerationIssue)
                    .on(couponType.couponTypeNo.eq(couponGenerationIssue.couponType.couponTypeNo))
                    .notExists())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.isStopGenerationIssue))
            .fetch();


//        List<CouponTypeDto> couponTypeDtoList = from(couponType)
//            .where(couponType.couponGenerationIssueList.isNotEmpty())
//            .offset(pageable.getOffset()).limit(Math.min(pageable.getPageSize(), 10))
//            .orderBy(couponType.couponTypeNo.desc())
//            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
//                couponType.discountAmount, couponType.couponGenerationIssueList))
//            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListCanDelete, pageable,
            couponTypeDtoListCanDelete::size);
    }

    /**
     * 이미 생성, 발급 된 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 이미 생성, 발급 된 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesCannotDelete(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListCannotDelete = from(couponType)
            .where(JPAExpressions.select(couponType)
                    .from(couponType)
                    .innerJoin(couponGenerationIssue)
                    .on(couponType.couponTypeNo.eq(couponGenerationIssue.couponType.couponTypeNo))
                    .exists())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.isStopGenerationIssue))
            .fetch();


        return PageableExecutionUtils.getPage(couponTypeDtoListCannotDelete, pageable,
            couponTypeDtoListCannotDelete::size);
    }

    @Override
    public Page<CouponTypeDto> coupon(Pageable pageable,
                                      boolean typeCheck){
        List<CouponTypeDto> content = from(couponType)
                .where(trueIsRateFalseAmount(typeCheck))
                .select(Projections.constructor(CouponTypeDto.class,
                        couponType.name,
                        couponType.discountRate,
                        couponType.discountAmount,
                        couponType.isStopGenerationIssue))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(couponType.couponTypeNo.desc())
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }
    //정액 정률 구분
    private BooleanExpression trueIsRateFalseAmount(boolean test){
        if(test){
            return couponType.discountRate.isNull().and(couponType.discountAmount.isNotNull());
        }
        return couponType.discountRate.isNotNull().and(couponType.discountAmount.isNull());
    }
    /**
     * 정액 할인 정책을 가진 coupont type 의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정액 할인 정책인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesFixedAmount(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListFixedAmount = from(couponType)
            .where(couponType.discountRate.isNull(),
                    couponType.discountAmount.isNotNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListFixedAmount, pageable,
            couponTypeDtoListFixedAmount::size);
    }

    /**
     * 정률 할인 정책을 가진 coupont type의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정률 할인 정책인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findAllCouponTypesFixedRate(Pageable pageable) {
        List<CouponTypeDto> couponTypeDtoListFixedRate = from(couponType)
            .where(couponType.discountRate.isNotNull(),
                    couponType.discountAmount.isNull())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.isStopGenerationIssue))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListFixedRate, pageable,
            couponTypeDtoListFixedRate::size);
    }
}
