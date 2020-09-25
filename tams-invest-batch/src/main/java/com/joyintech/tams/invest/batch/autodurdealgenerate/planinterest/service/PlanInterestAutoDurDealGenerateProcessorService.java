/*
 * 文件名：PlanInterestAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.dto.PlanInterestAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.planinterest.dto.PlanInterestAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.autoperiod.planinterest.AutoPeriodPlanInterestCommonService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;
import com.joyintech.tams.invest.common.dto.period.trdplanintduration.TrdPlanInterestDurationDto;

/**
 * processor相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see PlanInterestAutoDurDealGenerateProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class PlanInterestAutoDurDealGenerateProcessorService extends BaseService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * PinganBatchCommonService
     */
    @Autowired
    private PinganBatchCommonService pinganBatchCommonService;

    /**
     * AutoPeriodPlanInterestCommonService
     */
    @Autowired
    private AutoPeriodPlanInterestCommonService autoPeriodPlanInterestCommonService;

    /**
     * process 数据处理
     *
     * @param param1 传递参数
     * @return BookGenerateResult
     * @see
     * @since 1.0
     */
    public PlanInterestAutoDurDealGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        PlanInterestAutoDurDealGenerateReaderResult readerResult = (PlanInterestAutoDurDealGenerateReaderResult)param1.get(
            BatchConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (readerResult == null) {
            return null;
        }

        // 待处理交易数据参数
        TrdPlanInterestDurationDto targetTradeDataParamDto = readerResult.getTradeDataDto();
        Map<String, Object> paramMap = BeanUtil.object2Map(targetTradeDataParamDto);

        // 查询待处理交易详细数据
        TrdPlanInterestDurationDto pendingTradeData = restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdPlanInterestDuration/findEffectiveData",
            paramMap, TrdPlanInterestDurationDto.class);

        if (pendingTradeData == null) {
            return null;
        }

        // 补充数据
        // 业务代码汇总交易编号
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        pendingTradeData.setBusiCode(autoPeriodCommonService.buildBussinessCode(parameter));

        // 画面输入项目 TODO
        Date platDate = (Date) param1.get(BatchConstant.PLATE_DATE);
        pendingTradeData.setArrivalDate(DateUtil.format(platDate));

        // 构造将要登录的业务流程表数据
        BaseTotalTradeInfoDto baseTotalTradeInfoDto = genDataForInsertDto(pendingTradeData);

        PlanInterestAutoDurDealGenerateResult resultDto = new PlanInterestAutoDurDealGenerateResult();
        resultDto.setBaseTotalTradeInfoDto(baseTotalTradeInfoDto);
        resultDto.setProdId("项目类资产自动存续期生成处理");

        return resultDto;
    }

    /**
     * 构造将要登录的业务流程表数据
     *
     * @param entity 参数dto
     * @see
     * @since 1.0
     */
    private BaseTotalTradeInfoDto genDataForInsertDto(TrdPlanInterestDurationDto entity) {
        BaseTotalTradeInfoDto result = autoPeriodPlanInterestCommonService.genFinProductTradeDto(
            entity);

        // 处理划款指令
        List<FinAssetIntInfo> 利息明细 = entity.getFinAssetIntInfoList();
        if (!StringUtil.isEmptyList(利息明细) && !StringUtil.isEmptyList(result.getBaseTradeInfoDtoList())) {
            int dataIndex = 0;
            for (FinAssetIntInfo finAssetIntInfo : 利息明细) {
                // 利息金额大于0时，登一笔利息
                if (finAssetIntInfo.getFpriceAmt().compareTo(
                    finAssetIntInfo.getReturnAmount()) > 0) {

                    StlPayOrders stlPayOrdersDtos = gen划款指令流程(entity, finAssetIntInfo);

                    if (result.getBaseTradeInfoDtoList().size() > dataIndex) {
                        result.getBaseTradeInfoDtoList().get(dataIndex).setStlPayOrders(stlPayOrdersDtos);
                        dataIndex++ ;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 生成划款指令
     *
     * @param entity 交易数据
     * @return 划款指令
     * @see
     * @since 1.0
     */
    private StlPayOrders gen划款指令流程(TrdPlanInterestDurationDto entity, FinAssetIntInfo finAssetIntInfo) {
        // 自动生成划款指令用deal
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        trdProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        trdProductDeal.setPs(AutoPeriodCommonService.change现金流类型To交易方向(entity.getCashType()));
        trdProductDeal.setTradeDate(DateUtil.parse(entity.getArrivalDate()));
        trdProductDeal.setTradeAmt(finAssetIntInfo.getFpriceAmt());
        trdProductDeal.setCcy(entity.getCcy());

        BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
        parameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        parameterDto.setBankLendingType(null);
        parameterDto.setPortfolioId(finAssetIntInfo.getPortfolioId());
        parameterDto.setDeal(trdProductDeal);

        List<WflStlPayOrders> resultList = pinganBatchCommonService.generateSettleOrder(parameterDto);
        if (!StringUtil.isEmptyList(resultList)) {
            StlPayOrders stlPayOrders = new StlPayOrders();
            BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, resultList.get(0));
            return stlPayOrders;
        }

        return null;
    }
}
