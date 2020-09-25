/*
 * 文件名：DepoAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.depo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.dto.DepoAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.depo.dto.DepoAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.autoperiod.depo.AutoPeriodDepoCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trddepoduration.TrdDepoDurationCommonDto;

/**
 * processor相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月14日
 * @see DepoAutoDurDealGenerateProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class DepoAutoDurDealGenerateProcessorService extends BaseService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * AutoPeriodDepoCommonService
     */
    @Autowired
    private AutoPeriodDepoCommonService autoPeriodDepoCommonService;

    /**
     * PinganBatchCommonService
     */
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    /**
     * process 数据处理
     *
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public DepoAutoDurDealGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        DepoAutoDurDealGenerateReaderResult readerResult = (DepoAutoDurDealGenerateReaderResult)param1.get(
            BatchConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (readerResult == null) {
            return null;
        }

        // 待处理交易数据参数
        TrdDepoDurationCommonDto targetTradeDataParamDto = readerResult.getTradeDataDto();
        Map<String, Object> paramMap = BeanUtil.object2Map(targetTradeDataParamDto);
        paramMap.put("adjustDate", DateUtil.format(targetTradeDataParamDto.getAdjustDate()));

        // 查询待处理交易详细数据
        TrdDepoDurationCommonDto pendingTradeData = restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdDepoDuration/findEffectiveData",
            paramMap, TrdDepoDurationCommonDto.class);

        if (pendingTradeData == null) {
            return null;
        }

        // 补充数据
        // 交易编号
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        pendingTradeData.setBusiCode(autoPeriodCommonService.buildBussinessCode(parameter));

        // 画面输入项目
        // 资金到账日
        Date platDate = (Date) param1.get(BatchConstant.PLATE_DATE);
        pendingTradeData.setTradeDate(platDate);

        // 构造将要登录的业务流程表数据
        BaseTrdDurDbInertDto baseTrdDurDbInertDto = this.genDataForInsertDto(pendingTradeData);

        DepoAutoDurDealGenerateResult resultDto = new DepoAutoDurDealGenerateResult();
        resultDto.setBaseTrdDurDbInertDto(baseTrdDurDbInertDto);
        resultDto.setProdId("存款类资产自动存续期生成处理");

        return resultDto;
    }

    /**
     * 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    private BaseTrdDurDbInertDto genDataForInsertDto(TrdDepoDurationCommonDto entity) {
        BaseTrdDurDbInertDto baseTrdDurDbInertDto = new BaseTrdDurDbInertDto();

        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        WflTrdFinProductDealTotal trdFinProductDealTotal = AutoPeriodDepoCommonService.gen汇总流程表(entity);
        baseTrdDurDbInertDto.setWflTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity);
        baseTrdDurDbInertDto.setWflStlPayOrdersList(stlPayOrdersDtos);

        // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = AutoPeriodDepoCommonService.gen汇总扩展业务流程(entity,
            trdFinProductDealTotal);
        baseTrdDurDbInertDto.setWflPaTrdFinDealTotal(wflPaTrdFinDealTotal);

        // 金融产品交易Entity -> 金融产品交易业务流程表
        List<WflTrdProductDeal> wflTrdProductDeals = autoPeriodDepoCommonService.gen交易业务流程(entity);
        baseTrdDurDbInertDto.setWflTrdProductDealList(wflTrdProductDeals);

        return baseTrdDurDbInertDto;
    }

    /**
     * 生成划款指令
     *
     * @param entity 交易数据
     * @return 划款指令
     * @see
     * @since 1.0
     */
    private List<WflStlPayOrders> gen划款指令流程(TrdDepoDurationCommonDto entity) {
        // 自动生成划款指令用deal
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setCcy(entity.getCcy());
        trdProductDeal.setTradeAmt(entity.getTradeAmt());
        trdProductDeal.setPs(entity.getPs());
        trdProductDeal.setTradeDate(entity.getTradeDate());
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_存款.getValue());

        BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
        parameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        parameterDto.setBankLendingType(null);
        parameterDto.setPortfolioId(entity.getPortfolioId());
        parameterDto.setDeal(trdProductDeal);

        return pinganBatchCommonService.generateSettleOrder(parameterDto);
    }
}
