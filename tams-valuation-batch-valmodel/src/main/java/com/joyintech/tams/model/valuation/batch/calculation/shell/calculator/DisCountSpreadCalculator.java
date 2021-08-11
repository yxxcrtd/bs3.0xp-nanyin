package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.CalculatorFactory;
import com.joyintech.tams.model.valuation.batch.calculation.constant.CurveType;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.entity.KeyPointDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.*;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 贴现模型-现金流折现法点差计算
 * @author zznn
 * @date 2021/7/2
 */
public class DisCountSpreadCalculator implements Calculator<DisCountInputParameter> {

	@Override
	public DisCountOutputParameter calculate(DisCountInputParameter input) {
		input.check();
		DisCountOutputParameter out = new DisCountOutputParameter();
		
		//现金流信息
		List<CashInfoDto> cashInfoDtoList = new ArrayList<>();
		//1. 剩余期限计算
		RemainingTermInputParameter rtInput = new RemainingTermInputParameter(input.getCalDate(),input.getCashInfoDtoList(),
				input.getFirstPayDate(),input.getVariety(),input.getCalMethod());
		RemainingTermCalculator rtCalculator = CalculatorFactory.createRemainingTermCalculator();
		RemainingTermOutputParameter rtOutput = rtCalculator.calculate(rtInput);
		cashInfoDtoList.addAll(rtOutput.getCashInfoDtoList());
		
		//按实际结束日升序
        Collections.sort(cashInfoDtoList, new Comparator<CashInfoDto>() {
            @Override
            public int compare(CashInfoDto o1, CashInfoDto o2) {
            	return o1.getActualCutoffDate().compareTo(o2.getActualCutoffDate());
            }
        });
		
		//2. 点差计算
		CurveType curveType = input.getCurveInfoDto().getCurveType();
		if (CurveType.即期.equals(curveType)) {
			for(CashInfoDto item:cashInfoDtoList){
				//即期R是否已经计算
				boolean  sprCalFalg = false;
				if(!StringUtil.isEmptyList(input.getCurveInfoDto().getKeyPointDtoList())){
					for (KeyPointDto item2 : input.getCurveInfoDto().getKeyPointDtoList()) {
						if(item2.getKeyPoint().compareTo(item.getRemainingTerm())==0){
							sprCalFalg = true;
							item.setSpR(item2.getYield());
							break;
						}
					}
					if(!sprCalFalg){
						TargetRateInputParameter trinput = new TargetRateInputParameter(input.getCurveInfoDto().getKeyPointDtoList(),item.getRemainingTerm());
						CurveRateSpRCalculator trCalculator = CalculatorFactory.createCurveRateSpRCalculator();
						TargetRateOutputParameter troutput = trCalculator.calculate(trinput);
						item.setSpR(troutput.getTargerYield());
					}
				}
			}
		    //二分法计算点差-即期
			DichotomySpreadSpInputParameter dssinput = new DichotomySpreadSpInputParameter();
			dssinput.setCashInfoDtoList(cashInfoDtoList);
			dssinput.setTarget(input.getTargetPrice());
			dssinput.setPrecision(input.getPrecision());
			if(null!=input.getTotalTimes()){
				dssinput.setTotalTimes(input.getTotalTimes());
			}
			DichotomySpreadSpCalculator dssCaluculator = CalculatorFactory.createDichotomySpreadSpCalculator();
			DichotomySpreadSpOutputParameter dssoutput = dssCaluculator.calculate(dssinput);
			out.setSpread(dssoutput.getSpread());
		}
		else if (CurveType.到期.equals(curveType)) {
			//到期日剩余期限 = 现金流折现法计算参数.GET(现金流信息).最后一个.剩余期限
			//到期日R值
			BigDecimal lastYield = null;
			BigDecimal lastremainingTerm = cashInfoDtoList.get(cashInfoDtoList.size() - 1).getRemainingTerm();
			if(!StringUtil.isEmptyList(input.getCurveInfoDto().getKeyPointDtoList())){
				for (KeyPointDto item : input.getCurveInfoDto().getKeyPointDtoList()) {
					if(item.getKeyPoint().compareTo(lastremainingTerm)==0){
						lastYield = item.getYield();
						break;
					}
				}
			}
			if(null==lastYield){
				TargetRateInputParameter trinput = new TargetRateInputParameter(input.getCurveInfoDto().getKeyPointDtoList(),lastremainingTerm);
				CurveRateSpRCalculator trCalculator = CalculatorFactory.createCurveRateSpRCalculator();
				TargetRateOutputParameter troutput = trCalculator.calculate(trinput);
				lastYield = troutput.getTargerYield();
			}
			//二分法计算点差-到期
			DichotomySpreadExpireInputParameter dssinput = new DichotomySpreadExpireInputParameter();
			dssinput.setCashInfoDtoList(cashInfoDtoList);
			dssinput.setTarget(input.getTargetPrice());
			dssinput.setPrecision(input.getPrecision());
			if(null!=input.getTotalTimes()){
				dssinput.setTotalTimes(input.getTotalTimes());
			}
			dssinput.setMatYield(lastYield);
			dssinput.setYearIntRate(input.getYearIntRate());
			DichotomySpreadExpireCalculator dssCaluculator = CalculatorFactory.createDichotomySpreadExpireCalculator();
			DichotomySpreadExpireOutputParameter dssoutput = dssCaluculator.calculate(dssinput);
			out.setSpread(dssoutput.getSpread());
		}
		return out;
	}
}
