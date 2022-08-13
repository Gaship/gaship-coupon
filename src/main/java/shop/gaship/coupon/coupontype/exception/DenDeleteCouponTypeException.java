package shop.gaship.coupon.coupontype.exception;

/**
 * CouponType 엔티티를 수정, 삭제하고자 할때 이미 해당 쿠폰 타입의 쿠폰이 생성, 발급 되어있을 시 발생하는 에러 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public class DenDeleteCouponTypeException extends RuntimeException {

    public static final String MESSAGE = "해당 쿠폰 종류에 대한 쿠폰이 이미 생성, 발급 되었기 때문에 수정 불가능 합니다.";

    public DenDeleteCouponTypeException() {
        super(MESSAGE);
    }
}
