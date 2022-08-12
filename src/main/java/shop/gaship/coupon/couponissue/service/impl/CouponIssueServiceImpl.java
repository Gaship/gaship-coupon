package shop.gaship.coupon.couponissue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.couponissue.adapter.MemberAdapter;
import shop.gaship.coupon.couponissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.couponissue.repository.CouponIssueRepository;
import shop.gaship.coupon.couponissue.service.CouponIssueService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponIssueServiceImpl implements CouponIssueService {
    private final CouponIssueRepository couponIssueRepository;
    private final MemberAdapter memberAdapter;

    @Override
    public void addCouponIssue(CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
    }
}
