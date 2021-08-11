package com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam;

import com.joyintech.tams.model.valuation.batch.calculation.OutputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.constant.Basis;
import com.joyintech.tams.model.valuation.batch.calculation.constant.SecIntSchCalMethod;
import com.joyintech.tams.model.valuation.batch.calculation.constant.Variety;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CashInfoDto;
import com.joyintech.tams.model.valuation.batch.calculation.entity.CurveInfoDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 现金流计算参数
 * @author zznn
 * @date 2021/7/1
 */
public class CashFlowCalOutParameter implements OutputParameter {

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     *基准日
     */
    private LocalDate baseDate;

    /**
     *现金流
     */
    private List<CashInfoDto> cashInfoDtos;

    /**
     * 年付息次数
     */
    private int interestTimes;

    /**
     * 计息基础
     */
    private Basis basis;

    /**
     * 付息方式
     */
    private Variety variety;

    /**
     * 推算方式
     */
    private SecIntSchCalMethod secIntSchCalMethod;

    /**
     * 贴现目标价
     */
    private BigDecimal targetPrice;

    /**
     * 曲线信息
     */
    private CurveInfoDto curveInfoDto;

    /**
     * 到期收益率
     */
    private BigDecimal maturityYield;

    /**
     * 点差
     */
    private BigDecimal spread;

    /**
     * 全价
     */
    private BigDecimal fullPrice;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(LocalDate baseDate) {
        this.baseDate = baseDate;
    }

    public List<CashInfoDto> getCashInfoDtos() {
        return cashInfoDtos;
    }

    public void setCashInfoDtos(List<CashInfoDto> cashInfoDtos) {
        this.cashInfoDtos = cashInfoDtos;
    }

    public int getInterestTimes() {
        return interestTimes;
    }

    public void setInterestTimes(int interestTimes) {
        this.interestTimes = interestTimes;
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

    public SecIntSchCalMethod getSecIntSchCalMethod() {
        return secIntSchCalMethod;
    }

    public void setSecIntSchCalMethod(SecIntSchCalMethod secIntSchCalMethod) {
        this.secIntSchCalMethod = secIntSchCalMethod;
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

    public BigDecimal getMaturityYield() {
        return maturityYield;
    }

    public void setMaturityYield(BigDecimal maturityYield) {
        this.maturityYield = maturityYield;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }
}

