package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.service.OOSService;
import cn.gaozheng.sales.utils.SaleStringUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.File;

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
    public String modifyImagetogeter(String addImage, String sourceImg, int x, int y,double w,double h){
        //水印图片为当前的Bucket下图片，直接针对图片名称进行base64编码。
        String p1 = addImage.replace(accessUrl,"");
        String imagePathStr = p1+"/watermark/wm.png?x-oss-process=image/resize,P_80";
        String encodeBase64 = SaleStringUtils.base64Encode(imagePathStr);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        safeBase64Str = safeBase64Str.replaceAll("=", "");
        String waterMark = sourceImg+"?x-oss-process=image/resize,w_4000/watermark,image_"+safeBase64Str+",g_center";
        return waterMark;
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
