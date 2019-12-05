package cn.gaozheng.sales.service;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface  ImageService {
    BufferedImage generateQRCodeImage(String text, int size,String innerImagePath);

    /**
     * 加载一张本地图片
     * @param imgName
     * @return
     */
    BufferedImage loadImageLocal(String imgName);

    /**
     * 将一张图片合成到另外一张图片
     * @param addImage
     * @param sourceImg
     * @param x
     * @param y
     * @return
     */
    BufferedImage modifyImagetogeter(BufferedImage addImage, BufferedImage sourceImg, int x, int y);

    /**
     *  创建文字图片
     * @param text
     * @param font
     * @return
     */
    BufferedImage createImage(String text, Font font);

}
