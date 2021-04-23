/*
 * 文件名：TrdBondInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Zane.Y
 * 修改时间：2018年9月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.bankuplink.common.dto;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.component.business.basicdata.dto.TrdTradeFeeAddRateDetail;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.cstp.InterfTradSecdealDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.CreditLinesDetail;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 债券交易Dto
 *
 * @author Zane.Y
 * @version 1.0 2018年9月7日
 * @see TrdBondInvestTradeDto
 * @since 1.0
 */
public class TrdBondInvestTradeDto extends TrdProductDeal {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -7438510730454849159L;

	private BigDecimal investRate;

	/**
	 *功能描述:报价编号
	 * @author zhangnian
	 * @date 2021/1/18 0018
	 * @param  * @param null
	 * @return
	 */
	private String quoteId;

	public BigDecimal getInvestRate() {
		return investRate;
	}

	public void setInvestRate(BigDecimal investRate) {
		this.investRate = investRate;
	}

	/**
	 * 取得 交易编号
	 *
	 * @return 交易编号
	 */
	@Override
	public String getTradeId() {
		return super.getTradeId();
	}

	/**
	 * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 *
	 * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 */
	@Override
	public String getFinprodType2() {
		return super.getFinprodType2();
	}

	/**
	 * 有效状态
	 */
	private String validStatus;

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
	 * 资产名称
	 */
	private String finprodName;

	/**
	 * 清算速度
	 */
	private String settleSpeedStr;

	/**
	 * 交易日历
	 */
	private String calendarId;

	/**
	 * 债券一级分类
	 */
	private String windtypeone;

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
	private PtlTrade ptlTrade;

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
	private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

	/**
	 * 附件上传
	 */
	private List<UploadFile> uploadFileList;

	/**
	 * 券面总额
	 */
	private BigDecimal totalFaceValue;

	/**
	 * 是否含权债
	 */
	private String isRight;

	/**
	 * 有效状态
	 */
	private String validStatusText;

	/**
	 * 交易方向
	 */
	private String psText;

	/**
	 * 交易场所
	 */
	private String tradeMarketText;

	/**
	 * 交易市场
	 */
	private String tradeMarketModeText;

	/**
	 * 交易平台
	 */
	private String tradePlatText;
	/**
	 * 交易所账户余额
	 */
	private BigDecimal tradeMarketCash;

	/**
	 * 币种
	 */
	private String ccyText;

	/**
	 * 交易日
	 */
	private String tradeDateText;

	/**
	 * 交割日
	 */
	private String settleDateText;

	/**
	 * 委托时的券面总额
	 */
	private BigDecimal entrustNum;

	/**
	 * 委托时的净价
	 */
	private BigDecimal entrustPrice;

	/**
	 * 委托时的到期收益率
	 */
	private BigDecimal entrustYield;

	/**
	 * 委托时的行权收益率
	 */
	private BigDecimal entrustExerYield;

	/**
	 * 撤销说明
	 */
	private String revokeReason;

	/**
	 * 金融产品代码（系统自动生成）
	 */
	private String systemFinprodId;

	/**
	 * 资产可用持仓
	 */
	private BigDecimal assetPosition;
	/**
	 * 风险试算实体类
	 */
	private List<RiskTrialRetDto> riskTrialRetDto;

	// ---add by 裴家俊 2019/03/28---start---------
	/**
	 * 结算费
	 */
	private BigDecimal settleAmt;

	/**
	 * 交易手续费
	 */
	private BigDecimal procedureAmt;

	/**
	 * 交易手续费费率
	 */
	private BigDecimal procedureAmtRate;

	/**
	 * 佣金
	 */
	private BigDecimal commissionAmt;
	/**
	 * 佣金费率
	 */
	private BigDecimal commissionAmtRate;
	/**
	 * 过户手续费
	 */
	private BigDecimal transferAmt;

	/**
	 * 交易金额(原交易金额被券面总额所用)
	 */
	private BigDecimal tradeRepAmt;
	/**
	 * 证券账户名称
	 */
	private String secAcctName;
	/**
	 * 票面利率
	 */
	private BigDecimal rate;
	/**
	 * 发行人
	 */
	private String customerName;
	/**
	 * 交易对手名称
	 */
	private String counterName;
	/**
	 * 投资经理名称
	 */
	private String customerNameJl;
	/**
	 * 交易员名称
	 */
	private String traderName;
	/**
	 * 当前处理人
	 */
	private String nextUser;

	/**
	 * 交易费用LIST
	 */
	private List<TrdTradeFeeAddRateDetail> tradeFeeList;

	// ---add by 裴家俊 2019/03/28---end---------

	// sjj add 20190409平安需求变更风险试算 start
	/**
	 * 授信试算实体类
	 */
	private List<CreditLinesDetail> creditLinesdetail;

	/**
	 * 画面按钮
	 */
	private String elementId;

	/**
	 * 画面Id
	 */
	private String pageId;

	/**
	 * 导入标识
	 */
	private boolean importFlag;

	/**
	 * 成交回报信息
	 */
	private InterfCstpTrade interfCstpTrade;
	/**
	 * 债券成交回报信息
	 */
	private InterfTradSecdealDto interfTradSecdeal;

	/**
	 * 账户来源
	 */
	private String secAcctSource;

	/**
	 * 批量插入对象
	 */
	private List<TrdBondInvestTradeDto> trdBondInvestTradeDtoList;

	/**
	 * 获取 creditLinesdetail
	 * 
	 * @return creditLinesdetail.
	 */
	public List<CreditLinesDetail> getCreditLinesdetail() {
		return creditLinesdetail;
	}

	/**
	 * 设置 creditLinesdetail
	 * 
	 * @param creditLinesdetail creditLinesdetail
	 */
	public void setCreditLinesdetail(List<CreditLinesDetail> creditLinesdetail) {
		this.creditLinesdetail = creditLinesdetail;
	}

	// sjj add 20190409平安需求变更风险试算 end
	/**
	 * MUREX导入状态
	 */
	private String orderRetStatus;

	/**
	 * QUERRYPARTY返回的发送murex的交易对手
	 */
	private String mxCounterId;

	/**
	 * 批复号
	 */
	private String serialNo;

	/**
	 * 批复号id(页面显示用)
	 */
	private String serialNoId;

	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 是否签署非格式合同
	 */
	private String unFormatted;
	/**
	 * 可用批复余额
	 */
	private BigDecimal quotaAmt;

	/**
	 * 暂存债券代码
	 */
	private String finprodNameTemp;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getUnFormatted() {
		return unFormatted;
	}

	public void setUnFormatted(String unFormatted) {
		this.unFormatted = unFormatted;
	}

	public BigDecimal getQuotaAmt() {
		return quotaAmt;
	}

	public void setQuotaAmt(BigDecimal quotaAmt) {
		this.quotaAmt = quotaAmt;
	}

	public String getFinprodNameTemp() {
		return finprodNameTemp;
	}

	public void setFinprodNameTemp(String finprodNameTemp) {
		this.finprodNameTemp = finprodNameTemp;
	}

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
	 * @param riskTrialRetDto riskTrialRetDto
	 */
	public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
		this.riskTrialRetDto = riskTrialRetDto;
	}

	/**
	 * 取得 交易日期
	 *
	 * @return 交易日期
	 */
	@NotEmpty(fieldName = "TrdBondInvestTrade002.tradeDate")
	@Override
	public Date getTradeDate() {
		return super.getTradeDate();
	}

	/**
	 * 取得 交割日期
	 *
	 * @return 交割日期
	 */
	@Override
	public Date getSettleDate() {
		return super.getSettleDate();
	}

	/**
	 * 取得 交易方向，买入、卖出、收息、还本、申购、赎回等
	 *
	 * @return 交易方向，买入、卖出、收息、还本、申购、赎回等
	 */
	@Override
	public String getPs() {
		return super.getPs();
	}

	/**
	 * 取得 金融产品代码
	 *
	 * @return 金融产品代码
	 */
	@NotEmpty(fieldName = "TrdBondInvestTrade002.finprodId")
	@Override
	public String getFinprodId() {
		return super.getFinprodId();
	}

	/**
	 * 取得 交易场所，银行间、上交所、深交所、柜面等
	 *
	 * @return 交易场所，银行间、上交所、深交所、柜面等
	 */
	@Override
	public String getTradeMarket() {
		return super.getTradeMarket();
	}

	/**
	 * 取得 交易市场，一级市场、二级市场
	 *
	 * @return 交易市场，一级市场、二级市场
	 */
	@Override
	public String getTradeMarketMode() {
		return super.getTradeMarketMode();
	}

	/**
	 * 取得 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
	 *
	 * @return 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
	 */
	@Override
	public String getTradePlat() {
		return super.getTradePlat();
	}

	/**
	 * 取得 证券管理户代码
	 *
	 * @return 证券管理户代码
	 */
	@Override
	public String getSecManageAcctId() {
		return super.getSecManageAcctId();
	}

	/**
	 * 取得 交易金额
	 *
	 * @return 交易金额
	 */
	@Override
	public BigDecimal getTradeAmt() {
		return super.getTradeAmt();
	}

	/**
	 * 取得 单位净价
	 *
	 * @return 单位净价
	 */
	@Override
	public BigDecimal getUnitCprice() {
		return super.getUnitCprice();
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
	 * @param bussTrdTransferOrder bussTrdTransferOrder
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
	 * @param trdInvestPersonInfo trdInvestPersonInfo
	 */
	public void setTrdInvestPersonInfo(TrdInvestPersonInfo002Dto trdInvestPersonInfo) {
		this.trdInvestPersonInfo = trdInvestPersonInfo;
	}

	/**
	 * 获取 isExercise
	 *
	 * @return isExercise
	 */
	public String getIsExercise() {
		return isExercise;
	}

	/**
	 * 设置 isExercise
	 *
	 * @param isExercise isExercise
	 */
	public void setIsExercise(String isExercise) {
		this.isExercise = isExercise;
	}

	/**
	 * 是否行权
	 */
	private String isExercise;

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
	 * @param validStatus validStatus
	 */
	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	/**
	 * 获取 entrustId
	 *
	 * @return entrustId
	 */
	public String getEntrustId() {
		return entrustId;
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
	 * @return portfolioId
	 */
	public String getPortfolioId() {
		return portfolioId;
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
	 * @return portfolioName
	 */
	public String getPortfolioName() {
		return portfolioName;
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
	 * @param finprodName finprodName
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
	 * @param calendarId calendarId
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
	 * @param operateType operateType
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
	 * @param pageModel pageModel
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
	 * @param trdProductDealAdd trdProductDealAdd
	 */
	public void setTrdProductDealAdd(TrdProductDealAdd trdProductDealAdd) {
		this.trdProductDealAdd = trdProductDealAdd;
	}

	/**
	 * 获取 ptlTrade
	 *
	 * @return ptlTrade
	 */
	public PtlTrade getPtlTrade() {
		return ptlTrade;
	}

	/**
	 * 设置 ptlTrade
	 *
	 * @param ptlTrade ptlTrade
	 */
	public void setPtlTrade(PtlTrade ptlTrade) {
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
	 * @param paTrdProductDeal paTrdProductDeal
	 */
	public void setPaTrdProductDeal(PaTrdProductDeal paTrdProductDeal) {
		this.paTrdProductDeal = paTrdProductDeal;
	}

	/**
	 * 获取 windtypeone
	 *
	 * @return windtypeone
	 */
	public String getWindtypeone() {
		return windtypeone;
	}

	/**
	 * 设置 windtypeone
	 *
	 * @param windtypeone windtypeone
	 */
	public void setWindtypeone(String windtypeone) {
		this.windtypeone = windtypeone;
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
	 * @param calculateField calculateField
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
	 * @param uploadFileList uploadFileList
	 */
	public void setUploadFileList(List<UploadFile> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	/**
	 * 获取 totalFaceValue
	 *
	 * @return totalFaceValue.
	 */
	public BigDecimal getTotalFaceValue() {
		return totalFaceValue;
	}

	/**
	 * 设置 totalFaceValue
	 *
	 * @param totalFaceValue totalFaceValue
	 */
	public void setTotalFaceValue(BigDecimal totalFaceValue) {
		this.totalFaceValue = totalFaceValue;
	}

	/**
	 * 获取 isRight
	 *
	 * @return isRight.
	 */
	public String getIsRight() {
		return isRight;
	}

	/**
	 * 设置 isRight
	 *
	 * @param isRight isRight
	 */
	public void setIsRight(String isRight) {
		this.isRight = isRight;
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
	 * @param workflowStatus workflowStatus
	 */
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	/**
	 * 获取 ccyText
	 *
	 * @return validStatusText
	 */
	public String getValidStatusText() {
		validStatusText = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易流程状态, validStatus);
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
	 * 获取 psText
	 *
	 * @return psText.
	 */
	public String getPsText() {
		psText = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, this.getPs());
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
	 * 获取 tradeMarketText
	 *
	 * @return tradeMarketText.
	 */
	public String getTradeMarketText() {
		tradeMarketText = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易场所, this.getTradeMarket());
		return tradeMarketText;
	}

	/**
	 * 设置 tradeMarketText
	 *
	 * @param tradeMarketText tradeMarketText
	 */
	public void setTradeMarketText(String tradeMarketText) {
		this.tradeMarketText = tradeMarketText;
	}

	/**
	 * 获取 tradeMarketModeText
	 *
	 * @return tradeMarketModeText.
	 */
	public String getTradeMarketModeText() {
		tradeMarketModeText = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易市场, this.getTradeMarketMode());
		return tradeMarketModeText;
	}

	/**
	 * 设置 tradeMarketModeText
	 *
	 * @param tradeMarketModeText tradeMarketModeText
	 */
	public void setTradeMarketModeText(String tradeMarketModeText) {
		this.tradeMarketModeText = tradeMarketModeText;
	}

	/**
	 * 获取 tradePlatText
	 *
	 * @return tradePlatText.
	 */
	public String getTradePlatText() {
		tradePlatText = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易平台, this.getTradePlat());
		return tradePlatText;
	}

	/**
	 * 设置 tradePlatText
	 *
	 * @param tradePlatText tradePlatText
	 */
	public void setTradePlatText(String tradePlatText) {
		this.tradePlatText = tradePlatText;
	}

	/**
	 * 获取 ccyText
	 *
	 * @return ccyText.
	 */
	public String getCcyText() {
		ccyText = SysDataUtil.getNameFromDataDict(BussinessDictCode.币种, this.getCcy());
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
	 * 获取 tradeDateText
	 *
	 * @return tradeDateText.
	 */
	public String getTradeDateText() {
		tradeDateText = DateUtil.format(this.getTradeDate());
		return tradeDateText;
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
	 * 获取 settleDateText
	 *
	 * @return settleDateText.
	 */
	public String getSettleDateText() {
		settleDateText = DateUtil.format(this.getSettleDate());
		return settleDateText;
	}

	/**
	 * 设置 settleDateText
	 *
	 * @param settleDateText settleDateText
	 */
	public void setSettleDateText(String settleDateText) {
		this.settleDateText = settleDateText;
	}

	/**
	 * 获取 settleSpeedStr
	 *
	 * @return settleSpeedStr.
	 */
	public String getSettleSpeedStr() {
		return settleSpeedStr;
	}

	/**
	 * 设置 settleSpeedStr
	 *
	 * @param settleSpeedStr settleSpeedStr
	 */
	public void setSettleSpeedStr(String settleSpeedStr) {
		this.settleSpeedStr = settleSpeedStr;
	}

	/**
	 * 获取 entrustNum
	 *
	 * @return entrustNum.
	 */
	public BigDecimal getEntrustNum() {
		return entrustNum;
	}

	/**
	 * 设置 entrustNum
	 *
	 * @param entrustNum entrustNum
	 */
	public void setEntrustNum(BigDecimal entrustNum) {
		this.entrustNum = entrustNum;
	}

	/**
	 * 获取 entrustPrice
	 *
	 * @return entrustPrice.
	 */
	public BigDecimal getEntrustPrice() {
		return entrustPrice;
	}

	/**
	 * 设置 entrustPrice
	 *
	 * @param entrustPrice entrustPrice
	 */
	public void setEntrustPrice(BigDecimal entrustPrice) {
		this.entrustPrice = entrustPrice;
	}

	/**
	 * 获取 entrustYield
	 *
	 * @return entrustYield.
	 */
	public BigDecimal getEntrustYield() {
		return entrustYield;
	}

	/**
	 * 设置 entrustYield
	 *
	 * @param entrustYield entrustYield
	 */
	public void setEntrustYield(BigDecimal entrustYield) {
		this.entrustYield = entrustYield;
	}

	/**
	 * 获取 entrustExerYield
	 *
	 * @return entrustExerYield.
	 */
	public BigDecimal getEntrustExerYield() {
		return entrustExerYield;
	}

	/**
	 * 设置 entrustExerYield
	 *
	 * @param entrustExerYield entrustExerYield
	 */
	public void setEntrustExerYield(BigDecimal entrustExerYield) {
		this.entrustExerYield = entrustExerYield;
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
	 * @param revokeReason revokeReason
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
	 * @param systemFinprodId systemFinprodId
	 */
	public void setSystemFinprodId(String systemFinprodId) {
		this.systemFinprodId = systemFinprodId;
	}

	/**
	 * 获取 assetPosition
	 *
	 * @return assetPosition.
	 */
	public BigDecimal getAssetPosition() {
		return assetPosition;
	}

	/**
	 * 设置 assetPosition
	 *
	 * @param assetPosition assetPosition
	 */
	public void setAssetPosition(BigDecimal assetPosition) {
		this.assetPosition = assetPosition;
	}

	/**
	 * 获取 orderRetStatus
	 *
	 * @return orderRetStatus.
	 */
	public String getOrderRetStatus() {
		return orderRetStatus;
	}

	/**
	 * 设置 orderRetStatus
	 *
	 * @param orderRetStatus orderRetStatus
	 */
	public void setOrderRetStatus(String orderRetStatus) {
		this.orderRetStatus = orderRetStatus;
	}

	/**
	 * 获取 mxCounterId
	 * 
	 * @return mxCounterId.
	 */
	public String getMxCounterId() {
		return mxCounterId;
	}

	/**
	 * 设置 mxCounterId
	 * 
	 * @param mxCounterId mxCounterId
	 */
	public void setMxCounterId(String mxCounterId) {
		this.mxCounterId = mxCounterId;
	}

	/**
	 * 获取MUREX状态字符串
	 * 
	 * @return OrderRetStatusStr
	 */
	public String getOrderRetStatusStr() {
		if (StringUtil.isNotEmpty(this.getOrderRetStatus())) {
			//return SysDataUtil.getNameFromDataDict(BussinessDictCode.MUREX状态, this.getOrderRetStatus());
		}
		return this.getOrderRetStatus();
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	public BigDecimal getProcedureAmt() {
		return procedureAmt;
	}

	public void setProcedureAmt(BigDecimal procedureAmt) {
		this.procedureAmt = procedureAmt;
	}

	public BigDecimal getCommissionAmt() {
		return commissionAmt;
	}

	public void setCommissionAmt(BigDecimal commissionAmt) {
		this.commissionAmt = commissionAmt;
	}

	public BigDecimal getTradeRepAmt() {
		return tradeRepAmt;
	}

	public void setTradeRepAmt(BigDecimal tradeRepAmt) {
		this.tradeRepAmt = tradeRepAmt;
	}

	public List<TrdTradeFeeAddRateDetail> getTradeFeeList() {
		return tradeFeeList;
	}

	public void setTradeFeeList(List<TrdTradeFeeAddRateDetail> tradeFeeList) {
		this.tradeFeeList = tradeFeeList;
	}

	/**
	 * 取得 交易所账户余额
	 * 
	 * @return
	 */
	public BigDecimal getTradeMarketCash() {
		return tradeMarketCash;
	}

	/**
	 * 设置 交易所账户余额
	 * 
	 * @return
	 */
	public void setTradeMarketCash(BigDecimal tradeMarketCash) {
		this.tradeMarketCash = tradeMarketCash;
	}

	/**
	 * 交易手续费费率
	 * 
	 * @return 交易手续费费率
	 */
	public BigDecimal getProcedureAmtRate() {
		return procedureAmtRate;
	}

	/**
	 * 交易手续费费率
	 * 
	 * @param procedureAmtRate 交易手续费费率
	 */
	public void setProcedureAmtRate(BigDecimal procedureAmtRate) {
		this.procedureAmtRate = procedureAmtRate;
	}

	/**
	 * 佣金费率
	 * 
	 * @return 佣金费率
	 */
	public BigDecimal getCommissionAmtRate() {
		return commissionAmtRate;
	}

	/**
	 * 佣金费率
	 * 
	 * @param commissionAmtRate 佣金费率
	 */
	public void setCommissionAmtRate(BigDecimal commissionAmtRate) {
		this.commissionAmtRate = commissionAmtRate;
	}

	/**
	 * 过户结算费
	 * 
	 * @return 过户结算费
	 */
	public BigDecimal getTransferAmt() {
		return transferAmt;
	}

	/**
	 * 过户结算费
	 * 
	 * @param transferAmt 过户结算费
	 */
	public void setTransferAmt(BigDecimal transferAmt) {
		this.transferAmt = transferAmt;
	}

	/**
	 * 获取 secAcctName
	 * 
	 * @return secAcctName.
	 */
	public String getSecAcctName() {
		return secAcctName;
	}

	/**
	 * 设置 secAcctName
	 * 
	 * @param secAcctName secAcctName
	 */
	public void setSecAcctName(String secAcctName) {
		this.secAcctName = secAcctName;
	}

	/**
	 * 获取 rate
	 * 
	 * @return rate.
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * 设置 rate
	 * 
	 * @param rate rate
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * 获取 customerName
	 * 
	 * @return customerName.
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 设置 customerName
	 * 
	 * @param customerName customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 获取 counterName
	 * 
	 * @return counterName.
	 */
	public String getCounterName() {
		return counterName;
	}

	/**
	 * 设置 counterName
	 * 
	 * @param counterName counterName
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	/**
	 * 债券到期日字符
	 * 
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String getMdateStr() {
		return DateUtil.format(this.getMdate());
	}

	/**
	 * 获取 customerNameJl
	 * 
	 * @return customerNameJl.
	 */
	public String getCustomerNameJl() {
		return customerNameJl;
	}

	/**
	 * 设置 customerNameJl
	 * 
	 * @param customerNameJl customerNameJl
	 */
	public void setCustomerNameJl(String customerNameJl) {
		this.customerNameJl = customerNameJl;
	}

	/**
	 * 获取 traderName
	 * 
	 * @return traderName.
	 */
	public String getTraderName() {
		return traderName;
	}

	/**
	 * 设置 traderName
	 * 
	 * @param traderName traderName
	 */
	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	/**
	 * 投资目的
	 * 
	 * @return 投资目的
	 * @see
	 * @since 1.0
	 */
	public String getInvAimStr() {
		return super.getInvAim();
	}

	/**
	 * 获取 nextUser
	 * 
	 * @return nextUser.
	 */
	public String getNextUser() {
		return nextUser;
	}

	/**
	 * 设置 nextUser
	 * 
	 * @param nextUser nextUser
	 */
	public void setNextUser(String nextUser) {
		this.nextUser = nextUser;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public boolean isImportFlag() {
		return importFlag;
	}

	public void setImportFlag(boolean importFlag) {
		this.importFlag = importFlag;
	}

	/**
	 * 获取 interfCstpTrade
	 * 
	 * @return interfCstpTrade.
	 */
	public InterfCstpTrade getInterfCstpTrade() {
		return interfCstpTrade;
	}

	/**
	 * 设置 interfCstpTrade
	 * 
	 * @param interfCstpTrade interfCstpTrade
	 */
	public void setInterfCstpTrade(InterfCstpTrade interfCstpTrade) {
		this.interfCstpTrade = interfCstpTrade;
	}

	/**
	 * 获取 trdBondInvestTradeDtoList
	 * 
	 * @return trdBondInvestTradeDtoList.
	 */
	public List<TrdBondInvestTradeDto> getTrdBondInvestTradeDtoList() {
		return trdBondInvestTradeDtoList;
	}

	/**
	 * 设置 trdBondInvestTradeDtoList
	 * 
	 * @param trdBondInvestTradeDtoList trdBondInvestTradeDtoList
	 */
	public void setTrdBondInvestTradeDtoList(List<TrdBondInvestTradeDto> trdBondInvestTradeDtoList) {
		this.trdBondInvestTradeDtoList = trdBondInvestTradeDtoList;
	}

	public String getSerialNoId() {
		return serialNoId;
	}

	public void setSerialNoId(String serialNoId) {
		this.serialNoId = serialNoId;
	}

	public String getSecAcctSource() {
		return secAcctSource;
	}

	public void setSecAcctSource(String secAcctSource) {
		this.secAcctSource = secAcctSource;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public InterfTradSecdealDto getInterfTradSecdeal() {
		return interfTradSecdeal;
	}

	public void setInterfTradSecdeal(InterfTradSecdealDto interfTradSecdeal) {
		this.interfTradSecdeal = interfTradSecdeal;
	}
}
