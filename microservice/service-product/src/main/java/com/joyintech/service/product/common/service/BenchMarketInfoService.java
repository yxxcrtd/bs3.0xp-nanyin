/*
 * 文件名：BenchMarketInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.FinBaseRuleInfoMapper;
import com.joyintech.tams.common.base.dao.MstIndexInfoMapper;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.tams.common.base.entity.MstIndexInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.BenchMarketInfoMapper;
import com.joyintech.service.product.common.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.common.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.service.product.messageadjust.bmquotation.dao.FinProductBmQuotationMapper;
import com.joyintech.service.product.messageadjust.bmquotation.dto.BenchmarkRate;
import com.joyintech.service.product.messageadjust.bmquotation.dto.FinProductBmQuotationDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 业绩基准信息共通
 * @author 陈卓
 * @version 1.0 2018年9月17日
 * @see BenchMarketInfoService
 * @since 1.0
 */
@Service("backBenchMarketInfoService")
public class BenchMarketInfoService {
	
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private BenchMarketInfoMapper benchMarketInfoMapper;
	@Autowired(required=false)
	private FinBaseRuleInfoMapper finBaseRuleInfoMapper;
    @Autowired
    private FinProductBmQuotationMapper finProductBmQuotationMapper;
    @Autowired(required = false)
    private MstIndexInfoMapper mstIndexInfoMapper;
    @Autowired
    private IPlatformDate iPlatformDate;

	
	/**
	 * 基准信息取得
	 * @return 基准信息
	 */
	public List<SelectOptionDto> getFinBaseRuleInfo() {
		return benchMarketInfoMapper.getFinBaseRuleInfo();
	}

    /**
     * 页面参数设置
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public BussBenchMarkInfoDto getByFinprodIdAndEffDate(Map<String, Object> param) {
    	param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
    	//是否来自浮动管理费控件
    	param.put("dealMode10", DictCodeList.处理模式_周期型.getValue());
    	param.put("termType02", DictCodeListExt2.期限类型_多固定期限.getValue());
        param.put("sysDate", DateUtil.getSysDate()); 
        if (StringUtil.isNotEmpty(param.get("effDate")) 
            && DateUtil.compareDate(param.get("effDate").toString(),DateUtil.getSysDate())!=-1) {
            param.put("sysDate", param.get("effDate").toString());
        } 
    	if(param.get("dealMode")!=null) {
    		return benchMarketInfoMapper.selectByPrimaryKey2(param);
    	}
        return benchMarketInfoMapper.selectByPrimaryKey(param);
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
                    	param.put("effDate", DateUtil.format(iPlatformDate.getSysDate()));
                    }
                    if (mstIndexInfo != null){
                        //查出出该基准代码所对应的所有行情信息
                        List<BenchmarkRate> list = finProductBmQuotationMapper.getBenchmarkRate(param);
                        //循环利率*权重
                        if(list != null && list.size()>0){
                            for(BenchmarkRate benchmarkRate :list){
                                realIntRate = realIntRate.add(intRate.add(benchmarkRate.getIntRate().multiply(benchmarkRate.getWeight())));
                            }
                        }
                    }
                    else{
                        if(finProductBmQuotationMapper.getIntRate(param) != null){
                            intRate = finProductBmQuotationMapper.getIntRate(param);
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

    /**
     * 页面参数设置
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<BussBenchMarkInfoDto> getByFinprodIdAndEffDateList(Map<String, Object> param) {
        param.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        return benchMarketInfoMapper.selectByFinprodidList(param);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
    public int deleteByPrimaryKey(FinBaseRuleInfoKey key) {
        return finBaseRuleInfoMapper.deleteByPrimaryKey(key);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseRuleId
     * @return
     * @see
     * @since 1.0
     */
    public int delete(String baseRuleId) {
        return benchMarketInfoMapper.delete(baseRuleId);
    }
}
