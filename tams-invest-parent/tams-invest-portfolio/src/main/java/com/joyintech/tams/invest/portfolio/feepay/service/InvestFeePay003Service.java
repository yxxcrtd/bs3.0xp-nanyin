/*
 * 文件名：InvestFeePay002Service.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 
 * 描述：理财资管管理系统V2.0 
 * 修改人：malinlin 
 * 修改时间：2019年12月8日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.feepay.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.component.base.group.BasePtlCashPositionInfoService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlPositionHoldDto;
import com.joyintech.tams.invest.common.dto.common.InvestInterfCommDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.product.dto.FinProductExpireBaseTrdDto;
import com.joyintech.tams.invest.common.service.InvestInterfCommService;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay002Dto;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay003Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 投资端费用支付管理-新建
 *
 * @author malinlin
 * @version 1.0 2019年12月8日
 * @see InvestFeePay003Service
 * @since 1.0
 */
@Service
public class InvestFeePay003Service extends BaseService<InvestFeePay002Dto> implements BaseProcessService<InvestFeePay002Dto> {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private BussTrdTransferOrderService bussTrdTransferOrderService;

    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;
    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;
    @Autowired
    private TrdProductDealMapper trdProductDealMapper;


    /**
     * 组合可用现金和实际现金查询更新组件
     */
    @Autowired
    private BasePtlCashPositionInfoService basePtlCashPositionInfoServiceImpl;
    /**
     * 委托指令共通
     */
    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     *	 投管接口共通组件
     */
    @Autowired
    private InvestInterfCommService investInterfCommService;

    /**
     *
     * 编辑查询页面客户评级表查询分页
     * <功能详细描述>
     * @param pageParam pageParam
     * @return Page<PaRskCustomerRating>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<InvestFeePay003Dto> selectDetail(Map<String, Object> pageParam)
            throws Exception {
        String workflowId = (String)pageParam.get("workflowId");
        if (StringUtil.isNotEmpty(workflowId)) {
            InvestFeePay002Dto entity = this.getBussinessEntityByWorkflowId(workflowId);
            Page<InvestFeePay003Dto> page = new Page<>();
            page.setRoot(entity.getFeeInfoList());
            page.setRecords(entity.getFeeInfoList().size());
            return page;
        }else if(StringUtil.isNotEmpty((String)pageParam.get("tradeId"))){
            pageParam.put("totleTradeId",pageParam.get("tradeId"));
            return restTemplate.postForPage(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/InvestFeePay/selectDetail",
                    pageParam, InvestFeePay003Dto.class);
        }
        return new Page<>();
    }

    @Override
    public void doComplete(InvestFeePay002Dto entity, OperationType type) {

        // 每条组合明细循环生成一条划款指令
        List<StlPayOrders> stlPayOrdersList = this.genStlPayOrdersDto(entity);
        for (StlPayOrders stlPayOrders : stlPayOrdersList) {
            stlPayOrdersMapper.insertSelective(stlPayOrders);
        }

        // 金融产品交易汇总表
        TrdFinProductDealTotal trdFinProductDealTotal = this.genTrdFinProductDealTotal(entity);
        trdFinProductDealTotalMapper.updateByPrimaryKeySelective(trdFinProductDealTotal);
        FinProductExpireBaseTrdDto finProductExpireBaseTrdDto = genBaseTradeDealDtoList(entity);
        if (finProductExpireBaseTrdDto.getTrdProductDealList() != null) {
            finProductExpireBaseTrdDto.getTrdProductDealList().forEach(trdProductDeal -> trdProductDealMapper.updateByPrimaryKeySelective(trdProductDeal));
        }

        // 组合现金流水Entity
        List<PtlCashPositionFlowDto> ptlCashPositionFlows = new ArrayList<PtlCashPositionFlowDto>();
        for (StlPayOrders stlPayOrders : stlPayOrdersList) {
            PtlCashPositionFlowDto ptlCashPositionFlowDto = new PtlCashPositionFlowDto();
            ptlCashPositionFlowDto.setPortfolioId(stlPayOrders.getPortfolioId());
            ptlCashPositionFlowDto.setAcctId(stlPayOrders.getMyAcctNo());
            ptlCashPositionFlowDto.setHodingType(DictCodeList.持仓类型_实际.getValue());
            ptlCashPositionFlowDto.setCdate(entity.getPayDate());
            ptlCashPositionFlowDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            ptlCashPositionFlowDto.setAmount(stlPayOrders.getSettleAmt().negate());
            ptlCashPositionFlowDto.setOrderId(stlPayOrders.getOrderId());
            ptlCashPositionFlows.add(ptlCashPositionFlowDto);
        }

        PtlPositionHoldDto ptlPositionHoldDto = new PtlPositionHoldDto();
        ptlPositionHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);

        if (ptlPositionHoldDto.getPtlCashPositionFlows() != null
                && !ptlPositionHoldDto.getPtlCashPositionFlows().isEmpty()) {
            for (PtlCashPositionFlowDto ptlCashPositionFlow : ptlPositionHoldDto.getPtlCashPositionFlows()) {
                basePtlCashPositionInfoServiceImpl.insertCashPositionFlow(ptlCashPositionFlow);
            }
        }
//          委托指令流水共通调用
        //增加委托指令流水逻辑
        if(baseEtrCashPositionCommService!=null){
            PtlPositionHoldDto ptlHoldDto = new PtlPositionHoldDto();
            ptlHoldDto.setPtlCashPositionFlows(ptlCashPositionFlows);
            baseEtrCashPositionCommService.portfolioEtrCashPosition(ptlHoldDto, true);
        }

    }

    /**
     * 生成金融产品交易汇总
     *
     * @param entity 投资端费用支付Dto
     * @return 金融产品交易汇总
     * @see
     * @since 1.0
     */
    private TrdFinProductDealTotal genTrdFinProductDealTotal(InvestFeePay002Dto entity) {
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        trdFinProductDealTotal.setTotleTradeId(entity.getTradeId());
//        trdFinProductDealTotal.setBusiType(DictCodeList.共通业务类型_产品费用支付管理.getValue());
//        trdFinProductDealTotal.setFinprodId(entity.getFinprodId());
//        trdFinProductDealTotal.setVdate(entity.getStartDate());
//        trdFinProductDealTotal.setMdate(entity.getEndDate());
        trdFinProductDealTotal.setSettleDate(entity.getPayDate());
        trdFinProductDealTotal.setTradeAmt(this.getActPayAmt(entity.getFeeInfoList()));
//        trdFinProductDealTotal.setCashId(entity.getCashId());
//        trdFinProductDealTotal.setPs(DictCodeList.交易方向_产品付费.getValue());
        trdFinProductDealTotal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
        trdFinProductDealTotal.setIsCancel(DictCodeList.是否_否.getValue());
//        trdFinProductDealTotal.setSecManageAcctId(entity.getSecAcctId());
//        trdFinProductDealTotal.setPortfolioId(entity.getPtlId());
//        trdFinProductDealTotal.setRemark(entity.getRemark());
//        trdFinProductDealTotal.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        trdFinProductDealTotal.setUpdateUser(entity.getUpdateUser());
//        trdFinProductDealTotal.setCreateUser(entity.getCreateUser());
//        trdFinProductDealTotal.setCreateDept(entity.getCreateDept());
//        trdFinProductDealTotal.setWorkflowId(entity.getWorkflowId());
//        trdFinProductDealTotal.setTgBusiType("142");
//        trdFinProductDealTotal.setFinprodType(entity.getFeeType());
//        trdFinProductDealTotal.setFinprodType2(entity.getFeeType());
//        trdFinProductDealTotal.setMdate(entity.getPayDate());
//        trdFinProductDealTotal.setTgBusiType("142");
        //二级法人改造 sjj add 20200628 start
//        if(MultilevelDataAuthorityUtil.isEnabled()){
//            trdFinProductDealTotal.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(entity.getCreateUser()));
//        }
        //二级法人改造 sjj add 20200628 end
        return trdFinProductDealTotal;
    }

    private BigDecimal getActPayAmt(List<InvestFeePay003Dto> list){
        BigDecimal total =  new BigDecimal(0);
        for(InvestFeePay003Dto dto : list){
            total = total.add(dto.getActPayAmt());
        }
        return total;
    }
    /**
     * 金融产品交易共通业务逻辑处理
     *
     * @param entity 投资端费用支付Dto
     * @return 金融产品交易共通
     * @see
     * @since 1.0
     */
    private FinProductExpireBaseTrdDto genBaseTradeDealDtoList(InvestFeePay002Dto entity) {
        FinProductExpireBaseTrdDto finProductExpireBaseTrdDto = new FinProductExpireBaseTrdDto();
        List<TrdProductDeal> trdProductDealList = new ArrayList<>();
        // 费用明细
        if (!StringUtil.isEmptyList(entity.getFeeInfoList())) {
            for (InvestFeePay003Dto finProductFeePay003Dto : entity.getFeeInfoList()) {
                // 金融产品交易Entity
                TrdProductDeal trdProductDeal = new TrdProductDeal();
                trdProductDeal.setTradeId(finProductFeePay003Dto.getTradeId());
                trdProductDeal.setSettleDate(entity.getPayDate());
                trdProductDeal.setTradeAmt(finProductFeePay003Dto.getActPayAmt());
                // 金融产品交易
                trdProductDealList.add(trdProductDeal);
            }
        }
        finProductExpireBaseTrdDto.setTrdProductDealList(trdProductDealList);
        return finProductExpireBaseTrdDto;
    }

    /**
     * 生成划款指令信息
     *
     * @param entity 投资端费用支付Dto
     * @return 划款指令信息
     * @see
     * @since 1.0
     */
    private List<StlPayOrders> genStlPayOrdersDto(InvestFeePay002Dto entity) {
        entity.setBussTrdTransferOrder(bussTrdTransferOrderService.save(entity.getBussTrdTransferOrder()));
        List<StlPayOrders> StlPayOrdersList = new ArrayList<StlPayOrders>();
        for (BussTrdTransferOrderDto bussTrdTransferOrderDto : entity.getBussTrdTransferOrder()) {
            // 划款指令表Entity
            StlPayOrders stlPayOrders = new StlPayOrders();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, bussTrdTransferOrderDto);
            stlPayOrders.setTradeId(entity.getTradeId());
            stlPayOrders.setWorkflowId(entity.getWorkflowId());

            List<? extends DataDictItem> yesNoItemList = SysDataUtil.getDataDictItem(DictCode.是否.getValue());
            yesNoItemList.forEach(item -> {
                if (StringUtil.equals(item.getItemValue(), stlPayOrders.getIsNeedSend())) {
                    stlPayOrders.setIsNeedSend(item.getItemCode());
                }
            });
            stlPayOrders.setCreateTime(entity.getCreateTime());
            stlPayOrders.setUpdateTime(entity.getUpdateTime());
            StlPayOrdersList.add(stlPayOrders);
        }
        return StlPayOrdersList;
    }
}
