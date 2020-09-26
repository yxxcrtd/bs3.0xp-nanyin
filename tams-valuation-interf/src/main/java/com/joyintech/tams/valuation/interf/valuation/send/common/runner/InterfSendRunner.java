/*
 * 文件名：InterfSendRunner.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.common.runner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.interf.conf.InterfUser;
import com.joyintech.tams.valuation.interf.core.InterfBaseService;
import com.joyintech.tams.valuation.interf.core.dao.InterfBaseMapper;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;
import com.joyintech.tams.common.base.dao.ValInterfActionlogMapper;
import com.joyintech.tams.common.base.entity.ValInterfActionlog;

/**
 * 接口发送启动类
 * 
 * @author 王波
 * @version 1.0 2018年11月19日
 * @see InterfSendRunner
 * @since 1.0
 */
@Service("interfSendRunner")
@Scope("prototype")
public class InterfSendRunner {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private InterfCommonService interfCommonService;
    
    @Autowired
    private InterfBaseMapper interfBaseMapper;
    
    @Autowired
    private ValInterfActionlogMapper valInterfActionlogMapper;
    
    @Autowired
    private InterfCommonService valInterfCommonService;
    
    private ExecutorService executor = Executors.newCachedThreadPool() ;
    
    /**
     * 参数
     */
    private Map<String, Object> params;
    
    public void execute() throws Exception {
        // 将用户加入到SESSION中
        addUserToSession();
        executor.submit(new Runnable() {
            
            @Override
            public void run() {
                logger.debug("接收数据接口启动时间：" + DateUtil.getCurrentDateTime());

                List<String> seqnos = interfCommonService.getSeqnoUnEXEC();
                if (!CollectionUtils.isEmpty(seqnos)) {
                    logger.error("有任务未执行完毕, 该任务将不会执行！");
                    return;
                }
                
                String execTaskId = interfCommonService.getUUid();
                // 来自画面传输的任务
                 if (params != null && params.get(InterfCommonConstant.接口_任务执行ID) != null) {
                     execTaskId = (String)params.get(InterfCommonConstant.接口_任务执行ID);
                 } else if (params == null){
                     params = new HashMap<>();
                 } else {
                     logger.error("无任务执行ID！");
                     return;
                 }
                 params.put(InterfCommonConstant.接口_发送接收, DictCodeInterfList.接口发送接收状态_发送.getValue());
                 // 查询画面指定的批次号
                 List<String> orderNos = interfBaseMapper.getOrderNosByTempENames(params);
                 params.put(InterfCommonConstant.INTERF_ORDER_LIST, orderNos);
                 
                 List<InterfExecuteInfo> tasks = interfBaseMapper.getInterfExecuteInfo(params);
                 String startDate = params == params.get(InterfCommonConstant.接口_开始日) ? null : (String)params.get(InterfCommonConstant.接口_开始日);
                 String endDate = params == params.get(InterfCommonConstant.接口_开始日) ? null : (String)params.get(InterfCommonConstant.接口_结束日);
                 if (StringUtil.isNotEmpty(startDate) && StringUtil.isNotEmpty(endDate)) {
                     Date start = DateUtil.parse(startDate);
                     Date end = DateUtil.parse(endDate);
                     if (start.after(end)) {
                         logger.error("开始日不能大于结束日！");
                         return;
                     }
                     // 从start开始跑到end，含头含尾
                     while (end.compareTo(start) >= 0) {
                         logger.info("--------------------开始标志——接入日期："+DateUtil.format(start)+" 接口数据--------------------");
                         if (tasks != null) {
                             excuteTask(tasks, start, execTaskId);
                         }
                         logger.info("--------------------结束标志——接入日期："+DateUtil.format(start)+" 接口数据--------------------");
                         start = DateUtil.addDay(start, 1);
                     }
                 }
                 else {
                     Date runDate = new Date();
                     if (params.get("interfTime") != null) {
                         runDate = (Date)params.get("interfTime");
                     }
                     else {

                     }
                     logger.info("--------------------开始标志——发送日期："+DateUtil.format(runDate)+" 接口数据--------------------");
                     excuteTask(tasks, runDate, execTaskId);
                     logger.info("--------------------结束标志——发送日期："+DateUtil.format(runDate)+" 接口数据--------------------");
                 }
                
            }
        });
        
    }
    
    /**
     * 根据指定的时间执行指定的TASK
     * 
     * @param tasks
     * @param runDate
     */
    private void excuteTask(List<InterfExecuteInfo> tasks, Date runDate, String execId) {
     // 所要执行的任务不为空
        if (tasks == null || tasks.isEmpty()) {
            logger.info("未选定批处理任务，直接退出此次任务.");
            return;
        }
     // 根据task创建副本作为待执行句柄
        List<InterfExecuteInfo> taskTodo = initTaskTodo(tasks, runDate, execId);
        for (InterfExecuteInfo task : taskTodo) {
            Long begin = System.currentTimeMillis();
            ValInterfActionlog actionLog = task.getActionLog();

            actionLog = valInterfActionlogMapper.selectByPrimaryKey(actionLog.getSeqno());
            if (actionLog.getStartTime() == null) {
                // 设置开始时间
                actionLog.setStartTime(new Date());
            }
            // 手动停止的不执行
            if (DictCodeInterfList.接口执行状态_未执行.getValue().equals(actionLog.getStatus())) {
                actionLog.setStatus(DictCodeInterfList.接口执行状态_执行中.getValue());
                valInterfActionlogMapper.updateByPrimaryKeySelective(actionLog);
            }
            else {
                continue;
            }
            try {
                InterfBaseService runner = (InterfBaseService)SpringContextUtil.getBean(
                    task.getBeanName());
                params.put("interfTime", runDate);
                // 任务执行中
                runner.setParams(params);
                runner.setInterfExecuteInfo(task);
                runner.execute();
                // 执行失败的不更新
                actionLog = valInterfActionlogMapper.selectByPrimaryKey(actionLog.getSeqno());
                Long end = System.currentTimeMillis();
                // 执行时间
                actionLog.setLastTime(end - begin);
                actionLog.setEndTime(new Date());
                if (DictCodeInterfList.接口执行状态_执行中.getValue().equals(actionLog.getStatus())) {
                    // 执行失败不更新状态
                    actionLog.setStatus(DictCodeInterfList.接口执行状态_执行成功.getValue());
                }
                valInterfActionlogMapper.updateByPrimaryKeySelective(actionLog);
            }
            catch (Exception e) {
                Long end = System.currentTimeMillis();
                // 执行时间
                actionLog.setLastTime(end - begin);
                actionLog.setEndTime(new Date());
                actionLog.setStatus(DictCodeInterfList.接口执行状态_执行失败.getValue());
                actionLog.setExecResult("执行失败：" + e.getMessage());
                String execDetail = "执行task:" + task.getTaskName() + "失败！--" + e.getMessage();
                if (actionLog.getDetailAction() == null) {
                    actionLog.setDetailAction("");
                }
                actionLog.setDetailAction(actionLog.getDetailAction() + execDetail);
                valInterfActionlogMapper.updateByPrimaryKeySelective(actionLog);
                if (Integer.parseInt(task.getOrderNo()) == 0) {
                    logger.error("从服务器下载数据失败", e);
                    continue;
                }
                execDetail = "执行task:" + task.getTaskName() + "失败！--" + e.getMessage();
                valInterfCommonService.updateInterfActionLogToFailure(execDetail);
                logger.error("", e);
                continue;
            }

        }
    }
    
    private List<InterfExecuteInfo> initTaskTodo(List<InterfExecuteInfo> tasks, Date runDate, String execId) {
        List<InterfExecuteInfo> taskTodo = new ArrayList<InterfExecuteInfo>();
        for (InterfExecuteInfo task : tasks) {
            InterfExecuteInfo taskTmp = new InterfExecuteInfo();
            BeanUtils.copyProperties(task, taskTmp);
            taskTmp.setInterfDate(runDate);
            if (StringUtil.isEmpty(taskTmp.getLocalRootPath())) {
                taskTmp.setLocalRootPath(System.getProperty("user.home"));
            }

            ValInterfActionlog actionLog = new ValInterfActionlog();
            // 业务日期
            actionLog.setBusiDate(runDate);
            // 接口英文名称
            actionLog.setTaskEname(task.getBeanName());
            // 接口批次号
            actionLog.setOrderno(Long.parseLong(task.getOrderNo()));
            // 日志UUID
            actionLog.setSeqno(interfCommonService.getUUid());
            // 执行状态
            actionLog.setStatus(DictCodeInterfList.接口执行状态_未执行.getValue());
            // 任务ID
            actionLog.setExectaskSeqno(execId);
            // 插入数据库
            valInterfActionlogMapper.insert(actionLog);
            taskTmp.setActionLog(actionLog);
            taskTodo.add(taskTmp);
        }
        return taskTodo;
    }
    
    /**
     * 将用户加入到SESSION中
     */
    private void addUserToSession() {
        InterfUser user = new InterfUser();
        user.setUserName(InterfCommonConstant.接口_默认用户);
        user.setDeptCode(null);
        SecurityUtil.addUserToSession(user);
    }
    
    /**
     * 获取 params
     * 
     * @return params.
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * 设置 params
     * 
     * @param params params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
