package com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.lossmodel.insideoutsidegrademanagement.validator.InsideOutsideGradeValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("CashFlowDiscount002")
@ValidLogic(targetClass = InsideOutsideGradeValidator.class, groups = {ValidateGroupEdit.class})
public class InsideOutsideGrade001Dto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 评级机构
     */
    private String gradeInst;

    /**
     * 对象类型
     */
    private String oType;

    /**
     * 外部评级
     */
    private String gradeResult;

    /**
     * 内部评级
     */
    private String gradeStandardResult;

    /**
     * 评级映射明细dto
     */
    private List<InsideOutsideGradeDetail001Dto> selectInsideOutsideDetail;

    /**
     * 操作类型
     */
    private String operateType;

    private String pageModel;

    /**
     * 画面Id
     */
    private String pageId;

    /**
     * elementId
     */
    private String elementId;

    /**
     * 选中的数据
     */
    private transient List<InsideOutsideGrade001Dto> selectedItem;

    public List<InsideOutsideGradeDetail001Dto> getSelectInsideOutsideDetail() {
        return selectInsideOutsideDetail;
    }

    public void setSelectInsideOutsideDetail(List<InsideOutsideGradeDetail001Dto> selectInsideOutsideDetail) {
        this.selectInsideOutsideDetail = selectInsideOutsideDetail;
    }

    public List<InsideOutsideGrade001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<InsideOutsideGrade001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "评级机构", groups = {ValidateGroupEdit.class})
    public String getGradeInst() {
        return gradeInst;
    }

    public void setGradeInst(String gradeInst) {
        this.gradeInst = gradeInst;
    }
    @PrimaryKey(PrimaryKey.Field.PK_2)
    @NotEmpty(fieldName = "对象类型", groups = {ValidateGroupEdit.class})
    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getGradeResult() {
        return gradeResult;
    }

    public void setGradeResult(String gradeResult) {
        this.gradeResult = gradeResult;
    }

    public String getGradeStandardResult() {
        return gradeStandardResult;
    }

    public void setGradeStandardResult(String gradeStandardResult) {
        this.gradeStandardResult = gradeStandardResult;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getGradeInstStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级机构, this.getGradeInst());
    }

    public String getoTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.评级类型, this.getoType());
    }
}
