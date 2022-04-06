package com.joyintech.tams.svreport.chb.boksubject.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.svreport.chb.boksubject.validator.BokSubjectDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * @author wubingbing
 * @date 2022-03-28 14:42
 */
@ValidLogic(targetClass = BokSubjectDtoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})

public class BokSubjectDto extends BaseEntity implements ProcessEntity {


    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 上级科目号
     */
    private String fsubjectId;

    /**
     * 科目类型
     */
    private String subjectType;

    /**
     * 科目级别
     */
    private Integer subjectLevel;
    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 备注
     */
    private String remark;

    private String updateDate;

    /**
     * 操作类型
     */
    private String operateType;

    private List<BokSubjectDto> selectedItem;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 要素id
     */
    private String elementId;



    private static final long serialVersionUID = 1L;
    @NotEmpty(fieldName = "BokSubject.subjectNo",groups = {ValidateGroupEdit.class})
    @Length(fieldName = "BokSubject.subjectNo", max = 32, groups = {ValidateGroupEdit.class})
    @PrimaryKey(Field.PK_1)
    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }
    @NotEmpty(fieldName = "BokSubject.subjectName",groups = {ValidateGroupEdit.class})
    @Length(fieldName = "BokSubject.subjectName", max = 300, groups = {ValidateGroupEdit.class})
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    @NotEmpty(fieldName = "BokSubject.fsubjectId",groups = {ValidateGroupEdit.class})
    public String getFsubjectId() {
        return fsubjectId;
    }

    public void setFsubjectId(String fsubjectId) {
        this.fsubjectId = fsubjectId;
    }
    @NotEmpty(fieldName = "BokSubject.subjectType",groups = {ValidateGroupEdit.class})
    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
    @NotEmpty(fieldName = "BokSubject.subjectLevel",groups = {ValidateGroupEdit.class})
    public Integer getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(Integer subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    @Length(fieldName = "BokSubject.remark", max = 1000, groups = {ValidateGroupEdit.class})
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<BokSubjectDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<BokSubjectDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

}
