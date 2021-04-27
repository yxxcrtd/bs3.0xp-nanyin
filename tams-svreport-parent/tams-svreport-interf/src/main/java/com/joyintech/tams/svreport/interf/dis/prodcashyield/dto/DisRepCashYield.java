package com.joyintech.tams.svreport.interf.dis.prodcashyield.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DisRepCashYield extends BaseEntity {




    /**
     * 导出类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * uuid主键
     */
    private String datauuid;

    public String getDatauuid() {
        return datauuid;
    }

    public void setDatauuid(String datauuid) {
        this.datauuid = datauuid;
    }

    /**
     * 产品代码
     */
    private String busiId;
    private String busiIdName;

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流名称
     */
    private String cashName;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 现金流二级类型
     */
    private String cashType2;
    private String cashType2Name;

    /**
     * 启用日
     */
    private Date startDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 导入方法
     */
    private String inputType;

    /**
     * 导入用户
     */
    private String inputUser;

    /**
     * 适配器ID

     */
    private String adapterId;

    /**
     * 数据状态

     */
    private String status;
    private String statusName;
    /**
     * 创建人

     */
    private String createUser;
    /**
     * 创建时间

     */
    private Date createTime;
    /**
     * 数据来源

     */
    private String dataSource;
    private String dataSourceName;

    public String getBusiIdName() {
        return busiIdName;
    }

    public void setBusiIdName(String busiIdName) {
        this.busiIdName = busiIdName;
    }

    public String getCashType2Name() {
        return cashType2Name;
    }

    public void setCashType2Name(String cashType2Name) {
        this.cashType2Name = cashType2Name;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    /**
     * 选择项
     */
    private List<DisRepCashYield> selectedItem;

    public List<DisRepCashYield> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<DisRepCashYield> selectedItem) {
        this.selectedItem = selectedItem;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 费率/收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 产品代码
     * @param busiId 产品代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 现金流代码
     * @return 现金流代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    /**
     * 取得 现金流名称
     * @return 现金流名称
     */
    public String getCashName() {
        return cashName;
    }

    /**
     * 设置 现金流名称
     * @param cashName 现金流名称
     */
    public void setCashName(String cashName) {
        this.cashName = cashName == null ? null : cashName.trim();
    }

    /**
     * 取得 现金流类型
     * @return 现金流类型
     */
    public String getCashType() {
        return cashType;
    }

    /**
     * 设置 现金流类型
     * @param cashType 现金流类型
     */
    public void setCashType(String cashType) {
        this.cashType = cashType == null ? null : cashType.trim();
    }

    /**
     * 取得 现金流二级类型
     * @return 现金流二级类型
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 现金流二级类型
     * @param cashType2 现金流二级类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2 == null ? null : cashType2.trim();
    }

    /**
     * 取得 启用日
     * @return 启用日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 启用日
     * @param startDate 启用日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 导入方法
     * @return 导入方法
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 导入方法
     * @param inputType 导入方法
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 导入用户
     * @return 导入用户
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 导入用户
     * @param inputUser 导入用户
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 适配器ID

     * @return 适配器ID

     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 适配器ID

     * @param adapterId 适配器ID

     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }

    /**
     * 取得 费率/收益率
     * @return 费率/收益率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 费率/收益率
     * @param rate 费率/收益率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}