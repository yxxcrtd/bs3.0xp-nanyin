/*
 * 文件名：ExchangeAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年7月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.exchange.dto.ExchangeAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.autoperiod.exchange.AutoPeriodExchangeCommonService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDuration002ProcessDto;
import com.joyintech.tams.invest.common.dto.period.trdexchangeduration.TrdExchangeDurationDto;

/**
 * 外汇远期掉期到期交易自动生成processor相关业务处理
 *
 * @author renping
 * @version 1.0 2019年7月19日
 * @see ExchangeAutoDurDealGenerateProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class ExchangeAutoDurDealGenerateProcessorService extends BaseService {

    /**
     * PinganBatchCommonService
     */
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    /**
     * AutoPeriodExchangeCommonService
     */
    @Autowired
    private AutoPeriodExchangeCommonService autoPeriodExchangeCommonService;

    /**
     * process 数据处理
     *
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public ExchangeAutoDurDealGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        ExchangeAutoDurDealGenerateReaderResult readerResult = (ExchangeAutoDurDealGenerateReaderResult)param1.get(
            BatchConstant.READER_RESULT);
        // 若reader传递的参数为空，不进行后续处理
        if (readerResult == null) {
            return null;
        }
        // 待处理交易数据
        TrdExchangeDurationDto trdExchangeDurationDto = readerResult.getTradeDataDto();

        // 构造将要登录的业务流程表数据
        genDataForInsertDto(trdExchangeDurationDto);

        // 流程中数据对象(外汇远期差额清算发流程处理)
        TrdExchangeDuration002ProcessDto pendingTradeData = null;
        if (StringUtil.equals(DictCodeListExt2.结算方式_差额清算.getValue(),
            trdExchangeDurationDto.getTrdExchangeDurDbInertDto().getTrdProductDeal().getDeliveryType())) {
            // 业务流程表数据
            autoPeriodExchangeCommonService.genExchangeDataForInsertDto(trdExchangeDurationDto);
            // 子组合改造(母组合转子组合)
            TrdExchangeDurDbInertDto inertDto = trdExchangeDurationDto.getTrdExchangeDurDbInertDto();
            inertDto.getTrdProductDealAdd().setPortfolioId(inertDto.getPortfolioId());
            if(inertDto.getPtlTrade()!=null){
                inertDto.getPtlTrade().setPortfolioId(inertDto.getPortfolioId());
            }
            // 流程中数据对象
            pendingTradeData = new TrdExchangeDuration002ProcessDto();
            BeanUtil.copyPropertiesIgnoreNull(pendingTradeData, trdExchangeDurationDto);
        }
        ExchangeAutoDurDealGenerateResult resultDto = new ExchangeAutoDurDealGenerateResult();
        resultDto.setTrdExchangeDurDbInertDto(
            trdExchangeDurationDto.getTrdExchangeDurDbInertDto());
        resultDto.setTrdExchangeDuration002ProcessDto(pendingTradeData);
        resultDto.setProdId("外汇远期掉期到期交易生成处理");

        return resultDto;
    }

    /**
     * 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    private void genDataForInsertDto(TrdExchangeDurationDto entity) {
        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity);
        entity.getTrdExchangeDurDbInertDto().setWflStlPayOrdersList(stlPayOrdersDtos);
        if (!StringUtil.isEmptyList(stlPayOrdersDtos)) {
            List<StlPayOrders> stlPayOrdersList = new ArrayList<>();
            stlPayOrdersDtos.forEach(wflStlPayOrders -> {
                StlPayOrders stlPayOrders = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, wflStlPayOrders);
                stlPayOrdersList.add(stlPayOrders);
            });
            entity.getTrdExchangeDurDbInertDto().setStlPayOrdersList(stlPayOrdersList);;
        }
    }

    /**
     * 生成划款指令
     *
     * @param entity 交易数据
     * @return 划款指令
     * @see
     * @since 1.0
     */
    private List<WflStlPayOrders> gen划款指令流程(TrdExchangeDurationDto entity) {
        TrdExchangeDurDbInertDto trdExchangeDurDbInertDto = entity.getTrdExchangeDurDbInertDto();
        TrdProductDeal dto = trdExchangeDurDbInertDto.getTrdProductDeal();
        if (StringUtil.equals(dto.getDeliveryType(), DictCodeListExt2.结算方式_差额清算.getValue())) {
            return null;
        }
        // 自动生成划款指令用deal
        List<TrdProductDeal> trdProductDealList = autoPeriodExchangeCommonService.getTrdProductDeal(entity);
        List<WflStlPayOrders> wflStlPayOrderslist= new ArrayList<>();
        for(TrdProductDeal deal : trdProductDealList){
            BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
            parameterDto.setIsTotalTrade(BussinessDictCodeList.是否_是.getValue());
            parameterDto.setBankLendingType(null);
            parameterDto.setPortfolioId(
                trdExchangeDurDbInertDto.getTrdProductDealAdd().getPortfolioId());
            parameterDto.setDeal(deal);
            wflStlPayOrderslist.addAll(pinganBatchCommonService.generateSettleOrder(parameterDto));
        }
        return wflStlPayOrderslist;
    }

}
