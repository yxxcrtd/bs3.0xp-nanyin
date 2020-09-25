package com.joyintech.interf.bankname.cfetsup.secdialogquote.dto;

import com.joyintech.interf.bankname.cfetsup.secdialogquote.entity.FxSecdialogQuote;
import com.joyintech.workflow.engine.dto.ProcessEntity;

public class FxSecdialogQuote001Dto extends FxSecdialogQuote implements ProcessEntity{

    //组合代码
    private String portfolioId;

    // 组合名称
    private String portfolioName;

    //交易场所
    private String jycs;

    //待偿期
    private Integer waitdate;

    //债项评级
    private String seccreditgrade;

    //本币本方
    private String homeid;

    //债券币种
    private String bondccy;

    //组合名称
    private String accountname;

    //债券简称
    private String secname;

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

    public String getJycs() {
        return jycs;
    }

    public void setJycs(String jycs) {
        this.jycs = jycs;
    }

    public Integer getWaitdate() {
        return waitdate;
    }

    public void setWaitdate(Integer waitdate) {
        this.waitdate = waitdate;
    }

    public String getSeccreditgrade() {
        return seccreditgrade;
    }

    public void setSeccreditgrade(String seccreditgrade) {
        this.seccreditgrade = seccreditgrade;
    }

    public String getHomeid() {
        return homeid;
    }

    public void setHomeid(String homeid) {
        this.homeid = homeid;
    }

    public String getBondccy() {
        return bondccy;
    }

    public void setBondccy(String bondccy) {
        this.bondccy = bondccy;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
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


}
