package shop.gaship.coupon.coupontype.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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
import shop.gaship.coupon.coupontype.exception.CouponTypeNotFoundException;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

/**
 * @author : 최겸준
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(CouponTypeServiceImpl.class)
class CouponTypeServiceImplTest {

    @Autowired
    private CouponTypeServiceImpl couponTypeService;

    @MockBean
    private CouponTypeRepository couponTypeRepository;

    @MockBean
    private RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoFixedAmount;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoFixedRate;

    private CouponType couponTypeFixedAmount;

    private CouponType couponTypeFixedRate;

    @BeforeEach
    void setUp() {
        couponTypeCreationRequestDtoFixedAmount = new CouponTypeCreationRequestDto();
        couponTypeCreationRequestDtoFixedAmount.setDiscountAmount(1000L);
        ReflectionTestUtils.setField(couponTypeCreationRequestDtoFixedAmount, "name", "1000원 할인쿠폰");

        couponTypeCreationRequestDtoFixedRate = new CouponTypeCreationRequestDto();
        couponTypeCreationRequestDtoFixedRate.setDiscountRate(0.9);
        ReflectionTestUtils.setField(couponTypeCreationRequestDtoFixedRate, "name", "10% 할인쿠폰");

        couponTypeFixedAmount
            = CouponType.builder()
            .discountAmount(couponTypeCreationRequestDtoFixedAmount.getDiscountAmount())
            .name(couponTypeCreationRequestDtoFixedAmount.getName())
            .isStopGenerationIssue(Boolean.FALSE)
            .discountRate(null)
            .build();

        couponTypeFixedRate
            = CouponType.builder()
            .discountRate(couponTypeCreationRequestDtoFixedRate.getDiscountRate())
            .name(couponTypeCreationRequestDtoFixedRate.getName())
            .isStopGenerationIssue(Boolean.FALSE)
            .discountAmount(null)
            .build();
    }

    @DisplayName("쿠폰타입을 추가하는 요청에 대한 비지니스로직이 예외없이 잘 수행된다.")
    @Test
    void addCouponType() {
        couponTypeService.addCouponType(couponTypeCreationRequestDtoFixedAmount);
        verify(couponTypeRepository).save(any(CouponType.class));
    }

    @DisplayName("추천회원쿠폰 수정 요청에 대한 비지니스 로직 처리가 잘 이루어진다.")
    @Test
    void modifyRecommendMemberCoupon() {

        RecommendMemberCouponType recommendMemberCouponType = mock(RecommendMemberCouponType.class);
        when(recommendMemberCouponType.getCouponTypeNo())
            .thenReturn(1);

        given(recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc())
            .willReturn(Optional.ofNullable(recommendMemberCouponType));

        given(couponTypeRepository.findById(anyInt()))
            .willReturn(Optional.ofNullable(couponTypeFixedRate));

        couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDtoFixedRate);

        verify(couponTypeRepository).save(any(CouponType.class));
        verify(recommendMemberCouponTypeRepository).save(any(RecommendMemberCouponType.class));
        verify(couponTypeRepository).findById(1);

        assertThat(couponTypeFixedRate.getIsStopGenerationIssue())
            .isTrue();
    }

    @DisplayName("존재하지 않는 추천인이 올시에 changePrevRecommendMemberCouponTypeAsStop 메서드가 작동하지 않는다.")
    @Test
    void modifyRecommendMemberCoupon_never_changePrevRecommendMemberCouponTypeAsStop() {
        given(recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc())
            .willReturn(Optional.empty());
        assertThatNoException().isThrownBy(() -> couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDtoFixedRate));

        verify(couponTypeRepository, never()).findById(anyInt());
    }

    @DisplayName("존재하지 않는 쿠폰종류번호가 나왔을시에 CouponTypeNotFoundException 이 발생한다.")
    @Test
    void modifyRecommendMemberCoupon_fail_CouponTypeNotFoundException() {

        RecommendMemberCouponType recommendMemberCouponType = mock(RecommendMemberCouponType.class);
        when(recommendMemberCouponType.getCouponTypeNo())
            .thenReturn(1);

        given(recommendMemberCouponTypeRepository.findFirstByOrderByCouponTypeNoDesc())
            .willReturn(Optional.ofNullable(recommendMemberCouponType));

        given(couponTypeRepository.findById(anyInt()))
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDtoFixedRate))
            .isInstanceOf(CouponTypeNotFoundException.class)
            .hasMessageContaining(CouponTypeNotFoundException.MESSAGE);
    }
}