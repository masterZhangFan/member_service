package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.service.ImageService;
import cn.gaozheng.sales.utils.QRCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private Font font = null;
    private Graphics2D g = null;
    @Override
    public BufferedImage generateQRCodeImage(String text, int size,String innerImagePath) {
        //生成二维码
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = QRCodeUtil.encode(text, innerImagePath,size, true);
        }
        catch (Exception ex){

        }
        // 打印出解析出的内容
        return bufferedImage;
    }
    /**
     * 导入本地图片到缓冲区
     *
     * @param imgName
     * @return
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public BufferedImage modifyImagetogeter(BufferedImage addImage, BufferedImage sourceImg, int x, int y) {
        int width = addImage.getWidth();
        int height = addImage.getHeight();
        try {
            g = sourceImg.createGraphics();
            g.drawImage(addImage, x, y, width, height, null);
            g.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sourceImg;
    }
    // 根据str,font的样式以及输出文件目录
    public BufferedImage createImage(String text, Font font) {
        // 获取font的样式应用在输出内容上整个的宽高
        int[] arr = getWidthAndHeight(text, font);
        int width = arr[0];
        int height = arr[1];
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);//创建图片画布
        //透明背景  the begin
        Graphics2D g = image.createGraphics();
        image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g=image.createGraphics();
        //透明背景  the end
        /**
         如果你需要白色背景或者其他颜色背景可以直接这么设置，其实就是满屏输出的颜色
         我这里上面设置了透明颜色，这里就不用了
         */
        //g.setColor(Color.WHITE);
        //画出矩形区域，以便于在矩形区域内写入文字
        g.fillRect(0, 0, width, height);
        /**
         * 文字颜色，这里支持RGB。new Color("red", "green", "blue", "alpha");
         * alpha 我没用好，有用好的同学可以在下面留言，我开始想用这个直接输出透明背景色，
         * 然后输出文字，达到透明背景效果，最后选择了，createCompatibleImage Transparency.TRANSLUCENT来创建。
         * android 用户有直接的背景色设置，Color.TRANSPARENT 可以看下源码参数。对alpha的设置
         */
        g.setColor(Color.gray);
        // 设置画笔字体
        g.setFont(font);
        // 画出一行字符串
        g.drawString(text, 0, font.getSize());
        // 画出第二行字符串，注意y轴坐标需要变动
        g.drawString(text, 0, 2 * font.getSize());
        //执行处理
        g.dispose();
        // 输出png图片，formatName 对应图片的格式
        return image;
    }
    private static int[] getWidthAndHeight(String text, Font font) {
        Rectangle2D r = font.getStringBounds(text, new FontRenderContext(
                AffineTransform.getScaleInstance(1, 1), false, false));
        int unitHeight = (int) Math.floor(r.getHeight());//
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width = (int) Math.round(r.getWidth()) + 1;
        // 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        int height = unitHeight + 3;
        return new int[]{width, height};
    }
}
