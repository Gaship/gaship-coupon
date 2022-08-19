package shop.gaship.coupon.coupongenerationissue.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;

/**
 * 쿠폰 생성 발급에 대한 비즈니스 로직을 처리하는 service 인터페이스 클래스 입니다.
 *
 * @author 최겸준, 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueService {

    /**
     * 생성, 발급된 모든 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssues(Pageable pageable);

    /**
     * 생성, 발급된 모든 쿠폰 중 사용된 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsed(Pageable pageable);

    /**
     * 생성, 발급된 모든 쿠폰 중 사용하지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnused(Pageable pageable);

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesByMemberNo(Pageable pageable, Integer memberNo);

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용한 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않고 만료가 된 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndExpiredByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않고 만료가 되지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 주문시 해당 쿠폰을 사용시 비즈니스 로직을 처리하는 메서드.
     *
     * @param couponGenerationIssueNumbers 사용하고자 하는 쿠폰생성발급 번호.
     */
    void useCoupons(List<Integer> couponGenerationIssueNumbers);

    /**
     * 주문 취소시 해당 사용 쿠폰을 취소하는 비즈니스 로직을 처리하는 메서드.
     *
     * @param couponGenerationIssueNumbers 사용 취소하고자 하는 쿠폰생성발급 번호.
     */
    void cancelUsedCoupons(List<Integer> couponGenerationIssueNumbers);

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
