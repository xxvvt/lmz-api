package com.bing.admin.dao;


import com.bing.admin.model.*;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//通用Mapper

public interface DlysCommonDao {

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

    // 插入语句
    @Insert("INSERT INTO ${table} ${fields} values(${values}) ")
    void insert(@Param("table") String table,@Param("fields") String fields,@Param("values") String values);

    @Select("select t.id_ as inst_id from bpm_instance t\n" +
            "where exists (\n" +
            "  select 1 from bpm_bus_link x where x.inst_id_ = t.id_ and x.biz_id_ = '${biz_id}'\n" +
            ") " +
            "order by t.create_time_ desc limit 1")
    public HashMap<String,Object> getInstIdByBizId(@Param("biz_id") String biz_id);

    @Select("select *  from zs_city")
    List<City> getCityList();

    //预警企业top10
    @Select("SELECT count(*) num,ownername FROM `dlys_data_vehicle_redcode` GROUP BY ownername order by num desc limit 10\n")
    List<Map<String,String>> getYujingTop();

    //今日预警总数
    @Select("SELECT count(*) FROM `dlys_data_vehicle_redcode` where batchid = (SELECT max(batchid) FROM `dlys_data_vehicle_redcode`)")
    double getYujingTotal();

    @Select("SELECT count(*) FROM `dlys_data_vehicle_redcode` where batchid = #{time}")
    double getYujingTotals(@Param("time") String time);

    //昨日预警总数
    @Select("SELECT count(*) FROM `dlys_data_vehicle_redcode` where batchid = (SELECT max(batchid) FROM `dlys_data_vehicle_redcode`)-1")
    double getOutDayYujingTotal();
    //获取辖区下属预警数量
    @Select("select grantziparea,b.name,count(*) as value from dlys_data_vehicle_redcode a left join zs_city_code b on a.grantziparea = b.code  where b.parentId = #{parentId} and batchid = (select max(batchid) from dlys_data_vehicle_redcode) GROUP BY a.grantziparea,b.name ORDER BY value desc")
    List<Map<String,String>> getYujingXiaquNumber(@Param("parentId") String parentId);

    @Select("with one as (\n" +
            "\n" +
            "SELECT  batchid,'有效' as sta FROM ( SELECT  batchid from `dlys_data_vehicle_redcode` GROUP BY batchid order by batchid desc limit 7) a\n" +
            "union all \n" +
            "SELECT  batchid,'无效' as sta FROM ( SELECT  batchid from `dlys_data_vehicle_redcode` GROUP BY batchid order by batchid desc limit 7) b\n" +
            ")\n" +
            ",two as (\n" +
            "\tSELECT count(*) as num,batchid, `status` FROM `dlys_data_vehicle_redcode` where batchid in (SELECT distinct batchid FROM `one` )  GROUP BY batchid ,status\n" +
            ")\n" +
            "select a.batchid,a.sta,IFNULL(b.num,0) as num from one a left join two b on a.batchid = b.batchid and a.sta = b.status\n" +
            "\n" +
            "\n")
    List<Map<String,String>> getqtyjsl();

    @Select("SELECT * FROM `dlys_data_vehicle_redcode` where id = ${id}")
    DlysDataVehicleRedcode getYjDatail(@Param("id") String id);

    @Insert("insert into zs_map_code values(#{map.code},#{map.name},#{map.childrens})")
    void insertMapCode(@Param("map") ZsMapCode map);

    @Select("select * from zs_map_code where code=#{code}")
    ZsMapCode getMapCode(@Param("code") String code);

    //获取核查记录，用以判断用户是否已核查
    @Select("select * from zs_clue_inspect where userId=#{clue.userId} and yujingId=${clue.yujingId}")
    ZsClueInspect getClueInspect(@Param("clue") ZsClueInspect clue);
    //获取核查记录，用以判断该预警是否已核查
    @Select("select * from zs_clue_inspect where  yujingId=${clue.yujingId}")
    ZsClueInspect getClueInspectOne(@Param("clue") ZsClueInspect clue);
    @Insert("insert into zs_city_code values(#{code},#{name},#{parentId})")
    void setCityCode(@Param("code") String code,@Param("name") String name,@Param("parentId") String parentId);

    @Update("update dlys_data_vehicle_redcode set status=#{status} where id =${id}")
    void saveCehicleStatus(@Param("id") String id ,@Param("status") String status);

    //用预警id获取重点名单记录，用以判断是否转入了重点监管名单
    @Select("select * from zs_emphasis where  yjId=${id} and type = ${type}")
    ZsEmphasis getClueEmphasis(@Param("id") long id,@Param("type") long type);

    //车牌号获取车辆信息
    @Select("select * from dlys_t_vehicle where  vehicleid=#{id} ")
    DlysTVehicle getCarDatail(@Param("id") String id);

    //公司名称获取公司信息
    @Select("select * from dlys_t_owner where  ownername=#{ownername} ")
    DlysTOwner getOwnerDatail(@Param("ownername") String ownername);

    //车牌号获取包车信息
    @Select("select * from dlys_t_rbrand where  VEHICLEID=#{id} limit 1")
    DlysTRbrand getRbrandDatail(@Param("id") String id);

    //车牌号获取危货信息
    @Select("select * from dlys_dtms_waybill where  carnumber=#{id} limit 1")
    DlysDtmsWaybill getWaybillDatail(@Param("id") String id);

    @Select("select  Top 100 * from lmzjt.dbo.BD_bom ")
    List<Map<String,String>> getBomList();

    @Select("select * from flow.dbo.flow_basic ")
    List<Map<String,String>> getFlowBasic();
}
