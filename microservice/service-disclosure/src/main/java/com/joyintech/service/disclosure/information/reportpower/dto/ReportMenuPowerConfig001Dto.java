/*
 * 文件名：ReportMenuPowerConfig001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.disclosure.information.reportpower.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 报表菜单权限配置
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月10日
 * @see ReportMenuPowerConfig001Dto
 * @since 1.0
 */
public class ReportMenuPowerConfig001Dto extends BaseEntity{
    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * ID
     */
    private String repId;
    
    /**
     * ID
     */
    private String roleId;

    
    
    
    public String getRoleId() {
        return roleId;
    }



    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }



    public String getRepId() {
        return repId;
    }


    
    public void setRepId(String repId) {
        this.repId = repId;
    }


    public String getReportName() {
        return reportName;
    }

    
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    
    public String getRoleName() {
        return roleName;
    }

    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    

}
