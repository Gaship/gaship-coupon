package shop.gaship.coupon.coupongenerationissue.exception;

/**
 * 생성, 발급되지 않은 쿠폰을 조회하고자 할때 발생시키는 에러 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public class NotFoundCouponGenerationIssueException extends RuntimeException {

    public static final String MESSAGE = "해당 생성 발급 된 쿠폰이 존재하지 않습니다.";

    public NotFoundCouponGenerationIssueException() {
        super(MESSAGE);
    }
}
