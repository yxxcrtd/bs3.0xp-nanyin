package com.joyintech.background.component.order.exchange.business.dto;

import com.joyintech.background.component.order.exchange.common.constant.DictCodeList;
import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;

public class EtrFutureBiddingDto extends EtrSinglePortfolioDto {
	/**
     * 序列号
     */
    private static final long serialVersionUID = 5068367164278394754L;

    public EtrFutureBiddingDto() {
        super(DictCodeList.指令业务类型_期货指令.getValue());
    }
    
	/**
     * 是否精确委托
     */
    private String isAccurate;
    
    /**
     * 衍生品买卖方向
     */
    private String derivativeBsDir;
    
    /**
     * 衍生品开平仓标志
     */
    private String derivativeOcFlag;
    
    public String getIsAccurate() {
		return isAccurate;
	}

	public void setIsAccurate(String isAccurate) {
		this.isAccurate = isAccurate;
	}

	public String getDerivativeBsDir() {
		return derivativeBsDir;
	}

	public void setDerivativeBsDir(String derivativeBsDir) {
		this.derivativeBsDir = derivativeBsDir;
	}

	public String getDerivativeOcFlag() {
		return derivativeOcFlag;
	}

	public void setDerivativeOcFlag(String derivativeOcFlag) {
		this.derivativeOcFlag = derivativeOcFlag;
	}
}
