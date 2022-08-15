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

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssues(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssues(pageable);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsed(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUsed(pageable);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnused(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnused(pageable);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesByMemberNo(pageable, memberNo);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUsedByMemberNo(pageable, memberNo);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedByMemberNo(pageable, memberNo);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndExpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(pageable, memberNo);
    }

    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(pageable, memberNo);
    }
}
