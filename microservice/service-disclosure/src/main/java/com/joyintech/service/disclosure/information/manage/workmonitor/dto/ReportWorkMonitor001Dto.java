/*
 * 
 */
package com.joyintech.service.disclosure.information.manage.workmonitor.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.RepMonitor;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ZhangYangFei
 * @version 1.0 2018年11月12日
 * @see ReportWorkMonitor001Dto
 * @since 1.0
 */
@PageId("ReportWorkMonitor001")
public class ReportWorkMonitor001Dto extends RepMonitor implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private String reportName;

    private String loopTypeName;

    private String outTypeName;

    private String data;

    private String elementId;

    private String jobName;

    private String jobStatusName;

    /**
     * 上一次运行时间
     */
    private String endDateStr;

    /**
     * 下一次运行时间
     */
    private String nextDateStr;
    
    /**
     * 获取 endDateStr
     * @return endDateStr.
     */
    public String getEndDateStr() {
        return endDateStr;
    }

    
    /**
     * 设置 endDateStr
     * @param endDateStr endDateStr
     */
    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    
    /**
     * 获取 nextDateStr
     * @return nextDateStr.
     */
    public String getNextDateStr() {
        return nextDateStr;
    }

    
    /**
     * 设置 nextDateStr
     * @param nextDateStr nextDateStr
     */
    public void setNextDateStr(String nextDateStr) {
        this.nextDateStr = nextDateStr;
    }

    /**
     * 获取 jobStatusName
     * 
     * @return jobStatusName.
     */
    public String getJobStatusName() {
        return jobStatusName;
    }

    /**
     * 设置 jobStatusName
     * 
     * @param jobStatusName jobStatusName
     */
    public void setJobStatusName(String jobStatusName) {
        this.jobStatusName = jobStatusName;
    }

    /**
     * 获取 jobName
     * 
     * @return jobName.
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置 jobName
     * 
     * @param jobName jobName
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取 reportName
     * 
     * @return reportName.
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * 设置 reportName
     * 
     * @param reportName reportName
     */
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    /**
     * 获取 loopTypeName
     * 
     * @return loopTypeName.
     */
    public String getLoopTypeName() {
        return loopTypeName;
    }

    /**
     * 设置 loopTypeName
     * 
     * @param loopTypeName loopTypeName
     */
    public void setLoopTypeName(String loopTypeName) {
        this.loopTypeName = loopTypeName;
    }

    /**
     * 获取 outTypeName
     * 
     * @return outTypeName.
     */
    public String getOutTypeName() {
        return outTypeName;
    }

    /**
     * 设置 outTypeName
     * 
     * @param outTypeName outTypeName
     */
    public void setOutTypeName(String outTypeName) {
        this.outTypeName = outTypeName;
    }

    /**
     * 获取 data
     * 
     * @return data.
     */
    public String getData() {
        return data;
    }

    /**
     * 设置 data
     * 
     * @param data data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * 获取 elementId
     * 
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     * 
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

}
