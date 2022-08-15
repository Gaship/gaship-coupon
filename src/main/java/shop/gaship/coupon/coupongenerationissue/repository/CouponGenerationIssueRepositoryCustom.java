package shop.gaship.coupon.coupongenerationissue.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;

/**
 * @author : 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepositoryCustom {

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssues(Pageable pageable);
}
