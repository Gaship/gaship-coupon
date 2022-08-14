package shop.gaship.coupon.coupongenerationissue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.exception.CouponGenerationIssueNotFoundException;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.coupontype.entity.CouponType;

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
    private final SchedulerAdapterAboutCouponCreation schedulerAdapterAboutCouponCreation;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCouponGenerationIssue(
        CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {

        schedulerAdapterAboutCouponCreation.addCouponGenerationIssue(couponGenerationIssueCreationRequestDto);
    }

    @Override
    public CouponGenerationIssueDetailsResponseDto findCouponGenerationIssue(
        Integer couponGenerationIssueNo) {

        CouponGenerationIssue coupon =
            couponGenerationIssueRepository.findCouponGenerationIssueByIdAsFetchJoin(
                    couponGenerationIssueNo)
                .orElseThrow(CouponGenerationIssueNotFoundException::new);

        CouponType couponType = coupon.getCouponType();
        return new CouponGenerationIssueDetailsResponseDto(couponType.getName(),
            couponType.getDiscountAmount(), couponType.getDiscountRate(),
            coupon.getGenerationDatetime(), coupon.getIssueDatetime(),
            coupon.getExpirationDatetime(), coupon.getUsedDatetime(), coupon.getMemberNo());
    }
}
