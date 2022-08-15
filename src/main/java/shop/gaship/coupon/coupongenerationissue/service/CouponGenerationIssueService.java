package shop.gaship.coupon.coupongenerationissue.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;

/**
 * 쿠폰 생성 발급에 대한 비즈니스 로직을 처리하는 service 인터페이스 클래스 입니다.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueService {

    void addCouponIssue(CouponIssueCreationRequestDto couponTypeDto);

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
}
