package shop.gaship.coupon.coupongenerationissue.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * @author : 최겸준
 * @since 1.0
 */
@DataJpaTest
class CouponGenerationIssueRepositoryTest {
    @Autowired
    private CouponGenerationIssueRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    private CouponType couponType;
    private CouponGenerationIssue couponGenerationIssue;

    @BeforeEach
    void setUp() {
        couponType = new CouponType();
        ReflectionTestUtils.setField(couponType, "name", "couponTypeName");
        ReflectionTestUtils.setField(couponType, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponType, "discountRate", null);
        ReflectionTestUtils.setField(couponType, "isStopGenerationIssue", Boolean.FALSE);
        ReflectionTestUtils.setField(couponType, "name", "couponTypeName");
        testEntityManager.persist(couponType);

        couponGenerationIssue = new CouponGenerationIssue();
        ReflectionTestUtils.setField(couponGenerationIssue, "couponType", couponType);
        ReflectionTestUtils.setField(couponGenerationIssue, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssue, "generationDatetime", LocalDateTime.now().minusDays(1));
        ReflectionTestUtils.setField(couponGenerationIssue, "issueDatetime", LocalDateTime.now());
        ReflectionTestUtils.setField(couponGenerationIssue, "expirationDatetime", LocalDateTime.now().plusMonths(1));
        ReflectionTestUtils.setField(couponGenerationIssue, "usedDatetime", null);

        testEntityManager.persist(couponGenerationIssue);
        testEntityManager.clear();
    }

    @DisplayName("named query가 정상동작한다.")
    @Test
    void findByIdWithCouponType() {


        CouponGenerationIssue result
            = repository.findCouponGenerationIssueByIdAsFetchJoin(
                couponGenerationIssue.getCouponGenerationIssueNo()).get();
        CouponType type = result.getCouponType();

        assertThat(result.getCouponGenerationIssueNo())
            .isEqualTo(couponGenerationIssue.getCouponGenerationIssueNo());
        assertThat(result.getMemberNo())
            .isEqualTo(couponGenerationIssue.getMemberNo());
        assertThat(type.getCouponTypeNo()).isEqualTo(couponType.getCouponTypeNo());
    }
}