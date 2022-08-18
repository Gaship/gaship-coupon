package shop.gaship.coupon.coupongenerationissue.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;

/**
 * 쿠폰을 생성 및 발급하기위해 db에 접근하는 jpa repository 입니다.
 *
 * @author 최겸준, 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepository extends JpaRepository<CouponGenerationIssue, Integer>, CouponGenerationIssueRepositoryCustom {

    @Query(name = "CouponGenerationIssue.findByIdAsFetchJoin")
    Optional<CouponGenerationIssue> findCouponGenerationIssueByIdAsFetchJoin(Integer couponGenerationIssueNo);
}
