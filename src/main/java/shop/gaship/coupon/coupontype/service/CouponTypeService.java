package shop.gaship.coupon.coupontype.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;

/**
 * The interface Coupon type service.
 *
 * @author : 최겸준, 조재철
 * @since 1.0
 */
public interface CouponTypeService {

    /**
     * 해당 쿠폰타입에 해당하는 쿠폰을 생성, 발급을 막기위해 stop_generation_issues 를 수정하는 service 메서드 입니다.
     *
     * @param couponTypeNo 수정하고자 하는 쿠폰타입의 번호 입니다.
     */
    void modifyCouponTypeStopGenerationIssue(Integer couponTypeNo);

    /**
     * coupon type 을 삭제하기 위한 service 메서드 입니다.
     *
     * @param couponTypeNo 삭제하고자 하는 쿠폰타입의 번호 입니다.
     */
    void deleteCouponType(Integer couponTypeNo);

    /**
     * 모든 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 모든 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypes(Pageable pageable);

    /**
     * 아직 생성, 발급 되지 않은 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 아직 생성, 발급 되지 않은 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypesCanDelete(Pageable pageable);

    /**
     * 이미 생성, 발급 된 coupon type 의 Page 타입만큼 가져오기 위한 service 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 이미 생성, 발급 된 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findCouponTypesCannotDelete(Pageable pageable);
}
