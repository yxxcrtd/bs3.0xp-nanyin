package com.joyintech.interf.bankname.cfetsup.secdialogquote.dto;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.entity.FxSecdialogQuote;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.validator.FxSecdialogQuote002Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

@PageId("FxSecdialogQuote002")
@ValidLogic(targetClass = FxSecdialogQuote002Validator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class FxSecdialogQuote002Dto extends FxSecdialogQuote implements ProcessEntity {

    //组合代码
    private String portfolioId;

    // 组合名称
    private String portfolioName;

    //交割日期
    @NotEmpty(fieldName = "FxSecdialogQuote002.settledate", groups = {ValidateGroupEdit.class})
    private Date settledate;

    //组合T日可用现金
    private BigDecimal avaliblecash;

    //组合T日可用持仓
    private BigDecimal avalibleholdposition;

    //债券简称
    private String secname;

    //债券代码
    @NotEmpty(fieldName = "FxSecdialogQuote002.secname", groups = {ValidateGroupEdit.class})
    private String seccode;

    //本币本方
    private String homeid;

    //交易场所
    private String jycs;

    //交易日历
    private String calendarId;

    //回复报价
    private String replyType;

    /**
     * 计算类型
     */
    private String calculateField;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 按钮类型
     */
    private String btnOperateType;

    /**
     * 撤销原因
     */
    private String revokeReason;
    /**
     * 界面类型
     */
    private String pageModel;
    /**
     * 画面元素
     */
    private String elementId;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    @Override
    public Date getSettledate() {
        return settledate;
    }

    @Override
    public void setSettledate(Date settledate) {
        this.settledate = settledate;
    }

    public BigDecimal getAvaliblecash() {
        return avaliblecash;
    }

    public void setAvaliblecash(BigDecimal avaliblecash) {
        this.avaliblecash = avaliblecash;
    }

    public BigDecimal getAvalibleholdposition() {
        return avalibleholdposition;
    }

    public void setAvalibleholdposition(BigDecimal avalibleholdposition) {
        this.avalibleholdposition = avalibleholdposition;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    @Override
    public String getSeccode() {
        return seccode;
    }

    @Override
    public void setSeccode(String seccode) {
        this.seccode = seccode;
    }

    public String getHomeid() {
        return homeid;
    }

    public void setHomeid(String homeid) {
        this.homeid = homeid;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getRevokeReason() {
        return revokeReason;
    }

    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
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

    public String getJycs() {
        return jycs;
    }

    public void setJycs(String jycs) {
        this.jycs = jycs;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getBtnOperateType() {
        return btnOperateType;
    }

    public void setBtnOperateType(String btnOperateType) {
        this.btnOperateType = btnOperateType;
    }

    public String getCalculateField() {
        return calculateField;
    }

    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }
}
