package shop.gaship.coupon.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author : 최겸준
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
