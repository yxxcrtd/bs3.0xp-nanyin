/*
 * 文件名：InterfColumns.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.constant;

/**
 * 风险接口规范
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月26日
 * @see InfColumns
 * @since 1.0
 */
public enum InfColumns {
    /**
     * 系统代码
     */
    COM_SYSCODE(
        "std400aqid", 4
    ),
    /**
     * 接口ID
     */
    COM_INFID(
        "stdprocode", 6
    ),
    /**
     * 客户端IP
     */
    COM_IP(
        "consumerIP", 128
    ),
    /**
     * 用户端口号
     */
    COM_PORT(
        "consumerPort", 5
    ), COM_OPTYPE(
        "", 1
    ),
    /**
     * 达到预警线是否需要人工确认
     */
    COM_ISWARNNDCFM(
        "isWarnNeedConfirm", 1
    ),
    /**
     * 达到审批线是否需要人工确认
     */
    COM_ISAPPRNDCFM(
        "isApprNeedConfirm", 1
    ),
    /**
     * 交易流水号
     */
    COM_SEQ_NO(
        "stdtermtrc", 36
    ),
    /**
     * 发送日期
     */
    COM_TRANDATE(
        "std400rbdt", 10
    ),
    /**
     * 发起方系统时间戳
     */
    COM_STD400RSTS(
        "std400rsts", 26
    ),
    /**
     * 发送时间
     */
    COM_TRANTIME(
        "std400rtim", 9
    ),
    /**
     * 信息类型
     */
    COM_STD400MSGT(
        "std400msgt", 1
    ),
    /**
     * 返回码
     */
    COM_RTNCODE(
        "std400mgid", 7
    ),
    /**
     * 返回消息
     */
    COM_RTNMSG(
        "stdrtninfo", 1024
    ),
    /**
     * 报文类型
     */
    COM_STDMSGTYPE(
        "stdmsgtype", 4
    ),
    /**
     * 多页查询标识
     */
    COM_STD400PGQF(
        "std400pgqf", 1
    ),
    /**
     * 业务归属系统代码
     */
    COM_STD400BSYS(
        "std400bsys", 4
    ),
    /**
     * 报文长度
     */
    COM_STD400IMTL(
        "std400imtl", 7
    ),
    /**
     * 系统标识
     */
    COM_SYS_FLAG(
        "sysFlag", 2
    ),
    /**
     * 返回备注消息
     */
    COM_STDREMARK(
            "stdremark", 1024
    ),
    /**
     * 平台时间
     */
    COM_SYSDATE(
        "sysDate", 8
    );

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段类型
     */
    private Class<?> fieldType;

    /**
     * 字段长度
     */
    private int fieldSize;

    private InfColumns(String name, Class<?> type, int size) {
        this.fieldName = name;
        this.fieldType = type;
        this.fieldSize = size;
    }

    private InfColumns(String name, int length) {
        this(name, String.class, length);
    }

    /**
     * 取得 字段名称
     * 
     * @return 字段名称
     * @see
     * @since 1.0
     */
    public String fieldName() {
        return fieldName;
    }

    /**
     * 取得 字段类型
     * 
     * @return 字段类型
     * @see
     * @since 1.0
     */
    public Class<?> fieldType() {
        return fieldType;
    }

    /**
     * 取得 字段长度
     * 
     * @return 字段长度
     * @see
     * @since 1.0
     */
    public int fieldSize() {
        return fieldSize;
    }
}
