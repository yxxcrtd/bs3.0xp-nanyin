package com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.validator.CashFlowDiscountValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

@PageId("CashFlowDiscount002")
@ValidLogic(targetClass = CashFlowDiscountValidator.class, groups = {ValidateGroupEdit.class})
public class CashFlowDiscount001Dto extends BaseEntity implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 证券品种
     */
    private String secTypeModel;

    /**
     * 证券细分类
     */
    private String secDetailTypeModel;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 曲线族代码
     */
    private String curveGroupCode;

    /**
     * 曲线类型
     */
    private String curveType;

    /**
     * 是否平滑曲线
     */
    private String isSmooth;

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
     * 平滑区间
     */
    private String smoothSection;

    /**
     * 选中的数据
     */
    private transient List<CashFlowDiscount001Dto> selectedItem;

    public List<CashFlowDiscount001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<CashFlowDiscount001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSmoothSection() {
        return smoothSection;
    }

    public void setSmoothSection(String smoothSection) {
        this.smoothSection = smoothSection;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "资产类型", groups = {ValidateGroupEdit.class})
    public String getSecTypeModel() {
        return secTypeModel;
    }

    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel;
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    @NotEmpty(fieldName = "资产细分类", groups = {ValidateGroupEdit.class})
    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    @NotEmpty(fieldName = "曲线族", groups = {ValidateGroupEdit.class})
    public String getCurveGroupCode() {
        return curveGroupCode;
    }

    public void setCurveGroupCode(String curveGroupCode) {
        this.curveGroupCode = curveGroupCode;
    }

    @NotEmpty(fieldName = "曲线类型", groups = {ValidateGroupEdit.class})
    public String getCurveType() {
        return curveType;
    }

    public void setCurveType(String curveType) {
        this.curveType = curveType;
    }

    @NotEmpty(fieldName = "是否平滑曲线", groups = {ValidateGroupEdit.class})
    public String getIsSmooth() {
        return isSmooth;
    }

    public void setIsSmooth(String isSmooth) {
        this.isSmooth = isSmooth;
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

    public String getSecTypeModelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产类型, this.getSecTypeModel());
    }

    public String getSecDetailTypeModelStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.资产分类, this.getSecDetailTypeModel());
    }

    public String getCurveGroupCodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.曲线族, this.getCurveGroupCode());
    }

    public String getCurveTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.收益率曲线类型, this.getCurveType());
    }

    public String getSmoothSectionStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.平滑区间, this.getSmoothSection());
    }

    public String getIsSmoothStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsSmooth());
    }
}
