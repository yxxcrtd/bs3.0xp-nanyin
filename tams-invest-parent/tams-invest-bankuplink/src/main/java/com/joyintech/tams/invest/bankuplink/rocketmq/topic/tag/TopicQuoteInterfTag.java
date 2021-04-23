/*
 * 文件名：BankUplinkMQTag.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.bankuplink.rocketmq.topic.tag;

import org.apache.commons.lang3.StringUtils;

/**
 * 银行间上行模块需要订阅主题的TAG
 * @author Jiaoxujin
 * @version 1.0 2020年5月18日
 */
public enum TopicQuoteInterfTag {
    /**
     * 现券二级指令新增
     */
    现券二级指令新增(
        "bondEntrustPublish"
    ),

    /**
     * 现券委托指令列表刷新
     */
    现券委托指令列表刷新(
        "bondEntrustReload"
    ),

    /**
     * 回购委托指令列表刷新
     */
    回购委托指令列表刷新(
        "repoEntrustReload"
    );

    /**
     * 对应值
     */
    private String value;

    TopicQuoteInterfTag(String value) {
        this.value = value;
    }

    /**
     * @param value值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static TopicQuoteInterfTag get(String value) {
        TopicQuoteInterfTag[] values = TopicQuoteInterfTag.values();
        for (TopicQuoteInterfTag code : values) {
            if (StringUtils.equals(value, code.getValue())) {
                return code;
            }
        }
        return null;
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
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过value生成枚举
     *
     * @param value value值
     * @return DictCode
     */
    public static TopicQuoteInterfTag fromValue(String value) {
        for (TopicQuoteInterfTag dictCode : TopicQuoteInterfTag.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }

}
