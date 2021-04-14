/*
 * 文件名：SecPosAutoGenerationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.proledger.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstIndexInfoMapper;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dao.ProLedgerAutoGenerationMapper;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.BenchmarkRate;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.BussFinProductLayeredInfoDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.FinBaseRuleInfoDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.FinFloatMgtExpQuotationDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.FinProductFloatFeeDto;
import com.joyintech.tams.invest.batch.autogeneration.proledger.dto.ProLedgerAutoGenerationDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;

/**
 * 产品台账报表自动生成批处理Service
 *
 * @author lwj
 * @version 1.0 2018年11月20日
 * @see ProLedgerAutoGenerationService
 * @since 1.0
 */
@Service
public class ProLedgerAutoGenerationService {

    /**
     * ProLedgerAutoGenerationMapper
     */
    @Autowired
    private ProLedgerAutoGenerationMapper proLedgerAutoGenerationMapper;
    @Autowired(required = false)
    private MstIndexInfoMapper mstIndexInfoMapper;
    @Autowired
	private SqlSessionTemplate template;

    /**
     * 产品台账报表自动生成
     *
     * @param batchDate 执行日
     * @param productIdList
     * @see
     * @since 1.0
     */
    public void autoGeneration(Date batchDate, List<String> productIdList)  throws Exception {

    	// 删除当天的的产品台账
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String batchDateNow=(new SimpleDateFormat("yyyy-MM-dd")).format(DateUtil.addDay(batchDate, -1));
        paramMap.put("batchDate", batchDateNow);
        String queryDate = batchDateNow;
        paramMap.put("queryDate", batchDateNow);
        if (productIdList.size() < 1000) {
            paramMap.put("productIdList", productIdList);
        }
        else {
            paramMap.put("productIdList", null);
            productIdList = null;
        }
        proLedgerAutoGenerationMapper.deleteTodayProLedgerData(paramMap);

        paramMap.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
    	paramMap.put("statusValidA", DictCodeListExt1.有效状态_待处理.getValue());
    	paramMap.put("statusValidEName", DictCodeListExt1.有效状态_有效.getCodeName());
        paramMap.put("statusValidAName", DictCodeListExt1.有效状态_待处理.getCodeName());
    	paramMap.put("manObiTypeFinProd", DictCodeListExt1.管理对象类型_金融产品.getValue());
    	paramMap.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
    	paramMap.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
    	paramMap.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
    	paramMap.put("yes", DictCodeList.是否_是.getValue());
    	paramMap.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        
        // 生成当日产品台账
        // 获取未清盘的产品信息
        String queryDateStart = DateUtil.format(DateUtil.getFirstDay(DateUtil.addDay(batchDate, -1)));
        //String queryDateEnd = DateUtil.format(DateUtil.addDay(batchDate, -1));
//		List<ProLedgerAutoGenerationDto> proLedgerAutoGenerationDtoList = proLedgerAutoGenerationMapper.selectTodayProLedgerData(paramMap);
		List<ProLedgerAutoGenerationDto> proLedgerAutoGenerationDtoList = proLedgerAutoGenerationMapper.selectTodayProLedgerData(batchDateNow,queryDateStart,productIdList);
		
		
		List<ProLedgerAutoGenerationDto> llist = proLedgerAutoGenerationDtoList;       
        List<ProLedgerAutoGenerationDto> rlist = new ArrayList<ProLedgerAutoGenerationDto>();
        for (ProLedgerAutoGenerationDto mdto : llist) {
        	ProLedgerAutoGenerationDto rdto = (ProLedgerAutoGenerationDto)mdto;
        	rdto.setCdate(DateUtil.addDay(batchDate, -1));
        	paramMap.put("finBrach", 0);	
        	paramMap.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
        	paramMap.put("finProdId", rdto.getFinprodId());
        	BussBenchMarkLayeredInfoDto bDto=new BussBenchMarkLayeredInfoDto();
        	bDto.setEffDate(DateUtil.addDay(batchDate, -1));
        	if (StringUtil.isNotEmpty(rdto.getFinprodId())) {
    			String floatFeeProportion = "";
        		if(StringUtil.equals(DictCodeList.处理模式_分档.getValue(), rdto.getDealMode())) {
        			List<FinProductFloatFeeDto> pList = queryLayerFloatFeeInfoByFinprodId2(rdto.getFinprodId());
        			int pstyle = 0;
        			for(FinProductFloatFeeDto pdto:pList) {
        				List<FinFloatCalcRuleDetail> finList = pdto.getFinFloatCalcRuleDetailDtoList();
        				if(pstyle > 0) {
        					floatFeeProportion += ",";
        				}else if(pstyle == 0) {
        					pstyle++;
        				}
	        			floatFeeProportion += "款型" + pdto.getProdStyle() + ":";
        				BeanUtil.copyPropertiesIgnoreNull(bDto,pdto);
        				if(StringUtil.equals(pdto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
        					floatFeeProportion+= "款型" + pdto.getProdStyle() + ":"+ pdto.getRate();
        				}
        				else {
        					floatFeeProportion+= "款型" + pdto.getProdStyle() + ":"+ getFloatRate(bDto);
        				}
	        			int loop = 0;
	        			for(FinFloatCalcRuleDetail fdto:finList) {
	        				if(loop > 0) {
	        					floatFeeProportion += "/";
	        				}else if(loop == 0) {
	        					loop++;
	        				}
	        				floatFeeProportion += "层级" + fdto.getLev() + " " + "比例" + fdto.getChargeRatio();
	        			}
        			}
        		}
        		else if(StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), rdto.getDealMode())) {
        			List<FinProductFloatFeeDto> pList = queryLayerFloatFeeInfoByFinprodId3(rdto.getFinprodId(),queryDate);
        			int layer = 0;
        			for(FinProductFloatFeeDto pdto:pList) {
        				List<FinFloatMgtExpQuotationDto> finList = pdto.getFinFloatMgtExpQuotationDtoList();
        				if(layer > 0) {
        					floatFeeProportion += ",";
        				}else if(layer == 0) {
        					layer++;
        				}
	        			floatFeeProportion += "期限" + pdto.getInvestmentCycle() + ":";
	        			int loop = 0;
	        			for(FinFloatMgtExpQuotationDto fdto:finList) {
	        				if(loop > 0) {
	        					floatFeeProportion += "/";
	        				}else if(loop == 0) {
	        					loop++;
	        				}
	        				floatFeeProportion += "层级" + fdto.getLev() + " " + "比例" + fdto.getChargeRatio();
	        				BeanUtil.copyPropertiesIgnoreNull(bDto,pdto);
	        				if(StringUtil.equals(pdto.getRateType(), DictCodeList.利率类型_固定利率.getValue())) {
	        					floatFeeProportion+= "期限" + pdto.getInvestmentCycle() + ":"+ pdto.getRate();
	        				}else {
	        					floatFeeProportion+= "期限" + pdto.getInvestmentCycle() + ":"+ getFloatRate(bDto);
	        				}
	        			}
        			}
        		}        		
        		else {
        			paramMap.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
        			List<FinBaseRuleInfo> info = proLedgerAutoGenerationMapper.getFinBaseRuleInfo(paramMap);
        			List<FinFloatCalcRuleDetail> finList = queryFloatFeeDetailByFinprodId(rdto.getFinprodId(),"",batchDateNow);
        			int loop = 0;
        			for(FinFloatCalcRuleDetail fdto:finList) {
        				if(loop > 0) {
        					floatFeeProportion += "/";
        				}else if(loop == 0) {
        					loop++;
        				}
        				floatFeeProportion += "层级" + fdto.getLev() + " " + "比例" + fdto.getChargeRatio();
        			}
        			for (FinBaseRuleInfo qto : info) {
        				BeanUtil.copyPropertiesIgnoreNull(bDto, qto);
        			}
        				
        		}
        		rdto.setFloatFeeProportion(floatFeeProportion);
            }
        	if(!DictCodeList.收益类型_收益型.getValue().equals(rdto.getProfitType())) {
        		if(!StringUtil.equals(DictCodeList.处理模式_分档.getValue(), rdto.getDealMode()) && 
        				!StringUtil.equals(DictCodeList.处理模式_周期型.getValue(), rdto.getDealMode())) {
        		//查询最新业绩基准信息
        		Map<String, Object> mapLast = new HashMap<String, Object>();
        		mapLast.put("finBrach", 0);	
        		mapLast.put("queryDate", DateUtil.addDay(batchDate, -1));
        		mapLast.put("finProdId", rdto.getFinprodId());
        		mapLast.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
        		FinBaseRuleInfo finBaseRuleInfo = proLedgerAutoGenerationMapper.getLastFinBaseRuleInfo(mapLast);
        		if(finBaseRuleInfo !=null){
        			if(DictCodeListExt1.披露类型_区间披露.getValue().equals(finBaseRuleInfo.getPublishType())) {
        				//变量.预期收益率/业绩比较基准 = 2.1.1结果.利率下限*100 +"（%）-"+2.1.1结果.利率上限*100+"（%）"	
        				BigDecimal RateLow = finBaseRuleInfo.getRateLower().multiply(BigDecimal.valueOf(100));
        				BigDecimal RateLim = finBaseRuleInfo.getRateLimit().multiply(BigDecimal.valueOf(100));
        				rdto.setRateStr(RateLow+"(%)-"+RateLim+"(%)");
        			}else {
        				if(DictCodeList.利率类型_固定利率.getValue().equals(finBaseRuleInfo.getRateType())) {
        					rdto.setRateStr(finBaseRuleInfo.getRate().multiply(BigDecimal.valueOf(100)).toString());
        				}else{
        					//调用【业绩基准信息共通】获取浮动利率，参数参考产品台账查询	
        					List<FinProductFloatFeeDto> pList = queryLayerFloatFeeInfoByFinprodId2(rdto.getFinprodId());
        					if(!StringUtil.isEmptyList(pList)){
								BeanUtil.copyPropertiesIgnoreNull(bDto,pList.get(0));
								rdto.setRateStr(this.getFloatRate(bDto));
							}
        				}
        			}
        		}
        	}else if(StringUtil.equals(DictCodeList.处理模式_分档.getValue(), rdto.getDealMode())){
        		//查询最新业绩基准信息
        		Map<String, Object> mapLast = new HashMap<String, Object>();
        		mapLast.put("finBrach", 0);	
        		mapLast.put("queryDate", DateUtil.addDay(batchDate, -1));
        		mapLast.put("finProdId", rdto.getFinprodId());
        		mapLast.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
        		List<FinBaseRuleInfoDto> finBaseRuleInfoDtoList = proLedgerAutoGenerationMapper.getLastFinBaseRuleInfoFD(mapLast);
    			List<String> rateStr = new ArrayList<>();
        		for(FinBaseRuleInfoDto finBaseRuleInfoDto:finBaseRuleInfoDtoList){
    				if(DictCodeListExt1.披露类型_区间披露.getValue().equals(finBaseRuleInfoDto.getPublishType())) {
    					//变量.预期收益率/业绩比较基准 += "款型" +DTO.产品款型+":"+DTO.利率下限*100 +"（%）-"+DTO.利率上限限*100 +"（%）;"
    					BigDecimal RateLow = finBaseRuleInfoDto.getRateLower().multiply(BigDecimal.valueOf(100));
    					BigDecimal RateLim = finBaseRuleInfoDto.getRateLimit().multiply(BigDecimal.valueOf(100));
    					rateStr.add("款型"+finBaseRuleInfoDto.getProdStyle()+":"+RateLow+"(%)-"+RateLim+"(%)");
    				}else {
    					if(DictCodeList.利率类型_固定利率.getValue().equals(finBaseRuleInfoDto.getRateType())) {
    						rateStr.add(finBaseRuleInfoDto.getRate().multiply(BigDecimal.valueOf(100)).toString());
    					}else{
    						//变量.预期收益率/业绩比较基准 += "款型" +DTO.产品款型+":"+上述结果.GET(0).浮动利率*100 +"（%）"+";"
    						//调用【业绩基准信息共通】获取浮动利率，参数参考产品台账查询	
    						List<FinProductFloatFeeDto> pList = queryLayerFloatFeeInfoByFinprodId2(rdto.getFinprodId());
    						BeanUtil.copyPropertiesIgnoreNull(bDto,pList.get(0));
    						rateStr.add("款型"+finBaseRuleInfoDto.getProdStyle()+":"+this.getFloatRate(bDto)+"(%)");
    					}
    				}
    			}
        		if(rateStr.size()>0) {
        			rdto.setRateStr(String.join("/", rateStr));
        		}
        	  }else{//周期型
      			//查询最新业绩基准信息
          		Map<String, Object> mapLast = new HashMap<String, Object>();
          		mapLast.put("finBrach", 0);	
          		mapLast.put("queryDate", DateUtil.addDay(batchDate, -1));
          		mapLast.put("finProdId", rdto.getFinprodId());
          		mapLast.put("finBaseRuleType", DictCodeListExt1.业绩基准类型_产品披露.getValue());
          		List<FinBaseRuleInfoDto> finBaseRuleInfoDtoList = proLedgerAutoGenerationMapper.getLastFinBaseRuleInfoFD(mapLast);
          		String rateStr = "";
          		int loop = 0;
          		for(FinBaseRuleInfoDto finBaseRuleInfoDto:finBaseRuleInfoDtoList){
          			if(loop > 0) {
      					rateStr += "/";
      				}else if(loop == 0) {
      					loop++;
      				}
          			if(DictCodeListExt1.披露类型_区间披露.getValue().equals(finBaseRuleInfoDto.getPublishType())) {
          				//变量.预期收益率/业绩比较基准 += "款型" +DTO.产品款型+":"+DTO.利率下限*100 +"（%）-"+DTO.利率上限限*100 +"（%）;"
          				BigDecimal RateLow = finBaseRuleInfoDto.getRateLower().multiply(BigDecimal.valueOf(100));
          				BigDecimal RateLim = finBaseRuleInfoDto.getRateLimit().multiply(BigDecimal.valueOf(100));
          				rateStr = rateStr+"期限"+finBaseRuleInfoDto.getTermMax()+":"+RateLow+"(%)-"+RateLim+"(%)";
          			}else {
          				if(DictCodeList.利率类型_固定利率.getValue().equals(finBaseRuleInfoDto.getRateType())) {
          					rateStr = rateStr+"期限"+finBaseRuleInfoDto.getTermMax()+":"+finBaseRuleInfoDto.getRate().multiply(BigDecimal.valueOf(100)).toString();
          				}else{
          					//变量.预期收益率/业绩比较基准 += "款型" +DTO.产品款型+":"+上述结果.GET(0).浮动利率*100 +"（%）"+";"
          					//调用【业绩基准信息共通】获取浮动利率，参数参考产品台账查询	
          					BeanUtil.copyPropertiesIgnoreNull(bDto,finBaseRuleInfoDto);
          					bDto.setSpreadRate(bDto.getSpreadRate().multiply(BigDecimal.valueOf(100)));
          				    rateStr = rateStr+"期限"+finBaseRuleInfoDto.getTermMax()+":"+this.getFloatRate(bDto)+"(%)";
          				}
          			}
          		  }
          		rdto.setRateStr(rateStr);
        	  }
        	}
    		else if(DictCodeList.收益类型_收益型.getValue().equals(rdto.getProfitType())){
    			//取产品头寸数据加权计算
    			Map<String, Object> mapp = new HashMap<String, Object>();
    			mapp.put("finprodId", rdto.getFinprodId());
    			mapp.put("queryDate", DateUtil.addDay(batchDate, -1));
    			mapp.put("dealMode", rdto.getDealMode());
    			mapp.put("dealModeFD", DictCodeList.处理模式_分档.getValue());
    			mapp.put("dealModeZQX", DictCodeList.处理模式_分档周期型.getValue());
    			BigDecimal rate = proLedgerAutoGenerationMapper.getRateFroBokPosiProd(mapp);
    			if(rate!=null && BigDecimal.ZERO.compareTo(rate)!=0){
					rdto.setRateStr(ArithmeticUtil.round(rate,2,null).toString());
    			}else{
    				rdto.setRateStr(null);
    			}
    		}
    		
    		rlist.add(rdto);        		        		
        }
		
//		for (ProLedgerAutoGenerationDto proLedgerAutoGenerationDto : rlist) {}
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	try{
	        int count = 0;
			for(ProLedgerAutoGenerationDto proLedgerAutoGenerationDto : rlist){
				proLedgerAutoGenerationDto.setCdate(DateUtil.addDay(batchDate, -1));
				proLedgerAutoGenerationMapper.insertTodayProLedgerData(proLedgerAutoGenerationDto);
				count++;
	    		if(count % 1000==0){
	        	   session.commit();
	        	   session.clearCache();
	    		}
			}
			session.commit();
	        // 清理缓存，防止溢出
	        session.clearCache();
	    } finally {
			session.close();
		}
    }
    
    public List<FinBaseRuleInfo> getFinBaseRuleInfo(Map<String, Object> map){
        return proLedgerAutoGenerationMapper.getFinBaseRuleInfo(map);
    }
    //业绩基准浮动管理费计算规则
    public String getFloatRate(BussBenchMarkLayeredInfoDto dto) {
      	 List<BussBenchMarkLayeredInfoDto> result = new ArrayList<>();
      	 result.add(dto);
      	 result = getBenchMarkLayeredInfoList(result);
           String floatRate = "";
           if(result != null && result.size()>0){
           	BussBenchMarkLayeredInfoDto bussBenchMarkLayeredInfoDto = result.get(0);
               floatRate = bussBenchMarkLayeredInfoDto.getFloatRate() == null? "": bussBenchMarkLayeredInfoDto.getFloatRate().toString();
           }
           return floatRate;
      }
    
    /**
     * 页面参数设置
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<BussBenchMarkLayeredInfoDto> getBenchMarkLayeredInfoList(List<BussBenchMarkLayeredInfoDto> dtolist) {
    	if(dtolist != null && dtolist.size()>0){
            for(BussBenchMarkLayeredInfoDto dto:dtolist){
                BigDecimal realIntRate = BigDecimal.valueOf(0);
                BigDecimal intRate = BigDecimal.valueOf(0);
                if(dto.getRate() == null){
                    
                	String lowLimitRate = "";
                	if(StringUtil.isNotEmpty(dto.getRateLower()) || StringUtil.isNotEmpty(dto.getRateLimit())) {
                		lowLimitRate = dto.getRateLower() + "%~" + dto.getRateLimit() + "%";
                		dto.setLowLimitRate(lowLimitRate);
                	}
                	MstIndexInfo mstIndexInfo = mstIndexInfoMapper.selectByPrimaryKey(dto.getIndexId());
                    Map<String, Object> param = new HashMap<String, Object>();
                    param.put("indexId", dto.getIndexId());
                    if (StringUtil.isNotEmpty(dto.getVdate())) {
                    	param.put("effDate", DateUtil.format(dto.getVdate()));
                    }
                    else {
                    	param.put("effDate", DateUtil.format(dto.getEffDate()));
                    }
                    if (mstIndexInfo != null){
                        //查出出该基准代码所对应的所有行情信息
                        List<BenchmarkRate> list = proLedgerAutoGenerationMapper.getBenchmarkRate(param);
                        //循环利率*权重
                        if(list != null && list.size()>0){
                            for(BenchmarkRate benchmarkRate :list){
                                realIntRate = realIntRate.add(intRate.add(benchmarkRate.getIntRate().multiply(benchmarkRate.getWeight())));
                            }
                        }
                    }
                    else{
                        if(proLedgerAutoGenerationMapper.getIntRate(param) != null){
                            intRate = proLedgerAutoGenerationMapper.getIntRate(param);
                            realIntRate = intRate;
                        }
                        
                    }

                    if(dto.getCoefficient() == null){
                        if(dto.getSpreadRate() != null){
                            dto.setFloatRate(realIntRate.add(dto.getSpreadRate()));
                        }else if(BigDecimal.ZERO.compareTo(realIntRate)!=0){
                            dto.setFloatRate(realIntRate);
                        }
                        
                    }
                    else if(dto.getCoefficient() != null && dto.getSpreadRate() != null){
                        if(realIntRate != null){
                            dto.setFloatRate(realIntRate.multiply(dto.getCoefficient()).add(dto.getSpreadRate()));
                        }
                    }
                }
            } 
        }
        return dtolist;
    }
    
    public List<FinProductFloatFeeDto> queryLayerFloatFeeInfoByFinprodId2(String finprodId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_浮动管理费.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("baseRuleType", DictCodeListExt1.业绩基准类型_浮动管理费.getValue());
        List<FinProductFloatFeeDto> finProductFloatFeeDtos = proLedgerAutoGenerationMapper.queryLayerFloatFeeInfoByFinprodId(map);
        finProductFloatFeeDtos.forEach(finProductFloatFeeDto -> {
            map.put("finprodId", finProductFloatFeeDto.getFinprodid());
            finProductFloatFeeDto.setFinFloatCalcRuleDetailDtoList(proLedgerAutoGenerationMapper.queryFloatFeeDetailByFinprodId2(map));
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
        List<FinProductFloatFeeDto> finProductFloatFeeDtos = proLedgerAutoGenerationMapper.queryLayerFloatFeeInfoByFinprodId(map);
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
            finProductFloatFeeDto.setFinFloatMgtExpQuotationDtoList(proLedgerAutoGenerationMapper.getRuleDetail(param));
        } );
        return list;
    }
    
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
        return proLedgerAutoGenerationMapper.queryFloatFeeDetailByFinprodId(map);
    }
}
