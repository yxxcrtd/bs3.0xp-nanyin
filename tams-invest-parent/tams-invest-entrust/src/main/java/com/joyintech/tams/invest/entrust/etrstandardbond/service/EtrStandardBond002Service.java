/*
 * 文件名：TrdStandardBond002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrstandardbond.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class EtrStandardBond002Service extends BaseService<EtrStandardBond002Dto> implements BaseProcessService<EtrStandardBond002Dto> {

    @Autowired
    private IPlatformDate platformDateImpl;

    /**
     * 工作流doInsert
     *
     * @param entity        entity
     * @param operationType operationType
     */
    @Override
    public void doInsert(EtrStandardBond002Dto entity, OperationType operationType) {
        // do nothing
    }

    /**
     * 工作流中doUpdate
     *
     * @param input input
     */
    @Override
    public void doUpdate(EtrStandardBond002Dto input) {
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
    public void doComplete(EtrStandardBond002Dto input, OperationType operationType) {
        Long insertCount = 0L;
        if (!StringUtil.isEmptyList(input.getPledgeBondList())) {
//            input.setBranch(Long.parseLong((String) CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE)));
            insertCount += restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/inSave",
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
    public List<EtrStandardBond002Dto> selectAvaPledgeBondList(Map<String, Object> params) {
        params.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        List<EtrStandardBond002Dto> list = new ArrayList<EtrStandardBond002Dto>() ;
        // 多选下拉框值处理
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("tradePlace"))) {
            params.put("tradePlace", StringUtil.splitToList(",", (String)params.get("tradePlace")));
        }
        if (StringUtil.isNotEmpty(params.get("secAcctId"))) {
            params.put("secAcctId", StringUtil.splitToList(",", (String)params.get("secAcctId")));
        }
        params.put("plateDate", platformDateImpl.getSysDateStr());
        params.put("userId", SecurityUtil.getLoginUser().getUserName());
        list = restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectAvaPledgeBondList",
                params, EtrStandardBond002Dto.class);
        
//        List<EtrStandardBond002Dto> pledList = restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectPledgeBondList",
//            params, EtrStandardBond002Dto.class);
//        
//        if(list.size() >0 && pledList.size() > 0){
//            for (EtrStandardBond002Dto avaDto : list) {
//                for (EtrStandardBond002Dto pledDto : pledList) {
//                    if(StringUtil.equals(avaDto.getPortfolioId(), pledDto.getPortfolioId())&&
//                        StringUtil.equals(avaDto.getSecAcctId(), pledDto.getSecAcctId())&&
//                        StringUtil.equals(avaDto.getFinProdId(), pledDto.getFinProdId())){
//                        list.remove(avaDto) ;
//                    }
//                }
//            }
//        }
        return list ;
    }
    /**
     * 检索已入库债券信息
     *
     * @param params 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public List<EtrStandardBond002Dto> selectPledgeBondList(Map<String, Object> params) {
        params.put("BRANCH_0", CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE));
        
        // 多选下拉框值处理
        if (StringUtil.isNotEmpty(params.get("portfolioId"))) {
            params.put("portfolioId", StringUtil.splitToList(",", (String)params.get("portfolioId")));
        }
        if (StringUtil.isNotEmpty(params.get("tradePlace"))) {
            params.put("tradePlace", StringUtil.splitToList(",", (String)params.get("tradePlace")));
        }
        if (StringUtil.isNotEmpty(params.get("secAcctId"))) {
            params.put("secAcctId", StringUtil.splitToList(",", (String)params.get("secAcctId")));
        }
        params.put("plateDate", platformDateImpl.getSysDateStr());
        params.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStandardBond/selectPledgeBondList",
            params, EtrStandardBond002Dto.class);
    }
}
