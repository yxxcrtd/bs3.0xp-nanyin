/*
 * 文件名：ReportPageManage001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：jiangsucheng
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.information.config.reportpage.dto;

import java.util.List;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCode;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author jiangsucheng
 * @version 1.0 2018年11月7日
 * @see ReportPageManage001Dto
 * @since 1.0
 */
public class ReportPageManage001Dto extends RepInfo {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    private String fileType;

    private String updateDate;

    private String creationDate;

    private String description;

    private String label;

    private String uri;

    private String reportparams;

    // private String modelName;
    // private String repId;
    // private String reportName;
    // private String updateUser;
    private String reportType;

    private List<RepScreen> repScreenParam;

    private List<RepJobRangeDto> settedRule;

    private String reportCategoryName; // 报表类别名称

    /**
     * 报表状态Name
     */
    @SuppressWarnings("unused")
    private String reportTypeName;

    /**
     * 获取 repScreenParam
     * 
     * @return repScreenParam.
     */
    public List<RepScreen> getRepScreenParam() {
        return repScreenParam;
    }

    /**
     * 设置 repScreenParam
     * 
     * @param repScreenParam repScreenParam
     */
    public void setRepScreenParam(List<RepScreen> repScreenParam) {
        this.repScreenParam = repScreenParam;
    }

    /**
     * 获取 reportType
     * 
     * @return reportType.
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * 设置 reportType
     * 
     * @param reportType reportType
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * 获取 reportTypeName
     * 
     * @return reportTypeName.
     */
    public String getReportTypeName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.报表启用状态,
            this.reportType)) ? SysDataUtil.getNameFromDataDict(DictCode.报表启用状态,
                this.reportType) : this.reportType;
    }

    /**
     * 设置 reportTypeName
     * 
     * @param reportTypeName reportTypeName
     */
    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    /**
     * 获取 报表类别
     * 
     * @return 报表类别.
     */
    public String getReportCategoryName() {
        return reportCategoryName;
    }

    /**
     * 设置 报表类别
     * 
     * @param 报表类别 报表类别
     */
    public void setReportCategoryName(String reportCategoryName) {
        this.reportCategoryName = reportCategoryName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getReportparams() {
        return reportparams;
    }

    public void setReportparams(String reportparams) {
        this.reportparams = reportparams;
    }

    /**
     * 获取 settedRule
     * 
     * @return settedRule.
     */
    public List<RepJobRangeDto> getSettedRule() {
        return settedRule;
    }

    /**
     * 设置 settedRule
     * 
     * @param settedRule settedRule
     */
    public void setSettedRule(List<RepJobRangeDto> settedRule) {
        this.settedRule = settedRule;
    }
}
