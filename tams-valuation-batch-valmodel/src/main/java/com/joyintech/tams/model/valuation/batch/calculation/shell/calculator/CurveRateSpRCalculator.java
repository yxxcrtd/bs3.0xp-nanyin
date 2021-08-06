package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.CalculatorFactory;
import com.joyintech.tams.model.valuation.batch.calculation.entity.KeyPointDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.TargetRateInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.TargetRateOutputParameter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 贴现模型-现金流信息对应的曲线收益率
 * @author 吴洁
 * @version 1.0 2021年7月3日
 * @see CurveRateSpRCalculator
 * @since 1.0
 */
public class CurveRateSpRCalculator implements Calculator<TargetRateInputParameter> {

	@Override
	public TargetRateOutputParameter calculate(TargetRateInputParameter input) {
		input.check();
		
		TargetRateOutputParameter  out = new TargetRateOutputParameter();
		
		BigDecimal spR = this.getCurvYield(input.getKeyPointDtoList(), input.getTargetTerm());
		if(spR == null){
			//插值计算
			TargetRateCalculator trCalculator = CalculatorFactory.createTargetRateCalculator();
			TargetRateOutputParameter troutput = trCalculator.calculate(input);
			spR = troutput.getTargerYield();
		}
		out.setTargerYield(spR);
		return out;
	}
	
	private BigDecimal getCurvYield(List<KeyPointDto> keyPointDtoList, BigDecimal remainingTerm){
		BigDecimal yield = null;
		for(KeyPointDto item :keyPointDtoList){
			if(item.getKeyPoint().compareTo(remainingTerm) == 0){
				yield = item.getYield();
			}
		}
		return yield;
	}

}
