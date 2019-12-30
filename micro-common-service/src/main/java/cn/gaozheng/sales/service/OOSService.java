package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.vo.WatermarkModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public interface OOSService {
    String upload(String uploadPath,long user_id);

    String watermark(String sourceImg,double sourceWidth, double sourceHeight, List<WatermarkModel> watermarkModelList);
}
