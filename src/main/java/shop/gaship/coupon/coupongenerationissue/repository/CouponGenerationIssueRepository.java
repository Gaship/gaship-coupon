package shop.gaship.coupon.coupongenerationissue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;

/**
 * 쿠폰 생성 발급 엔티티에 대한 repository 인터페이스 클래스 입니다.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepository
    extends JpaRepository<CouponGenerationIssue, Integer>, CouponGenerationIssueRepositoryCustom {

}
