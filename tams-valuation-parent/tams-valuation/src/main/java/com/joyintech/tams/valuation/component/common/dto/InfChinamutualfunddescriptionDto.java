/*
 * 文件名：InfChinamutualfunddescriptionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.InfChinamutualfunddescription;
import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.common.base.entity.FinProductValuation;
import com.joyintech.tams.common.base.entity.InfChinamutualfunddescription;

/**
 * wind基金资产DTO
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月14日
 * @see InfChinamutualfunddescriptionDto
 * @since 1.0
 */
public class InfChinamutualfunddescriptionDto extends InfChinamutualfunddescription{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 金融产品信息List
     */
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;

    /**
     * 场外金融产品估值list
     */
    private List<FinProductValuation> finProductValuationList;
    /**
     * 基金市场行情list
     */
    private List<MstFundMarketInfo> mstFundMarketInfoList;
    /**
     * 获取 baseFinProductBaseInfoList
     * @return baseFinProductBaseInfoList.
     */
    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }

    
    /**
     * 设置 baseFinProductBaseInfoList
     * @param baseFinProductBaseInfoList baseFinProductBaseInfoList
     */
    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }


	public List<FinProductValuation> getFinProductValuationList() {
		return finProductValuationList;
	}


	public void setFinProductValuationList(List<FinProductValuation> finProductValuationList) {
		this.finProductValuationList = finProductValuationList;
	}


	public List<MstFundMarketInfo> getMstFundMarketInfoList() {
		return mstFundMarketInfoList;
	}


	public void setMstFundMarketInfoList(List<MstFundMarketInfo> mstFundMarketInfoList) {
		this.mstFundMarketInfoList = mstFundMarketInfoList;
	}
    
    

}
