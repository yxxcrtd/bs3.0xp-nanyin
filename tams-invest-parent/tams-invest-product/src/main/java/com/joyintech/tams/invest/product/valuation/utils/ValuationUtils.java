package com.joyintech.tams.invest.product.valuation.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.joyintech.framework.util.DateUtil;

public class ValuationUtils {

	/**
	 * 计息计算器
	 * 
	 * @param prinamt
	 * @param yield
	 * @param basis
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static BigDecimal calcIntamt(BigDecimal prinamt, BigDecimal yield, String basis, Date startDate,
			Date endDate) {
		return prinamt.multiply(yield).divide(convertBasis(basis), 4, RoundingMode.HALF_UP)
				.multiply(BigDecimal.valueOf(DateUtil.getBetweenDayNumber(startDate, endDate) + 1));
	}

	/**
	 * 计息基础转换
	 * 
	 * @param basis
	 * @return
	 */
	public static BigDecimal convertBasis(String basis) {
		switch (basis) {
		case "A/360":
			return BigDecimal.valueOf(360L);
		case "A/365":
			return BigDecimal.valueOf(365L);
		default:
			return BigDecimal.valueOf(365L);
		}
	}

}
