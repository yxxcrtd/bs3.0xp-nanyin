/*
 * 文件名：MsgTypeEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.cfets.constant;


/**
 * 报文类型
 * 〈功能详细描述〉
 * @author etliu
 * @version 1.0 2021年1月8日
 * @see MsgTypeEnum
 * @since 1.0
 */
public enum MsgTypeEnum {
    // 发送、修改报价
    S("S"),
    // 撤销报价
    Z("Z"),
    // 报价状态报告
    AI("AI"),
    // 回复报价
    AJ("AJ"),
    // 请求做市机构报价
    R("R"),
    // 撤销报价请求
    U32("U32"),
    // 报价请求接收反馈
    U29("U29"),
    // 拒绝报价请求
    AG("AG"),
    // 订单状态更新通知或成交通知
    MSGTYPE_8("8"),
    // 业务操作错误反馈
    j("j")
    ;

    /**
     * value
     */
    private String value;

    MsgTypeEnum(String value) {
        this.value = value;
    }
    
    /**
     * 获取 value
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value
     *            value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
