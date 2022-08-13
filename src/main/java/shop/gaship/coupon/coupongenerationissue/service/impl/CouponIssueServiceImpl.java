package shop.gaship.coupon.coupongenerationissue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.coupongenerationissue.adapter.CouponAdapter;
import shop.gaship.coupon.coupongenerationissue.adapter.MemberAdapter;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.repository.CouponIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponIssueService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponIssueServiceImpl implements CouponIssueService {
    private final CouponIssueRepository couponIssueRepository;
    private final MemberAdapter memberAdapter;
    private final CouponAdapter couponAdapter;

    @Override
    public void addCouponGenerationIssue(CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        couponAdapter.addCouponGenerationIssue(couponIssueCreationRequestDto);
    }
}
