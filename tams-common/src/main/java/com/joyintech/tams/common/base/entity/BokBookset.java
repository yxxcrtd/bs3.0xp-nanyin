package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokBookset extends BaseEntity {
    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * null
     */
    private String booksetFullname;

    /**
     * 账套类别，模板、核算主体等
     */
    private String booksetType;

    /**
     * 核算方式，收益、净值、货币
     */
    private String acctMode;

    /**
     * 特殊核算模型，普通分层核算、收益分层核算，普通情况为空
     */
    private String bokSpecMode;

    /**
     * 核算主体代码，产品代码、组合代码、部门号、机构号等
     */
    private String acctSubjectId;

    /**
     * 核算主体类别，理财产品，投资组合、部门等
     */
    private String acctSubjectType;

    /**
     * 本位币
     */
    private String standardCcy;

    /**
     * 模板账套代码
     */
    private String booksetIdTmpl;

    /**
     * 会计准则
     */
    private String acctStandard;

    /**
     * 账套开始日
     */
    private Date startDate;

    /**
     * 账套结束日
     */
    private Date endDate;

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 账套名称
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName == null ? null : booksetName.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getBooksetFullname() {
        return booksetFullname;
    }

    /**
     * 设置 null
     * @param booksetFullname null
     */
    public void setBooksetFullname(String booksetFullname) {
        this.booksetFullname = booksetFullname == null ? null : booksetFullname.trim();
    }

    /**
     * 取得 账套类别，模板、核算主体等
     * @return 账套类别，模板、核算主体等
     */
    public String getBooksetType() {
        return booksetType;
    }

    /**
     * 设置 账套类别，模板、核算主体等
     * @param booksetType 账套类别，模板、核算主体等
     */
    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType == null ? null : booksetType.trim();
    }

    /**
     * 取得 核算方式，收益、净值、货币
     * @return 核算方式，收益、净值、货币
     */
    public String getAcctMode() {
        return acctMode;
    }

    /**
     * 设置 核算方式，收益、净值、货币
     * @param acctMode 核算方式，收益、净值、货币
     */
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode == null ? null : acctMode.trim();
    }

    /**
     * 取得 特殊核算模型，普通分层核算、收益分层核算，普通情况为空
     * @return 特殊核算模型，普通分层核算、收益分层核算，普通情况为空
     */
    public String getBokSpecMode() {
        return bokSpecMode;
    }

    /**
     * 设置 特殊核算模型，普通分层核算、收益分层核算，普通情况为空
     * @param bokSpecMode 特殊核算模型，普通分层核算、收益分层核算，普通情况为空
     */
    public void setBokSpecMode(String bokSpecMode) {
        this.bokSpecMode = bokSpecMode == null ? null : bokSpecMode.trim();
    }

    /**
     * 取得 核算主体代码，产品代码、组合代码、部门号、机构号等
     * @return 核算主体代码，产品代码、组合代码、部门号、机构号等
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 核算主体代码，产品代码、组合代码、部门号、机构号等
     * @param acctSubjectId 核算主体代码，产品代码、组合代码、部门号、机构号等
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId == null ? null : acctSubjectId.trim();
    }

    /**
     * 取得 核算主体类别，理财产品，投资组合、部门等
     * @return 核算主体类别，理财产品，投资组合、部门等
     */
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    /**
     * 设置 核算主体类别，理财产品，投资组合、部门等
     * @param acctSubjectType 核算主体类别，理财产品，投资组合、部门等
     */
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType == null ? null : acctSubjectType.trim();
    }

    /**
     * 取得 本位币
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * @param standardCcy 本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy == null ? null : standardCcy.trim();
    }

    /**
     * 取得 模板账套代码
     * @return 模板账套代码
     */
    public String getBooksetIdTmpl() {
        return booksetIdTmpl;
    }

    /**
     * 设置 模板账套代码
     * @param booksetIdTmpl 模板账套代码
     */
    public void setBooksetIdTmpl(String booksetIdTmpl) {
        this.booksetIdTmpl = booksetIdTmpl == null ? null : booksetIdTmpl.trim();
    }

    /**
     * 取得 会计准则
     * @return 会计准则
     */
    public String getAcctStandard() {
        return acctStandard;
    }

    /**
     * 设置 会计准则
     * @param acctStandard 会计准则
     */
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard == null ? null : acctStandard.trim();
    }

    /**
     * 取得 账套开始日
     * @return 账套开始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 账套开始日
     * @param startDate 账套开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 账套结束日
     * @return 账套结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 账套结束日
     * @param endDate 账套结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}