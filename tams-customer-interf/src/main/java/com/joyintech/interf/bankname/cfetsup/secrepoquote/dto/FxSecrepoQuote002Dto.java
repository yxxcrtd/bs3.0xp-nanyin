package com.joyintech.interf.bankname.cfetsup.secrepoquote.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.interf.bankname.cfetsup.outbound.Table;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuote;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.validator.FxSecrepoQuote002DtoValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 回购对话报价dto
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-08
 */
@PageId("FxSecrepoQuote002")
@ValidLogic(targetClass = FxSecrepoQuote002DtoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class FxSecrepoQuote002Dto extends FxSecrepoQuote implements ProcessEntity {

    /**
     * 错误描述
     */
    private String errormessage;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 本币本方
     */
    private String ywqc;

    /**
     *  本币交易对手
     */
    private String jycyqc;

    /**
     * 质押券信息
     */
    private List<FxSecrepoQuote003Dto > finprodList;

    /**
     * 债券币种
     */
    private String bondccy;

    /**
     * 组合T日可以现金
     */
    private BigDecimal avalibleCash;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     *  操作类型1
     */
    private String operateType1;

    /**
     *  操作类型2
     */
    private String operateType2;

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

    /**
     * 中债登券面总额合计（万元）
     */
    private BigDecimal zzTotalAmt;

    /**
     * 上清所券面总额合计（万元）
     */
    private BigDecimal sqTotalAmt;

    /**
     * 折后券面总额合计（万元）
     */
    private BigDecimal zhTotalAmt;

    /**
     * 折后中债登券面总额合计（万元）
     */
    private BigDecimal zhzzTotalAmt;

    /**
     * 折后上清所券面总额合计（万元）
     */
    private BigDecimal zhsqsTotalAmt;

    /**
     * 回复类型
     */
    private String replyType;

    /**
     * 交易日历
     */
    private String calendarId;

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

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

    public String getYwqc() {
        return ywqc;
    }

    public void setYwqc(String ywqc) {
        this.ywqc = ywqc;
    }

    public String getJycyqc() {
        return jycyqc;
    }

    public void setJycyqc(String jycyqc) {
        this.jycyqc = jycyqc;
    }

    public List<FxSecrepoQuote003Dto> getFinprodList() {
        return finprodList;
    }

    public void setFinprodList(List<FxSecrepoQuote003Dto> finprodList) {
        this.finprodList = finprodList;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateType1() {
        return operateType1;
    }

    public void setOperateType1(String operateType1) {
        this.operateType1 = operateType1;
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

    public String getBondccy() {
        return bondccy;
    }

    public void setBondccy(String bondccy) {
        this.bondccy = bondccy;
    }

    public BigDecimal getAvalibleCash() {
        return avalibleCash;
    }

    public void setAvalibleCash(BigDecimal avalibleCash) {
        this.avalibleCash = avalibleCash;
    }

    public BigDecimal getZzTotalAmt() {
        return zzTotalAmt;
    }

    public void setZzTotalAmt(BigDecimal zzTotalAmt) {
        this.zzTotalAmt = zzTotalAmt;
    }

    public BigDecimal getSqTotalAmt() {
        return sqTotalAmt;
    }

    public void setSqTotalAmt(BigDecimal sqTotalAmt) {
        this.sqTotalAmt = sqTotalAmt;
    }

    public BigDecimal getZhTotalAmt() {
        return zhTotalAmt;
    }

    public void setZhTotalAmt(BigDecimal zhTotalAmt) {
        this.zhTotalAmt = zhTotalAmt;
    }

    public BigDecimal getZhzzTotalAmt() {
        return zhzzTotalAmt;
    }

    public void setZhzzTotalAmt(BigDecimal zhzzTotalAmt) {
        this.zhzzTotalAmt = zhzzTotalAmt;
    }

    public BigDecimal getZhsqsTotalAmt() {
        return zhsqsTotalAmt;
    }

    public void setZhsqsTotalAmt(BigDecimal zhsqsTotalAmt) {
        this.zhsqsTotalAmt = zhsqsTotalAmt;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public String getOperateType2() {
        return operateType2;
    }

    public void setOperateType2(String operateType2) {
        this.operateType2 = operateType2;
    }
}