package com.bing.admin.server;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.bing.admin.dao.UserCommonDao;
import com.bing.admin.model.DlysListParamModel;
import com.bing.admin.model.DlysListWhereModel;
import com.bing.admin.model.ZsUser;
import com.bing.admin.model.listResultModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class TableServer {
    @Resource
    UserCommonDao userCommonDao;

    private List<Map<String, Object>> getList(String fields,String table,String wheres,String limit,String order){
        System.out.println("select "+fields+" from "+table+" where 1 = 1 "+wheres+" "+order+" "+limit);
        List<Map<String, Object>> list = userCommonDao.selectPaingList(fields,table,wheres,limit,order);
        return list;
    }

    public listResultModel getVehicleList(DlysListParamModel param,String table){
        String limit = "";
        if(param.getOffset()!=null){
            limit +=" limit "+param.getOffset();
            if(param.getLimit()!=null){
                limit +=","+param.getLimit();
            }
        }
        String fields = "*";
        String where = "";
        if (param.getWhere() != null) {
            where = getStructureWhere(param.getWheres());
        }
        String order = "";
        if(param.getOrder()!=null){
            order = " ORDER BY "+param.getOrder();
        }
        List<Map<String, Object>> list = getList(fields,table,where,limit,order);
        listResultModel data = new listResultModel();
        data.setList(list);
        data.setOffset(param.getOffset());
        data.setLimit(param.getLimit());
        data.setTotal(userCommonDao.selectCountList(table,where));
        return data;
    }

    /*
    * 构造where条件
    * @param List<DlysListWhereModel> where 条件json数据
    * [{
    *   name:"", 字段名称
    *   type:"", 查询类型
    *   value:"" 值
    * }]
    * */
    private String getStructureWhere(List<DlysListWhereModel> where){
        String toWhere = "";
        for (int i = 0; i < where.size(); i++) {
            DlysListWhereModel item = where.get(i);
            toWhere+=" and ";
            if("like".equals(item.getType())){
                toWhere+="`"+item.getName()+"` "+item.getType()+" '%"+item.getValue()+"%'";
            }else if("in".equals(item.getType())){
                toWhere+="`"+item.getName()+"` "+item.getType()+" ("+item.getValue()+")";
            }else if("custom".equals(item.getType())){
                toWhere+=item.getValue();
            }else{
                toWhere+="`"+item.getName()+"` "+item.getType()+" '"+item.getValue()+"'";
            }
        }
        return toWhere;
    }

    //length用户要求产生字符串的长度
    public  String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

        public  String getMD5Str(String str) {
            byte[] digest = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("md5");
                digest  = md5.digest(str.getBytes("utf-8"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //16是表示转换为16进制数
            String md5Str = new BigInteger(1, digest).toString(16);
            return md5Str;
        }

        //拆解where
        public List<DlysListWhereModel> getWheres(List<Map<String,String>> where){
            List<DlysListWhereModel> wheres = new ArrayList<>();
            for (int i = 0; i < where.size(); i++) {
                Map<String,String> item = where.get(i);
                if(item.get("value")!=null){
                    DlysListWhereModel tets = JSON.parseObject(JSON.toJSONString(item), DlysListWhereModel.class);
                    wheres.add(tets);
                }
            }
            return wheres;
        }

    public String getToken(ZsUser user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000*2;//2小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        String tokenKey = "sys:user:token" + token;

        token = JWT.create().withAudience(user.getId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public ZsUser findUserById(String id){
        return  userCommonDao.selectUserInfo(id);
    }

    public ZsUser tokenUser(String token){
        String userid = this.tokenGetUserId(token);
        ZsUser userInfoVO = this.findUserById(userid);
        return userInfoVO;
    }

    public String tokenGetUserId(String token){
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        return userId;
    }

}
