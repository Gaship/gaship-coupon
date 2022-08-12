package shop.gaship.coupon.couponissue.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.coupon.couponissue.dto.request.CouponIssueCreationRequestDto;
import shop.gaship.coupon.couponissue.service.CouponIssueService;
import shop.gaship.coupon.coupontype.entity.CouponTypeDto;

/**
 * @author : 최겸준
 * @since 1.0
 */
@RestController
@RequestMapping("/api/coupon-issues")
@RequiredArgsConstructor
public class CouponIssueRestController {
    private final CouponIssueService couponIssueService;

    @PostMapping
    public ResponseEntity<Void> couponIssueAdd(@RequestBody @Valid CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        // Todo : dto만들고 dtoToEntity만들어야함
        couponIssueService.addCouponIssue(couponIssueCreationRequestDto);
        return ResponseEntity.ok().build();
    }
}
