
package com.joyintech.tams.invest.portfolio.ptlmock.service;

import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.isNotFomartSec;
import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.toLocalDate;
import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.toSecInfoModel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.calculation.shell.PriceByYieldCalculator;
import com.joyintech.calculation.shell.param.PriceYieldOutParameter;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.MockProductDeal;
import com.joyintech.tams.common.base.entity.MockTestNetTrend;
import com.joyintech.tams.common.base.entity.MockTradeProduct;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.tams.component.business.riskrds.dto.InterfRiskRdsDto;
import com.joyintech.tams.component.business.riskrds.service.InterfRiskRdsService;

import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.valuationinterf.ValuationInterfService;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTestRiskDto;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.SecPrinSchedule;
import com.joyintech.calculation.bonds.constant.Basis;
import com.joyintech.calculation.bonds.constant.CurrIntCalMethod;
import com.joyintech.calculation.bonds.constant.DiscountScheme;
import com.joyintech.calculation.bonds.constant.FlowCashScheme;
import com.joyintech.calculation.bonds.constant.IntRateType;
import com.joyintech.calculation.bonds.constant.NextIntCalMethod;
import com.joyintech.calculation.bonds.constant.Variety;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.AccurIntForSecheduleCalculator;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.AccurIntCalInParameter;
import com.joyintech.calculation.shell.param.AccurIntCalOutParameter;
import com.joyintech.calculation.shell.param.PriceYieldInParameter;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.calculation.shell.param.YieldPriceInParameter;
import com.joyintech.calculation.shell.param.YieldPriceOutParameter;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.handler.ReportCfgHandler;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDetailDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.calculator.InterestSubAmtCal;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationParameter;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockSingleAssetChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.calcindicator.IndicatorName;
import com.joyintech.tams.invest.common.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlValuationPositionDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTradeInfoDto;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValIndicatorCalcUtil;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTest002Dto;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTestTaaDto;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTestTradeDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

import sun.misc.BASE64Decoder;

/**
 * 模拟试算
 * 
 * @author yjm
 * @version 1.0 2020/04/13
 * @see PtlMockTest002Service
 * @since 1.0
 */
@Service
public class PtlMockTest002Service extends BaseService<PtlMockTest002Dto> implements BaseProcessService<PtlMockTest002Dto> {

    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    private static Logger logger = LoggerFactory.getLogger(PtlMockTest002Service.class);
    
    @Autowired
    private InterfRiskRdsService<InterfRiskRdsDto> interfRiskRdsService;

    @Autowired
    private ValuationInterfService valuationInterfService;
    /**
     * 根据组合代码获取组合信息
     * 
     * @param param
     * @return
     */
    public List<SelectOptionDto> getPtlInfo(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/getPtlInfo", param,
            SelectOptionDto.class);
    }

    /**
     * 根据组合代码获取组合详细信息
     * 
     * @param portfolioId 组合代码
     * @return 组合详细信息
     */
    public MockPtlPortfolioDto getPtlDetailInfo(String portfolioId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("portfolioId", portfolioId);
        paramMap.put("paramDate", iPlatformDate.getSysDateStr());
        MockPtlPortfolioDto ptlPortfolioDto = restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/selectPortfolioDetailInfo",
            paramMap, MockPtlPortfolioDto.class);

        if (ptlPortfolioDto != null) {
            ptlPortfolioDto.setFinBaseRuleYield(ArithmeticUtil.multiply(
                ptlPortfolioDto.getFinBaseRuleYield(), BigDecimal.valueOf(100)));
            ptlPortfolioDto.setManageFeeRate(ArithmeticUtil.multiply(
                ptlPortfolioDto.getManageFeeRate(), BigDecimal.valueOf(100)));
        }

        return ptlPortfolioDto;
    }

    @Override
    public void doComplete(PtlMockTest002Dto ptlMockTest002Dto, OperationType operationType) {
        // 入库
        ptlMockTest002Dto.setOperateType(operationType.toString());
        // 前一日组合表现、实时组合表现、实时组合表现（测算后）项目百分比处理
        this.do试算结果百分比处理(ptlMockTest002Dto);
        
        restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/doComplete",
            ptlMockTest002Dto, Integer.class);
    }
    
    /**
     * 保存之前，将试算结果部分百分比项目，进行/100处理
     * 试算参数部分，试算处理已经处理过了，这里不需要再处理
     * @param ptlMockTest002Dto 试算结果
     */
    private void do试算结果百分比处理(PtlMockTest002Dto ptlMockTest002Dto) {
        List<String> 百分比项目列表 = new ArrayList<>();
        百分比项目列表.add("preDayValChg");
        百分比项目列表.add("preDaySevenDayRate");
        百分比项目列表.add("preDayLeverRate");
        百分比项目列表.add("realTimeValChg");
        百分比项目列表.add("realTimeSevenDayRate");
        百分比项目列表.add("realTimeLeverRate");
        百分比项目列表.add("mockValChg");
        百分比项目列表.add("mockLeverRate");
        百分比项目列表.add("mockSevenDayRate");
        百分比项目列表.add("mockOneMonthRate");
        百分比项目列表.add("mockThreeMonthRate");
        百分比项目列表.add("mockQuarterDayRate");
        百分比项目列表.add("mockOneYearRate");
        百分比项目列表.add("mockYield");
        百分比项目列表.add("mockRangeRate");
        
        for (String itemName : 百分比项目列表) {
            try {
                BigDecimal value = new BigDecimal(BeanUtil.getProperty(ptlMockTest002Dto, itemName));
                BeanUtil.setProperty(ptlMockTest002Dto, itemName,
                    ArithmeticUtil.divide(value, 100, 12));
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * 获取资产信息
     * 
     * @param param
     * @return
     */
    public List<SelectOptionDto> getFinProdId(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/getFinProdId", param,
            SelectOptionDto.class);
    }

    /**
     * 获取模拟试算信息
     * 
     * @param map
     * @return
     */
    public PtlMockTest002Dto getInitInfo(Map<String, Object> map) {
        PtlMockTest002Dto dto = restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/getInitInfo", map,
            PtlMockTest002Dto.class);
        return dto;
    }


    /**
     * 模拟测算按钮点击时的处理
     *
     * @param inputDto PtlMockTest002Dto
     * @return 处理结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public PtlMockTest002Dto doMockTest(PtlMockTest002Dto inputDto)
        throws Exception {
        PtlMockTest002Dto resultDto = new PtlMockTest002Dto();
        logger.info("测算开始...");
        // BigDecimal 一百 = BigDecimal.valueOf(100);
        final String 平台日 = iPlatformDate.getSysDateStr();
        final String 平台日前一日 = DateUtil.addDay(平台日, -1);
        final Date 平台日Date = DateUtil.parse(平台日);
        final Date 平台日前一日Date = DateUtil.parse(平台日前一日);
        boolean 非虚拟组合 = !StringUtil.equals(DictCodeListExt3.组合类型_虚拟组合.getValue(),
            inputDto.getPortfolioType());

        // 画面上的业绩基准%、管理费率%的值是百分比，需要除以100
        this.do画面参数百分比处理(inputDto);
        
        // 画面输入项目保存
        BeanUtil.copyPropertiesIgnoreNull(resultDto, inputDto);
        resultDto.setMockDate(平台日Date);

        // 2.1 组合基本信息查询
        MockPtlPortfolioDto ptlBaseInfoDto = this.getPtlDetailInfo(inputDto.getPortfolioId());
        ptlBaseInfoDto.setVdate(inputDto.getVdate());
        ptlBaseInfoDto.setMdate(inputDto.getMdate());
        ptlBaseInfoDto.setFinBaseRuleYield(inputDto.getBaseRule());
        ptlBaseInfoDto.setManageFeeRate(inputDto.getMgrFeeRate());
        if(StringUtil.isEmpty(ptlBaseInfoDto.getCcy())){
            ptlBaseInfoDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        }

        List<MockPtlSecValuationDto> 平台日前一日组合资产估值列表 = new ArrayList<>();
        List<MockPtlPortfolioValuationDto> 全量组合估值列表 = new ArrayList<>();
        List<MockPtlFeePositionDto> 平台日前一日组合费用头寸列表 = new ArrayList<>();
        List<MockPtlCashPositionDto> 平台日前一日组合现金列表 = new ArrayList<>();
        if (非虚拟组合) {
            // 2.2 平台日前一日组合资产估值列表查询
            // 2.3 全量组合估值列表查询
            // 2.4 平台日前一日组合费用头寸列表查询
            // 2.5 平台日前一日组合现金查询
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("portfolioId", inputDto.getPortfolioId());
            paramMap.put("cdate", 平台日前一日);
            paramMap.put("sysdate", 平台日);
            MockPtlValuationPositionDto valuationPositionDto = restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/valuationposition/selectPtlValuationPositionAllData",
                paramMap, MockPtlValuationPositionDto.class);
            if (valuationPositionDto == null) {
                valuationPositionDto = new MockPtlValuationPositionDto();
            }

            平台日前一日组合资产估值列表 = valuationPositionDto.getPtlSecValuationList();
            全量组合估值列表 = valuationPositionDto.getPtlPortfolioValuationList();
            平台日前一日组合费用头寸列表 = valuationPositionDto.getPtlFeePositionList();
            平台日前一日组合现金列表 = valuationPositionDto.getPtlCashPositionList();
        }
        else {
//            // 如果所选组合为虚拟组合，则上记组合估值查询方法是查询不到数据的，需要初始化组合估值列表数据：
//            Date tempCalcDate = ptlBaseInfoDto.getVdate();
//            while (DateUtil.compareDate(tempCalcDate, 平台日前一日Date) <= 0) {
//                MockPtlPortfolioValuationDto tempPtlValuationDto = new MockPtlPortfolioValuationDto();
//                tempPtlValuationDto.setPortfolioId(inputDto.getPortfolioId());
//                tempPtlValuationDto.setCdate(tempCalcDate);
//                tempPtlValuationDto.setCcy(ptlBaseInfoDto.getCcy());
//                tempPtlValuationDto.setTdyShareAmt(inputDto.getRaiseAmtPlan());
//                tempPtlValuationDto.setTdyUnitValue(BigDecimal.ONE);
//                tempPtlValuationDto.setTdyUnitValueSum(BigDecimal.ONE);
//                全量组合估值列表.add(tempPtlValuationDto);
//
//                tempCalcDate = DateUtil.addDay(tempCalcDate, 1);
//            }

            // 将虚拟产品的起息日置为平台日期(虚拟产品没有历史的估值数据  如果计算指标从历史计算的话 导致计算不准确)
            ptlBaseInfoDto.setVdate(平台日Date);
            // 如果所选组合为虚拟组合，则上记组合估值查询方法是查询不到数据的，需要初始化组合估值列表数据：
            MockPtlPortfolioValuationDto tempPtlValuationDto = new MockPtlPortfolioValuationDto();
            tempPtlValuationDto.setPortfolioId(inputDto.getPortfolioId());
            tempPtlValuationDto.setCdate(平台日前一日Date);
            tempPtlValuationDto.setCcy(ptlBaseInfoDto.getCcy());
            tempPtlValuationDto.setTdyAssetValue(inputDto.getRaiseAmtPlan());
            tempPtlValuationDto.setTdyShareAmt(inputDto.getRaiseAmtPlan());
            tempPtlValuationDto.setTdyUnitValue(BigDecimal.ONE);
            全量组合估值列表.add(tempPtlValuationDto);

            // 如果所选组合为虚拟组合，则上记组合现金查询方法是查询不到数据的，需要初始化组合现金数据：
            MockPtlCashPositionDto ptlCashPositionDto = new MockPtlCashPositionDto();
            ptlCashPositionDto.setPortfolioId(inputDto.getPortfolioId());
            ptlCashPositionDto.setCdate(平台日前一日Date);
            ptlCashPositionDto.setCcy(ptlBaseInfoDto.getCcy());
            ptlCashPositionDto.setAmount(inputDto.getRaiseAmtPlan());
            平台日前一日组合现金列表.add(ptlCashPositionDto);
        }

        // 2.5.2 调用行情服务.汇率行情信息批量查询方法查询汇率行情数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("paramDate", 平台日);
        paramMap.put("ccyList", Arrays.asList(ptlBaseInfoDto.getCcy()));
        List<MockMstExchargeQuoteDto> 汇率行情数据 = restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/Priceinfo/selectQuotationofrate",
            paramMap, MockMstExchargeQuoteDto.class);

        MockPtlCashPositionDto 平台日前一日组合现金 = null;
        // ※查询到的组合现金数据需要根据组合募集币种以及汇率行情数据做换算
        if (非虚拟组合) {
            平台日前一日组合现金 = this.do汇率换算(平台日前一日组合现金列表, 汇率行情数据, ptlBaseInfoDto.getCcy());
        }
        else {
            平台日前一日组合现金 = 平台日前一日组合现金列表.get(0);
        }

        // 2.6 平台日前一日组合持有资产基本信息列表查询
        List<MockAssetInfoDto> 平台日前一日组合持有资产基本信息列表 = new ArrayList<>();
        List<MockAssetInfoDto> 平台日前一日组合持有加模拟交易对应资产基本信息列表 = new ArrayList<>();
        List<String> finprodIdList = new ArrayList<>();
        List<String> 平台日前一日组合持有资产代码列表 = new ArrayList<>();
        if (!StringUtil.isEmptyList(平台日前一日组合资产估值列表)) {
            for (MockPtlSecValuationDto ptlSecValuation : 平台日前一日组合资产估值列表) {
                if(StringUtil.isNotEmpty(ptlSecValuation.getFinprodId()))
                平台日前一日组合持有资产代码列表.add(ptlSecValuation.getFinprodId());
            }
            finprodIdList.addAll(平台日前一日组合持有资产代码列表);
        }
        // 模拟交易
        for (PtlMockTestTradeDto mockTestTradeDto : inputDto.getPageMockDealList()) {
            if (StringUtil.isNotEmpty(mockTestTradeDto.getFinprodId())&&StringUtil.isNotBlank(mockTestTradeDto.getFinprodId())
                && !finprodIdList.contains(mockTestTradeDto.getFinprodId())) {
                finprodIdList.add(mockTestTradeDto.getFinprodId());
            }
        }
        paramMap.clear();
        paramMap.put("finprodIdList", finprodIdList);
        paramMap.put("paramDate", 平台日);
        paramMap.put("isForMock",DictCodeList.是否_是.getValue());
        if(finprodIdList!=null&&finprodIdList.size()>0){
            平台日前一日组合持有加模拟交易对应资产基本信息列表 = restTemplate.postForList(
                    ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
                    MockAssetInfoDto.class);
        }
        if (!StringUtil.isEmptyList(平台日前一日组合持有资产代码列表)) {
            平台日前一日组合持有资产基本信息列表 = 平台日前一日组合持有加模拟交易对应资产基本信息列表.stream().filter(
                x -> 平台日前一日组合持有资产代码列表.contains(x.getFinprodId())).collect(Collectors.toList());
        }

//        // 到期收益率为空 重新算
//        Map<String, MockAssetInfoDto> assetInfoMap = 平台日前一日组合持有加模拟交易对应资产基本信息列表.stream().collect(
//                JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x));
//        for(MockPtlSecValuationDto mockPtlSecValuationDto:平台日前一日组合资产估值列表){
//            if(mockPtlSecValuationDto.getYield()!=null||assetInfoMap==null||assetInfoMap.get(mockPtlSecValuationDto.getFinprodId())==null){
//                continue;
//            }
//            if(!StringUtil.equals(assetInfoMap.get(mockPtlSecValuationDto.getFinprodId()).getTgAssetType(), DictCodeList.平安资产类型_债券.getValue())){
//                mockPtlSecValuationDto.setYield(BigDecimal.ZERO);
//                continue;
//            }
//            BigDecimal unitFprice = ArithmeticUtil.divide(
//                    ArithmeticUtil.doAdd(mockPtlSecValuationDto.getDscCostAmt(),
//                            mockPtlSecValuationDto.getTdyIntincexp()),
//                    mockPtlSecValuationDto.getShareAmt(), 12);
//            PtlMockTestTradeDto tempCalcResult = this.calcBondYield(
//                    assetInfoMap.get(mockPtlSecValuationDto.getFinprodId()),
//                    mockPtlSecValuationDto.getFinprodId(), null, null, null, unitFprice, iPlatformDate.getSysDate());
//            if (tempCalcResult != null && tempCalcResult.getYield() != null) {
//                mockPtlSecValuationDto.setYield(tempCalcResult.getYield());
//            }
//        }


        if (非虚拟组合) {
            // 重新计算债券到期收益率（组合久期计算用到债券到期收益率)
            this.reCalcBondYield(平台日前一日组合资产估值列表, 平台日前一日组合持有资产基本信息列表,
                    new ArrayList<>(), 平台日前一日Date,false);
            // 3.计算前一日组合表现
            this.do计算组合表现(ptlBaseInfoDto, 平台日前一日组合资产估值列表, 全量组合估值列表, 平台日前一日组合现金, 平台日前一日组合持有资产基本信息列表,
                汇率行情数据, 平台日前一日, resultDto, 1);
        }

        // 4.实时试算参数信息查询
        MockTradeInfoDto 当日实时成交交易 = null;
        List<MockPtlCashPositionDto> 当日实时组合现金列表 = new ArrayList<>();
        List<MockAssetInfoDto> 当日实时成交交易对应资产基本信息列表 = new ArrayList<>();
        List<MockTrdProductDealDto> 资产端交易列表 = new ArrayList<>();
        List<MockTrdProductDealDto> 负债端交易列表 = new ArrayList<>();
        List<MockTrdProductDealDto> 资产端T加N交易列表 = new ArrayList<>();
        List<MockTrdProductDealDto> 负债端T加N交易列表 = new ArrayList<>();
        List<MockTrdProductDealDto> 费用交易列表 = new ArrayList<>();
        if (非虚拟组合) {
            // 4.1 当日实时成交交易查询
            paramMap.clear();
            paramMap.put("portfolioId", inputDto.getPortfolioId());
            paramMap.put("tradeDate", 平台日);
            当日实时成交交易 = restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                                                  + "/common/trdcommonbase/selectTradeinfodeal",
                paramMap, MockTradeInfoDto.class);
            if (当日实时成交交易 != null) {
                资产端交易列表 = 当日实时成交交易.getAssetTradeList();
                负债端交易列表 = 当日实时成交交易.getLiabilitiesTradeList();
                资产端T加N交易列表 = 当日实时成交交易.getAssetTdayPlusNdayTradeList();
                负债端T加N交易列表 = 当日实时成交交易.getLiabilitiesTdayPlusNdayTradeList();
                费用交易列表 = 当日实时成交交易.getFeeTradeList();
            }

            // 4.2 当日实时组合现金查询
            paramMap.clear();
            paramMap.put("portfolioId", inputDto.getPortfolioId());
            paramMap.put("cdate", 平台日);
            当日实时组合现金列表 = restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                                                  + "/valuationposition/selectPtlCashPositionData",
                paramMap, MockPtlCashPositionDto.class);

            // 4.3 当日实时成交交易对应资产基本信息列表查询
            List<String> 当日实时成交交易对应资产代码列表 = new ArrayList<>();
            for (MockTrdProductDealDto trdProductDealDto : 资产端交易列表) {
                if (StringUtil.isNotEmpty(trdProductDealDto.getFinprodId())&&!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            for (MockTrdProductDealDto trdProductDealDto : 负债端交易列表) {
                if (StringUtil.isNotEmpty(trdProductDealDto.getFinprodId())&&!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            for (MockTrdProductDealDto trdProductDealDto : 资产端T加N交易列表) {
                if (StringUtil.isNotEmpty(trdProductDealDto.getFinprodId())&&!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            for (MockTrdProductDealDto trdProductDealDto : 负债端T加N交易列表) {
                if (StringUtil.isNotEmpty(trdProductDealDto.getFinprodId())&&!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            if (!StringUtil.isEmptyList(当日实时成交交易对应资产代码列表)) {
                paramMap.clear();
                paramMap.put("finprodIdList", 当日实时成交交易对应资产代码列表);
                paramMap.put("paramDate", 平台日);
                paramMap.put("isForMock",DictCodeList.是否_是.getValue());
                if(当日实时成交交易对应资产代码列表!=null&&当日实时成交交易对应资产代码列表.size()>0){
                    当日实时成交交易对应资产基本信息列表 = restTemplate.postForList(
                            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
                            MockAssetInfoDto.class);
                }
            }
        }
        else {
            // 如果所选组合为虚拟组合，则上记组合现金查询方法是查询不到数据的，需要初始化组合现金数据：
            MockPtlCashPositionDto ptlCashPositionDto = new MockPtlCashPositionDto();
            ptlCashPositionDto.setPortfolioId(inputDto.getPortfolioId());
            ptlCashPositionDto.setCdate(平台日Date);
            ptlCashPositionDto.setCcy(ptlBaseInfoDto.getCcy());
            ptlCashPositionDto.setAmount(inputDto.getRaiseAmtPlan());
            当日实时组合现金列表.add(ptlCashPositionDto);
        }
        MockPtlCashPositionDto 当日实时组合现金 = null;
        // ※查询到的组合现金数据需要根据组合募集币种以及汇率行情数据做换算
        if (非虚拟组合) {
            当日实时组合现金 = this.do汇率换算(当日实时组合现金列表, 汇率行情数据, ptlBaseInfoDto.getCcy());
        }
        else {
            当日实时组合现金 = 当日实时组合现金列表.get(0);
        }
        Map<String, BigDecimal> 当日实时组合现金Map = new HashMap<>();
        if (当日实时组合现金Map != null) {
            当日实时组合现金Map.put(当日实时组合现金.getCcy(), 当日实时组合现金.getAmount());
        }

        // 5.计算实时组合表现（考虑当日成交）
        // 5.1 当日实时组合资产估值
        // 资产基本信息列表
//        2.6 平台日前一日组合持有资产基本信息列表查询
//        4.3 当日实时成交交易对应资产基本信息列表查询
//        两次查询结果去重后拼装在一起→当日实时组合持有资产基本信息列表
        List<MockAssetInfoDto> 当日实时组合持有资产基本信息列表 = new ArrayList<>();
        当日实时组合持有资产基本信息列表.addAll(平台日前一日组合持有资产基本信息列表);
        for (MockAssetInfoDto mockAssetInfoDto : 当日实时成交交易对应资产基本信息列表) {
            if (!平台日前一日组合持有资产代码列表.contains(mockAssetInfoDto.getFinprodId())) {
                当日实时组合持有资产基本信息列表.add(mockAssetInfoDto);
            }
        }
        
        MockCalcAssetValuationResult 当日实时组合资产估值_考虑当日成交 = new MockCalcAssetValuationResult();
        if (非虚拟组合) {
            当日实时组合资产估值_考虑当日成交 = this.do实时组合资产估值(当日实时组合持有资产基本信息列表,
                ptlBaseInfoDto, 平台日前一日组合资产估值列表, 全量组合估值列表, 平台日前一日组合费用头寸列表, 资产端交易列表, 负债端交易列表, 费用交易列表,
                资产端T加N交易列表, 负债端T加N交易列表, 当日实时组合现金Map, 平台日, 平台日, 平台日, 汇率行情数据, inputDto.getFinanCost(),
                inputDto.getAgainInvestRate(), inputDto.getMgrFeeRate(), null, null, null);
            
            // 重新计算债券到期收益率（组合久期计算用到债券到期收益率)
            this.reCalcBondYield(当日实时组合资产估值_考虑当日成交.getMockPtlSecValuationDtoList(), 当日实时组合持有资产基本信息列表,
                资产端交易列表, 平台日Date,true);

            Map<String, BigDecimal> 测算后组合现金Map = 当日实时组合资产估值_考虑当日成交.getMockPtlCashPositionMap();
            MockPtlCashPositionDto 测算后组合现金 = new MockPtlCashPositionDto();
            测算后组合现金.setCcy(ptlBaseInfoDto.getCcy());
            测算后组合现金.setAmount(测算后组合现金Map.get(ptlBaseInfoDto.getCcy()));
            // 5.2.计算实时组合表现（考虑当日成交）
            this.do计算组合表现(ptlBaseInfoDto, 当日实时组合资产估值_考虑当日成交.getMockPtlSecValuationDtoList(),
                当日实时组合资产估值_考虑当日成交.getMockPtlPortfolioValuationDtoList(), 测算后组合现金, 当日实时组合持有资产基本信息列表,
                汇率行情数据, 平台日, resultDto, 2);
        }
        
        // 6.计算实时组合表现（试算后）
        // 6.1 模拟交易对应资产基本信息列表查询
        List<MockAssetInfoDto> 全部资产基本信息列表 = new ArrayList<>();
        全部资产基本信息列表.addAll(平台日前一日组合持有加模拟交易对应资产基本信息列表);
        Map<String, String> 全部资产金融产品代码Map = 全部资产基本信息列表.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getFinprodId()));
        // 添加当日实时成交交易对应资产基本信息列表
        for (MockAssetInfoDto mockAssetInfoDto : 当日实时成交交易对应资产基本信息列表) {
            if (!全部资产金融产品代码Map.containsKey(mockAssetInfoDto.getFinprodId())) {
                全部资产基本信息列表.add(mockAssetInfoDto);
            }
        }
        // 模拟交易
        this.buildMockTradeInfo(resultDto, 平台日Date, 全部资产基本信息列表);

        // 6.2.确定计算截止日
        String 计算截止日 = this.calc计算截止日(平台日Date, ptlBaseInfoDto, inputDto);
        
        // 6.3 当日实时组合资产估值（包含模拟交易）
        List<MockTrdProductDealDto> 资产端交易列表_含模拟交易 = new ArrayList<>();
        资产端交易列表_含模拟交易.addAll(资产端交易列表);
        for (MockProductDeal mockProductDeal : resultDto.getMockProductDealList()) {
            MockTrdProductDealDto mockTrdProductDealDto = new MockTrdProductDealDto();
            BeanUtil.copyPropertiesIgnoreNull(mockTrdProductDealDto, mockProductDeal);
            mockTrdProductDealDto.setCashProcessedOrNot(DictCodeList.是否_否.getValue());
            资产端交易列表_含模拟交易.add(mockTrdProductDealDto);
        }
        // 调用组合实时估值服务.组合资产估值-主方法
        MockCalcAssetValuationResult 当日实时组合资产估值_包含模拟交易 = this.do实时组合资产估值(全部资产基本信息列表,
            ptlBaseInfoDto, 平台日前一日组合资产估值列表, 全量组合估值列表, 平台日前一日组合费用头寸列表, 资产端交易列表_含模拟交易, 负债端交易列表,
            费用交易列表, 资产端T加N交易列表, 负债端T加N交易列表, 当日实时组合现金Map, 平台日, 计算截止日, 平台日, 汇率行情数据,
            inputDto.getFinanCost(), inputDto.getAgainInvestRate(), inputDto.getMgrFeeRate(), null,
            null, null);
        
        // 根据上记返回值及【5.1 当日实时组合资产估值】结果加载画面资产配置画面
        this.buildMockTestTaaData(当日实时组合资产估值_考虑当日成交.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_考虑当日成交.getMockPtlPortfolioValuationDtoList(),
            当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(), resultDto,
            平台日Date);
        
        // 调用风险试算共通计算风险试算指标
//        this.do调用风险试算共通计算风险试算指标(ptlBaseInfoDto, 平台日, 全部资产基本信息列表, resultDto.getMockProductDealList(), resultDto);
        
        // 重新计算债券到期收益率（组合久期计算用到债券到期收益率）
        this.reCalcBondYield(当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(), 全部资产基本信息列表,
            资产端交易列表_含模拟交易, 平台日Date,true);
        
        // 6.4.计算实时组合表现（试算后）（区间相关指标除外）
        // 6.5.1.计算实时组合表现（试算后）（区间相关指标）
        Map<String, BigDecimal> 测算后组合现金Map = 当日实时组合资产估值_包含模拟交易.getMockPtlCashPositionMap();
        MockPtlCashPositionDto 测算后组合现金 = new MockPtlCashPositionDto();
        测算后组合现金.setCcy(ptlBaseInfoDto.getCcy());
        测算后组合现金.setAmount(测算后组合现金Map.get(ptlBaseInfoDto.getCcy()));
        // 打印测算后的用于计算组合表现的值

        Map<Date, MockPtlPortfolioValuationDto> ptlValuationDtoMap = 当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList().stream().collect(
                JoyinCollectors.toMap(x -> x.getCdate(), x -> x));

        List<MockPtlSecValuationDto> currSecValuationDtoList = 当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList().stream().filter(
                x -> DateUtil.isSameDate(DateUtil.parse(平台日), x.getCdate())).collect(Collectors.toList());
        List<MockPtlFeePositionDto>  费用头寸列表 = 当日实时组合资产估值_包含模拟交易.getMockPtlFeePositionDtoList().stream().filter(
                x -> DateUtil.isSameDate(DateUtil.parse(平台日), x.getCdate())).collect(Collectors.toList());
        logger.info("平台日:"+平台日+"当天计算实时组合表现（试算后）（区间相关指标）======= start");
        logger.info("组合基本信息dto======="+JSONObject.toJSONString(ptlBaseInfoDto));
        logger.info("当日实时组合资产估值_包含模拟交易=======");
        logger.info("组合估值列表========"+JSONObject.toJSONString(ptlValuationDtoMap.get(DateUtil.parse(平台日))));
//        logger.info("组合资产估值拓展========"+JSONObject.toJSONString(currSecValuationDtoList));
        logger.info("测算后组合现金======="+JSONObject.toJSONString(测算后组合现金));
        logger.info("组合资产估值拓展========");
        for(MockPtlSecValuationDto dto:currSecValuationDtoList){
            logger.info(JSONObject.toJSONString(dto));
        }
        logger.info("测算后费用头寸========");
        for(MockPtlFeePositionDto dto:费用头寸列表){
            logger.info(JSONObject.toJSONString(dto));
        }
        this.do计算组合表现(ptlBaseInfoDto, 当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(), 测算后组合现金, 全部资产基本信息列表,
            汇率行情数据, 平台日, resultDto, 3);
        logger.info("计算结果======="+JSONObject.toJSONString(resultDto));
        logger.info("计算实时组合表现（试算后）（区间相关指标）======= end");

        // 确定模拟试算的试算区间
        List<Date> periodList = RealTimeValIndicatorCalcUtil.genMockDatePeriod(ptlBaseInfoDto.getProfitType(), ptlBaseInfoDto.getVdate()
                , ptlBaseInfoDto.getMdate(), ptlBaseInfoDto.getFinOpenPlanList(), DateUtil.parse(平台日));
        if(periodList!=null&&periodList.get(1)!=null){
            String logDateEx = DateUtil.format(periodList.get(1));
            List<MockPtlPortfolioValuationDto> logMockPtlPortfolioValuationDtoList =
                    当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList().stream().filter(
                            x -> DateUtil.isSameDate(DateUtil.parse(logDateEx), x.getCdate())).collect(Collectors.toList());
            logger.info("日期:"+logDateEx+"组合估值列表========");
            for(MockPtlPortfolioValuationDto dto:logMockPtlPortfolioValuationDtoList){
                logger.info(JSONObject.toJSONString(dto));
            }

            List<MockPtlSecValuationDto> logCurrSecValuationDtoList = 当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList().stream().filter(
                    x -> DateUtil.isSameDate(DateUtil.parse(logDateEx), x.getCdate())).sorted(Comparator.comparing(MockPtlSecValuationDto::getAssetLiabilitiesMark)).collect(Collectors.toList());
            logger.info("日期:"+logDateEx+"组合资产估值拓展========");
            for(MockPtlSecValuationDto dto:logCurrSecValuationDtoList){
                logger.info(JSONObject.toJSONString(dto));
            }
            List<MockPtlFeePositionDto> log费用头寸列表 = 当日实时组合资产估值_包含模拟交易.getMockPtlFeePositionDtoList().stream().filter(
                    x -> DateUtil.isSameDate(DateUtil.parse(logDateEx), x.getCdate())).collect(Collectors.toList());
            logger.info("日期:"+logDateEx+"测算后费用头寸========");
            for(MockPtlFeePositionDto dto:log费用头寸列表){
                logger.info(JSONObject.toJSONString(dto));
            }
        }

//        for(String logDate = DateUtil.addDay(平台日,1);DateUtil.compareDate(计算截止日,logDate)>=0;logDate = DateUtil.addDay(logDate,1)){
//            String logDateEx = logDate;
//            List<MockPtlPortfolioValuationDto> logMockPtlPortfolioValuationDtoList =
//                    当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList().stream().filter(
//                            x -> DateUtil.isSameDate(DateUtil.parse(logDateEx), x.getCdate())).collect(Collectors.toList());
//            logger.info("日期:"+logDateEx+"组合估值列表========");
//            for(MockPtlPortfolioValuationDto dto:logMockPtlPortfolioValuationDtoList){
//                logger.info(JSONObject.toJSONString(dto));
//            }
//
//            List<MockPtlSecValuationDto> logCurrSecValuationDtoList = 当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList().stream().filter(
//                    x -> DateUtil.isSameDate(DateUtil.parse(logDateEx), x.getCdate())).sorted(Comparator.comparing(MockPtlSecValuationDto::getAssetLiabilitiesMark)).collect(Collectors.toList());
//            logger.info("日期:"+logDateEx+"组合资产估值拓展========");
//            for(MockPtlSecValuationDto dto:logCurrSecValuationDtoList){
//                logger.info(JSONObject.toJSONString(dto));
//            }
//            List<MockPtlFeePositionDto> log费用头寸列表 = 当日实时组合资产估值_包含模拟交易.getMockPtlFeePositionDtoList().stream().filter(
//                    x -> DateUtil.isSameDate(DateUtil.parse(logDateEx), x.getCdate())).collect(Collectors.toList());
//            logger.info("日期:"+logDateEx+"测算后费用头寸========");
//            for(MockPtlFeePositionDto dto:log费用头寸列表){
//                logger.info(JSONObject.toJSONString(dto));
//            }
//        }
        // 6.5.2.计算区间收益率
        this.do计算区间收益率(ptlBaseInfoDto, 当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(),
            inputDto.getMockRangeStartDate(), inputDto.getMockRangeEndDate(), resultDto);
        
        // 6.5.3.净值走势、万份收益走势等曲线加载
        // 6.5.4.七日年化收益率走势图曲线加载
        this.do曲线数据处理(ptlBaseInfoDto, 平台日Date,
            当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(), resultDto);

        logger.info("测算结束...");
        return resultDto;
    }

    /**
     * 调用风险试算共通计算风险试算指标
     * @param ptlBaseInfoDto 组合基本信息
     * @param 平台日 平台日
     * @param mockProductDealList 模拟交易
     * @param resultDto 模拟试算整体结果dto
     */
    private void do调用风险试算共通计算风险试算指标(MockPtlPortfolioDto ptlBaseInfoDto, String 平台日,
                                    List<MockAssetInfoDto> 资产基本信息列表,
                                    List<MockProductDeal> mockProductDealList,
                                    PtlMockTest002Dto resultDto) {
        if (StringUtil.isEmptyList(mockProductDealList)) {
            return;
        }
        
        Map<String , MockAssetInfoDto> assetInfoMap = new HashMap<>();
        if (!StringUtil.isEmptyList(资产基本信息列表)) {
            assetInfoMap = 资产基本信息列表.stream().collect(JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x));
        }
        
        List<Map<String,Object>> listMap = new ArrayList<>();
        BigDecimal tdyAssetValue=null;
        BigDecimal tdyAssetNetValue=null;
        //当是虚拟组合时把产品总资产与净资产传到接口那边去
        if(StringUtil.equals(resultDto.getPortfolioType(),DictCodeListExt3.组合类型_虚拟组合.getValue())){
            tdyAssetValue=resultDto.getRaiseAmtPlan();
            tdyAssetNetValue=ArithmeticUtil.subtract(resultDto.getRaiseAmtPlan(),ArithmeticUtil.divide(ArithmeticUtil.multiply(resultDto.getRaiseAmtPlan(),resultDto.getMgrFeeRate()),new BigDecimal(365),10,RoundingMode.HALF_UP));
        }
        int index=0;
        for (MockProductDeal mockProductDeal : mockProductDealList) {
            InterfRiskRdsDto riskRdsDto = new InterfRiskRdsDto();
//            riskRdsDto.setInfCode(BussinessDictCodeListRds.接口系统ID_风险试算接口.getValue());
            riskRdsDto.setInfCode(BussinessDictCodeListRds.接口系统ID_风险试算批量接口.getValue());
            riskRdsDto.setEventDate(平台日);
            riskRdsDto.setTdyAssetValue(tdyAssetValue);
            riskRdsDto.setTdyAssetNetValue(tdyAssetNetValue);
            if(StringUtil.equals(resultDto.getPortfolioType(),DictCodeListExt3.组合类型_虚拟组合.getValue())) {
                riskRdsDto.setPrdMdate(DateUtil.format(resultDto.getMdate()));
            }
            riskRdsDto.setPortfolioCode(ptlBaseInfoDto.getPortfolioId());
            if (StringUtil.equals(RealTimeValCommonUtil.资产类型_模拟资产, mockProductDeal.getFinprodType2())) {
                riskRdsDto.setAssetCode(null);
                riskRdsDto.setAssetName(StringUtil.isNotEmpty(resultDto.getPageMockDealList())?StringUtil.isNotEmpty(resultDto.getPageMockDealList().get(index))?resultDto.getPageMockDealList().get(index).getFinprodAbbr():null:null);
                riskRdsDto.setAssetType(StringUtil.isNotEmpty(resultDto.getPageMockDealList())?StringUtil.isNotEmpty(resultDto.getPageMockDealList().get(index))?StringUtil.isNotEmpty(resultDto.getPageMockDealList().get(index).getType8())?resultDto.getPageMockDealList().get(index).getType8():DictCodeList.平安资产类型_债券.getValue():DictCodeList.平安资产类型_债券.getValue():DictCodeList.平安资产类型_债券.getValue());
                //riskRdsDto.setAssetType(DictCodeList.平安资产类型_债券.getValue());
                riskRdsDto.setAmount(mockProductDeal.getFpriceAmt()); // 债券：全价总额（元）
                riskRdsDto.setNetPrice(mockProductDeal.getUnitCprice()); // 债券 必填；其他不填 （元）
                riskRdsDto.setAllPrice(mockProductDeal.getUnitFprice()); // 债券 必填；其他不填 （元）
                riskRdsDto.setIsVirtual(DictCodeList.是否_是.getValue());
            }
            else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(),
                mockProductDeal.getFinprodType2())) {
                riskRdsDto.setAssetCode(mockProductDeal.getFinprodId());
                riskRdsDto.setAssetType(DictCodeList.平安资产类型_债券.getValue());
                riskRdsDto.setAmount(mockProductDeal.getFpriceAmt()); // 债券：全价总额（元）
                riskRdsDto.setNetPrice(mockProductDeal.getUnitCprice()); // 债券 必填；其他不填 （元）
                riskRdsDto.setAllPrice(mockProductDeal.getUnitFprice()); // 债券 必填；其他不填 （元）
                
                // 市场代码有则必填
                if (assetInfoMap.containsKey(mockProductDeal.getFinprodId())
                    && assetInfoMap.get(mockProductDeal.getFinprodId()).getFinProduct() != null) {
                    riskRdsDto.setMarketCode(assetInfoMap.get(
                        mockProductDeal.getFinprodId()).getFinProduct().getFinprodMarketId());
                }
            }
            else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue(),
                mockProductDeal.getFinprodType2())) {
                riskRdsDto.setAssetCode(mockProductDeal.getFinprodId());
                riskRdsDto.setAssetType(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
                riskRdsDto.setAmount(mockProductDeal.getPrinAmt()); // 存款、计划类利率型、项目类资产：本金金额（元）
            }
            else if (StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue(),
                mockProductDeal.getFinprodType2())) {
                riskRdsDto.setAssetCode(mockProductDeal.getFinprodId());
                riskRdsDto.setAssetType(DictCodeList.平安资产类型_项目类资产.getValue());
                riskRdsDto.setAmount(mockProductDeal.getPrinAmt()); // 存款、计划类利率型、项目类资产：本金金额（元）
            }
            riskRdsDto.setPs(mockProductDeal.getPs());
            riskRdsDto.setPriceAmt(mockProductDeal.getUnitCprice());
            riskRdsDto.setTradeNum(mockProductDeal.getShareAmt());
            riskRdsDto.setYieldEndRate(mockProductDeal.getYield());
            riskRdsDto.setEventType(BussinessDictCodeListRds.事件类型_投资交易.getValue());
            
            // 交易场所: 交易场所有则必填
            if (assetInfoMap.containsKey(mockProductDeal.getFinprodId())
                && assetInfoMap.get(mockProductDeal.getFinprodId()).getFinProduct() != null) {
                riskRdsDto.setTradePlace(assetInfoMap.get(
                    mockProductDeal.getFinprodId()).getFinProduct().getTradeMarket());
            }
            riskRdsDto.setCcy(mockProductDeal.getCcy());
            
            listMap.add(BeanUtil.object2Map(riskRdsDto));
            index++;
        }
        
        ClientData riskClientData = interfRiskRdsService.sendListDataCommon(listMap);
        if (riskClientData != null && riskClientData.getBody() != null
            && riskClientData.getBody().get("RSK_LIST") != null) {
            try {
                List<Map<String, Object>> riskDataList = (List<Map<String, Object>>) riskClientData.getBody().get("RSK_LIST");
                if (!StringUtil.isEmptyList(riskDataList)) {
                    Date riskDate = DateUtil.parse(平台日);
                    List<PtlMockTestRiskDto> mockTestRskList = new ArrayList<PtlMockTestRiskDto>();
                    for (Map<String, Object> dataMap : riskDataList) {
                        RiskTrialRetDto riskTrialRetDto = (RiskTrialRetDto) JsonUtils.map2entity(dataMap, RiskTrialRetDto.class);
                        PtlMockTestRiskDto mockTestRsk = new PtlMockTestRiskDto();
                        mockTestRsk.setSeqNo(IDGenerator.generate());
                        mockTestRsk.setIndId(riskTrialRetDto.getKriCode());
                        mockTestRsk.setIndName(riskTrialRetDto.getKriName());
                        mockTestRsk.setRiskDate(riskDate);
                        mockTestRsk.setwInvValue(riskTrialRetDto.getWarningLine());
                        mockTestRsk.setaInvValue(riskTrialRetDto.getApproveLine());
                        mockTestRsk.seteInvValue(riskTrialRetDto.getForbiddenLn());
                        mockTestRsk.setCurrValue(riskTrialRetDto.getKriValue());
                        mockTestRsk.setAssetCode(riskTrialRetDto.getAssetCode());
                        mockTestRsk.setKriStatus(riskTrialRetDto.getKriStatus());
                        mockTestRsk.setKriType(riskTrialRetDto.getKriType());
                        mockTestRsk.setKriExpr(riskTrialRetDto.getKriExpr());
                        if(StringUtil.isNotEmpty(riskTrialRetDto.getAssetName())){
                            mockTestRsk.setAssetName(riskTrialRetDto.getAssetName());
                        }else {
                            Object curAst=assetInfoMap.get(riskTrialRetDto.getAssetCode());
                            if(StringUtil.isNotEmpty(curAst)&&StringUtil.isNotEmpty(((MockAssetInfoDto) curAst).getFinProduct())){
                                mockTestRsk.setAssetName( ((MockAssetInfoDto) curAst).getFinProduct().getFinprodAbbr());
                            }
                        }

                        mockTestRskList.add(mockTestRsk);
                    }
                    resultDto.setMockTestRskList(mockTestRskList);
                }
            }
            catch (Exception e) {
                logger.error("风险试算发生异常：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 计算七日年化收益率图表（使用业绩基准）
     * @param basis
     * @param 业绩基准_转换Map
     * @param calcDateStart
     * @param calcDateEnd
     * @param calcDay
     * @param scale
     * @return
     */
    private BigDecimal calc业绩基准年化收益率_日日复利(int basis,
                                                Map<Date, BigDecimal> 业绩基准_转换Map,
                                             Date calcDateStart, Date calcDateEnd, long calcDay,
                                             int scale) {
        return calc业绩基准年化收益率_复利_basic(业绩基准_转换Map, calcDateStart, calcDateEnd, calcDay, basis,
                calcDay, scale);
    }

    /**
     * 计算七日年化收益率图表（使用业绩基准）
     * @param 业绩基准_转换Map
     * @param calcDateStart
     * @param calcDateEnd
     * @param calcDay
     * @param indexNumerator
     * @param indexDenominator
     * @param scale
     * @return
     */
    private BigDecimal calc业绩基准年化收益率_复利_basic(Map<Date, BigDecimal> 业绩基准_转换Map,
                                                 Date calcDateStart, Date calcDateEnd,
                                                 long calcDay, long indexNumerator,
                                                 long indexDenominator, int scale) {
        if (业绩基准_转换Map == null || 业绩基准_转换Map.isEmpty()) {
            return null;
        }
        BigDecimal 计算结果 = BigDecimal.ONE;
        BigDecimal 一万 = BigDecimal.valueOf(10000);
        Date tempCalcDate = calcDateStart;

        while (DateUtil.compareDate(calcDateEnd, tempCalcDate) >= 0) {
            if (业绩基准_转换Map.containsKey(tempCalcDate)) {
                计算结果 = ArithmeticUtil.multiply(计算结果,
                        ArithmeticUtil.doAdd(BigDecimal.ONE, ArithmeticUtil.divide(
                                业绩基准_转换Map.get(tempCalcDate), 一万, scale)));
            }

            tempCalcDate = DateUtil.addDay(tempCalcDate, 1);
        }

        // 解决double  NaN转BigDecimal报错
        if(!Double.isNaN(Math.pow(计算结果.doubleValue(), (double)indexNumerator / indexDenominator)))
            计算结果 = BigDecimal.valueOf(
                    Math.pow(计算结果.doubleValue(), (double)indexNumerator / indexDenominator));
        计算结果 = ArithmeticUtil.subtract(计算结果, BigDecimal.ONE);
        return 计算结果;
    }

    /**
     * 计算七日年化收益率图表（使用业绩基准）
     * @param basis
     * @param 业绩基准_转换Map
     * @param calcDateStart
     * @param calcDateEnd
     * @param calcDay
     * @param scale
     * @return
     */
    private static BigDecimal calc业绩基准年化收益率_月月复利(int basis,
                                                 Map<Date, BigDecimal> 业绩基准_转换Map,
                                             Date calcDateStart, Date calcDateEnd, long calcDay,
                                             int scale) {
        if (业绩基准_转换Map == null || 业绩基准_转换Map.isEmpty()) {
            return null;
        }
        BigDecimal 计算结果 = BigDecimal.ZERO;
        BigDecimal 一万 = BigDecimal.valueOf(10000);
        Date tempCalcDate = calcDateStart;

        while (DateUtil.compareDate(calcDateEnd, tempCalcDate) >= 0) {
            if (业绩基准_转换Map.containsKey(tempCalcDate)) {
                计算结果 = ArithmeticUtil.doAdd(计算结果,
                        业绩基准_转换Map.get(tempCalcDate));
            }

            tempCalcDate = DateUtil.addDay(tempCalcDate, 1);
        }

        计算结果 = ArithmeticUtil.divide(ArithmeticUtil.multiply(
                ArithmeticUtil.divide(计算结果, BigDecimal.valueOf(calcDay), scale),
                BigDecimal.valueOf(basis)), 一万, scale);

        return 计算结果;
    }

    /**
     * 计算七日年化收益率图表（使用业绩基准）
     * @param basis
     * @param 业绩基准_转换Map
     * @param calcDateStart
     * @param calcDateEnd
     * @param calcDay
     * @param scale
     * @return
     */
    private static BigDecimal calc业绩基准年化收益率_净值型(int basis,
                                                Map<Date, BigDecimal> 业绩基准_转换Map,
                                            Date calcDateStart, Date calcDateEnd, long calcDay,
                                            int scale) {
        if (业绩基准_转换Map == null || 业绩基准_转换Map.isEmpty()) {
            return null;
        }
        BigDecimal 开始日净值 = BigDecimal.ONE;
        BigDecimal 结束日净值 = BigDecimal.ONE;
        if(业绩基准_转换Map.get(calcDateEnd)==null){
            logger.info(DateUtil.format(calcDateEnd)+"当天没有组合估值信息...取值为1");
        }else{
            结束日净值 =
                    业绩基准_转换Map.get(calcDateEnd);
        }
        if(业绩基准_转换Map.get(calcDateStart)==null){
            logger.info(DateUtil.format(calcDateStart)+"当天没有组合估值信息...取值为1");
        }else{
            // 计算日期为1时 说明是起息日当天  取期初净值1
            开始日净值 =
                    业绩基准_转换Map.get(calcDateStart);
        }
        BigDecimal 计算结果 = ArithmeticUtil.subtract(结束日净值,开始日净值);
        计算结果 = ArithmeticUtil.divide(计算结果,开始日净值, scale);
        计算结果 = ArithmeticUtil.multiply(计算结果, BigDecimal.valueOf(basis));
        计算结果 = ArithmeticUtil.divide(计算结果, BigDecimal.valueOf(calcDay), scale);

        return 计算结果;
    }


    /**
     *
     * 计算七日年化收益率图表（使用业绩基准）
     * @param profitType
     * @param vdate
     * @param bonusPayCycle
     * @param basisNum
     * @param 业绩基准_转换Map
     * @param calcDate
     * @return
     */
    private BigDecimal calc业绩基准七日年化收益率(String profitType, Date vdate, String bonusPayCycle,
                int basisNum,Map<Date, BigDecimal> 业绩基准_转换Map,
                Date calcDate) {
        if (业绩基准_转换Map == null || 业绩基准_转换Map.isEmpty()) {
            return null;
        }

        BigDecimal 计算结果 = null;
        int scale = 12;

        // 产品存续天数
        long productSurvivingDays = DateUtil.getBetweenDayNumber(vdate, calcDate) + 1;

        // 货币型产品
        if (StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), profitType)) {
            // 产品存续天数<7（含计算日）天时：
            if (productSurvivingDays < 7) {
                // 日日复利
                if (StringUtil.equals(DictCodeList.分红规则_日付.getValue(), bonusPayCycle)) {
                    // 收益率%={[∏_(i=1)^N▒(1+r_i/10000) ]^(计息基础/N)-1}×100%
                    计算结果 = calc业绩基准年化收益率_日日复利(basisNum, 业绩基准_转换Map, vdate, calcDate,
                            productSurvivingDays, scale);
                }
                // 月月复利
                else {
                    // 收益率%=((∑_(i=1)^N▒r_i )/N)×计息基础/10000×100%
                    计算结果 = calc业绩基准年化收益率_月月复利(basisNum, 业绩基准_转换Map, vdate, calcDate,
                            productSurvivingDays, scale);
                }
            }
            // 产品存续天数N≥7天时：
            else {
                // 日日复利
                if (StringUtil.equals(DictCodeList.分红规则_日付.getValue(), bonusPayCycle)) {
                    // 收益率%={[∏_(i=1)^7▒(1+r_i/10000) ]^(计息基础/7)-1}×100%
                    计算结果 = calc业绩基准年化收益率_日日复利(basisNum, 业绩基准_转换Map,
                            DateUtil.addDay(calcDate, -6), calcDate, 7, scale);
                }
                // 月月复利
                else {
                    // 收益率%=((∑_(i=1)^7▒r_i )/7)×计息基础/10000×100%
                    计算结果 = calc业绩基准年化收益率_月月复利(basisNum, 业绩基准_转换Map,
                            DateUtil.addDay(calcDate, -6), calcDate, 7, scale);
                }
            }
        }
        // 净值型产品
        else if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), profitType)) {
            // 产品存续天数<7（含计算日）天时：
            if (productSurvivingDays < 7) {
                // 7日年化收益率R= （计算日的累计净值-起息日的累计净值）/起息日的累计净值*计息基础/计息天数
                计算结果 = calc业绩基准年化收益率_净值型(basisNum, 业绩基准_转换Map, DateUtil.addDay(calcDate, -7), calcDate,
                        productSurvivingDays, scale);
            }
            // 产品存续天数N≥7天时：
            else {
                // 7日年化收益率R= （计算日的累计净值-7日前的累计净值）/7日前的累计净值*计息基础/计息天数
                计算结果 = calc业绩基准年化收益率_净值型(basisNum, 业绩基准_转换Map, DateUtil.addDay(calcDate, -7),
                        calcDate, 7, scale);
            }
        }

        if (计算结果 != null) {
            计算结果 = ArithmeticUtil.multiply(ArithmeticUtil.round(计算结果, 6, RoundingMode.HALF_UP),new BigDecimal(100));
        }

        return 计算结果;
    }


    /**
     * 6.5.3.净值走势、万份收益走势等曲线加载
     * 6.5.4.七日年化收益率走势图曲线加载
     * @param ptlBaseInfoDto 组合基本信息
     * @param 平台日 平台日
     * @param 组合资产估值列表 组合资产估值列表
     * @param 全量组合估值列表 全量组合估值列表
     * @param resultDto 计算结果dto
     * @throws Exception 异常
     */
    private void do曲线数据处理(MockPtlPortfolioDto ptlBaseInfoDto, Date 平台日,
                          List<MockPtlSecValuationDto> 组合资产估值列表,
                          List<MockPtlPortfolioValuationDto> 全量组合估值列表,
                          PtlMockTest002Dto resultDto) throws Exception {
        // 展示区间为：封闭型组合为起息日至到期日、定开型为上一开放日至下一开放日、高频开放型为起息日至三个月后
        List<Date> periodList = RealTimeValIndicatorCalcUtil.genMockDatePeriod(
            ptlBaseInfoDto.getPaPtlPortfolio().getOpenType(), ptlBaseInfoDto.getVdate(),
            ptlBaseInfoDto.getMdate(), ptlBaseInfoDto.getFinOpenPlanList(), 平台日);
        Date 区间开始日 = periodList.get(0);
        Date 区间结束日 = periodList.get(1);


        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("portfolioId", ptlBaseInfoDto.getPortfolioId());
        // 根据组合代码查询组合对应产品的产品金融产品代码
        String finprodId = valuationInterfService.selectFinprodIdByPortfolioId(ptlBaseInfoDto.getPortfolioId());
        if (StringUtil.isBlank(finprodId)) {
            finprodId = ptlBaseInfoDto.getPortfolioId();
        }
        paramMap.put("finprodId", finprodId);
        List<FinBaseRuleInfo> finBaseRuleList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/selectAllBaseRule",
                paramMap, FinBaseRuleInfo.class);

        List<MstIntRateInfo> mstIntRateInfoList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/selectAlllv",
                paramMap, MstIntRateInfo.class);


        int basisNum = 365;
        // 按业绩基准+管理费率计算净值/万份收益
        Map<Date, BigDecimal> 净值型业绩基准_转换Map = null;
        Map<Date, BigDecimal> 货币型业绩基准_转换Map = null;
//        BigDecimal 货币型业绩基准_转换 = null;
        if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
            ptlBaseInfoDto.getProfitType())) {
            净值型业绩基准_转换Map = this.calc净值型业绩基准_转换(ptlBaseInfoDto.getVdate(),
                ptlBaseInfoDto.getMdate(), ptlBaseInfoDto.getFinBaseRuleYield(),finBaseRuleList,mstIntRateInfoList);
        }
        else {
            if (StringUtil.equals(DictCodeList.计息基础_A_360.getValue(),ptlBaseInfoDto.getBasis())) {
                basisNum = 360;
            }
            货币型业绩基准_转换Map = this.calc货币型业绩基准_转换(ptlBaseInfoDto.getVdate(),
                    ptlBaseInfoDto.getMdate(),ptlBaseInfoDto.getFinBaseRuleYield(),
                ptlBaseInfoDto.getBasis(),finBaseRuleList,mstIntRateInfoList);
        }
        
        Date calcDate = 区间开始日;
        List<MockTestNetTrend> mockTestNetTrendList = new ArrayList<>();
        while (DateUtil.compareDate(区间结束日, calcDate) >= 0) {
            MockTestNetTrend mockTestNetTrend = new MockTestNetTrend();
            // 日期
            mockTestNetTrend.setCpriceDate(calcDate);
            
            PtlMockTest002Dto tempResultDto = new PtlMockTest002Dto();
            this.do计算组合表现(ptlBaseInfoDto, 组合资产估值列表, 全量组合估值列表, null, null, null,
                DateUtil.format(calcDate), tempResultDto, 4);
            
            if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
                ptlBaseInfoDto.getProfitType())) {
                // 净值/万份收益
                mockTestNetTrend.setNetUnitValue(tempResultDto.getMockUnitValue());
                // 业绩基准_转换
                mockTestNetTrend.setBaseRuleChg(净值型业绩基准_转换Map.get(calcDate));

                mockTestNetTrend.setBaseRule(
                        calc业绩基准七日年化收益率(ptlBaseInfoDto.getProfitType(),
                                ptlBaseInfoDto.getVdate(), ptlBaseInfoDto.getBonusPayCycle(),
                                basisNum, 净值型业绩基准_转换Map, calcDate));
            }
            else {
                // 净值/万份收益
                mockTestNetTrend.setNetUnitValue(tempResultDto.getMockPYield());
                // 业绩基准_转换
                mockTestNetTrend.setBaseRuleChg(货币型业绩基准_转换Map.get(calcDate));

                mockTestNetTrend.setBaseRule(
                        calc业绩基准七日年化收益率(ptlBaseInfoDto.getProfitType(),
                                ptlBaseInfoDto.getVdate(), ptlBaseInfoDto.getBonusPayCycle(),
                                basisNum, 货币型业绩基准_转换Map, calcDate));
            }
            // 当天净值下跌
            if(tempResultDto.getMockValChg()!=null&&tempResultDto.getMockValChg().compareTo(BigDecimal.ZERO)<0){
                logger.info(DateUtil.format(calcDate)+"当天净值跌幅为"+tempResultDto.getMockValChg());
            }
            // 涨跌幅
            mockTestNetTrend.setValChg(tempResultDto.getMockValChg());
            // 七日年化收益率
            mockTestNetTrend.setSevenDayRate(tempResultDto.getMockSevenDayRate());
            // 七日年化收益率（业绩基准计算）   4位小数
//            mockTestNetTrend.setBaseRule(ArithmeticUtil.multiply(ptlBaseInfoDto.getFinBaseRuleYield(),new BigDecimal(100)));

            mockTestNetTrendList.add(mockTestNetTrend);
            
            calcDate = DateUtil.addDay(calcDate, 1);
        }

        resultDto.setMockTestNetTrendList(mockTestNetTrendList);
    }
    
    /**
     * 计算净值型业绩基准_转换
     * 净值走势：以起息日前一日为1，后续每天的单位净值 = 昨日单位净值 *（1+ 业绩基准）
     * 
     * @param vdate 组合起息日
     * @param mdate 组合到期日
     * @param 业绩基准 业绩基准
     * @return 净值型业绩基准_转换
     */
    private Map<Date, BigDecimal> calc净值型业绩基准_转换(Date vdate, Date mdate, BigDecimal 业绩基准,List<FinBaseRuleInfo> finBaseRuleList,List<MstIntRateInfo> mstIntRateInfoList ) {
        Map<Date, BigDecimal> resultMap = new HashMap<>();

        BigDecimal 昨日单位净值 = BigDecimal.ONE;
        Date calcDate = vdate;
        while (DateUtil.compareDate(mdate, calcDate) >= 0) {
            if(finBaseRuleList!=null){
                Date compareDate = calcDate;
                FinBaseRuleInfo finBaseRuleInfo = finBaseRuleList.stream().filter(x->DateUtil.compareDate(x.getEffDate(),compareDate)<=0).
                        collect(Collectors.maxBy(Comparator.comparing(FinBaseRuleInfo::getEffDate))).orElse(null);
                if(finBaseRuleInfo!=null){

                    if (StringUtil.equals(DictCodeList.利率类型_固定利率.getValue(), finBaseRuleInfo.getRateType())) {
                        // 固定利率
                        业绩基准 = finBaseRuleInfo.getRate();
                    }
                    else if (StringUtil.equals(DictCodeList.利率类型_浮动利率.getValue(), finBaseRuleInfo.getRateType())) {
                        // 基准来源利率
                        if(mstIntRateInfoList!=null){
                            MstIntRateInfo mstIntRateInfo = mstIntRateInfoList.stream()
                                    .filter(x->(DateUtil.compareDate(x.getEffDate(),compareDate)<=0&&StringUtil.equals(finBaseRuleInfo.getIndexId(),x.getIntRateId()))
                                    ).
                                    collect(Collectors.maxBy(Comparator.comparing(MstIntRateInfo::getEffDate))).orElse(null);
                            if(StringUtil.isNotEmpty(mstIntRateInfo)){
                                业绩基准 = ArithmeticUtil.doAdd(
                                        ArithmeticUtil.multiply(mstIntRateInfo.getIntRate(),finBaseRuleInfo.getCoefficient()==null?BigDecimal.ONE:finBaseRuleInfo.getCoefficient()),finBaseRuleInfo.getSpreadRate());
                            }
                        }
                    }
                }
            }
            // 以起息日前一日为1，后续每天的单位净值 = 昨日单位净值 *（1+ 业绩基准）
            BigDecimal 当日单位净值 = ArithmeticUtil.multiply(昨日单位净值,
                ArithmeticUtil.doAdd(BigDecimal.ONE, ArithmeticUtil.divide(业绩基准,new BigDecimal(365),8,RoundingMode.HALF_UP)));
            当日单位净值 = ArithmeticUtil.round(当日单位净值, 8, RoundingMode.HALF_UP);
            resultMap.put(calcDate, 当日单位净值);

            calcDate = DateUtil.addDay(calcDate, 1);
            昨日单位净值 = 当日单位净值;
        }

        return resultMap;
    }
    
    /**
     * 计算货币型业绩基准_转换
     * 万份收益走势：业绩基准 * 10000 / 计息基础
     * 
     * @param 业绩基准 业绩基准
     * @param basis 计息基础
     * @return 货币型业绩基准_转换
     */
    private Map<Date, BigDecimal> calc货币型业绩基准_转换(Date vdate, Date mdate,BigDecimal 业绩基准, String basis,List<FinBaseRuleInfo> finBaseRuleList,List<MstIntRateInfo> mstIntRateInfoList ) {
        // 计息基础
        int basisNum = 365;
        if (StringUtil.equals(DictCodeList.计息基础_A_360.getValue(), basis)) {
            basisNum = 360;
        }

        Map<Date, BigDecimal> resultMap = new HashMap<>();

        Date calcDate = vdate;
        while (DateUtil.compareDate(mdate, calcDate) >= 0) {
            if(finBaseRuleList!=null){
                Date compareDate = calcDate;
                FinBaseRuleInfo finBaseRuleInfo = finBaseRuleList.stream().filter(x->DateUtil.compareDate(x.getEffDate(),compareDate)<=0).
                        collect(Collectors.maxBy(Comparator.comparing(FinBaseRuleInfo::getEffDate))).orElse(null);
                if(finBaseRuleInfo!=null){

                    if (StringUtil.equals(DictCodeList.利率类型_固定利率.getValue(), finBaseRuleInfo.getRateType())) {
                        // 固定利率
                        业绩基准 = finBaseRuleInfo.getRate();
                    }
                    else if (StringUtil.equals(DictCodeList.利率类型_浮动利率.getValue(), finBaseRuleInfo.getRateType())) {
                        // 基准来源利率
                        if(mstIntRateInfoList!=null){
                            MstIntRateInfo mstIntRateInfo = mstIntRateInfoList.stream()
                                    .filter(x->(DateUtil.compareDate(x.getEffDate(),compareDate)<=0&&StringUtil.equals(finBaseRuleInfo.getIndexId(),x.getIntRateId()))
                                    ).
                                            collect(Collectors.maxBy(Comparator.comparing(MstIntRateInfo::getEffDate))).orElse(null);
                            if(StringUtil.isNotEmpty(mstIntRateInfo)){
                                业绩基准 = ArithmeticUtil.doAdd(
                                        ArithmeticUtil.multiply(mstIntRateInfo.getIntRate(),finBaseRuleInfo.getCoefficient()==null?BigDecimal.ONE:finBaseRuleInfo.getCoefficient()),finBaseRuleInfo.getSpreadRate());
                            }
                        }
                    }
//                    else if (StringUtil.equals(DictCodeList.利率类型_浮动利率.getValue(), finBaseRuleInfo.getRateType())) {
//                        // 基准来源利率
//                        Map<String, Object> paramMap = new HashMap<String, Object>();
//                        paramMap.put("indexId", finBaseRuleInfo.getIndexId());
//                        paramMap.put("paramDate",calcDate);
//                        MstIntRateInfo dto1 = restTemplate.postForObject(
//                                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/selectlv",
//                                paramMap, MstIntRateInfo.class);
//                        if (StringUtil.isNotEmpty(dto1)) {
//                            业绩基准 = dto1.getIntRate();
//                        }
//                    }
                }
            }
            // 万份收益走势：业绩基准 * 10000 / 计息基础
            BigDecimal result = ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(业绩基准, BigDecimal.valueOf(10000)), basisNum, 6);
            resultMap.put(calcDate, result);
            calcDate = DateUtil.addDay(calcDate, 1);
        }

        return resultMap;


    }

    /**
     * 根据实时估值结果整理资产配置列表数据
     * @param 测算前组合资产估值列表 测算前（考虑当日成交）组合资产估值
     * @param 测算前组合估值列表 测算前（考虑当日成交）组合估值
     * @param 测算后组合资产估值列表 测算后组合资产估值
     * @param 测算后组合估值列表 测算后组合估值
     * @param resultDto 结束结果dto
     * @param 平台日 平台日
     */
    private void buildMockTestTaaData(List<MockPtlSecValuationDto> 测算前组合资产估值列表,
                                      List<MockPtlPortfolioValuationDto> 测算前组合估值列表,
                                      List<MockPtlSecValuationDto> 测算后组合资产估值列表,
                                      List<MockPtlPortfolioValuationDto> 测算后组合估值列表,
                                      PtlMockTest002Dto resultDto, Date 平台日) {
        if (测算前组合资产估值列表 == null) {
            测算前组合资产估值列表 = new ArrayList<>();
        }
        if (测算前组合估值列表 == null) {
            测算前组合估值列表 = new ArrayList<>();
        }
        if (测算后组合资产估值列表 == null) {
            测算后组合资产估值列表 = new ArrayList<>();
        }
        if (测算后组合估值列表 == null) {
            测算后组合估值列表 = new ArrayList<>();
        }
        
        List<MockPtlSecValuationDto> 测算前当日组合资产估值列表 = 测算前组合资产估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList());
        
        MockPtlPortfolioValuationDto 测算前组合估值 = null;
        List<MockPtlPortfolioValuationDto> tempList = 测算前组合估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList());
        if (!StringUtil.isEmptyList(tempList)) {
            测算前组合估值 = tempList.get(0);
        }
        resultDto.setPreMockTestTaaList(
            this.buildMockTestTaaOneData(测算前当日组合资产估值列表, 测算前组合估值, true));

        List<MockPtlSecValuationDto> 测算后当日组合资产估值列表 = 测算后组合资产估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList());
        
        MockPtlPortfolioValuationDto 测算后组合估值 = null;
        tempList = 测算后组合估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList());
        if (!StringUtil.isEmptyList(tempList)) {
            测算后组合估值 = tempList.get(0);
        }
        resultDto.setAfterMockTestTaaList(
            this.buildMockTestTaaOneData(测算后当日组合资产估值列表, 测算后组合估值, false));
    }
    
    /**
     * 根据实时估值结果整理资产配置列表数据
     * 
     * @param 组合资产估值列表 组合资产估值列表
     * @param 组合估值 组合估值
     * @param isMockBefore 是否测算前(01：测算前 02：测算后PK)
     * @return 资产配置列表数据
     */
    private List<PtlMockTestTaaDto> buildMockTestTaaOneData(List<MockPtlSecValuationDto> 组合资产估值列表,
                                                      MockPtlPortfolioValuationDto 组合估值,
                                                      boolean isMockBefore) {
        List<PtlMockTestTaaDto> resultList = new ArrayList<>();
        
        if (组合资产估值列表 == null || 组合估值 == null) {
            return resultList;
        }

//        BigDecimal 组合净资产 = 组合估值.getTdyAssetValue();
        BigDecimal 市值总和 = ArithmeticUtil.subtract(ArithmeticUtil.doAdd(
                组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_资产,
                        x.getAssetLiabilitiesMark())&&x.getDscCostAmt()!=null).map(MockPtlSecValuationDto::getDscCostAmt).reduce(BigDecimal.ZERO,BigDecimal::add)
                        ,组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_资产,
                        x.getAssetLiabilitiesMark())&&x.getTdyFloatIngpl()!=null).map(MockPtlSecValuationDto::getTdyFloatIngpl).reduce(BigDecimal.ZERO,BigDecimal::add)
                        ,组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_资产,
                        x.getAssetLiabilitiesMark())&&x.getTdyIntincexp()!=null).map(MockPtlSecValuationDto::getTdyIntincexp).reduce(BigDecimal.ZERO,BigDecimal::add)
                ,组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_资产,
                        x.getAssetLiabilitiesMark())&&x.getDelayPayAmt()!=null).map(MockPtlSecValuationDto::getDelayPayAmt).reduce(BigDecimal.ZERO,
                        BigDecimal::add))
                ,ArithmeticUtil.doAdd(组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债,
                    x.getAssetLiabilitiesMark())&&x.getDscCostAmt()!=null).map(MockPtlSecValuationDto::getDscCostAmt).reduce(BigDecimal.ZERO,BigDecimal::add)
                    ,组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债,
                        x.getAssetLiabilitiesMark())&&x.getTdyFloatIngpl()!=null).map(MockPtlSecValuationDto::getTdyFloatIngpl).reduce(BigDecimal.ZERO,BigDecimal::add)
                        ,组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债,
                                x.getAssetLiabilitiesMark())&&x.getTdyIntincexp()!=null).map(MockPtlSecValuationDto::getTdyIntincexp).reduce(BigDecimal.ZERO,BigDecimal::add)
                        ,组合资产估值列表.stream().filter(x->StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债,
                                x.getAssetLiabilitiesMark())&&x.getDelayPayAmt()!=null).map(MockPtlSecValuationDto::getDelayPayAmt).reduce(BigDecimal.ZERO,BigDecimal::add))
        );

        Map<String, List<MockPtlSecValuationDto>> 组合资产估值分组Map = 组合资产估值列表.stream().collect(
                JoyinCollectors.groupingBy(x -> StringUtil.equals(x.getTgAssetType(),RealTimeValCommonUtil.资产类型_模拟资产)?
                        x.getFinprodType3():x.getTgAssetType()));
        int size = 组合资产估值分组Map.keySet().size();
        BigDecimal total占比 = BigDecimal.ZERO;
        int index = 0;
        for (String tgAssetType : 组合资产估值分组Map.keySet()) {
            List<MockPtlSecValuationDto> curr组合资产估值 = 组合资产估值分组Map.get(tgAssetType);
            BigDecimal 成本 = BigDecimal.ZERO;
            BigDecimal 市值 = BigDecimal.ZERO;
            BigDecimal 应收利息 = BigDecimal.ZERO;
            BigDecimal 资产价值 = BigDecimal.ZERO;
            BigDecimal 待清算款 =BigDecimal.ZERO;
            
            String assetLiabilitiesMark = null;
            for (MockPtlSecValuationDto mockPtlSecValuationDto : curr组合资产估值) {
                assetLiabilitiesMark = mockPtlSecValuationDto.getAssetLiabilitiesMark();
                if (StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债, assetLiabilitiesMark)) {
                    成本 = ArithmeticUtil.subtract(成本, mockPtlSecValuationDto.getDscCostAmt());
                    市值 = ArithmeticUtil.subtract(ArithmeticUtil.subtract(市值, mockPtlSecValuationDto.getDscCostAmt()),
                            mockPtlSecValuationDto.getTdyFloatIngpl());
                    应收利息 = ArithmeticUtil.subtract(应收利息, mockPtlSecValuationDto.getTdyIntincexp());
                    资产价值 = ArithmeticUtil.subtract(资产价值, mockPtlSecValuationDto.getAssetValue());
                }else{
                    成本 = ArithmeticUtil.doAdd(成本, mockPtlSecValuationDto.getDscCostAmt());
                    市值 = ArithmeticUtil.doAdd(市值, mockPtlSecValuationDto.getDscCostAmt(),
                            mockPtlSecValuationDto.getTdyFloatIngpl());
                    应收利息 = ArithmeticUtil.doAdd(应收利息, mockPtlSecValuationDto.getTdyIntincexp());
                    资产价值 = ArithmeticUtil.doAdd(资产价值, mockPtlSecValuationDto.getAssetValue());
                }
                待清算款=ArithmeticUtil.doAdd(待清算款, mockPtlSecValuationDto.getDelayPayAmt());
            }
            if(待清算款!=null && 待清算款.compareTo(BigDecimal.ZERO)!=0){//待清算款
               // 市值总和=ArithmeticUtil.doAdd(市值总和,待清算款);
                PtlMockTestTaaDto 待清算款mockTestTaa = new PtlMockTestTaaDto();
                if (isMockBefore) {
                    待清算款mockTestTaa.setIsMock(DictCodeList.是否_是.getValue());
                }
                else {
                    待清算款mockTestTaa.setIsMock(DictCodeList.是否_否.getValue());
                }
                待清算款mockTestTaa.setAssetType(RealTimeValCommonUtil.资产类型_待清算款);
                待清算款mockTestTaa.setCost(待清算款);
                待清算款mockTestTaa.setMarketValue(待清算款);
                待清算款mockTestTaa.setAccruedInc(BigDecimal.ZERO);
                BigDecimal 规模占比 = ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(待清算款, BigDecimal.valueOf(100)), 市值总和, 2);
                total占比 = total占比.add(规模占比);
                待清算款mockTestTaa.setAmtRation(规模占比);
                    resultList.add(待清算款mockTestTaa);
            }
            // 模拟测算资产配置
            PtlMockTestTaaDto mockTestTaa = new PtlMockTestTaaDto();
            mockTestTaa.setAssetType(tgAssetType);
            if (isMockBefore) {
                mockTestTaa.setIsMock(DictCodeList.是否_是.getValue());
            }
            else {
                mockTestTaa.setIsMock(DictCodeList.是否_否.getValue());
            }
            mockTestTaa.setCost(成本);
            mockTestTaa.setMarketValue(市值);
            mockTestTaa.setAccruedInc(应收利息);

            BigDecimal 规模占比 = ArithmeticUtil.divide(
                ArithmeticUtil.multiply(ArithmeticUtil.doAdd(市值,应收利息), BigDecimal.valueOf(100)), 市值总和, 2);
//            if (StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债, assetLiabilitiesMark)) {
//                规模占比 = ArithmeticUtil.multiply(规模占比, BigDecimal.valueOf(-1));
//            }
            if(++index==size){
                规模占比 = ArithmeticUtil.subtract(BigDecimal.valueOf(100),total占比);
            }
            total占比 = total占比.add(规模占比);
            mockTestTaa.setAmtRation(规模占比);
            if(资产价值!=null&&BigDecimal.ZERO.compareTo(资产价值)!=0)
            resultList.add(mockTestTaa);
        }
        
        return resultList;
    }
    
    /**
     * 6.2.确定计算截止日
     * @param 平台日  平台日
     * @param ptlBaseInfoDto 组合基本信息
     * @param inputDto 画面输入参数dto
     * @return 计算截止日
     */
    private String calc计算截止日(Date 平台日, MockPtlPortfolioDto ptlBaseInfoDto, PtlMockTest002Dto inputDto) {
        // 下一季报日
        List<Date> periodList = RealTimeValIndicatorCalcUtil.genMockQuarterDatePeriod(
            ptlBaseInfoDto.getVdate(), ptlBaseInfoDto.getMdate(), 平台日);
        Date 下一季报日 = periodList.get(1);

        // 到期收益率计算结束日
        periodList = RealTimeValIndicatorCalcUtil.genMockDatePeriod(
            ptlBaseInfoDto.getPaPtlPortfolio().getOpenType(), ptlBaseInfoDto.getVdate(),
            ptlBaseInfoDto.getMdate(), ptlBaseInfoDto.getFinOpenPlanList(), 平台日);
        Date 到期收益率计算结束日 = periodList.get(1);
        
        // 区间收益率计算结束日
        Date 区间收益率计算结束日 = inputDto.getMockRangeEndDate();
        
        Date 计算截止日 = 下一季报日;
        if (DateUtil.compareDate(计算截止日, 到期收益率计算结束日) < 0) {
            计算截止日 = 到期收益率计算结束日;
        }
        if (区间收益率计算结束日 != null) {
            if (DateUtil.compareDate(计算截止日, 区间收益率计算结束日) < 0) {
                计算截止日 = 区间收益率计算结束日;
            }
        }
        
        return DateUtil.format(计算截止日);
    }
    
    /**
     * 模拟交易信息生成
     * 根据模拟交易生成对应模拟金融产品交易及模拟交易类金融产品
     * 并将模拟交易类金融产品对应资产信息添加到全部资产基本信息列表
     * @param resultDto 结算结果dto
     */
    private void buildMockTradeInfo(PtlMockTest002Dto resultDto, Date 平台日, List<MockAssetInfoDto> 全部资产基本信息列表) {
        List<MockProductDeal> mockProductDealList = new ArrayList<>();
        List<MockTradeProduct> mockTradeProductList = new ArrayList<>();
        
        Map<String, MockAssetInfoDto> assetInfoMap = 全部资产基本信息列表.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x));
        boolean flag = true;
        for (PtlMockTestTradeDto mockTestTradeDto : resultDto.getPageMockDealList()) {
            // 模拟金融产品交易
            MockProductDeal mockProductDeal = new MockProductDeal();
            mockProductDeal.setTradeId(IDGenerator.generate());
            if (StringUtil.isNotBlank(mockTestTradeDto.getFinprodId())) {
                // 金融产品代码 实际资产：画面模拟交易.资产代码对应的金融产品代码
                mockProductDeal.setFinprodId(mockTestTradeDto.getFinprodId());
                // 金融产品类型（投管分类）
                if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), mockTestTradeDto.getType8())) {
                    mockProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
                }
                else if (StringUtil.equals(DictCodeList.平安资产类型_计划类资产_利率型.getValue(), mockTestTradeDto.getType8())) {
                    mockProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
                }
                if (StringUtil.equals(DictCodeList.平安资产类型_项目类资产.getValue(), mockTestTradeDto.getType8())) {
                    mockProductDeal.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
                }
                
                // 交易场所
                if (assetInfoMap.containsKey(mockTestTradeDto.getFinprodId())) {
                    if (assetInfoMap.get(
                        mockTestTradeDto.getFinprodId()).getFinProduct() != null) {
                        mockProductDeal.setTradeMarket(assetInfoMap.get(
                            mockTestTradeDto.getFinprodId()).getFinProduct().getTradeMarket());
                    }
                    else if (assetInfoMap.get(
                        mockTestTradeDto.getFinprodId()).getFinTradeProduct() != null) {
                        mockProductDeal.setTradeMarket(assetInfoMap.get(
                            mockTestTradeDto.getFinprodId()).getFinTradeProduct().getTradeMarket());
                    }
                }
            }
            else {
                // 金融产品代码 虚拟资产：模拟交易类金融产品：MOCK_TRADE_PRODUCT表登录的金融产品代码
                mockProductDeal.setFinprodId(mockProductDeal.getTradeId());
//                mockProductDeal.setFinprodId(RealTimeValCommonUtil.资产类型_模拟资产);
                // 金融产品类型（投管分类）：null
                mockProductDeal.setFinprodType2(RealTimeValCommonUtil.资产类型_模拟资产);
            }
            mockProductDeal.setBranch(0L);
            mockProductDeal.setPortfolioId(mockTestTradeDto.getPortfolioId());
            mockProductDeal.setTradeDate(平台日);
            mockProductDeal.setMdate(mockTestTradeDto.getMdate());
            mockProductDeal.setSettleDate(平台日);
            mockProductDeal.setCcy(StringUtil.isEmpty(mockTestTradeDto.getCcy())?
                    DictCodeList.币种_人民币_CNY.getValue():mockTestTradeDto.getCcy());
            // 单位净价: 画面模拟交易.交易净价
            mockProductDeal.setUnitCprice(mockTestTradeDto.getTradePrice());
            // 单位利息: 画面模拟交易.应计利息
            mockProductDeal.setUnitInt(mockTestTradeDto.getAccuir());
            // 单位全价: 画面模拟交易.交易净价 + 画面模拟交易.应计利息
            mockProductDeal.setUnitFprice(ArithmeticUtil.doAdd(mockTestTradeDto.getTradePrice(), mockTestTradeDto.getAccuir()));
            if (StringUtil.isNotBlank(mockTestTradeDto.getFinprodId())&&StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                mockTestTradeDto.getType8())) {
                // 净价总额: 债券：画面模拟交易.交易净价 * 画面模拟交易.交易数量/100
                mockProductDeal.setCpriceAmt(
                    ArithmeticUtil.divide(ArithmeticUtil.multiply(mockTestTradeDto.getTradePrice(),
                        mockTestTradeDto.getAmount()), BigDecimal.valueOf(100), 2));
                // 交易份额: 债券：画面模拟交易.交易数量/100 非标：画面模拟交易.交易数量
                mockProductDeal.setShareAmt(ArithmeticUtil.divide(mockTestTradeDto.getAmount(),
                    BigDecimal.valueOf(100), 2));
                // 交易本金: 债券：画面模拟交易.交易净价 * 画面模拟交易.交易数量/100
                mockProductDeal.setPrinAmt(
                    ArithmeticUtil.divide(ArithmeticUtil.multiply(mockTestTradeDto.getTradePrice(),
                        mockTestTradeDto.getAmount()), BigDecimal.valueOf(100), 2));
                // 利息总额: 债券：画面模拟交易.应计利息 * 画面模拟交易.交易数量/100
                mockProductDeal.setIntAmt(
                    ArithmeticUtil.divide(ArithmeticUtil.multiply(mockTestTradeDto.getAccuir(),
                        mockTestTradeDto.getAmount()), BigDecimal.valueOf(100), 2));
                // 全价总额: 债券：（画面模拟交易.交易净价 + 画面模拟交易.应计利息）* 画面模拟交易.交易数量/100
                mockProductDeal.setFpriceAmt(ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(ArithmeticUtil.doAdd(mockTestTradeDto.getTradePrice(),
                        mockTestTradeDto.getAccuir()), mockTestTradeDto.getAmount()),
                    BigDecimal.valueOf(100), 2));
            }
            else {
                // 净价总额: 其他：画面模拟交易.交易净价 * 画面模拟交易.交易数量
                mockProductDeal.setCpriceAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(
                    mockTestTradeDto.getTradePrice(), mockTestTradeDto.getAmount()),
                        BigDecimal.valueOf(100), 2));
                // 交易份额: 债券：画面模拟交易.交易数量/100 非标：画面模拟交易.交易数量
                mockProductDeal.setShareAmt(mockTestTradeDto.getAmount());
                // 交易本金: 其他：画面模拟交易.交易净价 * 画面模拟交易.交易数量
                mockProductDeal.setPrinAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(
                    mockTestTradeDto.getTradePrice(), mockTestTradeDto.getAmount()),
                        BigDecimal.valueOf(100), 2));
                // 利息总额: 其他：画面模拟交易.应计利息 * 画面模拟交易.交易数量
                mockProductDeal.setIntAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(mockTestTradeDto.getAccuir(),
                    mockTestTradeDto.getAmount()),
                        BigDecimal.valueOf(100), 2));
                // 全价总额: 其他：（画面模拟交易.交易净价 + 画面模拟交易.应计利息） * 画面模拟交易.交易数量
                mockProductDeal.setFpriceAmt(ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(ArithmeticUtil.doAdd(mockTestTradeDto.getTradePrice(),
                        mockTestTradeDto.getAccuir()), mockTestTradeDto.getAmount()),
                        BigDecimal.valueOf(100), 2));
            }
            // 交易金额: 画面模拟交易.交易金额
            mockProductDeal.setTradeAmt(mockTestTradeDto.getTradeAmt());
            // 交易方向: 画面模拟交易.交易方向
            mockProductDeal.setPs(mockTestTradeDto.getPs());
            // 到期收益率: 画面模拟交易.到期收益率/100
            mockProductDeal.setYield(mockTestTradeDto.getYield());
            // 行权收益率: 画面模拟交易.行权收益率/100
            mockProductDeal.setExerYield(mockTestTradeDto.getExerYield());
            // 投资目的: 画面模拟交易.投资目的
            mockProductDeal.setInvAim(mockTestTradeDto.getInvAim());
//            // 投资目的 只有债券用投资目的  其他模拟资产投资目的置为999999
//            if(!StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券.getValue(),mockProductDeal.getFinprodType2())){
//                mockProductDeal.setInvAim("999999");
//            }
            // 交易状态: 01 初始
            mockProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            // 是否已撤销: N 否
            mockProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            // 交易市场: 二级市场
            mockProductDeal.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
            
            mockProductDealList.add(mockProductDeal);
            
            // 模拟交易类金融产品
            if (StringUtil.isBlank(mockTestTradeDto.getFinprodId())) {
                MockTradeProduct mockTradeProduct = new MockTradeProduct();
                mockTradeProduct.setFinprodId(mockProductDeal.getFinprodId());
                mockTradeProduct.setBranch(0L);
                mockTradeProduct.setCcy(mockTestTradeDto.getCcy());
                mockTradeProduct.setMdate(mockTestTradeDto.getMdate());
                mockTradeProduct.setIntRate(mockTestTradeDto.getYield());
                
                mockTradeProductList.add(mockTradeProduct);
                
                // 模拟交易类金融产品对应资产信息
                MockAssetInfoDto mockAssetInfoDto = new MockAssetInfoDto();
                mockAssetInfoDto.setFinprodId(mockProductDeal.getFinprodId());
                mockAssetInfoDto.setTgAssetType(RealTimeValCommonUtil.资产类型_模拟资产);
                mockAssetInfoDto.setBranch(0L);
                mockAssetInfoDto.setMockTradeProduct(mockTradeProduct);
                mockAssetInfoDto.setFinprodType3(mockTestTradeDto.getType8());
                // 用于模拟资产区分资产类型
                mockProductDeal.setFinprodType(mockTestTradeDto.getType8());
                mockProductDeal.setFinprodAbbr(mockTestTradeDto.getFinprodAbbr());
//                mockProductDeal.setFinprodType3(mockTestTradeDto.getType8());
                FinProduct finProduct = new FinProduct();
                BeanUtil.copyPropertiesIgnoreNull(finProduct,mockTradeProduct);
                mockAssetInfoDto.setFinProduct(finProduct);

                // 封装现金流付息计划  用于生成到期交易
                List<MockFinCashPlanDto> mockFinCashPlanList = new ArrayList<MockFinCashPlanDto>();
                MockFinCashPlanDto mockFinCashPlanDto = new MockFinCashPlanDto();
                FinCashInfo finCashInfo = new FinCashInfo();
                finCashInfo.setCashId(RealTimeValCommonUtil.资产类型_模拟资产);
                mockFinCashPlanDto.setFinCashInfo(finCashInfo);
                FinCashPlan finCashPlan = new FinCashPlan();
                finCashPlan.setCashId(RealTimeValCommonUtil.资产类型_模拟资产);
                finCashPlan.setCashType(DictCodeList.现金流类型_到期收息.getValue());
                finCashPlan.setFinprodId(mockProductDeal.getFinprodId());
                finCashPlan.setFinprodType2(RealTimeValCommonUtil.资产类型_模拟资产);
                finCashPlan.setMdate(mockTestTradeDto.getMdate());
                finCashPlan.setPayDate(mockTestTradeDto.getMdate());
                finCashPlan.setVdate(平台日);
                finCashPlan.setPrinAmt(mockTestTradeDto.getAmount());
                finCashPlan.setCashBaseamt(mockTestTradeDto.getAmount());
                finCashPlan.setIsPayInt(DictCodeList.是否_否.getValue());
                mockFinCashPlanDto.setPaidOrNot(DictCodeList.是否_否.getValue());
                mockFinCashPlanDto.setFinCashPlan(finCashPlan);
                mockFinCashPlanList.add(mockFinCashPlanDto);
                MockFinCashPlanDto mockFinCashPlanDto2 = new MockFinCashPlanDto();
                BeanUtil.copyPropertiesIgnoreNull(mockFinCashPlanDto2,mockFinCashPlanDto);
                FinCashPlan finCashPlan2 = new FinCashPlan();
                BeanUtil.copyPropertiesIgnoreNull(finCashPlan2,finCashPlan);
                finCashPlan2.setCashType(DictCodeList.现金流类型_到期还本.getValue());
                mockFinCashPlanDto2.setFinCashPlan(finCashPlan2);
                mockFinCashPlanList.add(mockFinCashPlanDto2);

                mockAssetInfoDto.setMockFinCashPlanDtoList(mockFinCashPlanList);
//                MockFinCashPlanDto.setFinCashPlan();

                // 封装现金流付息规则的收益率用于计提
                List<FinCashCalcRule> finCashCalcRuleList = new ArrayList<FinCashCalcRule>();
                FinCashCalcRule finCashCalcRule = new FinCashCalcRule();
                finCashCalcRule.setCashId(RealTimeValCommonUtil.资产类型_模拟资产);
                finCashCalcRule.setYield(mockTestTradeDto.getYield());
                finCashCalcRule.setEffDate(平台日);
                finCashCalcRule.setBasis(DictCodeList.计息基础_A_365.getValue());
                finCashCalcRuleList.add(finCashCalcRule);
                mockAssetInfoDto.setFinCashCalcRuleList(finCashCalcRuleList);


                FinProductType finProductType = new FinProductType();
                finProductType.setType8(RealTimeValCommonUtil.资产类型_模拟资产);
                mockAssetInfoDto.setFinProductType(finProductType);
                // 模拟资产（不选择产品代码） 防止重复
//                if(flag)
                全部资产基本信息列表.add(mockAssetInfoDto);
//                flag = false;
            }
        }
        resultDto.setMockProductDealList(mockProductDealList);
        resultDto.setMockTradeProductList(mockTradeProductList);
        
    }

    /**
     * 组合实时估值
     * 
     * @param mockAssetInfoDtoList 资产基本信息列表 数据结构：List<资产基本信息DTO> 参考【数据结构定义】定义
     * @param mockPtlPortfolioDto 组合基本信息DTO 数据结构：参考【数据结构定义】定义
     * @param mockPtlSecValuationDtoList 组合资产估值列表 数据结构：List<组合资产估值扩展Entity>
     * @param mockPtlPortfolioValuationDtoList 组合估值列表 数据结构：List<组合估值DTO> 参考【数据结构定义】定义
     * @param mockPtlFeePositionDtoList 组合费用头寸扩展列表 数据结构：List<组合费用头寸Entity>
     * @param assetTradeList 资产端交易列表 数据结构：List<金融产品交易扩展>
     * @param liabilitiesTradeList 负债端交易列表 数据结构：List<金融产品交易扩展>
     * @param feeTradeList 费用交易列表 数据结构：List<金融产品交易扩展>
     * @param assetTdayPlusNdayTradeList 资产端T+N交易列表 数据结构：List<金融产品交易扩展> 说明：查询对象交易日<平台日 且 交割日>= 平台日
     * @param liabilitiesTdayPlusNdayTradeList 负债端T+N交易列表 数据结构：List<金融产品交易扩展> 说明：查询对象交易日<平台日 且
     *            交割日>= 平台日
     * @param mockPtlCashPositionMap 组合现金DTO 数据结构：参考【数据结构定义】定义 说明：不含本次传入金融产品交易 对现金的影响
     * @param mockStartDate 测算开始日
     * @param mockEndDate 测算结束日
     * @param baseDate 基准日期
     * @param mstExchargeQuoteList 汇率行情列表
     * @param financingCost 融资成本
     * @param reinvestmentRate 再投收益率
     * @param compositeRate 综合费率
     * @param T_N资产变化 T+N资产变化
     * @param T_N现金变化 T+N现金变化
     * @param T_N负债变化 T+N负债变化
     */
    private MockCalcAssetValuationResult do实时组合资产估值(List<MockAssetInfoDto> mockAssetInfoDtoList,
                                                    MockPtlPortfolioDto mockPtlPortfolioDto,
                                                    List<MockPtlSecValuationDto> mockPtlSecValuationDtoList,
                                                    List<MockPtlPortfolioValuationDto> mockPtlPortfolioValuationDtoList,
                                                    List<MockPtlFeePositionDto> mockPtlFeePositionDtoList,
                                                    List<MockTrdProductDealDto> assetTradeList,
                                                    List<MockTrdProductDealDto> liabilitiesTradeList,
                                                    List<MockTrdProductDealDto> feeTradeList,
                                                    List<MockTrdProductDealDto> assetTdayPlusNdayTradeList,
                                                    List<MockTrdProductDealDto> liabilitiesTdayPlusNdayTradeList,
                                                    Map<String, BigDecimal> mockPtlCashPositionMap,
                                                    String mockStartDate, String mockEndDate,
                                                    String baseDate,
                                                    List<MockMstExchargeQuoteDto> mstExchargeQuoteList,
                                                    BigDecimal financingCost,
                                                    BigDecimal reinvestmentRate,
                                                    BigDecimal compositeRate,
                                                    Map<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> T_N资产变化,
                                                    Map<MockCashChangingKey, BigDecimal> T_N现金变化,
                                                    Map<String, LiabilitiesChanging> T_N负债变化) {
        // 调用组合实时估值服务.组合资产估值-主方法
        MockCalcAssetValuationParameter paramObj = new MockCalcAssetValuationParameter();
        paramObj.setMockAssetInfoDtoList(mockAssetInfoDtoList);
        paramObj.setMockPtlPortfolioDto(mockPtlPortfolioDto);
        paramObj.setMockPtlSecValuationDtoList(mockPtlSecValuationDtoList);
        paramObj.setMockPtlPortfolioValuationDtoList(mockPtlPortfolioValuationDtoList);
        paramObj.setMockPtlFeePositionDtoList(mockPtlFeePositionDtoList);
        paramObj.setAssetTradeList(assetTradeList);
        paramObj.setLiabilitiesTradeList(liabilitiesTradeList);
        paramObj.setFeeTradeList(feeTradeList);
        paramObj.setAssetTdayPlusNdayTradeList(assetTdayPlusNdayTradeList);
        paramObj.setLiabilitiesTdayPlusNdayTradeList(liabilitiesTdayPlusNdayTradeList);
        paramObj.setMockPtlCashPositionMap(mockPtlCashPositionMap);
        paramObj.setMockStartDate(mockStartDate);
        paramObj.setMockEndDate(mockEndDate);
        paramObj.setBaseDate(baseDate);
        paramObj.setMstExchargeQuoteList(mstExchargeQuoteList);
        paramObj.setFinancingCost(financingCost);
        paramObj.setReinvestmentRate(reinvestmentRate);
        paramObj.setCompositeRate(compositeRate);
        paramObj.setSingleAssetChanging(T_N资产变化);
        paramObj.setCashChanging(T_N现金变化);
        paramObj.setLiabilitiesChanging(T_N负债变化);

        return restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlRealTimeValuation/valuationMain",
            paramObj, MockCalcAssetValuationResult.class);
    }

    /**
     * 画面参数百分比处理
     * 
     * @param inputDto 画面参数
     */
    private void do画面参数百分比处理(PtlMockTest002Dto inputDto) {
        BigDecimal 一百 = BigDecimal.valueOf(100);
        // 业绩基准% / 100
        inputDto.setBaseRule(ArithmeticUtil.divide(inputDto.getBaseRule(), 一百, 6));
        // 管理费率% / 100
        inputDto.setMgrFeeRate(ArithmeticUtil.divide(inputDto.getMgrFeeRate(), 一百, 6));
        // 画面.募集金额（万元） * 10000
        inputDto.setRaiseAmtPlan(
            ArithmeticUtil.multiply(inputDto.getRaiseAmtPlan(), BigDecimal.valueOf(10000)));
        // 再投资收益率% / 100
        inputDto.setAgainInvestRate(ArithmeticUtil.divide(inputDto.getAgainInvestRate(), 一百, 4));
        // 融资成本% / 100
        inputDto.setFinanCost(ArithmeticUtil.divide(inputDto.getFinanCost(), 一百, 4));
        // 模拟交易列表
        for (PtlMockTestTradeDto mockTestTrade : inputDto.getPageMockDealList()) {
            // 到期收益率% / 100
            mockTestTrade.setYield(ArithmeticUtil.divide(mockTestTrade.getYield(), 一百, 14));
            // 行权收益率% / 100
            mockTestTrade.setExerYield(ArithmeticUtil.divide(mockTestTrade.getExerYield(), 一百, 14));
        }
    }

    /**
     * 计算组合表现
     * 
     * @param ptlBaseInfoDto 组合信息
     * @param 组合资产估值列表 组合资产估值列表
     * @param 全量组合估值列表 全量组合估值列表
     * @param 组合现金 组合现金
     * @param 资产基本信息列表 资产基本信息列表
     * @param resultDto 计算结果
     * @param calcFlg 计算区分
     * 1：计算前一日组合表现 2：计算实时组合表现（考虑当日成交）
     * 3：计算实时组合表现（试算后）（区间相关指标除外） 4:计算模拟测算净值曲线数据
     * @throws Exception 异常
     */
    private void do计算组合表现(MockPtlPortfolioDto ptlBaseInfoDto,
                             List<MockPtlSecValuationDto> 组合资产估值列表,
                             List<MockPtlPortfolioValuationDto> 全量组合估值列表,
                             MockPtlCashPositionDto 组合现金,
                             List<MockAssetInfoDto> 资产基本信息列表,
                             List<MockMstExchargeQuoteDto> 汇率行情数据, String 计算日期,
                             PtlMockTest002Dto resultDto, int calcFlg)
        throws Exception {
        Map<IndicatorName, String> 计算指标信息Map = new HashMap<>();
        // 1：计算前一日组合表现
        if (calcFlg == 1) {
            计算指标信息Map.put(IndicatorName.单位净值, "preDayUnitValue");
            计算指标信息Map.put(IndicatorName.万份收益, "preDayPYield");
            计算指标信息Map.put(IndicatorName.剩余现金, "preDayCashPosition");
            计算指标信息Map.put(IndicatorName.涨跌幅, "preDayValChg");
            计算指标信息Map.put(IndicatorName.七日年化收益率, "preDaySevenDayRate");
            计算指标信息Map.put(IndicatorName.总资产, "preDayAssetValue");
            计算指标信息Map.put(IndicatorName.净资产, "preDayAssetNetvalue");
            计算指标信息Map.put(IndicatorName.杠杆率, "preDayLeverRate");
            计算指标信息Map.put(IndicatorName.基点价值, "preDayBpvalue");
            计算指标信息Map.put(IndicatorName.平均剩余期限, "preDayAvgsurplustime");
            计算指标信息Map.put(IndicatorName.平均剩余存续期限, "preDayAvgsurplustimeP");
            计算指标信息Map.put(IndicatorName.组合久期, "preDayPtlMDuration");
        }
        // 2：计算实时组合表现（考虑当日成交）
        else if (calcFlg == 2) {
            计算指标信息Map.put(IndicatorName.单位净值, "realTimeUnitValue");
            计算指标信息Map.put(IndicatorName.万份收益, "realTimePYield");
            计算指标信息Map.put(IndicatorName.剩余现金, "realTimeCashPosition");
            计算指标信息Map.put(IndicatorName.涨跌幅, "realTimeValChg");
            计算指标信息Map.put(IndicatorName.七日年化收益率, "realTimeSevenDayRate");
            计算指标信息Map.put(IndicatorName.总资产, "realTimeAssetValue");
            计算指标信息Map.put(IndicatorName.净资产, "realTimeAssetNetvalue");
            计算指标信息Map.put(IndicatorName.杠杆率, "realTimeLeverRate");
            计算指标信息Map.put(IndicatorName.基点价值, "realTimeBpvalue");
            计算指标信息Map.put(IndicatorName.平均剩余期限, "realTimeAvgsurplustime");
            计算指标信息Map.put(IndicatorName.平均剩余存续期限, "realTimeAvgsurplustimeP");
            计算指标信息Map.put(IndicatorName.组合久期, "realTimePtlMDuration");
        }
        // 3：计算实时组合表现（试算后）（区间相关指标除外）
        else if (calcFlg == 3) {
            计算指标信息Map.put(IndicatorName.单位净值, "mockUnitValue");
            计算指标信息Map.put(IndicatorName.万份收益, "mockPYield");
            计算指标信息Map.put(IndicatorName.剩余现金, "mockCashPosition");
            计算指标信息Map.put(IndicatorName.涨跌幅, "mockValChg");
            计算指标信息Map.put(IndicatorName.七日年化收益率, "mockSevenDayRate");
            计算指标信息Map.put(IndicatorName.总资产, "mockAssetValue");
            计算指标信息Map.put(IndicatorName.净资产, "mockAssetNetvalue");
            计算指标信息Map.put(IndicatorName.杠杆率, "mockLeverRate");
            计算指标信息Map.put(IndicatorName.基点价值, "mockBpvalue");
            计算指标信息Map.put(IndicatorName.平均剩余期限, "mockAvgsurplustime");
            计算指标信息Map.put(IndicatorName.平均剩余存续期限, "mockAvgsurplustimeP");
            计算指标信息Map.put(IndicatorName.组合久期, "mockPtlMDuration");
            计算指标信息Map.put(IndicatorName.一月年化收益率, "mockOneMonthRate");
            计算指标信息Map.put(IndicatorName.三月年化收益率, "mockThreeMonthRate");
            计算指标信息Map.put(IndicatorName.一年收益率, "mockOneYearRate");
            计算指标信息Map.put(IndicatorName.至下一季报日收益率, "mockQuarterDayRate");
            计算指标信息Map.put(IndicatorName.到期收益率, "mockYield");
        }
        // 4:计算模拟测算净值曲线数据
        else if (calcFlg == 4) {
            计算指标信息Map.put(IndicatorName.单位净值, "mockUnitValue");
            计算指标信息Map.put(IndicatorName.万份收益, "mockPYield");
            计算指标信息Map.put(IndicatorName.涨跌幅, "mockValChg");
            计算指标信息Map.put(IndicatorName.七日年化收益率, "mockSevenDayRate");
        }

        List<IndicatorName> 指标列表 = new ArrayList<>();
        指标列表.addAll(计算指标信息Map.keySet());

        // 调用组合实时估值服务-计算指标方法
        Map<IndicatorName, Object> calcResult = RealTimeValIndicatorCalcUtil.calcIndicator(
            ptlBaseInfoDto, 组合资产估值列表, 全量组合估值列表, 组合现金, 资产基本信息列表, 汇率行情数据, 指标列表,
            DateUtil.parse(计算日期));

        List<String> 百分比项目列表 = new ArrayList<>();
        百分比项目列表.add("preDayValChg");
        百分比项目列表.add("preDaySevenDayRate");
        百分比项目列表.add("preDayLeverRate");
        百分比项目列表.add("realTimeValChg");
        百分比项目列表.add("realTimeSevenDayRate");
        百分比项目列表.add("realTimeLeverRate");
        百分比项目列表.add("mockValChg");
        百分比项目列表.add("mockLeverRate");
        百分比项目列表.add("mockSevenDayRate");
        百分比项目列表.add("mockOneMonthRate");
        百分比项目列表.add("mockThreeMonthRate");
        百分比项目列表.add("mockQuarterDayRate");
        百分比项目列表.add("mockOneYearRate");
        百分比项目列表.add("mockYield");
        百分比项目列表.add("mockRangeRate");

        // 将计算结果设置到resultDto的对应属性上
        BigDecimal 一百 = BigDecimal.valueOf(100);
        for (IndicatorName indicatorName : calcResult.keySet()) {
            if (计算指标信息Map.containsKey(indicatorName) && calcResult.get(indicatorName) != null) {
                String itemName = 计算指标信息Map.get(indicatorName);
                if (百分比项目列表.contains(itemName)) {
                    BigDecimal value = (BigDecimal)calcResult.get(indicatorName);
                    BeanUtil.setProperty(resultDto, 计算指标信息Map.get(indicatorName),
                        ArithmeticUtil.multiply(value, 一百));
                }
                else {
                    BeanUtil.setProperty(resultDto, 计算指标信息Map.get(indicatorName),
                        calcResult.get(indicatorName));
                }
            }
        }

        //货币型产品前一日组合估值的七日年化收益率和万份收益取自估值表
        if((calcFlg == 1 || calcFlg == 4) && StringUtil.equals(DictCodeList.收益类型_货币型.getValue(), ptlBaseInfoDto.getProfitType())){
            for(MockPtlPortfolioValuationDto dto :全量组合估值列表){
                if(StringUtil.equals(DateUtil.format(dto.getCdate()),计算日期) ){
                    if(dto.getDayPYield() != null){
                        resultDto.setPreDayPYield(dto.getDayPYield());
                    }
                    if (dto.getYield7() != null){
                        resultDto.setPreDaySevenDayRate(dto.getYield7());
                    }
                    if (calcFlg == 4 && dto.getDayPYield() != null){
                        resultDto.setMockPYield(dto.getDayPYield());
                    }
                    if (calcFlg == 4 && dto.getDayPYield() != null){
                        resultDto.setMockSevenDayRate(dto.getYield7());
                    }
                }    
            }
        }
    }
    
    /**
     * 计算区间收益率
     * 
     * @param ptlBaseInfoDto 组合信息
     * @param 全量组合估值列表 全量组合估值列表
     * @param 区间开始日 区间开始日
     * @param 区间结束日 区间结束日
     * @param resultDto 计算结果
     * @throws Exception 异常
     */
    private void do计算区间收益率(MockPtlPortfolioDto ptlBaseInfoDto,
                           List<MockPtlPortfolioValuationDto> 全量组合估值列表, Date 区间开始日, Date 区间结束日,
                           PtlMockTest002Dto resultDto) {
        if (区间开始日 == null || 区间结束日 == null) {
            return;
        }

        List<IndicatorName> 指标列表 = new ArrayList<>();
        指标列表.add(IndicatorName.区间收益率);

        if(DateUtil.compareDate(区间开始日,ptlBaseInfoDto.getVdate())<0){
            区间开始日 = ptlBaseInfoDto.getVdate();
        }
        if(DateUtil.compareDate(区间结束日,ptlBaseInfoDto.getMdate())>0){
            区间结束日 = ptlBaseInfoDto.getMdate();
        }
//        // 调用组合实时估值服务-计算指标方法
//        if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(), ptlBaseInfoDto.getProfitType())){
//            区间开始日 = DateUtil.addDay(区间开始日,-1);
//        }

            Map<IndicatorName, Object> calcResult = RealTimeValIndicatorCalcUtil.calcPeriodIndicator(
            ptlBaseInfoDto, null, 全量组合估值列表, null, null, null, 指标列表,区间开始日, 区间结束日);

        if (calcResult.containsKey(IndicatorName.区间收益率)) {
            resultDto.setMockRangeRate(ArithmeticUtil.multiply(
                (BigDecimal)calcResult.get(IndicatorName.区间收益率), BigDecimal.valueOf(100)));
        }
    }

    /**
     * 查询到的组合现金数据需要根据组合募集币种以及汇率行情数据做换算
     * 
     * @param orgDataList 查询到的组合现金数据
     * @param exchargeQuoteList 汇率行情数据
     * @param ptlCcy 组合募集币种
     * @return 汇率换算后的数据
     */
    private MockPtlCashPositionDto do汇率换算(List<MockPtlCashPositionDto> orgDataList,
                                          List<MockMstExchargeQuoteDto> exchargeQuoteList,
                                          String ptlCcy) {
        MockPtlCashPositionDto resultDto = new MockPtlCashPositionDto();
        resultDto.setCcy(StringUtil.isEmpty(ptlCcy)?DictCodeList.币种_人民币_CNY.getValue():ptlCcy);
        if (StringUtil.isEmptyList(orgDataList) ) {
            resultDto.setAmount(BigDecimal.ZERO);
            return resultDto;
        }

        BigDecimal totalAmout = BigDecimal.ZERO;
        for (MockPtlCashPositionDto cashPositionDto : orgDataList) {
            if (StringUtil.equals(ptlCcy, cashPositionDto.getCcy())) {
                totalAmout = ArithmeticUtil.doAdd(totalAmout, cashPositionDto.getAmount());
            }
            else {
                BigDecimal 汇率 = RealTimeValCommonUtil.汇率获取(cashPositionDto.getCcy(), ptlCcy,
                    exchargeQuoteList);
                totalAmout = ArithmeticUtil.doAdd(totalAmout,
                    ArithmeticUtil.multiply(cashPositionDto.getAmount(), 汇率));
            }
        }

        resultDto.setAmount(totalAmout);
        return resultDto;
    }

    
    
    /**
     * 计算模拟交易列表净价、到期收益率、行权收益率
     * @param inputTradeDto 模拟交易
     * @param calType 计算类型
     * @return 计算结果
     */
    public PtlMockTestTradeDto calcYield(PtlMockTestTradeDto inputTradeDto,String calType) {
        if (inputTradeDto == null || StringUtil.isBlank(inputTradeDto.getFinprodId())) {
            return null;
        }

        // 查询资产基本信息
        MockAssetInfoDto assetInfoDto = null;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("finprodIdList", Arrays.asList(inputTradeDto.getFinprodId()));
        paramMap.put("paramDate", iPlatformDate.getSysDateStr());
        paramMap.put("isForMock",DictCodeList.是否_是.getValue());
        List<MockAssetInfoDto> tempAssetList = restTemplate.postForList(
            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
            MockAssetInfoDto.class);
        if (!StringUtil.isEmptyList(tempAssetList)) {
            assetInfoDto = tempAssetList.get(0);
        }
        if (assetInfoDto == null) {
            return null;
        }

        PtlMockTestTradeDto resultDto = new PtlMockTestTradeDto();
        if(StringUtil.equals("01",calType) && inputTradeDto.getTradePrice() != null){
            if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), inputTradeDto.getType8())) {
                resultDto = this.calcBondYield(null, inputTradeDto.getFinprodId(),
                        inputTradeDto.getPs(), inputTradeDto.getTradePrice(),
                        inputTradeDto.getAccuir(), null,null,null, iPlatformDate.getSysDate(),calType);
            }
            else {
                // 取最新生效利率
                if (!StringUtil.isEmptyList(assetInfoDto.getFinCashCalcRuleList())) {
                    resultDto.setYield(assetInfoDto.getFinCashCalcRuleList().get(0).getYield());

                    // 计算应计利息
                    resultDto.setAccuir(this.calcProjectUnitInt(assetInfoDto, iPlatformDate.getSysDate()));
                }
            }

            // 乘以100
            resultDto.setYield(ArithmeticUtil.multiply(resultDto.getYield(), BigDecimal.valueOf(100)));
            resultDto.setExerYield(ArithmeticUtil.multiply(resultDto.getExerYield(), BigDecimal.valueOf(100)));
        };


        if(StringUtil.equals("02",calType) && inputTradeDto.getYield() != null){
            if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), inputTradeDto.getType8())) {
                resultDto = this.calcBondYield(null, inputTradeDto.getFinprodId(),
                        inputTradeDto.getPs(), null,
                        inputTradeDto.getAccuir(), null,inputTradeDto.getYield(),null, iPlatformDate.getSysDate(),calType);
            }
            else {
                resultDto.setTradePrice(new BigDecimal(100));
                // 计算应计利息
                resultDto.setAccuir(this.calcProjectUnitInt(assetInfoDto, iPlatformDate.getSysDate()));
            }

            resultDto.setExerYield(ArithmeticUtil.multiply(resultDto.getExerYield(), BigDecimal.valueOf(100)));
        }

        if(StringUtil.equals("03",calType) && inputTradeDto.getExerYield() != null){
            if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), inputTradeDto.getType8())) {
                resultDto = this.calcBondYield(null, inputTradeDto.getFinprodId(),
                        inputTradeDto.getPs(), null,
                        inputTradeDto.getAccuir(), null,null,inputTradeDto.getExerYield(), iPlatformDate.getSysDate(),calType);
            }
            resultDto.setYield(ArithmeticUtil.multiply(resultDto.getYield(), BigDecimal.valueOf(100)));
        }

        return resultDto;
    }

    /**
     * 计算非标百元应计利息
     * @param assetInfoDto 资产信息
     * @return 百元应计利息
     */
    private BigDecimal calcProjectUnitInt(MockAssetInfoDto assetInfoDto, Date calcDate) {
        BigDecimal result = null;
        try {
            // 最新的付息计划
            List<MockFinCashPlanDto> listIntinfo = assetInfoDto.getMockFinCashPlanDtoList().stream().filter(
                x -> (StringUtil.equals(DictCodeList.现金流类型_到期收息.getValue(),
                    x.getFinCashPlan().getCashType())
                      || StringUtil.equals(
                          DictCodeList.现金流类型_中间收息.getValue(),
                          x.getFinCashPlan().getCashType()))
                     && DateUtil.compareDate(x.getFinCashPlan().getVdate(), calcDate) <= 0
                     && DateUtil.compareDate(x.getFinCashPlan().getMdate(), calcDate) > 0).collect(
                         Collectors.toList());
            
            FinCashPlan finCashPlan = new FinCashPlan();
            if (!StringUtil.isEmptyList(listIntinfo)) {
                finCashPlan = listIntinfo.get(0).getFinCashPlan();
            }
            
            // 现金流计算规则
            List<FinCashCalcRule> finCashCalcRuleList = assetInfoDto.getFinCashCalcRuleList();
    
            FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
            finCashPlanDto.setVdate(finCashPlan.getVdate());
            finCashPlanDto.setMdate(calcDate);
            
            FinCashPlanDetailDto finCashPlanDetailDto = new FinCashPlanDetailDto();
            finCashPlanDetailDto.setVdate(finCashPlan.getVdate());
            finCashPlanDetailDto.setMdate(calcDate);
            finCashPlanDetailDto.setYield(finCashCalcRuleList.get(0).getYield());
            finCashPlanDetailDto.setIntPrinAmt(BigDecimal.valueOf(100));
            
            FinCashPayRule isr = assetInfoDto.getFinCashPayRuleList().get(0);
    
            AssetCommonInfoDto scai = new AssetCommonInfoDto();
            scai.setBasis(finCashCalcRuleList.get(0).getBasis());
            scai.setIntpayRule(DictCodeListExt1.利息分配方式_按照实际天数分配.getValue());
            scai.setVdate(assetInfoDto.getFinProduct().getVdate());
            scai.setMdate(assetInfoDto.getFinProduct().getMdate());

            result = InterestSubAmtCal.cal(finCashPlanDto, finCashPlanDetailDto, isr, scai);
        } catch (ParseException e) {
            logger.error("计算利息失败", e);
        }
        
        return result;
    }
    
    /**
     * 重新计算债券到期收益率（组合久期计算用到债券到期收益率）
     * 
     * @param ptlSecValuationDtoList 组合资产估值数据，计算对象
     * @param assetInfoDtoList 资产信息列表
     * @param tradeList 当日发生交易列表
     * @param calcDate 计算日
     * @param flag 是否需要重算
     */
    private void reCalcBondYield(List<MockPtlSecValuationDto> ptlSecValuationDtoList,
                                 List<MockAssetInfoDto> assetInfoDtoList,
                                 List<MockTrdProductDealDto> tradeList, Date calcDate,boolean flag) {

        if (StringUtil.isEmptyList(ptlSecValuationDtoList) && StringUtil.isEmptyList(tradeList)) {
            return;
        }

        List<MockPtlSecValuationDto> targetList = ptlSecValuationDtoList.stream().filter(
            x -> DateUtil.isSameDate(calcDate, x.getCdate())).collect(Collectors.toList());

        if (StringUtil.isEmptyList(targetList)) {
            return;
        }

        if(tradeList==null){
            tradeList = new ArrayList<>();
        }

        Map<String, String> tradeFinprodIdMap = tradeList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getFinprodId()));
        Map<String, MockAssetInfoDto> assetInfoMap = assetInfoDtoList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x));

        for (MockPtlSecValuationDto mockPtlSecValuationDto : targetList) {
            if (!assetInfoMap.containsKey(mockPtlSecValuationDto.getFinprodId())) {
                continue;
            }
            if(!StringUtil.equals(mockPtlSecValuationDto.getTgAssetType(), DictCodeList.平安资产类型_债券.getValue())){
                continue;
            }
            // 到期日重算会报错
            if(DateUtil.compareDate(assetInfoMap.get(mockPtlSecValuationDto.getFinprodId()).getFinProduct().getMdate(),calcDate)<=0){
                continue;
            }
            if(!flag&&StringUtil.isNotEmpty(mockPtlSecValuationDto.getYield())){
                    continue;
            }

            BigDecimal unitFprice = ArithmeticUtil.divide(
                ArithmeticUtil.doAdd(mockPtlSecValuationDto.getDscCostAmt(),
                    mockPtlSecValuationDto.getTdyIntincexp()),
                mockPtlSecValuationDto.getShareAmt(), 12);
            PtlMockTestTradeDto tempCalcResult = this.calcBondYield(
                assetInfoMap.get(mockPtlSecValuationDto.getFinprodId()),
                mockPtlSecValuationDto.getFinprodId(), null, null, null, unitFprice,null,null, calcDate,"04");
            if (tempCalcResult != null && tempCalcResult.getYield() != null) {
                mockPtlSecValuationDto.setYield(tempCalcResult.getYield());
            }
        }
    }
    
    /**
     * 计算债券到期收益率、行权收益率
     * @param assetInfoDto 债券基本信息
     * @param finprodId 金融产品代码
     * @param ps 交易方向
     * @param tradePrice 交易净价
     * @param unitInt 单位应计利息
     * @param unitFprice 全价
     * @param calType 计算类型
     *                01 净价
     *                02 到期收益率
     *                03 行权收益率
     *                04 全价
     * @return 计算结果
     */
    public PtlMockTestTradeDto calcBondYield(MockAssetInfoDto assetInfoDto, String finprodId, String ps,
                                       BigDecimal tradePrice, BigDecimal unitInt, BigDecimal unitFprice,
                                             BigDecimal yield,BigDecimal exerYield,Date calcDate,String calType) {
//        BigDecimal yield = null; // 到期收益率
//        BigDecimal exerYield = null; // 行权收益率


        yield = ArithmeticUtil.divide(yield, 100, 14);
        exerYield = ArithmeticUtil.divide(exerYield, 100, 14);

        if (assetInfoDto == null) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("finprodIdList", Arrays.asList(finprodId));
            paramMap.put("paramDate", DateUtil.format(calcDate));
            paramMap.put("isForMock",DictCodeList.是否_是.getValue());
            List<MockAssetInfoDto> tempAssetList = restTemplate.postForList(
                ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
                MockAssetInfoDto.class);
            if (!StringUtil.isEmptyList(tempAssetList)) {
                assetInfoDto = tempAssetList.get(0);
            }
        }

        Date settleDate = calcDate;
        Date vdate = null;
        String tradeMarket = null;
        String tradeMarketMode = DictCodeListExt1.交易市场_二级市场.getValue();
//        BigDecimal 应计利息总额 = null;

        if (assetInfoDto != null && assetInfoDto.getFinProduct() != null) {
            vdate = assetInfoDto.getFinProduct().getVdate();
            tradeMarket = assetInfoDto.getFinProduct().getTradeMarket();
        }
        if (vdate != null && DateUtil.compareDate(settleDate, vdate) < 0) {
            settleDate = vdate;
        }

        // 如果交易场所为上交所、深交所以外的情况
        boolean isJiaoYiSuo = true;
        if (!StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), tradeMarket)
            && !StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), tradeMarket)) {
            // // 应计利息根据后台估值核算中前一个自然日的数据计算，等于后台的应计利息总额/后台的债券持仓总数量
            // this.getUnitIntFromValution(dto);
            isJiaoYiSuo = false;
        }

        final int SCALE = 12;

        YieldByPriceCalculator yieldByPriceCalculator = new YieldByPriceCalculator();
         PriceByYieldCalculator priceByYieldCalculator = new PriceByYieldCalculator();
        YieldPriceInParameter yieldPriceInParameter = null;
        PriceYieldInParameter priceYieldInParameter = null;
        YieldPriceOutParameter yieldPriceOutParameter = null;
         PriceYieldOutParameter priceYieldOutParameter = null;

        // b.获取计息区间投资端利率报价信息（债券的利率重置信息）
        List<FinCashCalcRule> finCashCalcRuleList = assetInfoDto.getFinCashCalcRuleList();

        // c.获取债券的付息计划信息
        List<MockFinCashPlanDto> finCashPlanList = assetInfoDto.getMockFinCashPlanDtoList();

        List<SecIntSchedule> invScheduleList = new ArrayList<>();
        List<FinCashPlanDto> intSchList = new ArrayList<FinCashPlanDto>();
        for (MockFinCashPlanDto mockFinCashPlanDto : finCashPlanList) {
            FinCashPlan finCashPlan = mockFinCashPlanDto.getFinCashPlan();
            if (StringUtil.equals(mockFinCashPlanDto.getFinCashInfo().getCashType1(),
                DictCodeListExt1.现金流一级类型_利息.getValue())
                && finCashPlan.getVdateUnadjust() != null) {
                SecIntSchedule schedule = new SecIntSchedule();
                schedule.setTheoryStartDate(toLocalDate(finCashPlan.getVdateUnadjust()));
                schedule.setTheoryCutoffDate(toLocalDate(finCashPlan.getMdateUnadjust()));
                schedule.setActualStartDate(toLocalDate(finCashPlan.getVdate()));
                schedule.setActualCutoffDate(toLocalDate(finCashPlan.getMdate()));
                schedule.setAmount(finCashPlan.getIntAmt());
                invScheduleList.add(schedule);

                FinCashPlanDto finCashPlanDto = new FinCashPlanDto();
                BeanUtil.copyPropertiesIgnoreNull(finCashPlanDto, finCashPlan);
                intSchList.add(finCashPlanDto);
            }
        }

        // d.获取债券基本资料信息
        // 计算行权收益率，非行权时，取大于当日的行权日
        SecInfoModelDto secInfoModelDto = toSecInfoModelDto(assetInfoDto,calcDate);
        SecInfoModel secInfoModel = toSecInfoModel(secInfoModelDto);
        // 如果交易场所为上交所、深交所的情况
        if (isJiaoYiSuo) {
            // 付息债的计息基础使用A_365F
            if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(),
                secInfoModelDto.getBondsType())) {
                secInfoModel.setBasis(Basis.A_365F);
            }
        }

        // e.获取债券的还本计划信息
        List<SecPrinSchedule> secPrinScheduleList = new ArrayList<SecPrinSchedule>();
        for (MockFinCashPlanDto mockFinCashPlanDto : finCashPlanList) {
            FinCashPlan item = mockFinCashPlanDto.getFinCashPlan();
            if (StringUtil.equals(mockFinCashPlanDto.getFinCashInfo().getCashType1(),
                DictCodeListExt1.现金流一级类型_本金.getValue())) {
                SecPrinSchedule schedule = new SecPrinSchedule();
                schedule.setAmount(item.getIntAmt());
                schedule.setFlag(
                    StringUtil.equals(item.getIsPayInt(), DictCodeList.是否_是.getValue()));
                schedule.setPrincipal(item.getPrinAmt());
                schedule.setInterestEndDate(toLocalDate(item.getMdate()));
                secPrinScheduleList.add(schedule);
            }
        }

        List<PaperIrReset> paperIrResetList = finCashCalcRuleList.stream().map(item -> {
            PaperIrReset ret = new PaperIrReset();
            // BeanUtil.copyPropertiesIgnoreNull(ret, item);
            ret.setBaseRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            ret.setParRate(item.getYield());
            ret.setParRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            return ret;
        }).collect(Collectors.toList());
        if (paperIrResetList != null && paperIrResetList.size() > 0) {
            paperIrResetList.get(0).setBaseRateEffDate(
                BasicUtil.dateToLocal(secInfoModelDto.getStartDate()));
        }
        if (paperIrResetList != null && paperIrResetList.size() > 1) {
            // 多重利率则计算按浮动利率算
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }
        yieldPriceInParameter = new YieldPriceInParameter();
        yieldPriceInParameter.setCalDate(toLocalDate(settleDate));
        yieldPriceInParameter.setSecInfoModel(secInfoModel);
        yieldPriceInParameter.setSecIntScheduleList(invScheduleList);
        yieldPriceInParameter.setSecPrinScheduleList(secPrinScheduleList);
        yieldPriceInParameter.setPaperIrResetList(paperIrResetList);
        priceYieldInParameter = new PriceYieldInParameter();
        priceYieldInParameter.setCalDate(toLocalDate(settleDate));
        priceYieldInParameter.setSecInfoModel(secInfoModel);
        priceYieldInParameter.setSecIntScheduleList(invScheduleList);
        priceYieldInParameter.setSecPrinScheduleList(secPrinScheduleList);
        priceYieldInParameter.setPaperIrResetList(paperIrResetList);
        yieldPriceInParameter.setCashScheme(FlowCashScheme.付息计划推算计息年度);
        priceYieldInParameter.setCashScheme(FlowCashScheme.付息计划推算计息年度);
        yieldPriceInParameter.setDisScheme(DiscountScheme.以剩余年限为单位);
        priceYieldInParameter.setDisScheme(DiscountScheme.以剩余年限为单位);
        if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), secInfoModelDto.getBondsType())) {
            if (isNotFomartSec(intSchList, secInfoModelDto)) {
                yieldPriceInParameter.setCashScheme(FlowCashScheme.理论付息区间);
                priceYieldInParameter.setCashScheme(FlowCashScheme.理论付息区间);
                yieldPriceInParameter.setDisScheme(DiscountScheme.以付息区间为单位);
                priceYieldInParameter.setDisScheme(DiscountScheme.以付息区间为单位);
            }
            else {
                yieldPriceInParameter.setCashScheme(FlowCashScheme.付息计划推算计息年度);
                priceYieldInParameter.setCashScheme(FlowCashScheme.付息计划推算计息年度);
                yieldPriceInParameter.setDisScheme(DiscountScheme.以剩余年限为单位);
                priceYieldInParameter.setDisScheme(DiscountScheme.以剩余年限为单位);
            }
        }
        yieldPriceInParameter.setCalMethod(secInfoModel.getCalMethod());
        priceYieldInParameter.setCalMethod(secInfoModel.getCalMethod());
        // 后续区间利息计算方案
        if (IntRateType.浮动利率.equals(secInfoModel.getIntRateType())) {
            priceYieldInParameter.setNextIntMethod(NextIntCalMethod.按实际利率重置分段计息计算);
            yieldPriceInParameter.setNextIntMethod(NextIntCalMethod.按实际利率重置分段计息计算);

            yieldPriceInParameter.setCurrIntMethod(CurrIntCalMethod.按实际利率重置分段计息计算);
            priceYieldInParameter.setCurrIntMethod(CurrIntCalMethod.按实际利率重置分段计息计算);
        }
        else {
            priceYieldInParameter.setNextIntMethod(NextIntCalMethod.使用计算日最新最新票面计算);
            yieldPriceInParameter.setNextIntMethod(NextIntCalMethod.使用计算日最新最新票面计算);

            yieldPriceInParameter.setCurrIntMethod(CurrIntCalMethod.使用区间起始日前最新票面利率计算);
            priceYieldInParameter.setCurrIntMethod(CurrIntCalMethod.使用区间起始日前最新票面利率计算);
        }

        // 百元面值应计利息
        BigDecimal accurInt = BigDecimal.ZERO;
        // 交易方式为“一级市场”时，单位应计利息=应计利息总额=0
        if (StringUtil.equals(tradeMarketMode, DictCodeListExt1.交易市场_一级市场.getValue())) {
            unitInt = accurInt;
//            应计利息总额 = BigDecimal.ZERO;
        }
        // 交易方式为“二级市场”时，单位应计利息 =e取得的百元面值应计利息/100*a取得的当前债券面额
        else if (StringUtil.equals(tradeMarketMode, DictCodeListExt1.交易市场_二级市场.getValue())) {
            // a.当前债券面额取得（根据还本计划计算）
            // BigDecimal curSecAmt = transactionBond001Mapper.getCurSecAmt(dto);
            // accurInt = ArithmeticUtil.round(BondsCalculation.calAccurInt(dto.getSettDate(),
            // bondsInfo,
            // bondsScheduleList, bondsIntResetList), SCALE, RoundingMode.HALF_UP);
            // 单位应计利息
            // if (dto.getUnitInt() == null) {
            // dto.setUnitInt(ArithmeticUtil.doMul(
            // ArithmeticUtil.doDiv(accurInt, new BigDecimal(100), SCALE), curSecAmt));
            // }
            // // 应计利息总额 = 单位应计利息 * 券面总额/100
            // dto.setIntAmt(ArithmeticUtil.doDiv(ArithmeticUtil.doMul(accurInt, dto.getPrinAmt()),
            // new BigDecimal(100), SCALE));

            // 交易所债券特殊处理start----------------------------------------------------------------
            Date tempSettleDate = settleDate;
            // 如果交易场所为上交所、深交所的情况
            if (isJiaoYiSuo) {
                // 付息债、贴现债、零息债，交割日+1
                // if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(),
                // secInfoModelDto.getBondsType())
                // || StringUtil.equals(DictCodeList.息票品种_贴现.getValue(),
                // secInfoModelDto.getBondsType())
                // || StringUtil.equals(DictCodeList.息票品种_零息.getValue(),
                // secInfoModelDto.getBondsType())) {
                // add by 张年 20190909 start
                // 行权-回售 行权-赎回 交割日不加1
                if (!StringUtil.equals(ps, DictCodeList.交易方向_行权_回售.getValue())
                    && !StringUtil.equals(ps, DictCodeList.交易方向_行权_赎回.getValue())) {
                    tempSettleDate = DateUtil.addDay(tempSettleDate, 1);
                }
                // add by 张年 20190909 end
                // }
            }
            // 交易所债券特殊处理end----------------------------------------------------------------

            // a.当前债券面额取得（根据还本计划计算）
            BigDecimal curSecAmt = this.getCurSecAmt(assetInfoDto, tempSettleDate);

            AccurIntCalInParameter accurIntCalInParameter = new AccurIntCalInParameter();
            // 计算日
            // accurIntCalInParameter.setCalDate(this.toLocalDate(dto.getSettleDate()));
            accurIntCalInParameter.setCalDate(toLocalDate(tempSettleDate));
            if (paperIrResetList != null && paperIrResetList.size() > 1) {
                // 多重利率则计算按浮动利率算
                secInfoModel.setIntRateType(IntRateType.浮动利率);
            }
            // 债券基本资料
            accurIntCalInParameter.setSecInfoModel(secInfoModel);
            // 付息计划
            accurIntCalInParameter.setSecIntScheduleList(invScheduleList);
            // 利率重置信息
            accurIntCalInParameter.setPaperIrResetList(paperIrResetList);

            // 交易所债券特殊处理start----------------------------------------------------------------
            AccurIntCalOutParameter out = null;
            // 如果交易场所为上交所、深交所的情况
            if (isJiaoYiSuo) {
                // 付息债、贴现债、零息债，交割日+1
                // if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(),
                // secInfoModelDto.getBondsType())
                // || StringUtil.equals(DictCodeList.息票品种_贴现.getValue(),
                // secInfoModelDto.getBondsType())
                // || StringUtil.equals(DictCodeList.息票品种_零息.getValue(),
                // secInfoModelDto.getBondsType())) {
                // 付息计划截止日对应的利息
                Map<String, BigDecimal> intMap = new HashMap<>();
                if (null != invScheduleList && !invScheduleList.isEmpty()) {
                    for (SecIntSchedule intSche : invScheduleList) {
                        intMap.put(intSche.getActualCutoffDate().toString(), intSche.getAmount());
                    }
                }

                if (intMap.containsKey(accurIntCalInParameter.getCalDate().toString())
                    && !((Variety.贴现.equals(secInfoModel.getBondsType()))
                         || Variety.零息.equals(secInfoModel.getBondsType()))) {
                    out = new AccurIntCalOutParameter();
                    out.setInterest(
                        intMap.get(accurIntCalInParameter.getCalDate().toString()).multiply(
                            new BigDecimal("100")).divide(curSecAmt, 12, RoundingMode.HALF_UP));
                    accurIntCalInParameter.setCalDate(accurIntCalInParameter.getCalDate());
                    AccurIntForSecheduleCalculator calculator2 = CalculatorFactory.createAccurIntForSecheduleCalculator();
                    if (accurIntCalInParameter.getPaperIrResetList() != null
                        && accurIntCalInParameter.getPaperIrResetList().size() > 1) {
                        accurIntCalInParameter.getSecInfoModel().setIntRateType(IntRateType.浮动利率);
                    }
                    out = calculator2.calculate(accurIntCalInParameter);
                }
                // }
            }
            // 交易所债券特殊处理end----------------------------------------------------------------

            if (out != null) {
                accurInt = ArithmeticUtil.round(out.getInterest(), SCALE, RoundingMode.HALF_UP);
            }
            else {
                // 从计算工厂中创建应计利息计算器
                AccurIntCalculator calc = CalculatorFactory.createAccurIntCalculator();
                // 计算利息
                AccurIntCalOutParameter accurIntCalOutParameter = calc.calculate(
                    accurIntCalInParameter);
                accurInt = ArithmeticUtil.round(accurIntCalOutParameter.getInterest(), SCALE,
                    RoundingMode.HALF_UP);
            }

            // 单位应计利息
            if (unitInt == null) {
                if (isJiaoYiSuo) {
                    unitInt = ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt),
                        new BigDecimal(100),
                        Integer.parseInt(StringUtil.isEmpty(CacheUtil.getSysParam(
                            "SEC_INT_SCALE_EXCHANGE")) ? "8" : CacheUtil.getSysParam(
                                "SEC_INT_SCALE_EXCHANGE")),
                        RoundingMode.HALF_UP);
                }
                else {
                    unitInt = ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt),
                        new BigDecimal(100),
                        Integer.parseInt(StringUtil.isEmpty(CacheUtil.getSysParam(
                            "SEC_INT_SCALE_BANK")) ? "12" : CacheUtil.getSysParam(
                                "SEC_INT_SCALE_BANK")),
                        RoundingMode.HALF_UP);
                }
            }
//            // 应计利息总额 = 单位应计利息 * 券面总额/100
//            应计利息总额 = ArithmeticUtil.divide(ArithmeticUtil.multiply(unitInt, tradeAmount), 100, 2);

            // add by wl 交易方向选择行权时，应计利息及利息总额为0
            if ((StringUtil.equals(DictCodeList.交易方向_行权_回售.getValue(), ps)
                 || StringUtil.equals(DictCodeList.交易方向_行权_赎回.getValue(), ps))) {
                unitInt = BigDecimal.ZERO;
//                应计利息总额 = BigDecimal.ZERO;
            }
        }
        try {

//            BigDecimal cpriceAmt = ArithmeticUtil.divide(
//                ArithmeticUtil.multiply(tradePrice, tradeAmount), new BigDecimal(100), 2);
//            // 全价总额 = 净价总额+ 利息总额
//            BigDecimal fpriceAmt = ArithmeticUtil.doAdd(应计利息总额, cpriceAmt);
            // round（全价总额/券面总额，10）*100
            // 全价 = 单位应计利息 + 单位净价 (保持横向的一致性)

            if (StringUtil.equals(calType, "01")) {
                unitFprice = ArithmeticUtil.doAdd(unitInt, tradePrice);
            }
            else if (StringUtil.equals(calType, "04")) {
                tradePrice = ArithmeticUtil.subtract(unitFprice, unitInt);
            }

            switch (calType){
                    // 单位净价
                case "01":
                    // 全价总额
                case "04":
                    // 全价总额==>到期收益率
                    yieldPriceInParameter.setFullPrice(unitFprice);
                    yieldPriceOutParameter = yieldByPriceCalculator.calculate(yieldPriceInParameter);
                    yield = yieldPriceOutParameter.getMatYield();
                    if (secInfoModelDto.getNextExeDate() != null) {
                        // 全价总额==>行权收益率
                        yieldPriceInParameter.setNextExeDate(
                                toLocalDate(secInfoModelDto.getNextExeDate()));
                        yieldPriceInParameter.setNextExePrice(secInfoModelDto.getNextExercisePrice());
                        if (yieldPriceInParameter.getNextExePrice() != null) {
                            yieldPriceOutParameter = yieldByPriceCalculator.calculate(
                                    yieldPriceInParameter);
                            exerYield = yieldPriceOutParameter.getExeYield();
                        }
                        else {
                            exerYield = null;
                        }
                    }
                    break;
                case "02":
                    // 到期收益率==>全价总额
                    priceYieldInParameter.setMatYield(yield);
                    priceYieldOutParameter = priceByYieldCalculator.calculate(priceYieldInParameter);
                    // dto.setFpriceAmt(priceYieldOutParameter.getFullPrice());
                    unitFprice = priceYieldOutParameter.getFullPrice();
                    // 单位净价 = 全价 - 单位应计利息
                    tradePrice = ArithmeticUtil.subtract(unitFprice, unitInt);
                    // 全价总额==>行权收益率
                    if (secInfoModelDto.getNextExeDate() != null) {
                        yieldPriceInParameter.setFullPrice(unitFprice/* dto.getFpriceAmt() */);
                        yieldPriceInParameter.setNextExeDate(toLocalDate(secInfoModelDto.getNextExeDate()));
                        yieldPriceInParameter.setNextExePrice(secInfoModelDto.getNextExercisePrice());
                        if (yieldPriceInParameter.getNextExePrice() != null) {
                            yieldPriceOutParameter = yieldByPriceCalculator.calculate(yieldPriceInParameter);
                            exerYield = yieldPriceOutParameter.getExeYield();
                        }
                        else {
                            exerYield = null;
                        }
                    }
                    break;
                case "03":
                    // 行权收益率==>全价总额
                    if (secInfoModelDto.getNextExeDate() != null) {
                        priceYieldInParameter.setMatYield(exerYield);
                        priceYieldInParameter.setNextExeDate(toLocalDate(secInfoModelDto.getNextExeDate()));
                        priceYieldInParameter.setNextExePrice(secInfoModelDto.getNextExercisePrice());
                        priceYieldOutParameter = priceByYieldCalculator.calculate(priceYieldInParameter);
                        unitFprice = priceYieldOutParameter.getFullPrice();
                        // 单位净价 = 全价 - 单位应计利息
                        tradePrice = ArithmeticUtil.subtract(unitFprice, unitInt);
                        // 全价总额==>到期收益率
                        yieldPriceInParameter.setFullPrice(unitFprice);
                        yieldPriceOutParameter = yieldByPriceCalculator.calculate(yieldPriceInParameter);
                        yield = yieldPriceOutParameter.getMatYield();
                    }
                    break;
                default:
                    break;
            }

//            // 全价总额==>到期收益率
//            yieldPriceInParameter.setFullPrice(unitFprice);
//            yieldPriceOutParameter = yieldByPriceCalculator.calculate(yieldPriceInParameter);
//            yield = yieldPriceOutParameter.getMatYield();
//            if (secInfoModelDto.getNextExeDate() != null) {
//                // 全价总额==>行权收益率
//                yieldPriceInParameter.setNextExeDate(
//                        toLocalDate(secInfoModelDto.getNextExeDate()));
//                yieldPriceInParameter.setNextExePrice(secInfoModelDto.getNextExercisePrice());
//                if (yieldPriceInParameter.getNextExePrice() != null) {
//                    yieldPriceOutParameter = yieldByPriceCalculator.calculate(
//                            yieldPriceInParameter);
//                    exerYield = yieldPriceOutParameter.getExeYield();
//                }
//                else {
//                    exerYield = null;
//                }
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PtlMockTestTradeDto resultDto = new PtlMockTestTradeDto();
        resultDto.setTradePrice(tradePrice);
        resultDto.setYield(yield);
        resultDto.setExerYield(exerYield);
        resultDto.setAccuir(unitInt);
        return resultDto;
    }
    
    /**
     * 资产信息类型转换
     * @param assetInfoDto MockAssetInfoDto
     * @return SecInfoModelDto
     */
    private SecInfoModelDto toSecInfoModelDto(MockAssetInfoDto assetInfoDto,Date calcDate) {
        SecInfoModelDto secInfoModelDto = new SecInfoModelDto();
        if (assetInfoDto.getFinProduct() != null) {
            secInfoModelDto.setStartDate(assetInfoDto.getFinProduct().getVdate());
            secInfoModelDto.setEndDate(assetInfoDto.getFinProduct().getMdate());
            secInfoModelDto.setIssuePrice(assetInfoDto.getFinProduct().getIssuePrice());
            secInfoModelDto.setBondsType(assetInfoDto.getFinProduct().getCouponSpecies());
        }
        if (!StringUtil.isEmptyList(assetInfoDto.getMockFinCashPlanDtoList())) {
            List<MockFinCashPlanDto> 付息计划列表 = assetInfoDto.getMockFinCashPlanDtoList().stream().filter(
                x -> StringUtil.equals(x.getFinCashInfo().getCashType1(),
                    DictCodeListExt1.现金流一级类型_利息.getValue())).collect(Collectors.toList());
            if (!StringUtil.isEmptyList(付息计划列表)) {
                secInfoModelDto.setFirstPayDate(付息计划列表.get(0).getFinCashPlan().getVdate());
            }
        }
        if (!StringUtil.isEmptyList(assetInfoDto.getFinCashCalcRuleList())) {
            secInfoModelDto.setBasis(assetInfoDto.getFinCashCalcRuleList().get(0).getBasis());
            secInfoModelDto.setIntRateType(assetInfoDto.getFinCashCalcRuleList().get(0).getCalcType());
        }
        if (!StringUtil.isEmptyList(assetInfoDto.getFinCashPayRuleList())) {
            secInfoModelDto.setYearIntRate(assetInfoDto.getFinCashPayRuleList().get(0).getPayCycle());
            secInfoModelDto.setCalMethod(assetInfoDto.getFinCashPayRuleList().get(0).getCalcRule());
        }
        if (!StringUtil.isEmptyList(assetInfoDto.getFinExercisePlanList())) {
            if(DateUtil.compareDate(calcDate,assetInfoDto.getFinExercisePlanList().get(0).getExerciseDate())!=0){
                secInfoModelDto.setNextExeDate(assetInfoDto.getFinExercisePlanList().get(0).getExerciseDate());
                secInfoModelDto.setNextExercisePrice(assetInfoDto.getFinExercisePlanList().get(0).getExercisePrice());
            }else if(assetInfoDto.getFinExercisePlanList().size()>1){
                secInfoModelDto.setNextExeDate(assetInfoDto.getFinExercisePlanList().get(1).getExerciseDate());
                secInfoModelDto.setNextExercisePrice(assetInfoDto.getFinExercisePlanList().get(1).getExercisePrice());
            }
        }
        return secInfoModelDto;
    }
    
    /**
     * 当前债券面额取得（根据还本计划计算）
     * @param assetInfoDto 资产基本信息
     * @return 当前债券面额
     */
    private BigDecimal getCurSecAmt(MockAssetInfoDto assetInfoDto, Date calcDate) {
        BigDecimal result = BigDecimal.valueOf(100);
        if (assetInfoDto == null
            || StringUtil.isEmptyList(assetInfoDto.getMockFinCashPlanDtoList())) {
            return result;
        }

        List<MockFinCashPlanDto> 计算日前还本计划列表 = assetInfoDto.getMockFinCashPlanDtoList().stream().filter(
            x -> StringUtil.equals(x.getFinCashInfo().getCashType1(),
                DictCodeListExt1.现金流一级类型_本金.getValue()) && DateUtil.compareDate(
                    x.getFinCashPlan().getMdate(), calcDate) < 0).collect(Collectors.toList());
        if (!StringUtil.isEmptyList(计算日前还本计划列表)) {
            for (MockFinCashPlanDto mockFinCashPlanDto : 计算日前还本计划列表) {
                result = ArithmeticUtil.subtract(result,
                    mockFinCashPlanDto.getFinCashPlan().getPrinAmt());
            }
        }
        
        return result;
    }

    /**
     * base64编码转换为字节
     * @param req
     * @param imgsURl
     * @return
     */
    private byte[] base64TObyte(HttpServletRequest req, String imgsURl) {
        //对字节数组字符串进行Base64解码并生成图片
        byte[] buffer;
        if (imgsURl == null) //图像数据为空
            return null;
        try {
            imgsURl =  imgsURl.replaceAll(" ", "+");
            String[] url = imgsURl.split("base64,");
            String[] imgUrlArr = imgsURl.split("base64,");//拆分base64编码后部分   
            String u = url[1];
            //Base64解码
            buffer = new BASE64Decoder().decodeBuffer(url[1]);
            //生成图片
            return buffer;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * 净值走势、万份收益走势、七日年化收益率走势图表导出
     * @param request request
     * @param picinfo 图片信息
     * @param response response
     * @return 导出结果
     */
    public ResponseEntity<byte[]> chartExport(HttpServletRequest request, String picinfo, HttpServletResponse response) {
        //类型
        String type = request.getParameter("type");

        try {
            //decodeBase64();
            List<MockTestNetTrend> dataList = JsonUtils.json2List(
                request.getParameter("mockTestNetTrendList"), MockTestNetTrend.class);
            List<String> cellList = JsonUtils.json2List(request.getParameter("cellList"),
                String.class);
            // 生成图片
            byte[] picInfoByte = base64TObyte(request, picinfo);

            String outFileName = FileNameUtil.createFileName("xls");
            String exportFileName = type + GlobalConstant.OPEN_PARENTHESIS
                                    + DateUtil.format(DateUtil.platDateFromCache())
                                    + GlobalConstant.CLOSE_PARENTHESIS + ".xls";
            //导出路径
            String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

            // 模板输入流
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet();
            int index = 1;

            //创建表头
            createHeader(wb, sheet, cellList);

            //写入数据
            if (!StringUtil.isEmptyList(dataList)) {
                writeData(dataList, wb, sheet, index, type);
            }

            Drawing patri = sheet.createDrawingPatriarch();
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                    (short) 0, dataList.size() + 5, (short) 9, dataList.size() + 33);

            //图通过流的形式插入到excel模板中
            ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // 将图片写入流中
            ByteArrayInputStream in = new ByteArrayInputStream(picInfoByte);    //将picInfoByte作为输入流；
            BufferedImage bufferImg = ImageIO.read(in);     //将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
            ImageIO.write(bufferImg, "png", outStream); // 利用HSSFPatriarch将图片写入EXCEL

            patri.createPicture(anchor, wb.addPicture(
                    outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
            File outFile = new File(exportFilePath + File.separator + outFileName);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            wb.write(new FileOutputStream(outFile));
            outStream.flush();
            outStream.close();

            return ExportUtil.export(exportFileName, outFile, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 数据写入
     * @param dataList
     * @param wb
     * @param sheet
     * @param index
     * @param type
     */
    private void writeData(List<MockTestNetTrend> dataList, Workbook wb, Sheet sheet, int index, String type) {
        ReportCfgHandler handler = new ReportCfgHandler();
        // 模板文件最后一行
        Row lastRow = sheet.getRow(sheet.getLastRowNum());
        for (MockTestNetTrend mockTestNetTrend : dataList) {
            Row row = sheet.getRow(index++);
            if (row == null) {
                row = sheet.createRow(index - 1);
                if (lastRow != null) {
                    handler.copyRow(wb, lastRow, row, false);
                }
            }
            // 日期列
            Cell cell = row.createCell(0);
            cell.setCellValue(DateUtil.format(mockTestNetTrend.getCpriceDate()));

            // 单位净值、万份收益、七日年化收益率
            Cell cell2 = row.createCell(1);
            if (StringUtil.equals(type, "净值走势")) {
                if (mockTestNetTrend.getNetUnitValue() != null) {
                    cell2.setCellValue(mockTestNetTrend.getNetUnitValue().toString());
                }
            }
            else if (StringUtil.equals(type, "万份收益走势")) {
                if (mockTestNetTrend.getNetUnitValue() != null) {
                    cell2.setCellValue(mockTestNetTrend.getNetUnitValue().toString());
                }
            }
            else {
                if (mockTestNetTrend.getSevenDayRate() != null) {
                    cell2.setCellValue(mockTestNetTrend.getSevenDayRate().toString());
                }
            }

            // 业绩基准计算结果
            Cell cell3 = row.createCell(2);
            if (mockTestNetTrend.getBaseRule() != null) {
                cell3.setCellValue(mockTestNetTrend.getBaseRuleChg().toString());
                if(StringUtil.equals(type, "七日年化收益率走势")){
                    cell3.setCellValue(mockTestNetTrend.getBaseRule().toString());
                }
            }
            
            // 涨跌幅%
            if (StringUtil.equals(type, "净值走势")) {
                Cell cell4 = row.createCell(3);
                if (mockTestNetTrend.getValChg() != null) {
                    cell4.setCellValue(mockTestNetTrend.getValChg().toString());
                }
            }
        }
    }

    /**
     * 创建表头
     * @param wb
     * @param sheet
     * @param cellList
     */
    private void createHeader(Workbook wb, Sheet sheet, List<String> cellList) { 
        int start = 0;
        sheet.setDefaultColumnWidth(20);
        Row headrow = sheet.createRow(start++);
        CellStyle hCellStyle = wb.createCellStyle();
        hCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        hCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        hCellStyle.setAlignment(HorizontalAlignment.CENTER);
        hCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Font font = wb.createFont();
        font.setBold(true);
        hCellStyle.setFont(font);
        for (int i =0;i< cellList.size(); i++){
            Cell cell = headrow.createCell(i);
            cell.setCellStyle(hCellStyle);
            cell.setCellValue(cellList.get(i));
        } 

        sheet.createRow(start);
    }

    /**
     *
     * @param map
     * @return
     */
    public BigDecimal getUsedMockPosition(Map<String, Object> map) {
        return restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/getUsedMockPosition", map,
                BigDecimal.class);
    }

    /**
     * 根据组合关联的估值方案查询对应资产的投资目的
     * @param portfolioId,finprodId
     * @return 投资目的
     */
    public String findInvAim(Map<String, Object> pageParam) {
        String type = (String) pageParam.get("type8");
        if (StringUtil.equals(type,DictCodeList.平安资产类型_债券.getValue()) || 
                StringUtil.equals(type, DictCodeListExt2.平安资产类型_股票.getValue()) || 
                    StringUtil.equals(type,DictCodeList.平安资产类型_计划类资产_货币型.getValue()) ||
                        StringUtil.equals(type,DictCodeList.平安资产类型_计划类资产_净值型.getValue())){
            return restTemplate.getForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/PtlMockTest/findInvAim/" + pageParam.get("portfolioId") + "/" + pageParam.get("finprodId"),
                String.class);    
        } else{
            return null;    
        }
    }
}
