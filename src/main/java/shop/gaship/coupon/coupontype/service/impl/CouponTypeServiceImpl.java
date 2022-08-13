package shop.gaship.coupon.coupontype.service.impl;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.exception.DenDeleteCouponTypeException;
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
    @Transactional
    @Override
    public void modifyCouponTypeStopGenerationIssue(Integer couponTypeNo) {

        CouponType couponType = getCouponType(couponTypeNo);

        couponType.changeStopGenerationIssue(true);

    }

    /**
     * coupon type을 삭제하기 위한 메서드 입니다.
     *
     * @param couponTypeNo 삭제하고자 하는 쿠폰타입의 번호 입니다.
     */
    @Transactional
    @Override
    public void deleteCouponType(Integer couponTypeNo) {

        if (Boolean.FALSE.equals(couponGenerationIssueRepository.existCouponHasCouponTypeNo(couponTypeNo))) {
            throw new DenDeleteCouponTypeException();
        }

        CouponType couponType = getCouponType(couponTypeNo);

        couponTypeRepository.delete(couponType);

    }

    /**
     * 쿠폰타입번호로 쿠폰타입을 조회하기 위한 메서드 입니다.
     *
     * @param couponTypeNo 쿠폰타입을 조회하기 위한 쿠폰타입번호
     * @return
     */
    private CouponType getCouponType(Integer couponTypeNo) {
        return couponTypeRepository.findById(couponTypeNo)
                                   .orElseThrow(NotFoundCouponTypeException::new);
    }

}
