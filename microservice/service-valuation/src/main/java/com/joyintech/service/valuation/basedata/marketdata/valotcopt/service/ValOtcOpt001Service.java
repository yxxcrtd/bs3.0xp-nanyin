/*
 * 文件名：ValOtcOpt001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.marketdata.valotcopt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valotcopt.dao.ValOtcOpt001Mapper;
import com.joyintech.service.valuation.basedata.marketdata.valotcopt.dto.ValOtcOpt001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 
 * 场外期权交易Service
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see ValOtcOpt001Service
 * @since 1.0
 */
@Service(value="serviceValOtcOpt001Service")
public class ValOtcOpt001Service extends CommonExportService<ValOtcOpt001Dto>{
	
    /**
     * ValOtcOpt001Mapper
     */
    @Autowired
    private ValOtcOpt001Mapper mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValOtcOpt001Dto> findPage(int page,int length,Map<String, Object> params) throws Exception {
    	params.put("TRADE_FLOW_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
    	params.put("TRADE_FLOW_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
    	params.put("TRADE_FLOW_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
    	params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
    	params.put("YES_OR_NO_Y", DictCodeListExt1.是否_是.getValue());
    	params.put("FINPROD_TYPE_TG_F35", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
        return super.queryForPage(page,length,()->mapper.findPage(params));
    }
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public List<ValOtcOpt001Dto> findAll(Map<String, Object> params) throws Exception {
    	params.put("TRADE_FLOW_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
    	params.put("TRADE_FLOW_STATUS_02", DictCodeListExt1.交易流程状态_有效.getValue());
    	params.put("TRADE_FLOW_STATUS_03", DictCodeListExt1.交易流程状态_撤销.getValue());
    	params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
    	params.put("YES_OR_NO_Y", DictCodeListExt1.是否_是.getValue());
    	params.put("FINPROD_TYPE_TG_F35", DictCodeListExt1.金融产品类型_投管用_期权.getValue());
        return mapper.findPage(params);
    }
	
    /**
     * 下拉框分页获取账套
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(int page,int length,Map<String,Object> map) {
    	map.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
        return super.queryForPageForSelectReturn(page,length,()->mapper.getBooksetId(map));
    }
    
    /**
     * 下拉框分页获取交易编号
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getTotleTradeId(Map<String,Object> map) {
    	map.put("TRADE_FLOW_STATUS_01", DictCodeListExt1.交易流程状态_流程中.getValue());
        return mapper.getTotleTradeId(map);
    }
    
    /**
     * 下拉框分页获取合约标的
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBenchmarkId(Map<String,Object> map) {
        return mapper.getBenchmarkId(map);
    }
	
}
