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
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.trdstandardbond.dto.TrdStandardBond003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrdStandardBond003Service extends BaseService<TrdStandardBond003Dto> implements BaseProcessService<TrdStandardBond003Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(TrdStandardBond003Dto entity, OperationType operationType) {
        // do nothing
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(TrdStandardBond003Dto input) {
        // do nothing
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
    public void doComplete(TrdStandardBond003Dto input, OperationType operationType) {
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(input.getOutBondList())) {
            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/outSave",
                    input, Long.class);
        }
    }

    /**
     * 质押券出库信息一览查询
     *
     * @param pageParam 查询参数
     * @return List<TrdStandardBond003Dto>
     * @see
     * @since 1.0
     */
    public List<TrdStandardBond003Dto> selectOutListData(Map<String, Object> pageParam) {
        pageParam.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        pageParam.put("isPreciseSearch", true);

        // 组合代码
        if (pageParam.get("portfolioId") != null) {
            pageParam.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("portfolioId")));
        } else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }
        // 债券对应金融产品代码
        if (pageParam.get("bondCode") != null) {
            pageParam.put("bondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("bondCode")));
        } else {
            pageParam.put("bondCodeList", new ArrayList<>());
        }
        if (pageParam.get("initBondCode") != null) {
            pageParam.put("initBondCodeList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("initBondCode")));
        } else {
            pageParam.put("initBondCodeList", new ArrayList<>());
        }
        if (pageParam.get("initPortfolioId") != null) {
            pageParam.put("initPortfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("initPortfolioId")));
        } else {
            pageParam.put("initPortfolioIdList", new ArrayList<>());
        }
        // 交易场所
        if (pageParam.get("tradePlace") != null) {
            pageParam.put("tradePlaceList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("tradePlace")));
        } else {
            pageParam.put("tradePlaceList", new ArrayList<>());
        }
        // 证券账户
        if (pageParam.get("secAcctId") != null) {
            pageParam.put("secAcctIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String) pageParam.get("secAcctId")));
        } else {
            pageParam.put("secAcctIdList", new ArrayList<>());
        }
        pageParam.put("searchDate", platformDateImpl.getSysDateStr());
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/TrdStandardBond/selectOutListData",
                pageParam, TrdStandardBond003Dto.class);
    }
}
