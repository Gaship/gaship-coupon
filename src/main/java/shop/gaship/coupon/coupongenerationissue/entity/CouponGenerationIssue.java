package shop.gaship.coupon.coupongenerationissue.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * coupon_generations_issues 테이블과 1:1 대응되는 엔티티입니다.
 *
 * @author 최겸준, 조재철
 * @since 1.0
 */
@NamedQuery(name = "CouponGenerationIssue.findByIdAsFetchJoin",
    query = "select c from CouponGenerationIssue c join fetch c.couponType where c.couponGenerationIssueNo = :couponGenerationIssueNo")
@Entity
@Table(name = "coupon_generations_issues")
@Getter
@NoArgsConstructor
public class CouponGenerationIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_generation_issue_no")
    private Integer couponGenerationIssueNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_type_no", nullable = false)
    private CouponType couponType;

    @Column(name = "member_no", nullable = false)
    private Integer memberNo;

    @Column(name = "generation_datetime", nullable = false)
    private LocalDateTime generationDatetime;

    @Column(name = "issue_datetime")
    private LocalDateTime issueDatetime;

    @Column(name = "expiration_datetime", nullable = false)
    private LocalDateTime expirationDatetime;

    @Column(name = "used_datetime")
    private LocalDateTime usedDatetime;

    public void useCoupon() {
        this.usedDatetime = LocalDateTime.now();
    }

    public void cancelUsedCoupon() {
        this.usedDatetime = null;
    }

    @Builder
    public CouponGenerationIssue(CouponType couponType, Integer memberNo,
        LocalDateTime generationDatetime,
        LocalDateTime issueDatetime,
        LocalDateTime expirationDatetime) {
        this.couponType = couponType;
        this.memberNo = memberNo;
        this.generationDatetime = generationDatetime;
        this.issueDatetime = issueDatetime;
        this.expirationDatetime = expirationDatetime;
    }
}
