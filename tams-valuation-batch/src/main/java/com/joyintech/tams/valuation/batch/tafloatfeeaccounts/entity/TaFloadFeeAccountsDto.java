package com.joyintech.tams.valuation.batch.tafloatfeeaccounts.entity;
/**
 * @author yanya
 * @create 2020-01-01 8:57
 */

import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;

/**
 * @ClassName TaFloadFeeAccountsDto
 * @Description: 浮动管理费区间计提
 * @Author Administrator
 * @Date 2020/1/10 0010 
 * @Version V1.0
 **/
public class TaFloadFeeAccountsDto extends BokBookdetailCurrent {

    private static final long serialVersionUID = 8937121263723526873L;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 业务明细类型
     */
    private String busiDetailType;

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public String getBusiDetailType() {
        return busiDetailType;
    }

    public void setBusiDetailType(String busiDetailType) {
        this.busiDetailType = busiDetailType;
    }
}
