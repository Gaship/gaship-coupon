package shop.gaship.coupon.coupongenerationissue.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.UnusedMemberCouponResponseDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.dto.response.PageResponse;

/**
 * 쿠폰생성발급에 대한 요청을 처리하기위한 controller class입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@RestController
@RequestMapping("/api/coupons/coupon-generations-issues")
@RequiredArgsConstructor
public class CouponGenerationIssueRestController {

    private final CouponGenerationIssueService couponGenerationIssueService;

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
    @GetMapping("/unused-coupons")
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

    /**
     * 주문으로 인하여 쿠폰을 사용상태로 변경 요청을 처리하는 메서드.
     *
     * @param couponGenerationIssueNumbers 사용하고자 하는 쿠폰생성발급 번호.
     * @return 요청에 대한 처리가 잘되었다는 응답을 반환.
     */
    @PatchMapping("/used")
    public ResponseEntity<Void> useCoupons(@RequestBody List<Integer> couponGenerationIssueNumbers) {
        couponGenerationIssueService.useCoupons(couponGenerationIssueNumbers);

        return ResponseEntity.ok().build();
    }

    /**
     * 주문 취소로 인하여 사용하였던 쿠폰을 사용 전으로 되돌리는 요청을 처리하는 메서드.
     *
     * @param couponIssueNumbers 사용 취소하고자 하는 쿠폰생성발급 번호.
     * @return 요청에 대한 처리가 잘되었다는 응답을 반환.
     */
    @PatchMapping("/used-to-cancel")
    public ResponseEntity<Void> cancelUsedCoupons(@RequestBody List<Integer> couponIssueNumbers) {
        couponGenerationIssueService.cancelUsedCoupons(couponIssueNumbers);

        return ResponseEntity.ok().build();
    }

    /**
     * 쿠폰을 생성 및 발급하기위한 요청을 처리하는 기능입니다.
     *
     * @param couponGenerationIssueCreationRequestDto 쿠폰생성 및 발급을 위해 client에서 넘겨주는 정보가 들어있는 클래스입니다.
     * @return 성공적으로 요청이 이루어질시에 ResponseEntity의 body에 void를 넣고 상태코드 201을 반환합니다.
     * @author 최겸준
     */
    @PostMapping
    public ResponseEntity<Void> couponGenerationIssueAdd(@RequestBody
    @Valid CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {
        couponGenerationIssueService.addCouponGenerationIssue(
            couponGenerationIssueCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 추천인쿠폰 생성 및 발급 요청을 처리하는 기능입니다.
     *
     * @param recommendMemberNo 추천인번호를 나타냅니다.
     * @return
     */
    @PostMapping("/{recommendMemberNo}")
    public ResponseEntity<Void> recommendMemberCouponGenerationIssueAdd(
        @PathVariable Integer recommendMemberNo) {
        couponGenerationIssueService.addCouponGenerationIssueToRecommendMember(recommendMemberNo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 쿠폰의 상세조회 요청을 할때 처리할 메서드입니다.
     *
     * @param couponGenerationIssueNo 조회시 기준이 되는 쿠폰생성발급 테이블의 로우번호입니다.
     * @return body에 CouponGenerationIssueDetailsResponseDto를 담아서 200 상태코드와 함께 반환합니다.
     */
    @GetMapping("/{couponGenerationIssueNo}")
    public ResponseEntity<CouponGenerationIssueDetailsResponseDto> couponGenerationIssueDetails(
        @PathVariable Integer couponGenerationIssueNo) {
        return ResponseEntity.ok(
            couponGenerationIssueService.findCouponGenerationIssue(couponGenerationIssueNo));
    }

    /**
     * 회원의 현재 사용가능한 쿠폰 다건 조회 요청을 처리합니다.
     *
     * @param memberNo 쿠폰 다건 조회 대상이 되는 회원의 식별번호입니다.
     * @return 회원의 사용가능한 쿠폰 목록을 body 로 갖는 상태코드 200의 ResponseEntity 를 반환합니다.
     * @author 김세미
     */
    @GetMapping(params = "memberNo")
    public ResponseEntity<List<UnusedMemberCouponResponseDto>> unusedMemberCouponList(
            @RequestParam Integer memberNo) {
        List<UnusedMemberCouponResponseDto> a = couponGenerationIssueService
                .findUnusedMemberCouponList(memberNo);

        return ResponseEntity.ok(a);
    }
}
