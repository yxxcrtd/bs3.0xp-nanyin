package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportassetindex.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class DisReportAssetIndexResult extends NeedOperateEntity {
	/**
     * 流水号
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 业务明细类型
     */
    private String busiType;

    /**
     * 业务明细简称
     */
    private String busiAbbr;

    /**
     * 业务明细名称
     */
    private String busiName;

    /**
     * 资产细分类
     */
    private String bondType;

    /**
     * 资产细分类排序号
     */
    private String bondTypeOrder;

    /**
     * 资产细分类(汉字)
     */
    private String bondTypeZ;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 当前持仓
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posAmt;

    /**
     * 计提利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 日摊销收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dailydscYield;

    /**
     * 当日发生应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 当日累计应计利息余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 当日份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyPosition;

    /**
     * 当日成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyCostAmt;

    /**
     * 当日公允价值变动
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngpl;

    /**
     * 剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays1;

    /**
     * 剩余存续期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDays2;

    /**
     * 剩余期限用成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal endDaysCostAmt;

    /**
     * 资产负债标识
     */
    private String alFlag;

    /**
     * 剩余存续期
     */
    private Integer surplusTerm;

    /**
     * 偏离金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviationAmt;

    /**
     * null
     */
    private String inputType;

    /**
     * null
     */
    private String inputUser;

    /**
     * null
     */
    private String adapterId;

    /**
     * null
     */
    private String rateType;
    
    /**
     * 参数配置
     */
    private Map<String,Object> param;
    
    

    public Map<String, Object> getParam() {
		return param;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

	/**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 估值日
     * @return 估值日
     */
    public Date getValueDate() {
        return valueDate;
    }

    /**
     * 设置 估值日
     * @param valueDate 估值日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 业务明细类型
     * @return 业务明细类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务明细类型
     * @param busiType 业务明细类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 业务明细简称
     * @return 业务明细简称
     */
    public String getBusiAbbr() {
        return busiAbbr;
    }

    /**
     * 设置 业务明细简称
     * @param busiAbbr 业务明细简称
     */
    public void setBusiAbbr(String busiAbbr) {
        this.busiAbbr = busiAbbr == null ? null : busiAbbr.trim();
    }

    /**
     * 取得 业务明细名称
     * @return 业务明细名称
     */
    public String getBusiName() {
        return busiName;
    }

    /**
     * 设置 业务明细名称
     * @param busiName 业务明细名称
     */
    public void setBusiName(String busiName) {
        this.busiName = busiName == null ? null : busiName.trim();
    }

    /**
     * 取得 资产细分类
     * @return 资产细分类
     */
    public String getBondType() {
        return bondType;
    }

    /**
     * 设置 资产细分类
     * @param bondType 资产细分类
     */
    public void setBondType(String bondType) {
        this.bondType = bondType == null ? null : bondType.trim();
    }

    /**
     * 取得 资产细分类排序号
     * @return 资产细分类排序号
     */
    public String getBondTypeOrder() {
        return bondTypeOrder;
    }

    /**
     * 设置 资产细分类排序号
     * @param bondTypeOrder 资产细分类排序号
     */
    public void setBondTypeOrder(String bondTypeOrder) {
        this.bondTypeOrder = bondTypeOrder == null ? null : bondTypeOrder.trim();
    }

    /**
     * 取得 资产细分类(汉字)
     * @return 资产细分类(汉字)
     */
    public String getBondTypeZ() {
        return bondTypeZ;
    }

    /**
     * 设置 资产细分类(汉字)
     * @param bondTypeZ 资产细分类(汉字)
     */
    public void setBondTypeZ(String bondTypeZ) {
        this.bondTypeZ = bondTypeZ == null ? null : bondTypeZ.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim == null ? null : invAim.trim();
    }

    /**
     * 取得 当前持仓
     * @return 当前持仓
     */
    public BigDecimal getPosAmt() {
        return posAmt;
    }

    /**
     * 设置 当前持仓
     * @param posAmt 当前持仓
     */
    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * 取得 计提利率
     * @return 计提利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 计提利率
     * @param intRate 计提利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 日摊销收益率
     * @return 日摊销收益率
     */
    public BigDecimal getDailydscYield() {
        return dailydscYield;
    }

    /**
     * 设置 日摊销收益率
     * @param dailydscYield 日摊销收益率
     */
    public void setDailydscYield(BigDecimal dailydscYield) {
        this.dailydscYield = dailydscYield;
    }

    /**
     * 取得 当日发生应计利息
     * @return 当日发生应计利息
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日发生应计利息
     * @param tdyIntincexpAdd 当日发生应计利息
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    /**
     * 取得 当日累计应计利息余额
     * @return 当日累计应计利息余额
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 当日累计应计利息余额
     * @param tdyIntincexp 当日累计应计利息余额
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 当日份额
     * @return 当日份额
     */
    public BigDecimal getTdyPosition() {
        return tdyPosition;
    }

    /**
     * 设置 当日份额
     * @param tdyPosition 当日份额
     */
    public void setTdyPosition(BigDecimal tdyPosition) {
        this.tdyPosition = tdyPosition;
    }

    /**
     * 取得 当日成本
     * @return 当日成本
     */
    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    /**
     * 设置 当日成本
     * @param tdyCostAmt 当日成本
     */
    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    /**
     * 取得 当日公允价值变动
     * @return 当日公允价值变动
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    /**
     * 设置 当日公允价值变动
     * @param tdyFloatIngpl 当日公允价值变动
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    /**
     * 取得 剩余期限
     * @return 剩余期限
     */
    public BigDecimal getEndDays1() {
        return endDays1;
    }

    /**
     * 设置 剩余期限
     * @param endDays1 剩余期限
     */
    public void setEndDays1(BigDecimal endDays1) {
        this.endDays1 = endDays1;
    }

    /**
     * 取得 剩余存续期限
     * @return 剩余存续期限
     */
    public BigDecimal getEndDays2() {
        return endDays2;
    }

    /**
     * 设置 剩余存续期限
     * @param endDays2 剩余存续期限
     */
    public void setEndDays2(BigDecimal endDays2) {
        this.endDays2 = endDays2;
    }

    /**
     * 取得 剩余期限用成本
     * @return 剩余期限用成本
     */
    public BigDecimal getEndDaysCostAmt() {
        return endDaysCostAmt;
    }

    /**
     * 设置 剩余期限用成本
     * @param endDaysCostAmt 剩余期限用成本
     */
    public void setEndDaysCostAmt(BigDecimal endDaysCostAmt) {
        this.endDaysCostAmt = endDaysCostAmt;
    }

    /**
     * 取得 资产负债标识
     * @return 资产负债标识
     */
    public String getAlFlag() {
        return alFlag;
    }

    /**
     * 设置 资产负债标识
     * @param alFlag 资产负债标识
     */
    public void setAlFlag(String alFlag) {
        this.alFlag = alFlag == null ? null : alFlag.trim();
    }

    /**
     * 取得 剩余存续期
     * @return 剩余存续期
     */
    public Integer getSurplusTerm() {
        return surplusTerm;
    }

    /**
     * 设置 剩余存续期
     * @param surplusTerm 剩余存续期
     */
    public void setSurplusTerm(Integer surplusTerm) {
        this.surplusTerm = surplusTerm;
    }

    /**
     * 取得 偏离金额
     * @return 偏离金额
     */
    public BigDecimal getDeviationAmt() {
        return deviationAmt;
    }

    /**
     * 设置 偏离金额
     * @param deviationAmt 偏离金额
     */
    public void setDeviationAmt(BigDecimal deviationAmt) {
        this.deviationAmt = deviationAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 null
     * @param inputType null
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 null
     * @param inputUser null
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 null
     * @param adapterId null
     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 null
     * @param rateType null
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }
    
}
