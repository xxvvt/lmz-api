package com.bing.admin.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bing.admin.dao.DlysCommonDao;
import com.bing.admin.exception.BlogException;
import com.bing.admin.model.*;
import com.bing.admin.utils.SqlSentence;
import com.bing.admin.utils.VeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("DlysServer")
public class DlysServer  extends TableServer{
    @Resource
    DlysCommonDao dlysCommonDao;

    //今日预警记录
    public listResultModel getVList(DlysListParamModel param){
        List<DlysListWhereModel> where = param.getWheres();
        DlysListWhereModel today = new DlysListWhereModel();
        today.setName("statusdate");
        today.setType("custom");
        today.setValue("left(statusdate,10) = (select max(left(statusdate,10)) from dlys_data_vehicle_redcode)");
        where.add(today);
        return this.getVehicleList(param,"dlys_data_vehicle_redcode");
    }

    //历史预警记录
    public listResultModel getVlLogist(DlysListParamModel param){
        List<DlysListWhereModel> where = param.getWheres();
        return this.getVehicleList(param,"dlys_data_vehicle_redcode");
    }

    public listResultModel getCargisnewList(DlysListParamModel param){
        return this.getVehicleList(param,"dlys_cargisnew");
    }

    /*
    * 获取广西省市县数据
    * */
    public List<City> getGxCityList(){
        return dlysCommonDao.getCityList();
    }

    /*
     * 预警企业top10
     * */
    public List<Map<String,String>> getYujingTop(){
        return dlysCommonDao.getYujingTop();
    }

    /*
     * 预警总数
     * */
    public Map<String,Double> getYujingTotal(){
        Map<String,Double> data = new HashMap<>();
        data.put("total",dlysCommonDao.getYujingTotal()) ;
        data.put("outDayTotal",dlysCommonDao.getOutDayYujingTotal()) ;
        return data;
    }

    /*
     * 预警七日内预警增幅趋势
     * */
    public List<Map<String,String>> getqtyjsl(){
        List<Map<String,String>> data = dlysCommonDao.getqtyjsl();
        return data;
    }

    public DlysDataVehicleRedcode getYjDatail(String id){
        return dlysCommonDao.getYjDatail(id);
    }

    public void insertMapCode(List<ZsMapCode> data){
        for (int i = 0; i < data.size(); i++) {
            Map<String,Object> item = new HashMap<>();
            ZsMapCode map = data.get(i);
            map.setChildrens(JSONObject.toJSONString(map.getChildren()));
            dlysCommonDao.insertMapCode(map);
        }
    }

    public ZsMapCode getMapCode(){
        return dlysCommonDao.getMapCode("45");
    }

    //预警处置反馈
    public void saveClueBack(ZsClueWay clue) throws BlogException {
        try{
            String dat = VeDate.getStringDate();
            String sql = "";
            if(clue.getId()==0){
                clue.setAddtime(dat);
                sql = SqlSentence.getInsertSqlNotId("zs_clue_way", ZsClueWay.class,clue);
                userCommonDao.insertSql(sql);
            }else{
                sql =SqlSentence.getUpdateSql("zs_clue_way",ZsClueWay.class,clue);
                userCommonDao.updateSql(sql);
            }
            System.out.println(sql);
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

    //预警核查记录
    public ZsClueInspect saveClueInspect(ZsClueInspect clue) throws BlogException {
        try{
            ZsClueInspect data = dlysCommonDao.getClueInspect(clue);
            String dat = VeDate.getStringDate();
            String sql = "";
            if(data==null){
                clue.setId(UUID.randomUUID().toString());
                data = dlysCommonDao.getClueInspectOne(clue);
                if(data==null){
                    clue.setAgoStatus("未核查");
                }else{
                    clue.setAgoStatus("已核查");
                }
                clue.setAddtime(dat);
                sql = SqlSentence.getInsertSql("zs_clue_inspect", ZsClueInspect.class,clue);
                userCommonDao.insertSql(sql);
            }else{
                data.setDisposeTime(dat);

                data.setRemark(data.getRemark()+clue.getRemark());

                sql =SqlSentence.getUpdateSql("zs_clue_inspect",ZsClueInspect.class,data);
                userCommonDao.updateSql(sql);
            }
            System.out.println(sql);
            return clue;
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

    public void setCityCode(List<Map<String,String>> data){
        for (int i = 0; i < data.size(); i++) {
            Map<String,String> item = data.get(i);
            dlysCommonDao.setCityCode(item.get("code"),item.get("name"),item.get("parentId"));
            System.out.println("第"+i+"条写入");
        }
    }

    public List<Map<String,String>> getYujingCityNumber(String parentId){
        if(parentId==null || "".equals(parentId)){
            parentId = "450000";
        }
        return dlysCommonDao.getYujingXiaquNumber(parentId);
    }
    //解除预警
    public void saveCehicleStatus(String id){
        dlysCommonDao.saveCehicleStatus(id,"无效");
    }

    public void saveClueEmphasis(ZsEmphasis clue) throws BlogException {
        try{
            String dat = VeDate.getStringDate();
            String sql = "";
            if(clue.getId()==0){
                clue.setAddtime(dat);
                sql = SqlSentence.getInsertSqlNotId("zs_emphasis", ZsEmphasis.class,clue);
                userCommonDao.insertSql(sql);
            }else{
                sql =SqlSentence.getUpdateSql("zs_emphasis",ZsEmphasis.class,clue);
                userCommonDao.updateSql(sql);
            }
            System.out.println(sql);
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }
    //用预警id获取重点名单记录，用以判断是否转入了重点监管名单
    public ZsEmphasis getClueEmphasis(long id,long type){
        return dlysCommonDao.getClueEmphasis(id,type);
    }

    public listResultModel getClueInspectList(DlysListParamModel param){
        param.setWheres(this.getWheres(param.getWhere()));
        return this.getVehicleList(param,"zs_clue_inspect");
    }

    //获取重点监管车辆清单
    public listResultModel getClueEmphasisCarList(DlysListParamModel param){
        param.setWheres(this.getWheres(param.getWhere()));
        return this.getVehicleList(param,"zs_emphasis_car");
    }

    //获取重点监管公司清单
    public listResultModel getClueEmphasisCompanyList(DlysListParamModel param){
        param.setWheres(this.getWheres(param.getWhere()));
        return this.getVehicleList(param,"zs_emphasis_company");
    }

    //获取白名单列表
    public listResultModel getWhiteList(DlysListParamModel param){
        param.setWheres(this.getWheres(param.getWhere()));
        return this.getVehicleList(param,"zs_whitelist");
    }

    //重点监管操作记录
    public ZsEmphasisLog saveEmphasisLog(ZsEmphasisLog clue) throws BlogException {
        try{
            String dat = VeDate.getStringDate();
            String sql = "";
            clue.setAddtime(dat);
            sql = SqlSentence.getInsertSqlNotId("zs_emphasis_log", ZsEmphasisLog.class,clue);
            userCommonDao.insertSql(sql);
            System.out.println(sql);
            return clue;
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

    //撤销重点监管 type:car,company,man
    public void revocationEmphasis(String id,String type,String remark,String token){
        try{
            ZsEmphasis data = JSON.parseObject(JSON.toJSONString(dlysCommonDao.loadFormData("zs_emphasis",id)), ZsEmphasis.class);
            switch (type){
                case "car":
                    data.setCarStatus(2);
                    break;
                case "company":
                    data.setCompanyStatus(2);
                    break;
                case "man":
                    data.setManStatus(2);
                    break;
            }
            String sql = "";
            sql = SqlSentence.getUpdateSql("zs_emphasis", ZsEmphasis.class,data);

            userCommonDao.updateSql(sql);
            ZsUser userInfoVO = this.tokenUser(token);
            ZsEmphasisLog datalog = new ZsEmphasisLog();
            datalog.setType(type);
            datalog.setEmphasisId(data.getId());
            datalog.setRemark(remark);
            datalog.setDepartmentId(userInfoVO.getDepartmentId());
            datalog.setUserDepartment(userInfoVO.getDepartment());
            datalog.setUserId(userInfoVO.getId());
            datalog.setUserName(userInfoVO.getName());
            datalog.setAddtime(VeDate.getStringDate());
            this.saveEmphasisLog(datalog);
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

    //获取监管操作日志列表
    public listResultModel getEmphasisLogList(DlysListParamModel param){
        param.setWheres(this.getWheres(param.getWhere()));
        return this.getVehicleList(param,"zs_emphasis_log");
    }

    public DlysTVehicle getCarDatail(String id){
        return dlysCommonDao.getCarDatail(id);
    }

    public DlysTOwner getOwnerDatail(String ownername){
        return dlysCommonDao.getOwnerDatail(ownername);
    }

    public DlysTRbrand getRbrandDatail(String id){
        return dlysCommonDao.getRbrandDatail(id);
    }

    public DlysDtmsWaybill getWaybillDatail(String id){
        return dlysCommonDao.getWaybillDatail(id);
    }

    public List<Map<String,String>> getBomList(){
        return dlysCommonDao.getBomList();
    }

    public List<Map<String,String>> getFlowBasic(){
        return dlysCommonDao.getFlowBasic();
    }
}
