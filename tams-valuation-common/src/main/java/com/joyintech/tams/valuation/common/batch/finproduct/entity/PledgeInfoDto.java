/*
 * 文件名：PledgeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import com.joyintech.tams.common.base.entity.FinDealPledge;

/**
 * 质押品信息查询dto
 * 
 * @author chengzhong
 * @version 1.0 2018年5月31日
 * @see PledgeInfoDto
 * @since 1.0
 */
public class PledgeInfoDto extends FinDealPledge{
    

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4785708141179042981L;

    /**
     * 交易场所
     */
    private String tradeMarket;
    
    /**
     * 登记托管机构
     */
    private String registOrg;
    
    /**
     * 金融产品类型
     */
    private String pledgeFinprodType;
   

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }
    
    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

	public String getPledgeFinprodType() {
		return pledgeFinprodType;
	}

	public void setPledgeFinprodType(String pledgeFinprodType) {
		this.pledgeFinprodType = pledgeFinprodType;
	}
    
    
    
}
