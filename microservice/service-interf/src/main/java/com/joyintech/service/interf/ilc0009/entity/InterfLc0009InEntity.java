/*
 * 文件名：InterfLc0009Inentity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0009.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfLc0009;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 产品提前赎回受理经办Lc0009输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月9日
 * @see InterfLc0009InEntity
 * @since 1.0
 */
public class InterfLc0009InEntity extends InterfLc0009 {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 5040428294043810474L;

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdid(String prodid) {
        super.setProdid(prodid);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_SHORTNAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setShortname(String shortname) {
        super.setShortname(shortname);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdname(String prodname) {
        super.setProdname(prodname);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_DEAL_MODEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProdmode(String prodmode) {
        super.setProdmode(prodmode);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REDEEM_ACCEPT_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRedemode(String redemode) {
        super.setRedemode(redemode);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "MANAGEMENT_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setIncomemode(String incomemode) {
        super.setIncomemode(incomemode);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "SALE_PRODUCT_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setProxysaleprodid(String proxysaleprodid) {
        super.setProxysaleprodid(proxysaleprodid);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBranch(String branch) {
        super.setBranch(branch);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "ESTABLISH_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setVdate(String vdate) {
        super.setVdate(vdate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_MATURE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setMdate(String mdate) {
        super.setMdate(mdate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "PRODUCT_REDEEM_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRdate(String rdate) {
        super.setRdate(rdate);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "INTEREST_BASE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBasis(String basis) {
        super.setBasis(basis);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CLIENT_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setAccountid(String accountid) {
        super.setAccountid(accountid);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REDEEM_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRedeamt(BigDecimal redeamt) {
        super.setRedeamt(redeamt);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REDEEM_YIELD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setYield(BigDecimal yield) {
        super.setYield(yield);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REDEEM_INCOME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setReinterest(BigDecimal reinterest) {
        super.setReinterest(reinterest);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setSubccy(String subccy) {
        super.setSubccy(subccy);
    }

    /**
     * {@inheritDoc}
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setRemark(String remark) {
        super.setRemark(remark);
    }

}
