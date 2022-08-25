package shop.gaship.coupon.coupontype.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;

/**
 * couponType 의 repository(crud 를 위한)의 커스텀 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
public interface CouponTypeRepositoryCustom {

    /**
     * 모든 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 모든 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findAllCouponTypes(Pageable pageable);

    /**
     * 아직 생성, 발급 되지 않은 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 아직 생성, 발급 되지 않은 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findAllCouponTypesCanDelete(Pageable pageable);

    /**
     * 이미 생성, 발급 된 coupon type 의 Page 타입만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 이미 생성, 발급 된 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findAllCouponTypesCannotDelete(Pageable pageable);

    /**
     * 정액 할인 정책을 가진 coupont type의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정액 할인 정책인 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findAllCouponTypesFixedAmount(Pageable pageable);

    /**
     * 정률 할인 정책을 가진 coupont type의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 정률 할인 정책인 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findAllCouponTypesFixedRate(Pageable pageable);

    /**
     * 추천인 coupon type 의 Page 타입 만큼 가져오기 위한 repository 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 추천인 쿠폰 타입의 Page 타입.
     */
    Page<CouponTypeDto> findAllCouponTypesRecommend(Pageable pageable);
}
