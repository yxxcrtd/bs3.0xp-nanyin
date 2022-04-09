package com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto;

import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.validator.ChbBokSubjectDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * @author wubingbing
 * @date 2022-03-28 14:42
 */
@ValidLogic(targetClass = ChbBokSubjectDtoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})

public class ChbBokSubjectDto extends BokSubject implements ProcessEntity {


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
    private Long subjectLevel;
    /**
     * 数据来源
     */
    private String dataSource;


    /**
     * 备注
     */
    private String remark;



    /**
     * 要素id
     */
    private String elementId;
    /**
     * 状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String des;



    private String operateType;

    private List<ChbBokSubjectDto> selectedItem;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    private String updateTimeStr;

    private String subjectTypeStr;
    private String dataSourceStr;
    private String statusStr;

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
    public Long getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(Long subjectLevel) {
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

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ChbBokSubjectDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ChbBokSubjectDto> selectedItem) {
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

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getSubjectTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.科目类型,subjectType);
    }

    public void setSubjectTypeStr(String subjectTypeStr) {
        this.subjectTypeStr = subjectTypeStr;
    }

    public String getDataSourceStr() {
        return  dataSource.equals("D")?"新建导入":"接口";
    }

    public void setDataSourceStr(String dataSourceStr) {
        this.dataSourceStr = dataSourceStr;
    }

    public String getStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,status);
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
