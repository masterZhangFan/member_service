package cn.gaozheng.sales.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static cn.gaozheng.util.Constants.DEFAULT_DATA_SOURCE;
import static cn.gaozheng.util.Constants.DRUID_DATA_SOURCE_1;

/**
 * DataSourceConfig
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = DEFAULT_DATA_SOURCE, destroyMethod = "close")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari", ignoreUnknownFields=false)
    public HikariDataSource dataSource() {
        return new HikariDataSource();
    }

    @Bean(name = DRUID_DATA_SOURCE_1, initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.oracle", ignoreUnknownFields=false)
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

}
