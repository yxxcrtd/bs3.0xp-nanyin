package com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam;

import com.joyintech.tams.model.valuation.batch.calculation.InputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.constant.Basis;
import com.joyintech.tams.model.valuation.batch.calculation.constant.IntFrequency;
import com.joyintech.tams.model.valuation.batch.calculation.constant.SecIntSchCalMethod;
import com.joyintech.tams.model.valuation.batch.calculation.constant.Variety;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CurveInfoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 现金流折算法输入参数
 * @author zznn
 * @date 2021/7/5
 */
public class DisCountInputParameter implements InputParameter {

	/**
	 * 基准日期
	 */
	private LocalDate calDate;

	/**
	 * 现金流信息
	 */
	private List<CashInfoDto> cashInfoDtoList;

	/**
	 * 年付息次数
	 */
	private IntFrequency yearIntRate;

	/**
	 * 首次付息日
	 */
	private LocalDate firstPayDate;

	/**
	 * 计息基础
	 */
	private Basis basis;

	/**
	 * 息票品种 0:附息 1:贴现 2:零息 3:利息本清
	 */
	protected Variety variety;

	/**
	 * 推算方式 0:起息日向后 1:到期日向前 2:首次付息日 息票品种为附息时必输，否则为空
	 */
	protected SecIntSchCalMethod calMethod;

	/**
	 * 贴现目标价
	 */
	private BigDecimal targetPrice;

	/**
	 * 曲线信息
	 */
	private CurveInfoDto curveInfoDto;


	/**
	 * 点差
	 */
	private BigDecimal spread;

	/**
	 * 收益率
	 */
	private BigDecimal yield;

	/**
	 * 误差值
	 */
	private BigDecimal precision;

	/**
	 * 计算最大次数
	 */
	private Integer totalTimes;

	private BigDecimal fullprice;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ValModelDisCountInputParameter [calDate=" + calDate + ", cashInfoDtoList=" + cashInfoDtoList
				+ ", yearIntRate=" + yearIntRate + ", basis=" + basis + ", variety="
				+ variety + ", calMethod=" + calMethod + ", targetPrice=" + targetPrice + ", curveInfoDto="
				+ curveInfoDto + "]";
	}

	@Override
	public void check() {
		checkNull(cashInfoDtoList, "现金流信息");
		checkNull(yearIntRate, "年付息次数");
	}

	public LocalDate getCalDate() {
		return calDate;
	}

	public void setCalDate(LocalDate calDate) {
		this.calDate = calDate;
	}

	public List<CashInfoDto> getCashInfoDtoList() {
		return cashInfoDtoList;
	}

	public void setCashInfoDtoList(List<CashInfoDto> cashInfoDtoList) {
		this.cashInfoDtoList = cashInfoDtoList;
	}

	public IntFrequency getYearIntRate() {
		return yearIntRate;
	}

	public void setYearIntRate(IntFrequency yearIntRate) {
		this.yearIntRate = yearIntRate;
	}

	public LocalDate getFirstPayDate() {
		return firstPayDate;
	}

	public void setFirstPayDate(LocalDate firstPayDate) {
		this.firstPayDate = firstPayDate;
	}

	public Basis getBasis() {
		return basis;
	}

	public void setBasis(Basis basis) {
		this.basis = basis;
	}

	public Variety getVariety() {
		return variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}


	public SecIntSchCalMethod getCalMethod() {
		return calMethod;
	}

	public void setCalMethod(SecIntSchCalMethod calMethod) {
		this.calMethod = calMethod;
	}

	public BigDecimal getTargetPrice() {
		return targetPrice;
	}

	public void setTargetPrice(BigDecimal targetPrice) {
		this.targetPrice = targetPrice;
	}

	public CurveInfoDto getCurveInfoDto() {
		return curveInfoDto;
	}

	public void setCurveInfoDto(CurveInfoDto curveInfoDto) {
		this.curveInfoDto = curveInfoDto;
	}

	public BigDecimal getSpread() {
		return spread;
	}

	public void setSpread(BigDecimal spread) {
		this.spread = spread;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public BigDecimal getPrecision() {
		return precision;
	}

	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}

	public Integer getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}

	public BigDecimal getFullprice() {
		return fullprice;
	}

	public void setFullprice(BigDecimal fullprice) {
		this.fullprice = fullprice;
	}
}
