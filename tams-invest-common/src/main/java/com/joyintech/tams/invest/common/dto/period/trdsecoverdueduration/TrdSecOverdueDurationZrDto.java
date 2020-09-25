package com.joyintech.tams.invest.common.dto.period.trdsecoverdueduration;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TrdSecOverdueDurationZrDto extends TrdSecOverdueDurationDto{
    /**
     * 实际交易组合
     */
    private String actPortfolioId;
    /**
     * 分类8
     */
    private String type8;
    /**
     * 转让罚息
     */
    private BigDecimal penaltyInt;
    /**
     *  转让日期
     */
    private Date transferDate;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 交易编号
     */
    private String busiCode;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 根据投资目的排序的券面
     */
    private String amountList;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 证券托管户
     */
    private String secManageAcctId;
    // <!-- 通道新增 qiangchengxi 2018-03-30 start -->


    private String validatorType;

    @Override
    public String getValidatorType() {
        return validatorType;
    }

    @Override
    public void setValidatorType(String validatorType) {
        this.validatorType = validatorType;
    }

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 是否是通道
     */
    private boolean channel;

    /**
     * 证券托管户名称/通道名称
     */
    private String secAcctName;
    // <!-- 通道新增 qiangchengxi 2018-03-30 end -->

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 产品代码
     */
    private String finprodMarketName;

    /**
     * 产品起息日
     */
    private String vDateWhole;

    /**
     * 产品到期日
     */
    private String mDateWhole;

    /**
     * 计息开始日
     */
    private String vDate;

    /**
     * 计息结束日
     */
    private String mDate;

    /**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;


    /**
     * 券面总额
     */
    private BigDecimal qty;

    /**
     * 全价总额 理论本息总额
     */
    private BigDecimal fpriceAmt;

    /**
     * 到账金额
     */
    private BigDecimal tradeAmt;

    /**
     * 实收利息总额
     */
    private BigDecimal intAmt;

    /**
     * 还本金额
     */
    private BigDecimal prinAmt;

    /**
     * 理论应计利息
     */
    private BigDecimal intAmtTheory;

    /**
     * 理论还本金额
     */
    private BigDecimal prinAmtTheory;

    /**
     * 现金流计划表.计息本金 单位本金,
     */
    private BigDecimal intPrinAmt;

    /**
     * 单位应计利息,
     */
    private BigDecimal unitIntAmt;

    /**
     * 区间票面利率
     */
    private BigDecimal yield;


    /**
     * 现金流代码（多条时，以<code>,</code>分割）
     */
    private String cashId;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 交易备注
     */
    private String remark;

    /**
     * 撤销理由
     */
    private String revokeReason;

    private String issuePartyId;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;


    /***update zhangweiqi 2019-10-14 存续期添加通道费 begin**/
    /**
     * 通道费
     */
    private BigDecimal channelFee;

    /**
     * 通道性质
     */
    private String payType;

    /**
     * 通道收取方式
     */
    private String collectType;

    /**
     * 实收本息总额
     */

    private BigDecimal tradeAmtReal;
    /***update zhangweiqi 2019-10-14 存续期添加通道费 end**/

    //add by lishuping 20191030 start
    /**
     * 资产简称
     */
    private String finprodAbbr;


    /**
     * 页面元素
     */
    private String elementId;

    /**
     * code Name
     */
    private String statusValidString;

    /**
     * 平安业务类型
     */
    private String paBussTypeString;

    /**
     * 附件上传
     */
    private List<UploadFile> uploadFileList;
    /**
     * 理论本金罚息
     */
    private BigDecimal theoryPenaltyIntBj;
    /**
     * 理论利息罚息
     */
    private BigDecimal theoryPenaltyIntLx;
    /**
     * 获取 转让日期
     *
     * @return transferDate 转让日期
     */
    public Date getTransferDate() {
        return this.transferDate;
    }

    /**
     * 设置 转让日期
     *
     * @param transferDate 转让日期
     */
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    /**
     * 获取 产品起息日
     *
     * @return vDateWhole 产品起息日
     */
    public String getVDateWhole() {
        return this.vDateWhole;
    }

    /**
     * 设置 产品起息日
     *
     * @param vDateWhole 产品起息日
     */
    public void setVDateWhole(String vDateWhole) {
        this.vDateWhole = vDateWhole;
    }

    /**
     * 获取 产品到期日
     *
     * @return mDateWhole 产品到期日
     */
    public String getMDateWhole() {
        return this.mDateWhole;
    }

    /**
     * 设置 产品到期日
     *
     * @param mDateWhole 产品到期日
     */
    public void setMDateWhole(String mDateWhole) {
        this.mDateWhole = mDateWhole;
    }

    /**
     * 获取 计息开始日
     *
     * @return vDate 计息开始日
     */
    public String getVDate() {
        return this.vDate;
    }

    /**
     * 设置 计息开始日
     *
     * @param vDate 计息开始日
     */
    public void setVDate(String vDate) {
        this.vDate = vDate;
    }

    /**
     * 获取 计息结束日
     *
     * @return mDate 计息结束日
     */
    public String getMDate() {
        return this.mDate;
    }

    /**
     * 设置 计息结束日
     *
     * @param mDate 计息结束日
     */
    public void setMDate(String mDate) {
        this.mDate = mDate;
    }

    /**
     * 获取 附件上传
     *
     * @return uploadFileList 附件上传
     */
    public List<UploadFile> getUploadFileList() {
        return this.uploadFileList;
    }

    /**
     * 设置 附件上传
     *
     * @param uploadFileList 附件上传
     */
    public void setUploadFileList(List<UploadFile> uploadFileList) {
        this.uploadFileList = uploadFileList;
    }

    /**
     * 获取 转让罚息
     *
     * @return penaltyInt 转让罚息
     */
    public BigDecimal getPenaltyInt() {
        return this.penaltyInt;
    }

    /**
     * 设置 转让罚息
     *
     * @param penaltyInt 转让罚息
     */
    public void setPenaltyInt(BigDecimal penaltyInt) {
        this.penaltyInt = penaltyInt;
    }

    /**
     * 获取 实际交易组合
     *
     * @return actPortfolioId 实际交易组合
     */
    public String getActPortfolioId() {
        return this.actPortfolioId;
    }

    /**
     * 设置 实际交易组合
     *
     * @param actPortfolioId 实际交易组合
     */
    public void setActPortfolioId(String actPortfolioId) {
        this.actPortfolioId = actPortfolioId;
    }

    /**
     * 获取 操作类型
     *
     * @return operateType 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     *
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 金融产品代码
     *
     * @return finprodId 金融产品代码
     */
    public String getFinprodId() {
        return this.finprodId;
    }

    /**
     * 设置 金融产品代码
     *
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 有效状态
     *
     * @return statusValid 有效状态
     */
    public String getStatusValid() {
        return this.statusValid;
    }

    /**
     * 设置 有效状态
     *
     * @param statusValid 有效状态
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    /**
     * 获取 交易编号
     *
     * @return busiCode 交易编号
     */
    public String getBusiCode() {
        return this.busiCode;
    }

    /**
     * 设置 交易编号
     *
     * @param busiCode 交易编号
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 获取 现金流类型
     *
     * @return cashType 现金流类型
     */
    public String getCashType() {
        return this.cashType;
    }

    /**
     * 设置 现金流类型
     *
     * @param cashType 现金流类型
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    /**
     * 获取 投资目的
     *
     * @return invAim 投资目的
     */
    public String getInvAim() {
        return this.invAim;
    }

    /**
     * 设置 投资目的
     *
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 根据投资目的排序的券面
     *
     * @return amountList 根据投资目的排序的券面
     */
    public String getAmountList() {
        return this.amountList;
    }

    /**
     * 设置 根据投资目的排序的券面
     *
     * @param amountList 根据投资目的排序的券面
     */
    public void setAmountList(String amountList) {
        this.amountList = amountList;
    }

    /**
     * 获取 平安业务类型
     *
     * @return paBussType 平安业务类型
     */
    public String getPaBussType() {
        return this.paBussType;
    }

    /**
     * 设置 平安业务类型
     *
     * @param paBussType 平安业务类型
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 组合名称
     *
     * @return portfolioName 组合名称
     */
    public String getPortfolioName() {
        return this.portfolioName;
    }

    /**
     * 设置 组合名称
     *
     * @param portfolioName 组合名称
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 证券托管户
     *
     * @return secManageAcctId 证券托管户
     */
    public String getSecManageAcctId() {
        return this.secManageAcctId;
    }

    /**
     * 设置 证券托管户
     *
     * @param secManageAcctId 证券托管户
     */
    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    /**
     * 获取 划款指令
     *
     * @return bussTrdTransferOrder 划款指令
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return this.bussTrdTransferOrder;
    }

    /**
     * 设置 划款指令
     *
     * @param bussTrdTransferOrder 划款指令
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

    /**
     * 获取 是否是通道
     *
     * @return channel 是否是通道
     */
    public boolean isChannel() {
        return this.channel;
    }

    /**
     * 设置 是否是通道
     *
     * @param channel 是否是通道
     */
    public void setChannel(boolean channel) {
        this.channel = channel;
    }

    /**
     * 获取 证券托管户名称通道名称
     *
     * @return secAcctName 证券托管户名称通道名称
     */
    public String getSecAcctName() {
        return this.secAcctName;
    }

    /**
     * 设置 证券托管户名称通道名称
     *
     * @param secAcctName 证券托管户名称通道名称
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    /**
     * 获取 市场代码
     *
     * @return finprodMarketId 市场代码
     */
    public String getFinprodMarketId() {
        return this.finprodMarketId;
    }

    /**
     * 设置 市场代码
     *
     * @param finprodMarketId 市场代码
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 产品代码
     *
     * @return finprodMarketName 产品代码
     */
    public String getFinprodMarketName() {
        return this.finprodMarketName;
    }

    /**
     * 设置 产品代码
     *
     * @param finprodMarketName 产品代码
     */
    public void setFinprodMarketName(String finprodMarketName) {
        this.finprodMarketName = finprodMarketName;
    }

    /**
     * 获取 是否需要跳过插入流程表数据
     *
     * @return needSkipInsertWfl 是否需要跳过插入流程表数据
     */
    public Boolean getNeedSkipInsertWfl() {
        return this.needSkipInsertWfl;
    }

    /**
     * 设置 是否需要跳过插入流程表数据
     *
     * @param needSkipInsertWfl 是否需要跳过插入流程表数据
     */
    public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
        this.needSkipInsertWfl = needSkipInsertWfl;
    }

    /**
     * 获取 券面总额
     *
     * @return qty 券面总额
     */
    public BigDecimal getQty() {
        return this.qty;
    }

    /**
     * 设置 券面总额
     *
     * @param qty 券面总额
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 获取 全价总额 理论本息总额
     *
     * @return fpriceAmt 全价总额 理论本息总额
     */
    public BigDecimal getFpriceAmt() {
        return this.fpriceAmt;
    }

    /**
     * 设置 全价总额 理论本息总额
     *
     * @param fpriceAmt 全价总额 理论本息总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 到账金额
     *
     * @return tradeAmt 到账金额
     */
    public BigDecimal getTradeAmt() {
        return this.tradeAmt;
    }

    /**
     * 设置 到账金额
     *
     * @param tradeAmt 到账金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 获取 实收利息总额
     *
     * @return intAmt 实收利息总额
     */
    public BigDecimal getIntAmt() {
        return this.intAmt;
    }

    /**
     * 设置 实收利息总额
     *
     * @param intAmt 实收利息总额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 还本金额
     *
     * @return prinAmt 还本金额
     */
    public BigDecimal getPrinAmt() {
        return this.prinAmt;
    }

    /**
     * 设置 还本金额
     *
     * @param prinAmt 还本金额
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 理论应计利息
     *
     * @return intAmtTheory 理论应计利息
     */
    public BigDecimal getIntAmtTheory() {
        return this.intAmtTheory;
    }

    /**
     * 设置 理论应计利息
     *
     * @param intAmtTheory 理论应计利息
     */
    public void setIntAmtTheory(BigDecimal intAmtTheory) {
        this.intAmtTheory = intAmtTheory;
    }

    /**
     * 获取 理论还本金额
     *
     * @return prinAmtTheory 理论还本金额
     */
    public BigDecimal getPrinAmtTheory() {
        return this.prinAmtTheory;
    }

    /**
     * 设置 理论还本金额
     *
     * @param prinAmtTheory 理论还本金额
     */
    public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
        this.prinAmtTheory = prinAmtTheory;
    }

    /**
     * 获取 现金流计划表.计息本金 单位本金
     *
     * @return intPrinAmt 现金流计划表.计息本金 单位本金
     */
    public BigDecimal getIntPrinAmt() {
        return this.intPrinAmt;
    }

    /**
     * 设置 现金流计划表.计息本金 单位本金
     *
     * @param intPrinAmt 现金流计划表.计息本金 单位本金
     */
    public void setIntPrinAmt(BigDecimal intPrinAmt) {
        this.intPrinAmt = intPrinAmt;
    }

    /**
     * 获取 单位应计利息
     *
     * @return unitIntAmt 单位应计利息
     */
    public BigDecimal getUnitIntAmt() {
        return this.unitIntAmt;
    }

    /**
     * 设置 单位应计利息
     *
     * @param unitIntAmt 单位应计利息
     */
    public void setUnitIntAmt(BigDecimal unitIntAmt) {
        this.unitIntAmt = unitIntAmt;
    }

    /**
     * 获取 区间票面利率
     *
     * @return yield 区间票面利率
     */
    public BigDecimal getYield() {
        return this.yield;
    }

    /**
     * 设置 区间票面利率
     *
     * @param yield 区间票面利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 现金流代码（多条时，以<code><code>分割）
     *
     * @return cashId 现金流代码（多条时，以<code><code>分割）
     */
    public String getCashId() {
        return this.cashId;
    }

    /**
     * 设置 现金流代码（多条时，以<code><code>分割）
     *
     * @param cashId 现金流代码（多条时，以<code><code>分割）
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    /**
     * 获取 币种
     *
     * @return ccy 币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 交易备注
     *
     * @return remark 交易备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 交易备注
     *
     * @param remark 交易备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 撤销理由
     *
     * @return revokeReason 撤销理由
     */
    public String getRevokeReason() {
        return this.revokeReason;
    }

    /**
     * 设置 撤销理由
     *
     * @param revokeReason 撤销理由
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取
     *
     * @return issuePartyId
     */
    public String getIssuePartyId() {
        return this.issuePartyId;
    }

    /**
     * 设置
     *
     * @param issuePartyId
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    /**
     * 获取 交易场所
     *
     * @return tradeMarket 交易场所
     */
    public String getTradeMarket() {
        return this.tradeMarket;
    }

    /**
     * 设置 交易场所
     *
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    /**
     * 获取 所有相关表，贯穿整个流程
     *
     * @return baseTotalTradeInfoDto 所有相关表，贯穿整个流程
     */
    public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
        return this.baseTotalTradeInfoDto;
    }

    /**
     * 设置 所有相关表，贯穿整个流程
     *
     * @param baseTotalTradeInfoDto 所有相关表，贯穿整个流程
     */
    public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
    }

    /**
     * 获取 通道费
     *
     * @return channelFee 通道费
     */
    public BigDecimal getChannelFee() {
        return this.channelFee;
    }

    /**
     * 设置 通道费
     *
     * @param channelFee 通道费
     */
    public void setChannelFee(BigDecimal channelFee) {
        this.channelFee = channelFee;
    }

    /**
     * 获取 通道性质
     *
     * @return payType 通道性质
     */
    public String getPayType() {
        return this.payType;
    }

    /**
     * 设置 通道性质
     *
     * @param payType 通道性质
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取 通道收取方式
     *
     * @return collectType 通道收取方式
     */
    public String getCollectType() {
        return this.collectType;
    }

    /**
     * 设置 通道收取方式
     *
     * @param collectType 通道收取方式
     */
    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    /**
     * 获取 实收本息总额
     *
     * @return tradeAmtReal 实收本息总额
     */
    public BigDecimal getTradeAmtReal() {
        return this.tradeAmtReal;
    }

    /**
     * 设置 实收本息总额
     *
     * @param tradeAmtReal 实收本息总额
     */
    public void setTradeAmtReal(BigDecimal tradeAmtReal) {
        this.tradeAmtReal = tradeAmtReal;
    }

    /**
     * 获取 资产简称
     *
     * @return finprodAbbr 资产简称
     */
    public String getFinprodAbbr() {
        return this.finprodAbbr;
    }

    /**
     * 设置 资产简称
     *
     * @param finprodAbbr 资产简称
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 页面元素
     *
     * @return elementId 页面元素
     */
    public String getElementId() {
        return this.elementId;
    }

    /**
     * 设置 页面元素
     *
     * @param elementId 页面元素
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 code Name
     *
     * @return statusValidString code Name
     */
    public String getStatusValidString() {
        return this.statusValidString;
    }

    /**
     * 设置 code Name
     *
     * @param statusValidString code Name
     */
    public void setStatusValidString(String statusValidString) {
        this.statusValidString = statusValidString;
    }

    /**
     * 获取 平安业务类型
     *
     * @return paBussTypeString 平安业务类型
     */
    public String getPaBussTypeString() {
        return this.paBussTypeString;
    }

    /**
     * 设置 平安业务类型
     *
     * @param paBussTypeString 平安业务类型
     */
    public void setPaBussTypeString(String paBussTypeString) {
        this.paBussTypeString = paBussTypeString;
    }

    /**
     * 获取 理论本金罚息
     *
     * @return theoryPenaltyIntBj 理论本金罚息
     */
    public BigDecimal getTheoryPenaltyIntBj() {
        return this.theoryPenaltyIntBj;
    }

    /**
     * 设置 理论本金罚息
     *
     * @param theoryPenaltyIntBj 理论本金罚息
     */
    public void setTheoryPenaltyIntBj(BigDecimal theoryPenaltyIntBj) {
        this.theoryPenaltyIntBj = theoryPenaltyIntBj;
    }

    /**
     * 获取 理论利息罚息
     *
     * @return theoryPenaltyIntLx 理论利息罚息
     */
    public BigDecimal getTheoryPenaltyIntLx() {
        return this.theoryPenaltyIntLx;
    }

    /**
     * 设置 理论利息罚息
     *
     * @param theoryPenaltyIntLx 理论利息罚息
     */
    public void setTheoryPenaltyIntLx(BigDecimal theoryPenaltyIntLx) {
        this.theoryPenaltyIntLx = theoryPenaltyIntLx;
    }

    /**
     * 获取 分类8
     *
     * @return type8 分类8
     */
    public String getType8() {
        return this.type8;
    }

    /**
     * 设置 分类8
     *
     * @param type8 分类8
     */
    public void setType8(String type8) {
        this.type8 = type8;
    }

    /**
     * 获取 风险试算实体类
     *
     * @return riskTrialRetDto 风险试算实体类
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return this.riskTrialRetDto;
    }

    /**
     * 设置 风险试算实体类
     *
     * @param riskTrialRetDto 风险试算实体类
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }
}
