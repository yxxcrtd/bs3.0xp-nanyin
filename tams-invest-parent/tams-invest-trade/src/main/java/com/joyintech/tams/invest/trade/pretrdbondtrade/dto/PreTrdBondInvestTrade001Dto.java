/*
 * 文件名：TrdBondInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdbondtrade.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 债券交易预录入查询界面dto
 * @author 王伟
 * @version 1.0 2020年1月6日
 * @see PreTrdBondInvestTrade001Dto
 * @since 1.0
 */
@PageId("PreTrdBondInvestTrade001")
public class PreTrdBondInvestTrade001Dto extends TrdFinProductDealTotal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -7438510730454849159L;

    /**
     * 有效状态
     */
    private String validStatus;
    
    /**
     * 匹配状态
     */
    private String matchStatus;

    /**
     * 有效状态Text
     */
    private String validStatusText;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 交易方向Text
     */
    private String psText;

    /**
     * 交易日期Text
     */
    private String tradeDateText;

    /**
     * 币种Text
     */
    private String ccyText;

    /**
     * 清算速度Text
     */
    private String settleSpeedText;

    /**
     * 有效时间Text
     */
    private String effTimeText;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 证券托管户
     */
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 资金账户
     */
    private String acctNo;
    /**
     * 资金账户名
     */
    private String acctName;

    /**
     * 资金开户行
     */
    private String acctBankDep;

    /**
     * 资金开户行支付系统行号
     */
    private String acctBankDepoPayno;

    /**
     * 获取 有效状态
     * @rturn 有效状态
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 有效状态
     * @param validStatus 有效状态
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getMatchStatus() {
		return matchStatus;
	}

	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}

	/**
     * 取得 有效状态
     * @return 有效状态
     */
    public String getValidStatusText() {
        return validStatusText;
    }

    /**
     * 设置 有效状态
     * @param validStatusText 有效状态
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    /**
     * 获取 操作类型
     * @rturn 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    /**
     * 获取 工作流状态
     * @rturn 工作流状态
     */
    public String getWorkflowStatus() {
        return this.workflowStatus;
    }

    /**
     * 设置 工作流状态
     * @param workflowStatus 工作流状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPsText() {
        return psText;
    }

    /**
     * 设置 交易方向
     * @param psText 交易方向
     */
    public void setPsText(String psText) {
        this.psText = psText;
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public String getTradeDateText() {
        return tradeDateText;
    }

    /**
     * 设置 交易日期
     * @param tradeDateText 交易日期
     */
    public void setTradeDateText(String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcyText() {
        return ccyText;
    }

    /**
     * 设置 币种
     * @param ccyText 币种
     */
    public void setCcyText(String ccyText) {
        this.ccyText = ccyText;
    }

    /**
     * 取得 清算速度
     * @return 清算速度
     */
    public String getSettleSpeedText() {
        return settleSpeedText;
    }

    /**
     * 设置 清算速度
     * @param settleSpeedText 清算速度
     */
    public void setSettleSpeedText(String settleSpeedText) {
        this.settleSpeedText = settleSpeedText;
    }

    /**
     * 取得 有效时间
     * @return 有效时间
     */
    public String getEffTimeText() {
        return effTimeText;
    }

    /**
     * 设置 有效时间
     * @param effTimeText 有效时间
     */
    public void setEffTimeText(String effTimeText) {
        this.effTimeText = effTimeText;
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    @Override
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 资产代码
     * @param finprodId 资产代码
     */
    @Override
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getFinprodName() {
        return finprodName;
    }

    /**
     * 设置 资产名称
     * @param finprodName 资产名称
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    /**
     * 获取 证券托管户
     * @rturn secAcctId
     */
    public String getSecAcctId() {
        return this.secAcctId;
    }

    /**
     * 设置 证券托管户
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 证券账户名称
     * @return
     */
    public String getSecAcctName() {
        return secAcctName;
    }

    /**
     * 设置 证券账户名称
     * @param secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    /**
     * 获取 登记托管机构
     * @return
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    /**
     * 获取 资金账户
     * @return
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 资金账户
     * @param acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 资金账户名
     * @return
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金账户名
     * @param acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * 获取 资金开户行
     * @return
     */
    public String getAcctBankDep() {
        return acctBankDep;
    }

    /**
     * 设置 资金开户行
     * @param acctBankDep
     */
    public void setAcctBankDep(String acctBankDep) {
        this.acctBankDep = acctBankDep;
    }

    /**
     * 获取 资金开户行支付系统行号
     * @return
     */
    public String getAcctBankDepoPayno() {
        return acctBankDepoPayno;
    }

    /**
     * 设置 资金开户行支付系统行号
     * @param acctBankDepoPayno
     */
    public void setAcctBankDepoPayno(String acctBankDepoPayno) {
        this.acctBankDepoPayno = acctBankDepoPayno;
    }

}
