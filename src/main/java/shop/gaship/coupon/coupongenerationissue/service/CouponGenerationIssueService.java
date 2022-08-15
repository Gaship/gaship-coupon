package shop.gaship.coupon.coupongenerationissue.service;

import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;

/**
 * 쿠폰 생성 및 발급요청에 대한 business 로직 처리를 담당합니다.
 *
 * @author 최겸준
 * @since 1.0
 */
public interface CouponGenerationIssueService {
    /**
     * 쿠폰 생성 및 발급에대한 client의 요청을 adapter에 전달합니다.
     *
     * @param couponGenerationIssueCreationRequestDto 쿠폰생성 및 발급을 위해 client에서 넘겨주는 정보가 들어있는 클래스입니다.
     * @author 최겸준
     */
    void addCouponGenerationIssue(
        CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto);

    /**
     * 추천인쿠폰종류에 맞게 쿠폰을 생성발급하기위한 기능입니다.
     *
     * @param recommendMemberNo 지급해야할 추천인번호입니다.
     */
    void addCouponGenerationIssueToRecommendMember(Integer recommendMemberNo);

    /**
     * 쿠폰의 상세조회 요청을 할때 처리할 메서드입니다.
     *
     * @param couponGenerationIssueNo
     * @return
     */
    CouponGenerationIssueDetailsResponseDto findCouponGenerationIssue(
        Integer couponGenerationIssueNo);
}
