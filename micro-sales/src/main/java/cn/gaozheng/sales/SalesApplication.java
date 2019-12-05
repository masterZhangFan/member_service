package cn.gaozheng.sales;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"cn.gaozheng.mini.mybatis", "cn.gaozheng.sales.mapper"})
@EnableSwagger2Doc
@SpringBootApplication(exclude = {FlywayAutoConfiguration.class, DataSourceAutoConfiguration.class}, scanBasePackages = {"cn.gaozheng.sales", "cn.gaozheng.common"})
public class SalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }
}

