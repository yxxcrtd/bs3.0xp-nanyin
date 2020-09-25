package com.joyintech.service.disclosure.information.reveal.detailshow.dto;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.RepScreen;

/**
 * @author leipeng
 * @version 1.0 2018年8月2日
 * @see RepDetailsShow001Dto
 * @since 1.0
 */
public class RepDetailsShow001Dto extends BaseEntity{
	
    /**
     * 报表ID
     */
    private String repId;

    /**
     * 模板编号
     */
    private String templateId;

    /**
     * 模板目录
     */
    private String templatePath;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 报表状态
     */
    private String reportType;
    
    
    /**
     * 报表列表
     */
    private List<RepScreen> repScreenList = new ArrayList<>();


    
    public String getRepId() {
        return repId;
    }


    
    public void setRepId(String repId) {
        this.repId = repId;
    }


    
    public String getTemplateId() {
        return templateId;
    }


    
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }


    
    public String getTemplatePath() {
        return templatePath;
    }


    
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }


    
    public String getReportName() {
        return reportName;
    }


    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }


    
    public String getReportType() {
        return reportType;
    }


    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }


    
    public List<RepScreen> getRepScreenList() {
        return repScreenList;
    }


    
    public void setRepScreenList(List<RepScreen> repScreenList) {
        this.repScreenList = repScreenList;
    }
    

}
