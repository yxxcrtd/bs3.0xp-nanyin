package com.joyintech.tams.invest.common.autocoupon.dto;

import com.joyintech.tams.common.base.entity.FinStdsecConverseRadio;

/**
 * @auther zhangnian
 * @date 2021/4/19
 */
public class FinStdsecConverseRadioDto extends FinStdsecConverseRadio {
    /**
     * 加券标识
     */
    private String couponFlag;

    public String getCouponFlag() {
        return couponFlag;
    }

    public void setCouponFlag(String couponFlag) {
        this.couponFlag = couponFlag;
    }
}

