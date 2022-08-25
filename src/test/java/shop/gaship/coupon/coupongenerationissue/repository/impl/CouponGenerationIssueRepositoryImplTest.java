package shop.gaship.coupon.coupongenerationissue.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
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

    @MockBean
    CouponGenerationIssueService couponGenerationIssueService;

    private CouponType couponTypeUsed;
    private CouponType couponTypeUnusedAndExpired;
    private CouponType couponTypeUnusedAndUnexpired;

    private CouponGenerationIssue couponGenerationIssueUsed;
    private CouponGenerationIssue couponGenerationIssueUnusedAndExpired;
    private CouponGenerationIssue couponGenerationIssueUnusedAndUnexpired;

    private CouponGenerationIssueResponseDto couponGenerationIssueUsedResponseDto;
    private CouponGenerationIssueResponseDto couponGenerationIssueUnusedAndExpiredResponseDto;
    private CouponGenerationIssueResponseDto couponGenerationIssueUnusedAndUnexpiredResponseDto;

    @BeforeEach
    void setUp() {
        setCouponTypeUsed();
        setCouponTypeUnusedAndExpired();
        setCouponTypeUnusedAndUnexpired();


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

    private void setCouponTypeUsed() {
        couponTypeUsed = new CouponType();

        ReflectionTestUtils.setField(couponTypeUsed, "couponTypeNo", 1);
        ReflectionTestUtils.setField(couponTypeUsed, "name", "겸준쿠폰");
        ReflectionTestUtils.setField(couponTypeUsed, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponTypeUsed, "isStopGenerationIssue", Boolean.FALSE);
    }

    private void setCouponTypeUnusedAndExpired() {
        couponTypeUnusedAndExpired = new CouponType();

        ReflectionTestUtils.setField(couponTypeUnusedAndExpired, "couponTypeNo", 1);
        ReflectionTestUtils.setField(couponTypeUnusedAndExpired, "name", "겸준쿠폰");
        ReflectionTestUtils.setField(couponTypeUnusedAndExpired, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponTypeUnusedAndExpired, "isStopGenerationIssue", Boolean.FALSE);
    }

    private void setCouponTypeUnusedAndUnexpired() {
        couponTypeUnusedAndUnexpired = new CouponType();

        ReflectionTestUtils.setField(couponTypeUnusedAndUnexpired, "couponTypeNo", 1);
        ReflectionTestUtils.setField(couponTypeUnusedAndUnexpired, "name", "겸준쿠폰");
        ReflectionTestUtils.setField(couponTypeUnusedAndUnexpired, "discountAmount", 1000L);
        ReflectionTestUtils.setField(couponTypeUnusedAndUnexpired, "isStopGenerationIssue", Boolean.FALSE);
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

        PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoPage =
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

        PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredResponseDtoPage =
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

        PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredResponseDtoPage =
            new PageImpl(couponGenerationIssueUnusedAndUnexpiredResponseDtoList, PageRequest.of(0, 5), 10);
    }

    @Test
    void existCouponHasCouponTypeNo() {

        // given
        CouponType couponType = new CouponType();

        ReflectionTestUtils.setField(couponType, "name", "최겸준");
        ReflectionTestUtils.setField(couponType, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponType, "discountRate", 10);

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

    @Test
    void findAllCouponGenerationIssues() {

        // given
        CouponType savedCouponTypeUsed = couponTypeRepository.save(couponTypeUsed);

        ReflectionTestUtils.setField(couponGenerationIssueUsed, "couponType", savedCouponTypeUsed);

        couponGenerationIssueRepository.save(couponGenerationIssueUsed);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssues(pageable);

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUsed.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUsed.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUsed.getExpirationDatetime());

    }

    @Test
    void findAllCouponGenerationIssuesUsed() {
        // given
        CouponType savedCouponTypeUsed = couponTypeRepository.save(couponTypeUsed);

        ReflectionTestUtils.setField(couponGenerationIssueUsed, "couponType", savedCouponTypeUsed);

        couponGenerationIssueRepository.save(couponGenerationIssueUsed);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesUsed(pageable);

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUsed.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUsed.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUsed.getExpirationDatetime());
    }

    @Test
    void findAllCouponGenerationIssuesUnused() {
        // given
        CouponType savedCouponTypeUnusedAndExpired = couponTypeRepository.save(couponTypeUnusedAndExpired);

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "couponType",
            savedCouponTypeUnusedAndExpired);

        couponGenerationIssueRepository.save(couponGenerationIssueUnusedAndExpired);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesUnused(pageable);

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUnusedAndExpired.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getExpirationDatetime());
    }

    @Test
    void findAllCouponGenerationIssuesByMemberNo() {
        // given
        CouponType savedCouponTypeUnusedAndExpired = couponTypeRepository.save(couponTypeUnusedAndExpired);

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "couponType",
            savedCouponTypeUnusedAndExpired);

        couponGenerationIssueRepository.save(couponGenerationIssueUnusedAndExpired);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesByMemberNo(pageable,
                couponGenerationIssueUnusedAndExpired.getMemberNo());

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUnusedAndExpired.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getExpirationDatetime());
    }

    @Test
    void findAllCouponGenerationIssuesUsedByMemberNo() {
        // given
        CouponType savedCouponTypeUsed = couponTypeRepository.save(couponTypeUsed);

        ReflectionTestUtils.setField(couponGenerationIssueUsed, "couponType", savedCouponTypeUsed);

        couponGenerationIssueRepository.save(couponGenerationIssueUsed);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesUsedByMemberNo(pageable,
                couponGenerationIssueUnusedAndExpired.getMemberNo());

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUsed.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUsed.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUsed.getExpirationDatetime());
    }

    @Test
    void findAllCouponGenerationIssuesUnusedByMemberNo() {
        // given
        CouponType savedCouponTypeUnusedAndExpired = couponTypeRepository.save(couponTypeUnusedAndExpired);

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "couponType",
            savedCouponTypeUnusedAndExpired);

        couponGenerationIssueRepository.save(couponGenerationIssueUnusedAndExpired);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedByMemberNo(pageable,
                couponGenerationIssueUnusedAndExpired.getMemberNo());

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUnusedAndExpired.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getExpirationDatetime());
    }

    @Test
    void findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo() {
        // given
        CouponType savedCouponTypeUnusedAndExpired = couponTypeRepository.save(couponTypeUnusedAndExpired);

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndExpired, "couponType",
            savedCouponTypeUnusedAndExpired);

        couponGenerationIssueRepository.save(couponGenerationIssueUnusedAndExpired);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(pageable,
                couponGenerationIssueUnusedAndExpired.getMemberNo());

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUnusedAndExpired.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUnusedAndExpired.getExpirationDatetime());
    }

    @Test
    void findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo() {
        // given
        CouponType savedCouponTypeUnusedAndUnExpired = couponTypeRepository.save(couponTypeUnusedAndUnexpired);

        ReflectionTestUtils.setField(couponGenerationIssueUnusedAndUnexpired, "couponType",
            savedCouponTypeUnusedAndUnExpired);

        couponGenerationIssueRepository.save(couponGenerationIssueUnusedAndUnexpired);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponPage"));

        // when
        Page<CouponGenerationIssueResponseDto> allCouponGenerationIssues =
            couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(pageable,
                couponGenerationIssueUnusedAndExpired.getMemberNo());

        // then
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getTotalElements()).isEqualTo(1);
        assertThat(allCouponGenerationIssues.getContent()).hasSize(1);

        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getMemberNo()).isEqualTo(
            couponGenerationIssueUnusedAndUnexpired.getMemberNo());
        assertThat(allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeUnusedAndUnexpired.getName());
        assertThat(
            allCouponGenerationIssues.get().collect(Collectors.toList()).get(0).getExpirationDatetime()).isEqualTo(
            couponGenerationIssueUnusedAndUnexpired.getExpirationDatetime());
    }
}