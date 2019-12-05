package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.Fan;
import cn.gaozheng.sales.model.vo.UserInfo;

import java.util.List;

public interface UserInfoService {
    //获取直接粉丝
    List<Fan> getDirectlyFanWithUserId(Long userId);
    //获取简介粉丝
    List<Fan> getIndirectFanWithUserId(Long userId);

    UserInfo getUserInfo( Long userId);


}
