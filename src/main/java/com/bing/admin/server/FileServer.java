package com.bing.admin.server;

import com.bing.admin.dao.FileCommonDao;
import com.bing.admin.dao.UserCommonDao;
import com.bing.admin.model.FileModel;
import com.bing.admin.model.ZsUser;
import com.bing.admin.utils.SqlSentence;
import com.bing.admin.utils.VeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service("FileServer")
public class FileServer extends TableServer{
    @Resource
    FileCommonDao fileCommonDao;

    public void addFile(FileModel file){
        String dat = VeDate.getStringDate();
        file.setAddtime(dat);
        fileCommonDao.insertFile(file);
    }
}
