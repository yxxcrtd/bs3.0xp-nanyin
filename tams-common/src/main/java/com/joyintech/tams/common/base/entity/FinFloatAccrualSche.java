package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class FinFloatAccrualSche extends FinFloatAccrualScheKey {
    /**
     * 区间开始日
     */
    private Date accrStrDate;

    /**
     * 区间结束日
     */
    private Date accrEndDate;

    /**
     * 调整前结束日
     */
    private Date valDate;

    /**
     * 取得 区间开始日
     * @return 区间开始日
     */
    public Date getAccrStrDate() {
        return accrStrDate;
    }

    /**
     * 设置 区间开始日
     * @param accrStrDate 区间开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAccrStrDate(Date accrStrDate) {
        this.accrStrDate = accrStrDate;
    }

    /**
     * 取得 区间结束日
     * @return 区间结束日
     */
    public Date getAccrEndDate() {
        return accrEndDate;
    }

    /**
     * 设置 区间结束日
     * @param accrEndDate 区间结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setAccrEndDate(Date accrEndDate) {
        this.accrEndDate = accrEndDate;
    }

    /**
     * 取得 调整前结束日
     * @return 调整前结束日
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 调整前结束日
     * @param valDate 调整前结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }
}