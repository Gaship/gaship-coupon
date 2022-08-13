package shop.gaship.coupon.couponissue.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.couponissue.entity.QCouponGenerationIssue;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepositoryCustom;

/**
 * @author : 조재철
 * @since 1.0
 */
public class CouponGenerationIssueRepositoryImpl
    extends QuerydslRepositorySupport implements CouponGenerationIssueRepositoryCustom {

    public CouponGenerationIssueRepositoryImpl() {
        super(CouponGenerationIssue.class);
    }

    @Override
    public Long countByCouponTypeNo(Integer couponTypeNo) {
        QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;

        return from(couponGenerationIssue)
            .where(couponGenerationIssue.couponType.couponTypeNo.eq(couponTypeNo))
            .select(couponGenerationIssue.count())
            .fetchOne();
    }
}
