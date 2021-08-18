package com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam;

import com.joyintech.tams.model.valuation.batch.calculation.InputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.constant.IntFrequency;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 二分法到期点差输入参数
 * @author zznn
 * @date 2021/7/5
 */
public class DichotomySpreadExpireInputParameter implements InputParameter {

    /**
     * 现金流集合
     */
    private List<CashInfoDto> cashInfoDtoList;

    /**
     * 误差值
     */
    private BigDecimal precision;

    /**
     * 计算次数
     */
    private int totalTimes;

    /**
     * 目标金额
     */
    private BigDecimal target;

    /**
     * 到期收益率
     */
    private BigDecimal matYield;

    /**
     * 年付息次数
     */
    private IntFrequency yearIntRate;

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

    public BigDecimal getMatYield() {
        return matYield;
    }

    public void setMatYield(BigDecimal matYield) {
        this.matYield = matYield;
    }

    public IntFrequency getYearIntRate() {
        return yearIntRate;
    }

    public void setYearIntRate(IntFrequency yearIntRate) {
        this.yearIntRate = yearIntRate;
    }

    @Override
    public void check() {
// 现金流信息
        checkNull(cashInfoDtoList, "现金流信息");

        // 目标金额
        checkNull(target, "目标金额");

        // 到期收益率
        checkNull(matYield, "到期收益率");

        checkNull(yearIntRate, "年付息次数");
    }
}

