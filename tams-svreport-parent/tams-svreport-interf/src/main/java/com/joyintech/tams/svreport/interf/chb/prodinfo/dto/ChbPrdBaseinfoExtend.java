/**   
 * 功能描述：
 * @Package: com.joyintech.tams.svreport.interf.chb.prodinfo.dto 
 * @author: 王冠/wangguan   
 * @date: 2019年8月7日 下午4:42:52 
 */
package com.joyintech.tams.svreport.interf.chb.prodinfo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;

/**   
* @ClassName: ChbPrdBaseinfoExtend.java
* @Description: ChbPrdBaseinfo 扩展字段类 用于CASH_MANA_CATE，YIELD，INVEST_FEE_RATE三个字段的接入使用
*
* @author: 王冠/wangguan
* @date: 2019年8月7日 下午4:42:52 
*/
public class ChbPrdBaseinfoExtend extends ChbPrdBaseinfo {

	private String pfinprodId;
	
	private String finprodMarketId;
	
	private String rateType;
	
	private Date effDate;
	
	private BigDecimal rate;
	
	private String indexId;
	
	private BigDecimal coefficient;
	
	private BigDecimal spreadRate;
	
	private String baseRuleId;
	
	/**
	 * @return the pfinprodId
	 */
	public String getPfinprodId() {
		return pfinprodId;
	}

	/**
	 * @param pfinprodId the pfinprodId to set
	 */
	public void setPfinprodId(String pfinprodId) {
		this.pfinprodId = pfinprodId;
	}

	/**
	 * @return the finprodMarketId
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	/**
	 * @param finprodMarketId the finprodMarketId to set
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	/**
	 * @return the rateType
	 */
	public String getRateType() {
		return rateType;
	}

	/**
	 * @param rateType the rateType to set
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	/**
	 * @return the effDate
	 */
	public Date getEffDate() {
		return effDate;
	}

	/**
	 * @param effDate the effDate to set
	 */
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * @return the indexId
	 */
	public String getIndexId() {
		return indexId;
	}

	/**
	 * @param indexId the indexId to set
	 */
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	/**
	 * @return the coefficient
	 */
	public BigDecimal getCoefficient() {
		return coefficient;
	}

	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(BigDecimal coefficient) {
		this.coefficient = coefficient;
	}

	/**
	 * @return the spreadRate
	 */
	public BigDecimal getSpreadRate() {
		return spreadRate;
	}

	/**
	 * @param spreadRate the spreadRate to set
	 */
	public void setSpreadRate(BigDecimal spreadRate) {
		this.spreadRate = spreadRate;
	}

	/**
	 * @return the baseRuleId
	 */
	public String getBaseRuleId() {
		return baseRuleId;
	}

	/**
	 * @param baseRuleId the baseRuleId to set
	 */
	public void setBaseRuleId(String baseRuleId) {
		this.baseRuleId = baseRuleId;
	}
	
}
