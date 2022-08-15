package shop.gaship.coupon.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * @author : 최겸준
 * @since 1.0
 */
@SpringBootTest
@TestPropertySource(value = "classpath:application.properties")
class DataSourceConfigTest {

    @Autowired
    private DataSource dataSource;

    @DisplayName("datasource가 올바른 스키마로 연결되었는지 확인한다.")
    @Test
    void name() throws SQLException {
        assertThat(dataSource.getConnection().getMetaData().getURL())
            .contains("gaship_coupon_test");
    }
}