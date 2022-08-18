package shop.gaship.coupon.coupongenerationissue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;

/**
 * couponGenerationIssue 의 repository(crud 를 위한) 클래스 입니다.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepository
    extends JpaRepository<CouponGenerationIssue, Integer>, CouponGenerationIssueRepositoryCustom {

}
