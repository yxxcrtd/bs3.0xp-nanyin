package com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam;

import com.joyintech.tams.model.valuation.batch.calculation.InputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 贴现模型-二分法计算点差输入参数
 *
 * @author 吴洁
 * @version 1.0 2021年7月2日
 * @see DichotomySpreadSpInputParameter
 * @since 1.0
 */
public class DichotomySpreadSpInputParameter implements InputParameter {

	/**
	 * 现金流信息
	 */
	private List<CashInfoDto> cashInfoDtoList;
	
	/**
	 * 二分法误差值
	 */
	private BigDecimal precision;
	
	/**
	 * 二分法计算最大次数
	 */
	private int totalTimes;
	
	/**
	 * 目标金额
	 */
	private BigDecimal target;
	
	
	@Override
	public void check() {
		// 现金流信息
        checkNull(cashInfoDtoList, "现金流信息");
        
        // 目标金额
        checkNull(target, "目标金额");
        
	}
	
	/**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RemainingTermInputParameter [cashInfoDtoList=" + cashInfoDtoList + ", precision=" + precision
               + ", totalTimes=" + totalTimes + ",target="+target+"]";
    }


	public List<CashInfoDto> getCashInfoDtoList() {
		return cashInfoDtoList;
	}


	public void setCashInfoDtoList(List<CashInfoDto> cashInfoDtoList) {
		this.cashInfoDtoList = cashInfoDtoList;
	}


	public BigDecimal getPrecision() {
		return precision;
	}


	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}


	public int getTotalTimes() {
		return totalTimes;
	}


	public void setTotalTimes(int totalTimes) {
		this.totalTimes = totalTimes;
	}


	public BigDecimal getTarget() {
		return target;
	}


	public void setTarget(BigDecimal target) {
		this.target = target;
	}
	
	

}
