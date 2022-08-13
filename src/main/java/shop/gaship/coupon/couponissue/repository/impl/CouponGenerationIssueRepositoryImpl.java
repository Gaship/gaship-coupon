package shop.gaship.coupon.couponissue.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.couponissue.entity.QCouponGenerationIssue;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepositoryCustom;

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
     * 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰의 갯수를 알아내는 메서드 입니다.
     *
     * @param couponTypeNo 쿠폰 타입 번호 입니다.
     * @return 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰의 갯수를 반환 합니다.
     */
    @Override
    public Long countByCouponTypeNo(Integer couponTypeNo) {
        QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;

        return from(couponGenerationIssue)
            .where(couponGenerationIssue.couponType.couponTypeNo.eq(couponTypeNo))
            .select(couponGenerationIssue.count())
            .fetchOne();
    }
}
