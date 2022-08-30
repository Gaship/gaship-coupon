package shop.gaship.coupon.coupongenerationissue.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 주문화면에서 쿠폰 선택시 사용되는
 * 아직 사용하지 않은 회원의 쿠폰 조회 응답 dto 입니다.
 *
 * @author : 김세미
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnusedMemberCouponResponseDto {
    private Integer couponGenerationIssueNo;
    private String couponName;
    private Long discountAmount;
    private Integer discountRate;
    private LocalDateTime issueDatetime;
    private LocalDateTime expirationDatetime;
}
