package cn.gaozheng.sales.service;

import java.awt.image.BufferedImage;

public interface OOSService {
    String upload(String uploadPath,long user_id);

    String modifyImagetogeter(String addImage, String sourceImg, int x, int y,double w,double h);
}
