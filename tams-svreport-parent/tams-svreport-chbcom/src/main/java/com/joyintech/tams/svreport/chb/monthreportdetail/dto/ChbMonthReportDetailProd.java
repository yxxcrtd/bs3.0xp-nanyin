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


    public String getIvstorientationStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资性质, this.getIvstorientation());
    }

    public String getOperateModeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, this.getOperateMode());
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
        return SysDataUtil.getNameFromDataDict(DictCode.开放类型, this.getOpentype());
    }

    public String getTermtypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.期限类型, this.getTermtype());
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
}