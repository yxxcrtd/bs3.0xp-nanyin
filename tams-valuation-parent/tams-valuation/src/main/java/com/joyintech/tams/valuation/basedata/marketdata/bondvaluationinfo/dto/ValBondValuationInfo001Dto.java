/*
 * 文件名：ValBondValuationInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.valuation.basedata.marketdata.bondvaluationinfo.validator.ValBondValuationInfo001DtoCheckValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 债券估值行情dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValBondValuationInfo001DtoCheckValidator.class)
public class ValBondValuationInfo001Dto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 债券代码
     */
    @PrimaryKey(value = Field.PK_1)
    private String secId;

    /**
     * 获取 secId
     * 
     * @return secId.
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 secId
     * 
     * @param secId secId
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 债券简称
     */
    private String finprodAbbr;

    /**
     * 估值来源
     */
    @PrimaryKey(value = Field.PK_2)
    private String valueSource;

    /**
     * 估值日
     */
    @PrimaryKey(value = Field.PK_3)
    private String valueDate;

    /**
     * 修改前的pk1、pk2、pk3组成的pk
     */
    @PrimaryKey(value = Field.PK_4)
    private String bfUpdPk;
    
    /**
     * 剩余期限
     */
    private BigDecimal lastPeriod;

    /**
     * 市价全价
     */
    private BigDecimal price;

    /**
     * 市价净价
     */
    private BigDecimal netprice;

    /**
     * 市价久期
     */
    private BigDecimal mDuration;

    /**
     * 市价凸性
     */
    private BigDecimal mConvexity;

    /**
     * 基点价值
     */
    private BigDecimal bpvalue;

    /**
     * 利差久期
     */
    private BigDecimal sduration;

    /**
     * 利差凸性
     */
    private BigDecimal scnvxty;

    /**
     * 利率久期
     */
    private BigDecimal interestDuration;

    /**
     * 利率凸性
     */
    private BigDecimal interestCnvxty;

    /**
     * 市价收益率
     */
    private BigDecimal bpyield;
    
    /**
     * 债券估值行情list
     */
    private List<ValBondValuationInfo001Dto> listValBondValuationInfo001Dto;
    
    /**
     * 账套日期
     */
    private  Date booksetDate;
    
    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 取得 估值来源
     * 
     * @return 估值来源
     */
    public String getValueSource() {
        return valueSource;
    }
    /**
     * 获取 估值来源转换数据字典
     * 
     * @return 估值来源转换数据字典
     */
    public String getvalueSourceName() {
//        return SysDataUtil.getNameFromDataDict(DictCode.估值来源, valueSource);
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.估值来源, valueSource))?
    			SysDataUtil.getNameFromDataDict(DictCode.估值来源, valueSource):valueSource;
    }
    /**
     * 设置 估值来源
     * 
     * @param valueSource 估值来源
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    /**
     * 取得 估值日
     * 
     * @return 估值日
     */
    public String getValueDate() {
        return DateUtil.formatDateAddSplit(valueDate);
    }

    /**
     * 设置 估值日
     * 
     * @param valueDate 估值日
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 剩余期限
     * 
     * @return 剩余期限
     */
    public BigDecimal getLastPeriod() {
        return lastPeriod;
    }

    /**
     * 设置 剩余期限
     * 
     * @param lastPeriod 剩余期限
     */
    public void setLastPeriod(BigDecimal lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    /**
     * 取得 市价全价
     * 
     * @return 市价全价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置 市价全价
     * 
     * @param price 市价全价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 取得 市价净价
     * 
     * @return 市价净价
     */
    public BigDecimal getNetprice() {
        return netprice;
    }

    /**
     * 设置 市价净价
     * 
     * @param netprice 市价净价
     */
    public void setNetprice(BigDecimal netprice) {
        this.netprice = netprice;
    }

    /**
     * 取得 市价久期
     * 
     * @return 市价久期
     */
    public BigDecimal getmDuration() {
        return mDuration;
    }

    /**
     * 设置 市价久期
     * 
     * @param mDuration 市价久期
     */
    public void setmDuration(BigDecimal mDuration) {
        this.mDuration = mDuration;
    }

    /**
     * 取得 市价凸性
     * 
     * @return 市价凸性
     */
    public BigDecimal getmConvexity() {
        return mConvexity;
    }

    /**
     * 设置 市价凸性
     * 
     * @param mConvexity 市价凸性
     */
    public void setmConvexity(BigDecimal mConvexity) {
        this.mConvexity = mConvexity;
    }

    /**
     * 取得 基点价值
     * 
     * @return 基点价值
     */
    public BigDecimal getBpvalue() {
        return bpvalue;
    }

    /**
     * 设置 基点价值
     * 
     * @param bpvalue 基点价值
     */
    public void setBpvalue(BigDecimal bpvalue) {
        this.bpvalue = bpvalue;
    }

    /**
     * 取得 利差久期
     * 
     * @return 利差久期
     */
    public BigDecimal getSduration() {
        return sduration;
    }

    /**
     * 设置 利差久期
     * 
     * @param sduration 利差久期
     */
    public void setSduration(BigDecimal sduration) {
        this.sduration = sduration;
    }

    /**
     * 取得 利差凸性
     * 
     * @return 利差凸性
     */
    public BigDecimal getScnvxty() {
        return scnvxty;
    }

    /**
     * 设置 利差凸性
     * 
     * @param scnvxty 利差凸性
     */
    public void setScnvxty(BigDecimal scnvxty) {
        this.scnvxty = scnvxty;
    }

    /**
     * 取得 利率久期
     * 
     * @return 利率久期
     */
    public BigDecimal getInterestDuration() {
        return interestDuration;
    }

    /**
     * 设置 利率久期
     * 
     * @param interestDuration 利率久期
     */
    public void setInterestDuration(BigDecimal interestDuration) {
        this.interestDuration = interestDuration;
    }

    /**
     * 取得 利率凸性
     * 
     * @return 利率凸性
     */
    public BigDecimal getInterestCnvxty() {
        return interestCnvxty;
    }

    /**
     * 设置 利率凸性
     * 
     * @param interestCnvxty 利率凸性
     */
    public void setInterestCnvxty(BigDecimal interestCnvxty) {
        this.interestCnvxty = interestCnvxty;
    }

    /**
     * 取得 市价收益率
     * 
     * @return 市价收益率
     */
    public BigDecimal getBpyield() {
        return bpyield;
    }

    /**
     * 设置 市价收益率
     * 
     * @param bpyield 市价收益率
     */
    public void setBpyield(BigDecimal bpyield) {
        this.bpyield = bpyield;
    }

    
    /**
     * 获取 listValBondValuationInfo001Dto
     * @return listValBondValuationInfo001Dto.
     */
    public List<ValBondValuationInfo001Dto> getListValBondValuationInfo001Dto() {
        return listValBondValuationInfo001Dto;
    }
    
    /**
     * 设置 listValBondValuationInfo001Dto
     * @param listValBondValuationInfo001Dto listValBondValuationInfo001Dto
     */
    public void setListValBondValuationInfo001Dto(List<ValBondValuationInfo001Dto> listValBondValuationInfo001Dto) {
        this.listValBondValuationInfo001Dto = listValBondValuationInfo001Dto;
    }
    
    /**
     * 获取 booksetDate
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }
    
    /**
     * 设置 booksetDate
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }
    
}
