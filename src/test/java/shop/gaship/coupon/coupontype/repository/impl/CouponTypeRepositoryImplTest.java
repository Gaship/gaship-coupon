package shop.gaship.coupon.coupontype.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;
import shop.gaship.coupon.couponissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;

/**
 * CouponType 커스텀 repository 테스트 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CouponTypeRepositoryImplTest {

    @Autowired
    CouponTypeRepository couponTypeRepository;

    @Autowired
    CouponGenerationIssueRepository couponGenerationIssueRepository;

    private CouponType couponTypeFixRateCanDelete;
    private CouponType couponTypeFixAmountCannotDelete;

    private CouponTypeDto couponTypeDtoFixRateCanDelete;
    private CouponTypeDto couponTypeDtoFixAmountCannotDelete;

    private PageImpl<CouponTypeDto> couponTypeDtoFixRateCanPage;
    private PageImpl<CouponTypeDto> couponTypeDtoFixAmountCannotDeletePage;

    @BeforeEach
    void setUp() {
        setCouponTypeFixRateCanDelete();
        setCouponTypeFixAmountCannotDelete();
        setCouponTypeDtoFixRateCanDelete();
        setCouponTypeDtoFixAmountCannotDelete();
        setCouponTypeDtoFixRateCanDeleteList();
        setCouponTypeDtoFixAmountCannotDeleteList();
    }

    @Order(1)
    private void setCouponTypeFixRateCanDelete() {
        couponTypeFixRateCanDelete = new CouponType();

        ReflectionTestUtils.setField(couponTypeFixRateCanDelete, "name", "최겸준");
        ReflectionTestUtils.setField(couponTypeFixRateCanDelete, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponTypeFixRateCanDelete, "discountRate", 10.0);
    }

    @Order(1)
    private void setCouponTypeDtoFixRateCanDelete() {
        couponTypeDtoFixRateCanDelete = new CouponTypeDto();

        ReflectionTestUtils.setField(couponTypeDtoFixRateCanDelete, "name", "최겸준");
        ReflectionTestUtils.setField(couponTypeDtoFixRateCanDelete, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponTypeDtoFixRateCanDelete, "discountRate", 10.0);
    }

    @Order(1)
    private void setCouponTypeDtoFixAmountCannotDelete() {
        couponTypeDtoFixAmountCannotDelete = new CouponTypeDto();

        ReflectionTestUtils.setField(couponTypeDtoFixAmountCannotDelete, "name", "최겸준");
        ReflectionTestUtils.setField(couponTypeDtoFixAmountCannotDelete, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponTypeDtoFixAmountCannotDelete, "discountAmount", 10000L);
    }


    @Order(2)
    private void setCouponTypeDtoFixRateCanDeleteList() {
        List<CouponTypeDto> couponTypeDtoFixRateCanList = List.of(couponTypeDtoFixRateCanDelete);

        couponTypeDtoFixRateCanPage = new PageImpl(couponTypeDtoFixRateCanList, PageRequest.of(0, 5), 10);
    }

    @Order(1)
    private void setCouponTypeFixAmountCannotDelete() {
        couponTypeFixAmountCannotDelete = new CouponType();

        ReflectionTestUtils.setField(couponTypeFixAmountCannotDelete, "name", "최겸준");
        ReflectionTestUtils.setField(couponTypeFixAmountCannotDelete, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponTypeFixAmountCannotDelete, "discountAmount", 10000L);
    }

    @Order(2)
    private void setCouponTypeDtoFixAmountCannotDeleteList() {
        List<CouponTypeDto> couponTypeDtoFixAmountCannotDeleteList = List.of(couponTypeDtoFixAmountCannotDelete);

        couponTypeDtoFixAmountCannotDeletePage = new PageImpl(
            couponTypeDtoFixAmountCannotDeleteList, PageRequest.of(0, 5), 10);
    }

    @Test
    void findAllCouponTypes() {
        // given
        couponTypeRepository.save(couponTypeFixRateCanDelete);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));

        // when
        Page<CouponTypeDto> allCouponTypes = couponTypeRepository.findAllCouponTypes(pageable);

        // then
        assertThat(allCouponTypes.getTotalElements()).isEqualTo(1);
        assertThat(allCouponTypes.getContent()).hasSize(1);
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeFixRateCanDelete.getName());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getIsStopGenerationIssue()).isEqualTo(
            couponTypeFixRateCanDelete.getIsStopGenerationIssue());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountAmount()).isEqualTo(
            couponTypeFixRateCanDelete.getDiscountAmount());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountRate()).isEqualTo(
            couponTypeFixRateCanDelete.getDiscountRate());
    }

    @Test
    void findAllCouponTypesCanDelete() {
        // given
        couponTypeRepository.save(couponTypeFixRateCanDelete);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));

        // when
        Page<CouponTypeDto> allCouponTypes = couponTypeRepository.findAllCouponTypesCanDelete(pageable);

        // then
        assertThat(allCouponTypes.getTotalElements()).isEqualTo(1);
        assertThat(allCouponTypes.getContent()).hasSize(1);
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeFixRateCanDelete.getName());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getIsStopGenerationIssue()).isEqualTo(
            couponTypeFixRateCanDelete.getIsStopGenerationIssue());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountAmount()).isEqualTo(
            couponTypeFixRateCanDelete.getDiscountAmount());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountRate()).isEqualTo(
            couponTypeFixRateCanDelete.getDiscountRate());
    }

    @Test
    void findAllCouponTypesCannotDelete() {
        // given
        CouponType savedCouponType = couponTypeRepository.save(couponTypeFixAmountCannotDelete);

        CouponGenerationIssue couponGenerationIssue = new CouponGenerationIssue();

        ReflectionTestUtils.setField(couponGenerationIssue, "couponType", savedCouponType);
        ReflectionTestUtils.setField(couponGenerationIssue, "memberNo", 1);
        ReflectionTestUtils.setField(couponGenerationIssue, "generationDatetime", LocalDateTime.now());
        ReflectionTestUtils.setField(couponGenerationIssue, "expirationDatetime", LocalDateTime.now().plusDays(1));

        couponGenerationIssueRepository.save(couponGenerationIssue);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));

        // when
        Page<CouponTypeDto> allCouponTypes = couponTypeRepository.findAllCouponTypesCannotDelete(pageable);

        // then
        assertThat(allCouponTypes.getTotalElements()).isEqualTo(1);
        assertThat(allCouponTypes.getContent()).hasSize(1);
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeFixAmountCannotDelete.getName());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getIsStopGenerationIssue()).isEqualTo(
            couponTypeFixAmountCannotDelete.getIsStopGenerationIssue());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountAmount()).isEqualTo(
            couponTypeFixAmountCannotDelete.getDiscountAmount());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountRate()).isEqualTo(
            couponTypeFixAmountCannotDelete.getDiscountRate());
    }

    @Test
    void findAllCouponTypesFixedAmount() {
        // given
        couponTypeRepository.save(couponTypeFixAmountCannotDelete);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));

        // when
        Page<CouponTypeDto> allCouponTypes = couponTypeRepository.findAllCouponTypesFixedAmount(pageable);

        // then
        assertThat(allCouponTypes.getTotalElements()).isEqualTo(1);
        assertThat(allCouponTypes.getContent()).hasSize(1);
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeFixAmountCannotDelete.getName());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getIsStopGenerationIssue()).isEqualTo(
            couponTypeFixAmountCannotDelete.getIsStopGenerationIssue());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountAmount()).isEqualTo(
            couponTypeFixAmountCannotDelete.getDiscountAmount());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountRate()).isEqualTo(
            couponTypeFixAmountCannotDelete.getDiscountRate());
    }

    @Test
    void findAllCouponTypesFixedRate() {
        // given
        couponTypeRepository.save(couponTypeFixRateCanDelete);

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "couponTypeNo"));

        // when
        Page<CouponTypeDto> allCouponTypes = couponTypeRepository.findAllCouponTypesFixedRate(pageable);

        // then
        assertThat(allCouponTypes.getTotalElements()).isEqualTo(1);
        assertThat(allCouponTypes.getContent()).hasSize(1);
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getName()).isEqualTo(
            couponTypeFixRateCanDelete.getName());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getIsStopGenerationIssue()).isEqualTo(
            couponTypeFixRateCanDelete.getIsStopGenerationIssue());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountAmount()).isEqualTo(
            couponTypeFixRateCanDelete.getDiscountAmount());
        assertThat(allCouponTypes.get().collect(Collectors.toList()).get(0).getDiscountRate()).isEqualTo(
            couponTypeFixRateCanDelete.getDiscountRate());
    }
}