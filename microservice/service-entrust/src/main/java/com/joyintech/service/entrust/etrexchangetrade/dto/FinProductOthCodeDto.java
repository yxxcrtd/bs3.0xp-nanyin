package com.joyintech.service.entrust.etrexchangetrade.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinProductOthCodeDto extends BaseEntity {

    /**
     * 交易场所
     */
    public String tradeMarket;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 业务类型
     01：回售
     02：标准券出入库
     03：可转债信用申购
     04：可转债转股
     05：配债
     06：配股
     07：新股申购
     */
    private String othType;
    /**
     * 业务类型（暂时恢复，废弃）
     */
    private String othCodeType;

    /**
     * 申报代码
     */
    private String othCode;

    /**
     * 申报资产名称
     */
    private String othName;

    /**
     * 申报价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal declaredPrice;

    /**
     * 申报起始日
     */
    private Date declaredStartDate;

    /**
     * 申报终止日
     */
    private Date declaredEndDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 业务类型（暂时恢复，废弃）
     * @return 业务类型（暂时恢复，废弃）
     */
    public String getOthCodeType() {
        return othCodeType;
    }

    /**
     * 设置 业务类型（暂时恢复，废弃）
     * @param othCodeType 业务类型（暂时恢复，废弃）
     */
    public void setOthCodeType(String othCodeType) {
        this.othCodeType = othCodeType == null ? null : othCodeType.trim();
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getOthType() {
        return othType;
    }

    public void setOthType(String othType) {
        this.othType = othType;
    }

    /**
     * 取得 申报代码
     * @return 申报代码
     */
    public String getOthCode() {
        return othCode;
    }

    /**
     * 设置 申报代码
     * @param othCode 申报代码
     */
    public void setOthCode(String othCode) {
        this.othCode = othCode == null ? null : othCode.trim();
    }

    /**
     * 取得 申报资产名称
     * @return 申报资产名称
     */
    public String getOthName() {
        return othName;
    }

    /**
     * 设置 申报资产名称
     * @param othName 申报资产名称
     */
    public void setOthName(String othName) {
        this.othName = othName == null ? null : othName.trim();
    }

    /**
     * 取得 申报价格
     * @return 申报价格
     */
    public BigDecimal getDeclaredPrice() {
        return declaredPrice;
    }

    /**
     * 设置 申报价格
     * @param declaredPrice 申报价格
     */
    public void setDeclaredPrice(BigDecimal declaredPrice) {
        this.declaredPrice = declaredPrice;
    }

    /**
     * 取得 申报起始日
     * @return 申报起始日
     */
    public Date getDeclaredStartDate() {
        return declaredStartDate;
    }

    /**
     * 设置 申报起始日
     * @param declaredStartDate 申报起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDeclaredStartDate(Date declaredStartDate) {
        this.declaredStartDate = declaredStartDate;
    }

    /**
     * 取得 申报终止日
     * @return 申报终止日
     */
    public Date getDeclaredEndDate() {
        return declaredEndDate;
    }

    /**
     * 设置 申报终止日
     * @param declaredEndDate 申报终止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDeclaredEndDate(Date declaredEndDate) {
        this.declaredEndDate = declaredEndDate;
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
}