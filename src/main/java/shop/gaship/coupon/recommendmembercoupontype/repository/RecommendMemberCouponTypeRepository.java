package shop.gaship.coupon.recommendmembercoupontype.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface RecommendMemberCouponTypeRepository extends JpaRepository<RecommendMemberCouponType, Integer> {

    Optional<RecommendMemberCouponType> findFirstByOrderByCouponTypeNoDesc();
}
