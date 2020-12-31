/*
 * 文件名：TrdFxswapInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.trdfxswapinvesttrade.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdfxswapinvesttrade.dto.TrdFxswapInvestTrade001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 外汇掉期投资交易service
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdFxswapInvestTrade001Service extends CommonExportService<TrdFxswapInvestTrade001Dto> implements BaseProcessService<TrdFxswapInvestTrade001Dto> {
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/trdfxswapinvesttrade/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdFxswapInvestTrade001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 分页数据查询
     * 
     * @param map 参数
     * @return 分页数据
     * @see
     * @since 1.0
     */
    public Page<TrdFxswapInvestTrade001Dto> findPage(Map<String, Object> map) {
        // 多选下拉框值处理
        // 组合代码
        if (map.get("portfolioId") != null) {
            map.put("portfolioIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)map.get("portfolioId")));
        }
        // 货币对
        if (map.get("curPair") != null) {
            map.put("curPairList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)map.get("curPair")));
        }
        // 期限类型
        if (map.get("excFxsTermType") != null) {
            map.put("excFxsTermTypeList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)map.get("excFxsTermType")));
        }
        map.put("userId", LoginUserUtil.getUserName());
        map.put("tradeFlowStatus", DictCodeListExt1.交易流程状态_流程中.getValue());
        return restTemplate.postForPage(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdFxswapInvestTrade001/findPage", map,
            TrdFxswapInvestTrade001Dto.class);
    }

    /**
     * 获取委托代码
     * 
     * @param param 模糊查询参数
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getEntrustId(String param) {
        return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
                                       + "/TrdFxswapInvestTrade001/getEntrustId?entrustId="
                                       + param,
            SuggestDto.class);
    }

    /**
     * 获取组合代码
     * 
     * @param map 模糊查询参数
     * @return 联想文本框列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String, Object> map) {
        if (map.get("initValue") == null) {
            map.put("userId", LoginUserUtil.getUserName());
        }
        return restTemplate.postForList(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdFxswapInvestTrade001/getPortfolioId", map,
            SelectOptionDto.class);
    }

    /**
     * 获取资金代码
     * 
     * @param param 模糊查询参数
     * @return 联想文本框列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketId(String param) {
        return restTemplate.getForList(ServiceApplication.TRADE_SERVICE_NAME
                                       + "/TrdFxswapInvestTrade001/getFinprodMarketId?finprodMarketId="
                                       + param,
            SuggestDto.class);
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
    	  if(StringUtil.equals(propName,"tradeDate") ||
                  StringUtil.equals(propName,"vdate") || StringUtil.equals(propName,"mdate")){
              if(value instanceof Date){
                  cell.setCellValue(DateUtil.format((Date)value));
              }else if (value instanceof Long){
                  cell.setCellValue(DateUtil.format(new Date((Long) value)));
              }
          }
      }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(TrdFxswapInvestTrade001Dto arg0, OperationType arg1) {
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
