/*
 * 文件名：PtlSubFundsAllocate002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：adrian.zhou
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.settle.dto.BussSettleOrderParameterDto;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.business.common.BaseAssetCashPositionCommService;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioCashPositionsFlowInputDto;
import com.joyintech.tams.invest.common.component.business.common.dto.PortfolioPositionsFlowInputDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.*;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.portfolio.dto.FinSubFundsDealDbDataDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsAllocate002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author adrian.zhou
 * @version 1.0 2019年8月15日
 * @see PtlSubFundsAllocate002Service
 * @since 1.0
 */
@Service("PtlSubFundsAllocate002Service")
@Transactional(rollbackFor = Exception.class)
public class PtlSubFundsAllocate002Service extends CommonExportService<PtlSubFundsAllocate002Dto>
        implements BaseProcessService<PtlSubFundsAllocate002Dto> {

    /**
     * BaseGetBusinessId
     */
    @Autowired(required = false)
    private BaseGetBusinessId baseGetBusinessId;
    /**
     * BussTrdTransferOrderService
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * BussCommonMapperDelegateService
     */
    /**
     * BaseTradeProductDealCommService
     */
    @Autowired(required = false)
    private BaseTradeProductDealCommService baseTradeProductDealCommService;

    @Autowired
    private BaseAssetCashPositionCommService assetPositionsCashCommServiceImpl;

    /**
     * 获取组合代码的下拉框
     * <功能详细描述>
     *
     * @return SelectOptionDto
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userName", user.getUserName());
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                + "/PtlSubFundsAllocate002/getPortfolioId", param, SelectOptionDto.class);
    }

    public Page<PtlSubFundsAllocate002Dto> getSubFundsAllocate(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/getSubFundsAllocate", pageParam,
                PtlSubFundsAllocate002Dto.class);
    }

    public Page<PtlSubFundsAllocate002Dto> selectOther(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/selectOther", pageParam,
                PtlSubFundsAllocate002Dto.class);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doComplete(PtlSubFundsAllocate002Dto entity, OperationType operationType) {
        if (!operationType.equals(OperationType.REVOKE)
                && !operationType.equals(OperationType.DELETE)) {
            //this.handleTradeData(entity,DictCodeListExt1.交易流程状态_有效.getValue());
            FinProductTradeDto finProductTradeDto;

            if (StringUtil.isEmptyList(entity.getSubFundsDealDbDataList())) {
                entity.setSettleDate(DateUtil.getSysDate());
                this.prepareEntity(entity);
            }
            finProductTradeDto = genFinProductTradeDto(entity);
            finProductTradeDto.setWorkflowStatus(DictCodeListExt1.交易流程状态_有效.getValue());

            if (!StringUtil.isEmptyList(finProductTradeDto.getBaseTradeProductDealDtos())) {
                restTemplate.postForObject(
                        ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/insertBatch", finProductTradeDto,
                        String.class);
            }

            baseTradeProductDealCommService.insertPosition(finProductTradeDto);
                /*for (FinSubFundsDealDbDataDto tradeDto : entity.getSubFundsDealDbDataList()) {
                	PtlTrade ptlTrade = tradeDto.getPtlTrade();
                	bussCommonMapperDelegateService.insertBatch(PtlTradeMapper.class, ptlTrade);
                }*/
            doDelete(entity.getWorkflowId());
        }
    }

    public void doUpdate(PtlSubFundsAllocate002Dto entity) {
        if (!StringUtil.equals("revoke", entity.getOperateType())
                && !StringUtil.equals("delete", entity.getOperateType())) {
            if (!BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
                // 先删除流程中数据
                this.doDeleteData(entity.getWorkflowId());
	
	            /*
	            ProcessMark newEntity = null;
	            try {
	                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(PtlSubFundsAllocate002Dto.class, true);
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
	            }*/
            } else {
                entity.setSettleDate(DateUtil.getSysDate());
                this.prepareEntity(entity);
                this.handleBussFlowData(entity, DictCodeListExt1.交易流程状态_流程中.getValue());
            }

        }
    }

    public void doDeleteData(String workflowId) {
        restTemplate.delete(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/deleteWflData", workflowId,
                String.class);
    }

    @Override
    public void doInsert(PtlSubFundsAllocate002Dto entity, OperationType operationType) {
        // 新建交易
        if (!operationType.equals(OperationType.REVOKE)
                && !operationType.equals(OperationType.DELETE) & BussinessConstant.SUBMIT.equals(entity.getOperateType())) {
            this.handleBussFlowData(entity, DictCodeListExt1.交易流程状态_流程中.getValue());
        }
        // 撤销交易
        else {
            // this.doRevokeInsert(entity);
        }
    }

    @Override
    public void doDelete(String workflowId) {
        this.doDeleteData(workflowId);
    }

    private void handleBussFlowData(PtlSubFundsAllocate002Dto entity, String workflowStatus) {

        if (!StringUtil.isEmptyList(entity.getSubFundsDealDbDataList())) {
            restTemplate.postForObject(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/handleBussFlowData", entity,
                    String.class);
            handleTradeData(entity, workflowStatus);
        }
    }

    private void handleTradeData(PtlSubFundsAllocate002Dto entity, String workflowStatus) {
        FinProductTradeDto finProductTradeDto = genFinProductTradeDto(entity);
        finProductTradeDto.setWorkflowStatus(workflowStatus);
        baseTradeProductDealCommService.execute(finProductTradeDto);
        //baseTradeProductDealCommService.insertPosition(finProductTradeDto);
    }

    private Boolean insertPosition(FinProductTradeDto finProductTradeDto) {
        DictCodeList 持仓类型 = null;
        boolean needNegative = false;
        if (DictCodeListExt1.交易流程状态_撤销.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = true;
        } else if (DictCodeListExt1.交易流程状态_流程中.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_可用;
            needNegative = false;
        } else if (DictCodeListExt1.交易流程状态_有效.getValue().equals(finProductTradeDto.getWorkflowStatus())) {
            持仓类型 = DictCodeList.持仓类型_实际;
            needNegative = false;
        }
        if (持仓类型 == null) {
            return false;
        }

        List<PtlSecPositionFlowDto> ptlSecPositionFlows = new ArrayList<>();
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<>();
        for (BaseTradeProductDealDto baseTradeProductDealDto : finProductTradeDto.getBaseTradeProductDealDtos()) {
            PortfolioPositionsFlowInputDto inputDto = new PortfolioPositionsFlowInputDto();
            inputDto.setPositionType(持仓类型.getValue());
            inputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
            inputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
            inputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
            inputDto.setAssetType2(finProductTradeDto.getAssetType2());
            ptlSecPositionFlows.addAll(assetPositionsCashCommServiceImpl.positionDeal(inputDto));

            if (baseTradeProductDealDto.getStlPayOrdersList() != null && !baseTradeProductDealDto.getStlPayOrdersList().isEmpty()) {
                PortfolioCashPositionsFlowInputDto cashInputDto = new PortfolioCashPositionsFlowInputDto();
                cashInputDto.setPositionType(持仓类型.getValue());
                cashInputDto.setTrdProductDeal(baseTradeProductDealDto.getTrdProductDeal());
                cashInputDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
                cashInputDto.setBonusMode(baseTradeProductDealDto.getTrdProductDealAdd().getPayType());
                cashInputDto.setAssetType2(finProductTradeDto.getAssetType2());
                cashInputDto.setStlPayOrders(baseTradeProductDealDto.getStlPayOrdersList());
                List<PtlCashPositionFlowDto> resultList = assetPositionsCashCommServiceImpl.cashDeal(cashInputDto);
                for (PtlCashPositionFlowDto result : resultList) {
                    result.setSplitType("01");
                }
                ptlCashPositionFlows.addAll(resultList);
            }
            //-------panbin 2019-7-18 add---------start
            else {
                TrdProductDeal deal = baseTradeProductDealDto.getTrdProductDeal();
                /*
                 * 输入金融产品交易Entity.金融产品类型（投管分类）= 数据字典缓存.金融产品类型（投管分类).股票 and 金融产品交易Entity.交易方向 in （除权登记）
                 * （如果全价总额 = 0或者为空，则此数据不生成）
                 */
                if (DictCodeListExt1.金融产品类型_投管用_股票.getValue().equals(deal.getFinprodType2())
                        && DictCodeList.交易方向_除权登记.getValue().equals(deal.getPs())
                        && DictCodeList.持仓类型_实际.getValue().equals(持仓类型.getValue())
                        && deal.getFpriceAmt() != null && deal.getFpriceAmt().compareTo(new BigDecimal(0)) > 0) {
                    PtlCashPositionFlowDto ztFlowDto = new PtlCashPositionFlowDto();
                    // 输入参数.组合代码
                    ztFlowDto.setPortfolioId(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId());
                    //获取证券账户信息
//                    StlSecAcctInfo acctInfo = baseAssetCashPositionCommMapper.selectSecAcctInfo(deal.getSecManageAcctId());
                    // 证券账户信息Entity.关联回款账户
                    // modify by wj 20200620 二级法人改造 五个特殊参数 修改 为特殊取值方法
                    String institutionCode = MultilevelDataAuthorityUtil.getOrgCodeByBussinessIDType(baseTradeProductDealDto.getTrdProductDealAdd().getPortfolioId(), com.joyintech.framework.constant.DictCodeList.联社模式查询数据权限_修改和删除.getValue(), DictCodeList.共通业务类型_组合管理.getValue());
                    //ztFlowDto.setAcctId(CacheUtil.getSysParam(BussinessConstant.SETTLE_ACCT_NO_KEY));
                    ztFlowDto.setAcctId(CacheUtil.getSpecialSysParam(BussinessConstant.SETTLE_ACCT_NO_KEY,institutionCode));
                    // 持仓类型-在途
                    ztFlowDto.setHodingType(DictCodeList.持仓类型_在途.getValue());
                    //金融产品交易Entity.交易日
                    ztFlowDto.setCdate(deal.getTradeDate());
                    // 划款指令Entity.币种
                    ztFlowDto.setCcy(deal.getCcy());
                    // 金融产品交易Entity.交易编号
                    ztFlowDto.setOrderId(deal.getTradeId());
                    //金融产品交易Entity.全价总额
                    ztFlowDto.setAmount(deal.getFpriceAmt());
                    ztFlowDto.setCreateUser(deal.getCreateUser());
                    ztFlowDto.setCreateDept(deal.getCreateDept());
                    ztFlowDto.setUpdateUser(deal.getCreateUser());
                    ptlCashPositionFlows.add(ztFlowDto);
                }
            }
            //-------panbin 2019-7-18 add---------end
        }
        if (needNegative) {
            if (ptlCashPositionFlows != null && !ptlCashPositionFlows.isEmpty()) {
                for (PtlCashPositionFlowDto ptlCashPosition : ptlCashPositionFlows) {
                    if (ptlCashPosition.getAmount() != null) {
                        ptlCashPosition.setAmount(ptlCashPosition.getAmount().negate());
                    }
                }
            }
            if (ptlSecPositionFlows != null && !ptlSecPositionFlows.isEmpty()) {
                for (PtlSecPositionFlowDto ptlSecPosition : ptlSecPositionFlows) {
                    if (ptlSecPosition.getAmount() != null) {
                        ptlSecPosition.setAmount(ptlSecPosition.getAmount().negate());
                    }
                }
            }
        }
        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlSecPositionFlows(ptlSecPositionFlows);
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
        if (!ptlSecPositionFlows.isEmpty() || !ptlCashPositionFlows.isEmpty()) {
            return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/common/ptlposition/insertPosition",
                    ptlPositionHoldDto, Boolean.class);
        }
        return Boolean.TRUE;
    }

    private FinProductTradeDto genFinProductTradeDto(PtlSubFundsAllocate002Dto entity) {
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        boolean isTrdFinProductDealTotal = false;
        boolean isPaTrdFinProductDealTotal = false;
        for (FinSubFundsDealDbDataDto tradeDto : entity.getSubFundsDealDbDataList()) {
            BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
            if (!isTrdFinProductDealTotal) {
                TrdFinProductDealTotal trdFinProductDealTotal = tradeDto.getTrdFinProductDealTotal();
                trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
                baseTradeProductDealDto.setTrdFinProductDealTotal(trdFinProductDealTotal);
                isTrdFinProductDealTotal = true;
            }
            if (!isPaTrdFinProductDealTotal) {
                PaTrdFinProductDealTotal paTrdFinProductDealTotal = tradeDto.getPaTrdFinProductDealTotal();
                paTrdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
                baseTradeProductDealDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);
                isPaTrdFinProductDealTotal = true;
            }

            baseTradeProductDealDto.setActPortfolioId(entity.getPortfolioId());
            TrdProductDeal trdProductDeal = tradeDto.getTrdProductDeal();
            trdProductDeal.setTradeDate(DateUtil.parse(entity.getSettleDate()));
            trdProductDeal.setWorkflowId(entity.getWorkflowId());
            baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
            PaTrdProductDeal paTrdProductDeal = tradeDto.getPaTrdProductDeal();
            paTrdProductDeal.setWorkflowId(entity.getWorkflowId());
            baseTradeProductDealDto.setPaTrdProductDeal(paTrdProductDeal);
            TrdProductDealAdd trdProductDealAdd = tradeDto.getTrdProductDealAdd();
            trdProductDealAdd.setWorkflowId(entity.getWorkflowId());
            baseTradeProductDealDto.setTrdProductDealAdd(trdProductDealAdd);
            tradeDto.getPtlTrade().setWorkflowId(entity.getWorkflowId());
            baseTradeProductDealDto.setPtlTrade(Arrays.asList(tradeDto.getPtlTrade()));
            StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
            tradeDto.getBussTrdTransferOrderDto().setWorkflowId(entity.getWorkflowId());
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, tradeDto.getBussTrdTransferOrderDto());
            if (tradeDto.getBussTrdTransferOrderDto() != null && StringUtil.isNotEmpty(tradeDto.getBussTrdTransferOrderDto().getRecAcctNo())) {
                stlPayOrdersDto.setMyAcctNo(tradeDto.getBussTrdTransferOrderDto().getRecAcctNo());
            } else {
                stlPayOrdersDto.setMyAcctNo(tradeDto.getBussTrdTransferOrderDto().getPayAcctNo());
            }
            List<StlPayOrdersDto> list = new ArrayList<StlPayOrdersDto>();
            list.add(stlPayOrdersDto);
            baseTradeProductDealDto.setStlPayOrdersList(list);
            baseTradeProductDealDtos.add(baseTradeProductDealDto);
        }
        finProductTradeDto.setWorkflowId(entity.getWorkflowId());
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        return finProductTradeDto;
    }

    private String handleData(PtlSubFundsAllocate002Dto ptlSubFundsAllocate002Dto) throws WorkFlowException {
        BussinessCodeParameter codeParameter = new BussinessCodeParameter();
        codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_子组合资金调配);
        String totleTradeId = baseGetBusinessId.buildBussinessCode(codeParameter);
        ptlSubFundsAllocate002Dto.setTradeId(totleTradeId);
        ptlSubFundsAllocate002Dto.setSettleDate(DateUtil.getSysDate());

        ProcessParameter<PtlSubFundsAllocate002Dto> processParameter = new ProcessParameter<PtlSubFundsAllocate002Dto>();
        WorkFlowParameter<PtlSubFundsAllocate002Dto> params = new WorkFlowParameter<PtlSubFundsAllocate002Dto>();
        params.setPageId("PtlSubFundsAllocate002");
        params.setElementId(ptlSubFundsAllocate002Dto.getElementId());
        params.setBusinessCode(totleTradeId);
        params.setBusinessType(DictCodeList.共通业务类型_子组合资金调配.getValue());

        // 设置流程业务说明
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(entity.getPortfolioName() +
                    GlobalConstant.OPEN_PARENTHESIS + entity.getPortfolioId(), GlobalConstant.CLOSE_PARENTHESIS +
                    getMessage("PtlSubFundsAllocate001.settleDate") + GlobalConstant.OPEN_PARENTHESIS +
                    entity.getSettleDate() + GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        this.prepareEntity(ptlSubFundsAllocate002Dto);
        String msg = this.start(processParameter, ptlSubFundsAllocate002Dto);
        return msg;

    }

    private void prepareEntity(PtlSubFundsAllocate002Dto ptlSubFundsAllocate002Dto) {
        if (StringUtil.equals("submit", ptlSubFundsAllocate002Dto.getOperateType()) || StringUtil.equals("save", ptlSubFundsAllocate002Dto.getOperateType())) {
        	//查询母组合收益类型
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("profitId", ptlSubFundsAllocate002Dto.getPortfolioId());
        	String profitType = restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME 
        			+ "/PtlSubFundsAllocate002/getProfitType", map, String.class);
        	//业务类型
        	String busiType = "";
        	if(DictCodeList.收益类型_货币型.getValue().equals(profitType)) {
        		busiType = DictCodeList.业务类型_货币型TA交易.getValue();
        	}
        	else if(DictCodeList.收益类型_收益型.getValue().equals(profitType)) {
        		busiType = DictCodeList.业务类型_预期收益型TA交易.getValue();
        	}
        	else {
        		busiType = DictCodeList.业务类型_净值型TA交易.getValue();
        	}
        	List<FinSubFundsDealDbDataDto> dtoList = new ArrayList<FinSubFundsDealDbDataDto>();
            for (PtlSubFundsAllocate002Dto dto : ptlSubFundsAllocate002Dto.getSelectedItem()) {
                this.handleOneData(dto, dtoList, ptlSubFundsAllocate002Dto.getTradeId(), busiType);
            }
            ptlSubFundsAllocate002Dto.setSubFundsDealDbDataList(dtoList);
        }
    }

    private void handleOneData(PtlSubFundsAllocate002Dto dto, List<FinSubFundsDealDbDataDto> dtoList, String totleTradeId, String busiType) {
        String genTotleTradeId = baseGetBusinessId.buildBussinessCode(new BussinessCodeParameter(DictCodeList.共通业务类型_子组合资金调配));
        FinSubFundsDealDbDataDto result = new FinSubFundsDealDbDataDto();

        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(totleTradeId);
        trdFinProductDealTotal.setBusiType(busiType);
        trdFinProductDealTotal.setFinprodId(totleTradeId);
        trdFinProductDealTotal.setFinprodType(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdFinProductDealTotal.setBranch(0L);
        trdFinProductDealTotal.setPortfolioId(dto.getParentPortfolioId());
        trdFinProductDealTotal.setCcy(dto.getCcy());
        trdFinProductDealTotal.setPs(DictCodeList.交易方向_资金调配.getValue());
        trdFinProductDealTotal.setSettleDate(DateUtil.platDateFromCache());
        trdFinProductDealTotal.setPrinAmt(BigDecimal.ZERO);
        trdFinProductDealTotal.setTradeAmt(BigDecimal.ZERO);
        result.setTrdFinProductDealTotal(trdFinProductDealTotal);

        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        paTrdFinProductDealTotal.setTotleTradeId(totleTradeId);
        paTrdFinProductDealTotal.setPaBussType(DictCodeListExt1.平安业务类型_子组合资金调配.getValue());
        result.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(genTotleTradeId);
        trdProductDeal.setBusiType(busiType);
        trdProductDeal.setFinprodId(dto.getPortfolioId());
        trdProductDeal.setFinprodType(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        trdProductDeal.setBranch(0L);
        trdProductDeal.setCcy(dto.getCcy());
        trdProductDeal.setPs(dto.getTradeAmt().compareTo(BigDecimal.ZERO) > 0 ? DictCodeList.交易方向_产品申购.getValue() : DictCodeList.交易方向_产品赎回.getValue());
        trdProductDeal.setSettleDate(DateUtil.platDateFromCache());
        trdProductDeal.setPrinAmt( BigDecimal.valueOf(Math.abs(dto.getTradeAmt().doubleValue())));
        trdProductDeal.setTradeAmt(BigDecimal.valueOf(Math.abs(dto.getTradeAmt().doubleValue())));
        trdProductDeal.setShareAmt(BigDecimal.valueOf(Math.abs(dto.getTradeAmt().doubleValue())));
        trdProductDeal.setTotleTradeId(totleTradeId);
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        // 新增调配备注
        if(StringUtils.isNotBlank(dto.getDeployrRemark())) {
        trdProductDeal.setRemark(dto.getDeployrRemark());
        }
        result.setTrdProductDeal(trdProductDeal);

        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(genTotleTradeId);
        paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_子组合资金调配.getValue());
        result.setPaTrdProductDeal(paTrdProductDeal);

        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(genTotleTradeId);
        trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        result.setTrdProductDealAdd(trdProductDealAdd);

        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getSubPortfolioId());
        ptlTrade.setBusiNo(trdProductDeal.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
        ptlTrade.setBusiId(trdProductDeal.getFinprodId());
        ptlTrade.setSettleDate(DateUtil.platDateFromCache());
        ptlTrade.setBusiType(busiType);
        result.setPtlTrade(ptlTrade);

        BussSettleOrderParameterDto param = new BussSettleOrderParameterDto();
        param.setIsTotalTrade(DictCodeList.是否_否.getValue());
        param.setPortfolioId(dto.getSubPortfolioId());
        param.setDeal(trdProductDeal);
        BussTrdTransferOrderDto resultOrderDto = bussTrdTransferOrderService.generateOrder(param);
        resultOrderDto.setSettleDate(DateUtil.platDateFromCache());
        resultOrderDto.setSettleAmt(trdProductDeal.getTradeAmt());
        resultOrderDto.setPortfolioId(dto.getSubPortfolioId());
        resultOrderDto.setTradeId(trdProductDeal.getTradeId());
        resultOrderDto.setCcy(dto.getCcy());
        result.setBussTrdTransferOrderDto(resultOrderDto);
        dtoList.add(result);
    }

    public AjaxResult handleSave(PtlSubFundsAllocate002Dto ptlSubFundsAllocate002Dto) throws WorkFlowException {
        String msg = handleData(ptlSubFundsAllocate002Dto);
        return new AjaxResult(msg);
    }

    public BigDecimal getCashFlowActualAmt(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/getCashFlowActualAmt", pageParam,
                BigDecimal.class);
    }

    public BigDecimal getSumCashFlowAvaAmt(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsAllocate002/getSumCashFlowAvaAmt", pageParam,
                BigDecimal.class);
    }

}
