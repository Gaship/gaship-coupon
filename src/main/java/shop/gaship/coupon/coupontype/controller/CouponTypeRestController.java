package shop.gaship.coupon.coupontype.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * The type Coupon type rest controller.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
@RequestMapping("/api/coupon-types")
@Controller
@RequiredArgsConstructor
public class CouponTypeRestController {
    private final CouponTypeService couponTypeService;

    /**
     * @return response entity
     * @author 최겸준
     */
    @PostMapping
    public ResponseEntity<Void> couponTypeAdd(@RequestBody @Valid CouponType couponType) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "{couponTypeNo}/stop-generation-issue")
    public ResponseEntity<Void> couponTypeModify(@PathVariable(value = "couponTypeNo") Integer couponTypeNo) {

        couponTypeService.modifyCouponTypeStopGenerationIssue(couponTypeNo);

        return ResponseEntity.ok().build();
    }
}
