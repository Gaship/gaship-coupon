package shop.gaship.coupon.coupongenerationissue.service;

import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponIssueService {
    void addCouponGenerationIssue(CouponIssueCreationRequestDto couponTypeDto);
}
