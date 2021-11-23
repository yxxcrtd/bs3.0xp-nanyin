package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:TRD_BANK_MANUAL_SETTLE_FLOW表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2021-11-16
 */
public class TrdBankManualSettleFlow extends BaseEntity {
    /**
     * 交收流水序号
     */
    private String seqNo;

    /**
     * 待交收序号
     */
    private String settleSeqNo;

    /**
     * 交收时间
     */
    private Date settleTime;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 交收数量
     */
    private BigDecimal settleNum;

    /**
     * 交收金额
     */
    private BigDecimal settleAmt;

    /**
     * 操作类型 数据字典
     */
    private String operType;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 交收流水序号
     * @return SEQ_NO 交收流水序号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 交收流水序号
     * @param seqNo 交收流水序号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 待交收序号
     * @return SETTLE_SEQ_NO 待交收序号
     */
    public String getSettleSeqNo() {
        return settleSeqNo;
    }

    /**
     * 待交收序号
     * @param settleSeqNo 待交收序号
     */
    public void setSettleSeqNo(String settleSeqNo) {
        this.settleSeqNo = settleSeqNo == null ? null : settleSeqNo.trim();
    }

    /**
     * 交收时间
     * @return SETTLE_TIME 交收时间
     */
    public Date getSettleTime() {
        return settleTime;
    }

    /**
     * 交收时间
     * @param settleTime 交收时间
     */
    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    /**
     * 资产代码
     * @return FINPROD_ID 资产代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 资产代码
     * @param finprodId 资产代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 资产简称
     * @return FINPROD_ABBR 资产简称
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 资产简称
     * @param finprodAbbr 资产简称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr == null ? null : finprodAbbr.trim();
    }

    /**
     * 交收数量
     * @return SETTLE_NUM 交收数量
     */
    public BigDecimal getSettleNum() {
        return settleNum;
    }

    /**
     * 交收数量
     * @param settleNum 交收数量
     */
    public void setSettleNum(BigDecimal settleNum) {
        this.settleNum = settleNum;
    }

    /**
     * 交收金额
     * @return SETTLE_AMT 交收金额
     */
    public BigDecimal getSettleAmt() {
        return settleAmt;
    }

    /**
     * 交收金额
     * @param settleAmt 交收金额
     */
    public void setSettleAmt(BigDecimal settleAmt) {
        this.settleAmt = settleAmt;
    }

    /**
     * 操作类型 数据字典
     * @return OPER_TYPE 操作类型 数据字典
     */
    public String getOperType() {
        return operType;
    }

    /**
     * 操作类型 数据字典
     * @param operType 操作类型 数据字典
     */
    public void setOperType(String operType) {
        this.operType = operType == null ? null : operType.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}