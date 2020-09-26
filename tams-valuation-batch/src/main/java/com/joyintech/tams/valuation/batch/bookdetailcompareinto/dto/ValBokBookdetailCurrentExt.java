package com.joyintech.tams.valuation.batch.bookdetailcompareinto.dto;

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
/**
 * 记账明细当前表扩展表
 */
public class ValBokBookdetailCurrentExt extends BokBookdetailCurrent {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7376368703959969623L;
    /**
     * 期待余额方向
     */
    private String balFlag;

    
    /**
     * 获取 期待余额方向
     * @return balFlag.
     */
    public String getBalFlag() {
        return balFlag;
    }

    
    /**
     * 设置 期待余额方向
     * @param balFlag 期待余额方向
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }


}