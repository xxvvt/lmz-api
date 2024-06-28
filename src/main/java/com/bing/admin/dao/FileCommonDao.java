package com.bing.admin.dao;


import com.bing.admin.model.FileModel;
import com.bing.admin.model.ZsUser;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通用Mapper

public interface FileCommonDao {
    // sql插入语句
    @Insert("${sql}")
    void insertSql(@Param("sql") String sql);

    // sql更新语句
    @Update("${sql}")
    void updateSql(@Param("sql") String sql);

    @Insert("insert into zs_file table_name(name,suffx,path,addtime) values(${file.name},${file.suffx},${file.path},${file.addtime})")
    void insertFile(@Param("file")FileModel file);

    @Select("select * from zs_file where id=#{id}")
    FileModel getFile(@Param("id") String id);
}
