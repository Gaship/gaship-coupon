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

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUsed(Pageable pageable);

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnused(Pageable pageable);

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesByMemberNo(Pageable pageable, Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUsedByMemberNo(Pageable pageable, Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedByMemberNo(Pageable pageable, Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(Pageable pageable, Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(Pageable pageable, Integer memberNo);
}
