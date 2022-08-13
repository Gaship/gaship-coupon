package shop.gaship.coupon.couponissue.repository;

/**
 * couponGenerationIssue 의 repository(crud 를 위한) 의 custom 인터페이스 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepositoryCustom {

    /**
     * couponGenerationIssue 의 repository(crud 를 위한) 의 custom 구현체 입니다.
     *
     * @author : 조재철
     * @since 1.0
     */
    Long countByCouponTypeNo(Integer couponTypeNo);
}
