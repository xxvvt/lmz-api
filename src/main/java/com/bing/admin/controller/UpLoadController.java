package com.bing.admin.controller;

import com.bing.admin.model.FileModel;
import com.bing.admin.server.FileServer;
import com.bing.admin.utils.VeDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @description: 上传文件控制器
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/19 4:09 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/19 4:09 下午
 * @updateRemark:
 * @version: 1.0
 **/
@RestController
@RequestMapping({"/api/file"})
public class UpLoadController {
    @Value("${file.path}")
    private String FILE_PATH;
    @Value("${access.base-url.file}")
    private String ACCESS_BASE_URL;
    @Resource
    FileServer fileServer;
    @PostMapping("upload")
    public Object upload(@RequestParam("file")MultipartFile file){
        return saveFile(file);
    }
    @PostMapping("multiUpload")
    public Object multiUpload(@RequestParam("file")MultipartFile[] files){
        System.out.println("文件的个数:"+files.length);
        for (MultipartFile f : files){
            saveFile(f);
        }
        return "ok";
    }

    private Object saveFile(MultipartFile file){
        if (file.isEmpty()){
            return "未选择文件";
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = FILE_PATH+"/"+ VeDate.getNowDateShort().toString()+"/";
        File temp = new File(filePath);
        if (!temp.exists()){
            temp.mkdirs();
        }

        File localFile = new File(filePath+filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            FileModel fm = new FileModel();
            fm.setName(filename);
            fm.setPath(filePath+filename);
            fm.setSuffix(Files.probeContentType(localFile.toPath()));
            fileServer.addFile(fm);
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return "上传失败";
        }

        return "ok";
    }
}