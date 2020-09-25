/**
 * 
 */
package com.joyintech.tams.invest.common.dto.period.trdstockbonus;

import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 股票存续期
 * @author panbin
 * @version 2019年6月19日
 * @see TrdStockBonusDto
 */
public class TrdStockBonusDto extends TrdFinProductDealTotal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 有效状态
     */
    private String statusValid;

    /**
     * 业务编号
     */
    private String busiCode;
    
    /**
     * 资产代码
     */
    private String finprodMarketId;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
    
    /**
     * 通道名称
     */
    private String finprodName;
    
    
    /**
     * 权益登记日
     */
    private Date regDate;
    
    
    /**
     * 权益登记日份额
     */
    private BigDecimal regDateAmt;

	/**
	 * 红股上市日
	 */
	private Date redStockDate;

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

	public Date getRedStockDate() {
		return redStockDate;
	}

	public void setRedStockDate(Date redStockDate) {
		this.redStockDate = redStockDate;
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

	/**
     * 
     */
    private String revokeReason;
    
    private String repIssuePartyId;
    
    private String type;
    
    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;
    
    public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	/**
     * 操作类型
     */
    private String operateType;

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


	public BigDecimal getRegDateAmt() {
		return regDateAmt;
	}

	public void setRegDateAmt(BigDecimal regDateAmt) {
		this.regDateAmt = regDateAmt;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
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

	// 多级法人改造 add by caiqun 20200615
	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	// 多级法人改造 add by caiqun 20200615
}
