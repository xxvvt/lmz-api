package com.bing.admin.server;

import cn.hutool.json.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.bing.admin.common.constant.Constant;
import com.bing.admin.common.domain.Result;
import com.bing.admin.dao.DlysCommonDao;
import com.bing.admin.dao.UserCommonDao;
import com.bing.admin.model.DlysListParamModel;
import com.bing.admin.model.DlysListWhereModel;
import com.bing.admin.model.ZsUser;
import com.bing.admin.model.listResultModel;
import com.bing.admin.modules.system.form.LoginForm;
import com.bing.admin.utils.RedisUtils;
import com.bing.admin.utils.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("LoginServer")
public class LoginServer extends TableServer{
    @Resource
    UserCommonDao userCommonDao;


    @Autowired
    private RedisUtils redisUtils;

    public Result<Map<String, Object>> login(LoginForm loginForm){
        Map<String, Object> data = new HashMap<>(1);
        Result<Map<String, Object>> result = new Result<>();
        String token = null;
        ZsUser user =  userCommonDao.selectUser(loginForm.getUsername());
        if(user!=null) {
            String pass = this.getMD5Str(loginForm.getPassword() + user.getWolt());
            if (user.getPassword().equals(pass)) {
                token = this.getToken(user);
                redisUtils.addToRedis(token,token,7200);

                if (token != null) {
                    String dat = VeDate.getStringDate();
                    userCommonDao.updateUserLoginTime(dat,user.getId());
                    result.setCode(Constant.SUCCESS);
                    result.setMessage("登录成功");
                    data.put("token", token);
                } else {
                    result.setCode(Constant.FAIL);
                    result.setMessage("系统错误");
                }
                result.setData(data);
                return result;
            }
        }
        result.setCode(Constant.FAIL);
        result.setMessage("用户名或密码错误");

        return result;
    }



    public Boolean logout(String token){
        if(redisUtils.hasKey(token)){
            return redisUtils.delete(token);
        }
        return true;
    }

}
