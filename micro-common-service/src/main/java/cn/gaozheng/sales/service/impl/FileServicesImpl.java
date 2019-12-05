package cn.gaozheng.sales.service.impl;

import cn.gaozheng.sales.exception.SalesException;
import cn.gaozheng.sales.mapper.TblFileInfoMapper;
import cn.gaozheng.sales.model.po.TblFileInfo;
import cn.gaozheng.sales.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class FileServicesImpl implements FileService {
    @Value("${web.upload-path}")
    private String uploadPath;

    @Autowired
    TblFileInfoMapper tblFileInfoMapper;

    private TblFileInfo insertFileWithPath(MultipartFile multipartFile , String path){
        if (multipartFile == null || multipartFile.isEmpty())
        {
            return  null;
        }
        String fileDir = uploadPath +"/"+ path ;
        UUID uuid =  UUID.randomUUID();
        if (!new File(fileDir).exists()){
            new File(fileDir).mkdirs();
        }
        String extendName = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1);
        String filePath = fileDir+ uuid.toString() + "."+ extendName;
        //目标文件
        File destFile = new File(filePath);

        //写入文件
        try {
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
            throw  new SalesException(e.getMessage());
        }
        String url = filePath.replace(uploadPath,"");
        TblFileInfo tblFileInfo = new TblFileInfo();
        tblFileInfo.setName(multipartFile.getOriginalFilename());
        tblFileInfo.setExtendName(extendName);
        tblFileInfo.setSize(multipartFile.getSize());
        tblFileInfo.setRefrenceCount(0);
        tblFileInfo.setUploadTime(new Date());
        tblFileInfo.setPath(url);
        tblFileInfoMapper.insert(tblFileInfo);
        return tblFileInfo;
    }
    /*上传临时文件*/
    @Override
    public List<TblFileInfo> uploadTempFile(Map<String, MultipartFile> files) {
        List<TblFileInfo> fileReturns = new ArrayList<>();
        List<Dictionary<Integer, String>> uploadsUrls = new ArrayList<>();
        for (String key : files.keySet()) {
            MultipartFile file = files.get(key);
            String filePath = "";
            Date nowDate = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fileSuffix = sdf.format(nowDate);
            filePath = "files" + directorySeparator() + fileSuffix + directorySeparator();
            TblFileInfo fileInfo = insertFileWithPath(file, filePath);
            if (fileInfo == null) {
                throw new SalesException("文件《" + file.getName() + "》上传失败");
            }
            fileReturns.add(fileInfo);
        }
        return  fileReturns;
    }

    /*返回系统分隔符*/
    private String directorySeparator(){
        return "/";
    }

}
