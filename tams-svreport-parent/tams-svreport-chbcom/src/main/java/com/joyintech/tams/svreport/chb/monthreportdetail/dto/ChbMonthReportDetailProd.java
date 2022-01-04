package com.joyintech.tams.svreport.chb.monthreportdetail.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
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
public class ChbMonthReportDetailProd extends ChbMonthPrdPositiondetail implements  Cloneable{




    private String prodName;
    /**
     * 期限
     */
    private String term;

    /**
     *
     * 是否金融同业专属
     */
    private String excFinSector;

    /**
     * 是否保本理财
     */
    private String isBreakeven;

    /**
     * 开放类型
     */
    private String opentype;

    /**
     * 是否本期发行
     */
    private String isThisTrem;


    /**
     * 是否本期发行
     */
    private String isEndTrem;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified01;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified02;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified03;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified04;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified05;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified06;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified07;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified08;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentified09;


    private String isFbhy;


    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getIsEndTrem() {
        return isEndTrem;
    }

    public void setIsEndTrem(String isEndTrem) {
        this.isEndTrem = isEndTrem;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getIsThisTrem() {
        return isThisTrem;
    }

    public void setIsThisTrem(String isThisTrem) {
        this.isThisTrem = isThisTrem;
    }

    public BigDecimal getOrdIdentified01() {
        return ordIdentified01;
    }

    public void setOrdIdentified01(BigDecimal ordIdentified01) {
        this.ordIdentified01 = ordIdentified01;
    }

    public BigDecimal getOrdIdentified02() {
        return ordIdentified02;
    }

    public void setOrdIdentified02(BigDecimal ordIdentified02) {
        this.ordIdentified02 = ordIdentified02;
    }

    public BigDecimal getOrdIdentified03() {
        return ordIdentified03;
    }

    public void setOrdIdentified03(BigDecimal ordIdentified03) {
        this.ordIdentified03 = ordIdentified03;
    }

    public BigDecimal getOrdIdentified04() {
        return ordIdentified04;
    }

    public void setOrdIdentified04(BigDecimal ordIdentified04) {
        this.ordIdentified04 = ordIdentified04;
    }

    public BigDecimal getOrdIdentified05() {
        return ordIdentified05;
    }

    public void setOrdIdentified05(BigDecimal ordIdentified05) {
        this.ordIdentified05 = ordIdentified05;
    }

    public BigDecimal getOrdIdentified06() {
        return ordIdentified06;
    }

    public void setOrdIdentified06(BigDecimal ordIdentified06) {
        this.ordIdentified06 = ordIdentified06;
    }

    public BigDecimal getOrdIdentified07() {
        return ordIdentified07;
    }

    public void setOrdIdentified07(BigDecimal ordIdentified07) {
        this.ordIdentified07 = ordIdentified07;
    }

    public BigDecimal getOrdIdentified08() {
        return ordIdentified08;
    }

    public void setOrdIdentified08(BigDecimal ordIdentified08) {
        this.ordIdentified08 = ordIdentified08;
    }

    public BigDecimal getOrdIdentified09() {
        return ordIdentified09;
    }

    public void setOrdIdentified09(BigDecimal ordIdentified09) {
        this.ordIdentified09 = ordIdentified09;
    }

    public String getVdateStr() {
        return DateUtil.format(this.getVdate(),"yyyy-MM-dd");
    }

    public String getMdateStr() {
        return DateUtil.format(this.getMdate(),"yyyy-MM-dd");
    }

    /**
     * 取得 募集方式 01：公募 02：私募
     * @return 募集方式 01：公募 02：私募
     */
    public String getRaisemodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.募集方式_中债, this.getRaisemode());
    }

    private String raiseType;
    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }
    public String getRaiseTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.募集方式_中债, this.getRaiseType());
    }

    public String getIvstorientationStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资性质, this.getIvstorientation());
    }

    public String getOperateModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, this.getOperateMode());
    }


    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种_中债, this.getCcy());
    }

    public String getExcFinSector() {
        return excFinSector;
    }

    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector;
    }

    public String getOpentype() {
        return opentype;
    }

    public void setOpentype(String opentype) {
        this.opentype = opentype;
    }

    public String getIsBreakeven() {
        return isBreakeven;
    }

    public void setIsBreakeven(String isBreakeven) {
        this.isBreakeven = isBreakeven;
    }

    public String getOpentypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_开放类型, this.getOpentype());
    }

    public String getTermtypeStr() {
        String  code = SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_期限类型, this.getTermtype());
        return null!=code?code:SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_产品期限, this.getTermtype());
    }

    public String getIsTransferStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsTransfer());
    }

    public String getIsBreakevenStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsBreakeven());
    }

    public String getIsYllccpStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsYllccp());
    }

    public String getExcFinSectorStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getExcFinSector());
    }
    public String getIsFhxgcpStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsFhxgcp());
    }

    public String getIsThisTremStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsThisTrem());
    }

    public String getIsEndTremStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsEndTrem());
    }



    public String getDirtyamtsumStr(){ return null!=this.getDirtyamtsum()? BigDecimalConverterUtil.getMonthDetailString(this.getDirtyamtsum()):null; }
    public String getCleanamtsumStr(){ return null!=this.getCleanamtsum()? BigDecimalConverterUtil.getMonthDetailString(this.getCleanamtsum()):null; }
    public String getEndamtsumOthStr(){ return null!=this.getEndamtsumOth()? BigDecimalConverterUtil.getMonthDetailString(this.getEndamtsumOth()):null; }
    public String getPayamtsumStr(){ return null!=this.getPayamtsum()? BigDecimalConverterUtil.getMonthDetailString(this.getPayamtsum()):null; }
    public String getCustomeramtStr(){ return null!=this.getCustomeramt()? BigDecimalConverterUtil.getMonthDetailString(this.getCustomeramt()):null; }
    public String getBankamtStr(){ return null!=this.getBankamt()? BigDecimalConverterUtil.getMonthDetailString(this.getBankamt()):null; }
    public String getOrdIdentified01Str(){ return null!=this.getOrdIdentified01()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified01()):null; }
    public String getOrdIdentified02Str(){ return null!=this.getOrdIdentified02()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified02()):null; }
    public String getOrdIdentified03Str(){ return null!=this.getOrdIdentified03()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified03()):null; }
    public String getOrdIdentified04Str(){ return null!=this.getOrdIdentified04()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified04()):null; }
    public String getOrdIdentified05Str(){ return null!=this.getOrdIdentified05()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified05()):null; }
    public String getOrdIdentified06Str(){ return null!=this.getOrdIdentified06()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified06()):null; }
    public String getOrdIdentified07Str(){ return null!=this.getOrdIdentified07()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified07()):null; }
    public String getOrdIdentified08Str(){ return null!=this.getOrdIdentified08()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified08()):null; }
    public String getOrdIdentified09Str(){ return null!=this.getOrdIdentified09()? BigDecimalConverterUtil.getMonthDetailString(this.getOrdIdentified09()):null; }



    public BigDecimal getDirtyamtsumDv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getDirtyamtsum()); }
    public BigDecimal getCleanamtsumDv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getCleanamtsum()); }
    public BigDecimal getEndamtsumOthDv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getEndamtsumOth()); }
    public BigDecimal getPayamtsumDv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getPayamtsum()); }
    public BigDecimal getCustomeramtDv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getCustomeramt()); }
    public BigDecimal getBankamtDv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getBankamt()); }
    public BigDecimal getOrdIdentified01Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified01()); }
    public BigDecimal getOrdIdentified02Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified02()); }
    public BigDecimal getOrdIdentified03Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified03()); }
    public BigDecimal getOrdIdentified04Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified04()); }
    public BigDecimal getOrdIdentified05Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified05()); }
    public BigDecimal getOrdIdentified06Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified06()); }
    public BigDecimal getOrdIdentified07Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified07()); }
    public BigDecimal getOrdIdentified08Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified08()); }
    public BigDecimal getOrdIdentified09Dv(){return BigDecimalConverterUtil.BigDecimalDivTenTh(this.getOrdIdentified09()); }


}