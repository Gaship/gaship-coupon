package shop.gaship.coupon.coupontype.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * The type Coupon type rest controller.
 *
 * @author 최겸준
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
    @PostMapping("/fixed-rate")
    public ResponseEntity<Void> couponTypeAddFixedRate(@RequestBody @Valid CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        couponTypeCreationRequestDto.setDiscountAmount(null);
        couponTypeService.addCouponType(couponTypeCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @return response entity
     * @author 최겸준
     */
    @PostMapping("/fixed-amount")
    public ResponseEntity<Void> couponTypeAddFixedAmount(@RequestBody @Valid CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        couponTypeCreationRequestDto.setDiscountRate(null);
        couponTypeService.addCouponType(couponTypeCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @return response entity
     * @author 최겸준
     */
    @PutMapping("/recommend-member-coupon")
    public ResponseEntity<Void> couponTypeModifyRecommendMemberCoupon(@RequestBody @Valid CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        couponTypeCreationRequestDto.setDiscountAmount(null);
        couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDto);

        return ResponseEntity.ok().build();
    }
}
