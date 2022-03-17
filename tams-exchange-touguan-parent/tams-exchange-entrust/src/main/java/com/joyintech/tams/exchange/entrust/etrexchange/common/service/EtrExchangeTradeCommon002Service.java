/*
 * 文件名：EtrTradePlaceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：meifaxin
 * 修改时间：2021年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.service.entrust.etrexchangetrade.dto.EtrExchangeTrade002Dto;
import com.joyintech.tams.business.common.dao.JoyinbusinessCommonMapper;
import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dao.EtrExchangeTradeCommonMapper;
import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.*;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.position.PositionCashSecDto;
import com.joyintech.tams.invest.common.entrust.dao.EtrTrdCommonMapper;
import com.joyintech.tams.invest.common.entrust.inter.EtrTradeCommonervice;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

/**
 *  交易所指令交易（债券股票回购基金场内指令交易）
 * @author meifaxin
 * @version 1.0 2021年12月20日
 * @see EtrExchangeTradeCommon002Service
 * @since 1.0
 */
@Service
public class EtrExchangeTradeCommon002Service extends BaseService {

	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private IPlatformDate iPlatformDate;

	@Autowired
	private EtrExchangeTradeCommonMapper etrExchangeTradeCommonMapper;
	@Autowired
	private JoyinbusinessCommonMapper joyinbusinessCommonMapper;
	@Autowired
	private EtrTradeCommonervice etrTradeCommonervice;
    /**
     * 分页查询产品持仓信息
     * <功能详细描述>
     * @param dto 查询参数
     * @return 分页结果
     * @see
     * @since 1.0
     */
	public Page<EtrExchangeTradePositionsCommonResponse> findPositionsPage(EtrExchangeTradePositionsCommonRequest dto) {
		try {
			Map<String, Object> param = BeanUtil.object2Map(dto);
			String userId = SecurityUtil.getLoginUser().getUserName();
			if (StringUtils.isNotEmpty(userId)) {
				logger.info("请求用户【{}】", "userId = " + userId);
				param.put("userId", SecurityUtil.getLoginUser().getUserName());
				String portfolioId = (String)param.get("portfolioId");
				if (StringUtil.isNotEmpty(portfolioId)) {
					param.put("portfolioIdList",
							StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
				}
				param.put("sysDate", iPlatformDate.getSysDate());
				param.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
				param.put("POSITION_TYPE_04", BussinessDictCodeList.持仓类型_质押.getValue());
				param.put("POSITION_TYPE_05", BussinessDictCodeList.持仓类型_融入.getValue());
				param.put("POSITION_TYPE_06", BussinessDictCodeList.持仓类型_融出.getValue());
				param.put("VALUATION_SOURCE_CSD", BussinessDictCodeList.估值来源_中证登.getValue());
				param.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
				param.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
				param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
				param.put("PS_T01", BussinessDictCodeList.交易方向_买入.getValue());
				param.put("PS_T02", BussinessDictCodeList.交易方向_卖出.getValue());
				param.put("PS_T03", BussinessDictCodeList.交易方向_行权回售.getValue());
				param.put("PS_T05", BussinessDictCodeList.交易方向_申购确认.getValue());
				param.put("PS_T19", BussinessDictCodeList.交易方向_赎回确认.getValue());
				param.put("PS_T46", DictCodeListExt2.交易方向_配股.getValue());
				param.put("PS_T48", DictCodeListExt2.交易方向_新股中签.getValue());
				param.put("PS_T49", DictCodeListExt3.交易方向_债转股.getValue());
				param.put("PS_T97", DictCodeListExt3.交易方向_配债.getValue());
				param.put("PS_T04", DictCodeList.交易方向_申购.getValue());
				param.put("TRADE_PLACE_03", BussinessDictCodeList.交易场所_深圳证券交易所.getValue());
				param.put("TRADE_PLACE_04", BussinessDictCodeList.交易场所_上海证券交易所.getValue());
				param.put("search_isNumberFlag", BaseMap.isNumeric(dto.getSearch()));
				if(StringUtil.isNotEmpty((String)param.get("finprodMarketIds"))){
					param.put("finprodMarketIds",StringUtil.splitToList(GlobalConstant.COMMA, param.get("finprodMarketIds").toString()));
				}else{
					param.put("finprodMarketIds", null);
				}
				// 浮动盈亏计算修改 modify by yufei 20200806 start
				Page<EtrExchangeTradePositionsCommonResponse> result = super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(), () -> etrExchangeTradeCommonMapper.findPositionsPage(param));
				if(!StringUtil.isEmptyList(result.getRoot())){
					// 计算浮动盈亏
					for(EtrExchangeTradePositionsCommonResponse entity : result.getRoot()){
						//可用数量
						PositionCashSecDto secDto=new PositionCashSecDto();
						BeanUtil.copyPropertiesIgnoreNull(secDto,entity);
						secDto.setHoldingType(BussinessDictCodeList.持仓类型_可用.getValue());
						BigDecimal sellNum=etrTradeCommonervice.selectAvailableNum(secDto);
						entity.setSellNum(sellNum);
						//持仓数量
						secDto.setHoldingType(BussinessDictCodeList.持仓类型_实际.getValue());
						BigDecimal positionsNum=etrTradeCommonervice.selectAvailableNum(secDto);
						entity.setPositionsNum(positionsNum);
						// 最新净价*组合持仓.数量 - 持仓成本 as 浮动盈亏
						BigDecimal floatFd = ArithmeticUtil.multiply(entity.getPrice(),entity.getPtlPositionsNum());
						floatFd = ArithmeticUtil.subtract(floatFd,entity.getPositionsAmt());
						entity.setFloating(floatFd);
						BigDecimal floatPL =ArithmeticUtil.divide(floatFd,ArithmeticUtil.multiply(entity.getPrice(),entity.getPtlPositionsNum()),2);
						entity.setFloatingPL(floatPL);
					}
				}
				return result;
//				return restTemplate.postForPage(
//						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/findPositionsPage", param,
//						EtrExchangeTrade002Dto.class);
			}
		} catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		return null;
	}

	// modify by etliu 债券质押持仓查询只有非交易业务指令使用，代码删除 20220308 start
//	/**
//	 * 分页查询债券质押持仓信息
//	 *
//	 * @param request
//	 *            查询参数
//	 * @return 分页结果
//	 * @see
//	 * @since 1.0
//	 */
//	public Page<EtrExchangeTradePositionsCommonResponse> findZqzyPositionsPage(EtrExchangeTradePositionsCommonRequest request) {
//		try {
//			Map<String, Object> pageParam = BeanUtil.object2Map(request);
//			String userId = SecurityUtil.getLoginUser().getUserName();
//			if (StringUtils.isNotEmpty(userId)) {
//				logger.info("请求用户【{}】", "userId = " + userId);
//				pageParam.put("userId", SecurityUtil.getLoginUser().getUserName());
//				pageParam.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
//				pageParam.put("POSITION_TYPE_04", BussinessDictCodeList.持仓类型_质押.getValue());
//				pageParam.put("POSITION_TYPE_05", BussinessDictCodeList.持仓类型_融入.getValue());
//				pageParam.put("POSITION_TYPE_06", BussinessDictCodeList.持仓类型_融出.getValue());
//				pageParam.put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
//				pageParam.put("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
//				pageParam.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
//				pageParam.put("PS_T01", BussinessDictCodeList.交易方向_买入.getValue());
//				pageParam.put("PS_T02", BussinessDictCodeList.交易方向_卖出.getValue());
//				pageParam.put("PS_T03", BussinessDictCodeList.交易方向_行权回售.getValue());
//				pageParam.put("PS_T05", BussinessDictCodeList.交易方向_申购确认.getValue());
//				pageParam.put("PS_T19", BussinessDictCodeList.交易方向_赎回确认.getValue());
//				pageParam.put("PS_T46", DictCodeListExt2.交易方向_配股.getValue());
//				pageParam.put("PS_T48", DictCodeListExt2.交易方向_新股中签.getValue());
//				pageParam.put("PS_T49", DictCodeListExt3.交易方向_债转股.getValue());
//				pageParam.put("PS_T97", DictCodeListExt3.交易方向_配债.getValue());
//				pageParam.put("PS_T04", DictCodeList.交易方向_申购.getValue());
//				pageParam.put("LIBRARY_DIRECTION_01", DictCodeListExt3.交易方向_质押券入库.getValue());
//				pageParam.put("TRADE_PLACE_03", BussinessDictCodeList.交易场所_深圳证券交易所.getValue());
//				pageParam.put("TRADE_PLACE_04", BussinessDictCodeList.交易场所_上海证券交易所.getValue());
//				pageParam.put("MORE_CONVRATE", 0.9);
//				if(StringUtil.isNotEmpty(pageParam.get("date"))) {
//					pageParam.put("date", DateUtil.parse((String) pageParam.get("date")));
//				}
//				else {
//					pageParam.put("date", iPlatformDate.getSysDate());
//				}
//				if(StringUtil.isNotEmpty((String)pageParam.get("finprodMarketIds"))){
//					pageParam.put("finprodMarketIds",StringUtil.splitToList(GlobalConstant.COMMA, pageParam.get("finprodMarketIds").toString()));
//				}else{
//					pageParam.put("finprodMarketIds", null);
//				}
//				Page<EtrExchangeTradePositionsCommonResponse> result = super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(),
//						() -> etrExchangeTradeCommonMapper.findZqzyPositionsPage(pageParam));
//				return result;
////				return restTemplate.postForPage(
////						ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/findZqzyPositionsPage", param,
////						EtrExchangeTrade002Dto.class);
//			}
//		} catch (Exception ex) {
//			logger.info(ex.getMessage());
//		}
//		return null;
//	}
	// modify by etliu 债券质押持仓查询只有非交易业务指令使用，代码删除 20220308 end

    /**
     * 查询证券代码联想框文本框
     *
     * @param request 画面参数
     * @return 证券代码联想框文本框
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getSecAcctId(SelectOptionRequestDto request) {
		Map<String, Object> param = BeanUtil.object2Map(request);
		Page<SelectOptionDto> tempList = super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(), () -> etrExchangeTradeCommonMapper.getSecAcctId(param));
        return tempList;
    }

    /**
     * 查询上交所和深交所标准券数量
     *
     * @param request 页面参数
     * @return 上交所和深交所标准券数量
     * @see
     * @since 1.0
     */
    public Map<String, String> loadSecNum(SelectOptionRequestDto request) {
		Map<String, Object> pageParam = BeanUtil.object2Map(request);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("secNum_sh", "0");
		resultMap.put("secNum_color_sh", "red");
		resultMap.put("secNum_sz", "0");
		resultMap.put("secNum_color_sz", "red");
		List<EtrExchangeTrade002Dto> dtoList = etrExchangeTradeCommonMapper.selectSecNum(pageParam);
		for (EtrExchangeTrade002Dto dto : dtoList) {
			if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
				java.text.DecimalFormat format = new java.text.DecimalFormat("###,###,###");
				resultMap.put("secNum_sh", format.format(dto.getSecNum()));
				if(dto.getSecNum().compareTo(BigDecimal.ZERO) < 0) {
					resultMap.put("secNum_color_sh", "green");
				}
			}
			else if(StringUtil.equals(dto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
				java.text.DecimalFormat format = new java.text.DecimalFormat("###,###,###");
				resultMap.put("secNum_sz", format.format(dto.getSecNum()));
				if(dto.getSecNum().compareTo(BigDecimal.ZERO) < 0) {
					resultMap.put("secNum_color_sz", "green");
				}
			}
		}
		return resultMap;
    }


	public Page<EtrExchangeTradeEtrExchangeCommonResponse> selectEtrExchange(EtrExchangeTradeEtrExchangeCommonRequest request) {
		Map<String, Object> param = BeanUtil.object2Map(request);
		param.put("userId", SecurityUtil.getLoginUser().getUserName());
		param.put("sysDate",iPlatformDate.getSysDateStr());
		param.put("PS_03", DictCodeListExt3.交易方向_正回购.getValue());
		param.put("PS_04", DictCodeListExt3.交易方向_逆回购.getValue());
		param.put("PS_T68", DictCodeListExt3.交易方向_质押券入库.getValue());
		param.put("PS_T69", DictCodeListExt3.交易方向_质押券出库.getValue());
		String portfolioId = (String)param.get("portfolioId");
		if (StringUtil.isNotEmpty(portfolioId)) {
			param.put("portfolioIdList",
					StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
		}
		if(CollectionUtils.isNotEmpty(request.getEntrustTypeList())){
			param.put("entrustTypeList", request.getEntrustTypeList());
		}
		param.put("search_isNumberFlag", BaseMap.isNumeric(request.getSearch()));
		List<String> tradePlatList = new ArrayList<>();
		if(request.getTradePlat().equals(DictCodeList.交易平台_大宗交易平台.getValue())){
			 tradePlatList.add(request.getTradePlat());
			 tradePlatList.add(DictCodeList.交易平台_综合协议交易平台.getValue());
		}else{
			tradePlatList.add(request.getTradePlat());
		}
		param.put("tradePlatList",tradePlatList);
		if(StringUtil.isNotEmpty((String)param.get("finprodMarketIds"))){
			param.put("finprodMarketIds",StringUtil.splitToList(GlobalConstant.COMMA, param.get("finprodMarketIds").toString()));
		}else{
			param.put("finprodMarketIds", null);
		}
		//申报类型 - 模糊查询
		if(StringUtils.isNotEmpty((String)param.get("search"))){
			List<String> declaTypeList = new ArrayList<>();
			List<? extends DataDictItem> list = SysDataUtil.getDataDictItem(DictCode.申报类型.getValue());
			if(!list.isEmpty()&&list.size()>0){
				for (DataDictItem t:
						list) {
					String typeValue = t.getItemValue();
					if(typeValue.indexOf((String)param.get("search"))!=-1){
						declaTypeList.add(t.getItemCode());
					}
				}
				param.put("declaTypeList", declaTypeList);
			}
		} else {
			param.put("declaTypeList", null);
		}
		return super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(), () -> etrExchangeTradeCommonMapper.selectEtrExchange(param));
//		return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrExchangeTrade/selectEtrExchange",
//				param, EtrExchangeTrade003Dto.class);
	}
	  /* 指令导出履历处理
     *
	 * @param dataList 数据列表
     * @author jiangpeng
     * @date 2021/12/20 14:33
			*/
	public void handExportTimes(List<EtrExchangeTradeEtrExchangeCommonRequest> dataList) {
		List<CfetsOprHisInfoRequest> result = new ArrayList<>();
		dataList.forEach(item -> {
			if (StringUtil.isNotEmpty(item.getEntrustId())) {
				CfetsOprHisInfoRequest cfetsOprHisInfoRequest = new CfetsOprHisInfoRequest();
				cfetsOprHisInfoRequest.setEntrustId(item.getEntrustId());
				cfetsOprHisInfoRequest.setOperType(DictCodeListExt3.操作类型_导出.getValue());
				cfetsOprHisInfoRequest.setSeqNo(IDGenerator.generate(32));
				cfetsOprHisInfoRequest.setOperDate(DateUtil.platDateFromCache());
				cfetsOprHisInfoRequest.setOperTime(DateUtil.platDateFromCache());
				cfetsOprHisInfoRequest.setUserId(SecurityUtil.getLoginUser().getUserName());
				cfetsOprHisInfoRequest.setCreateUser(SecurityUtil.getLoginUser().getUserName());
				cfetsOprHisInfoRequest.setCreateDept(CommonUtil.getDeptCode());
				cfetsOprHisInfoRequest.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
				result.add(cfetsOprHisInfoRequest);
			}
		});
		joyinbusinessCommonMapper.mergeCfetsOprHis(result);
	}

	/**
	 * 查询指令编号联想框文本框
	 *
	 * @param request 画面参数
	 * @return 证券代码联想框文本框
	 * @see
	 * @since 1.0
	 */
	public Page<SelectOptionDto> getId(SelectOptionRequestDto request) {
		Map<String, Object> param = BeanUtil.object2Map(request);
		Page<SelectOptionDto> tempList = super.queryForPage(request.getPageMode().getPageNo(), request.getPageMode().getPageSize(), () -> etrExchangeTradeCommonMapper.getId(param));
		return tempList;
	}
}
