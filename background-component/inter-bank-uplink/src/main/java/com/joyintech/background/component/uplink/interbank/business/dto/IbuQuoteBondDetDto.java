/*
 * 文件名：IbuQuoteBondDetDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.business.dto;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondDet;

/**
 * 现券银行间报价明细扩展对象DTO
 * 
 * @author Jiaoxujin
 * @version 1.0 2020年5月14日
 * @see IbuQuoteBondDetDto
 * @since 1.0
 */
public class IbuQuoteBondDetDto extends IbuQuoteBondDet {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4635305024365455368L;

    /**
     * 对手银行间机构名称
     */
    private String cpPartyName;

    /**
     * 对手方银行间交易员姓名
     */
    private String cpTraderName;

    /**
     * 获取 cpPartyName
     * 
     * @return cpPartyName.
     */
    public String getCpPartyName() {
        return cpPartyName;
    }

    /**
     * 设置 cpPartyName
     * 
     * @param cpPartyName cpPartyName
     */
    public void setCpPartyName(String cpPartyName) {
        this.cpPartyName = cpPartyName;
    }

    /**
     * 获取 cpTraderName
     * 
     * @return cpTraderName.
     */
    public String getCpTraderName() {
        return cpTraderName;
    }

    /**
     * 设置 cpTraderName
     * 
     * @param cpTraderName cpTraderName
     */
    public void setCpTraderName(String cpTraderName) {
        this.cpTraderName = cpTraderName;
    }
    
    
    /**
     * // 由于之前的映射文档再实际实施时key值有 差异，此处特殊转换
        sendData.put("cpTraderId", sendData.get("cpTrader"));
     */
    public String getCpTraderId() {
		return super.getCpTrader();
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "IbuQuoteBondDetDto [getDetId()=" + getDetId() + ", getReqId()=" + getReqId()
               + ", getVersion()=" + getVersion() + ", getCfetsReqId()=" + getCfetsReqId()
               + ", getQuoteId()=" + getQuoteId() + ", getCpPartyId()=" + getCpPartyId()
               + ", getCpTrader()=" + getCpTrader() + ", getUnitCprice()=" + getUnitCprice()
               + ", getUnitInt()=" + getUnitInt() + ", getUnitFprice()=" + getUnitFprice()
               + ", getParValue()=" + getParValue() + ", getShareAmt()=" + getShareAmt()
               + ", getPrinAmt()=" + getPrinAmt() + ", getParValueAmt()=" + getParValueAmt()
               + ", getCpriceAmt()=" + getCpriceAmt() + ", getIntAmt()=" + getIntAmt()
               + ", getFpriceAmt()=" + getFpriceAmt() + ", getYield()=" + getYield()
               + ", getExerYield()=" + getExerYield() + ", getTradeStatus()=" + getTradeStatus()
               + ", getSendStatus()=" + getSendStatus() + ", getExecid()=" + getExecid()
               + ", getRemark()=" + getRemark() + ", getStatus()=" + getStatus()
               + ", getCreateUser()=" + getCreateUser() + ", getCreateDept()=" + getCreateDept()
               + ", getCreateTime()=" + getCreateTime() + ", getUpdateUser()=" + getUpdateUser()
               + ", getUpdateTime()=" + getUpdateTime() + ", getWorkflowId()=" + getWorkflowId()
               + ", getUpdateFlg()=" + getUpdateFlg() + "]";
    }
}
