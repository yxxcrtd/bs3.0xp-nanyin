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

    /**
     * null
     */
    private String posuuid;

    /**
     * 产品代码
     */
    private String prodid;


    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 募集方式 01：公募 02：私募
     */
    private String raiseType;

    /**
     * 投资性质 01固定收益类/02权益类/03混合类/04商品及金融衍生品类
     */
    private String ivstorientation;

    /**
     * 统计日期
     */
    private Date bookdate;

    /**
     * 开放类型
     */
    private String opentype;

    /**
     * 期限
     */
    private String term;

    /**
     * 币种 CNY:人民币/USD:美元/EUR 欧元/QTBZ 其他币种
     */
    private String raiseCcy;

    /**
     * 期限品种01 T+0产品/02 7天（含）以内/03 7天-1个月（含）/04 1-3个月（含）/05 3-6 25个月(含)/06 6-12个月（含）/07 1-3年（含）/08 3年以上
     */
    private String termtype;

    /**
     * 运行方式01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型
     */
    private String operateMode;

    /**
     * 是否母行划转产品
     */
    private String isTransfer;
    /**
     * 是否保本理财
     */
    private String isBreakeven;

    /**
     * 新老产品标记
     */
    private String isFhxgcp;

    /**
     * 是否金融同业专属
     */
    private String excFinSector;

    /**
     * 是否养老理财产品
     * @return
     */
    private   String  isYllccp;

    /**
     * 本期总募集金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal dirtyamtsum;

    /**
     * 本期净募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cleanamtsum;



    /**
     * 是否本期发行
     */
    private String isThisTrem;


    /**
     * 是否本期发行
     */
    private String isEndTrem;

    /**
     * 期末余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endamtsumOth;

    /**
     * 本期兑付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal payamtsum;


    /**
     * 本期客户实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal customeramt;

    /**
     * 本期银行实现收益总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bankamt;


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


    public String getIsEndTrem() {
        return isEndTrem;
    }

    public void setIsEndTrem(String isEndTrem) {
        this.isEndTrem = isEndTrem;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getPosuuid() {
        return posuuid;
    }

    public void setPosuuid(String posuuid) {
        this.posuuid = posuuid;
    }

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getIvstorientation() {
        return ivstorientation;
    }

    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation;
    }

    public Date getBookdate() {
        return bookdate;
    }

    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    public String getOpentype() {
        return opentype;
    }

    public void setOpentype(String opentype) {
        this.opentype = opentype;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getRaiseCcy() {
        return raiseCcy;
    }

    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    public String getTermtype() {
        return termtype;
    }

    public void setTermtype(String termtype) {
        this.termtype = termtype;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getIsBreakeven() {
        return isBreakeven;
    }

    public void setIsBreakeven(String isBreakeven) {
        this.isBreakeven = isBreakeven;
    }

    public String getIsFhxgcp() {
        return isFhxgcp;
    }

    public void setIsFhxgcp(String isFhxgcp) {
        this.isFhxgcp = isFhxgcp;
    }

    public String getExcFinSector() {
        return excFinSector;
    }

    public void setExcFinSector(String excFinSector) {
        this.excFinSector = excFinSector;
    }

    public String getIsYllccp() {
        return isYllccp;
    }

    public void setIsYllccp(String isYllccp) {
        this.isYllccp = isYllccp;
    }

    public BigDecimal getDirtyamtsum() {
        return dirtyamtsum;
    }

    public void setDirtyamtsum(BigDecimal dirtyamtsum) {
        this.dirtyamtsum = dirtyamtsum;
    }

    public BigDecimal getCleanamtsum() {
        return cleanamtsum;
    }

    public void setCleanamtsum(BigDecimal cleanamtsum) {
        this.cleanamtsum = cleanamtsum;
    }

    public String getIsThisTrem() {
        return isThisTrem;
    }

    public void setIsThisTrem(String isThisTrem) {
        this.isThisTrem = isThisTrem;
    }

    public BigDecimal getEndamtsumOth() {
        return endamtsumOth;
    }

    public void setEndamtsumOth(BigDecimal endamtsumOth) {
        this.endamtsumOth = endamtsumOth;
    }

    public BigDecimal getPayamtsum() {
        return payamtsum;
    }

    public void setPayamtsum(BigDecimal payamtsum) {
        this.payamtsum = payamtsum;
    }

    public BigDecimal getCustomeramt() {
        return customeramt;
    }

    public void setCustomeramt(BigDecimal customeramt) {
        this.customeramt = customeramt;
    }

    public BigDecimal getBankamt() {
        return bankamt;
    }

    public void setBankamt(BigDecimal bankamt) {
        this.bankamt = bankamt;
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