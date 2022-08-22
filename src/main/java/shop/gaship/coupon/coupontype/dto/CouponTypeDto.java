package shop.gaship.coupon.coupontype.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CouponTypeDto {

    @NotNull
    private Integer couponTypeNo;

    @NotBlank
    private String name;

    @Min(0)
    @Max(100)
    private Integer discountRate;

    @Min(0)
    private Long discountAmount;

    @NotNull
    private Boolean isStopGenerationIssue;
}
