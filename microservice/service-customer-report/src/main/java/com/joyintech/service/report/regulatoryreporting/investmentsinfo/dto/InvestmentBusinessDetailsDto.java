package com.joyintech.service.report.regulatoryreporting.investmentsinfo.dto;

import java.math.BigDecimal;

/**
 * 表内外投资业务情况统计表大型银行统计报表
 * 表二 表内外投资业务明细表实体类
 * @author Administrator
 *
 */
public class InvestmentBusinessDetailsDto {
	// 属性代码
	private String propertycode;
	// 表一、表六本季末数额
	private BigDecimal tqeAmount;
	// 表一、表六上季末数额
	private BigDecimal tlqeAmount;
	// 表二本季末数额
	private BigDecimal sessionendNum;
	// 表二本季末不良资产余额
	private BigDecimal nonperformingAssetNum;
	// 表二上季末数额
	private BigDecimal lastSessionendNum;
	// 表二上季末不良资产余额
	private BigDecimal lastNonperformingAssetNum;
	// 表三 非标准化债权类投资本季末数额
	private BigDecimal nonsStandardAmount;
	// 表三 非标准化债权类投资上季末数额
	private BigDecimal lastNonsStandardAmount;
	// 表三 产业投资基金本季末数额
	private BigDecimal indInvestmentAmount;
	// 表三 产业投资基金上季末数额
	private BigDecimal lastIndInvestmentAmount;
	// 表三 其他本季末数额
	private BigDecimal otherAmount;
	// 表三 其他上季末数额
	private BigDecimal lastOtherAmount;
	// 表四本季末数额余额
	private BigDecimal sessionendBalance;
	// 表四 本季末平均年化收益水平 收益率
	private BigDecimal sessionendYield;
	// 表四上季末数额余额
	private BigDecimal lastSessionendBalance;
	// 表四 上季末平均年化收益水平 收益率
	private BigDecimal lastSessionendYield;
	//表四  期限类型
	private String termType;
	//表四计算用 份额
	private BigDecimal shareamt;
	//表四计算用 收益=份额*收益率
	private BigDecimal syamt;
	//表四计算用 上季度份额
	private BigDecimal lastshareamt;
	//表四计算用 上季度收益=份额*收益率
	private BigDecimal lastsyamt;
	//资产代码（资管计划代码）表二算底层用 
	private String assetcode;
	//资产类型  表二算底层用 
	private String assettype;
	private String assettype2;
	private String remark;
	//表三附表
	//总计，本季末数额
	private BigDecimal totleBalance;
	//总计，上季末数额
	private BigDecimal lasttotleBalance;
    //表7中行业类别
	private String industryType;
	//机构名称
	private String orgName;
	//数据时点
	private String reportDate;

	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @return nonsStandardAmount  
	 */
	
	public BigDecimal getNonsStandardAmount() {
		return nonsStandardAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @param nonsStandardAmount nonsStandardAmount  
	 */
	
	public void setNonsStandardAmount(BigDecimal nonsStandardAmount) {
		this.nonsStandardAmount = nonsStandardAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @return lastNonsStandardAmount  
	 */
	
	public BigDecimal getLastNonsStandardAmount() {
		return lastNonsStandardAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @param lastNonsStandardAmount lastNonsStandardAmount  
	 */
	
	public void setLastNonsStandardAmount(BigDecimal lastNonsStandardAmount) {
		this.lastNonsStandardAmount = lastNonsStandardAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @return indInvestmentAmount  
	 */
	
	public BigDecimal getIndInvestmentAmount() {
		return indInvestmentAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @param indInvestmentAmount indInvestmentAmount  
	 */
	
	public void setIndInvestmentAmount(BigDecimal indInvestmentAmount) {
		this.indInvestmentAmount = indInvestmentAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @return lastIndInvestmentAmount  
	 */
	
	public BigDecimal getLastIndInvestmentAmount() {
		return lastIndInvestmentAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @param lastIndInvestmentAmount lastIndInvestmentAmount  
	 */
	
	public void setLastIndInvestmentAmount(BigDecimal lastIndInvestmentAmount) {
		this.lastIndInvestmentAmount = lastIndInvestmentAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @return otherAmount  
	 */
	
	public BigDecimal getOtherAmount() {
		return otherAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @param otherAmount otherAmount  
	 */
	
	public void setOtherAmount(BigDecimal otherAmount) {
		this.otherAmount = otherAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @return lastOtherAmount  
	 */
	
	public BigDecimal getLastOtherAmount() {
		return lastOtherAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月9日 下午2:27:33  
	 * @param lastOtherAmount lastOtherAmount  
	 */
	
	public void setLastOtherAmount(BigDecimal lastOtherAmount) {
		this.lastOtherAmount = lastOtherAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:10:10  
	 * @return tqeAmount  
	 */
	
	public BigDecimal getTqeAmount() {
		return tqeAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:10:10  
	 * @param tqeAmount tqeAmount  
	 */
	
	public void setTqeAmount(BigDecimal tqeAmount) {
		this.tqeAmount = tqeAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:10:10  
	 * @return tlqeAmount  
	 */
	
	public BigDecimal getTlqeAmount() {
		return tlqeAmount;
	}
	/**  
	 * @Description   
	 * @Author      高健  
	 * @Date        2019年8月7日 下午3:10:10  
	 * @param tlqeAmount tlqeAmount  
	 */
	
	public void setTlqeAmount(BigDecimal tlqeAmount) {
		this.tlqeAmount = tlqeAmount;
	}
	public String getPropertycode() {
		return propertycode;
	}
	public void setPropertycode(String propertycode) {
		this.propertycode = propertycode;
	}
	public BigDecimal getSessionendNum() {
		return sessionendNum;
	}
	public void setSessionendNum(BigDecimal sessionendNum) {
		this.sessionendNum = sessionendNum;
	}
	public BigDecimal getNonperformingAssetNum() {
		return nonperformingAssetNum;
	}
	public void setNonperformingAssetNum(BigDecimal nonperformingAssetNum) {
		this.nonperformingAssetNum = nonperformingAssetNum;
	}
	public BigDecimal getLastSessionendNum() {
		return lastSessionendNum;
	}
	public void setLastSessionendNum(BigDecimal lastSessionendNum) {
		this.lastSessionendNum = lastSessionendNum;
	}
	public BigDecimal getLastNonperformingAssetNum() {
		return lastNonperformingAssetNum;
	}
	public void setLastNonperformingAssetNum(BigDecimal lastNonperformingAssetNum) {
		this.lastNonperformingAssetNum = lastNonperformingAssetNum;
	}
	public BigDecimal getSessionendBalance() {
		return sessionendBalance;
	}
	public void setSessionendBalance(BigDecimal sessionendBalance) {
		this.sessionendBalance = sessionendBalance;
	}
	public BigDecimal getSessionendYield() {
		return sessionendYield;
	}
	public void setSessionendYield(BigDecimal sessionendYield) {
		this.sessionendYield = sessionendYield;
	}
	public BigDecimal getLastSessionendBalance() {
		return lastSessionendBalance;
	}
	public void setLastSessionendBalance(BigDecimal lastSessionendBalance) {
		this.lastSessionendBalance = lastSessionendBalance;
	}
	public BigDecimal getLastSessionendYield() {
		return lastSessionendYield;
	}
	public void setLastSessionendYield(BigDecimal lastSessionendYield) {
		this.lastSessionendYield = lastSessionendYield;
	}
	public String getTermType() {
		return termType;
	}
	public void setTermType(String termType) {
		this.termType = termType;
	}
	public BigDecimal getShareamt() {
		return shareamt;
	}
	public void setShareamt(BigDecimal shareamt) {
		this.shareamt = shareamt;
	}
	public BigDecimal getSyamt() {
		return syamt;
	}
	public void setSyamt(BigDecimal syamt) {
		this.syamt = syamt;
	}
	public BigDecimal getLastshareamt() {
		return lastshareamt;
	}
	public void setLastshareamt(BigDecimal lastshareamt) {
		this.lastshareamt = lastshareamt;
	}
	public BigDecimal getLastsyamt() {
		return lastsyamt;
	}
	public void setLastsyamt(BigDecimal lastsyamt) {
		this.lastsyamt = lastsyamt;
	}
	public String getAssetcode() {
		return assetcode;
	}
	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}
	public String getAssettype() {
		return assettype;
	}
	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}
	public String getAssettype2() {
		return assettype2;
	}
	public void setAssettype2(String assettype2) {
		this.assettype2 = assettype2;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getTotleBalance() {
		return totleBalance;
	}

	public void setTotleBalance(BigDecimal totleBalance) {
		this.totleBalance = totleBalance;
	}

	public BigDecimal getLasttotleBalance() {
		return lasttotleBalance;
	}

	public void setLasttotleBalance(BigDecimal lasttotleBalance) {
		this.lasttotleBalance = lasttotleBalance;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
}
