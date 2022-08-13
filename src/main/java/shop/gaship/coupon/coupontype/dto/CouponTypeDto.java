package shop.gaship.coupon.coupontype.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Getter
public class CouponTypeDto {
    @NotBlank
    private String name;

    @Min(0)
    @Max(100)
    private Double discountRate;

    @Min(0)
    private Long discountAmount;

    @NotNull
    private Boolean isStopGenerationIssue;
}
