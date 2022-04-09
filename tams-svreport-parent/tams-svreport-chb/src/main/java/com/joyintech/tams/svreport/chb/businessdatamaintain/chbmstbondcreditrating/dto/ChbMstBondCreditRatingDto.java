package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BaseCommonCheckDto;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.validator.ChbMstBondCreditRatingValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 债券信用评级
 * @TableName MST_BOND_CREDIT_RATING
 */
@ValidLogic(targetClass = ChbMstBondCreditRatingValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ChbMstBondCreditRatingDto extends MstBondCreditRating implements ProcessEntity {
    /**
     * 债券代码（市场代码）
     */
    private String oCode;

    /**
     * 评级机构
     */
    private String gradeOrgId;
    private String gradeOrgIdStr;

    /**
     * 评级日期
     */
    private String gradeDateStr;


    private String updateTimeStr;


    /**
     * 评级类型
     */
    private String shortLongTerm;
    private String shortLongTermStr;

    /**
     * 评级结果
     */
    private String gradeResult;
    private String gradeResultStr;

    /**
     * 评级来源
     */
    private String source;
    private String sourceStr;

    /**
     * 数据来源 
     */
    private String dataSource;
    private String dataSourceStr;

    /**
     * 状态
     */
    private String status;
    private String statusStr;

    /**
     * 债券名称
     */
    private String oName;

    /**
     * 评级对象
     */
    private String oObject;
    private String oObjectStr;

    /**
     * 描述
     */
    private String des;
    /**
     * 操作类型
     */
    private String operateType;

    private List<ChbMstBondCreditRatingDto> selectedItem;

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
    @NotEmpty(fieldName = "MstBondCreditRating001.gradeDateStr",groups = {ValidateGroupEdit.class})
    public String getGradeDateStr() {
        return gradeDateStr;
    }

    /**
     * 评级日期
     */
    public void setGradeDateStr(String gradeDateStr) {
        this.gradeDateStr = gradeDateStr;
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

    public List<ChbMstBondCreditRatingDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ChbMstBondCreditRatingDto> selectedItem) {
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



    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getGradeOrgIdStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级机构,gradeOrgId);
    }

    public void setGradeOrgIdStr(String gradeOrgIdStr) {
        this.gradeOrgIdStr = gradeOrgIdStr;
    }

    public String getShortLongTermStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.长期短期,shortLongTerm);
    }

    public void setShortLongTermStr(String shortLongTermStr) {
        this.shortLongTermStr = shortLongTermStr;
    }

    public String getGradeResultStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级结果,gradeResult);
    }

    public void setGradeResultStr(String gradeResultStr) {
        this.gradeResultStr = gradeResultStr;
    }

    public String getSourceStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级来源类型,source);
    }

    public void setSourceStr(String sourceStr) {
        this.sourceStr = sourceStr;
    }

    public String getDataSourceStr() {
        return dataSource.equals("D")?"新建导入":"接口";
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

    public String getoObjectStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级类型,oObject);
    }

    public void setoObjectStr(String oObjectStr) {
        this.oObjectStr = oObjectStr;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}