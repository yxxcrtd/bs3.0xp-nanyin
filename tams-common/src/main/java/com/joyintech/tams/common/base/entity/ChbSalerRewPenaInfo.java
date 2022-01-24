package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerRewPenaInfo extends BaseEntity {
    /**
     * 奖惩编号 
     */
    private String salerRewPenaNo;

    /**
     * 内部编号 
     */
    private String salerInsNo;

    /**
     * 销售机构代码 
     */
    private String salerInstCode;

    /**
     * 记录分类 
     */
    private String salerRewPenaTyep;

    /**
     * 奖惩名称 
     */
    private String salerRewPenaName;

    /**
     * 奖惩说明 
     */
    private String salerRewPenaComments;

    /**
     * 奖惩单位 
     */
    private String salerRewPenaInst;

    /**
     * 奖惩单位具体名称 
     */
    private String salerRewPenaInstName;

    /**
     * 奖惩起始日期 
     */
    private Date salerRewPenaStart;

    /**
     * 奖惩结束日期 
     */
    private Date salerRewPenaEnd;

    /**
     * 投诉详情 
     */
    private String salerComplainDetail;

    /**
     * 投诉处理结果 
     */
    private String salerComplainResult;

    /**
     * 投诉日期 
     */
    private Date salerComplainDate;

    /**
     * 数据日期 
     */
    private Date dataDate;

    /**
     * 备注 
     */
    private String remark;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 取得 奖惩编号 
     * @return 奖惩编号 
     */
    public String getSalerRewPenaNo() {
        return salerRewPenaNo;
    }

    /**
     * 设置 奖惩编号 
     * @param salerRewPenaNo 奖惩编号 
     */
    public void setSalerRewPenaNo(String salerRewPenaNo) {
        this.salerRewPenaNo = salerRewPenaNo == null ? null : salerRewPenaNo.trim();
    }

    /**
     * 取得 内部编号 
     * @return 内部编号 
     */
    public String getSalerInsNo() {
        return salerInsNo;
    }

    /**
     * 设置 内部编号 
     * @param salerInsNo 内部编号 
     */
    public void setSalerInsNo(String salerInsNo) {
        this.salerInsNo = salerInsNo == null ? null : salerInsNo.trim();
    }

    /**
     * 取得 销售机构代码 
     * @return 销售机构代码 
     */
    public String getSalerInstCode() {
        return salerInstCode;
    }

    /**
     * 设置 销售机构代码 
     * @param salerInstCode 销售机构代码 
     */
    public void setSalerInstCode(String salerInstCode) {
        this.salerInstCode = salerInstCode == null ? null : salerInstCode.trim();
    }

    /**
     * 取得 记录分类 
     * @return 记录分类 
     */
    public String getSalerRewPenaTyep() {
        return salerRewPenaTyep;
    }

    /**
     * 设置 记录分类 
     * @param salerRewPenaTyep 记录分类 
     */
    public void setSalerRewPenaTyep(String salerRewPenaTyep) {
        this.salerRewPenaTyep = salerRewPenaTyep == null ? null : salerRewPenaTyep.trim();
    }

    /**
     * 取得 奖惩名称 
     * @return 奖惩名称 
     */
    public String getSalerRewPenaName() {
        return salerRewPenaName;
    }

    /**
     * 设置 奖惩名称 
     * @param salerRewPenaName 奖惩名称 
     */
    public void setSalerRewPenaName(String salerRewPenaName) {
        this.salerRewPenaName = salerRewPenaName == null ? null : salerRewPenaName.trim();
    }

    /**
     * 取得 奖惩说明 
     * @return 奖惩说明 
     */
    public String getSalerRewPenaComments() {
        return salerRewPenaComments;
    }

    /**
     * 设置 奖惩说明 
     * @param salerRewPenaComments 奖惩说明 
     */
    public void setSalerRewPenaComments(String salerRewPenaComments) {
        this.salerRewPenaComments = salerRewPenaComments == null ? null : salerRewPenaComments.trim();
    }

    /**
     * 取得 奖惩单位 
     * @return 奖惩单位 
     */
    public String getSalerRewPenaInst() {
        return salerRewPenaInst;
    }

    /**
     * 设置 奖惩单位 
     * @param salerRewPenaInst 奖惩单位 
     */
    public void setSalerRewPenaInst(String salerRewPenaInst) {
        this.salerRewPenaInst = salerRewPenaInst == null ? null : salerRewPenaInst.trim();
    }

    /**
     * 取得 奖惩单位具体名称 
     * @return 奖惩单位具体名称 
     */
    public String getSalerRewPenaInstName() {
        return salerRewPenaInstName;
    }

    /**
     * 设置 奖惩单位具体名称 
     * @param salerRewPenaInstName 奖惩单位具体名称 
     */
    public void setSalerRewPenaInstName(String salerRewPenaInstName) {
        this.salerRewPenaInstName = salerRewPenaInstName == null ? null : salerRewPenaInstName.trim();
    }

    /**
     * 取得 奖惩起始日期 
     * @return 奖惩起始日期 
     */
    public Date getSalerRewPenaStart() {
        return salerRewPenaStart;
    }

    /**
     * 设置 奖惩起始日期 
     * @param salerRewPenaStart 奖惩起始日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSalerRewPenaStart(Date salerRewPenaStart) {
        this.salerRewPenaStart = salerRewPenaStart;
    }

    /**
     * 取得 奖惩结束日期 
     * @return 奖惩结束日期 
     */
    public Date getSalerRewPenaEnd() {
        return salerRewPenaEnd;
    }

    /**
     * 设置 奖惩结束日期 
     * @param salerRewPenaEnd 奖惩结束日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSalerRewPenaEnd(Date salerRewPenaEnd) {
        this.salerRewPenaEnd = salerRewPenaEnd;
    }

    /**
     * 取得 投诉详情 
     * @return 投诉详情 
     */
    public String getSalerComplainDetail() {
        return salerComplainDetail;
    }

    /**
     * 设置 投诉详情 
     * @param salerComplainDetail 投诉详情 
     */
    public void setSalerComplainDetail(String salerComplainDetail) {
        this.salerComplainDetail = salerComplainDetail == null ? null : salerComplainDetail.trim();
    }

    /**
     * 取得 投诉处理结果 
     * @return 投诉处理结果 
     */
    public String getSalerComplainResult() {
        return salerComplainResult;
    }

    /**
     * 设置 投诉处理结果 
     * @param salerComplainResult 投诉处理结果 
     */
    public void setSalerComplainResult(String salerComplainResult) {
        this.salerComplainResult = salerComplainResult == null ? null : salerComplainResult.trim();
    }

    /**
     * 取得 投诉日期 
     * @return 投诉日期 
     */
    public Date getSalerComplainDate() {
        return salerComplainDate;
    }

    /**
     * 设置 投诉日期 
     * @param salerComplainDate 投诉日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSalerComplainDate(Date salerComplainDate) {
        this.salerComplainDate = salerComplainDate;
    }

    /**
     * 取得 数据日期 
     * @return 数据日期 
     */
    public Date getDataDate() {
        return dataDate;
    }

    /**
     * 设置 数据日期 
     * @param dataDate 数据日期 
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    /**
     * 取得 备注 
     * @return 备注 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注 
     * @param remark 备注 
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

}