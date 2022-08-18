package shop.gaship.coupon.coupongenerationissue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.coupongenerationissue.adapter.MemberAdapter;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponGenerationIssueServiceImpl implements CouponGenerationIssueService {

    private final CouponGenerationIssueRepository couponIssueRepository;
    private final MemberAdapter memberAdapter;

    @Override
    public void addCouponIssue(CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
    }
}
