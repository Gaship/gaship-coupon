package shop.gaship.coupon.coupongenerationissue.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * CouponGenerationIssue 전체 목록 조회시 반환되는 dto 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponGenerationIssueResponseDto {

    private String name;

    private Integer memberNo;

    private LocalDateTime expirationDatetime;
}
