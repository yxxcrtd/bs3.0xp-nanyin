/*
 * 文件名：BaseService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.joyintech.tams.common.base.entity.InterfFileLog;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.service.SysParamInfService;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.pingan.bfts.bftscore.client.FileAccessConfig;

/**
 * 接口基类Service
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月1日
 * @see InterfBaseService
 * @since 1.0
 */
public abstract class InterfBaseService {

    /**
     * 事务管理器
     */
    @Autowired
    private DataSourceTransactionManager transactionManager;

    /**
     * 日志登录服务类
     */
    @Autowired
    private AddLogService logService;

    /**
     * 服务需要使用的变量存储Map（解决多线程单例不安全问题）
     */
    private static ThreadLocal<Map<String, Object>> serviceInfoMap = ThreadLocal.withInitial(
        HashMap::new);

    /**
     * 错误信息
     */
    private static final String ERR_MSG = "errMsg";

    /**
     * 处理识别号
     */
    private static final String UUID = "uuid";

    /**
     * 处理从ESB接收数据的状态，true：成功
     */
    private static final String HANDLER_OK = "handlerOK";

    /**
     * 系统参数Service
     */
    @Autowired
    private SysParamInfService paraService;

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 平台日期
     */
    private static final String SYSDATE = "sysDate";

    /**
     * 返回错误信息
     * 
     * @return String 错误信息
     */
    public String getErrMsg() {
        return (String)getServiceInfo(ERR_MSG);
    }

    /**
     * 设置错误信息
     * 
     * @param errMsg 错误信息
     * @see
     * @since 1.0
     */
    public void setErrMsg(String errMsg) {
        setServiceInfo(ERR_MSG, errMsg);
    }

    /**
     * 获取日志对象
     * 
     * @return 日志对象
     * @see
     * @since 1.0
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * 获取 处理ESB返回数据的结果，处理成功或失败
     * 
     * @return handleOK.
     */
    public boolean isHandleOK() {
        return (Boolean)getServiceInfo(HANDLER_OK);
    }

    /**
     * 设置处理结果
     * 
     * @param handlerOK 处理结果
     * @see
     * @since 1.0
     */
    public void setHandleOK(boolean handlerOK) {
        setServiceInfo(HANDLER_OK, handlerOK);
    }

    /**
     * 异常处理
     * 
     * @param e 异常实例
     * @param errMsg 信息
     * @see
     * @since 1.0
     */
    protected void doException(Exception e, String errMsg) {
        setHandleOK(false);
        if (e != null) {
            getLogger().error(e.getMessage());
            errMsg = errMsg + "【" + e.getMessage() + "】";
        }
        setErrMsg(errMsg);
    }

    /**
     * 取得日志服务类
     * 
     * @return 日志服务类
     * @see
     * @since 1.0
     */
    public AddLogService getAddLogService() {
        return this.logService;
    }

    /**
     * 接口日志登录
     * 
     * @param interfId 接口ID
     * @param bizSeqNo 报文流水号
     * @param flg 预警标示
     * @param remark1 备用字段1
     * @param remark2 备用字段2
     * @see
     * @since 1.0
     */
    public void addInterfLog(String interfId, String bizSeqNo, String flg, String remark1,
                             String remark2) {
        getAddLogService().addInterfLog(interfId,
            isHandleOK() ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue(),
            getErrMsg(), flg, remark1, remark2, getUuid(), bizSeqNo, getSysDate());
    }

    /**
     * 文件日志登录
     * 
     * @param config 连接配置
     * @param localPath 本地全路径
     * @param bftsFlg 是否经过文件系统
     * @param ftpPath 文件路径
     * @param fileName 文件名
     * @param excuteType 文件操作类型
     * @param remark1 备用字段1
     * @param remark2 备用字段2
     * @see
     * @since 1.0
     */
    public void addFileLog(FileAccessConfig config, String localPath, String bftsFlg,
                           String ftpPath, String fileName, String excuteType, String remark1,
                           String remark2) {
        InterfFileLog fileLogInfo = new InterfFileLog();
        fileLogInfo.setUuid(getUuid());
        fileLogInfo.setBftsFlg(bftsFlg);
        fileLogInfo.setLocalFullPath(localPath);
        fileLogInfo.setFtpPath(ftpPath);
        fileLogInfo.setFtpName(fileName);
        fileLogInfo.setRemark1(remark1);
        fileLogInfo.setRemark2(remark2);
        fileLogInfo.setErrMessage(getErrMsg());
        fileLogInfo.setExcuteType(excuteType);
        fileLogInfo.setTaskStatus(
            isHandleOK() ? InterfDictCodeList.执行状态_成功.getValue() : InterfDictCodeList.执行状态_失败.getValue());
        if (config != null) {
            fileLogInfo.setIp(config.getServer());
            fileLogInfo.setPort(new StringBuffer(config.getPort()).toString());
            fileLogInfo.setUserName(config.getUsername());
            fileLogInfo.setPassword(config.getPassword());
            fileLogInfo.setEncode(config.getEncoding());
        }
        logService.addFileLog(fileLogInfo);
    }

    /**
     * 获取 处理识别号
     * 
     * @return uuid 处理识别号.
     */
    public String getUuid() {
        return (String)getServiceInfo(UUID);
    }

    /**
     * 设置 处理识别号
     * 
     * @param uuid 处理识别号
     */
    public void setUuid(String uuid) {
        setServiceInfo(UUID, uuid);
    }

    /**
     * 获取 平台日期
     * 
     * @return 平台日期.
     */
    public String getSysDate() {
        String sysDate = (String)getServiceInfo(SYSDATE);
        if (StringUtil.isEmpty(sysDate)) {
            sysDate = paraService.getSysDate();
            setSysDate(sysDate);
        }
        return sysDate;
    }

    /**
     * 设置 平台日期
     * 
     * @param sysDate 平台日期
     */
    public void setSysDate(String sysDate) {
        setServiceInfo(SYSDATE, sysDate);
    }

    /**
     * 取得系统参数
     * 
     * @return 系统参数Map
     * @see
     * @since 1.0
     */
    public Map<String, Object> getSysParamMap() {
        return paraService.interfCache();
    }

    /**
     * 取得事务管理器
     * 
     * @return 事务管理器
     * @see
     * @since 1.0
     */
    public DataSourceTransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * 获得事务状态
     * 
     * @return 事务状态
     * @see
     * @since 1.0
     */
    public TransactionStatus getTransactionStatus() {
        // 获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        // 设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        // 获得事务状态
        return transactionManager.getTransaction(def);
    }

    /**
     * 取得服务需要使用的变量存储Map
     * 
     * @return 服务需要使用的变量存储Map
     */
    protected Map<String, Object> getServiceInfoMap() {
        return serviceInfoMap.get();
    }

    /**
     * 取得服务需要使用的变量
     * 
     * @param key 服务需要使用的变量名称
     * @return 服务需要使用的变量
     */
    protected Object getServiceInfo(String key) {
        return getServiceInfoMap().get(key);
    }

    /**
     * 设置 服务需要使用的变量
     * 
     * @param key 服务需要使用的变量名称
     * @param value 服务需要使用的变量
     */
    protected void setServiceInfo(String key, Object value) {
        getServiceInfoMap().put(key, value);
    }
}
