package shop.gaship.coupon.coupongenerationissue.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;

/**
 * 쿠폰 생성 발급 엔티티에 대한 커스텀 repository 구현체 인터페이스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public interface CouponGenerationIssueRepositoryCustom {

    /**
     * 생성, 발급된 전체 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssues(Pageable pageable);

    /**
     * 생성, 발급된 전체 쿠폰 중 사용된 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUsed(Pageable pageable);

    /**
     * 생성, 발급된 전체 쿠폰 중 사용되지 않은 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnused(Pageable pageable);

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesByMemberNo(Pageable pageable, Integer memberNo);

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용된 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용되지 않은 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용되지 않고 만료된 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(Pageable pageable,
        Integer memberNo);

    /**
     * 특정 회원의 생성, 발급된 전체 쿠폰 중 사용되지 않고 만료되지 않은 쿠폰을 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 쿼리의 결과 (페이지).
     */
    Page<CouponGenerationIssueResponseDto> findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(Pageable pageable,
        Integer memberNo);
}
