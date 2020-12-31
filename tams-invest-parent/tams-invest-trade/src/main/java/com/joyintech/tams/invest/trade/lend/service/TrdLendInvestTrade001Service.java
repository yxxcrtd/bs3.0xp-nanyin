/*
 * 文件名：TrdLendInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.lend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.lend.dto.TrdLendInvestTradeDto;

@Service
public class TrdLendInvestTrade001Service extends CommonExportService<TrdLendInvestTradeDto> {

    /**
     * 检索拆借投资交易信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdLendInvestTradeDto> findPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }

        pageParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdLendInvestTrade/findPage",
                pageParam, TrdLendInvestTradeDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/lend/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdLendInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
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

    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 start  
    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean selectDurationDataCount(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
            + "/TrdLendInvestTrade/selectDurationDataCount/" + tradeId,
            null, Boolean.class);
    }
    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 end 

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
                                          + "/TrdLendInvestTrade/isAlreadyRevoked/" + tradeId,
            null, Boolean.class);
    }

    /**
     * 根据交易编号查询该交易是否已做流出资金核对
     *
     * @param tradeId 交易编号
     * @return 该交易是否已做流出资金核对
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyLqdPipedTrade(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                                          + "/TrdLendInvestTrade/isAlreadyLqdPipedTrade/" + tradeId,
            null, Boolean.class);
    }

    /**
     * 获取交易代码
     * @param map 请求参数
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getEntrustIdByType(Map<String,Object> map){
        return restTemplate.postForList(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getEntrustId", map,
                SuggestDto.class);
    }
}
