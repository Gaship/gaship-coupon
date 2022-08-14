package shop.gaship.coupon.coupontype.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
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

    @Test
    void modifyCouponTypeStopGenerationIssue() {
    }

    @Test
    void deleteCouponType() {
    }

    @Test
    void findCouponTypes() {
    }

    @Test
    void findCouponTypesCanDelete() {
    }

    @Test
    void findCouponTypesCannotDelete() {
    }

    @Test
    void findCouponTypesFixedAmount() {
    }

    @Test
    void findCouponTypesFixedRate() {
    }
}