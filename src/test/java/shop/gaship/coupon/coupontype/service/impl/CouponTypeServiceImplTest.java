package shop.gaship.coupon.coupontype.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
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
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.exception.DeleteCouponTypeException;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * CouponType service 테스트 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(CouponTypeServiceImpl.class)
class CouponTypeServiceImplTest {

    @MockBean
    CouponTypeRepository couponTypeRepository;

    @MockBean
    CouponGenerationIssueRepository couponGenerationIssueRepository;

    @Autowired
    CouponTypeService couponTypeService;

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
        ReflectionTestUtils.setField(fixRateCouponType, "name", "최겸준");
        ReflectionTestUtils.setField(fixRateCouponType, "discountRate", 10.0);
        ReflectionTestUtils.setField(fixRateCouponType, "isStopGenerationIssue", false);

        ReflectionTestUtils.setField(fixAmountCouponType, "couponTypeNo", fixAmountCouponTypeNo);
        ReflectionTestUtils.setField(fixAmountCouponType, "name", "조재철");
        ReflectionTestUtils.setField(fixAmountCouponType, "discountAmount", 10000L);
        ReflectionTestUtils.setField(fixAmountCouponType, "isStopGenerationIssue", false);
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
            "해당 쿠폰 종류에 대한 쿠폰이 이미 생성, 발급 되었기 때문에 수정 불가능 합니다.");

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
}