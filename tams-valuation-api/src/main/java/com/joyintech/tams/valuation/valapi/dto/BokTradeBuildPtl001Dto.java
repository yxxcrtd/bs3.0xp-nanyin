package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;

/**
 * @ClassName BokTradeBuildPtl001Dto
 * @Description: 交易上带的组合信息
 * @Author yanya
 * @Date 2021/2/25
 * @Version V1.0
 **/
public class BokTradeBuildPtl001Dto implements Serializable {
    private static final long serialVersionUID = -4177595160038553266L;
    /**
     * 组合代码
     */
    @ValInterfApiField
    private String portfolioId;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    @Override
    public String toString() {
        return "BokTradeBuildPtl001Dto{" +
                "portfolioId='" + portfolioId + '\'' +
                '}';
    }
}
