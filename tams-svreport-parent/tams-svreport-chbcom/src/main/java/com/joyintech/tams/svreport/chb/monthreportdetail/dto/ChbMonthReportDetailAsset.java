package com.joyintech.tams.svreport.chb.monthreportdetail.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class ChbMonthReportDetailAsset extends ChbMonthAssetPositiondetail implements  Cloneable{


    private String prodName;

    private String reportType1;

    private String reportType2;

    private String reportType3;

    private String reportType4;

    private String assetName;

    private String oldAssetName;

    private String oldAssetCode;


    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    @Override
    public String getOldAssetCode() {
        return oldAssetCode;
    }

    @Override
    public void setOldAssetCode(String oldAssetCode) {
        this.oldAssetCode = oldAssetCode;
    }

    public String getReportType1() {
        return reportType1;
    }

    public void setReportType1(String reportType1) {
        this.reportType1 = reportType1;
    }

    public String getReportType2() {
        return reportType2;
    }

    public void setReportType2(String reportType2) {
        this.reportType2 = reportType2;
    }

    public String getReportType3() {
        return reportType3;
    }

    public void setReportType3(String reportType3) {
        this.reportType3 = reportType3;
    }

    public String getReportType4() {
        return reportType4;
    }

    public void setReportType4(String reportType4) {
        this.reportType4 = reportType4;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getOldAssetName() {
        return oldAssetName;
    }

    public void setOldAssetName(String oldAssetName) {
        this.oldAssetName = oldAssetName;
    }



    public String getVdateStr() {
        return DateUtil.format(this.getVdate(),"yyyy-MM-dd");
    }

    public String getMdateStr() {
        return DateUtil.format(this.getMdate(),"yyyy-MM-dd");
    }

    public String getPrivatefundFlagStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPrivatefundFlag());
    }

    public String getPrivateprdFlagStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPrivateprdFlag());
    }
    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种_中债, this.getCcy());
    }

    public String getBondtostockFlagStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getBondtostockFlag());
    }

    public String getIsGovernFund1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsGovernFund1());
    }

    public String getOrgAssetType1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产一级分类, this.getOrgAssetType1());
    }

    public String getOrgAssetType2Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产二级分类, this.getOrgAssetType2());
    }

    public String getOrgAssetType3Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产三级分类, this.getOrgAssetType3());
    }

}