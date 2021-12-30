package com.joyintech.tams.svreport.pbc.reportdetail.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import com.joyintech.tams.common.base.entity.PbcMonthAssetPositiondetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.interf.common.util.BigDecimalConverterUtil;
import com.joyintech.tams.svreport.pbc.reportdetail.service.PbcMonthReportDetailProdUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class PbcMonthReportDetailAsset extends PbcMonthAssetPositiondetail implements  Cloneable{

    private String reportType1;

    private String reportType2;

    private String reportType3;

    private String reportType4;

    private String reportType5;

    private String reportType6;

    private String assetName;

    private String oldAssetName;

    private String prodName;

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

    public String getReportType5() {
        return reportType5;
    }

    public void setReportType5(String reportType5) {
        this.reportType5 = reportType5;
    }

    public String getReportType6() {
        return reportType6;
    }

    public void setReportType6(String reportType6) {
        this.reportType6 = reportType6;
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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getVdateStr() {
        return DateUtil.format(this.getVdate(),"yyyy-MM-dd");
    }

    public String getMdateStr() {
        return DateUtil.format(this.getMdate(),"yyyy-MM-dd");
    }

    public String getRateStr(){
        return null!=this.getRate()?this.getRate()+"%":null;
    }

    public String getIndustryStr(){
        String code = SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, this.getIndustry());
        return null!=code?code:SysDataUtil.getNameFromDataDict(DictCode.行业门内编码, this.getIndustry());
    }

    public String getPartyType1Str(){
        String code = SysDataUtil.getNameFromDataDict(DictCode.发行机构类型_按规模划分, this.getPartyType1());
        return null!=code?code:SysDataUtil.getNameFromDataDict(DictCode.企业规模, this.getPartyType1());
    }

    public String getBuyPlaceStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.销售区域_中债, this.getBuyPlace());
    }

    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种_中债, this.getCcy());
    }

    public String getOrgAssetType1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产一级分类, this.getOrgAssetType1());
    }

    public String getOrgAssetType2Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产二级分类, this.getOrgAssetType2());
    }

    public String getOrgAssetType3Str() {
//        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产三级分类, this.getOrgAssetType3());
        return SysDataUtil.getNameFromDataDict(DictCode.资产_负债二级类别, this.getOrgAssetType3());
    }

    public String getCounterListCode(){
        return this.getProdid()+"_"+this.getAssetCode();
    }


    public String getCostAmtStr(){
        //        //人行月报资产端成本已经是包含了利息调整了，需求把利息调整又单独列出来，然后余额写的是成本+利息调整，这样的话利息调整会统计重复？  成本直接减去利息调整进行展示
        return null!=this.getCostAmt()? BigDecimalConverterUtil.getMonthDetailString(this.getCostAmt()):null;
    }

    public String getFloatIngplStr(){
        return null!=this.getFloatIngpl()? BigDecimalConverterUtil.getMonthDetailString(this.getFloatIngpl()):null;
    }

    public String getIntincexpStr(){
        return null!=this.getIntincexp()? BigDecimalConverterUtil.getMonthDetailString(this.getIntincexp()):null;
    }

    public String getIntadjexpAddStr(){
        return null!=this.getIntadjexpAdd()? BigDecimalConverterUtil.getMonthDetailString(this.getIntadjexpAdd()):null;
    }

    public String getLossAmtStr(){
        return null!=this.getLossAmt()? BigDecimalConverterUtil.getMonthDetailString(this.getLossAmt()):null;
    }
    public String getAmtStr(){
        return null!=this.getAmt()? BigDecimalConverterUtil.getMonthDetailString(this.getAmt()):null;
    }

    public String getEndDaysType(){
        return SysDataUtil.getNameFromDataDict(DictCode.人行月度明细_期限类型, PbcMonthReportDetailProdUtil.getTermType(this.getEndDays()));
    }

    public String getDetailCode(){
        return this.getProdid()+"_"+this.getAssetCode()+"_"+this.getDetailno();
    }
}