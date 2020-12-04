/*
 * 文件名：TrdSpotexInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangguoxian
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.spotex.service;

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
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.spotex.dto.TrdSpotexInvestTradeDto;

/**
 * 外汇即期交易查询Service
 *
 * @author wangguoxian
 * @version 1.0 2019年7月4日
 * @see TrdSpotexInvestTrade001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TrdSpotexInvestTrade001Service extends CommonExportService<TrdSpotexInvestTradeDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdSpotexInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        // TODO Auto-generated method stub
        return findPage(selectParam);
    }

    /**
     * 
     * 分页查询
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdSpotexInvestTradeDto> findPage(Map<String, Object> map) {
        // 组合代码多选
        String[] portfolioId = null;
        if (map.get("portfolioId") != null && !(map.get("portfolioId").toString().isEmpty())) {
            String portfolioIdTemp = (String)map.get("portfolioId");
            portfolioId = portfolioIdTemp.split(",");
            map.replace("portfolioId", Arrays.asList(portfolioId));
        }
        else {
            map.replace("portfolioId", portfolioId);
        }
        // 货币对多选
        String[] curPair = null;
        if (map.get("curPair") != null && !(map.get("curPair").toString().isEmpty())) {
            String curPairTemp = (String)map.get("curPair");
            curPair = curPairTemp.split(",");
            map.replace("curPair", Arrays.asList(curPair));
        }
        else {
            map.replace("curPair", curPair);
        }

        map.put("userId", LoginUserUtil.getUserName());
        map.put("tradeFlowStatus", DictCodeListExt1.交易流程状态_流程中.getValue());
        return restTemplate.postForPage(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdSpotexInvestTrade/findPage", map,
            TrdSpotexInvestTradeDto.class);
    }


    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/trade/spotex/conf/export_conf.xml")
        .dataSource(DataSourceType.MASERT);
    }
    
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                                          + "/TrdLendInvestTrade/isInFlowDataExisted/" + tradeId,
            null, Boolean.class);
    }
    
    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isRevokeDataExisted(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                                          + "/TrdSpotexInvestTrade/isAlreadyRevoked/" + tradeId,
            null, Boolean.class);
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
