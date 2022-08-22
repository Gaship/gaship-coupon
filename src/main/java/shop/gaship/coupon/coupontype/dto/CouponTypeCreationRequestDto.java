package shop.gaship.coupon.coupontype.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 쿠폰타입을 추가할때 필요로하는 정보를 담고있는 DTO 클래스입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@Getter
public class CouponTypeCreationRequestDto {

    @NotBlank
    private String name;

    @Min(0)
    @Max(100)
    private Integer discountRate;

    @Min(0)
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
