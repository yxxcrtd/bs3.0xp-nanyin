package com.joyintech.tams.model.valuation.batch.calculation.shell.calculator;

import com.joyintech.tams.model.valuation.batch.calculation.Calculator;
import com.joyintech.tams.model.valuation.batch.calculation.entity.KeyPointDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.TargetRateInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.TargetRateOutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.util.ArithmeticUtil;
import com.joyintech.tams.model.valuation.batch.calculation.util.EngineContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * 插值计算
 * @author zznn
 * @date 2021/7/2
 */
public class TargetRateCalculator implements Calculator<TargetRateInputParameter> {

	private Logger logger = LoggerFactory.getLogger(TargetRateCalculator.class);

	private static final int TARGETRATE_SCALE = EngineContext.getInstance().getConfig().getAllPrecision();

	@Override
	public TargetRateOutputParameter calculate(TargetRateInputParameter input) {
		input.check();

		//左侧期限INDEX = -1
		int leftTermIndex =-1;
		//右侧期限INDEX = -1
		int rightTermIndex = -1;
		//目标收益率 = 0
		BigDecimal targetYield = BigDecimal.ZERO;

		List<KeyPointDto> keyPointDtoList = input.getKeyPointDtoList();

		if(keyPointDtoList.size() == 1){
			logger.warn("【插值算法】-插值计算提供的曲线行情仅存在一个关键年限点，直接返回该数据.");
			targetYield = keyPointDtoList.get(0).getYield();
		}else{

			for(int index = 0;index < keyPointDtoList.size();index++){
				KeyPointDto item = keyPointDtoList.get(index);
				if(item.getKeyPoint().compareTo(input.getTargetTerm())<0
						&& index > leftTermIndex){
					leftTermIndex = index;
				}
				else if(item.getKeyPoint().compareTo(input.getTargetTerm())>0
						&& (index < rightTermIndex || rightTermIndex == -1)){
					rightTermIndex = index;
				}
			}

			if(leftTermIndex==-1 /*&& rightTermIndex == -1*/){
				leftTermIndex = 0;
				rightTermIndex = 1;
			}
			else if(rightTermIndex == -1){
				rightTermIndex = leftTermIndex;
				leftTermIndex = (rightTermIndex -1)<0?0:rightTermIndex -1;
			}
			KeyPointDto leftKeyPoint = keyPointDtoList.get(leftTermIndex);
			KeyPointDto rigthKeyPoint = keyPointDtoList.get(rightTermIndex);

			//y = (x-x0)*(y1-y0)/(x1-x0)+y0
			//目标收益率 = (目标期限 - 变量.左侧期限）*（变量.右侧收益率 - 变量.左侧收益率）/（变量.右侧期限 - 变量.左侧期限） + 变量.左侧收益率
			targetYield = ArithmeticUtil.doAdd(
					ArithmeticUtil.divide(
							ArithmeticUtil.multiply(
									ArithmeticUtil.doSub(input.getTargetTerm(),leftKeyPoint.getKeyPoint()),
									ArithmeticUtil.doSub(rigthKeyPoint.getYield(),leftKeyPoint.getYield())),
							ArithmeticUtil.doSub(rigthKeyPoint.getKeyPoint(),leftKeyPoint.getKeyPoint()),
							TARGETRATE_SCALE),
					leftKeyPoint.getYield()
			);
		}

		TargetRateOutputParameter out = new TargetRateOutputParameter();
		out.setTargerYield(targetYield);
		return out;
	}

}
