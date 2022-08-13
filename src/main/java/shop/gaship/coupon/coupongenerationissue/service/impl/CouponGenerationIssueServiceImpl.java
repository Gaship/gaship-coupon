package shop.gaship.coupon.coupongenerationissue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.coupongenerationissue.adapter.CouponAdapter;
import shop.gaship.coupon.coupongenerationissue.adapter.MemberAdapter;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;

/**
 * couponGenerationIssueService를 구현한 클래스입니다.
 *
 * @author 최겸준
 * @see CouponGenerationIssueService
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponGenerationIssueServiceImpl implements CouponGenerationIssueService {
    private final CouponGenerationIssueRepository couponGenerationIssueRepository;
    private final MemberAdapter memberAdapter;
    private final CouponAdapter couponAdapter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCouponGenerationIssue(
        CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {
        couponAdapter.addCouponGenerationIssue(couponGenerationIssueCreationRequestDto);
    }
}
