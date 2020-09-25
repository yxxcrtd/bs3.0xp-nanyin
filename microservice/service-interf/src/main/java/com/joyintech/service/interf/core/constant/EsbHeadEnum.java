/*
 * 文件名：EsbHeadEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

import com.joyintech.service.interf.core.annotation.EsbFieldType;

/**
 * ESB报文头字段标准定义
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月25日
 * @see EsbHeadEnum
 * @since 1.0
 */
public enum EsbHeadEnum implements EsbHeadCol {
    /**
     * 系统报文头_服务代码
     */
    系统报文头_服务代码(
        "SERVICE_CODE", EsbFieldType.STRING, 30
    ),
    /**
     * 系统报文头_请求系统编号
     */
    系统报文头_请求系统编号(
        "CONSUMER_ID", EsbFieldType.STRING, 6
    ),
    /**
     * 系统报文头_服务场景
     */
    系统报文头_服务场景(
        "SERVICE_SCENE", EsbFieldType.STRING, 2
    ),
    /**
     * 系统报文头_交易流水号
     */
    系统报文头_交易流水号(
        "CONSUMER_SEQ_NO", EsbFieldType.STRING, 42
    ),
    /**
     * 系统报文头_交易发起的日期
     */
    系统报文头_交易发起的日期(
        "TRAN_DATE", EsbFieldType.STRING, 8
    ),
    /**
     * 系统报文头_交易发起的时间
     */
    系统报文头_交易发起的时间(
        "TRAN_TIMESTAMP", EsbFieldType.STRING, 9
    ),
    /**
     * 系统报文头_交易状态
     */
    系统报文头_交易状态(
        "RET_STATUS", EsbFieldType.STRING, 1
    ),
    /**
     * 系统报文头_交易返回代码
     */
    系统报文头_交易返回代码(
        "RET_CODE", EsbFieldType.STRING, 15
    ),
    /**
     * 系统报文头_交易返回信息
     */
    系统报文头_交易返回信息(
        "RET_MSG", EsbFieldType.STRING, 512
    ),
    /**
     * 系统报文头_交易码
     */
    系统报文头_交易码(
        "TRAN_CODE", EsbFieldType.STRING, 32
    ),
    /**
     * 系统报文头_文件路径
     */
    系统报文头_文件路径(
        InterfConstant.KEY_FILE_PATH, EsbFieldType.STRING, 512
    ),
    /**
     * 应用报文头_发送方机构ID
     */
    应用报文头_发送方机构ID(
        "BRANCH_ID", EsbFieldType.STRING, 9
    ),
    /**
     * 应用报文头_服务请求者身份
     */
    应用报文头_服务请求者身份(
        "USER_ID", EsbFieldType.STRING, 30
    ),
    /**
     * 应用报文头_发起方业务流水号
     */
    应用报文头_发起方业务流水号(
        "BIZ_SEQ_NO", EsbFieldType.STRING, 42
    ),
    /**
     * 应用报文头_需冲正的原流水交易日前
     */
    应用报文头_需冲正的原流水交易日前(
        "REVERSAL_DATE", EsbFieldType.STRING, 8
    ),
    /**
     * 应用报文头_需冲正的原流水号
     */
    应用报文头_需冲正的原流水号(
        "REVERSAL_SEQ_NO", EsbFieldType.STRING, 42
    ),
    /**
     * 应用报文头_上翻_下翻标志
     */
    应用报文头_上翻_下翻标志(
        "PGUP_OR_PGDN", EsbFieldType.STRING, 1
    ),
    /**
     * 应用报文头_本次返回查询结果记录总数
     */
    应用报文头_本次返回查询结果记录总数(
        "RESULT_NUM", EsbFieldType.STRING, 3
    ),
    /**
     * 应用报文头_符合查询条件的记录总数
     */
    应用报文头_符合查询条件的记录总数(
        "TOTAL_NUM", EsbFieldType.STRING, 5
    ),
    /**
     * 应用报文头_查询结果定位串
     */
    应用报文头_查询结果定位串(
        "QUERY_KEY", EsbFieldType.STRING, 256
    ),
    /**
     * 应用报文头_是否结束
     */
    应用报文头_是否结束(
        "END_FLAG", EsbFieldType.STRING, 1
    ),
    /**
     * 应用报文头_要求每页返回记录总数
     */
    应用报文头_要求每页返回记录总数(
        "PER_PAGE_NUM", EsbFieldType.STRING, 3
    );

    /**
     * 字段名称
     */
    private String colName;

    /**
     * 数据类型
     */
    private String colType;

    /**
     * 数据长度
     */
    private int colLength;

    /**
     * @param colname 字段名称
     * @param coltype 数据类型
     * @param collength 数据长度
     * @param required 是否必输
     */
    EsbHeadEnum(String colname, String coltype, int collength) {
        this.colName = colname;
        this.colType = coltype;
        this.colLength = collength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String colName() {
        return colName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String colType() {
        return colType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int colLength() {
        return colLength;
    }
}
