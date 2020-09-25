/*
 * 文件名：InterfG1010InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1010.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 新核心记账撤销1010接口输入entity
 * 
 * @author changwenbin
 * @version 1.0 2018年11月28日
 * @see InterfG1010InEntity
 * @since 1.0
 */
public class InterfG1010InEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -5255404639058557401L;

    /**
     * 原交易流水号
     */
    private String oldtranseqno;

    /**
     * 标志
     */
    private String flag;

    /**
     * 获取 原交易流水号
     * 
     * @return 原交易流水号
     */
    public String getOldtranseqno() {
        return oldtranseqno;
    }

    /**
     * 设置 原交易流水号
     * 
     * @param oldtranseqno 原交易流水号
     */
    @EsbName(value = "OLD_TRAN_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    @Required
    public void setOldtranseqno(String oldtranseqno) {
        this.oldtranseqno = oldtranseqno;
    }

    /**
     * 获取 标志
     * 
     * @return 标志
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置 标志
     * 
     * @param flag 标志
     */
    @EsbName(value = "FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    @Required
    public void setFlag(String flag) {
        this.flag = flag;
    }

}
