package shop.gaship.coupon.recommendmembercoupontype.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;

/**
 * 추천인쿠폰종류 테이블과 연결된 repository로 db 통신을 담당하는 클래스입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
public interface RecommendMemberCouponTypeRepository
    extends JpaRepository<RecommendMemberCouponType, Integer> {

    @EntityGraph(value = "RecommendMemberCouponType.withCouponType")
    Optional<RecommendMemberCouponType> findTopFetchJoinByOrderByCouponTypeNoDesc();
}
