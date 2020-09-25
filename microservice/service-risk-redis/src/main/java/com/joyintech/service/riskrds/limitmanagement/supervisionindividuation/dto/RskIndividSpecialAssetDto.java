package com.joyintech.service.riskrds.limitmanagement.supervisionindividuation.dto;

import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;

public class RskIndividSpecialAssetDto extends RskIndividSpecialStage{

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
