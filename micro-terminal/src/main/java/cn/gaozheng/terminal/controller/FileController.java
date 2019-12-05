package cn.gaozheng.terminal.controller;


import cn.gaozheng.sales.model.po.TblFileInfo;
import cn.gaozheng.sales.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @ApiOperation(value = "上传文件", response = TblFileInfo.class)
    @PostMapping(value = "/upload")
    public List<TblFileInfo> uploadTempFile( MultipartHttpServletRequest multipartHttpServletRequest) {
        return fileService.uploadTempFile(multipartHttpServletRequest.getFileMap());
    }
}
