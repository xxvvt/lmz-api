package com.bing.admin.controller;

import cn.hutool.json.JSONObject;
import com.bing.admin.annotation.PassToken;
import com.bing.admin.annotation.UserLoginToken;
import com.bing.admin.common.constant.Constant;
import com.bing.admin.common.domain.Result;
import com.bing.admin.constants.JwtConstants;
import com.bing.admin.model.ZsUser;
import com.bing.admin.modules.system.form.LoginForm;
import com.bing.admin.modules.system.vo.UserInfoVO;
import com.bing.admin.server.LoginServer;
import com.bing.admin.server.UserServer;
import com.bing.admin.utils.RedisUtils;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping({"/api"})
public class LoginController {

    @Resource
    LoginServer loginServer;
    @Resource
    UserServer userServer;

    @PassToken
    @PostMapping("login")
    public Result<Map<String,Object>> login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        return loginServer.login(loginForm);
    }


    @GetMapping("userInfo")
    public Result<ZsUser> userinfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("X-token");// 从 http 请求头中取出 token
        Result<ZsUser> result = new Result<>();
        ZsUser userInfoVO = loginServer.tokenUser(token);
        result.setCode(Constant.SUCCESS);
        result.setData(userInfoVO);
        return result;
    }
    @GetMapping("logout")
    @ApiOperation("用户注销登录")
    public Result<Object> logout(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("X-token");// 从 http 请求头中取出 token
        Result<Object> result = new Result<>();
        if(loginServer.logout(token)){
            result.setCode(Constant.SUCCESS);
            result.setMessage("退出登录成功");
        }else{
            result.setCode(Constant.FAIL);
            result.setMessage("退出登录失败");
        }
        return result;
    }


}
