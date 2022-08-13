package shop.gaship.coupon.coupongenerationissue.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import shop.gaship.coupon.coupongenerationissue.dto.request.CouponIssueCreationRequestDto;

/**
 * 쿠폰 기능을 수행하기위해서 외부와의 통신이 필요할때 사용할 adapter class입니다.
 *
 * @author : 최겸준
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class CouponAdapter {
    private final WebClient schedulerWebClient;

    public void addCouponGenerationIssue(CouponIssueCreationRequestDto couponIssueCreationRequestDto) {
        schedulerWebClient.post()
            .bodyValue(couponIssueCreationRequestDto)
            .retrieve()
            .bodyToMono(void.class)
            .block();
    }
}
