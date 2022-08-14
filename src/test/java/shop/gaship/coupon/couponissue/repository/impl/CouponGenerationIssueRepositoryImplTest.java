package shop.gaship.coupon.couponissue.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;

/**
 * CouponGenerationIssue 의 커스텀 repository 테스트 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CouponGenerationIssueRepositoryImplTest {

    @Autowired
    CouponGenerationIssueRepository couponGenerationIssueRepository;

    @Autowired
    CouponTypeRepository couponTypeRepository;

    @Test
    void existCouponHasCouponTypeNo() {

        // given
        CouponType couponType = new CouponType();

        ReflectionTestUtils.setField(couponType, "name", "최겸준");
        ReflectionTestUtils.setField(couponType, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponType, "discountRate", 10.0);

        CouponType savedCouponType = couponTypeRepository.save(couponType);

        CouponGenerationIssue couponGenerationIssue = new CouponGenerationIssue();

        ReflectionTestUtils.setField(couponGenerationIssue, "couponType", savedCouponType);
        ReflectionTestUtils.setField(couponGenerationIssue, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssue, "generationDatetime", LocalDateTime.now());
        ReflectionTestUtils.setField(couponGenerationIssue, "expirationDatetime", LocalDateTime.now().plusDays(1));

        couponGenerationIssueRepository.save(couponGenerationIssue);

        // when
        Boolean isExistCouponGenerationIssue =
            couponGenerationIssueRepository.existCouponHasCouponTypeNo(savedCouponType.getCouponTypeNo());

        // then
        assertThat(isExistCouponGenerationIssue).isTrue();
    }
}