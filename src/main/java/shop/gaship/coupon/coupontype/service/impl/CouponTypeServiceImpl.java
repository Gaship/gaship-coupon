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

    /**
     * 해당 쿠폰타입에 해당하는 쿠폰을 생성, 발급을 막기위해 stop_generation_issues 를 수정하는 메서드 입니다.
     *
     * @param couponTypeNo 수정하고자 하는 쿠폰타입의 번호 입니다.
     */
    @Override
    public void modifyCouponTypeStopGenerationIssue(Integer couponTypeNo) {

        if (Boolean.FALSE.equals(couponGenerationIssueRepository.existCouponHasCouponTypeNo(couponTypeNo))) {
            throw new DenyChangeCouponTypeException();
        }

        CouponType couponType = couponTypeRepository.findById(couponTypeNo)
                                                    .orElseThrow(NotFoundCouponTypeException::new);

        couponType.changeStopGenerationIssue(true);

    }
}
