package shop.gaship.coupon.coupontype.service;

/**
 * The interface Coupon type service.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponTypeService {

    /**
     * 해당 쿠폰타입에 해당하는 쿠폰을 생성, 발급을 막기위해 stop_generation_issues를 수정하는 메서드 입니다.
     *
     * @param couponTypeNo 수정하고자 하는 쿠폰타입의 번호 입니다.
     */
    void modifyCouponTypeStopGenerationIssue(Integer couponTypeNo);

    /**
     * coupon type을 삭제하기 위한 메서드 입니다.
     *
     * @param couponTypeNo 삭제하고자 하는 쿠폰타입의 번호 입니다.
     */
    void deleteCouponType(Integer couponTypeNo);

}
