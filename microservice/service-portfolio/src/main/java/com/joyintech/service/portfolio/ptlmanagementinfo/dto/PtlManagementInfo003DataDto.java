/*
 * 文件名：PtlManagementInfo003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlmanagementinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

/**
 * 用户分配实体类
 * @author fanshuanghu
 * @version 1.0 2018年11月20日
 * @see PtlManagementInfo003DataDto
 * @since 1.0
 */
public class PtlManagementInfo003DataDto extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 分配方法
     */
    private String allocationWay;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 用户
     */
    private String userId;
    /**
     * 用户集合
     */
    private List<String> userList;
    /**
     * elementId
     */
    private String elementId; 
    /**
     * 操作类型
     */
    private String OperateType;

    private List<PtlManagementInfo003DataDto> selectedList;

    /**
     * 获取 allocationWay
     * @return allocationWay.
     */
    public String getAllocationWay() {
        return allocationWay;
    }
    
    /**
     * 设置 allocationWay
     * @param allocationWay allocationWay
     */
    public void setAllocationWay(String allocationWay) {
        this.allocationWay = allocationWay;
    }

    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return OperateType;
    }
    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        OperateType = operateType;
    }


    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }

    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 portfolioId
     * @return portfolioId.
     */
    
    public String getPortfolioId() {
        return portfolioId;
    }
    
    /**
     * 设置 portfolioId
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    /**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 userId
     * @return userId.
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * 设置 userId
     * @param userId userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取 userList
     * @return userList.
     */
    public List<String> getUserList() {
        return userList;
    }
    
    /**
     * 设置 userList
     * @param userList userList
     */
    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    /**
     * 获取 selectedList
     * @return selectedList.
     */
    public List<PtlManagementInfo003DataDto> getSelectedList() {
        return selectedList;
    }

    /**
     * 设置 selectedList
     * @param selectedList selectedList
     */
    public void setSelectedList(List<PtlManagementInfo003DataDto> selectedList) {
        this.selectedList = selectedList;
    }
}
