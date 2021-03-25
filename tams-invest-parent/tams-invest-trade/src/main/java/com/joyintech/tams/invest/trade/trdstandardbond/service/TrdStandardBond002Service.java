/*
 * 文件名：TrdStandardBond002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.trdstandardbond.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdStandardBond002Service extends BaseService<TrdStandardBond002Dto> implements BaseProcessService<TrdStandardBond002Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(TrdStandardBond002Dto entity, OperationType operationType) {
        //出库不考虑撤销
        if (!StringUtil.isEmptyList(entity.getPledgeBondList())) {
            entity.setIsBack(false);
            entity.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSubmit",
                    entity, Long.class);
        }
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(TrdStandardBond002Dto input) {
        //出库不考虑撤销
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
            input.setIsBack(false);
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSubmit",
                    input, Long.class);
        }
    }

    /**
     * 工作流中doBack
     *
     * @param input
     */
    @Override
    public void doBack(TrdStandardBond002Dto input) {
        //出库不考虑撤销
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
            input.setIsBack(true);
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSubmit",
                    input, Long.class);
        }
    }

    /**
     * 工作流中doDelete
     *
     * @param workflowId workflowId
     */
    @Override
    public void doDelete(String workflowId) {
        // do nothing
    }

    /**
     * 工作流审核完成doComplete
     *
     * @param input         entity
     * @param operationType operationType
     */
    @Override
    public void doComplete(TrdStandardBond002Dto input, OperationType operationType) {
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/inSave",
                    input, Long.class);
        }
    }

    /**
     * 检索可质押债券列表
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdStandardBond002Dto> selectAvaPledgeBondList(Map<String, Object> params) {
        params.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));

        // 多选下拉框值处理
        // 组合代码
        if (params.get("bondCode") != null) {
            params.put("bondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) params.get("bondCode")));
        } else {
            params.put("bondCodeList", new ArrayList<>());
        }
        params.put("searchDate", platformDateImpl.getSysDateStr());
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/selectAvaPledgeBondList",
                params, TrdStandardBond002Dto.class);
    }
}
