/*
 * 文件名：SendEsbService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.dc.eai.data.CompositeData;
import com.dcfs.esb.client.ESBClient;
import com.dcfs.esb.client.converter.PackUtil;
import com.dcfs.esb.client.exception.TimeoutException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.EsbHeadEnum;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.tams.invest.common.component.base.core.impl.dao.BaseGetBussinessIdMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 向ESB请求数据的Service
 * 
 * @param <T> 发送ESB类型
 * @param <F> 接收ESB类型
 * @author zhangrui1
 * @version 1.0 2018年10月31日
 * @see SendEsbService
 * @since 1.0
 */
public abstract class SendEsbService<T, F> extends InterfBaseEsbService<T, F> {

    /**
     * 业务编号生成规则mapper
     */
    @Autowired
    private BaseGetBussinessIdMapper bussinessCodeMapper;

    /**
     * 发送状态
     */
    private static final String SEND_OK = "sendOK";

    /**
     * 接收状态
     */
    private static final String RECEIVE_OK = "receiveOK";

    /**
     * 接口处理入口
     * 
     * @param param 接口参数
     * @return 应答报文
     * @see
     * @since 1.0
     */
    public final F sendRequest(Map<String, Object> param) {
        getLogger().info("【sendRequest】 start ... ");
        // 初始化变量
        setSysDate((String)param.get(InterfConstant.SYS_DATE));
        init((String)param.get(InterfConstant.UUID), getSeqNo(getConsumerId(), getSysDate()),
            InterfDictCodeList.出入方向_发送ESB.getValue());
        // 发送报文构建
        CompositeData reqData = preparedData(param);
        CompositeData rspData = null;
        // 发送ESB请求
        if (reqData != null) {
            rspData = sendRequest(reqData);
        }
        // 处理返回报文
        F from = null;
        if (rspData != null) {
            from = handleResponseData(rspData);
        }
        // 日志记录
        addInterfLog(getInterfId(), getBizSeqNo(), DictCodeList.是否_否.getValue(),
            StringUtil.EMPTY, StringUtil.EMPTY);
        getLogger().info("【sendRequest】 end ... ");
        return from;
    }

    /**
     * 构建发送数据
     * 
     * @param param 查询条件
     * @return 发送数据
     * @see
     * @since 1.0
     */
    private CompositeData preparedData(Map<String, Object> param) {
        getLogger().info("【preparedData】 start ... ");
        T entity = null;
        Object obj = param.get(InterfConstant.PROD_INFO);
        try {
            entity = preparedLocalData(obj);
        }
        catch (Exception e) {
            entity = null;
            doException(e, "请求数据准备异常");
        }
        getLogger().info("【preparedData】 end ... ");
        return entity == null ? null : createRequestData(entity);
    }

    /**
     * 发送ESB
     * 
     * @param reqData 报文
     * @return 应答报文体
     * @see
     * @since 1.0
     */
    private CompositeData sendRequest(CompositeData reqData) {
        // 打印报文xml
        String reqXml = PackUtil.packXmlStr(reqData);
        getLogger().info("====send====");
        getLogger().info(reqXml);
        CompositeData rspData = null;
        try {
            rspData = ESBClient.request(reqData);
            setSendOK(true);

            // 打印报文xml
            String rspXml = PackUtil.packXmlStr(rspData);
            getLogger().info("====receive====");
            getLogger().info(rspXml);
        }
        catch (TimeoutException e) {
            rspData = null;
            doException(e, "ESB服务请求异常");
        }
        addInterfEsbLog(getInterfId(), reqXml, rspData);
        return rspData == null ? null : rspData;
    }

    /**
     * check返回状态
     * 
     * @param rspData 报文
     * @return 状态
     * @see
     * @since 1.0
     */
    private boolean checkStatus(CompositeData rspData) {
        boolean result = false;
        try {
            CompositeData head = rspData.getStruct(InterfConstant.SYS_HEAD);
            String retStatus = head.getField(InterfConstant.RET_STATUS).strValue();
            String retCode = head.getArray(InterfConstant.RET).getStruct(0).getField(
                InterfConstant.RET_CODE).strValue();
            if (InterfConstant.ESB_RET_STATUS_S.equals(retStatus)
                && InterfConstant.ESB_RET_CODE_000000.equals(retCode)) {
                result = true;
            }
            setHandleOK(result);
            setErrMsg(head.getArray(InterfConstant.RET).getStruct(0).getField(
                InterfConstant.RET_MSG).strValue());
        }
        catch (Exception e) {
            result = false;
            doException(e, "查询数据check失败");
        }
        setReceiveOK(result);
        // 对于指令发送接口需要更新指令发送状态和指令接收状态
        updateStatus(rspData, isSendOK(), result);
        return result;
    }

    /**
     * 更新状态
     * 
     * @param rspData 报文
     * @param sendOK 发送状态
     * @param receiveOK 接收状态
     * @see
     * @since 1.0
     */
    public void updateStatus(CompositeData rspData, boolean sendOK, boolean receiveOK) {

    }

    /**
     * 处理应答报文
     * 
     * @param rspData 应答报文
     * @return 处理结果
     * @see
     * @since 1.0
     */
    private F handleResponseData(CompositeData rspData) {
        getLogger().info("【handleResponseData】 start ... ");
        // 校验返回结果状态
        if (!checkStatus(rspData)) {
            return null;
        }
        F result = parseEsbDataBody(rspData.getStruct(InterfConstant.BODY));
        // 报文体为空时
        if (result == null) {
            getLogger().info("返回的报文体为空！！！");
            return null;
        }
        // 获取事务控制管理器
        DataSourceTransactionManager transactionManager = getTransactionManager();
        // 获取事务控制管理器
        TransactionStatus status = getTransactionStatus();
        try {
            saveInterfData(result);
            transactionManager.commit(status);
        }
        catch (Exception e) {
            doException(e, "接口表登录异常");
            transactionManager.rollback(status);
            return result;
        }
        status = getTransactionStatus();
        try {
            saveBusinessData(result);
            transactionManager.commit(status);
        }
        catch (Exception e) {
            doException(e, "业务表登录异常");
            transactionManager.rollback(status);
            return result;
        }
        getLogger().info("【handleResponseData】 end ... ");
        return result;
    }

    /**
     * 抽取发送数据
     * 
     * @param param 抽取条件
     * @return 发送数据
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public abstract T preparedLocalData(Object param)
        throws Exception;

    /**
     * 构建发送给ESB的数据 </br>
     * 将本地数据构建成固定格式的报文发送给ESB
     * 
     * @param param Entity对象
     * @return CompositeData 固定格式的报文
     * @see
     * @since 1.0
     */
    protected CompositeData createRequestData(T param) {
        Date cdate = new Date();
        String strTimeMs = DateUtil.format(cdate, InterfConstant.FORMAT_HHMMSSMS);
        if (strTimeMs.length() > 9) {
            strTimeMs = strTimeMs.substring(0, strTimeMs.length() - 1);
        }
        CompositeData reqData = new CompositeData();
        CompositeData sysHead = new CompositeData();
        CompositeData appHead = new CompositeData();
        CompositeData localHead = new CompositeData();
        reqData.addStruct(InterfConstant.SYS_HEAD, sysHead);
        reqData.addStruct(InterfConstant.APP_HEAD, appHead);
        reqData.addStruct(InterfConstant.LOCAL_HEAD, localHead);
        // SYS_HEAD
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_服务代码, getServiceCode());// 1服务代码
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_请求系统编号, getConsumerId());// 2请求系统编号
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_服务场景, getServiceScene());// 3服务场景
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_交易流水号, getBizSeqNo());// 4服务请求发送方流水号
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_交易发起的日期, getSysDate());// 5发送方交易日期
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_交易发起的时间, strTimeMs);// 6发送方交易时间
        InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_交易码, getTranCode());// 交易码
        // APP_HEAD
        InterfUtil.addField(appHead, EsbHeadEnum.应用报文头_发送方机构ID, getBranchId());// 1发送方机构ID
        InterfUtil.addField(appHead, EsbHeadEnum.应用报文头_服务请求者身份, getUserId());// 2服务请求者身份
        InterfUtil.addField(appHead, EsbHeadEnum.应用报文头_发起方业务流水号, getBizSeqNo());// 3发起方业务流水号
        // BODY
        CompositeData body = getEsbDataBody(param);
        // 若body为空，怎返回空的报文
        if (body == null) {
            return null;
        }
        reqData.addStruct(InterfConstant.BODY, body);
        return reqData;
    }

    /**
     * 拼接流水号
     * 
     * @param consumerId 系统编号
     * @param yyyymmdd 日期
     * @return String 流水号
     * @see
     * @since 1.0
     */
    private synchronized String getSeqNo(String consumerId, String yyyymmdd) {
        return consumerId + StringUtil.substring(yyyymmdd, 2)
               + (bussinessCodeMapper == null ? "0000000000" : bussinessCodeMapper.getNextValBySequenceName(
                   InterfConstant.INTERF_BIZNO));
    }

    /**
     * 获取 发送状态
     * 
     * @return 发送状态.
     */
    public boolean isSendOK() {
        return (Boolean)getServiceInfo(SEND_OK);
    }

    /**
     * 设置 发送状态
     * 
     * @param sendOK 发送状态
     */
    public void setSendOK(boolean sendOK) {
        setServiceInfo(SEND_OK, sendOK);
    }

    /**
     * 获取 接收状态
     * 
     * @return 接收状态.
     */
    public boolean isReceiveOK() {
        return (Boolean)getServiceInfo(RECEIVE_OK);
    }

    /**
     * 设置 接收状态
     * 
     * @param receiveOK 接收状态
     */
    public void setReceiveOK(boolean receiveOK) {
        setServiceInfo(RECEIVE_OK, receiveOK);
    }
}
