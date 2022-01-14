/*
 * 文件名：TrdLendInvestTrade001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.trademanage.trdbuyoutrepovaluation.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.trdbuyoutrepovaluation.dto.TrdOutRightRepoValuationTrade001Dto;

import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TrdOutRightRepoValuationTrade001Service extends CommonExportService<TrdOutRightRepoValuationTrade001Dto> implements BaseProcessService<TrdOutRightRepoValuationTrade001Dto> {


    /**
     * 查询
     *
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<TrdOutRightRepoValuationTrade001Dto> findPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade001/findPage",
                pageParam, TrdOutRightRepoValuationTrade001Dto.class);
    }


    /**
     * 不分页  导出
     *
     * @param params
     * @return
     */
    public List<TrdOutRightRepoValuationTrade001Dto> queryPageList(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade001/queryPageList", params,
                TrdOutRightRepoValuationTrade001Dto.class);
    }


    @Override
    public void doComplete(TrdOutRightRepoValuationTrade001Dto entity, OperationType type) {
        String action = "delete";
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/TrdOutRightRepoValuationTrade001/" + action, entity, Integer.class);
    }

}
