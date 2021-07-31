/*
 * 文件名：HeadEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.constant;

import java.util.function.Consumer;

/**
 * 报文头字段枚举
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月9日
 * @see HeadEnum
 * @since 1.0
 */
public enum HeadEnum {

    /**
     * 系统代码
     */
    STD400AQID(
        "std400aqid", "系统代码"
    ),
    /**
     * 接口ID
     */
    STDPROCODE(
        "stdprocode", "接口ID"
    ),
    /**
     * 交易流水号
     */
    STDTERMTRC(
        "stdtermtrc", "交易流水号"
    ),
    /**
     * 发送日期
     */
    STD400RBDT(
        "std400rbdt", "发送日期"
    ),
    /**
     * 发起方系统时间戳
     */
    STD400RSTS(
        "std400rsts", "发起方系统时间戳"
    ),
    /**
     * 发送时间
     */
    STD400RTIM(
        "std400rtim", "发送时间"
    ),
    /**
     * 信息类型
     */
    STD400MSGT(
        "std400msgt", "信息类型"
    ),
    /**
     * 返回码
     */
    STD400MGID(
        "std400mgid", "返回码"
    ),
    /**
     * 返回消息
     */
    STDRTNINFO(
        "stdrtninfo", "返回消息"
    ),
    /**
     * 报文类型
     */
    STDMSGTYPE(
        "stdmsgtype", "报文类型"
    ),
    /**
     * 多页查询标识
     */
    STD400PGQF(
        "std400pgqf", "多页查询标识"
    ),
    /**
     * 业务归属系统代码
     */
    STD400BSYS(
        "std400bsys", "业务归属系统代码"
    ),
    /**
     * 报文长度
     */
    STD400IMTL(
        "std400imtl", "报文长度"
    ),
    /**
     * 返回备注消息
     */
    STDREMARK(
        "stdremark", "返回备注消息"
    ),
    /**
     * 系统代码
     */
    SYSTEMCODE(
        "riskinf_system_code", "系统代码"
    ),;

    /**
     * 字段
     */
    private String field;

    /**
     * 名称
     */
    private String discription;

    private HeadEnum(String value, String discription) {
        this.field = value;
        this.discription = discription;
    }

    public String field() {
        return this.field;
    }

    public String discription() {
        return this.discription;
    }

    public static final void forEach(Consumer<HeadEnum> consumer) {
        HeadEnum[] arr = HeadEnum.values();
        if (arr != null && arr.length > 0 && null != consumer) {
            for (HeadEnum head : arr) {
                consumer.accept(head);
            }
        }
    }
}
