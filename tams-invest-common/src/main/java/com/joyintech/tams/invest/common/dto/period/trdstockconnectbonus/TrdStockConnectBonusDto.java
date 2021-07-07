package com.joyintech.tams.invest.common.dto.period.trdstockconnectbonus;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 港股通股票存续期
 * @see TrdStockConnectBonusDto
 */
public class TrdStockConnectBonusDto extends TrdFinProductDealTotal {

	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型
	 */
	private String operateType;
	
	/**
     * 有效状态
     */
    private String statusValid;

    /**
     * 业务编号
     */
    private String busiCode;

	/**
	 * 红股上市日
	 */
	private Date redStockDate;
    
    /**
     * 资产代码
     */
    private String finprodMarketId;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    /**
     * 资产名称
     */
    private String finprodName;

	/**
	 * 权益登记日字符串
	 */
	private String regDateStr;

	/**
	 * 除权除息日字符串
	 */
	private String tradeDateStr;

	/**
	 * 派息日字符串
	 */
	private String settleDateStr;

	// 多级法人改造 add by caiqun 20200615
	/**
	 * 所属机构名称
	 */
	private String institutionName;
	// 多级法人改造 add by caiqun 20200615

	/**
	 * 上交所还是深交所
	 */
	private String hkseMarket;

	/**
	 * 是否含股利选择权
	 */
	private String ifDividend;

	/**
	 * 汇率
	 */
	private String dividCcy;

	/**
	 * 汇率
	 */
	private BigDecimal exchangeRate;

	/**
	 * 以股代息价格
	 */
	private BigDecimal replacePrice;

	/**
	 * 以股代息股本
	 */
	private BigDecimal replaceAmt;

    private String revokeReason;
    
    private String repIssuePartyId;
    
    private String type;
    
    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getStatusValid() {
		return statusValid;
	}

	public void setStatusValid(String statusValid) {
		this.statusValid = statusValid;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public Date getRedStockDate() {
		return redStockDate;
	}

	public void setRedStockDate(Date redStockDate) {
		this.redStockDate = redStockDate;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String getRegDateStr() {
		return getRegDate()==null?"":sdf.format(getRegDate());
	}

	public String getTradeDateStr() {
		return getTradeDate()==null?"":sdf.format(getTradeDate());
	}

	public String getSettleDateStr() {
		return getSettleDate()==null?"":sdf.format(getSettleDate());
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getHkseMarket() {
		return hkseMarket;
	}

	public void setHkseMarket(String hkseMarket) {
		this.hkseMarket = hkseMarket;
	}

	public String getIfDividend() {
		return ifDividend;
	}

	public void setIfDividend(String ifDividend) {
		this.ifDividend = ifDividend;
	}

	public String getDividCcy() {
		return dividCcy;
	}

	public void setDividCcy(String dividCcy) {
		this.dividCcy = dividCcy;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getReplacePrice() {
		return replacePrice;
	}

	public void setReplacePrice(BigDecimal replacePrice) {
		this.replacePrice = replacePrice;
	}

	public BigDecimal getReplaceAmt() {
		return replaceAmt;
	}

	public void setReplaceAmt(BigDecimal replaceAmt) {
		this.replaceAmt = replaceAmt;
	}

	public String getRevokeReason() {
		return revokeReason;
	}

	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	public String getRepIssuePartyId() {
		return repIssuePartyId;
	}

	public void setRepIssuePartyId(String repIssuePartyId) {
		this.repIssuePartyId = repIssuePartyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
		return baseTotalTradeInfoDto;
	}

	public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
		this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
	}
}
