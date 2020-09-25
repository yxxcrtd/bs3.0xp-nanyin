/*
 * 文件名：interfIg3023inEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig3023.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 接口G3023-输入
 * 
 * @author whm
 * @version 1.0 2018年11月28日
 * @see InterfG3023InEntity
 * @since 1.0
 */
public class InterfG3023InEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -653596357407620404L;

    /**
     * 交易流水号
     */
    private String tranSeqNo;

    /**
     * 获取 交易流水号
     * 
     * @return 交易流水号.
     */
    public String getTranSeqNo() {
        return tranSeqNo;
    }

    /**
     * 设置 交易流水号
     * 
     * @param tranSeqNo 交易流水号
     */
    @EsbName(value = "TRAN_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    @Required
    public void setTranSeqNo(String tranSeqNo) {
        this.tranSeqNo = tranSeqNo;
    }

}
