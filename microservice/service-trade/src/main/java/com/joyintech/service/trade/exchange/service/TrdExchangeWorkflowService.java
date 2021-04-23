package com.joyintech.service.trade.exchange.service;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.exchange.dto.TrdExchangeEntrustDto;
import com.joyintech.service.trade.exchange.dto.TrdExchangeIntegratedEntrustDto;
import com.joyintech.service.trade.fund.dto.WorkFlowDto;
import com.joyintech.service.trade.fund.service.TrdFundInvestTradeService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 场内交易工作流
 *
 * @author swwan
 * @className TrdExchangeWorkflowService
 * @date 2021/2/24 10:38
 */
@Service
public class TrdExchangeWorkflowService extends BaseService {

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TrdExchangeWorkflowService.class);

    @Autowired
    private TrdFundInvestTradeService trdFundInvestTradeService;

    /**
     * 普通委托复核工作流
     */
    public boolean startReviewWorkflow(TrdExchangeEntrustDto dto) {

        logger.info("start exchange review workflow [{}]", JsonUtils.object2Json(dto));
        ProcessParameter<TrdExchangeEntrustDto> workFlowPatameter = getReviewWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), dto.getRoles()
                    , Objects.nonNull(dto.getOrderPlaceBean()) ? dto.getOrderPlaceBean().getUserId() : dto.getEntrustList().get(0).getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start exchange review workflow success.");
            return true;
        }
        catch (Exception e) {
            logger.warn("start exchange review workflow failed with error : ", e);
            return false;
        }
    }

    /**
     * 设置流程参数
     *
     * @param dto
     * @return
     */
    public ProcessParameter<TrdExchangeEntrustDto> getReviewWorkFlowPatameter(TrdExchangeEntrustDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");

        ProcessParameter<TrdExchangeEntrustDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeEntrustDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange002");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);

        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage002Service");
        workFlowParameter.setPageId("TrdExchange002");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易下单.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        if (Objects.nonNull(dto.getOrderPlaceBean())) {
            // 批量
            workFlowParameter.setBusinessCode(dto.getEventId());
            workFlowParameter.setComment(
                    entity -> (StringUtil.isNotEmpty(entity.getOrderPlaceBean().getStockCode()) ?
                            entity.getOrderPlaceBean().getStockCode()
                                    + GlobalConstant.VERTICAL_LINE + (entity.getOrderPlaceBean().getStockCode()) : "")
                            + GlobalConstant.SPLIT + "订单编号"
                            + GlobalConstant.OPEN_PARENTHESIS + entity.getEventId() + GlobalConstant.CLOSE_PARENTHESIS
                            + (StringUtil.isNotEmpty(entity.getOrderPlaceBean().getEntrustPrice()) ?
                            (GlobalConstant.SPLIT + "委托价格"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getOrderPlaceBean().getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getOrderPlaceBean().getEntrustVolume()) ?
                            (GlobalConstant.SPLIT + "委托数量"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getOrderPlaceBean().getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        }  else if (dto.getEntrustList().size() == 1) {
            // 非公平
            workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getId());
            workFlowParameter.setComment(
                    entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                            (entity.getEntrustList().get(0).getStockName()
                                    + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getId()) ?
                            (GlobalConstant.SPLIT + "委托编号"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                            (GlobalConstant.SPLIT + "订单编号"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustVolume()) ?
                            (GlobalConstant.SPLIT + "委托数量"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                            (GlobalConstant.SPLIT + "委托价格"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        } else {
            // 公平
            workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getParentOrderId());
            workFlowParameter.setComment(
                    entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                            (entity.getEntrustList().get(0).getStockName()
                                    + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                            (GlobalConstant.SPLIT + "订单编号"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                            + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                            (GlobalConstant.SPLIT + "委托价格"
                                    + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        }

        return processParameter;
    }

    /**
     * 综合委托复核工作流
     */
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public boolean startReviewWorkflow(TrdExchangeIntegratedEntrustDto dto) {
        logger.info("start exchange review workflow [{}]", JsonUtils.object2Json(dto));
        ProcessParameter<TrdExchangeIntegratedEntrustDto> workFlowPatameter = getReviewWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), dto.getRoles(), dto.getEntrustList().get(0).getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start exchange review workflow success.");
            return true;
        }
        catch (Exception e) {
            logger.error("start exchange review workflow failed with error : ",e);
            return false;
        }
    }

    public ProcessParameter<TrdExchangeIntegratedEntrustDto> getReviewWorkFlowPatameter(TrdExchangeIntegratedEntrustDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");

        ProcessParameter<TrdExchangeIntegratedEntrustDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeIntegratedEntrustDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange003");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);
        workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getId());
        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage003Service");
        workFlowParameter.setPageId("TrdExchange003");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易下单.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        workFlowParameter.setComment(
                entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                        (entity. getEntrustList().get(0).getStockName()
                                + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getId()) ?
                        (GlobalConstant.SPLIT + "委托编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                        (GlobalConstant.SPLIT + "订单编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustVolume()) ?
                        (GlobalConstant.SPLIT + "委托数量"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS): "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                        (GlobalConstant.SPLIT + "委托价格"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS): ""));
        return processParameter;
    }

    /**
     * 综合委托撤审工作流
     */
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public boolean startRevokeWorkflow(TrdExchangeIntegratedEntrustDto dto) {
        logger.info("start revoke workflow [{}]", JsonUtils.object2Json(dto));
        ProcessParameter<TrdExchangeIntegratedEntrustDto> workFlowPatameter = getRevokeWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), dto.getRoles(), dto.getEntrustList().get(0).getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start revoke workflow success.");
            return true;
        } catch (Exception e) {
            logger.error("start revoke workflow failed with error : [{}]", e.getMessage());
            return false;
        }
    }

    /**
     * 设置流程参数
     *
     * @param dto
     * @return
     */
    private ProcessParameter<TrdExchangeIntegratedEntrustDto> getRevokeWorkFlowPatameter(TrdExchangeIntegratedEntrustDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");
        ProcessParameter<TrdExchangeIntegratedEntrustDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeIntegratedEntrustDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange005");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);
        workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getId());
        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage005Service");
        workFlowParameter.setPageId("TrdExchange005");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易撤销.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        workFlowParameter.setComment(
                entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                        (entity.getEntrustList().get(0).getStockName()
                                + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getId()) ?
                        (GlobalConstant.SPLIT + "委托编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getId() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                        (GlobalConstant.SPLIT + "订单编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustVolume()) ?
                        (GlobalConstant.SPLIT + "委托数量"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                        (GlobalConstant.SPLIT + "委托价格"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS) : ""));
        return processParameter;
    }

    /**
     * 普通委托复核工作流
     */
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public boolean startRevokeWorkflow(TrdExchangeEntrustDto dto) {
        logger.info("start revoke workflow [{}]", JsonUtils.object2Json(dto));
        ProcessParameter<TrdExchangeEntrustDto> workFlowPatameter = getRevokeWorkFlowPatameter(dto);
        try {
            WorkFlowUtil.startProcess(workFlowPatameter.getWorkFlowParameter(), dto.getUsers(), dto.getRoles(), dto.getEntrustList().get(0).getUserId(),
                    OperationType.NEW, true, dto);
            logger.info("start revoke workflow success.");
            return true;
        } catch (Exception e) {
            logger.error("start revoke workflow failed with error : [{}]", e.getMessage());
            return false;
        }
    }

    /**
     * 设置流程参数
     *
     * @param dto
     * @return
     */
    private ProcessParameter<TrdExchangeEntrustDto> getRevokeWorkFlowPatameter(TrdExchangeEntrustDto dto) {
        dto.setWorkflowId(null);
        dto.setOperateType("submit");
        ProcessParameter<TrdExchangeEntrustDto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TrdExchangeEntrustDto> workFlowParameter = new WorkFlowParameter<>();
        processParameter.setWorkFlowParameter(workFlowParameter);
        // 画面ID
        processParameter.setPageId("TrdExchange004");
        // 画面元素ID
        workFlowParameter.setElementId(dto.getElementId());
        processParameter.setOperationType(OperationType.NEW);
        workFlowParameter.setBusinessCode(dto.getEntrustList().get(0).getId());
        // 处理Service名
        workFlowParameter.setServiceName("TrdExchangePage004Service");
        workFlowParameter.setPageId("TrdExchange004");
        workFlowParameter.setBusinessType(DictCodeListExt3.共通业务类型_场内交易撤销.getValue());
        // 组合名称 资金账号 证券代码  证券名称  委托方向  委托价格 委托数量  委托金额  申报类型  清算速度  净价价格  全价价格 到期收益率  本方交易员 对手交易商 对手交易员  交易对手 约定号
        workFlowParameter.setComment(
                entity -> (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getStockCode()) ?
                        (entity.getEntrustList().get(0).getStockName()
                                + GlobalConstant.VERTICAL_LINE + entity.getEntrustList().get(0).getStockCode()) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getId()) ?
                        (GlobalConstant.SPLIT + "委托编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getId() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getParentOrderId()) ?
                        (GlobalConstant.SPLIT + "订单编号"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getParentOrderId() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustVolume()) ?
                        (GlobalConstant.SPLIT + "委托数量"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustVolume() + GlobalConstant.CLOSE_PARENTHESIS) : "")
                        + (StringUtil.isNotEmpty(entity.getEntrustList().get(0).getEntrustPrice()) ?
                        (GlobalConstant.SPLIT + "委托价格"
                                + GlobalConstant.OPEN_PARENTHESIS + entity.getEntrustList().get(0).getEntrustPrice() + GlobalConstant.CLOSE_PARENTHESIS) : ""));
        return processParameter;
    }

    // 流程作废
    @DTXTransaction(propagation = Propagation.REQUIRES_NEW)
    @Transactional(rollbackFor = {Exception.class})
    public boolean doRevoke(String entrustId) {
        logger.info("开始流程作废, 委托编号:[{}]", entrustId);

        boolean result = false;
        Map<String, Object> param = new HashMap<>();
        param.put("state", DictCodeList.流程状态_流程中.getValue());
        param.put("tradeId", entrustId);
        List<WorkFlowDto> workflowIds = trdFundInvestTradeService.selectTradeAfter(param);

        if (workflowIds != null && workflowIds.size() > 0) {
            for (WorkFlowDto flow : workflowIds) {
                try {
                    WorkFlowUtil.deleteProcess(flow.getSeqNo(), flow.getWorkflowId());
                    result = true;
                    logger.info("流程作废成功, 委托编号:[{}]", flow.getWorkflowId());
                } catch (WorkFlowException e) {
                    logger.error("流程作废异常, 委托编号:[{}] 异常信息: [{}]", flow.getWorkflowId(), e.getMessage());
                    return false;
                }
            }
        } else {
            logger.info("流程作废失败, 流程中无此委托:[{}]", entrustId);
        }
        return result;
    }
}
