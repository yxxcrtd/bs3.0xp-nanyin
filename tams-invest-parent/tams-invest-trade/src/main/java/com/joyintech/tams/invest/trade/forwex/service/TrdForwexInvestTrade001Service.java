/*
 * 文件名：TrdForwexInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.trade.forwex.service;

import java.util.ArrayList;
import java.util.Map;

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
import com.joyintech.tams.invest.trade.forwex.dto.TrdForwexInvestTradeDto;

/**
 * 外汇远期投资交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月6日
 * @see TrdForwexInvestTrade001Service
 * @since 1.0
 */
@Service
public class TrdForwexInvestTrade001Service extends CommonExportService<TrdForwexInvestTradeDto>  {

	/**
     * 查询列表
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdForwexInvestTradeDto> findPage(Map<String, Object> pageParam) {
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

        // 交易方向
        Object psObj = pageParam.get("ps");
        if (psObj != null) {
            pageParam.put("psList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)psObj));
        }
        else {
            pageParam.put("psList", new ArrayList<>());
        }
        pageParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdForwexInvestTrade/findPage",
            pageParam,TrdForwexInvestTradeDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/forwex/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdForwexInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
