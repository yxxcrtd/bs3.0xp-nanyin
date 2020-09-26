/*
 * 文件名：ProdShareCount001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年6月29日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.queryanalysis.prodsharecount.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;

/**
 * 产品份额统计dto
 *
 * @author zuoyinyi
 * @version 1.0 2018年6月29日
 * @see ProdShareCount001Dto
 * @since 1.0
 */
public class ProdShareCount001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2428743251071121099L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 产品登记编码
     */
    private String prdRegistCode;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品份额
     */
    private BigDecimal qty;
    /**
     * 产品总份额
     */
    private  BigDecimal sumQty;
    /**
     * 客户总数
     */
    private BigDecimal sumCus;
    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 持有日期Str
     */
    private String holdDateStr;

    /**
     * 上一日产品份额
     */
    private BigDecimal lastQty;

    /**
     * 产品份额变动
     */
    private BigDecimal changeQty;

    /**
     * 获取 prodId
     *
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 prodId
     *
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取 ccy
     *
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 prdRegistCode
     *
     * @return prdRegistCode.
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 prdRegistCode
     *
     * @param prdRegistCode prdRegistCode
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode;
    }

    /**
     * 获取 prodName
     *
     * @return prodName.
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 prodName
     *
     * @param prodName prodName
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取 qty
     *
     * @return qty.
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 qty
     *
     * @param qty qty
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 获取 holdDate
     *
     * @return holdDate.
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 设置 holdDate
     *
     * @param holdDate holdDate
     */
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    /**
     * 获取 lastQty
     *
     * @return lastQty.
     */
    public BigDecimal getLastQty() {
        return lastQty;
    }

    /**
     * 设置 lastQty
     *
     * @param lastQty lastQty
     */
    public void setLastQty(BigDecimal lastQty) {
        this.lastQty = lastQty;
    }

    /**
     * 获取 changeQty
     *
     * @return changeQty.
     */
    public BigDecimal getChangeQty() {
        return changeQty;
    }

    /**
     * 设置 changeQty
     *
     * @param changeQty changeQty
     */
    public void setChangeQty(BigDecimal changeQty) {
        this.changeQty = changeQty;
    }

    /**
     * 获取 holdDateStr
     * @return holdDateStr.
     */
    public String getHoldDateStr() {
        return DateUtil.format(getHoldDate());
    }

    /**
     * 设置 holdDateStr
     * @param holdDateStr holdDateStr
     */
    public void setHoldDateStr(String holdDateStr) {
        this.holdDateStr = holdDateStr;
    }
    /**
     * 获取 sumQty
     * @return sumQty.
     */
	public BigDecimal getSumQty() {
		return sumQty;
	}
	 /**
     * 设置 sumQty
     * @param sumQty sumQty
     */
	public void setSumQty(BigDecimal sumQty) {
		this.sumQty = sumQty;
	}
	/**
     * 获取 sumCus
     * @return sumCus.
     */
	public BigDecimal getSumCus() {
		return sumCus;
	}
	 /**
     * 设置 sumCus
     * @param sumCus sumCus
     */
	public void setSumCus(BigDecimal sumCus) {
		this.sumCus = sumCus;
	}
    

}
