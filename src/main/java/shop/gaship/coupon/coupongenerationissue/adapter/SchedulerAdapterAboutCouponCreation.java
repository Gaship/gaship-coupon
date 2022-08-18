package shop.gaship.coupon.coupongenerationissue.adapter;

import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;

/**
 * 쿠폰 기능을 수행하기위해서 외부와의 통신이 필요할때 사용할 adapter interface입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
public interface SchedulerAdapterAboutCouponCreation {

    /**
     * 쿠폰 생성 및 발급 요청을 직접 통신하는 기능입니다.
     *
     * @param couponGenerationIssueCreationRequestDto 쿠폰생성 및 발급을 위해 client에서 넘겨주는 정보가 들어있는 클래스입니다.
     * @author 최겸준
     */
    void addCouponGenerationIssue(CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto);
}
