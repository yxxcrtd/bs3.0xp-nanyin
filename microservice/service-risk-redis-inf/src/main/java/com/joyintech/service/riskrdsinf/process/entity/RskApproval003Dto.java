package com.joyintech.service.riskrdsinf.process.entity;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.RskDimEntrustInfo;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

/**
 * 委托审批
 * 
 * @author zhengjunping
 * @version 1.0 2019年9月9日
 * @see RskApproval003Dto
 * @since 1.0
 */
@PageId("RskApproval003")
public class RskApproval003Dto extends RskDimEntrustInfo implements RskApprovalDto {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8159423314401208111L;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 资产列表
     */
    private List<RskApproval003AssetDto> assetList;

    /**
     * 获取 资产名称
     * 
     * @return 资产名称.
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * 
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * {@inheritDoc}
     */
    @PrimaryKey(Field.PK_2)
    public String getEventDateStr() {
        return DateUtil.format(super.getEventDate());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getEventId() {
        return super.getEventId();
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 试算结论
     */
    private String conclusion;

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 画面Id - 工作流参数
     */
    private String pageId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取 资产列表
     * 
     * @return 资产列表.
     */
    public List<RskApproval003AssetDto> getAssetList() {
        return assetList;
    }

    /**
     * 设置 资产列表
     * 
     * @param assetList 资产列表
     */
    public void setAssetList(List<RskApproval003AssetDto> assetList) {
        this.assetList = assetList;
    }

    /**
     * 系统标识
     */
    private String sysCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSysCode() {
        return sysCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
