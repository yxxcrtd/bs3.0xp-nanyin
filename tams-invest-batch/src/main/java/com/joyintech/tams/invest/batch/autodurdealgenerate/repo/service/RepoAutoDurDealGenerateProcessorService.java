/*
 * 文件名：RepoAutoDurDealGenerateProcessorService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflPaTrdFinDealTotal;
import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateResult;
import com.joyintech.tams.invest.batch.common.dto.BatchBussSettleOrderParameterDto;
import com.joyintech.tams.invest.batch.common.service.PinganBatchCommonService;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.autoperiod.repo.AutoPeriodRepoCommonService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.dto.period.common.BaseTrdDurDbInertDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.FinProductTradeDto;

/**
 * processor相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateProcessorService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class RepoAutoDurDealGenerateProcessorService extends BaseService {

    /**
     * AutoPeriodCommonService
     */
    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    /**
     * AutoPeriodRepoCommonService
     */
    @Autowired
    private AutoPeriodRepoCommonService autoPeriodRepoCommonService;

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
    public RepoAutoDurDealGenerateResult execute(Map<String, Object> param1) {
        // 获取reader返回值
        RepoAutoDurDealGenerateReaderResult readerResult = (RepoAutoDurDealGenerateReaderResult)param1.get(
            BatchConstant.READER_RESULT);

        // 若reader传递的参数为空，不进行后续处理
        if (readerResult == null) {
            return null;
        }

        // 待处理交易数据参数
        TrdRepoDurationDto targetTradeDataParamDto = readerResult.getTradeDataDto();
        Map<String, Object> paramMap = BeanUtil.object2Map(targetTradeDataParamDto);

        // 查询待处理交易详细数据
        TrdRepoDurationDto pendingTradeData = restTemplate.postForObject(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findEffectiveData",
            paramMap, TrdRepoDurationDto.class);

        if (pendingTradeData == null) {
            return null;
        }

        // 补充数据
        // 业务代码汇总交易编号
        BussinessCodeParameter parameter = new BussinessCodeParameter();
        parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产存续期交易);
        pendingTradeData.setBusiCode(autoPeriodCommonService.buildBussinessCode(parameter));

        // 画面设置的项目
        pendingTradeData.setSettleDate(pendingTradeData.getmDate());
        pendingTradeData.setTradeAmt(pendingTradeData.getTheoryAmt());
        // 组合转换
        pendingTradeData.setActPortfolioId(pendingTradeData.getPortfolioId());
        pendingTradeData.setPortfolioId(
            AllTradeCommonUtil.exchangePortfolio(pendingTradeData.getPortfolioId()));

        // 构造将要登录的有效数据
        BaseTrdDurDbInertDto baseTrdDurDbInertDto = genDataForInsertDto(pendingTradeData);

        RepoAutoDurDealGenerateResult resultDto = new RepoAutoDurDealGenerateResult();
        resultDto.setBaseTrdDurDbInertDto(baseTrdDurDbInertDto);

        resultDto.setFinProductTradeDto(genFinProductTradeDto(baseTrdDurDbInertDto, pendingTradeData));
        resultDto.setProdId("回购自动存续期生成处理");

        return resultDto;
    }

    /**
     * 构造将要登录的有效数据
     *
     * @param entity 参数dto
     * @return 将要登录的有效数据
     * @see
     * @since 1.0
     */
    private BaseTrdDurDbInertDto genDataForInsertDto(TrdRepoDurationDto entity) {
        BaseTrdDurDbInertDto baseTrdDurDbInertDto = new BaseTrdDurDbInertDto();
        baseTrdDurDbInertDto.setActPortfolioId(entity.getActPortfolioId());
        // 金融产品交易信息DTO.金融产品交易汇总Entity -> 金融产品交易汇总业务流程表
        WflTrdFinProductDealTotal wflTrdFinProductDealTotal = AutoPeriodRepoCommonService.gen汇总流程表(
            entity, DictCodeList.共通业务类型_质押式回购存续期.getValue());
        wflTrdFinProductDealTotal.setFpriceAmt(entity.getTheoryAmt());
        baseTrdDurDbInertDto.setWflTrdFinProductDealTotal(wflTrdFinProductDealTotal);
        // 金融产品交易汇总表
        TrdFinProductDealTotal trdFinProductDealTotal = new TrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(trdFinProductDealTotal, wflTrdFinProductDealTotal);
        baseTrdDurDbInertDto.setTrdFinProductDealTotal(trdFinProductDealTotal);

        // 金融产品交易信息DTO.划款指令表Entity -> 划款指令业务流程表
        List<WflStlPayOrders> stlPayOrdersDtos = gen划款指令流程(entity);
        baseTrdDurDbInertDto.setWflStlPayOrdersList(stlPayOrdersDtos);
        if (stlPayOrdersDtos != null) {
            List<StlPayOrders> stlPayOrdersList = new ArrayList<>();
            for (WflStlPayOrders wflStlPayOrders : stlPayOrdersDtos) {
                StlPayOrders stlPayOrders = new StlPayOrders();
                BeanUtil.copyPropertiesIgnoreNull(stlPayOrders, wflStlPayOrders);
                stlPayOrdersList.add(stlPayOrders);
            }
            baseTrdDurDbInertDto.setStlPayOrdersList(stlPayOrdersList);
        }

        // 金融产品交易汇总扩展Entity -> 金融产品交易汇总扩展业务流程表
        WflPaTrdFinDealTotal wflPaTrdFinDealTotal = AutoPeriodRepoCommonService.gen汇总扩展业务流程(entity,
            wflTrdFinProductDealTotal);
        baseTrdDurDbInertDto.setWflPaTrdFinDealTotal(wflPaTrdFinDealTotal);
        // 金融产品交易汇总扩展表
        PaTrdFinProductDealTotal paTrdFinProductDealTotal = new PaTrdFinProductDealTotal();
        BeanUtil.copyPropertiesIgnoreNull(paTrdFinProductDealTotal, wflPaTrdFinDealTotal);
        baseTrdDurDbInertDto.setPaTrdFinProductDealTotal(paTrdFinProductDealTotal);

        // 金融产品交易Entity -> 金融产品交易业务流程表
        List<WflTrdProductDeal> wflTrdProductDeals = autoPeriodRepoCommonService.gen交易业务流程(entity);
        baseTrdDurDbInertDto.setWflTrdProductDealList(wflTrdProductDeals);
        if (wflTrdProductDeals != null) {
            List<TrdProductDeal> trdProductDealList = new ArrayList<>();
            List<TrdProductDealAdd> trdProductDealAddList = new ArrayList<>();
            List<PtlTrade> ptlTradeList = new ArrayList<>();
            for (WflTrdProductDeal wflTrdProductDeal : wflTrdProductDeals) {
                TrdProductDeal trdProductDeal = new TrdProductDeal();
                BeanUtil.copyPropertiesIgnoreNull(trdProductDeal, wflTrdProductDeal);
                trdProductDealList.add(trdProductDeal);
                TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
                trdProductDealAdd.setTradeId(trdProductDeal.getTradeId());
                trdProductDealAdd.setPortfolioId(entity.getPortfolioId());
                trdProductDealAddList.add(trdProductDealAdd);
                PtlTrade ptlTrade = new PtlTrade();
                ptlTrade.setPortfolioId(entity.getPortfolioId());
                ptlTrade.setBusiNo(wflTrdProductDeal.getTradeId());
                ptlTrade.setBusiTable(DictCodeList.业务表_金融产品表.getValue());
                ptlTrade.setBusiId(entity.getFinprodId());
                ptlTrade.setBusiType(DictCodeList.共通业务类型_质押式回购存续期.getValue());
                ptlTrade.setSecManageAcctId(entity.getSecManageAcctId());
                ptlTrade.setSettleDate(DateUtil.parse(entity.getSettleDate()));
                ptlTradeList.add(ptlTrade);
            }
            baseTrdDurDbInertDto.setTrdProductDealList(trdProductDealList);
            baseTrdDurDbInertDto.setTrdProductDealAdds(trdProductDealAddList);
            baseTrdDurDbInertDto.setPtlTrades(ptlTradeList);
        }
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
    private List<WflStlPayOrders> gen划款指令流程(TrdRepoDurationDto entity) {

        // 自动生成划款指令用deal
        TrdProductDeal trdProductDeal = AutoPeriodRepoCommonService.getTrdProductDeal(entity);
        trdProductDeal.setTradeAmt(entity.getTradeAmt());
        trdProductDeal.setSettleDate(DateUtil.parse(entity.getSettleDate()));

        BatchBussSettleOrderParameterDto parameterDto = new BatchBussSettleOrderParameterDto();
        parameterDto.setIsTotalTrade(DictCodeList.是否_是.getValue());
        parameterDto.setBankLendingType(null);
        parameterDto.setPortfolioId(entity.getPortfolioId());
        parameterDto.setDeal(trdProductDeal);

        return pinganBatchCommonService.generateSettleOrder(parameterDto);
    }

    /**
     * 生成持仓共通使用的DTO
     * 
     * @param baseTrdDurDbInertDto 登录用DTO
     * @param entity 详情DTO
     * @return 持仓共通使用的DTO
     * @see
     * @since 1.0
     */
    private FinProductTradeDto genFinProductTradeDto(BaseTrdDurDbInertDto baseTrdDurDbInertDto,
                                                     TrdRepoDurationDto entity) {
        // 5.构造调用组件的输入参数DTO
        FinProductTradeDto finProductTradeDto = new FinProductTradeDto();
        List<BaseTradeProductDealDto> baseTradeProductDealDtos = new ArrayList<>();
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        baseTradeProductDealDtos.add(baseTradeProductDealDto);
        TrdProductDeal trdProductDeal = AutoPeriodRepoCommonService.getTrdProductDeal(entity);
        trdProductDeal.setFpriceAmt(entity.getTheoryAmt());

        // 划款指令表Entity
        List<StlPayOrders> stlPayOrdersList = baseTrdDurDbInertDto.getStlPayOrdersList();
        List<StlPayOrdersDto> stlPayOrdersDtos = new ArrayList<>();
        if (stlPayOrdersList != null) {
            stlPayOrdersList.forEach(stlPayOrders -> {
                StlPayOrdersDto dto = new StlPayOrdersDto();
                BeanUtil.copyPropertiesIgnoreNull(dto, stlPayOrders);
                stlPayOrdersDtos.add(dto);
            });
        }
        TrdProductDealAdd add = new TrdProductDealAdd();
        add.setPortfolioId(entity.getPortfolioId());
        baseTradeProductDealDto.setTrdProductDealAdd(add);
        baseTradeProductDealDto.setTrdProductDeal(trdProductDeal);
        baseTradeProductDealDto.setStlPayOrdersList(stlPayOrdersDtos);
        baseTradeProductDealDto.setAssetType2(entity.getType6());
        finProductTradeDto.setBaseTradeProductDealDtos(baseTradeProductDealDtos);
        finProductTradeDto.setAssetType2(entity.getType6());
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", entity.getFinprodId());
        List<RepoInfo> repoInfoList = restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/findRepoInfo", map,
            RepoInfo.class);
        if (repoInfoList != null && repoInfoList.size() > 0) {
            // 质押券信息
            BussRepoSecDto bussRepoSecDto = new BussRepoSecDto();
            finProductTradeDto.setBussRepoSec(bussRepoSecDto);
            List<PledgeBondInfoDto> pledgeBondList = new ArrayList<>();
            repoInfoList.forEach(item -> {
                PledgeBondInfoDto pledgeBondInfoDto = new PledgeBondInfoDto();
                pledgeBondInfoDto.setBondCode(item.getFinprodMarketId());
                pledgeBondInfoDto.setPledgePrin(item.getQty());
                pledgeBondInfoDto.setFinprodId(item.getPledgeFinprodId());
                pledgeBondList.add(pledgeBondInfoDto);
            });
            bussRepoSecDto.setPledgeBondList(pledgeBondList);
        }
        return finProductTradeDto;
    }
}
