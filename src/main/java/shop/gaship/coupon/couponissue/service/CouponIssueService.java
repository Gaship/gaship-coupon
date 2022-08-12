package shop.gaship.coupon.couponissue.service;

import java.time.LocalDateTime;
import shop.gaship.coupon.couponissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponIssueService {
    void addCouponIssue(CouponIssueCreationRequestDto couponTypeDto);
}
