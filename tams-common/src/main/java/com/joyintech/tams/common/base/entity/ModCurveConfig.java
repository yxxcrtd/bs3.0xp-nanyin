package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ModCurveConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 证券品种（数据字典.证券品种_模型）
     */
    private String secTypeModel;

    /**
     * 证券细分类（数据字典.证券细分类_模型）
     */
    private String secDetailTypeModel;

    /**
     * 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * 曲线族代码（数据字典.曲线族）
     */
    private String curveGroupCode;

    /**
     * 曲线类型（数据字典.曲线类型）
     */
    private String curveType;

    /**
     * 是否平滑曲线(数据字典.是否）
     */
    private String issmooth;

    /**
     * 平滑区间(数据字典.平滑区间）
     */
    private String smoothsection;

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
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 证券品种（数据字典.证券品种_模型）
     * @return 证券品种（数据字典.证券品种_模型）
     */
    public String getSecTypeModel() {
        return secTypeModel;
    }

    /**
     * 设置 证券品种（数据字典.证券品种_模型）
     * @param secTypeModel 证券品种（数据字典.证券品种_模型）
     */
    public void setSecTypeModel(String secTypeModel) {
        this.secTypeModel = secTypeModel == null ? null : secTypeModel.trim();
    }

    /**
     * 取得 证券细分类（数据字典.证券细分类_模型）
     * @return 证券细分类（数据字典.证券细分类_模型）
     */
    public String getSecDetailTypeModel() {
        return secDetailTypeModel;
    }

    /**
     * 设置 证券细分类（数据字典.证券细分类_模型）
     * @param secDetailTypeModel 证券细分类（数据字典.证券细分类_模型）
     */
    public void setSecDetailTypeModel(String secDetailTypeModel) {
        this.secDetailTypeModel = secDetailTypeModel == null ? null : secDetailTypeModel.trim();
    }

    /**
     * 取得 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @return 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码，存储上游系统内部代码，比如金融产品代码
     * @param finprodId 金融产品代码，存储上游系统内部代码，比如金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品名称
     * @return 金融产品名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 金融产品名称
     * @param finprodName 金融产品名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName == null ? null : finprodName.trim();
    }

    /**
     * 取得 曲线族代码（数据字典.曲线族）
     * @return 曲线族代码（数据字典.曲线族）
     */
    public String getCurveGroupCode() {
        return curveGroupCode;
    }

    /**
     * 设置 曲线族代码（数据字典.曲线族）
     * @param curveGroupCode 曲线族代码（数据字典.曲线族）
     */
    public void setCurveGroupCode(String curveGroupCode) {
        this.curveGroupCode = curveGroupCode == null ? null : curveGroupCode.trim();
    }

    /**
     * 取得 曲线类型（数据字典.曲线类型）
     * @return 曲线类型（数据字典.曲线类型）
     */
    public String getCurveType() {
        return curveType;
    }

    /**
     * 设置 曲线类型（数据字典.曲线类型）
     * @param curveType 曲线类型（数据字典.曲线类型）
     */
    public void setCurveType(String curveType) {
        this.curveType = curveType == null ? null : curveType.trim();
    }

    /**
     * 取得 是否平滑曲线(数据字典.是否）
     * @return 是否平滑曲线(数据字典.是否）
     */
    public String getIssmooth() {
        return issmooth;
    }

    /**
     * 设置 是否平滑曲线(数据字典.是否）
     * @param issmooth 是否平滑曲线(数据字典.是否）
     */
    public void setIssmooth(String issmooth) {
        this.issmooth = issmooth == null ? null : issmooth.trim();
    }

    /**
     * 取得 平滑区间(数据字典.平滑区间）
     * @return 平滑区间(数据字典.平滑区间）
     */
    public String getSmoothsection() {
        return smoothsection;
    }

    /**
     * 设置 平滑区间(数据字典.平滑区间）
     * @param smoothsection 平滑区间(数据字典.平滑区间）
     */
    public void setSmoothsection(String smoothsection) {
        this.smoothsection = smoothsection == null ? null : smoothsection.trim();
    }

    /**
     * 取得 是否使用兜底评级
     * @return 是否使用兜底评级
     */
    public String getIsBottomGrade() {
        return isBottomGrade;
    }

    /**
     * 设置 是否使用兜底评级
     * @param isBottomGrade 是否使用兜底评级
     */
    public void setIsBottomGrade(String isBottomGrade) {
        this.isBottomGrade = isBottomGrade == null ? null : isBottomGrade.trim();
    }

    /**
     * 取得 兜底评级
     * @return 兜底评级
     */
    public String getBottomGrade() {
        return bottomGrade;
    }

    /**
     * 设置 兜底评级
     * @param bottomGrade 兜底评级
     */
    public void setBottomGrade(String bottomGrade) {
        this.bottomGrade = bottomGrade == null ? null : bottomGrade.trim();
    }

    /**
     * 取得 生效日期
     * @return 生效日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日期
     * @param effDate 生效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 失效日期
     * @return 失效日期
     */
    public Date getInvalidDate() {
        return invalidDate;
    }

    /**
     * 设置 失效日期
     * @param invalidDate 失效日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }
}