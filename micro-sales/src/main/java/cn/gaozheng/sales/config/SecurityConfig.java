package cn.gaozheng.sales.config;

import com.spring4all.swagger.Swagger2Configuration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@AutoConfigureBefore(Swagger2Configuration.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {





    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/user/login")
                .loginPage("/singIn.html")
                .and()
                .rememberMe()
                .tokenValiditySeconds(3600*24*7)
                .and()
                .authorizeRequests()
                .antMatchers("/","**","/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable();



    }
}
