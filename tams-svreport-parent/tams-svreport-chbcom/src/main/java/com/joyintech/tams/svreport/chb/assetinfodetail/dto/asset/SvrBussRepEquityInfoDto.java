/*
 * 文件名：BussRepEquityInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepAssetEquity;

import java.util.Date;

/**
 *  权益类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月31日
 * @see SvrBussRepEquityInfoDto
 * @since 1.0
 */
public class SvrBussRepEquityInfoDto extends RepAssetEquity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 资产名称
     */
    private String assetName;


    /**
     * 股票类型 01 普通股/02 优先股/99 其他
     */
    private String sharesType;
    /**
     * 行业一级分类 枚举值数据字典：INSTITUTION_INDUSTRY_FIRST_LEVEL
     */
    private String industryShares;

    /**
     * 行业二级分类 枚举值数据字典：INSTITUTION_INDUSTRY_SECOND_LEVEL
     */
    private String industryShares2;

    /**
     * 投资阶段 01 种子期/ 02 起步期/ 03 扩张期/ 04 过渡期/ 05 重建期/ 06 已上市/ 99 其它
     */
    private String investStage;

    /**
     * 股权退出安排
     */
    private Date equityQuit;


    /**
     * 投资企业类型（按规模划分） 01 中小微企业/02 大型企业/99 其他
     */
    private String sharesType1;

    /**
     * 投资企业类型（按经济类型划分）01 国有企业/02 集体企业/03 私营企业/99 其他
     */
    private String sharesType2;

    /**
     * 投资企业类型（按技术领域划分）01 高新技术企业/99 其他
     */
    private String sharesType3;

    /**
     * 是否为质押融资 01是/02否
     */
    private String isPledge;

    /**
     * 是否为债转股
     */
    private String deptEquity;

    /**
     * 商业银行优先股
     */
    private  String bankSecPrefer;
    /**
     * 备注
     */
    private String remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getSharesType() {
        return sharesType;
    }

    public void setSharesType(String sharesType) {
        this.sharesType = sharesType;
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

    public String getInvestStage() {
        return investStage;
    }

    public void setInvestStage(String investStage) {
        this.investStage = investStage;
    }

    public Date getEquityQuit() {
        return equityQuit;
    }

    public void setEquityQuit(Date equityQuit) {
        this.equityQuit = equityQuit;
    }

    public String getSharesType1() {
        return sharesType1;
    }

    public void setSharesType1(String sharesType1) {
        this.sharesType1 = sharesType1;
    }

    public String getSharesType2() {
        return sharesType2;
    }

    public void setSharesType2(String sharesType2) {
        this.sharesType2 = sharesType2;
    }

    public String getSharesType3() {
        return sharesType3;
    }

    public void setSharesType3(String sharesType3) {
        this.sharesType3 = sharesType3;
    }

    public String getIsPledge() {
        return isPledge;
    }

    public void setIsPledge(String isPledge) {
        this.isPledge = isPledge;
    }

    public String getDeptEquity() {
        return deptEquity;
    }

    public void setDeptEquity(String deptEquity) {
        this.deptEquity = deptEquity;
    }

    public String getBankSecPrefer() {
        return bankSecPrefer;
    }

    public void setBankSecPrefer(String bankSecPrefer) {
        this.bankSecPrefer = bankSecPrefer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
