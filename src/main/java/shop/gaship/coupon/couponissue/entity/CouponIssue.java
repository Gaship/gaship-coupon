package shop.gaship.coupon.couponissue.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.gaship.coupon.coupontype.entity.CouponTypeDto;

/**
 * @author : 최겸준
 * @since 1.0
 */
@Entity
@Table(name = "coupon_issues")
@Getter
@NoArgsConstructor
public class CouponIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_issue_no")
    private Integer couponIssueNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_type_no", nullable = false)
    private CouponTypeDto couponType;

    @Column(name = "member_no", nullable = false)
    private Integer memberNo;

    @Column(name = "issue_datetime", nullable = false)
    private LocalDateTime issueDatetime;

    @Column(name = "expiration_datetime", nullable = false)
    private LocalDateTime expirationDatetime;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed;

    @Builder
    public CouponIssue(Integer memberNo, LocalDateTime issueDatetime,
                       LocalDateTime expirationDatetime, Boolean isUsed) {
        this.memberNo = memberNo;
        this.issueDatetime = issueDatetime;
        this.expirationDatetime = expirationDatetime;
        this.isUsed = isUsed;
    }

}
