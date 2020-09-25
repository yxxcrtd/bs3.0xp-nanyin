/*
 * 文件名：BussBenchMarketInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkInfoDto;
import com.joyintech.tams.component.business.prod.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 产品开放计划实现类
 * 
 * @author 陈卓
 * @version 1.0 2018年9月3日
 * @see BussBenchMarketInfoImplService
 * @since 1.0
 */
@Service
public class BussBenchMarketInfoImplService extends BaseService<BussBenchMarkInfoDto> implements BussBenchMarketInfoService {

	@Override
	public List<SelectOptionDto> getFinBaseRuleInfo() {
	    return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/getFinBaseRuleInfo",
            SelectOptionDto.class);
	}

    
    @Override
    public BussBenchMarkInfoDto getByFinprodIdAndEffDate(Map<String,Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/getByFinprodIdAndEffDate",
            param,BussBenchMarkInfoDto.class);
    }

    @Override
    public List<BussBenchMarkLayeredInfoDto> getBenchMarkLayeredInfoList(List<BussBenchMarkLayeredInfoDto> result) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/getBenchMarkLayeredInfoList",
            result,BussBenchMarkLayeredInfoDto.class);
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String baseRuleId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/delete?baseRuleId="
            +baseRuleId,Integer.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteByPrimaryKey(FinBaseRuleInfoKey key) {
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/deleteByPrimaryKey",
            key,Integer.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<BussBenchMarkLayeredInfoDto> getByFinprodIdAndEffDateList(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/BenchMarketInfo/getByFinprodIdAndEffDateList",
            param,BussBenchMarkLayeredInfoDto.class);
    }
}
