
package com.joyintech.service.common.finproductrating.dto;

import com.joyintech.tams.common.base.entity.MstBondCreditRating;

/**
 * 产品评级dto
 * @see FinProductRatingDto
 * @since 1.0
 */
public class FinProductRatingDto extends MstBondCreditRating{
	/**
	 * 市场代码
	 */
	private String marketId;
	/**
	 * 机构代码
	 */
	private String orgId;
	/**
	 * 原始评级结果
	 */
	private String mstRatingResult01;
	/**
	 * 标准评级结果
	 */
	private String mstRatingResult02;
	
	public String getMarketId() {
		return marketId;
	}
	
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	
	public String getOrgId() {
		return orgId;
	}
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getMstRatingResult01() {
		return mstRatingResult01;
	}

	public void setMstRatingResult01(String mstRatingResult01) {
		this.mstRatingResult01 = mstRatingResult01;
	}

	public String getMstRatingResult02() {
		return mstRatingResult02;
	}

	public void setMstRatingResult02(String mstRatingResult02) {
		this.mstRatingResult02 = mstRatingResult02;
	}
	
}
