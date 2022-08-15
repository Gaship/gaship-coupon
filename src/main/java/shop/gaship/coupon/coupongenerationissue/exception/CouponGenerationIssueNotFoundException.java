package shop.gaship.coupon.coupongenerationissue.exception;

/**
 * 조회한 쿠폰생성발급 정보가 없을때 발생시킬 예외클래스입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
public class CouponGenerationIssueNotFoundException extends RuntimeException {
    public static final String MESSAGE = "해당 쿠폰을 찾을 수 없습니다.";
    public CouponGenerationIssueNotFoundException() {
        super(MESSAGE);
    }
}
