package com.joyintech.tams.invest.riskredis.quota.medianmanagement.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.riskredis.quota.medianmanagement.validator.PltMedianManagementValidator;


/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagementDto
 * @since 1.0
 */
@PageId("PltMedianManagement001")
@ValidLogic(targetClass = PltMedianManagementValidator.class, groups = {ValidateGroupEdit.class})
public class PltMedianManagementDto extends RskIndicatorParaCalcdetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8559576346600595510L;
    
    /**
     * 实占编号（交易代码）
     */
    private String eventId;

    /**
     * 事件类型(1：投资交易、2：交易拆分)
     */
    private String eventType;
    
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 维度资产资产代码
     */
    private String tassetCode;
    
    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal priceAmt;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeNum;
    
    /**
     * 资产类型
     */
    private String assetType;
    
    /**
     * 交易方向
     */
    private String ps;
    
    /**
     * 事件日期
     */
    private Date eventDate;
    
    /**
     * 序号
     */
    private String num;
    
    /**
     * 参数名称
     */
    private String paraName;
    
    /**
     * 交易类型
     */
    private String itemValue;
    
    /**
     * 产品名称
     */
    private String portfolioName;
    
    /**
     * 事件类型前半部分
     */
    private String typey1;
    
    /**
     * 事件类型后半部分
     */
    private String typey2;
    
    public String getTypey1() {
		return typey1;
	}

	public void setTypey1(String typey1) {
		this.typey1 = typey1;
	}

	public String getTypey2() {
		return typey2;
	}

	public void setTypey2(String typey2) {
		this.typey2 = typey2;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}
	
	/**
	 * 事件类型
	 */
	private String typey;

    private String operateType;

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getTypey() {
		return typey;
	}

	public void setTypey(String typey) {
		this.typey = typey;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getTassetCode() {
		return tassetCode;
	}

	public void setTassetCode(String tassetCode) {
		this.tassetCode = tassetCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPriceAmt() {
		return priceAmt;
	}

	public void setPriceAmt(BigDecimal priceAmt) {
		this.priceAmt = priceAmt;
	}

	public BigDecimal getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(BigDecimal tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	
	/**
	 * 日期
	 */
	public String getSupDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return StringUtil.isNotEmpty(this.getSupDate())?sdf.format(this.getSupDate()):"";
	}
	
	/**
	 * 入库时间
	 */
	public String getDotimestampStr() {
		String str = "";
		if(StringUtil.isNotEmpty(this.getDotimestamp())){
			SimpleDateFormat f1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
			Date d;
			try {
				d = f1.parse(this.getDotimestamp());
			} catch (ParseException e) {
				return this.getDotimestamp();
			}
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			str = f.format(d);
		}
		return str;
	}
	
	/**
	 * 处理时间
	 */
	public String getCreateTimeStr(){
		String str = "";
		if(StringUtil.isNotEmpty(this.getCreateTime().toString())){
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			str = f.format(this.getCreateTime());
		}
		return str;
	}
	
	/**
     * 交易数量
     */
    public String getTradeNumStr(){
    	String str = "";
    	if(StringUtil.isNotEmpty(this.getTradeNum())){
    		int i = this.getTradeNum().compareTo(BigDecimal.ZERO);
    		if(i == 1 || i==-1){
    			DecimalFormat df = new DecimalFormat("#,###.00");
    			str = String.valueOf(df.format(((this.getTradeNum().doubleValue())*100)/100));
    		}else{
    			DecimalFormat df = new DecimalFormat("0.00");
    			str = String.valueOf(df.format(((this.getTradeNum().doubleValue())*100)/100));
    		}
    	}
		return str;
    }
    
    /**
     * 交易价格
     */
    public String getPriceAmtStr(){
    	String str = "";
    	if(StringUtil.isNotEmpty(this.getPriceAmt())){
    		int i = this.getPriceAmt().compareTo(BigDecimal.ZERO);
    		if(i == 1 || i==-1){
    			DecimalFormat df = new DecimalFormat("#,###.00");
    			str = String.valueOf(df.format(((this.getPriceAmt().doubleValue())*100)/100));
    		}else{
    			DecimalFormat df = new DecimalFormat("0.00");
    			str = String.valueOf(df.format(((this.getPriceAmt().doubleValue())*100)/100));
    		}
    	}
		return str;
    }
    
    /**
     * 交易金额
     */
    public String getAmountStr(){
    	String str = "";
    	if(StringUtil.isNotEmpty(this.getAmount())){
    		int i = this.getAmount().compareTo(BigDecimal.ZERO);
        	if(i == 1 || i==-1){
        		DecimalFormat df = new DecimalFormat("#,###.00");
        		str = String.valueOf(df.format(((this.getAmount().doubleValue())*100)/100));
        	}else{
        		DecimalFormat df = new DecimalFormat("0.00");
        		str = String.valueOf(df.format(((this.getAmount().doubleValue())*100)/100));
        	}
    	}
    	return str;
    }
    
    /**
     * 本次发生额
     */
    public String getChangeValueStr(){
    	String str = "";
    	if(StringUtil.isNotEmpty(this.getChangeValue())){
    		int i = this.getChangeValue().compareTo(BigDecimal.ZERO);
        	if(i == 1 || i==-1){
        		DecimalFormat df = new DecimalFormat("#,###.00");
        		str = String.valueOf(df.format(((this.getChangeValue().doubleValue())*100)/100));
        	}else{
        		DecimalFormat df = new DecimalFormat("0.00");
        		str = String.valueOf(df.format(((this.getChangeValue().doubleValue())*100)/100));
        	}
    	}
    	return str;
    }
    
    /**
     * 计算后结果
     */
    public String getNewValueStr(){
    	String str = "";
    	if(StringUtil.isNotEmpty(this.getNewValue())){
    		int i = this.getNewValue().compareTo(BigDecimal.ZERO);
        	if(i == 1 || i==-1){
        		DecimalFormat df = new DecimalFormat("#,###.00");
        		str = String.valueOf(df.format(((this.getNewValue().doubleValue())*100)/100));
        	}else{
        		DecimalFormat df = new DecimalFormat("0.00");
        		str = String.valueOf(df.format(((this.getNewValue().doubleValue())*100)/100));
        	}
    	}
    	return str;
    }
    
    /**
     * 计算前结果
     */
    public String getOldValueStr(){
    	String str = "";
    	if(StringUtil.isNotEmpty(this.getOldValue())){
    		int i = this.getOldValue().compareTo(BigDecimal.ZERO);
        	if(i == 1 || i==-1){
        		DecimalFormat df = new DecimalFormat("#,###.00");
        		str = String.valueOf(df.format(((this.getOldValue().doubleValue())*100)/100));
        	}else{
        		DecimalFormat df = new DecimalFormat("0.00");
        		str = String.valueOf(df.format(((this.getOldValue().doubleValue())*100)/100));
        	}
    	}
    	return str;
    }
    
    /**
     * 资产类型
     */
	public String getAssetTypeStr(){
	    if (StringUtil.isNotEmpty(getAssetType()) && StringUtil.equals(getAssetType(), DictCodeListExt1.金融产品类型_理财产品.getValue())) {
	        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型,getAssetType());
        }
    	return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,getAssetType());
    }
	
	/**
     * 交易方向
     */
	public String getPsStr(){
    	return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易方向,this.getPs());
    }
	
	/**
     * 参数粒度
     */
	public String getParaGranularityStr(){
    	return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.参数粒度,this.getParaGranularity());
    }
	
	/**
     * 参数粒度
     */
	public String getTypeyStr(){
    	String str = "";
	    // modified by wangzhiang TYPE6对应数据字典过于复杂，现调整为资产类型 20190417 start
//	    str +=SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,this.getTypey1())==null?"":SysDataUtil.getNameFromDataDict(DictCode.平安资产类型,this.getTypey1());
        if (StringUtil.isNotEmpty(getAssetType()) && StringUtil.equals(getAssetType(), DictCodeListExt1.金融产品类型_理财产品.getValue())) {
            str += SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.getAssetType())
                == null ? "" : SysDataUtil.getNameFromDataDict(DictCode.金融产品类型, this.getAssetType());
        }else {
    	  str += SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getAssetType())
			   == null ? "" : SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getAssetType());
        }
    	// modified by wangzhiang TYPE6对应数据字典过于复杂，现调整为资产类型 20190417 end
		str +=SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易方向, this.getTypey2())==null?"":SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.交易方向, this.getTypey2());
    	return str;
    }
}
