package com.bing.admin.server;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.bing.admin.dao.UserCommonDao;
import com.bing.admin.exception.BlogException;
import com.bing.admin.model.*;
import com.bing.admin.utils.SqlSentence;
import com.bing.admin.utils.VeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("UserServer")
public class UserServer extends TableServer{
    @Resource
    UserCommonDao userCommonDao;



    /*
    * 获取用户列表
    * */
    public listResultModel getUserList(DlysListParamModel param) {
        if(param.getWhere()!=null)param.setWheres(this.getWheres(param.getWhere()));
        String table = "zs_user";
        String order = " registerTime desc ";
        param.setOrder(order);
        return  this.getVehicleList(param,table);
    }

    /*
    * 新增更新用户
    * */
    public String saveUser(ZsUser user){
        String dat = VeDate.getStringDate();
        user.setSaveTime(dat);
        if(user.getId()==null || "".equals(user.getId())){
            user.setId(UUID.randomUUID().toString());
            user.setWolt(this.getRandomString(6));
            user.setPassword(this.getMD5Str(user.getPassword()+user.getWolt()));
            user.setRegisterTime(dat);
            String sql =SqlSentence.getInsertSql("zs_user",ZsUser.class,user);
            userCommonDao.insertSql(sql);
        }else{

//            user.setPassword(tableServer.getMD5Str(user.getPassword()+user.getWolt()));
            String sql =SqlSentence.getUpdateSql("zs_user",ZsUser.class,user);
            userCommonDao.updateSql(sql);
        }
        return "ok";
    }

    public ZsUser findUserById(String id){
        return  userCommonDao.selectUserInfo(id);
    }

    //判断token是否过期
    public boolean tokenEnd(String token){
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endtime = JWT.decode(token).getAudience().get(2);
            Date end = sdf.parse(endtime);
            return  now.before(end);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }


    /*
     * 获取部门列表
     * */
    public listResultModel getDepartmentList(DlysListParamModel param) {
        String table = "zs_department";
        String order = " id asc ";
        param.setOrder(order);
        return  this.getVehicleList(param,table);
    }

    /*
     * 新增更新部门
     * */
    public String saveDepartment(ZsDepartment department){
        String dat = VeDate.getStringDate();
        department.setUptime(dat);
        if(department.getId()==null || "".equals(department.getId())){
            department.setId(UUID.randomUUID().toString());
            department.setAddtime(dat);
            String sql =SqlSentence.getInsertSql("zs_department",ZsDepartment.class,department);
            userCommonDao.insertSql(sql);
        }else{
            String sql =SqlSentence.getUpdateSql("zs_department",ZsDepartment.class,department);
            userCommonDao.updateSql(sql);
        }
        return "ok";
    }

    public void deleteDepartment(ZsDepartment department)throws Exception{
        try{
            userCommonDao.deleteId("zs_department",department.getId());
        }catch (Exception e){
            System.out.println(e);
            throw new Exception(e);
        }
    }

    //修改用户角色
    public void saveRoleUser(List<ZsUser> user){
        try{
            for (int i = 0; i < user.size(); i++) {
                ZsUser item = user.get(i);
                userCommonDao.updateUserRole(item.getRoleId(),item.getId());
            }
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

}
