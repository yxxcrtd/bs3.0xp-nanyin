
package com.joyintech.tams.report.submitexamination.investorsdetailcheck.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class InvestorsDetailCheck001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 6322534072316652791L;

    /**
     * 检查日期
     */
    private Date equityConfDate;

    /**
     *检查结果
     */
    private String checkTotal;

    /**
     *检查结果值
     */
    private String checkTotalStr;

    /**
     *产品代码
     */
    private String account;

    /**
     *产品登记编码
     */
    private String prodRegistCode;

    /**
     *产品名称
     */
    private String prodName;

    /**
     *产品运作模式
     */
    private String operateMode;

    /**
     *产品运作模式值
     */
    private String operateModeStr;

    /**
     * 是否代销
     */
    private String isConsign;

    /**
     * 是否代销值
     */
    private String isConsignStr;

    /**
     *产品成立日
     */
    private Date vdate;

    /**
     *产品实际到期日
     */
    private Date mdate;

    /**
     *检查—募集金额
     */
    private String checkMj;

    /**
     *检查—募集金额值
     */
    private String checkMjStr;

    /**
     *检查—申购金额
     */
    private String checkSgamt;

    /**
     *检查—申购金额值
     */
    private String checkSgamtStr;

//    {title:'检查—申购份额',field:'checkSgqty', sortable:false},

    /**
     * 检查—申购份额
     */
    private String checkSgqty;

    /**
     * 检查—申购份额值
     */
    private String checkSgqtyStr;

    /**
     *检查—赎回份额
     */
    private String checkShqty;

    /**
     *检查—赎回份额值
     */
    private String checkShqtyStr;

    /**
     *检查—赎回金额
     */
    private String checkShamt;

    /**
     *检查—赎回金额值
     */
    private String checkShamtStr;

    public Date getEquityConfDate() {
        return equityConfDate;
    }

    public void setEquityConfDate(Date equityConfDate) {
        this.equityConfDate = equityConfDate;
    }

    public String getCheckTotal() {
        return checkTotal;
    }

    public void setCheckTotal(String checkTotal) {
        this.checkTotal = checkTotal;
    }

    public String getCheckTotalStr() {
        return checkTotalStr;
    }

    public void setCheckTotalStr(String checkTotalStr) {
        this.checkTotalStr = checkTotalStr;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProdRegistCode() {
        return prodRegistCode;
    }

    public void setProdRegistCode(String prodRegistCode) {
        this.prodRegistCode = prodRegistCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getOperateModeStr() {
        return operateModeStr;
    }

    public void setOperateModeStr(String operateModeStr) {
        this.operateModeStr = operateModeStr;
    }

    public String getIsConsign() {
        return isConsign;
    }

    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign;
    }

    public String getIsConsignStr() {
        return isConsignStr;
    }

    public void setIsConsignStr(String isConsignStr) {
        this.isConsignStr = isConsignStr;
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

    public String getCheckMj() {
        return checkMj;
    }

    public void setCheckMj(String checkMj) {
        this.checkMj = checkMj;
    }

    public String getCheckMjStr() {
        return checkMjStr;
    }

    public void setCheckMjStr(String checkMjStr) {
        this.checkMjStr = checkMjStr;
    }

    public String getCheckSgamt() {
        return checkSgamt;
    }

    public void setCheckSgamt(String checkSgamt) {
        this.checkSgamt = checkSgamt;
    }

    public String getCheckSgamtStr() {
        return checkSgamtStr;
    }

    public void setCheckSgamtStr(String checkSgamtStr) {
        this.checkSgamtStr = checkSgamtStr;
    }

    public String getCheckSgqty() {
        return checkSgqty;
    }

    public void setCheckSgqty(String checkSgqty) {
        this.checkSgqty = checkSgqty;
    }

    public String getCheckSgqtyStr() {
        return checkSgqtyStr;
    }

    public void setCheckSgqtyStr(String checkSgqtyStr) {
        this.checkSgqtyStr = checkSgqtyStr;
    }

    public String getCheckShqty() {
        return checkShqty;
    }

    public void setCheckShqty(String checkShqty) {
        this.checkShqty = checkShqty;
    }

    public String getCheckShqtyStr() {
        return checkShqtyStr;
    }

    public void setCheckShqtyStr(String checkShqtyStr) {
        this.checkShqtyStr = checkShqtyStr;
    }

    public String getCheckShamt() {
        return checkShamt;
    }

    public void setCheckShamt(String checkShamt) {
        this.checkShamt = checkShamt;
    }

    public String getCheckShamtStr() {
        return checkShamtStr;
    }

    public void setCheckShamtStr(String checkShamtStr) {
        this.checkShamtStr = checkShamtStr;
    }
}
