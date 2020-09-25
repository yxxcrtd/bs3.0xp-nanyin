package com.joyintech.tams.invest.product.investmentevaluate.service;

import com.jixu.common.util.DateUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.tams.component.business.evaluation.EvalIndexAlgorithmService;
import com.joyintech.tams.component.business.evaluation.dao.BokPositionPrdReferenceMapper;
import com.joyintech.tams.component.business.evaluation.dto.AverageStrategyDto;
import com.joyintech.tams.component.business.evaluation.dto.FinancialProductInformationDto;
import com.joyintech.tams.component.business.evaluation.dto.TimePointStrategyDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.evaluationentrance.EvaluationEntrance;
import com.joyintech.tams.invest.product.evaluationentrance.dto.EvaluationRateDto;
import com.joyintech.tams.invest.product.investmentevaluate.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * FinInvestmentEvaluate001Service
 */
@Service
public class FinInvestmentEvaluate001Service extends CommonExportService {
    //调用共同接口方法
    @Autowired
    EvaluationEntrance evaluationEntrance;
    @Autowired
    BokPositionPrdReferenceMapper bokPositionPrdReferenceMapper;
    @Autowired
    EvalIndexAlgorithmService evalIndexAlgorithmService;
    public Page<FinInvestmentEvaluate001Dto> findPage2(Map<String, Object> pageParam) {

        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage2", pageParam,
                FinInvestmentEvaluate001Dto.class);}
    public Page<BokPositionPrdNetvalueDto> findPage3(Map<String, Object> pageParam ) {


        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage3", pageParam,
                BokPositionPrdNetvalueDto.class);
    }
    public List<BokPositionPrdNetvalueDto> findPage3e(Map<String, Object> pageParam ) {
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage3e", pageParam,
                BokPositionPrdNetvalueDto.class);
    }
    /**
     * 调用 金融产品信息+组合+区间起始日终止日
     */
    public FinInvestmentEvaluate002Dto finProductInfo(Map<String, Object> pageParam){
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findProductInfo",
                pageParam,FinInvestmentEvaluate002Dto.class);

    }
    /**
     *组合信息查询
     */
    public Page<FinInvestmentEvaluate003Dto> findPage(Map<String, Object> pageParam){
        Page<FinInvestmentEvaluate003Dto> findPage=restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage",
                pageParam, FinInvestmentEvaluate003Dto.class);
        System.out.println(findPage);
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage",
                pageParam, FinInvestmentEvaluate003Dto.class);
    }
    /**
     * 封装公共方法
     *
     */
    public FinancialProductInformationDto financialProductInfo(FinInvestmentEvaluate002Dto finInvestmentEvaluate002Dto){
        FinancialProductInformationDto financialProductInformationDto=new FinancialProductInformationDto();
        financialProductInformationDto.setFinProdId(finInvestmentEvaluate002Dto.getFinprodId());
        financialProductInformationDto.setBranch(finInvestmentEvaluate002Dto.getBranch());
        financialProductInformationDto.setFinProdType(finInvestmentEvaluate002Dto.getFinprodType2());
        financialProductInformationDto.setProcessingMode(finInvestmentEvaluate002Dto.getDealMode());
        financialProductInformationDto.setRevenueType(finInvestmentEvaluate002Dto.getProfitType());
        financialProductInformationDto.setValueDate(finInvestmentEvaluate002Dto.getVdate());
        return financialProductInformationDto;
    }
    /**
     * 收益指标查询
     */
    public List findPage4(Map<String, Object> pageParam) throws ParseException {
       FinInvestmentEvaluate002Dto finInvestmentEvaluate002Dto=finProductInfo(pageParam);

        List page=new ArrayList();
        if (finInvestmentEvaluate002Dto==null){return page;}
            FinancialProductInformationDto financialProductInformationDto=financialProductInfo(finInvestmentEvaluate002Dto);
            //区间年化收益率
            List<EvaluationRateDto> yearYield=evaluationEntrance.yearYield
                    (DateUtil.convertStr2Date2( (String) pageParam.get("startDate")), DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
//            Map<Date, EvaluationRateDto> yearYieldMap = yearYield.stream()
//                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //净值增长率
            List<EvaluationRateDto> netValueGrowthRate=evaluationEntrance.netValueGrowthRate
                    (DateUtil.convertStr2Date2( (String) pageParam.get("startDate")),DateUtil.convertStr2Date2((String) pageParam.get("endDate")) ,financialProductInformationDto);
            Map<Date, EvaluationRateDto> netValueGrowthRateMap = netValueGrowthRate.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //胜率
            List<EvaluationRateDto> winRate=evaluationEntrance.winRate
                    (DateUtil.convertStr2Date2( (String) pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> winRateMap = winRate.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //正负收益周期比
            List<EvaluationRateDto> plusMinusEarningsPeriodRatio=evaluationEntrance.plusMinusEarningsPeriodRatio
                    (DateUtil.convertStr2Date2((String) pageParam.get("startDate")) ,DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> plusMinusEarningsPeriodRatioMap = plusMinusEarningsPeriodRatio.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            for (EvaluationRateDto evaluationRateDto:yearYield){
                Date end=evaluationRateDto.getEndDate();
                Map<String,Object> map=new HashMap<>();
                map.put("portfolioId",finInvestmentEvaluate002Dto.getPortfolioId());//组合代码
                map.put("portfolioName",finInvestmentEvaluate002Dto.getPortfolioName());//组合名称
                map.put("startDate",finInvestmentEvaluate002Dto.getStartDate());//区间开始日
                map.put("endDate",evaluationRateDto.getEndDate());//区间结束日
                map.put("yearYield", ArithmeticUtil.multiply(evaluationRateDto.getRate(),BigDecimal.valueOf(100)));//区间年化收益率
                map.put("netValueGrowthRate",ArithmeticUtil.multiply(netValueGrowthRateMap.get(end).getRate(),BigDecimal.valueOf(100)));//净值增长率
                map.put("winRate",ArithmeticUtil.multiply(winRateMap.get(end).getRate(),BigDecimal.valueOf(100)));//胜率
                map.put("plusMinusEarningsPeriodRatio",ArithmeticUtil.multiply(plusMinusEarningsPeriodRatioMap.get(end).getRate(),BigDecimal.valueOf(100)));//正负收益周期比
                page.add(map);
            }

        return page;
    }
    //风险指标
    public List findPage5(Map<String, Object> pageParam) throws ParseException {
     FinInvestmentEvaluate002Dto finInvestmentEvaluate002Dto=finProductInfo(pageParam);
        List page=new ArrayList();
        if (finInvestmentEvaluate002Dto==null){return page;}
            FinancialProductInformationDto financialProductInformationDto=financialProductInfo(finInvestmentEvaluate002Dto);
            //波动率、年化波动率
            List<EvaluationRateDto> fluctuateRate=evaluationEntrance.fluctuateRate
                    (DateUtil.convertStr2Date2((String)  pageParam.get("startDate")),DateUtil.convertStr2Date2( (String) pageParam.get("endDate")),financialProductInformationDto);
            //下行标准差
            List<EvaluationRateDto> descendingStandardDeviation=evaluationEntrance.descendingStandardDeviation
                    (DateUtil.convertStr2Date2((String) pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> descendingStandardDeviationMap = descendingStandardDeviation.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //年化特累诺比率
            List<EvaluationRateDto> annualizedTraynorRatio=evaluationEntrance.annualizedTraynorRatio
                    (DateUtil.convertStr2Date2( (String)pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> annualizedTraynorRatioMap = annualizedTraynorRatio.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //期间最大净值回撤
            List<EvaluationRateDto> maximumRangeRetracement=evaluationEntrance.maximumRangeRetracement
                    (DateUtil.convertStr2Date2( (String)pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> maximumRangeRetracementMap = maximumRangeRetracement.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //历史最大净值回撤
            List<EvaluationRateDto> historyBiggistWorthBack=evaluationEntrance.historyBiggistWorthBack
                    (DateUtil.convertStr2Date2( (String)pageParam.get("startDate")),DateUtil.convertStr2Date2((String) pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> historyBiggistWorthBackMap = historyBiggistWorthBack.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            for(EvaluationRateDto evaluationRateDto:fluctuateRate){
                Date end=evaluationRateDto.getEndDate();
                Map<String,Object> map=new HashMap<>();
                map.put("portfolioId",finInvestmentEvaluate002Dto.getPortfolioId());//组合代码
                map.put("portfolioName",finInvestmentEvaluate002Dto.getPortfolioName());//组合名称
                map.put("startDate",finInvestmentEvaluate002Dto.getStartDate());//区间开始日
                map.put("endDate",evaluationRateDto.getEndDate());//区间结束日
                map.put("fluctuateRate",ArithmeticUtil.multiply(evaluationRateDto.getRate2(),BigDecimal.valueOf(100)));//波动率
                map.put("yearFluctuateRate",ArithmeticUtil.multiply(evaluationRateDto.getRate(),BigDecimal.valueOf(100)));//年化波动率
                map.put("descendingStandardDeviation",descendingStandardDeviationMap.get(end).getRate());//下行标准差
                map.put("annualizedTraynorRatio",ArithmeticUtil.multiply(annualizedTraynorRatioMap.get(end).getRate(),BigDecimal.valueOf(100)));//年化特累诺比率
                map.put("maximumRangeRetracement",maximumRangeRetracementMap.get(end).getRate());//期间最大净值回撤
                map.put("historyBiggistWorthBack",historyBiggistWorthBackMap.get(end).getRate());//历史最大净值回撤
                page.add(map);
            }

        return page;
    }
    //风险调整收益指标
    public List findPage6(Map<String, Object> pageParam) throws ParseException {
        FinInvestmentEvaluate002Dto finInvestmentEvaluate002Dto=finProductInfo(pageParam);

        List page=new ArrayList();
        if (finInvestmentEvaluate002Dto==null){return page;}
            FinancialProductInformationDto financialProductInformationDto=financialProductInfo(finInvestmentEvaluate002Dto);
            //年化夏普比率
            List<EvaluationRateDto> annualizedSharpe=evaluationEntrance.annualizedSharpe
                    (DateUtil.convertStr2Date2( (String)pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            //年化卡玛比率
            List<EvaluationRateDto> annualKarmaRatio=evaluationEntrance.annualKarmaRatio
                    (DateUtil.convertStr2Date2( (String)pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> annualKarmaRatioMap = annualKarmaRatio.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //年化索提诺比率
            List<EvaluationRateDto> annualizedSotinoRatio=evaluationEntrance.annualizedSotinoRatio
                    (DateUtil.convertStr2Date2( (String)pageParam.get("startDate")),DateUtil.convertStr2Date2( (String)pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> annualizedSotinoRatioMap = annualizedSotinoRatio.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            //詹森指数
            List<EvaluationRateDto> jensenIndex=evaluationEntrance.jensenIndex
                    (DateUtil.convertStr2Date2((String) pageParam.get("startDate")),DateUtil.convertStr2Date2((String) pageParam.get("endDate")),financialProductInformationDto);
            Map<Date, EvaluationRateDto> jensenIndexMap = jensenIndex.stream()
                    .collect(Collectors.toMap(EvaluationRateDto::getEndDate, Function.identity(), (k1, k2) -> k1));
            for(EvaluationRateDto evaluationRateDto:annualizedSharpe){
                Date end=evaluationRateDto.getEndDate();
                Map<String,Object> map=new HashMap<>();
                map.put("portfolioId",finInvestmentEvaluate002Dto.getPortfolioId());//组合代码
                map.put("portfolioName",finInvestmentEvaluate002Dto.getPortfolioName());//组合名称
                map.put("startDate",finInvestmentEvaluate002Dto.getStartDate());//区间开始日
                map.put("endDate",evaluationRateDto.getEndDate());//区间结束日
                map.put("annualizedSharpe",ArithmeticUtil.multiply(evaluationRateDto.getRate(),BigDecimal.valueOf(100)));//年化夏普比率
                map.put("annualKarmaRatio",ArithmeticUtil.multiply(annualKarmaRatioMap.get(end).getRate(),BigDecimal.valueOf(100)));//年化卡玛比率
                map.put("annualizedSotinoRatio",ArithmeticUtil.multiply(annualizedSotinoRatioMap.get(end).getRate(),BigDecimal.valueOf(100)));//年化索提诺比率
                map.put("jensenIndex",jensenIndexMap.get(end).getRate());//詹森指数
                page.add(map);
            }

        return page;
    }
    //策略执行有效性
    public List<FinInvestmentEvaluate005Dto> findPage7(Map<String, Object> pageParam) throws ParseException {
        List<FinInvestmentEvaluate005Dto> list= restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage7",
                pageParam,FinInvestmentEvaluate005Dto.class);
        String portfolioId=(String)pageParam.get("portfolioId");
        Date startDate=DateUtil.convertStr2Date2((String)pageParam.get("startDate"));
        Date endDate=DateUtil.convertStr2Date2((String)pageParam.get("endDate"));
        List<TimePointStrategyDto> calcTimePointStrategy=evalIndexAlgorithmService.
                calcTimePointStrategy(startDate,endDate,portfolioId);
        Map<String, TimePointStrategyDto> calcTimePointStrategyMap = calcTimePointStrategy.stream()
                .collect(Collectors.toMap(TimePointStrategyDto::getBatchId, Function.identity(), (k1, k2) -> k1));
        List<AverageStrategyDto> calcAverageStrategy=evalIndexAlgorithmService.
                calcAverageStrategy(startDate,endDate,portfolioId);
        Map<String, AverageStrategyDto> calcAverageStrategyMap = calcAverageStrategy.stream()
                .collect(Collectors.toMap(AverageStrategyDto::getBatchId, Function.identity(), (k1, k2) -> k1));
        for (FinInvestmentEvaluate005Dto fdto:list){
            fdto.setTimePointEff(calcTimePointStrategyMap.get(fdto.getBatchId()).getTimePointEff().multiply(BigDecimal.valueOf(100)));
            fdto.setAvgAmtRatio(calcAverageStrategyMap.get(fdto.getBatchId()).getAvgAmtRatio().multiply(BigDecimal.valueOf(100)));
            fdto.setEffDate(endDate);
        }

        return list;
    }

    //策略执收益来源
    public List findPage8(Map<String, Object> pageParam) throws ParseException {
        List<FinInvestmentEvaluate006Dto> list= restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPage8",
                pageParam,FinInvestmentEvaluate006Dto.class);
        for (FinInvestmentEvaluate006Dto dto:list){
            BigDecimal rate=evalIndexAlgorithmService.
                    calcAssetContributionRate(dto.getAssetNetVal(),dto.getAssetCost()
                    ,dto.getPtlAssetNetVal(),dto.getPtlAssetCost());
            dto.setCalcAssetContributionRate(rate.multiply(BigDecimal.valueOf(100)));
            dto.setcDate(DateUtil.convertStr2Date2((String)pageParam.get("endDate")));
        }
        return list;
    }
    //组合信息查询
    public List<SelectOptionDto> findPort(Map<String, Object> pageParam){
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findPort",
                pageParam,SelectOptionDto.class);
    }
    //策略查询
    public List<SelectOptionDto> findStrategyId(Map<String, Object> pageParam){
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/findStrategyId",
                pageParam,SelectOptionDto.class);
    }
    //调用收益目标计算 查询组合信息
    public List<FinInvestmentEvaluate003Dto> findPortInfo(Map<String, Object> pageParam){
        List<FinInvestmentEvaluate003Dto> li=new ArrayList<>();
        List<FinInvestmentEvaluate007Dto> list7=restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/info7",
                pageParam,FinInvestmentEvaluate007Dto.class);
        for (FinInvestmentEvaluate007Dto dto:list7) {
            FinInvestmentEvaluate003Dto dto3=new FinInvestmentEvaluate003Dto();
            dto3.setIndexId(BigDecimal.ZERO);
            if (DictCodeList.评价指标收益目标类型_固定.getValue().equals(dto.getIncomeType())){
                dto3.setIndexId(dto.getIncomeTarget());
            }
            if (DictCodeList.评价指标收益目标类型_浮动.getValue().equals(dto.getIncomeType())){
                Map<String,Object> m=new HashMap<>();m.put("indexId",dto.getIndexId());
                List<FinInvestmentEvaluate008Dto> list8=restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinInvestmentEvaluate/info8",
                        m,FinInvestmentEvaluate008Dto.class);

                     dto3.setIndexId(dto3.getIndexId().add(list8.get(0).getIntRate().multiply(list8.get(0).getCoefficient())).add(list8.get(0).getSpreadRate()));

            }
            dto3.setIndexId(dto3.getIndexId().multiply(BigDecimal.valueOf(100)));
            dto3.setEffDate(DateUtil.convertStr2Date2((String)pageParam.get("endDate")));
            dto3.setPortfolioId(dto.getPortfolioId()); dto3.setPortfolioName(dto.getPortfolioName());
            dto3.setEvalIndex(dto.getEvalIndex());
            li.add(dto3);
        }
        return li;
    }

}
