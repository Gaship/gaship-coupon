package shop.gaship.coupon.coupontype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.coupontype.entity.CouponTypeDto;

/**
 * The interface Coupon type repository.
 *
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponTypeRepository extends JpaRepository<CouponTypeDto, Integer> {

}
