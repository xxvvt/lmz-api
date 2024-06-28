package com.bing.admin.server;

import com.bing.admin.dao.PermissionCommonDao;
import com.bing.admin.dao.UserCommonDao;
import com.bing.admin.exception.BlogException;
import com.bing.admin.model.*;
import com.bing.admin.utils.SqlSentence;
import com.bing.admin.utils.VeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
@Service("PermissionServer")
public class PermissionServer extends TableServer{
    @Resource
    UserCommonDao userCommonDao;
    @Resource
    PermissionCommonDao permissionCommonDao;


    public  List<ZsPermission> buildDeptTreeByStream(List<ZsPermission> trees){
        //获取parentId = 0的根节点
        List<ZsPermission> list = trees.stream().filter(item -> item.getParentId() == "0").collect(Collectors.toList());
        //根据parentId进行分组
        Map<String, List<ZsPermission>> map = trees.stream().collect(Collectors.groupingBy(ZsPermission::getParentId));
        recursionFnTree(list, map);
        return list;
    }

    /**
     * 递归遍历节点
     * @param list
     * @param map
     */
    public  void recursionFnTree(List<ZsPermission> list, Map<String, List<ZsPermission>> map){
        for (ZsPermission ZsPermission : list) {
            List<ZsPermission> childList = map.get(ZsPermission.getId());
            ZsPermission.setChildren(childList);
            if (null != childList && 0 < childList.size()){
                recursionFnTree(childList,map);
            }
        }
    }

    /*
    获取菜单列表
     */
    public listResultModel getPermissionList(DlysListParamModel param) {
        String table = "zs_permission";
        String order = " sortNo asc ";
        param.setOrder(order);
        return  this.getVehicleList(param,table);
    }

    public void savePermission(ZsPermission permission) throws BlogException {
        try{
            String dat = VeDate.getStringDate();
            String sql = "";
            permission.setUpdateTime(dat);
            permission.setChildren(null);
            if(permission.getId()==null || "".equals(permission.getId())){
                permission.setId(UUID.randomUUID().toString());
                permission.setCreateTime(dat);
                 sql = SqlSentence.getInsertSql("zs_permission", ZsPermission.class,permission);
                userCommonDao.insertSql(sql);
            }else{
                 sql =SqlSentence.getUpdateSql("zs_permission",ZsPermission.class,permission);
                userCommonDao.updateSql(sql);
            }
            System.out.println(sql);
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

    public void deletePermission(ZsPermission permission)throws Exception{
        try{
            permissionCommonDao.deleteId("zs_permission",permission.getId());
        }catch (Exception e){
            System.out.println(e);
            throw new Exception(e);
        }
    }

    /*
    获取角色列表
     */
    public listResultModel getRoleList(DlysListParamModel param) {
        String table = "zs_role";
        String order = " id asc ";
        param.setOrder(order);
        return  this.getVehicleList(param,table);
    }
    //新增修改角色
    public void saveRole(ZsRole role) throws BlogException {
        try{
            String dat = VeDate.getStringDate();
            String sql = "";
            if(role.getId()==0L ){
                role.setAddtime(dat);
                sql = SqlSentence.getInsertSql("zs_role", ZsRole.class,role);
                userCommonDao.insertSql(sql);
            }else{
                sql =SqlSentence.getUpdateSql("zs_role",ZsRole.class,role);
                userCommonDao.updateSql(sql);
            }
            System.out.println(sql);
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }


    //删除角色
    public void deleteRole(ZsRole role)throws Exception{
        try{
            permissionCommonDao.deleteIdl("zs_role",role.getId());
        }catch (Exception e){
            System.out.println(e);
            throw new Exception(e);
        }
    }

    public listResultModel getRolePermissList(DlysListParamModel param) {
        param.setWheres(this.getWheres(param.getWhere()));
        String table = "zs_role_permission";
        String order = " id asc ";
        param.setOrder(order);
        return  this.getVehicleList(param,table);
    }

    //新增修改角色
    public void saveRolePermission(List<ZsRolePermiss> role) throws BlogException {
        try{
            permissionCommonDao.deleteRolePermiss(role.get(0).getRoleId());
            for (int i = 0; i < role.size(); i++) {
                ZsRolePermiss item = role.get(i);

                String sql = "";
                sql = SqlSentence.getInsertSqlNotId("zs_role_permission", ZsRolePermiss.class,item);
                 userCommonDao.insertSql(sql);
            }
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }
    public List<Map<String,String>> getRolePermissList(long roleId) {
        if(roleId==0L ){
            roleId =1;
        }
        return  permissionCommonDao.getPermissionRole(roleId);
    }



}
