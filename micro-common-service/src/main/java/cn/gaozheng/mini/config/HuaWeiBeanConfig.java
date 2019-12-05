package cn.gaozheng.mini.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class HuaWeiBeanConfig {
    private String url = "https://rtcsms.cn-north-1.myhuaweicloud.com:10743/sms/batchSendSms/v1"; //APP接入地址+接口访问URI
    private String appKey = "c8RWg3ggEcyd4D3p94bf3Y7x1Ile"; //APP_Key
    private String appSecret = "q4Ii87BhST9vcs8wvrzN80SfD7Al"; //APP_Secret
    private String sender = "csms12345678"; //国内短信签名通道号或国际/港澳台短信通道号
    private String templateId = "8ff55eac1d0b478ab3c06c3c6a492300"; //模板ID

}
