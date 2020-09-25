package com.joyintech.tams.invest.product.resultscovering.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealAddMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.resultscovering.dto.FinResultsCovering002Dto;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class FinResultsCovering002Service extends BaseService<FinResultsCovering002Dto> implements BaseProcessService<FinResultsCovering002Dto>{
	@Autowired
	private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;
    /**
     * 接口共通
     */
    @Autowired
    private InterfCommonService interfCommonService;
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

	@Override
	public void doComplete(FinResultsCovering002Dto dto, OperationType type) {
		if (OperationType.NEW.equals(type)) {
			//生成汇总交易编号
			String totalTradeId= interfCommonService.getTradeid("HB", dto.getCreateTime(), "A");
			TrdFinProductDealTotal trdFinProductDealTotal=new TrdFinProductDealTotal();
			trdFinProductDealTotal.setTradeStatus(BussinessDictCodeList.交易状态_初始.getValue());
			trdFinProductDealTotal.setVdate(dto.getCovCycleStartDate());
			trdFinProductDealTotal.setMdate(dto.getCovCycleEndDate());
			trdFinProductDealTotal.setSettleDate(dto.getCoveringDate());
			trdFinProductDealTotal.setTradeDate(dto.getCoveringDate());
			trdFinProductDealTotal.setIntAmt(dto.getActualAmount());
			trdFinProductDealTotal.setFinprodId(dto.getProdId());
			trdFinProductDealTotal.setBusiType("31");
			trdFinProductDealTotal.setCcy(dto.getCcy());
			if(StringUtils.isNotEmpty(dto.getProdId()) && dto.getCovCycleEndDate()!=null){
				trdFinProductDealTotal.setShareAmt(querySumAmt(dto));
			}
			trdFinProductDealTotal.setPs(DictCodeList.交易方向_回补.getValue());//回补
			trdFinProductDealTotal.setTotleTradeId(totalTradeId);
			trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
			if(StringUtil.equals(dto.getCoveringWay(), DictCodeListExt1.回补规则_仅使用浮动管理费回补.getValue())){
				TrdProductDeal  trdProductDeal=new TrdProductDeal();
				trdProductDeal.setTradeStatus(BussinessDictCodeList.交易状态_初始.getValue());
				if(StringUtil.equals(dto.getIsLay(), DictCodeList.是否_是.getValue())) {
				    trdProductDeal.setFinprodId(dto.getLayFinprodId());
				} else {
				    trdProductDeal.setFinprodId(dto.getProdId()); 
				}
				
				trdProductDeal.setVdate(dto.getCovCycleStartDate());
				trdProductDeal.setMdate(dto.getCovCycleEndDate());
				trdProductDeal.setTradeDate(dto.getCoveringDate());
				trdProductDeal.setSettleDate(dto.getCoveringDate());
				trdProductDeal.setIntAmt(dto.getActualAmount());
				trdProductDeal.setShareAmt(trdFinProductDealTotal.getShareAmt());
				trdProductDeal.setCcy(trdFinProductDealTotal.getCcy());
				trdProductDeal.setPs(trdFinProductDealTotal.getPs());
				trdProductDeal.setBusiType("31");
				//估值和投管都是C04
				trdProductDeal.setFinprodType(BussinessDictCodeList.金融产品类型_投管用_浮动管理费.getValue());
				trdProductDeal.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_浮动管理费.getValue());
				trdProductDeal.setTradeId(dto.getTradeId());
				trdProductDeal.setTotleTradeId(totalTradeId);
				trdProductDealMapper.insertSelective(trdProductDeal);
				TrdProductDealAdd trdProductDealAdd=new TrdProductDealAdd();
				trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
				trdProductDealAdd.setTradeId(dto.getTradeId());
				trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
				
				trdProductDealAddMapper.insertSelective(trdProductDealAdd);
				 AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
		            inputDto.setProdid(trdProductDeal.getFinprodId());
		            inputDto.setProdFinprodType(BussinessDictCodeList.金融产品类型_理财产品.getValue());
		            inputDto.setTrdProductDeal(trdProductDeal);
		            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
			}
			if(StringUtil.equals(dto.getCoveringWay(), DictCodeListExt1.回补规则_仅使用固定管理费回补.getValue())){
				TrdProductDeal  trdProductDeal=new TrdProductDeal();
				TrdProductDealAdd trdProductDealAdd=new TrdProductDealAdd();
				trdProductDeal.setTradeId(dto.getTradeId());
				trdProductDealAdd.setTradeId(dto.getTradeId());
				if(StringUtil.equals(dto.getIsLay(), DictCodeList.是否_是.getValue())) {
                    trdProductDeal.setFinprodId(dto.getLayFinprodId());
                } else {
                    trdProductDeal.setFinprodId(dto.getProdId()); 
                }
				trdProductDeal.setTradeStatus(BussinessDictCodeList.交易状态_初始.getValue());
				trdProductDeal.setVdate(dto.getCovCycleStartDate());
				trdProductDeal.setMdate(dto.getCovCycleEndDate());
				trdProductDeal.setTradeDate(dto.getCoveringDate());
				trdProductDeal.setSettleDate(dto.getCoveringDate());
				trdProductDeal.setIntAmt(dto.getActualAmount());
				trdProductDeal.setShareAmt(trdFinProductDealTotal.getShareAmt());
				trdProductDeal.setCcy(trdFinProductDealTotal.getCcy());
				trdProductDeal.setPs(trdFinProductDealTotal.getPs());
				trdProductDeal.setTotleTradeId(trdFinProductDealTotal.getTotleTradeId());
				trdProductDeal.setBusiType(DictCodeList.业务类型_产品回补交易.getValue());
				//估值和投管都是C03
				trdProductDeal.setFinprodType(BussinessDictCodeList.金融产品类型_投管用_固定管理费.getValue());
				trdProductDeal.setFinprodType2(BussinessDictCodeList.金融产品类型_投管用_固定管理费.getValue());
				trdProductDealMapper.insertSelective(trdProductDeal);
				trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
				trdProductDealAddMapper.insertSelective(trdProductDealAdd);
				 AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
		            inputDto.setProdid(trdProductDeal.getFinprodId());
		            inputDto.setProdFinprodType(BussinessDictCodeList.金融产品类型_理财产品.getValue());
		            inputDto.setTrdProductDeal(trdProductDeal);
		            AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
			}
			// 所有交易生效后调用的共通方法
				           
		}else if(type.equals(OperationType.REVOKE) || type.equals(OperationType.DELETE)){
			
			   // 根据汇总交易编号查询金融产品交易汇总
	        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(
	        		dto.getTradeId());
	        if (trdFinProductDealTotal != null) {
				trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
	        }
	        //原汇总交易是否撤销改为是
	        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
	        trdFinProductDealTotalRevoke.setTotleTradeId(dto.getTradeId());
	        trdFinProductDealTotalRevoke.setIsCancel(BussinessDictCodeList.是否_是.getValue());
	        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotal);
	        
		     // 根据汇总交易编号查询交易附属信息
	        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.genTrdProductDealAddRevoke(
	        		dto.getTradeId());
	        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
				trdProductDealAddList.forEach(trdProductDealAdd -> trdProductDealAddMapper.insertSelective(trdProductDealAdd));
	        }
	        
	        // 根据汇总交易编号查询交易信息
	        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(
	        		dto.getTradeId(), null);
	        if (!StringUtil.isEmptyList(trdProductDealList)) {
				trdProductDealList.forEach(trdProductDeal -> trdProductDealMapper.insertSelective(trdProductDeal));
	        }
	        
	        //原交易是否撤销改为是 
	        List<TrdProductDeal> pTrdProductDealList = productCommonService.queryTrdProductDealByTotleTradeId(
	            dto.getTradeId());
	        if (!StringUtil.isEmptyList(pTrdProductDealList)) {
	            for(TrdProductDeal productDeal:pTrdProductDealList) {
	                productDeal.setIsCancel(BussinessDictCodeList.是否_是.getValue());
	                trdProductDealMapper.updateByPrimaryKeySelective(productDeal); 
	            }
	        }
	        
	        // 交易类金融产品（金融产品代码+成立日分支序号）
	        if (!StringUtil.isEmptyList(trdProductDealList)) {
	            for (TrdProductDeal trdProductDeal : trdProductDealList) {
	                // 一体化对应etliu start
	                // 所有交易生效后调用的共通方法
	                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
	                inputDto.setProdid(trdProductDeal.getFinprodId());
	                inputDto.setProdFinprodType(BussinessDictCodeList.金融产品类型_理财产品.getValue());
	                //inputDto.setProdVdate(new Date(trdFinProductDealTotal.getSettleDate().getTime()));
	                inputDto.setTrdProductDeal(trdProductDeal);
	                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
	                // 一体化对应etliu end
	            }
	        }
	       /* // 原始汇总交易的状态改为已撤销
	        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
	        trdFinProductDealTotalRevoke.setTotleTradeId(dto.getTradeId());
	        trdFinProductDealTotalRevoke.setIsCancel(BussinessDictCodeList.是否_是.getValue());
	        trdFinProductDealTotalRevoke.setCreateDept(null);
	        trdFinProductDealTotalRevoke.setCreateTime(null);
	        trdFinProductDealTotalRevoke.setCreateDept(null);
	        bussCommonMapperDelegateService.updateByPrimaryKeySelective(
	            TrdFinProductDealTotalMapper.class, trdFinProductDealTotalRevoke);
	        // 原始交易的状态改为已撤销
	        productCommonService.updateTrdProductDealByTotleTradeId(dto.getTradeId());*/

		}
	}
	/**
	 *获取总实收资本(SUM(产品头寸.当日份额))	
	 * @param dto
	 * @return
	 */
	public BigDecimal querySumAmt(FinResultsCovering002Dto dto) {
		return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinResultsCovering/getSumAmt",dto,
				BigDecimal.class);
	}
	
	/**
	 * 获取组合代码
	 */
//	public String queryPortfolioId(FinResultsCovering002Dto dto) {
//		return simpleRestTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
//				+ "/FinResultsCovering/getPortfolioId",dto,
//				String.class);
//	}
	/**
	 * 获取基本信息
	 */
	public FinResultsCovering002Dto selectBaseInfo(Map<String, Object> map) {
		map.put("COMMON_BUSINESS_TYPE_01", BussinessDictCodeList.共同业务类型_金融产品.getValue());
        map.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
        map.put("CASH_TYPE_2_C11", DictCodeListExt1.现金流二级类型_回补.getValue());
        map.put("ANAPLEROTIC_TYPE_01", DictCodeListExt1.回补方式_手工回补.getValue());
		return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinResultsCovering/selectBaseInfo",map,
				FinResultsCovering002Dto.class);
	}
	
	public FinResultsCovering002Dto selectInfo(String prodId,String tradeId) {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("prodId", prodId);
    	map.put("tradeId", tradeId);
    	map.put("COMMON_BUSINESS_TYPE_01", BussinessDictCodeList.共同业务类型_金融产品.getValue());
        map.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
        map.put("CASH_TYPE_2_C11", DictCodeListExt1.现金流二级类型_回补.getValue());
		return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinResultsCovering/selectInfo",map,
				FinResultsCovering002Dto.class);
	}
	public List<FinResultsCovering002Dto> selectInfoCovering(String prodId,String bookDate) {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("prodId", prodId);
    	map.put("bookDate", bookDate);
    	map.put("COMMON_BUSINESS_TYPE_01", BussinessDictCodeList.共同业务类型_金融产品.getValue());
        map.put("CASH_TYPE_1_06", DictCodeListExt1.现金流一级类型_回补.getValue());
        map.put("CASH_TYPE_2_C11", DictCodeListExt1.现金流二级类型_回补.getValue());
		return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME
				+ "/FinResultsCovering/selectInfoCovering",map,
				FinResultsCovering002Dto.class);
	}
}
