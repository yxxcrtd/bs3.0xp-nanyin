package com.joyintech.tams.common.base.entity;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class FinCashPlanEntity {

    /**
     * 现金流一级类型
     */
    private String cashTypeOne;
    /**
     *现金流二级类型
     */
    private String cashTypeTwo;
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流序号，从1开始
     */
    private Long cashNum;

    /**
     * 现金流类型，中间还本、中间收息、到期还本、到期收息、费用支付等
     */
    private String cashType;

    /**
     * 未调整的计算开始日
     */
    private Date vdateUnadjust;

    /**
     * 未调整的计算结束日
     */
    private Date mdateUnadjust;

    /**
     * 未调整的计划支付日
     */
    private Date payDateUnadjust;

    /**
     * 调整后的计算开始日
     */
    private Date vdate;

    /**
     * 调整后的计算结束日
     */
    private Date mdate;

    /**
     * 调整后的计划支付日
     */
    private Date payDate;

    /**
     * 计算周期天数
     */
    private Long termdays;

    /**
     * 理论计息年度开始日，A/A_BOND适用
     */
    private Date vdateY;

    /**
     * 完整周期开始日
     */
    private Date vdateTerm;

    /**
     * 完整周期结束日
     */
    private Date mdateTerm;

    /**
     * 完整周期天数
     */
    private Long termdaysTerm;

    /**
     * 计划支付本金，针对现金流类型为本金的
     */

    private BigDecimal prinAmt;

    /**
     * 计息本金，存计划支付利息对应的本金
     */

    private BigDecimal intPrinAmt;

    /**
     * 计划支付利息，还本计息区间对应的利息
     */

    private BigDecimal intAmt;

    /**
     * 是否支付利息，针对现金流类型为本金的
     */
    private String isPayInt;

    /**
     * 计划支付现金流，如果支付利息：计划支付本金+计划支付利息。如果不支付利息：计划支付本金
     */

    private BigDecimal cashAmt;

    /**
     * 现金流基数，存100或者总金额的具体数值
     */

    private BigDecimal cashBaseamt;

    /**
     * 现金流单位，单位、百元、 总金额
     */
    private String cashUnitType;

    /**
     * 计息算法，分摊法、累计法，目前无
     */
    private String calcFunction;

    /**
     * 年付息次数
     */
    private Long frequency;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 支付方式，现金、红利再投等
     */
    private String payType;

    /**
     * 区间净收益率
     */

    private BigDecimal rangeYield;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String create_user;
    /**
     * 创建时间
     */
    private Timestamp create_time;
    /**
     * 创建部门
     */
    private String create_dept;
    /**
     * 更新人
     */
    private String update_user;
    /**
     * 更新时间
     */
    private Timestamp update_time;

    public String getCashTypeOne() {
        return cashTypeOne;
    }

    public void setCashTypeOne(String cashTypeOne) {
        this.cashTypeOne = cashTypeOne;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    public String getCashTypeTwo() {
        return cashTypeTwo;
    }

    public void setCashTypeTwo(String cashTypeTwo) {
        this.cashTypeTwo = cashTypeTwo;
    }

    public Long getCashNum() {
        return cashNum;
    }

    public void setCashNum(Long cashNum) {
        this.cashNum = cashNum;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public Date getVdateUnadjust() {
        return vdateUnadjust;
    }

    public void setVdateUnadjust(Date vdateUnadjust) {
        this.vdateUnadjust = vdateUnadjust;
    }

    public Date getMdateUnadjust() {
        return mdateUnadjust;
    }

    public void setMdateUnadjust(Date mdateUnadjust) {
        this.mdateUnadjust = mdateUnadjust;
    }

    public Date getPayDateUnadjust() {
        return payDateUnadjust;
    }

    public void setPayDateUnadjust(Date payDateUnadjust) {
        this.payDateUnadjust = payDateUnadjust;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Long getTermdays() {
        return termdays;
    }

    public void setTermdays(Long termdays) {
        this.termdays = termdays;
    }

    public Date getVdateY() {
        return vdateY;
    }

    public void setVdateY(Date vdateY) {
        this.vdateY = vdateY;
    }

    public Date getVdateTerm() {
        return vdateTerm;
    }

    public void setVdateTerm(Date vdateTerm) {
        this.vdateTerm = vdateTerm;
    }

    public Date getMdateTerm() {
        return mdateTerm;
    }

    public void setMdateTerm(Date mdateTerm) {
        this.mdateTerm = mdateTerm;
    }

    public Long getTermdaysTerm() {
        return termdaysTerm;
    }

    public void setTermdaysTerm(Long termdaysTerm) {
        this.termdaysTerm = termdaysTerm;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getIntPrinAmt() {
        return intPrinAmt;
    }

    public void setIntPrinAmt(BigDecimal intPrinAmt) {
        this.intPrinAmt = intPrinAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public String getIsPayInt() {
        return isPayInt;
    }

    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt;
    }

    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    public BigDecimal getCashBaseamt() {
        return cashBaseamt;
    }

    public void setCashBaseamt(BigDecimal cashBaseamt) {
        this.cashBaseamt = cashBaseamt;
    }

    public String getCashUnitType() {
        return cashUnitType;
    }

    public void setCashUnitType(String cashUnitType) {
        this.cashUnitType = cashUnitType;
    }

    public String getCalcFunction() {
        return calcFunction;
    }

    public void setCalcFunction(String calcFunction) {
        this.calcFunction = calcFunction;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getRangeYield() {
        return rangeYield;
    }

    public void setRangeYield(BigDecimal rangeYield) {
        this.rangeYield = rangeYield;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getCreate_dept() {
        return create_dept;
    }

    public void setCreate_dept(String create_dept) {
        this.create_dept = create_dept;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}
