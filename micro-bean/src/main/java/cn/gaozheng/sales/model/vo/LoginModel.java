package cn.gaozheng.sales.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginModel {
    @ApiModelProperty(value = "电话")
    private String phoneNumber;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "1.会员登录  2.代理登录")
    private Integer loginType;
    @ApiModelProperty(value = "微信ID")
    private String openId;
}
