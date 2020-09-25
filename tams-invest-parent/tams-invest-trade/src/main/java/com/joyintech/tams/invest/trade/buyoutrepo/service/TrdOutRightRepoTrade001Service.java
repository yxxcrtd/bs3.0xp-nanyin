package com.joyintech.tams.invest.trade.buyoutrepo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.buyoutrepo.dto.TrdOutRightRepoTrade001Dto;

@Service
public class TrdOutRightRepoTrade001Service extends CommonExportService<TrdOutRightRepoTrade001Dto> {

	
	
	/**
     * 查询排期所有数据
     * 
     * @param map 参数map
     * @return 排期所有数据
     * @see
     * @since 1.0
     */
    public Page<TrdOutRightRepoTrade001Dto> selectForPage(Map<String, Object> map) {
        if (StringUtil.isNotEmpty(map.get("portfolioId"))) {
            map.put("portfolioId", StringUtil.splitToList(null, map.get("portfolioId").toString()));
        }
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdOutRightRepoTrade/queryForPage", map,
            TrdOutRightRepoTrade001Dto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/trade/buyoutrepo/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdOutRightRepoTrade001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
}
