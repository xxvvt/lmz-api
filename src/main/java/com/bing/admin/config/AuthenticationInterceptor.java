package com.bing.admin.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bing.admin.annotation.PassToken;
import com.bing.admin.annotation.UserLoginToken;
import com.bing.admin.exception.BlogException;
import com.bing.admin.model.ErrorInfoEnum;
import com.bing.admin.model.ZsUser;
import com.bing.admin.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import  com.bing.admin.server.UserServer;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    UserServer userServer;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws BlogException {
        String token = httpServletRequest.getHeader("X-token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();

        return true;
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//
//        //检查有没有需要用户权限的注解------注释掉设置成没有passToken的都进判断
////        if (method.isAnnotationPresent(UserLoginToken.class)) {
////            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
////            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    throw new BlogException(ErrorInfoEnum.TOKEN_INVALID);
//                }
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                } catch (JWTDecodeException j) {
//                    throw new BlogException(ErrorInfoEnum.NOT_LOGIN);
//                }
//                ZsUser user = userServer.findUserById(userId);
//                if (user == null) {
//                    throw new BlogException(ErrorInfoEnum.INVALID_ID);
//                }
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new BlogException(ErrorInfoEnum.NOT_LOGIN);
//                }
//                if(!redisUtils.hasKey(token)){
//                    throw new BlogException(ErrorInfoEnum.TOKEN_EXPIRED);
//                }else{
//                    redisUtils.expire(token,7200);
//                }
//                return true;
////            }
//        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}