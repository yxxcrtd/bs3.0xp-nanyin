package com.joyintech.tams.valuation.common.batch.tradecommon.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @ClassName ValExecApplyInfoDto
 * @Description: 行权申请信息dto
 * @Author yanya
 * @Date 2021/3/9 0009 
 * @Version V1.0
 **/
public class ValExecApplyInfoDto extends BaseEntity {

    private static final long serialVersionUID = -3483643474070596032L;

    /**
     * 行权价格
     */
    private BigDecimal execPrice;

    /**
     * 行权数量
     */
    private BigDecimal execNum;

    public BigDecimal getExecPrice() {
        return execPrice;
    }

    public void setExecPrice(BigDecimal execPrice) {
        this.execPrice = execPrice;
    }

    public BigDecimal getExecNum() {
        return execNum;
    }

    public void setExecNum(BigDecimal execNum) {
        this.execNum = execNum;
    }
}
