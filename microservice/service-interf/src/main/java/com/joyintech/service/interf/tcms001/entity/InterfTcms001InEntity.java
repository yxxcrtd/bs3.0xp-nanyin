/*
 * 文件名：Tcms001InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms001.entity;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfTcms001;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * tcms001输入-接收小资平台向投管系统传送产品成立信息
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月9日
 * @see InterfTcms001InEntity
 * @since 1.0
 */
public class InterfTcms001InEntity extends InterfTcms001 {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2598093763049213410L;

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
    @Override
    public void setShortname(String shortname) {
        super.setShortname(shortname);
    }

    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Override
    public void setProdname(String prodname) {
        super.setProdname(prodname);
    }

    @EsbName(value = "ASSETS_POOL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setAssetpool(String assetpool) {
        super.setAssetpool(assetpool);
    }

    @EsbName(value = "DEPT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    @Override
    public void setMktdeptno(String mktdeptno) {
        super.setMktdeptno(mktdeptno);
    }

    @EsbName(value = "PRODUCT_TYPE_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setProdtype1(String prodtype1) {
        super.setProdtype1(prodtype1);
    }

    @EsbName(value = "SALE_PLAT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setMktplat(String mktplat) {
        super.setMktplat(mktplat);
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
    @Override
    public void setIndistype(String indistype) {
        super.setIndistype(indistype);
    }

    @EsbName(value = "ROLL_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
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
    @Required
    @Override
    public void setRollamt(Short rollamt) {
        super.setRollamt(rollamt);
    }

    @EsbName(value = "DAYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
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
    @Override
    public void setBranch(String branch) {
        super.setBranch(branch);
    }

    @EsbName(value = "INVEST_SCOPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 500)
    @Required
    @Override
    public void setInvestscope(String investscope) {
        super.setInvestscope(investscope);
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

    @EsbName(value = "TERM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Override
    public void setTerm(Short term) {
        super.setTerm(term);
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
    @Required
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
    @Override
    public void setIntpaycycle(String intpaycycle) {
        super.setIntpaycycle(intpaycycle);
    }

    @EsbName(value = "SALE_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Override
    public void setSyield(BigDecimal syield) {
        super.setSyield(syield);
    }

    @EsbName(value = "CONSIGN_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    @Override
    public void setHyield(BigDecimal hyield) {
        super.setHyield(hyield);
    }

    @EsbName(value = "ASSETS_MANAGEMENT_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    @Override
    public void setAmyield(BigDecimal amyield) {
        super.setAmyield(amyield);
    }

    @EsbName(value = "OTHER_FEE_RATE")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 9)
    @Required
    @Override
    public void setOyield(BigDecimal oyield) {
        super.setOyield(oyield);
    }

    @EsbName(value = "PUBLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setPdate(String pdate) {
        super.setPdate(pdate);
    }

    @EsbName(value = "BEGIN_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Override
    public void setSsdate(String ssdate) {
        super.setSsdate(ssdate);
    }

    @EsbName(value = "END_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Override
    public void setSedate(String sedate) {
        super.setSedate(sedate);
    }

    @EsbName(value = "CLEAR_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setSubpaydate(String subpaydate) {
        super.setSubpaydate(subpaydate);
    }

    @EsbName(value = "ISSUE_UPPER_LIMIT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public void setUpperlimit(BigDecimal upperlimit) {
        super.setUpperlimit(upperlimit);
    }

    @EsbName(value = "ISSUE_LOWER_LIMIT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public void setLowerlimit(BigDecimal lowerlimit) {
        super.setLowerlimit(lowerlimit);
    }

    @EsbName(value = "MIN_SUBSCRIPTION_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Override
    public void setStartamt(BigDecimal startamt) {
        super.setStartamt(startamt);
    }

    @EsbName(value = "ADD_AMT")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 20, scale = 4)
    @Required
    @Override
    public void setAddamt(BigDecimal addamt) {
        super.setAddamt(addamt);
    }

    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    @Required
    @Override
    public void setRemark(String remark) {
        super.setRemark(remark);
    }

    @EsbName(value = "AGENCY_CLIENT_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 36)
    @Required
    @Override
    public void setSubmituserid(String submituserid) {
        super.setSubmituserid(submituserid);
    }

    @EsbName(value = "AGENCY_CLIENT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    @Override
    public void setSubmitusername(String submitusername) {
        super.setSubmitusername(submitusername);
    }

    @EsbName(value = "APPR_USER_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 36)
    @Required
    @Override
    public void setReviewuserid(String reviewuserid) {
        super.setReviewuserid(reviewuserid);
    }

    @EsbName(value = "APPR_TELLER_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 60)
    @Override
    public void setReviewusername(String reviewusername) {
        super.setReviewusername(reviewusername);
    }

}
