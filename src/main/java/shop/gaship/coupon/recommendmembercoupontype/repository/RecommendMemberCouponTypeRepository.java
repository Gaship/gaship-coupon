package shop.gaship.coupon.recommendmembercoupontype.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface RecommendMemberCouponTypeRepository
    extends JpaRepository<RecommendMemberCouponType, Integer> {

    @EntityGraph(value = "RecommendMemberCouponType.withCouponType")
    Optional<RecommendMemberCouponType> findTopFetchJoinByOrderByCouponTypeNoDesc();
}
