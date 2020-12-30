package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ShareFileReport extends BaseEntity {
    /**
     * 主键
     */
    private String reportuuid;

    /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型:B_1~B_7
     */
    private String reporttype;

    /**
     * 开始日期
     */
    private Date startdate;

    /**
     * 结束日期
     */
    private Date enddate;

    /**
     * 报表频率：D-日，WE-周报，M-月报，S-季，Y-年
     */
    private String reportfrequ;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 主键
     * @param reportuuid 主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 取得 期数
     * @return 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 设置 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 取得 报表类型:B_1~B_7
     * @return 报表类型:B_1~B_7
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型:B_1~B_7
     * @param reporttype 报表类型:B_1~B_7
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 取得 开始日期
     * @return 开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 设置 开始日期
     * @param startdate 开始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 取得 结束日期
     * @return 结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 设置 结束日期
     * @param enddate 结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 取得 报表频率：D-日，WE-周报，M-月报，S-季，Y-年
     * @return 报表频率：D-日，WE-周报，M-月报，S-季，Y-年
     */
    public String getReportfrequ() {
        return reportfrequ;
    }

    /**
     * 设置 报表频率：D-日，WE-周报，M-月报，S-季，Y-年
     * @param reportfrequ 报表频率：D-日，WE-周报，M-月报，S-季，Y-年
     */
    public void setReportfrequ(String reportfrequ) {
        this.reportfrequ = reportfrequ == null ? null : reportfrequ.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
}