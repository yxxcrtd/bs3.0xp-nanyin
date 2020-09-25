/*
 * 文件名：InterfG1003OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.entity;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 新核心记账接口G1003输出Entity
 * 
 * @author changwenbin
 * @version 1.0 2018年12月3日
 * @see InterfG1003OutEntity
 * @since 1.0
 */
public class InterfG1003OutEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8418308339037245022L;

    /**
     * 借方交易金额
     */
    private BigDecimal drTranAmt;

    /**
     * 贷方交易金额
     */
    private BigDecimal crTranAmt;

    /**
     * 转账数组
     */
    private List<InterfG1003OutTransferEntity> transferList;

    /**
     * 备注
     */
    private String remark;

    /**
     * 卡片序号
     */
    private String cardSerialNo;

    /**
     * 获取 借方交易金额
     * 
     * @return 借方交易金额
     */
    public BigDecimal getDrTranAmt() {
        return drTranAmt;
    }

    /**
     * 设置 借方交易金额
     * 
     * @param drTranAmt 借方交易金额
     */
    @EsbName(value = "DR_TRAN_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setDrTranAmt(BigDecimal drTranAmt) {
        this.drTranAmt = drTranAmt;
    }

    /**
     * 获取 贷方交易金额
     * 
     * @return 贷方交易金额
     */
    public BigDecimal getCrTranAmt() {
        return crTranAmt;
    }

    /**
     * 设置 贷方交易金额
     * 
     * @param crTranAmt 贷方交易金额
     */
    @EsbName(value = "CR_TRAN_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    public void setCrTranAmt(BigDecimal crTranAmt) {
        this.crTranAmt = crTranAmt;
    }

    /**
     * 获取 转账数组
     * 
     * @return 转账数组
     */
    public List<InterfG1003OutTransferEntity> getTransferList() {
        return transferList;
    }

    /**
     * 设置 转账数组
     * 
     * @param transferList 转账数组
     */
    @EsbName(value = "TRANSFER_ARRAY")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public void setTransferList(List<InterfG1003OutTransferEntity> transferList) {
        this.transferList = transferList;
    }

    /**
     * 获取 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 卡片序号
     * 
     * @return 卡片序号
     */
    public String getCardSerialNo() {
        return cardSerialNo;
    }

    /**
     * 设置 卡片序号
     * 
     * @param cardSerialNo 卡片序号
     */
    @EsbName(value = "CARD_SERIAL_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setCardSerialNo(String cardSerialNo) {
        this.cardSerialNo = cardSerialNo;
    }

}
