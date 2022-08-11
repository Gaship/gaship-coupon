package shop.gaship.coupon.coupontype.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@RequestMapping("/api/coupon-types")
@Controller
@RequiredArgsConstructor
public class CouponTypeRestController {
    private final CouponTypeService couponTypeService;

    @PostMapping
    public ResponseEntity<Void> couponTypeAdd() {
        return ResponseEntity.ok().build();
    }
}
