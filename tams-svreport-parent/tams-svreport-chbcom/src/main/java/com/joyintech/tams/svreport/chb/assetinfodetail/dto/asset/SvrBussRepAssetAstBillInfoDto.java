/*
 * 文件名：BussRepAssetAstBillDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetAstBill;

import java.math.BigDecimal;

/**
 * 非标资产(票据或信用)报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see SvrBussRepAssetAstBillInfoDto
 * @since 1.0
 */
public class SvrBussRepAssetAstBillInfoDto extends RepAssetAstBill{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 是否为收/受益权 01是/02否
     */
    private String isRight;

    /**
     * 是否属于买入返售 01是/02否
     */
    private String isResale;

    /**
     * 非标准化债券类资产类型 01 商业承兑汇票/ 02 银行承兑汇票/ 03 其他票据/ 04 混合类票据/ 05 信用证
     */
    private String notstandSecType;

    /**
     * 非标准化债券类资产数量
     */
    private Long notstandSecNum;

    /**
     * 金额
     */
    private BigDecimal amt;

    /**
     * 加权剩余期限(天)
     */
    private BigDecimal remainTerm;

    /**
     * 最长剩余期限(天)
     */
    private BigDecimal maxRemainTerm;

    /**
     * 最短剩余期限(天)
     */
    private BigDecimal minRemainTerm;

    /**
     * 收益率/年利率
     */
    private BigDecimal assetYield;

    /**
     * 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industryShares;

    /**
     * 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryShares2;

    /**
     * 是否投向地方政府融资平台
     */
    private String privateFlag;
    /**
     * 备注
     */
    private String remark;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getIsRight() {
        return isRight;
    }

    public void setIsRight(String isRight) {
        this.isRight = isRight;
    }

    public String getIsResale() {
        return isResale;
    }

    public void setIsResale(String isResale) {
        this.isResale = isResale;
    }

    public String getNotstandSecType() {
        return notstandSecType;
    }

    public void setNotstandSecType(String notstandSecType) {
        this.notstandSecType = notstandSecType;
    }

    public Long getNotstandSecNum() {
        return notstandSecNum;
    }

    public void setNotstandSecNum(Long notstandSecNum) {
        this.notstandSecNum = notstandSecNum;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getRemainTerm() {
        return remainTerm;
    }

    public void setRemainTerm(BigDecimal remainTerm) {
        this.remainTerm = remainTerm;
    }

    public BigDecimal getMaxRemainTerm() {
        return maxRemainTerm;
    }

    public void setMaxRemainTerm(BigDecimal maxRemainTerm) {
        this.maxRemainTerm = maxRemainTerm;
    }

    public BigDecimal getMinRemainTerm() {
        return minRemainTerm;
    }

    public void setMinRemainTerm(BigDecimal minRemainTerm) {
        this.minRemainTerm = minRemainTerm;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public String getIndustryShares() {
        return industryShares;
    }

    public void setIndustryShares(String industryShares) {
        this.industryShares = industryShares;
    }

    public String getIndustryShares2() {
        return industryShares2;
    }

    public void setIndustryShares2(String industryShares2) {
        this.industryShares2 = industryShares2;
    }

    public String getPrivateFlag() {
        return privateFlag;
    }

    public void setPrivateFlag(String privateFlag) {
        this.privateFlag = privateFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }





}
