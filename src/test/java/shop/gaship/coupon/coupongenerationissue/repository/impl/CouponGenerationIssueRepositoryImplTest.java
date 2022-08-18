package shop.gaship.coupon.coupongenerationissue.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;

/**
 * @author : 조재철
 * @since 1.0
 */
class CouponGenerationIssueRepositoryImplTest {

    @Autowired
    CouponGenerationIssueService couponGenerationIssueService;

    @MockBean
    CouponGenerationIssueRepository couponGenerationIssueRepository;

    @MockBean
    CouponTypeRepository couponTypeRepository;

    private CouponType couponTypeUsed;
    private CouponType couponTypeUnusedAndExpired;
    private CouponType couponTypeUnusedAndUnexpired;

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
    void findAllCouponGenerationIssues() {
    }

    @Test
    void findAllCouponGenerationIssuesUsed() {
    }

    @Test
    void findAllCouponGenerationIssuesUnused() {
    }

    @Test
    void findAllCouponGenerationIssuesByMemberNo() {
    }

    @Test
    void findAllCouponGenerationIssuesUsedByMemberNo() {
    }

    @Test
    void findAllCouponGenerationIssuesUnusedByMemberNo() {
    }

    @Test
    void findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo() {
    }

    @Test
    void findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo() {
    }
}