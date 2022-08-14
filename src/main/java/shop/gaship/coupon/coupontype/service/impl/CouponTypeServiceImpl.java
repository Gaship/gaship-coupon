package shop.gaship.coupon.coupontype.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.coupon.coupongenerationissue.exception.CouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.exception.RecommendMemberCouponTypeNotFoundException;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.coupontype.service.CouponTypeService;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

/**
 * The type Coupon type service.
 *
 * @author 최겸준
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl implements CouponTypeService {
    private final CouponTypeRepository couponTypeRepository;
    private final RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    @Transactional
    @Override
    public void addCouponType(CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        CouponType couponType =
            couponTypeCreationRequestDtoToCouponTypeEntity(couponTypeCreationRequestDto);

        couponTypeRepository.save(couponType);
    }

    @Transactional
    @Override
    public void modifyRecommendMemberCoupon(
        CouponTypeCreationRequestDto couponTypeCreationRequestDto) {

        Optional<RecommendMemberCouponType> optionalRecommendMemberCouponType
            = recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc();
        
        creationCouponTypeAndRecommendMemberCouponType(couponTypeCreationRequestDto);

        if (optionalRecommendMemberCouponType.isEmpty()) return;
        changePrevRecommendMemberCouponTypeAsStop(optionalRecommendMemberCouponType);
    }

    private void creationCouponTypeAndRecommendMemberCouponType(CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        CouponType couponType = couponTypeCreationRequestDtoToCouponTypeEntity(
            couponTypeCreationRequestDto);
        couponTypeRepository.save(couponType);

        RecommendMemberCouponType recommendMemberCouponType = new RecommendMemberCouponType();
        recommendMemberCouponType.setCouponType(couponType);
        recommendMemberCouponType.setRegisterDatetime(LocalDateTime.now());
        recommendMemberCouponTypeRepository.save(recommendMemberCouponType);
    }

    private void changePrevRecommendMemberCouponTypeAsStop(Optional<RecommendMemberCouponType> optionalRecommendMemberCouponType) {
        Integer couponTypeNo = optionalRecommendMemberCouponType.orElseThrow(RecommendMemberCouponTypeNotFoundException::new).getCouponTypeNo();
        CouponType couponType = couponTypeRepository.findById(couponTypeNo).orElseThrow(
            CouponTypeNotFoundException::new);

        couponType.setIsStopGenerationIssue(Boolean.TRUE);
    }
}
