package ca.jrvs.apps.trading;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class AppConfig {


    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

            //you can hard code this for now or use env_var
           // @Value("${iex.host}")
            //private String iex_host;

            @Bean
            public MarketDataConfig marketDataConfig() {
                MarketDataConfig marketDataConfig = new MarketDataConfig();
                marketDataConfig.setHost("https://cloud.iexapis.com/");
                marketDataConfig.setToken("pk_3b1a567e4bba43b096233f15f8a1c242");
                return marketDataConfig;
            }

            @Bean
            public DataSource dataSource() {
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setUrl("jdbc:postgresql://localhost:5432/jrvstrading");
                dataSource.setUsername("postgres");
                dataSource.setPassword("password");
                return dataSource;
            }

            //http://bit.ly/2tWTmzQ connectionPool
            @Bean
            public HttpClientConnectionManager httpClientConnectionManager() {
                PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
                cm.setMaxTotal(50);
                cm.setDefaultMaxPerRoute(50);
                return cm;
            }
}

