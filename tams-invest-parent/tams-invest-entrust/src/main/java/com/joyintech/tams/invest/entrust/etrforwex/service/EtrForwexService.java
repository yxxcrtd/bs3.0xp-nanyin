/*
 * 文件名：EtrForwexService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrforwex.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.entrust.etrwtspotex.dto.EtrWtSpotexDto;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrforwex.dto.EtrForwexDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年6月18日
 * @see EtrForwexService
 * @since 1.0
 */
@Service
public class EtrForwexService extends CommonExportService<EtrForwexDto> implements BaseProcessService<EtrForwexDto> {

    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;

    @Autowired
    private CommonBusinessService commonBusinessService;
	/**
	 * 流程结束
	 */
	@Override
	public void doComplete(EtrForwexDto entity, OperationType type) {
		// TODO Auto-generated method stub
        entity.setInvManagerId(SecurityUtil.getLoginUser().getUserName());
        entity.setOperateType(type.toString());
        basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getEntrustId());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/doComplete",
            entity, Integer.class);

		
	}
	
	/**
	 * 插入
	 */
	 @Override
    public void doInsert(EtrForwexDto entity, OperationType type){
        //entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/doInsert",
            entity, Integer.class);
    }

    /**
     * 修改
     */
    @Override
    public void doUpdate(EtrForwexDto entity){
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/doInsert",
            entity, Integer.class);
    }
	    
	
    /**
     * 导出excel
     */
	@Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrforwex/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
	
	/**
	 * 导出查询
	 * {@inheritDoc}
	 */
    @Override
    public Page<EtrForwexDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 设置单元格值
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"transactioncom")){
            cell.setCellValue(value+"%");
        }
    }
    
    /**
     * 查询
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrForwexDto> selectForPage(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/findPage", param,
            EtrForwexDto.class);
    }
	

	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param entrustId
	 * @return
	 * @see
	 * @since 1.0
	 */
    public EtrForwexDto selectByKey(String entrustId){
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
            + "/EtrForwex/selectByKey?entrustId=" + entrustId,
            EtrForwexDto.class);
    }

    
    /**
     * 撤回委托
     * <功能详细描述>
     * @param dto 实体类
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(String entrustId) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/recall?entrustId="+entrustId,
            boolean.class);
    }
    /**
     * 关闭委托
     * <功能详细描述>
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId,String closeWay) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/close?entrustId="+entrustId+"&closeWay="+closeWay,
            boolean.class);
    }

    /**
     *
     * 删除投资委托
     * <功能详细描述>
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/delete?entrustId="+entrustId,
            boolean.class);
    }
	
    
	/**
	 * 获取清算账户 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<SelectOptionDto> selectPtlCapAcctNo(Map<String, Object> param) {
		// TODO Auto-generated method stub
        User user = SecurityUtil.getLoginUser();
        param.put("loginUser", user.getUserName());
        param.put("acctType","A01");
        if(!StringUtil.isNotEmpty(param.get("ccy"))) {
        	param.put("ccy", StringUtil.isNotEmpty(param.get("corCcy"))?param.get("corCcy"):"CNY");
        }
        return restTemplate.postForList(ServiceApplication.COMMON_SERVICE_NAME
                                       + "/CommonSelect/selectPtlCapAcctNo" , param,
                                       SelectOptionDto.class);
	}
	
	/**
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Map<String,Object> getProAvailableCash(Map<String,Object> param) {
        Date sysDate = DateUtil.platDateFromCache();
        param.put("vdate", DateUtil.format(sysDate));
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrFund/getProAvailableCash",param,
            Map.class);
    }
    
    /**
    *
    * 删除、撤销、关闭委托校验方法
    * <功能详细描述>
    * @param dto EtrFundDto
    * @return List<ValidMessage>
    * @see
    * @since 1.0
    */
   public List<ValidMessage> check(EtrForwexDto dto) {
       return restTemplate.postForList(
           ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrForwex/check", dto,
           ValidMessage.class);
   }

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        EtrForwexDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            try {
            	Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), dto.getEntrustAmt(), LoginUserUtil.getUserName(),
                        DictCodeListExt2.平安资产类型_外汇远期.getValue(), null, dto.getTradeMarket(), dto.getPs()
                        , null, entity.getWorkflowId());
                return commonBusinessService.是否需要上级审批_委托用(params);
            }
            catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        }
        else {
            return null;
        }
    }
    
}
