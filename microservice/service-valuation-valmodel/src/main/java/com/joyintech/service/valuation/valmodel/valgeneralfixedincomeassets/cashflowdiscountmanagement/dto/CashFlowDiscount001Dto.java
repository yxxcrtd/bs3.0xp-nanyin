package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;

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
     * 平滑区间
     */
    private String smoothSection;

    /** modify by yanya on 20210821 按需求增加、是否兜底评级、兜底评级、生失效时间 start**/
    /**
     * 是否使用兜底评级
     */
    private String isBottomGrade;

    /**
     * 兜底评级
     */
    private String bottomGrade;

    /**
     * 生效日期
     */
    private Date effDate;

    /**
     * 失效日期
     */
    private Date invalidDate;

    /**
     * 检验生效时间条件
     */
    private String effDateCon;

    /**
     * 检验生效时间序列
     */
    private String seqNoCon;
    /** modify by yanya on 20210821 按需求增加、是否兜底评级、兜底评级、生失效时间 end**/


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

    public String getSecTypeModel() {
        return secTypeModel;
    }

    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel;
    }

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

    public String getCurveGroupCode() {
        return curveGroupCode;
    }

    public void setCurveGroupCode(String curveGroupCode) {
        this.curveGroupCode = curveGroupCode;
    }

    public String getCurveType() {
        return curveType;
    }

    public void setCurveType(String curveType) {
        this.curveType = curveType;
    }

    public String getIsSmooth() {
        return isSmooth;
    }

    public void setIsSmooth(String isSmooth) {
        this.isSmooth = isSmooth;
    }

    public String getIsBottomGrade() {
        return isBottomGrade;
    }

    public void setIsBottomGrade(String isBottomGrade) {
        this.isBottomGrade = isBottomGrade;
    }

    public String getBottomGrade() {
        return bottomGrade;
    }

    public void setBottomGrade(String bottomGrade) {
        this.bottomGrade = bottomGrade;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getEffDateCon() {
        return effDateCon;
    }

    public void setEffDateCon(String effDateCon) {
        this.effDateCon = effDateCon;
    }

    public String getSeqNoCon() {
        return seqNoCon;
    }

    public void setSeqNoCon(String seqNoCon) {
        this.seqNoCon = seqNoCon;
    }

    /**
     * 资产类型（证券品种_模型）
     */
    public String assetType;

    /**
     * 资产细分类（数据字典证券细分类_模型）
     */
    public String assetDetailType;

    /**
     * 评级机构（数据字典 评级机构）
     */
    public String gradeOrgId;

    /**
     * 评级类型（数据字典 评级类型_模型）
     */
    public String oType;


    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetDetailType() {
        return assetDetailType;
    }

    public void setAssetDetailType(String assetDetailType) {
        this.assetDetailType = assetDetailType;
    }

    public String getGradeOrgId() {
        return gradeOrgId;
    }

    public void setGradeOrgId(String gradeOrgId) {
        this.gradeOrgId = gradeOrgId;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }
}
