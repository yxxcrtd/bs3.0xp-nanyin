package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.DichotomySpreadSpInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.DichotomySpreadSpOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.util.ArithmeticUtil;
import com.joyintech.tams.model.valuation.batch.calculation.util.BasicUtil;
import com.joyintech.tams.model.valuation.batch.calculation.util.EngineContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 贴现模型-二分法计算点差-即期
 * @author 吴洁
 * @version 1.0 2021年7月2日
 * @see DichotomySpreadSpCalculator
 * @since 1.0
 */
public class DichotomySpreadSpCalculator implements Calculator<DichotomySpreadSpInputParameter> {

	@Override
	public DichotomySpreadSpOutputParameter calculate(DichotomySpreadSpInputParameter input) {
		input.check();
		// 误差值
        BigDecimal precision = input.getPrecision();
		if(precision==null){
			precision = new BigDecimal(
		            String.valueOf(EngineContext.getInstance().getConfig().dicPrecision()));
		}
		// 最大循环次数
		Integer totalTimes = input.getTotalTimes();
        if(totalTimes == null){
        	totalTimes = EngineContext.getInstance().getConfig().dicTotalTimes();
        }
        //现金流组合
        List<BigDecimal> cashAmtList = new ArrayList<>();
        //剩余期限组合
        List<BigDecimal> remainingTermList = new ArrayList<>();
        //即期R值组合
        List<BigDecimal> spRList = new ArrayList<>();
        
        /*for(CashInfoDto item:input.getCashInfoDtoList()){
        	cashAmtList.add(item.getCashAmt());
        	remainingTermList.add(item.getRemainingTerm());
        	spRList.add(item.getSpR());
        }*/
        //BigDecimal spread = dichotomy(cashAmtList,remainingTermList,spRList,input.getTarget(),precision,totalTimes);
		BigDecimal spread = BasicUtil.dichotomy(input.getTarget(),input.getPrecision(),input.getTotalTimes(), tempspread -> {
			return BasicUtil.calPriceByMatYield2(input.getCashInfoDtoList(), tempspread);});
		DichotomySpreadSpOutputParameter out = new DichotomySpreadSpOutputParameter();
		out.setSpread(spread);
		return out;
	}
	/**
	 *  二分法计算点差
	 * @param cashAmtList 现金流组合
	 * @param remainingTermList 剩余期限组合
	 * @param spRList 即期R值组合
	 * @param target 目标金额(贴现目标价格)
	 * @param precision 误差值
	 * @param totalTimes 最大计算次数
	 * @return
	 */
	private BigDecimal dichotomy(List<BigDecimal> cashAmtList,List<BigDecimal> remainingTermList,List<BigDecimal> spRList,
			BigDecimal target,BigDecimal precision,Integer totalTimes){
		
		// 二分算法起始金额
		BigDecimal tempSpreadLow = new BigDecimal(
	            String.valueOf(EngineContext.getInstance().getConfig().dicStartAmountLow()));
        
        // 二分算法结束金额
        BigDecimal tempSpreadHigh = new BigDecimal(
	            String.valueOf(EngineContext.getInstance().getConfig().dicStartAmountHigh()));
		
        //二分算法中间量
        BigDecimal tempSpread = ArithmeticUtil.divide(
                ArithmeticUtil.doAdd(tempSpreadLow, tempSpreadHigh), 2);
        
        
        BigDecimal cpLast = BigDecimal.ZERO;
        int counter = 0;
        
        while (precision.compareTo(ArithmeticUtil.subtract(cpLast, target).abs()) < 0
                && counter < totalTimes) {
        	
        	if (cpLast.compareTo(target) > 0) {
        		tempSpreadLow = tempSpread;
            }
            else {
            	tempSpreadHigh = tempSpread;
            }
        	
        	tempSpread = ArithmeticUtil.divide(ArithmeticUtil.doAdd(EngineContext.getInstance().getConfig().getAllPrecision(),
                    RoundingMode.HALF_UP, tempSpreadLow, tempSpreadHigh), 2, NumberEnum.ALL_PRECISION.getCode());
        	cpLast = calcDiscountPV(cashAmtList,remainingTermList,spRList,tempSpread);
        	counter++ ;
        }
		return tempSpread;
	}
	
	/**
	 * 折现因子计算
	 * @param cashAmtList
	 * @param remainingTermList
	 * @param spRList
	 * @param spread
	 * @return
	 */
	private BigDecimal calcDiscountPV(List<BigDecimal> cashAmtList,List<BigDecimal> remainingTermList,List<BigDecimal> spRList,BigDecimal spread){
		BigDecimal pv = BigDecimal.ZERO;
		/**现金流金额/pow(1+曲线收益率+点差,剩余期限)**/
		for(int i=0;i<cashAmtList.size();i++){
			BigDecimal powa = ArithmeticUtil.doAdd(BigDecimal.ONE,spRList.get(i),spread);
			BigDecimal powb = remainingTermList.get(i).negate();
			pv = ArithmeticUtil.doAdd(EngineContext.getInstance().getConfig().getAllPrecision(),RoundingMode.HALF_UP,
                    pv,
                    ArithmeticUtil.multiply(cashAmtList.get(i),
                    		BigDecimal.valueOf(Math.pow(powa.doubleValue(), powb.doubleValue()))
                    		)
                    );
		}
		return pv;
	}
	
	
//	/**
//	 * 中间过程计算
//	 * @param cashAmt 现金流金额
//	 * @param remainingTerm 剩余期限
//	 * @param spR  即期R值
//	 * @param tempSpread 中间值
//	 * @return
//	 */
//	private BigDecimal calcSpreadTemp(BigDecimal cashAmt,BigDecimal remainingTerm,BigDecimal spR,BigDecimal tempSpread){
//		//现金流金额/([(1 + R值数组[i] + 中间量）]求 剩余期限数组[i] 次方)
//		BigDecimal powbase = ArithmeticUtil.doAdd(BigDecimal.ONE,spR,tempSpread);
//		return ArithmeticUtil.divide(cashAmt, BigDecimal.valueOf(Math.pow(powbase.doubleValue(), remainingTerm.doubleValue())));
//	}
}
