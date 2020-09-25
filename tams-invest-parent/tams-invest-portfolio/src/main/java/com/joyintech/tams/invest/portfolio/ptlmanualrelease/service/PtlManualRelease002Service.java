/*
 * 文件名：PtlManualRelease001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenlei1
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlmanualrelease.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.portfolio.ptlmanualrelease.dto.PtlManualRelease001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 手动释放头寸
 * 〈功能详细描述〉
 * @author chenlei1
 * @version 1.0 2019年12月10日
 * @see PtlManualRelease002Service
 * @since 1.0
 */
@Service("PtlManualRelease002Service")
@Transactional(rollbackFor = Exception.class)
public class PtlManualRelease002Service extends CommonExportService<PtlManualRelease001Dto> 
    implements BaseProcessService<PtlManualRelease001Dto> {
  
    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlManualRelease001Dto entity, OperationType type) {
        //entity.setActPortfolioId(entity.getPortfolioId());
        entity.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(entity.getPortfolioId()));
        String action = StringUtil.EMPTY;
        // entity.setSystemCode("SYS");
        if (OperationType.REVOKE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        
        restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/doCompleteRs", entity,
            String.class);
    }
    @Override
    public void doInsert(PtlManualRelease001Dto entity, OperationType type) {
        //saveDataWhenStartWorkflow(entity, type);
    }
    /**
     * 工作流doUpdate
     * @param entity entity 业务entity
     */
    @Override
    public void doUpdate(PtlManualRelease001Dto entity) {
        //if("submit".equals(entity.getOperateType())) {
            //doDelete(entity.getWorkflowId());
       // }
        //saveDataWhenStartWorkflow(entity, OperationType.NEW);
    }
    
    @Override
    public void doDelete(String workflowId) {
        restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/doDelete?workflowId="+ workflowId,
            void.class);
    }
    
    
    public PtlManualRelease001Dto selectTradeInfo(String tradeId) { 
    	Map<String, Object> map =new HashMap<String, Object>();
    	map.put("tradeId", tradeId);
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
                + "/PtlManualRelease/selectTradeInfo" , map,PtlManualRelease001Dto.class);
    }
    /**
     * 质押式券查询
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<PtlManualRelease001Dto> findPageRepInfo(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/findPageRepInfo",
            pageParam, PtlManualRelease001Dto.class);
    }
    
    public List<PtlManualRelease001Dto> findPageRepInfoList(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME+"/PtlManualRelease/findPageRepInfoList",
        		pageParam, PtlManualRelease001Dto.class);
    }
}
