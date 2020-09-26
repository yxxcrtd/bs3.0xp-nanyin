/*
 * 文件名：ValFtpService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.transdata.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.ValInterfTemplate;
import com.joyintech.tams.valuation.common.interf.base.util.DbUtil;
import com.joyintech.tams.valuation.common.interf.transdata.pabhq.utils.PabDbUtil;
import com.joyintech.tams.valuation.common.interf.transdata.service.HqTemplateCommonService;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvFileEntity;
import com.joyintech.tams.valuation.interf.valuation.transdata.entity.ValRecvServerInfoEntity;

@Service
@Scope("prototype")
public class DbService {

	@Autowired
    HqTemplateCommonService hqTemplateCommonService;
    /**
     * 数据源操作工具
     */
    private DbUtil dbUtil;
    /**
     * 模版列表
     */
    private List<ValInterfTemplate> tempList;
    
    /**
     * 接收文件
     * 
     * @param entity
     * @param fileNamePart
     * @throws Exception
     * @see
     * @since 1.0
     */
    public ValRecvFileEntity doRecv(ValRecvServerInfoEntity entity, List<ValInterfTemplate> _tempList)
        throws Exception {
    	ValRecvFileEntity result = new ValRecvFileEntity();
    	
    	Date time = entity.getOccurTime();
    	//1.初始化数据库参数
    	dbUtil = new DbUtil(entity.getServerIp(),entity.getServerPort(),entity.getRemotePath(),entity.getUserName(),entity.getPassword());
    	//2.加载数据库
    	dbUtil.init();
    	
    	if(dbUtil.getDbConnection()==null){
    		System.out.println("数据库("+entity.getRemotePath()+")连接"+dbUtil.getConnectionResult());
    		return null;
    	}
    	//3.读取数据
    	tempList = _tempList;
    	Map<String,Object> dataMap = new HashMap<String,Object>();
    	for(ValInterfTemplate _t : tempList){
    		if(!_t.getSystemid().equals(entity.getSystemID())){
    			//过滤系统类型不一致的模版
    			continue;
    		}
    		//3.1 根据发布日期删除当天数据库临时表里面的数据
    		hqTemplateCommonService.deleteThisDayData(_t.getTmpEName(), time);
    		
    		//3.2 根据发布日期删除15天之前历史表数据
    		hqTemplateCommonService.deleteFifthDayData(_t.getTmpEName(), time);
    		
    		//3.3 查询当日发布的行情数据
    		Object rstMap = PabDbUtil.queryResultData(dbUtil.getDbConnection(), _t, time);
    		if(rstMap!=null){
    			dataMap.put(_t.getTmpEName(), rstMap);
//    			ValInterflog log = new ValInterflog();
//    			log.setExDate(new Date());
//    			log.setFileName(_t.getTmpEName());
//    			log.setTmpName("DB_load_template");
//    			log.setParentSeqno(entity.getExecInfoId());
//    			log.setIsCheck("Y");
//    			log.setNum(0L);
//    			result.getInterfLogs().add(log);
    		}
    	}
    	//4.加载数据
    	hqTemplateCommonService.loadThisDayData(dataMap);
    	//5.释放数据库连接
    	dbUtil.release();
    	return result;
    }
}