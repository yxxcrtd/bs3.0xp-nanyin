package com.joyintech.tams.valuation.interf.valuation.send.dto;

import com.joyintech.framework.base.BaseEntity;

public class ESB448103Dto extends BaseEntity {
	 
	 /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
	 
    /**
     * 中文简称
     */
    private String shortname;
    /**
     * 净值
     */
    private String netvalue;
    /**
     * 净值日期
     */
    private String navdate;
    /**
     * 生效日
     */
    private String usedate;
    /**
     * 累计净值
     */
    private String totalnetvalue;
    /**
     * 年化收益率
     */
    private String yearrate;
    /**
     * 7日年化收益率
     */
    private String sevenyearrate;
    /**
     * 万份收益
     */
    private String fundincome;
    /**
     * 销售费率
     */
    private String salerate;
    /**
     * 产品类型
     */
    private String prodtype;
    /**
     * 到期收益率
     */
    private String dueyield;
    /**
     * 实际到期日
     */
    private String duedate;
    /**
     * 最低收益率
     */
    private String lowestyield;
    /**
     * 最高收益率
     */
    private String highestyield;
    /**
     * 月度收益率
     */
    private String monthyield;
    /**
     * 季度收益率
     */
    private String quarteryield;
    /**
     * 半年度收益率
     */
    private String semiannualyield;
    /**
     * 日涨幅
     */
    private String dailyincrease;
    /**
     * 年化日涨幅
     */
    private String dailyyearincrease;
    /**
     * 周涨幅
     */
    private String weeklyincrease;
    /**
     * 年化周涨幅
     */
    private String weeklyyearincrease;
    /**
     * 月涨幅
     */
    private String monthlyincrease;
    /**
     * 年化月涨幅
     */
    private String monthlyyearincrease;
    /**
     * 季涨幅
     */
    private String quarterincrease;
    /**
     * 年化季涨幅
     */
    private String quarteryearincrease;
    /**
     * 半年涨幅
     */
    private String semiannualincrease;
    /**
     * 年化半年涨幅
     */
    private String semiannualyearincrease;
    /**
     * 年涨幅
     */
    private String annualincrease;
    /**
     * 年化年涨幅
     */
    private String annualyearincrease;
    /**
     * 成立以来涨跌幅
     */
    private String sinceestablishincrease;
    /**
     * 历史收益率
     */
    private String hisrate;
    /**
     * 单位分红金额
     */
    private String divamt;
    /**
     * 权益登记日
     */
    private String registerdate;
    /**
     * 红利下发日
     */
    private String dividenddate;

    /**
     *  取得 中文简称
     *  @return 中文简称
     */
    public String getShortname() {
       return shortname;
    }

    /**
     *  设置 中文简称
     *  @param shortname 中文简称
     */
    public void setShortname( String shortname)  {
       this.shortname = shortname;
    }

    /**
     *  取得 净值
     *  @return 净值
     */
    public String getNetvalue() {
       return netvalue;
    }

    /**
     *  设置 净值
     *  @param netvalue 净值
     */
    public void setNetvalue( String netvalue)  {
       this.netvalue = netvalue;
    }

    /**
     *  取得 净值日期
     *  @return 净值日期
     */
    public String getNavdate() {
       return navdate;
    }

    /**
     *  设置 净值日期
     *  @param navdate 净值日期
     */
    public void setNavdate( String navdate)  {
       this.navdate = navdate;
    }

    /**
     *  取得 生效日
     *  @return 生效日
     */
    public String getUsedate() {
       return usedate;
    }

    /**
     *  设置 生效日
     *  @param usedate 生效日
     */
    public void setUsedate( String usedate)  {
       this.usedate = usedate;
    }

    /**
     *  取得 累计净值
     *  @return 累计净值
     */
    public String getTotalnetvalue() {
       return totalnetvalue;
    }

    /**
     *  设置 累计净值
     *  @param totalnetvalue 累计净值
     */
    public void setTotalnetvalue( String totalnetvalue)  {
       this.totalnetvalue = totalnetvalue;
    }

    /**
     *  取得 年化收益率
     *  @return 年化收益率
     */
    public String getYearrate() {
       return yearrate;
    }

    /**
     *  设置 年化收益率
     *  @param yearrate 年化收益率
     */
    public void setYearrate( String yearrate)  {
       this.yearrate = yearrate;
    }

    /**
     *  取得 7日年化收益率
     *  @return 7日年化收益率
     */
    public String getSevenyearrate() {
       return sevenyearrate;
    }

    /**
     *  设置 7日年化收益率
     *  @param sevenyearrate 7日年化收益率
     */
    public void setSevenyearrate( String sevenyearrate)  {
       this.sevenyearrate = sevenyearrate;
    }

    /**
     *  取得 万份收益
     *  @return 万份收益
     */
    public String getFundincome() {
       return fundincome;
    }

    /**
     *  设置 万份收益
     *  @param fundincome 万份收益
     */
    public void setFundincome( String fundincome)  {
       this.fundincome = fundincome;
    }

    /**
     *  取得 销售费率
     *  @return 销售费率
     */
    public String getSalerate() {
       return salerate;
    }

    /**
     *  设置 销售费率
     *  @param salerate 销售费率
     */
    public void setSalerate( String salerate)  {
       this.salerate = salerate;
    }

    /**
     *  取得 产品类型
     *  @return 产品类型
     */
    public String getProdtype() {
       return prodtype;
    }

    /**
     *  设置 产品类型
     *  @param prodtype 产品类型
     */
    public void setProdtype( String prodtype)  {
       this.prodtype = prodtype;
    }

    /**
     *  取得 到期收益率
     *  @return 到期收益率
     */
    public String getDueyield() {
       return dueyield;
    }

    /**
     *  设置 到期收益率
     *  @param dueyield 到期收益率
     */
    public void setDueyield( String dueyield)  {
       this.dueyield = dueyield;
    }

    /**
     *  取得 实际到期日
     *  @return 实际到期日
     */
    public String getDuedate() {
       return duedate;
    }

    /**
     *  设置 实际到期日
     *  @param duedate 实际到期日
     */
    public void setDuedate( String duedate)  {
       this.duedate = duedate;
    }

    /**
     *  取得 最低收益率
     *  @return 最低收益率
     */
    public String getLowestyield() {
       return lowestyield;
    }

    /**
     *  设置 最低收益率
     *  @param lowestyield 最低收益率
     */
    public void setLowestyield( String lowestyield)  {
       this.lowestyield = lowestyield;
    }

    /**
     *  取得 最高收益率
     *  @return 最高收益率
     */
    public String getHighestyield() {
       return highestyield;
    }

    /**
     *  设置 最高收益率
     *  @param highestyield 最高收益率
     */
    public void setHighestyield( String highestyield)  {
       this.highestyield = highestyield;
    }

    /**
     *  取得 月度收益率
     *  @return 月度收益率
     */
    public String getMonthyield() {
       return monthyield;
    }

    /**
     *  设置 月度收益率
     *  @param monthyield 月度收益率
     */
    public void setMonthyield( String monthyield)  {
       this.monthyield = monthyield;
    }

    /**
     *  取得 季度收益率
     *  @return 季度收益率
     */
    public String getQuarteryield() {
       return quarteryield;
    }

    /**
     *  设置 季度收益率
     *  @param quarteryield 季度收益率
     */
    public void setQuarteryield( String quarteryield)  {
       this.quarteryield = quarteryield;
    }

    /**
     *  取得 半年度收益率
     *  @return 半年度收益率
     */
    public String getSemiannualyield() {
       return semiannualyield;
    }

    /**
     *  设置 半年度收益率
     *  @param semiannualyield 半年度收益率
     */
    public void setSemiannualyield( String semiannualyield)  {
       this.semiannualyield = semiannualyield;
    }

    /**
     *  取得 日涨幅
     *  @return 日涨幅
     */
    public String getDailyincrease() {
       return dailyincrease;
    }

    /**
     *  设置 日涨幅
     *  @param dailyincrease 日涨幅
     */
    public void setDailyincrease( String dailyincrease)  {
       this.dailyincrease = dailyincrease;
    }

    /**
     *  取得 年化日涨幅
     *  @return 年化日涨幅
     */
    public String getDailyyearincrease() {
       return dailyyearincrease;
    }

    /**
     *  设置 年化日涨幅
     *  @param dailyyearincrease 年化日涨幅
     */
    public void setDailyyearincrease( String dailyyearincrease)  {
       this.dailyyearincrease = dailyyearincrease;
    }

    /**
     *  取得 周涨幅
     *  @return 周涨幅
     */
    public String getWeeklyincrease() {
       return weeklyincrease;
    }

    /**
     *  设置 周涨幅
     *  @param weeklyincrease 周涨幅
     */
    public void setWeeklyincrease( String weeklyincrease)  {
       this.weeklyincrease = weeklyincrease;
    }

    /**
     *  取得 年化周涨幅
     *  @return 年化周涨幅
     */
    public String getWeeklyyearincrease() {
       return weeklyyearincrease;
    }

    /**
     *  设置 年化周涨幅
     *  @param weeklyyearincrease 年化周涨幅
     */
    public void setWeeklyyearincrease( String weeklyyearincrease)  {
       this.weeklyyearincrease = weeklyyearincrease;
    }

    /**
     *  取得 月涨幅
     *  @return 月涨幅
     */
    public String getMonthlyincrease() {
       return monthlyincrease;
    }

    /**
     *  设置 月涨幅
     *  @param monthlyincrease 月涨幅
     */
    public void setMonthlyincrease( String monthlyincrease)  {
       this.monthlyincrease = monthlyincrease;
    }

    /**
     *  取得 年化月涨幅
     *  @return 年化月涨幅
     */
    public String getMonthlyyearincrease() {
       return monthlyyearincrease;
    }

    /**
     *  设置 年化月涨幅
     *  @param monthlyyearincrease 年化月涨幅
     */
    public void setMonthlyyearincrease( String monthlyyearincrease)  {
       this.monthlyyearincrease = monthlyyearincrease;
    }

    /**
     *  取得 季涨幅
     *  @return 季涨幅
     */
    public String getQuarterincrease() {
       return quarterincrease;
    }

    /**
     *  设置 季涨幅
     *  @param quarterincrease 季涨幅
     */
    public void setQuarterincrease( String quarterincrease)  {
       this.quarterincrease = quarterincrease;
    }

    /**
     *  取得 年化季涨幅
     *  @return 年化季涨幅
     */
    public String getQuarteryearincrease() {
       return quarteryearincrease;
    }

    /**
     *  设置 年化季涨幅
     *  @param quarteryearincrease 年化季涨幅
     */
    public void setQuarteryearincrease( String quarteryearincrease)  {
       this.quarteryearincrease = quarteryearincrease;
    }

    /**
     *  取得 半年涨幅
     *  @return 半年涨幅
     */
    public String getSemiannualincrease() {
       return semiannualincrease;
    }

    /**
     *  设置 半年涨幅
     *  @param semiannualincrease 半年涨幅
     */
    public void setSemiannualincrease( String semiannualincrease)  {
       this.semiannualincrease = semiannualincrease;
    }

    /**
     *  取得 年化半年涨幅
     *  @return 年化半年涨幅
     */
    public String getSemiannualyearincrease() {
       return semiannualyearincrease;
    }

    /**
     *  设置 年化半年涨幅
     *  @param semiannualyearincrease 年化半年涨幅
     */
    public void setSemiannualyearincrease( String semiannualyearincrease)  {
       this.semiannualyearincrease = semiannualyearincrease;
    }

    /**
     *  取得 年涨幅
     *  @return 年涨幅
     */
    public String getAnnualincrease() {
       return annualincrease;
    }

    /**
     *  设置 年涨幅
     *  @param annualincrease 年涨幅
     */
    public void setAnnualincrease( String annualincrease)  {
       this.annualincrease = annualincrease;
    }

    /**
     *  取得 年化年涨幅
     *  @return 年化年涨幅
     */
    public String getAnnualyearincrease() {
       return annualyearincrease;
    }

    /**
     *  设置 年化年涨幅
     *  @param annualyearincrease 年化年涨幅
     */
    public void setAnnualyearincrease( String annualyearincrease)  {
       this.annualyearincrease = annualyearincrease;
    }

    /**
     *  取得 成立以来涨跌幅
     *  @return 成立以来涨跌幅
     */
    public String getSinceestablishincrease() {
       return sinceestablishincrease;
    }

    /**
     *  设置 成立以来涨跌幅
     *  @param sinceestablishincrease 成立以来涨跌幅
     */
    public void setSinceestablishincrease( String sinceestablishincrease)  {
       this.sinceestablishincrease = sinceestablishincrease;
    }

    /**
     *  取得 历史收益率
     *  @return 历史收益率
     */
    public String getHisrate() {
       return hisrate;
    }

    /**
     *  设置 历史收益率
     *  @param hisrate 历史收益率
     */
    public void setHisrate( String hisrate)  {
       this.hisrate = hisrate;
    }

    /**
     *  取得 单位分红金额
     *  @return 单位分红金额
     */
    public String getDivamt() {
       return divamt;
    }

    /**
     *  设置 单位分红金额
     *  @param divamt 单位分红金额
     */
    public void setDivamt( String divamt)  {
       this.divamt = divamt;
    }

    /**
     *  取得 权益登记日
     *  @return 权益登记日
     */
    public String getRegisterdate() {
       return registerdate;
    }

    /**
     *  设置 权益登记日
     *  @param registerdate 权益登记日
     */
    public void setRegisterdate( String registerdate)  {
       this.registerdate = registerdate;
    }

    /**
     *  取得 红利下发日
     *  @return 红利下发日
     */
    public String getDividenddate() {
       return dividenddate;
    }

    /**
     *  设置 红利下发日
     *  @param dividenddate 红利下发日
     */
    public void setDividenddate( String dividenddate)  {
       this.dividenddate = dividenddate;
    }

    
    
    
	 
}
