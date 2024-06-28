package com.bing.admin.server;


import com.bing.admin.dao.GameCommonDao;
import com.bing.admin.exception.BlogException;
import com.bing.admin.model.DlysListParamModel;
import com.bing.admin.model.ErrorInfoEnum;
import com.bing.admin.model.GNpc;
import com.bing.admin.model.listResultModel;
import com.bing.admin.utils.SqlSentence;

import com.bing.admin.utils.VeDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("GameServer")
public class GameServer extends TableServer {
    @Resource
    GameCommonDao gameCommonDao;

    public listResultModel getNpcList(DlysListParamModel param){
        param.setWheres(this.getWheres(param.getWhere()));
        return this.getVehicleList(param,"g_npc");
    }

    public void saveNpc(GNpc npc) throws BlogException {
        try{
            String sql = "";
            String dat = VeDate.getStringDate();
            if(npc.getId()==0L){
                npc.setAddtime(dat);
                sql = SqlSentence.getInsertSqlNotId("g_npc", GNpc.class,npc);
                gameCommonDao.insertSql(sql);
            }else{
                sql =SqlSentence.getUpdateSql("g_npc",GNpc.class,npc);
                gameCommonDao.updateSql(sql);
            }
            System.out.println(sql);
        }catch (Exception e){
            throw new BlogException(ErrorInfoEnum.UNKNOWN_ERROR);
        }
    }

    public void deleteNpc(GNpc npc)throws Exception{
        try{
            gameCommonDao.deleteIdL("g_npc",npc.getId());
        }catch (Exception e){
            System.out.println(e);
            throw new Exception(e);
        }
    }
}
