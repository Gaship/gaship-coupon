package shop.gaship.coupon.coupontype.exception;

/**
 * @author : 조재철
 * @since 1.0
 */
public class DenyChangeCouponTypeException extends RuntimeException {

    public static final String MESSAGE = "해당 쿠폰 종류에 대한 쿠폰이 이미 생성, 발급 되었기 때문에 수정 불가능 합니다.";

    public DenyChangeCouponTypeException() {
        super(MESSAGE);
    }
}
