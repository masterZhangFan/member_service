package cn.gaozheng.sales.config;

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

    public static final String PROFILE_DEV = "dev";
    public static final String PROFILE_TEST = "test";

    @Value("${sms.config.api-key}")
    private String apikey;

    @Value("${sms.config.send.enalbe:false}")
    private boolean smsEnable;

    @Value("${sms.config.username}")
    private String username;

    @Value("${sms.config.password}")
    private String password;

    @Value("${sms.template.enable-enter-door}")
    private String enableEnterDoor;

    @Value("${sms.template.out-of-door}")
    private String outOfDoor;

    @Value("${spring.profiles.active}")
    private String profile;
}
