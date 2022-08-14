package shop.gaship.coupon.coupongenerationissue.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.exception.CouponGenerationIssueNotFoundException;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;

/**
 * @author : 최겸준
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(CouponGenerationIssueServiceImpl.class)
class CouponGenerationIssueServiceImplTest {

    @Autowired
    private CouponGenerationIssueServiceImpl service;

    @MockBean
    private CouponGenerationIssueRepository couponGenerationIssueRepository;

    @MockBean
    private SchedulerAdapterAboutCouponCreation schedulerAdapterAboutCouponCreation;

    private CouponGenerationIssue couponGenerationIssue;

    private CouponType couponType;

    @BeforeEach
    void setUp() {
        couponType = new CouponType();
        ReflectionTestUtils.setField(couponType, "name", "couponTypeName");
        ReflectionTestUtils.setField(couponType, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponType, "discountRate", null);
        ReflectionTestUtils.setField(couponType, "isStopGenerationIssue", Boolean.FALSE);
        ReflectionTestUtils.setField(couponType, "name", "couponTypeName");


        couponGenerationIssue = new CouponGenerationIssue();
        ReflectionTestUtils.setField(couponGenerationIssue, "couponType", couponType);
        ReflectionTestUtils.setField(couponGenerationIssue, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssue, "generationDatetime", LocalDateTime.now().minusDays(1));
        ReflectionTestUtils.setField(couponGenerationIssue, "issueDatetime", LocalDateTime.now());
        ReflectionTestUtils.setField(couponGenerationIssue, "expirationDatetime", LocalDateTime.now().plusMonths(1));
        ReflectionTestUtils.setField(couponGenerationIssue, "usedDatetime", null);
    }

    @Test
    void addCouponGenerationIssue() {
    }

    @DisplayName("쿠폰생성발급의 상세조회 내용을 responseDto에 맞게 잘 맞추어 반환한다.")
    @Test
    void findCouponGenerationIssue() {

        given(couponGenerationIssueRepository.findCouponGenerationIssueByIdAsFetchJoin(1))
            .willReturn(Optional.ofNullable(couponGenerationIssue));

        CouponGenerationIssueDetailsResponseDto dto = service.findCouponGenerationIssue(1);
        assertThat(dto.getCouponName())
            .isEqualTo(couponType.getName());

        assertThat(dto.getDiscountAmount())
            .isEqualTo(couponType.getDiscountAmount());

        assertThat(dto.getDiscountRate())
            .isEqualTo(couponType.getDiscountRate());
        assertThat(dto.getMemberNo())
            .isEqualTo(couponGenerationIssue.getMemberNo());
        assertThat(dto.getIssueDatetime())
            .isEqualTo(couponGenerationIssue.getIssueDatetime());
    }

    @DisplayName("존재하지않는 쿠폰생성발급번호를 보낸경우 CouponGenerationIssueNotFoundException 이 발생한다.")
    @Test
    void findCouponGenerationIssue_fail_CouponGenerationIssueNotFoundException() {

        given(couponGenerationIssueRepository.findCouponGenerationIssueByIdAsFetchJoin(1))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> service.findCouponGenerationIssue(1)).isInstanceOf(
            CouponGenerationIssueNotFoundException.class)
            .hasMessageContaining(CouponGenerationIssueNotFoundException.MESSAGE);

    }
}