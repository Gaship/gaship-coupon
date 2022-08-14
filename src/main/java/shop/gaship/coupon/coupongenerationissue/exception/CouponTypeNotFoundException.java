package shop.gaship.coupon.coupongenerationissue.exception;

/**
 * @author : 최겸준
 * @since 1.0
 */
public class CouponTypeNotFoundException extends RuntimeException {

    public static final String MESSAGE = "쿠폰종류를 찾을수 없습니다.";

    public CouponTypeNotFoundException() {
        super(MESSAGE);
    }
}
