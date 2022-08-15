package shop.gaship.coupon.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * property에 저장된 정보를 불러와서 webClient bean을 만드는 설정파일입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "gaship-server")
@Getter
@Setter
public class WebClientConfig {

    private String schedulerUrl;

    @Bean
    public WebClient schedulerWebClient() {
        return WebClient.builder().baseUrl(schedulerUrl).defaultHeader("Content-Type",
            MediaType.APPLICATION_JSON_VALUE).defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE).build();
    }
}
