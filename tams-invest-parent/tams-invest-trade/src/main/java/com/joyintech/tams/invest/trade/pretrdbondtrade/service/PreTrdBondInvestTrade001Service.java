/*
 * 文件名：PreTrdBondInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wang wei
 * 修改时间：2019年12月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.pretrdbondtrade.service;

import java.util.Map;

import com.joyintech.framework.util.StringUtil;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.pretrdbondtrade.dto.PreTrdBondInvestTrade001Dto;

@Service
public class PreTrdBondInvestTrade001Service extends CommonExportService<PreTrdBondInvestTrade001Dto> {

    /**
     * 检索债券预录入交易信息
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<PreTrdBondInvestTrade001Dto> findPage(Map<String, Object> pageParam) {
        // 添加登陆用户参数
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        if (StringUtil.equals(pageParam.get("settleSpreed"),"01")){
            pageParam.put("settleSpreed","0");
        }else if (StringUtil.equals(pageParam.get("settleSpreed"),"02")){
            pageParam.put("settleSpreed","1");
        }
        // 微服务访问路径
        String url = ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdBondInvestTrade/findPage";
        return restTemplate.postForPage(url, pageParam, PreTrdBondInvestTrade001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/pretrdbondtrade/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PreTrdBondInvestTrade001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
