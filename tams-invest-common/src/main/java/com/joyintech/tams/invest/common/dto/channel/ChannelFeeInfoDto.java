package com.joyintech.tams.invest.common.dto.channel;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

public class ChannelFeeInfoDto extends BaseEntity {
    //组合代码
    private String portfolioId;
    //组合名称
    private String portFolioName;
    //理论费用
    private BigDecimal payAmt;
    //实付费用
    private BigDecimal actPayAmt;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortFolioName() {
        return portFolioName;
    }

    public void setPortFolioName(String portFolioName) {
        this.portFolioName = portFolioName;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getActPayAmt() {
        return actPayAmt;
    }

    public void setActPayAmt(BigDecimal actPayAmt) {
        this.actPayAmt = actPayAmt;
    }
}
