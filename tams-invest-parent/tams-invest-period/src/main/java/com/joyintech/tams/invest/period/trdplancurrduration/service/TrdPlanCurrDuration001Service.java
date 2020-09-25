/*
 * 文件名：TrdFundDuration001Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：dingzhipeng 修改时间：2018年10月15日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdplancurrduration.service;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.period.trdplancurrduration.dto.TrdPlanCurrDuration001Dto;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.joyintech.tams.invest.period.common.service.PeriodCommonService.setWorkflowId;

/**
 * 计划类货币型 〈功能详细描述〉
 *
 * @author dingzhipeng
 * @version 1.0 2018年10月15日
 * @see TrdPlanCurrDuration001Service
 * @since 1.0
 */
@Service
public class TrdPlanCurrDuration001Service extends CommonExportService<TrdPlanCurrDuration001Dto> implements BaseProcessService<TrdPlanCurrDuration001Dto> {

    private final String 非货币总集复核_BTN = "TrdFundDuration002_btn_total";

    /**
     * 业务编号
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    @Autowired
    private TrdPlanCurrDurationDealEntity dealEntityService;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    /**
     * 划款指令共通
     */
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    /**
     * 获取资产代码 <功能详细描述>
     *
     * @return 资产代码
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodMarketId() {
        Map<String, Object> params = RequestUtil.getPageParam();
        params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        params.put("profitType", DictCodeList.收益类型_货币型.getValue());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/getFinprodMarketId", params,
            SelectOptionDto.class);
    }

    /**
     * 工作流doInsert
     *
     * @param entity 业务entity
     * @param type 操作类型
     */
    @Override
    public void doInsert(TrdPlanCurrDuration001Dto entity, OperationType type) {
        if (BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
            setWorkflowId(entity.getBaseTotalTradeInfoDto(), entity.getWorkflowId());
        }
        dealEntityService.saveDataWhenStartWorkflow(entity, type, false);
    }

    /**
     * 工作流doUpdate
     *
     * @param entity entity 业务entity
     */
    @Override
    public void doUpdate(TrdPlanCurrDuration001Dto entity) {
//        baseTradeProductDealCommServiceImpl.delete(entity.getWorkflowId());
        entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
        ProcessMark newEntity = null;
        try {
            newEntity = WorkFlowUtil.getBusinessObjectFromRequest(TrdPlanCurrDuration001Dto.class, true);
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

    /**
     * 工作流删除
     *
     * @param workflowId
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommServiceImpl.delete(workflowId);
    }

    /**
     * 工作流完成
     */
    @Override
    public void doComplete(TrdPlanCurrDuration001Dto entity, OperationType type) {
        entity.setUpdateUser(SecurityUtil.getLoginUser().getUserName());
        if (OperationType.REVOKE == type) {
            dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_撤销, false);
            entity.setOperateType(BussinessConstant.REVOKE);
        }
        else {
            dealEntityService.execute交易共通(entity, DictCodeListExt1.交易流程状态_有效, false);
        }
        entity.setRepIssuePartyId(CacheUtil.getSysParam("issue_party_id"));
        // 新建交易撤销数据 或 插入Entity到对应的业务DB中, 并删除流程表中的数据
        restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/doComplete", entity,
            String.class);
        // 除权登记生成总集复核数据
        if (OperationType.REVOKE != type && StringUtil.equals(entity.getType(), "05")) {
            // 设置工作流代码
            entity.setWorkflowId(null);
            if (StringUtil.equals(entity.getBonusMode(), DictCodeList.分红方式_现金分红.getValue())) {
                // 非货币现金分红
                entity.setType("06");
                // 处理数据参数
                getWorkFlowPatameter(entity);
                entity.setBaseTotalTradeInfoDto(genFinProductTradeDto(entity));
                dealEntityService.saveDataWhenStartWorkflow(entity, OperationType.NEW, true);
            }
        }
    }

    /**
     * 设置流程所需parameter
     *
     * @param dto 流程dto
     * @return 构建的dto
     */
    public WorkFlowParameter<TrdPlanCurrDuration001Dto> getWorkFlowPatameter(TrdPlanCurrDuration001Dto dto) {
        WorkFlowParameter<TrdPlanCurrDuration001Dto> parameter = new WorkFlowParameter<>();
        parameter.setPageId("TrdPlanCurrDuration002");
        parameter.setServiceName(WorkFlowUtil.getServiceName(this.getClass()));
        parameter.setElementId(dto.getElementId());
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        // 为非货币现金分红及非货币红利再投时 设置前置交易编号
        if (StringUtil.equals(dto.getType(), "06")) {
            dto.setbTradeId(dto.getTotleTradeId());
        }
        dto.setTotleTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        parameter.setBusinessCode(dto.getTotleTradeId());
        parameter.setBusinessType(DictCodeList.共通业务类型_计划类资产_货币型_存续期.getValue());
        // 设置流程业务说明, entity即传入的dto
        parameter.setComment(entity -> WorkFlowUtil.concatComment(this.getMessage("TrdDepoDuration001.finprodName"),
            GlobalConstant.OPEN_PARENTHESIS + entity.getFinprodMarketName() + GlobalConstant.CLOSE_PARENTHESIS,
            this.getMessage("TrdFundDuration001.tradeId"),
            GlobalConstant.OPEN_PARENTHESIS + entity.getTotleTradeId() + GlobalConstant.CLOSE_PARENTHESIS));
        return parameter;
    }

    /**
     * 获取组合代码 组合代码获取功能
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getPortfolioId() {
        Map<String, Object> map = RequestUtil.getPageParam();
        if (map.get("initValue") == null) {
            map.put("userId", LoginUserUtil.getUserName());
        }
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/getPortfolioId", map,
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
    public Page<TrdPlanCurrDuration001Dto> findPage(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/findPage", pageParam,
            TrdPlanCurrDuration001Dto.class);
    }

    /**
     * @Description: 构建导出参数
     * @param: [exportParam 参数]
     * @author: 丁志鹏
     * @Date: 2018/11/23
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/period/trdplancurrduration/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<TrdPlanCurrDuration001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }

    /**
     * 资产代码联动查询 <功能详细描述>
     *
     * @param pageParam 画面的条件
     * @return 画面查询结果
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> finprodMarketIdLink(Map<String, Object> pageParam) {
        pageParam.put("FINPRODTYPE2_F06", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/finprodMarketIdLink",
            pageParam, Map.class);
    }

    /**
     * 登记份额查询 <功能详细描述>
     *
     * @param pageParam 画面的条件
     * @return 登记份额
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> calRegistShare(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/calRegistShare",
            pageParam, Map.class);
    }

    /**
     * 获取有效或者撤销的数据
     *
     * @param param
     * @return
     */
    public TrdPlanCurrDuration001Dto findEffectiveData(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/findEffectiveData",
            param, TrdPlanCurrDuration001Dto.class);
    }

    /**
     * 获取现金分红流程中
     *
     * @param param 查询参数
     * @return 匹配结果
     */
    public TrdPlanCurrDuration001Dto findCashDividend(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/findCashDividend", param,
            TrdPlanCurrDuration001Dto.class);
    }

    /**
     * 统计是否有后续交易
     *
     * @param map 参数
     * @return 统计数
     */
    public int countFollowDeal(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/countFollowDeal", map,
            Integer.class);
    }

    /**
     * 处理导出参数，格式化导出的格式
     *
     * @param beanName beanName
     * @param cell 单元格
     * @param propName 配置
     * @param value 值
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals("registDate", propName) || StringUtil.equals("exRightsDate", propName)
            || StringUtil.equals("arriveDate", propName) || StringUtil.equals("reInvesDate", propName)) {
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * 获取利息明细信息 <功能详细描述>
     *
     * @param pageParam 界面参数
     * @return 查询到的结果
     * @see
     * @since 1.0
     */
    public List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> pageParam) {
        pageParam.put("userName", LoginUserUtil.getUserName());
        List<FinAssetIntInfo> finAssetIntInfos = restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanCurrDuration/findAssetIntInfo", pageParam, FinAssetIntInfo.class);
        return finAssetIntInfos;
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
    public BaseTotalTradeInfoDto genFinProductTradeDto(TrdPlanCurrDuration001Dto entity) {
        BaseTotalTradeInfoDto result = dealEntityService.genBaseTotalTradeInfoDto(entity);
        return result;
    }

    @Override
    public void doBack(TrdPlanCurrDuration001Dto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getTotleTradeId());
    }

    @Override
    public void doRevoke(TrdPlanCurrDuration001Dto entity) {
        AllTradeCommonUtil.deletePosition(entity.getRevokeReason(), entity.getTotleTradeId());
    }
}
