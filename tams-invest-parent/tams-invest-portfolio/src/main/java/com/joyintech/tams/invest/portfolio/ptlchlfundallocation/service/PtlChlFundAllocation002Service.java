/*
 * 文件名：PtlChlFundAllocation002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通道资金划拨新建Service
 *
 * @author etliu
 * @version 1.0 2019年4月4日
 * @see PtlChlFundAllocation002Service
 * @since 1.0
 */
@Service("PtlChlFundAllocation002Service")
@Transactional(rollbackFor = Exception.class)
public class PtlChlFundAllocation002Service extends BaseService<PtlChlFundAllocationDto> implements BaseProcessService<PtlChlFundAllocationDto> {

    /**
     * 基础Mapper代理Service
     */
    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    /**
     * 业务编号生成类
     */
    @Autowired(required = false)
    public BaseGetBusinessId baseGetBussinessId;

    /**
     * 划款指令
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    /**
     * ValuationInterfService
     */
    @Autowired
    private ValuationInterfService valuationInterfService;

    /**
     * 查询画面详细信息
     *
     * @param orderId 指令编号
     * @return 画面详细信息
     * @see
     * @since 1.0
     */
    public PtlChlFundAllocationDto getTradeDetailData(String orderId) {
        PtlChlFundAllocationDto resultDto = new PtlChlFundAllocationDto();

        // 根据指令编号查询划款指令
        StlPayOrders stlPayOrders = bussCommonMapperDelegateService.selectBySinglePrimaryKey(StlPayOrdersMapper.class, orderId, StlPayOrders.class);
        BeanUtil.copyPropertiesIgnoreNull(resultDto, stlPayOrders);

        if (StringUtil.equals(DictCodeListExt1.收支方向_支出.getValue(), stlPayOrders.getPs())) {
            resultDto.setMyAcctNo(stlPayOrders.getMyAcctNo());
            resultDto.setCounterAcctNo(stlPayOrders.getCounterAcctNo());
        }
        else {
            resultDto.setMyAcctNo(stlPayOrders.getCounterAcctNo());
            resultDto.setCounterAcctNo(stlPayOrders.getMyAcctNo());
        }

        TrdProductDeal trdProductDeal = bussCommonMapperDelegateService.selectBySinglePrimaryKey(TrdProductDealMapper.class, stlPayOrders.getTradeId(), TrdProductDeal.class);
        resultDto.setPayType(trdProductDeal.getPs());
        resultDto.setRemark(trdProductDeal.getRemark());
        resultDto.setChlId(trdProductDeal.getChlId());
        // 划款类型选择通道回款增加本金和收益 add by renping 20190929 start
        resultDto.setPrinAmt(trdProductDeal.getPrinAmt());
        resultDto.setIntAmt(trdProductDeal.getIntAmt());
        // 划款类型选择通道回款增加本金和收益 add by renping 20190929 end

        resultDto.setBusiNo(orderId);
        resultDto.setPortfolioId(stlPayOrders.getPortfolioId());

        return resultDto;
    }


    /**
     * 查询收款账号
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterAcctNo(Map<String,Object> map){
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/getCounterAcctNo", map, SelectOptionDto.class);
    }

    /**
     * 查询付款账号
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getMyAcctNo(Map<String,Object> map){
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/getMyAcctNo", map, SelectOptionDto.class);
    }

    public Page<SelectOptionDto> selectPagePortfolioId(Map<String, Object> param)
            throws Exception {
        param.put("loginUserName", SecurityUtil.getLoginUser().getUserName());
        param.put("portfolioType", DictCodeListExt1.组合类型_投资类组合.getValue());
        return restTemplate.postForPage(
                ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getGroupIdInfo", param,
                SelectOptionDto.class);
    }

    /**
     * 获取账户余额
     * @return 账户余额
     * @see
     * @since 1.0
     */
    public BigDecimal getAmount(Map<String,Object> map){
        // 实时从接口查询，查询不到即为空
        return restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/getAmount", map, BigDecimal.class);
//        return null;
    }

    /**
     * 实体类的合成
     * @param dto 资金划拨实体类
     * @return 资金划拨实体类
     * @see
     * @since 1.0
     */
    public PtlChlFundAllocationDto setEntity(PtlChlFundAllocationDto dto) {
        dto.setActPortfolioId(dto.getPortfolioId());
        dto.setPortfolioId(AllTradeCommonUtil.exchangePortfolio(dto.getPortfolioId()));
        PtlChlFundAllocationDto ptlChlFundAllocationDto = new PtlChlFundAllocationDto();
        BeanUtil.copyPropertiesIgnoreNull(ptlChlFundAllocationDto, dto);

        // 先处理付款账户，生成可用持仓流水，生效后处理成实际及收款账户
        /**
         * 组合现金流水
         */
        List<PtlCashPositionFlow> ptlCashPositionFlows = new ArrayList<>();
        PtlCashPositionFlow ptlCashPositionFlow = new PtlCashPositionFlow();
        ptlCashPositionFlow.setPortfolioId(dto.getPortfolioId());
        ptlCashPositionFlow.setAcctId(dto.getMyAcctNo());
        ptlCashPositionFlow.setAmount(dto.getSettleAmt().negate());
        ptlCashPositionFlow.setCcy(dto.getCcy());
        ptlCashPositionFlow.setHodingType(DictCodeList.持仓类型_可用.getValue());
        ptlCashPositionFlow.setCdate(dto.getSettleDate());
        ptlCashPositionFlow.setOrderId(dto.getBussTrdTransferOrder().get(0).getOrderId());
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setSeqNo("9999");
        String seqno = baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                bussinessCodeParameter) : IDGenerator.generate();
        ptlCashPositionFlow.setSeqno(seqno);
        // 通道代码
        if (StringUtil.equals(DictCodeList.交易方向_通道回款.getValue(), dto.getPayType())) {
            ptlCashPositionFlow.setChlFinprodId(dto.getChlId());
        }
        ptlCashPositionFlows.add(ptlCashPositionFlow);

        if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
            PtlCashPositionFlow ptlCashPositionFlow1 = new PtlCashPositionFlow();
            BeanUtil.copyPropertiesIgnoreNull(ptlCashPositionFlow1, ptlCashPositionFlow);
            ptlCashPositionFlow1.setPortfolioId(dto.getActPortfolioId());
            ptlCashPositionFlow1.setSeqno(baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(bussinessCodeParameter)
                    : IDGenerator.generate());
            ptlCashPositionFlows.add(ptlCashPositionFlow1);
        }

        ptlChlFundAllocationDto.setPtlCashPositionFlow(ptlCashPositionFlows);

        /**
         * 组合现金余额
         */
        List<PtlCashPosition> ptlCashPositions = new ArrayList<>();
        PtlCashPosition ptlCashPosition = new PtlCashPosition();
        ptlCashPosition.setAcctId(dto.getMyAcctNo());
        ptlCashPosition.setAmount(ptlCashPositionFlow.getAmount());
        ptlCashPosition.setCcy(dto.getCcy());
        ptlCashPosition.setPortfolioId(dto.getPortfolioId());
        ptlCashPosition.setHodingType(DictCodeList.持仓类型_实际.getValue());
        ptlCashPosition.setCdate(dto.getSettleDate());
        // 通道代码
        if (StringUtil.equals(DictCodeList.交易方向_通道回款.getValue(), dto.getPayType())) {
            ptlCashPosition.setChlFinprodId(dto.getChlId());
        }
        ptlCashPositions.add(ptlCashPosition);

        if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
            PtlCashPosition ptlCashPosition1 = new PtlCashPosition();
            BeanUtil.copyPropertiesIgnoreNull(ptlCashPosition1, ptlCashPosition);
            ptlCashPosition1.setPortfolioId(dto.getActPortfolioId());
            ptlCashPositions.add(ptlCashPosition1);
        }

        ptlChlFundAllocationDto.setPtlCashPosition(ptlCashPositions);

        /**
         * 组合交易
         */
        List<PtlTrade> ptlTrades = new ArrayList<>();
        PtlTrade ptlTrade = new PtlTrade();
        ptlTrade.setPortfolioId(dto.getPortfolioId());
        ptlTrade.setBusiNo(dto.getTradeId());
        ptlTrade.setBusiTable(DictCodeList.业务表_结算指令表.getValue());
        ptlTrade.setBusiType(DictCodeListExt1.平安业务类型_通道划款.getValue());
        ptlTrades.add(ptlTrade);

        if (StringUtil.isNotEmpty(dto.getActPortfolioId()) && !StringUtil.equals(dto.getPortfolioId(), dto.getActPortfolioId())) {
            PtlTrade ptlTrade1 = new PtlTrade();
            BeanUtil.copyPropertiesIgnoreNull(ptlTrade1, ptlTrade);
            ptlTrade1.setPortfolioId(dto.getActPortfolioId());
            ptlTrades.add(ptlTrade1);
        }
        ptlChlFundAllocationDto.setPtlTrade(ptlTrades);
        /**
         * 金融产品交易
         */
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(dto.getTradeId());
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdProductDeal.setCcy(dto.getCcy());
        trdProductDeal.setPs(dto.getPayType());
        trdProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
        trdProductDeal.setTradeDate(dto.getSettleDate());
        trdProductDeal.setSettleDate(dto.getSettleDate());
        trdProductDeal.setRemark(dto.getRemark());
        trdProductDeal.setTradeAmt(dto.getSettleAmt());
        // add by renping 20190929 start
        trdProductDeal.setPrinAmt(dto.getPrinAmt());
        trdProductDeal.setIntAmt(dto.getIntAmt());
        // add by renping 20190929 end
        trdProductDeal.setChlId(dto.getChlId());

        // 根据组合代码，查询该组合下的产品
        String finprodId = valuationInterfService.selectFinprodIdByPortfolioId(dto.getPortfolioId());
        trdProductDeal.setFinprodId(finprodId);
//        // 金融产品类型（投管分类）
//        FinProduct finProduct = bussCommonMapperDelegateService.selectBySinglePrimaryKey(FinProductMapper.class, finprodId, FinProduct.class);
//        if (finProduct != null) {
//            trdProductDeal.setFinprodType2(finProduct.getFinprodType2()); 
//        }

        ptlChlFundAllocationDto.setTrdProductDeal(trdProductDeal);

        // 交易附属表
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
        trdProductDealAdd.setPortfolioId(dto.getPortfolioId());
        trdProductDealAdd.setGenType(DictCodeListExt1.生成方式_画面录入.getValue());
        ptlChlFundAllocationDto.setTrdProductDealAdd(trdProductDealAdd);

        /**
         * 金融产品交易扩展表
         */
        PaTrdProductDeal paTrdProductDeal = new PaTrdProductDeal();
        paTrdProductDeal.setTradeId(trdProductDeal.getTradeId());
        if (StringUtil.equals(DictCodeList.交易方向_通道划款.getValue(), dto.getPayType())) {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_通道划款.getValue());
        }
        else {
            paTrdProductDeal.setPaBussType(DictCodeListExt1.平安业务类型_通道回款.getValue());
        }
        ptlChlFundAllocationDto.setPaTrdProductDeal(paTrdProductDeal);

        if(bussTrdTransferOrderService != null){
            dto.setBussTrdTransferOrder(bussTrdTransferOrderService.save(dto.getBussTrdTransferOrder()));
            StlPayOrdersDto stlPayOrdersDto = new StlPayOrdersDto();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrdersDto, dto.getBussTrdTransferOrder().get(0));
            stlPayOrdersDto.setTradeId(dto.getTradeId());

            List<StlPayOrdersDto> list = new ArrayList<StlPayOrdersDto>();
            list.add(stlPayOrdersDto);
            ptlChlFundAllocationDto.setStlPayOrdersList(list);
        }
        ptlChlFundAllocationDto.setOperateType("NEW");
        ptlChlFundAllocationDto.setRemark(dto.getRemark());

        return ptlChlFundAllocationDto;
    }

    @Override
    public void doInsert(PtlChlFundAllocationDto entity, OperationType operationType) {
        if (!StringUtil.equals("submit", entity.getOperateType())) {
            return;
        }
        // 新建交易
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            PtlChlFundAllocationDto dto = this.setEntity(entity);
            restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/doInsert", dto, String.class);
        }
    }

    @Override
    public void doUpdate(PtlChlFundAllocationDto entity) {
        PtlChlFundAllocationDto dto = this.setEntity(entity);
        restTemplate.postForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/doInsert", dto, String.class);
    }

    @Override
    public void doDelete(String workflowId) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlChlFundAllocationDto entity, OperationType operationType) {
        entity.setOperateType(operationType.toString());
        if (!operationType.equals(OperationType.REVOKE) && !operationType.equals(OperationType.DELETE)) {
            restTemplate.postForObject(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/doComplete", this.setEntity(entity),
                    Integer.class);
        }
        else {
            restTemplate.postForObject(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/doComplete", entity,
                    Integer.class);
        }
    }

    @Override
    public void doBack(PtlChlFundAllocationDto entity) {
        AllTradeCommonUtil.deletePositionByOrderId(entity.getBussTrdTransferOrder().get(0).getOrderId());
    }

    @Override
    public void doRevoke(PtlChlFundAllocationDto entity) {
        AllTradeCommonUtil.deletePositionByOrderId(entity.getBussTrdTransferOrder().get(0).getOrderId());
    }
}
