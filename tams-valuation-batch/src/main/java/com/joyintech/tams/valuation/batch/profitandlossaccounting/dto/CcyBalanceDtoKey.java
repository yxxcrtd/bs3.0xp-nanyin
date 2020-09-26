/*
 * 文件名：CcyBalanceDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2019年1月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.profitandlossaccounting.dto;

/**
 * 币种余额Dto
 * 
 * @author weipeng
 * @version 1.0 2019年1月14日
 * @see CcyBalanceDtoKey
 * @since 1.0
 */
public class CcyBalanceDtoKey {

    /**
     * 币种
     */
    private String ccy;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 获取 币种
     * 
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * 
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 账务明细代码
     * 
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * 
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bokDetailId == null) ? 0 : bokDetailId.hashCode());
        result = prime * result + ((ccy == null) ? 0 : ccy.hashCode());
        return result;
    }

    /**
     * 重写equals
     */
    public boolean equals(Object obj) {
        boolean flag = true;
        // 用 break 代替return过多，用do while做处理,循环一次
        do {
            if (this == obj) {
                flag = true;
                break;
            }

            if (obj == null) {
                flag = false;
                break;
            }

            if (getClass() != obj.getClass()) {
                flag = false;
                break;
            }

            CcyBalanceDtoKey other = (CcyBalanceDtoKey)obj;
            if (bokDetailId == null) {
                if (other.bokDetailId != null) {
                    flag = false;
                    break;
                }
            }
            else if (!bokDetailId.equals(other.bokDetailId)) {
                flag = false;
                break;
            }
            if (ccy == null) {
                if (other.ccy != null) {
                    flag = false;
                    break;
                }

            }
            else if (!ccy.equals(other.ccy)) {
                flag = false;
                break;
            }
        }
        while (false);
        return flag;
    }

}
