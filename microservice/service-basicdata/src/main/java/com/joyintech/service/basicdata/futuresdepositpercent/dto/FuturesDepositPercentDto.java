package com.joyintech.service.basicdata.futuresdepositpercent.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname FuturesDepositPercentDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 18:44 
 * @version 1.0 
 */
public class FuturesDepositPercentDto extends BaseEntity {

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 期货类型
     */
    private String futuresType;

    /**
     * 合约标的
     */
    private String futuresContractId;

    /**
     * 期货公司
     */
    private String futuresCompany;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 选中的数据
     */
    private transient List<FuturesDepositPercentDto> selectedItem;

    /**
     * 保证金比例
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal depositPercent;

    /**
     * 期货公司名称
     * @return
     */
    private String futuresCompanyName;

    public String getFuturesCompanyName() {
        return futuresCompanyName;
    }

    public void setFuturesCompanyName(String futuresCompanyName) {
        this.futuresCompanyName = futuresCompanyName;
    }

    public List<FuturesDepositPercentDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<FuturesDepositPercentDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getFuturesType() {
        return futuresType;
    }

    public void setFuturesType(String futuresType) {
        this.futuresType = futuresType;
    }

    public String getFuturesContractId() {
        return futuresContractId;
    }

    public void setFuturesContractId(String futuresContractId) {
        this.futuresContractId = futuresContractId;
    }

    public String getFuturesCompany() {
        return futuresCompany;
    }

    public void setFuturesCompany(String futuresCompany) {
        this.futuresCompany = futuresCompany;
    }

    public Date getEffDate() {
        return effDate;
    }
    public String getEffDateStr() {
        return DateUtil.format(this.getEffDate(), "yyyy-MM-dd");
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public BigDecimal getDepositPercent() {
        return depositPercent;
    }

    public void setDepositPercent(BigDecimal depositPercent) {
        this.depositPercent = depositPercent;
    }
}
