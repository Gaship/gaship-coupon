package shop.gaship.coupon.coupontype.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * 쿠폰종류에 관한 service 로직을 담당하는 기능이 모여있는 interface입니다.
 *
 * @author : 최겸준, 조재철
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
     * 추천인쿠폰 변경요청에 대한 비지니스로직을 처리합니다. 실제로는 변경이아니라 기존의 것을 새로운것으로 대체하는 작업입니다.
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
    default CouponType couponTypeCreationRequestDtoToCouponTypeEntity(
        CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        return CouponType.builder()
                         .name(couponTypeCreationRequestDto.getName())
                         .discountAmount(couponTypeCreationRequestDto.getDiscountAmount())
                         .discountRate(couponTypeCreationRequestDto.getDiscountRate())
                         .isStopGenerationIssue(Boolean.FALSE)
                         .build();
    }

    /**
     * 해당 쿠폰타입에 해당하는 쿠폰을 생성, 발급을 막기위해 stop_generation_issues 를 수정하는 service 메서드 입니다.
     *
     * @param couponTypeNo 수정하고자 하는 쿠폰타입의 번호 입니다.
     */
    void modifyCouponTypeStopGenerationIssue(Integer couponTypeNo);

    /**
     * coupon type 을 삭제하기 위한 service 메서드 입니다.
     *
     * @param couponTypeNo 삭제하고자 하는 쿠폰타입의 번호 입니다.
     */
    void deleteCouponType(Integer couponTypeNo);

    /**
     * 모든 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 모든 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypes(Pageable pageable);

    /**
     * 아직 생성, 발급 되지 않은 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 아직 생성, 발급 되지 않은 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypesCanDelete(Pageable pageable);

    /**
     * 이미 생성, 발급 된 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 이미 생성, 발급 된 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypesCannotDelete(Pageable pageable);

    /**
     * 정액 할인 정책을 가진 coupont type 의 Page 타입만큼 조회하기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정액 할인 정책인 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypesFixedAmount(Pageable pageable);

    /**
     * 정률 할인 정책을 가진 coupont type 의 Page 타입만큼 조회하기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정률 할인 정책인 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypesFixedRate(Pageable pageable);

    Page<CouponTypeDto> findCouponTypeRecommend(Pageable pageable);
}
