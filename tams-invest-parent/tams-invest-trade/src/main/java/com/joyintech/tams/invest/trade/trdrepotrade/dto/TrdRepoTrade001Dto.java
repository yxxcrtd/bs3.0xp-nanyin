/*
 * 文件名：TrdRepoTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年12月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.trdrepotrade.dto;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessEntity;

@PageId("TrdRepoTrade001")
public class TrdRepoTrade001Dto extends TrdProductDeal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 有效状态
     */
    private String validStatus;

    /**
     * 有效状态Text
     */
    private String validStatusText;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 回购类型（H01：质押式协议回购；H02：质押式竞价回购）
     */
    private String repoType;
    /**
     * 回购类型Text
     */
    private String repoTypeText;

    /**
     * 期限品种
     */
    private String termTypeReback;

    /**
     * 交易方向（正回购、逆回购）
     */

    /**
     * 交易方向Text
     */
    private String psText;

    /**
     * 交易日期
     */

    /**
     * 交易日期Text
     */
    private String tradeDateText;

    /**
     * 本金金额
     */

    /**
     * 币种
     */

    /**
     * 币种Text
     */
    private String ccyText;

    /**
     * 备注
     */

    /**
     * 工作流状态
     */
    private String workflowStatus;
    
    /**
     * 债券名称
     */
    private String bondName;


    /**
     * 获取 operateType
     *
     * @rturn operateType
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 validStatus
     *
     * @rturn validStatus
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 validStatus
     *
     * @param validStatus validStatus
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * 获取 validStatusText
     *
     * @rturn validStatusText
     */
    public String getValidStatusText() {
        if (StringUtil.isNotEmpty(this.getValidStatus())) {
            validStatusText = SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, this.getValidStatus());
        }
        return validStatusText;
    }

    /**
     * 设置 validStatusText
     *
     * @param validStatusText validStatusText
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    /**
     * 获取 entrustId
     *
     * @rturn entrustId
     */
    public String getEntrustId() {
        return this.entrustId;
    }

    /**
     * 设置 entrustId
     *
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 portfolioId
     *
     * @rturn portfolioId
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     *
     * @rturn portfolioName
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 repoType
     *
     * @rturn repoType
     */
    public String getRepoType() {
        return this.repoType;
    }

    /**
     * 设置 repoType
     *
     * @param repoType repoType
     */
    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    /**
     * 获取 repoTypeText
     *
     * @rturn repoTypeText
     */
    public String getRepoTypeText() {

        if (StringUtil.isNotEmpty(this.getRepoType()) /*&&! this.getTradeMarket().equals(DictCodeListExt1.交易场所_银行间.getCodeName())*/) {
            repoTypeText = SysDataUtil.getNameFromDataDict(DictCode.回购类型, this.getRepoType());
        }

        return repoTypeText;
    }

    /**
     * 设置 repoTypeText
     *
     * @param repoTypeText repoTypeText
     */
    public void setRepoTypeText(String repoTypeText) {
        this.repoTypeText = repoTypeText;
    }

    /**
     * 获取 termTypeReback
     *
     * @rturn termTypeReback
     */
    public String getTermTypeReback() {
        return this.termTypeReback;
    }

    /**
     * 设置 termTypeReback
     *
     * @param termTypeReback termTypeReback
     */
    public void setTermTypeReback(String termTypeReback) {
        this.termTypeReback = termTypeReback;
    }

    /**
     * 获取 psText
     *
     * @return psText.
     */
    public String getPsText() {
        if (StringUtil.isNotEmpty(this.getPs())) {
            psText = SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, this.getPs());
        }
        return psText;
    }

    /**
     * 设置 psText
     *
     * @param psText psText
     */
    public void setPsText(String psText) {
        this.psText = psText;
    }

    /**
     * 获取 tradeDateText
     *
     * @rturn tradeDateText
     */
    public String getTradeDateText() {
        tradeDateText = DateUtil.format(this.getTradeDate());
        return this.tradeDateText;
    }

    /**
     * 设置 tradeDateText
     *
     * @param tradeDateText tradeDateText
     */
    public void setTradeDateText(String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }

    /**
     * 获取 ccyText
     *
     * @rturn ccyText
     */
    public String getCcyText() {
        if (StringUtil.isNotEmpty(this.getCcy())) {
            ccyText = SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
        }
        return ccyText;
    }

    /**
     * 设置 ccyText
     *
     * @param ccyText ccyText
     */
    public void setCcyText(String ccyText) {
        this.ccyText = ccyText;
    }

    /**
     * 获取 workflowStatus
     *
     * @rturn workflowStatus
     */
    public String getWorkflowStatus() {
        return this.workflowStatus;
    }

    /**
     * 设置 workflowStatus
     *
     * @param workflowStatus workflowStatus
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

	/**
	 * 获取 bondName
	 * @return bondName.
	 */
	public String getBondName() {
		return bondName;
	}

	/**
	 * 设置 bondName
	 * @param bondName bondName
	 */
	public void setBondName(String bondName) {
		this.bondName = bondName;
	}
}
