/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：meifaxin
 * 修改时间：2021年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.exchange.entrust.etrexchange.hqjj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.SecPrinSchedule;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.AccurIntForSecheduleCalculator;
import com.joyintech.calculation.shell.PriceByYieldCalculator;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.*;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.FinProductConvertibleMapper;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.bond.BusiFinExercisePlanService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeJjSaveRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationHqResponse;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationRequest;
import com.joyintech.tams.exchange.entrust.etrexchange.hqjj.dto.EtrExchangeTradeQuotationResponse;
import com.joyintech.tams.invest.common.asset.dto.AssetGenerateEntry;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.asset.util.AssetCommonUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.*;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.*;

/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 * 
 * @author meifaxin
 * @version 1.0 2021年12月20日
 * @see EtrExchangeTradeJj001Service
 * @since 1.0
 */
@Service
public class EtrExchangeTradeJj001Service extends BaseService<EtrExchangeTradeJjSaveRequest> {

	public static String queryIntegratedQuote = "/joyin/entrust/integrated/quote";
	
	public static String queryIntegratedTick = "/joyin/quote/tick";

    @Autowired
    FinProductConvertibleMapper finProductConvertibleMapper;

    @Autowired
    FinProductAddMapper finProductAddMapper;

	@Autowired
	FinProductMapper finProductMapper;

	 /**
     * 查询证券代码
     * @return
     */
	public Page<SelectOptionDto> getFinprodMarketInfo(Map<String,Object> map) {
		return restTemplate.postForPage(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/getFinprodMarketInfo", map,
				SelectOptionDto.class);
	}

	/** 查询大宗交易行情信息 **/
	public Page<EtrExchangeTradeQuotationResponse> findTradeQuotationsPage(EtrExchangeTradeQuotationRequest request) {
		Map<String, Object> map = BeanUtil.object2Map(request);
		Page<EtrExchangeTradeQuotationResponse> page = new Page<EtrExchangeTradeQuotationResponse>();
		List<EtrExchangeTradeQuotationResponse> list = new ArrayList<EtrExchangeTradeQuotationResponse>();
        String secId = (String)map.get("secId");
		String param = "?quote_type=1&stock_code="+secId;

		boolean flag = isBuyback(secId);
		Map quoteMap = restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + queryIntegratedQuote + param, Map.class);

		if(quoteMap != null && quoteMap.get("data") !=null) {
			List<Map<String, Object>> quoteList = (List<Map<String, Object>>) quoteMap.get("data");
			if(!StringUtil.isEmptyList(quoteList)) {
				quoteList.forEach(tmp -> {
					EtrExchangeTradeQuotationResponse dto =new EtrExchangeTradeQuotationResponse();
					dto.setSecId((String)tmp.get("stockCode"));
					dto.setSecName((String)tmp.get("stockName"));
                    dto.setMarket((String)tmp.get("market"));
					//行情类型
					//意向申报
					if(StringUtil.equals((String)tmp.get("entrustProp"),"a")) {
						dto.setQuotationType(DictCodeListExt3.申报类型_意向申报.getValue());
					} else if(StringUtil.equals((String)tmp.get("entrustProp"),"b")) {
						//定价申报
						dto.setQuotationType(DictCodeListExt3.申报类型_定价申报.getValue());
					}
					//买卖方向
					// 买入
					if (StringUtil.equals((String) tmp.get("entrustBs"), "1")) {
						dto.setEntrustPs(DictCodeList.交易方向_买入.getValue());
						if (flag) {
							dto.setEntrustPs(DictCodeListExt3.交易方向_正回购.getValue());
						}
					} else if (StringUtil.equals((String) tmp.get("entrustBs"), "2")) {
						// 卖出
						// 定价申报
						dto.setEntrustPs(DictCodeList.交易方向_卖出.getValue());
						if (flag) {
							dto.setEntrustPs(DictCodeListExt3.交易方向_逆回购.getValue());
						}
					}

					//数量
					if((Integer)tmp.get("entrustVolume") !=null) {
						dto.setEntrustNum(new BigDecimal((Integer)tmp.get("entrustVolume")));
					}

					//价格（净价） / 回购利率
					if((Double)tmp.get("entrustPrice") !=null) {
						dto.setEntrustPrice(new BigDecimal((Double)tmp.get("entrustPrice")));
					}

					//对手方交易席位
					dto.setOppositeSeat((String)tmp.get("propSeatNo"));

					//订单编号
					dto.setAgreeNum((String)tmp.get("contractId"));

					//对手方交易员姓名
					dto.setContacts((String)tmp.get("liaisonName"));

					//对手方交易员联系方式
					dto.setContactNumber((String)tmp.get("liaisonTel"));

					//list.add(dto);
				});
			}
		}
		page.setRoot(list);
		return page;
	}

	/** 查询固定收益行情数据 **/
	public Page<EtrExchangeTradeQuotation001Dto> findFixedIncomePage(EtrExchangeTradeQuotationRequest request) {
        Page<EtrExchangeTradeQuotation001Dto> page = new Page<EtrExchangeTradeQuotation001Dto>();
        List<EtrExchangeTradeQuotation001Dto> list = new ArrayList<EtrExchangeTradeQuotation001Dto>();
		Map<String, Object> map = BeanUtil.object2Map(request);
		String secId= (String)map.get("secId");
        String param = "?quote_type=5&stockId="+secId;
        Map quoteMap = restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + queryIntegratedQuote + param, Map.class);
        if(quoteMap != null && quoteMap.get("data") !=null) {
            List<Map<String, Object>> quoteList = (List<Map<String, Object>>) quoteMap.get("data");
            if(!StringUtil.isEmptyList(quoteList)) {
                quoteList.forEach(tmp -> {
                    EtrExchangeTradeQuotation001Dto dto =new EtrExchangeTradeQuotation001Dto();
                    dto.setSecId((String)tmp.get("stockCode"));
                    dto.setSecName((String)tmp.get("stockName"));
                    dto.setMarket((String)tmp.get("market"));
                    //行情类型
                    //买卖方向
                    //买入
                    if(StringUtil.equals((String)tmp.get("entrustBs"),"1")) {
                        dto.setEntrustPs(DictCodeList.交易方向_买入.getValue());
                    } else if(StringUtil.equals((String)tmp.get("entrustBs"),"2")) {
                        //卖出
                        //定价申报
                        dto.setEntrustPs(DictCodeList.交易方向_卖出.getValue());
                    }
                    //数量
                    if(tmp.get("entrustVolume") !=null) {
                        dto.setEntrustNum(new BigDecimal((Integer)tmp.get("entrustVolume")));
                    }
                    //价格（净价） / 回购利率
                    if(tmp.get("entrustPrice") !=null) {
                        dto.setnPrice(new BigDecimal((Double)tmp.get("entrustPrice")));
                    }
                    //全价
                    if(tmp.get("fullPrice") !=null) {
                        dto.setfPrice(new BigDecimal((Double)tmp.get("fullPrice")));
                    }

                    //收益率
                    if(tmp.get("expireIncomeRate") !=null) {
                        dto.setYield(new BigDecimal((Double)tmp.get("expireIncomeRate")));
                    }

                    //订单编号
                    dto.setOrderId((String)tmp.get("contractId"));

                    list.add(dto);
                });
            }
        }
        page.setRoot(list);
        return page;
	}

	/** 查询大宗盘后行情信息 **/
	public Page<EtrExchangeTradeQuotation001Dto> findAfterHoursPage(EtrExchangeTradeQuotationRequest request) {
		Page<EtrExchangeTradeQuotation001Dto> page = new Page<EtrExchangeTradeQuotation001Dto>();
		List<EtrExchangeTradeQuotation001Dto> list = new ArrayList<EtrExchangeTradeQuotation001Dto>();
		Map<String, Object> map = BeanUtil.object2Map(request);
        String secId= (String)map.get("secId");
		String param = "?quote_type=2&stock_code="+secId;
	    Map quoteMap = restTemplate.getForObject(ServiceApplication.EXCHANGE_SERVICE_NAME + queryIntegratedQuote + param, Map.class);
		if(quoteMap != null && quoteMap.get("data") !=null) {
			List<Map<String, Object>> quoteList = (List<Map<String, Object>>) quoteMap.get("data");
			if(!StringUtil.isEmptyList(quoteList)) {
				quoteList.forEach(tmp -> {
					EtrExchangeTradeQuotation001Dto dto =new EtrExchangeTradeQuotation001Dto();
					dto.setSecId((String)tmp.get("stockCode"));
					dto.setSecName((String)tmp.get("stockName"));
                    dto.setMarket((String)tmp.get("market"));
					//收盘价
					if(tmp.get("entrustPrice") !=null) {
						dto.setClosingPrice(new BigDecimal((Double)tmp.get("entrustPrice")));
					}
					//日均价
					if(tmp.get("fullPrice") !=null) {
						dto.setDailyAvgPrice(new BigDecimal((Double)tmp.get("fullPrice")));
					}
					list.add(dto);
				});
			}
		}
		page.setRoot(list);
		return page;
	}

	/** 查询竞价交易行情信息 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EtrExchangeTradeQuotationHqResponse findQuotations(String secId) {
		String param = "?stock_code=" + secId;
		Map<String, Object> quoteList;
		Map quoteMap = restTemplate.getForObject(ServiceApplication.QUOTE_SERVICE_NAME + queryIntegratedTick + param, Map.class);
		if (quoteMap != null && quoteMap.get("data") != null) {
			quoteList = (Map<String, Object>) quoteMap.get("data");
			return BeanUtil.mapToObject(quoteList,EtrExchangeTradeQuotationHqResponse.class);
		}
		return new EtrExchangeTradeQuotationHqResponse();
	}



	/**
     * 根据画面的信息计算委托金额和委托数量。<br/>
     * 如果目标类型为“数量”，则根据数量计算金额<br/>
     * 如果目标类型为“金额”，则根据数量计算数量<br/>
     * 
     * @param param
     * @return 计算结果
     * @author dubaojun
     */
    public Map<String, BigDecimal> calcEntrustNumAndAmt(Map<String, Object> param) {
        String resetFlag = param.get("resetFlag") == null ? null : param.get("resetFlag").toString();
        if (resetFlag != null) {
            Map<String, BigDecimal> resultMap = new HashMap<>();
            resultMap.put("entrustNum", null);
            resultMap.put("entrustAmt", null);
            return resultMap;
        }
        Map<String, BigDecimal> resultMap = new HashMap<>();
        BigDecimal entrustAmt = this.toBigDecimal(param.get("entrustAmt"));
        BigDecimal entrustNum = this.toBigDecimal(param.get("entrustNum"));
        String ps = param.get("ps") == null ? null : param.get("ps").toString();
        String currentTab = param.get("currentTab") == null ? null : param.get("currentTab").toString();
        BigDecimal entrustPrice = this.toBigDecimal(param.get("entrustPrice"));
        String targetType = (String) param.get("targetType");
        String finprodId = (String) param.get("finprodId");
        String finprodType2 = (String) param.get("finprodType2");
        BigDecimal availableNum = param.get("availableNum") == null ? null : new BigDecimal(param.get("availableNum").toString());
        // 判断指令价格是否为百分比
        if (entrustPrice != null && (StringUtil.equals(ps, DictCodeListExt3.交易方向_质押券入库.getValue())
                || StringUtil.equals(ps, DictCodeListExt3.交易方向_质押券出库.getValue()))) {
            entrustPrice = ArithmeticUtil.divide(entrustPrice, new BigDecimal(100), entrustPrice.scale() + 2);
        }
        // 如果是质押式回购，则默认为价格为100
        else if (StringUtil.equals(finprodType2, "ZYSHG")) {
            entrustPrice = new BigDecimal(100);
        }
        String tradeMarket = (String) param.get("tradeMarket");
        if (StringUtil.equals(targetType, DictCodeListExt3.目标类型_金额.getValue())) {
            entrustNum = ArithmeticUtil.divide(entrustAmt, entrustPrice,0,RoundingMode.DOWN);
        }
        
		if (null != param.get("minTrdUnitLimitDto")) {
			MinTrdUnitLimitDto unit = (MinTrdUnitLimitDto) param.get("minTrdUnitLimitDto");
			entrustNum = this.formatEntrustNum(entrustNum, unit);
		} else {
			BigDecimal minNum = new BigDecimal(1);
			MinTrdUnitLimitDto minTrdUnitLimitDto = getInterfSysMinTrdUnit(finprodId, currentTab, tradeMarket, finprodType2, ps);
			if (null != minTrdUnitLimitDto && null != minTrdUnitLimitDto.getMinStep()) {
				minNum = minTrdUnitLimitDto.getMinStep();
			}
			if (null != availableNum && availableNum.compareTo(BigDecimal.ZERO) > 0) {
				resultMap.put("minNum", minNum);
			}
			if (entrustNum == null || entrustNum.compareTo(BigDecimal.ZERO) == 0) {
			} else {
				if (StringUtils.equals(currentTab, "jj")
						&& (DictCodeListExt1.金融产品类型_投管用_债券.getValue().equals(finprodType2)
								|| DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(finprodType2)
								|| DictCodeListExt1.金融产品类型_投管用_基金.getValue().equals(finprodType2))
						&& StringUtils.equals(ps, DictCodeList.交易方向_卖出.getValue())
						&& isSalesOfSmallShares(availableNum, minNum, entrustNum)) {
				} else {
					entrustNum = ArithmeticUtil
							.multiply(ArithmeticUtil.divide(entrustNum, minNum, 0, RoundingMode.DOWN), minNum);
				}
			}
		}
        // int rs = entrustNum.compareTo(BigDecimal.ZERO);
        // rs == -1 entrustNum小于0 例如：entrustNum = -10.00
        // rs == 0 entrustNum等于0 例如：entrustNum = 0.00
        // rs == 1 entrustNum大于0 例如：entrustNum = 10.00
        if (StringUtil.equals(targetType, DictCodeListExt3.目标类型_数量.getValue())) {
            entrustAmt = ArithmeticUtil.multiply(entrustNum, entrustPrice);
            if(StringUtils.equals(DictCodeListExt3.交易方向_质押券入库.getValue(), ps) || 
            		StringUtils.equals(DictCodeListExt3.交易方向_质押券出库.getValue(), ps)){
            	if(null != entrustAmt){//entrustAmt 代表标准券数量
            		entrustAmt = new BigDecimal(entrustAmt.intValue());
            	}
            }
        }/* else {
            if ((entrustPrice != null && entrustPrice.compareTo(BigDecimal.ZERO) != 0)
                    && (entrustNum != null && entrustNum.compareTo(BigDecimal.ZERO) != 0)) {// 价格和数量均不为0,重新计算指令金额
                entrustAmt = ArithmeticUtil.multiply(entrustNum, entrustPrice);
            }
        }*/
        
        resultMap.put("entrustNum", entrustNum);
        resultMap.put("entrustAmt", entrustAmt);
        return resultMap;
    }

    /**
     * 判断是否零股
     * @param availableNum
     * @param minNum
     * @param entrustNum
     * @return
     */
	public Boolean isSalesOfSmallShares(BigDecimal availableNum, BigDecimal minNum, BigDecimal entrustNum) {
		if (null != availableNum && availableNum.compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal multiple = (availableNum.subtract(entrustNum)).divide(minNum);
			// 零股卖出 、整数倍+零股卖出 可以卖出，
			if (new BigDecimal(multiple.intValue()).compareTo(multiple) == 0
					|| (availableNum.intValue() == entrustNum.intValue() && availableNum.compareTo(minNum) <= 0)) {
				return true;
			}
		}
		return false;
	}
    
	/**
	 * 
	 * @param entrustNum 指令数量
	 * @param minTrdUnitLimitDto 最小交易单位对象
	 * @return 格式化指令数量
	 */
	private BigDecimal formatEntrustNum(BigDecimal entrustNum, MinTrdUnitLimitDto minTrdUnitLimitDto) {
		if (entrustNum == null || entrustNum.compareTo(BigDecimal.ZERO) == 0) {
			return entrustNum;
		}
		BigDecimal minNum = new BigDecimal(1);
		if (null != minTrdUnitLimitDto && null != minTrdUnitLimitDto.getMinStep()) {
			minNum = minTrdUnitLimitDto.getMinStep();
		}
		entrustNum = ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustNum, minNum, 0, RoundingMode.DOWN), minNum);
		return entrustNum;
	}
    
	/**
	 * 获取最小交易单元
	 * @param finprodId 金融产品代码
	 * @param tradePlat 交易平台
	 * @param assetType 资产大类
	 * @return 获取最小交易单元
	 */
	@SuppressWarnings({ "unchecked" })
	public MinTrdUnitLimitDto getInterfSysMinTrdUnit(String finprodId, String tradePlat, String tradeMarket,
			String assetType, String ps) {
		MinTrdUnitLimitDto minTrdUnitLimitDto = null;
		tradePlat = getTradePlat(tradePlat, tradeMarket);
		String PINANAssetType = getPAAssetType(assetType);
		// 质押式回购 没有资产代码，虚拟的资产代码
		if (StringUtils.equals(PINANAssetType, DictCodeList.平安资产类型_质押式回购.getValue())) {
			finprodId = null;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("finprodId", finprodId);
		map.put("tradeMarket", tradeMarket);
		map.put("tradePlat", tradePlat);
		map.put("assetType", PINANAssetType);
		map.put("ps", ps);
		Map<String, Object> res = restTemplate.postForObject(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/InterfSysTrdConfig/getMinTrdUnitLimit", map, Map.class);
		if (res != null && res.get("data") != null) {
			List<MinTrdUnitLimitDto> list = (List<MinTrdUnitLimitDto>) res.get("data");
			if (list != null && list.size() > 0) {
				minTrdUnitLimitDto = JSON.parseObject(JSON.toJSONString(list.get(0)), MinTrdUnitLimitDto.class);
			}
		}
		return minTrdUnitLimitDto;
	}
	
	/**
	 * 
	 * @param finprodId 金融产品代码
	 * @param tradePlat 交易平台
	 * @param assetType 资产大类
	 * @return 获取最小价差
	 */
	@SuppressWarnings("unchecked")
	public MinPirceDto getInterfSysMinPrice(String finprodId, String tradePlat, String tradeMarket, String assetType, BigDecimal ...price) {
		MinPirceDto minPirceDto = null;
		tradePlat = getTradePlat(tradePlat, tradeMarket);
		String PINANAssetType = getPAAssetType(assetType);
		// 质押式回购 没有资产代码，虚拟的资产代码
		if (StringUtils.equals(PINANAssetType, DictCodeList.平安资产类型_质押式回购.getValue())) {
			finprodId = null;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("finprodId", finprodId);
		map.put("tradePlat", tradePlat);
		map.put("tradeMarket", tradeMarket);
		map.put("assetType", PINANAssetType);
		if (price.length > 0 && null != price[0]) {
			map.put("currPrice", new BigDecimal(price[0].toString()));
		}
		Map<String, Object> res = restTemplate.postForObject(
				ServiceApplication.BASICDATA_SERVICE_NAME + "/InterfSysTrdConfig/getMinPrice", map, Map.class);
		if (res != null && res.get("data") != null) {
			List<MinPirceDto> list = (List<MinPirceDto>) res.get("data");
			if (list != null && list.size() > 0) {
				minPirceDto = JSON.parseObject(JSON.toJSONString(list.get(0)), MinPirceDto.class);
			}
		}
		return minPirceDto;
	}

	/**
	 * 
	 * @param currentTab 交易平台
	 * @param tradeMarket 交易场所
	 * @return 交易平台
	 */
	private String getTradePlat(String currentTab, String tradeMarket) {
		String tradePlat = DictCodeList.交易平台_竞价撮合平台.getValue();
		if (StringUtils.isNotEmpty(currentTab)) {
			if ((currentTab.equals("dz") || currentTab.equals(DictCodeList.交易平台_大宗交易平台.getValue()))
					&& DictCodeListExt1.交易场所_深圳证券交易所.getValue().equals(tradeMarket)) {
				tradePlat = DictCodeList.交易平台_综合协议交易平台.getValue();
			} else if ((currentTab.equals("dz") || currentTab.equals(DictCodeList.交易平台_大宗交易平台.getValue()))
					&& DictCodeListExt1.交易场所_上海证券交易所.getValue().equals(tradeMarket)) {
				tradePlat = DictCodeList.交易平台_大宗交易平台.getValue();
			} else if (currentTab.equals("gd") || currentTab.equals(DictCodeList.交易平台_固定收益平台.getValue())) {
				tradePlat = DictCodeList.交易平台_固定收益平台.getValue();
			}
		}
		return tradePlat;
	}
	

	/**
	 * 
	 * @param finprodType2
	 * @return
	 */
	private String getPAAssetType(String finprodType2) {
		String assetType = "";
		if (StringUtils.isNotEmpty(finprodType2)) {
			if (StringUtils.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
				assetType = DictCodeList.平安资产类型_债券.getValue();
			} else if (StringUtils.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
				assetType = DictCodeList.平安资产类型_基金.getValue();
			} else if (StringUtils.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
				assetType = DictCodeListExt2.平安资产类型_股票.getValue();
			} else if (StringUtils.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
					|| StringUtils.equals(finprodType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
					|| StringUtils.equals(finprodType2, "ZYSHG")) {
				assetType = DictCodeList.平安资产类型_质押式回购.getValue();
			}
		}
		return assetType;
	}

    /**
     * 转为数值类型
     * 
     * @param object Object
     * @return 数值
     */
    private BigDecimal toBigDecimal(Object object) {
        if(object == null || object == "") {
            return null;
        }
        return new BigDecimal(object.toString());
    }

	/**
	 * 获取最小交易单元
	 * @param unit
	 * @param <T>
	 * @return
	 */
	private <T extends EtrEntrust> MinTrdUnitLimitDto getMinTrdUnitLimit(T unit){
		return StringUtil.isEmptyList(Arrays.asList(unit.getFinprodId(),unit.getPs()))?null:getInterfSysMinTrdUnit(unit.getFinprodId(), unit.getTradePlat(),
				unit.getTradeMarket(), unit.getFinprodType2(), unit.getPs());
	}

    /**
     * 指令数量与金额相互反算
     * @param unit
     * @param consumer
     * @param <T>
     * @return
     */
    private <T extends EtrExchangeTradeJjSaveRequest> Map<String, BigDecimal> calNumAndAmt(T unit, Consumer<Map> consumer){
        // 重新计算指令金额
        Map<String, Object> paramMap = BeanUtil.object2Map(unit);
		paramMap.put("currentTab", unit.getTradePlat());
        consumer.accept(paramMap);
        Map<String, BigDecimal> result = calcEntrustNumAndAmt(paramMap);
       return result;
    }

	/**
	 * check后的保存 <功能详细描述>
	 * 
	 * @param equityList
	 *            需要保存的数据
	 * @see
	 * @since 1.0
	 */
	public void dealAfterCheck(List<EtrExchangeTradeJjSaveRequest> equityList) {
		List<EtrExchangeTradeJjSaveRequest> listN = new ArrayList<EtrExchangeTradeJjSaveRequest>();
		for (int i = 0; i < equityList.size(); i++) {
			EtrExchangeTradeJjSaveRequest assetDeclarationCodeMaintenanceDto = equityList.get(i);
			EtrExchangeTradeJjSaveRequest equityInfoCheck = null;
			if (equityInfoCheck == null) {
				listN.add(assetDeclarationCodeMaintenanceDto);
			}
		}
/*		if (listN != null && listN.size() > 0) {
			this.insertBtachEquityInfoByPart(listN);
		}
		if (listA != null && listA.size() > 0) {
			this.updateBtachEquityInfoByPart(listA);
		}*/
	}

	public boolean isBuyback(String secId) {
		boolean flag = false;
		switch (secId) {
		case "131800.SZ":
		case "131801.SZ":
		case "131802.SZ":
		case "131803.SZ":
		case "131805.SZ":
		case "131806.SZ":
		case "131809.SZ":
		case "131810.SZ":
		case "131811.SZ":
		case "204001.SH":
		case "204002.SH":
		case "204003.SH":
		case "204004.SH":
		case "204007.SH":
		case "204014.SH":
		case "204028.SH":
		case "204091.SH":
		case "204182.SH":
			flag = true;
			break;
		default:
			flag = false;
		}
		return flag;
	}

}
