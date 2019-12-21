package cn.gaozheng.sales.service;

import cn.gaozheng.sales.model.po.TblFileInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface FileService {
    //上传文件
    List<TblFileInfo> uploadTempFile( Map<String, MultipartFile> files );
    //保存图片
    String saveTempImage( BufferedImage bufferedImage,String fileName);
}
