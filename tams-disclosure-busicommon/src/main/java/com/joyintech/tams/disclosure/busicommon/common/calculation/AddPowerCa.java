package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.disclosure.busicommon.common.dto.PowerDto;




/**
 * 加权计算共通
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-03
 */
public class AddPowerCa {

	public static BigDecimal addPoweCommen(List<PowerDto> list) {

		// 定义总权值
		BigDecimal sumPowerValue = BigDecimal.ZERO;
		// 定义总权重
		BigDecimal sumPowerWeight = BigDecimal.ZERO;

		for (PowerDto li : list) {
			sumPowerValue = sumPowerValue.add(li.getPowerValue().multiply(li.getPowerWeight()));
			sumPowerWeight = sumPowerWeight.add(li.getPowerWeight());
		}

		// 加权结果 = 总权值/总权重 * 保留20位精度
		sumPowerValue = sumPowerValue.divide(sumPowerWeight, 20, BigDecimal.ROUND_HALF_UP);
		// BigDecimal re=result.setScale(20,BigDecimal.ROUND_DOWN);

		return sumPowerValue;

	}

}
