package shop.gaship.coupon.coupongenerationissue.exception;

/**
 * @author : 최겸준
 * @since 1.0
 */
public class CouponGenerationIssueNotFoundException extends RuntimeException {
    public static final String MESSAGE = "해당 쿠폰을 찾을 수 없습니다.";
    public CouponGenerationIssueNotFoundException() {
        super(MESSAGE);
    }
}
