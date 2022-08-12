package shop.gaship.coupon.coupontype.dto;

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
    @NotNull
    @Min(0)
    private Double discount;
    @NotNull
    private Boolean isFixedAmount;
}
