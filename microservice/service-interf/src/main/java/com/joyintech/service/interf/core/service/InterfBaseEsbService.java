/*
 * 文件名：InterfBaseService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.service;

import java.lang.reflect.ParameterizedType;

import com.dc.eai.data.CompositeData;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.util.InterfUtil;

/**
 * ESB交互Service
 * 
 * @param <T> 发送ESB类
 * @param <F> 接收ESB类
 * @author zhangrui1
 * @version 1.0 2018年10月31日
 * @see InterfBaseEsbService
 * @since 1.0
 */
public abstract class InterfBaseEsbService<T, F> extends InterfBaseService {

    /**
     * 报文流水号
     */
    private static final String BIZ_SEQ_NO = "bizSeqNo";

    /**
     * 出入方向
     */
    private static final String SEND_RECEIVE = "sendReceive";

    /**
     * 初始化变量
     * 
     * @param uuid 处理识别号
     * @param bizSeqNo 报文流水号
     * @param sendReceive 交易方向
     * @see
     * @since 1.0
     */
    protected void init(String uuid, String bizSeqNo, String sendReceive) {
        // 初始化处理结果
        setHandleOK(true);
        // 设置处理识别号
        setUuid(uuid);
        // 设置报文流水号
        setBizSeqNo(bizSeqNo);
        // 初始化错误信息
        setErrMsg(StringUtil.EMPTY);
        // 设置出入方向
        setSendReceive(sendReceive);
    }

    /**
     * 取得@EsbSyshead注解
     * 
     * @return @EsbSyshead注解
     * @see
     * @since 1.0
     */
    private EsbSyshead getEsbSyshead() {
        EsbSyshead head = this.getClass().getAnnotation(EsbSyshead.class);
        if (head == null) {
            getLogger().info("未设置@EsbSyshead注解!!!");
        }
        return head;
    }

    /**
     * 取得交易码
     * 
     * @return String 交易码
     * @see
     * @since 1.0
     */
    public final String getTransId() {
        EsbSyshead head = getEsbSyshead();
        return head == null ? null : head.value().getTransId();
    }

    /**
     * 取得服务代码
     * 
     * @return String 服务代码
     * @see
     * @since 1.0
     */
    public final String getServiceCode() {
        EsbSyshead head = getEsbSyshead();
        return head == null ? null : head.value().getServiceCode();
    }

    /**
     * 取得服务场景
     * 
     * @return String 服务场景
     * @see
     * @since 1.0
     */
    public final String getServiceScene() {
        EsbSyshead head = getEsbSyshead();
        return head == null ? null : head.value().getServiceScene();
    }

    /**
     * 获取接口ID
     * 
     * @return 接口ID
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public final String getInterfId() {
        EsbSyshead head = getEsbSyshead();
        return head == null ? null : head.value().getInterfId();
    }

    /**
     * 获取交易码
     * 
     * @return 交易码
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public final String getTranCode() {
        EsbSyshead head = getEsbSyshead();
        return head == null ? null : head.value().getTranCode();
    }

    /**
     * 取得系统编号
     * 
     * @return String 系统编号
     * @see
     * @since 1.0
     */
    public final String getConsumerId() {
        return InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID);
    }

    /**
     * 取得消费者ID
     * 
     * @return String 消费者ID
     * @see
     * @since 1.0
     */
    public final String getUserId() {
        return InterfCacheUtil.getSysParam(InterfConstant.INF_USER_ID);
    }

    /**
     * 取得发送方机构ID
     * 
     * @return String 发送方机构ID
     * @see
     * @since 1.0
     */
    public final String getBranchId() {
        return InterfCacheUtil.getSysParam(InterfConstant.INF_BRANCH_ID);
    }

    /**
     * 获取参数Class类
     * 
     * @return 参数Class类
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    protected Class<F> getParamFROMESBClass() {
        return (Class<F>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * 构建报文体
     * 
     * @param param <T> 实例对象
     * @return 报文体
     * @see
     * @since 1.0
     */
    protected CompositeData getEsbDataBody(T param) {
        CompositeData bodyData = null;
        if (param != null) {
            try {
                bodyData = InterfUtil.changeEntityToESBData(param);
            }
            catch (Exception e1) {
                doException(e1, "报文构建异常：");
            }
        }
        return bodyData;
    }

    /**
     * 解析报文体
     * 
     * @param reqData 报文体
     * @return <T> 实例对象
     * @see
     * @since 1.0
     */
    protected F parseEsbDataBody(CompositeData reqData) {
        F entity = null;
        try {
            entity = InterfUtil.parseESBDataToEntity(reqData, getParamFROMESBClass());
        }
        catch (Exception e) {
            doException(e, "报文解析异常：");
        }
        return entity;
    }

    /**
     * 获取 报文流水号
     * 
     * @return bizSeqNo 报文流水号.
     */
    public String getBizSeqNo() {
        return (String)getServiceInfo(BIZ_SEQ_NO);
    }

    /**
     * 设置 报文流水号
     * 
     * @param bizSeqNo 报文流水号
     */
    public void setBizSeqNo(String bizSeqNo) {
        setServiceInfo(BIZ_SEQ_NO, bizSeqNo);
    }

    /**
     * 接口表登录
     * 
     * @param entity 登录数据
     * @return 登录行数
     * @throws Exception 数据库异常
     * @see
     * @since 1.0
     */
    public abstract int saveInterfData(F entity)
        throws Exception;

    /**
     * 业务表登录
     * 
     * @param entity 登录数据
     * @return 登录行数
     * @throws Exception 数据库异常
     * @see
     * @since 1.0
     */
    public abstract int saveBusinessData(F entity)
        throws Exception;

    /**
     * 获取 出入方向
     * 
     * @return 出入方向.
     */
    public String getSendReceive() {
        return (String)getServiceInfo(SEND_RECEIVE);
    }

    /**
     * 设置 出入方向
     * 
     * @param sendReceive 出入方向
     */
    public void setSendReceive(String sendReceive) {
        setServiceInfo(SEND_RECEIVE, sendReceive);
    }

    /**
     * ESB日志登录
     * 
     * @param interfId 接口ID
     * @param reqData 请求报文
     * @param resData 应答报文
     * @see
     * @since 1.0
     */
    public void addInterfEsbLog(String interfId, String reqData, CompositeData resData) {
        getAddLogService().addEsbLog(interfId, reqData, resData,
            isHandleOK() ? InterfConstant.SENDFLAG_S : InterfConstant.SENDFLAG_F, getErrMsg(),
            getUuid(), getSendReceive());
    }
}
