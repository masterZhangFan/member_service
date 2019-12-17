package cn.gaozheng.sales.model.vo.charge;

import cn.gaozheng.sales.model.po.TblChargeConfig;
import lombok.Data;

@Data
public class ChargeConfig extends TblChargeConfig {
    private Long timestamp;// 必填，生成签名的时间戳
    private String nonceStr;// 必填，生成签名的随机串
    private String signature;// 必填，签名
    private String appId;
}
