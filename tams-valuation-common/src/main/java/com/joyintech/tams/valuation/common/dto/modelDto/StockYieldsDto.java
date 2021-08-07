package com.joyintech.tams.valuation.common.dto.modelDto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class StockYieldsDto extends BaseEntity {

    /**
     * 日期
     */
    private Date cdate;
    /**
     * 股票对数
     */
    private BigDecimal stockLogarithm;

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public BigDecimal getStockLogarithm() {
        return stockLogarithm;
    }

    public void setStockLogarithm(BigDecimal stockLogarithm) {
        this.stockLogarithm = stockLogarithm;
    }
}
