package shop.gaship.coupon.coupontype.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import java.util.List;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<CouponTypeDto> findAllCouponTypes(Pageable pageable) {

        List<CouponTypeDto> couponTypeDtoList = from(couponType)
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.couponGenerationIssueList))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoList, pageable,
            couponTypeDtoList::size);
    }

    @Override
    public Page<CouponTypeDto> findAllCouponTypesCanDelete(Pageable pageable) {

        List<CouponTypeDto> couponTypeDtoListCanDelete = from(couponType)
            .innerJoin(couponGenerationIssue)
            .where(JPAExpressions.select(couponType).notExists())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.couponGenerationIssueList))
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

    @Override
    public Page<CouponTypeDto> findAllCouponTypesCannotDelete(Pageable pageable) {

        List<CouponTypeDto> couponTypeDtoListCannotDelete = from(couponType)
            .innerJoin(couponGenerationIssue)
            .where(JPAExpressions.select(couponType).exists())
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponType.couponTypeNo.desc())
            .select(Projections.constructor(CouponTypeDto.class, couponType.name, couponType.discountRate,
                couponType.discountAmount, couponType.couponGenerationIssueList))
            .fetch();

        return PageableExecutionUtils.getPage(couponTypeDtoListCannotDelete, pageable,
            couponTypeDtoListCannotDelete::size);
    }
}
