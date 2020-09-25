/*
 * 文件名：FinSubmittedModule001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PrdDeclareScheme;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.product.productelement.findisclosuremodule.finsubmittedmodule.validator.FinSubmittedModule001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 申报方案dto
 * 
 * @author liuwei
 * @version 1.0 2018年8月21日
 * @see FinSubmittedModule001Dto
 * @since 1.0
 */
@PageId("FinSubmittedModule001")
@ValidLogic(targetClass = FinSubmittedModule001Validator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class FinSubmittedModule001Dto extends PrdDeclareScheme implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 操作类型
     */
    private String pageModel;

    /**
     * 画面元素
     */
    private String elementId;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    @Length(fieldName = "FinSubmittedModule002.schemeName", max = 60, groups = {
        ValidateGroupEdit.class})
    @NotEmpty(fieldName = "FinSubmittedModule002.schemeName", groups = {ValidateGroupEdit.class})
    public String getSchemeName() {
        return super.getSchemeName();
    }

    /**
     * 选中的数据列表
     */
    private transient List<FinSubmittedModule001Dto> selectedItem;

    /**
     * 取得 方案代码
     * 
     * @return 方案代码
     */
    @PrimaryKey(Field.PK_1)
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
    public List<FinSubmittedModule001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     * 
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinSubmittedModule001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取 pageModel
     *
     * @return pageModel
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置 pageModel
     *
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
}
