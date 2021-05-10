/*
 * 文件名：TrdPlanNetDuration001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：dingzhipeng
 * 修改时间：2018年10月15日 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdassetlossclear.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.period.trdassetlossclear.dto.TrdAssetLossClear001Dto;
import com.joyintech.tams.invest.period.trdplannetduration.dto.TrdPlanNetDuration001Dto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.setWorkflowId;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chufengjie 
 * @version 1.0 2020年10月20日
 * @see TrdAssetLossClear001Service
 * @since 1.0
 */
@Service
public class TrdAssetLossClear001Service extends CommonExportService<TrdAssetLossClear001Dto> implements BaseProcessService<TrdAssetLossClear001Dto> {

    @Autowired
    private TrdAssetLossClearDealEntity dealEntityService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommService;
    
    
	@Override
	public void doComplete(TrdAssetLossClear001Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/doComplete",
            entity, Integer.class);
		
	}

    /**
     * 工作流doUpdate
     * 
     * @param entity entity 业务entity
     */
    @Override
    public void doUpdate(TrdAssetLossClear001Dto entity) {
        ProcessMark newEntity = null;
        try {
            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(TrdPlanNetDuration001Dto.class, true);
            // 工作流ID
            newEntity.setWorkflowId(entity.getWorkflowId());
            newEntity.setStatus(entity.getStatus());
            newEntity.setBusinessData(JsonUtils.object2Json(entity));
            // 更新业务表数据
            // 操作留痕表
            WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        dealEntityService.saveDataWhenStartWorkflow(entity, OperationType.NEW, false);
    }

    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
    }

    /**
     * 获取组合代码 组合代码获取功能
     * 
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> finprodMarketId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/finprodMarketId", map,
            SelectOptionDto.class);
    }

    /**
     * 画面查询 <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdAssetLossClear001Dto> findPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/findPage", pageParam,
        		TrdAssetLossClear001Dto.class);
    }
    
    /**
     * 画面查询 <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdAssetLossClear001Dto> findNewPage(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/findNewPage", pageParam,
        		TrdAssetLossClear001Dto.class);
    }
    
    /**
     * 画面查询 <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<TrdAssetLossClear001Dto> findNewList(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/findNewList", pageParam,
        		TrdAssetLossClear001Dto.class);
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param cashId String
     * @param finprodId String
     * @param effDate Date
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public List<TrdAssetLossClear001Dto> selectDetail(String finprodMarketId,String clearDateStart){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodMarketId", finprodMarketId);  
        param.put("clearDateStart", clearDateStart);   
        return (List<TrdAssetLossClear001Dto>) restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/selectDetail", param,
            TrdAssetLossClear001Dto.class);
    }
    
    /**
     * 策略代码生成
     * 
     * @see
     * @since 1.0
     */
    public String createStrategyId() {
        return restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/createStrategyId",
            null, String.class);

    }
    
    /**
     * 已清理资产查询
     * 
     * @see
     * @since 1.0
     */
    public int selectAredyLossAsset(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/selectAredyLossAsset",
            param, int.class);

    }
    

    /**
     * @Description: 构建导出参数
     * @param: [selectParam 参数]
     * @return:
     * @Date: 2018/11/23
     */
    public List<TrdAssetLossClear001Dto> selectForExport(Map<String, Object> param) {
        List<TrdAssetLossClear001Dto> list = restTemplate.postForList(ServiceApplication.PERIOD_SERVICE_NAME
                        + "/TrdAssetLossClear/selectForExport", param,TrdAssetLossClear001Dto.class);
        return list;
    }
    
    /**
     * 
     * 双击查看、修改取得数据源
     * <功能详细描述>
     * @param cashId String
     * @param finprodId String
     * @param effDate Date
     * @return FinProductRateQuotationDto
     * @see
     * @since 1.0
     */
    public Page<TrdAssetLossClear001Dto> selectByKey(String totleTradeId) {
        Map<String, Object> param = new HashMap<>();
        param.put("totleTradeId",totleTradeId);
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdAssetLossClear/selectByKey",
                param, TrdAssetLossClear001Dto.class);
    }

    /**
     * 获取有效或者撤销的数据
     * 
     * @param param
     * @return
     */
    public TrdPlanNetDuration001Dto findEffectiveData(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanNetDuration/findEffectiveData", param,
            TrdPlanNetDuration001Dto.class);
    }

    /**
     * 获取现金分红流程中
     * 
     * @param param
     * @return
     */
    public TrdPlanNetDuration001Dto findCashDividend(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanNetDuration/findCashDividend", param,
            TrdPlanNetDuration001Dto.class);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "registDate") || StringUtil.equals(propName, "exRightsDate")
                || StringUtil.equals(propName, "arriveDate") || StringUtil.equals(propName, "reInvesDate")) {
            if (value instanceof Date) {
                cell.setCellValue(DateUtil.format((Date) value));
            } else if (value instanceof Long) {
                cell.setCellValue(DateUtil.format(new Date((Long) value)));
            }
        }
    }
    
    /**
     * 以组合维度生成dto，就是以利息明细维度组装新的dto，作为属性保存到流程entity中<br/>
     * 汇总 <br/>
     * 汇总扩展 <br/>
     * 汇总费用明细 <br/>
     * List<Dto> | 交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 附属 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 扩展 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 费用明细 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| 组合交易 <br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| List<划款指令>
     */
    public BaseTotalTradeInfoDto genFinProductTradeDto(TrdPlanNetDuration001Dto entity) {
        BaseTotalTradeInfoDto result = dealEntityService.genBaseTotalTradeInfoDto(entity);
        return result;
    }

    /**
     * 根据交易编号删除流程表中数据 <功能详细描述>
     * 
     * @param tradeId 交易代码
     * @see
     * @since 1.0
     */
    public int deleteWflByTradeId(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdFundDuration/deleteWflByTradeId", tradeId,
            Integer.class);
    }

}
