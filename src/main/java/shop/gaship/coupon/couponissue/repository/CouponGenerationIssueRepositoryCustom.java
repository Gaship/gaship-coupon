package shop.gaship.coupon.couponissue.repository;

/**
 * couponGenerationIssue 의 repository(crud 를 위한) 의 custom 인터페이스 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepositoryCustom {

    /**
     * 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰이 존재하는지 체크하는 메서드 입니다.
     *
     * @param couponTypeNo 쿠폰 타입 번호 입니다.
     * @return 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰의 존재 여부 반환.
     */
    Boolean existCouponHasCouponTypeNo(Integer couponTypeNo);
}
