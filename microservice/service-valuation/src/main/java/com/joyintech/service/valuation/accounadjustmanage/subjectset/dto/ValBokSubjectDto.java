/*
 * 文件名：ValBokSubjectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.subjectset.dto;

import java.util.List;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.service.valuation.accounadjustmanage.subjectset.validator.ValBokSubjectDtoValidator;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCode;

@ValidLogic(targetClass = ValBokSubjectDtoValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class ValBokSubjectDto extends BokSubject implements ITableRow {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1852967864201289335L;

    /**
     * 余额方向
     */
    @SuppressWarnings("unused")
    private String balFlagName;

    /**
     * 余额方向
     */
    @SuppressWarnings("unused")
    private String subjectTypeName;

    /**
     * 是否核算数量
     */
    @SuppressWarnings("unused")
    private String accQuaFlagName;

    /**
     * 是否计息
     */
    @SuppressWarnings("unused")
    private String accIntFlagName;

    /**
     * 是否允许透支
     */
    @SuppressWarnings("unused")
    private String overdrawnFlagName;

    /**
     * 自动生成子科目
     */
    private String genDetsubFlag;
    
    /**
     * 自动生成子科目Code
     */
    private String genDetsubFlagCode;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 画面模式
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<ValBokSubjectDto> selectedItem;

    /**
     * 账套代码
     */
    @Length(fieldName = "ValSubjectSet001.booksetId", max = 20, groups = {ValidateGroupEdit.class})
    @Override
    public String getBooksetId() {
        return super.getBooksetId();
    }

    /**
     * 科目号
     */
    @Length(fieldName = "ValSubjectSet001.subjectNo", max = 20, groups = {ValidateGroupEdit.class})
    @Override
    public String getSubjectNo() {
        return super.getSubjectNo();
    }

    /**
     * 科目名称
     */
    @NotEmpty(fieldName = "ValSubjectSet001.subjectName", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "ValSubjectSet001.subjectName", max = 200, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getSubjectName() {
        return super.getSubjectName();
    }

    /**
     * 上级科目号
     */
    @Length(fieldName = "ValSubjectSet001.fsubjectId", max = 20, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getFsubjectId() {
        return super.getFsubjectId();
    }

    /**
     * 余额方向
     */
    @Length(fieldName = "ValSubjectSet001.balFlag", max = 10, groups = {ValidateGroupEdit.class})
    @NotEmpty(fieldName = "ValSubjectSet001.balFlag", groups = {ValidateGroupEdit.class})
    @Override
    public String getBalFlag() {
        return super.getBalFlag();
    }

    /**
     * 科目类型
     */
    @NotEmpty(fieldName = "ValSubjectSet001.subjectType", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "ValSubjectSet001.subjectType", max = 10, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getSubjectType() {
        return super.getSubjectType();
    }

    /**
     * 科目级别
     */
    @NotEmpty(fieldName = "ValSubjectSet001.subjectLevel", groups = {ValidateGroupEdit.class})
    @Override
    public Long getSubjectLevel() {
        return super.getSubjectLevel();
    }

    /**
     * 是否核算数量
     */
    @Length(fieldName = "ValSubjectSet001.accQuaFlag", max = 10, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getAccQuaFlag() {
        return super.getAccQuaFlag();
    }

    /**
     * 是否计息
     */
    @Length(fieldName = "ValSubjectSet001.accIntFlag", max = 10, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getAccIntFlag() {
        return super.getAccIntFlag();
    }

    /**
     * 是否允许透支
     */
    @Length(fieldName = "ValSubjectSet001.overdrawnFlag", max = 10, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getOverdrawnFlag() {
        return super.getOverdrawnFlag();
    }

    /**
     * 获取 自动生成子科目
     * 
     * @return genDetsubFlag.
     */
    public String getGenDetsubFlag() {
        return this.genDetsubFlag;
    }

    /**
     * 设置 自动生成子科目
     * 
     * @param genDetsubFlag genDetsubFlag
     */
    public void setGenDetsubFlag(String genDetsubFlag) {
        this.genDetsubFlag = genDetsubFlag;
    }

    
    
    /**
     * 获取 genDetsubFlagCode
     * @return genDetsubFlagCode.
     */
    public String getGenDetsubFlagCode() {
        return genDetsubFlagCode;
    }

    
    /**
     * 设置 genDetsubFlagCode
     * @param genDetsubFlagCode genDetsubFlagCode
     */
    public void setGenDetsubFlagCode(String genDetsubFlagCode) {
        this.genDetsubFlagCode = genDetsubFlagCode;
    }

    /**
     * 基础科目性质
     */
    @Length(fieldName = "ValSubjectSet001.baseSubjectNature", max = 100, groups = {
        ValidateGroupEdit.class})
    @Override
    public String getBaseSubjectNature() {
        return super.getBaseSubjectNature();
    }

    /**
     * 扩展科目性质1
     */
    // @Length(fieldName = "ValSubjectSet001.subjectNature1", max = 100, groups = {
    // ValidateGroupEdit.class})
    // @Override
    // public String getSubjectNature1() {
    // return super.getSubjectNature1();
    // }

    /**
     * 扩展科目性质2
     */
    // @Length(fieldName = "ValSubjectSet001.subjectNature2", max = 100, groups = {
    // ValidateGroupEdit.class})
    // @Override
    // public String getSubjectNature2() {
    // return super.getSubjectNature2();
    // }

    /**
     * 扩展科目性质3
     */
    // @Length(fieldName = "ValSubjectSet001.subjectNature3", max = 100, groups = {
    // ValidateGroupEdit.class})
    // @Override
    // public String getSubjectNature3() {
    // return super.getSubjectNature3();
    // }

    /**
     * 扩展科目性质4
     */
    // @Length(fieldName = "ValSubjectSet001.subjectNature4", max = 100, groups = {
    // ValidateGroupEdit.class})
    // @Override
    // public String getSubjectNature4() {
    // return super.getSubjectNature4();
    // }

    /**
     * 扩展科目性质5
     */
    // @Length(fieldName = "ValSubjectSet001.subjectNature5", max = 100, groups = {
    // ValidateGroupEdit.class})
    // @Override
    // public String getSubjectNature5() {
    // return super.getSubjectNature5();
    // }

    /**
     * 扩展科目性质6
     */
    // @Length(fieldName = "ValSubjectSet001.subjectNature6", max = 100, groups = {
    // ValidateGroupEdit.class})
    // @Override
    // public String getSubjectNature6() {
    // return super.getSubjectNature6();
    // }

    public List<ValBokSubjectDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ValBokSubjectDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getBalFlagName() {
        return this.balFlagName;
    }

    public void setBalFlagName(String balFlagName) {
        this.balFlagName = balFlagName;
    }

    public String getSubjectTypeName() {
        return this.subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    public String getAccQuaFlagName() {
        return this.accQuaFlagName;
    }

    public void setAccQuaFlagName(String accQuaFlagName) {
        this.accQuaFlagName = accQuaFlagName;
    }

    public String getAccIntFlagName() {
        return this.accIntFlagName;
    }

    public void setAccIntFlagName(String accIntFlagName) {
        this.accIntFlagName = accIntFlagName;
    }

    public String getOverdrawnFlagName() {
        return this.overdrawnFlagName;
    }

    public void setOverdrawnFlagName(String overdrawnFlagName) {
        this.overdrawnFlagName = overdrawnFlagName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

}
