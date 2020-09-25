/**
 * 
 */
package com.joyintech.tams.invest.common.dto.period.trdoptionduration;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 股票存续期
 * @author panbin
 * @version 2019年6月19日
 * @see TrdOptionDurationDto
 */
public class TrdOptionDurationDto extends TrdFinProductDealTotal{

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
	 * 组合代码
	 */
	private String portfolioId;

	/**
	 * 组合代码
	 */
	private String portfolioName;

    /**
     * 资产代码
     */
    private String finprodMarketId;
    
    /**
     * 金融产品简称
     */
    private String finprodAbbr;
	/**
	 * 交易方向
	 */
	private String excPs;/**
	 * 原交易编号
	 */
	private String pTradeId;

	@Override
	public String getpTradeId() {
		return pTradeId;
	}

	@Override
	public void setpTradeId(String pTradeId) {
		this.pTradeId = pTradeId;
	}


	public String getAssetType11() {
		return assetType11;
	}

	public String getAssetType11Str() {
		return SysDataUtil.getNameFromDataDict(DictCode.结构类型, getAssetType11());
	}

	public void setAssetType11(String assetType11) {
		this.assetType11 = assetType11;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public String getExerciseTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.行权方式, getExerciseType());
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}


	/**
	 * 合约类型
	 */
	private String assetType11;
	/**
	 * 合约标的
	 */
	private String targetId;
	/**
	 * 行权方式
	 */
	private String exerciseType;
	/**
	 * 行权状态
	 */
	private String xqzt;
    /**
     * 交易日
     */
    private Date cDate;
    /**
     * 行权日
     */
    private Date xqDate;
	/**
	 * 起息日
	 */
	private Date vDate;
	/**
	 * 到期日
	 */
	private Date mDate;
	/**
	 * 平台日
	 */
	private String sysDate;

	public String getSysDate() {
		return DateUtil.getSysDate();
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	/**
	 * 收付方向
	 */
	private String sffx;

	/**
	 * 到期金额
	 */
	private BigDecimal tradeAmt;

	public String getOptFeePayType() {
		return optFeePayType;
	}

	public void setOptFeePayType(String optFeePayType) {
		this.optFeePayType = optFeePayType;
	}

	/**
	 * 期权费支付类型
	 */
	private String optFeePayType;

	public Date getvDate() {
		return vDate;
	}

	public String getvDateStr() {
		return DateUtil.format(getvDate());
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	public Date getmDate() {
		return mDate;
	}
	public String getmDateStr() {
		return DateUtil.format(getmDate());
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public String getSffx() {
		return sffx;
	}
	public String getSffxStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.收付方向, getSffx());
	}

	public void setSffx(String sffx) {
		this.sffx = sffx;
	}

	@Override
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	@Override
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	@Override
	public String getPortfolioId() {
		return portfolioId;
	}

	@Override
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public Date getcDate() {
		return cDate;
	}
	public String getcDateStr() {
		return DateUtil.format(getcDate());
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public String getExcPs() {
		return excPs;
	}

	public String getExcPsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易方向, getExcPs());
	}

	public void setExcPs(String excPs) {
		this.excPs = excPs;
	}

	public String getXqzt() {
		return xqzt;
	}

	public void setXqzt(String xqzt) {
		this.xqzt = xqzt;
	}

/*	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}*/

	/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String getRegDateStr() {
		return getCDate()==null?"":sdf.format(getCDate());
	}

	public String getTradeDateStr() {
		return getTradeDate()==null?"":sdf.format(getTradeDate());
	}

	public String getSettleDateStr() {
		return getSettleDate()==null?"":sdf.format(getSettleDate());
	}
*/
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
    
	/**
     * 操作类型
     */
    private String operateType;

	public String getStatusValid() {
		return statusValid;
	}

	public String getStatusValidStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, getStatusValid());
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

	/**
	 * 获取 原交易编号
	 *
	 * @return pTradeId 原交易编号
	 */
	public String getPTradeId() {
		return this.pTradeId;
	}

	/**
	 * 设置 原交易编号
	 *
	 * @param pTradeId 原交易编号
	 */
	public void setPTradeId(String pTradeId) {
		this.pTradeId = pTradeId;
	}

	/**
	 * 获取 交易日
	 *
	 * @return cDate 交易日
	 */
	public Date getCDate() {
		return this.cDate;
	}

	/**
	 * 设置 交易日
	 *
	 * @param cDate 交易日
	 */
	public void setCDate(Date cDate) {
		this.cDate = cDate;
	}

	/**
	 * 获取 行权日
	 *
	 * @return xqDate 行权日
	 */
	public Date getXqDate() {
		return this.xqDate;
	}

	/**
	 * 设置 行权日
	 *
	 * @param xqDate 行权日
	 */
	public void setXqDate(Date xqDate) {
		this.xqDate = xqDate;
	}

	/**
	 * 获取 起息日
	 *
	 * @return vDate 起息日
	 */
	public Date getVDate() {
		return this.vDate;
	}

	/**
	 * 设置 起息日
	 *
	 * @param vDate 起息日
	 */
	public void setVDate(Date vDate) {
		this.vDate = vDate;
	}

	/**
	 * 获取 到期日
	 *
	 * @return mDate 到期日
	 */
	public Date getMDate() {
		return this.mDate;
	}

	/**
	 * 设置 到期日
	 *
	 * @param mDate 到期日
	 */
	public void setMDate(Date mDate) {
		this.mDate = mDate;
	}
}
