package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupIndicator extends BaseEntity {
    /**
     * 监督指标编号
     */
    private String indId;

    /**
     * 监督指标名称

     */
    private String indName;

    /**
     * 指标类型
     */
    private String indType;

    /**
     * 表达式
     */
    private String expr;

    /**
     * 监督类型(SUPERVISION:监管,INTERNAL:内控)

     */
    private String supType;

    /**
     * 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     */
    private String timePoint;

    /**
     * 监督频率(日间、日终)

     */
    private String supFrequency;

    /**
     * 监控方向
     */
    private String supDirection;

    /**
     * 预警条件
     */
    private String warnCond;

    /**
     * 审批条件
     */
    private String approveCond;

    /**
     * 违规条件
     */
    private String foulCond;

    /**
     * 是否调整期
     */
    private String isAdjust;

    /**
     * 调整期时间(天)
     */
    private Long adjust;

    /**
     * 是否启用(是，否)，默认为是
     */
    private String isEnabled;

    /**
     * 备注
     */
    private String memo;

    /**
     * 除0是否异常
     */
    private String isExcpWhenDivZero;

    /**
     * 比较天数
     */
    private Long compareDays;

    /**
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 监督指标编号
     * @param indId 监督指标编号
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

    /**
     * 取得 监督指标名称

     * @return 监督指标名称

     */
    public String getIndName() {
        return indName;
    }

    /**
     * 设置 监督指标名称

     * @param indName 监督指标名称

     */
    public void setIndName(String indName) {
        this.indName = indName == null ? null : indName.trim();
    }

    /**
     * 取得 指标类型
     * @return 指标类型
     */
    public String getIndType() {
        return indType;
    }

    /**
     * 设置 指标类型
     * @param indType 指标类型
     */
    public void setIndType(String indType) {
        this.indType = indType == null ? null : indType.trim();
    }

    /**
     * 取得 表达式
     * @return 表达式
     */
    public String getExpr() {
        return expr;
    }

    /**
     * 设置 表达式
     * @param expr 表达式
     */
    public void setExpr(String expr) {
        this.expr = expr == null ? null : expr.trim();
    }

    /**
     * 取得 监督类型(SUPERVISION:监管,INTERNAL:内控)

     * @return 监督类型(SUPERVISION:监管,INTERNAL:内控)

     */
    public String getSupType() {
        return supType;
    }

    /**
     * 设置 监督类型(SUPERVISION:监管,INTERNAL:内控)

     * @param supType 监督类型(SUPERVISION:监管,INTERNAL:内控)

     */
    public void setSupType(String supType) {
        this.supType = supType == null ? null : supType.trim();
    }

    /**
     * 取得 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     * @return 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     */
    public String getTimePoint() {
        return timePoint;
    }

    /**
     * 设置 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     * @param timePoint 监督时点(委托监控,交易监控,事后监控),多个用逗号分隔
     */
    public void setTimePoint(String timePoint) {
        this.timePoint = timePoint == null ? null : timePoint.trim();
    }

    /**
     * 取得 监督频率(日间、日终)

     * @return 监督频率(日间、日终)

     */
    public String getSupFrequency() {
        return supFrequency;
    }

    /**
     * 设置 监督频率(日间、日终)

     * @param supFrequency 监督频率(日间、日终)

     */
    public void setSupFrequency(String supFrequency) {
        this.supFrequency = supFrequency == null ? null : supFrequency.trim();
    }

    /**
     * 取得 监控方向
     * @return 监控方向
     */
    public String getSupDirection() {
        return supDirection;
    }

    /**
     * 设置 监控方向
     * @param supDirection 监控方向
     */
    public void setSupDirection(String supDirection) {
        this.supDirection = supDirection == null ? null : supDirection.trim();
    }

    /**
     * 取得 预警条件
     * @return 预警条件
     */
    public String getWarnCond() {
        return warnCond;
    }

    /**
     * 设置 预警条件
     * @param warnCond 预警条件
     */
    public void setWarnCond(String warnCond) {
        this.warnCond = warnCond == null ? null : warnCond.trim();
    }

    /**
     * 取得 审批条件
     * @return 审批条件
     */
    public String getApproveCond() {
        return approveCond;
    }

    /**
     * 设置 审批条件
     * @param approveCond 审批条件
     */
    public void setApproveCond(String approveCond) {
        this.approveCond = approveCond == null ? null : approveCond.trim();
    }

    /**
     * 取得 违规条件
     * @return 违规条件
     */
    public String getFoulCond() {
        return foulCond;
    }

    /**
     * 设置 违规条件
     * @param foulCond 违规条件
     */
    public void setFoulCond(String foulCond) {
        this.foulCond = foulCond == null ? null : foulCond.trim();
    }

    /**
     * 取得 是否调整期
     * @return 是否调整期
     */
    public String getIsAdjust() {
        return isAdjust;
    }

    /**
     * 设置 是否调整期
     * @param isAdjust 是否调整期
     */
    public void setIsAdjust(String isAdjust) {
        this.isAdjust = isAdjust == null ? null : isAdjust.trim();
    }

    /**
     * 取得 调整期时间(天)
     * @return 调整期时间(天)
     */
    public Long getAdjust() {
        return adjust;
    }

    /**
     * 设置 调整期时间(天)
     * @param adjust 调整期时间(天)
     */
    public void setAdjust(Long adjust) {
        this.adjust = adjust;
    }

    /**
     * 取得 是否启用(是，否)，默认为是
     * @return 是否启用(是，否)，默认为是
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置 是否启用(是，否)，默认为是
     * @param isEnabled 是否启用(是，否)，默认为是
     */
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 取得 除0是否异常
     * @return 除0是否异常
     */
    public String getIsExcpWhenDivZero() {
        return isExcpWhenDivZero;
    }

    /**
     * 设置 除0是否异常
     * @param isExcpWhenDivZero 除0是否异常
     */
    public void setIsExcpWhenDivZero(String isExcpWhenDivZero) {
        this.isExcpWhenDivZero = isExcpWhenDivZero == null ? null : isExcpWhenDivZero.trim();
    }

    /**
     * 取得 比较天数
     * @return 比较天数
     */
    public Long getCompareDays() {
        return compareDays;
    }

    /**
     * 设置 比较天数
     * @param compareDays 比较天数
     */
    public void setCompareDays(Long compareDays) {
        this.compareDays = compareDays;
    }
    
    /**
     * 机构号
     */
    private String institutionCode;

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    
}