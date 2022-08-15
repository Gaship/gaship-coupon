package shop.gaship.coupon.coupontype.exception;

/**
 * 쿠폰종류가 존재하지 않을때 발생시킬 예외클래스입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
public class CouponTypeNotFoundException extends RuntimeException {

    public static final String MESSAGE = "쿠폰종류를 찾을수 없습니다.";

    public CouponTypeNotFoundException() {
        super(MESSAGE);
    }
}
