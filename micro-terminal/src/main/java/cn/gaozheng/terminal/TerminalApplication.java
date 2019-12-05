package cn.gaozheng.terminal;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Cheng Bo
 * @version 1.0
 */
@SpringBootApplication(exclude = {FlywayAutoConfiguration.class}, scanBasePackages = {"cn.gaozheng.terminal", "cn.gaozheng.mini", "cn.gaozheng.chauffeur", "cn.gaozheng.sales", "cn.gaozheng.common"})
@EnableTransactionManagement
@EnableSwagger2Doc
@MapperScan(basePackages = {"cn.gaozheng.mini.mybatis", "cn.gaozheng.sales.mapper"})
public class TerminalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TerminalApplication.class, args);
    }

}
