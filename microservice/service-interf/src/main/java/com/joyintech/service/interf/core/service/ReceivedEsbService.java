/*
 * 文件名：ReceivedEsbService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.cs.esb.util.ESBDict;
import com.dc.eai.data.Array;
import com.dc.eai.data.CompositeData;
import com.dcfs.esb.client.converter.PackUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.EsbHeadEnum;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 接收并处理ESB请求的数据
 * 
 * @param <T> 发送ESB类型
 * @param <F> 接收ESB类型
 * @author zhangrui1
 * @version 1.0 2018年10月31日
 * @see ReceivedEsbService
 * @since 1.0
 */
public abstract class ReceivedEsbService<T, F> extends InterfBaseEsbService<T, F> {

    /**
     * 处理接收的ESB请求报文
     * 
     * @param reqData 请求报文
     * @param uuid 处理识别码
     * @return 应答报文
     * @see
     * @since 1.0
     */
    public CompositeData doReceivedData(CompositeData reqData, String uuid) {
        getLogger().info("【doReceivedData】 start ... ");
        String reqXml = PackUtil.packXmlStr(reqData);
        // 初始化变量
        init(uuid, reqData.getStruct(ESBDict.APP_HEAD).getField(ESBDict.BIZ_SEQ_NO).strValue(),
            InterfDictCodeList.出入方向_接收ESB.getValue());
        // 解析报文
        F reqEntity = parseEsbDataBody(reqData.getStruct(ESBDict.BODY));
        T resEntity = null;
        if (reqEntity != null) {
            // 数据登录
            this.handleDataFromEsb(reqEntity);
            // 返回数据取得
            resEntity = createResponseEntity(reqEntity);
        }

        // 返回报文体构建
        CompositeData resBody = getEsbDataBody(resEntity);
        // 返回报文构建
        CompositeData resData = createResponseData(reqData);
        if (resBody != null) {
            resData.addStruct(InterfConstant.BODY, resBody);
        }
        // 日志登录
        addInterfEsbLog(getInterfId(), reqXml, resData);
        addInterfLog(getInterfId(), getBizSeqNo(), DictCodeList.是否_否.getValue(),
            StringUtil.EMPTY, StringUtil.EMPTY);
        getLogger().info("【doReceivedData】 end ... ");
        return resData;
    }

    /**
     * 登录DB
     * 
     * @param reqEntity 登录内容
     * @see
     * @since 1.0
     */
    private void handleDataFromEsb(F reqEntity) {
        getLogger().info("【handleDataFromEsb】 start ... ");
        // 获取事务控制管理器
        DataSourceTransactionManager transactionManager = getTransactionManager();
        // 获取事务控制管理器
        TransactionStatus status = getTransactionStatus();
        try {
            saveInterfData(reqEntity);
            transactionManager.commit(status);
        }
        catch (Exception e) {
            doException(e, "接口表登录异常");
            transactionManager.rollback(status);
            return;
        }
        status = getTransactionStatus();
        try {
            saveBusinessData(reqEntity);
            transactionManager.commit(status);
        }
        catch (Exception e) {
            doException(e, "业务表登录异常");
            transactionManager.rollback(status);
        }
        getLogger().info("【handleDataFromEsb】 end ... ");
    }

    /**
     * 抽取应答内容
     * 
     * @param entity 抽取条件
     * @return 应答内容
     * @see
     * @since 1.0
     */
    private T createResponseEntity(F entity) {
        T resEntity = null;
        try {
            resEntity = selectResponseData(entity);
        }
        catch (Exception e) {
            doException(e, "应答数据查询异常");
        }
        return resEntity;
    }

    /**
     * 构建报文头部
     * 
     * @param reqData 请求报文
     * @return 报文头部
     * @see
     * @since 1.0
     */
    private CompositeData createResponseData(CompositeData reqData) {
        CompositeData resData = new CompositeData();
        CompositeData sysHead = reqData.getStruct(InterfConstant.SYS_HEAD);
        CompositeData appHead = reqData.getStruct(InterfConstant.APP_HEAD);
        CompositeData localHead = new CompositeData();
        resData.addStruct(InterfConstant.SYS_HEAD, sysHead);
        resData.addStruct(InterfConstant.APP_HEAD, appHead);
        resData.addStruct(InterfConstant.LOCAL_HEAD, localHead);
        Array arr = new Array();
        CompositeData cd = new CompositeData();
        if (isHandleOK()) {
            InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_交易状态, InterfConstant.ESB_RET_STATUS_S);
            InterfUtil.addField(cd, EsbHeadEnum.系统报文头_交易返回代码, InterfConstant.ESB_RET_CODE_000000);
            InterfUtil.addField(cd, EsbHeadEnum.系统报文头_交易返回信息, "交易成功");
        }
        else {
            InterfUtil.addField(sysHead, EsbHeadEnum.系统报文头_交易状态, InterfConstant.ESB_RET_STATUS_F);
            InterfUtil.addField(cd, EsbHeadEnum.系统报文头_交易返回代码, InterfConstant.ESB_RET_CODE_111111);
            InterfUtil.addField(cd, EsbHeadEnum.系统报文头_交易返回信息, "交易失败，失败详情：[" + getErrMsg() + "]");
        }
        arr.addStruct(cd);
        sysHead.addArray(InterfConstant.RET, arr);
        return resData;
    }

    /**
     * 抽取应答数据
     * 
     * @param entity 抽取条件
     * @return 应答数据
     * @throws Exception SQL异常
     * @see
     * @since 1.0
     */
    public abstract T selectResponseData(F entity)
        throws Exception;
}
