package shop.gaship.coupon.coupongenerationissue.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponIssueService;

/**
 * 쿠폰생성발급에 대한 요청을 처리하기위한 controller class입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
@RestController
@RequestMapping("/api/coupon-issues")
@RequiredArgsConstructor
public class CouponGenerationIssueRestController {
    private final CouponIssueService couponIssueService;

    @PostMapping
    public ResponseEntity<Void> couponGenerationIssueAdd(@RequestBody @Valid CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        couponIssueService.addCouponGenerationIssue(couponIssueCreationRequestDto);
        return ResponseEntity.ok().build();
    }
}
