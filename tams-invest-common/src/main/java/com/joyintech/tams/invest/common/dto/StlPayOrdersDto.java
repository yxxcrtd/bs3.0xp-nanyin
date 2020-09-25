package com.joyintech.tams.invest.common.dto;

import com.joyintech.tams.common.base.entity.StlPayOrders;

/**
 * @program: tams-invest-parent
 * @description: 划款指令DTO
 * @author: 强成西
 * @create: 2018-10-09 09:59
 **/
public class StlPayOrdersDto extends StlPayOrders {

    /**
     * 账户类型
     */
    private String acctType;

    /**
     * 获取 acctType
     *
     * @return acctType
     */
    public String getAcctType() {
        return acctType;
    }

    /**
     * 设置 acctType
     *
     * @param acctType acctType
     */
    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
}
