package shop.gaship.coupon.coupontype.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.gaship.coupon.coupontype.entity.CouponType;
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
}
