package shop.gaship.coupon.coupontype.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.coupontype.service.CouponTypeService;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

/**
 * CouponTypeService를 구현한 클래스입니다.
 *
 * @author 최겸준
 * @see shop.gaship.coupon.coupontype.service.CouponTypeService
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl implements CouponTypeService {
    private final CouponTypeRepository couponTypeRepository;
    private final RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void addCouponType(CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        CouponType couponType =
            couponTypeCreationRequestDtoToCouponTypeEntity(couponTypeCreationRequestDto);

        couponTypeRepository.save(couponType);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void modifyRecommendMemberCoupon(
        CouponTypeCreationRequestDto couponTypeCreationRequestDto) {

        Optional<RecommendMemberCouponType> optionalRecommendMemberCouponType =
            recommendMemberCouponTypeRepository.findTopFetchJoinByOrderByCouponTypeNoDesc();

        createCouponTypeAndRecommendMemberCouponType(couponTypeCreationRequestDto);

        if (optionalRecommendMemberCouponType.isEmpty()) {
            return;
        }
        CouponType couponType = optionalRecommendMemberCouponType.get().getCouponType();
        couponType.setIsStopGenerationIssue(Boolean.TRUE);
    }

    /**
     * 추천인쿠폰종류가 기존에 존재했든 하지않았든간에 새로운 추천인쿠폰종류를 만들고 관련된 정보를 RecommendMemberCouponType에
     * 삽입하고 db에 연동하는 기능입니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     */
    private void createCouponTypeAndRecommendMemberCouponType(
        CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        CouponType couponType =
            couponTypeCreationRequestDtoToCouponTypeEntity(couponTypeCreationRequestDto);
        couponTypeRepository.save(couponType);

        RecommendMemberCouponType recommendMemberCouponType = new RecommendMemberCouponType();
        recommendMemberCouponType.setCouponType(couponType);
        recommendMemberCouponType.setRegisterDatetime(LocalDateTime.now());
        recommendMemberCouponTypeRepository.save(recommendMemberCouponType);
    }


}
