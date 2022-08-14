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

    Page<CouponTypeDto> findAllCouponTypes(Pageable pageable);

    Page<CouponTypeDto> findAllCouponTypesCanDelete(Pageable pageable);

    Page<CouponTypeDto> findAllCouponTypesCannotDelete(Pageable pageable);
}
