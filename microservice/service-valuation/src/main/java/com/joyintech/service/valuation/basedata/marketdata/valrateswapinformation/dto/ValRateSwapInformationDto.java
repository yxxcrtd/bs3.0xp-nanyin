package com.joyintech.service.valuation.basedata.marketdata.valrateswapinformation.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.MstIrsQuotation;
import com.joyintech.tams.common.base.entity.RateSwapInfo;

import java.math.BigDecimal;
import java.util.List;

@PageId("ValRateSwapInformation002")
public class ValRateSwapInformationDto extends MstIrsQuotation {

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 利率互换行情信息列表
     */
    private List<ValRateSwapInformationDto> rateSwapInformationDtoList;
    
    /**
     * 收益率，改为单个价格类型录入，只check收益率
     */
    private BigDecimal yield;
    
	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ValRateSwapInformationDto> getValRateSwapInformationDtoList() {
        return rateSwapInformationDtoList;
    }

    public void setValRateSwapInformationDtoList(List<ValRateSwapInformationDto> rateSwapInformationDtoList) {
        this.rateSwapInformationDtoList = rateSwapInformationDtoList;
    }
}
