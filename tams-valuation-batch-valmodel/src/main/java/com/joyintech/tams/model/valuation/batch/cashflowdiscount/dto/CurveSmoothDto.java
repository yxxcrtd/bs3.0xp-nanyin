package com.joyintech.tams.model.valuation.batch.cashflowdiscount.dto;

import com.joyintech.tams.valuation.common.dto.modelDto.ModYieldCurveInfoDto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 平滑后曲线行情
 * @author zznn
 * @date 2021/7/7
 */
public class CurveSmoothDto extends ModYieldCurveInfoDto {

    /**
     * 平滑次数
     */
    private int smoothTimes;

    public int getSmoothTimes() {
        return smoothTimes;
    }

    public void setSmoothTimes(int smoothTimes) {
        this.smoothTimes = smoothTimes;
    }

    public CurveSmoothDto(){}

    public CurveSmoothDto(Date curveDate, String curveCode, BigDecimal keyPoint, BigDecimal yield, String curveType) {
        super(curveDate, curveCode, keyPoint, yield, curveType);
    }
}

