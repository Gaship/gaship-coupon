package shop.gaship.coupon.coupontype.service.impl;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.couponissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.exception.DeleteCouponTypeException;
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
        if (Boolean.TRUE.equals(couponGenerationIssueRepository.existCouponHasCouponTypeNo(couponTypeNo))) {
            throw new DeleteCouponTypeException();
        }

        CouponType couponType = getCouponType(couponTypeNo);

        couponTypeRepository.delete(couponType);

    }

    /**
     * 모든 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 모든 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findCouponTypes(Pageable pageable) {
        return couponTypeRepository.findAllCouponTypes(pageable);
    }

    /**
     * 아직 생성, 발급 되지 않은 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 아직 생성, 발급 되지 않은 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findCouponTypesCanDelete(Pageable pageable) {
        return couponTypeRepository.findAllCouponTypesCanDelete(pageable);
    }

    /**
     * 이미 생성, 발급 된 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 이미 생성, 발급 된 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findCouponTypesCannotDelete(Pageable pageable) {
        return couponTypeRepository.findAllCouponTypesCannotDelete(pageable);
    }

    /**
     * 정액 할인 정책을 가진 coupont type 의 Page 타입만큼 조회하기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정액 할인 정책인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findCouponTypesFixedAmount(Pageable pageable) {
        return couponTypeRepository.findAllCouponTypesFixedAmount(pageable);
    }

    /**
     * 정률 할인 정책을 가진 coupont type 의 Page 타입만큼 조회하기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정률 할인 정책인 쿠폰 타입의 Page 타입.
     */
    @Override
    public Page<CouponTypeDto> findCouponTypesFixedRate(Pageable pageable) {
        return couponTypeRepository.findAllCouponTypesFixedRate(pageable);
    }

    /**
     * 쿠폰타입번호로 쿠폰타입을 조회하기 위한 service 메서드 입니다.
     *
     * @param couponTypeNo 쿠폰타입을 조회하기 위한 쿠폰타입번호
     * @return 쿠폰타입번호에 해당하는 쿠폰타입 객체를 반환 합니다.
     */
    private CouponType getCouponType(Integer couponTypeNo) {
        return couponTypeRepository.findById(couponTypeNo)
                                   .orElseThrow(NotFoundCouponTypeException::new);
    }

}
