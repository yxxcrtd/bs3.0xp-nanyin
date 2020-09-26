/*
 * 文件名：TrdDepositInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.trddepositinvesttrade.service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trddepositinvesttrade.dto.TrdDepositInvestTrade001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 存款类投资交易service
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositInvestTrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TrdDepositInvestTrade001Service extends CommonExportService<TrdDepositInvestTrade001Dto> implements BaseProcessService<TrdDepositInvestTrade001Dto>{

    @Autowired
   private TrdProductDealMapper trdProductDealMapper;
    /**
     * 导出 
     */
     @Override
     protected void buildExportParam(ExportParam exportParam) {
    	 String isShowType = (String) exportParam.getSelectParam().get("isShowType");
    	 if(!StringUtil.isNotEmpty(isShowType) || StringUtil.equals(isShowType, "01")) {
         	exportParam.config("/com/joyintech/tams/invest/trade/trddepositinvesttrade/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
         }else {
         	exportParam.config("/com/joyintech/tams/invest/trade/trddepositinvesttrade/conf/export_conf002.xml").dataSource(DataSourceType.MASERT);
         }
         
     }
     /**
      * {@inheritDoc}
      */
     @Override
     public Page<TrdDepositInvestTrade001Dto> selectPageData(Map<String, Object> selectParam) {
         return findPage(selectParam);
     }
     public Page<TrdDepositInvestTrade001Dto> findPage(Map<String,Object> map){
         String[] ps = null;
         if(map.get("ps") != null && !map.get("ps").toString().isEmpty()){
             String psTemp = (String)map.get("ps");
             ps = psTemp.split(",");
             map.replace("ps", Arrays.asList(ps));
         }else{
             map.replace("ps", ps);
         }
         String[] portfolioId = null;
         if(map.get("portfolioId") != null && !(map.get("portfolioId").toString().isEmpty())){
             String portfolioIdTemp = (String)map.get("portfolioId");
             portfolioId = portfolioIdTemp.split(",");
             map.replace("portfolioId", Arrays.asList(portfolioId));
         }else{
             map.replace("portfolioId", portfolioId);
         }
         map.put("userId", LoginUserUtil.getUserName());
         map.put("tradeFlowStatus", DictCodeListExt1.交易流程状态_流程中.getValue());
         // type9-11 created by wangguiqi on 2019/7/19 -start
         if (StringUtil.isNotEmpty(map.get("type9"))) {
             map.put("type9", StringUtil.splitToList(null, map.get("type9").toString()));
         }else{
             map.remove("type9");
         }
         if (StringUtil.isNotEmpty(map.get("type10"))) {
             map.put("type10", StringUtil.splitToList(null, map.get("type10").toString()));
         }else{
             map.remove("type10");
         }
         if (StringUtil.isNotEmpty(map.get("type11"))) {
             map.put("type11", StringUtil.splitToList(null, map.get("type11").toString()));
         }else{
             map.remove("type11");
         }
         // type9-11 created by wangguiqi on 2019/7/19 -end
         return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
             + "/TrdDepositInvestTrade001/findPage",map,TrdDepositInvestTrade001Dto.class);
     }
     /**
      * 获取交易代码
      * @param param 模糊查询参数
      * @return 联想文本框实体类
      * @see
      * @since 1.0
      */
     public List<SuggestDto> getEntrustId(String param){
         return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
             + "/TrdDepositInvestTrade001/getEntrustId?entrustId="+param,SuggestDto.class);
     }
     /**
      * 获取组合代码
      * @param param 模糊查询参数
      * @return 联想文本框列表
      * @see
      * @since 1.0
      */
     public Page<SelectOptionDto> getPortfolioId(Map<String,Object> map){
         if(map.get("initValue") == null){
             map.put("userId", LoginUserUtil.getUserName());
         }
         return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
             + "/TrdDepositInvestTrade001/getPortfolioId",map,SelectOptionDto.class);
     }
     /**
      * 获取资金代码
      * @param param 模糊查询参数
      * @return 联想文本框列表
      * @see
      * @since 1.0
      */
     public List<SuggestDto> getFinprodMarketId(String param){
         return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
             + "/TrdDepositInvestTrade001/getFinprodMarketId?finprodMarketId="+param,SuggestDto.class);
     }
     
     /**
      * 设置单元格值
      * <p>
      * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
      * </p>
      * 
      * @param beanName sheet对应的JavaBean
      * @param cell 单元格
      * @param propName 属性名
      * @param value 单元格的值
      */
     @Override
     public void setCellValue(String beanName, Cell cell, String propName, Object value) {
         if("tradeDate".equals(propName)){
             cell.setCellValue(DateUtil.format((Date)value, "yyyy-MM-dd"));
         }
     }
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdDepositInvestTrade001Dto arg0, OperationType arg1) {
        // TODO Auto-generated method stub
        
    }

    public List<TrdProductDeal> selectByTrade(TrdProductDeal deal){
        List<TrdProductDeal> list=trdProductDealMapper.selectByTrade(deal);
        return list;
    }

}
