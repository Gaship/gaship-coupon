package shop.gaship.coupon.couponissue.service;

import shop.gaship.coupon.couponissue.dto.request.CouponIssueCreationRequestDto;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponGenerationIssueService {
    void addCouponIssue(CouponIssueCreationRequestDto couponTypeDto);
}
