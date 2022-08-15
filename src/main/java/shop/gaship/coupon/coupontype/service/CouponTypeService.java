package shop.gaship.coupon.coupontype.service;

import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * 쿠폰종류에 관한 service 로직을 담당하는 기능이 모여있는 interface입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
public interface CouponTypeService {
    /**
     * 쿠폰타입 추가요청에 대한 비지니스로직을 처리합니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     * @author 최겸준
     */
    void addCouponType(CouponTypeCreationRequestDto couponTypeCreationRequestDto);

    /**
     * 추천인쿠폰 변경요청에 대한 비지니스로직을 처리합니다.
     * 실제로는 변경이아니라 기존의 것을 새로운것으로 대체하는 작업입니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     * @author 최겸준
     */
    void modifyRecommendMemberCoupon(CouponTypeCreationRequestDto couponTypeCreationRequestDto);

    /**
     * 생성하기 위한 dto를 CouponTypeEntity로 변경시켜주는 편의 메소드입니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     * @return CouponTypeEntity가 반환됩니다.
     * @author 최겸준
     */
    default CouponType couponTypeCreationRequestDtoToCouponTypeEntity(CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        return CouponType.builder()
            .name(couponTypeCreationRequestDto.getName())
            .discountAmount(couponTypeCreationRequestDto.getDiscountAmount())
            .discountRate(couponTypeCreationRequestDto.getDiscountRate())
            .isStopGenerationIssue(Boolean.FALSE)
            .build();
    }
}
