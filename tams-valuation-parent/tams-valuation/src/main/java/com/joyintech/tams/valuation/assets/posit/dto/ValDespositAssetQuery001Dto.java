package com.joyintech.tams.valuation.assets.posit.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCode;

/**
 * 存款类资产查询list DTO
 * 
 * @author wangtingting
 * @version 1.0 2018年7月19日
 * @see ValDespositAssetQuery001Dto
 * @since 1.0
 */
public class ValDespositAssetQuery001Dto extends FinProduct {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /** 资产代码、资产名称、存款本金（发行价）父类已有、其他字段 */
    /** 存款类型 */
    private String finprodType;

    /**
     * 起息日
     */
    private String vdate1;

    /**
     * 到期日
     */
    private String mdate1;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 存款机构
     */
    private String positAddr;

    /**
     * 存放本金
     */
    private BigDecimal issuePrice;

    /**
     * 利率类型
     */
    private String intType;

    /**
     * 利率类型code
     */
    private String intTypeCode;

    /**
     * 最新利率
     */
    private BigDecimal rate;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 basis
     * 
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 basis
     * 
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 获取 finprodType
     * 
     * @return finprodType.
     */
    public String getFinprodTypeFt() {
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType);
    }

    /**
     * 设置 finprodType
     * 
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 depositType
     * 
     * @return depositType.
     */
    public String getDepositType() {
        return finprodType;
    }

    /**
     * 获取 getdepositTypeName
     * 
     * @return getdepositTypeName
     * @see
     * @since 1.0
     */
    public String getdepositTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, finprodType);
    }

    /**
     * 设置 depositType
     * 
     * @param finprodType finprodType
     * @see
     * @since 1.0
     */
    public void setDepositType(String finprodType) {
        this.finprodType = finprodType;
    }

    /**
     * 获取 vdate1
     * 
     * @return vdate1.
     */
    public String getVdate1() {
        return vdate1;
    }

    /**
     * 设置 vdate1
     * 
     * @param vdate1 vdate1
     */
    public void setVdate1(String vdate1) {
        this.vdate1 = vdate1;
    }

    /**
     * 获取 mdate1
     * 
     * @return mdate1.
     */
    public String getMdate1() {
        return mdate1;
    }

    /**
     * 设置 mdate1
     * 
     * @param mdate1 mdate1
     */
    public void setMdate1(String mdate1) {
        this.mdate1 = mdate1;
    }

    /**
     * 获取 positAddr
     * 
     * @return positAddr.
     */
    public String getPositAddr() {
        return positAddr;
    }

    /**
     * 设置 positAddr
     * 
     * @param positAddr positAddr
     */
    public void setPositAddr(String positAddr) {
        this.positAddr = positAddr;
    }

    /**
     * 获取 issuePrice
     * 
     * @return issuePrice.
     */
    public BigDecimal getIssuePrice() {
        return issuePrice;
    }

    /**
     * 设置 issuePrice
     * 
     * @param issuePrice issuePrice
     */
    public void setIssuePrice(BigDecimal issuePrice) {
        this.issuePrice = issuePrice;
    }

    /**
     * 获取 intType
     * 
     * @return intType.
     */
    public String getIntType() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.利率类型,
            this.intType)) ? SysDataUtil.getNameFromDataDict(DictCode.利率类型,
                this.intType) : this.intType;
    }

    /**
     * 设置 intType
     * 
     * @param intType intType
     */
    public void setIntType(String intType) {
        this.intType = intType;
    }

    /**
     * 获取 rate
     * 
     * @return rate.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 rate
     * 
     * @param rate rate
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 获取 intTypeCode
     * 
     * @return intTypeCode.
     */
    public String getIntTypeCode() {
        return intTypeCode;
    }

    /**
     * 设置 intTypeCode
     * 
     * @param intTypeCode intTypeCode
     */
    public void setIntTypeCode(String intTypeCode) {
        this.intTypeCode = intTypeCode;
    }

}
