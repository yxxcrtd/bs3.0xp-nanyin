package com.joyintech.service.valuation.invest.service;

import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.FinProductTypes;
import com.jixu.common.util.JsonUtil;
import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.*;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.valuation.common.dao.BaseCommonMapper;
import com.joyintech.tams.valuation.common.dto.ApiTrdCommDto;
import com.joyintech.tams.valuation.common.interf.base.service.dealcommon.DealCommonService;
import com.joyintech.tams.valuation.common.interf.common.ResultCode;
import com.joyintech.tams.valuation.common.interf.common.ResultObject;
import com.joyintech.tams.valuation.common.interf.invest.constant.BussinessJixuConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValInvest2JixuService{

	 /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ValInvest2JixuService.class);
    
	@Autowired
	Joyin2JixuInterface joyin2JixuInterface;
	
	@Autowired
    private FinTradeProductMapper finTradeProductMapper;
	
    @Autowired
    private DealCommonService dealCommonService;

	@Autowired
	private BaseCommonMapper baseCommonMapper;
	
	// 操作状态
	final String operate_add  = "ADD";
	
	final String operate_update  = "UPDATE";
	
	final String operate_del  = "DELETE";
	
	public ResultObject sendInfo(Object o) {
//		// 校验未通过
//		if(!check(o)) {
//			ResultContainer resultContainer = new ResultContainer();
//			resultContainer.setCorrectFlag(false);
//			resultContainer.setErrorMsg("接口传入交易数据不完整，请检查...");
//			return resultContainer;
//		}
		BaseEntity dto = transDto(o);
		String jsonMsg = JsonUtil.objToString(dto);
		logger.info("jsonMsg字符串消息:"+jsonMsg);
		ResultContainer resultContainer = joyin2JixuInterface.executeJixuMessage(jsonMsg);
		ResultObject resultObject = new ResultObject();
		if(resultContainer.isCorrectFlag()){
			resultObject.setResultCode(ResultCode.SUCCESS.getCode());
		}else{
			resultObject.setResultCode(ResultCode.FAILURE.getCode());
		}
		resultObject.setResultMsg(resultContainer.getErrorMsg());
		return resultObject;
	}


//	/**
//	 * 接口交易数据校验  
//	 * @param o
//	 * @return
//	 */
//	private boolean check(Object o) {
//		// TODO Auto-generated method stub
//		
//		if(o != null) {
//			if(o instanceof ApiTrdCommDto) {
//				ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
//				// 交易类金融产品	
//				FinTradeProduct	finTradeProduct = trdCommDto.getFinTradeProduct();
//				// 金融产品交易	
//				TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
//				// 金融产品交易附属表	
//				TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
//				// 非空检验通过
//				if(finTradeProduct!=null&&trdProductDeal!=null&&trdProductDealAdd!=null) {
//					// 检验通过
//					return true;
//				}
//			}
//		}
//		
//		return false;
//	}

	/**
	 * dto转换和封装
	 * @param o
	 * @return
	 */
	private BaseEntity transDto(Object o) {
		
		BaseEntity baseEntity = new BaseEntity();
		
		ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
		
		// 即期换汇
		if(trdCommDto.getFinTradeProduct()!=null
				&&StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_即期.getValue(), trdCommDto.getFinTradeProduct().getFinprodType2())) {
			baseEntity = transDtoToFXOpenDto(o);
		}
		
		// 外汇掉期
		else if(trdCommDto.getFinTradeProduct()!=null
				&&StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_掉期.getValue(), trdCommDto.getFinTradeProduct().getFinprodType2())) {
			baseEntity = transDtoToSwapFXOpenDto(o);
		}
		// 股票交易  增发 配售 买入 卖出 
		else if(trdCommDto.getTrdProductDeal()!=null
				&&(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue(), trdCommDto.getTrdProductDeal().getFinprodType2()))
				&&(StringUtil.equals(trdCommDto.getTrdProductDeal().getPs(), DictCodeList.交易方向_买入.getValue())
						||StringUtil.equals(trdCommDto.getTrdProductDeal().getPs(), DictCodeList.交易方向_卖出.getValue())
						||StringUtil.equals(trdCommDto.getTrdProductDeal().getPs(), DictCodeListExt2.交易方向_配股.getValue())
						||StringUtil.equals(trdCommDto.getTrdProductDeal().getPs(), DictCodeListExt2.交易方向_增发.getValue()))
				){
			baseEntity = transDtoToStockDto(o);
		}
		// 股票交易  新股中签
		else if(trdCommDto.getTrdProductDeal()!=null
				&&(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_股票.getValue(), trdCommDto.getTrdProductDeal().getFinprodType2()))
				&&StringUtil.equals(trdCommDto.getTrdProductDeal().getPs(), DictCodeListExt2.交易方向_新股中签.getValue())
				){
			baseEntity = transDtoToNewStockDto(o);
		}
		
		// 外汇远期 开仓
		else if(StringUtil.equals(BussinessJixuConstant.消息头_外汇远期开仓交易, trdCommDto.getHeader())) {
			baseEntity = transDtoToForwardCurrencyOpenDto(o);
		}
		
		// 远期换汇 轧差交割
		else if(StringUtil.equals(BussinessJixuConstant.消息头_远期换汇轧差交割, trdCommDto.getHeader())) {
			baseEntity = transDtoToForwardCurrencyCuttingDto(o);
		}
		
		// 远期换汇 合约到期
		else if(StringUtil.equals(BussinessJixuConstant.消息头_远期换汇合约到期, trdCommDto.getHeader())) {
			baseEntity = transDtoToForwardCurrencyMaurityDto(o);
		}
		
		// 股票标的信息
		else if(StringUtil.equals(BussinessJixuConstant.消息头_股票标的信息, trdCommDto.getHeader())) {
			baseEntity = transDtoToForFinStockDto(o);
		}

		// 股票存续期
		else if(trdCommDto.getTrdProductDeal()!=null
				&&StringUtil.equals(DictCodeListExt2.共通业务类型_股票分红交易.getValue(), trdCommDto.getTrdProductDeal().getBusiType())) {
			if(StringUtil.equals(DictCodeList.交易方向_现金分红.getValue(),trdCommDto.getTrdProductDeal().getPs())){
				baseEntity = transDtoToCACashDividDto(o);
			}else if(StringUtil.equals(DictCodeList.交易方向_红利再投.getValue(),trdCommDto.getTrdProductDeal().getPs())){
				baseEntity = transDtoToCAStockDividDto(o);
			}

		}
		
		// 利率互换 开仓交易 
		else if(StringUtil.equals(BussinessJixuConstant.消息头_利率互换开仓交易, trdCommDto.getHeader())) {
			baseEntity = transDtoToForwardCurrencyMaurityDto(o);
		}
		
		
		return baseEntity;
	}

	/**
	 * 股票存续期 现金分红
	 * @param o
	 * @return
	 */
	private CashDividendDto transDtoToCACashDividDto(Object o){
		//封装成CorpActionCashDividendDto
//		CorpActionCashDividendDto result = new CorpActionCashDividendDto();
		//封装成CashDividendDto
		CashDividendDto result = new CashDividendDto();
		result.setHeader(BussinessJixuConstant.消息头_股票_现金分红);
		// 消息头
//		result.setHeader(BussinessJixuConstant.消息头_);
		if(o instanceof ApiTrdCommDto) {
			ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
			//金融产品交易汇总
			TrdFinProductDealTotal dealTotal = trdCommDto.getTrdFinProductDealTotal();
			// 金融产品交易
			TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
			// 金融产品交易附属表
			TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
			// 划款指令
			List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
			// 为空退出
			if(trdProductDeal==null||trdProductDealAdd==null) {
				return result;
			}
			// 操作类型
			result.setOperation(trdCommDto.getOperateType());
			//资产类型
			result.setAssetType(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
			//证券代码 = 金融产品交易.金融产品代码
			result.setSecid(trdProductDeal.getFinprodId());
			//交易编号
			result.setId(trdProductDeal.getTradeId());
			//权益登记日
			result.setDivDate(DateUtil.format(trdProductDealAdd.getRegDate()));
			//记账日 = 交易汇总.交易日
//			result.setExDividentDate(DateUtil.format(dealTotal.getTradeDate()));
			//结算日
			if(stlPayOrdersList!=null) {
				//结算时间
				StlPayOrders stlPayOrder = stlPayOrdersList.get(0);
				result.setSettleDate(DateUtil.format(stlPayOrder.getSettleDate()));
				//银行账户
				result.setBankAcct(stlPayOrder.getMyAcctNo());
			}
			//公司行为类型    现金股利:1   股票股利:8   股票分割:9   股票合并10
//			result.setCorpActionType(1);
			//每股分红
//			result.setAlRate(dealTotal.getUnitInt().toString());
			//交易类型
			result.setTradeType("103");
			//交易日期
			result.setTradeDate(DateUtil.format(dealTotal.getTradeDate()));
			//每股分红比例
//			result.setAlRate(dealTotal.getUnitCprice().toString());
			//基准数量 股本
			if(trdProductDealAdd.getRegDateAmt()!=null){
				result.setBaseAmount(trdProductDealAdd.getRegDateAmt().doubleValue());
			}
			//配股比例
			if(trdProductDeal.getUnitInt()!=null){
				result.setAlRate(trdProductDeal.getUnitInt().toString());
			}
			//分红金额
			if(trdProductDeal.getTradeAmt()!=null){
				result.setTradeCash(trdProductDeal.getTradeAmt().doubleValue());
			}
			//证券账户
			result.setSecAcct(trdProductDeal.getSecManageAcctId());
			//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
			FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
			String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
			//投资组合代码
			result.setPort(booksetid);
		}
		return  result;
	}

	/**
	 * 股票存续期 红利再投
	 * @param o
	 * @return
	 */
	private StockDividendDto transDtoToCAStockDividDto(Object o){
		//封装成CorpActionStockDividendDto
//		CorpActionStockDividendDto result = new CorpActionStockDividendDto();
		//封装成StockDividendDto
		StockDividendDto result = new StockDividendDto();
		result.setHeader(BussinessJixuConstant.消息头_股票_红利再投);
		if(o instanceof ApiTrdCommDto) {
			ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
			//金融产品交易汇总
			TrdFinProductDealTotal dealTotal = trdCommDto.getTrdFinProductDealTotal();
			// 金融产品交易
			TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
			// 金融产品交易附属表
			TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
			// 为空退出
			if(trdProductDeal==null||trdProductDealAdd==null) {
				return result;
			}
			// 操作类型
			result.setOperation(trdCommDto.getOperateType());
			//资产类型
			result.setAssetType(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
			//证券代码 = 金融产品交易.金融产品代码
			result.setSecid(trdProductDeal.getFinprodId());
			//交易编号
			result.setId(trdProductDeal.getTradeId());
			//权益登记日
			result.setDivDate(DateUtil.format(trdProductDealAdd.getRegDate()));
			//除权除息日 = 交易汇总.交易日
//			result.setExDividentDate(DateUtil.format(dealTotal.getTradeDate()));
			//公司行为类型    现金股利:1   股票股利:8   股票分割:9   股票合并10
//			result.setCorpActionType(8);
			//交易类型
			result.setTradeType("101");
			//交易日期
			result.setTradeDate(DateUtil.format(dealTotal.getTradeDate()));
			//每股分红比例
//			result.setAlRate(dealTotal.getUnitCprice().toString());
			//基准数量 股本
			if(trdProductDealAdd.getRegDateAmt()!=null){
				result.setBaseAmount(trdProductDealAdd.getRegDateAmt().doubleValue());
			}
			//配股比例
			if(trdProductDeal.getUnitCprice()!=null){
				result.setAlRate(trdProductDeal.getUnitCprice().toString());
			}
			//送股股数
			if(trdProductDeal.getUnitCprice()!=null&&trdProductDealAdd.getRegDateAmt()!=null){
				BigDecimal unitCprice = trdProductDeal.getUnitCprice();
				BigDecimal regDateAmt = trdProductDealAdd.getRegDateAmt();
				result.setAmount(ArithmeticUtil.multiply(regDateAmt,unitCprice).doubleValue());
			}
			//证券账户
			result.setSecAcct(trdProductDeal.getSecManageAcctId());
			//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
			FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
			String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
			//投资组合代码
			result.setPort(booksetid);
		}
		return  result;
	}

	/**
	 * 标的 股票
	 * @param o
	 * @return ForwardCurrencyCuttingDto
	 */
	private BaseEntity transDtoToForFinStockDto(Object o) {
		SmfOperationDto result = new SmfOperationDto();
		result.setHeader(BussinessJixuConstant.消息头_股票标的信息);
		// 后续实现DTO的转换
		if(o instanceof ApiTrdCommDto) {
			ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
			// 金融产品
			FinProduct finProduct = trdCommDto.getFinProduct();
			// 金融产品附属
			FinProductAdd finProductAdd = trdCommDto.getFinProductAdd();
			// 操作类型
			result.setOperation(trdCommDto.getOperateType());
			//证券主ID
			result.setSecid(finProduct.getFinprodId());
			//ID
//			result.setId(finProduct.getFinprodId());
			//证券名称
			result.setSecurityName(finProduct.getFinprodName());
			//证券类型
			result.setIssuetype(finProduct.getFinprodType2());
			//发行商
			result.setIssuer("5002");
			//所在国家
			result.setCountry("CN");
			//交易所
			if(StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), finProduct.getTradeMarket())){
				result.setExchange("03");
			} else if(StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), finProduct.getTradeMarket())){
				result.setExchange("04");
			}
			//成本货币
			result.setPrcurrency(finProduct.getCcy());
			//收入货币
			result.setIncurrency(finProduct.getCcy());
			//资产类别  总账组
			if(StringUtil.equals(DictCodeListExt2.是否沪港通或深港通_否.getValue(), finProductAdd.getShOrZh())){
				if(StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), finProduct.getTradeMarket())){
					result.setAssetType("101");
					result.setGlgroup(161);
				}else if(StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), finProduct.getTradeMarket())){
					result.setAssetType("102");
					result.setGlgroup(162);
				}
			}else if(StringUtil.equals(DictCodeListExt2.是否沪港通或深港通_沪港通.getValue(), finProductAdd.getShOrZh())){
				result.setAssetType("103");
				result.setGlgroup(163);
			}else if(StringUtil.equals(DictCodeListExt2.是否沪港通或深港通_深港通.getValue(), finProductAdd.getShOrZh())){
				result.setAssetType("104");
				result.setGlgroup(164);
			}
			//数据来源
			result.setSource(0);
			//生效日期
			result.setActiveDate(DateUtil.format(finProduct.getVdate()));
		}
		return result;
	}


	/**
	 * 股票交易 新股中签
	 * @param o
	 * @return ForwardCurrencyCuttingDto
	 */
	private BaseEntity transDtoToNewStockDto(Object o) {
		StockNewReceiveDto result = new StockNewReceiveDto();
		// 消息头  
		result.setHeader(BussinessJixuConstant.消息头_股票交易_新股中签);
		// 后续实现DTO的转换
		if(o instanceof ApiTrdCommDto) {
			ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
			// 金融产品交易	
			TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
			// 金融产品交易附属表	
			TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
			// 划款指令
			List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
			//费用明细
//			List<TrdTradeFeeDetail> tradeFeeDetaillist = trdCommDto.getTradeFeeDetaillist();
			// 操作类型
			result.setOperation(trdCommDto.getOperateType());
			// 资产类型
			result.setAssetType(trdProductDeal.getFinprodType2());
			//证券代码
			result.setSecid(trdProductDeal.getFinprodId());
			//证券名称
//			result.setSecName("");secManageAcctId
			//证券账户
			result.setSecAcct(trdProductDeal.getSecManageAcctId());
			//交易编号 =trade_id
			result.setId(trdProductDeal.getTradeId());
			//券商
			result.setBroker("5002");
			//交易市场 暂时不填
			result.setMarket("2");
			//投资经理
			result.setManager("1");
			//交易金额
			result.setTradeCash(String.valueOf(trdProductDeal.getTradeAmt()));
			//交易类型
			result.setTradeType("105");
			//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
			FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
			String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
			// 产品代码  = 金融产品交易附属.投资组合代码
			result.setPort(booksetid);
			//交易日期
			result.setTradeDate(DateUtil.format(trdProductDeal.getTradeDate()));
			//结算日期
			result.setSettleDate(DateUtil.format(trdProductDeal.getSettleDate()));
			//数量
			result.setShares(String.valueOf(trdProductDeal.getShareAmt()));
			//价格
			result.setPrice(String.valueOf(trdProductDeal.getUnitCprice()));
			//交易货币
			result.setTradeCurrency(trdProductDeal.getCcy());
			//本金 数量*价格
			result.setPrincipal(String.valueOf(ArithmeticUtil.multiply(trdProductDeal.getShareAmt(),trdProductDeal.getUnitCprice())) );
			if(stlPayOrdersList!=null) {
				//现金账户
				//结算货币
				//结算汇率
				//结算金额
				StlPayOrders stlPayOrder = stlPayOrdersList.get(0);
				result.setBankAcct(stlPayOrder.getMyAcctNo()!=null?stlPayOrder.getMyAcctNo():trdCommDto.getPtlCapacctInfo().getAcctNo());
				result.setSettleCurrency(stlPayOrder.getCcy());
				result.setSettleFX("1");
				result.setTradeFX("1");
				result.setSettleCash(stlPayOrder.getSettleAmt().toString());
			}
			
		}
		
		
		return result;
	}

	/**
	 * 股票交易 买卖 增发配售
	 * @param o
	 * @return ForwardCurrencyCuttingDto
	 */
	private BaseEntity transDtoToStockDto(Object o) {
		StockTranDto result = new StockTranDto();
		// 消息头  
		result.setHeader(BussinessJixuConstant.消息头_股票交易_买卖);
		// 后续实现DTO的转换
		if(o instanceof ApiTrdCommDto) {
			ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
			// 金融产品交易	
			TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
			// 金融产品交易附属表	
			TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
			// 划款指令
			List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
			//费用明细
			List<TrdTradeFeeDetail> tradeFeeDetaillist = trdCommDto.getTradeFeeDetaillist();
			
			// 操作类型
			result.setOperation(trdCommDto.getOperateType());
			// 资产类型
			result.setAssetType(trdProductDeal.getFinprodType2());
			//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
			FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
			String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
			// 产品代码  = 金融产品交易附属.投资组合代码
			result.setPort(booksetid);
			// 合同编号 = 股票代码
			result.setSecid(trdProductDeal.getFinprodId());
			//证券账户
			result.setSecAcct(trdProductDeal.getSecManageAcctId());
			//交易编号 =trade_id
			result.setId(trdProductDeal.getTradeId());
			//交易类型
			if(StringUtil.equals(trdProductDeal.getPs(), DictCodeList.交易方向_买入.getValue())
					||StringUtil.equals(trdProductDeal.getPs(), DictCodeListExt2.交易方向_增发.getValue())
					||StringUtil.equals(trdProductDeal.getPs(), DictCodeListExt2.交易方向_配股.getValue())){
				//买入 增发 配股
				result.setTradeType("100");
			}else if (StringUtil.equals(trdProductDeal.getPs(), DictCodeList.交易方向_卖出.getValue())){
				//卖出
				result.setTradeType("200");
			}
			//交易日期
			result.setTradeDate(DateUtil.format(trdProductDeal.getTradeDate()));
			//结算日期
			result.setSettleDate(DateUtil.format(trdProductDeal.getSettleDate()));
			//交易市场 暂时不填
			result.setMarket("2");
			//投资经理
			result.setManager("1");
			//券商
			result.setBroker("5002");
			//数量
			result.setShares(String.valueOf(trdProductDeal.getShareAmt()));
			//价格
			result.setPrice(String.valueOf(trdProductDeal.getUnitCprice()));
			//本金 数量*价格
			result.setPrincipal(String.valueOf(ArithmeticUtil.multiply(trdProductDeal.getShareAmt(),trdProductDeal.getUnitCprice())) );
			//计提收入
			result.setAccruedIncome("0");
			//交易货币
			result.setTradeCurrency(trdProductDeal.getCcy());
			//交易金额
			result.setTradeCash(String.valueOf(trdProductDeal.getTradeAmt()));
			StlPayOrders stlPayOrder = null;
			if(stlPayOrdersList!=null) {
				//现金账户
				//结算货币
				//结算汇率
				//结算金额
				stlPayOrder = stlPayOrdersList.get(0);
				result.setBankAcct(stlPayOrder.getMyAcctNo());
				result.setSettleCurrency(stlPayOrder.getCcy());
				result.setSettleFX("1");
				result.setTradeFX("1");
				result.setSettleCash(stlPayOrder.getSettleAmt().toString());
			}
			List<TranExpenseDto> tranExpenseDtolist = new ArrayList<>();
			for(TrdTradeFeeDetail fee:tradeFeeDetaillist){
				TranExpenseDto dto = new TranExpenseDto();
				//费用结转银行账户
//				dto.setBankAccount(bankAccount);
				if(StringUtil.equals(fee.getIsPayWithTrade(), DictCodeList.是否_是.getValue())){
					if(null!=stlPayOrder){
						dto.setBankAccount(stlPayOrder.getMyAcctNo());
					}
				}else{
					dto.setBankAccount(trdCommDto.getPtlCapacctInfo().getAcctNo());
				}
				//费用金额
				if(StringUtil.equals(fee.getIsPayWithTrade(), DictCodeList.是否_是.getValue())){
					dto.setCashAmount(0);
				}else{
					if(fee.getFeeAmt()!=null){
						dto.setCashAmount(fee.getFeeAmt().doubleValue());
					}else{
						dto.setCashAmount(0);
					}
				}
				if(fee.getFeeAmt()!=null){
					dto.setExpenseAmount(fee.getFeeAmt().doubleValue());
					dto.setTotalAmount(fee.getFeeAmt().doubleValue());
				}else{
					dto.setExpenseAmount(0);
					dto.setTotalAmount(0);
				}
				
				//
				if(StringUtil.equals(fee.getFeeType(), DictCodeListExt1.一次性费用类型_印花税.getValue())){
					dto.setExpense(102);
				}else if (StringUtil.equals(fee.getFeeType(), DictCodeListExt1.一次性费用类型_佣金.getValue())){
					dto.setExpense(101);
				}
				dto.setFeeTradeCurrency(trdProductDeal.getCcy());
				dto.setFxRate(1);
				tranExpenseDtolist.add(dto);
			}
			result.setExpenses(tranExpenseDtolist);
		}
		return result;
	}

	/**
	 * 外汇远期 合约到期	
	 * @param o
	 * @return ForwardCurrencyMaurityDto
	 */
	private ForwardCurrencyMaurityDto transDtoToForwardCurrencyMaurityDto(Object o) {
		// 外汇远期合约到期 封装成ForwardCurrencyMaurityDto
		ForwardCurrencyMaurityDto result = new ForwardCurrencyMaurityDto();
		// 
		ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
		// 金融产品交易	
		TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
		// 金融产品交易附属表	
		TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
		// 划款指令
		List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
		
		// 根据首期交易编号 获取金融交易产品信息 finTradeProduct
		FinTradeProductKey finTradeProductKey = new FinTradeProductKey();
		finTradeProductKey.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessJixuConstant.NOBRANCHNOVALUE)));
		finTradeProductKey.setFinprodId(trdProductDeal.getFinprodId());
		FinTradeProduct finTradeProduct = finTradeProductMapper.selectByPrimaryKey(finTradeProductKey);
				
		// 消息头
		result.setHeader(trdCommDto.getHeader());
		//
		result.setId(trdProductDeal.getTradeId());
		// 操作类型
		result.setOperation(trdCommDto.getOperateType());
		// 资产类型
		result.setAssetType(finTradeProduct.getFinprodType2());
		// 合同编号 = 交易编号
		result.setSecid(trdProductDeal.getFinprodId());
		// 交易类型 衍生品后端的远期合约到期编号
		result.setTradeType(BussinessJixuConstant.交易类型_远期合约到期);
		//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
		FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
		String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
		// 产品代码  = 金融产品交易附属.投资组合代码
		result.setPort(booksetid);
		// 投资经理组   默认1
		result.setManager(BussinessJixuConstant.投资经理组_默认);
		// 近端交割日 = 金融产品交易.交割日
		result.setSettleDate(DateUtil.format(trdProductDeal.getSettleDate()));
		result.setTradeDate(DateUtil.format(trdProductDeal.getSettleDate()));
		
		// 交易货币对
		if(trdProductDealAdd.getCurPair()!=null) {
			String[] curpiar = trdProductDealAdd.getCurPair().split("/");
			if(stlPayOrdersList!=null) {
				for(StlPayOrders stlPayOrders:stlPayOrdersList) {
					if(stlPayOrders==null)continue;
					if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(),finTradeProduct.getExcPs())) {
						// 是否基准货币  多头账户 多头金额  多头货币 
						if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[0])){
							result.setBankAcct(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash(stlPayOrders.getSettleAmt().toString());
							result.setLongCurrency(stlPayOrders.getCcy());
						}
						// 是否非基准货币   空头账户 空头金额  空头货币
						else if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[1])){
							result.setBankAcct2(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash2(stlPayOrders.getSettleAmt().toString());
							result.setShortCurrency(stlPayOrders.getCcy());
						}
					}
					else if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),finTradeProduct.getExcPs())) {
						// 是否基准货币  空头账户 空头金额  空头货币
						if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[0])){
							result.setBankAcct2(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash2(stlPayOrders.getSettleAmt().toString());
							result.setShortCurrency(stlPayOrders.getCcy());
						}
						// 是否非基准货币   多头账户 多头金额  多头货币
						else if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[1])){
							result.setBankAcct(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash(stlPayOrders.getSettleAmt().toString());
							result.setLongCurrency(stlPayOrders.getCcy());
						}
					}
				}
			}
		}
		
		// 获取合约汇率
		// 多头金额和空头金额不为空
		if(result.getTradeCash()!=null&&result.getTradeCash2()!=null) {
			// 多头汇率
			result.setLongFX(new BigDecimal(result.getTradeCash2())
					.divide(new BigDecimal(result.getTradeCash()), 6, RoundingMode.HALF_UP).toString());
			// 空头汇率
			result.setShortFX(new BigDecimal(result.getTradeCash())
					.divide(new BigDecimal(result.getTradeCash2()), 6, RoundingMode.HALF_UP).toString());
		}
		
		return result;
	}
	
	
	/**
	 * 外汇远期 轧差交割
	 * @param o
	 * @return ForwardCurrencyCuttingDto
	 */
	private ForwardCurrencyCuttingDto transDtoToForwardCurrencyCuttingDto(Object o) {
		// 外汇远期轧差交易 封装成ForwardCurrencyCuttingDto
		ForwardCurrencyCuttingDto result = new ForwardCurrencyCuttingDto();
		// 
		ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
		// 金融产品交易	
		TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
		// 金融产品交易附属表	
		TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
		// 消息头
		result.setHeader(trdCommDto.getHeader());
		// 
		result.setId(trdProductDeal.getTradeId());
		// 操作类型
		result.setOperation(trdCommDto.getOperateType());
		// 资产类型
		result.setAssetType(trdProductDeal.getFinprodType2());
		// 合同编号 = 交易编号
		result.setSecid(trdProductDeal.getFinprodId());
		// 交易类型 衍生品后端的远期轧差交割编号
		result.setTradeType(BussinessJixuConstant.交易类型_远期轧差交割);
		//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
		FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
		String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
		// 产品代码  = 金融产品交易附属.投资组合代码
		result.setPort(booksetid);
		// 投资经理组   默认1
		result.setManager(BussinessJixuConstant.投资经理组_默认);
		// 近端交割日 = 金融产品交易.交割日
		result.setSettleDate(DateUtil.format(trdProductDeal.getSettleDate()));
		result.setTradeDate(DateUtil.format(trdProductDeal.getSettleDate()));
		
		// 根据首期交易编号 获取金融交易产品信息 finTradeProduct
		FinTradeProductKey finTradeProductKey = new FinTradeProductKey();
		finTradeProductKey.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessJixuConstant.NOBRANCHNOVALUE)));
		finTradeProductKey.setFinprodId(trdProductDeal.getFinprodId());
		FinTradeProduct finTradeProduct = finTradeProductMapper.selectByPrimaryKey(finTradeProductKey);
		
		if(trdProductDealAdd.getCurPair()!=null) {
			String[] curpiar = trdProductDealAdd.getCurPair().split("/");
			// 交易币种
			String dealCur = finTradeProduct.getCcy();
			// 远端交易的交易货币金额 = finTradeProduct.mPrinAmt
			// 远端交易的对应货币金额 = finTradeProduct.mTradeAmt
			// 交易币种为基准货币
			if(StringUtil.equals(dealCur,curpiar[0])){
				// 买入
				if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash(finTradeProduct.getmPrinAmt().toString());
					// tradeCash2 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(dealCur);
					result.setShortCurrency(curpiar[1]);
				}
				// 卖出
				else if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash2	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmPrinAmt().toString());
					// tradeCash 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(curpiar[1]);
					result.setShortCurrency(dealCur);
				}
			}
			
			else if(StringUtil.equals(dealCur,curpiar[1])){
				// 买入
				if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash2	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmPrinAmt().toString());
					// tradeCash 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(dealCur);
					result.setShortCurrency(curpiar[0]);
				}
				// 卖出
				else if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash(finTradeProduct.getmPrinAmt().toString());
					// tradeCash2 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(curpiar[0]);
					result.setShortCurrency(dealCur);
				}
			}
		}
		// 获取合约汇率
		// 多头金额和空头金额不为空
		if(result.getTradeCash()!=null&&result.getTradeCash2()!=null) {
			// 多头汇率
			result.setLongFX(new BigDecimal(result.getTradeCash2())
					.divide(new BigDecimal(result.getTradeCash()), 6, RoundingMode.HALF_UP).toString());
			// 空头汇率
			result.setShortFX(new BigDecimal(result.getTradeCash())
					.divide(new BigDecimal(result.getTradeCash2()), 6, RoundingMode.HALF_UP).toString());
		}
		
		// 结算银行账户     人民币账户
		result.setBankAcct(
				trdCommDto.getPtlCapacctInfo().getAcctNo());
		// 轧差金额收付方向(应收默认是0   应付默认是1)
		if(StringUtil.equals(DictCodeList.交易方向_收取.getValue(),trdProductDealAdd.getDifPs())) {
			result.setDiffSide(BussinessJixuConstant.差额收付方向_应收);
		}else if(StringUtil.equals(DictCodeList.交易方向_支付.getValue(),trdProductDealAdd.getDifPs())) {
			result.setDiffSide(BussinessJixuConstant.差额收付方向_应付);
		}
		//	 差额收付金额	轧差金额
		if(trdProductDealAdd.getDifPayAmt()!=null)
		result.setDiffCash(trdProductDealAdd.getDifPayAmt().toString());
		
		return result;
	}
	

	/**
	 * 外汇远期 开仓交易
	 * @param o
	 * @return ForwardCurrencyOpenDto
	 */ 
	private ForwardCurrencyOpenDto transDtoToForwardCurrencyOpenDto(Object o) {
		// 外汇远期开仓交易 封装成ForwardCurrencyOpenDto
		ForwardCurrencyOpenDto result = new ForwardCurrencyOpenDto();
		// 
		ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
		// 交易类金融产品	
		FinTradeProduct	finTradeProduct = trdCommDto.getFinTradeProduct();
		// 金融产品交易	
		TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
		// 金融产品交易附属表	
		TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
//		// 划款指令
//		List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
		// 为空退出
		if(finTradeProduct==null||trdProductDeal==null||trdProductDealAdd==null) {
			return result;
		}
		// 银行账户
		String dealAcct = "";
		String corAcct = "";
//		// 账户不为空
//		if(stlFinProductAcctList!=null) {
//			for(StlFinProductAcct stlFinProductAcct :stlFinProductAcctList) {
//				// 本方账户
//				if(StringUtil.equals(DictCodeListExt2.标的账户类型_本方账户.getValue(), stlFinProductAcct.getAcctType())) {
//					dealAcct = stlFinProductAcct.getAcctNo();
//				}
//				// 对手方账户
//				else if(StringUtil.equals(DictCodeListExt2.标的账户类型_对手方账户.getValue(), stlFinProductAcct.getAcctType())) {
//					corAcct = stlFinProductAcct.getAcctNo();
//				}
//			}
//		}
		
		
		// 消息头
		result.setHeader(trdCommDto.getHeader());
		// 操作类型
		result.setOperation(trdCommDto.getOperateType());
		// 资产类型
		result.setAssetType(finTradeProduct.getFinprodType2());
		// 合同编号 = 交易编号
		result.setSecid(trdProductDeal.getFinprodId());
		// 合约名称     目前存放交易id
		result.setContract(trdProductDeal.getFinprodId());
		// 交易类型 衍生品后端的远期开仓交易编号
		result.setTradeType(BussinessJixuConstant.交易类型_远期开仓);
		// 券商 5002(默认券商)
		result.setBroker(BussinessJixuConstant.券商_默认);
		//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
		FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
		String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
		// 产品代码  = 金融产品交易附属.投资组合代码
		result.setPort(booksetid);
		// 投资经理组   默认1
		result.setManager(BussinessJixuConstant.投资经理组_默认);
		// 估值方法
		result.setValuationMethod(BussinessJixuConstant.估值方法_默认);
		// id = 交易编号
		result.setId(trdProductDeal.getTradeId());
		// 估值方向   多方/空方
		result.setValuationSide(BussinessJixuConstant.估值方向_默认);
		// 交易日期 = 金融产品交易.交易日
		result.setTradeDate(DateUtil.format(trdProductDeal.getTradeDate()));
		// 近端交割日 = 交易类金融产品.交割日
		result.setSettleDate(DateUtil.format(finTradeProduct.getMdate()));
		// 数据源配置 默认值
		result.setSource(BussinessJixuConstant.数据源配置_默认);
		// 外汇远期对应资产ID
		result.setRpSecType(String.valueOf(FinProductTypes.FORWARD_CURRENCY_RPSECTYPE));
		// 外汇远期总帐组
		result.setGlgroup(BussinessJixuConstant.总帐组_外汇远期);
		
		if(trdProductDealAdd.getCurPair()!=null) {
			String[] curpiar = trdProductDealAdd.getCurPair().split("/");
			// 交易币种
			String dealCur = finTradeProduct.getCcy();
			
			for(String ccy:curpiar) {
				// 
				Map<String,Object> paramMap = new HashMap<String,Object>();
		    	if(trdCommDto.getTrdProductDealAdd()!=null) {
		        	paramMap.put("portfolioId", trdCommDto.getTrdProductDealAdd().getPortfolioId());
		    	}
		    	paramMap.put("ccy", ccy);
		    	paramMap.put("acctType", DictCodeListExt2.资金账户类型_托管户.getValue());
		    	PtlCapacctInfo ptlCapacctInfo = baseCommonMapper.selectProdAcctData(paramMap);

				if(StringUtil.equals(ccy,curpiar[0])){
			    	dealAcct = ptlCapacctInfo!=null?ptlCapacctInfo.getAcctNo():"";
				}
				else if(StringUtil.equals(ccy,curpiar[1])){
					corAcct = ptlCapacctInfo!=null?ptlCapacctInfo.getAcctNo():"";
				}
			}
			
	    	
			// 远端交易的交易货币金额 = finTradeProduct.mPrinAmt
			// 远端交易的对应货币金额 = finTradeProduct.mTradeAmt
			// 交易币种为基准货币
			if(StringUtil.equals(dealCur,curpiar[0])){
				// 买入
				if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash(finTradeProduct.getmPrinAmt().toString());
					// tradeCash2 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(curpiar[0]);
					result.setShortCurrency(curpiar[1]);
					result.setBankAcct(dealAcct);
					result.setBankAcct2(corAcct);
				}
				// 卖出
				else if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash2	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmPrinAmt().toString());
					// tradeCash 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(curpiar[1]);
					result.setShortCurrency(curpiar[0]);
					result.setBankAcct(corAcct);
					result.setBankAcct2(dealAcct);
				}
			}
			
			else if(StringUtil.equals(dealCur,curpiar[1])){
				// 买入
				if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash2	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmPrinAmt().toString());
					// tradeCash 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(curpiar[0]);
					result.setShortCurrency(curpiar[1]);
					result.setBankAcct(dealAcct);
					result.setBankAcct2(corAcct);
				}
				// 卖出
				else if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash	远端收取金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash(finTradeProduct.getmPrinAmt().toString());
					// tradeCash2 远端支出金额  = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmTradeAmt().toString());
					result.setLongCurrency(curpiar[1]);
					result.setShortCurrency(curpiar[0]);
					result.setBankAcct(corAcct);
					result.setBankAcct2(dealAcct);

				}
			}
		}
		
		// 获取合约汇率
		// 多头金额和空头金额不为空
		if(result.getTradeCash()!=null&&result.getTradeCash2()!=null) {
			// 多头汇率
			result.setLongFX(new BigDecimal(result.getTradeCash2())
					.divide(new BigDecimal(result.getTradeCash()), 6, RoundingMode.HALF_UP).toString());
			// 空头汇率
			result.setShortFX(new BigDecimal(result.getTradeCash())
					.divide(new BigDecimal(result.getTradeCash2()), 6, RoundingMode.HALF_UP).toString());
		}
		
		/**
		 * 交易费用
		 */
		List<TranExpenseDto> expenses = new ArrayList<TranExpenseDto>();
		TranExpenseDto tranExpenseDto = new TranExpenseDto();
		// 交易费用
		if(trdProductDeal!=null&&trdProductDeal.getFeeAmtUnpay()!=null) {

			//总费用
			result.setTotalExpense(trdProductDeal.getFeeAmtUnpay().toString());
			
			// 费用结转银行账户  =  支出账户
			if(trdCommDto.getPtlCapacctInfo()!=null)
			tranExpenseDto.setBankAccount(
					trdCommDto.getPtlCapacctInfo().getAcctNo());
			// 费用金额
			tranExpenseDto.setCashAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			tranExpenseDto.setExpenseAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			tranExpenseDto.setTotalAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			// 5034
			tranExpenseDto.setExpense(BussinessJixuConstant.费用类型_远期手续费);
			// 人民币
			tranExpenseDto.setFeeTradeCurrency(DictCodeList.币种_人民币_CNY.getValue());
			// 1
			tranExpenseDto.setFxRate(1);
			expenses.add(tranExpenseDto);
		}
		result.setExpenses(expenses);

		return result;
	}
	
	/**
	 * 外汇掉期交易
	 * @param o
	 * @return SwapFXOpenDto
	 */
	private SwapFXOpenDto transDtoToSwapFXOpenDto(Object o) {
		// 外汇掉期开仓交易就封装成SwapFXOpenDto
		SwapFXOpenDto result = new SwapFXOpenDto();
		// 消息头  
		result.setHeader(BussinessJixuConstant.消息头_外汇掉期);
		// 
		ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
		
		// 交易类金融产品	
		FinTradeProduct	finTradeProduct = trdCommDto.getFinTradeProduct();
		// 金融产品交易	
		TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
		// 金融产品交易附属表	
		TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
		// 划款指令
		List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
		// 为空退出
		if(finTradeProduct==null||trdProductDeal==null||trdProductDealAdd==null||stlPayOrdersList==null) {
			return result;
		}
		
		// 操作类型
		result.setOperation(trdCommDto.getOperateType());
		//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
		FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
		String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
		// 产品代码  = 金融产品交易附属.投资组合代码
		result.setPort(booksetid);
		// 合同编号 = 交易编号
		result.setSecid(trdProductDeal.getTradeId());
		// 交易类型 衍生品后端的掉期开仓交易编号
		result.setTradeType(BussinessJixuConstant.交易类型_掉期);
		// 交易编号 id = 交易编号
		result.setId(trdProductDeal.getTradeId());
		// 资产类型
		result.setAssetType(finTradeProduct.getFinprodType2());
		// 交易日期 = 金融产品交易.交易日
		result.setTradeDate(DateUtil.format(trdProductDeal.getTradeDate()));
		// 近端交割日 = 金融产品交易.交割日
		result.setSettleDate(DateUtil.format(trdProductDeal.getSettleDate()));
		// 远端交割日 = 金融交易产品.到期日
		result.setMaturityDate(DateUtil.format(finTradeProduct.getMdate()));
		// 投资经理组   默认值
		result.setManager(BussinessJixuConstant.投资经理组_默认);
		// 资产类型
		result.setRpSecType(BussinessJixuConstant.资产类型_外汇掉期);
					
		// 成交类型
		if(StringUtil.equals(DictCodeListExt2. 外汇期限类型_即期对远期掉期.getValue(),finTradeProduct.getExcFxsTermType())
				||StringUtil.equals(DictCodeListExt2. 外汇期限类型_Spot.getValue(),finTradeProduct.getExcFxsTermType())) {
			result.setSwapExchangeInd(BussinessJixuConstant.成交类型_即期_远期);
		}else if(StringUtil.equals(DictCodeListExt2.外汇期限类型_远期对远期掉期.getValue(),finTradeProduct.getExcFxsTermType())) {
			result.setSwapExchangeInd(BussinessJixuConstant.成交类型_远期_远期);
		}else{
			result.setSwapExchangeInd(BussinessJixuConstant.成交类型_即期_即期);
		};

//			bankAcct	近端收入银行账户	
//			bankAcct2	近端支出银行账户	
//			principal	近端收入金额	
//			principal2	近端支出金额	
//			tradeCash	远端支出金额	
//			tradeCash2	远端收入金额	
		// 交易货币对
		if(trdProductDealAdd.getCurPair()!=null) {
			String[] curpiar = trdProductDealAdd.getCurPair().split("/");
			// 交易币种
			String dealCur = finTradeProduct.getCcy();
			// 远端交易的交易货币金额 = finTradeProduct.mPrinAmt
			// 远端交易的对应货币金额 = finTradeProduct.mTradeAmt
			// 交易币种为基准货币
			if(StringUtil.equals(dealCur,curpiar[0])){
				// 买/卖
				if(StringUtil.equals(DictCodeListExt2.交易方向_买卖.getValue(),finTradeProduct.getExcPs())) {
					//tradeCash	远端支出金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash(finTradeProduct.getmPrinAmt().toString());
					// tradeCash2	远端收入金额	 = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmTradeAmt().toString());
				}
				// 卖/买
				else if(StringUtil.equals(DictCodeListExt2.交易方向_卖买.getValue(),finTradeProduct.getExcPs())) {
					// tradeCash	远端支出金额	 = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash(finTradeProduct.getmTradeAmt().toString());
					// tradeCash2	远端收入金额	 = 远端交易的对应货币金额  finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmPrinAmt().toString());
				}
			}
			// 交易币种为非基准货币
			else if(StringUtil.equals(dealCur,curpiar[1])){
				// 买/卖
				if(StringUtil.equals(DictCodeListExt2.交易方向_买卖.getValue(),finTradeProduct.getExcPs())) {
					// tradeCash	远端支出金额	 = 远端交易的对应货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash(finTradeProduct.getmTradeAmt().toString());
					//tradeCash2	远端收入金额	 = 远端交易的交易货币金额   finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmPrinAmt().toString());
				}
				// 卖/买
				else if(StringUtil.equals(DictCodeListExt2.交易方向_卖买.getValue(),finTradeProduct.getExcPs())) {
					// tradeCash	远端支出金额	 = 远端交易的对应货币金额  finTradeProduct.mPrinAmt
					if(finTradeProduct.getmPrinAmt()!=null)
					result.setTradeCash(finTradeProduct.getmPrinAmt().toString());
					// tradeCash2	远端收入金额	 = 远端交易的交易货币金额  finTradeProduct.mTradeAmt
					if(finTradeProduct.getmTradeAmt()!=null)
					result.setTradeCash2(finTradeProduct.getmTradeAmt().toString());
				}
			}
			
			if(stlPayOrdersList!=null) {
				for(StlPayOrders stlPayOrders:stlPayOrdersList) {
					if(stlPayOrders==null)continue;
					// 买/卖
					if(StringUtil.equals(DictCodeListExt2.交易方向_买卖.getValue(),finTradeProduct.getExcPs())) {
						// 是否基准货币  多头账户
						if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[0])){
							result.setBankAcct(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setPrincipal(stlPayOrders.getSettleAmt().toString());
						}
						// 是否非基准货币
						else if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[1])){
							result.setBankAcct2(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setPrincipal2(stlPayOrders.getSettleAmt().toString());
						}
					}
					else if(StringUtil.equals(DictCodeListExt2.交易方向_卖买.getValue(),finTradeProduct.getExcPs())) {
						// 是否基准货币
						if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[0])){
							result.setBankAcct2(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setPrincipal2(stlPayOrders.getSettleAmt().toString());
						}
						// 是否非基准货币
						else if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[1])){
							result.setBankAcct(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setPrincipal(stlPayOrders.getSettleAmt().toString());
						}
					}
				}
			}
		}
					
		// tradeFX	近端汇率 =  trdProductDeal.unitFprice
		if(trdProductDeal.getUnitFprice()!=null)
		result.setTradeFX(trdProductDeal.getUnitFprice().toString());
		// tradeFX2	远端汇率 = finTradeProduct.mUnitFprice
		if(trdProductDeal.getUnitFprice()!=null)
		result.setTradeFX2(finTradeProduct.getmUnitFprice().toString());
		
		/**
		 * 交易费用
		 */
		List<TranExpenseDto> expenses = new ArrayList<TranExpenseDto>();
		TranExpenseDto tranExpenseDto = new TranExpenseDto();
		// 交易费用
		if(trdProductDeal!=null&&trdProductDeal.getFeeAmtUnpay()!=null) {
			// 费用结转银行账户  =  支出账户
			if(trdCommDto.getPtlCapacctInfo()!=null)
			tranExpenseDto.setBankAccount(
					trdCommDto.getPtlCapacctInfo().getAcctNo());
			// 费用金额
			tranExpenseDto.setCashAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			tranExpenseDto.setExpenseAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			tranExpenseDto.setTotalAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			// 5034
			tranExpenseDto.setExpense(BussinessJixuConstant.费用类型_掉期手续费);
			// 人民币
			tranExpenseDto.setFeeTradeCurrency(DictCodeList.币种_人民币_CNY.getValue());
			// 1
			tranExpenseDto.setFxRate(1);
			expenses.add(tranExpenseDto);
		}
		result.setExpenses(expenses);
		
		return result;
	}
	
	
	/**
	 * 外汇即期换汇交易
	 * @param o
	 * @return FXOpenDto
	 */
	private FXOpenDto transDtoToFXOpenDto(Object o) {
		// 外汇即期交易就封装成FXOpenDto
		FXOpenDto result = new FXOpenDto();
		// 消息头  
		result.setHeader(BussinessJixuConstant.消息头_外汇即期);
		// 
		ApiTrdCommDto trdCommDto = (ApiTrdCommDto) o;
		
		// 交易类金融产品	
		FinTradeProduct	finTradeProduct = trdCommDto.getFinTradeProduct();
		// 金融产品交易	
		TrdProductDeal trdProductDeal = trdCommDto.getTrdProductDeal();
		// 金融产品交易附属表	
		TrdProductDealAdd trdProductDealAdd = trdCommDto.getTrdProductDealAdd();
		// 划款指令
		List<StlPayOrders> stlPayOrdersList = trdCommDto.getStlPayOrdersList();
		// 为空退出
		if(finTradeProduct==null||trdProductDeal==null||trdProductDealAdd==null||stlPayOrdersList==null) {
			return result;
		}


		// 操作类型
		result.setOperation(trdCommDto.getOperateType());
		
		// 交易货币对
		if(trdProductDealAdd.getCurPair()!=null) {
			String[] curpiar = trdProductDealAdd.getCurPair().split("/");
			if(stlPayOrdersList!=null) {
				for(StlPayOrders stlPayOrders:stlPayOrdersList) {
					if(stlPayOrders==null)continue;
					if(StringUtil.equals(DictCodeList.交易方向_买入.getValue(),finTradeProduct.getExcPs())) {
						// 是否基准货币  多头账户
						if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[0])){
							result.setBankAcct(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash(stlPayOrders.getSettleAmt().toString());
						}
						// 是否非基准货币
						else if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[1])){
							result.setBankAcct2(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash2(stlPayOrders.getSettleAmt().toString());
						}
					}
					else if(StringUtil.equals(DictCodeList.交易方向_卖出.getValue(),finTradeProduct.getExcPs())) {
						// 是否基准货币
						if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[0])){
							result.setBankAcct2(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash2(stlPayOrders.getSettleAmt().toString());
						}
						// 是否非基准货币
						else if(StringUtil.equals(stlPayOrders.getCcy(),curpiar[1])){
							result.setBankAcct(stlPayOrders.getMyAcctNo());
							if(stlPayOrders.getSettleAmt()!=null)
							result.setTradeCash(stlPayOrders.getSettleAmt().toString());
						}
					}
				}
			}
		}
		
		// 资产类型
		result.setAssetType(finTradeProduct.getFinprodType2());
		// 证券名称 = 交易编号
		result.setSecid(trdProductDeal.getTradeId());
		//根据组合代码查询金融产品：净值型和货币型可以找到，预期收益型会返回空
		FinProduct finProduct = dealCommonService.queryFinProductByPortfolidId(trdProductDealAdd.getPortfolioId());
		String booksetid = (finProduct.getFinprodId() == null ? trdProductDealAdd.getPortfolioId() : finProduct.getFinprodId());
		// 产品代码  = 金融产品交易附属.投资组合代码
		result.setPort(booksetid);
		// 交易编号 id = 交易编号
		result.setId(trdProductDeal.getTradeId());
		// 交易类型 衍生品后端的即期开仓交易编号
		result.setTradeType(BussinessJixuConstant.交易类型_即期);
		// 交易日期 = 金融产品交易.交易日
		result.setTradeDate(DateUtil.format(trdProductDeal.getTradeDate()));
		// 结算日期 = 金融产品交易.交割日
		result.setSettleDate(DateUtil.format(trdProductDeal.getSettleDate()));
		// 交易目的  空
		// 交易对手方
//			finTradeProduct.getCounterId();
		
		/**
		 * 交易费用
		 */
		List<TranExpenseDto> expenses = new ArrayList<TranExpenseDto>();
		TranExpenseDto tranExpenseDto = new TranExpenseDto();
		// 交易费用
		if(trdProductDeal!=null&&trdProductDeal.getFeeAmtUnpay()!=null) {
			// 费用结转银行账户  =  支出账户
			if(trdCommDto.getPtlCapacctInfo()!=null)
			tranExpenseDto.setBankAccount(
					trdCommDto.getPtlCapacctInfo().getAcctNo());
			// 费用金额
			tranExpenseDto.setCashAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			tranExpenseDto.setExpenseAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			tranExpenseDto.setTotalAmount(trdProductDeal.getFeeAmtUnpay().doubleValue());
			// 5034
			tranExpenseDto.setExpense(BussinessJixuConstant.费用类型_即期手续费);
			// 人民币
			tranExpenseDto.setFeeTradeCurrency(DictCodeList.币种_人民币_CNY.getValue());
			// 费率1
			tranExpenseDto.setFxRate(1);
			
			expenses.add(tranExpenseDto);
		}
		result.setExpenses(expenses);
		
		return result;
	}
}
