/*
 * 文件名：TrdStockDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.stockdeal.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.TrdTradeFeeDetail;
import com.joyintech.tams.component.business.trade.dto.BussTrdTransfenpeiDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.component.business.trade.dto.TrdInvestPersonInfo002Dto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.stockdeal.validator.TrdStockDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 股票交易
 *
 * @author wangpeng
 * @version 1.0 2019年7月29日
 *
 *
 */
@PageId("TrdStock002")
@ValidLogic(targetClass = TrdStockDtoValidator.class, groups = ValidateGroupEdit.class)
public class TrdStockDto extends TrdProductDeal implements ProcessEntity {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -7438510730454849159L;

	/**
	 * 是否有锁定期
	 */
	private String withLockPeriod;

	/**
	 * 组合分配
	 */
	private List<BussTrdTransfenpeiDto> bussTrdTransfenpeiDto;
	/**
	 * 锁定截止日
	 */
	private Date lockMdate;

	/**
	 * 有效状态
	 */
	private String validStatus;

	/**
	 * 委托代码
	 */
	private String entrustId;

	/**
	 * 资产名称
	 */
	private String finprodAbbr;

	/**
	 * 组合代码
	 */
	private String portfolioId;
	/**
	 * 实体组合代码
	 */
	private String actPortfolioId;

	/**
	 * 组合名称
	 */
	private String portfolioName;

	/**
	 * 资产名称
	 */
	private String finprodName;

	/**
	 * 交易日历
	 */
	private String calendarId;

	/**
	 * 操作类型
	 */
	private String operateType;

	/**
	 * 画面模式
	 */
	private String pageModel;

	/**
	 * 工作流状态
	 */
	private String workflowStatus;

	/**
	 * 计算类型
	 */
	private String calculateField;

	/**
	 * 金融产品交易附属
	 */
	private TrdProductDealAdd trdProductDealAdd;

	/**
	 * 组合交易
	 */
	private List<PtlTrade> ptlTrade;

	/**
	 * 金融产品交易扩展
	 */
	private PaTrdProductDeal paTrdProductDeal;

	/**
	 * 划款指令
	 */
	private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

	/**
	 * 人员信息
	 */
	@Valid
	private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

	/**
	 * 附件上传
	 */
	private List<UploadFile> uploadFileList;

	/**
	 * 有效状态
	 */
	private String validStatusText;

	/**
	 * 撤销说明
	 */
	private String revokeReason;

	/**
	 * 金融产品代码（系统自动生成）
	 */
	private String systemFinprodId;

	/**
	 * 风险试算实体类
	 */
	private List<RiskTrialRetDto> riskTrialRetDto;

	// 通道交易 新增 强成西 2019/03/26 start
//	/**
//	 * 通道费
//	 */
//	private BigDecimal channelFee;
	/**
	 * 委托数量
	 */
	private BigDecimal entrustAmt;
	/**
	 * 委托额度
	 */
	private BigDecimal entrustNum;

	/**
	 * 委托单价
	 */
	private BigDecimal price;
	/**
	 * 完成数量
	 */
	private BigDecimal finishAmt;


	/**
	 * 通道性质
	 */
	private String payType;
	/**
	 * 金额信息
	 */
	private String amtInfo;

	/**
	 * 上市板
	 */
	private String board;

	/**
	 * 证券托管户
	 */
	private String secAcctId;

	/**
	 * 费用明细表
	 */
	private TrdTradeFeeDetail trdTradeFeeDetail;

	/**
	 * 交易金额
	 */
	private BigDecimal dealAmt;

	/**
	 * 资产可用持仓
	 */
	private BigDecimal bottomAssetAmount;

	/**
	 * 托管户剩余现金
	 */
	private BigDecimal accAvailableCash;
	/**
	 * 资金账户可用现金
	 */
	private BigDecimal tradeCash;

	/**
	 * 组合T日可用现金
	 */
	private BigDecimal avalibleCash;
	/**
	 * 交易方式
	 */
	private String dealMode;
//	/**
//	 * 通道
//	 */
//	private String chlId;

	/**
	 * 印花税
	 */
	private BigDecimal stampDuty;
	/**
	 * 佣金
	 */
	private BigDecimal commission;

	/**
	 * 印花税是否随交易支付
	 */
	private String stampDutyIsPayWithTrade;
	/**
	 * 佣金是否随交易支付
	 */
	private String commissionIsPayWithTrade;

	private String tradeDateStr;
	private String settleDateStr;

	/**
	 * 获取锁定期
	 *
	 * @return
	 */
	@NotEmpty(fieldName = "TrdStock002.withLockPeriod", groups = { ValidateGroupEdit.class })
	public String getWithLockPeriod() {
		return withLockPeriod;
	}

	/**
	 * 设置锁定期
	 *
	 * @param withLockPeriod
	 */

	public void setWithLockPeriod(String withLockPeriod) {
		this.withLockPeriod = withLockPeriod;
	}

	/**
	 * 获取锁定截止日
	 *
	 * @return
	 */
	public String getLockMdateStr() {
		return DateUtil.format(this.getLockMdate());
	}

	public List<BussTrdTransfenpeiDto> getBussTrdTransfenpeiDto() {
		return bussTrdTransfenpeiDto;
	}

	public void setBussTrdTransfenpeiDto(List<BussTrdTransfenpeiDto> bussTrdTransfenpeiDto) {
		this.bussTrdTransfenpeiDto = bussTrdTransfenpeiDto;
	}

	/**
	 * 取得 交易编号
	 *
	 * @return 交易编号
	 */
	@Override
	@PrimaryKey(PrimaryKey.Field.PK_1)
	public String getTradeId() {
		return super.getTradeId();
	}

	/**
	 * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 *
	 * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 */
	/*
	 * @Override
	 *
	 * @PrimaryKey(PrimaryKey.Field.PK_2) public String getFinprodType2() {
	 * return super.getFinprodType2(); }
	 */

	/**
	 * 获取资产名称
	 *
	 * @return
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * 设置资产名称
	 *
	 * @param finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	/**
	 * 取得 交割日期
	 *
	 * @return 交割日期
	 */
	@NotEmpty(fieldName = "TrdStock002.settleDate", groups = { ValidateGroupEdit.class })
	@Override
	public Date getSettleDate() {
		return super.getSettleDate();
	}

	/**
	 * 获取 ccyStr
	 *
	 * @return ccyStr.
	 */
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
	}
	/**
	 * 获取 payType
	 *
	 * @return payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 获取金额信息
	 *
	 * @return
	 */
	public String getAmtInfo() {
		return amtInfo;
	}

	/**
	 * 设置金额信息
	 *
	 * @param amtInfo
	 */
	public void setAmtInfo(String amtInfo) {
		this.amtInfo = amtInfo;
	}

	/**
	 * 设置 payType
	 *
	 * @param payType
	 *            payType
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

//	/**
//	 * 获取 channelFee
//	 *
//	 * @return channelFee
//	 */
//	public BigDecimal getChannelFee() {
//		return channelFee;
//	}
//
//	/**
//	 * 设置 channelFee
//	 *
//	 * @param channelFee
//	 *            channelFee
//	 */
//	public void setChannelFee(BigDecimal channelFee) {
//		this.channelFee = channelFee;
//	}

	// 通道交易 新增 强成西 2019/03/26 end
	/**
	 * 获取 riskTrialRetDto
	 *
	 * @return riskTrialRetDto.
	 */
	public List<RiskTrialRetDto> getRiskTrialRetDto() {
		return riskTrialRetDto;
	}

	/**
	 * 设置 riskTrialRetDto
	 *
	 * @param riskTrialRetDto
	 *            riskTrialRetDto
	 */
	public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
		this.riskTrialRetDto = riskTrialRetDto;
	}

	/**
	 * 取得 交易日期
	 *
	 * @return 交易日期
	 */
	@NotEmpty(fieldName = "TrdStock002.tradeDate", groups = { ValidateGroupEdit.class })
	@Override
	public Date getTradeDate() {
		return super.getTradeDate();
	}

	/**
	 * 交易数量
	 */
	@Override
	@Amount(fieldName = "TrdStock002.shareAmt", groups = { ValidateGroupEdit.class })
	public BigDecimal getShareAmt() {
		return super.getShareAmt();
	}

	/**
	 * 获取证券托管户
	 *
	 * @return
	 */
	public String getSecAcctId() {
		return secAcctId;
	}

	/**
	 * 设置证券托管户
	 *
	 * @param secAcctId
	 */
	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	/**
	 * 获取 bottomAssetAmount
	 *
	 * @return bottomAssetAmount.
	 */
	public BigDecimal getBottomAssetAmount() {
		return bottomAssetAmount;
	}

	/**
	 * 设置 bottomAssetAmount
	 *
	 * @param bottomAssetAmount
	 *            bottomAssetAmount
	 */
	public void setBottomAssetAmount(BigDecimal bottomAssetAmount) {
		this.bottomAssetAmount = bottomAssetAmount;
	}

//	/**
//	 * 获取通道
//	 */
//	public String getChlId() {
//		return chlId;
//	}
//
//	/**
//	 * 设置通道
//	 */
//	public void setChlId(String chlId) {
//		this.chlId = chlId;
//	}

	/**
	 * 获取上市板
	 *
	 * @return
	 */
	public String getBoard() {
		return board;
	}

	/**
	 * 设置上市板
	 *
	 * @param board
	 */
	public void setBoard(String board) {
		this.board = board;
	}

	/**
	 * 获取上市板
	 *
	 * @return
	 */
	public String getBoardStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.上市板, this.getBoard());
	}

	/**
	 * 获取交易方式
	 *
	 * @return
	 */
	public String getDealModeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.外汇交易模式, this.getDealMode());
	}

	/**
	 * 获取交易方式
	 *
	 * @return
	 */
	public String getDealMode() {
		return dealMode;
	}

	/**
	 * 设置交易方式
	 *
	 * @param dealMode
	 */
	public void setDealMode(String dealMode) {
		this.dealMode = dealMode;
	}

	/**
	 * 取得 交易方向，买入、卖出、收息、还本、申购、赎回等
	 *
	 * @return 交易方向，买入、卖出、收息、还本、申购、赎回等
	 */
	@NotEmpty(fieldName = "TrdStock002.ps", groups = { ValidateGroupEdit.class })
	@Override
	public String getPs() {
		return super.getPs();
	}

	/**
	 * 取得 金融产品代码
	 *
	 * @return 金融产品代码
	 */
	@NotEmpty(fieldName = "TrdStock002.finprodId", groups = { ValidateGroupEdit.class })
	@Override
	public String getFinprodId() {
		return super.getFinprodId();
	}

	/**
	 * 取得 证券管理户代码
	 *
	 * @return 证券管理户代码
	 */

	@Override
	@NotEmpty(fieldName = "TrdStock002.secManageAcctId", groups = { ValidateGroupEdit.class })
	public String getSecManageAcctId() {
		return super.getSecManageAcctId();
	}

	/**
	 * 取得 单位净价
	 *
	 * @return 单位净价
	 */
	@NotEmpty(fieldName = "TrdStock002.unitCprice", groups = { ValidateGroupEdit.class })
	@Override
	public BigDecimal getUnitCprice() {
		return super.getUnitCprice();
	}

	/**
	 * 取得 交易金额
	 *
	 * @return 交易金额
	 */
	@NotEmpty(fieldName = "TrdStock002.tradeAmt", groups = { ValidateGroupEdit.class })
	@Override
	public BigDecimal getTradeAmt() {
		return super.getTradeAmt();
	}


	/**
	 * 取得 到期收益率
	 *
	 * @return 到期收益率
	 */

	@Override
	public BigDecimal getYield() {
		return super.getYield();
	}

	/**
	 * 获取 bussTrdTransferOrder
	 *
	 * @return bussTrdTransferOrder
	 */
	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return bussTrdTransferOrder;
	}

	/**
	 * 设置 bussTrdTransferOrder
	 *
	 * @param bussTrdTransferOrder
	 *            bussTrdTransferOrder
	 */
	public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
		this.bussTrdTransferOrder = bussTrdTransferOrder;
	}

	/**
	 * 获取 trdInvestPersonInfo
	 *
	 * @return trdInvestPersonInfo
	 */
	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return trdInvestPersonInfo;
	}

	/**
	 * 设置 trdInvestPersonInfo
	 *
	 * @param trdInvestPersonInfo
	 *            trdInvestPersonInfo
	 */
	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}

	/**
	 * 获取 validStatus
	 *
	 * @return validStatus
	 */
	public String getValidStatus() {
		return validStatus;
	}

	/**
	 * 设置 validStatus
	 *
	 * @param validStatus
	 *            validStatus
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	/**
	 * 获取 entrustId
	 *
	 * @return entrustId
	 */
	// huwenbin 指令接收交易录单验证数据已存在流程中
	@PrimaryKey(PrimaryKey.Field.PK_3)
	public String getEntrustId() {
		return entrustId;
	}

	/**
	 * 设置 entrustId
	 *
	 * @param entrustId
	 *            entrustId
	 */
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	/**
	 * 获取 portfolioId
	 *
	 * @return portfolioId
	 */
	@NotEmpty(fieldName = "TrdStock002.portfolioId", groups = { ValidateGroupEdit.class })
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * 设置 portfolioId
	 *
	 * @param portfolioId
	 *            portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * 获取 portfolioName
	 *
	 * @return portfolioName
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * 设置 portfolioName
	 *
	 * @param portfolioName
	 *            portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * 获取 finprodName
	 *
	 * @return finprodName
	 */
	public String getFinprodName() {
		return finprodName;
	}

	/**
	 * 设置 finprodName
	 *
	 * @param finprodName
	 *            finprodName
	 */
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	/**
	 * 获取 calendarId
	 *
	 * @return calendarId
	 */
	public String getCalendarId() {
		return calendarId;
	}

	/**
	 * 设置 calendarId
	 *
	 * @param calendarId
	 *            calendarId
	 */
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	/**
	 * 获取 operateType
	 *
	 * @return operateType
	 */
	public String getOperateType() {
		return operateType;
	}

	/**
	 * 设置 operateType
	 *
	 * @param operateType
	 *            operateType
	 */
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	/**
	 * 获取 pageModel
	 *
	 * @return pageModel
	 */
	public String getPageModel() {
		return pageModel;
	}

	/**
	 * 设置 pageModel
	 *
	 * @param pageModel
	 *            pageModel
	 */
	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

	/**
	 * 获取 trdProductDealAdd
	 *
	 * @return trdProductDealAdd
	 */
	public TrdProductDealAdd getTrdProductDealAdd() {
		return trdProductDealAdd;
	}

	/**
	 * 设置 trdProductDealAdd
	 *
	 * @param trdProductDealAdd
	 *            trdProductDealAdd
	 */
	public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
		this.trdProductDealAdd = trdProductDealAdd;
	}

	/**
	 * 获取 ptlTrade
	 *
	 * @return ptlTrade
	 */
	public List<PtlTrade> getPtlTrade() {
		return ptlTrade;
	}

	/**
	 * 设置 ptlTrade
	 *
	 * @param ptlTrade
	 *            ptlTrade
	 */
	public void setPtlTrade(List<PtlTrade> ptlTrade) {
		this.ptlTrade = ptlTrade;
	}

	/**
	 * 获取 paTrdProductDeal
	 *
	 * @return paTrdProductDeal
	 */
	public PaTrdProductDeal getPaTrdProductDeal() {
		return paTrdProductDeal;
	}

	/**
	 * 设置 paTrdProductDeal
	 *
	 * @param paTrdProductDeal
	 *            paTrdProductDeal
	 */
	public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
		this.paTrdProductDeal = paTrdProductDeal;
	}

	/**
	 * 获取 calculateField
	 *
	 * @return calculateField
	 */
	public String getCalculateField() {
		return calculateField;
	}

	/**
	 * 设置 calculateField
	 *
	 * @param calculateField
	 *            calculateField
	 */
	public void setCalculateField(String calculateField) {
		this.calculateField = calculateField;
	}

	/**
	 * 获取 uploadFileList
	 *
	 * @return uploadFileList.
	 */
	public List<UploadFile> getUploadFileList() {
		return uploadFileList;
	}

	/**
	 * 设置 uploadFileList
	 *
	 * @param uploadFileList
	 *            uploadFileList
	 */
	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	/**
	 * 获取 workflowStatus
	 *
	 * @return workflowStatus.
	 */
	public String getWorkflowStatus() {
		return workflowStatus;
	}

	/**
	 * 设置 workflowStatus
	 *
	 * @param workflowStatus
	 *            workflowStatus
	 */
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	/**
	 * 获取 validStatusText
	 *
	 * @return validStatusText
	 */
	public String getValidStatusText() {
		validStatusText = SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, validStatus);
		return validStatusText;
	}

	/**
	 * 设置 validStatusText
	 *
	 * @param validStatusText
	 *            validStatusText
	 */
	public void setValidStatusText(String validStatusText) {
		this.validStatusText = validStatusText;
	}

	/**
	 * 获取 psStr
	 *
	 * @return psStr.
	 */
	public String getPsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
	}

	/**
	 * 获取 getTradeMarketStr
	 *
	 * @return getTradeMarketStr.
	 */
	public String getTradeMarketStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
	}

	@Override
	@NotEmpty(fieldName = "TrdStock002.tradeMarket", groups = { ValidateGroupEdit.class })
	public String getTradeMarket() {
		return super.getTradeMarket();
	}

	@Override
	@NotEmpty(fieldName = "TrdStock002.ccy", groups = { ValidateGroupEdit.class })
	public String getCcy() {
		return super.getCcy();
	}


	/**
	 * 获取 revokeReason
	 *
	 * @return revokeReason.
	 */
	public String getRevokeReason() {
		return revokeReason;
	}

	/**
	 * 设置 revokeReason
	 *
	 * @param revokeReason
	 *            revokeReason
	 */
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
	}

	/**
	 * 获取 systemFinprodId
	 *
	 * @return systemFinprodId.
	 */
	public String getSystemFinprodId() {
		return systemFinprodId;
	}

	/**
	 * 设置 systemFinprodId
	 *
	 * @param systemFinprodId
	 *            systemFinprodId
	 */
	public void setSystemFinprodId(String systemFinprodId) {
		this.systemFinprodId = systemFinprodId;
	}

	public BigDecimal getDealAmt() {
		return dealAmt;
	}

	public void setDealAmt(BigDecimal dealAmt) {
		this.dealAmt = dealAmt;
	}

	public TrdTradeFeeDetail getTrdTradeFeeDetail() {
		return trdTradeFeeDetail;
	}

	public void setTrdTradeFeeDetail(TrdTradeFeeDetail trdTradeFeeDetail) {
		this.trdTradeFeeDetail = trdTradeFeeDetail;
	}

	public BigDecimal getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(BigDecimal stampDuty) {
		this.stampDuty = stampDuty;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getAccAvailableCash() {
		return accAvailableCash;
	}

	public void setAccAvailableCash(BigDecimal accAvailableCash) {
		this.accAvailableCash = accAvailableCash;
	}

	public BigDecimal getTradeCash() {
		return tradeCash;
	}

	public void setTradeCash(BigDecimal tradeCash) {
		this.tradeCash = tradeCash;
	}

	public BigDecimal getAvalibleCash() {
		return avalibleCash;
	}

	public void setAvalibleCash(BigDecimal avalibleCash) {
		this.avalibleCash = avalibleCash;
	}

	public Date getLockMdate() {
		return lockMdate;
	}

	public void setLockMdate(Date lockMdate) {
		this.lockMdate = lockMdate;
	}

	public String getStampDutyIsPayWithTrade() {
		return stampDutyIsPayWithTrade;
	}

	public void setStampDutyIsPayWithTrade(String stampDutyIsPayWithTrade) {
		this.stampDutyIsPayWithTrade = stampDutyIsPayWithTrade;
	}

	public String getCommissionIsPayWithTrade() {
		return commissionIsPayWithTrade;
	}

	public void setCommissionIsPayWithTrade(String commissionIsPayWithTrade) {
		this.commissionIsPayWithTrade = commissionIsPayWithTrade;
	}

	public BigDecimal getEntrustAmt() {
		return entrustAmt;
	}

	public void setEntrustAmt(BigDecimal entrustAmt) {
		this.entrustAmt = entrustAmt;
	}

	public BigDecimal getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(BigDecimal entrustNum) {
		this.entrustNum = entrustNum;
	}

	public BigDecimal getFinishAmt() {
		return finishAmt;
	}

	public void setFinishAmt(BigDecimal finishAmt) {
		this.finishAmt = finishAmt;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

 
	/**
	 * 获取 actPortfolioId
	 *
	 * @return actPortfolioId
	 */
	public String getActPortfolioId() {
		return actPortfolioId;
	}

	/**
	 * 设置 actPortfolioId
	 *
	 * @param actPortfolioId actPortfolioId
	 */
	public void setActPortfolioId(String actPortfolioId) {
		this.actPortfolioId = actPortfolioId;
	}

	/**
	 * 获取 tradeDateStr
	 *
	 * @return tradeDateStr.
	 */
	public String getTradeDateStr() {
		return DateUtil.format(super.getTradeDate());
	}

	public void setTradeDateStr(){
		this.tradeDateStr = getTradeDateStr();
	}
	/**
	 * 获取 settleDate
	 *
	 * @return settleDate.
	 */
	public String getSettleDateStr() {
		return DateUtil.format(super.getSettleDate());
	}

	public void  setSettleDateStr(){
		this.settleDateStr = getSettleDateStr();
	}
}
