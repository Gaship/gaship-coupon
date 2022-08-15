package shop.gaship.coupon.coupongenerationissue.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponGenerationIssueService {
    void addCouponIssue(CouponIssueCreationRequestDto couponTypeDto);

    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssues(Pageable pageable);
}
