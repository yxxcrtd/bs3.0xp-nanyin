/*
 * 文件名：BussFinBondRepInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetBond;
import com.joyintech.tams.svreport.chb.assetinfodetail.validator.asset.SvrBussFinBondRepInfoValidator;

import java.math.BigDecimal;

/**
 *  资产基本信息_QDII拆出逆回购类信息
 * 〈功能详细描述〉
 * @author
 * @version 1.0
 * @see SvrBussRepQDIIdemolitionInfoDto
 * @since 1.0
 */
public class SvrBussRepQDIIdemolitionInfoDto extends RepAssetBond{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 所属国家或地区 参考数据字典：COUNTRY_AREA
     */
    private String countryArea;

    /**
     * 对手方
     */
    private String rivalParty;

    /**
     * 收益率/年利率
     */
    private BigDecimal assetYield;

    /**
     * 计息基础 01 ACT/366；02 ACT/360；03 ACT/365；04 ACT/ACT；05 30/360
     */
    private String basis;

    /**
     * 备注
     */
    private String  remark;

    public String getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }

    public String getRivalParty() {
        return rivalParty;
    }

    public void setRivalParty(String rivalParty) {
        this.rivalParty = rivalParty;
    }

    public BigDecimal getAssetYield() {
        return assetYield;
    }

    public void setAssetYield(BigDecimal assetYield) {
        this.assetYield = assetYield;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
