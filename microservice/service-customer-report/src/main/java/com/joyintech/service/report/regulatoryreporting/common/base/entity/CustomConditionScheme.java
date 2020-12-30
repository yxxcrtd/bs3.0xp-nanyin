package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CustomConditionScheme extends BaseEntity {
    /**
     * 主键,配置方案名称加密
     */
    private String seqno;

    /**
     * 配置方案名称
     */
    private String schemeName;

    /**
     * 查询模块代码
     */
    private String moduleId;

    /**
     * 查询日期
     */
    private String queryDate;

    /**
     * 查询区间
     */
    private String queryRange;

    /**
     * 报表维度
     */
    private String reportDimension;

    /**
     * 穿透维度
     */
    private String penetrateDimension;

    /**
     * 组合维度
     */
    private String combinationDimension;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 主键,配置方案名称加密
     * @return 主键,配置方案名称加密
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 主键,配置方案名称加密
     * @param seqno 主键,配置方案名称加密
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 配置方案名称
     * @return 配置方案名称
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * 设置 配置方案名称
     * @param schemeName 配置方案名称
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    /**
     * 取得 查询模块代码
     * @return 查询模块代码
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置 查询模块代码
     * @param moduleId 查询模块代码
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    /**
     * 取得 查询日期
     * @return 查询日期
     */
    public String getQueryDate() {
        return queryDate;
    }

    /**
     * 设置 查询日期
     * @param queryDate 查询日期
     */
    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate == null ? null : queryDate.trim();
    }

    /**
     * 取得 查询区间
     * @return 查询区间
     */
    public String getQueryRange() {
        return queryRange;
    }

    /**
     * 设置 查询区间
     * @param queryRange 查询区间
     */
    public void setQueryRange(String queryRange) {
        this.queryRange = queryRange == null ? null : queryRange.trim();
    }

    /**
     * 取得 报表维度
     * @return 报表维度
     */
    public String getReportDimension() {
        return reportDimension;
    }

    /**
     * 设置 报表维度
     * @param reportDimension 报表维度
     */
    public void setReportDimension(String reportDimension) {
        this.reportDimension = reportDimension == null ? null : reportDimension.trim();
    }

    /**
     * 取得 穿透维度
     * @return 穿透维度
     */
    public String getPenetrateDimension() {
        return penetrateDimension;
    }

    /**
     * 设置 穿透维度
     * @param penetrateDimension 穿透维度
     */
    public void setPenetrateDimension(String penetrateDimension) {
        this.penetrateDimension = penetrateDimension == null ? null : penetrateDimension.trim();
    }

    /**
     * 取得 组合维度
     * @return 组合维度
     */
    public String getCombinationDimension() {
        return combinationDimension;
    }

    /**
     * 设置 组合维度
     * @param combinationDimension 组合维度
     */
    public void setCombinationDimension(String combinationDimension) {
        this.combinationDimension = combinationDimension == null ? null : combinationDimension.trim();
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
}