package shop.gaship.coupon.coupontype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
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

}
