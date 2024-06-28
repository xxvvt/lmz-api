package com.bing.admin.lmz.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bing.admin.common.constant.Constant;
import com.bing.admin.common.domain.Result;
import com.bing.admin.dao.DlysCommonDao;
import com.bing.admin.exception.BlogException;
import com.bing.admin.lmz.dao.LmzDao;
import com.bing.admin.lmz.model.*;
import com.bing.admin.model.*;
import com.bing.admin.server.TableServer;
import com.google.gson.Gson;

import com.bing.admin.utils.SqlSentence;
import com.bing.admin.utils.VeDate;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import com.bing.admin.utils.HttpUtil;
import javax.annotation.Resource;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import com.bing.admin.utils.*;
import org.springframework.transaction.annotation.Transactional;

@Service("LmzServer")
public class LmzServer extends TableServer {
    @Resource
    LmzDao lmzDao;

    public List<Map<String,String>> getBomList(){
        return lmzDao.getBomList();
    }

    public List<Map<String,String>> getFlowBasic(){
        return lmzDao.getFlowBasic();
    }




    public Map<String, Object> getApiData() throws IOException {
        ApiLoginModel login = lmzDao.getApiLoginData();
        String url = "https://lmzjt.ik3cloud.com/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.GetSysReportData.common.kdsvc";
//        Map<String,String> map = new HashMap<>();
        File file = new File("E:\\work\\lmz-api\\params.json");
        String params = readerMethod(file);
        HashMap<String, String> headers = new HashMap<>(3);
        headers.put("content-type", "application/json");
        headers.put("ASP.NET_SessionId",login.getSessionid());
        headers.put("kdservice-sessionid",login.getKdsvcsessionid());
        // 发送post请求
        String resultData = HttpUtil.sendPostWithJson(url, params,headers);
        JSONObject resJson =  JSONObject.parseObject(resultData);
        Map<String, Object> map = (Map<String, Object>)resJson;
        return map;
    }

    private  String readerMethod(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString();
//        System.out.println(JSON.parseObject(jsonStr));
        return jsonStr;
    }


    public String getLoginInfo() throws IOException {
        File file = new File("E:\\work\\lmz-api\\login.json");
        String params = readerMethod(file);

        return params;
    }

    public String setLogin() throws IOException {
        String requestUrl = "https://lmzjt.ik3cloud.com/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";

        HashMap<String, String> headers = new HashMap<>(3);
        String json = getLoginInfo();
        headers.put("content-type", "application/json");
        // 发送post请求
        String resultData = HttpUtil.sendPostWithJson(requestUrl, json,headers);
        // 并接收返回结果
        System.out.println(resultData);
        return resultData;
    }
    public  List<List<String>> getApiDatas() throws IOException {

        File file = new File("E:\\work\\lmz-api\\data.json");
        String params = readerMethod(file);

        String result1 = params.replaceAll("\\\\", "");
        String result2 = result1.replaceAll(Matcher.quoteReplacement("$"), "");
        // 并接收返回结果
//        JSONObject resJson =  JSONObject.parseObject(params);
//        List<Map<String, Object>> map = (List<Map<String, Object>>)resJson;
//        Object data = map.get("Result");
        // 将 Map 转换为 实体类
        Gson gson = new Gson();
//        BdSalOutStockDetailRptNew2 data = JSON.parseObject(JSON.toJSONString(map), BdSalOutStockDetailRptNew2.class);
//        List<BdSalOutStockDetailRptNew2> gameListFromJson = gson.fromJson(result2, new TypeToken<List<BdSalOutStockDetailRptNew2>>() {}.getType());
        String fidle = "FBILLNAME,FBILLTYPENAME,FBILLNUMBER,FBILLDATE,FApproveDate,FSALEORGNAME,FSettleOrgName,FDEPTNUMBER,FDEPTNAME,FSALERNUMBER,FSALERNAME,FSalGroupName,FSETTLETYPENAME,FCUSTOMERNUMBER,FCUSTOMERID,FCustGroupName,FMATERIALNUMBER,FMATERIALID,FMAPID,FMAPNAME,FMATERIALGROUP,FERPCLSID,FCATEGORYID,FMATERIALMODEL,FMATERIALAUXID,FSTOCKID,FStockLocId,FLOTNAME,FSOORDERNO,FUNITNAME,FCURRENCYNAME,FLCCURRENCYNAME,FOUTSTOCKQTY,FLCNOTAXOUTSTOCKPRICE,FLCTAXOUTSTOCKPRICE,FLCTAXNETPRICE,FLCNOTAXOUTSTOCKAMOUNT,FLCTAXOUTSTOCKAMOUNT,FCOSTPRICE,FLCOUTSTOCKTATALCOSTAMOUNT,FRECQTY,FLCNOTAXRECAMOUNT,FLCTAXRECAMOUNT,FNOTAXOUTSTOCKPRICE,FTAXOUTSTOCKPRICE,FTAXNETPRICE,FTAXRECAMOUNT,FNOTAXRECAMOUNT,FNOTAXOUTSTOCKAMOUNT,FSALCOSTPRICE,FOUTSTOCKTATALCOSTAMOUNT,FTAXOUTSTOCKAMOUNT,FBaseUnitName,FDeliveryQty,FReceiveQty,FSALEPRICEUNITNAME,FOUTSTOCKQTY_PRICE,FRECQTY_PRICE,FEXTAUXUNITID,FOUTSTOCKQTY_EXTAUX,FRECQTY_EXTAUX,FISFREE,FMTONO,FPRESETBASENAME1,FPRESETBASENAME2,FPRESETASSISTANTNAME1,FPRESETASSISTANTNAME2,F_TPS_DECIMAL,F_TPS_AMOUNT2,F_TPS_AMOUNT3,F_PAEZ_AMOUNT1,F_TPS_AMOUNT1,FALLAMOUNT,F_HZJ_FALLAMOUNT,FCREATEDATE,F_KHSQ,F_KHQY,FNOTE";
        String[] split = fidle.split(",");
        List<List<String>> list = gson.fromJson(result2, new TypeToken<List<List<String>>>(){}.getType());
        for (int i = 16; i < split.length; i++) {
            String flid = split[i];
            System.out.println("字段："+flid);
            for (int i1 = 0; i1 < list.size(); i1++) {
                List<String> item = list.get(i1);
                String value = item.get(i);
                lmzDao.insertNew2BatchToFlid(flid,value);
                if(i1%1000==0){
                    System.out.println("第"+i1+"条数据");
                }
            }
        }


        return list;
    }
    //流程提交
    @Transactional
    public Result<List<FlowMain>> flowSubmit(FlowDataModel data){
        Result<List<FlowMain>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        try{
            //获取当前时间String
            String nowDate = DateTimeUtils.now();
            //获取流程主信息
            FlowMain flowMain = lmzDao.getFlowMain(data.getFlowId());
            //获取当前节点
            FlowOperation operation = lmzDao.getFlowOperation(data.getoId());
            //#region 写入审批记录
            //写入审批记录
            FlowOperactionAuditLog log = new FlowOperactionAuditLog();
            log.setFlowId(data.getFlowId());
            log.setOid(data.getoId());
            log.setId(UUID.randomUUID().toString());
            log.setCreateBy(data.getSubmitUser());
            log.setNodeId(data.getNodeId());
            log.setRemark(data.getText());
            log.setTitle(operation.getTitle());
            log.setCreateTime(Timestamp.valueOf(nowDate));
            log.setType("1");
            String logsql = SqlSentence.getInsertSql("flow_operaction_audit_log", FlowOperactionAuditLog.class, log);
            lmzDao.insertSql(logsql);
            //#endregion

            //更新当前节点状态 为已完成
            lmzDao.updateFlowOperationStatus(data.getoId(),"2");
            //推送流程的提交
            if(flowMain.getType()==4){

                //寻找同级节点是否全部完成
                List<FlowOperation> sameFlowOperaction = lmzDao.getFlowPushTJNode(data.getFlowId(), data.getoId(), data.getNodeId());
                if(sameFlowOperaction.size()==0){
                    //获取当前节点的下一个节点
                    List<FlowOperation> nextOperaction =  lmzDao.getFlowPushXJNode(data.getFlowId(), data.getNodeId());
                    if (nextOperaction.size()>0){
                        //更新当前流程状态为进行中
                        for (int i = 0; i < nextOperaction.size(); i++) {
                            FlowOperation item = nextOperaction.get(i);
                            lmzDao.updateFlowOperationStatus(item.getId(),"1");
                        }
                    }else {
                        //更新当前流程状态为已完成
                        lmzDao.updateFlowMainStatus(data.getFlowId(), "2");
                    }
                }

                result.setCode(Constant.SUCCESS);
                result.setData(new ArrayList<>());
                return result;
            }
//            result.setCode(Constant.FLOWEND);
            //获取下一个节点
            List<FlowOperation> nextFlowOperaction = lmzDao.getNextFlowOperaction(data.getFlowId(), data.getNodeId());


            List<FlowMain> nextFlowMain  = new ArrayList<>();
            if(nextFlowOperaction.size()>0){
                //生成子节点
                for (int i = 0; i < nextFlowOperaction.size(); i++) {
                    FlowOperation item = nextFlowOperaction.get(i);
                    //寻找已生成过的节点
                    FlowOperation oldItem = lmzDao.getFlowOperationToNode(data.getFlowId(), item.getNodeId());
                    if (oldItem != null) {
                        //更新当前节点状态 为未完成
                        lmzDao.updateFlowOperationStatus(oldItem.getId(), "1");
                    }else{
                        item.setFlowId(data.getFlowId());
                        item.setUpModel(operation.getNodeId());
                        item.setCreateBy(data.getSubmitUser());
                        item.setCreateTime(nowDate);
                        item.setId(UUID.randomUUID().toString());
                        item.setStatus(1);
                        String sql = SqlSentence.getInsertSql("flow_operation", FlowOperation.class, item);
                        lmzDao.insertSql(sql);
                    }
                }
            }else{
                //没有下一个节点
                //获取未结束的同级节点
                List<FlowOperation> sameFlowOperaction = lmzDao.getFlowONoClossListByFlowId(data.getFlowId(), data.getoId());
                if(sameFlowOperaction.size()==0) {
                    //更新当前流程状态为已完成
                    lmzDao.updateFlowMainStatus(data.getFlowId(), "2");
                    result.setCode(Constant.FLOWEND);
                    result.setMessage("流程已结束，修改当前流程状态为已完成");
                    if(flowMain.getType()==3){//流程类型为3的时候，查找是否有下个流程
                        nextFlowMain = lmzDao.getNextFlowMain(data.getFlowId());
                        if(nextFlowMain.size()>0){
                            long maxNo = lmzDao.getMaxFlowNo(data.getFlowId());
                            //生成下个流程
                            for (int i = 0; i < nextFlowMain.size(); i++) {
                                maxNo++;
                                FlowMain item = nextFlowMain.get(i);
                                item.setCreateBy(data.getSubmitUser());
                                item.setCreateTime(nowDate);
                                item.setId(UUID.randomUUID().toString());
                                item.setParentId(flowMain.getParentId());
                                item.setStatus("1");
                                item.setType(3);
                                item.setFlowNo(Long.toString(maxNo));
                                item.setBgtType(flowMain.getBgtType());
                                item.setBgtYear(flowMain.getBgtYear());
                                item.setVersionId(flowMain.getVersionId());
                                String sql = SqlSentence.getInsertSqlT("flow_main", FlowMain.class, item);
                                lmzDao.insertSqls(sql,item);
                                List<FlowOperation> nextFlowOper = lmzDao.getFlowModel(item.getBasicId());
                                //生成子节点
                                for (int i1 = 0; i1 < nextFlowOper.size(); i1++) {
                                    FlowOperation item1 = nextFlowOper.get(i1);
                                    item1.setFlowId(item.getId());
                                    item1.setCreateBy(data.getSubmitUser());
                                    item1.setCreateTime(nowDate);
                                    item1.setStatus(1);
                                    item1.setId(UUID.randomUUID().toString());
                                    String sql1 = SqlSentence.getInsertSql("flow_operation", FlowOperation.class, item1);
                                    lmzDao.insertSql(sql1);
                                }
                            }
                        }else{//没有下一个流程
                            //查找是否有未结束的同级流程
                            List<FlowMain> sameFlowMain = lmzDao.getFlowMNoClossListByFlowId(data.getFlowId(),data.getFlowId());
                            if(sameFlowMain.size()==0){
                                //更新父流程状态为已完成
                                lmzDao.updateFlowMainStatus(flowMain.getParentId(),"2");
                                result.setCode(Constant.FLOWEND);
                            }
                        }
                    }
                }
            }
            result.setData(nextFlowMain);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setCode(Constant.FAIL);
        return result;
    }



    //流程驳回
    public List<String> flowUp(FlowDataModel data)throws Exception{
        try{
            //获取流程主信息
            FlowMain flowMain = lmzDao.getFlowMain(data.getFlowId());
            //获取当前节点
            FlowOperation operation = lmzDao.getFlowOperation(data.getoId());
            //获取当前时间String
            String nowDate = DateTimeUtils.now();
            StringBuilder remark = new StringBuilder("驳回了节点：");
            //判断是否是推送流程
            if(flowMain.getType()==4){
                remark = new StringBuilder(data.getText());
                //更新当前节点状态 为驳回
                lmzDao.updateFlowOperationStatus(data.getoId(),"3");
                //写入审批记录
                FlowOperactionAuditLog log = new FlowOperactionAuditLog();
                log.setFlowId(data.getFlowId());
                log.setOid(data.getoId());
                log.setId(UUID.randomUUID().toString());
                log.setCreateBy(data.getSubmitUser());
                log.setCreateTime(Timestamp.valueOf(nowDate));
                log.setNodeId(data.getNodeId());
                log.setRemark(data.getText());
                log.setTitle(operation.getTitle());
                log.setType("2");
                log.setRemark(remark.toString());
                String logsql = SqlSentence.getInsertSql("flow_operaction_audit_log", FlowOperactionAuditLog.class, log);
                lmzDao.insertSql(logsql);
                List<String> upFlowOperaction = new ArrayList<>();
                upFlowOperaction.add(data.getoId());
                return upFlowOperaction;
            }
            //获取指定的驳回节点
            List<String> upFlowOperaction = data.getBhList();
            if(upFlowOperaction.size()==0){
                throw new Exception("驳回节点不能为空");
            }
            if(upFlowOperaction.size()>0) {
                //修改前节点的状态为未完成
                for (int i = 0; i < upFlowOperaction.size(); i++) {
                    lmzDao.updateFlowOperationStatus(upFlowOperaction.get(i),"5");
                    //获取驳回节点
                    FlowOperation node = lmzDao.getFlowOperation(upFlowOperaction.get(i));
                    remark.append(node.getTitle()).append(",");
                }
                remark.append("备注：").append(data.getText());
                //更新当前节点状态 为驳回
                lmzDao.updateFlowOperationStatus(data.getoId(),"3");
                //#region 写入审批记录
                //写入审批记录
                FlowOperactionAuditLog log = new FlowOperactionAuditLog();
                log.setFlowId(data.getFlowId());
                log.setOid(data.getoId());
                log.setId(UUID.randomUUID().toString());
                log.setCreateBy(data.getSubmitUser());
                log.setCreateTime(Timestamp.valueOf(nowDate));
                log.setNodeId(data.getNodeId());
                log.setRemark(data.getText());
                log.setTitle(operation.getTitle());
                log.setType("2");
                log.setRemark(remark.toString());
                String logsql = SqlSentence.getInsertSql("flow_operaction_audit_log", FlowOperactionAuditLog.class, log);
                lmzDao.insertSql(logsql);
                //#endregion
            }
            return upFlowOperaction;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    public Map<String,String> getFlowData(String oid){
        return lmzDao.getFlowData(oid);
    }

    //获取上级节点列表
    public List<String> getUpFlowOperation(String flowId,String nodeId){
        return lmzDao.getUpFlowOperation(flowId,nodeId);
    }

    //删除节点
    public void deleteModel(String id,String type){
        if(type.equals("1")){
            lmzDao.deleteFlowModel(id);
        }else if(type.equals("2")){
            lmzDao.deleteFlowModelCorrelation(id);
        }
    }

    //获取上级节点列表详细
    public Map<String,List<FlowOperation>> getUpFlowOperationList(FlowDataModel data){
        Map<String,List<FlowOperation>> map = new HashMap<>();
        //获取开始节点
        List<FlowOperation> list = this.getFlowStartOperation(data);
        map.put("start",list);
        //获取上级节点
        List<String> ids = lmzDao.getUpFlowOperation(data.getFlowId(),data.getNodeId());
        List<FlowOperation> list1 = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
                FlowOperation item = lmzDao.getFlowOperation(ids.get(i));
                list1.add(item);
        }
        map.put("up",list1);
        return map;
    }
    //删除流程
    public void deleteFlow(String flowId){

        lmzDao.updateFlowMainStatus(flowId,"4");
    }

    public FlowOperation getFlowOperation(String id){
        FlowOperation operation = lmzDao.getFlowOperation(id);

        return operation;
    }

    //从当前节点寻找到开始节点
    public List<FlowOperation> getFlowStartOperation(FlowDataModel data){
        //一级级寻找上级节点
        List<FlowOperation> list = new ArrayList<>();
        List<FlowOperation> basic = new ArrayList<>();
        List<String> ids = lmzDao.getUpFlowOperation(data.getFlowId(),data.getNodeId());
        for (int i = 0; i < ids.size(); i++) {
            FlowOperation item = lmzDao.getFlowOperation(ids.get(i));
            basic.add(item);
        }
        getFlowStartOperation(basic,list);
        return list;
    }

    //递归获取上级节点
    public void getFlowStartOperation(List<FlowOperation> basic,List<FlowOperation> list){
        //一级级寻找上级节点
        List<FlowOperation> upData = new ArrayList<>();
        List<FlowOperation> startData = new ArrayList<>();
        for (FlowOperation operation : basic) {
            List<String> ids = lmzDao.getUpFlowOperation(operation.getFlowId(),operation.getNodeId());
            for (int i = 0; i < ids.size(); i++) {
                FlowOperation item = lmzDao.getFlowOperation(ids.get(i));
                if(item.getUpModel().equals("1")){//开始节点
                    startData.add(item);
                }else {
                    upData.add(item);//还有上级节点
                }
            }
        }
        if(startData.size()>0) {
            list.addAll(startData);
        }
        if (upData.size()>0){
            getFlowStartOperation(upData,list);
        }
    }

    //获取年份列表
    public String[] getYearList(){
        return lmzDao.getBgtYear();
    }
    //通过年份获取版本列表
    public List<Map<String,String>> getBgtVersion(){
        return lmzDao.getBgtVersionByYear();
    }

    public List<Map<String,String>> getNodeParams(String nodeId){
        List<Map<String,String>> list = lmzDao.getNodeParams(nodeId);
        List<Map<String,String>> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String,String> item = list.get(i);
            if(item.get("type").equals("1")) {
                Map<String, String> item1 = new HashMap<>();
                item1.put(item.get("name"), item.get("value_formula"));
                list1.add(item1);
            }
        }
        return list1;
    }

    //获取推送模板
    public List<FlowPushBasic> getFlowPushBasicList(){
        return lmzDao.getFlowPushBasicList();
    }


    /*
    *@description 生成推送流程
    *@param pBasic 推送模板
    *实际需要字段 id,createBy,bgtType,bgtYear,versionId
    * */
    public void setPushFlow(FlowPushBasic pBasic)throws Exception{
        try{
            FlowPushBasic basic = lmzDao.getFlowPushBasic(pBasic.getId());
            FlowMain main = new FlowMain();
            main.setId(UUID.randomUUID().toString());
            main.setBasicId(basic.getId());
            main.setCreateTime(DateTimeUtils.now());
            main.setCreateBy(pBasic.getCreateBy());
            main.setTitle(basic.getTitle());
            main.setStatus("1");
            main.setType(4);
            //获取流水号 暂时固定写死001 到秒应该不会有重复的吧
            main.setFlowNo(getFlowNo());
            main.setBgtType(pBasic.getBgtType());
            main.setBgtYear(pBasic.getBgtYear());
            main.setVersionId(pBasic.getVersionId());
            String sql = SqlSentence.getInsertSqlT("flow_main", FlowMain.class, main);
            lmzDao.insertSqls(sql,main);
            List<FlowPushNode> node = lmzDao.getFlowPushNodeList(main.getBasicId());
            //生成子节点
            for (int i = 0; i < node.size(); i++) {
                FlowPushNode item = node.get(i);
                //拆解人员字段，多少个人就多少个节点
                String[] users = item.getUsers().split(",");
                for (int i1 = 0; i1 < users.length; i1++) {
                    FlowOperation operation = new FlowOperation();
                    operation.setFlowId(main.getId());
                    operation.setCreateBy(pBasic.getCreateBy());
                    operation.setCreateTime(DateTimeUtils.now());
                    if (i==0) {//第一个节点为开始节点
                        operation.setStatus(1);
                    }else{
                        operation.setStatus(0);
                    }
                    operation.setId(UUID.randomUUID().toString());
                    operation.setNodeId(item.getId());
                    operation.setTitle(item.getTitle());
                    operation.setUserName(users[i1]);
                    operation.setNodeType("4");
                    operation.setBasicId(main.getBasicId());
                    operation.setUpModel("1");
                    operation.setType(3);
                    operation.setNode(item.getTitle());
                    String sql1 = SqlSentence.getInsertSql("flow_operation", FlowOperation.class, operation);
                    lmzDao.insertSql(sql1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("系统错误，请求失败！");
        }
    }

    public String getFlowNo(){
        String time = VeDate.getStringDate();
        //去掉time头两位并把-转换成空格
        String flowId = time.substring(2).replaceAll("-","").replaceAll(" ","").replaceAll(":","")+"001";
        return flowId;
    }

    //重新发起推送节点
    public void openPushNode(String oid)throws Exception{
        try{
            System.out.println(oid);
            //获取当前节点
            FlowOperation operation = lmzDao.getFlowOperation(oid);
            if (operation == null) throw new Exception("当前节点不存在");
            if (operation.getStatus()!=3) throw new Exception("当前节点状态不可重新发起");
            //获取当前时间String
            String nowDate = DateTimeUtils.now();
            //更新当前节点状态 为进行中
            lmzDao.updateFlowOperationStatusTime(oid,"1",nowDate);
            //写入审批记录
            FlowOperactionAuditLog log = new FlowOperactionAuditLog();
            log.setFlowId(operation.getFlowId());
            log.setOid(oid);
            log.setId(UUID.randomUUID().toString());
            log.setCreateBy(operation.getCreateBy());
            log.setCreateTime(Timestamp.valueOf(nowDate));
            log.setNodeId(operation.getNodeId());
            log.setRemark("重新发起");
            log.setTitle(operation.getTitle());
            log.setType("1");
            String logsql = SqlSentence.getInsertSql("flow_operaction_audit_log", FlowOperactionAuditLog.class, log);
            lmzDao.insertSql(logsql);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    //写入流程运行报错
    public void setFlowError(FlowError error){
        lmzDao.insertFlowError(error);
    }
}
