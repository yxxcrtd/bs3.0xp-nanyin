/*
 * 文件名：InterfServiceCaller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.constant;

import com.joyintech.tams.common.base.entity.InterfSendMurex;
import com.joyintech.tams.invest.interf.dto.BatchResultDto;
import com.joyintech.tams.invest.interf.dto.CusdepOrderDto;
import com.joyintech.tams.invest.interf.dto.EsbReqRspDto;
import com.joyintech.tams.invest.interf.dto.FinProdDto;
import com.joyintech.tams.invest.interf.dto.SendEmailDto;
import com.joyintech.tams.invest.interf.dto.TradeClearDto;

/**
 * 调用模块枚举类
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月28日
 * @see InterfServiceCaller
 * @since 1.0
 */
public enum InterfServiceCaller {
    /**
     * 获取每日托管流水
     */
    获取每日托管流水(
        "INTERF_ODS_ACCOUNT_CASH", BatchResultDto.class
    ),
    /**
     * 每日上传CMS信贷资产余额信息
     */
    每日上传CMS信贷资产余额信息(
        "INTERF_CMS_CREDIT_INFO", BatchResultDto.class
    ),
    /**
     * ESB请求模块
     */
    ESB(
        "ESB", EsbReqRspDto.class
    ),
    /**
     * 产品端定时任务模块
     */
    PROD_BATCH(
        "prodBatch", BatchResultDto.class
    ),
    /**
     * wind数据接入定时任务模块
     */
    WIND_BATCH(
        "windBatch", BatchResultDto.class
    ),
    /**
     * OPICS数据接入定时任务模块
     */
    OPICS_BATCH(
        "opicsBatch", BatchResultDto.class
    ),
    /**
     * 发送已保存邮件模块
     */
    SEND_SAVED_EMAIL(
        "sendSavedEmail", SendEmailDto.class
    ),
    /**
     * 发送新建邮件模块
     */
    SEND_NEW_EMAIL(
        "sendNewEmail", SendEmailDto.class
    ),
    /**
     * 发送指令
     */
    SEND_ORDER(
        "sendOrder", TradeClearDto.class
    ),
    /**
     * 发送TA
     */
    SEND_TA(
        "sendTA", FinProdDto.class
    ),
    /**
     * 发送托管网银
     */
    SEND_CUSDEP(
        "sendCusdep", CusdepOrderDto.class
    ),
    /**
     * 现金流更新
     */
    SEND_CASH_SELECT(
            "sendCashSelect",Integer.class
    ),
    /**
     * 发送MUREX
     */
    SEND_MUREX(
        "sendMurex", InterfSendMurex.class
    );

    /**
     * 调用模块代码
     */
    private String modeCode;

    /**
     * 数据返回类型
     */
    private Class<?> returnDto;

    private InterfServiceCaller(String modeCode, Class<?> dto) {
        this.modeCode = modeCode;
        this.returnDto = dto;
    }

    /**
     * 取得调用模块代码
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String code() {
        return modeCode;
    }

    /**
     * 取得返回数据类型
     * 
     * @return 返回数据类型
     * @see
     * @since 1.0
     */
    public Class<?> dto() {
        return returnDto;
    }
}
