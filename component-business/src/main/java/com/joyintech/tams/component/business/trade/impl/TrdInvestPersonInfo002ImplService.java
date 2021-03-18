/*
 * 文件名：TrdInvestPersonInfo002ImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.component.business.trade.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstCounterPartyMapper;
import com.joyintech.tams.component.business.trade.TrdInvestPersonInfo002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

@Service
public class TrdInvestPersonInfo002ImplService implements TrdInvestPersonInfo002Service {
    @Autowired
    private JoyinRestTemplate restTemplate;
    
    @Autowired
    private MstCounterPartyMapper mstCounterPartyMapper;
    
    @Override
    public Page<SelectOptionDto> selectCounterId(Map<String, Object> param) {
        // 场内基金改造 add by renping 20191117 start
        if ((StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_买入.getValue())
             || StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_卖出.getValue()))
            && StringUtil.equals((String)param.get("finprodType2"),
                DictCodeListExt1.金融产品类型_投管用_基金.getValue())
            && StringUtil.isEmpty((String)param.get("chlId"))) {
        	if(MultilevelDataAuthorityUtil.isEnabled()) {
        		if(StringUtil.equals((String)param.get("tradeMarket"),DictCodeListExt1.交易场所_上海证券交易所.getValue())||
        				StringUtil.equals((String)param.get("tradeMarket"),DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
                	Map<String,Object> para = new HashMap<>();
                	para.put("insTree", LoginUserUtil.getTreeInstCode());
                	String key,value;
                	Set<String> counterSet = new HashSet<>();
                	if(StringUtil.equals((String)param.get("tradeMarket"),DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
                		para.put("counterName", "上海证券交易所");
                		key = "COUNTER_PARTY_ID_SHANGHAI";
                		value = BussinessConstant.COUNTER_PARTY_ID_SHANGHAI;
                	}
                	else {
                		para.put("counterName", "深圳证券交易所");
                		key = "COUNTER_PARTY_ID_SHENZHEN";
                		value = BussinessConstant.COUNTER_PARTY_ID_SHENZHEN;
                	}
                	List<String> counterList =mstCounterPartyMapper.getInsCounterIdByName(para); 
                	counterSet.addAll(counterList);
                	boolean initFlag = false;
                	if(StringUtil.isNotEmpty(param.get("initValue"))) {
                		if(counterSet.contains(param.get("initValue").toString())) {
                			value = param.get("initValue").toString();
                			initFlag = true;
                		}
                	}
                	if(!initFlag&&counterList.size()>0) {
                		value = counterList.get(0);
                	}
                	param.put(key, value);
        		}
        	}
        	else {
                if (StringUtil.equals((String)param.get("tradeMarket"),
                        DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
                        param.put("COUNTER_PARTY_ID_SHANGHAI",
                            BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
                    }
                    else if (StringUtil.equals((String)param.get("tradeMarket"),
                        DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
                        param.put("COUNTER_PARTY_ID_SHENZHEN",
                            BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
                    }
        	}
        }
        
        // 场内基金改造 add by renping 20191117 end
        if((StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_买入.getValue())
            ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_卖出.getValue()))
            // 场内基金改造 modify by renping 20191117 start
            && ((StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue())
                && (StringUtil.isEmpty((String)param.get("chlId"))
                    || StringUtil.equals((String)param.get("tradeMarket"),
                        DictCodeListExt1.交易场所_OTC场外.getValue())))
            // 场内基金改造 modify by renping 20191117 end
                ||StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_净值型.getValue())
                ||StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_货币型.getValue()))){
            param.put("counterType", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
        }
            // 场内基金改造 add by renping 20191117 start
        if(((StringUtil.equals((String)param.get("ps"), DictCodeListExt2.交易方向_认购.getValue())
                ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_认购确认.getValue())
                // 场内基金改造 add by renping 20191117 end
                ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_申购.getValue())
                ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_赎回.getValue())
                ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_申购确认.getValue())
                ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_赎回确认.getValue())
                ||StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_赎回到账.getValue()))
                && (StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue())
                    ||StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_净值型.getValue())
                    ||StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_货币型.getValue())))
        		//add by yujiaping 20210318 ZGBSS-366 可转债转股交易新建页面，交易对手不可以选择内部交易对手start
        		||(StringUtil.equals("TrdBondToStock002",(String)param.get("pageId")))
        		//add by yujiaping 20210318 ZGBSS-366 可转债转股交易新建页面，交易对手不可以选择内部交易对手end
        		//反向交易开关 add by liuwenjuan 20200323 start
        		// modify wj 20200430  去掉校验 自动生成反向交易:开的情况  买入 可以选择内部对手
//        		||(StringUtil.equals("Y",CacheUtil.getSysParam("is_auto_generate_negate_deal")) 
//        				&& StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_买入.getValue()))
        		){
        	//反向交易开关 add by liuwenjuan 20200323 end
            return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/TrdInvestPersonInfo/getCounterIdDepat", param,
                    SelectOptionDto.class);
        }else{
            return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/TrdInvestPersonInfo/getCounterId", param, SelectOptionDto.class);
        }
    }
}
