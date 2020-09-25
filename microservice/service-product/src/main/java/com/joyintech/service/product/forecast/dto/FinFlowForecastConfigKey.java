package com.joyintech.service.product.forecast.dto;

import com.joyintech.framework.base.BaseEntity;

public class FinFlowForecastConfigKey extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 查询日期
     */
    private String queryDate;

    /**
     * 部门类型
     */
    private String depType;

    /**
     * 流动性种类
     */
    private String flowType;

    /**
     * 指标种类
     */
    private String targetType;

    /**
     * 取得 查询日期
     * 
     * @return 查询日期
     */
    public String getQueryDate() {
        return queryDate;
    }

    /**
     * 设置 查询日期
     * 
     * @param queryDate 查询日期
     */
    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate == null ? null : queryDate.trim();
    }

    /**
     * 取得 部门类型
     * 
     * @return 部门类型
     */
    public String getDepType() {
        return depType;
    }

    /**
     * 设置 部门类型
     * 
     * @param depType 部门类型
     */
    public void setDepType(String depType) {
        this.depType = depType == null ? null : depType.trim();
    }

    /**
     * 取得 流动性种类
     * 
     * @return 流动性种类
     */
    public String getFlowType() {
        return flowType;
    }

    /**
     * 设置 流动性种类
     * 
     * @param flowType 流动性种类
     */
    public void setFlowType(String flowType) {
        this.flowType = flowType == null ? null : flowType.trim();
    }

    /**
     * 取得 指标种类
     * 
     * @return 指标种类
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置 指标种类
     * 
     * @param targetType 指标种类
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }
}