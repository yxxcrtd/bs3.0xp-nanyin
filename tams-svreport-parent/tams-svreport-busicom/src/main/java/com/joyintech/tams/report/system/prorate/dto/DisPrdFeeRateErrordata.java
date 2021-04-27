package com.joyintech.tams.report.system.prorate.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class DisPrdFeeRateErrordata extends BaseEntity {
    /**
    * uuid主键
    */
    private String datauuid;

    /**
 * 数据状态
 */
    private String status;
    /**
 * 产品代码
 */
    private String prodId;
    /**
 * 产品名称
 */
    private String prodName;
    /**
 * 启用日
 */
    private Date startDate;
    /**
 * 信披费用类型
 */
    private String cashType2;
    /**
 * 费率
 */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal feeRate;
    /**
 * 创建人
 */
    private String createUser;
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
 * 数据来源
 */
    private String dataSource;

    public String getDatauuid() {
        return datauuid;
    }

    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCashType2() {
        return cashType2;
    }

    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}
