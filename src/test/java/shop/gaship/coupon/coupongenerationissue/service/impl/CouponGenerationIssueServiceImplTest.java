package shop.gaship.coupon.coupongenerationissue.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.exception.CouponGenerationIssueNotFoundException;
import shop.gaship.coupon.coupongenerationissue.exception.RecommendMemberCouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

/**
 * CouponGenerationIssue 에 관련된 service 테스트 클래스 입니다.
 *
 * @author 최겸준, 조재철
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@Import(CouponGenerationIssueServiceImpl.class)
class CouponGenerationIssueServiceImplTest {

    @Autowired
    private CouponGenerationIssueService service;

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
            service.findCouponGenerationIssues(any());

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
            service.findCouponGenerationIssuesUsed(any());

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
            service.findCouponGenerationIssuesUnused(any());

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
            service.findCouponGenerationIssuesByMemberNo(any(), any());

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
            service.findCouponGenerationIssuesUsedByMemberNo(any(), any());

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
            service.findCouponGenerationIssuesUnusedByMemberNo(any(), any());

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
            service.findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(any(), any());

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
            service.findCouponGenerationIssuesUnusedAndExpiredByMemberNo(any(), any());

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
        service.useCoupons(couponGenerationIssueNumbers);

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
        service.cancelUsedCoupons(couponGenerationIssueNumbers);

        // then
        verify(couponGenerationIssueRepository).findById(couponGenerationIssueUsed.getCouponGenerationIssueNo());
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

        given(recommendMemberCouponTypeRepository.findTopFetchJoinByOrderByCouponTypeNoDesc())
            .willReturn(Optional.ofNullable(recommendMemberCouponType));

        given(couponTypeRepository.findById(anyInt()))
            .willReturn(Optional.ofNullable(couponType));

        assertThatNoException().isThrownBy(() -> service.addCouponGenerationIssueToRecommendMember(1));
        verify(couponGenerationIssueRepository).save(any(CouponGenerationIssue.class));
    }

    @DisplayName("추천인쿠폰종류가 존재하지 않을때 추천인쿠폰생성발급 요청이 들어오면 RecommendMemberCouponTypeNotFoundException 이 발생한다.")
    @Test
    void addCouponGenerationIssueToRecommendMember_fail_RecommendMemberCouponTypeNotFoundException() {
        given(recommendMemberCouponTypeRepository.findTopFetchJoinByOrderByCouponTypeNoDesc())
            .willReturn(Optional.empty());

        assertThatThrownBy(() -> service.addCouponGenerationIssueToRecommendMember(1))
            .isInstanceOf(RecommendMemberCouponTypeNotFoundException.class)
            .hasMessageContaining(RecommendMemberCouponTypeNotFoundException.MESSAGE);
    }
}