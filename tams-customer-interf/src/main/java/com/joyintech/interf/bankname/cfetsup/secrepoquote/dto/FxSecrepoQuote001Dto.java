package com.joyintech.interf.bankname.cfetsup.secrepoquote.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.entity.FxSecrepoQuote;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 回购对话报价dto
 *
 * @author zcf
* @version 1.0
 * @date 2020-01-08
 */
@PageId("FxSecrepoQuote001")
public class FxSecrepoQuote001Dto extends FxSecrepoQuote implements ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;

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
}