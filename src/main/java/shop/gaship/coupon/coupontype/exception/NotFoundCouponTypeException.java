package shop.gaship.coupon.coupontype.exception;

/**
 * @author : 조재철
 * @since 1.0
 */
public class NotFoundCouponTypeException extends RuntimeException {

    public static final String MESSAGE = "해당 쿠폰 종류가 존재하지 않습니다.";

    public NotFoundCouponTypeException() {
        super(MESSAGE);
    }
}
