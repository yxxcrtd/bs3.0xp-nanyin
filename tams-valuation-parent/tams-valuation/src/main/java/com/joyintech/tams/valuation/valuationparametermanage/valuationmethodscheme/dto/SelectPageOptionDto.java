/*
 * 文件名：SelectPageOptionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.valuationparametermanage.valuationmethodscheme.dto;

import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeList;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongjun
 * @version 1.0 2018年12月18日
 * @see SelectPageOptionDto
 * @since 1.0
 */
public class SelectPageOptionDto extends SelectOptionDto implements ITableRow{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状态: A,E,P,D
     */
    private String status;
    
    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    public SelectPageOptionDto() {
//        this.createUser = LoginUserUtil.getUserName();
//        this.updateUser = LoginUserUtil.getUserName();
        this.createTime = new Date();
        this.updateTime = new Date();
//        this.createDept = LoginUserUtil.getDeptCode();
        this.createTime = new Date();
        this.updateTime = new Date();
        this.status = DictCodeList.数据状态_新建.getValue();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取 createDept
     * 
     * @return createDept.
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 设置 createDept
     * 
     * @param createDept createDept
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    
    /**
     * 获取 workflowId
     * @return workflowId.
     */
    public String getWorkflowId() {
        return this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    
    /**
     * 设置 workflowId
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }
   
   

}
