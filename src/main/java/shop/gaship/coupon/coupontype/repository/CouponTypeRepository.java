package shop.gaship.coupon.coupontype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * The interface Coupon type repository.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponTypeRepository extends JpaRepository<CouponType, Integer>, CouponTypeRepositoryCustom {

}
