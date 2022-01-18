/*
 * 文件名：TrdLendInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdlendvaluationtrade.dto.TrdLendValuationTradeDto;
import com.joyintech.tams.valuation.trademanage.trdplanvaluationtrade.dto.TrdPlanValuationTradeDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdLendValuationTrade001Service extends CommonExportService<TrdLendValuationTradeDto> implements BaseProcessService<TrdLendValuationTradeDto> {


    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<TrdLendValuationTradeDto> findPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdLendValuationTrade/findPage",
                pageParam, TrdLendValuationTradeDto.class);
    }

    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(TrdLendValuationTradeDto entity, OperationType type) {
        String action = "delete";
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/TrdLendValuationTrade/" + action, entity, Integer.class);
    }

    /**
     * 不分页  导出
     *
     * @param params
     * @return
     */
    public List<TrdLendValuationTradeDto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdLendValuationTrade/queryPageList", params,
                TrdLendValuationTradeDto.class);
    }




}
