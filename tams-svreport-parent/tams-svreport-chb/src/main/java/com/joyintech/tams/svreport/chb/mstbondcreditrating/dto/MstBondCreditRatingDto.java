package com.joyintech.tams.svreport.chb.mstbondcreditrating.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import com.joyintech.tams.svreport.chb.boksubject.validator.BokSubjectDtoValidator;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.validator.MstBondCreditRatingValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

/**
 * 债券信用评级
 * @TableName MST_BOND_CREDIT_RATING
 */
@ValidLogic(targetClass = MstBondCreditRatingValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class MstBondCreditRatingDto extends BaseEntity implements ProcessEntity {
    /**
     * 债券代码（市场代码）
     */
    private String oCode;

    /**
     * 评级机构
     */
    private String gradeOrgId;

    /**
     * 评级日期
     */
    private Date gradeDate;


    /**
     * 评级类型
     */
    private String shortLongTerm;

    /**
     * 评级结果
     */
    private String gradeResult;

    /**
     * 评级来源
     */
    private String source;

    /**
     * 数据来源 
     */
    private String dataSource;

    /**
     * 状态
     */
    private String status;

    /**
     * 债券名称
     */
    private String oName;

    /**
     * 评级对象
     */
    private String oObject;

    /**
     * 描述
     */
    private String describe;
    /**
     * 操作类型
     */
    private String operateType;

    private List<MstBondCreditRatingDto> selectedItem;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;

    /**
     * 要素id
     */
    private String elementId;


    private static final long serialVersionUID = 1L;

    /**
     * 债券代码（市场代码）
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.oCode",groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getoCode() {
        return oCode;
    }

    /**
     * 债券代码（市场代码）
     */
    public void setoCode(String oCode) {
        this.oCode = oCode;
    }

    /**
     * 评级机构
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.gradeOrgId",groups = {ValidateGroupEdit.class})
    public String getGradeOrgId() {
        return gradeOrgId;
    }

    /**
     * 评级机构
     */
    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    /**
     * 评级日期
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.gradeDate",groups = {ValidateGroupEdit.class})
    public Date getGradeDate() {
        return gradeDate;
    }

    /**
     * 评级日期
     */
    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }



    /**
     * 评级类型
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.shortLongTerm",groups = {ValidateGroupEdit.class})
    public String getShortLongTerm() {
        return shortLongTerm;
    }

    /**
     * 评级类型
     */
    public void setShortLongTerm(String shortLongTerm) {
        this.shortLongTerm = shortLongTerm;
    }

    /**
     * 评级结果
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.gradeResult",groups = {ValidateGroupEdit.class})
    public String getGradeResult() {
        return gradeResult;
    }

    /**
     * 评级结果
     */
    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    /**
     * 数据来源 
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 数据来源 
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 债券名称
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.oName",groups = {ValidateGroupEdit.class})
    public String getoName() {
        return oName;
    }

    /**
     * 债券名称
     */
    public void setoName(String oName) {
        this.oName = oName;
    }

    /**
     * 评级对象
     */
    @NotEmpty(fieldName = "MstBondCreditRating001.oObject",groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getoObject() {
        return oObject;
    }

    /**
     * 评级对象
     */
    public void setoObject(String oObject) {
        this.oObject = oObject;
    }

    /**
     * 描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    @NotEmpty(fieldName = "MstBondCreditRating001.source",groups = {ValidateGroupEdit.class})
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<MstBondCreditRatingDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstBondCreditRatingDto> selectedItem) {
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