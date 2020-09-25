/*
 * 文件名：FinProductLedgerService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.ledger.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.product.common.dao.BenchMarketInfoMapper;
import com.joyintech.service.product.common.dao.FinProductFloatFeeMapper;
import com.joyintech.service.product.common.dao.ProductCommonMapper;
import com.joyintech.service.product.common.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.common.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.service.product.common.dto.BussFinProductInvestPeriodDto;
import com.joyintech.service.product.common.dto.BussFinProductLayeredInfoDto;
import com.joyintech.service.product.common.dto.FinProductFloatFeeDto;
import com.joyintech.service.product.common.service.BenchMarketInfoService;
import com.joyintech.service.product.messageadjust.bmquotation.dao.FinProductBmQuotationMapper;
import com.joyintech.service.product.messageadjust.bmquotation.dto.BenchmarkRate;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.service.product.productelement.build.dao.FinProductBuildMapper;
import com.joyintech.service.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.service.product.productelement.ledger.dto.FinProductLedger001Dto;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 产品新建管理
 *
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinProductLedgerService
 * @since 1.0
 */
@Service("backFinProductLedgerService")
public class FinProductLedgerService extends BaseService<FinProductBuild001Dto> {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductBuildMapper finProductBuildMapper;
    @Autowired(required=false)
    private FinProductFloatFeeMapper finProductFloatFeeMapper;
    @Autowired(required = false)
    private ProductCommonMapper productCommonMapper;
    @Autowired(required=false)
	private BenchMarketInfoMapper benchMarketInfoMapper;
    @Autowired
    private BenchMarketInfoService benchMarketInfoService;
    
	/**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<FinProductBuild001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
    	pageParam.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
    	pageParam.put("statusValidA", DictCodeListExt1.有效状态_待处理.getValue());
    	pageParam.put("statusValidEName", DictCodeListExt1.有效状态_有效.getCodeName());
        pageParam.put("statusValidAName", DictCodeListExt1.有效状态_待处理.getCodeName());
    	pageParam.put("manObiTypeFinProd", DictCodeListExt1.管理对象类型_金融产品.getValue());
    	pageParam.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
    	pageParam.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
    	pageParam.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
    	pageParam.put("yes", DictCodeList.是否_是.getValue());
    	pageParam.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());

    	Page<FinProductBuild001Dto> dto = super.queryForPage(page, length, () -> finProductBuildMapper.selectForPage(pageParam));
    	return dto;
    }

    @Autowired
    private FinProductBmQuotationMapper finProductBmQuotationMapper;
    
    public List<FinFloatCalcRuleDetail> queryFloatFeeDetailByFinprodId(String finprodId, String cashId, String queryDate){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashId", cashId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        //是否来自浮动管理费控件
        map.put("dealMode10", DictCodeList.处理模式_周期型.getValue());
        map.put("termType02", DictCodeListExt2.期限类型_多固定期限.getValue());

        map.put("queryDate", queryDate);
        return finProductBuildMapper.queryFloatFeeDetailByFinprodId(map);
    }
    
    public List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId2(String finprodId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("baseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
        List<FinProductFloatFeeDto> finProductFloatFeeDtos = finProductFloatFeeMapper.queryLayerFloatFeeInfoByFinprodId(map);
        finProductFloatFeeDtos.forEach(finProductFloatFeeDto -> {
            map.put("finprodId", finProductFloatFeeDto.getFinprodid());
            finProductFloatFeeDto.setFinFloatCalcRuleDetailDtoList(finProductBuildMapper.queryFloatFeeDetailByFinprodId2(map));
        } );
        return finProductFloatFeeDtos;
    }
    
    public List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId3(String finprodId,String queryDate){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("baseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
        map.put("queryDate", queryDate);
        List<FinProductFloatFeeDto> finProductFloatFeeDtos = finProductBuildMapper.queryLayerFloatFeeInfoByFinprodId(map);
        List<FinProductFloatFeeDto> list = new ArrayList<FinProductFloatFeeDto>();
        String productId = "";
        Date effDate = null;
        int loop = 0;
        int loop2 = 0;
        for(FinProductFloatFeeDto dto:finProductFloatFeeDtos) {
        	if(loop >0) {
        		if(StringUtil.equals(productId,dto.getFinprodid())) {
        			if(loop2 >0) {
        				if(DateUtil.compareDate(effDate, dto.getEffDate()) == 0) {
        					list.add(dto);
        				}
        			}
        		}
        		else {
        			loop=loop2=1;
        			productId = dto.getFinprodid();
        			effDate = dto.getEffDate();
        			list.add(dto);
        		}
        	}
        	else {
        		loop++;loop2++;
        		productId = dto.getFinprodid();
        		effDate = dto.getEffDate();
        		list.add(dto);
        	}
        }
        list.forEach(finProductFloatFeeDto -> {
        	Map<String, Object> param = new HashMap<String, Object>();
        	param.put("cashId", finProductFloatFeeDto.getCashId());
        	param.put("effDate", DateUtil.format(finProductFloatFeeDto.getEffDate()));
            finProductFloatFeeDto.setFinFloatMgtExpQuotationDtoList(finProductBuildMapper.getRuleDetail(param));
        } );
        return list;
    }
    
    public Page<FinProductBuild001Dto> selectForLedger(int page, int length, Map<String, Object> pageParam) throws Exception {
    	pageParam.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
    	pageParam.put("statusValidA", DictCodeListExt1.有效状态_待处理.getValue());
    	pageParam.put("statusValidEName", DictCodeListExt1.有效状态_有效.getCodeName());
        pageParam.put("statusValidAName", DictCodeListExt1.有效状态_待处理.getCodeName());
    	pageParam.put("manObiTypeFinProd", DictCodeListExt1.管理对象类型_金融产品.getValue());
    	pageParam.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
    	pageParam.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
    	pageParam.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
    	pageParam.put("yes", DictCodeList.是否_是.getValue());
    	pageParam.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());

    	Page<FinProductBuild001Dto> dto = super.queryForPage(page, length, () -> finProductBuildMapper.selectForLedger(pageParam));
    	
    	List<FinProductBuild001Dto> llist = dto.getRoot();       
        List<FinProductBuild001Dto> rlist = new ArrayList<FinProductBuild001Dto>();
        for (FinProductBuild001Dto mdto : llist) {
        	FinProductLedger001Dto rdto = (FinProductLedger001Dto)mdto;
        	// 单位净值 值空处理
        	rdto.setTdyUnitValue(rdto.getTdyUnitValue() == BigDecimal.ZERO ? null : rdto.getTdyUnitValue());
        	// 七日年化收益率% 值空处理
        	rdto.setYield7(rdto.getYield7() == BigDecimal.ZERO ? null : rdto.getYield7());
        	pageParam.put("finBrach", 0);	
        	pageParam.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
        	pageParam.put("finProdId", rdto.getFinprodId());
        	String prodManager = rdto.getProdManager();
        	if(StringUtil.isNotEmpty(prodManager)) {
        		Map<String, Object> map = new HashMap<String, Object>();
        		map.put("initValue", prodManager);
        		List<SelectOptionDto> select = productCommonMapper.selectByUserName(map);
        		if(select != null && select.size()>0)
        		rdto.setProdManager(select.get(0).getText());
        	}
        	BussBenchMarkLayeredInfoDto bDto=new BussBenchMarkLayeredInfoDto();
        	if (StringUtil.isNotEmpty(rdto.getFinprodId())) {
//    			String floatFeeProportion = "";
    			String rateYieldFloat1="";
        		if(StringUtil.equals(DictCodeList.处理模式_分档.getValue(), rdto.getDealMode())) {
        			List<FinProductFloatFeeDto> pList = queryLayerFloatFeeInfoByFinprodId2(rdto.getFinprodId());
//        			int pstyle = 0;
        			for(FinProductFloatFeeDto pdto:pList) {
//        				List<FinFloatCalcRuleDetail> finList = pdto.getFinFloatCalcRuleDetailDtoList();
//        				if(pstyle > 0) {
//        					floatFeeProportion += ",";
//        					rateYieldFloat1+=";";
//        				}else if(pstyle == 0) {
//        					pstyle++;
//        				}
//	        			floatFeeProportion += "款型" + pdto.getProdStyle() + ":";
        				BeanUtil.copyPropertiesIgnoreNull(bDto,pdto);
        				if(StringUtil.equals(pdto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
	        				rateYieldFloat1+= "款型" + pdto.getProdStyle() + ":"+ pdto.getRate();
        				}
        				else {
	        				rateYieldFloat1+= "款型" + pdto.getProdStyle() + ":"+ getFloatRate(bDto);
        				}
//	        			int loop = 0;
//	        			for(FinFloatCalcRuleDetail fdto:finList) {
//	        				if(loop > 0) {
//	        					floatFeeProportion += "/";
//	        				}else if(loop == 0) {
//	        					loop++;
//	        				}
//	        				floatFeeProportion += "层级" + fdto.getLev() + " " + "比例" + fdto.getChargeRatio();
//	        			}
        			}
        		}
        		else if(StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), rdto.getDealMode())) {
        			String queryDate = (String)pageParam.get("queryDate"); 
        			List<FinProductFloatFeeDto> pList = queryLayerFloatFeeInfoByFinprodId3(rdto.getFinprodId(),queryDate);
//        			int layer = 0;
        			for(FinProductFloatFeeDto pdto:pList) {
//        				List<FinFloatMgtExpQuotationDto> finList = pdto.getFinFloatMgtExpQuotationDtoList();
//        				if(layer > 0) {
//        					floatFeeProportion += ",";
//        				}else if(layer == 0) {
//        					layer++;
//        				}
//	        			floatFeeProportion += "期限" + pdto.getInvestmentCycle() + ":";
//	        			int loop = 0;
//	        			for(FinFloatMgtExpQuotationDto fdto:finList) {
//	        				if(loop > 0) {
//	        					floatFeeProportion += "/";
//	        					rateYieldFloat1+= ";";
//	        				}else if(loop == 0) {
//	        					loop++;
//	        				}
//	        				floatFeeProportion += "层级" + fdto.getLev() + " " + "比例" + fdto.getChargeRatio();
	        				BeanUtil.copyPropertiesIgnoreNull(bDto,pdto);
	        				if(StringUtil.equals(pdto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
	        					rateYieldFloat1+= "期限" + pdto.getInvestmentCycle() + ":"+ pdto.getRate();
	        				}else {
	        					rateYieldFloat1+= "期限" + pdto.getInvestmentCycle() + ":"+ getFloatRate(bDto);
	        				}
//	        			}
        			}
        			if(StringUtil.equals(DictCodeListExt2.期限类型_多固定期限.getValue(), rdto.getTermType())) {
        				List<BussFinProductInvestPeriodDto> periodList = finProductBuildMapper.queryPeriodList(pageParam);
        				String investmentCycle = "";
        				int loop = 0;
	        			for(BussFinProductInvestPeriodDto pdto:periodList) {
	        				if(loop > 0) {
	        					investmentCycle += ",";
	        				}else if(loop == 0) {
	        					loop++;
	        				}
	        				investmentCycle += pdto.getInvestDays();
	        			}
	        			rdto.setInvestmentCycle(investmentCycle);
        			}
        		}        		
        		else {
        			pageParam.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
        			List<FinBaseRuleInfo> info = finProductBuildMapper.getFinBaseRuleInfo(pageParam);
//        			List<FinFloatCalcRuleDetail> finList = queryFloatFeeDetailByFinprodId(rdto.getFinprodId(),"",(String)pageParam.get("queryDate"));
//        			int loop = 0;
//        			for(FinFloatCalcRuleDetail fdto:finList) {
//        				if(loop > 0) {
//        					floatFeeProportion += "/";
//        				}else if(loop == 0) {
//        					loop++;
//        				}
//        				floatFeeProportion += "层级" + fdto.getLev() + " " + "比例" + fdto.getChargeRatio();
//        			}
        			for (FinBaseRuleInfo qto : info) {
        				BeanUtil.copyPropertiesIgnoreNull(bDto, qto);
        				if(StringUtil.equals(qto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
        					rateYieldFloat1 = qto.getRate().multiply(BigDecimal.valueOf(100)).toString();
        				}else {
        					rateYieldFloat1 = getFloatRate(bDto);
        				}
        			}
        				
        		}
//        		rdto.setFloatFeeProportion(floatFeeProportion);
        		rdto.setRateYieldFloat(rateYieldFloat1);
            }
//        	if(!DictCodeList.收益类型_收益型.getValue().equals(rdto.getProfitType())) {
//        		if(!StringUtil.equals(DictCodeList.处理模式_分档.getValue(), rdto.getDealMode()) && 
//        				!StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), rdto.getDealMode())) {
//	        	List<FinBaseRuleInfo> finBaseRuleInfoList = this.getFinBaseRuleInfo(pageParam);
//	        	if(finBaseRuleInfoList!=null && finBaseRuleInfoList.size() > 0) {
//	        		FinBaseRuleInfo finBaseRuleInfo = finBaseRuleInfoList.get(0);
//	        		if(DictCodeListExt1.披露类型_区间披露.getValue().equals(finBaseRuleInfo.getPublishType())) {
////	        			rdto.setRateRepYield(finBaseRuleInfo.getRateLower());
//	        		}
//	        		else {
//	        			if(DictCodeList.利率类型_固定利率.getValue().equals(finBaseRuleInfo.getRateType())) {
////	        				rdto.setRateRepYield(finBaseRuleInfo.getRate());
//	        			}
//	        			else {
//	        				BigDecimal realIntRate = BigDecimal.valueOf(0);
//	                        BigDecimal intRate = BigDecimal.valueOf(0);
//	        				if(DictCodeListExt1.基准类型_2_利率.getValue().equals(finBaseRuleInfo.getIndexSource())){
//	                            Map<String,Object> map = new HashMap<String,Object>();
//	                            map.put("indexId", finBaseRuleInfo.getIndexId());
//	                            map.put("effDate", DateUtil.format(finBaseRuleInfo.getEffDate()));
//	                            if(finProductBmQuotationMapper.getIntRate(map) != null){
//	                                intRate = finProductBmQuotationMapper.getIntRate(map);
//	                                realIntRate = intRate;
//	                            }
//	                            
//	                        }
//	                        //基准类型 = 复合基准时
//	                        //查询出该基准代码所对应的所有行情信息
//	                        else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(finBaseRuleInfo.getIndexSource())
//	                                || DictCodeListExt1.基准类型_2_SAA.getValue().equals(finBaseRuleInfo.getIndexSource())){
//	                            Map<String, Object> param = new HashMap<String, Object>();
//	                            param.put("indexId", finBaseRuleInfo.getIndexId());
//	                            //设置平台日
//	                            param.put("effDate", DateUtil.format(finBaseRuleInfo.getEffDate()));
//	                            //查出出该基准代码所对应的所有行情信息
//	                            List<BenchmarkRate> list = finProductBmQuotationMapper.getBenchmarkRate(param);
//	                            //循环利率*权重
//	                            if(list != null && list.size()>0){
//	                                for(BenchmarkRate benchmarkRate :list){
//	                                    realIntRate = realIntRate.add(intRate.add(benchmarkRate.getIntRate().multiply(benchmarkRate.getWeight())));
//	                                }
//	                            }
//	                        }
////	                        if(finBaseRuleInfo.getCoefficient() == null){
////	                            if(finBaseRuleInfo.getSpreadRate() != null){
////	                            	rdto.setRateRepYield(realIntRate.add(finBaseRuleInfo.getSpreadRate()));
////	                            }else if(BigDecimal.ZERO.compareTo(realIntRate)!=0){
////	                            	rdto.setRateRepYield(realIntRate);
////	                            }
////
////	                        }
////	                        else if(finBaseRuleInfo.getCoefficient() != null && finBaseRuleInfo.getSpreadRate() != null){
////	                            if(realIntRate != null){
////	                            	rdto.setRateRepYield(realIntRate.multiply(finBaseRuleInfo.getCoefficient()).add(finBaseRuleInfo.getSpreadRate()));
////	                            }
////	                        }
//	                        
//	        			}
//	        		}
//	        		if(StringUtil.isNotEmpty(rdto.getRateRepYield())){
//	        			//BSTOT-1139 产品台账-货币型产品的预期收益率/业绩比较基准多了100倍 guyunfei 20200212 start
//	        			//rdto.setRateRepYield(rdto.getRateRepYield().multiply(BigDecimal.valueOf(100)));
//						//BSTOT-1139 产品台账-货币型产品的预期收益率/业绩比较基准多了100倍 guyunfei 20200212 end
//	        			rdto.setRateRepYieldStr(rdto.getRateRepYield().toString());
//	        		}
//        		}
//        	}
//        		else{
//        			pageParam.put("baseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
//        			pageParam.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
//        			pageParam.put("finprodId", rdto.getFinprodId());
//        			List<BussBenchMarkInfoDto> benchList = finProductBuildMapper.selectByFinprodidList(pageParam);
//    	        	if(benchList!=null && benchList.size() > 0) {
////    	        		BigDecimal rateRepYield = BigDecimal.ZERO;
//    	        		BigDecimal rateLower = BigDecimal.ZERO;
//    	        		BigDecimal rateLimit = BigDecimal.ZERO;
//    	        		for(BussBenchMarkInfoDto finBaseRuleInfo:benchList) {    
//    	        			Map<String, Object> ruleParam = new HashMap<String, Object>();
//    	        			ruleParam.put("finBrach", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
//    	        			ruleParam.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
//    	        			ruleParam.put("finProdId", finBaseRuleInfo.getFinprodId());
//    	        			String queryDate = (String)pageParam.get("queryDate"); 
//    	        			ruleParam.put("queryDate", queryDate);
//    	        			List<FinBaseRuleInfo> ruleList = finProductBuildMapper.getFinBaseRuleInfo2(ruleParam);
//    	    	        	if(ruleList!=null && ruleList.size() > 0) {
//    	    	        		FinBaseRuleInfo ruleInfo = ruleList.get(0);    
//    	    	        		BeanUtil.copyProperties(finBaseRuleInfo, ruleInfo);
//    	    	        	}   	        		
//	    	        		if(DictCodeListExt1.披露类型_区间披露.getValue().equals(finBaseRuleInfo.getPublishType())) {
////	    	        			rateRepYield = rateRepYield.add(finBaseRuleInfo.getRateLower());
//	    	        			if(finBaseRuleInfo.getRateLower()!=null) {
//	    	        				if(rateLower.compareTo(BigDecimal.ZERO) == 0 || rateLower.compareTo(finBaseRuleInfo.getRateLower()) > 0)
//	    	        					rateLower = finBaseRuleInfo.getRateLower();
//	    	        			}
//	    	        			if(finBaseRuleInfo.getRateLimit()!=null) {
//	    	        				if(rateLimit.compareTo(BigDecimal.ZERO) == 0 || rateLimit.compareTo(finBaseRuleInfo.getRateLimit()) < 0)
//	    	        					rateLimit = finBaseRuleInfo.getRateLimit();
//	    	        			}
//	    	        		}
//	    	        		else {
//	    	        			if(DictCodeList.利率类型_固定利率.getValue().equals(finBaseRuleInfo.getRateType())) {
////	    	        				rateRepYield = rateRepYield.add(finBaseRuleInfo.getRate());
//	    	        				if(finBaseRuleInfo.getRate()!=null) {
//		    	        				if(rateLower.compareTo(BigDecimal.ZERO) == 0 || rateLower.compareTo(finBaseRuleInfo.getRate()) > 0)
//		    	        					rateLower = finBaseRuleInfo.getRate();
//		    	        				if(rateLimit.compareTo(BigDecimal.ZERO) == 0 || rateLimit.compareTo(finBaseRuleInfo.getRate()) < 0)
//		    	        					rateLimit = finBaseRuleInfo.getRate();
//		    	        			}
//	    	        			}
//	    	        			else {
//	    	        				BigDecimal realIntRate = BigDecimal.valueOf(0);
//	    	                        BigDecimal intRate = BigDecimal.valueOf(0);
//	    	        				if(DictCodeListExt1.基准类型_2_利率.getValue().equals(finBaseRuleInfo.getIndexSource())){
//	    	                            Map<String,Object> map = new HashMap<String,Object>();
//	    	                            map.put("indexId", finBaseRuleInfo.getIndexId());
//	    	                            map.put("effDate", DateUtil.format(finBaseRuleInfo.getEffDate()));
//	    	                            if(finProductBmQuotationMapper.getIntRate(map) != null){
//	    	                                intRate = finProductBmQuotationMapper.getIntRate(map);
//	    	                                realIntRate = intRate;
//	    	                            }
//	    	                            
//	    	                        }
//	    	                        //基准类型 = 复合基准时
//	    	                        //查询出该基准代码所对应的所有行情信息
//	    	                        else if(DictCodeListExt1.基准类型_2_复合.getValue().equals(finBaseRuleInfo.getIndexSource())
//	    	                                || DictCodeListExt1.基准类型_2_SAA.getValue().equals(finBaseRuleInfo.getIndexSource())){
//	    	                            Map<String, Object> param = new HashMap<String, Object>();
//	    	                            param.put("indexId", finBaseRuleInfo.getIndexId());
//	    	                            //设置平台日
//	    	                            param.put("effDate", DateUtil.format(finBaseRuleInfo.getEffDate()));
//	    	                            //查出出该基准代码所对应的所有行情信息
//	    	                            List<BenchmarkRate> list = finProductBmQuotationMapper.getBenchmarkRate(param);
//	    	                            //循环利率*权重
//	    	                            if(list != null && list.size()>0){
//	    	                                for(BenchmarkRate benchmarkRate :list){
//	    	                                    realIntRate = realIntRate.add(intRate.add(benchmarkRate.getIntRate().multiply(benchmarkRate.getWeight())));
//	    	                                }
//	    	                            }
//	    	                        }
//	    	        				BigDecimal floatRate = null;
//	    	                        if(finBaseRuleInfo.getCoefficient() == null){
//	    	                            if(finBaseRuleInfo.getSpreadRate() != null){
////	    	                            	rateRepYield = rateRepYield.add(realIntRate.add(finBaseRuleInfo.getSpreadRate()));
//	    	                            	floatRate = realIntRate.add(finBaseRuleInfo.getSpreadRate());
//	    	                            }else if(BigDecimal.ZERO.compareTo(realIntRate)!=0){
////	    	                            	rateRepYield = rateRepYield.add(realIntRate);
//	    	                            	floatRate = realIntRate;
//	    	                            }
//	    	                            
//	    	                        }
//	    	                        else if(finBaseRuleInfo.getCoefficient() != null && finBaseRuleInfo.getSpreadRate() != null){
//	    	                            if(realIntRate != null){
////	    	                            	rateRepYield = rateRepYield.add(realIntRate.multiply(finBaseRuleInfo.getCoefficient()).add(finBaseRuleInfo.getSpreadRate()));
//	    	                            	floatRate = realIntRate.multiply(finBaseRuleInfo.getCoefficient()).add(finBaseRuleInfo.getSpreadRate());
//	    	                            }
//	    	                        }
//	    	        				if(floatRate!=null) {
//		    	        				if(rateLower.compareTo(BigDecimal.ZERO) == 0 || rateLower.compareTo(floatRate) > 0)
//		    	        					rateLower = floatRate;
//		    	        				if(rateLimit.compareTo(BigDecimal.ZERO) == 0 || rateLimit.compareTo(floatRate) < 0)
//		    	        					rateLimit = floatRate;
//		    	        			}
//	    	                        
//	    	        			}
//	    	        		}
//	    	        	}
////    	        		if(BigDecimal.ZERO.compareTo(rateRepYield)!=0){
////    	        			rateRepYield = ArithmeticUtil.divide(rateRepYield,BigDecimal.valueOf(benchList.size()),6);
////    	        			//rdto.setRateRepYield(rateRepYield);
////    	        			rdto.setRateRepYield(rateRepYield);
////    	        		}
////    	        		else {
////    	        			rdto.setRateRepYield(null);
////    	        		}
//    	        		if(BigDecimal.ZERO.compareTo(rateLower)!=0){
//    	        			if(rateLower.compareTo(rateLimit)==0)
//    	        				rdto.setRateRepYieldStr(ArithmeticUtil.round(rateLower,2,null).toString());
//    	        			else
//    	        				rdto.setRateRepYieldStr(ArithmeticUtil.round(rateLower,2,null) + "-" +ArithmeticUtil.round(rateLimit,2,null));
//    	        		}
//    	        		else rdto.setRateRepYieldStr(null);
//            		}
//        		}
//        	}
        	if(DictCodeList.收益类型_货币型.getValue().equals(rdto.getProfitType())) {
        		//根据组合代码查询付息周期
    			Map<String, Object> map =new HashMap<String, Object>();
    			map.put("finprodid", rdto.getFinprodId());
    			FinCashPayRule finCashPayRule = finProductBuildMapper.getPayCycle(map);
    			if(finCashPayRule.getPayCycleVal() ==null||"".equals(finCashPayRule.getPayCycleVal())){
    				rdto.setPayCycle(SysDataUtil.getNameFromDataDict(DictCode.分红规则, rdto.getPayCycle()));
    			}else{
    				String paycycle = SysDataUtil.getNameFromDataDict(DictCode.频率, rdto.getPayCycle());
    				rdto.setPayCycle(finCashPayRule.getPayCycleVal()+paycycle);
    			}
        	}else if(DictCodeList.收益类型_收益型.getValue().equals(rdto.getProfitType())){
    			//根据组合代码查询付息周期
    			Map<String, Object> map =new HashMap<String, Object>();
    			map.put("finprodid", rdto.getFinprodId());
    			FinCashPayRule finCashPayRule = finProductBuildMapper.getPayCycle(map);
    			if(finCashPayRule.getPayCycleVal() ==null||"".equals(finCashPayRule.getPayCycleVal())){
    				rdto.setPayCycle(DictCodeListExt1.付息周期_到期支付.getCodeName());
    			}else{
    				String paycycle = SysDataUtil.getNameFromDataDict(DictCode.频率, rdto.getPayCycle());
    				rdto.setPayCycle(finCashPayRule.getPayCycleVal()+paycycle);
    			}
    			
//    			if(StringUtil.equals(DictCodeList.处理模式_分档.getValue(), rdto.getDealMode())) {
//	    			List<BussFinProductLayeredInfoDto> list = new ArrayList<>();
//	    			pageParam.put("finprodId", rdto.getFinprodId());
//	    	    	list = finProductBuildMapper.getLayerParam(pageParam);
//	    	    	if(list!=null && list.size() > 0) {
//		        		BigDecimal rateLower = BigDecimal.ZERO;
//		        		BigDecimal rateLimit = BigDecimal.ZERO;
//		        		for(BussFinProductLayeredInfoDto layerinfo:list) { 
//		        			if(layerinfo.getYield()!=null) {
//		        				if(rateLower.compareTo(BigDecimal.ZERO) == 0 || rateLower.compareTo(layerinfo.getYield()) > 0)
//		        					rateLower = layerinfo.getYield();
//		        				if(rateLimit.compareTo(BigDecimal.ZERO) == 0 || rateLimit.compareTo(layerinfo.getYield()) < 0)
//		        					rateLimit = layerinfo.getYield();
//		        			}
//		        		}
//		        		if(BigDecimal.ZERO.compareTo(rateLower)!=0){
//		        			if(rateLower.compareTo(rateLimit)==0)
//		        				rdto.setRateRepYieldStr(ArithmeticUtil.round(rateLower,2,null).toString());
//		        			else
//		        				rdto.setRateRepYieldStr(ArithmeticUtil.round(rateLower,2,null) + "-" +ArithmeticUtil.round(rateLimit,2,null));
//		        		}
//	        		}
//    	    	}
//    			else {
//    				if(rdto.getRateRepYield()!=null && BigDecimal.ZERO.compareTo(rdto.getRateRepYield())!=0)
//    					rdto.setRateRepYieldStr(ArithmeticUtil.round(rdto.getRateRepYield(),2,null).toString());
//    				else
//    					rdto.setRateRepYieldStr(null);
//    			}
    		}
    		
    		rlist.add(rdto);        		        		
        }
        dto.setRoot(rlist);
    	return dto;
    }
    
    public List<FinBaseRuleInfo> getFinBaseRuleInfo(Map<String, Object> map){
        return finProductBuildMapper.getFinBaseRuleInfo(map);
    }
    //业绩基准浮动管理费计算规则

    public String getFloatRate(BussBenchMarkLayeredInfoDto dto) {
      	 List<BussBenchMarkLayeredInfoDto> result = new ArrayList<>();
      	 result.add(dto);
      	 result = benchMarketInfoService.getBenchMarkLayeredInfoList(result);
           String floatRate = "";
           if(result != null && result.size()>0){
           	BussBenchMarkLayeredInfoDto bussBenchMarkLayeredInfoDto = result.get(0);
               floatRate = bussBenchMarkLayeredInfoDto.getFloatRate() == null? "": bussBenchMarkLayeredInfoDto.getFloatRate().toString();
           }
           return floatRate;
      }

}
