package shop.gaship.coupon.coupongenerationissue.service.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.exception.CouponGenerationIssueNotFoundException;
import shop.gaship.coupon.coupongenerationissue.exception.CouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.exception.RecommendMemberCouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

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
    private final CouponTypeRepository couponTypeRepository;
    private final RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void addCouponGenerationIssue(
        CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {
        schedulerAdapterAboutCouponCreation.addCouponGenerationIssue(
            couponGenerationIssueCreationRequestDto);
    }

    @Transactional
    @Override
    public void addCouponGenerationIssueToRecommendMember(Integer recommendMemberNo) {
        RecommendMemberCouponType recommendMemberCouponType =
            recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc().orElseThrow(
                RecommendMemberCouponTypeNotFoundException::new);

        CouponType couponType =
            couponTypeRepository.findById(recommendMemberCouponType.getCouponTypeNo())
                .orElseThrow(CouponTypeNotFoundException::new);

        CouponGenerationIssue couponGenerationIssue = CouponGenerationIssue.builder()
            .couponType(couponType)
            .memberNo(recommendMemberNo)
            .generationDatetime(LocalDateTime.now())
            .issueDatetime(LocalDateTime.now())
            .expirationDatetime(LocalDateTime.now())
            .build();
        couponGenerationIssueRepository.save(couponGenerationIssue);
    }

    @Transactional(readOnly = true)
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
