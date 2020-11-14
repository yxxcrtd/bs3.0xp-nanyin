/*
 * 文件名：FinProductExpire002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：zhangliang 修改时间：2019年1月9日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productduration.expire.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.BussTrdPositionDto;
import com.joyintech.tams.invest.common.trade.service.TradeProductDealCommService;
import com.joyintech.tams.invest.product.common.service.ProductCommonService;
import com.joyintech.tams.invest.product.productduration.expire.dto.*;
import com.joyintech.tams.invest.product.productelement.build.dto.FinProductBuild002Dto;
import com.joyintech.tams.invest.product.productelement.build.service.FinProductBuild002Service;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 产品到期确认
 * 
 * @author zhangliang
 * @version 1.0 2019年1月9日
 * @see FinProductExpire002Service
 * @since 1.0
 */
@Service
public class FinProductExpire002Service extends BaseService<FinProductExpire002Dto> implements BaseProcessService<FinProductExpire002Dto> {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * {@linkplain}
     */
    @Autowired
    private BaseGetBusinessId baseGetBusinessId;

    /**
     * {@linkplain}
     */
    @Autowired
    private ProductCommonService productCommonService;

    /**
     * {@linkplain}
     */
    @Autowired
    private MapperDelegateService bussCommonMapperDelegateService;
    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private TradeProductDealCommService baseTradeProductDealCommService;
    /**
     * 平台日基础组件
     */
    @Autowired(required = false)
    private IPlatformDate platformDate;

    @Autowired
    private FinProductBuild002Service finProductBuild002Service;
    
    @Autowired
    private TrdAsynHandleStatusMapper trdAsynHandleStatusMapper;

    /**
     * {@inheritDoc} ---丁俊峰放开注释
     */
    @Override
    public void doInsert(FinProductExpire002Dto entity, OperationType type) {
        entity.setPs(entity.getDealType());
        if (!type.equals(OperationType.REVOKE) && !type.equals(OperationType.DELETE)) {
            this.insertBussFlowData(entity);
        }
        else {
            this.doInsertRevoke(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(FinProductExpire002Dto entity) {
        entity.setPs(entity.getDealType());
        if (!StringUtil.equals("revoke", entity.getOperateType()) && !StringUtil.equals("delete", entity.getOperateType())) {
            // 先删除流程中数据
            this.doDelete(entity.getWorkflowId());
            // 2.0改造
            this.setFinProductExpireBaseTrdDto(entity);
            // genDataForInsertDto(entity);
            ProcessMark newEntity;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(FinProductExpire002Dto.class, true);
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
            entity.setOperateType(OperationType.MODIFY.toString());
            // 重新insert
            this.insertBussFlowData(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doDelete(String workflowId) {
        baseTradeProductDealCommService.delete(workflowId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(FinProductExpire002Dto entity, OperationType type) {
        entity.setPs(getPs(entity));
        if (type == OperationType.NEW) {
            this.doCompleteInsert(entity);
        }
        else if (type == OperationType.REVOKE || type == OperationType.DELETE) {
            this.doCompleteRevoke(entity);
        }
        // 删除工作流表
        this.doDelete(entity.getWorkflowId());
    }

    /**
     * 生成产品赎回到期数据 <功能详细描述>
     *
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    public FinProductExpire002Dto setFinProductExpireBaseTrdDto(FinProductExpire002Dto entity) {
        FinProductExpireTrdDto finProductExpireTrdDto = new FinProductExpireTrdDto();
        finProductExpireTrdDto.setPtlTradeList(new ArrayList<>());
        finProductExpireTrdDto.setTrdProductDealList(new ArrayList<>());
        finProductExpireTrdDto.setPaTrdProductDealList(new ArrayList<>());
        finProductExpireTrdDto.setTrdProductDealAddList(new ArrayList<>());
        entity.setPs(getPs(entity));
        this.prepareData(entity);
        // 生成划款指令信息
        finProductExpireTrdDto.setStlPayOrdersDtoList(this.genStlPayOrdersDto(entity));
        // 生成金融产品交易汇总
        finProductExpireTrdDto.setTrdFinProductDealTotal(this.genTrdFinProductDealTotal(entity));
        // 生成金融产品交易汇总扩展
        finProductExpireTrdDto.setPaTrdFinProductDealTotal(this.genPaTrdFinProductDealTotal(entity));

        // 生成组合交易表(PTL_TRADE)
        finProductExpireTrdDto.getPtlTradeList().add(this.genPtlTrade(entity));

        if (!StringUtil.isEmptyList(entity.getFinProductExpireMInfoDtoList())) {
            for (ReduceInfoDto reduceInfoDto : entity.getFinProductExpireMInfoDtoList()) {
                // 生成金融产品交易(TRD_PRODUCT_DEAL) 本金
                TrdProductDeal trdProductDealAmt = this.genTrdProductDealAmt(entity, reduceInfoDto);
                finProductExpireTrdDto.getTrdProductDealList().add(trdProductDealAmt);

                // 生成金融产品交易扩展(pa_trd_product_deal)（本金）
                PaTrdProductDeal paTrdProductDealAmt = this.genPaTrdProductDealAmt(reduceInfoDto, trdProductDealAmt);
                finProductExpireTrdDto.getPaTrdProductDealList().add(paTrdProductDealAmt);

                // 生成金融产品交易附属表(TRD_PRODUCT_DEAL_ADD) 本金
                TrdProductDealAdd trdProductDealAddAmt = this.genTrdProductDealAdd(entity, trdProductDealAmt);
                finProductExpireTrdDto.getTrdProductDealAddList().add(trdProductDealAddAmt);
                if (reduceInfoDto.getActInterest() != null && reduceInfoDto.getActInterest().compareTo(BigDecimal.ZERO) != 0) {

                    // 生成金融产品交易(TRD_PRODUCT_DEAL) 利息
                    TrdProductDeal trdProductDealInt = this.genTrdProductDealInt(entity, reduceInfoDto);
                    finProductExpireTrdDto.getTrdProductDealList().add(trdProductDealInt);

                    // 生成金融产品交易扩展(pa_trd_product_deal)（利息）
                    PaTrdProductDeal paTrdProductDealInt = this.genPaTrdProductDealInt(reduceInfoDto, trdProductDealInt);
                    finProductExpireTrdDto.getPaTrdProductDealList().add(paTrdProductDealInt);

                    // 生成金融产品交易附属表(TRD_PRODUCT_DEAL_ADD) 利息
                    TrdProductDealAdd trdProductDealAddInt = this.genTrdProductDealAdd(entity, trdProductDealInt);
                    finProductExpireTrdDto.getTrdProductDealAddList().add(trdProductDealAddInt);
                }
            }
        }
        entity.setFinProductExpireTrdDto(finProductExpireTrdDto);
        return entity;
    }

    /**
     * 提交时登录业务流程表
     *
     * @param entity 画面参数dto
     * @see
     * @since 1.0
     */
    private void insertBussFlowData(FinProductExpire002Dto entity) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }

        FinProductExpireTrdDto finProductExpireTrdDto = entity.getFinProductExpireTrdDto();

        // 生成划款指令信息
        List<StlPayOrdersDto> stlPayOrdersDtoList = finProductExpireTrdDto.getStlPayOrdersDtoList();
        if (stlPayOrdersDtoList != null && !stlPayOrdersDtoList.isEmpty()) {
            WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
            BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, stlPayOrdersDtoList.get(0));
            wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
            wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
            wflStlPayOrdersMapper.insert(wflStlPayOrders);
        }

        // 生成金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = finProductExpireTrdDto.getTrdFinProductDealTotal();
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, trdFinProductDealTotal);
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        wflTrdFinProductDealTotalMapper.insert(wflTrdFinProductDealTotal);

        // 生成金融产品交易汇总扩展(pa_trd_fin_product_deal_total)
        PaTrdFinProductDealTotal patrdFinProductDealTotal = finProductExpireTrdDto.getPaTrdFinProductDealTotal();
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = new WflPaTrdFinDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflPaTrdFinDealTotal, patrdFinProductDealTotal);
        wflPaTrdFinDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflPaTrdFinDealTotal.setWorkflowId(entity.getWorkflowId());
        wflPaTrdFinDealTotalMapper.insert(wflPaTrdFinDealTotal);

        List<TrdProductDeal> trdProductDeals = finProductExpireTrdDto.getTrdProductDealList();
        // 生成金融产品交易(TRD_PRODUCT_DEAL)
        if (!StringUtil.isEmptyList(trdProductDeals)) {
            trdProductDeals.forEach(trdProductDeal -> {
                WflTrdProductDeal WflTrdProductDealAmt = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(WflTrdProductDealAmt, trdProductDeal);
                WflTrdProductDealAmt.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                WflTrdProductDealAmt.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDealMapper.insertSelective(WflTrdProductDealAmt);
            });
        }
        // 生成金融产品交易扩展(pa_trd_product_deal)
        if (!StringUtil.isEmptyList(finProductExpireTrdDto.getPaTrdProductDealList())) {
            finProductExpireTrdDto.getPaTrdProductDealList().forEach(paTrdProductDeal -> {
                WflPaTrdProductDeal wflPaTrdProductDealAmt = new WflPaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDealAmt, paTrdProductDeal);
                wflPaTrdProductDealAmt.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflPaTrdProductDealAmt.setWorkflowId(entity.getWorkflowId());
                wflPaTrdProductDealMapper.insertSelective(wflPaTrdProductDealAmt);
            });
        }
        // 生成金融产品交易附属表
        if (!StringUtil.isEmptyList(finProductExpireTrdDto.getTrdProductDealAddList())) {
            finProductExpireTrdDto.getTrdProductDealAddList().forEach(trdProductDealAdd -> {
                WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, trdProductDealAdd);
                wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdProductDealAdd.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDealAddMapper.insertSelective(wflTrdProductDealAdd);
            });
        }

        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
        bussTrdPositionDto.setPortfolioId(entity.getPtlId());
        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        bussTrdPositionDto.setTrdProductDealList(trdProductDeals);
        bussTrdPositionDto.setStlPayOrders(stlPayOrdersDtoList);
        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);
    }

    /**
     * 撤销流程表登录
     * 
     * @param entity 产品到期确认Dto
     * @see
     * @since 1.0
     */
    private void doInsertRevoke(FinProductExpire002Dto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(entity.getTradeId());
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = new WflTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(wflTrdFinProductDealTotal, trdFinProductDealTotal);
        wflTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        wflTrdFinProductDealTotal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
        wflTrdFinProductDealTotalMapper.insert(wflTrdFinProductDealTotal);

        // 根据汇总交易编号查询划款指令
        List<StlPayOrdersDto> stlPayOrdersList = productCommonService.genStlPayOrdersRevoke(entity.getTradeId());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            for (StlPayOrders stlPayOrders : stlPayOrdersList) {
                WflStlPayOrders wflStlPayOrders = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(wflStlPayOrders, stlPayOrders);
                wflStlPayOrders.setWorkflowId(entity.getWorkflowId());
                wflStlPayOrders.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflStlPayOrdersMapper.insertSelective(wflStlPayOrders);
            }
        }

        // 根据汇总交易编号查询交易信息
        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(entity.getTradeId(),
            entity.getRevokeReason());
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal trdProductDeal : trdProductDealList) {
                WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, trdProductDeal);
                wflTrdProductDeal.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdProductDealMapper.insertSelective(wflTrdProductDeal);
            }
        }

        // 根据汇总交易编号查询交易附属信息
        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.queryTrdProductDealAddByTotleTradeId(
            entity.getTradeId());
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            for (TrdProductDealAdd trdProductDealAdd : trdProductDealAddList) {
                WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
                BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, trdProductDealAdd);
                wflTrdProductDealAdd.setWorkflowId(entity.getWorkflowId());
                wflTrdProductDealAdd.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflTrdProductDealAdd.setUpdateUser(entity.getUpdateUser());
                wflTrdProductDealAdd.setCreateUser(entity.getCreateUser());
                wflTrdProductDealAdd.setCreateDept(entity.getCreateDept());
                wflTrdProductDealAdd.setUpdateTime(entity.getUpdateTime());
                wflTrdProductDealAdd.setCreateTime(entity.getCreateTime());
                wflTrdProductDealAddMapper.insertSelective(wflTrdProductDealAdd);
            }
        }

        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = productCommonService.queryPaTrdProductDealByTotleTradeId(
            entity.getTradeId());
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            for (PaTrdProductDeal paTrdProductDeal : paTrdProductDealList) {
                WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);
                wflPaTrdProductDeal.setWorkflowId(entity.getWorkflowId());
                wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
                wflPaTrdProductDeal.setUpdateUser(entity.getUpdateUser());
                wflPaTrdProductDeal.setCreateUser(entity.getCreateUser());
                wflPaTrdProductDeal.setCreateDept(entity.getCreateDept());
                wflPaTrdProductDeal.setUpdateTime(entity.getUpdateTime());
                wflPaTrdProductDeal.setCreateTime(entity.getCreateTime());
                wflPaTrdProductDealMapper.insertSelective(wflPaTrdProductDeal);
            }
        }
    }

    /**
     * 撤销插入共通逻辑
     * 
     * @param finProductExpireBaseTrdDto 产品到期交易共通
     * @param workflowId 工作流代码
     * @see
     * @since 1.0
     */
    /*
     * private void insertWflDealCommon(FinProductExpireBaseTrdDto finProductExpireBaseTrdDto, String workflowId) { if
     * (!StringUtil.isEmptyList(finProductExpireBaseTrdDto.getTrdProductDealList())) { for (TrdProductDeal trdProductDeal :
     * finProductExpireBaseTrdDto.getTrdProductDealList()) { WflTrdProductDeal wflTrdProductDeal = new WflTrdProductDeal();
     * BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDeal, trdProductDeal); wflTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue());
     * wflTrdProductDeal.setWorkflowId(workflowId); bussCommonMapperDelegateService.insert(WflTrdProductDealMapper.class, wflTrdProductDeal); } } if
     * (!StringUtil.isEmptyList(finProductExpireBaseTrdDto.getTrdProductDealAddList())) { for (TrdProductDealAdd trdProductDealAdd :
     * finProductExpireBaseTrdDto.getTrdProductDealAddList()) { WflTrdProductDealAdd wflTrdProductDealAdd = new WflTrdProductDealAdd();
     * BeanUtil.copyPropertiesIgnoreNull(wflTrdProductDealAdd, trdProductDealAdd); wflTrdProductDealAdd.setWorkflowStatus(
     * DictCodeListExt1.交易流程状态_流程中.getValue()); wflTrdProductDealAdd.setWorkflowId(workflowId);
     * bussCommonMapperDelegateService.insert(WflTrdProductDealAddMapper.class, wflTrdProductDealAdd); } } if
     * (!StringUtil.isEmptyList(finProductExpireBaseTrdDto.getPaTrdProductDealList())) { for (PaTrdProductDeal paTrdProductDeal :
     * finProductExpireBaseTrdDto.getPaTrdProductDealList()) { WflPaTrdProductDeal wflPaTrdProductDeal = new WflPaTrdProductDeal();
     * BeanUtil.copyPropertiesIgnoreNull(wflPaTrdProductDeal, paTrdProductDeal);
     * wflPaTrdProductDeal.setWorkflowStatus(DictCodeListExt1.交易流程状态_流程中.getValue()); wflPaTrdProductDeal.setWorkflowId(workflowId);
     * bussCommonMapperDelegateService.insert(WflPaTrdProductDealMapper.class, wflPaTrdProductDeal); } } }
     */

    @Autowired
    private FinCashInfoMapper finCashInfoMapper;

    @Autowired
    private FinCashPayRuleMapper finCashPayRuleMapper;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private PaTrdProductDealMapper paTrdProductDealMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdProductDealAddMapper trdProductDealAddMapper;

    @Autowired
    private PtlTradeMapper ptlTradeMapper;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    @Autowired
    private TrdTradeFeeDetailMapper trdTradeFeeDetailMapper;

    @Autowired
    private PaTrdFinProductDealTotalMapper paTrdFinProductDealTotalMapper;

    @Autowired
    private PrdSaleParamMapper prdSaleParamMapper;

    @Autowired
    private FinProductAddMapper finProductAddMapper;

    @Autowired
    private WflStlPayOrdersMapper wflStlPayOrdersMapper;

    @Autowired
    private WflTrdFinProductDealTotalMapper wflTrdFinProductDealTotalMapper;

    @Autowired
    private WflPaTrdFinDealTotalMapper wflPaTrdFinDealTotalMapper;

    @Autowired
    private WflPaTrdProductDealMapper wflPaTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealMapper wflTrdProductDealMapper;

    @Autowired
    private WflTrdProductDealAddMapper wflTrdProductDealAddMapper;

    /**
     * 撤销doComplete
     *
     * @param entity 参数
     * @see
     * @since 1.0
     */
    private void doCompleteRevoke(FinProductExpire002Dto entity) {
        // 根据汇总交易编号查询金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = productCommonService.genTrdFinProductDealTotalRevoke(entity.getBusiNo());
        if (trdFinProductDealTotal != null) {
            trdFinProductDealTotalMapper.insert(trdFinProductDealTotal);
        }

        // 根据汇总交易编号查询金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = productCommonService.genPaTrdFinProductDealTotalRevoke(
            entity.getBusiNo());
        if (paTrdFinProductDealTotal != null) {
            paTrdFinProductDealTotalMapper.insert(paTrdFinProductDealTotal);
        }

        // 根据汇总交易编号查询划款指令
        List<StlPayOrdersDto> stlPayOrdersList = productCommonService.genStlPayOrdersRevoke(entity.getBusiNo());
        if (!StringUtil.isEmptyList(stlPayOrdersList)) {
            stlPayOrdersList.forEach(stlPayOrdersDto -> stlPayOrdersMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询交易信息
        List<TrdProductDeal> trdProductDealList = productCommonService.genTrdProductDealRevoke(entity.getBusiNo(),
            entity.getRevokeReason());
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            trdProductDealList.forEach(stlPayOrdersDto -> trdProductDealMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询交易附属信息
        List<TrdProductDealAdd> trdProductDealAddList = productCommonService.genTrdProductDealAddRevoke(entity.getBusiNo());
        if (!StringUtil.isEmptyList(trdProductDealAddList)) {
            trdProductDealAddList.forEach(stlPayOrdersDto -> trdProductDealAddMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询金融产品交易扩展
        List<PaTrdProductDeal> paTrdProductDealList = productCommonService.genPaTrdProductDealRevoke(entity.getBusiNo());
        if (!StringUtil.isEmptyList(paTrdProductDealList)) {
            paTrdProductDealList.forEach(stlPayOrdersDto -> paTrdProductDealMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询组合交易信息
        List<PtlTrade> ptlTradeList = productCommonService.genPtlTradeRevoke(entity.getBusiNo());
        if (!StringUtil.isEmptyList(ptlTradeList)) {
            ptlTradeList.forEach(stlPayOrdersDto -> ptlTradeMapper.insertSelective(stlPayOrdersDto));
        }

        // 根据汇总交易编号查询理财分行销售明细
        /*
         * List<PaBranchSaleDetail> paBranchSaleDetailList = productCommonService.genPaBranchSaleDetailRevoke(entity.getTradeId()); if
         * (!StringUtil.isEmptyList(paBranchSaleDetailList)) { bussCommonMapperDelegateService.insertAutoClassifyBatch(paBranchSaleDetailList); }
         */

        // 原始汇总交易的状态改为已撤销
        TrdFinProductDealTotal trdFinProductDealTotalRevoke = new TrdFinProductDealTotal();
        trdFinProductDealTotalRevoke.setTotleTradeId(entity.getTradeId());
        trdFinProductDealTotalRevoke.setIsCancel(DictCodeList.是否_是.getValue());
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotalRevoke);

        // 原始交易的状态改为已撤销
        productCommonService.updateTrdProductDealByTotleTradeId(entity.getTradeId());

        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
        //撤销时若ptlId中没有值，则从指令中获取
        if(StringUtil.isNotEmpty(entity.getPtlId())){
            bussTrdPositionDto.setPortfolioId(entity.getPtlId());
        }
        else if(!StringUtil.isEmptyList(stlPayOrdersList)){
            bussTrdPositionDto.setPortfolioId(stlPayOrdersList.get(0).getPortfolioId());
        }
        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_撤销.getValue());
        bussTrdPositionDto.setTrdProductDealList(trdProductDealList);
        bussTrdPositionDto.setStlPayOrders(stlPayOrdersList);
        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);

        // // 组合现金流水Entity
        // List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<PtlCashPositionFlowDto>();
        // if (!StringUtil.isEmptyList(stlPayOrdersList)) {
        // for (StlPayOrders stlPayOrdersDto : stlPayOrdersList) {
        // PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
        // ptlCashPositionFlowDto.setPortfolioId(entity.getPtlId());
        // ptlCashPositionFlowDto.setAcctId(stlPayOrdersDto.getMyAcctNo());
        // ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        // ptlCashPositionFlowDto.setCdate(entity.getPayDate());
        // ptlCashPositionFlowDto.setCcy(entity.getCcy());
        // ptlCashPositionFlowDto.setAmount(stlPayOrdersDto.getSettleAmt());
        // ptlCashPositionFlowDto.setOrderId(stlPayOrdersDto.getOrderId());
        // ptlCashPositionFlows.add(ptlCashPositionFlowDto);
        // }
        // }
        // // 组合持仓流水Entity
        // List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<PtlSecPositionFlowDto>();
        // if (!StringUtil.isEmptyList(entity.getFinProductExpireMInfoDtoList())) {
        // for (ReduceInfoDto finProductExpireMInfoDto : entity.getFinProductExpireMInfoDtoList()) {
        // PtlSecPositionFlowDto ptlSecPositionFlowDto = new PtlSecPositionFlowDto();
        // ptlSecPositionFlowDto.setPortfolioId(entity.getPtlId());
        // ptlSecPositionFlowDto.setFinprodId(
        // finProductExpireMInfoDto.getFinprodId() != null ? finProductExpireMInfoDto.getFinprodId() : entity.getFinprodId());
        // ptlSecPositionFlowDto.setBranch(Long.parseLong(entity.getBranch()));
        // ptlSecPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
        // ptlSecPositionFlowDto.setCdate(entity.getPayDate());
        // ptlSecPositionFlowDto.setCcy(entity.getCcy());
        //// ptlSecPositionFlowDto.setAmount(finProductExpireMInfoDto.getTdyPrinAmt());
        // ptlSecPositionFlowDto.setAmount(entity.getmCusRevAmt());
        // ptlSecPositionFlowDto.setpFinprodId(
        // finProductExpireMInfoDto.getFinprodId() != null ? entity.getFinprodId() : null);
        // ptlSecPositionFlowDto.setTradeId(entity.getTradeId());
        // ptlSecPositionFlowDto.setSecAcctId("999999");
        // ptlSecPositionFlowDto.setInvAim("999999");
        // ptlSecPositionFlows.add(ptlSecPositionFlowDto);
        // }
        // }
        //
        // PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        // ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        // ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        // restTemplate.postForObject(
        // ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
        // ptlPositionHoldDto, Boolean.class);

        // 一体化对应etliu start
        // 所有交易生效后调用的共通方法
        if (!StringUtil.isEmptyList(trdProductDealList)) {
            for (TrdProductDeal tradeDto : trdProductDealList) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getFinprodId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                if (tradeDto.getPs().equals(DictCodeList.交易方向_提前终止.getValue())) {
                    tradeDto.setPs(DictCodeList.交易方向_产品到期.getValue());
                }
                inputDto.setTrdProductDeal(tradeDto);
                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            }
        }
        // 一体化对应etliu end

        // add by zhangJin 邮储项目个性化-TA交易发送 start
        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            if (!StringUtil.isEmptyList(trdProductDealList)) {
                BigDecimal tradeAmt = BigDecimal.ZERO;
                BigDecimal shareAmt = BigDecimal.ZERO;
                BigDecimal feeAmt = BigDecimal.ZERO;
                for (TrdProductDeal temp : trdProductDealList) {
                    tradeAmt = ArithmeticUtil.doAdd(tradeAmt, temp.getTradeAmt());
                    shareAmt = ArithmeticUtil.doAdd(shareAmt, temp.getShareAmt());
                    feeAmt = ArithmeticUtil.doAdd(feeAmt, temp.getFeeAmt());
                }
                TrdProductDeal deal = new TrdProductDeal();
                deal.setFinprodId(entity.getFinprodId());
                deal.setTradeAmt(tradeAmt);
                deal.setSettleDate(trdProductDealList.get(0).getSettleDate());
                deal.setPs(entity.getPs());
                deal.setFeeAmt(feeAmt);
                deal.setShareAmt(shareAmt);
                deal.setTradeId(entity.getTradeId());
                deal.setStatus("3");
                restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/sendProdTADeal", deal,
                    String.class);
            }
        }
        // add by zhangJin 邮储项目个性化-TA交易发送 end
        
        //20191008新增 	
        if (trdFinProductDealTotal != null) {
        	TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
            trdAsynHandleStatus.setTradeId(trdFinProductDealTotal.getTotleTradeId());		//交易汇总id
            trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
            trdAsynHandleStatus.setHandleStatus("01");		//01
            trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
        }
    }

    /**
     * 数据处理
     * 
     * @param entity 产品到期确认Dto
     * @see
     * @since 1.0
     */
    private void prepareData(FinProductExpire002Dto entity) {
        if (!StringUtil.isEmptyList(entity.getFinProductExpireFeeInfoDtoList())) {
            BigDecimal totalFeeAmt = BigDecimal.ZERO;
            for (FinProductExpireFeeInfoDto finProductExpireFeeInfoDto : entity.getFinProductExpireFeeInfoDtoList()) {
                totalFeeAmt = ArithmeticUtil.doAdd(totalFeeAmt, finProductExpireFeeInfoDto.getActFeeAmt());
            }
            entity.setTotalFeeAmt(totalFeeAmt);
        }

        if (!StringUtil.isEmptyList(entity.getFinProductExpireBranchInfoDtoList())) {
            String branch = "";
            for (FinProductExpireBranchInfoDto finProductExpireBranchInfoDto : entity.getFinProductExpireBranchInfoDtoList()) {
                branch = branch + finProductExpireBranchInfoDto.getBranchCode() + GlobalConstant.COMMA;
            }
            if (branch.length() > 0) {
                entity.setBranchInfo(branch.substring(0, branch.length() - 1));
            }
        }
    }

    /**
     * 到期确认doComplete
     *
     * @param entity 产品到期确认Dto
     * @see
     * @since 1.0
     */
    private void doCompleteInsert(FinProductExpire002Dto entity) {
        // 数据处理
        // this.prepareData(entity);

        FinProductExpireTrdDto finProductExpireTrdDto = entity.getFinProductExpireTrdDto();

        // 生成划款指令信息
        List<StlPayOrdersDto> stlPayOrdersDtoList = finProductExpireTrdDto.getStlPayOrdersDtoList();
        // 生成金融产品交易汇总
        TrdFinProductDealTotal trdFinProductDealTotal = finProductExpireTrdDto.getTrdFinProductDealTotal();
        // 生成金融产品交易汇总扩展
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = finProductExpireTrdDto.getPaTrdFinProductDealTotal();

        // 生成组合交易表(PTL_TRADE)
        PtlTrade ptlTrade = this.genPtlTrade(entity);

        // 金融产品交易表
        List<TrdProductDeal> trdProductDeals = finProductExpireTrdDto.getTrdProductDealList();
        // 金融产品附属表
        List<PaTrdProductDeal> paTrdProductDeals = finProductExpireTrdDto.getPaTrdProductDealList();
        // 金融产品附加表
        List<TrdProductDealAdd> trdProductDealAdds = finProductExpireTrdDto.getTrdProductDealAddList();

        trdFinProductDealTotalMapper.insertSelective(trdFinProductDealTotal);
        paTrdFinProductDealTotalMapper.insertSelective(paTrdFinProductDealTotal);
        ptlTradeMapper.insertSelective(ptlTrade);
        trdProductDeals.forEach(trdProductDeal -> trdProductDealMapper.insertSelective(trdProductDeal));
        paTrdProductDeals.forEach(trdProductDeal -> paTrdProductDealMapper.insertSelective(trdProductDeal));
        trdProductDealAdds.forEach(trdProductDeal -> trdProductDealAddMapper.insertSelective(trdProductDeal));
        stlPayOrdersDtoList.forEach(trdProductDeal -> stlPayOrdersMapper.insertSelective(trdProductDeal));

        BussTrdPositionDto bussTrdPositionDto = new BussTrdPositionDto();
        bussTrdPositionDto.setPortfolioId(entity.getPtlId());
        bussTrdPositionDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());
        bussTrdPositionDto.setTrdProductDealList(trdProductDeals);
        bussTrdPositionDto.setStlPayOrders(stlPayOrdersDtoList);
        baseTradeProductDealCommService.trdProductInsertPosition(bussTrdPositionDto);

        if (!StringUtil.isEmptyList(trdProductDeals)) {
            for (TrdProductDeal tradeDto : trdProductDeals) {
                AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
                inputDto.setProdid(entity.getFinprodId());
                inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
                if (tradeDto.getPs().equals(DictCodeList.交易方向_提前终止.getValue())) {
                    tradeDto.setPs(DictCodeList.交易方向_产品到期.getValue());
                }
                inputDto.setTrdProductDeal(tradeDto);
                inputDto.setProdVdate(entity.getVdate());

                AllTradeCommonUtil.handleAfterTradeComplete(inputDto);
            }
        }

        if ("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))) {
            // 调用个性化接口- 资金到账标识接口 lijun add by 2019-08-12
            restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendFundToTAccount",
                trdFinProductDealTotal, String.class);

            // add by zhangjin 邮储项目个性化-TA交易发送 start
            if (!StringUtil.isEmptyList(trdProductDeals)) {
                BigDecimal tradeAmt = BigDecimal.ZERO;
                BigDecimal shareAmt = BigDecimal.ZERO;
                BigDecimal feeAmt = BigDecimal.ZERO;
                for (TrdProductDeal temp : trdProductDeals) {
                    tradeAmt = ArithmeticUtil.doAdd(tradeAmt, temp.getTradeAmt());
                    shareAmt = ArithmeticUtil.doAdd(shareAmt, temp.getShareAmt());
                    feeAmt = ArithmeticUtil.doAdd(feeAmt, temp.getFeeAmt());
                }
                TrdProductDeal deal = new TrdProductDeal();
                deal.setFinprodId(entity.getFinprodId());
                deal.setTradeAmt(tradeAmt);
                deal.setSettleDate(trdProductDeals.get(0).getSettleDate());
                deal.setPs(entity.getPs());
                deal.setFeeAmt(feeAmt);
                deal.setShareAmt(shareAmt);
                deal.setTradeId(entity.getTradeId());
                deal.setStatus("0");
                restTemplate.postForObject(ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/custodian/sendProdTADeal", deal,
                    String.class);
            }
            // add by zhangjin 邮储项目个性化-TA交易发送 end
        }
        //20191008新增 	
        TrdAsynHandleStatus trdAsynHandleStatus = new TrdAsynHandleStatus();
        trdAsynHandleStatus.setTradeId(trdFinProductDealTotal.getTotleTradeId());		//交易汇总id
        trdAsynHandleStatus.setTrdTableType("01");		// 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
        trdAsynHandleStatus.setHandleStatus("01");		//01	待处理
        trdAsynHandleStatusMapper.insertSelective(trdAsynHandleStatus);
    }

    /**
     * 金融产品交易共通业务逻辑处理
     * 
     * @param entity 产品成立确认
     * @param totleTradeId 汇总交易编号
     * @param finprodId 金融产品代码
     * @param mCusRevAmt 交易金额
     * @return 金融产品交易信息实体
     * @see
     * @since 1.0
     */
    private BaseTradeProductDealDto insertBaseTradeProductDealComm(FinProductExpire002Dto entity, String totleTradeId,
                                                                   String finprodId, BigDecimal mCusRevAmt) {
        // 金融产品交易Entity
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
            trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            trdProductDeal.setTradeId(IDGenerator.generate());
        }
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
        trdProductDeal.setFinprodId(finprodId);
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (this.isTrdFinProduct(entity.getDealMode())) {
            trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
        }
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_产品到期.getValue());
        trdProductDeal.setTradeDate(entity.getPayDate());
        trdProductDeal.setVdate(entity.getVdate());
        trdProductDeal.setSettleDate(entity.getPayDate());
        trdProductDeal.setMdate(entity.getMdate());
        trdProductDeal.setShareAmt(mCusRevAmt);
        trdProductDeal.setPrinAmt(mCusRevAmt);
        trdProductDeal.setFpriceAmt(ArithmeticUtil.doAdd(mCusRevAmt, entity.getActCusProfit()));
        trdProductDeal.setTradeAmt(ArithmeticUtil.doAdd(mCusRevAmt, entity.getActCusProfit()));
        trdProductDeal.setTotleTradeId(totleTradeId);

        // 一体化对应etliu start
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        // 一体化对应etliu end

        // 金融产品交易附属表Entity
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
        trdProductDealAdd.setPortfolioId(entity.getPtlId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDto.setTrdProductDeal(trdProductDeal); // 金融产品交易
        baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd); // 金融产品交易附属表

        return baseTradeProductDealDto;
    }

    /**
     * 根据处理模式判断是否生成交易类金融产品
     * 
     * @param dealMode 处理模式
     * @return 是否生成交易类金融产品
     * @see
     * @since 1.0
     */
    private boolean isTrdFinProduct(String dealMode) {
        boolean flag = false;
        if (StringUtil.equals(dealMode, DictCodeList.处理模式_普通.getValue())
            || StringUtil.equals(dealMode, DictCodeList.处理模式_分档.getValue())
            || StringUtil.equals(dealMode, DictCodeList.处理模式_周期型.getValue())
            || StringUtil.equals(dealMode, DictCodeList.处理模式_分档周期型.getValue())) {
            flag = true;
        }
        return flag;
    }

    /**
     * 生成理财分行销售明细
     * 
     * @param entity 产品到期确认Dto
     * @return 理财分行销售明细
     * @see
     * @since 1.0
     */
    /*
     * private List<PaBranchSaleDetail> genPaBranchSaleDetailList(FinProductExpire002Dto entity) { List<PaBranchSaleDetail> paBranchSaleDetailList = new
     * ArrayList<PaBranchSaleDetail>(); if (!StringUtil.isEmptyList(entity.getFinProductExpireBranchInfoDtoList())) { for (FinProductExpireBranchInfoDto
     * finProductExpireBranchInfoDto : entity.getFinProductExpireBranchInfoDtoList()) { PaBranchSaleDetail paBranchSaleDetail = new PaBranchSaleDetail();
     * paBranchSaleDetail.setTradeId(entity.getTradeId()); paBranchSaleDetail.setBranch(finProductExpireBranchInfoDto.getBranchCode());
     * paBranchSaleDetail.setCdate(entity.getPayDate()); paBranchSaleDetail.setPrinAmt(finProductExpireBranchInfoDto.getPrinAmt());
     * paBranchSaleDetail.setAccIntAmt(finProductExpireBranchInfoDto.getProfitAmt());
     * paBranchSaleDetail.setIntAmt(finProductExpireBranchInfoDto.getActProfitAmt());
     * paBranchSaleDetail.setAccSalefeeAmt(finProductExpireBranchInfoDto.getSaleFeeAmt());
     * paBranchSaleDetail.setSalefeeAmt(finProductExpireBranchInfoDto.getActSaleFeeAmt()); paBranchSaleDetail.setAccSalefee2Amt(
     * finProductExpireBranchInfoDto.getoSaleFeeAmt()); paBranchSaleDetail.setSalefee2Amt( finProductExpireBranchInfoDto.getActOSaleFeeAmt());
     *
     * paBranchSaleDetailList.add(paBranchSaleDetail); } }
     *
     * return paBranchSaleDetailList; }
     */

    /**
     * 生成金融产品交易（费用）
     * 
     * @param entity 产品到期确认Dto
     * @return 金融产品交易（费用）
     * @see
     * @since 1.0
     */
    /*
     * private FinProductExpireBaseTrdDto genTrdProductDealListFEE(FinProductExpire002Dto entity) { FinProductExpireBaseTrdDto finProductExpireBaseTrdDto
     * = new FinProductExpireBaseTrdDto(); List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>(); List<TrdProductDealAdd>
     * trdProductDealAddList = new ArrayList<TrdProductDealAdd>(); List<PaTrdProductDeal> paTrdProductDealList = new ArrayList<PaTrdProductDeal>();
     * List<PtlTrade> ptlTradeList = new ArrayList<PtlTrade>();
     *
     * if (!StringUtil.isEmptyList(entity.getFinProductExpireFeeInfoDtoList())) { for (FinProductExpireFeeInfoDto finProductExpireFeeInfoDto :
     * entity.getFinProductExpireFeeInfoDtoList()) {
     *
     * TrdProductDeal trdProductDeal = new TrdProductDeal(); if (baseGetBusinessId != null) { BussinessCodeParameter codeParameter = new
     * BussinessCodeParameter(); codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
     * trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter)); }
     * trdProductDeal.setBusiType(DictCodeList.共通业务类型_产品费用支付管理.getValue()); trdProductDeal.setFinprodId(entity.getFinprodId());
     * trdProductDeal.setFinprodType2(productCommonService.convertFeeTypeToCashType( finProductExpireFeeInfoDto.getFeeType()));
     * trdProductDeal.setBranch(Long.parseLong(entity.getBranch())); trdProductDeal.setTradeDate(entity.getPayDate());
     * trdProductDeal.setVdate(DateUtil.parse(finProductExpireFeeInfoDto.getStartDate()));
     * trdProductDeal.setMdate(DateUtil.parse(finProductExpireFeeInfoDto.getEndDate())); trdProductDeal.setSettleDate(entity.getPayDate());
     * trdProductDeal.setCcy(entity.getCcy()); trdProductDeal.setShareAmt(finProductExpireFeeInfoDto.getPrinamt());
     * trdProductDeal.setTradeAmt(finProductExpireFeeInfoDto.getActFeeAmt()); trdProductDeal.setPs(DictCodeList.交易方向_产品付费.getValue());
     * trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue()); trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
     * trdProductDeal.setIsClean(DictCodeList.是否_是.getValue()); trdProductDeal.setTotleTradeId(entity.getTradeId());
     * trdProductDeal.setRemark(finProductExpireFeeInfoDto.getRemark());
     *
     * TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd(); trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
     * trdProductDealAdd.setPortfolioId(entity.getPtlId()); trdProductDealAdd.setCustomCashType(DictCodeList.自定义现金流类型_费用.getValue());
     * trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
     *
     * PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal(); paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
     * paTrdProductDeal.setTheoryRate(finProductExpireFeeInfoDto.getFeeRate()); paTrdProductDeal.setActRate(finProductExpireFeeInfoDto.getActFeeRate());
     * paTrdProductDeal.setTheoryInt(finProductExpireFeeInfoDto.getFeeAmt()); paTrdProductDeal.setBasis(finProductExpireFeeInfoDto.getBasis());
     * paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品到期.getValue());
     *
     * PtlTrade ptlTrade = new PtlTrade(); ptlTrade.setPortfolioId(entity.getPtlId()); ptlTrade.setBusiNo(trdProductDeal.getTradeId());
     * ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); ptlTrade.setBusiType(DictCodeListExt1.平安业务类型_产品到期.getValue());
     * ptlTrade.setSettleDate(entity.getPayDate());
     *
     * trdProductDealList.add(trdProductDeal); trdProductDealAddList.add(trdProductDealAdd); paTrdProductDealList.add(paTrdProductDeal);
     * ptlTradeList.add(ptlTrade); } }
     *
     * finProductExpireBaseTrdDto.setTrdProductDealList(trdProductDealList); finProductExpireBaseTrdDto.setTrdProductDealAddList(trdProductDealAddList);
     * finProductExpireBaseTrdDto.setPaTrdProductDealList(paTrdProductDealList); finProductExpireBaseTrdDto.setPtlTradeList(ptlTradeList);
     *
     * return finProductExpireBaseTrdDto; }
     */

    /**
     * 生成金融产品交易（利息）
     * 
     * @param entity 产品到期确认Dto
     * @return 金融产品交易（利息）
     * @see
     * @since 1.0
     */
    /*
     * private FinProductExpireBaseTrdDto genTrdProductDealListINT(FinProductExpire002Dto entity) { FinProductExpireBaseTrdDto finProductExpireBaseTrdDto
     * = new FinProductExpireBaseTrdDto(); List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>(); List<TrdProductDealAdd>
     * trdProductDealAddList = new ArrayList<TrdProductDealAdd>(); List<PaTrdProductDeal> paTrdProductDealList = new ArrayList<PaTrdProductDeal>();
     * List<PtlTrade> ptlTradeList = new ArrayList<PtlTrade>();
     *
     * if (!StringUtil.isEmptyList(entity.getFinProductExpireMInfoDtoList())) { for (FinProductExpireMInfoDto finProductExpireMInfoDto :
     * entity.getFinProductExpireMInfoDtoList()) {
     *
     * TrdProductDeal trdProductDeal = new TrdProductDeal(); if (baseGetBusinessId != null) { BussinessCodeParameter codeParameter = new
     * BussinessCodeParameter(); codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
     * trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter)); }
     * trdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue()); trdProductDeal.setFinprodId( finProductExpireMInfoDto.getFinprodId() !=
     * null ? finProductExpireMInfoDto.getFinprodId() : entity.getFinprodId()); trdProductDeal.setBranch(Long.parseLong(entity.getBranch()));
     * trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue()); trdProductDeal.setTradeDate(entity.getPayDate());
     * trdProductDeal.setVdate(DateUtil.parse(finProductExpireMInfoDto.getStartDate()));
     * trdProductDeal.setMdate(DateUtil.parse(finProductExpireMInfoDto.getEndDate())); trdProductDeal.setSettleDate(entity.getPayDate());
     * trdProductDeal.setCcy(entity.getCcy()); trdProductDeal.setIntAmt(finProductExpireMInfoDto.getActIntAmt());
     * trdProductDeal.setFpriceAmt(finProductExpireMInfoDto.getActIntAmt()); trdProductDeal.setTradeAmt(finProductExpireMInfoDto.getActIntAmt());
     * trdProductDeal.setPs(DictCodeList.交易方向_赎回收益确认.getValue()); trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
     * trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue()); trdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
     * trdProductDeal.setTotleTradeId(entity.getTradeId());
     *
     * TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd(); trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
     * trdProductDealAdd.setPortfolioId(entity.getPtlId()); trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
     *
     * PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal(); paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
     * paTrdProductDeal.setTheoryRate(finProductExpireMInfoDto.getYield()); paTrdProductDeal.setActRate(finProductExpireMInfoDto.getActYield());
     * paTrdProductDeal.setTheoryInt(finProductExpireMInfoDto.getIntAmt()); paTrdProductDeal.setBasis(finProductExpireMInfoDto.getBasis());
     * paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_产品到期.getValue());
     *
     * PtlTrade ptlTrade = new PtlTrade(); ptlTrade.setPortfolioId(entity.getPtlId()); ptlTrade.setBusiNo(trdProductDeal.getTradeId());
     * ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); ptlTrade.setBusiType(DictCodeListExt1.平安业务类型_产品到期.getValue());
     * ptlTrade.setSettleDate(entity.getPayDate());
     *
     *
     * trdProductDealList.add(trdProductDeal); trdProductDealAddList.add(trdProductDealAdd); paTrdProductDealList.add(paTrdProductDeal);
     * ptlTradeList.add(ptlTrade); } }
     *
     * finProductExpireBaseTrdDto.setTrdProductDealList(trdProductDealList); finProductExpireBaseTrdDto.setTrdProductDealAddList(trdProductDealAddList);
     * finProductExpireBaseTrdDto.setPaTrdProductDealList(paTrdProductDealList); finProductExpireBaseTrdDto.setPtlTradeList(ptlTradeList);
     *
     * return finProductExpireBaseTrdDto; }
     */

    /**
     * 生成金融产品交易（本金）
     * 
     * @param entity 产品到期确认Dto
     * @return 金融产品交易（本金）
     * @see
     * @since 1.0
     */
    /*
     * private FinProductExpireBaseTrdDto genTrdProductDealListAMT(FinProductExpire002Dto entity) { FinProductExpireBaseTrdDto finProductExpireBaseTrdDto
     * = new FinProductExpireBaseTrdDto(); List<TrdProductDeal> trdProductDealList = new ArrayList<TrdProductDeal>(); List<TrdProductDealAdd>
     * trdProductDealAddList = new ArrayList<TrdProductDealAdd>(); List<PtlTrade> ptlTradeList = new ArrayList<PtlTrade>();
     *
     * if (!StringUtil.isEmptyList(entity.getFinProductExpireMInfoDtoList())) { for (FinProductExpireMInfoDto finProductExpireMInfoDto :
     * entity.getFinProductExpireMInfoDtoList()) {
     *
     * TrdProductDeal trdProductDeal = new TrdProductDeal(); if (baseGetBusinessId != null) { BussinessCodeParameter codeParameter = new
     * BussinessCodeParameter(); codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
     * trdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter)); }
     * trdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue()); trdProductDeal.setFinprodId( finProductExpireMInfoDto.getFinprodId() !=
     * null ? finProductExpireMInfoDto.getFinprodId() : entity.getFinprodId()); trdProductDeal.setBranch(Long.parseLong(entity.getBranch()));
     * trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue()); trdProductDeal.setTradeDate(entity.getPayDate());
     * trdProductDeal.setVdate(DateUtil.parse(finProductExpireMInfoDto.getStartDate()));
     * trdProductDeal.setMdate(DateUtil.parse(finProductExpireMInfoDto.getEndDate())); trdProductDeal.setSettleDate(entity.getPayDate());
     * trdProductDeal.setCcy(entity.getCcy()); trdProductDeal.setShareAmt(finProductExpireMInfoDto.getTdyPrinAmt());
     * trdProductDeal.setPrinAmt(finProductExpireMInfoDto.getTdyPrinAmt()); trdProductDeal.setFpriceAmt(finProductExpireMInfoDto.getTdyPrinAmt());
     * trdProductDeal.setTradeAmt(finProductExpireMInfoDto.getTdyPrinAmt()); if (DateUtil.parse(finProductExpireMInfoDto.getEndDate()).compareTo(
     * entity.getEndDate()) != 0) { trdProductDeal.setPs(DictCodeList.交易方向_产品赎回.getValue()); } else {
     * trdProductDeal.setPs(DictCodeList.交易方向_产品到期.getValue()); } trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
     * trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue()); trdProductDeal.setTotleTradeId(entity.getTradeId());
     *
     * TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd(); trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
     * trdProductDealAdd.setPortfolioId(entity.getPtlId()); trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
     *
     * PtlTrade ptlTrade = new PtlTrade(); ptlTrade.setPortfolioId(entity.getPtlId()); ptlTrade.setBusiNo(trdProductDeal.getTradeId());
     * ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue()); ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
     * ptlTrade.setSettleDate(entity.getPayDate());
     *
     * trdProductDealList.add(trdProductDeal); trdProductDealAddList.add(trdProductDealAdd); ptlTradeList.add(ptlTrade); } }
     *
     * finProductExpireBaseTrdDto.setTrdProductDealList(trdProductDealList); finProductExpireBaseTrdDto.setTrdProductDealAddList(trdProductDealAddList);
     * finProductExpireBaseTrdDto.setPtlTradeList(ptlTradeList);
     *
     * return finProductExpireBaseTrdDto; }
     */
    /**
     * 生成金融产品交易汇总
     * 
     * @param entity 产品到期确认Dto
     * @return 金融产品交易汇总
     * @see
     * @since 1.0
     */
    private TrdFinProductDealTotal genTrdFinProductDealTotal(FinProductExpire002Dto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getTradeId());
        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_理财产品.getValue());
        // trdFinProductDealTotal.setBranch(Long.parseLong(entity.getBranch()));
        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
        trdFinProductDealTotal.setTradeDate(entity.getMdate());
        trdFinProductDealTotal.setVdate(entity.getVdate());
        trdFinProductDealTotal.setMdate(entity.getMdate());
        trdFinProductDealTotal.setSettleDate(entity.getPayDate());
        trdFinProductDealTotal.setPrinAmt(entity.getReduceAmt());
        trdFinProductDealTotal.setShareAmt(entity.getReduceAmt());
        trdFinProductDealTotal.setIntAmt(entity.getActCusProfit());
        trdFinProductDealTotal.setFpriceAmt(ArithmeticUtil.doAdd(entity.getReduceAmt(), entity.getActCusProfit()));
        trdFinProductDealTotal.setTradeAmt(ArithmeticUtil.doAdd(entity.getReduceAmt(), entity.getActCusProfit()));
        // trdFinProductDealTotal.setFeeAmtPay(entity.getTotalFeeAmt());
        // trdFinProductDealTotal.setFeeAmt(entity.getTotalFeeAmt());
        // trdFinProductDealTotal.setTradeAmt(entity.getTotalTransAmt());
        // trdFinProductDealTotal.setPs(DictCodeList.交易方向_产品到期.getValue());
        trdFinProductDealTotal.setPs(entity.getPs());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
        trdFinProductDealTotal.setPortfolioId(entity.getPtlId());
        trdFinProductDealTotal.setUpdateUser(entity.getUpdateUser());
        trdFinProductDealTotal.setCreateUser(entity.getCreateUser());
        trdFinProductDealTotal.setCreateDept(entity.getCreateDept());
        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
        // trdFinProductDealTotal.setDqPrinAmt(entity.getPrinamt());
        return trdFinProductDealTotal;
    }

    /**
     * 生成金融产品交易汇总扩展
     * 
     * @param entity 产品到期确认Dto
     * @return 金融产品交易汇总扩展
     * @see
     * @since 1.0
     */
    private PaTrdFinProductDealTotal genPaTrdFinProductDealTotal(FinProductExpire002Dto entity) {
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(entity.getTradeId());
        paTrdFinProductDealTotal.setInterest(entity.getActCusProfit());
        paTrdFinProductDealTotal.setTheoryInt(entity.getCusProfit());
		/**
         * add by xr   现金流缺口分析 赎回显示为到期BUF修复 start
         */
        if(DictCodeList.交易方向_产品赎回.getValue().equals(entity.getDealType())){
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品赎回.getValue());
        }else{
            paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_产品到期.getValue());
        }
        /**
         * add by xr   现金流缺口分析 赎回显示为到期BUF修复 end
         */
        paTrdFinProductDealTotal.setUpdateUser(entity.getUpdateUser());
        paTrdFinProductDealTotal.setCreateUser(entity.getCreateUser());
        paTrdFinProductDealTotal.setCreateDept(entity.getCreateDept());
        paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());

        return paTrdFinProductDealTotal;
    }

    private FinProductAdd genFinProductAdd(FinProductExpire002Dto entity) {
        FinProductAdd finProductAdd = new FinProductAdd();
        finProductAdd.setFinprodId(entity.getFinprodId());
        finProductAdd.setSaleDepartment(entity.getSaleDepartment());
        finProductAdd.setUpdateUser(entity.getUpdateUser());
        finProductAdd.setCreateUser(entity.getCreateUser());
        finProductAdd.setCreateDept(entity.getCreateDept());
        finProductAdd.setWorkflowId(entity.getWorkflowId());

        return finProductAdd;
    }

    private TrdProductDeal genTrdProductDeal(FinProductExpire002Dto entity, ReduceInfoDto reduceInfoDto) {
        TrdProductDeal TrdProductDeal = new TrdProductDeal();
        TrdProductDeal.setBusiType(DictCodeList.业务类型_预期收益型TA交易.getValue());
        TrdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_理财产品.getValue());
        TrdProductDeal.setTradeId(entity.getTradeId());
        TrdProductDeal.setFinprodId(entity.getFinprodId());
        TrdProductDeal.setTradeDate(platformDate.getSysDate());
        TrdProductDeal.setVdate(entity.getVdate());
        TrdProductDeal.setMdate(entity.getMdate());
        TrdProductDeal.setSettleDate(entity.getPayDate());
        TrdProductDeal.setCcy(entity.getCcy());
        TrdProductDeal.setPrinAmt(reduceInfoDto.getReduceAmt());// 赎回本金
        TrdProductDeal.setIntAmt(reduceInfoDto.getActInterest());
        TrdProductDeal.setFpriceAmt(ArithmeticUtil.doAdd(reduceInfoDto.getReduceAmt(), reduceInfoDto.getActInterest()));
        TrdProductDeal.setTradeAmt(ArithmeticUtil.doAdd(reduceInfoDto.getReduceAmt(), reduceInfoDto.getActInterest()));
        // TrdProductDeal.setPs(DictCodeList.交易方向_产品到期.getValue());
        TrdProductDeal.setPs(entity.getPs());
        TrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        TrdProductDeal.setUpdateUser(entity.getUpdateUser());
        TrdProductDeal.setCreateUser(entity.getCreateUser());
        TrdProductDeal.setCreateDept(entity.getCreateDept());
        TrdProductDeal.setWorkflowId(entity.getWorkflowId());
        // add by yejiejie 2019-2-22 start
        TrdProductDeal.setTotleTradeId(entity.getTradeId());
        // add by yejiejie 2019-2-22 end
        // add by yejiejie 2019-2-21 start
        /*
         * String remark = null; if(StringUtil.equals(TrdProductDeal.getPs(), DictCodeList.交易方向_产品赎回.getValue())){ //{产品代码}{产品名称}赎回
         * //正常赎回:客户赎回确认日{支付日}，本金{赎回金额}元，收益{赎回收益}元,兑付客户清算金额{赎回金额+赎回收益}元。 remark = entity.getFinprodId()+"," + entity.getFinprodName()+",赎回;"
         * +"正常赎回:客户赎回确认日:"+entity.getPayDate() +",本金："+entity.getPrinamt()+",收益:"+entity.getActCusProfit()+",兑付客户清算金额："+entity.getmCusRevAmt(); }else
         * if(StringUtil.equals(TrdProductDeal.getPs(), DictCodeList.交易方向_产品到期.getValue())){ //{产品代码}{产品名称}到期清算
         * //{产品到期日}产品到期,客户本金{还本金额}元，收益{客户收益金额}元,兑付客户本金及收益合计{还本金额+客户收益金额}元，分支行中间业务收入{分支行收入金额}元，资产管理部中间业务收入{资金部收入}元。根据目标客户一级分类，后续加上“产品客户分类:个人。”“产品客户分类:同业。”“
         * 产品客户分类:公司。” remark = entity.getFinprodId() +","+ entity.getFinprodName()+",到期清算:"
         * +entity.getMdate()+"产品到期,客户本金:"+entity.getPrinamt()+"元,收益:"+entity.getActCusProfit()+ "元,兑付客户本金及收益合计:"+entity.getmCusRevAmt()+"元,分支行中间业务收入"+ "。"; }
         */
        TrdProductDeal.setRemark(entity.getRemark());
        // add by yejiejie 2019-2-21 start

        return TrdProductDeal;
    }

    private PaTrdProductDeal genPaTrdProductDeal(FinProductExpire002Dto entity) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(entity.getTradeId());
        paTrdProductDeal.setTheoryInt(entity.getActIntAmt());
        paTrdProductDeal.setUpdateUser(entity.getUpdateUser());
        paTrdProductDeal.setCreateUser(entity.getCreateUser());
        paTrdProductDeal.setCreateDept(entity.getCreateDept());
        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());
        // add by gcl 2019/3/18
        if (StringUtil.equals(DictCodeList.交易方向_产品赎回.getValue(), entity.getPs())) {
            paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品赎回_提前赎回_违约赎回.getValue());
        }
        else {
            paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品到期_提前终止.getValue());
        }
        // add by gcl 2019/3/18
        return paTrdProductDeal;
    }

    private TrdProductDealAdd genTrdProductDealAdd(FinProductExpire002Dto entity, TrdProductDeal trdProductDeal) {
        TrdProductDealAdd paTrdProductDeal = new TrdProductDealAdd();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setPortfolioId(entity.getPtlId());
        paTrdProductDeal.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        paTrdProductDeal.setUpdateUser(entity.getUpdateUser());
        paTrdProductDeal.setCreateUser(entity.getCreateUser());
        paTrdProductDeal.setCreateDept(entity.getCreateDept());
        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());

        return paTrdProductDeal;
    }

    private PtlTrade genPtlTrade(FinProductExpire002Dto entity) {
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(entity.getPtlId());
        ptlTrade.setBusiNo(entity.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(entity.getFinprodId());
        ptlTrade.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
        ptlTrade.setSettleDate(entity.getVdate());
        ptlTrade.setPortfolioId(entity.getPtlId());
        ptlTrade.setWorkflowId(entity.getWorkflowId());

        return ptlTrade;
    }

    /**
     * 生成划款指令信息
     * 
     * @param entity 产品到期确认Dto
     * @return 划款指令信息
     * @see
     * @since 1.0
     */
    private List<StlPayOrdersDto> genStlPayOrdersDto(FinProductExpire002Dto entity) {
        entity.setBussTrdTransferOrder(bussTrdTransferOrderService.save(entity.getBussTrdTransferOrder()));
        // 划款指令表Entity
        List<? extends DataDictItem> yesNoItemList = SysDataUtil.getDataDictItem(DictCode.是否.getValue());

        List<StlPayOrdersDto> stlPayOrdersDtoList = new ArrayList<StlPayOrdersDto>();

        if (!StringUtil.isEmptyList(entity.getBussTrdTransferOrder())) {

            BigDecimal totalTradeAmt = BigDecimal.ZERO;

            for (BussTrdTransferOrderDto bussTrdTransferOrderDto : entity.getBussTrdTransferOrder()) {
                StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, bussTrdTransferOrderDto);
                stlPayOrdersDto.setTradeId(entity.getTradeId());
                stlPayOrdersDto.setWorkflowId(entity.getWorkflowId());

                yesNoItemList.forEach(item -> {
                    if (StringUtil.equals(item.getItemValue(), stlPayOrdersDto.getIsNeedSend())) {
                        stlPayOrdersDto.setIsNeedSend(item.getItemCode());
                    }
                });
                stlPayOrdersDto.setCreateTime(entity.getCreateTime());
                stlPayOrdersDto.setUpdateTime(entity.getUpdateTime());
                stlPayOrdersDtoList.add(stlPayOrdersDto);

                totalTradeAmt = ArithmeticUtil.doAdd(totalTradeAmt, bussTrdTransferOrderDto.getSettleAmt());
            }

            entity.setTotalTransAmt(totalTradeAmt);

        }

        return stlPayOrdersDtoList;
    }

    /**
     * 根据金融产品代码查询到期信息
     * 
     * @param map 参数
     * @return 到期信息
     * @see
     * @since 1.0
     */
    public List<FinProductExpireMInfoDto> queryMInfoListByFinprodId(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryMInfoListByFinprodId",
            map, FinProductExpireMInfoDto.class);
    }

    /**
     * 根据金融产品代码查询费用信息
     * 
     * @param map 参数
     * @return 费用信息
     * @see
     * @since 1.0
     */
    /*
     * public List<FinProductExpireFeeInfoDto> queryFeeInfoListByFinprodId(Map<String, Object> map) { return
     * restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryFeeInfoListByFinprodId", map,
     * FinProductExpireFeeInfoDto.class); }
     *
     *//**
        * 根据金融产品代码查询分行信息
        *
        * @param map 参数
        * @return 分行信息
        * @see
        * @since 1.0
        */

    /*
     * public List<FinProductExpireBranchInfoDto> queryBranchInfoListByFinprodId(Map<String, Object> map) { return
     * restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryBranchInfoListByFinprodId", map,
     * FinProductExpireBranchInfoDto.class); }
     *
     *//**
        * 根据交易编号查询到期信息
        *
        * @param tradeId 交易编号
        * @return 到期信息
        * @see
        * @since 1.0
        */
    public List<FinProductExpireMInfoDto> queryMInfoListByTradeId(String tradeId) {
        return restTemplate.getForList(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryMInfoListByTradeId?tradeId=" + tradeId,
            FinProductExpireMInfoDto.class);
    }

    /**
     * 判断交易类型：赎回？到期？提前终止？
     *
     * @param dto
     * @return
     */
    public String getPs(FinProductExpire002Dto dto) {
        FinProductBuild002Dto info = finProductBuild002Service.queryProdInfoByFinprodId(dto.getFinprodId());
        if (StringUtil.isNotEmpty(dto.getMdate()) && dto.getMdate().compareTo(info.getMdate()) < 0) {
            return DictCodeList.交易方向_产品赎回.getValue();
        }
        else if (StringUtil.isNotEmpty(dto.getMdate()) && dto.getMdate().compareTo(info.getMdate()) == 0) {
            PrdMdateAdjust cond = new PrdMdateAdjust();
            cond.setFinprodId(dto.getFinprodId());
//            cond.setAdjustType("01");
            List<PrdMdateAdjust> listPrdMdateAdjust = restTemplate.postForList(
                ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductCommon/queryPrdMdateAdjustByModel", cond,
                PrdMdateAdjust.class);
            if (listPrdMdateAdjust != null && listPrdMdateAdjust.size() > 0) {
                PrdMdateAdjust prdMdateAdjust = listPrdMdateAdjust.stream().sorted((item1, item2)->DateUtil.compareDate(item2.getUpdateTime(),item1.getUpdateTime())).findFirst().get();
                if (DateUtil.compareDate(prdMdateAdjust.getMdate(),prdMdateAdjust.getMdateUnadjust())==1){
                    return DictCodeList.交易方向_产品到期.getValue();
                }else{
                    return DictCodeList.交易方向_提前终止.getValue();
                }
            }
            return DictCodeList.交易方向_产品到期.getValue();
        } // 如果超过到期日就置空交易类型
        else {
            return null;
        }
    }

    /**
     * 根据交易编号查询费用信息
     * 
     * @param tradeId 交易编号
     * @return 费用信息
     * @see
     * @since 1.0
     */
    /*
     * public List<FinProductExpireFeeInfoDto> queryFeeInfoListByTradeId(String tradeId) { return
     * restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryFeeInfoListByTradeId?tradeId=" + tradeId,
     * FinProductExpireFeeInfoDto.class); }
     *
     *//**
        * 根据交易编号查询分行信息
        *
        * @param tradeId 交易编号
        * @return 分行信息
        * @see
        * @since 1.0
        *//*
           * public List<FinProductExpireBranchInfoDto> queryBranchInfoListByTradeId(String tradeId) { return
           * restTemplate.getForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryBranchInfoListByTradeId?tradeId=" + tradeId,
           * FinProductExpireBranchInfoDto.class); }
           *
           * public FinProductExpire002Dto queryProdInfoByMdate(FinProductExpire002Dto dto) { Map<String, Object> map = new HashMap<String, Object>();
           * map.put("finprodMarketId", dto.getFinprodMarketId()); map.put("mdate", dto.getMdate()); map.put("finprodType2",
           * DictCodeListExt1.金融产品类型_投管用_理财产品.getValue()); map.put("branch", Long.parseLong(CacheUtil.getSysParam(BussinessConstant.NOBRANCHNOVALUE))); return
           * restTemplate.postForObject( ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryProdInfoByMdate", map, FinProductExpire002Dto.class);
           * }
           */

    /**
     * 是否分层并且有分层列表
     *
     * @param entity 确认信息
     * @return 是否分层并且有分层列表
     * @see
     * @since 1.0
     */
    private boolean isIsLay(FinProductExpire002Dto entity) {
        boolean flag = false;
        if (StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_分档.getValue())
            || StringUtil.equals(entity.getDealMode(), DictCodeList.处理模式_分档周期型.getValue())) {
            if (!StringUtil.isEmptyList(entity.getFinProductExpireMInfoDtoList())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 本金
     *
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal genTrdProductDealAmt(FinProductExpire002Dto entity, ReduceInfoDto reduceInfoDto) {

        TrdProductDeal TrdProductDeal = new TrdProductDeal();
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品赎回到期管理);
            TrdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            TrdProductDeal.setTradeId(IDGenerator.generate());
        }
        TrdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品赎回到期管理.getValue());
        TrdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        TrdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (this.isIsLay(entity)) {
            TrdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        }
        TrdProductDeal.setBranch((long)0);
        TrdProductDeal.setFinprodId(reduceInfoDto.getFinprodId());
        TrdProductDeal.setTradeDate(entity.getMdate());
        TrdProductDeal.setVdate(DateUtil.parse(reduceInfoDto.getvDate()));
        TrdProductDeal.setMdate(entity.getMdate());
        // TrdProductDeal.setVdate(entity.getVdate());
        TrdProductDeal.setSettleDate(entity.getPayDate());
        TrdProductDeal.setCcy(entity.getCcy());
        TrdProductDeal.setShareAmt(reduceInfoDto.getReduceAmt());
        TrdProductDeal.setPrinAmt(reduceInfoDto.getReduceAmt());
        TrdProductDeal.setFpriceAmt(reduceInfoDto.getReduceAmt());
        TrdProductDeal.setTradeAmt(reduceInfoDto.getReduceAmt());
        TrdProductDeal.setPs(entity.getPs());
        if (entity.getPs().equals(DictCodeList.交易方向_中间付息.getValue())) {
            TrdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        }
        TrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        TrdProductDeal.setUpdateUser(entity.getUpdateUser());
        TrdProductDeal.setCreateUser(entity.getCreateUser());
        TrdProductDeal.setCreateDept(entity.getCreateDept());
        TrdProductDeal.setWorkflowId(entity.getWorkflowId());
        TrdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        TrdProductDeal.setTotleTradeId(entity.getTradeId());
        TrdProductDeal.setRemark(entity.getRemark());

        return TrdProductDeal;
    }

    /**
     * 利息
     *
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    private TrdProductDeal genTrdProductDealInt(FinProductExpire002Dto entity, ReduceInfoDto reduceInfoDto) {
        TrdProductDeal TrdProductDeal = new TrdProductDeal();
        if (baseGetBusinessId != null) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_收益型产品中间付息管理);
            TrdProductDeal.setTradeId(baseGetBusinessId.buildBussinessCode(codeParameter));
        }
        else {
            TrdProductDeal.setTradeId(IDGenerator.generate());
        }
        TrdProductDeal.setBusiType(DictCodeList.共通业务类型_收益型产品中间付息管理.getValue());
        TrdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_理财产品.getValue());
        TrdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (this.isIsLay(entity)) {
            TrdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        }
        TrdProductDeal.setBranch((long)0);
        TrdProductDeal.setFinprodId(reduceInfoDto.getFinprodId());
        TrdProductDeal.setTradeDate(entity.getMdate());
        // TrdProductDeal.setVdate(entity.getVdate());
        TrdProductDeal.setVdate(DateUtil.parse(reduceInfoDto.getvDate()));
        TrdProductDeal.setMdate(entity.getMdate());
        TrdProductDeal.setSettleDate(entity.getPayDate());
        TrdProductDeal.setCcy(entity.getCcy());
        TrdProductDeal.setPrinAmt(reduceInfoDto.getReduceAmt());
        TrdProductDeal.setShareAmt(reduceInfoDto.getReduceAmt());
        TrdProductDeal.setIntAmt(reduceInfoDto.getActInterest());
        TrdProductDeal.setTradeAmt(reduceInfoDto.getActInterest());

        // 交易类型为赎回时-数据字典：交易方向.赎回收益确认；交易类型为到期时-数据字典：交易方向.中间付息
        if (entity.getPs().equals(DictCodeList.交易方向_产品到期.getValue())
            || entity.getPs().equals(DictCodeList.交易方向_提前终止.getValue())) {
            TrdProductDeal.setPs(DictCodeList.交易方向_中间付息.getValue());
        }
        else {
            TrdProductDeal.setPs(DictCodeList.交易方向_赎回收益确认.getValue());
        }
        if (TrdProductDeal.getPs().equals(DictCodeList.交易方向_中间付息.getValue())) {
            TrdProductDeal.setIsClean(DictCodeList.是否_是.getValue());
        }
        TrdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        TrdProductDeal.setUpdateUser(entity.getUpdateUser());
        TrdProductDeal.setCreateUser(entity.getCreateUser());
        TrdProductDeal.setCreateDept(entity.getCreateDept());
        TrdProductDeal.setWorkflowId(entity.getWorkflowId());
        TrdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        TrdProductDeal.setTotleTradeId(entity.getTradeId());
        TrdProductDeal.setRemark(entity.getRemark());

        return TrdProductDeal;
    }

    private PaTrdProductDeal genPaTrdProductDealAmt(ReduceInfoDto reduceInfoDto, TrdProductDeal trdProductDeal) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setTheoryInt(reduceInfoDto.getInterest());
        // 添加指令业务类型lijun modify SHBS-1199 2019-03-21 start
        if (StringUtil.equals(DictCodeList.交易方向_产品赎回.getValue(), trdProductDeal.getPs())) {
            paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品赎回_提前赎回_违约赎回.getValue());
        }
        else {
            paTrdProductDeal.setOrderBussType(DictCodeListExt1.指令业务类型_产品到期_提前终止.getValue());
        }
        // 添加指令业务类型 lijun modify SHBS-1199 2019-03-16 end
        paTrdProductDeal.setWorkflowId(trdProductDeal.getWorkflowId());
        return paTrdProductDeal;
    }

    private PaTrdProductDeal genPaTrdProductDealInt(ReduceInfoDto reduceInfoDto, TrdProductDeal trdProductDeal) {
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        paTrdProductDeal.setTheoryInt(reduceInfoDto.getInterest());
        paTrdProductDeal.setRemainPrinAmt(
            ArithmeticUtil.subtract(reduceInfoDto.getRemainPrinAmt(), reduceInfoDto.getReduceAmt()));
        paTrdProductDeal.setWorkflowId(trdProductDeal.getWorkflowId());
        return paTrdProductDeal;
    }

    private TrdProductDealAdd genTrdProductDealAddAmt(FinProductExpire002Dto entity) {
        TrdProductDealAdd paTrdProductDeal = new TrdProductDealAdd();
        paTrdProductDeal.setTradeId(entity.getTradeId());
        paTrdProductDeal.setPortfolioId(entity.getPtlId());
        paTrdProductDeal.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        paTrdProductDeal.setUpdateUser(entity.getUpdateUser());
        paTrdProductDeal.setCreateUser(entity.getCreateUser());
        paTrdProductDeal.setCreateDept(entity.getCreateDept());
        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());

        return paTrdProductDeal;
    }

    private TrdProductDealAdd genTrdProductDealAddInt(FinProductExpire002Dto entity, String tradeid) {
        TrdProductDealAdd paTrdProductDeal = new TrdProductDealAdd();
        paTrdProductDeal.setTradeId(tradeid);
        paTrdProductDeal.setPortfolioId(entity.getPtlId());
        paTrdProductDeal.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        paTrdProductDeal.setUpdateUser(entity.getUpdateUser());
        paTrdProductDeal.setCreateUser(entity.getCreateUser());
        paTrdProductDeal.setCreateDept(entity.getCreateDept());
        paTrdProductDeal.setWorkflowId(entity.getWorkflowId());

        return paTrdProductDeal;
    }

    public List<ReduceInfoDto> queryInterest(String totleTradeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totleTradeId", totleTradeId);
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryInterest", map,
            ReduceInfoDto.class);
    }

    public Integer checkpayAmt(String cashId, String mdate, String ptlId, String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cashId", cashId);
        map.put("mdate", mdate);
        List<String> list = new ArrayList<String>();
        list.add(ptlId);
        map.put("ptlId", list);
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        /*
         * List<FinProductFeePay001Dto> finProductFeePay001Dto = restTemplate.postForList( ServiceApplication.PRODUCT_SERVICE_NAME +
         * "/FinProductFeePay/queryPendingFeeInfoByCashId", map, FinProductFeePay001Dto.class);
         */
        map.put("finprodId", finprodId);
        Integer finProductFeePay001Dto1 = restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeePay/checkpayAmt", map, Integer.class);
        Integer finProductFeePay001Dto = restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeePay/checkpayAmtrule", map, Integer.class);
        /*
         * if(StringUtil.isNotEmpty(finProductFeePay001Dto1)&&finProductFeePay001Dto1.size()>0){ map1.put("finProductFeePay001Dto1",
         * finProductFeePay001Dto1.get(0)); }
         */
        // map1.put("finProductFeePay001Dto", null);
        if (finProductFeePay001Dto != null && finProductFeePay001Dto1 != null) {
            return finProductFeePay001Dto + finProductFeePay001Dto1;
        }
        else {
            return finProductFeePay001Dto1;
        }

    }

    /**
     * 根据到期汇总交易编号查询到期交易 <功能详细描述>
     *
     * @param tradeId
     * @return
     * @see
     * @since 1.0
     */
    public FinProductExpire002Dto queryProdExpireByTotleTradeId(String tradeId) {
        return restTemplate.getForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/queryProdExpireByTotleTradeId?tradeId=" + tradeId,
            FinProductExpire002Dto.class);
    }

    public Integer isExpireTradeByFinprodId(String finprodId, String ps) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("finprodId", finprodId);
        // PS = #{psC5}
        // and TRADE_STATUS = #{tradeStatus01}
        // and IS_CANCEL = #{isCancelN}
        param.put("ps", ps);
        param.put("tradeStatus01", DictCodeListExt1.交易状态_初始.getValue());
        param.put("isCancelN", DictCodeList.是否_否.getValue());
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductExpire/isExpireTradeByFinprodId",
            param, Integer.class);
    }
}
