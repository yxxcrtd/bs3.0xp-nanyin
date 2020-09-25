/*
 * 文件名：Interfibond001InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.ibond001.entity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.cache.InterfCacheUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 向MUREX发送债券交易输入entity
 * 
 * @author whm
 * @version 1.0 2018年11月23日
 * @see InterfBond001InEntity
 * @since 1.0
 */
public class InterfBond001InEntity {

    /**
     * 成交状态：撤销
     */
    private static final String STATUS_REVOKE = "R";

    /**
     * 发送MUREX信息
     */
    private Map<String, Object> sendMurexInfo;

    public InterfBond001InEntity(Map<String, Object> sendMurexInfo) {
        this.sendMurexInfo = sendMurexInfo;
    }

    /**
     * 获取 sendMurexInfo
     * 
     * @return sendMurexInfo.
     */
    public Map<String, Object> getSendMurexInfo() {
        return sendMurexInfo;
    }

    /**
     * 设置 sendMurexInfo
     * 
     * @param sendMurexInfo sendMurexInfo
     */
    public void setSendMurexInfo(Map<String, Object> sendMurexInfo) {
        this.sendMurexInfo = sendMurexInfo;
    }

    @EsbName(value = "BUY_SELL_DIRECTION")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    @Required
    public String getPs() {
        return (String)sendMurexInfo.get("BUY_SELL_DIRECTION");
    }

    @EsbName(value = "BOND_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    @Required
    public String getBondName() {
        return (String)sendMurexInfo.get("BOND_NAME");
    }

    @EsbName(value = "BOND_BELONG_MARKET")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 35)
    @Required
    public String getBondMarket() {
        return (String)sendMurexInfo.get("BOND_BELONG_MARKET");
    }

    @EsbName(value = "CORPUS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getAmt() {
        return (String)sendMurexInfo.get("CORPUS");
    }

    @EsbName(value = "DELIVERY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getDeliveryDate() {
        return DateUtil.formatDate((String)sendMurexInfo.get("DELIVERY_DATE"));
    }

    @EsbName(value = "DELIVERY_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    public String getCcy() {
        return (String)sendMurexInfo.get("DELIVERY_CCY");
    }

    @EsbName(value = "TRAN_NET_PRICE_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getNetPrice() {
        return (String)sendMurexInfo.get("TRAN_NET_PRICE_AMT");
    }

    @EsbName(value = "ALL_PRICE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getFullPrice() {
        return (String)sendMurexInfo.get("ALL_PRICE");
    }

    @EsbName(value = "DUE_ACCRUED_INTEREST")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getInterest() {
        return (String)sendMurexInfo.get("DUE_ACCRUED_INTEREST");
    }

    @EsbName(value = "SETTLE_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    public String getSettleType() {
        return (String)sendMurexInfo.get("SETTLE_MODE");
    }

    @EsbName(value = "TRAN_ALL_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    @Required
    public String getBusinessId() {
        return (String)sendMurexInfo.get("TRAN_ALL_SEQ_NO");
    }

    @EsbName(value = "INVEST_GROUP")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    public String getInvestGroup() {
        return (String)sendMurexInfo.get("INVEST_GROUP");
    }

    @EsbName(value = "COUNTER_PARTY_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getCno() {
        return (String)sendMurexInfo.get("COUNTER_PARTY_NAME");
    }

    @EsbName(value = "TURNOVER_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getDealDate() {
        return (String)sendMurexInfo.get("TURNOVER_DATE");
    }

    /**
     * 获取 上游系统编码
     * 
     * @return 上游系统编码
     */
    @EsbName(value = "UPPER_SYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getUpperSys() {
        // return InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID)
        return "TRCMS-PFMS";
    }

    @EsbName(value = "CLEARING_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getClearingMode() {
        return (String)sendMurexInfo.get("CLEARING_MODE");
    }

    @EsbName(value = "TRAN_OPERATION_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOpType() {
        return (String)sendMurexInfo.get("TRAN_OPERATION_TYPE");
    }

    /**
     * 获取事件日期
     * 
     * @return 事件日期
     */
    @EsbName(value = "EVENT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getCdate() {
        // 获取成交状态，如果状态=4(撤销)那么取平台日期
        if (STATUS_REVOKE.equals(sendMurexInfo.get("TRAN_OPERATION_TYPE"))) {
            return InterfCacheUtil.getSysDate();
        }
        return DateUtil.formatDate((String)sendMurexInfo.get("EVENT_DATE"));
    }

    @EsbName(value = "DEALER_ROLE_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 30)
    @Required
    public String getTrad() {
        return (String)sendMurexInfo.get("DEALER_ROLE_CODE");
    }

    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getRemark() {
        return (String)sendMurexInfo.get("REMARK");
    }

    @EsbName(value = "PROXY_SAVE_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getProxySaveFlag() {
        return (String)sendMurexInfo.get("PROXY_SAVE_FLAG");
    }

    @EsbName(value = "FT_SPECIAL_BUSS_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    public String getFtSpecialBussFlag() {
        return (String)sendMurexInfo.get("FT_SPECIAL_BUSS_FLAG");
    }

    @EsbName(value = "BUSS_MODEL")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getBussModel() {
        return (String)sendMurexInfo.get("BUSS_MODEL");
    }

    @EsbName(value = "ADD_CASHFLOW")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getAddCashflow() {
        return (String)sendMurexInfo.get("ADD_CASHFLOW");
    }

    @EsbName(value = "DELIVERY_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getDeliveryAmt() {
        return (String)sendMurexInfo.get("DELIVERY_AMT");
    }

    @EsbName(value = "APPROVE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getSn() {
        return (String)sendMurexInfo.get("APPROVE_NO");
    }

    @EsbName(value = "INSIDE_OUTSIDE_TRAN_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    public String getInsideOutsideFlag() {
        return (String)sendMurexInfo.get("INSIDE_OUTSIDE_TRAN_FLAG");
    }

    @EsbName(value = "MERGER_TRAN_FLAG")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 4)
    public String getMergerTranFlag() {
        return (String)sendMurexInfo.get("MERGER_TRAN_FLAG");
    }

    @EsbName(value = "FEE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getFeeMoudle() {
        return (String)sendMurexInfo.get("FEE_TYPE");
    }

    @EsbName(value = "FEE_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getFeeAmt() {
        return (String)sendMurexInfo.get("FEE_AMT");
    }

    @EsbName(value = "SECONDARY_MARKET_BUY_SELL_BOND_FEE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getFees() {
        return (String)sendMurexInfo.get("SECONDARY_MARKET_BUY_SELL_BOND_FEE");
    }

    /**
     * 取得 资金账户名称
     * 
     * @return 资金账户名称
     */
    @EsbName(value = "SEC_ACCT_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getSecAcctName() {
        return (String)sendMurexInfo.get("SEC_ACCT_NAME");
    }

    /**
     * 取得 资金开户行
     * 
     * @return 资金开户行
     */
    @EsbName(value = "SEC_OPEN_BRANCH")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    public String getSecOpenBranch() {
        return (String)sendMurexInfo.get("SEC_OPEN_BRANCH");
    }

    /**
     * 取得 资金账号
     * 
     * @return 资金账号
     */
    @EsbName(value = "SEC_ACCT_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getSecAcctNo() {
        return (String)sendMurexInfo.get("SEC_ACCT_NO");
    }

    /**
     * 取得 资金开户行联行行号
     * 
     * @return 资金开户行联行行号
     */
    @EsbName(value = "SEC_OPEN_INTERBANK_BRANCH_ID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getSecOpenInterbankBrnId() {
        return (String)sendMurexInfo.get("SEC_OPEN_INTERBANK_BRANCH_ID");
    }
}
