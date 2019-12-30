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
import cn.gaozheng.sales.model.vo.WatermarkModel;
import cn.gaozheng.sales.model.vo.base.EnumUtils;
import cn.gaozheng.sales.service.*;
import cn.gaozheng.sales.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
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
    @Autowired
    OOSService oosService;
    @Autowired
    FileService fileService;

    @Override
    public List<TblShareTemp> shareTemps( Integer shareTempType){
        List<TblShareTemp>  tblShareTempList =  tblShareTempMapper.shareTempList(shareTempType);
        return tblShareTempList;
    }
    @Override
    public ShareInstance shareInstance(Long userId, Long shareTempId){
        User user = userMapper.selectByPrimaryKey(userId);
        List<DomainSet> domainSets = domainSetMapper.selectAll();
        if (domainSets == null || domainSets.size() == 0){
            throw new SaleException("域名没配置");
        }
        TblShareTemp tblShareTemp = tblShareTempMapper.selectByPrimaryKey(shareTempId);
        if (tblShareTemp == null) throw new SaleException("模板不存在");
        DomainSet domainSet = domainSets.get(0);
        String shareUrl = domainSet.getDomainUrl()+ "reg2/index.php?pk="+getPk(user.getUserName());
        if (!EmptyUtil.isNotEmpty(user.getQrcodeUrl())) {
            if (EmptyUtil.isNotEmpty(user.getIcon())){
                user.setIcon(EnumUtils.defaultProtrailt);
            }
            BufferedImage qrImageBuffer = imageService.generateQRCodeImage(shareUrl, tblShareTemp.getShareQrcodePointSize().intValue(), getInnerICon(user.getIcon()));
            String localPath = fileService.saveTempImage(qrImageBuffer, "qrode" + userId + ".jpg");
            String fullPath = oosService.upload(localPath, userId);
            if (fullPath == null) {
                throw new SaleException("创建二维码失败");
            }
            user.setQrcodeUrl(fullPath);
        }
        String title = user.getNickname()+"邀请您加入高级会员";
        List<WatermarkModel> watermarkModelList = new ArrayList<>();
        WatermarkModel m1 = new WatermarkModel();
        m1.setImageUrl(user.getQrcodeUrl());
        m1.setX(tblShareTemp.getShareQrcodePointX());
        m1.setY(tblShareTemp.getShareQrcodePointY());
        WatermarkModel m2 = new WatermarkModel();
        m2.setText(title);
        m2.setX(tblShareTemp.getShareTextPointX());
        m2.setY(tblShareTemp.getShareTextPointY());
        watermarkModelList.add(m1);
        watermarkModelList.add(m2);
        String imageUrl =  oosService.watermark(tblShareTemp.getShareTempBigPic(),tblShareTemp.getShareTempSizeWidth(),tblShareTemp.getShareTempSizeHeight(),watermarkModelList);
        TblMemberSetting tblMemberSetting = settingService.getSysConfig();
        ShareInstance shareInstance =  new ShareInstance();
        shareInstance.setImgUrl(imageUrl);
        shareInstance.setDesc(tblMemberSetting.getMemberRules());
        shareInstance.setLink(shareUrl);
        shareInstance.setTitle(title);
        return shareInstance;
    }
    @Override
    public ShareInstance shareInfo(Long userId,Long shareTempId){
       return shareInstance(userId,shareTempId);
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
