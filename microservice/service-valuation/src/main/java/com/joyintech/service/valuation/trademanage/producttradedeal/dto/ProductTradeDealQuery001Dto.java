/*
 * 文件名：ProductTradeDealQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.trademanage.producttradedeal.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * TA交易-汇总查询 DTO
 * @author zhangwei
 * @version 1.0 2018年8月7日
 * @see ProductTradeDealQuery001Dto
 * @since 1.0
 */@SuppressWarnings("serial")

public class ProductTradeDealQuery001Dto extends BaseEntity{
     
     /**产品代码**/
     private String finprodId;
     
     /**产品简称**/
     private String finprodAbbr;
     
     /**确认日- 交割日**/
     private String settleDate;
     
     
     /**产品类型Code - 收益类型**/
     private String profitTypeCode;
     
     /**产品类型 - 收益类型 **/
     private String profitType;
     
     /**币种Code**/
     private String ccyCode;
     
     /**币种**/
     private String ccy;
     
     /** 净申赎份额 **/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal netRedemptionShare;
     
     /** 净申赎金额 **/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal netRedemptionAmt;
     
     /** 分红付息金额 **/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal divIntAmt;
     
     /** 再投份额 **/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal reShare;
     
     /** 上一日份额 **/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal shareLastDay;
     
     /** 当日剩余份额 **/
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal shareRemainderDay;
     
     /**
      * 净值日资产净值
      */
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal fpriceAmt;
     
     /**
      * 净值日
      */
     private String cpriceDate;
     
     /**
      * 净值日资产净值
      */
     @JsonSerialize(using = BigDecimalSerializer.class)
     private BigDecimal unitCprice;
     
     
     
     

    
    /**
     * 获取 unitCprice
     * @return unitCprice.
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }



    
    /**
     * 设置 unitCprice
     * @param unitCprice unitCprice
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }



    /**
     * 获取 fpriceAmt
     * @return fpriceAmt.
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }


    
    /**
     * 设置 fpriceAmt
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }


    
    /**
     * 获取 cpriceDate
     * @return cpriceDate.
     */
    public String getCpriceDate() {
        return cpriceDate;
    }


    
    /**
     * 设置 cpriceDate
     * @param cpriceDate cpriceDate
     */
    public void setCpriceDate(String cpriceDate) {
        this.cpriceDate = cpriceDate;
    }


    //     ---------  树所需要属性  start------------
     /**母金融产品代码**/
     private String pFinprodId;
     
     /**是否含有子节点**/
     private String hasChildren;
     
     /** 是否是叶子节点 **/
     private boolean leaf;
//   ---------  树所需要属性  end------------
    
    public String getFinprodId() {
        return finprodId;
    }

    
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    
    



	public String getProfitTypeCode() {
		return profitTypeCode;
	}


	public void setProfitTypeCode(String profitTypeCode) {
		this.profitTypeCode = profitTypeCode;
	}


	public String getProfitType() {
		return this.profitType;
	}


	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}


	public String getCcy() {
        return this.ccy;
    }

    
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }
    
    

    
    public String getCcyCode() {
		return ccyCode;
	}


	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}


	public String getpFinprodId() {
        return pFinprodId;
    }

    
    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }

    
    public String getHasChildren() {
        return hasChildren;
    }

    
    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
        this.leaf = StringUtil.equals(DictCodeList.是否_否.getValue(), hasChildren);
    }
     
    
    
    public String getSettleDate() {
		return settleDate;
	}


	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}


	/**
     * 获取 leaf
     * 是否是叶子节点
     * @return leaf.
     */
    public boolean isLeaf() {
        return leaf;
    }

    /**
     * 设置 leaf
     * 
     * @param leaf leaf
     */
    public void setLeaf(boolean leaf) {
    	this.leaf = leaf;
    }


	public BigDecimal getNetRedemptionShare() {
		return netRedemptionShare;
	}


	public void setNetRedemptionShare(BigDecimal netRedemptionShare) {
		this.netRedemptionShare = netRedemptionShare;
	}


	public BigDecimal getNetRedemptionAmt() {
		return netRedemptionAmt;
	}


	public void setNetRedemptionAmt(BigDecimal netRedemptionAmt) {
		this.netRedemptionAmt = netRedemptionAmt;
	}


	public BigDecimal getDivIntAmt() {
		return divIntAmt;
	}


	public void setDivIntAmt(BigDecimal divIntAmt) {
		this.divIntAmt = divIntAmt;
	}


	public BigDecimal getReShare() {
		return reShare;
	}


	public void setReShare(BigDecimal reShare) {
		this.reShare = reShare;
	}


	public BigDecimal getShareLastDay() {
		return null!=shareLastDay?shareLastDay:BigDecimal.ZERO;
	}


	public void setShareLastDay(BigDecimal shareLastDay) {
		this.shareLastDay = shareLastDay;
	}


	public BigDecimal getShareRemainderDay() {
		return shareRemainderDay;
	}


	public void setShareRemainderDay(BigDecimal shareRemainderDay) {
		this.shareRemainderDay = shareRemainderDay;
	}


	


	


     
    
    

}
