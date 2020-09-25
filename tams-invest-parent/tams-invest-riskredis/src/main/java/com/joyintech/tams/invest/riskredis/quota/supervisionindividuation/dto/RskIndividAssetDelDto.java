package com.joyintech.tams.invest.riskredis.quota.supervisionindividuation.dto;

import com.joyintech.tams.common.base.entity.RskIndividAssetDel;

public class RskIndividAssetDelDto extends RskIndividAssetDel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 金融产品全称
     */
    private String finprodName;

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}
    

}
