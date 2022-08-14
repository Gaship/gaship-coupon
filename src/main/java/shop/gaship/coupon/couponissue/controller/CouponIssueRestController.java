package shop.gaship.coupon.couponissue.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.coupon.couponissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.couponissue.service.CouponGenerationIssueService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@RestController
@RequestMapping("/api/coupon-issues")
@RequiredArgsConstructor
public class CouponIssueRestController {

    private final CouponGenerationIssueService couponGenerationIssueService;

    @PostMapping
    public ResponseEntity<Void> couponIssueAdd(
        @RequestBody @Valid CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        couponGenerationIssueService.addCouponIssue(couponIssueCreationRequestDto);
        return ResponseEntity.ok().build();
    }
}
