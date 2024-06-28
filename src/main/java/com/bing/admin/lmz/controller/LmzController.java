package com.bing.admin.lmz.controller;

import com.bing.admin.annotation.PassToken;
import com.bing.admin.common.constant.Constant;
import com.bing.admin.common.domain.Result;
import com.bing.admin.lmz.model.*;
import com.bing.admin.lmz.server.CallBatServer;
import com.bing.admin.model.*;
import com.bing.admin.lmz.server.LmzServer;
import com.bing.admin.server.DlysServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.IIOException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping({"/lmz"})
@Api(value = "流程接口", tags = {"流程接口"})
public class LmzController {
    @Resource
    LmzServer lmzServer;
    @Resource
    CallBatServer callBatServer;

    @PassToken
    @GetMapping("getBomList")
    @ApiOperation("测试")
    public Result<List<Map<String,String>>> getBomList() {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getBomList());
        return result;
    }
    @PassToken
    @GetMapping("getFlowBasic")
    @ApiOperation("获取流程模板信息")
    public Result<List<Map<String,String>>> getFlowBasic() {
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getFlowBasic());
        return result;
    }
//    @PassToken
//    @GetMapping("setFlowMain")
//    @ApiOperation("setFlowMain")
//    public Result<String> setFlowMain() {
//        Result<String> result = new Result<>();
//        result.setCode(Constant.SUCCESS);
//        result.setData(lmzServer.setFlowMain());
//        return result;
//    }

    @PassToken
    @GetMapping("runbat")
    @ApiOperation("调用脚本")
    public Result<String> runbat() {
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        callBatServer.runbat();
        result.setData("ok");
        return result;
    }

    @PassToken
    @GetMapping("shutbat")
    @ApiOperation("停止脚本")
    public Result<String> shutbat() {
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        callBatServer.shutbat();
        result.setData("ok");
        return result;
    }

    @PassToken
    @GetMapping("getApiData")
    @ApiOperation("获取接口信息")
    public Result<List<List<String>>> getApiData() throws IOException {
        Result<List<List<String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getApiDatas());
        return result;
    }

    @PassToken
    @GetMapping("setLogin")
    @ApiOperation("写入登录信息")
    public Result<String> setLogin() throws IOException {
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.setLogin());
        return result;
    }
    @PassToken
    @PostMapping("flowSubmit")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("流程提交")
    public Result<List<FlowMain>> flowSubmit(@RequestBody FlowDataModel data){
        return lmzServer.flowSubmit(data);
    }
    @PassToken
    @PostMapping("flowUp")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("流程驳回")
    public Result<List<String>> flowUp(@RequestBody FlowDataModel data) throws Exception{
        Result<List<String>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.flowUp(data));
        return result;
    }

    @PassToken
    @GetMapping("getFlowData")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("获取流程数据")
    public Result<Map<String,String>> getFlowData(@RequestParam("oid") String oid) {
        Result<Map<String,String>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getFlowData(oid));
        return result;
    }

    @PassToken
    @GetMapping("deleteModel")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("删除模板节点")
    public Result<String> deleteModel(@RequestParam("id") String id,@RequestParam("type") String type) {
        Result<String> result = new Result<>();
        lmzServer.deleteModel(id,type);
        result.setCode(Constant.SUCCESS);
        result.setData("ok");
        return result;
    }
    @PassToken
    @PostMapping("getUpFlowOperationList")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("获取驳回流程节点信息")
    public Result<Map<String,List<FlowOperation>>> getUpFlowOperationList(@RequestBody FlowDataModel data){
        Result<Map<String,List<FlowOperation>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getUpFlowOperationList(data));
        return result;
    }
    @PassToken
    @PostMapping("deleteFlow")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("删除流程")
    public Result<String> deleteFlow(@RequestBody FlowDataModel data){
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        lmzServer.deleteFlow(data.getFlowId());
        result.setData("ok");
        return result;
    }

    @PassToken
    @GetMapping("getFlowOperation")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("获取流程节点信息")
    public Result<FlowOperation> getFlowOperation(@RequestParam("id") String id) {
        Result<FlowOperation> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getFlowOperation(id));
        return result;
    }

    @PassToken
    @PostMapping("getBgtYear")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("获取年份")
    public Result<String[]> getBgtYear(){
        Result<String[]> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getYearList());
        return result;
    }

    @PassToken
    @PostMapping("getBgtVersion")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("获取年份版本")
    public Result<List<Map<String,String>>> getBgtVersion(){
        Result<List<Map<String,String>>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getBgtVersion());
        return result;
    }

    @PassToken
    @PostMapping("getPushBasicList")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("获取年份版本")
    public Result<List<FlowPushBasic>> getFlowPushBasicList(){
        Result<List<FlowPushBasic>> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData(lmzServer.getFlowPushBasicList());
        return result;
    }
    @PassToken
    @PostMapping("setPushFlow")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("生成推送流程")
    public Result<String> setPushFlow(@RequestBody FlowPushBasic data)throws Exception{
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData("ok");
        lmzServer.setPushFlow(data);
        return result;
    }

    @PassToken
    @PostMapping("openPushNode")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("重新发起推送节点")
    public Result<String> openPushNode(@RequestBody FlowDataModel data)throws Exception{
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData("ok");
        lmzServer.openPushNode(data.getoId());
        return result;
    }
    @PassToken
    @PostMapping("setFlowError")
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ApiOperation("写入流程报错信息")
    public Result<String> setFlowError(@RequestBody FlowError data)throws Exception{
        Result<String> result = new Result<>();
        result.setCode(Constant.SUCCESS);
        result.setData("ok");
        lmzServer.setFlowError(data);
        return result;
    }
}
