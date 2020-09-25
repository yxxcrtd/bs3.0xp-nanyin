/*
 * 文件名：EtrIRSService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrirs.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.invest.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrirs.dto.EtrIRSDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 利率互换委托画面端service 〈功能详细描述〉
 *
 * @author wangpeng
 * @version 1.0 2019年7月11日
 * @see EtrIRSService
 * @since 1.0
 */
@Service
public class EtrIRSService extends CommonExportService<EtrIRSDto> implements BaseProcessService<EtrIRSDto> {

    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 利率互换委托分页查询 <功能详细描述>
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrIRSDto> selectForPage(Map<String, Object> param) {
        /*if (StringUtil.isNotEmpty((String)param.get("entrustDate"))) {
            // 写法待定
            List<String> list = StringUtil.splitToList(" ", (String)param.get("entrustDate"));
            // get(0)可能报异常
            param.put("startDate", list.get(0));
            param.put("endDate", list.get(2));
        }*/
        if (StringUtil.isNotEmpty(param.get("portfolioId"))) {
            List<String> portfolioIdList = StringUtil.splitToList(GlobalConstant.COMMA,
                (String)param.get("portfolioId"));
            param.put("portfolioIdList", portfolioIdList);
        }
        if (StringUtil.isNotEmpty(param.get("dealMode"))) {
            List<String> dealModeList = StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("dealMode"));
            param.put("dealModeList", dealModeList);
        }
        if (StringUtil.isNotEmpty(param.get("busiPs"))) {
            List<String> busiPsList = StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("busiPs"));
            param.put("busiPsList", busiPsList);
        }
        if (StringUtil.isNotEmpty(param.get("entrustStatus"))) {
            List<String> entrustStatusList = StringUtil.splitToList(GlobalConstant.COMMA,
                (String)param.get("entrustStatus"));
            param.put("entrustStatusList", entrustStatusList);
        }
        param.put("loginUser", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/findPage", param,
            EtrIRSDto.class);

    }

    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param entity
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrIRSDto entity) {
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/recall", entity,
            boolean.class);
    }

    /**
     * 关闭委托 <功能详细描述>
     * 
     * @param entrustId
     * @param closeWay
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/close?entrustId=" + entrustId
                                         + "&closeWay?=" + closeWay,
            boolean.class);
    }

    /**
     * 删除委托 <功能详细描述>
     * 
     * @param entrustId
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/delete?entrustId=" + entrustId,
            boolean.class);
    }

    /**
     * 校验方法
     *
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrIRSDto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/check", dto,
            ValidMessage.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(EtrIRSDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate = DateUtil.platDateFromCache();
        dto.setEntrustDate(sysDate);
        basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/doComplete", dto,
            Integer.class);
    }

    /**
     * 流程发起时调用
     */
    @Override
    public void doInsert(EtrIRSDto dto, OperationType type) {
        dto.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/doInsert", dto, Integer.class);
    }

    /**
     * 流程修改时调用
     */
    @Override
    public void doUpdate(EtrIRSDto dto) {
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/doInsert", dto, Integer.class);
    }

    /**
     * 流程作废时调用
     */
    // @Override
    // public void doDelete(String workflowId){
    // restTemplate.getForObject(
    // ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/doDelete?workflowId="+workflowId,
    // Integer.class);
    // }
    /**
     * 修改/查看反显查询 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrIRSDto selectByKey(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                         + "/EtrIRS/selectByKey?entrustId=" + entrustId,
                                         EtrIRSDto.class);
    }

    /**
     * 导出
     *
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/entrust/etrirs/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public Page<EtrIRSDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /*@Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "transactioncom")) {
            cell.setCellValue(value + "%");
        }
    }*/

    /**
     * 组合可用现金查询 <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    /*@SuppressWarnings("unchecked")
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Date sysDate = DateUtil.platDateFromCache();
        param.put("sysDate", DateUtil.format(sysDate));

        Map<String, Object> cash = restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIRS/getProAvailableCash", param,
            Map.class);

        return cash;
    }*/

    /**
     * 查询文件上传详情 <功能详细描述>
     * 
     * @param businessId String
     * @return List<SysUploadFileInfo>
     * @see
     * @since 1.0
     */
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId) {
        return restTemplate.getForList(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrIRS/selectFileByBusinessId?businessId="
                                       + businessId,
            SysUploadFileInfo.class);
    }

    /**
     * 查询授权额度 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectLimitAmt(EtrIRSDto dto) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", SecurityUtil.getLoginUser().getUserName());
        map.put("assetType", DictCodeListExt2.平安资产类型_利率互换.getValue());
        map.put("tradeMarket", dto.getTradeMarket());
        map.put("ps", dto.getPs());
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/SelectLimitAmt/selectLimitAmt", map,
            BigDecimal.class);
    }

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        EtrIRSDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            try {
            	Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), dto.getEntrustAmt().multiply(new BigDecimal(10000)), LoginUserUtil.getUserName(),
                        DictCodeListExt2.平安资产类型_利率互换.getValue(), null, dto.getTradeMarket(), dto.getBusiPs()
                        // 资产类型改为授权类型 add by renping 2019-08-06  start
                        , null, dto.getWorkflowId());
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
