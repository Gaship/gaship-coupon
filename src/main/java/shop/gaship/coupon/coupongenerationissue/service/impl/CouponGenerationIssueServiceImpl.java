package shop.gaship.coupon.coupongenerationissue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.coupongenerationissue.adapter.MemberAdapter;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;

/**
 * 쿠폰 생성 발급에 대한 비즈니스 로직을 처리하는 service 구현체 클래스 입니다.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponGenerationIssueServiceImpl implements CouponGenerationIssueService {

    private final CouponGenerationIssueRepository couponGenerationIssueRepository;
    private final MemberAdapter memberAdapter;

    @Override
    public void addCouponIssue(CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
    }

    /**
     * 생성, 발급된 모든 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssues(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssues(pageable);
    }

    /**
     * 생성, 발급된 모든 쿠폰 중 사용된 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsed(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUsed(pageable);
    }

    /**
     * 생성, 발급된 모든 쿠폰 중 사용하지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnused(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnused(pageable);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesByMemberNo(pageable, memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용한 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUsedByMemberNo(pageable, memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedByMemberNo(pageable, memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않고 만료가 된 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndExpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(pageable,
            memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않고 만료가 되지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(pageable,
            memberNo);
    }
}
