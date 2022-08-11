package shop.gaship.coupon.coupontype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponTypeRepository extends JpaRepository<CouponType, Integer> {

}
