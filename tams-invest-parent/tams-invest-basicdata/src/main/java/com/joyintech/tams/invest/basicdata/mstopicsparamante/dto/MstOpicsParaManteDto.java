/*
 * 文件名：MstOpicsParaManteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstopicsparamante.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.OpicsParam;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.basicdata.mstopicsparamante.validator.MstOpicsParaManteValidator;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * OPICS参数维护dto
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaManteDto
 * @since 1.0
 */
@PageId("MstOpicsParaMante002")
@ValidLogic(targetClass = MstOpicsParaManteValidator.class, groups = {
    ValidateGroupEdit.class, ValidateGroupDelete.class})
public class MstOpicsParaManteDto extends OpicsParam implements ITableRow, ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 选中的数据
     */
    private transient List<MstOpicsParaManteDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    /**
     * 参数类型，包括计息规则、BRANCK、BROK、COST、DEALTYPE、PORT、业务类型、VERIND
     */
    private String paraTypeText;

    /**
     * 获取 selectedItem
     *
     * @return selectedItem.
     */
    public List<MstOpicsParaManteDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 selectedItem
     *
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<MstOpicsParaManteDto> selectedItem) {
        this.selectedItem = selectedItem;
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
     * 获取 pageModel
     *
     * @return pageModel.
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
     * 获取 serialversionuid
     *
     * @return serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 设置参数类型为主键 {@inheritDoc}
     */
    @Override
    @PrimaryKey(value = Field.PK_1)
    @NotEmpty(fieldName = "MstOpicsParaMante001.paraType", groups = {ValidateGroupEdit.class})
    public String getParaType() {
        return super.getParaType();
    }

    /**
     * 设置项目代码为主键并设置长度 {@inheritDoc}
     */
    @Override
    @Length(fieldName = "MstOpicsParaMante001.itemId", max = 32, groups = {
        ValidateGroupEdit.class})
    @NotEmpty(fieldName = "MstOpicsParaMante001.itemId", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value = Field.PK_2)
    public String getItemId() {
        return super.getItemId();
    }

    /**
     * 设置项目代码名称长度 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstOpicsParaMante001.itemName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstOpicsParaMante001.itemName", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getItemName() {
        return super.getItemName();
    }

    /**
     * 设置项目值(中文)的长度 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstOpicsParaMante001.itemValueCn", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstOpicsParaMante001.itemValueCn", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getItemValueCn() {
        return super.getItemValueCn();
    }

    /**
     * 设置项目值(英文)的长度 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstOpicsParaMante001.itemValueEn", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstOpicsParaMante001.itemValueEn", max = 50, groups = {
        ValidateGroupEdit.class})
    public String getItemValueEn() {
        return super.getItemValueEn();
    }

    /**
     * 设置排序的长度 {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "MstOpicsParaMante001.orders", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstOpicsParaMante001.orders", max = 3, groups = {ValidateGroupEdit.class})
    public BigDecimal getOrders() {
        return super.getOrders();
    }

    /**
     * 设置备注的长度 {@inheritDoc}
     */
    @Override
    @Length(fieldName = "MstOpicsParaMante001.remark", max = 200, groups = {
        ValidateGroupEdit.class})
    public String getRemark() {
        return super.getRemark();
    }

    public String getParaTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.OPICS参数类型, this.getParaType());
    }

    /**
     * 获取 paraTypeText
     * @return paraTypeText.
     */
    public String getParaTypeText() {
        paraTypeText = SysDataUtil.getNameFromDataDict(DictCode.OPICS参数类型, this.getParaType());
        return paraTypeText;
    }

    /**
     * 设置 paraTypeText
     * @param paraTypeText paraTypeText
     */
    public void setParaTypeText(String paraTypeText) {
        this.paraTypeText = paraTypeText;
    }
}
