package com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam;

import com.joyintech.tams.model.valuation.batch.calculation.OutputParameter;

import java.math.BigDecimal;

/**
 * 贴现模型-二分法计算到期点差结果
 * @author zznn
 * @date 2021/7/6
 */
public class DichotomySpreadExpireOutputParameter implements OutputParameter {
    private BigDecimal spread;

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }
}

