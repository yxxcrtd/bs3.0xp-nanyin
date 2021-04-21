package com.joyintech.tams.invest.trade.bondmarkettrade.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.bondmarkettrade.dto.TrdBondMarketTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * 债券上市流通查询画面Service
 *
 * @author etliu
 * @version 1.0 2018年11月29日
 * @see com.joyintech.tams.invest.trade.bondmarkettrade.service.TrdBondMarketTrade001Service
 * @since 1.0
 */
@Service
public class TrdBondMarketTrade001Service extends BaseService<TrdBondMarketTradeDto> implements BaseProcessService<TrdBondMarketTradeDto> {

    @Override
    public void doComplete(TrdBondMarketTradeDto entity, OperationType type) {

    }

    /**
     * 发行代码下拉框
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/getFinprodId", params,
                SelectOptionDto.class);
    }

    /**
     * 上市代码下拉框
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getAppearMarketId(Map<String, Object> params) {
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/getAppearMarketId", params,
                SelectOptionDto.class);
    }
    
    /**
     * 检索债券信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdBondMarketTradeDto> findPage(Map<String, Object> pageParam) {

        Object finprodIdObj = pageParam.get("finprodId");
        Object appearMarketIdObj = pageParam.get("appearMarketId");

        if (finprodIdObj != null) {
            pageParam.put("finprodIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)finprodIdObj));
        }
        else {
            pageParam.put("finprodIdList", new ArrayList<>());
        }

        if (appearMarketIdObj != null) {
            pageParam.put("appearMarketIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)appearMarketIdObj));
        }
        else {
            pageParam.put("appearMarketIdList", new ArrayList<>());
        }
        
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondMarketTrade/findPage",
                pageParam,TrdBondMarketTradeDto.class);
    }
}
