package shop.gaship.coupon.coupongenerationissue.exception;

/**
 * @author : 최겸준
 * @since 1.0
 */
public class RecommendMemberCouponTypeNotFoundException extends RuntimeException{

    public static final String MESSAGE = "추천인에게 발급할 쿠폰의 쿠폰종류가 없습니다. 먼저 쿠폰종류를 만들어주세요.";

    public RecommendMemberCouponTypeNotFoundException() {
        super(MESSAGE);
    }
}
