package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class PaRskCustomerRatingKey extends BaseEntity {
    /**
     * 客户代码
     */
    private String customerId;

    /**
     * 评级类型
     */
    private String ratingtype;

    /**
     * 评级生效日
     */
    private Date startDate;

    /**
     * 评级到期日
     */
    private Date endDate;

    /**
     * 取得 客户代码
     * @return 客户代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 客户代码
     * @param customerId 客户代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 评级类型
     * @return 评级类型
     */
    public String getRatingtype() {
        return ratingtype;
    }

    /**
     * 设置 评级类型
     * @param ratingtype 评级类型
     */
    public void setRatingtype(String ratingtype) {
        this.ratingtype = ratingtype == null ? null : ratingtype.trim();
    }

    /**
     * 取得 评级生效日
     * @return 评级生效日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 评级生效日
     * @param startDate 评级生效日
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 评级到期日
     * @return 评级到期日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 评级到期日
     * @param endDate 评级到期日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}