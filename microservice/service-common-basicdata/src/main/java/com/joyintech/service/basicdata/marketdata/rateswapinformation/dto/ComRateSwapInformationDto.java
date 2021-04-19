package com.joyintech.service.basicdata.marketdata.rateswapinformation.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.MstIrsQuotation;

import java.math.BigDecimal;
import java.util.List;

public class ComRateSwapInformationDto extends MstIrsQuotation {

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 利率互换行情信息列表
     */
    private List<ComRateSwapInformationDto> comRateSwapInformationDtoList;
    
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

    public List<ComRateSwapInformationDto> getComRateSwapInformationDtoList() {
        return comRateSwapInformationDtoList;
    }

    public void setComRateSwapInformationDtoList(List<ComRateSwapInformationDto> comRateSwapInformationDtoList) {
        this.comRateSwapInformationDtoList = comRateSwapInformationDtoList;
    }

    private String curveIntType;

    private String baseIntCode;

    private String priceType;

    public String getCurveIntType() {
        return curveIntType;
    }

    public void setCurveIntType(String curveIntType) {
        this.curveIntType = curveIntType;
    }

    public String getBaseIntCode() {
        return baseIntCode;
    }

    public void setBaseIntCode(String baseIntCode) {
        this.baseIntCode = baseIntCode;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

}
