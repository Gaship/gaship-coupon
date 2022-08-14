package shop.gaship.coupon.coupontype.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.service.CouponTypeService;
import shop.gaship.coupon.dto.response.PageResponse;

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

    /**
     * 해당 쿠폰타입의 stop_generation_issue 의 수정 요청을 받는 메서드 입니다.
     *
     * @param couponTypeNo 수정해야할 쿠폰종류 번호 입니다.
     * @return 해당 요청을 성공적으로 잘 처리했다는 응답을 보냅니다.
     */
    @PatchMapping(value = "{couponTypeNo}/stop-generation-issue")
    public ResponseEntity<Void> couponTypeModify(@PathVariable(value = "couponTypeNo") Integer couponTypeNo) {
        couponTypeService.modifyCouponTypeStopGenerationIssue(couponTypeNo);

        return ResponseEntity.ok().build();
    }

    /**
     * 해당 쿠폰타입에 대한 삭제 요청을 받는 메서드 입니다.
     *
     * @param couponTypeNo 삭제하고자 하는 쿠폰 타입의 쿠폰 타입 번호 입니다.
     * @return 해당 요청을 성공적으로 잘 처리했다는 응답을 보냅니다.
     */
    @DeleteMapping(value = "{couponTypeNo}")
    public ResponseEntity<Void> couponTypeDelete(@PathVariable(value = "couponTypeNo") Integer couponTypeNo) {
        couponTypeService.deleteCouponType(couponTypeNo);

        return ResponseEntity.ok().build();
    }

    /**
     * 쿠폰 타입에 대한 전체 조회의 요청을 받는 클래스 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 요청에 대한 응답으로 요청을 성공적으로 잘 처리했다는 응답과, couponType 의 PageResponse 타입으로 보냅니다.
     */
    @GetMapping
    public ResponseEntity<PageResponse<CouponTypeDto>> couponTypeList(Pageable pageable) {
        Page<CouponTypeDto> couponTypesPage =
            couponTypeService.findCouponTypes(pageable);

        PageResponse<CouponTypeDto> couponTypesResponsePage = new PageResponse<>(couponTypesPage);

        return ResponseEntity.ok(couponTypesResponsePage);
    }

    /**
     * 해당 쿠폰 타입 중 아직 생성, 발급이 안된 쿠폰 타입 조회 요청을 받는 클래스 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 요청에 대한 응답으로 요청을 성공적으로 잘 처리했다는 응답과, couponType 의 PageResponse 타입으로 보냅니다.
     */
    @GetMapping(value = "/delete-can")
    public ResponseEntity<PageResponse<CouponTypeDto>> couponTypeListCanDelete(Pageable pageable) {
        Page<CouponTypeDto> couponTypesCanDeletePage =
            couponTypeService.findCouponTypesCanDelete(pageable);

        PageResponse<CouponTypeDto> couponTypesResponsePage = new PageResponse<>(couponTypesCanDeletePage);

        return ResponseEntity.ok(couponTypesResponsePage);
    }

    /**
     * 해당 쿠폰 타입 중 이미 생성, 발급이 된 쿠폰 타입 조회 요청을 받는 클래스 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 요청에 대한 응답으로 요청을 성공적으로 잘 처리했다는 응답과, couponType 의 PageResponse 타입으로 보냅니다.
     */
    @GetMapping(value = "/delete-cannot")
    public ResponseEntity<PageResponse<CouponTypeDto>> couponTypeListCannotDelete(Pageable pageable) {
        Page<CouponTypeDto> couponTypesCannotDeletePage =
            couponTypeService.findCouponTypesCannotDelete(pageable);

        PageResponse<CouponTypeDto> couponTypesResponsePage = new PageResponse<>(couponTypesCannotDeletePage);

        return ResponseEntity.ok(couponTypesResponsePage);
    }
}
