package shop.gaship.coupon.couponissue.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.couponissue.entity.QCouponGenerationIssue;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepositoryCustom;

/**
 * couponGenerationIssue 의 repository(crud 를 위한) 의 custom 구현체 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public class CouponGenerationIssueRepositoryImpl
    extends QuerydslRepositorySupport implements CouponGenerationIssueRepositoryCustom {

    public CouponGenerationIssueRepositoryImpl() {
        super(CouponGenerationIssue.class);
    }

    /**
     * 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰이 존재하는지 체크하는 메서드 입니다.
     *
     * @param couponTypeNo 쿠폰 타입 번호 입니다.
     * @return 해당 쿠폰 타입 번호를 가진 생성 or 발급 된 쿠폰의 존재 여부 반환.
     */
    @Override
    public Boolean existCouponHasCouponTypeNo(Integer couponTypeNo) {
        QCouponGenerationIssue couponGenerationIssue = QCouponGenerationIssue.couponGenerationIssue;

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.getEntityManager());

        Integer fetchOne = jpaQueryFactory
            .selectOne()
            .from(couponGenerationIssue)
            .where(couponGenerationIssue.couponType.couponTypeNo.eq(couponTypeNo))
            .fetchFirst();

        return fetchOne != null;
    }
}
