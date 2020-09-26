package com.joyintech.tams.invest.trade.project.service;

import java.util.Map;

import com.joyintech.framework.security.SecurityUtil;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.project.dto.TrdProjectInvestTrade001Dto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdbondduration.service
 * @Description: 债券存续期管理-查询
 * @date Date : 2018年09月21日 14:32
 */
@Service
public class TrdProjectInvestTrade001Service extends CommonExportService<TrdProjectInvestTrade001Dto> {

    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectInvestTrade/getFinprodMarketId", params,
            SelectOptionDto.class);
    }

    public Page<TrdProjectInvestTrade001Dto> findPage(Map<String, Object> params) {
        if (params.get("projectType") != null) {
            params.put("projectType", StringUtil.splitToList(",", (String)params.get("projectType")));
        }
        else {
            params.put("projectType", null);
        }
        // 项目类资产五级分类改造 add by renping 2019-08-01 start
        if (params.get("type9") != null) {
            params.put("type9", StringUtil.splitToList(",", (String)params.get("type9")));
        }
        else {
            params.put("type9", null);
        }
        if (params.get("type10") != null) {
            params.put("type10", StringUtil.splitToList(",", (String)params.get("type10")));
        }
        else {
            params.put("type10", null);
        }
        if (params.get("type11") != null) {
            params.put("type11", StringUtil.splitToList(",", (String)params.get("type11")));
        }
        else {
            params.put("type11", null);
        }
        // 项目类资产五级分类改造 add by renping 2019-08-01 end
        /*BUG_1157154 投资端-项目类资产交易查询界面，组合代码不支持多选*/
        if (params.get("portfolioId") != null) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        else {
            params.put("portfolioId", null);
        }
        if (params.get("ps") != null) {
            params.put("ps", StringUtil.splitToList(",", (String)params.get("ps")));
        }
        else {
            params.put("ps", null);
        }
        params.put("userName", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectInvestTrade/findPage", params,
            TrdProjectInvestTrade001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
    	String isShowType = (String) exportParam.getSelectParam().get("isShowType");
        if(!StringUtil.isNotEmpty(isShowType) || StringUtil.equals(isShowType, "01")) {
        	exportParam.config("/com/joyintech/tams/invest/trade/project/config/export_conf.xml").dataSource(DataSourceType.MASERT);
        }else {
        	exportParam.config("/com/joyintech/tams/invest/trade/project/config/export_conf002.xml").dataSource(DataSourceType.MASERT);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdProjectInvestTrade001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    public Page<SelectOptionDto> findEntrustId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("ENTRUST_STATUS03", DictCodeList.委托状态_已接收);
        params.put("ENTRUST_STATUS04", DictCodeList.委托状态_已完成);
        params.put("ENTRUST_STATUS06", DictCodeList.委托状态_已关闭);
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdProjectInvestTrade/findEntrustId", params,
                SelectOptionDto.class);
    }
}
