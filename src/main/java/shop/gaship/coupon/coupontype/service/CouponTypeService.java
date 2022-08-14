package shop.gaship.coupon.coupontype.service;

import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * The interface Coupon type service.
 *
 * @author 최겸준
 * @since 1.0
 */
public interface CouponTypeService {
    void addCouponType(CouponTypeCreationRequestDto couponTypeCreationRequestDto);

    void modifyRecommendMemberCoupon(CouponTypeCreationRequestDto couponTypeCreationRequestDto);

    default CouponType couponTypeCreationRequestDtoToCouponTypeEntity(CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        CouponType couponType = CouponType.builder()
            .name(couponTypeCreationRequestDto.getName())
            .discountAmount(couponTypeCreationRequestDto.getDiscountAmount())
            .discountRate(couponTypeCreationRequestDto.getDiscountRate())
            .isStopGenerationIssue(Boolean.FALSE)
            .build();
        return couponType;
    }
}
