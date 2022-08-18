package shop.gaship.coupon.coupontype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * coupon_types 테이블과 1:1 대응되는 엔티티입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@Entity
@Table(name = "coupon_types")
@Getter
@Setter
@NoArgsConstructor
public class CouponType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_type_no")
    private Integer couponTypeNo;

    @NotNull
    private String name;

    @Column(name = "discount_amount")
    private Long discountAmount;

    @Column(name = "discount_rate")
    private Double discountRate;

    @Column(name = "is_stop_generation_issue", nullable = false)
    private Boolean isStopGenerationIssue;

    /**
     * 간편하게 CouponType을 만들기 위한 builder 클래스입니다.
     *
     * @param name                  이름을 의미합니다.
     * @param discountAmount        할인금액을 의미합니다.
     * @param discountRate          할인 비율을 의미합니다.
     * @param isStopGenerationIssue 앞으로 해당 종류로 쿠폰을 생성 및 발급 할 수 있을지 여부를 의미합니다.
     */
    @Builder
    public CouponType(String name, Long discountAmount, Double discountRate,
        Boolean isStopGenerationIssue) {
        this.name = name;
        this.discountAmount = discountAmount;
        this.discountRate = discountRate;
        this.isStopGenerationIssue = isStopGenerationIssue;
    }
}
