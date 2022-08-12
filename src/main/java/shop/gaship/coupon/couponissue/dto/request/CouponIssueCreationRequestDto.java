package shop.gaship.coupon.couponissue.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Getter
public class CouponIssueCreationRequestDto {
    @NotNull
    @Min(1)
    private Integer couponTypeNo;
    @NotNull
    @Min(1)
    private Integer memberNo;

    @NotNull
    @Min(1)
    private Integer expirationMonth;
}
