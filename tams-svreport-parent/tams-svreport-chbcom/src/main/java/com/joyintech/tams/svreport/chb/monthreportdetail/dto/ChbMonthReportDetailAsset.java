package com.joyintech.tams.svreport.chb.monthreportdetail.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import com.joyintech.tams.common.base.entity.ChbMonthPrdPositiondetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.interf.common.util.BigDecimalConverterUtil;

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


    private Date exerciseDate;

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

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getOpenplanStr(){return DateUtil.format(this.getOpenplan(),"yyyy-MM-dd");}

    public String getVdateStr() {
        return DateUtil.format(this.getVdate(),"yyyy-MM-dd");
    }

    public String getMdateStr() {
        return DateUtil.format(this.getMdate(),"yyyy-MM-dd");
    }

    public String getPrivatefundFlagStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPrivatefundFlag());
    }

    public String getIndustry1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业一级分类, this.getIndustry1());
    }
    public String getIndustry2Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业二级分类, this.getIndustry2());
    }
    public String getIndustry3Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业三级分类, this.getIndustry3());
    }
    public String getIndustry4Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.机构所属行业四级分类, this.getIndustry4());
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

    public String getMonitTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getMonitType());
    }

    public String getPppflagStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getPppflag());
    }

    public String getIsGovernFund1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsGovernFund1());
    }

    public String getOrgAssetType1Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产一级分类, this.getOrgAssetType1());
    }

    public String getIsYhbcgjStr() {
        String  isYhbcgj = "";
        if(StringUtil.isNotEmpty(this.getIsYhbcgj())){
            for(String code : this.getIsYhbcgj().split(",")){
                isYhbcgj = isYhbcgj + ","+SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_银行资本补充工具, code);
            }
        }
        return StringUtil.isNotEmpty(isYhbcgj)&&isYhbcgj.length()>0?isYhbcgj.substring(1,isYhbcgj.length()):null;
    }

    public String getOrgAssetType2Str() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债_上游资产二级分类, this.getOrgAssetType2());
    }

    public String getOrgAssetType3Str() {
        String orgCode3 = null;
        if(null!=SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型,this.getOrgAssetType2())){
            orgCode3 = SysDataUtil.getNameFromDataDict(DictCode.存款类资产存款类型, this.getOrgAssetType3());
        }else if(null!=SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等,this.getOrgAssetType2())){
            orgCode3 = SysDataUtil.getNameFromDataDict(DictCode.WIND二级分类_含债券二级分类等, this.getOrgAssetType3());
        }else if(null!=SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型,this.getOrgAssetType2())){
            orgCode3 = SysDataUtil.getNameFromDataDict(DictCode.基金类资产二级类型, this.getOrgAssetType3());
        }else if(null!=SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, this.getOrgAssetType2())){
            orgCode3 = SysDataUtil.getNameFromDataDict(DictCode.项目类资产二级类型, this.getOrgAssetType3());
        }else if(null!=SysDataUtil.getNameFromDataDict(DictCode.拆借类型,this.getOrgAssetType2())){
            orgCode3 = SysDataUtil.getNameFromDataDict(DictCode.拆借资产类型, this.getOrgAssetType3());
        }

        return orgCode3;
    }

    private String isFbhy;

    public String getIsFbhy() {
        return isFbhy;
    }

    public void setIsFbhy(String isFbhy) {
        this.isFbhy = isFbhy;
    }

    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal costAmtB ;

    private BigDecimal costAmtA;

    public BigDecimal getCostAmtA() {
        return costAmtA;
    }

    public void setCostAmtA(BigDecimal costAmtA) {
        this.costAmtA = costAmtA;
    }

    public BigDecimal getCostAmtB() {
        return costAmtB;
    }
    public void setCostAmtB(BigDecimal costAmtB) {
        this.costAmtB = costAmtB;
    }

    public String getIsFbhyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_非标行业, this.getIsFbhy());
    }



    public String getDelayPayAmtStr(){ return null!=this.getDelayPayAmt()? BigDecimalConverterUtil.getMonthDetailString(this.getDelayPayAmt()):null; }

    public String getCostAmtStr(){
        //人行月报资产端成本已经是包含了利息调整了，需求把利息调整又单独列出来，然后余额写的是成本+利息调整，这样的话利息调整会统计重复？  成本直接减去利息调整进行展示
        return null!=this.getCostAmt()? BigDecimalConverterUtil.getMonthDetailString(this.getCostAmt()):null;
    }
    public String getCostAmtBStr(){
        //人行月报资产端成本已经是包含了利息调整了，需求把利息调整又单独列出来，然后余额写的是成本+利息调整，这样的话利息调整会统计重复？  成本直接减去利息调整进行展示
        return null!=this.getCostAmtB()? BigDecimalConverterUtil.getMonthDetailString(this.getCostAmtB()):null;
    }

    public String getCostAmtaStr(){
        //人行月报资产端成本已经是包含了利息调整了，需求把利息调整又单独列出来，然后余额写的是成本+利息调整，这样的话利息调整会统计重复？  成本直接减去利息调整进行展示
        return null!=this.getCostAmtA()? BigDecimalConverterUtil.getMonthDetailString(this.getCostAmtA()):null;
    }
    public String getIsFhxgcpStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsFhxgcp());
    }


    public String getFloatIngplStr(){ return null!=this.getFloatIngpl()? BigDecimalConverterUtil.getMonthDetailString(this.getFloatIngpl()):null; }
    public String getIntincexpStr(){ return null!=this.getIntincexp()? BigDecimalConverterUtil.getMonthDetailString(this.getIntincexp()):null; }
    public String getIntadjexpAddStr(){ return null!=this.getIntadjexpAdd()? BigDecimalConverterUtil.getMonthDetailString(this.getIntadjexpAdd()):null; }
    public String getLossAmtStr(){ return null!=this.getLossAmt()? BigDecimalConverterUtil.getMonthDetailString(this.getLossAmt()):null; }
    public String getAssetvalueAStr(){ return null!=this.getAssetvalueA()? BigDecimalConverterUtil.getMonthDetailString(this.getAssetvalueA()):null; }
    public String getAssetvalueBStr(){ return null!=this.getAssetvalueB()? BigDecimalConverterUtil.getMonthDetailString(this.getAssetvalueB()):null; }


    public BigDecimal getFloatIngplDv(){ return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getFloatIngpl()); }
    public BigDecimal getIntincexpDv(){ return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getIntincexp()); }
    public BigDecimal getIntadjexpAddDv(){ return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getIntadjexpAdd()); }
    public BigDecimal getLossAmtDv(){ return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getLossAmt()); }
    public BigDecimal getAssetvalueADv(){ return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getAssetvalueA()); }
    public BigDecimal getAssetvalueBDv(){ return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getAssetvalueB()); }



}