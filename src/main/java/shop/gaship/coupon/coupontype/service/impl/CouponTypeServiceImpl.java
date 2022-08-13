package shop.gaship.coupon.coupontype.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.exception.DenyChangeCouponTypeException;
import shop.gaship.coupon.coupontype.exception.NotFoundCouponTypeException;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * The type Coupon type service.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponTypeServiceImpl implements CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;
    private final CouponGenerationIssueRepository couponGenerationIssueRepository;

    @Override
    public void modifyCouponTypeStopGenerationIssue(Integer couponTypeNo) {

        if (couponGenerationIssueRepository.countByCouponTypeNo(couponTypeNo) != 0) {
            throw new DenyChangeCouponTypeException();
        }

        CouponType couponType = couponTypeRepository.findById(couponTypeNo)
                                                    .orElseThrow(NotFoundCouponTypeException::new);

        couponType.changeStopGenerationIssue(true);

    }
}
