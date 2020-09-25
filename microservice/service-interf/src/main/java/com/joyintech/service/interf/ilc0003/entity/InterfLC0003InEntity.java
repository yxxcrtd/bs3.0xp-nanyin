/*
 * 文件名：InterfLC0003InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0003.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfTcms001;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 接收行E通输入的产品信息
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月16日
 * @see InterfLC0003InEntity
 * @since 1.0
 */
public class InterfLC0003InEntity extends InterfTcms001 {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -4328759414178572328L;

    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setProdid(String prodid) {
        super.setProdid(prodid);
    }

    @EsbName(value = "PRODUCT_SHORTNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    @Override
    public void setShortname(String shortname) {
        super.setShortname(shortname);
    }

    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    @Override
    public void setProdname(String prodname) {
        super.setProdname(prodname);
    }

    @EsbName(value = "PRODUCT_BUSS_MODEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setProdmode(String prodmode) {
        super.setProdmode(prodmode);
    }

    @EsbName(value = "ASSIGN_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setIndistype(String indistype) {
        super.setIndistype(indistype);
    }

    @EsbName(value = "ROLL_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setRollmode(String rollmode) {
        super.setRollmode(rollmode);
    }

    @EsbName(value = "MANAGEMENT_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setIncomemode(String incomemode) {
        super.setIncomemode(incomemode);
    }

    @EsbName(value = "ROLL_TERM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Override
    public void setRollamt(Short rollamt) {
        super.setRollamt(rollamt);
    }

    @EsbName(value = "SALE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setSalemode(String salemode) {
        super.setSalemode(salemode);
    }

    @EsbName(value = "PRODUCT_CODE1")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Override
    public void setProdid1(String prodid1) {
        super.setProdid1(prodid1);
    }

    @EsbName(value = "DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Override
    public void setSyperiod(Short syperiod) {
        super.setSyperiod(syperiod);
    }

    @EsbName(value = "ISSUE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 5)
    @Required
    @Override
    public void setIssuetype(String indistype) {
        super.setIndistype(indistype);
    }

    @EsbName(value = "CLIENT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Override
    public void setCusname(String cusname) {
        super.setCusname(cusname);
    }

    @EsbName(value = "ACCT_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setBranch(String branch) {
        super.setBranch(branch);
    }

    @EsbName(value = "ESTABLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setVdate(String vdate) {
        super.setVdate(vdate);
    }

    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setMdate(String mdate) {
        super.setMdate(mdate);
    }

    @EsbName(value = "INTEREST_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public void setBasis(String basis) {
        super.setBasis(basis);
    }

    @EsbName(value = "YIELD")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 7)
    @Override
    public void setYield(BigDecimal yield) {
        super.setYield(yield);
    }

    @EsbName(value = "SCHEME_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setSolutionid(String solutionid) {
        super.setSolutionid(solutionid);
    }

    @EsbName(value = "SUBSCRIPTION_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    @Override
    public void setSubccy(String subccy) {
        super.setSubccy(subccy);
    }

    @EsbName(value = "INCOME_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    @Override
    public void setSettccy(String settccy) {
        super.setSettccy(settccy);
    }

    @EsbName(value = "INTEREST_PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    @Override
    public void setIntpaycycle(String intpaycycle) {
        super.setIntpaycycle(intpaycycle);
    }

    @EsbName(value = "BEGIN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setSsdate(String ssdate) {
        super.setSsdate(ssdate);
    }

    @EsbName(value = "END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setSedate(String sedate) {
        super.setSedate(sedate);
    }

    @EsbName(value = "ISSUE_UPPER_LIMIT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 24, scale = 4)
    @Override
    public void setUpperlimit(BigDecimal upperlimit) {
        super.setUpperlimit(upperlimit);
    }

    @EsbName(value = "ISSUE_LOWER_LIMIT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 24, scale = 4)
    @Override
    public void setLowerlimit(BigDecimal lowerlimit) {
        super.setLowerlimit(lowerlimit);
    }

    @EsbName(value = "MIN_SUBSCRIPTION_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 24, scale = 4)
    @Override
    public void setStartamt(BigDecimal startamt) {
        super.setStartamt(startamt);
    }

    @EsbName(value = "ADD_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 24, scale = 4)
    @Override
    public void setAddamt(BigDecimal addamt) {
        super.setAddamt(addamt);
    }

    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    @Override
    public void setRemark(String remark) {
        super.setRemark(remark);
    }

}
