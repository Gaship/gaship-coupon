package shop.gaship.coupon.coupongenerationissue.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;

/**
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueService {

    void addCouponIssue(CouponIssueCreationRequestDto couponTypeDto);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssues(Pageable pageable);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsed(Pageable pageable);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnused(Pageable pageable);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesByMemberNo(Pageable pageable, Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndExpiredByMemberNo(Pageable pageable,
        Integer memberNo);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(Pageable pageable,
        Integer memberNo);
}
