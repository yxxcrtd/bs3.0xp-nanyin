/*
 * 文件名：TrdLendDurationDealEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.period.trdlendduration.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.trade.BaseTradeProductDealCommService;
import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.autoperiod.lend.AutoPeriodLendCommonService;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;
import com.joyintech.tams.invest.period.trddepoduration.service.TrdDepoDuration001Service;
import com.joyintech.tams.invest.period.trdlendduration.dto.TrdLendDuration001Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年11月11日
 * @see TrdLendDurationDealEntity
 * @since 1.0
 */
@Service
public class TrdLendDurationDealEntity extends BaseService<TrdLendDuration001Dto>{
    @Autowired
    private BaseGetBusinessId baseGetBusinessIdImpl;

    @Autowired
    private TrdDepoDuration001Service trdDepoDuration001Service;

    @Autowired
    private BaseTradeProductDealCommService baseTradeProductDealCommServiceImpl;

    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderImplService;

    /**
     * AutoPeriodLendCommonService
     */
    @Autowired
    private AutoPeriodLendCommonService autoPeriodLendCommonService;

    public List<WflTrdProductDeal> gen交易业务流程(TrdLendDuration001Dto entity, List<WflPaTrdProductDeal> wflPaTrdProductDeals,
                                             List<WflTrdProductDealAdd> wflTrdProductDealAdds) {
        return autoPeriodLendCommonService.gen交易业务流程(entity, wflPaTrdProductDeals, wflTrdProductDealAdds);
    }

    public WflPaTrdFinDealTotal gen汇总扩展业务流程(TrdLendDuration001Dto entity,
                                             WflTrdFinProductDealTotal trdFinProductDealTotal) {
        return AutoPeriodLendCommonService.gen汇总扩展业务流程(entity, trdFinProductDealTotal);
    }

    public List<WflStlPayOrders> gen划款指令流程(TrdLendDuration001Dto entity) {
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(
            entity.getBussTrdTransferOrder());
        List<WflStlPayOrders> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                WflStlPayOrders dto = new WflStlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                dto.setTradeId(entity.getTotleTradeId());
                dto.setWorkflowId(entity.getWorkflowId());
                dto.setStatus(DictCodeList.数据状态_新建中.getValue());
                dto.setCreateDept(entity.getCreateDept());
                dto.setUpdateTime(entity.getUpdateTime());
                dto.setCreateUser(entity.getCreateUser());
                dto.setCreateTime(entity.getCreateTime());
                dto.setWorkflowStatus(DictCodeList.数据状态_新建.getValue());
                stlPayOrdersDtos.add(dto);
            });
        }
        return stlPayOrdersDtos;
    }

    public static WflTrdFinProductDealTotal gen汇总流程表(TrdLendDuration001Dto entity) {
        return AutoPeriodLendCommonService.gen汇总流程表(entity);
    }
    public FinProductTradeDto genFinProductTradeDto(TrdLendDuration001Dto entity) {
        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
        TrdProductDeal trdProductDeal = getTrdProductDeal(entity);

        // 划款指令表Entity
        List<BussTrdTransferOrderDto> stlPayOrdersList = bussTrdTransferOrderImplService.save(
            entity.getBussTrdTransferOrder());
        List<StlPayOrdersDto> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                StlPayOrdersDto dto = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                dto.setTradeId(entity.getBusiCode());
                stlPayOrdersDtos.add(dto);
            });
        }
        TrdProductDealAdd add = new TrdProductDealAdd();
        add.setPortfolioId(entity.getPortfolioId());

        baseTradeProductDealDto.setTrdProductDealAdd(add);
        baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
        baseTradeProductDealDto.setStlPayOrdersList(stlPayOrdersDtos);
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        return finProductTradeDto;
    }
    /**
     * 为调用共通组件组装的entity
     *
     * @param entity
     * @return
     */
    public static TrdProductDeal getTrdProductDeal(TrdLendDuration001Dto entity) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setTradeId(entity.getTotleTradeId());
        trdProductDeal.setBusiType(DictCodeList.共通业务类型_拆借存续期交易.getValue());
        trdProductDeal.setFinprodId(entity.getFinprodId());
        // trdProductDeal.setFinprodType(); TODO 估值核算 金融产品类型
        trdProductDeal.setFinprodType2(entity.getFinprodType2());
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setPs(DictCodeList.交易方向_到期.getValue());
        trdProductDeal.setTradeDate(entity.getTradeDate());
        trdProductDeal.setVdate(entity.getVdate());
        trdProductDeal.setCounterId(entity.getCounterId());
        trdProductDeal.setMdate(entity.getMdate());
        trdProductDeal.setSettleDate(entity.getSettleDate());
        trdProductDeal.setShareAmt(entity.getPrinAmt());
        trdProductDeal.setPrinAmt(entity.getPrinAmt());
        trdProductDeal.setIntAmt(
            ArithmeticUtil.subtract(entity.getTheoryAmt(), entity.getPrinAmt()));
        trdProductDeal.setTradeAmt(entity.getTradeAmt());
        trdProductDeal.setCounterId(entity.getCounterId());
        trdProductDeal.setTrader(entity.getTrader());
        
        // 一体化对应etliu start
        trdProductDeal.setFpriceAmt(trdProductDeal.getTradeAmt());
        // 一体化对应etliu end
        trdProductDeal.setRemark(entity.getRemark());
        
        return trdProductDeal;
    }
}
