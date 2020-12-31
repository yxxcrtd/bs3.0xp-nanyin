/*
 * 文件名：TrdPlanRateInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trdplanrateinvesttrade.service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdplanrateinvesttrade.dto.TrdPlanRateInvestTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 计划类资产投资交易查询
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanRateInvestTrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdPlanRateInvestTrade001Service extends CommonExportService<TrdPlanRateInvestTradeDto> 
            implements BaseProcessService<TrdPlanRateInvestTradeDto>{
    public Page<TrdPlanRateInvestTradeDto> findPage(Map<String,Object> map){
        map.put("userId", LoginUserUtil.getUserName());
        String[] portfolioId = null;
        if(map.get("portfolioId") != null && !(map.get("portfolioId").toString().isEmpty())){
            String portfolioIdTemp = (String)map.get("portfolioId");
            portfolioId = portfolioIdTemp.split(",");
            map.replace("portfolioId", Arrays.asList(portfolioId));
        }else{
            map.replace("portfolioId", portfolioId);
        }
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
            + "/TrdPlanRateInvestTrade/findPage",map,TrdPlanRateInvestTradeDto.class);
    }

    /**
     * 导出 
     */
     @Override
     protected void buildExportParam(ExportParam exportParam) {
    	 String isShowType = (String) exportParam.getSelectParam().get("isShowType");
         if(!StringUtil.isNotEmpty(isShowType) || StringUtil.equals(isShowType, "01")) {
         	exportParam.config("/com/joyintech/tams/invest/trade/trdplanrateinvesttrade/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
         }else {
         	exportParam.config("/com/joyintech/tams/invest/trade/trdplanrateinvesttrade/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
         }
    	 
         
     }
    
     /**
      * {@inheritDoc}
      */
     @Override
     public Page<TrdPlanRateInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
         return findPage(selectParam);
     }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdPlanRateInvestTradeDto entity, OperationType type) {
        // TODO Auto-generated method stub
        
    }

    /**
     * 获取委托代码
     * @param map 请求参数
     * @return 联想文本框实体类
     * @since 1.0
     */
    public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
        return restTemplate.postForList(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
                SuggestDto.class);
    }
}
