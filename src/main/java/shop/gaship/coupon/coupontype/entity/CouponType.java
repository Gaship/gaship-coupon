package shop.gaship.coupon.coupontype.entity;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;

/**
 * coupon_types 테이블과 1:1 대응되는 엔티티입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
@Getter
@Entity
@Table(name = "coupon_types")
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

    public void changeStopGenerationIssue(Boolean stopGenerationIssue) {
        isStopGenerationIssue = stopGenerationIssue;
    }
}
