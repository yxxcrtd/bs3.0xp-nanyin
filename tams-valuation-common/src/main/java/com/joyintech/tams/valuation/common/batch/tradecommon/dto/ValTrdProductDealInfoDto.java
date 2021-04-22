package com.joyintech.tams.valuation.common.batch.tradecommon.dto;


import com.joyintech.tams.common.base.entity.TrdProductDeal;

import java.util.Date;

/**
 * @ClassName ValTrdProductDealInfoDto
 * @Description: 交易信息dto
 * @Author yanya
 * @Date 2021/3/9 0009 
 * @Version V1.0
 **/
public class ValTrdProductDealInfoDto extends TrdProductDeal {
    private static final long serialVersionUID = 807612016655579382L;

    /**
     * 行权状态
     */
    private String exerciseStatus;

    /**
     * 放弃行权日
     */
    private Date cancelExerciseDate;

    public String getExerciseStatus() {
        return exerciseStatus;
    }

    public void setExerciseStatus(String exerciseStatus) {
        this.exerciseStatus = exerciseStatus;
    }

    public Date getCancelExerciseDate() {
        return cancelExerciseDate;
    }

    public void setCancelExerciseDate(Date cancelExerciseDate) {
        this.cancelExerciseDate = cancelExerciseDate;
    }
}
