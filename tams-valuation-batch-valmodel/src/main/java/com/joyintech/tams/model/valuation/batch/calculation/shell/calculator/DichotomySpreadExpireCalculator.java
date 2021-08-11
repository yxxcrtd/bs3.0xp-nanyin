package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.DichotomySpreadExpireInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.DichotomySpreadExpireOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.util.BasicUtil;

import java.math.BigDecimal;

/**
 * 二分法计算点差-到期
 * @author zznn
 * @date 2021/7/5
 */
public class DichotomySpreadExpireCalculator implements Calculator<DichotomySpreadExpireInputParameter> {

    @Override
    public DichotomySpreadExpireOutputParameter calculate(DichotomySpreadExpireInputParameter input) {
        input.check();
        DichotomySpreadExpireOutputParameter spreadOutParameter = new DichotomySpreadExpireOutputParameter();
        BigDecimal spread = BasicUtil.dichotomy(input.getTarget(),input.getPrecision(),input.getTotalTimes(), tempAmount -> {
            return BasicUtil.calPriceByMatYield(input.getCashInfoDtoList(),input.getMatYield(),input.getYearIntRate().getValue(), tempAmount);});
        spreadOutParameter.setSpread(spread);
        return spreadOutParameter;
    }
}

