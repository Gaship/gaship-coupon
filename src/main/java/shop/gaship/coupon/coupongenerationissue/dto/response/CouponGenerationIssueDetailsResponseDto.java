package shop.gaship.coupon.coupongenerationissue.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 쿠폰 상세조회 요청시 쿠폰에 대한 상세 정보를 반환해줄 응답 dto입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public class CouponGenerationIssueDetailsResponseDto {

    private String couponName;
    private Long discountAmount;
    private Integer discountRate;
    private LocalDateTime generationDatetime;
    private LocalDateTime issueDatetime;
    private LocalDateTime expirationDatetime;
    private LocalDateTime usedDatetime;
    private Integer memberNo;
}
