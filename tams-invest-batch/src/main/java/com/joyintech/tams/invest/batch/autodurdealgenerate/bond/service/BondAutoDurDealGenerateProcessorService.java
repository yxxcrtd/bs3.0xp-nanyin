/*
 * 文件名：BondAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto.BondAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto.BondAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.autoperiod.bond.AutoPeriodBondCommonService;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;

/**
 * processor相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see BondAutoDurDealGenerateProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class BondAutoDurDealGenerateProcessorService extends BaseService {

    /**
     * PinganBatchCommonService
     */
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    /**
     * AutoPeriodBondCommonService
     */
    @Autowired
    private AutoPeriodBondCommonService autoPeriodBondCommonService;

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * process 数据处理
     *
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public BondAutoDurDealGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        BondAutoDurDealGenerateReaderResult readerResult = (BondAutoDurDealGenerateReaderResult)param1.get(
            BatchConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (readerResult == null) {
            return null;
        }

        // 待处理交易数据参数
        TrdBondDurationDto targetTradeDataParamDto = readerResult.getTradeDataDto();
        Map<String, Object> paramMap = BeanUtil.object2Map(targetTradeDataParamDto);

        // 查询待处理交易详细数据
        TrdBondDurationDto pendingTradeData = restTemplate.postForObject(ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/findEffectiveData",
            paramMap, TrdBondDurationDto.class);

        if (pendingTradeData == null) {
            return null;
        }

        // 实收本息总额: 设置为 全价总额 理论本息总额
        pendingTradeData.setTradeAmt(pendingTradeData.getFpriceAmt());

        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        pendingTradeData.setBusiCode(autoPeriodCommonService.buildBussinessCode(parameter));

        // 构造将要登录的业务流程表数据
        genDataForInsertDto(pendingTradeData);

        BondAutoDurDealGenerateResult resultDto = new BondAutoDurDealGenerateResult();
        resultDto.setTrdBondDurDbInertDto(pendingTradeData.getTrdBondDurDbInertDto());
        resultDto.setProdId("债券自动存续期生成处理");

        return resultDto;
    }

    /**
     * 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    private void genDataForInsertDto(TrdBondDurationDto entity) {
        autoPeriodBondCommonService.genBondDataForInsertDto(entity);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity);

        TrdBondDurDbInertDto trdBondDurDbInertDto = entity.getTrdBondDurDbInertDto();
        trdBondDurDbInertDto.setWflStlPayOrdersList(stlPayOrdersDtos);
        entity.setTrdBondDurDbInertDto(trdBondDurDbInertDto);
    }

    /**
     * 生成划款指令
     *
     * @param entity 交易数据
     * @return 划款指令
     * @see
     * @since 1.0
     */
    private List<WflStlPayOrders> gen划款指令流程(TrdBondDurationDto entity) {
        // 自动生成划款指令用deal
        TrdProductDeal trdProductDeal = AutoPeriodBondCommonService.getTrdProductDeal(entity);
        BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
        parameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        parameterDto.setBankLendingType(null);
        parameterDto.setPortfolioId(entity.getPortfolioId());
        parameterDto.setDeal(trdProductDeal);

        return pinganBatchCommonService.generateSettleOrder(parameterDto);
    }
}
