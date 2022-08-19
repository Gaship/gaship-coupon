package shop.gaship.coupon.coupongenerationissue.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.entity.CouponGenerationIssue;
import shop.gaship.coupon.coupongenerationissue.exception.CouponGenerationIssueNotFoundException;
import shop.gaship.coupon.coupongenerationissue.exception.NotFoundCouponGenerationIssueException;
import shop.gaship.coupon.coupongenerationissue.exception.RecommendMemberCouponTypeNotFoundException;
import shop.gaship.coupon.coupongenerationissue.repository.CouponGenerationIssueRepository;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;
import shop.gaship.coupon.coupontype.entity.CouponType;
import shop.gaship.coupon.coupontype.repository.CouponTypeRepository;
import shop.gaship.coupon.recommendmembercoupontype.entity.RecommendMemberCouponType;
import shop.gaship.coupon.recommendmembercoupontype.repository.RecommendMemberCouponTypeRepository;

/**
 * 쿠폰 생성 발급에 대한 비즈니스 로직을 처리하는 service 구현체 클래스 입니다.
 *
 * @author : 최겸준, 조재철
 * @see CouponGenerationIssueService
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CouponGenerationIssueServiceImpl implements CouponGenerationIssueService {

    private final CouponGenerationIssueRepository couponGenerationIssueRepository;
    private final SchedulerAdapterAboutCouponCreation schedulerAdapterAboutCouponCreation;
    private final RecommendMemberCouponTypeRepository recommendMemberCouponTypeRepository;

    /**
     * 생성, 발급된 모든 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssues(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssues(pageable);
    }

    /**
     * 생성, 발급된 모든 쿠폰 중 사용된 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsed(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUsed(pageable);
    }

    /**
     * 생성, 발급된 모든 쿠폰 중 사용하지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnused(Pageable pageable) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnused(pageable);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesByMemberNo(pageable, memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용한 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUsedByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUsedByMemberNo(pageable, memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedByMemberNo(Pageable pageable,
        Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedByMemberNo(pageable, memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않고 만료가 된 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndExpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndExpiredByMemberNo(pageable,
            memberNo);
    }

    /**
     * 특정한 회원의 생성, 발급된 모든 쿠폰 중 사용하지 않고 만료가 되지 않은 쿠폰을 조회하기 위한 로직을 처리하는 메서드 입니다.
     *
     * @param pageable pagination 에 맞게 조회하기 위한 정보를 담고있는 객체.
     * @param memberNo 조회하는 회원의 회원 번호.
     * @return 해당 로직을 처리 후 적합한 데이터(페이지)를 반환 합니다.
     */
    @Override
    public Page<CouponGenerationIssueResponseDto> findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(
        Pageable pageable, Integer memberNo) {
        return couponGenerationIssueRepository.findAllCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(pageable,
            memberNo);
    }

    /**
     * 주문시 해당 쿠폰을 사용시 비즈니스 로직을 처리하는 메서드.
     *
     * @param couponGenerationIssueNumbers 사용하고자 하는 쿠폰생성발급 번호.
     */
    @Transactional
    @Override
    public void useCoupons(List<Integer> couponGenerationIssueNumbers) {

        for (Integer couponGenerationIssueNumber : couponGenerationIssueNumbers) {
            CouponGenerationIssue couponGenerationIssue =
                couponGenerationIssueRepository.findById(couponGenerationIssueNumber).orElseThrow(
                    NotFoundCouponGenerationIssueException::new);

            couponGenerationIssue.useCoupon();
        }

    }

    /**
     * 주문 취소시 해당 사용 쿠폰을 취소하는 비즈니스 로직을 처리하는 메서드.
     *
     * @param couponGenerationIssueNumbers 사용 취소하고자 하는 쿠폰생성발급 번호.
     */
    @Transactional
    @Override
    public void cancelUsedCoupons(List<Integer> couponGenerationIssueNumbers) {

        for (Integer couponGenerationIssueNumber : couponGenerationIssueNumbers) {
            CouponGenerationIssue couponGenerationIssue =
                couponGenerationIssueRepository.findById(couponGenerationIssueNumber).orElseThrow(
                    NotFoundCouponGenerationIssueException::new);

            couponGenerationIssue.cancelUsedCoupon();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void addCouponGenerationIssue(
        CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {
        schedulerAdapterAboutCouponCreation.addCouponGenerationIssue(
            couponGenerationIssueCreationRequestDto);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void addCouponGenerationIssueToRecommendMember(Integer recommendMemberNo) {
        RecommendMemberCouponType recommendMemberCouponType =
            recommendMemberCouponTypeRepository.findTopFetchJoinByOrderByCouponTypeNoDesc()
                                               .orElseThrow(
                                                   RecommendMemberCouponTypeNotFoundException::new);

        CouponGenerationIssue couponGenerationIssue = CouponGenerationIssue.builder()
                                                                           .couponType(
                                                                               recommendMemberCouponType.getCouponType())
                                                                           .memberNo(recommendMemberNo)
                                                                           .generationDatetime(LocalDateTime.now())
                                                                           .issueDatetime(LocalDateTime.now())
                                                                           .expirationDatetime(LocalDateTime.now())
                                                                           .build();

        couponGenerationIssueRepository.save(couponGenerationIssue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CouponGenerationIssueDetailsResponseDto findCouponGenerationIssue(
        Integer couponGenerationIssueNo) {

        CouponGenerationIssue coupon =
            couponGenerationIssueRepository.findCouponGenerationIssueByIdAsFetchJoin(
                                               couponGenerationIssueNo)
                                           .orElseThrow(CouponGenerationIssueNotFoundException::new);

        CouponType couponType = coupon.getCouponType();
        return new CouponGenerationIssueDetailsResponseDto(couponType.getName(),
            couponType.getDiscountAmount(), couponType.getDiscountRate(),
            coupon.getGenerationDatetime(), coupon.getIssueDatetime(),
            coupon.getExpirationDatetime(), coupon.getUsedDatetime(), coupon.getMemberNo());
    }
}
