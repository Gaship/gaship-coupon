package shop.gaship.coupon.coupontype.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gaship.coupon.coupontype.entity.CouponTypeDto;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * The type Coupon type rest controller.
 *
 * @author : 최겸준
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
    public ResponseEntity<Void> couponTypeAdd(@RequestBody @Valid CouponTypeDto couponType) {
        return ResponseEntity.ok().build();
    }
}
