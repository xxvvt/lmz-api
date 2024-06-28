package com.bing.admin.controller;

import com.alibaba.fastjson.JSON;
import com.bing.admin.annotation.PassToken;
import com.bing.admin.model.*;
import com.bing.admin.server.DlysServer;
import com.bing.admin.common.constant.Constant;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.bing.admin.common.domain.Result;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping({"/api"})
public class DlysController  {
    @Resource
    DlysServer dlysServer;

    @PostMapping("getVehicleList")
    @ApiOperation("获取预警信息列表")
    public Result <listResultModel> getList(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        dlys.setWheres(dlysServer.getWheres(dlys.getWhere())); ;
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getVList(dlys));
        return result;
    }

    @PostMapping("getVlLogist")
    @ApiOperation("获取历史预警列表")
    public Result <listResultModel> getVlLogist(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        dlys.setWheres(dlysServer.getWheres(dlys.getWhere())); ;
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getVlLogist(dlys));
        return result;
    }

    @GetMapping("getCity")
    @ApiOperation("获取城市列表")
    public Result <List<City>> getCity() {
        Result<List<City>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getGxCityList());
        return result;
    }
    @GetMapping("getYujingTop")
    @ApiOperation("获取预警企业TOP10")
    public Result<List<Map<String,String>>> getYujingTop() {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getYujingTop());
        return result;
    }

    @GetMapping("getYujingTotal")
    @ApiOperation("获取预警总数")
    public Result<Map<String,Double>> getYujingTotal() {
        Result<Map<String,Double>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getYujingTotal());
        return result;
    }

    @GetMapping("getqtyjsl")
    @ApiOperation("获取近七日预警数量")
    public Result<List<Map<String,String>>> getqtyjsl() {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getqtyjsl());
        return result;
    }
    @PostMapping("getYjDatail")
    @ApiOperation("获取预警数据")
    public Result<DlysDataVehicleRedcode> getYjDatail(@RequestBody Map<String,String> data) {
        Result<DlysDataVehicleRedcode> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getYjDatail(data.get("id")));
        return result;
    }
    @PassToken
    @PostMapping("test")
    @ApiOperation("测试")
    public Result<List<ZsMapCode>> test(@RequestBody List<ZsMapCode> data) {
        Result<List<ZsMapCode>> result = new Result<>();
//        dlysServer.insertMapCode(data);
        result.setCode(Constant.SUCCESS);
        result.setData(data);
        return result;
    }

    @GetMapping("getMapCode")
    @ApiOperation("获取广西地图code")
    public Result<ZsMapCode> getMapCode() {
        Result<ZsMapCode> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getMapCode());
        return result;
    }

    @PostMapping("saveClueBack")
    @ApiOperation("写入预警反馈")
    public Result<String> saveClueBack(@RequestBody ZsClueWay data) {
        Result<String> result = new Result<>();
        dlysServer.saveClueBack(data);
        result.setCode(Constant.SUCCESS);
        result.setData("ok");
        return result;
    }

    @PostMapping("saveClueInspect")
    @ApiOperation("写入预警线索核查记录")
    public Result<ZsClueInspect> saveClueInspect(@RequestBody ZsClueInspect data) {
        Result<ZsClueInspect> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.saveClueInspect(data));
        return result;
    }

    @PostMapping("setCityCode")
    @ApiOperation("写入预警线索核查记录")
    public Result<String> setCityCode(@RequestBody List<Map<String,String>> data) {
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        dlysServer.setCityCode(data);
        result.setData("ok");
        return result;
    }

    @GetMapping("getYujingCityNumber")
    @ApiOperation("获取下属辖区预警数量统计")
    public Result<List<Map<String,String>>> getYujingCityNumber(@RequestParam("parentId") String parentId) {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getYujingCityNumber(parentId));
        return result;
    }
    @GetMapping("saveCehicleStatus")
    @ApiOperation("解除预警")
    public Result<String> saveCehicleStatus(@RequestParam("id") String id) {
        Result<String> result = new Result<>();
        dlysServer.saveCehicleStatus(id);
        result.setCode(Constant.SUCCESS);
        result.setData("ok");
        return result;
    }

    @PostMapping("saveClueEmphasis")
    @ApiOperation("写入重点监管名单")
    public Result<String> saveClueEmphasis(@RequestBody ZsEmphasis data) {
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        dlysServer.saveClueEmphasis(data);
        result.setData("ok");
        return result;
    }

    @PostMapping("revocationEmphasis")
    @ApiOperation("重点监管名单撤销写入重点监管名单操作记录")
    public Result<String> revocationEmphasis(HttpServletRequest httpServletRequest,@RequestBody Map<String,String> data) {
        String token = httpServletRequest.getHeader("X-token");// 从 http 请求头中取出 token
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        dlysServer.revocationEmphasis(data.get("id"),data.get("type"),data.get("remark"),token);
        result.setData("ok");
        return result;
    }



    @GetMapping("getClueEmphasis")
    @ApiOperation("获取重点监督")
    public Result<ZsEmphasis> getClueEmphasis(@RequestParam("id") long id,@RequestParam("type") long type) {
        Result<ZsEmphasis> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getClueEmphasis(id,type));
        return result;
    }


    @PostMapping("getClueInspectList")
    @ApiOperation("获取预警线索核查日志列表")
    public Result <listResultModel> getClueInspectList(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getClueInspectList(dlys));
        return result;
    }

    @PostMapping("getClueEmphasisCarList")
    @ApiOperation("获取重点监管车辆清单")
    public Result <listResultModel> getClueEmphasisCarList(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getClueEmphasisCarList(dlys));
        return result;
    }
    @PostMapping("getClueEmphasisCompanyList")
    @ApiOperation("获取重点监管公司清单")
    public Result <listResultModel> getClueEmphasisCompanyList(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getClueEmphasisCompanyList(dlys));
        return result;
    }

    @PostMapping("getWhiteList")
    @ApiOperation("获取白名单列表")
    public Result <listResultModel> getWhiteList(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getWhiteList(dlys));
        return result;
    }


    @PostMapping("getEmphasisLogList")
    @ApiOperation("获取监管操作日志列表")
    public Result <listResultModel> getEmphasisLogList(@RequestBody DlysListParamModel dlys) {
        Result<listResultModel> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getEmphasisLogList(dlys));
        return result;
    }

    @GetMapping("getCarDatail")
    @ApiOperation("获取车辆信息")
    public Result<DlysTVehicle> getCarDatail(@RequestParam("id") String id) {
        Result<DlysTVehicle> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getCarDatail(id));
        return result;
    }

    @GetMapping("getOwnerDatail")
    @ApiOperation("获取所属企业信息")
    public Result<DlysTOwner> getOwnerDatail(@RequestParam("ownername") String ownername) {
        Result<DlysTOwner> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getOwnerDatail(ownername));
        return result;
    }
    @GetMapping("getRbrandDatail")
    @ApiOperation("车牌号获取包车信息")
    public Result<DlysTRbrand> getRbrandDatail(@RequestParam("id") String id) {
        Result<DlysTRbrand> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getRbrandDatail(id));
        return result;
    }
    @GetMapping("getWaybillDatail")
    @ApiOperation("车牌号获取危货信息")
    public Result<DlysDtmsWaybill> getWaybillDatail(@RequestParam("id") String id) {
        Result<DlysDtmsWaybill> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getWaybillDatail(id));
        return result;
    }

    @PassToken
    @GetMapping("getBomList")
    @ApiOperation("测试")
    public Result<List<Map<String,String>>> getBomList() {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getBomList());
        return result;
    }
    @PassToken
    @GetMapping("getFlowBasic")
    @ApiOperation("getFlowBasic")
    public Result<List<Map<String,String>>> getFlowBasic() {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(dlysServer.getFlowBasic());
        return result;
    }
}
