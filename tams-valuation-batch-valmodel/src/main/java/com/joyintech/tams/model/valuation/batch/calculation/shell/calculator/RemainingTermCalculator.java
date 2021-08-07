package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.config.NumberEnum;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.RemainingTermInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.RemainingTermOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.util.ArithmeticUtil;
import com.joyintech.tams.model.valuation.batch.calculation.util.BasicUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *  剩余期限
 * @author zznn
 * @date 2021/7/5
 */
public class RemainingTermCalculator implements Calculator<RemainingTermInputParameter> {

	@Override
	public RemainingTermOutputParameter calculate(RemainingTermInputParameter input) {
		input.check();
		List<CashInfoDto> cashInfoDtoList = input.getCashInfoDtoList();
		for(CashInfoDto item :cashInfoDtoList){
			//期限
			BigDecimal term = BigDecimal.ZERO;
			//开始日 = 基准日
			LocalDate starDate = input.getCalDate();
			//结束日 = 实际截止日
			LocalDate endDate = item.getActualCutoffDate();
			//计息年度天数
			BigDecimal yearTotalDays = new BigDecimal(365);
			
			//开始日 + 1年 <= 结束日
			while(BasicUtil.dateBefore(starDate.plusYears(1),endDate)){
				term = ArithmeticUtil.doAdd(term,BigDecimal.ONE);
				starDate = starDate.plusYears(1);
			}
            term = ArithmeticUtil.round(ArithmeticUtil.doAdd(term,
						ArithmeticUtil.divide(new BigDecimal(BasicUtil.days(starDate, endDate)),
								yearTotalDays, NumberEnum.ALL_PRECISION.getCode())),
            		    NumberEnum.ALL_PRECISION.getCode(), RoundingMode.HALF_UP);
			item.setRemainingTerm(term);
		}
		RemainingTermOutputParameter out = new RemainingTermOutputParameter();
		out.getCashInfoDtoList().addAll(cashInfoDtoList);
		return out;
	}

	
}
