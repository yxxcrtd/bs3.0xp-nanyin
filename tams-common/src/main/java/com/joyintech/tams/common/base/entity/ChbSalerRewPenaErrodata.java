package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class ChbSalerRewPenaErrodata extends BaseEntity {
    /**
     * 奖惩编号 
     */
    private String salerRewPenaNo;

    /**
     * 内部编号 
     */
    private String salerInsNo;

    /**
     * 记录分类 
     */
    private String salerRewPenaTyep;

    /**
     * 奖惩单位 
     */
    private String salerRewPenaInst;

    /**
     * 奖惩起始日期 
     */
    private Date salerRewPenaStart;

    /**
     * 投诉日期 
     */
    private Date salerComplainDate;

    /**
     * 数据日期 
     */
    private Date dataDate;

    /**
     * null
     */
    private String workflowId;

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
     * 取得 null
     * @return null
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 null
     * @param workflowId null
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
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