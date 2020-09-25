package com.joyintech.tams.common.base.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

public class FinOpenPlan extends FinOpenPlanKey {
    /**
     * 募集起始日
     */
    private Date openDateStrUn;

    /**
     * 募集结束日
     */
    private Date openDateEndUn;

    /**
     * 起息日
     */
    private Date openVdateUn;

    /**
     * 到期日
     */
    private Date openMdateUn;

    /**
     * 募集起始日
     */
    private Date openDateStr;

    /**
     * 起息日
     */
    private Date openVdate;

    /**
     * 到期日
     */
    private Date openMdate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 开放状态，不开放、开放
     */
    private String openStatus;

    /**
     * 净值日
     */
    private Date openNdate;

    /**
     * 取得 募集起始日
     * @return 募集起始日
     */
    public Date getOpenDateStrUn() {
        return openDateStrUn;
    }

    /**
     * 设置 募集起始日
     * @param openDateStrUn 募集起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenDateStrUn(Date openDateStrUn) {
        this.openDateStrUn = openDateStrUn;
    }

    /**
     * 取得 募集结束日
     * @return 募集结束日
     */
    public Date getOpenDateEndUn() {
        return openDateEndUn;
    }

    /**
     * 设置 募集结束日
     * @param openDateEndUn 募集结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenDateEndUn(Date openDateEndUn) {
        this.openDateEndUn = openDateEndUn;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getOpenVdateUn() {
        return openVdateUn;
    }

    /**
     * 设置 起息日
     * @param openVdateUn 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenVdateUn(Date openVdateUn) {
        this.openVdateUn = openVdateUn;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getOpenMdateUn() {
        return openMdateUn;
    }

    /**
     * 设置 到期日
     * @param openMdateUn 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenMdateUn(Date openMdateUn) {
        this.openMdateUn = openMdateUn;
    }

    /**
     * 取得 募集起始日
     * @return 募集起始日
     */
    public Date getOpenDateStr() {
        return openDateStr;
    }

    /**
     * 设置 募集起始日
     * @param openDateStr 募集起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenDateStr(Date openDateStr) {
        this.openDateStr = openDateStr;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getOpenVdate() {
        return openVdate;
    }

    /**
     * 设置 起息日
     * @param openVdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenVdate(Date openVdate) {
        this.openVdate = openVdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getOpenMdate() {
        return openMdate;
    }

    /**
     * 设置 到期日
     * @param openMdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenMdate(Date openMdate) {
        this.openMdate = openMdate;
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
     * 取得 开放状态，不开放、开放
     * @return 开放状态，不开放、开放
     */
    public String getOpenStatus() {
        return openStatus;
    }

    /**
     * 设置 开放状态，不开放、开放
     * @param openStatus 开放状态，不开放、开放
     */
    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus == null ? null : openStatus.trim();
    }

    /**
     * 取得 净值日
     * @return 净值日
     */
    public Date getOpenNdate() {
        return openNdate;
    }

    /**
     * 设置 净值日
     * @param openNdate 净值日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpenNdate(Date openNdate) {
        this.openNdate = openNdate;
    }
    // 发送状态
    private String sendStatus;
    // 发送状态备注
    private String sendRemark;

    
    /**
     * 获取 sendStatus
     * @return sendStatus.
     */
    public String getSendStatus() {
        return sendStatus;
    }

    
    /**
     * 设置 sendStatus
     * @param sendStatus sendStatus
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    
    /**
     * 获取 sendRemark
     * @return sendRemark.
     */
    public String getSendRemark() {
        return sendRemark;
    }

    
    /**
     * 设置 sendRemark
     * @param sendRemark sendRemark
     */
    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }
    
    
}