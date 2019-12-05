package cn.gaozheng.mini.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Druid数据源配置
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Configuration
public class DruidConfig {

    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid", ignoreUnknownFields=false)
    public DruidDataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
