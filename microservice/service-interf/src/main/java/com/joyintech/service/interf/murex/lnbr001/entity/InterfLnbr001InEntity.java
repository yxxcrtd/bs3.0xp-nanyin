/*
 * 文件名：LNBR001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.lnbr001.entity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 拆借交易接口-输入
 * 
 * @author whm
 * @version 1.0 2018年11月21日
 * @see InterfLnbr001InEntity
 * @since 1.0
 */
public class InterfLnbr001InEntity {

    /**
     * 发送Murex信息
     */
    private Map<String, Object> interfSendMurex;

    public InterfLnbr001InEntity(Map<String, Object> interfSendMurex) {
        this.interfSendMurex = interfSendMurex;
    }

    /**
     * 获取 发送Murex信息
     * 
     * @return 发送Murex信息.
     */
    public Map<String, Object> getInterfSendMurex() {
        return interfSendMurex;
    }

    /**
     * 设置 发送Murex信息
     * 
     * @param interfSendMurex 发送Murex信息
     */
    public void setInterfSendMurex(Map<String, Object> interfSendMurex) {
        this.interfSendMurex = interfSendMurex;
    }

    /**
     * 获取 交易方向
     * 
     * @return 交易方向.
     */
    @EsbName(value = "TRAN_DIR")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getTranDir() {
        return (String)interfSendMurex.get("TRAN_DIR");
    }

    /**
     * 获取 交易金额
     * 
     * @return 交易金额.
     */
    @EsbName(value = "TRAN_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getTranMat() {
        return (String)interfSendMurex.get("TRAN_AMT");
    }

    /**
     * 获取 交易货币
     * 
     * @return 交易货币.
     */
    @EsbName(value = "TRAN_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    @Required
    public String getTranCcy() {
        return (String)interfSendMurex.get("TRAN_CCY");
    }

    /**
     * 获取 起息日
     * 
     * @return 起息日.
     */
    @EsbName(value = "INT_EFFECT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getIntEffectDate() {
        return (String)interfSendMurex.get("INT_EFFECT_DATE");
    }

    /**
     * 获取 到期日
     * 
     * @return 到期日.
     */
    @EsbName(value = "EXPIRE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getExpireDate() {
        return (String)interfSendMurex.get("EXPIRE_DATE");
    }

    /**
     * 获取 拆借利率
     * 
     * @return 拆借利率.
     */
    @EsbName(value = "LEND_INT_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getLendIntRate() {
        return (String)interfSendMurex.get("LEND_INT_RATE");
    }

    /**
     * 获取 计息规则
     * 
     * @return 计息规则.
     */
    @EsbName(value = "INTEREST_RULE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 2)
    public String getInterestRule() {
        return (String)interfSendMurex.get("INTEREST_RULE");
    }

    /**
     * 获取 拆借类型
     * 
     * @return 拆借类型.
     */
    @EsbName(value = "LEND_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 1)
    public String getLendType() {
        return (String)interfSendMurex.get("LEND_TYPE");
    }

    /**
     * 获取 指标
     * 
     * @return 指标.
     */
    @EsbName(value = "INDEX")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getIndex() {
        return (String)interfSendMurex.get("INDEX");
    }

    /**
     * 获取 贵金属价格
     * 
     * @return 贵金属价格.
     */
    @EsbName(value = "METAL_PRICE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getMetalPrice() {
        return (String)interfSendMurex.get("METAL_PRICE");
    }

    /**
     * 获取 付息周期
     * 
     * @return 付息周期.
     */
    @EsbName(value = "REPAY_INTEREST_PERIOD")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getRepayInterest() {
        return (String)interfSendMurex.get("REPAY_INTEREST_PERIOD");
    }

    /**
     * 获取 利率类型
     * 
     * @return 利率类型.
     */
    @EsbName(value = "RATE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getRateType() {
        return (String)interfSendMurex.get("RATE_TYPE");
    }

    /**
     * 获取 产品
     * 
     * @return 产品.
     */
    @EsbName(value = "PRODUCT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getProduct() {
        return (String)interfSendMurex.get("PRODUCT");
    }

    /**
     * 获取 成色
     * 
     * @return 成色.
     */
    @EsbName(value = "PURITY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getPurity() {
        return (String)interfSendMurex.get("PURITY");
    }

    /**
     * 获取 交割账户
     * 
     * @return 交割账户.
     */
    @EsbName(value = "DELIVERY_ACCT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    public String getDeliveryAcct() {
        return (String)interfSendMurex.get("DELIVERY_ACCT");
    }

    /**
     * 获取 外部交易号
     * 
     * @return 外部交易号.
     */
    @EsbName(value = "OUTER_TRAN_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 100)
    @Required
    public String getOuterTranNo() {
        return (String)interfSendMurex.get("OUTER_TRAN_NO");
    }

    /**
     * 获取 投组
     * 
     * @return 投组.
     */
    @EsbName(value = "INVEST_GROUP")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getInvestGroup() {
        return (String)interfSendMurex.get("INVEST_GROUP");
    }

    /**
     * 获取 交易对手名称
     * 
     * @return 交易对手名称.
     */
    @EsbName(value = "COUNTER_PARTY_NAME")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 150)
    @Required
    public String getCounterPartyName() {
        return (String)interfSendMurex.get("COUNTER_PARTY_NAME");
    }

    /**
     * 获取 成交日
     * 
     * @return 成交日.
     */
    @EsbName(value = "DEAL_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    public String getDealDate() {
        return DateUtil.formatDate((String)interfSendMurex.get("DEAL_DATE"));
    }

    /**
     * 获取 上游系统
     * 
     * @return 上游系统.
     */
    @EsbName(value = "UPPER_SYS")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getUpperSys() {
        // return InterfCacheUtil.getSysParam(InterfConstant.INF_CONSUMER_ID)
        return "TRCMS-PFMS";
    }

    /**
     * 获取 清算方式
     * 
     * @return 清算方式.
     */
    @EsbName(value = "CLEARING_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    public String getClearingMode() {
        return (String)interfSendMurex.get("CLEARING_MODE");
    }

    /**
     * 获取 交易操作类型
     * 
     * @return 交易操作类型.
     */
    @EsbName(value = "TRAN_OPERATION_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getTranOperationType() {
        return (String)interfSendMurex.get("TRAN_OPERATION_TYPE");
    }

    /**
     * 获取 事件日期
     * 
     * @return 事件日期.
     */
    @EsbName(value = "EVENT_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getEventDate() {
        return DateUtil.formatDate((String)interfSendMurex.get("EVENT_DATE"));
    }

    /**
     * 获取 交易员
     * 
     * @return 交易员.
     */
    @EsbName(value = "DEALER_ROLE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 10)
    @Required
    public String getDealerRole() {
        return (String)interfSendMurex.get("DEALER_ROLE");
    }

    /**
     * 获取 备注
     * 
     * @return 备注.
     */
    @EsbName(value = "REMARK")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 300)
    public String getReMark() {
        return (String)interfSendMurex.get("REMARK");
    }

    /**
     * 获取 原始交易对手
     * 
     * @return 原始交易对手
     */
    @EsbName(value = "OLD_COUNTER_PARTY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getOldCounterParty() {
        return (String)interfSendMurex.get("OLD_COUNTER_PARTY");
    }

    /**
     * 获取 黄金进口方式
     * 
     * @return 黄金进口方式
     */
    @EsbName(value = "GOLD_IMPORT_MODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getGoldImportMode() {
        return (String)interfSendMurex.get("GOLD_IMPORT_MODE");
    }

    /**
     * 获取 交易类型
     * 
     * @return 交易类型
     */
    @EsbName(value = "TRAN_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getTranType() {
        return (String)interfSendMurex.get("TRAN_TYPE");
    }

    /**
     * 获取 对公原始交易对手BECIF号
     * 
     * @return 对公原始交易对手BECIF号
     */
    @EsbName(value = "PERSONAL_OLD_COUNTER_PARTY_BECIF_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOldBecifNo() {
        return (String)interfSendMurex.get("PERSONAL_OLD_COUNTER_PARTY_BECIF_NO");
    }

    /**
     * 获取 归集数目
     * 
     * @return 归集数目
     */
    @EsbName(value = "ACCUMUL_NUM")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 50)
    public String getAccumulNum() {
        return (String)interfSendMurex.get("ACCUMUL_NUM");
    }

    /**
     * 获取 企业批复号
     * 
     * @return 企业批复号
     */
    @EsbName(value = "COMPANY_APPROVE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getApproveNo() {
        return (String)interfSendMurex.get("COMPANY_APPROVE_NO");
    }

    /**
     * 请求流水号
     * 
     * @return 请求流水号
     */
    @EsbName(value = "REQUEST_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 52)
    public String getRequestSeqNo() {
        return (String)interfSendMurex.get("REQUEST_SEQ_NO");
    }

    /**
     * 获取 原始交易员
     * 
     * @return 原始交易员
     */
    @EsbName(value = "OLD_TRADER")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getOldTrader() {
        return (String)interfSendMurex.get("OLD_TRADER");
    }

    @EsbName(value = "PROD_PROPERTY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getProdProperty() {
        return (String)interfSendMurex.get("PROD_PROPERTY");
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
        return (String)interfSendMurex.get("SEC_ACCT_NAME");
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
        return (String)interfSendMurex.get("SEC_OPEN_BRANCH");
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
        return (String)interfSendMurex.get("SEC_ACCT_NO");
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
        return (String)interfSendMurex.get("SEC_OPEN_INTERBANK_BRANCH_ID");
    }
}
