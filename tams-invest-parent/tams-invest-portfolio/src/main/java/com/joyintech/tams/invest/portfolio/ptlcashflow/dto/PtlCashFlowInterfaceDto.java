/*
 * 文件名：PtlCashFlowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlcashflow.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.common.BussTrdTransferOrderBase;
import com.joyintech.tams.invest.portfolio.ptlcashflow.validate.PtlCashFlowValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 自定义现金流接口发送使用
 * 〈功能详细描述〉
 * @author xkni
 * @version 1.0 2020年3月2日
 * @see PtlCashFlowInterfaceDto
 * @since 1.0
 */
public class PtlCashFlowInterfaceDto{

    /**
     * 交易编号
     */
    private String TRADE_ID;
    /**
     * 关联业务编号
     */
    private String F_TRADE_ID;
    /**
     * 交易类型
     */
    private String TRADE_TYPE;
    /**
     * 组合代码
     */
    private String PORTFOLIO_ID;
    /**
     *组合名称
     */
    private String PORTFOLIO_NAME;
    /**
     *交易对手代码
     */
    private String COUNTER_ID;
    /**
     *交易对手名称
     */
    private String COUNTER_NAME;
    /**
     * 资产代码
     */
    private String FINPROD_ID;
    /**
     * 资产名称
     */
    private String FINPROD_NAME;
    /**
     * 交易日
     */
    private Date TRADE_DATE;
    /**
     * 交割日
     */
    private Date SETTLE_DATE;
    /**
     * 现金流类型
     */
    private String CASH_TYPE;
    /**
     * 收付方向
     */
    private String PS;
    /**
     * 交易金额
     */
    private BigDecimal TRADE_AMT;
    /**
     * 结算币种
     */
    private String CCY;
    /**
     * 交易状态
     */
    private String TRADE_STATUS;
    /**
     * 原交易编号
     */
    private String P_TRADE_ID;
    /**
     * 撤销说明
     */
    private String CANCEL_REMARK;
    /**
     * 备注
     */
    private String REMARK;
    /**
     * 参与人信息
     */

    private List<PartyInfoDto> PARTY_INFO;
    /**
     * 划款信息
     */

    private List<SettleInfoDto> SETTLE_INFO;


    public String getTRADE_ID() {
        return TRADE_ID;
    }

    public void setTRADE_ID(String TRADE_ID) {
        this.TRADE_ID = TRADE_ID;
    }

    public String getF_TRADE_ID() {
        return F_TRADE_ID;
    }

    public void setF_TRADE_ID(String f_TRADE_ID) {
        F_TRADE_ID = f_TRADE_ID;
    }

    public String getTRADE_TYPE() {
        return TRADE_TYPE;
    }

    public void setTRADE_TYPE(String TRADE_TYPE) {
        this.TRADE_TYPE = TRADE_TYPE;
    }

    public String getPORTFOLIO_ID() {
        return PORTFOLIO_ID;
    }

    public void setPORTFOLIO_ID(String PORTFOLIO_ID) {
        this.PORTFOLIO_ID = PORTFOLIO_ID;
    }

    public String getPORTFOLIO_NAME() {
        return PORTFOLIO_NAME;
    }

    public void setPORTFOLIO_NAME(String PORTFOLIO_NAME) {
        this.PORTFOLIO_NAME = PORTFOLIO_NAME;
    }

    public String getCOUNTER_ID() {
        return COUNTER_ID;
    }

    public void setCOUNTER_ID(String COUNTER_ID) {
        this.COUNTER_ID = COUNTER_ID;
    }

    public String getCOUNTER_NAME() {
        return COUNTER_NAME;
    }

    public void setCOUNTER_NAME(String COUNTER_NAME) {
        this.COUNTER_NAME = COUNTER_NAME;
    }

    public String getFINPROD_ID() {
        return FINPROD_ID;
    }

    public void setFINPROD_ID(String FINPROD_ID) {
        this.FINPROD_ID = FINPROD_ID;
    }

    public String getFINPROD_NAME() {
        return FINPROD_NAME;
    }

    public void setFINPROD_NAME(String FINPROD_NAME) {
        this.FINPROD_NAME = FINPROD_NAME;
    }

    public Date getTRADE_DATE() {
        return TRADE_DATE;
    }

    public void setTRADE_DATE(Date TRADE_DATE) {
        this.TRADE_DATE = TRADE_DATE;
    }

    public Date getSETTLE_DATE() {
        return SETTLE_DATE;
    }

    public void setSETTLE_DATE(Date SETTLE_DATE) {
        this.SETTLE_DATE = SETTLE_DATE;
    }

    public String getCASH_TYPE() {
        return CASH_TYPE;
    }

    public void setCASH_TYPE(String CASH_TYPE) {
        this.CASH_TYPE = CASH_TYPE;
    }

    public String getPS() {
        return PS;
    }

    public void setPS(String PS) {
        this.PS = PS;
    }

    public BigDecimal getTRADE_AMT() {
        return TRADE_AMT;
    }

    public void setTRADE_AMT(BigDecimal TRADE_AMT) {
        this.TRADE_AMT = TRADE_AMT;
    }

    public String getCCY() {
        return CCY;
    }

    public void setCCY(String CCY) {
        this.CCY = CCY;
    }

    public String getTRADE_STATUS() {
        return TRADE_STATUS;
    }

    public void setTRADE_STATUS(String TRADE_STATUS) {
        this.TRADE_STATUS = TRADE_STATUS;
    }

    public String getP_TRADE_ID() {
        return P_TRADE_ID;
    }

    public void setP_TRADE_ID(String p_TRADE_ID) {
        P_TRADE_ID = p_TRADE_ID;
    }

    public String getCANCEL_REMARK() {
        return CANCEL_REMARK;
    }

    public void setCANCEL_REMARK(String CANCEL_REMARK) {
        this.CANCEL_REMARK = CANCEL_REMARK;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public List<PartyInfoDto> getPARTY_INFO() {
        return PARTY_INFO;
    }

    public void setPARTY_INFO(List<PartyInfoDto> PARTY_INFO) {
        this.PARTY_INFO = PARTY_INFO;
    }

    public List<SettleInfoDto> getSETTLE_INFO() {
        return SETTLE_INFO;
    }

    public void setSETTLE_INFO(List<SettleInfoDto> SETTLE_INFO) {
        this.SETTLE_INFO = SETTLE_INFO;
    }
}
