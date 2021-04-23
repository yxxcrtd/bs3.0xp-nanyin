/*
 * 文件名：TrdLendInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.cstp.InterfTradRepoasstDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TrdRepoTrade002Dto extends TrdProductDeal {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = -441611713511199822L;

	/**
	 * 有效状态
	 */
	private String validStatus;

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
	 * 首期交付日
	 */
	private Date vpayDate;
	/**
	 * 到期交付日
	 */
	private Date mpayDate;
	/**
	 * 实际占款天数
	 */
	private Long actCapDays;
	/**
	 * 额度验证结果
	 */
	private CreditAmtSelDto creditList;

	// add by zhangliang 20190923 性能优化 start
	/**
	 * 账户来源
	 */
	private String secAcctSource;
	/**
	 * 登记托管机构
	 */
	private String secRegistOrg;
	// add by zhangliang 20190923 性能优化 end

	/**
	 * 回购信息List
	 */
	private List<TrdRepoTrade002Dto> trdRepoTrade002DtoList;

	/**
	 * 成交回报信息
	 */
	private InterfCstpTrade interfCstpTrade;

	/**
	 * 回购成交回报信息
	 */
	private InterfOpicsTradRepo interfOpicsTradRepo;

	/**
	 * 质押品信息
	*/
	private List<InterfTradRepoasstDto> interfTradRepoassts;

	/**
	 *功能描述:报价编号
	 * @author zhangnian
	 * @date 2021/1/18 0018
	 * @param  * @param null
	 * @return
	 */
	private String quoteId;

	/**
	 * 中债全价总额
	 */
	@JsonSerialize(using= BigDecimalSerializer.class)
	private BigDecimal cbFpriceAmt;

	/**
	 * 上清全价总额
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal scFpriceAmt;

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
	 * 委托的金额
	 */
	private BigDecimal entrustAmt;

	/**
	 * 委托的回购利率
	 */
	private BigDecimal entrustPrice;

	/**
	 * 委托的回购起息日
	 */
	private Date entrustVdate;

	/**
	 * 委托的回购到期日
	 */
	private Date entrustMdate;

	/**
	 * 委托的回购期限From
	 */
	private Long entrustTermFrom;

	/**
	 * 委托的回购期限To
	 */
	private Long entrustTermTo;

	/**
	 * 委托的期限品种
	 */
	private String entrustTermType;

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
	 * 交易所账户余额
	 */
	private BigDecimal tradeMarketCash;

	/**
	 * 银行间成交编号
	 */

	/**
	 * 交易日期
	 */

	/**
	 * 回购类型：质押式协议回购，质押式竞价回购
	 */
	private String repoType;

	/**
	 * 交易场所
	 */

	/**
	 * 交易方向：正回购、逆回购
	 */

	/**
	 * 清算速度
	 */
	private String settleSpeedStr;

	/**
	 * 交易日历
	 */
	private String calendarId;

	/**
	 * 期限品种
	 */
	private String termTypeReback;

	/**
	 * 起息日
	 */

	/**
	 * 到期日
	 */

	/**
	 * 期限（天)
	 */
	private Long term;

	/**
	 * 计息基础
	 */
	private String basis;

	/**
	 * 资金可用日
	 */
	private Date avaFundsDate;

	/**
	 * 证券托管户
	 */
	private String secAcctId;


	/**
	 * 可使用标准券额度
	 */
	private BigDecimal avaStandardStockPosition;

	/**
	 * 币种
	 */

	/**
	 * 本金金额
	 */
	private BigDecimal prinAmt;

	/**
	 * 回购利率%
	 */
	private BigDecimal rebackPrice;

	/**
	 * 回购利息
	 */
	private BigDecimal rebackIntAmt;

	/**
	 * 到期结算金额
	 */
	private BigDecimal mdateSettleAmt;

	/**
	 * 人员信息
	 */
	private TrdInvestPersonInfo002Dto trdInvestPersonInfo;

	/**
	 * 添加质押券信息
	 */
	private BussRepoSecDto bussRepoSec;

	/**
	 * 金融产品交易附属表
	 */
	private TrdProductDealAdd trdProductDealAdd;

	/**
	 * 金融产品交易附属业务流程表
	 */
	private WflTrdProductDealAdd wflTrdProductDealAdd;

	/**
	 * 组合交易
	 */
	private PtlTrade ptlTrade;

	/**
	 * 金融产品交易扩展业务流程表
	 */
	private WflPaTrdProductDeal wflPaTrdProductDeal;

	/**
	 * 划款指令
	 */
	private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

	/**
	 * 金融产品交易Entity
	 */
	private TrdProductDeal trdProductDeal;

	/**
	 * 金融产品交易业务流程信息
	 */
	private WflTrdProductDeal wflTrdProductDeal;

	/**
	 * 金融产品交易业务流程其他信息
	 */
	private WflTrdProductDealOth wflTrdProductDealOth;

	/**
	 * 金融产品分类
	 */
	private FinProductType finProductType;

	/**
	 * 交易类金融产品Entity
	 */
	private FinTradeProductDto finTradeProduct;

	/**
	 * 风险试算实体类
	 */
	private List<RiskTrialRetDto> riskTrialRetDto;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件上传
	 */
	private List<UploadFile> uploadFileList;

	/**
	 * 隐藏字段：是否保本
	 */
	private String isGuarantee;

	/**
	 * 撤销说明
	 */
	private String revokeReason;

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
	 * 交易费用LIST
	 */
	private List<TrdTradeFeeDetail> tradeFeeList;

	/**
	 * 是否是多个质押券
	 */
	private String isGrade;
	// ---add by 裴家俊 2019/03/28---end---------

	/**
	 * QUERRYPARTY返回的发送murex的交易对手
	 */
	private String mxCounterId;

	/**
	 * 画面按钮
	 */
	private String elementId;

	/**
	 * 画面Id
	 */
	private String pageId;

	/**
	 * 是否是交易所导入数据
	 */
	private boolean importFlag;

	/**
	 * 取得 交易日期
	 *
	 * @return 交易日期
	 */
	@Override
	public Date getTradeDate() {
		return super.getTradeDate();
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
	 * 取得 起息日
	 *
	 * @return 起息日
	 */
	@Override
	public Date getVdate() {
		return super.getVdate();
	}

	/**
	 * 取得 到期日
	 *
	 * @return 到期日
	 */
	@Override
	public Date getMdate() {
		return super.getMdate();
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
	 * 获取 pageModel
	 *
	 * @rturn pageModel
	 */
	public String getPageModel() {
		return this.pageModel;
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
	 * 获取 entrustAmt
	 *
	 * @rturn entrustAmt
	 */
	public BigDecimal getEntrustAmt() {
		return this.entrustAmt;
	}

	/**
	 * 设置 entrustAmt
	 *
	 * @param entrustAmt entrustAmt
	 */
	public void setEntrustAmt(BigDecimal entrustAmt) {
		this.entrustAmt = entrustAmt;
	}

	/**
	 * 获取 entrustPrice
	 *
	 * @rturn entrustPrice
	 */
	public BigDecimal getEntrustPrice() {
		return this.entrustPrice;
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
	 * 获取 entrustVdate
	 *
	 * @rturn entrustVdate
	 */
	public Date getEntrustVdate() {
		return this.entrustVdate;
	}

	/**
	 * 设置 entrustVdate
	 *
	 * @param entrustVdate entrustVdate
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setEntrustVdate(Date entrustVdate) {
		this.entrustVdate = entrustVdate;
	}

	/**
	 * 获取 entrustMdate
	 *
	 * @rturn entrustMdate
	 */
	public Date getEntrustMdate() {
		return this.entrustMdate;
	}

	/**
	 * 设置 entrustMdate
	 *
	 * @param entrustMdate entrustMdate
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setEntrustMdate(Date entrustMdate) {
		this.entrustMdate = entrustMdate;
	}

	/**
	 * 获取 entrustTermFrom
	 *
	 * @rturn entrustTermFrom
	 */
	public Long getEntrustTermFrom() {
		return this.entrustTermFrom;
	}

	/**
	 * 设置 entrustTermFrom
	 *
	 * @param entrustTermFrom entrustTermFrom
	 */
	public void setEntrustTermFrom(Long entrustTermFrom) {
		this.entrustTermFrom = entrustTermFrom;
	}

	/**
	 * 获取 entrustTermTo
	 *
	 * @rturn entrustTermTo
	 */
	public Long getEntrustTermTo() {
		return this.entrustTermTo;
	}

	/**
	 * 设置 entrustTermTo
	 *
	 * @param entrustTermTo entrustTermTo
	 */
	public void setEntrustTermTo(Long entrustTermTo) {
		this.entrustTermTo = entrustTermTo;
	}

	/**
	 * 获取 entrustTermType
	 *
	 * @rturn entrustTermType
	 */
	public String getEntrustTermType() {
		return this.entrustTermType;
	}

	/**
	 * 设置 entrustTermType
	 *
	 * @param entrustTermType entrustTermType
	 */
	public void setEntrustTermType(String entrustTermType) {
		this.entrustTermType = entrustTermType;
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
	 * 获取银行间成交编号
	 *
	 * @rturn fTradeId
	 */
	@Override
	public String getfTradeId() {
		return super.getfTradeId();
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
	 * 获取 ps
	 *
	 * @rturn ps
	 */
	@Override
	public String getPs() {
		return super.getPs();
	}

	/**
	 * 获取 settleSpeedStr
	 *
	 * @rturn settleSpeedStr
	 */
	public String getSettleSpeedStr() {
		return this.settleSpeedStr;
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
	 * 获取 calendarId
	 *
	 * @rturn calendarId
	 */
	public String getCalendarId() {
		return this.calendarId;
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
	 * 获取 term
	 *
	 * @rturn term
	 */
	public Long getTerm() {
		return this.term;
	}

	/**
	 * 设置 term
	 *
	 * @param term term
	 */
	public void setTerm(Long term) {
		this.term = term;
	}

	/**
	 * 获取 basis
	 *
	 * @rturn basis
	 */
	public String getBasis() {
		return this.basis;
	}

	/**
	 * 设置 basis
	 *
	 * @param basis basis
	 */
	public void setBasis(String basis) {
		this.basis = basis;
	}

	/**
	 * 获取 avaFundsDate
	 *
	 * @rturn avaFundsDate
	 */
	public Date getAvaFundsDate() {
		return this.avaFundsDate;
	}

	/**
	 * 设置 avaFundsDate
	 *
	 * @param avaFundsDate avaFundsDate
	 */
	@JsonDeserialize(using = CustomDateDeserialize.class)
	public void setAvaFundsDate(Date avaFundsDate) {
		this.avaFundsDate = avaFundsDate;
	}

	/**
	 * 获取 secAcctId
	 *
	 * @rturn secAcctId
	 */
	public String getSecAcctId() {
		return this.secAcctId;
	}

	/**
	 * 设置 secAcctId
	 *
	 * @param secAcctId secAcctId
	 */
	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	/**
	 * 获取 avaStandardStockPosition
	 *
	 * @rturn avaStandardStockPosition
	 */
	public BigDecimal getAvaStandardStockPosition() {
		return this.avaStandardStockPosition;
	}

	/**
	 * 设置 avaStandardStockPosition
	 *
	 * @param avaStandardStockPosition avaStandardStockPosition
	 */
	public void setAvaStandardStockPosition(BigDecimal avaStandardStockPosition) {
		this.avaStandardStockPosition = avaStandardStockPosition;
	}

	/**
	 * 获取 ccy
	 *
	 * @rturn ccy
	 */
	@Override
	public String getCcy() {
		return super.getCcy();
	}

	/**
	 * 获取 prinAmt
	 *
	 * @rturn prinAmt
	 */
	@Override
	public BigDecimal getPrinAmt() {
		return this.prinAmt;
	}

	/**
	 * 设置 prinAmt
	 *
	 * @param prinAmt prinAmt
	 */
	@Override
	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	/**
	 * 获取 rebackPrice
	 *
	 * @rturn rebackPrice
	 */
	public BigDecimal getRebackPrice() {
		return this.rebackPrice;
	}

	/**
	 * 设置 rebackPrice
	 *
	 * @param rebackPrice rebackPrice
	 */
	public void setRebackPrice(BigDecimal rebackPrice) {
		this.rebackPrice = rebackPrice;
	}

	/**
	 * 获取 rebackIntAmt
	 *
	 * @rturn rebackIntAmt
	 */
	public BigDecimal getRebackIntAmt() {
		return this.rebackIntAmt;
	}

	/**
	 * 设置 rebackIntAmt
	 *
	 * @param rebackIntAmt rebackIntAmt
	 */
	public void setRebackIntAmt(BigDecimal rebackIntAmt) {
		this.rebackIntAmt = rebackIntAmt;
	}

	/**
	 * 获取 mdateSettleAmt
	 *
	 * @rturn mdateSettleAmt
	 */
	public BigDecimal getMdateSettleAmt() {
		return this.mdateSettleAmt;
	}

	/**
	 * 设置 mdateSettleAmt
	 *
	 * @param mdateSettleAmt mdateSettleAmt
	 */
	public void setMdateSettleAmt(BigDecimal mdateSettleAmt) {
		this.mdateSettleAmt = mdateSettleAmt;
	}

	/**
	 * 获取 trdInvestPersonInfo
	 *
	 * @rturn trdInvestPersonInfo
	 */
	public TrdInvestPersonInfo002Dto getTrdInvestPersonInfo() {
		return this.trdInvestPersonInfo;
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
	 * 获取 bussRepoSec
	 *
	 * @rturn bussRepoSec
	 */
	public BussRepoSecDto getBussRepoSec() {
		return this.bussRepoSec;
	}

	/**
	 * 设置 bussRepoSec
	 *
	 * @param bussRepoSec bussRepoSec
	 */
	public void setBussRepoSec(BussRepoSecDto bussRepoSec) {
		this.bussRepoSec = bussRepoSec;
	}

	/**
	 * 获取 trdProductDealAdd
	 *
	 * @rturn trdProductDealAdd
	 */
	public TrdProductDealAdd getTrdProductDealAdd() {
		return this.trdProductDealAdd;
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
	 * 获取 wflTrdProductDealAdd
	 *
	 * @rturn wflTrdProductDealAdd
	 */
	public WflTrdProductDealAdd getWflTrdProductDealAdd() {
		return this.wflTrdProductDealAdd;
	}

	/**
	 * 设置 wflTrdProductDealAdd
	 *
	 * @param wflTrdProductDealAdd wflTrdProductDealAdd
	 */
	public void setWflTrdProductDealAdd(WflTrdProductDealAdd wflTrdProductDealAdd) {
		this.wflTrdProductDealAdd = wflTrdProductDealAdd;
	}

	/**
	 * 获取 ptlTrade
	 *
	 * @rturn ptlTrade
	 */
	public PtlTrade getPtlTrade() {
		return this.ptlTrade;
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
	 * 获取 wflPaTrdProductDeal
	 *
	 * @rturn wflPaTrdProductDeal
	 */
	public WflPaTrdProductDeal getWflPaTrdProductDeal() {
		return this.wflPaTrdProductDeal;
	}

	/**
	 * 设置 wflPaTrdProductDeal
	 *
	 * @param wflPaTrdProductDeal wflPaTrdProductDeal
	 */
	public void setWflPaTrdProductDeal(WflPaTrdProductDeal wflPaTrdProductDeal) {
		this.wflPaTrdProductDeal = wflPaTrdProductDeal;
	}

	/**
	 * 获取 bussTrdTransferOrder
	 *
	 * @rturn bussTrdTransferOrder
	 */
	public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
		return this.bussTrdTransferOrder;
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
	 * 获取 trdProductDeal
	 *
	 * @rturn trdProductDeal
	 */
	public TrdProductDeal getTrdProductDeal() {
		return this.trdProductDeal;
	}

	/**
	 * 设置 trdProductDeal
	 *
	 * @param trdProductDeal trdProductDeal
	 */
	public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
		this.trdProductDeal = trdProductDeal;
	}

	/**
	 * 获取 wflTrdProductDeal
	 *
	 * @rturn wflTrdProductDeal
	 */
	public WflTrdProductDeal getWflTrdProductDeal() {
		return this.wflTrdProductDeal;
	}

	/**
	 * 设置 wflTrdProductDeal
	 *
	 * @param wflTrdProductDeal wflTrdProductDeal
	 */
	public void setWflTrdProductDeal(WflTrdProductDeal wflTrdProductDeal) {
		this.wflTrdProductDeal = wflTrdProductDeal;
	}

	/**
	 * 获取 wflTrdProductDealOth
	 *
	 * @rturn wflTrdProductDealOth
	 */
	public WflTrdProductDealOth getWflTrdProductDealOth() {
		return this.wflTrdProductDealOth;
	}

	/**
	 * 设置 wflTrdProductDealOth
	 *
	 * @param wflTrdProductDealOth wflTrdProductDealOth
	 */
	public void setWflTrdProductDealOth(WflTrdProductDealOth wflTrdProductDealOth) {
		this.wflTrdProductDealOth = wflTrdProductDealOth;
	}

	/**
	 * 获取 finProductType
	 *
	 * @rturn finProductType
	 */
	public FinProductType getFinProductType() {
		return this.finProductType;
	}

	/**
	 * 设置 finProductType
	 *
	 * @param finProductType finProductType
	 */
	public void setFinProductType(FinProductType finProductType) {
		this.finProductType = finProductType;
	}

	/**
	 * 获取 finTradeProduct
	 *
	 * @rturn finTradeProduct
	 */
	public FinTradeProductDto getFinTradeProduct() {
		return this.finTradeProduct;
	}

	/**
	 * 设置 finTradeProduct
	 *
	 * @param finTradeProduct finTradeProduct
	 */
	public void setFinTradeProduct(FinTradeProductDto finTradeProduct) {
		this.finTradeProduct = finTradeProduct;
	}

	/**
	 * 获取 riskTrialRetDto
	 *
	 * @rturn riskTrialRetDto
	 */
	public List<RiskTrialRetDto> getRiskTrialRetDto() {
		return this.riskTrialRetDto;
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
	 * 获取 remark
	 *
	 * @rturn remark
	 */
	@Override
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 remark
	 *
	 * @param remark remark
	 */
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 uploadFileList
	 *
	 * @rturn uploadFileList
	 */
	public List<UploadFile> getUploadFileList() {
		return this.uploadFileList;
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
	 * 获取 isGuarantee
	 *
	 * @rturn isGuarantee
	 */
	public String getIsGuarantee() {
		return this.isGuarantee;
	}

	/**
	 * 设置 isGuarantee
	 *
	 * @param isGuarantee isGuarantee
	 */
	public void setIsGuarantee(String isGuarantee) {
		this.isGuarantee = isGuarantee;
	}

	/**
	 * 获取 revokeReason
	 *
	 * @rturn revokeReason
	 */
	public String getRevokeReason() {
		return this.revokeReason;
	}

	/**
	 * 设置 revokeReason
	 *
	 * @param revokeReason revokeReason
	 */
	public void setRevokeReason(String revokeReason) {
		this.revokeReason = revokeReason;
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

	public List<TrdTradeFeeDetail> getTradeFeeList() {
		return tradeFeeList;
	}

	public void setTradeFeeList(List<TrdTradeFeeDetail> tradeFeeList) {
		this.tradeFeeList = tradeFeeList;
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
	 * 获取 isGrade
	 * 
	 * @return isGrade.
	 */
	public String getIsGrade() {
		return isGrade;
	}

	/**
	 * 设置 isGrade
	 * 
	 * @param isGrade isGrade
	 */
	public void setIsGrade(String isGrade) {
		this.isGrade = isGrade;
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
	 * 首期交付日
	 * 
	 * @return
	 */
	public Date getVpayDate() {
		return vpayDate;
	}

	/**
	 * 首期交付日
	 * 
	 * @param vpayDate
	 */
	public void setVpayDate(Date vpayDate) {
		this.vpayDate = vpayDate;
	}

	/**
	 * 实际占款天数
	 * 
	 * @return
	 */
	public Date getMpayDate() {
		return mpayDate;
	}

	/**
	 * 到期交付日
	 * 
	 * @param mpayDate
	 */
	public void setMpayDate(Date mpayDate) {
		this.mpayDate = mpayDate;
	}

	/**
	 * 实际占款天数
	 * 
	 * @return
	 */
	public Long getActCapDays() {
		return actCapDays;
	}

	/**
	 * 实际占款天数
	 * 
	 * @param actCapDays
	 */
	public void setActCapDays(Long actCapDays) {
		this.actCapDays = actCapDays;
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
	 * 获取 creditList
	 * 
	 * @return creditList.
	 */
	public CreditAmtSelDto getCreditList() {
		return creditList;
	}

	/**
	 * 设置 creditList
	 * 
	 * @param creditList creditList
	 */
	public void setCreditList(CreditAmtSelDto creditList) {
		this.creditList = creditList;
	}

	/**
	 * 获取 secAcctSource
	 * 
	 * @return secAcctSource.
	 */
	public String getSecAcctSource() {
		return secAcctSource;
	}

	/**
	 * 设置 secAcctSource
	 * 
	 * @param secAcctSource secAcctSource
	 */
	public void setSecAcctSource(String secAcctSource) {
		this.secAcctSource = secAcctSource;
	}

	/**
	 * 获取 secRegistOrg
	 * 
	 * @return secRegistOrg.
	 */
	public String getSecRegistOrg() {
		return secRegistOrg;
	}

	/**
	 * 设置 secRegistOrg
	 * 
	 * @param secRegistOrg secRegistOrg
	 */
	public void setSecRegistOrg(String secRegistOrg) {
		this.secRegistOrg = secRegistOrg;
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
	 * 获取 trdRepoTrade002DtoList
	 * 
	 * @return trdRepoTrade002DtoList.
	 */
	public List<TrdRepoTrade002Dto> getTrdRepoTrade002DtoList() {
		return trdRepoTrade002DtoList;
	}

	/**
	 * 设置 trdRepoTrade002DtoList
	 * 
	 * @param trdRepoTrade002DtoList trdRepoTrade002DtoList
	 */
	public void setTrdRepoTrade002DtoList(List<TrdRepoTrade002Dto> trdRepoTrade002DtoList) {
		this.trdRepoTrade002DtoList = trdRepoTrade002DtoList;
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

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public InterfOpicsTradRepo getInterfOpicsTradRepo() {
		return interfOpicsTradRepo;
	}

	public void setInterfOpicsTradRepo(InterfOpicsTradRepo interfOpicsTradRepo) {
		this.interfOpicsTradRepo = interfOpicsTradRepo;
	}

	public List<InterfTradRepoasstDto> getInterfTradRepoassts() {
		return interfTradRepoassts;
	}

	public void setInterfTradRepoassts(List<InterfTradRepoasstDto> interfTradRepoassts) {
		this.interfTradRepoassts = interfTradRepoassts;
	}

	public BigDecimal getCbFpriceAmt() {
		return cbFpriceAmt;
	}

	public void setCbFpriceAmt(BigDecimal cbFpriceAmt) {
		this.cbFpriceAmt = cbFpriceAmt;
	}

	public BigDecimal getScFpriceAmt() {
		return scFpriceAmt;
	}

	public void setScFpriceAmt(BigDecimal scFpriceAmt) {
		this.scFpriceAmt = scFpriceAmt;
	}
}
