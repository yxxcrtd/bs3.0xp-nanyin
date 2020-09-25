/*
 * 文件名：InterfG1003InTransferEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ig1003.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * G1003转账输入entity
 * 
 * @author changwenbin
 * @version 1.0 2018年11月14日
 * @see InterfG1003InTransferEntity
 * @since 1.0
 */
public class InterfG1003InTransferEntity extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8858042113745245692L;

    /**
     * 流水号
     */
    private String seqno;

    /**
     * 借贷标志
     */
    private String drcrflag;

    /**
     * 转出账号
     */
    private String outacctno;

    /**
     * 通存通兑标志
     */
    private String intertranflag;

    /**
     * 资金划出方户名
     */
    private String fromacctname;

    /**
     * 转出行号
     */
    private String outbranchno;

    /**
     * 转入账号
     */
    private String inacctno;

    /**
     * 入账账号户名
     */
    private String inactname;

    /**
     * 转入行号
     */
    private String inbranceno;

    /**
     * 交易金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 钞汇标志
     */
    private String cattflag;

    /**
     * 转出账户用途
     */
    private String outacctusage;

    /**
     * 备注2
     */
    private String remark2;

    /**
     * 转入账户用途
     */
    private String inacctusage;

    /**
     * 获取 流水号
     * 
     * @return 流水号
     */
    @EsbName(value = "SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * 
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    /**
     * 获取 借贷标志
     * 
     * @return 借贷标志
     */
    @EsbName(value = "DR_CR_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    public String getDrcrflag() {
        return drcrflag;
    }

    /**
     * 设置 借贷标志
     * 
     * @param drcrflag 借贷标志
     */
    public void setDrcrflag(String drcrflag) {
        this.drcrflag = drcrflag;
    }

    /**
     * 获取 转出账号
     * 
     * @return 转出账号
     */
    @EsbName(value = "OUT_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getOutacctno() {
        return outacctno;
    }

    /**
     * 设置 转出账号
     * 
     * @param outacctno 转出账号
     */
    public void setOutacctno(String outacctno) {
        this.outacctno = outacctno;
    }

    /**
     * 获取 通存通兑标志
     * 
     * @return 通存通兑标志
     */
    @EsbName(value = "INTER_TRAN_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getIntertranflag() {
        return intertranflag;
    }

    /**
     * 设置 通存通兑标志
     * 
     * @param intertranflag 通存通兑标志
     */
    public void setIntertranflag(String intertranflag) {
        this.intertranflag = intertranflag;
    }

    /**
     * 获取 资金划出方户名
     * 
     * @return 资金划出方户名
     */
    @EsbName(value = "FROM_ACCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    public String getFromacctname() {
        return fromacctname;
    }

    /**
     * 设置 资金划出方户名
     * 
     * @param fromacctname 资金划出方户名
     */
    public void setFromacctname(String fromacctname) {
        this.fromacctname = fromacctname;
    }

    /**
     * 获取 转出行号
     * 
     * @return 转出行号
     */
    @EsbName(value = "OUT_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOutbranchno() {
        return outbranchno;
    }

    /**
     * 设置 转出行号
     * 
     * @param outbranchno 转出行号
     */
    public void setOutbranchno(String outbranchno) {
        this.outbranchno = outbranchno;
    }

    /**
     * 获取 转入账号
     * 
     * @return 转入账号
     */
    @EsbName(value = "IN_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getInacctno() {
        return inacctno;
    }

    /**
     * 设置 转入账号
     * 
     * @param inacctno 转入账号
     */
    public void setInacctno(String inacctno) {
        this.inacctno = inacctno;
    }

    /**
     * 获取 入账账号户名
     * 
     * @return 入账账号户名
     */
    @EsbName(value = "IN_ACCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    public String getInactname() {
        return inactname;
    }

    /**
     * 设置 入账账号户名
     * 
     * @param inactname 入账账号户名
     */
    public void setInactname(String inactname) {
        this.inactname = inactname;
    }

    /**
     * 获取 转入行号
     * 
     * @return 转入行号
     */
    @EsbName(value = "IN_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getInbranceno() {
        return inbranceno;
    }

    /**
     * 设置 转入行号
     * 
     * @param inbranceno 转入行号
     */
    public void setInbranceno(String inbranceno) {
        this.inbranceno = inbranceno;
    }

    /**
     * 获取 交易金额
     * 
     * @return 交易金额
     */
    @EsbName(value = "AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 交易金额
     * 
     * @param amt 交易金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 获取 币种
     * 
     * @return 币种
     */
    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 钞汇标志
     * 
     * @return 钞汇标志
     */
    @EsbName(value = "CA_TT_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    public String getCattflag() {
        return cattflag;
    }

    /**
     * 设置 钞汇标志
     * 
     * @param cattflag 钞汇标志
     */
    public void setCattflag(String cattflag) {
        this.cattflag = cattflag;
    }

    /**
     * 获取 转出账户用途
     * 
     * @return 转出账户用途
     */
    @EsbName(value = "OUT_ACCT_USAGE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getOutacctusage() {
        return outacctusage;
    }

    /**
     * 设置 转出账户用途
     * 
     * @param outacctusage 转出账户用途
     */
    public void setOutacctusage(String outacctusage) {
        this.outacctusage = outacctusage;
    }

    /**
     * 获取 备注2
     * 
     * @return 备注2
     */
    @EsbName(value = "REMARK2")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemark2() {
        return remark2;
    }

    /**
     * 设置 备注2
     * 
     * @param remark2 备注2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    /**
     * 获取 转入账户用途
     * 
     * @return 转入账户用途
     */
    @EsbName(value = "IN_ACCT_USAGE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getInacctusage() {
        return inacctusage;
    }

    /**
     * 设置 转入账户用途
     * 
     * @param inacctusage 转入账户用途
     */
    public void setInacctusage(String inacctusage) {
        this.inacctusage = inacctusage;
    }

}
