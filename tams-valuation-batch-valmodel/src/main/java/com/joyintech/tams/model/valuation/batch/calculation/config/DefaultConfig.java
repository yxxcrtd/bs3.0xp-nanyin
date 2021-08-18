package com.joyintech.tams.model.valuation.batch.calculation.config;

import com.joyintech.tams.model.valuation.batch.calculation.Configuration;
import com.joyintech.tams.model.valuation.batch.calculation.constant.GlobalConstant;

/**
 * @author zznn
 * @date 2021/7/1
 */
public class DefaultConfig implements Configuration {
    /**
     * {@inheritDoc}
     */
    @Override
    public int getForwadCashTermScale() {
        return 20;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAllPrecision() {
        return 20;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int defalutScale() {
        return 12;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dicPrecision() {
        return 1e-18;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int dicTotalTimes() {
        return 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dicStartRateLow() {
        return -1D;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dicStartRateHigh() {
        return 1D;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String disDebtScheme() {
        return GlobalConstant.DIS_DEBT_SCHEME_B;
    }

    @Override
    public double dicStartAmountLow() {
        return -0.5D;
    }

    @Override
    public double dicStartAmountHigh() {
        return 0.5D;
    }
}

