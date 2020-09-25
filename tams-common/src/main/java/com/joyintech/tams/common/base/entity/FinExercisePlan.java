package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinExercisePlan extends FinExercisePlanKey {
    /**
     * 申请开始日
     */
    private Date vdate;

    /**
     * 申请结束日
     */
    private Date mdate;

    /**
     * 行权状态，已行权、未行权
     */
    private String exerciseStatus;

    /**
     * 行权价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exercisePrice;

    /**
     * 行权条款
     */
    private String exerciseRemark;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 实际行权日
     */
    private Date actExerciseDate;

    /**
     * 取得 申请开始日
     * @return 申请开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 申请开始日
     * @param vdate 申请开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 申请结束日
     * @return 申请结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 申请结束日
     * @param mdate 申请结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 行权状态，已行权、未行权
     * @return 行权状态，已行权、未行权
     */
    public String getExerciseStatus() {
        return exerciseStatus;
    }

    /**
     * 设置 行权状态，已行权、未行权
     * @param exerciseStatus 行权状态，已行权、未行权
     */
    public void setExerciseStatus(String exerciseStatus) {
        this.exerciseStatus = exerciseStatus == null ? null : exerciseStatus.trim();
    }

    /**
     * 取得 行权价
     * @return 行权价
     */
    public BigDecimal getExercisePrice() {
        return exercisePrice;
    }

    /**
     * 设置 行权价
     * @param exercisePrice 行权价
     */
    public void setExercisePrice(BigDecimal exercisePrice) {
        this.exercisePrice = exercisePrice;
    }

    /**
     * 取得 行权条款
     * @return 行权条款
     */
    public String getExerciseRemark() {
        return exerciseRemark;
    }

    /**
     * 设置 行权条款
     * @param exerciseRemark 行权条款
     */
    public void setExerciseRemark(String exerciseRemark) {
        this.exerciseRemark = exerciseRemark == null ? null : exerciseRemark.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType2 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 实际行权日
     * @return 实际行权日
     */
    public Date getActExerciseDate() {
        return actExerciseDate;
    }

    /**
     * 设置 实际行权日
     * @param actExerciseDate 实际行权日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setActExerciseDate(Date actExerciseDate) {
        this.actExerciseDate = actExerciseDate;
    }
}