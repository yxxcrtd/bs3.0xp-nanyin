package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class FinCashResetPlan extends FinCashResetPlanKey {
    /**
     * 调整前重置日
     */
    private Date resetDateUnadjust;

    /**
     * 利率确认日
     */
    private Date confirmDate;

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
     * 取得 调整前重置日
     * @return 调整前重置日
     */
    public Date getResetDateUnadjust() {
        return resetDateUnadjust;
    }

    /**
     * 设置 调整前重置日
     * @param resetDateUnadjust 调整前重置日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setResetDateUnadjust(Date resetDateUnadjust) {
        this.resetDateUnadjust = resetDateUnadjust;
    }

    /**
     * 取得 利率确认日
     * @return 利率确认日
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * 设置 利率确认日
     * @param confirmDate 利率确认日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
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
}