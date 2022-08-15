package shop.gaship.coupon.coupongenerationissue.repository.impl;

import com.querydsl.core.types.Projections;
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
 * @author : 조재철
 * @since 1.0
 */
public class CouponGenerationIssueRepositoryImpl extends QuerydslRepositorySupport
    implements CouponGenerationIssueRepositoryCustom {

    public CouponGenerationIssueRepositoryImpl() {
        super(CouponGenerationIssue.class);
    }

    QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;
    QCouponType couponType = QCouponType.couponType;

    @Override
    public Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssues(Pageable pageable) {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueResponseDtoList = from(couponGenerationIssue)
            .offset(pageable.getOffset())
            .limit(Math.min(pageable.getPageSize(), 10))
            .orderBy(couponGenerationIssue.couponGenerationIssueNo.desc())
            .select(
                Projections.constructor(CouponGenerationIssueResponseDto.class, couponGenerationIssue.couponType.name,
                    couponGenerationIssue.memberNo, couponGenerationIssue.memberNo))
            .fetch();

        return PageableExecutionUtils.getPage(couponGenerationIssueResponseDtoList, pageable,
            couponGenerationIssueResponseDtoList::size);
    }
}
