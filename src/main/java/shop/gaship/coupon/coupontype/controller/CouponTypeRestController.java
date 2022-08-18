package shop.gaship.coupon.coupontype.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * 쿠폰종류에 대한 요청을 처리하는 컨트롤러 클래스입니다.
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
     * 정액쿠폰종류 추가요청을 처리하는 기능입니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     * @return body에 void값을 넣고 201값을 반환합니다.
     * @author 최겸준
     */
    @PostMapping("/fixed-amount")
    public ResponseEntity<Void> fixedAmountCouponTypeAdd(
        @RequestBody @Valid CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        couponTypeCreationRequestDto.setDiscountRate(null);
        couponTypeService.addCouponType(couponTypeCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 정률쿠폰종류 추가요청을 처리하는 기능입니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     * @return body에 void값을 넣고 201값을 반환합니다.
     * @author 최겸준
     */
    @PostMapping("/fixed-rate")
    public ResponseEntity<Void> fixedRateCouponTypeAdd(
        @RequestBody @Valid CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        couponTypeCreationRequestDto.setDiscountAmount(null);
        couponTypeService.addCouponType(couponTypeCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 추천인쿠폰에 대한 변경요청을 처리하는 기능입니다. 실제로는 변경이 아니라 새로 로우를 만들어서 대체하는 기능입니다.
     *
     * @param couponTypeCreationRequestDto 추가시 필요한 정보를 담고있는 DTO 객체입니다.
     * @return body에 void값을 넣고 200값을 반환합니다.
     * @author 최겸준
     */
    @PutMapping("/recommend-member-coupon")
    public ResponseEntity<Void> recommendMemberCouponTypeModify(
        @RequestBody @Valid CouponTypeCreationRequestDto couponTypeCreationRequestDto) {
        couponTypeCreationRequestDto.setDiscountAmount(null);
        couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDto);

        return ResponseEntity.ok().build();
    }
}
