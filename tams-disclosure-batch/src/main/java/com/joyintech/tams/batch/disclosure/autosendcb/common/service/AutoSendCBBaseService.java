/*
 * 文件名：AutoSendCBBaseService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2017年12月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.batch.disclosure.autosendcb.common.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.disclosure.common.db.DBUtil;
import com.joyintech.tams.common.base.dao.ChbAutosendBatchLogMapper;
import com.joyintech.tams.common.base.entity.ChbAutosendBatchLog;
import com.joyintech.tams.disclosure.common.common.constant.BatchBeanName;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;

/**
 * 中债自动检查报送共通基础service
 *
 * @author 刘二通
 * @version 1.0 2017年12月9日
 * @see AutoSendCBBaseService
 * @since 1.0
 */
@Service(value="disAutoSendCBBaseService")
public class AutoSendCBBaseService {

    /**
     * 批处理名称
     */
    private BatchBeanName batchBeanName;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dbUtil;

    /**
     * ChbAutosendBatchLogMapper
     */
    @Autowired
    private ChbAutosendBatchLogMapper chbAutosendBatchLogMapper;

    /**
     * 写日志
     *
     * @param logStr 日志内容
     * @see
     * @since 1.0
     */
    public void logInfo(String logStr) {
        logger.info("【" + batchBeanName.getBatchName() + "】" + logStr + " ： "
                    + DateUtil.getCurrentDateTime());
    }

    /**
     * 输出日志： 批处理开始
     *
     * @see
     * @since 1.0
     */
    public void logInfoBatchStart() {
        logInfo("批处理开始");
    }

    /**
     * 输出日志： 自动检查处理开始
     *
     * @see
     * @since 1.0
     */
    public void logInfoAutoCheckStart() {
        logInfo("自动检查处理开始");
    }

    /**
     * 输出日志： 无待处理数据, 批处理结束
     *
     * @param batchStartTime 批处理开始时间
     * @see
     * @since 1.0
     */
    public void logInfoNoHandleData(Date batchStartTime) {
        String logStr = "无待处理数据, 批处理结束";
        logInfo(logStr);
        insertResultLog(logStr, batchStartTime);
    }

    /**
     * 输出日志： 自动检查处理结果
     *
     * @param checkDataCount 检查数据条数
     * @param checkNGDataCount 检查不过数据条数
     *
     * @see
     * @since 1.0
     */
    public void logInfoAutoCheckResult(int checkDataCount, int checkNGDataCount) {
        String logStr = "自动检查处理结束, 共检查数据 " + checkDataCount + " 条, 检查不过数据 " + checkNGDataCount
                        + " 条";
        logInfo(logStr);
    }

    /**
     * 输出日志： 自动报送处理开始
     *
     * @see
     * @since 1.0
     */
    public void logInfoAutoSendStart() {
        logInfo("自动报送处理开始");
    }

    /**
     * 输出日志： 无检查通过数据, 批处理结束
     *
     * @param batchStartTime 批处理开始时间
     * @param checkDataCount 检查数据条数
     * @param checkNGDataCount 检查不过数据条数
     * @see
     * @since 1.0
     */
    public void logInfoNoCheckOKData(Date batchStartTime,
                                     int checkDataCount, int checkNGDataCount) {
        logInfo("无检查通过数据, 批处理结束");
        String logStr = "共检查数据 " + checkDataCount + " 条, 检查不过数据 " + checkNGDataCount
            + " 条, 无检查通过数据, 批处理结束";
        insertResultLog(logStr, batchStartTime);
    }

    /**
     * 输出日志： 自动报送处理结果
     *
     * @param batchStartTime 批处理开始时间
     * @param checkDataCount 检查数据条数
     * @param checkNGDataCount 检查不过数据条数
     * @param sendDataCount 报送数据条数
     * @see
     * @since 1.0
     */
    public void logInfoAutoSendResult(Date batchStartTime,
                                      int checkDataCount, int checkNGDataCount,
                                      int sendDataCount) {
        logInfo("自动报送处理结束, 共报送数据 " + sendDataCount + " 条 ");
        insertResultLog("共检查数据 " + checkDataCount + " 条, 检查不过数据 " + checkNGDataCount + " 条, 共报送数据 "
                        + sendDataCount + " 条 ", batchStartTime);
    }

    /**
     * 输出日志： 批处理结束
     *
     * @see
     * @since 1.0
     */
    public void logInfoBatchEnd() {
        logInfo("批处理结束");
    }

    /**
     * 将处理结果写入日志表
     *
     * @param logStr 日志内容
     * @param batchStartTime 批处理执行开始时间
     * @see
     * @since 1.0
     */
    public void insertResultLog(String logStr, Date batchStartTime) {
        ChbAutosendBatchLog batchLog = new ChbAutosendBatchLog();
        batchLog.setBatchExeId(dbUtil.getSequenceIndexData(DataIndexSequenceType.批处理用));
        batchLog.setTaskId(batchBeanName.getTaskId());
        batchLog.setStartTime(batchStartTime);
        batchLog.setEndTime(DateUtil.now());
        batchLog.setBatchResult(logStr);

        chbAutosendBatchLogMapper.insert(batchLog);
    }

    /**
     * 批处理异常结束时，写日志到日志表
     *
     * @param batchStartTime 批处理执行开始时间
     * @see
     * @since 1.0
     */
    public void insertResultLogException(Date batchStartTime) {
        insertResultLog("执行出现异常, 具体信息请联系管理员查看后台日志", batchStartTime);
    }

    /**
     * 获取 batchBeanName
     * @return batchBeanName.
     */
    public BatchBeanName getBatchBeanName() {
        return batchBeanName;
    }

    /**
     * 设置 batchBeanName
     * @param batchBeanName batchBeanName
     */
    public void setBatchBeanName(BatchBeanName batchBeanName) {
        this.batchBeanName = batchBeanName;
    }
}