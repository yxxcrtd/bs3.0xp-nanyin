/*
 * 文件名：FinProductBuild001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.build.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品新建查询dto
 * 
 * @author zhangliang
 * @version 1.0 2018年10月13日
 * @see FinProductBuild003Dto
 * @since 1.0
 */
@PageId(value = "FinProductBuild003")
public class FinProductBuild003Dto  extends BaseEntity implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6378395144237248960L;

    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 金融产品代码
     */
    private String finprodId;
    
    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 画面Id
     */
    private String pageId;
    
    /**
     * 按钮Id
     */
    private String elementId;

    /**
     * 有效状态
     */
    private String finprodMarketId;

    /**
     * 组合代码
     */
    private String issueStatus;

    
    /**
     * 新发行状态爱
     */
    private String newIssueStatus;
    
    /**
     * 发行状态说明
     */
    private String issueStatusRemark;

    /**
     * 选中数据列表
     */
    private List<FinProductBuild003Dto> selectedItem;
    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }


    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }


    
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }


    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
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
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    @PrimaryKey(Field.PK_2)
    public String getFinprodMarketId() {
        return finprodMarketId;
    }


    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }


    
    /**
     * 获取 issueStatus
     * @return issueStatus.
     */
    public String getIssueStatus() {
        return issueStatus;
    }
    
    /**
     * 获取 issueStatus
     * @return issueStatus.
     */
    public String getIssueStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.发行状态, issueStatus);
    }


    
    /**
     * 设置 issueStatus
     * @param issueStatus issueStatus
     */
    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }


    /**
     * 获取 newIssueStatus
     * @return newIssueStatus.
     */
    public String getNewIssueStatus() {
        return newIssueStatus;
    }

    
    /**
     * 设置 newIssueStatus
     * @param newIssueStatus newIssueStatus
     */
    public void setNewIssueStatus(String newIssueStatus) {
        this.newIssueStatus = newIssueStatus;
    }

    
    /**
     * 获取 issueStatusRemark
     * @return issueStatusRemark.
     */
    public String getIssueStatusRemark() {
        return issueStatusRemark;
    }

    
    /**
     * 设置 issueStatusRemark
     * @param issueStatusRemark issueStatusRemark
     */
    public void setIssueStatusRemark(String issueStatusRemark) {
        this.issueStatusRemark = issueStatusRemark;
    }



    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }



    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }


    /**
     * 获取 selectedItems
     * @return selectedItem.
     */
    public List<FinProductBuild003Dto> getSelectedItem() {
        return selectedItem;
    }



    
    /**
     * 设置 selectedItems
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinProductBuild003Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }



    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }



    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    
}
