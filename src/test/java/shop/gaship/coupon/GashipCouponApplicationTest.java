package shop.gaship.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * 메인 메서드 테스트 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@SpringBootTest
class GashipCouponApplicationTest {

    @Test
    void contextLoads(ApplicationContext context) {
        assertThat(context).isNotNull();
    }
}