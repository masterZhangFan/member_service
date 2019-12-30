package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.model.vo.WatermarkModel;
import cn.gaozheng.sales.service.OOSService;
import cn.gaozheng.sales.utils.EmptyUtil;
import cn.gaozheng.sales.utils.SaleStringUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OOSServiceImpl implements OOSService {
    //AccessKeyID：LTAI4Fru6FeT6facMp37vi3y
    //AccessKeySecret：JVk40129wGeV7q3xzcxaqiDwz8amdz
    public static final String accessUrl="https://zhuxiaobang.oss-cn-chengdu.aliyuncs.com";
    String filePath = "user/icon/qrcode/";
    String endpoint = "http://oss-cn-chengdu.aliyuncs.com";
    String accessKeyId = "LTAI4Fru6FeT6facMp37vi3y";
    String accessKeySecret = "JVk40129wGeV7q3xzcxaqiDwz8amdz";
    public static final String bucketName =  "zhuxiaobang";
    @Override
    public String upload(String uploadPath,long user_id){
        String fileName =  filePath+"qrcode_"+user_id+".jpg";
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(uploadPath));
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return accessUrl+fileName;
    }
    public String watermark(String sourceImg,double sourceWidth, double sourceHeight, List<WatermarkModel> watermarkModelList){
        String backgourdImage = sourceImg+"?x-oss-process=image/resize,w_"+new BigDecimal(sourceWidth).intValue()+",h_"+new BigDecimal(sourceHeight).intValue();
        for (WatermarkModel item:watermarkModelList) {
            String base64 = null;
            String imagePathStr = "";
            if (EmptyUtil.isNotEmpty(item.getImageUrl())) {
                String p1 = item.getImageUrl().replace(accessUrl, "");
                base64 = p1 + "?x-oss-process=image";
                base64 = SaleStringUtils.base64Encode(base64);
                base64 = base64.replace('+', '-');
                base64 = base64.replace('/', '_');
                base64 = base64.replaceAll("=", "");
                backgourdImage += "/watermark,image_" + base64 + ",g_nw,x_" + new BigDecimal(item.getX()).intValue() + ",y_" + new BigDecimal(item.getY()).intValue();
            }
            if (EmptyUtil.isNotEmpty(item.getText())){
                base64 = SaleStringUtils.base64Encode(item.getText());
                base64 = base64.replace('+', '-');
                base64 = base64.replace('/', '_');
                base64 = base64.replaceAll("=", "");
                backgourdImage+= "/watermark,text_"+base64+",size_12,color_000000,g_nw,x_"+new BigDecimal(item.getX()).intValue()+",y_"+new BigDecimal(item.getY()).intValue();
            }
        }
        //水印图片为当前的Bucket下图片，直接针对图片名称进行base64编码。
        return backgourdImage;
    }
    private void xx(){


    }
//    public static test(){
//
//        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "<yourAccessKeyId>";
//        String accessKeySecret = "<yourAccessKeySecret>";
//        String bucketName = "<yourBucketName>";
//
//// 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//// 创建存储空间。
//        ossClient.createBucket(bucketName);
//
//// 关闭OSSClient。
//        ossClient.shutdown();
//    }
}
