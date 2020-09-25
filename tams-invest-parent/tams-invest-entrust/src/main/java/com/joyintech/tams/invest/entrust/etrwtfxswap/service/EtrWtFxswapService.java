/*
 * 文件名：EtrWtFxswapService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangzhuangzhuang
 * 修改时间：2019年6月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrwtfxswap.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.entrust.etrseclend.dto.EtrSecLendDto;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrwtfxswap.dto.EtrWtFxswapDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 外汇掉期委托Service
 * 〈功能详细描述〉
 * @author wangzhuangzhuang
 * @version 1.0 2019年6月18日
 * @see EtrWtFxswapService
 * @since 1.0
 */
@Service
public class EtrWtFxswapService extends CommonExportService<EtrWtFxswapDto> implements BaseProcessService<EtrWtFxswapDto>{
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
     * 项目类委托分页查询 <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrWtFxswapDto> selectForPage(Map<String, Object> param) {
        if (StringUtil.isNotEmpty((String)param.get("entrustDate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)param.get("entrustDate"));
            param.put("startDate", dateList.get(0));
            param.put("endDate", dateList.get(2));
        }
        if(StringUtil.isNotEmpty((String)param.get("portfolioId"))){
            List<String> portfolioIdList = StringUtil.splitToList(",", (String)param.get("portfolioId"));
            param.put("portfolioIdList", portfolioIdList);
        }
        if(StringUtil.isNotEmpty((String)param.get("tradeType"))){
            List<String> tradeTypeList = StringUtil.splitToList(",", (String)param.get("tradeType"));
            param.put("tradeTypeList", tradeTypeList);
        }
        if(StringUtil.isNotEmpty((String)param.get("tradePs"))){
            List<String> tradePsList = StringUtil.splitToList(",", (String)param.get("tradePs"));
            param.put("tradePsList", tradePsList);
        }
        if(StringUtil.isNotEmpty((String)param.get("ccyPair"))){
            List<String> ccyPairList = StringUtil.splitToList(",", (String)param.get("ccyPair"));
            param.put("ccyPairList", ccyPairList);
        }
        if(StringUtil.isNotEmpty((String)param.get("termType"))){
            List<String> termTypeList = StringUtil.splitToList(",", (String)param.get("termType"));
            param.put("termTypeList", termTypeList);
        }
        if(StringUtil.isNotEmpty((String)param.get("entrustStatus"))){
            List<String> entrustStatusList = StringUtil.splitToList(",", (String)param.get("entrustStatus"));
            param.put("entrustStatusList", entrustStatusList);
        }
        
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/findPage", param,
            EtrWtFxswapDto.class);
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
        exportParam.config("/com/joyintech/tams/invest/entrust/etrwtfxswap/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }
    
    @Override
    public Page<EtrWtFxswapDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName, "tradePercent")){
            cell.setCellValue(new BigDecimal(100).multiply(StringUtil.isNotEmpty(value)?new BigDecimal(value.toString()):BigDecimal.ZERO)+"%");
        }
    }
    /**
     * 校验方法 
     * 
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrWtFxswapDto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/check", dto,
            ValidMessage.class);
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
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/close?entrustId=" + entrustId
                                         + "&closeWay=" + closeWay,
            boolean.class);
    }
    
    /**
     * 撤回委托 <功能详细描述>
     *
     * @param entity
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrWtFxswapDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/recall", dto, boolean.class);
    }
    
    /**
     * 查询修改/查看返显数据 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrWtFxswapDto selectByKey(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                         + "/EtrWtFxswap/selectByKey?entrustId=" + entrustId,
        EtrWtFxswapDto.class);
    }
    /**
     * 工作流完成
     */
    @Override
    public void doComplete(EtrWtFxswapDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate =  CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        dto.setEntrustDate(sysDate);
        basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/doComplete", dto,
            Integer.class);
    }
    /**
     * 
     * 流程发起时调用
     */
     @Override
     public void doInsert(EtrWtFxswapDto dto, OperationType type){
         dto.setOperateType(type.toString());
         restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/doInsert",dto,
             Integer.class);
     }

     /**
      * 
      * 流程修改时调用
      */
     @Override
     public void doUpdate(EtrWtFxswapDto dto){
         restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/doInsert",dto,
             Integer.class);
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
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/delete?entrustId="+entrustId,
            boolean.class);
    }
    /**
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getProAvailableCash(Map<String, Object> param) {
        Date vdate = DateUtil.parse((String)param.get("vdate"));
        String calId = (String)param.get("calId");
        if(StringUtil.isNotEmpty(calId)){
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(vdate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15),calId);
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
            param.put("vdate1",DateUtil.format(tradeDay));
        }
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrWtFxswap/getProAvailableCash",param,
            BigDecimal.class);
    }
    
    
    /**
     * 货币账户查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> selectPtlCapAcctNo(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectPtlCapAcctNo",param,
            SelectOptionDto.class);
    }

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        Map<String, Object> variables = new HashMap<>();
        EtrWtFxswapDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            try {
            	Map<String, Object> params = CommonBusinessService.setParams(
                        dto.getCounterId(), dto.getEntrustAmt(), LoginUserUtil.getUserName(),
                        DictCodeListExt2.平安资产类型_外汇掉期.getValue(), null, dto.getTradeMarket(), dto.getTradePs()
                        // 资产类型改为授权类型 add by renping 2019-08-06  start
                        , null, dto.getWorkflowId()
                        // 资产类型改为授权类型 add by renping 2019-08-06  end
                );
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
