package shop.gaship.coupon.coupongenerationissue.controller;

import java.sql.SQLException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;

/**
 * 쿠폰생성발급에 대한 요청을 처리하기위한 controller class입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@RestController
@RequestMapping("/api/coupon-generations-issues")
@RequiredArgsConstructor
public class CouponGenerationIssueRestController {
    private final CouponGenerationIssueService couponGenerationIssueService;

    /**
     * 쿠폰을 생성 및 발급하기위한 요청을 처리하는 기능입니다.
     *
     * @param couponGenerationIssueCreationRequestDto 쿠폰생성 및 발급을 위해 client에서 넘겨주는 정보가 들어있는 클래스입니다.
     * @return 성공적으로 요청이 이루어질시에 ResponseEntity의 body에 void를 넣고 상태코드 201을 반환합니다.
     * @author 최겸준
     */
    @PostMapping
    public ResponseEntity<Void> couponGenerationIssueAdd(@RequestBody @Valid CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {
        couponGenerationIssueService.addCouponGenerationIssue(couponGenerationIssueCreationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{recommendMemberNo}")
    public ResponseEntity<Void> couponGenerationIssueAddToRecommendMember(@PathVariable Integer recommendMemberNo) {
        couponGenerationIssueService.addCouponGenerationIssueToRecommendMember(recommendMemberNo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 쿠폰의 상세조회 요청을 할때 처리할 메서드입니다.
     *
     * @param couponGenerationIssueNo 조회시 기준이 되는 쿠폰생성발급 테이블의 로우번호입니다.
     * @return body에 CouponGenerationIssueDetailsResponseDto를 담아서 200 상태코드와 함께 반환합니다.
     */
    @GetMapping("/{couponGenerationIssueNo}")
    public ResponseEntity<CouponGenerationIssueDetailsResponseDto> couponGenerationIssueDetails(@PathVariable Integer couponGenerationIssueNo) {
        return ResponseEntity.ok(couponGenerationIssueService.findCouponGenerationIssue(couponGenerationIssueNo));
    }

}
