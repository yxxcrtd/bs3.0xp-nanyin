/*
 * 文件名：TrdBondInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.bondlend.service;

import java.util.ArrayList;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.trade.bondlend.dto.TrdBondLendInvestTradeDto;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

@Service
public class TrdBondLendInvestTrade001Service extends CommonExportService<TrdBondLendInvestTradeDto> {

    /**
     * 检索债券借贷信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdBondLendInvestTradeDto> findPage(Map<String, Object> pageParam) {
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

        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondLendInvestTrade/findPage",
            pageParam,TrdBondLendInvestTradeDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/bondlend/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdBondLendInvestTradeDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
