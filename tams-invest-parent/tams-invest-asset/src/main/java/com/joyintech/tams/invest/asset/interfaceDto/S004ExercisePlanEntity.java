package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;
import java.util.Date;

public class S004ExercisePlanEntity {
    private  String EXERCISE_TYPE;//    行权方式
    private String VDATE;//  申请开始日
    private  String MDATE;//    申请结束日
    private String EXERCISE_DATE;//    计划行权日
    private String ACT_EXERCISE_DATE;//    实际行权日
    private BigDecimal EXERCISE_PRICE;//    行权价
    private String EXERCISE_REMARK;//   行权条款

    public String getEXERCISE_TYPE() {
        return EXERCISE_TYPE;
    }

    public void setEXERCISE_TYPE(String EXERCISE_TYPE) {
        this.EXERCISE_TYPE = EXERCISE_TYPE;
    }

    public String getVDATE() {
        return VDATE;
    }

    public void setVDATE(String VDATE) {
        this.VDATE = VDATE;
    }

    public String getMDATE() {
        return MDATE;
    }

    public void setMDATE(String MDATE) {
        this.MDATE = MDATE;
    }

    public String getEXERCISE_DATE() {
        return EXERCISE_DATE;
    }

    public void setEXERCISE_DATE(String EXERCISE_DATE) {
        this.EXERCISE_DATE = EXERCISE_DATE;
    }

    public String getACT_EXERCISE_DATE() {
        return ACT_EXERCISE_DATE;
    }

    public void setACT_EXERCISE_DATE(String ACT_EXERCISE_DATE) {
        this.ACT_EXERCISE_DATE = ACT_EXERCISE_DATE;
    }

    public BigDecimal getEXERCISE_PRICE() {
        return EXERCISE_PRICE;
    }

    public void setEXERCISE_PRICE(BigDecimal EXERCISE_PRICE) {
        this.EXERCISE_PRICE = EXERCISE_PRICE;
    }

    public String getEXERCISE_REMARK() {
        return EXERCISE_REMARK;
    }

    public void setEXERCISE_REMARK(String EXERCISE_REMARK) {
        this.EXERCISE_REMARK = EXERCISE_REMARK;
    }
}
