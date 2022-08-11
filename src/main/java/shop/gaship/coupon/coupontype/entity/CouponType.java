package shop.gaship.coupon.coupontype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * coupon_types 테이블과 1:1 대응되는 엔티티입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
@Entity
@Table(name = "coupon_types")
public class CouponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupone_type_no")
    private Integer couponTypeNo;

    private String name;

    private Double discount;

    @Column(name = "is_fixed_amount")
    private Boolean isFixedAmount;
}
