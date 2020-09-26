package com.joyintech.tams.svreport.pbc.report.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_TEMPLATE表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-05
 */
public class PbcReportTemplateDto {
    /**
     * 模板uuid
     */
    private String tempuuid;

    /**
     * 模版编号
     */
    private String templateid;

    /**
     * 属性编号
     */
    private String propertyid;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 属性名称
     */
    private String propername;

    /**
     * 排序
     */
    private BigDecimal orderno;

    /**
     * 报表类型
     */
    private String reporttype;

    /**
     * 分组类型
     */
    private String grouptype;

    /**
     * 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     */
    private String reportcycle;

    /**
     * 状态
     */
    private String status;

    /**
     * null
     * @return TEMPUUID null
     */
    public String getTempuuid() {
        return tempuuid;
    }

    /**
     * null
     * @param tempuuid null
     */
    public void setTempuuid(String tempuuid) {
        this.tempuuid = tempuuid == null ? null : tempuuid.trim();
    }

    /**
     * 模版编号
     * @return TEMPLATEID 模版编号
     */
    public String getTemplateid() {
        return templateid;
    }

    /**
     * 模版编号
     * @param templateid 模版编号
     */
    public void setTemplateid(String templateid) {
        this.templateid = templateid == null ? null : templateid.trim();
    }

    /**
     * 属性编号
     * @return PROPERTYID 属性编号
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 属性编号
     * @param propertyid 属性编号
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
    }

    /**
     * 属性代码
     * @return PROPERTYCODE 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 属性名称
     * @return PROPERNAME 属性名称
     */
    public String getPropername() {
        return propername;
    }

    /**
     * 属性名称
     * @param propername 属性名称
     */
    public void setPropername(String propername) {
        this.propername = propername == null ? null : propername.trim();
    }

    /**
     * 排序
     * @return ORDERNO 排序
     */
    public BigDecimal getOrderno() {
        return orderno;
    }

    /**
     * 排序
     * @param orderno 排序
     */
    public void setOrderno(BigDecimal orderno) {
        this.orderno = orderno;
    }

    /**
     * 报表类型
     * @return REPORTTYPE 报表类型
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型
     * @param reporttype 报表类型
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型
     * @return GROUPTYPE 分组类型
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型
     * @param grouptype 分组类型
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
    }

    /**
     * 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     * @return REPORTCYCLE 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     */
    public String getReportcycle() {
        return reportcycle;
    }

    /**
     * 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     * @param reportcycle 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     */
    public void setReportcycle(String reportcycle) {
        this.reportcycle = reportcycle == null ? null : reportcycle.trim();
    }

    /**
     * 状态
     * @return STATUS 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}