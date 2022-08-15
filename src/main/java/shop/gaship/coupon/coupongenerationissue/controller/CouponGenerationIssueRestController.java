package shop.gaship.coupon.coupongenerationissue.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.dto.response.PageResponse;

/**
 * CouponIssue 관련 요청을 처리하는 클래스 입니다.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
@RestController
@RequestMapping("/api/coupon-generations-issues")
@RequiredArgsConstructor
public class CouponGenerationIssueRestController {

    private final CouponGenerationIssueService couponGenerationIssueService;

    @PostMapping
    public ResponseEntity<Void> couponIssueAdd(
        @RequestBody @Valid CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        couponGenerationIssueService.addCouponIssue(couponIssueCreationRequestDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 쿠폰생성발급 전체 조회 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueList(Pageable pageable) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssuePage =
            couponGenerationIssueService.findCouponGenerationIssues(pageable);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueResponsePage =
            new PageResponse<>(couponGenerationIssuePage);

        return ResponseEntity.ok(couponGenerationIssueResponsePage);
    }

    /**
     * 사용된 쿠폰생성발급 전체 조회 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/used-coupons")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueUsedList(
        Pageable pageable) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueUsedPage =
            couponGenerationIssueService.findCouponGenerationIssuesUsed(pageable);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponsePage =
            new PageResponse<>(couponGenerationIssueUsedPage);

        return ResponseEntity.ok(couponGenerationIssueUsedResponsePage);
    }

    /**
     * 사용되지 않은 쿠폰생성발급 전체 조회 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/unused-couopns")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueUnusedList(
        Pageable pageable) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedPage =
            couponGenerationIssueService.findCouponGenerationIssuesUnused(pageable);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedResponsePage =
            new PageResponse<>(couponGenerationIssueUnusedPage);

        return ResponseEntity.ok(couponGenerationIssueUnusedResponsePage);
    }

    /**
     * 회원이 자신의 쿠폰을 전체 조회하는 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueByMemberNoList(
        Pageable pageable, @PathVariable(value = "memberNo") Integer memberNo) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueByMemberNoPage =
            couponGenerationIssueService.findCouponGenerationIssuesByMemberNo(pageable, memberNo);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueByMemberNoResponsePage =
            new PageResponse<>(couponGenerationIssueByMemberNoPage);

        return ResponseEntity.ok(couponGenerationIssueByMemberNoResponsePage);
    }

    /**
     * 회원이 자신의 사용한 쿠폰을 전체 조회하는 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/member/{memberNo}/used-coupons")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueUsedByMemberNoList(
        Pageable pageable, @PathVariable(value = "memberNo") Integer memberNo) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueUsedByMemberNoPage =
            couponGenerationIssueService.findCouponGenerationIssuesUsedByMemberNo(pageable, memberNo);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueUsedByMemberNoResponsePage =
            new PageResponse<>(couponGenerationIssueUsedByMemberNoPage);

        return ResponseEntity.ok(couponGenerationIssueUsedByMemberNoResponsePage);
    }

    /**
     * 회원이 자신의 사용하지 않은 쿠폰을 전체 조회하는 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/member/{memberNo}/unused-coupons")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueUnusedByMemberNoList(
        Pageable pageable, @PathVariable(value = "memberNo") Integer memberNo) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedByMemberNoPage =
            couponGenerationIssueService.findCouponGenerationIssuesUnusedByMemberNo(pageable, memberNo);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedByMemberNoResponsePage =
            new PageResponse<>(couponGenerationIssueUnusedByMemberNoPage);

        return ResponseEntity.ok(couponGenerationIssueUnusedByMemberNoResponsePage);
    }

    /**
     * 회원이 자신의 사용하지 않았지만 이미 만료된 쿠폰을 전체 조회하는 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/member/{memberNo}/unused-coupons/expired-coupons")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>>
        couponGenerationIssueUnusedAndExpiredByMemberNoList(
        Pageable pageable, @PathVariable(value = "memberNo") Integer memberNo) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredByMemberNoPage =
            couponGenerationIssueService.findCouponGenerationIssuesUnusedAndExpiredByMemberNo(pageable, memberNo);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredByMemberNoResponsePage =
            new PageResponse<>(couponGenerationIssueUnusedAndExpiredByMemberNoPage);

        return ResponseEntity.ok(couponGenerationIssueUnusedAndExpiredByMemberNoResponsePage);
    }

    /**
     * 회원이 자신의 사용하지 않고 만료되지 않은 쿠폰을 전체 조회하는 요청을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 요청이 잘 처리되었다는 응답과, 요청에 대한 데이터(페이지).
     */
    @GetMapping("/member/{memberNo}/unused-coupons/unexpired-coupons")
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>>
        couponGenerationIssueUnusedAndUnexpiredByMemberNoList(
        Pageable pageable, @PathVariable(value = "memberNo") Integer memberNo) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredByMemberNoPage =
            couponGenerationIssueService.findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(pageable, memberNo);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredByMemberNoResponsePage =
            new PageResponse<>(couponGenerationIssueUnusedAndUnexpiredByMemberNoPage);

        return ResponseEntity.ok(couponGenerationIssueUnusedAndUnexpiredByMemberNoResponsePage);
    }
}
