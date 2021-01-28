package com.joyintech.tams.invest.trade.buyoutrepo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SuggestDto;
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

        //多选下拉框处理（抵押品代码）
        Object finprodIdObj = map.get("finprodId");
        if (finprodIdObj != null){
            map.put("finprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA,(String) finprodIdObj));
        }else {
            map.put("finprodIdList",new ArrayList<>());
        }

        //多选下拉框处理（合作资产）
        Object chlFinprodIdObj = map.get("chlFinprodId");
        if (chlFinprodIdObj != null){
            map.put("chlFinprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA,(String) chlFinprodIdObj));
        }else {
            map.put("chlFinprodIdList",new ArrayList<>());
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
