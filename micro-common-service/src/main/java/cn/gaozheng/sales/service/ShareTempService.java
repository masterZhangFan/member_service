package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.TblShareTemp;
import cn.gaozheng.sales.model.vo.ShareInstance;

import java.awt.*;
import java.util.List;

public interface ShareTempService {
    List<TblShareTemp> shareTemps( Integer shareTempType);
    //生成分享实例
    ShareInstance shareInstance(Long userId,Long shareTempId);

    //生成分享实例
    ShareInstance shareInfo(Long userId);
}
