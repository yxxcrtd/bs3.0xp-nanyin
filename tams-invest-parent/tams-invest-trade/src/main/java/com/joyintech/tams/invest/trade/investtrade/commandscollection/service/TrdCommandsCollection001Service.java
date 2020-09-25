/*
 * 文件名：TrdCommandsCollection001Service.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection001Dto;

/**
 *指令汇总001Service
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection001Service
 * @since 1.0
 */
@Service
public class TrdCommandsCollection001Service extends CommonExportService<TrdCommandsCollection001Dto>  {

    /**
     * 查询汇总指令
     * @param param 包括资产大类和输入的查询条件
     * @param page page
     * @param length length
     * @return Page<TrdCommandsCollection001Dto> 汇总指令列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<TrdCommandsCollection001Dto> selectForPage(Map<String, Object> param) {
        param.put("user", LoginUserUtil.getUserName());
        param.put("sysdate", DateUtil.format(DateUtil.platDateFromCache()));
        param.put("entrustStatus", DictCodeList.委托状态_已接收.getValue());
        param.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        param.put("PINAN_ASSET_TYPE_F02", DictCodeList.平安资产类型_基金.getValue());
        param.put("PINAN_ASSET_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        param.put("PINAN_ASSET_TYPE_F04", DictCodeList.平安资产类型_计划类资产_净值型.getValue());
        param.put("PINAN_ASSET_TYPE_F05", DictCodeList.平安资产类型_存款.getValue());
        param.put("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        param.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        param.put("PINAN_ASSET_TYPE_F08", DictCodeList.平安资产类型_拆借.getValue());
        param.put("PINAN_ASSET_TYPE_F09", DictCodeList.平安资产类型_计划类资产_货币型.getValue());
        param.put("PINAN_ASSET_TYPE_F10", DictCodeList.平安资产类型_买断式回购.getValue());
        param.put("PINAN_ASSET_TYPE_F11", DictCodeListExt2.平安资产类型_股票.getValue());
        param.put("PINAN_ASSET_TYPE_F17", DictCodeListExt2.平安资产类型_一级市场债.getValue());
        param.put("PS_T01", DictCodeList.交易方向_买入.getValue());
        param.put("PS_T02", DictCodeList.交易方向_卖出.getValue());
        param.put("PS_T03", DictCodeList.交易方向_行权_回售.getValue());
        param.put("PS_T04", DictCodeList.交易方向_申购.getValue());
        param.put("PS_T49", DictCodeListExt1.交易方向_债转股.getValue());
        param.put("TYPE6_B14", DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("TYPE6_B15", DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        List<String> type6List = StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("type6"));
        List<String> type7List = StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("type7"));
        param.put("type6List", type6List);
        param.put("type7List", type7List);
        return restTemplate.postForPage(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandsCollection/findPage", param,
                TrdCommandsCollection001Dto.class);
    }
    
    /**
     * 导出
     * @param exportParam
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        String assetType = exportParam.getSelectParam().get("assetType").toString();
        exportParam.config(
                "/com/joyintech/tams/invest/trade/investtrade/commandscollection/conf/export_conf_"+ assetType +".xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * 获取导出数据
     * @param selectParam 查询条件
     * @return Page<TrdCommandsCollection001Dto> 
     * @see
     * @since 1.0
     */
    @Override
    public Page<TrdCommandsCollection001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 资产代码联想查询各类资产对应的资产代码
     * @param param 包括资产大类和输入的资产名称
     * @return List<SuggestDto> 联想框下拉选项
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketIds(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandsCollection/getFinprodMarketIds", param,
                SuggestDto.class);
    }
}
