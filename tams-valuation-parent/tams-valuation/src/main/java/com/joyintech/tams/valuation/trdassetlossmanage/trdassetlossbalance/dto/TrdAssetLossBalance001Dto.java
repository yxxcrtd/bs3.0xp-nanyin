package com.joyintech.tams.valuation.trdassetlossmanage.trdassetlossbalance.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 统计分析明细表（账务明细）
 */
public class TrdAssetLossBalance001Dto extends BaseEntity {

    private String booksetId;   //账套代码
    private Date happenDate;   //发生日期
    private Date bookDate;   //入账日期
    private String bookDateStr;
    private String bokDetailId;   //账务明细代码
    private BigDecimal bookSummaryOrder;   //处理序列
    private Date booksetDate;   //账套日期
    private BigDecimal intRate;   //计提利率
    private BigDecimal dailydscYield;   //日摊销收益率
    private BigDecimal tdyIntincexpAdd;   //当日发生应计利息
    private BigDecimal tdyIntincexp;   //当日累计应计利息余额
    private String finprodType;   //金融产品类型
    private Date vdate;   //起息日
    private Date mdate;   //到期日
    private String chlId;   //通道代码
    private String invAim;   //投资目的
    private BigDecimal tdyPosition;   //当日计提份额
    private BigDecimal tdyCostAmt;   //当日成本
    private BigDecimal tdyFloatIngpl;   //当日公允价值变动
    private BigDecimal endDays1;   //剩余期限
    private BigDecimal endDays2;   //剩余存续期限
    private BigDecimal endDaysCostAmt;   //剩余期限用成本
    private String busiId;   //业务明细代码
    private Date valDate;   //行情日期
    private BigDecimal tdyFloatIngplExp;   //当日累计公允价值变动
    private String ccy;   //币种
    private String bCcy;   //本位币
    private BigDecimal tdyIntincexpAddB;   //当日发生应计利息_本位币
    private BigDecimal tdyCostAmtB;   //当日成本_本位币
    private BigDecimal tdyFloatIngplB;   //当日公允价值变动_本位币
    private BigDecimal tdyFloatIngplExpB;   //当日累计公允价值变动_本位币
    private BigDecimal tdyIntincexpB;   //当日累计应计利息余额_本位币
    private BigDecimal posAmt;   //当前持仓
    private BigDecimal tdyIntadjexpAdd;   //当日发生利息调整
    private BigDecimal tdyIntadjexp;   //当日累计利息调整余额
    private BigDecimal tdyIntadjexpAddB;   //当日发生利息调整_本位币
    private BigDecimal tdyIntadjexpB;   //当日累计利息调整余额_本位币
    private BigDecimal tdyLossAmt;   //减值余额
    private BigDecimal tdyLossAmtB;   //减值余额_本位币
    private BigDecimal tdyCostReceAmt;   //当日本金应收款余额
    private BigDecimal tdyCostReceAmtB;   //当日本金应收款余额_本位币
    private BigDecimal tdyIntReceAmt;   //当日利息应收款余额
    private BigDecimal tdyIntReceAmtB;   //当日利息应收款余额_本位币
    private BigDecimal tdyBeOverIntAmt;   //当日逾期利息余额
    private BigDecimal tdyBeOverIntAmtB;   //当日逾期利息余额_本位币

    private String finprodAbbr;     // 资产名称
    private String booksetName;     // 账套名称
    private BigDecimal costAmt;     // 账面价值

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public BigDecimal getCostAmt() {
        return costAmt;
    }

    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }

    public String getBookDateStr() {
        return bookDateStr;
    }

    public void setBookDateStr(String bookDateStr) {
        this.bookDateStr = bookDateStr;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public BigDecimal getBookSummaryOrder() {
        return bookSummaryOrder;
    }

    public void setBookSummaryOrder(BigDecimal bookSummaryOrder) {
        this.bookSummaryOrder = bookSummaryOrder;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public BigDecimal getDailydscYield() {
        return dailydscYield;
    }

    public void setDailydscYield(BigDecimal dailydscYield) {
        this.dailydscYield = dailydscYield;
    }

    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
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

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public BigDecimal getTdyPosition() {
        return tdyPosition;
    }

    public void setTdyPosition(BigDecimal tdyPosition) {
        this.tdyPosition = tdyPosition;
    }

    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }

    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }

    public BigDecimal getEndDays1() {
        return endDays1;
    }

    public void setEndDays1(BigDecimal endDays1) {
        this.endDays1 = endDays1;
    }

    public BigDecimal getEndDays2() {
        return endDays2;
    }

    public void setEndDays2(BigDecimal endDays2) {
        this.endDays2 = endDays2;
    }

    public BigDecimal getEndDaysCostAmt() {
        return endDaysCostAmt;
    }

    public void setEndDaysCostAmt(BigDecimal endDaysCostAmt) {
        this.endDaysCostAmt = endDaysCostAmt;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    public BigDecimal getTdyFloatIngplExp() {
        return tdyFloatIngplExp;
    }

    public void setTdyFloatIngplExp(BigDecimal tdyFloatIngplExp) {
        this.tdyFloatIngplExp = tdyFloatIngplExp;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getbCcy() {
        return bCcy;
    }

    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    public BigDecimal getTdyIntincexpAddB() {
        return tdyIntincexpAddB;
    }

    public void setTdyIntincexpAddB(BigDecimal tdyIntincexpAddB) {
        this.tdyIntincexpAddB = tdyIntincexpAddB;
    }

    public BigDecimal getTdyCostAmtB() {
        return tdyCostAmtB;
    }

    public void setTdyCostAmtB(BigDecimal tdyCostAmtB) {
        this.tdyCostAmtB = tdyCostAmtB;
    }

    public BigDecimal getTdyFloatIngplB() {
        return tdyFloatIngplB;
    }

    public void setTdyFloatIngplB(BigDecimal tdyFloatIngplB) {
        this.tdyFloatIngplB = tdyFloatIngplB;
    }

    public BigDecimal getTdyFloatIngplExpB() {
        return tdyFloatIngplExpB;
    }

    public void setTdyFloatIngplExpB(BigDecimal tdyFloatIngplExpB) {
        this.tdyFloatIngplExpB = tdyFloatIngplExpB;
    }

    public BigDecimal getTdyIntincexpB() {
        return tdyIntincexpB;
    }

    public void setTdyIntincexpB(BigDecimal tdyIntincexpB) {
        this.tdyIntincexpB = tdyIntincexpB;
    }

    public BigDecimal getPosAmt() {
        return posAmt;
    }

    public void setPosAmt(BigDecimal posAmt) {
        this.posAmt = posAmt;
    }

    public BigDecimal getTdyIntadjexpAdd() {
        return tdyIntadjexpAdd;
    }

    public void setTdyIntadjexpAdd(BigDecimal tdyIntadjexpAdd) {
        this.tdyIntadjexpAdd = tdyIntadjexpAdd;
    }

    public BigDecimal getTdyIntadjexp() {
        return tdyIntadjexp;
    }

    public void setTdyIntadjexp(BigDecimal tdyIntadjexp) {
        this.tdyIntadjexp = tdyIntadjexp;
    }

    public BigDecimal getTdyIntadjexpAddB() {
        return tdyIntadjexpAddB;
    }

    public void setTdyIntadjexpAddB(BigDecimal tdyIntadjexpAddB) {
        this.tdyIntadjexpAddB = tdyIntadjexpAddB;
    }

    public BigDecimal getTdyIntadjexpB() {
        return tdyIntadjexpB;
    }

    public void setTdyIntadjexpB(BigDecimal tdyIntadjexpB) {
        this.tdyIntadjexpB = tdyIntadjexpB;
    }

    public BigDecimal getTdyLossAmt() {
        return tdyLossAmt;
    }

    public void setTdyLossAmt(BigDecimal tdyLossAmt) {
        this.tdyLossAmt = tdyLossAmt;
    }

    public BigDecimal getTdyLossAmtB() {
        return tdyLossAmtB;
    }

    public void setTdyLossAmtB(BigDecimal tdyLossAmtB) {
        this.tdyLossAmtB = tdyLossAmtB;
    }

    public BigDecimal getTdyCostReceAmt() {
        return tdyCostReceAmt;
    }

    public void setTdyCostReceAmt(BigDecimal tdyCostReceAmt) {
        this.tdyCostReceAmt = tdyCostReceAmt;
    }

    public BigDecimal getTdyCostReceAmtB() {
        return tdyCostReceAmtB;
    }

    public void setTdyCostReceAmtB(BigDecimal tdyCostReceAmtB) {
        this.tdyCostReceAmtB = tdyCostReceAmtB;
    }

    public BigDecimal getTdyIntReceAmt() {
        return tdyIntReceAmt;
    }

    public void setTdyIntReceAmt(BigDecimal tdyIntReceAmt) {
        this.tdyIntReceAmt = tdyIntReceAmt;
    }

    public BigDecimal getTdyIntReceAmtB() {
        return tdyIntReceAmtB;
    }

    public void setTdyIntReceAmtB(BigDecimal tdyIntReceAmtB) {
        this.tdyIntReceAmtB = tdyIntReceAmtB;
    }

    public BigDecimal getTdyBeOverIntAmt() {
        return tdyBeOverIntAmt;
    }

    public void setTdyBeOverIntAmt(BigDecimal tdyBeOverIntAmt) {
        this.tdyBeOverIntAmt = tdyBeOverIntAmt;
    }

    public BigDecimal getTdyBeOverIntAmtB() {
        return tdyBeOverIntAmtB;
    }

    public void setTdyBeOverIntAmtB(BigDecimal tdyBeOverIntAmtB) {
        this.tdyBeOverIntAmtB = tdyBeOverIntAmtB;
    }
}
