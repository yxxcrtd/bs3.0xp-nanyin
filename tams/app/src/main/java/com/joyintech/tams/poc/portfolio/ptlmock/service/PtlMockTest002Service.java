
package com.joyintech.tams.poc.portfolio.ptlmock.service;

import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.isNotFomartSec;
import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.toLocalDate;
import static com.joyintech.tams.invest.common.asset.service.BondAssetCalcService.toSecInfoModel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.db2.jcc.am.x;
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
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.MockCalcAssetValuationParameter;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation.MockSingleAssetChangingKey;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.calcindicator.IndicatorName;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.LiabilitiesChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlCashPositionDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlFeePositionDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlPortfolioValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockPtlValuationPositionDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade.MockTradeInfoDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockProductDeal;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTestNetTrend;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTestRsk;
import com.joyintech.tams.poc.portfolio.ptlmock.entity.MockTradeProduct;
import com.joyintech.tams.poc.portfolio.ptlmock.RealTimeValCommonUtil;
import com.joyintech.tams.poc.portfolio.ptlmock.RealTimeValIndicatorCalcUtil;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.PtlMockTest002Dto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.PtlMockTestTaaDto;
import com.joyintech.tams.poc.portfolio.ptlmock.dto.PtlMockTestTradeDto;
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

    /**
     * 根据组合代码获取组合信息
     * 
     * @param param
     * @return
     */
    public List<SelectOptionDto> getPtlInfo(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/PtlMockTest/getPtlInfo", param,
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
        MockPtlPortfolioDto ptlPortfolioDto = new MockPtlPortfolioDto();//restTemplate.postForObject(
//            ServiceApplication.REPORT_SERVICE_NAME + "/ptlmanagementinfo/selectPortfolioDetailInfo",
//            paramMap, MockPtlPortfolioDto.class);

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
            ServiceApplication.REPORT_SERVICE_NAME + "/PtlMockTest/doComplete",
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
            ServiceApplication.REPORT_SERVICE_NAME + "/PtlMockTest/getFinProdId", param,
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
            ServiceApplication.REPORT_SERVICE_NAME + "/PtlMockTest/getInitInfo", map,
            PtlMockTest002Dto.class);
        return dto;
    }

    private void genTestData(PtlMockTest002Dto resultDto) {
        // 前一日组合表现
        resultDto.setPreDayUnitValue(BigDecimal.valueOf(1.2));
        resultDto.setPreDayValChg(BigDecimal.valueOf(3));
        
        // 实时组合表现
        resultDto.setRealTimeUnitValue(BigDecimal.valueOf(1.3));
        resultDto.setRealTimeValChg(BigDecimal.valueOf(1));
        
        // 实时组合表现（测算后）
        resultDto.setMockUnitValue(BigDecimal.valueOf(1.4));
        resultDto.setMockValChg(BigDecimal.valueOf(1.1));

        // 风险试算列表
        List<MockTestRsk> mockTestRskList = new ArrayList<>();
        MockTestRsk mockTestRsk = new MockTestRsk();
        mockTestRsk.setIndId("rsk001");
        mockTestRsk.setIndName("测试指标1");
        mockTestRsk.setRiskDate(iPlatformDate.getSysDate());
        mockTestRsk.setwInvValue(BigDecimal.valueOf(10));
        mockTestRsk.setaInvValue(BigDecimal.valueOf(15));
        mockTestRsk.seteInvValue(BigDecimal.valueOf(20));
        mockTestRsk.setCurrValue(BigDecimal.valueOf(13));
        mockTestRskList.add(mockTestRsk);

        mockTestRsk = new MockTestRsk();
        mockTestRsk.setIndId("rsk002");
        mockTestRsk.setIndName("测试指标2");
        mockTestRsk.setRiskDate(iPlatformDate.getSysDate());
        mockTestRsk.setwInvValue(BigDecimal.valueOf(12));
        mockTestRsk.setaInvValue(BigDecimal.valueOf(16));
        mockTestRsk.seteInvValue(BigDecimal.valueOf(26));
        mockTestRsk.setCurrValue(BigDecimal.valueOf(15));
        mockTestRskList.add(mockTestRsk);

        resultDto.setMockTestRskList(mockTestRskList);

        // 模拟测算资产配置数据(测算前（考虑当日成交）)
        List<PtlMockTestTaaDto> preMockTestTaaList = new ArrayList<>();
        PtlMockTestTaaDto mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_是.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(340.00));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(240.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(-1240.00));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(0));
        preMockTestTaaList.add(mockTestTaa);

        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_计划类资产_净值型.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_是.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(2818181.82));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(2900000.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(0));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(20));
        preMockTestTaaList.add(mockTestTaa);

        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_基金.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_是.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(9000000.00));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(9000000.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(0));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(30));
        preMockTestTaaList.add(mockTestTaa);

        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_债券.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_是.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(5000000.00));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(5000000.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(0));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(50));
        preMockTestTaaList.add(mockTestTaa);

        resultDto.setPreMockTestTaaList(preMockTestTaaList);

        // 模拟测算资产配置数据(测算后)
        List<PtlMockTestTaaDto> afterMockTestTaaList = new ArrayList<>();
        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_质押式回购.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_否.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(240.00));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(240.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(-1240.00));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(10));
        afterMockTestTaaList.add(mockTestTaa);

        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_计划类资产_净值型.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_否.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(2818181.82));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(2900000.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(0));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(20));
        afterMockTestTaaList.add(mockTestTaa);

        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_基金.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_否.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(9000000.00));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(9000000.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(0));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(30));
        afterMockTestTaaList.add(mockTestTaa);

        mockTestTaa = new PtlMockTestTaaDto();
        mockTestTaa.setAssetType(DictCodeList.平安资产类型_债券.getValue());
        mockTestTaa.setIsMock(DictCodeList.是否_否.getValue());
        mockTestTaa.setCost(BigDecimal.valueOf(5000000.00));
        mockTestTaa.setMarketValue(BigDecimal.valueOf(5000000.00));
        mockTestTaa.setAccruedInc(BigDecimal.valueOf(0));
        mockTestTaa.setAmtRation(BigDecimal.valueOf(40));
        afterMockTestTaaList.add(mockTestTaa);

        resultDto.setAfterMockTestTaaList(afterMockTestTaaList);

        /**
         * 模拟测算净值数据
         */
        List<MockTestNetTrend> mockTestNetTrendList = new ArrayList<>();
        MockTestNetTrend mockTestNetTrend = new MockTestNetTrend();
        mockTestNetTrend.setCpriceDate(DateUtil.parse("2020/03/12"));
        mockTestNetTrend.setNetUnitValue(BigDecimal.valueOf(1.0010));
        mockTestNetTrend.setValChg(BigDecimal.valueOf(0.01));
        mockTestNetTrend.setBaseRuleChg(BigDecimal.valueOf(1.0010));
        mockTestNetTrend.setSevenDayRate(BigDecimal.valueOf(1.0010));
        mockTestNetTrend.setBaseRule(BigDecimal.valueOf(0.02));
        mockTestNetTrendList.add(mockTestNetTrend);

        mockTestNetTrend = new MockTestNetTrend();
        mockTestNetTrend.setCpriceDate(DateUtil.parse("2020/03/13"));
        mockTestNetTrend.setNetUnitValue(BigDecimal.valueOf(1.1020));
        mockTestNetTrend.setValChg(BigDecimal.valueOf(0.02));
        mockTestNetTrend.setBaseRuleChg(BigDecimal.valueOf(1.0020));
        mockTestNetTrend.setSevenDayRate(BigDecimal.valueOf(1.1020));
        mockTestNetTrend.setBaseRule(BigDecimal.valueOf(0.02));
        mockTestNetTrendList.add(mockTestNetTrend);

        mockTestNetTrend = new MockTestNetTrend();
        mockTestNetTrend.setCpriceDate(DateUtil.parse("2020/03/14"));
        mockTestNetTrend.setNetUnitValue(BigDecimal.valueOf(1.2020));
        mockTestNetTrend.setValChg(BigDecimal.valueOf(0.01));
        mockTestNetTrend.setBaseRuleChg(BigDecimal.valueOf(1.0030));
        mockTestNetTrend.setSevenDayRate(BigDecimal.valueOf(1.4020));
        mockTestNetTrend.setBaseRule(BigDecimal.valueOf(0.02));
        mockTestNetTrendList.add(mockTestNetTrend);

        mockTestNetTrend = new MockTestNetTrend();
        mockTestNetTrend.setCpriceDate(DateUtil.parse("2020/03/15"));
        mockTestNetTrend.setNetUnitValue(BigDecimal.valueOf(1.2020));
        mockTestNetTrend.setValChg(BigDecimal.valueOf(0.03));
        mockTestNetTrend.setBaseRuleChg(BigDecimal.valueOf(1.0040));
        mockTestNetTrend.setSevenDayRate(BigDecimal.valueOf(1.4020));
        mockTestNetTrend.setBaseRule(BigDecimal.valueOf(0.02));
        mockTestNetTrendList.add(mockTestNetTrend);

        mockTestNetTrend = new MockTestNetTrend();
        mockTestNetTrend.setCpriceDate(DateUtil.parse("2020/03/16"));
        mockTestNetTrend.setNetUnitValue(BigDecimal.valueOf(1.3020));
        mockTestNetTrend.setValChg(BigDecimal.valueOf(0.01));
        mockTestNetTrend.setBaseRuleChg(BigDecimal.valueOf(1.0050));
        mockTestNetTrend.setSevenDayRate(BigDecimal.valueOf(1.4020));
        mockTestNetTrend.setBaseRule(BigDecimal.valueOf(0.02));
        mockTestNetTrendList.add(mockTestNetTrend);

        resultDto.setMockTestNetTrendList(mockTestNetTrendList);
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

        // BigDecimal 一百 = BigDecimal.valueOf(100);
        String 平台日 = iPlatformDate.getSysDateStr();
        String 平台日前一日 = DateUtil.addDay(iPlatformDate.getSysDateStr(), -1);
        boolean 非虚拟组合 = false;//!StringUtil.equals(DictCodeListExt3.组合类型_虚拟组合.getValue(),
//            inputDto.getPortfolioType());

        // 画面上的业绩基准%、管理费率%的值是百分比，需要除以100
        this.do画面参数百分比处理(inputDto);
        
        // 画面输入项目保存
        BeanUtil.copyPropertiesIgnoreNull(resultDto, inputDto);
        resultDto.setMockDate(new Date());
        
        // TODO 测试数据
        this.genTestData(resultDto);
        if (1 == 1) {
            return resultDto;
        }

        // 2.1 组合基本信息查询
        MockPtlPortfolioDto ptlBaseInfoDto = this.getPtlDetailInfo(inputDto.getPortfolioId());
        ptlBaseInfoDto.setVdate(inputDto.getVdate());
        ptlBaseInfoDto.setMdate(inputDto.getMdate());
        ptlBaseInfoDto.setFinBaseRuleYield(inputDto.getBaseRule());
        ptlBaseInfoDto.setManageFeeRate(inputDto.getMgrFeeRate());

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
            MockPtlValuationPositionDto valuationPositionDto = null;//restTemplate.postForObject(
//                ServiceApplication.REPORT_SERVICE_NAME + "/valuationposition/selectPtlValuationPositionAllData",
//                paramMap, MockPtlValuationPositionDto.class);
            if (valuationPositionDto == null) {
                valuationPositionDto = new MockPtlValuationPositionDto();
            }

            平台日前一日组合资产估值列表 = valuationPositionDto.getPtlSecValuationList();
            全量组合估值列表 = valuationPositionDto.getPtlPortfolioValuationList();
            平台日前一日组合费用头寸列表 = valuationPositionDto.getPtlFeePositionList();
            平台日前一日组合现金列表 = valuationPositionDto.getPtlCashPositionList();
        }
        else {
            // 如果所选组合为虚拟组合，则上记组合估值查询方法是查询不到数据的，需要初始化组合估值列表数据：
            MockPtlPortfolioValuationDto tempPtlValuationDto = new MockPtlPortfolioValuationDto();
            tempPtlValuationDto.setPortfolioId(inputDto.getPortfolioId());
            tempPtlValuationDto.setCdate(DateUtil.parse(平台日前一日));
            tempPtlValuationDto.setCcy(ptlBaseInfoDto.getCcy());
//            tempPtlValuationDto.setTdyShareAmt(inputDto.getRaiseAmtPlan());
            全量组合估值列表.add(tempPtlValuationDto);

            // 如果所选组合为虚拟组合，则上记组合现金查询方法是查询不到数据的，需要初始化组合现金数据：
            MockPtlCashPositionDto ptlCashPositionDto = new MockPtlCashPositionDto();
            ptlCashPositionDto.setPortfolioId(inputDto.getPortfolioId());
            ptlCashPositionDto.setCdate(DateUtil.parse(平台日前一日));
            ptlCashPositionDto.setCcy(ptlBaseInfoDto.getCcy());
            ptlCashPositionDto.setAmount(inputDto.getRaiseAmtPlan());
            平台日前一日组合现金列表.add(ptlCashPositionDto);
        }

        // 2.5.2 调用行情服务.汇率行情信息批量查询方法查询汇率行情数据
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("paramDate", 平台日);
        paramMap.put("ccyList", Arrays.asList(ptlBaseInfoDto.getCcy()));
        List<MockMstExchargeQuoteDto> 汇率行情数据 = null;//restTemplate.postForList(
//            ServiceApplication.BASICDATA_SERVICE_NAME + "/Priceinfo/selectQuotationofrate",
//            paramMap, MockMstExchargeQuoteDto.class);

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
                平台日前一日组合持有资产代码列表.add(ptlSecValuation.getFinprodId());
            }
            finprodIdList.addAll(平台日前一日组合持有资产代码列表);
        }
        // 模拟交易
        for (PtlMockTestTradeDto mockTestTradeDto : inputDto.getPageMockDealList()) {
            if (StringUtil.isNotBlank(mockTestTradeDto.getFinprodId())
                && !finprodIdList.contains(mockTestTradeDto.getFinprodId())) {
                finprodIdList.add(mockTestTradeDto.getFinprodId());
            }
        }
        paramMap.clear();
        paramMap.put("finprodIdList", finprodIdList);
        paramMap.put("paramDate", 平台日);
//        平台日前一日组合持有加模拟交易对应资产基本信息列表 = restTemplate.postForList(
//            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
//            MockAssetInfoDto.class);
        if (!StringUtil.isEmptyList(平台日前一日组合资产估值列表)) {
            平台日前一日组合持有资产基本信息列表 = 平台日前一日组合持有加模拟交易对应资产基本信息列表.stream().filter(
                x -> 平台日前一日组合持有资产代码列表.contains(x.getFinprodId())).collect(Collectors.toList());
        }

        // 3.计算前一日组合表现
        this.do计算组合表现(ptlBaseInfoDto, 平台日前一日组合资产估值列表, 全量组合估值列表, 平台日前一日组合现金, 平台日前一日组合持有资产基本信息列表,
            汇率行情数据, 平台日前一日, resultDto, 1);

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
            当日实时成交交易 = null;//restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
//                                                  + "/common/trdcommonbase/selectTradeinfodeal",
//                paramMap, MockTradeInfoDto.class);
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
//            当日实时组合现金列表 = restTemplate.postForList(ServiceApplication.REPORT_SERVICE_NAME
//                                                  + "/valuationposition/selectPtlCashPositionData",
//                paramMap, MockPtlCashPositionDto.class);

            // 4.3 当日实时成交交易对应资产基本信息列表查询
            List<String> 当日实时成交交易对应资产代码列表 = new ArrayList<>();
            for (MockTrdProductDealDto trdProductDealDto : 资产端交易列表) {
                if (!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            for (MockTrdProductDealDto trdProductDealDto : 负债端交易列表) {
                if (!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            for (MockTrdProductDealDto trdProductDealDto : 资产端T加N交易列表) {
                if (!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            for (MockTrdProductDealDto trdProductDealDto : 负债端T加N交易列表) {
                if (!当日实时成交交易对应资产代码列表.contains(trdProductDealDto.getFinprodId())) {
                    当日实时成交交易对应资产代码列表.add(trdProductDealDto.getFinprodId());
                }
            }
            if (!StringUtil.isEmptyList(当日实时成交交易对应资产代码列表)) {
                paramMap.clear();
                paramMap.put("finprodIdList", 当日实时成交交易对应资产代码列表);
                paramMap.put("paramDate", 平台日);
//                当日实时成交交易对应资产基本信息列表 = restTemplate.postForList(
//                    ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
//                    MockAssetInfoDto.class);
            }
        }
        else {
            // 如果所选组合为虚拟组合，则上记组合现金查询方法是查询不到数据的，需要初始化组合现金数据：
            MockPtlCashPositionDto ptlCashPositionDto = new MockPtlCashPositionDto();
            ptlCashPositionDto.setPortfolioId(inputDto.getPortfolioId());
            ptlCashPositionDto.setCdate(DateUtil.parse(平台日));
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
        MockCalcAssetValuationResult 当日实时组合资产估值_考虑当日成交 = this.do实时组合资产估值(当日实时组合持有资产基本信息列表,
            ptlBaseInfoDto, 平台日前一日组合资产估值列表, 全量组合估值列表, 平台日前一日组合费用头寸列表, 资产端交易列表, 负债端交易列表, 费用交易列表,
            资产端T加N交易列表, 负债端T加N交易列表, 当日实时组合现金Map, 平台日, 平台日, 平台日, 汇率行情数据, inputDto.getFinanCost(),
            inputDto.getAgainInvestRate(), inputDto.getMgrFeeRate(), null, null, null);
        
        // 重新计算债券到期收益率（组合久期计算用到债券到期收益率)
        this.reCalcBondYield(当日实时组合资产估值_考虑当日成交.getMockPtlSecValuationDtoList(), 当日实时组合持有资产基本信息列表,
            资产端交易列表, DateUtil.parse(平台日));
        
        // 5.2.计算实时组合表现（考虑当日成交）
        this.do计算组合表现(ptlBaseInfoDto, 当日实时组合资产估值_考虑当日成交.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_考虑当日成交.getMockPtlPortfolioValuationDtoList(), 当日实时组合现金, 当日实时组合持有资产基本信息列表,
            汇率行情数据, 平台日, resultDto, 2);
        
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
        this.buildMockTradeInfo(resultDto, DateUtil.parse(平台日), 全部资产基本信息列表);
        
        // 6.2.确定计算截止日
        String 计算截止日 = this.calc计算截止日(DateUtil.parse(平台日), ptlBaseInfoDto, inputDto);
        
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
            DateUtil.parse(平台日));
        
        // 调用风险试算共通计算风险试算指标 TODO
        
        // 重新计算债券到期收益率（组合久期计算用到债券到期收益率）
        this.reCalcBondYield(当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(), 全部资产基本信息列表,
            资产端交易列表_含模拟交易, DateUtil.parse(平台日));
        
        // 6.4.计算实时组合表现（试算后）（区间相关指标除外）
        // 6.5.1.计算实时组合表现（试算后）（区间相关指标）
        Map<String, BigDecimal> 测算后组合现金Map = 当日实时组合资产估值_包含模拟交易.getMockPtlCashPositionMap();
        MockPtlCashPositionDto 测算后组合现金 = new MockPtlCashPositionDto();
        测算后组合现金.setCcy(ptlBaseInfoDto.getCcy());
        测算后组合现金.setAmount(测算后组合现金Map.get(ptlBaseInfoDto.getCcy()));
        this.do计算组合表现(ptlBaseInfoDto, 当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(), 测算后组合现金, 全部资产基本信息列表,
            汇率行情数据, 平台日, resultDto, 3);
        
        // 6.5.2.计算区间收益率
        this.do计算区间收益率(ptlBaseInfoDto, 当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(),
            inputDto.getMockRangeStartDate(), inputDto.getMockRangeEndDate(), resultDto);
        
        // 6.5.3.净值走势、万份收益走势等曲线加载
        // 6.5.4.七日年化收益率走势图曲线加载
        this.do曲线数据处理(ptlBaseInfoDto, DateUtil.parse(平台日),
            当日实时组合资产估值_包含模拟交易.getMockPtlSecValuationDtoList(),
            当日实时组合资产估值_包含模拟交易.getMockPtlPortfolioValuationDtoList(), resultDto);

        return resultDto;
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
        
        // 按业绩基准+管理费率计算净值/万份收益
        Map<Date, BigDecimal> 净值型业绩基准_转换Map = null;
        BigDecimal 货币型业绩基准_转换 = null;
        if (StringUtil.equals(DictCodeList.收益类型_净值型.getValue(),
            ptlBaseInfoDto.getProfitType())) {
            净值型业绩基准_转换Map = this.calc净值型业绩基准_转换(ptlBaseInfoDto.getVdate(),
                ptlBaseInfoDto.getMdate(), ptlBaseInfoDto.getFinBaseRuleYield());
        }
        else {
            货币型业绩基准_转换 = this.calc货币型业绩基准_转换(ptlBaseInfoDto.getFinBaseRuleYield(),
                ptlBaseInfoDto.getBasis());
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
            }
            else {
                // 净值/万份收益
                mockTestNetTrend.setNetUnitValue(tempResultDto.getMockPYield());
                // 业绩基准_转换
                mockTestNetTrend.setBaseRuleChg(货币型业绩基准_转换);
            }

            // 涨跌幅
            mockTestNetTrend.setValChg(tempResultDto.getMockValChg());
            // 七日年化收益率
            mockTestNetTrend.setSevenDayRate(tempResultDto.getMockSevenDayRate());
            // 业绩基准
            mockTestNetTrend.setBaseRule(ptlBaseInfoDto.getFinBaseRuleYield());
            
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
    private Map<Date, BigDecimal> calc净值型业绩基准_转换(Date vdate, Date mdate, BigDecimal 业绩基准) {
        Map<Date, BigDecimal> resultMap = new HashMap<>();

        BigDecimal 昨日单位净值 = BigDecimal.ONE;

        Date calcDate = vdate;
        while (DateUtil.compareDate(mdate, calcDate) >= 0) {
            // 以起息日前一日为1，后续每天的单位净值 = 昨日单位净值 *（1+ 业绩基准）
            BigDecimal 当日单位净值 = ArithmeticUtil.multiply(昨日单位净值,
                ArithmeticUtil.doAdd(BigDecimal.ONE, 业绩基准));
            resultMap.put(calcDate, 当日单位净值);

            calcDate = DateUtil.addDay(calcDate, 1);
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
    private BigDecimal calc货币型业绩基准_转换(BigDecimal 业绩基准, String basis) {
        // 计息基础
        int basisNum = 365;
        if (StringUtil.equals(DictCodeList.计息基础_A_360.getValue(), basis)) {
            basisNum = 360;
        }

        // 万份收益走势：业绩基准 * 10000 / 计息基础
        BigDecimal result = ArithmeticUtil.divide(
            ArithmeticUtil.multiply(业绩基准, BigDecimal.valueOf(10000)), basisNum, 4);
        return result;
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
        List<MockPtlSecValuationDto> 测算前当日组合资产估值列表 = 测算前组合资产估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList());
        MockPtlPortfolioValuationDto 测算前组合估值 = 测算前组合估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList()).get(0);
        resultDto.setPreMockTestTaaList(
            this.buildMockTestTaaOneData(测算前当日组合资产估值列表, 测算前组合估值, true));

        List<MockPtlSecValuationDto> 测算后当日组合资产估值列表 = 测算后组合资产估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList());
        MockPtlPortfolioValuationDto 测算后组合估值 = 测算后组合估值列表.stream().filter(
            x -> DateUtil.isSameDate(平台日, x.getCdate())).collect(Collectors.toList()).get(0);
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
        BigDecimal 组合净资产 = 组合估值.getTdyAssetNetvalue();
        
        Map<String, List<MockPtlSecValuationDto>> 组合资产估值分组Map = 组合资产估值列表.stream().collect(
            JoyinCollectors.groupingBy(x -> x.getTgAssetType()));
        for (String tgAssetType : 组合资产估值分组Map.keySet()) {
            List<MockPtlSecValuationDto> curr组合资产估值 = 组合资产估值分组Map.get(tgAssetType);
            
            BigDecimal 成本 = BigDecimal.ZERO;
            BigDecimal 市值 = BigDecimal.ZERO;
            BigDecimal 应收利息 = BigDecimal.ZERO;
            BigDecimal 资产价值 = BigDecimal.ZERO;
            
            String assetLiabilitiesMark = null;
            for (MockPtlSecValuationDto mockPtlSecValuationDto : curr组合资产估值) {
                成本 = ArithmeticUtil.doAdd(成本, mockPtlSecValuationDto.getDscCostAmt());
                市值 = ArithmeticUtil.doAdd(市值, mockPtlSecValuationDto.getDscCostAmt(),
                    mockPtlSecValuationDto.getTdyFloatIngpl());
                应收利息 = ArithmeticUtil.doAdd(应收利息, mockPtlSecValuationDto.getTdyIntincexp());
                资产价值 = ArithmeticUtil.doAdd(资产价值, mockPtlSecValuationDto.getAssetValue());
                
                assetLiabilitiesMark = mockPtlSecValuationDto.getAssetLiabilitiesMark();
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
                ArithmeticUtil.multiply(资产价值, BigDecimal.valueOf(100)), 组合净资产, 2);
            if (StringUtil.equals(RealTimeValCommonUtil.资产负债标识_负债, assetLiabilitiesMark)) {
                规模占比 = ArithmeticUtil.multiply(规模占比, BigDecimal.valueOf(-1));
            }
            mockTestTaa.setAmtRation(规模占比);
            
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
            }
            else {
                // 金融产品代码 虚拟资产：模拟交易类金融产品：MOCK_TRADE_PRODUCT表登录的金融产品代码
                mockProductDeal.setFinprodId(mockProductDeal.getTradeId());
                // 金融产品类型（投管分类）：null
                mockProductDeal.setFinprodType2(null);
            }
            mockProductDeal.setBranch(0L);
            mockProductDeal.setTradeDate(平台日);
            mockProductDeal.setMdate(mockTestTradeDto.getMdate());
            mockProductDeal.setSettleDate(平台日);
            mockProductDeal.setCcy(mockTestTradeDto.getCcy());
            // 单位净价: 画面模拟交易.交易净价
            mockProductDeal.setUnitCprice(mockTestTradeDto.getTradePrice());
            // 单位利息: 画面模拟交易.应计利息
            mockProductDeal.setUnitInt(mockTestTradeDto.getAccuir());
            // 交易份额: 债券：画面模拟交易.交易数量/100 非标：画面模拟交易.交易数量
            if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(),
                mockTestTradeDto.getType8())) {
                mockProductDeal.setShareAmt(ArithmeticUtil.divide(mockTestTradeDto.getAmount(),
                    BigDecimal.valueOf(100), 12));
            }
            else {
                mockProductDeal.setShareAmt(mockTestTradeDto.getAmount());
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
            // 交易状态: 01 初始
            mockProductDeal.setTradeStatus(DictCodeListExt1.交易状态_初始.getValue());
            // 是否已撤销: N 否
            mockProductDeal.setIsCancel(DictCodeList.是否_否.getValue());
            
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
                mockAssetInfoDto.setFinprodId(mockTradeProduct.getFinprodId());
                mockAssetInfoDto.setTgAssetType(RealTimeValCommonUtil.资产类型_模拟资产);
                mockAssetInfoDto.setBranch(0L);
                mockAssetInfoDto.setMockTradeProduct(mockTradeProduct);
                全部资产基本信息列表.add(mockAssetInfoDto);
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
        return new MockCalcAssetValuationResult();
//        return restTemplate.postForObject(
//            ServiceApplication.REPORT_SERVICE_NAME + "/PtlRealTimeValuation/valuationMain",
//            paramObj, MockCalcAssetValuationResult.class);
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
            mockTestTrade.setYield(ArithmeticUtil.divide(mockTestTrade.getYield(), 一百, 2));
            // 行权收益率% / 100
            mockTestTrade.setExerYield(ArithmeticUtil.divide(mockTestTrade.getExerYield(), 一百, 2));
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
            if (计算指标信息Map.containsKey(indicatorName)) {
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

        // 调用组合实时估值服务-计算指标方法
        Map<IndicatorName, Object> calcResult = RealTimeValIndicatorCalcUtil.calcPeriodIndicator(
            ptlBaseInfoDto, null, 全量组合估值列表, null, null, null, 指标列表, 区间开始日, 区间结束日);

        if (calcResult.containsKey(IndicatorName.区间收益率)) {
            resultDto.setMockRangeRate(ArithmeticUtil.multiply(
                (BigDecimal)calcResult.get(IndicatorName.区间收益率), BigDecimal.valueOf(100)));
        }
    }

    /**
     * 查询到的组合现金数据需要根据组合募集币种以及汇率行情数据做换算
     * 
     * @param orgDataList 查询到的组合现金数据
     * @param mstExchargeQuoteList 汇率行情数据
     * @param ptlCcy 组合募集币种
     * @return 汇率换算后的数据
     */
    private MockPtlCashPositionDto do汇率换算(List<MockPtlCashPositionDto> orgDataList,
                                          List<MockMstExchargeQuoteDto> exchargeQuoteList,
                                          String ptlCcy) {
        if (StringUtil.isEmptyList(orgDataList) || StringUtil.isEmptyList(exchargeQuoteList)) {
            return null;
        }

        BigDecimal totalAmout = BigDecimal.ZERO;
        for (MockPtlCashPositionDto cashPositionDto : orgDataList) {
            if (StringUtil.equals(ptlCcy, cashPositionDto.getCcy())) {
                totalAmout = ArithmeticUtil.doAdd(totalAmout, cashPositionDto.getAmount());
            }
            else {
                BigDecimal 汇率 = RealTimeValCommonUtil.汇率获取(ptlCcy, cashPositionDto.getCcy(),
                    exchargeQuoteList);
                totalAmout = ArithmeticUtil.doAdd(totalAmout,
                    ArithmeticUtil.multiply(cashPositionDto.getAmount(), 汇率));
            }
        }

        MockPtlCashPositionDto resultDto = new MockPtlCashPositionDto();
        resultDto.setCcy(ptlCcy);
        resultDto.setAmount(totalAmout);
        return resultDto;
    }

    
    
    /**
     * 计算模拟交易列表到期收益率、行权收益率
     * @param inputTradeDto 模拟交易
     * @return 计算结果
     */
    public PtlMockTestTradeDto calcYield(PtlMockTestTradeDto inputTradeDto) {
        if (inputTradeDto == null || StringUtil.isBlank(inputTradeDto.getFinprodId())
            || inputTradeDto.getTradeAmt() == null || inputTradeDto.getTradePrice() == null) {
            return null;
        }
        
        // 查询资产基本信息
        MockAssetInfoDto assetInfoDto = null;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("finprodIdList", Arrays.asList(inputTradeDto.getFinprodId()));
        paramMap.put("paramDate", iPlatformDate.getSysDateStr());
        List<MockAssetInfoDto> tempAssetList = null;//restTemplate.postForList(
//            ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
//            MockAssetInfoDto.class);
        if (!StringUtil.isEmptyList(tempAssetList)) {
            assetInfoDto = tempAssetList.get(0);
        }
        if (assetInfoDto == null) {
            return null;
        }

        PtlMockTestTradeDto resultDto = new PtlMockTestTradeDto();
        if (StringUtil.equals(DictCodeList.平安资产类型_债券.getValue(), inputTradeDto.getType8())) {
            resultDto = this.calcBondYield(null, inputTradeDto.getFinprodId(),
                inputTradeDto.getPs(), inputTradeDto.getTradePrice(),
                inputTradeDto.getAccuir(), null, iPlatformDate.getSysDate());
        }
        else {
            // 取最新生效利率
            if (StringUtil.isEmptyList(assetInfoDto.getFinCashCalcRuleList())) {
                resultDto.setYield(assetInfoDto.getFinCashCalcRuleList().get(0).getYield());
            }
        }
        
        // 乘以100
        resultDto.setYield(ArithmeticUtil.multiply(resultDto.getYield(), BigDecimal.valueOf(100)));
        resultDto.setExerYield(ArithmeticUtil.multiply(resultDto.getExerYield(), BigDecimal.valueOf(100)));

        return resultDto;
    }
    
    /**
     * 重新计算债券到期收益率（组合久期计算用到债券到期收益率）
     * 
     * @param ptlSecValuationDtoList 组合资产估值数据，计算对象
     * @param assetInfoDtoList 资产信息列表
     * @param tradeList 当日发生交易列表
     * @param calcDate 计算日
     */
    private void reCalcBondYield(List<MockPtlSecValuationDto> ptlSecValuationDtoList,
                                 List<MockAssetInfoDto> assetInfoDtoList,
                                 List<MockTrdProductDealDto> tradeList, Date calcDate) {
        if (StringUtil.isEmptyList(ptlSecValuationDtoList) || StringUtil.isEmptyList(tradeList)) {
            return;
        }

        List<MockPtlSecValuationDto> targetList = ptlSecValuationDtoList.stream().filter(
            x -> DateUtil.isSameDate(calcDate, x.getCdate())).collect(Collectors.toList());

        if (StringUtil.isEmptyList(targetList)) {
            return;
        }

        Map<String, String> tradeFinprodIdMap = tradeList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x.getFinprodId()));
        Map<String, MockAssetInfoDto> assetInfoMap = assetInfoDtoList.stream().collect(
            JoyinCollectors.toMap(x -> x.getFinprodId(), x -> x));

        for (MockPtlSecValuationDto mockPtlSecValuationDto : targetList) {
            if (!tradeFinprodIdMap.containsKey(mockPtlSecValuationDto.getFinprodId())
                || !assetInfoMap.containsKey(mockPtlSecValuationDto.getFinprodId())) {
                continue;
            }

            BigDecimal unitFprice = ArithmeticUtil.divide(
                ArithmeticUtil.doAdd(mockPtlSecValuationDto.getDscCostAmt(),
                    mockPtlSecValuationDto.getTdyIntincexp()),
                mockPtlSecValuationDto.getShareAmt(), 12);
            PtlMockTestTradeDto tempCalcResult = this.calcBondYield(
                assetInfoMap.get(mockPtlSecValuationDto.getFinprodId()),
                mockPtlSecValuationDto.getFinprodId(), null, null, null, unitFprice, calcDate);
            if (tempCalcResult != null && tempCalcResult.getYield() != null) {
//                mockPtlSecValuationDto.setYield(tempCalcResult.getYield());
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
     * @return 计算结果
     */
    public PtlMockTestTradeDto calcBondYield(MockAssetInfoDto assetInfoDto, String finprodId, String ps,
                                       BigDecimal tradePrice, BigDecimal unitInt, BigDecimal unitFprice, Date calcDate) {
        BigDecimal yield = null; // 到期收益率
        BigDecimal exerYield = null; // 行权收益率
        
        if (assetInfoDto == null) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("finprodIdList", Arrays.asList(finprodId));
            paramMap.put("paramDate", DateUtil.format(calcDate));
            List<MockAssetInfoDto> tempAssetList = null;//restTemplate.postForList(
//                ServiceApplication.ASSET_SERVICE_NAME + "/AssetCommon/selectAsset", paramMap,
//                MockAssetInfoDto.class);
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
        // PriceByYieldCalculator priceByYieldCalculator = new PriceByYieldCalculator();
        YieldPriceInParameter yieldPriceInParameter = null;
        PriceYieldInParameter priceYieldInParameter = null;
        YieldPriceOutParameter yieldPriceOutParameter = null;
        // PriceYieldOutParameter priceYieldOutParameter = null;

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
        SecInfoModelDto secInfoModelDto = toSecInfoModelDto(assetInfoDto);
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
            if (unitFprice == null) {
                unitFprice = ArithmeticUtil.doAdd(unitInt, tradePrice);
            }

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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PtlMockTestTradeDto resultDto = new PtlMockTestTradeDto();
        resultDto.setYield(yield);
        resultDto.setExerYield(exerYield);
        return resultDto;
    }
    
    /**
     * 资产信息类型转换
     * @param assetInfoDto MockAssetInfoDto
     * @return SecInfoModelDto
     */
    private SecInfoModelDto toSecInfoModelDto(MockAssetInfoDto assetInfoDto) {
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
            secInfoModelDto.setNextExeDate(assetInfoDto.getFinExercisePlanList().get(0).getExerciseDate());
            secInfoModelDto.setNextExercisePrice(assetInfoDto.getFinExercisePlanList().get(0).getExercisePrice());
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
                    (short) 0, dataList.size() + 5, (short) 5, 25);

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
                cell2.setCellValue(mockTestNetTrend.getNetUnitValue().toString());
            }
            else if (StringUtil.equals(type, "万份收益走势")) {
                cell2.setCellValue(mockTestNetTrend.getNetUnitValue().toString());
            }
            else {
                cell2.setCellValue(mockTestNetTrend.getSevenDayRate().toString());
            }

            // 业绩基准计算结果
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(mockTestNetTrend.getBaseRule().toString());
            
            // 涨跌幅%
            if (StringUtil.equals(type, "净值走势")) {
                Cell cell4 = row.createCell(3);
                cell4.setCellValue(mockTestNetTrend.getValChg().toString());
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
}
