/*
 * 文件名：FlowRiskFlowAssets001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.risk.stresstestmanage.flowriskflowassets.validator.FlowRiskFlowAssets001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 *  流动性资产配置dto
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskFlowAssets001Dto
 * @since 1.0
 */
@PageId("FlowRiskFlowAssets001")
@ValidLogic(targetClass = FlowRiskFlowAssets001Validator.class,groups = {
        ValidateGroupEdit.class,ValidateGroupDelete.class})
public class FlowRiskFlowAssets001Dto extends BaseEntity implements ITableRow,ProcessEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2914961685269573570L;

    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    /**
     * 对象代码
     */
    private String tgtTypeId;
    /**
     * 字典明细id
     */
    private String id;
    /**
     * 父Id
     */
    private String pId;
    /**
     * 名称
     */
    private String name;
    /**
     * 字典代码
     */
    private String dictCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 映射代码
     */
    private String mappingId;
    /**
     * 目标代码
     */
    private String tgtCode;
    /**
     * 目标类型
     */
    private String tgtType;
    /**
     * 目标值
     */
    private String tgtValue;
    /**
     * 明细代码
     */
    private String detId;
    /**
     * 源代码
     */
    private String souceCode;
    /**
     * 源类型
     */
    private String souceType;
    /**
     * 源值
     */
    private String souceValue;
    /**
     * 级别
     */
    private String lev;
    /**
     * 父明细代码
     */
    private String fDetId;
    /**
     * tree子数据
     */
    private String child;
    /**
     * tree父数据
     */
    private String pDictCode;
    /**
     * 连接代码
     */
    private String linkedItemCode;
    /**
     * 一级流动性资产选择数据
     */
    private List<FlowRiskFlowAssets001Dto> selectedFlowAss1;
    
    /**
     * 二级流动性资产选择数据
     */
    private List<FlowRiskFlowAssets001Dto> selectedFlowAss2;

    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }

    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
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
     * 获取 id
     * @return id.
     */
    public String getId() {
        return id;
    }

    
    /**
     * 设置 id
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    
    /**
     * 获取 name
     * @return name.
     */
    public String getName() {
        return name;
    }

    
    /**
     * 设置 name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * 获取 dictCode
     * @return dictCode.
     */
    public String getDictCode() {
        return dictCode;
    }

    
    /**
     * 设置 dictCode
     * @param dictCode dictCode
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    
    /**
     * 获取 selectedFlowAss1
     * @return selectedFlowAss1.
     */
    public List<FlowRiskFlowAssets001Dto> getSelectedFlowAss1() {
        return selectedFlowAss1;
    }

    
    /**
     * 设置 selectedFlowAss1
     * @param selectedFlowAss1 selectedFlowAss1
     */
    public void setSelectedFlowAss1(List<FlowRiskFlowAssets001Dto> selectedFlowAss1) {
        this.selectedFlowAss1 = selectedFlowAss1;
    }

    
    /**
     * 获取 selectedFlowAss2
     * @return selectedFlowAss2.
     */
    public List<FlowRiskFlowAssets001Dto> getSelectedFlowAss2() {
        return selectedFlowAss2;
    }

    
    /**
     * 设置 selectedFlowAss2
     * @param selectedFlowAss2 selectedFlowAss2
     */
    public void setSelectedFlowAss2(List<FlowRiskFlowAssets001Dto> selectedFlowAss2) {
        this.selectedFlowAss2 = selectedFlowAss2;
    }

    /**
     * 获取 tgtTypeId
     * @return tgtTypeId.
     */
    @PrimaryKey(value=Field.PK_1)
    public String getTgtTypeId() {
        return tgtTypeId;
    }

    /**
     * 设置 tgtTypeId
     * @param projectId tgtTypeId
     */
    public void setTgtTypeId(String tgtTypeId) {
        this.tgtTypeId = tgtTypeId;
    }

    
    /**
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }

    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    
    /**
     * 获取 mappingId
     * @return mappingId.
     */
    public String getMappingId() {
        return mappingId;
    }


    
    /**
     * 设置 mappingId
     * @param mappingId mappingId
     */
    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }


    
    /**
     * 获取 tgtCode
     * @return tgtCode.
     */
    public String getTgtCode() {
        return tgtCode;
    }


    
    /**
     * 设置 tgtCode
     * @param tgtCode tgtCode
     */
    public void setTgtCode(String tgtCode) {
        this.tgtCode = tgtCode;
    }


    
    /**
     * 获取 tgtType
     * @return tgtType.
     */
    public String getTgtType() {
        return tgtType;
    }


    
    /**
     * 设置 tgtType
     * @param tgtType tgtType
     */
    public void setTgtType(String tgtType) {
        this.tgtType = tgtType;
    }


    
    /**
     * 获取 tgtValue
     * @return tgtValue.
     */
    public String getTgtValue() {
        return tgtValue;
    }


    
    /**
     * 设置 tgtValue
     * @param tgtValue tgtValue
     */
    public void setTgtValue(String tgtValue) {
        this.tgtValue = tgtValue;
    }


    
    /**
     * 获取 detId
     * @return detId.
     */
    public String getDetId() {
        return detId;
    }


    
    /**
     * 设置 detId
     * @param detId detId
     */
    public void setDetId(String detId) {
        this.detId = detId;
    }


    
    /**
     * 获取 souceCode
     * @return souceCode.
     */
    public String getSouceCode() {
        return souceCode;
    }


    
    /**
     * 设置 souceCode
     * @param souceCode souceCode
     */
    public void setSouceCode(String souceCode) {
        this.souceCode = souceCode;
    }


    
    /**
     * 获取 souceType
     * @return souceType.
     */
    public String getSouceType() {
        return souceType;
    }


    
    /**
     * 设置 souceType
     * @param souceType souceType
     */
    public void setSouceType(String souceType) {
        this.souceType = souceType;
    }


    
    /**
     * 获取 souceValue
     * @return souceValue.
     */
    public String getSouceValue() {
        return souceValue;
    }


    
    /**
     * 设置 souceValue
     * @param souceValue souceValue
     */
    public void setSouceValue(String souceValue) {
        this.souceValue = souceValue;
    }


    
    /**
     * 获取 lev
     * @return lev.
     */
    public String getLev() {
        return lev;
    }


    
    /**
     * 设置 lev
     * @param lev lev
     */
    public void setLev(String lev) {
        this.lev = lev;
    }


    
    /**
     * 获取 fDetId
     * @return fDetId.
     */
    public String getfDetId() {
        return fDetId;
    }


    
    /**
     * 设置 fDetId
     * @param fDetId fDetId
     */
    public void setfDetId(String fDetId) {
        this.fDetId = fDetId;
    }


    
    /**
     * 获取 pId
     * @return pId.
     */
    public String getpId() {
        return pId;
    }


    
    /**
     * 设置 pId
     * @param pId pId
     */
    public void setpId(String pId) {
        this.pId = pId;
    }


    
    /**
     * 获取 child
     * @return child.
     */
    public String getChild() {
        return child;
    }


    
    /**
     * 设置 child
     * @param child child
     */
    public void setChild(String child) {
        this.child = child;
    }


    
    /**
     * 获取 pDictCode
     * @return pDictCode.
     */
    public String getpDictCode() {
        return pDictCode;
    }


    
    /**
     * 设置 pDictCode
     * @param pDictCode pDictCode
     */
    public void setpDictCode(String pDictCode) {
        this.pDictCode = pDictCode;
    }


    
    /**
     * 获取 linkedItemCode
     * @return linkedItemCode.
     */
    public String getLinkedItemCode() {
        return linkedItemCode;
    }


    
    /**
     * 设置 linkedItemCode
     * @param linkedItemCode linkedItemCode
     */
    public void setLinkedItemCode(String linkedItemCode) {
        this.linkedItemCode = linkedItemCode;
    }
    
    
    
    
    
}
