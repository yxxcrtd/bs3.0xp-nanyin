package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @ClassName ChbPeriodicBonus
 * @Description: 定期报告收益分配
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicBonus extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 序号
     */
    private String seq;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 除权日期
     */
    private String regDate;

    /**
     * 每万份份额分红
     */
    private String tradeBonus;

    /**
     * 每万份金额分红
     */
    private String intBonus;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getTradeBonus() {
        return tradeBonus;
    }

    public void setTradeBonus(String tradeBonus) {
        this.tradeBonus = tradeBonus;
    }

    public String getIntBonus() {
        return intBonus;
    }

    public void setIntBonus(String intBonus) {
        this.intBonus = intBonus;
    }
}
