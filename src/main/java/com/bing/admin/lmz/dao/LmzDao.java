package com.bing.admin.lmz.dao;


import com.bing.admin.lmz.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Results;

import java.util.List;
import java.util.Map;

//通用Mapper
public interface LmzDao {

    // sql插入语句
    @Insert("${sql}")
    void insertSql(@Param("sql") String sql);
    // sql插入语句2
    @Insert("${sql}")
    void insertSqls(@Param("sql") String sql,@Param("data") Object data);

    // sql更新语句
    @Update("${sql}")
    void updateSql(@Param("sql") String sql);
    @Select("select  Top 100 * from lmzjt.dbo.BD_bom ")
    List<Map<String,String>> getBomList();

    @Select("select * from flow.dbo.flow_basic ")
    List<Map<String,String>> getFlowBasic();
    @Results({
            @Result(property = "bgtType", column = "bgtType"),
            @Result(property = "bgtYear", column = "bgtYear"),
            @Result(property = "versionId", column = "versionId")
    })
    @Select("select * from flow.dbo.flow_main where id=#{id}")
    FlowMain getFlowMain(String id);

    @Select("select * from flow.dbo.flow_operation where flow_id=#{id}")
    List<FlowOperation> getFlowOperationList(String id);

    @Select("select * from flow.dbo.flow_operation where id=#{id}")
    FlowOperation getFlowOperation(String id);
    //获取当前flow_id下当前节点的operation
    @Select("select * from flow.dbo.flow_operation where flow_id=#{flowId} and node_id = #{nodeId}")
    FlowOperation getFlowOperationToNode(String flowId,String nodeId);
    //获取当前flow_id下未结束的operation
    @Select("select * from flow.dbo.flow_operation where flow_id=#{id} and id!=#{oid} and (status=1 or status=3 or status=5)")
    List<FlowOperation> getFlowONoClossListByFlowId(String id,String oid);
    //获取当前parent_id下未结束的main
    @Results({
            @Result(property = "bgtType", column = "bgtType"),
            @Result(property = "bgtYear", column = "bgtYear"),
            @Result(property = "versionId", column = "versionId")
    })
    @Select("select * from flow.dbo.flow_main where parent_id=#{id} and status!=2 and id!=#{flow_id}")
    List<FlowMain> getFlowMNoClossListByFlowId(String id,String flow_id);
    //获取所有下级节点
    @Select("WITH one AS (\n" +
            "\tSELECT\n" +
            "\t\tb.id \n" +
            "\tFROM\n" +
            "\t\tflow_model a\n" +
            "\t\tLEFT JOIN flow_operation b ON CHARINDEX( b.node_id, a.up_model ) > 0 \n" +
            "\t\tAND b.node_id != #{node_id}\n" +
            "\t\tAND b.flow_id = #{flow_id} \n" +
            "\tWHERE\n" +
            "\t\tcharindex( #{node_id}, a.up_model ) > 0 \n" +
            "\t\tAND a.type = 3 \n" +
            "\t\tAND b.status = 1 \n" +
            "\t),\n" +
            "\ttwo AS (\n" +
            "\tSELECT\n" +
            "\t\t* \n" +
            "\tFROM\n" +
            "\t\tflow_model \n" +
            "\tWHERE\n" +
            "\t\tup_model = #{node_id}\n" +
            "\t\tUNION ALL\n" +
            "\tSELECT\n" +
            "\t\tb.* \n" +
            "\tFROM\n" +
            "\t\tflow_model a\n" +
            "\t\tLEFT JOIN flow_model b ON a.id = b.up_model \n" +
            "\tWHERE\n" +
            "\t\tcharindex( #{node_id}, a.up_model ) > 0 \n" +
            "\t\tand (select count(*) from one) = 0 --使同收束节点的未完成节点数量为0\n" +
            "\t\tAND a.type = 3\n" +
            "\t) \n" +
            "\t\n" +
            "\t\n" +
            "\tSELECT\n" +
            "\tid AS node_id,\n" +
            "\tparent_id AS basic_id,\n" +
            "\ttitle,\n" +
            "\ttype,\n" +
            "\ttitle AS node,\n" +
            "\tdepartment,\n" +
            "\tdepartment_id,\n" +
            "\tuser_name \n" +
            "FROM\n" +
            "\ttwo \n" +
            "WHERE\n" +
            "\tid IS NOT NULL")
    List<FlowOperation> getNextFlowOperaction(String flow_id,String node_id);
    @Select("select * from lmzjt.dbo.login ")
    ApiLoginModel getApiLoginData();
    //修改flow_operation状态
    @Update("update flow.dbo.flow_operation set status=#{status} where id=#{id}")
    int updateFlowOperationStatus(String id,String status);
    @Update("update flow.dbo.flow_operation set status=#{status},create_time=#{createTime} where id=#{id}")
    int updateFlowOperationStatusTime(String id,String status,String createTime);
    //修改flow_main状态
    @Update("update flow.dbo.flow_main set status=#{status} where id=#{id}")
    int updateFlowMainStatus(String id,String status);
    @Insert(" insert into BD_Sal_OutStockDetailRpt_new2(FBILLNAME,FBILLTYPENAME,FBILLNUMBER,FBILLDATE,FApproveDate,FSALEORGNAME,FSettleOrgName,FDEPTNUMBER,FDEPTNAME,FSALERNUMBER,FSALERNAME,FSalGroupName,FSETTLETYPENAME,FCUSTOMERNUMBER,FCUSTOMERID,FCustGroupName,FMATERIALNUMBER,FMATERIALID,FMAPID,FMAPNAME,FMATERIALGROUP,FERPCLSID,FCATEGORYID,FMATERIALMODEL,FMATERIALAUXID,FSTOCKID,FStockLocId,FLOTNAME,FSOORDERNO,FUNITNAME,FCURRENCYNAME,FLCCURRENCYNAME,FOUTSTOCKQTY,FLCNOTAXOUTSTOCKPRICE,FLCTAXOUTSTOCKPRICE,FLCTAXNETPRICE,FLCNOTAXOUTSTOCKAMOUNT,FLCTAXOUTSTOCKAMOUNT,FCOSTPRICE,FLCOUTSTOCKTATALCOSTAMOUNT,FRECQTY,FLCNOTAXRECAMOUNT,FLCTAXRECAMOUNT,FNOTAXOUTSTOCKPRICE,FTAXOUTSTOCKPRICE,FTAXNETPRICE,FTAXRECAMOUNT,FNOTAXRECAMOUNT,FNOTAXOUTSTOCKAMOUNT,FSALCOSTPRICE,FOUTSTOCKTATALCOSTAMOUNT,FTAXOUTSTOCKAMOUNT,FBaseUnitName,FDeliveryQty,FReceiveQty,FSALEPRICEUNITNAME,FOUTSTOCKQTY_PRICE,FRECQTY_PRICE,FEXTAUXUNITID,FOUTSTOCKQTY_EXTAUX,FRECQTY_EXTAUX,FISFREE,FMTONO,FPRESETBASENAME1,FPRESETBASENAME2,FPRESETASSISTANTNAME1,FPRESETASSISTANTNAME2,F_TPS_DECIMAL,F_TPS_AMOUNT2,F_TPS_AMOUNT3,F_PAEZ_AMOUNT1,F_TPS_AMOUNT1,FALLAMOUNT,F_HZJ_FALLAMOUNT,FCREATEDATE,F_KHSQ,F_KHQY,FNOTE) values(#{value})")
    void insertNew2(@Param("value") String value);

    @Insert({
            "<script>",
            "INSERT INTO BD_Sal_OutStockDetailRpt_new2(",
            "FBILLNAME,FBILLTYPENAME,FBILLNUMBER,FBILLDATE,FApproveDate,FSALEORGNAME,FSettleOrgName,FDEPTNUMBER,FDEPTNAME,FSALERNUMBER,FSALERNAME,FSalGroupName,FSETTLETYPENAME,FCUSTOMERNUMBER,FCUSTOMERID,FCustGroupName,FMATERIALNUMBER,FMATERIALID,FMAPID,FMAPNAME,FMATERIALGROUP,FERPCLSID,FCATEGORYID,FMATERIALMODEL,FMATERIALAUXID,FSTOCKID,FStockLocId,FLOTNAME,FSOORDERNO,FUNITNAME,FCURRENCYNAME,FLCCURRENCYNAME,FOUTSTOCKQTY,FLCNOTAXOUTSTOCKPRICE,FLCTAXOUTSTOCKPRICE,FLCTAXNETPRICE,FLCNOTAXOUTSTOCKAMOUNT,FLCTAXOUTSTOCKAMOUNT,FCOSTPRICE,FLCOUTSTOCKTATALCOSTAMOUNT,FRECQTY,FLCNOTAXRECAMOUNT,FLCTAXRECAMOUNT,FNOTAXOUTSTOCKPRICE,FTAXOUTSTOCKPRICE,FTAXNETPRICE,FTAXRECAMOUNT,FNOTAXRECAMOUNT,FNOTAXOUTSTOCKAMOUNT,FSALCOSTPRICE,FOUTSTOCKTATALCOSTAMOUNT,FTAXOUTSTOCKAMOUNT,FBaseUnitName,FDeliveryQty,FReceiveQty,FSALEPRICEUNITNAME,FOUTSTOCKQTY_PRICE,FRECQTY_PRICE,FEXTAUXUNITID,FOUTSTOCKQTY_EXTAUX,FRECQTY_EXTAUX,FISFREE,FMTONO,FPRESETBASENAME1,FPRESETBASENAME2,FPRESETASSISTANTNAME1,FPRESETASSISTANTNAME2,F_TPS_DECIMAL,F_TPS_AMOUNT2,F_TPS_AMOUNT3,F_PAEZ_AMOUNT1,F_TPS_AMOUNT1,FALLAMOUNT,F_HZJ_FALLAMOUNT,FCREATEDATE,F_KHSQ,F_KHQY,FNOTE",  // 列名列表
            ") VALUES (",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "'${item}'",  // 使用索引访问每个值
            "</foreach>",
            ")",
            "</script>"
    })
    void insertNew2Batch(@Param("list") List<String> values);
    @Insert("INSERT INTO BD_Sal_OutStockDetailRpt_new2 (${flid}) VALUES ('${value}')")
    void insertNew2BatchToFlid(@Param("flid") String flid,@Param("value") String value);

    //获取下一个流程
    @Results({
            @Result(property = "bgtType", column = "bgtType"),
            @Result(property = "bgtYear", column = "bgtYear"),
            @Result(property = "versionId", column = "versionId")
    })
    @Select("with one as (SELECT\n" +
            "\tc.id as basic_id,\n" +
            "\tc.title_formula,\n" +
            "\tc.type as title_type,\n" +
            "\ta.parent_id,\n" +
            "\n" +
            "\ta.core_id\n" +
            "FROM\n" +
            "\tflow_main a\n" +
            "\tJOIN flow_main b ON b.id = a.parent_id\n" +
            "\tJOIN flow_basic c ON c.id = b.basic_id \n" +
            "WHERE\n" +
            "\ta.id= '${flow_id}'\n" +
            "\t\n" +
            "\t)\n" +
            "select \n" +
            "\tc.id as basic_id,\n" +
            "\tc.title_formula,\n" +
            "\tc.type as title_type,\n" +
            "\tb.id as core_id,\n" +
            "\ta.parent_id\n" +
            "from \n" +
            "\tone a\n" +
            "\t join flow_model_correlation b on a.basic_id = b.parent_id and b.up_model = a.core_id\n" +
            "\t join flow_basic c on b.flow_id = c.id")
    List<FlowMain> getNextFlowMain(String flow_id);

    @Select("SELECT MAX\n" +
            "\t( b.flow_no ) flow_no \n" +
            "FROM\n" +
            "\tflow_main a\n" +
            "\tLEFT JOIN flow_main b ON b.parent_id = a.parent_id\n" +
            "\twhere a.id  = '${flow_id}'")
    long getMaxFlowNo(String flow_id);

    //获取流程下的开始节点
    @Select("\n" +
            "SELECT\n" +
            "\td.type AS node_type,\n" +
            "\td.department,\n" +
            "\td.department_id,\n" +
            "\td.user_name,\n" +
            "\td.title AS node,\n" +
            "\td.id AS node_id, \n" +
            "d.type," +
            "d.type as node_type," +
            "1 as up_model\n" +
            "FROM\n" +
            " flow_model d \n" +
            " \n" +
            " where d.parent_id = '${basic_id}'\n" +
            "\tAND d.up_model = '1'\n" +
            "\t")
    List<FlowOperation> getFlowModel(String basic_id);

    //获取流程节点
    @Select("select * from flow_model where id = #{id}")
    FlowModel getFlowModelItem(String id);
    @Select("WITH node AS ( SELECT up_model FROM flow_model WHERE id = #{node_id} ) ,\n" +
            "\n" +
            "datas as (\n" +
            "select b.id from \n" +
            "flow_model a \n" +
            " join flow_operation b on a.id = b.node_id\n" +
            "join node c on a.id = c.up_model\n" +
            "where b.flow_id = #{flow_id} and a.type!=3\n" +
            "union all\n" +
            "select c.id from \n" +
            "flow_model a \n" +
            "join node d on a.id = d.up_model and a.type=3\n" +
            "join flow_model b on CHARINDEX(b.id, a.up_model) >0\n" +
            "join flow_operation c on c.node_id = b.id\n" +
            "where \n" +
            "c.flow_id = #{flow_id}\n" +
            "\n" +
            ")\n" +
            "select * from datas\n" +
            "where id is not null")
    List<String> getUpFlowOperation(String flow_id,String node_id);

    //获取流程下的开始节点
    @Select("select * from flow_operation where flow_id = #{flow_id} and up_model='1'")
    List<FlowOperation> getFlowStartOperation(String flow_id);

    @Select("SELECT\n" +
            "\ta.flow_no,\n" +
            "\ta.title,\n" +
            "\ta.create_time,\n" +
            "\ta.create_by,\n" +
            "\ta.bgtType,\n" +
            "\ta.bgtYear,\n" +
            "\ta.versionId,\n" +
            "\tb.node,\n" +
            "\tb.status ,\n" +
            "\tc.FName,\n" +
            "\td.lbmc\n" +
            "FROM\n" +
            "\tflow_main AS a\n" +
            "\tLEFT JOIN flow_operation AS b ON a.id = b.flow_id\n" +
            "\tLEFT JOIN lmzjt.dbo.BD_User c ON b.user_name = c.FUserAccount \n" +
            "\tleft join lmzjt.dbo.sxmxb d on b.department_id = d.lbbh and d.flbh ='50'\n" +
            "and d.sxbh = '5023071701'\n" +
            "WHERE\n" +
            "\tb.id = #{oid}")
    Map<String,String> getFlowData(String oid);

    @Delete("delete from flow_model_correlation where id = #{id}")
    void deleteFlowModelCorrelation(String id);

    @Delete("delete from flow_model where id = #{id}")
    void deleteFlowModel(String id);
    //获取版本年份
    @Select("SELECT DISTINCT bgtYear FROM [lmzjt].[dbo].[DIC_BGT_VERSION]  where state !='未启用' order by bgtYear desc")
    String[] getBgtYear();

    //通过年份获取版本列表
    @Select("SELECT * FROM [lmzjt].[dbo].[DIC_BGT_VERSION] where  state ='01' order by bgtYear desc")
    List<Map<String,String>> getBgtVersionByYear();

    //通过nodeid获取params
    @Select("select * from flow_model_params where model_id = #{id}")
    List<Map<String,String>> getNodeParams(String id);

    @Select("select * from flow_push_basic order by create_time desc")
    List<FlowPushBasic> getFlowPushBasicList();

    //通过id获取FlowPushBasic
    @Select("select * from flow_push_basic where id = #{id}")
    FlowPushBasic getFlowPushBasic(String id);
    //通过pid查询FlowPushNode
    @Select("select * from flow_push_node where pid = #{pid} order by sort")
    List<FlowPushNode> getFlowPushNodeList(String pid);

    //推送流程中获取同级节点
    @Select("select * from flow.dbo.flow_operation where flow_id=#{id} and id!=#{oid} and node_id = #{nodeId} and (status=1 or status=3 or status=5)")
    List<FlowOperation> getFlowPushTJNode(String id, String oid, String nodeId);

    //推送流程中获取下级节点
        @Select("with one as (\n" +
                "\tselect top 1 a.* from flow_push_node a \n" +
                "\tleft join flow_push_node b on a.pid = b.pid and a.sort > b.sort\n" +
                "\twhere b.id = #{nodeId}\n" +
                "\torder by a.sort asc\n" +
                "\t\n" +
                ")\n" +
                "select a.* from flow_operation a join one as b on a.node_id = b.id\n" +
                "where a.flow_id = #{id} ")
    List<FlowOperation> getFlowPushXJNode(String id,String nodeId);
    //新增FlowError信息
    @Insert("insert into flow_error (id,create_time,create_user,type,content,remark) values (#{id},#{createTime},#{createUser},#{type},#{content},#{remark})")
    void insertFlowError(FlowError flowError);
}
