package com.joyintech.tams.svreport.chb.monthreport.dto;

import com.joyintech.framework.base.BaseEntity;

import java.io.Serializable;

public class ChbMonthReportG06bTemplate extends BaseEntity implements Serializable {
    /**
     * 
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
    private Long orderno;

    /**
     * 报表类型：PROD-产品，BALANCE-资产
     */
    private String reporttype;

    /**
     * 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     */
    private String reportcycle;

    /**
     * 状态
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public ChbMonthReportG06bTemplate(String tempuuid, String templateid, String propertyid, String propertycode, String propername, Long orderno, String reporttype, String reportcycle, String status) {
        this.tempuuid = tempuuid;
        this.templateid = templateid;
        this.propertyid = propertyid;
        this.propertycode = propertycode;
        this.propername = propername;
        this.orderno = orderno;
        this.reporttype = reporttype;
        this.reportcycle = reportcycle;
        this.status = status;
    }

    public ChbMonthReportG06bTemplate() {
        super();
    }

    /**
     * 获取 
     */
    public String getTempuuid() {
        return tempuuid;
    }

    /**
     * 设置 
     */
    public void setTempuuid(String tempuuid) {
        this.tempuuid = tempuuid == null ? null : tempuuid.trim();
    }

    /**
     * 获取 模版编号
     */
    public String getTemplateid() {
        return templateid;
    }

    /**
     * 设置 模版编号
     */
    public void setTemplateid(String templateid) {
        this.templateid = templateid == null ? null : templateid.trim();
    }

    /**
     * 获取 属性编号
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * 设置 属性编号
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid == null ? null : propertyid.trim();
    }

    /**
     * 获取 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 设置 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 获取 属性名称
     */
    public String getPropername() {
        return propername;
    }

    /**
     * 设置 属性名称
     */
    public void setPropername(String propername) {
        this.propername = propername == null ? null : propername.trim();
    }

    /**
     * 获取 排序
     */
    public Long getOrderno() {
        return orderno;
    }

    /**
     * 设置 排序
     */
    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    /**
     * 获取 报表类型：PROD-产品，BALANCE-资产
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型：PROD-产品，BALANCE-资产
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 获取 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     */
    public String getReportcycle() {
        return reportcycle;
    }

    /**
     * 设置 报表周期：D-日，WE-周报，M-月报，S-季，Y-年
     */
    public void setReportcycle(String reportcycle) {
        this.reportcycle = reportcycle == null ? null : reportcycle.trim();
    }

    /**
     * 获取 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChbMonthReportG06bTemplate other = (ChbMonthReportG06bTemplate) that;
        return (this.getTempuuid() == null ? other.getTempuuid() == null : this.getTempuuid().equals(other.getTempuuid()))
            && (this.getTemplateid() == null ? other.getTemplateid() == null : this.getTemplateid().equals(other.getTemplateid()))
            && (this.getPropertyid() == null ? other.getPropertyid() == null : this.getPropertyid().equals(other.getPropertyid()))
            && (this.getPropertycode() == null ? other.getPropertycode() == null : this.getPropertycode().equals(other.getPropertycode()))
            && (this.getPropername() == null ? other.getPropername() == null : this.getPropername().equals(other.getPropername()))
            && (this.getOrderno() == null ? other.getOrderno() == null : this.getOrderno().equals(other.getOrderno()))
            && (this.getReporttype() == null ? other.getReporttype() == null : this.getReporttype().equals(other.getReporttype()))
            && (this.getReportcycle() == null ? other.getReportcycle() == null : this.getReportcycle().equals(other.getReportcycle()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTempuuid() == null) ? 0 : getTempuuid().hashCode());
        result = prime * result + ((getTemplateid() == null) ? 0 : getTemplateid().hashCode());
        result = prime * result + ((getPropertyid() == null) ? 0 : getPropertyid().hashCode());
        result = prime * result + ((getPropertycode() == null) ? 0 : getPropertycode().hashCode());
        result = prime * result + ((getPropername() == null) ? 0 : getPropername().hashCode());
        result = prime * result + ((getOrderno() == null) ? 0 : getOrderno().hashCode());
        result = prime * result + ((getReporttype() == null) ? 0 : getReporttype().hashCode());
        result = prime * result + ((getReportcycle() == null) ? 0 : getReportcycle().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tempuuid=").append(tempuuid);
        sb.append(", templateid=").append(templateid);
        sb.append(", propertyid=").append(propertyid);
        sb.append(", propertycode=").append(propertycode);
        sb.append(", propername=").append(propername);
        sb.append(", orderno=").append(orderno);
        sb.append(", reporttype=").append(reporttype);
        sb.append(", reportcycle=").append(reportcycle);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}