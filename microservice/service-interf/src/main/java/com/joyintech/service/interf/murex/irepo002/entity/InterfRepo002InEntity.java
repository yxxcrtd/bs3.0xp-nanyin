/*
 * 文件名：InterfRepo002InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：whm
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.murex.irepo002.entity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * 质押式回购交易接口-输入
 * 
 * @author whm
 * @version 1.0 2018年11月22日
 * @see InterfRepo002InEntity
 * @since 1.0
 */
public class InterfRepo002InEntity {

    /**
     * 发送Murex信息
     */
    private Map<String, Object> interfSendMurex;

    public InterfRepo002InEntity(Map<String, Object> interfSendMurex) {
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
     * 获取 回购方向
     * 
     * @return 回购方向.
     */
    @EsbName(value = "BUY_BACK_DIR")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getPs() {
        return (String)interfSendMurex.get("BUY_BACK_DIR");
    }

    /**
     * 获取 到期日
     * 
     * @return 到期日.
     */
    @EsbName(value = "EXPIRE_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 16)
    @Required
    public String getExpireDate() {
        return (String)interfSendMurex.get("EXPIRE_DATE");
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
     * 获取 回购利率
     * 
     * @return 回购利率.
     */
    @EsbName(value = "BUY_BACK_INT_RATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    public String getBuyBackIntRate() {
        return (String)interfSendMurex.get("BUY_BACK_INT_RATE");
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
    public String getTranAmt() {
        return (String)interfSendMurex.get("TRAN_AMT");
    }

    /**
     * 获取 结算币种
     * 
     * @return 结算币种.
     */
    @EsbName(value = "SETTLE_CCY")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 3)
    public String getSettleCcy() {
        return (String)interfSendMurex.get("SETTLE_CCY");
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
     * 获取 估值折扣计算公式
     * 
     * @return 估值折扣计算公式.
     */
    @EsbName(value = "EVAL_DISC_FORMULAE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getEvalDiscFormulae() {
        return (String)interfSendMurex.get("EVAL_DISC_FORMULAE");
    }

    /**
     * 获取 质押券置换安排
     * 
     * @return 质押券置换安排.
     */
    @EsbName(value = "PLEDGE_BOND_CHANGE_ARRANGE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getPledgeBondChangeArrange() {
        return (String)interfSendMurex.get("PLEDGE_BOND_CHANGE_ARRANGE");
    }

    /**
     * 获取 质押券信息
     * 
     * @return 质押券信息.
     */
    @EsbName(value = "PLEDGE_BOND_INFO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 200)
    public String getPledgeBondInfo() {
        return (String)interfSendMurex.get("PLEDGE_BOND_INFO");
    }

    /**
     * 获取 结算方式
     * 
     * @return 结算方式.
     */
    @EsbName(value = "SETTLE_TYPE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 7)
    public String getSettleType() {
        return (String)interfSendMurex.get("SETTLE_TYPE");
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
        return (String)interfSendMurex.get("DEAL_DATE");
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
    public String getTramOperationType() {
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
    public String getRemark() {
        return (String)interfSendMurex.get("REMARK");
    }

    /**
     * 获取 批复号
     * 
     * @return 批复号
     */
    @EsbName(value = "APPROVE_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 32)
    public String getApproveNo() {
        return (String)interfSendMurex.get("APPROVE_NO");
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

    /**
     * 取得 到期结算金额
     * 
     * @return 到期结算金额
     */
    @EsbName(value = "EXPIRE_SETTLE_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    public String getExpireSettleAmt() {
        return (String)interfSendMurex.get("EXPIRE_SETTLE_AMT");
    }
}
