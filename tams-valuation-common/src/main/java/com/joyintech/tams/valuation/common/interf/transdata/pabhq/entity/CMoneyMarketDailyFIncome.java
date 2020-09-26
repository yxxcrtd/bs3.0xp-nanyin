package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class CMoneyMarketDailyFIncome extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String sInfoWindcode;

    /**
     * 起始日期
     */
    private String fInfoBgndate;

    /**
     * 截止日期
     */
    private String fInfoEnddate;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoUnityield;

    /**
     * 累计单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fAccunitnav;

    /**
     * 万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fIncomePerMillion;

    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoYearlyroe;

    /**
     * 公告日期
     */
    private String annDate;
    
    /**
     * 更新时间
     */
    private Date opdate;

    /**
     * 更新模式
     */
    private String opmode;

    /**
     * 取得 对象ID
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind代码
     * @return Wind代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 Wind代码
     * @param sInfoWindcode Wind代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 起始日期
     * @return 起始日期
     */
    public String getfInfoBgndate() {
        return fInfoBgndate;
    }

    /**
     * 设置 起始日期
     * @param fInfoBgndate 起始日期
     */
    public void setfInfoBgndate(String fInfoBgndate) {
        this.fInfoBgndate = fInfoBgndate == null ? null : fInfoBgndate.trim();
    }

    /**
     * 取得 截止日期
     * @return 截止日期
     */
    public String getfInfoEnddate() {
        return fInfoEnddate;
    }

    /**
     * 设置 截止日期
     * @param fInfoEnddate 截止日期
     */
    public void setfInfoEnddate(String fInfoEnddate) {
        this.fInfoEnddate = fInfoEnddate == null ? null : fInfoEnddate.trim();
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getfInfoUnityield() {
        return fInfoUnityield;
    }

    /**
     * 设置 单位净值
     * @param fInfoUnityield 单位净值
     */
    public void setfInfoUnityield(BigDecimal fInfoUnityield) {
        this.fInfoUnityield = fInfoUnityield;
    }

    /**
     * 取得 累计单位净值
     * @return 累计单位净值
     */
    public BigDecimal getfAccunitnav() {
        return fAccunitnav;
    }

    /**
     * 设置 累计单位净值
     * @param fAccunitnav 累计单位净值
     */
    public void setfAccunitnav(BigDecimal fAccunitnav) {
        this.fAccunitnav = fAccunitnav;
    }

    /**
     * 取得 万份收益
     * @return 万份收益
     */
    public BigDecimal getfIncomePerMillion() {
        return fIncomePerMillion;
    }

    /**
     * 设置 万份收益
     * @param fIncomePerMillion 万份收益
     */
    public void setfIncomePerMillion(BigDecimal fIncomePerMillion) {
        this.fIncomePerMillion = fIncomePerMillion;
    }

    /**
     * 取得 七日年化收益率
     * @return 七日年化收益率
     */
    public BigDecimal getfInfoYearlyroe() {
        return fInfoYearlyroe;
    }

    /**
     * 设置 七日年化收益率
     * @param fInfoYearlyroe 七日年化收益率
     */
    public void setfInfoYearlyroe(BigDecimal fInfoYearlyroe) {
        this.fInfoYearlyroe = fInfoYearlyroe;
    }

    /**
     * 取得 公告日期
     * @return 公告日期
     */
    public String getAnnDate() {
        return annDate;
    }

    /**
     * 设置 公告日期
     * @param annDate 公告日期
     */
    public void setAnnDate(String annDate) {
        this.annDate = annDate == null ? null : annDate.trim();
    }
    
    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 更新时间
     * @param opdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 更新模式
     * @return 更新模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 更新模式
     * @param opmode 更新模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}