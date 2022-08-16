package shop.gaship.coupon.coupongenerationissue.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;

/**
 * CouponGenerationIssue 에 관련된 service 테스트 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(CouponGenerationIssueServiceImpl.class)
class CouponGenerationIssueServiceImplTest {

    @Autowired
    CouponGenerationIssueService couponGenerationIssueService;

    @MockBean
    CouponGenerationIssueRepository couponGenerationIssueRepository;

    @MockBean
    CouponTypeRepository couponTypeRepository;

    private CouponGenerationIssue couponGenerationIssueUsed;
    private CouponGenerationIssue couponGenerationIssueUnusedAndExpired;
    private CouponGenerationIssue couponGenerationIssueUnusedAndUnexpired;

    private CouponGenerationIssueResponseDto couponGenerationIssueUsedResponseDto;
    private CouponGenerationIssueResponseDto couponGenerationIssueUnusedAndExpiredResponseDto;
    private CouponGenerationIssueResponseDto couponGenerationIssueUnusedAndUnexpiredResponseDto;

    private PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoPage;
    private PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredResponseDtoPage;
    private PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredResponseDtoPage;

    @BeforeEach
    void setUp() {
        setCouponGenerationIssueUsedResponseDto();
        setCouponGenerationIssueUnusedAndExpiredResponseDto();
        setCouponGenerationIssueUnusedAndUnexpiredResponseDto();

        setCouponGenerationIssueUsedResponseDtoList();
        setCouponGenerationIssueUnusedAndExpiredResponseDtoList();
        setCouponGenerationIssueUnusedAndUnexpiredResponseDtoList();

        setCouponGenerationIssueUsed();
        setCouponGenerationIssueUnusedAndExpired();
        setCouponGenerationIssueUnusedAndUnexpired();
    }

    private void setCouponGenerationIssueUsed() {
        couponGenerationIssueUsed = new CouponGenerationIssue();

        ReflectionTestUtils.setField(couponGenerationIssueUsed, "couponGenerationIssueNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssueUsed, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssueUsed, "generationDatetime", LocalDateTime.now().minusDays(1));
        ReflectionTestUtils.setField(couponGenerationIssueUsed, "issueDatetime", LocalDateTime.now());
        ReflectionTestUtils.setField(couponGenerationIssueUsed, "expirationDatetime", LocalDateTime.now());
        couponGenerationIssueUsed.useCoupon();
    }

    private void setCouponGenerationIssueUsedResponseDto() {
        couponGenerationIssueUsedResponseDto = new CouponGenerationIssueResponseDto("최겸준", 1, null);
    }

    private void setCouponGenerationIssueUsedResponseDtoList() {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoList =
            List.of(couponGenerationIssueUsedResponseDto);

        couponGenerationIssueUsedResponseDtoPage =
            new PageImpl(couponGenerationIssueUsedResponseDtoList, PageRequest.of(0, 5), 10);
    }

    private void setCouponGenerationIssueUnusedAndExpired() {
        couponGenerationIssueUnusedAndExpired = new CouponGenerationIssue();

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "couponGenerationIssueNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "generationDatetime",
            LocalDateTime.now().minusDays(3));
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "issueDatetime",
            LocalDateTime.now().minusDays(2));
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "expirationDatetime",
            LocalDateTime.now().minusDays(1));
    }

    private void setCouponGenerationIssueUnusedAndExpiredResponseDto() {
        couponGenerationIssueUnusedAndExpiredResponseDto = new CouponGenerationIssueResponseDto("최겸준", 1,
            LocalDateTime.now().minusDays(1L));
    }

    private void setCouponGenerationIssueUnusedAndExpiredResponseDtoList() {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredResponseDtoList =
            List.of(couponGenerationIssueUnusedAndExpiredResponseDto);

        couponGenerationIssueUnusedAndExpiredResponseDtoPage =
            new PageImpl(couponGenerationIssueUnusedAndExpiredResponseDtoList, PageRequest.of(0, 5), 10);
    }

    private void setCouponGenerationIssueUnusedAndUnexpired() {
        couponGenerationIssueUnusedAndUnexpired = new CouponGenerationIssue();

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndUnexpired, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndUnexpired, "generationDatetime",
            LocalDateTime.now().minusDays(3));
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndUnexpired, "issueDatetime",
            LocalDateTime.now().minusDays(2));
        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndUnexpired, "expirationDatetime",
            LocalDateTime.now().plusDays(1));
    }

    private void setCouponGenerationIssueUnusedAndUnexpiredResponseDto() {
        couponGenerationIssueUnusedAndUnexpiredResponseDto = new CouponGenerationIssueResponseDto("최겸준", 1,
            LocalDateTime.now().plusDays(1L));
    }

    private void setCouponGenerationIssueUnusedAndUnexpiredResponseDtoList() {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredResponseDtoList =
            List.of(couponGenerationIssueUnusedAndUnexpiredResponseDto);

        couponGenerationIssueUnusedAndUnexpiredResponseDtoPage =
            new PageImpl(couponGenerationIssueUnusedAndUnexpiredResponseDtoList, PageRequest.of(0, 5), 10);
    }

    @Test
    void findCouponGenerationIssues() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssues(any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssues(any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUnusedAndExpiredResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssues(any());
    }

    @Test
    void findCouponGenerationIssuesUsed() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesUsed(any())).thenReturn(
            couponGenerationIssueUsedResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesUsed(any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUsedResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesUsed(any());
    }

    @Test
    void findCouponGenerationIssuesUnused() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesUnused(any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesUnused(any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUnusedAndExpiredResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesUnused(any());
    }

    @Test
    void findCouponGenerationIssuesByMemberNo() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesByMemberNo(any(), any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesByMemberNo(any(), any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUnusedAndExpiredResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesByMemberNo(any(), any());
    }

    @Test
    void findCouponGenerationIssuesUsedByMemberNo() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesUsedByMemberNo(any(), any())).thenReturn(
            couponGenerationIssueUsedResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesUsedByMemberNo(any(), any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUsedResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesUsedByMemberNo(any(), any());
    }

    @Test
    void findCouponGenerationIssuesUnusedByMemberNo() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedByMemberNo(any(), any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesUnusedByMemberNo(any(), any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUnusedAndExpiredResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesUnusedByMemberNo(any(), any());
    }

    @Test
    void findCouponGenerationIssuesUnusedAndExpiredByMemberNo() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(any(), any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUnusedAndExpiredResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(any(), any());
    }

    @Test
    void findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo() {
        // given
        when(couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUnusedAndUnexpiredResponseDtoPage);

        // when
        Page<CouponGenerationIssueResponseDto> couponGenerationIssues =
            couponGenerationIssueService.findCouponGenerationIssuesUnusedAndExpiredByMemberNo(any(), any());

        // then
        assertThat(couponGenerationIssues).isEqualTo(couponGenerationIssueUnusedAndUnexpiredResponseDtoPage);
        verify(couponGenerationIssueRepository).findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(any(), any());
    }

    @Test
    void useCoupons() {
        // given
        when(couponGenerationIssueRepository.findById(
            couponGenerationIssueUnusedAndExpired.getCouponGenerationIssueNo())).thenReturn(
            Optional.ofNullable(couponGenerationIssueUsed));

        List<Integer> couponGenerationIssueNumbers =
            List.of(couponGenerationIssueUnusedAndExpired.getCouponGenerationIssueNo());

        // when
        couponGenerationIssueService.useCoupons(couponGenerationIssueNumbers);

        // then
        verify(couponGenerationIssueRepository).findById(
            couponGenerationIssueUnusedAndExpired.getCouponGenerationIssueNo());
    }

    @Test
    void cancelUsedCoupons() {
        // given
        when(couponGenerationIssueRepository.findById(
            couponGenerationIssueUsed.getCouponGenerationIssueNo())).thenReturn(
            Optional.ofNullable(couponGenerationIssueUsed));

        List<Integer> couponGenerationIssueNumbers = List.of(couponGenerationIssueUsed.getCouponGenerationIssueNo());

        // when
        couponGenerationIssueService.cancelUsedCoupons(couponGenerationIssueNumbers);

        // then
        verify(couponGenerationIssueRepository).findById(couponGenerationIssueUsed.getCouponGenerationIssueNo());
    }
}