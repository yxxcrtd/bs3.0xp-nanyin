/*
 * 文件名：InterfCoreService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.dao.ValInterfCommonMapper;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.valuation.interf.valuation.send.common.entity.SendWriterEntity;
import com.joyintech.tams.common.base.dao.ValInterfActionlogMapper;
import com.joyintech.tams.common.base.entity.ValInterfActionlog;


@Service
public class InterfCoreService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
   
    @Autowired
    private ValInterfCommonMapper valInterfCommonMapper;
    
    @Autowired
    private ValInterfActionlogMapper valInterfActionlogMapper;
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void onProcessError(Exception e, Map<String, Object> params, InterfBaseService batchService) {
        InterfExecuteInfo interfExecuteInfo = batchService.getInterfExecuteInfo();
        // 获取执行日志
        ValInterfActionlog valInterfActionlog = interfExecuteInfo.getActionLog();
        valInterfActionlog =  valInterfActionlogMapper.selectByPrimaryKey(valInterfActionlog.getSeqno());
        
        if (DictCodeInterfList.接口执行状态_执行中.getValue().equals(valInterfActionlog.getStatus())) {
            valInterfActionlog.setStatus(DictCodeInterfList.接口执行状态_执行失败.getValue());
            String detail = valInterfActionlog.getDetailAction();
            if (detail == null) {
                detail = "执行task:"  +  interfExecuteInfo.getTaskName() + "失败 --" + e.getMessage();
            } 
            else {
                detail += "执行task:"  +  interfExecuteInfo.getTaskName() + "失败 --" + e.getMessage();
            }
            valInterfActionlog.setExecResult(detail);
            valInterfActionlog.setDetailAction(detail);
            logger.error(detail);
            valInterfActionlogMapper.updateByPrimaryKeySelective(valInterfActionlog);
        }
    }
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void onReadError(Exception e, Map<String, Object> params, InterfBaseService batchService) {
        InterfExecuteInfo interfExecuteInfo = batchService.getInterfExecuteInfo();
        // 获取执行日志
        ValInterfActionlog valInterfActionlog = interfExecuteInfo.getActionLog();
        valInterfActionlog =  valInterfActionlogMapper.selectByPrimaryKey(valInterfActionlog.getSeqno());
        
        if (DictCodeInterfList.接口执行状态_执行中.getValue().equals(valInterfActionlog.getStatus())) {
            valInterfActionlog.setStatus(DictCodeInterfList.接口执行状态_执行失败.getValue());
            String detail = valInterfActionlog.getDetailAction();
            if (detail == null) {
                detail = "执行task:"  +  interfExecuteInfo.getTaskName() + "失败 --" + e.getMessage();
            } 
            else {
                detail += "执行task:"  +  interfExecuteInfo.getTaskName() + "失败 --" + e.getMessage();
            }
            valInterfActionlog.setExecResult(detail);
            valInterfActionlog.setDetailAction(detail);
            logger.error(detail);
            valInterfActionlogMapper.updateByPrimaryKeySelective(valInterfActionlog);
        }
    }
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void onWriterError(Exception e, Map<String, Object> params, InterfBaseService batchService) {
        InterfExecuteInfo interfExecuteInfo = batchService.getInterfExecuteInfo();
        // 获取执行日志
        ValInterfActionlog valInterfActionlog = interfExecuteInfo.getActionLog();
        valInterfActionlog =  valInterfActionlogMapper.selectByPrimaryKey(valInterfActionlog.getSeqno());
        
        if (DictCodeInterfList.接口执行状态_执行中.getValue().equals(valInterfActionlog.getStatus())) {
            valInterfActionlog.setStatus(DictCodeInterfList.接口执行状态_执行失败.getValue());
            String detail = valInterfActionlog.getDetailAction();
            if (detail == null) {
                detail = "执行task:"  +  interfExecuteInfo.getTaskName() + "插入 失败 ！" + e.getMessage();
            } 
            else {
                detail += "执行task:"  +  interfExecuteInfo.getTaskName() + "插入 失败！" + e.getMessage();
            }
            valInterfActionlog.setExecResult(detail);
            valInterfActionlog.setDetailAction(detail);
            logger.error(detail);
            valInterfActionlogMapper.updateByPrimaryKeySelective(valInterfActionlog);
        }
    }
    public void onAfterWriter(List<? extends BaseEntity> items, InterfBaseService service) {
        if (items != null && items.size() > 0 && items.get(0) instanceof SendWriterEntity) {
            InterfExecuteInfo interfExecuteInfo = service.getInterfExecuteInfo();
            // 获取执行日志
            ValInterfActionlog valInterfActionlog = interfExecuteInfo.getActionLog();
            valInterfActionlog =  valInterfActionlogMapper.selectByPrimaryKey(valInterfActionlog.getSeqno());
            
            if (DictCodeInterfList.接口执行状态_执行中.getValue().equals(valInterfActionlog.getStatus())) {
                valInterfActionlog.setStatus(DictCodeInterfList.接口执行状态_执行成功.getValue());
                String detail = valInterfActionlog.getDetailAction();
                
                int length = 0;
                for (BaseEntity i:items) {
                    SendWriterEntity i2 = (SendWriterEntity)i;
                    if (i2 != null && i2.getDatas() != null){
                        length += i2.getDatas().size();
                    }
                }
                if (detail == null) {
                    detail = "执行task:"  +  interfExecuteInfo.getTaskName() + " 成功,处理数据:" + length + "条！";
                } 
                else {
                    detail += "执行task:"  +  interfExecuteInfo.getTaskName() + " 成功,处理数据:" + length + "条！"; ;
                }
                valInterfActionlog.setExecResult(detail);
                valInterfActionlog.setDetailAction(detail);
                logger.error(detail);
                valInterfActionlogMapper.updateByPrimaryKeySelective(valInterfActionlog);
            }
        }
    }
    
    /**
     * 接口启动时初始化行为日志
     * 
     * @see
     * @since 1.0
     */
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void initInterfActionLog() {
        Map<String, Object> params = new HashMap<>();
        List<String> statusList = new ArrayList<>();
        statusList.add(DictCodeInterfList.接口执行状态_未执行.getValue());
        statusList.add(DictCodeInterfList.接口执行状态_执行中.getValue());
        
        params.put("statusList", statusList);
        params.put("status", DictCodeInterfList.接口执行状态_执行失败.getValue());
        String detail = "系统服务因重新启动，任务终止！";
        params.put("detailAction", detail);
        params.put("execResult", detail);
        
        int res = valInterfCommonMapper.updateInterfActionLogStatus(params);
        logger.info("系统重启，未执行任务和执行中任务被强制终止，处理了："  + res +"条接口任务");
    }
}
