/*
 * 文件名：FinMdateAdjustment001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finmdateadjustment.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.PrdMdateAdjust;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.dto.ProcessEntity;


/**
 * 到期日调整
 * 
 * @author zhangliang
 * @version 1.0 2018年11月2日
 * @see FinMdateAdjustment001Dto
 * @since 1.0
 */
public class FinMdateAdjustment001Dto extends PrdMdateAdjust implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5745186801613965122L;
    /**
     * 现金流代码
     */
    /*private String cashId;
    public String getCashId() {
		return cashId;
	}


	public void setCashId(String cashId) {
		this.cashId = cashId;
	}*/
    
	/**
     * 产品代码
     */
    private String finprodMarketId;
    
    /**
     * 产品简称
     */
    private String finprodAbbr;
    
    /**
     * 提前终止标识
     */
    private String preEndFlag;
    
    /**
     * 元素id
     */
    private String elementId;
    
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 提前终止日
     */
    private Date bdate;
    
    /**
     * 产品展期日
     */
    private Date rdate;
    
    public Date geteAccrDate() {
		return eAccrDate;
	}


	public void seteAccrDate(Date eAccrDate) {
		this.eAccrDate = eAccrDate;
	}


	/**
     * 费用名称
     */
    /*private String feeName;
    
    *//**
     * 费用类型
     *//*
    private String feeType;*/
    
    /**
     * 费用代码
     */
   /* private String feeId;
    
    public String getFeeId() {
		return feeId;
	}


	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

*/
	/**
     * 计提结束日
     */
    private Date eAccrDate;
    
    /**
     * 是否有效
     */
    private String status;
    
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
     * 产品收益类型
     */
    private String profitType;
    
    // 发送状态
    private String sendStatus;
    // 发送状态备注
    private String sendRemark;
    
    /**
     * 关联影响 为净值分档用
     */
    private List<FinMdateAdjustment001Dto> selectedItem1;
    /**
     * 产品起息日
     */
    private Date vdate;
    
    public List<FinMdateAdjustment001Dto> getSelectedItem1() {
		return selectedItem1;
	}


	public void setSelectedItem1(List<FinMdateAdjustment001Dto> selectedItem1) {
		this.selectedItem1 = selectedItem1;
	}
    /**
     * 关联影响
     */
    /*private List<FinMdateAdjustment001Dto> selectedItem;
    
    public List<FinMdateAdjustment001Dto> getSelectedItem() {
		return selectedItem;
	}


	public void setSelectedItem(List<FinMdateAdjustment001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}


	public String getFeeName() {
		return feeName;
	}


	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}


	public String getFeeType() {
		return feeType;
	}


	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}*/


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
        if (this.getMdate() != null && DateUtil.compareDate(this.getMdateUnadjust(), this.getMdate()) > 0){
            return DictCodeList.是否_是.getCodeName();
        }
        else{
            return DictCodeList.是否_否.getCodeName();
        }
    }

    
    /**
     * 设置 preEndFlag
     * @param preEndFlag preEndFlag
     */
    public void setPreEndFlag(String preEndFlag) {
        this.preEndFlag = preEndFlag;
    }
    
    public String getMdateStr(){
        return DateUtil.format(this.getMdate());
    }

    public String getldateStr(){
        return DateUtil.format(this.getLdate());
    }
    
    public String getBdateStr(){
    	return DateUtil.format(this.getBdate());
    }
    
    public String getRdateStr(){
    	return DateUtil.format(this.getRdate());
    }
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }
    
    /**
     * {@inheritDoc}
     */
    public String getMdateUnadjustStr() {
        return DateUtil.format(super.getMdateUnadjust());
    }

    /**
     * {@inheritDoc}
     */
    public String getldateUnadjustStr() {
        return DateUtil.format(super.getLdateUnadjust());
    }

    /**
     * 获取 bdate
     * @return bdate.
     */
    public Date getBdate() {
        return bdate;
    }


    /**
     * 设置 bdate
     * @param bdate bdate
     */
    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }


    /**
     * 获取 rdate
     * @return rdate.
     */
    public Date getRdate() {
        return rdate;
    }


    /**
     * 设置 rdate
     * @param rdate rdate
     */
    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }
    
    /**
     * 
     * @return
     */
    public String getAdjustTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.调整类型, this.getAdjustType());
    }


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatusStr() {
       return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
	}


    
    /**
     * 获取 calendarId
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }


    
    /**
     * 设置 calendarId
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
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


	public void setVdate(Date vdate) {
		this.vdate = vdate;
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

    public String getSendStatusStr() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus())) ? SysDataUtil.getNameFromDataDict(DictCode.发送状态, getSendStatus()):"";
    }

}
