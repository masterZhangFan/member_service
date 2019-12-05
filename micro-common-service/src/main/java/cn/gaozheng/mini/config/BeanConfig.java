package cn.gaozheng.mini.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * BeanConfig
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Component
@Getter
@Setter
public class BeanConfig {

    @Value("${sms.api-key}")
    private String apiKey;
    @Value("${sms.username}")
    private String username;
    @Value("${sms.password}")
    private String password;
    @Value("${sms.template}")
    private String template;

    @Value("${spring.profiles.active}")
    private String profile;

    @Value("${wx.config.url}")
    private String url;
    @Value("${wx.config.app-id}")
    private String appId;
    @Value("${wx.config.secret}")
    private String secret;
    @Value("${wx.config.grant-type}")
    private String grantType;

    @Value("${system.plan.total-weight}")
    private Double planTotalWeight;
    @Value("${system.plan.sug-weight}")
    private Double planSugWeight;
    @Value("${system.plan.sug-weight-float-fatio}")
    private Double planSugWeightFloatRatio;

    @Value("${system.order.weight-for-bag}")
    private Long weightForBag;

    @Value("${system.login.auth.phone-for-test}")
    private String phoneForTest;

    @Value("${system.login.auth.code-for-test}")
    private String codeForTest;

    @Value("${system.default.file.upload-path}")
    private String uploadPath;

    @Value("${system.default.file.upload-dir}")
    private String uploadDir;
    
}
