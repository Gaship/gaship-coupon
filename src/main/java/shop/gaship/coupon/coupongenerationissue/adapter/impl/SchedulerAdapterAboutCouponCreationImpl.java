package shop.gaship.coupon.coupongenerationissue.adapter.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponGenerationIssueCreationRequestDto;

/**
 * SchedulerAdapterAboutCouponCreation interface를 구현한 클래스입니다.
 *
 * @author 최겸준
 * @see shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation ;
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class SchedulerAdapterAboutCouponCreationImpl implements
    shop.gaship.coupon.coupongenerationissue.adapter.SchedulerAdapterAboutCouponCreation {

    private final WebClient schedulerWebClient;

    /**
     * {@inheritDoc}
     */
    public void addCouponGenerationIssue(
        CouponGenerationIssueCreationRequestDto couponGenerationIssueCreationRequestDto) {
        schedulerWebClient.post().uri("/api/coupon-generations-issues")
                          .bodyValue(couponGenerationIssueCreationRequestDto)
                          .retrieve()
                          .bodyToMono(void.class)
                          .block();
    }
}
