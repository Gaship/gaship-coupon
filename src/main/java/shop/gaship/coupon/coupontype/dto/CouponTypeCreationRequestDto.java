package shop.gaship.coupon.coupontype.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import shop.gaship.coupon.coupontype.dto.group.FixAmountGroup;
import shop.gaship.coupon.coupontype.dto.group.FixRateGroup;

/**
 * 쿠폰타입을 추가할때 필요로하는 정보를 담고있는 DTO 클래스입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@Getter
public class CouponTypeCreationRequestDto {

    @NotBlank(message = "이름은 필수 값 입니다.")
    private String name;

    @Min(value = 0, message = "할인 비율은 0 ~ 100% 입니다.", groups = FixRateGroup.class)
    @Max(value = 100, message = "할인 비율은 0 ~ 100% 입니다.", groups = FixRateGroup.class)
    private Integer discountRate;

    @Min(value = 0, message = "할인금액은 0원 이상입니다.", groups = FixAmountGroup.class)
    private Long discountAmount;

    /**
     * discountRate값의 setter입니다.
     *
     * @param discountRate 정액처리시 null값이 들어옵니다.
     */
    public void setDiscountRate(Integer discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * discountAmount값의 setter입니다.
     *
     * @param discountAmount 정률처리시 null값이 들어옵니다.
     */
    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

}
