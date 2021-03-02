package com.joyintech.tams.svreport.interf.chb.investmentsinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_RATE表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbAssetInfoRate extends BaseEntity{
    /**
     * 交易编号
     */
    private String seqno;

    /**
     * 资产代码
     */
    private String assetId;
    /**
     * 资产类型
     */
    private String finprodType2;

    /**
     * 变动日期
     */
    private Date changeDate;
    /**
     * 资产最新收益率
     */
    private BigDecimal assetRate;
    /**
     * 资产分红或红利再投单位净值
     */
    private BigDecimal assetBonusReinvestRate;
    /**
     * 收盘价
     */
    private BigDecimal closePriceFull;
    /**
     * 成本
     */
    private BigDecimal costAmt;
    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易编号
     * @return
     */
    private  String tradeId;
    /**
     * 资金账户
     */
    private  String scino;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public BigDecimal getAssetRate() {
        return assetRate;
    }

    public void setAssetRate(BigDecimal assetRate) {
        this.assetRate = assetRate;
    }

    public BigDecimal getAssetBonusReinvestRate() {
        return assetBonusReinvestRate;
    }

    public void setAssetBonusReinvestRate(BigDecimal assetBonusReinvestRate) {
        this.assetBonusReinvestRate = assetBonusReinvestRate;
    }

    public BigDecimal getClosePriceFull() {
        return closePriceFull;
    }

    public void setClosePriceFull(BigDecimal closePriceFull) {
        this.closePriceFull = closePriceFull;
    }

    public BigDecimal getCostAmt() {
        return costAmt;
    }

    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRevDate() {
        return revDate;
    }

    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getScino() {
        return scino;
    }

    public void setScino(String scino) {
        this.scino = scino;
    }
}