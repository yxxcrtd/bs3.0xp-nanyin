package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlInvStrategy extends BaseEntity {
    /**
     * 策略代码
     */
    private String strategyId;

    /**
     * 策略类型
     */
    private String strategyType;

    /**
     * 策略名称
     */
    private String strategyName;

    /**
     * 策略描述
     */
    private String strategyDesc;

    /**
     * 备注
     */
    private String remark;

    /**
     * 使用状态
     */
    private String useStatus;

    /**
     * 策略分类，投资策略，规划策略
     */
    private String strategyClass;

    /**
     * 取得 策略代码
     * @return 策略代码
     */
    public String getStrategyId() {
        return strategyId;
    }

    /**
     * 设置 策略代码
     * @param strategyId 策略代码
     */
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    /**
     * 取得 策略类型
     * @return 策略类型
     */
    public String getStrategyType() {
        return strategyType;
    }

    /**
     * 设置 策略类型
     * @param strategyType 策略类型
     */
    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType == null ? null : strategyType.trim();
    }

    /**
     * 取得 策略名称
     * @return 策略名称
     */
    public String getStrategyName() {
        return strategyName;
    }

    /**
     * 设置 策略名称
     * @param strategyName 策略名称
     */
    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName == null ? null : strategyName.trim();
    }

    /**
     * 取得 策略描述
     * @return 策略描述
     */
    public String getStrategyDesc() {
        return strategyDesc;
    }

    /**
     * 设置 策略描述
     * @param strategyDesc 策略描述
     */
    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc == null ? null : strategyDesc.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 使用状态
     * @return 使用状态
     */
    public String getUseStatus() {
        return useStatus;
    }

    /**
     * 设置 使用状态
     * @param useStatus 使用状态
     */
    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
    }

    /**
     * 取得 策略分类，投资策略，规划策略
     * @return 策略分类，投资策略，规划策略
     */
    public String getStrategyClass() {
        return strategyClass;
    }

    /**
     * 设置 策略分类，投资策略，规划策略
     * @param strategyClass 策略分类，投资策略，规划策略
     */
    public void setStrategyClass(String strategyClass) {
        this.strategyClass = strategyClass == null ? null : strategyClass.trim();
    }
}