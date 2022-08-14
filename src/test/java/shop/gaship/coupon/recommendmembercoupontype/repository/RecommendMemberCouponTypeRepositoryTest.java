package shop.gaship.coupon.recommendmembercoupontype.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;

/**
 * @author : 최겸준
 * @since 1.0
 */
@DataJpaTest
class RecommendMemberCouponTypeRepositoryTest {

    @Autowired
    private RecommendMemberCouponTypeRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    private CouponType couponType1;

    @BeforeEach
    void setUp() {
        couponType1 = new CouponType();
        ReflectionTestUtils.setField(couponType1, "name", "couponTypeName");
        ReflectionTestUtils.setField(couponType1, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponType1, "discountRate", null);
        ReflectionTestUtils.setField(couponType1, "isStopGenerationIssue", Boolean.FALSE);
        ReflectionTestUtils.setField(couponType1, "name", "couponTypeName");
        testEntityManager.persist(couponType1);
    }

    @DisplayName("하나의 값이 들어갔을때 1번의 couponTypeNo를 잘 얻어온다.")
    @Test
    void findFirstByOrderByIdDesc() {
        RecommendMemberCouponType recommendMemberCouponType = new RecommendMemberCouponType();
        recommendMemberCouponType.setCouponType(couponType1);
        recommendMemberCouponType.setRegisterDatetime(LocalDateTime.now());

        testEntityManager.persist(recommendMemberCouponType);
        testEntityManager.flush();
        testEntityManager.clear();

        RecommendMemberCouponType result = repository.findFirstByOrderByCouponTypeNoDesc().get();
        assertThat(result.getCouponTypeNo())
            .isEqualTo(recommendMemberCouponType.getCouponTypeNo());
    }

    @DisplayName("두개의 값이 들어갔을때 2번의 couponTypeNo를 잘 얻어온다.")
    @Test
    void findFirstByOrderByIdDesc_two() {
        RecommendMemberCouponType recommendMemberCouponType1 = new RecommendMemberCouponType();
        recommendMemberCouponType1.setCouponType(couponType1);
        recommendMemberCouponType1.setRegisterDatetime(LocalDateTime.now());

        CouponType couponType2 = new CouponType();
        ReflectionTestUtils.setField(couponType2, "name", "couponTypeName");
        ReflectionTestUtils.setField(couponType2, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponType2, "discountRate", null);
        ReflectionTestUtils.setField(couponType2, "isStopGenerationIssue", Boolean.FALSE);
        ReflectionTestUtils.setField(couponType2, "name", "couponTypeName");
        testEntityManager.persist(couponType2);

        RecommendMemberCouponType recommendMemberCouponType2 = new RecommendMemberCouponType();
        recommendMemberCouponType2.setCouponType(couponType2);
        recommendMemberCouponType2.setRegisterDatetime(LocalDateTime.now());

        testEntityManager.persist(recommendMemberCouponType1);
        testEntityManager.persist(recommendMemberCouponType2);
        testEntityManager.flush();
        testEntityManager.clear();

        RecommendMemberCouponType result = repository.findFirstByOrderByCouponTypeNoDesc().get();
        assertThat(result.getCouponTypeNo())
            .isEqualTo(recommendMemberCouponType2.getCouponTypeNo());
    }


    @DisplayName("두개의 값이(1,3) 들어갔을때 마지막값으로 준 3번의 couponTypeNo를 잘 얻어온다.")
    @Test
    void findFirstByOrderByIdDesc_three() {
        RecommendMemberCouponType recommendMemberCouponType1 = new RecommendMemberCouponType();
        recommendMemberCouponType1.setCouponType(couponType1);
        recommendMemberCouponType1.setRegisterDatetime(LocalDateTime.now());

        CouponType couponType2 = new CouponType();
        ReflectionTestUtils.setField(couponType2, "name", "couponTypeName");
        ReflectionTestUtils.setField(couponType2, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponType2, "discountRate", null);
        ReflectionTestUtils.setField(couponType2, "isStopGenerationIssue", Boolean.FALSE);
        ReflectionTestUtils.setField(couponType2, "name", "couponTypeName");
        testEntityManager.persist(couponType2);

        CouponType couponType3 = new CouponType();
        ReflectionTestUtils.setField(couponType3, "name", "couponTypeName");
        ReflectionTestUtils.setField(couponType3, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponType3, "discountRate", null);
        ReflectionTestUtils.setField(couponType3, "isStopGenerationIssue", Boolean.FALSE);
        ReflectionTestUtils.setField(couponType3, "name", "couponTypeName");
        testEntityManager.persist(couponType3);

        RecommendMemberCouponType recommendMemberCouponType2 = new RecommendMemberCouponType();
        recommendMemberCouponType2.setCouponType(couponType3);
        recommendMemberCouponType2.setRegisterDatetime(LocalDateTime.now());

        testEntityManager.persist(recommendMemberCouponType1);
        testEntityManager.persist(recommendMemberCouponType2);
        testEntityManager.flush();
        testEntityManager.clear();

        RecommendMemberCouponType result = repository.findFirstByOrderByCouponTypeNoDesc().get();
        assertThat(result.getCouponTypeNo())
            .isEqualTo(recommendMemberCouponType2.getCouponTypeNo());
    }
}