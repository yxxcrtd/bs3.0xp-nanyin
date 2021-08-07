package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.CalculatorFactory;
import com.joyintech.tams.model.valuation.batch.calculation.constant.CurveType;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.DisCountInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.RemainingTermInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.TargetRateInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.DisCountOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.RemainingTermOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.TargetRateOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.util.ArithmeticUtil;
import com.joyintech.tams.model.valuation.batch.calculation.util.EngineContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 贴现模型-现金流折现法全价计算
 * @author zznn
 * @date 2021/7/2
 */
public class DisCountFullPriceCalculator implements Calculator<DisCountInputParameter> {

	@Override
	public DisCountOutputParameter calculate(DisCountInputParameter input) {
		input.check();
		
		DisCountOutputParameter out = new DisCountOutputParameter();
		
		//现金流信息
		List<CashInfoDto> cashInfoDtoList = new ArrayList<>();
		//剩余期限计算
		RemainingTermInputParameter rtInput = new RemainingTermInputParameter(input.getCalDate(),input.getCashInfoDtoList(),
				input.getFirstPayDate(),input.getVariety(),input.getCalMethod());
		RemainingTermCalculator rtCalculator = CalculatorFactory.createRemainingTermCalculator();
		RemainingTermOutputParameter rtOutput = rtCalculator.calculate(rtInput);
		cashInfoDtoList = rtOutput.getCashInfoDtoList();
        CurveType curveType = input.getCurveInfoDto().getCurveType();
		//估值全价
		BigDecimal fullprice = BigDecimal.ZERO;
		//估值日R
		BigDecimal spR = null;
		for(CashInfoDto item:cashInfoDtoList){
			TargetRateInputParameter trinput = new TargetRateInputParameter(input.getCurveInfoDto().getKeyPointDtoList(),item.getRemainingTerm());
			CurveRateSpRCalculator trCalculator = CalculatorFactory.createCurveRateSpRCalculator();
			TargetRateOutputParameter troutput = trCalculator.calculate(trinput);
			item.setSpR(troutput.getTargerYield());
			spR = troutput.getTargerYield();
		}
		if (CurveType.即期.equals(curveType)) {
			for(CashInfoDto item :cashInfoDtoList){
				//估值全价 = 估值全价 +  中间计算过程值
				fullprice = ArithmeticUtil.doAdd(EngineContext.getInstance().getConfig().getAllPrecision(),
		                RoundingMode.HALF_UP,fullprice,calcMidTemp(item.getCashAmt(),item.getRemainingTerm(),item.getSpR(),input.getSpread()));
			}
		}
		else if (CurveType.到期.equals(curveType)) {
			//年付息次数
			BigDecimal yearIntRate = new BigDecimal(input.getYearIntRate().getValue());
			for(CashInfoDto item :cashInfoDtoList){
				//估值全价 = 估值全价 +  中间计算过程值
				fullprice = ArithmeticUtil.doAdd(EngineContext.getInstance().getConfig().getAllPrecision(),
						RoundingMode.HALF_UP,fullprice,calcMidTemp2(item.getCashAmt(),item.getRemainingTerm(),spR,input.getSpread(),yearIntRate));
			}
		}
		out.setCashInfoDtoList(cashInfoDtoList);
		out.setFullprice(fullprice);
		return out;
	}

	/**
	 * 中间计算过程(即期)
	 * @param cashAmt
	 * @param remainingTerm
	 * @param yield
	 * @param spread
	 * @return
	 */
	private BigDecimal calcMidTemp(BigDecimal cashAmt,BigDecimal remainingTerm,BigDecimal yield,BigDecimal spread){
		System.out.println("现金流金额="+cashAmt+",剩余期限="+remainingTerm+",即期R值="+yield+",点差="+spread);
		// ITEM.现金流金额 / (1 + ITEM.即期R值 + 参数.现金流折现法DTO.点差） 求 ITEM.剩余期限 次方
		BigDecimal powbase = ArithmeticUtil.doAdd(BigDecimal.ONE, ArithmeticUtil.doAdd(yield,spread));
		BigDecimal pownum = ArithmeticUtil.multiply(remainingTerm,BigDecimal.ONE);
		return ArithmeticUtil.divide(cashAmt, BigDecimal.valueOf(Math.pow(powbase.doubleValue(), pownum.doubleValue())));
	}

	/**
	 * 中间计算过程(到期)
	 * @param cashAmt
	 * @param yield
	 * @param spread
	 * @param yearIntRate
	 * @return
	 */
	private BigDecimal calcMidTemp2(BigDecimal cashAmt,BigDecimal remainingTerm,BigDecimal yield,BigDecimal spread,BigDecimal yearIntRate){
		System.out.println("现金流金额="+cashAmt+",剩余期限="+remainingTerm+",曲线收益率="+yield+",点差="+spread+",年付息次数="+yearIntRate);
		//{ ITEM.现金流金额 / [1 + (参数.现金流折现法计算参数.收益率 + 参数.现金流折现法计算参数,点差)/参数.现金流折现法计算参数.年付息次数] 求 （ITEM.剩余期限 * 参数.现金流折现法计算参数.年付息次数） 次方}
		BigDecimal powbase = ArithmeticUtil.doAdd(BigDecimal.ONE, ArithmeticUtil.divide(ArithmeticUtil.doAdd(yield,spread), yearIntRate));
		BigDecimal pownum = ArithmeticUtil.multiply(remainingTerm,yearIntRate);
		return ArithmeticUtil.divide(cashAmt, BigDecimal.valueOf(Math.pow(powbase.doubleValue(), pownum.doubleValue())));
	}
}
