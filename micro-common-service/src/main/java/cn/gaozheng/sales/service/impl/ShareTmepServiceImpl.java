package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SaleException;
import cn.gaozheng.sales.mapper.DomainSetMapper;
import cn.gaozheng.sales.mapper.TblShareTempMapper;
import cn.gaozheng.sales.mapper.UserMapper;
import cn.gaozheng.sales.model.po.DomainSet;
import cn.gaozheng.sales.model.po.TblMemberSetting;
import cn.gaozheng.sales.model.po.TblShareTemp;
import cn.gaozheng.sales.model.po.User;
import cn.gaozheng.sales.model.vo.ShareInstance;
import cn.gaozheng.sales.service.ImageService;
import cn.gaozheng.sales.service.SettingService;
import cn.gaozheng.sales.service.ShareTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class ShareTmepServiceImpl implements ShareTempService{
    @Value("${web.upload-path}")
    private String uploadPath;
    @Autowired
    TblShareTempMapper tblShareTempMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DomainSetMapper domainSetMapper;
    @Autowired
    ImageService imageService;
    @Autowired
    SettingService settingService;

    @Override
    public List<TblShareTemp> shareTemps( Integer shareTempType){
        List<TblShareTemp>  tblShareTempList =  tblShareTempMapper.shareTempList(shareTempType);
        return tblShareTempList;
    }
    @Override
    public ShareInstance shareInstance( Long userId, Long shareTempId){
        User user = userMapper.selectByPrimaryKey(userId);
        List<DomainSet> domainSets = domainSetMapper.selectAll();
        if (domainSets == null || domainSets.size() == 0){
            throw new SaleException("域名没配置");
        }
        TblShareTemp tblShareTemp = tblShareTempMapper.selectByPrimaryKey(shareTempId);
        if (tblShareTemp == null) throw new SaleException("模板不存在");

        DomainSet domainSet = domainSets.get(0);
        String shareUrl = domainSet.getDomainUrl()+ "reg2/index.php?pk="+getPk(user.getUserName());
        BufferedImage  qrImageBuffer = imageService.generateQRCodeImage(shareUrl,tblShareTemp.getShareQrcodePointSize().intValue(),getInnerICon(user.getIcon()));
        BufferedImage backGroudImage = imageService.loadImageLocal(uploadPath+tblShareTemp.getShareTempBigPic());
        imageService.modifyImagetogeter(qrImageBuffer,backGroudImage,tblShareTemp.getShareQrcodePointX(),tblShareTemp.getShareQrcodePointY());
        return null;
    }
    @Override
    public ShareInstance shareInfo(Long userId){
        User user = userMapper.selectByPrimaryKey(userId);
        List<DomainSet> domainSets = domainSetMapper.selectAll();
        if (domainSets == null || domainSets.size() == 0){
            throw new SaleException("域名没配置");
        }
        TblMemberSetting tblMemberSetting = settingService.getSysConfig();
        DomainSet domainSet = domainSets.get(0);
        String shareUrl = domainSet.getDomainUrl()+ "reg2/index.php?pk="+getPk(user.getUserName());
        String title = user.getNickname()+"邀请您加入高级会员";
        ShareInstance shareInstance =  new ShareInstance();
        shareInstance.setImgUrl(null);
        shareInstance.setDesc(tblMemberSetting.getMemberRules());
        shareInstance.setLink(shareUrl);
        shareInstance.setTitle(title);
        return shareInstance;
    }
    private String  getQRCodeUrl(){
        UUID uuid = UUID.randomUUID();
        String qrcodePath = uploadPath+"qrcodeTemp/";
        if (!new File(qrcodePath).exists()){
            new File(qrcodePath).mkdirs();
        }
        qrcodePath = qrcodePath+uuid+".png";
        return qrcodePath;
    }
    private String getInnerICon(String userIcon){
        return uploadPath+userIcon;
    }
    private String getPk(String userName){
        if (userName == null || userName.length()== 0) throw new SaleException("uid错误");
        userName = userName.replace("0","a");
        userName = userName.replace("1","b");
        userName = userName.replace("2","c");
        userName = userName.replace("3","d");
        userName = userName.replace("4","e");
        userName = userName.replace("5","f");
        userName = userName.replace("6","g");
        userName = userName.replace("7","h");
        userName = userName.replace("8","g");
        userName = userName.replace("9","i");
        return userName;
    }

}
