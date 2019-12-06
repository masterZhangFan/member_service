package cn.gaozheng.sales.model.vo;

import cn.gaozheng.sales.model.po.TblDelegate;
import cn.gaozheng.sales.model.po.TblMember;
import cn.gaozheng.sales.model.po.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfo{
    //用户ID
    private Long userId;
    //昵称
    private String nickname;
    //email
    private String email;
    //电话
    private String phone;
    //购物余额(元)
    private Double shoppingBalance;
    //话费
    private Double callBalance;
    //可提现金额
    private Double cash;
    //粉丝数
    private Integer fansNumber;
    //
    //1.初级会员 2.高级会员
    private Integer memberLevel;
    //代理信息
    private DelegateInfo delegate;
    //token
    private String token;
}
