package shop.gaship.coupon.coupongenerationissue.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.dto.response.PageResponse;

/**
 * CouponIssue 관련 요청을 받는 클래스 입니다.
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

    @GetMapping
    public ResponseEntity<PageResponse<CouponGenerationIssueResponseDto>> couponGenerationIssueList(Pageable pageable) {
        Page<CouponGenerationIssueResponseDto> couponGenerationIssuePage =
            couponGenerationIssueService.findCouponGenerationIssues(pageable);

        PageResponse<CouponGenerationIssueResponseDto> couponGenerationIssueResponsePage =
            new PageResponse<>(couponGenerationIssuePage);

        return ResponseEntity.ok(couponGenerationIssueResponsePage);
    }
}
