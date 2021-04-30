/*
 * 文件名：BussEtrInvestPersonInfoImplService.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：xiexinting
 * 修改时间：2018年10月18日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.trade.impl;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.trade.BussEtrInvestPersonInfoService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author xiexinting
 * @version 1.0 2018年10月18日
 * @see BussEtrInvestPersonInfoImplService
 * @since 1.0
 */
@Service
public class BussEtrInvestPersonInfoImplService implements BussEtrInvestPersonInfoService {

    @Autowired
    private JoyinRestTemplate restTemplate;

    /**
     * 本方交易员
     */
    @Override
    public Page<SelectOptionDto> getTrader(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getTrader",
            map, SelectOptionDto.class);
    }

    /**
     * 交易对手
     */
    @Override
    public Page<SelectOptionDto> getCounterId(Map<String, Object> param) {
        if ((StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_买入.getValue())
             || StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_卖出.getValue()))
            && (StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue())
                || StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_净值型.getValue())
                || StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_货币型.getValue()))) {
            param.put("counterType", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
            // 基金买入卖出 有交易场所 带出对应交易场所交易对手
            if ((StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_买入.getValue())
                 || StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_卖出.getValue()))
                && (StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue()))) {
                List<String> counterIdList = new ArrayList<>();
                if (StringUtil.equals((String)param.get("tradeMarket"), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
                    if (MultilevelDataAuthorityUtil.isEnabled()){
                        Map<String, Object> param1 = new HashMap<>();
                        param1.put("counterId",BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
                        List<String> counterIds = restTemplate.postForObject(
                                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/findSameNameCounterId", param1,
                                List.class);
                        counterIdList.addAll(counterIds);

                    }else {
                        counterIdList.add(BussinessConstant.COUNTER_PARTY_ID_SHANGHAI);
                    }

                }
                else if (StringUtil.equals((String)param.get("tradeMarket"), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {

                    if (MultilevelDataAuthorityUtil.isEnabled()){

                        Map<String, Object> param1 = new HashMap<>();
                        param1.put("counterId",BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
                        List<String> counterIds = restTemplate.postForObject(
                                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/findSameNameCounterId", param1,
                                List.class);
                        counterIdList.addAll(counterIds);

                    }else {
                        counterIdList.add(BussinessConstant.COUNTER_PARTY_ID_SHENZHEN);
                    }
                }
                param.put("counterIdList", counterIdList);
            }
        }
        // add by etliu 20210421 权益类资产相关代码合并 start
        if (StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_卖出.getValue())
                && StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt3.金融产品类型_投管用_权益类资产.getValue())) {
            param.put("counterType", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
        }
        // add by etliu 20210421 权益类资产相关代码合并 end

        if ((StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_申购.getValue())
             || StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_赎回.getValue())
             || StringUtil.equals((String)param.get("ps"), DictCodeListExt2.交易方向_转换申请.getValue())
             || StringUtil.equals((String)param.get("ps"), DictCodeListExt2.交易方向_认购.getValue()))
            && (StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue())
                || StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_净值型.getValue())
                || StringUtil.equals((String)param.get("profitType"), DictCodeList.收益类型_货币型.getValue()))) {
            // 基金申购赎回默认带出管理员
            if ((StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_申购.getValue())
                 || StringUtil.equals((String)param.get("ps"), DictCodeList.交易方向_赎回.getValue()))
                && (StringUtil.equals((String)param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue()))) {
                param.put("isAssteManager", DictCodeListExt1.是否_是.getValue());
            }

            return restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterIdDepat", param,
                SelectOptionDto.class);
            // 债券一级委托新建交易对手类型限制市场机构
        }
        else if ((StringUtil.equals((String)param.get("counterType"), DictCodeListExt1.交易对手类型_市场机构.getValue()))) {
            param.put("counterType", DictCodeListExt1.交易对手类型_市场机构.getValue());
            return restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterId", param,
                SelectOptionDto.class);
        }
        else {
            // return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdInvestPersonInfo/getCounterId", param, SelectOptionDto.class);
            return restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterId", param,
                SelectOptionDto.class);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getUser(String userName) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getUser?userName=" + userName,
            Map.class);
    }
    
    /**
     * 
     */
	@Override
	public Page<SelectOptionDto> getCfetsTrader(Map<String, Object> map) {
		return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCfetsTrader",
	            map, SelectOptionDto.class);
	}

	/**
     * 交易对手
     */
    @Override
    public Page<SelectOptionDto> getCounterIdCfets(Map<String, Object> param) {
    	return restTemplate.postForPage(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterIdCfets", param,
                SelectOptionDto.class);
    }

}
