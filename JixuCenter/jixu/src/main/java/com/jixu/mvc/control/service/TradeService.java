package com.jixu.mvc.control.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.PortTranMapType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.Pages;
import com.jixu.msg.dto.TranBaseMsgDto;
import com.jixu.mvc.control.model.SecTradeDetailModel;
import com.jixu.mvc.control.model.SecTradeSummaryModel;
import com.orm.obj.TranBasic;
import com.orm.obj.TranBasicFee;
import com.orm.obj.TranCollateral;


public interface TradeService {
	
	/**
	 * 获取交易列表
	 * @param secType
	 * @param secID
	 * @param portNum
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Pages<SecTradeSummaryModel> loadTranBasicList(Pages<SecTradeSummaryModel> pages,String secType, String secID, String portNum, Date startDate, Date endDate,Boolean displayDelete);
	/**
	 * 获取交易详情
	 * @param tanum
	 * @return
	 */
	public SecTradeDetailModel loadTranBasicByID(String tanum);
	
	/**
	 * 获取申赎交易列表
	 * @param portnum
	 * @param tranType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Pages<SecTradeSummaryModel> loadSubRedTranList(String portnum, int tranType, Date startDate, Date endDate,Pages<SecTradeSummaryModel> pages,Boolean displayDelete); 
	
	/**
	 * 获取换汇交易列表
	 * @param portnum
	 * @param tranType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Pages<SecTradeSummaryModel> loadFXTranTranList(String portnum, String assetType, Date startDate, Date endDate, Pages<SecTradeSummaryModel> pages,Boolean displayDelete); 
	
	/**
	 * 获取现金冲销列表
	 * @param portnum
	 * @param tranType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public Pages<SecTradeSummaryModel> loadCashOffList(String portnum, Date startDate, Date endDate, int tranType,Pages<SecTradeSummaryModel> pages,Boolean displayDelete);
	
	/**
	 * 获取现金交易列表
	 * @param portnum	投资组合
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @return	SecTradeSummaryModel类型的证券交易数据集。现金交易也在其中
	 */
	public Pages<SecTradeSummaryModel> loadCashTranList(String portnum, Date startDate, Date endDate,Pages<SecTradeSummaryModel> pages,Boolean displayDelete);

	public HashMap<Integer, String> loadTranFeeDefByTranTypeID(String assettype);
	
	public List<TranBasicFee> loadTranBasicFeeList(String tanum);
	
	public double genRepoMaturity(String secid, double rate, double baseamount, Date tradeDate, Date setDate, Date matDate);
	
	public List<TranCollateral> loadTranCollateralList(String tanum);
	
	public double calTotalFeeAmount(List<Object> exp_list, String port);
	
	public double newCalTotalFeeAmount(List<HashMap> mapList, String port);
	
	public ResultContainer deleteTran(String tradeID, boolean wflFlag);
	
	public MsgContainer operateTran(MsgContainer container);
	
	/**
	 * 返回 证券账户|LotNum|数量
	 * @param portnum
	 * @param secid
	 * @return
	 */
	public List<String> loadLotUnits(String portnum, String secid);
	
	/**
	 * 获取交易所需的配置,默认证券账号,默认现金帐号等
	 * @param portnum
	 * @return
	 */
	public List<PortTranMapType> getPortTranMapTypeList(String portnum);
	
	public List<String> loadLotUnitsPosition(String portnum, String secid);
	
	/**
	 * 获取期货平仓批次选择
	 */
	public List<String> loadFutureLotSelect(String portnum, String secid,String secacct,int longShort);
	
	/**
	 * 计算有效利率，实际利率
	 * @param smf
	 * @param tradeDate
	 * @param buyPrice
	 * @return
	 */
	public double calAmortRate(String portnum, String smf, Date tradeDate, Date settleDate, double amount, double pincipal);
	
	/**
	 * 根据交易编号获取凭证信息
	 * 1.如果已经生成凭证，则直接从凭证列表获取
	 * 2.尚未生成凭证的，则重新计算
	 * @param tradeNum
	 * @return
	 */
	public ResultContainer getTradeVouhcerDetailsViewList(String tradeNum);
	
	/**
	 * 计算绩效费
	 * @param portnum	投资组合代码
	 * @param cusip		证券代码
	 * @param lotnum	批次
	 * @param peCfg		绩效费配置
	 * @param shares	赎回份额
	 * @return
	 */
	public double[] calPerformanceFee(String portnum, String cusip, int lotnum, double shares);
	
	/**
	 * 获取卖出批次前一个日期的应收利息
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param shares
	 * @return
	 */
	public double getSalePortIncomes(String portnum, String cusip, int lotnum, double shares);
	
	/**
	 * 卖出证券，按照卖出数量的比例查询应付多少利息
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param shares
	 * @return
	 */
	public double getIncomeSalePayable(String portnum, String cusip, int lotnum, double shares);
	
	/**
	 * 获取批次的单位净值
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @return
	 */
	public double getPortBatchNAV(String portnum, String cusip, int lotnum);
	
	/**
	 * 获得组合批次的某个费用的当前计提信息
	 * @param portnum
	 * @param cusip
	 * @param lotnum
	 * @param feeType
	 * @return
	 */
	public double getPortExpensePayable(String portnum, String cusip, int lotnum, int feeType);
	
	public TranBasic getFCOpenTranBasic(String portnum, String secid,int tranType);
	
	public MsgContainer operateTranWF(MsgContainer container);
	
	/**
	 * 获取股权登记日的头寸数量
	 * @param portnum
	 * @param cusip
	 * @param closeDate
	 * @return
	 */
	public double getPositionUnits(String portnum, String cusip, Date closeDate);
	
	/**
	 * 返回计息基准日
	 * @param secid
	 * @param ledgerDate
	 * @param tranType
	 * @return
	 */
	public Date returnLastDivBasicDate(String secid, Date ledgerDate, int tranType);
	
	/**
	 * 自动触发交易
	 * @param dto
	 * @return
	 */
	public MsgContainer operateTranAuto(TranBaseMsgDto dto);
	
	/**
	 * 不随交易支付的费用合计
	 * @param mapList
	 * @param port
	 * @return
	 */
	public double newCalTotalFeeAmount2(List<HashMap> mapList, String port);
	
	/**
	 * 生成费用化金额
	 * @param feeID
	 * @param totalFee
	 * @return
	 */
	public double genUnCapitalizedPercent(int feeDefID, double totalFee);
	
	/**
	 * 返回费用是否随交易支付
	 * @param feeDefID
	 * @return
	 */
	public int isPaywithTrade(int feeDefID);
}
