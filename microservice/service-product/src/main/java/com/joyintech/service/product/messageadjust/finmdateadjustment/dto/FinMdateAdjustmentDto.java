/*
 * 文件名：FinMdateAdjustment001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finmdateadjustment.dto;

import java.util.Date;
import com.joyintech.tams.common.base.entity.PrdMdateAdjust;


/**
 * 到期日调整
 * 
 * @author zhangliang
 * @version 1.0 2018年11月2日
 * @see FinMdateAdjustmentDto
 * @since 1.0
 */
public class FinMdateAdjustmentDto extends PrdMdateAdjust {
    
    /**
     * 
     */
    private static final long serialVersionUID = -6712991831322928177L;

    /**
     * 现金流代码
     */
    //private String cashId;
    /**
     * 产品代码
     */
    private String finprodMarketId;
    
    /*public String getCashId() {
		return cashId;
	}

	public void setCashId(String cashId) {
		this.cashId = cashId;
	}*/


	/**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 提前终止标识
     */
    private String preEndFlag;
    
    /**
     * 是否有效
     */
    private String status;
    
    /**
     * 费用名称
     *//*
    private String feeName;
    *//**
     * 费用类型
     *//*
    private String feeType;
    *//**
     * 费用代码
     *//*
    private String feeId;*/
    
   /* public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}*/


	/**
     * 关联影响
     *//*
    private List<FinMdateAdjustmentDto> selectedItem;
    
    public List<FinMdateAdjustmentDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<FinMdateAdjustmentDto> selectedItem) {
		this.selectedItem = selectedItem;
	}*/


	/*public String getFeeType() {
		return feeType;
	}


	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}*/
    /**
     * 提前终止日
     */
    private Date bdate;
    
    /**
     * 产品展期日
     */
    private Date rdate;
	/**
     * 计提结束日
     */
    private Date eAccrDate;
    
    public Date geteAccrDate() {
		return eAccrDate;
	}

	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
	}


	/**
     * 起息日
     */
    private Date vdate;
    
    /**
     * 交易日历
     */
    private String calendarId;
    
    /**
     * 产品处理模式
     */
    private String dealMode;
    /**
     * 产品款型
     */
    private String prodStyle;
    /**
     * 费用是否按子产品分层
     */
    private String isFeeLayer;
    /**
     * 收益类型
     */
    private String profitType;
    
    /**
     * 发送状态
     */
    private String sendStatus;
    /**
     * 发送备注
     */
    private String sendRemark;
    
    /**
     * 获取 finprodMarketId
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    
    /**
     * 设置 finprodMarketId
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    /**
     * 获取 preEndFlag
     * @return preEndFlag.
     */
    public String getPreEndFlag() {
        return preEndFlag;
    }

    
    /**
     * 设置 preEndFlag
     * @param preEndFlag preEndFlag
     */
    public void setPreEndFlag(String preEndFlag) {
        this.preEndFlag = preEndFlag;
    }


    /**
     * 获取 feeName
     * @return feeName.
     */
   /* public String getFeeName() {
        return feeName;
    }


    *//**
     * 设置 feeName
     * @param feeName feeName
     *//*
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }*/


    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

    
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

	public String getDealMode() {
		return dealMode;
	}

	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	public String getProdStyle() {
		return prodStyle;
	}

	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}

	public String getIsFeeLayer() {
		return isFeeLayer;
	}

	public void setIsFeeLayer(String isFeeLayer) {
		this.isFeeLayer = isFeeLayer;
	}

	public String getProfitType() {
		return profitType;
	}

	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	public Date getVdate() {
		return vdate;
	}

	public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }
    
}
