package shop.gaship.coupon.couponissue.service;

import java.time.LocalDateTime;
import shop.gaship.coupon.couponissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.couponissue.entity.CouponIssue;

/**
 * @author : 최겸준
 * @since 1.0
 */
public interface CouponIssueService {
    void addCouponIssue(CouponIssueCreationRequestDto couponTypeDto);

    default CouponIssue requestDtoToCouponIssueEntity(CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        LocalDateTime expirationDatetime = LocalDateTime.now().plusMonths(couponIssueCreationRequestDto.getExpirationMonth());
        return CouponIssue.builder()
            .memberNo(couponIssueCreationRequestDto.getMemberNo())
            .issueDatetime(LocalDateTime.now())
            .expirationDatetime(expirationDatetime)
            .isUsed(Boolean.FALSE)
            .build();
    }
}
