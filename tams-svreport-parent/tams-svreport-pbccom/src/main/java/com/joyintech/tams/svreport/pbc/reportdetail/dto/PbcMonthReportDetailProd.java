package com.joyintech.tams.svreport.pbc.reportdetail.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.PbcMonthPrdPositiondetail;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:CHB_ASSET_POSITION表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-26
 */
public class PbcMonthReportDetailProd extends PbcMonthPrdPositiondetail implements  Cloneable{

    private String dataTypekey;

    private String  prodName;



    private String dataType;
    /**
     * 是否本期发行
     */
    private String isThisTrem;


    /**
     * 是否本期发行
     */
    private String isEndTrem;
    /**
     *
     */
    private String isEndSubsist;

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



    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ordIdentifiedAll;



    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace01;


    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace02;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace03;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace04;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace05;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace06;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace07;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace08;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace09;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace10;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace11;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace12;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace13;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace14;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace15;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace16;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace17;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace18;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace19;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace20;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace21;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace22;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace23;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace24;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace25;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace26;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace27;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace28;
    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace29;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace30;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace31;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace32;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace33;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace34;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace35;


    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlace36;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyPlaceAll;


    public String getDataTypekey() {
        return dataTypekey;
    }

    public void setDataTypekey(String dataTypekey) {
        this.dataTypekey = dataTypekey;
    }

    public String getDataType() {
        return dataType;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
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



    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getIsEndSubsist() {
        return isEndSubsist;
    }

    public void setIsEndSubsist(String isEndSubsist) {
        this.isEndSubsist = isEndSubsist;
    }

    public BigDecimal getOrdIdentifiedAll() {
        return ordIdentifiedAll;
    }

    public void setOrdIdentifiedAll(BigDecimal ordIdentifiedAll) {
        this.ordIdentifiedAll = ordIdentifiedAll;
    }

    public BigDecimal getBuyPlace01() {
        return buyPlace01;
    }

    public void setBuyPlace01(BigDecimal buyPlace01) {
        this.buyPlace01 = buyPlace01;
    }

    public BigDecimal getBuyPlace02() {
        return buyPlace02;
    }

    public void setBuyPlace02(BigDecimal buyPlace02) {
        this.buyPlace02 = buyPlace02;
    }

    public BigDecimal getBuyPlace03() {
        return buyPlace03;
    }

    public void setBuyPlace03(BigDecimal buyPlace03) {
        this.buyPlace03 = buyPlace03;
    }

    public BigDecimal getBuyPlace04() {
        return buyPlace04;
    }

    public void setBuyPlace04(BigDecimal buyPlace04) {
        this.buyPlace04 = buyPlace04;
    }

    public BigDecimal getBuyPlace05() {
        return buyPlace05;
    }

    public void setBuyPlace05(BigDecimal buyPlace05) {
        this.buyPlace05 = buyPlace05;
    }

    public BigDecimal getBuyPlace06() {
        return buyPlace06;
    }

    public void setBuyPlace06(BigDecimal buyPlace06) {
        this.buyPlace06 = buyPlace06;
    }

    public BigDecimal getBuyPlace07() {
        return buyPlace07;
    }

    public void setBuyPlace07(BigDecimal buyPlace07) {
        this.buyPlace07 = buyPlace07;
    }

    public BigDecimal getBuyPlace08() {
        return buyPlace08;
    }

    public void setBuyPlace08(BigDecimal buyPlace08) {
        this.buyPlace08 = buyPlace08;
    }

    public BigDecimal getBuyPlace09() {
        return buyPlace09;
    }

    public void setBuyPlace09(BigDecimal buyPlace09) {
        this.buyPlace09 = buyPlace09;
    }

    public BigDecimal getBuyPlace10() {
        return buyPlace10;
    }

    public void setBuyPlace10(BigDecimal buyPlace10) {
        this.buyPlace10 = buyPlace10;
    }

    public BigDecimal getBuyPlace11() {
        return buyPlace11;
    }

    public void setBuyPlace11(BigDecimal buyPlace11) {
        this.buyPlace11 = buyPlace11;
    }

    public BigDecimal getBuyPlace12() {
        return buyPlace12;
    }

    public void setBuyPlace12(BigDecimal buyPlace12) {
        this.buyPlace12 = buyPlace12;
    }

    public BigDecimal getBuyPlace13() {
        return buyPlace13;
    }

    public void setBuyPlace13(BigDecimal buyPlace13) {
        this.buyPlace13 = buyPlace13;
    }

    public BigDecimal getBuyPlace14() {
        return buyPlace14;
    }

    public void setBuyPlace14(BigDecimal buyPlace14) {
        this.buyPlace14 = buyPlace14;
    }

    public BigDecimal getBuyPlace15() {
        return buyPlace15;
    }

    public void setBuyPlace15(BigDecimal buyPlace15) {
        this.buyPlace15 = buyPlace15;
    }

    public BigDecimal getBuyPlace16() {
        return buyPlace16;
    }

    public void setBuyPlace16(BigDecimal buyPlace16) {
        this.buyPlace16 = buyPlace16;
    }

    public BigDecimal getBuyPlace17() {
        return buyPlace17;
    }

    public void setBuyPlace17(BigDecimal buyPlace17) {
        this.buyPlace17 = buyPlace17;
    }

    public BigDecimal getBuyPlace18() {
        return buyPlace18;
    }

    public void setBuyPlace18(BigDecimal buyPlace18) {
        this.buyPlace18 = buyPlace18;
    }

    public BigDecimal getBuyPlace19() {
        return buyPlace19;
    }

    public void setBuyPlace19(BigDecimal buyPlace19) {
        this.buyPlace19 = buyPlace19;
    }

    public BigDecimal getBuyPlace20() {
        return buyPlace20;
    }

    public void setBuyPlace20(BigDecimal buyPlace20) {
        this.buyPlace20 = buyPlace20;
    }

    public BigDecimal getBuyPlace21() {
        return buyPlace21;
    }

    public void setBuyPlace21(BigDecimal buyPlace21) {
        this.buyPlace21 = buyPlace21;
    }

    public BigDecimal getBuyPlace22() {
        return buyPlace22;
    }

    public void setBuyPlace22(BigDecimal buyPlace22) {
        this.buyPlace22 = buyPlace22;
    }

    public BigDecimal getBuyPlace23() {
        return buyPlace23;
    }

    public void setBuyPlace23(BigDecimal buyPlace23) {
        this.buyPlace23 = buyPlace23;
    }

    public BigDecimal getBuyPlace24() {
        return buyPlace24;
    }

    public void setBuyPlace24(BigDecimal buyPlace24) {
        this.buyPlace24 = buyPlace24;
    }

    public BigDecimal getBuyPlace25() {
        return buyPlace25;
    }

    public void setBuyPlace25(BigDecimal buyPlace25) {
        this.buyPlace25 = buyPlace25;
    }

    public BigDecimal getBuyPlace26() {
        return buyPlace26;
    }

    public void setBuyPlace26(BigDecimal buyPlace26) {
        this.buyPlace26 = buyPlace26;
    }

    public BigDecimal getBuyPlace27() {
        return buyPlace27;
    }

    public void setBuyPlace27(BigDecimal buyPlace27) {
        this.buyPlace27 = buyPlace27;
    }

    public BigDecimal getBuyPlace28() {
        return buyPlace28;
    }

    public void setBuyPlace28(BigDecimal buyPlace28) {
        this.buyPlace28 = buyPlace28;
    }

    public BigDecimal getBuyPlace29() {
        return buyPlace29;
    }

    public void setBuyPlace29(BigDecimal buyPlace29) {
        this.buyPlace29 = buyPlace29;
    }

    public BigDecimal getBuyPlace30() {
        return buyPlace30;
    }

    public void setBuyPlace30(BigDecimal buyPlace30) {
        this.buyPlace30 = buyPlace30;
    }

    public BigDecimal getBuyPlace31() {
        return buyPlace31;
    }

    public void setBuyPlace31(BigDecimal buyPlace31) {
        this.buyPlace31 = buyPlace31;
    }

    public BigDecimal getBuyPlace32() {
        return buyPlace32;
    }

    public void setBuyPlace32(BigDecimal buyPlace32) {
        this.buyPlace32 = buyPlace32;
    }

    public BigDecimal getBuyPlace33() {
        return buyPlace33;
    }

    public void setBuyPlace33(BigDecimal buyPlace33) {
        this.buyPlace33 = buyPlace33;
    }

    public BigDecimal getBuyPlace34() {
        return buyPlace34;
    }

    public void setBuyPlace34(BigDecimal buyPlace34) {
        this.buyPlace34 = buyPlace34;
    }

    public BigDecimal getBuyPlace35() {
        return buyPlace35;
    }

    public void setBuyPlace35(BigDecimal buyPlace35) {
        this.buyPlace35 = buyPlace35;
    }

    public BigDecimal getBuyPlace36() {
        return buyPlace36;
    }

    public void setBuyPlace36(BigDecimal buyPlace36) {
        this.buyPlace36 = buyPlace36;
    }

    public BigDecimal getBuyPlaceAll() {
        return buyPlaceAll;
    }

    public void setBuyPlaceAll(BigDecimal buyPlaceAll) {
        this.buyPlaceAll = buyPlaceAll;
    }

    public String getIsEndTrem() {
        return isEndTrem;
    }

    public void setIsEndTrem(String isEndTrem) {
        this.isEndTrem = isEndTrem;
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

    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种_中债, this.getCcy());
    }


    public String getTermtypeStr() {
        String  code = SysDataUtil.getNameFromDataDict(DictCode.中债月度明细_期限类型, this.getTermtype());
        return null!=code?code:SysDataUtil.getNameFromDataDict(DictCode.产品期限, this.getTermtype());
    }
    public String getIsTransferStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsTransfer());
    }

    public String getIsYllccpStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsYllccp());
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

    public String getPaystatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.兑付状态_中债, this.getPaystatus());
    }

    public String getIsConstracteStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsConstracte());
    }

    public String getTransferTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getTransferType());
    }
    public String getIsMarketFundStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsMarketFund());
    }

    public String getIsEndSubsistStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsEndSubsist());
    }

    public String getManageTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.管理方式, this.getManageType());
    }

    public String getFiduciaryDutyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品受托职责_中债, this.getFiduciaryDuty());
    }

    public String getDataTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.人行月报详情数据类型, this.getDataType());

    }

}