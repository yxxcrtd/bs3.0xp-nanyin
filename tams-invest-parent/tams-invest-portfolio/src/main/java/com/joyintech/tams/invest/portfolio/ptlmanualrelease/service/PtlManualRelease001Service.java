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
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
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
 * @see PtlManualRelease001Service
 * @since 1.0
 */
@Service("PtlManualRelease001Service")
@Transactional(rollbackFor = Exception.class)
public class PtlManualRelease001Service extends CommonExportService<PtlManualRelease001Dto> 
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
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlCashFlow/" + action, entity,
            Integer.class);
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
            doDelete(entity.getWorkflowId());
       // }
        //saveDataWhenStartWorkflow(entity, OperationType.NEW);
    }
    
    @Override
    public void doDelete(String workflowId) {
        restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlCashFlow/doDelete?workflowId="+ workflowId,
            void.class);
    }
    
   
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketId(String param) {
        return restTemplate.getForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/PtlManualRelease/getFinprodMarketId?param=" + param,SuggestDto.class);
    }

  
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public PtlManualRelease001Dto getEntity(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/PtlManualRelease/getEntity" , map,PtlManualRelease001Dto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/PtlManualRelease/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    

    
    /**
     * 证券账户账号联想文本框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String secAcctId) {
        return restTemplate.getForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/getSecAcctId?secAcctId="+secAcctId,
            SuggestDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
/*    public Page<SelectOptionDto> getCounterId(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/PtlManualRelease/getCounterId" , param,SelectOptionDto.class);
    }*/

    public List<SuggestDto> getCounterId(String counterId){
        Map<String, Object> map = new HashMap<>();
        map.put("counterId", counterId);
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME+"/PtlManualRelease/getCounterId",
            map, SuggestDto.class);
    }
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodIdToNew(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/PtlManualRelease/getFinprodIdToNew" , param,SelectOptionDto.class);
    }
    

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
/*    public Page<SelectOptionDto> getPortfolioId(String param) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("param", param);
        User user = SecurityUtil.getLoginUser();
        map.put("userName", user.getUserName());
        return restTemplate.postForPage(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/PtlManualRelease/getPortfolioId"  ,map,SelectOptionDto.class);
    }*/
    
    public List<SuggestDto> getPortfolioId(String portfolioId){
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("portfolioId", portfolioId);
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME+"/PtlManualRelease/getPortfolioId",
            map, SuggestDto.class);
    }
    
    /**
     * 获取组合代码的下拉框
     * <功能详细描述>
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioIdToNew(Map<String,Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userName", user.getUserName());
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/PtlManualRelease/getPortfolioIdToNew" ,param,SelectOptionDto.class);
    }
    
    
    /**
     * 质押式正回购查询
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<PtlManualRelease001Dto> selectForPageRs(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/findPageRs",
            pageParam, PtlManualRelease001Dto.class);
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
    
    
    /**
     * 质押式逆回购查询
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<PtlManualRelease001Dto> selectForPageRb(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/findPageRb",
            pageParam, PtlManualRelease001Dto.class);
    }
    

    /**
     * 质押式正回购解冻
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public void revokeRs(PtlManualRelease001Dto dto) {
         restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/revokeRs", dto,
                String.class);
    }

    /**
     * 质押式逆回购解冻
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
	@SuppressWarnings("unchecked")
	public void revokeRb(PtlManualRelease001Dto dto) {
		    restTemplate.postForObject(
	                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlManualRelease/revokeRb", dto,
	                String.class);
	}
    
}
