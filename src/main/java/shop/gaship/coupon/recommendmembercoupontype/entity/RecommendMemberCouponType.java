package shop.gaship.coupon.recommendmembercoupontype.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * recommend_member_coupon_type과 1:1 매칭되는 entity 입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
@NamedEntityGraph(name = "RecommendMemberCouponType.withCouponType",
    attributeNodes = {
        @NamedAttributeNode("couponType")
    })
@Entity
@Table(name = "recommend_member_coupon_type")
@Setter
@Getter
public class RecommendMemberCouponType {

    @Id
    private Integer couponTypeNo;

    @Column(name = "register_datetime")
    private LocalDateTime registerDatetime;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_type_no")
    private CouponType couponType;
}
