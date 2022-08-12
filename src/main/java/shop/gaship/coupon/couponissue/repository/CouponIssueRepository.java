package shop.gaship.coupon.couponissue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponIssueRepository extends JpaRepository<CouponGenerationIssue, Integer> {
}
