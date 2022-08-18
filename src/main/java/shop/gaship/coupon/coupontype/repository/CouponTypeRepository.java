package shop.gaship.coupon.coupontype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * coupon_types db와 연결되어있는 jpaRepository interface입니다.
 *
 * @author 최겸준, 조재철
 * @since 1.0
 */
public interface CouponTypeRepository extends JpaRepository<CouponType, Integer>, CouponTypeRepositoryCustom {

}
