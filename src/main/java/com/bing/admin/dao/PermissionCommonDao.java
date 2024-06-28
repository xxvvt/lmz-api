package com.bing.admin.dao;


import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通用Mapper

public interface PermissionCommonDao {

    /*
     * 通用单据状态变更操作
     * @param
     * String table_name 数据库表名
     * String sequenceStatus 单据状态
     * */
//    @AbMessage(subscribeKey = "sequenceStatus")
    @Update("update ${table_name} t set t.sequenceStatus = #{sequenceStatus} where t.id = #{id}")
    public void sequenceStatus(@Param("table_name") String table_name,@Param("id") String id, @Param("sequenceStatus") String sequenceStatus);

    @Select("select * from ${table_name} t where t.id = #{id}")
    public HashMap<String,Object> loadFormData(@Param("table_name") String table_name, @Param("id") String form_id);

    // 根据一个条件查询列表  =
    @Select("select * from ${table} where ${column_name} = #{column_value}")
    List<Map<String, Object>> getTableListById(@Param("table") String table,
                                               @Param("column_name") String column_name,
                                               @Param("column_value") String column_value);
    // 根据一个条件查询列表  in
    @Select("select * from ${table} where ${column_name} in ( ${column_values} )")
    List<Map<String, Object>> getTableListByIds(@Param("table") String table,
                                                @Param("column_name") String column_name,
                                                @Param("column_values") String column_values);

    // 根据多个条件更新列表
    @Update("update ${table} set ${set} where 1 = 1 ${where} ")
    void updateTable(@Param("table") String table,
                     @Param("set") String set,
                     @Param("where") String where);

    // 根据多个条件查询列表
    @Select("select ${select} from ${table} where 1 = 1 ${where}")
    List<Map<String, Object>> selectList(@Param("select") String select,
                                         @Param("table") String table,
                                         @Param("where") String where);
    // 根据多个条件查询分页列表
    @Select("select ${select} from ${table} where 1 = 1 ${where} ${limit}")
    List<Map<String, Object>> selectPaingList(@Param("select") String select,
                                         @Param("table") String table,
                                         @Param("where") String where,
                                         @Param("limit") String limit);
    // 根据多个条件查询数据数量
    @Select("select count(*) as total from ${table} where 1 = 1 ${where}")
    Integer selectCountList(@Param("table") String table,@Param("where") String where);
    // 根据多个条件查询一条记录
    @Select("select ${select} from ${table} where 1 = 1 ${where}")
    Map<String, Object> selectOne(@Param("select") String select,
                                  @Param("table") String table,
                                  @Param("where") String where);

    // 删除
    @Delete("delete from ${table} where 1=1 ${where} ")
    void delete(@Param("table") String table, @Param("where") String where);

    // 删除
    @Delete("delete from ${table} where id=#{id} ")
    void deleteId(@Param("table") String table, @Param("id") String id);

    @Delete("delete from ${table} where id=#{id} ")
    void deleteIdl(@Param("table") String table, @Param("id") long id);
    @Delete("delete from zs_role_permission where roleId=#{id} ")
    void deleteRolePermiss( @Param("id") long id);
    // 插入语句
    @Insert("INSERT INTO ${table} ${fields} values(${values}) ")
    void insert(@Param("table") String table,@Param("fields") String fields,@Param("values") String values);

    @Select("select a.roleId,b.* from zs_role_permission a left join zs_permission b on a.permissionId = b.id where a.roleId = ${roleId} order by SortNo asc")
    List<Map<String,String>> getPermissionRole(@Param("roleId") long roleId);
}