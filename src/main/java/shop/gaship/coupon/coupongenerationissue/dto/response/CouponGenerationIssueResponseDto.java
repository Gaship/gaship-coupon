package shop.gaship.coupon.coupongenerationissue.dto.response;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Integer couponGenerationIssueNo;

    @NotBlank(message = "쿠폰 이름은 필수값 입니다.")
    private String name;

    @NotNull(message = "회원 번호는 필수값 입니다.")
    private Integer memberNo;

    private LocalDateTime expirationDatetime;

    public CouponGenerationIssueResponseDto(String name, Integer memberNo, LocalDateTime expirationDatetime) {
        this.name = name;
        this.memberNo = memberNo;
        this.expirationDatetime = expirationDatetime;
    }
}
