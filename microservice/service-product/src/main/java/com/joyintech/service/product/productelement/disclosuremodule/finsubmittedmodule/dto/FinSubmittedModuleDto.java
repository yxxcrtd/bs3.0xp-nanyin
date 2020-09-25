/*
 * 文件名：FinSubmittedModule001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productelement.disclosuremodule.finsubmittedmodule.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PrdDeclareScheme;

/**
 * 申报方案dto
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModuleDto
 * @since 1.0
 */
public class FinSubmittedModuleDto extends PrdDeclareScheme{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    public String getSchemeName() {
        return super.getSchemeName();
    }

    /**
     * 选中的数据列表
     */
    private transient List<FinSubmittedModuleDto> selectedItem;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    public String getSchemeId() {
        return super.getSchemeId();
    }

    /**
     * 获取 operateType
     * 
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * 
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
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

    /**
     * 获取 selectedItem
     * 
     * @return selectedItem.
     */
    public List<FinSubmittedModuleDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinSubmittedModuleDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
