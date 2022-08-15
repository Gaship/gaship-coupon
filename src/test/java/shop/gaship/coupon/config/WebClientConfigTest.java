package shop.gaship.coupon.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author 최겸준
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = WebClientConfig.class)
@TestPropertySource(value = "classpath:application-dev.properties")
class WebClientConfigTest {
    @Autowired
    WebClientConfig webClientConfig;

    @Test
    void schedulerWebClient() {
        assertThat(webClientConfig.getSchedulerUrl())
            .isEqualTo("http://localhost:7074");

    }
}