package shop.gaship.coupon.coupongenerationissue.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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
import shop.gaship.coupon.coupongenerationissue.exception.CouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.exception.RecommendMemberCouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

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

    @MockBean
    private CouponTypeRepository couponTypeRepository;

    @MockBean
    private RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    private CouponGenerationIssue couponGenerationIssue;

    private CouponType couponType;

    @BeforeEach
    void setUp() {
        couponType = new CouponType();
        ReflectionTestUtils.setField(couponType, "couponTypeNo", 1);
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

    @DisplayName("비지니스 로직이 정상적으로 동작하며 정상적으로 save 메소드까지 동작한다.")
    @Test
    void addCouponGenerationIssueToRecommendMember() {
        RecommendMemberCouponType recommendMemberCouponType = new RecommendMemberCouponType();
        recommendMemberCouponType.setCouponType(couponType);
        recommendMemberCouponType.setRegisterDatetime(LocalDateTime.now());
        recommendMemberCouponType.setCouponTypeNo(1);

        given(recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc())
            .willReturn(Optional.ofNullable(recommendMemberCouponType));

        given(couponTypeRepository.findById(anyInt()))
            .willReturn(Optional.ofNullable(couponType));

        assertThatNoException().isThrownBy(() -> service.addCouponGenerationIssueToRecommendMember(1));
        verify(couponGenerationIssueRepository).save(any(CouponGenerationIssue.class));
    }

    @DisplayName("추천인쿠폰종류가 존재하지 않을때 추천인쿠폰생성발급 요청이 들어오면 RecommendMemberCouponTypeNotFoundException 이 발생한다.")
    @Test
    void addCouponGenerationIssueToRecommendMember_fail_RecommendMemberCouponTypeNotFoundException() {
        given(recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc())
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> service.addCouponGenerationIssueToRecommendMember(1))
            .isInstanceOf(RecommendMemberCouponTypeNotFoundException.class)
            .hasMessageContaining(RecommendMemberCouponTypeNotFoundException.MESSAGE);
    }

    @DisplayName("존재하지 않는 쿠폰타입을 요청하면 CouponTypeNotFoundException 이 발생한다.")
    @Test
    void addCouponGenerationIssueToRecommendMember_fail_CouponTypeNotFoundException() {
        RecommendMemberCouponType recommendMemberCouponType = new RecommendMemberCouponType();
        recommendMemberCouponType.setCouponType(couponType);
        recommendMemberCouponType.setRegisterDatetime(LocalDateTime.now());
        recommendMemberCouponType.setCouponTypeNo(1);

        given(recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc())
            .willReturn(Optional.ofNullable(recommendMemberCouponType));

        given(couponTypeRepository.findById(anyInt()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> service.addCouponGenerationIssueToRecommendMember(1))
            .isInstanceOf(CouponTypeNotFoundException.class)
            .hasMessageContaining(CouponTypeNotFoundException.MESSAGE);
    }
}