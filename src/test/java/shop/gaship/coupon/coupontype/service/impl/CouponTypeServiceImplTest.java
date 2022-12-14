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

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.exception.DeleteCouponTypeException;
import shop.gaship.coupon.coupontype.exception.NotFoundCouponTypeException;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.coupontype.service.CouponTypeService;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

/**
 * CouponType service ????????? ????????? ?????????.
 *
 * @author ?????????, ?????????
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(CouponTypeServiceImpl.class)
class CouponTypeServiceImplTest {

    @Autowired
    private CouponTypeService couponTypeService;

    @MockBean
    private CouponTypeRepository couponTypeRepository;

    @MockBean
    private CouponGenerationIssueRepository couponGenerationIssueRepository;

    @MockBean
    private RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoFixedAmount;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoFixedRate;

    private CouponType couponTypeFixedRate;

    Integer fixRateCouponTypeNo;
    Integer fixAmountCouponTypeNo;
    CouponType fixRateCouponType;
    CouponType fixAmountCouponType;

    @BeforeEach
    void setUp() {
        fixRateCouponTypeNo = 1;
        fixAmountCouponTypeNo = 2;

        fixRateCouponType = new CouponType();
        fixAmountCouponType = new CouponType();

        ReflectionTestUtils.setField(fixRateCouponType, "couponTypeNo", fixRateCouponTypeNo);
        ReflectionTestUtils.setField(fixRateCouponType, "name", "?????????");
        ReflectionTestUtils.setField(fixRateCouponType, "discountRate", 10);
        ReflectionTestUtils.setField(fixRateCouponType, "isStopGenerationIssue", false);

        ReflectionTestUtils.setField(fixAmountCouponType, "couponTypeNo", fixAmountCouponTypeNo);
        ReflectionTestUtils.setField(fixAmountCouponType, "name", "?????????");
        ReflectionTestUtils.setField(fixAmountCouponType, "discountAmount", 10000L);
        ReflectionTestUtils.setField(fixAmountCouponType, "isStopGenerationIssue", false);

        couponTypeCreationRequestDtoFixedAmount = new CouponTypeCreationRequestDto();
        couponTypeCreationRequestDtoFixedAmount.setDiscountAmount(1000L);
        ReflectionTestUtils.setField(couponTypeCreationRequestDtoFixedAmount, "name", "1000??? ????????????");

        couponTypeCreationRequestDtoFixedRate = new CouponTypeCreationRequestDto();
        couponTypeCreationRequestDtoFixedRate.setDiscountRate(90);
        ReflectionTestUtils.setField(couponTypeCreationRequestDtoFixedRate, "name", "10% ????????????");

        CouponType couponTypeFixedAmount = CouponType.builder()
                                                     .discountAmount(
                                                         couponTypeCreationRequestDtoFixedAmount.getDiscountAmount())
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

    @Test
    void modifyCouponTypeStopGenerationIssue() {

        // given
        when(couponTypeRepository.findById(fixAmountCouponTypeNo)).thenReturn(Optional.ofNullable(fixAmountCouponType));

        // when
        couponTypeService.modifyCouponTypeStopGenerationIssue(fixAmountCouponTypeNo);

        // then
        verify(couponTypeRepository).findById(fixAmountCouponTypeNo);

    }

    @Test
    void modifyCouponTypeStopGenerationIssueNotFoundCouponType() {

        // given
        when(couponTypeRepository.findById(fixAmountCouponTypeNo)).thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(
            () -> couponTypeService.modifyCouponTypeStopGenerationIssue(fixAmountCouponTypeNo))
            .isInstanceOf(NotFoundCouponTypeException.class)
            .hasMessageContaining("?????? ?????? ????????? ???????????? ????????????.");

    }

    @Test
    void deleteCouponType() {
        // given
        when(couponTypeRepository.findById(fixAmountCouponTypeNo)).thenReturn(Optional.ofNullable(fixAmountCouponType));
        when(couponGenerationIssueRepository.existCouponHasCouponTypeNo(fixAmountCouponTypeNo)).thenReturn(
            Boolean.FALSE);

        // when
        couponTypeService.deleteCouponType(fixAmountCouponTypeNo);

        // then
        verify(couponGenerationIssueRepository).existCouponHasCouponTypeNo(fixAmountCouponTypeNo);

    }

    @Test
    void deleteCouponTypeDeleteCouponTypeExceptionTest() {
        // given
        when(couponTypeRepository.findById(fixAmountCouponTypeNo)).thenReturn(Optional.ofNullable(fixAmountCouponType));
        when(couponGenerationIssueRepository.existCouponHasCouponTypeNo(fixAmountCouponTypeNo)).thenReturn(
            Boolean.TRUE);

        // when, then
        assertThatThrownBy(() -> couponTypeService.deleteCouponType(fixAmountCouponTypeNo)).isInstanceOf(
            DeleteCouponTypeException.class).hasMessageContaining(
            "?????? ?????? ????????? ?????? ????????? ?????? ??????, ?????? ????????? ????????? ?????? ????????? ?????????.");

    }

    @Test
    void findCouponTypes() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));
        Page<CouponTypeDto> page = new PageImpl<>(Collections.EMPTY_LIST, pageRequest, 10);

        when(couponTypeRepository.findAllCouponTypes(any())).thenReturn(page);

        // when
        Page<CouponTypeDto> couponTypes = couponTypeService.findCouponTypes(any());

        // then
        assertThat(couponTypes).isEqualTo(page);
        verify(couponTypeRepository).findAllCouponTypes(any());

    }

    @Test
    void findCouponTypesCanDelete() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));
        Page<CouponTypeDto> page = new PageImpl<>(Collections.EMPTY_LIST, pageRequest, 10);

        when(couponTypeRepository.findAllCouponTypesCanDelete(any())).thenReturn(page);

        // when
        Page<CouponTypeDto> couponTypes = couponTypeService.findCouponTypesCanDelete(any());

        // then
        assertThat(couponTypes).isEqualTo(page);
        verify(couponTypeRepository).findAllCouponTypesCanDelete(any());
    }

    @Test
    void findCouponTypesCannotDelete() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));
        Page<CouponTypeDto> page = new PageImpl<>(Collections.EMPTY_LIST, pageRequest, 10);

        when(couponTypeRepository.findAllCouponTypesCannotDelete(any())).thenReturn(page);

        // when
        Page<CouponTypeDto> couponTypes = couponTypeService.findCouponTypesCannotDelete(any());

        // then
        assertThat(couponTypes).isEqualTo(page);
        verify(couponTypeRepository).findAllCouponTypesCannotDelete(any());
    }

    @Test
    void findCouponTypesFixedAmount() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));
        Page<CouponTypeDto> page = new PageImpl<>(Collections.EMPTY_LIST, pageRequest, 10);

        when(couponTypeRepository.findAllCouponTypesFixedAmount(any())).thenReturn(page);

        // when
        Page<CouponTypeDto> couponTypes = couponTypeService.findCouponTypesFixedAmount(any());

        // then
        assertThat(couponTypes).isEqualTo(page);
        verify(couponTypeRepository).findAllCouponTypesFixedAmount(any());
    }

    @Test
    void findCouponTypesFixedRate() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));
        Page<CouponTypeDto> page = new PageImpl<>(Collections.EMPTY_LIST, pageRequest, 10);

        when(couponTypeRepository.findAllCouponTypesFixedRate(any())).thenReturn(page);

        // when
        Page<CouponTypeDto> couponTypes = couponTypeService.findCouponTypesFixedRate(any());

        // then
        assertThat(couponTypes).isEqualTo(page);
        verify(couponTypeRepository).findAllCouponTypesFixedRate(any());
    }

    @DisplayName("??????????????? ???????????? ????????? ?????? ????????????????????? ???????????? ??? ????????????.")
    @Test
    void addCouponType() {
        couponTypeService.addCouponType(couponTypeCreationRequestDtoFixedAmount);
        verify(couponTypeRepository).save(any(CouponType.class));
    }

    @DisplayName("?????????????????? ?????? ????????? ?????? ???????????? ?????? ????????? ??? ???????????????.")
    @Test
    void modifyRecommendMemberCoupon() {

        RecommendMemberCouponType recommendMemberCouponType = mock(RecommendMemberCouponType.class);
        when(recommendMemberCouponType.getCouponTypeNo())
            .thenReturn(1);
        when(recommendMemberCouponType.getCouponType())
            .thenReturn(couponTypeFixedRate);

        given(recommendMemberCouponTypeRepository.findTopFetchJoinByOrderByCouponTypeNoDesc())
            .willReturn(Optional.ofNullable(recommendMemberCouponType));

        couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDtoFixedRate);

        verify(couponTypeRepository).save(any(CouponType.class));
        verify(recommendMemberCouponTypeRepository).save(any(RecommendMemberCouponType.class));

        assertThat(couponTypeFixedRate.getIsStopGenerationIssue())
            .isTrue();
    }

    @DisplayName("???????????? ?????? ???????????? ????????? changePrevRecommendMemberCouponTypeAsStop ???????????? ???????????? ?????????.")
    @Test
    void modifyRecommendMemberCoupon_never_changePrevRecommendMemberCouponTypeAsStop() {
        given(recommendMemberCouponTypeRepository.findTopFetchJoinByOrderByCouponTypeNoDesc())
            .willReturn(Optional.empty());
        assertThatNoException().isThrownBy(
            () -> couponTypeService.modifyRecommendMemberCoupon(couponTypeCreationRequestDtoFixedRate));

        verify(couponTypeRepository, never()).findById(anyInt());
    }

    @Test
    void findCouponTypeRecommend() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));
        Page<CouponTypeDto> page = new PageImpl<>(Collections.EMPTY_LIST, pageRequest, 10);

        when(couponTypeRepository.findAllCouponTypesRecommend(any())).thenReturn(page);

        // when
        Page<CouponTypeDto> couponTypes = couponTypeService.findCouponTypeRecommend(any());

        // then
        assertThat(couponTypes).isEqualTo(page);
        verify(couponTypeRepository).findAllCouponTypesRecommend(any());

    }
}