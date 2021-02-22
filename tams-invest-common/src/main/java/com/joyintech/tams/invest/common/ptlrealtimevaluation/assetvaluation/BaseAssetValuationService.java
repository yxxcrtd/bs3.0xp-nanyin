package com.joyintech.tams.invest.common.ptlrealtimevaluation.assetvaluation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockSingleAssetChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAssetValuationService {


    private static Logger logger = LoggerFactory.getLogger(BaseAssetValuationService.class);

    public static Logger getLogger(){
        return logger;
    }
    //    一.准备准备

    //方法   1.头寸拷贝

    /**
     * 方法   1.头寸拷贝
     * <功能详细描述>
     *
     * @param 组合资产估值
     * @param 测算开始日
     * @param 资产基本信息
     * @param 组合基本信息dto
     * @param 投资目的
     * @return
     * @see
     * @since 1.0
     */
    public static MockPtlSecValuationDto 头寸拷贝(MockPtlSecValuationDto 组合资产估值, String 测算开始日
            , MockAssetInfoDto 资产基本信息, MockPtlPortfolioDto 组合基本信息dto, String 投资目的) {
        MockPtlSecValuationDto mockPtlSecValuationDto_result = new MockPtlSecValuationDto();
        if (组合资产估值 != null) {
            BeanUtil.copyPropertiesIgnoreNull(mockPtlSecValuationDto_result, 组合资产估值);
        } else {
            // 组合代码
            mockPtlSecValuationDto_result.setPortfolioId(组合基本信息dto.getPortfolioId());
            // 币种
            mockPtlSecValuationDto_result.setCcy(资产基本信息.getFinProduct().getCcy());
            // 投资目的
            mockPtlSecValuationDto_result.setInvAim(投资目的);
            // 金融产品代码
            mockPtlSecValuationDto_result.setFinprodId(资产基本信息.getFinprodId());
            // 证券管理账户/通道代码  统一为999999
            mockPtlSecValuationDto_result.setSecAcctId("999999");
        }
        mockPtlSecValuationDto_result.setTgAssetType(资产基本信息.getTgAssetType());
        if(StringUtil.equals(资产基本信息.getTgAssetType(), RealTimeValCommonUtil.资产类型_模拟资产)){
            mockPtlSecValuationDto_result.setFinprodType3(StringUtil.isEmpty(资产基本信息.getFinprodType3())?DictCodeList.平安资产类型_债券.getValue():资产基本信息.getFinprodType3());
        }
        mockPtlSecValuationDto_result.setAssetLiabilitiesMark(RealTimeValCommonUtil.judge资产负债标识(资产基本信息.getFinProductType()));
        return mockPtlSecValuationDto_result;
    }

    //  方法 2.List转换

    /**
     * 方法 2.List转换成map
     * <功能详细描述>
     *
     * @param 资产基本信息
     * @param 资产端交易列表
     * @param 行情map
     * @param 现金流计划表map
     * @param 测算开始日
     * @param T_N计划
     * @param 资产端交易map
     * @see
     * @since 1.0
     */
    public static void 转换List2Map(MockAssetInfoDto 资产基本信息, List<MockTrdProductDealDto> 资产端交易列表,
                                  Map<String, MockQuotationInfoDto> 行情map, Map<String, List<MockFinCashPlanDto>> 现金流计划表map, String 测算开始日,
                                  List<MockFinCashPlanDto> T_N计划, Map<String, List<MockTrdProductDealDto>> 资产端交易map) {
        //行情dto
        if (资产基本信息.getMockQuotationInfoDtoList() != null && !资产基本信息.getMockQuotationInfoDtoList().isEmpty()) {
            行情map.putAll(资产基本信息.getMockQuotationInfoDtoList().stream().collect(
                    JoyinCollectors.toMap(x -> DateUtil.format(x.getQuotationDate()), x -> x)));
        }
        // 现金流计划表
        if (资产基本信息 != null && 资产基本信息.getMockFinCashPlanDtoList() != null && !资产基本信息.getMockFinCashPlanDtoList().isEmpty()) {
            资产基本信息.getMockFinCashPlanDtoList().forEach(entity -> {
                if (entity != null && entity.getFinCashPlan() != null && entity.getFinCashPlan().getMdate() != null) {
                    String mdate = DateUtil.format(entity.getFinCashPlan().getMdate());
                    if (现金流计划表map.get(mdate) == null) {
                        List<MockFinCashPlanDto> 单日现金流计划表Entity列表 = new ArrayList<MockFinCashPlanDto>();
                        单日现金流计划表Entity列表.add(entity);
                        现金流计划表map.put(mdate, 单日现金流计划表Entity列表);
                    } else {
                        现金流计划表map.get(mdate).add(entity);
                    }

                    if (DateUtil.compareDate(DateUtil.format(entity.getFinCashPlan().getMdate()), 测算开始日) < 0
                            && DateUtil.compareDate(DateUtil.format(entity.getFinCashPlan().getPayDate()), 测算开始日) >= 0) {
                        T_N计划.add(entity);
                    }
                }
            });
        }

        // 资产端交易列表

        if (资产端交易map != null && 资产端交易列表 != null && !资产端交易列表.isEmpty()) {
            资产端交易列表.forEach(entity -> {
                if (entity != null && entity.getTradeDate() != null) {
                    String tradeDate = DateUtil.format(entity.getTradeDate());
                    if (资产端交易map.get(tradeDate) == null) {
                        List<MockTrdProductDealDto> 单日资产端交易列表 = new ArrayList<MockTrdProductDealDto>();
                        单日资产端交易列表.add(entity);
                        资产端交易map.put(tradeDate, 单日资产端交易列表);
                    } else {
                        资产端交易map.get(tradeDate).add(entity);
                    }
                }
            });
        }

    }

    // 方法 3.变量定义 (放在主方法是声明变量，然后调用不同资产的资产估值方法))
    // 方法 4.T+N计划处理
    public static void 计划处理T_N(List<MockTrdProductDealDto> 资产端t_N交易列表, List<MockFinCashPlanDto> T_N计划
            , MockAssetInfoDto 资产基本信息, MockPtlSecValuationDto 组合资产估值, String 投资目的
    ) {
        资产端t_N交易列表.addAll(RealTimeValCommonUtil.generate存续期交易(T_N计划, 资产基本信息.getFinProduct().getCcy()
                , 组合资产估值, 投资目的, 资产基本信息.getMockFinCashPlanDtoList(),false));
    }

    // 方法 5.T+N交易处理
    public static void 交易处理T_N(List<MockTrdProductDealDto> 资产端t_N交易列表, String 基准日期, MockAssetInfoDto 资产基本信息
            , Map<String, MockSingleAssetChanging> t_N单资产变化, Map<MockCashChangingKey, BigDecimal> t_N现金变化, Map<Date, Boolean> 摊销重算map) {
        RealTimeValCommonUtil.交易处理默认方法T_N(资产端t_N交易列表, 基准日期, 资产基本信息, t_N单资产变化, t_N现金变化, 摊销重算map);
    }


    //    二.处理详细
    // 方法 1.T+N资产变化处理
    public static void 资产变化处理T_N(Map<String, MockSingleAssetChanging> t_N资产变化_内部, String 测算日期, MockPtlSecValuationDto 组合资产估值) {
        if (t_N资产变化_内部 == null || 组合资产估值 == null) {
            return;
        }
        MockSingleAssetChanging 测算当日资产变动信息 = t_N资产变化_内部.remove(测算日期);
        if (测算当日资产变动信息 != null) {
            组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), 测算当日资产变动信息.getCost()));
            组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), 测算当日资产变动信息.getAmount()));
            组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), 测算当日资产变动信息.getInterest()));
            组合资产估值.setAccrualCost(ArithmeticUtil.doAdd(组合资产估值.getAccrualCost(), 测算当日资产变动信息.getAccrualCost()));
            组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), 测算当日资产变动信息.getAccrualAmount()));
            组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), 测算当日资产变动信息.getAccrualInterest()));
            组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), 测算当日资产变动信息.getDelayPayAmt()));
            组合资产估值.setInTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getInTdyIntincexp(), 测算当日资产变动信息.getInTdyIntincexp()));
            组合资产估值.setOutTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getOutTdyIntincexp(), 测算当日资产变动信息.getOutTdyIntincexp()));
        }

    }

    // 方法 2.存续期处理
    public static List<MockTrdProductDealDto> 存续期处理(Map<String, List<MockFinCashPlanDto>> 现金流计划表map, String 测算日期, MockAssetInfoDto 资产基本信息
            , MockPtlSecValuationDto 组合资产估值, String 投资目的) {
        List<MockFinCashPlanDto> 现金流计划表 = 现金流计划表map.get(测算日期);
        // 行权交易
        List<MockTrdProductDealDto> 行权交易列表 = RealTimeValCommonUtil.generate行权交易(资产基本信息.getFinExercisePlanList(), 资产基本信息.getFinProduct().getCcy(),
                组合资产估值.getAccrualAmt(), BigDecimal.ZERO, 投资目的,测算日期);
        boolean is行权 = false;
        if(行权交易列表!=null&&行权交易列表.size()>0){
            is行权 = true;
        }
            // 调用存续期交易生成
        List<MockTrdProductDealDto> 存续期交易列表 = RealTimeValCommonUtil.generate存续期交易(现金流计划表,资产基本信息.getFinProduct().getCcy(), 组合资产估值
                    , 投资目的, 资产基本信息.getMockFinCashPlanDtoList(),is行权);
//        组合资产估值.getAccrualAmt(), 组合资产估值.getAccrualInt(), 投资目的);
        存续期交易列表.addAll(行权交易列表);
        return 存续期交易列表;
    }

    // generate交易类标的存续期交易
    public static List<MockTrdProductDealDto> generate交易类标的存续期交易(String 测算日期, MockAssetInfoDto 资产基本信息,
                                                                 MockPtlSecValuationDto 组合资产估值, List<MockTrdProductDealDto> 资产端交易列表) {
        List<MockTrdProductDealDto> result = new ArrayList<MockTrdProductDealDto>();
        if(DateUtil.compareDate(资产基本信息.getFinTradeProduct().getMdate(),DateUtil.parse(测算日期))==0){
            result = RealTimeValCommonUtil.generate交易类标的存续期交易(资产基本信息,组合资产估值, 资产端交易列表);
        }
        return result;
    }


//    // 方法 3.交易处理
//    public static void 交易处理(List<MockTrdProductDealDto> 存续期交易列表, List<MockTrdProductDealDto> 资产端交易列表
//            , String 测算日期, MockPtlSecValuationDto 组合资产估值, String 投资目的, String 基准日期, MockAssetInfoDto 资产基本信息
//            , Map<String, MockSingleAssetChanging> t_N单资产变化_内部, Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部, Map<Date, Boolean> 摊销重算map) {
//        // 调用交易头寸处理
//    }
//
//    // 方法 4.计提     各资产自己实现
//    public static void 计提() {
//
//    }
//
//    // 方法 5.摊销    各资产自己实现
//    public static void 摊销() {
//
//    }
//
//    // 方法 6.公允价值变动    各资产自己实现
//    public static void 公允价值变动() {
//
//    }

    // 方法 7.资产价值计算
    public static void 资产价值计算(MockPtlSecValuationDto 组合资产估值,
                              String 币种,
                              List<MockMstExchargeQuoteDto> 汇率行情列表) {
        RealTimeValCommonUtil.calc资产价值(组合资产估值, 币种, 汇率行情列表);
    }


    /**
     * <功能详细描述>
     *
     * @see
     * @since 1.0
     */
    public static void execute资产估值(String 投资目的, MockAssetInfoDto 资产基本信息, MockPtlPortfolioDto 组合基本信息dto,
                                   MockPtlSecValuationDto 组合资产估值,
                                   List<MockTrdProductDealDto> 资产端交易列表,
                                   List<MockTrdProductDealDto> 资产端t_N交易列表,
                                   Map<String, MockSingleAssetChanging> t_N单资产变化, String 测算开始日, String 测算结束日, String 基准日期,
                                   List<MockMstExchargeQuoteDto> 汇率行情列表, List<MockPtlSecValuationDto> 处理后组合资产估值拓展,
                                   ConcurrentHashMap<MockSingleAssetChangingKey, Map<String, MockSingleAssetChanging>> t_N资产变化,
                                   ConcurrentHashMap<MockCashChangingKey, BigDecimal> t_N现金变化, List<BokTradeOrder> 交易排序规则) {

        // 1.头寸拷贝
        MockPtlSecValuationDto 组合资产估值Result = 头寸拷贝(组合资产估值, 测算开始日, 资产基本信息, 组合基本信息dto, 投资目的);

        // list转成Map
        Map<String, MockQuotationInfoDto> 行情map = new HashMap<String, MockQuotationInfoDto>();
        Map<String, List<MockFinCashPlanDto>> 现金流计划表map = new HashMap<String, List<MockFinCashPlanDto>>();
        List<MockFinCashPlanDto> T_N计划 = new ArrayList<MockFinCashPlanDto>();
        Map<String, List<MockTrdProductDealDto>> 资产端交易map = new HashMap<String, List<MockTrdProductDealDto>>();

        转换List2Map(资产基本信息, 资产端交易列表, 行情map, 现金流计划表map, 测算开始日, T_N计划, 资产端交易map);

        // 方法 3.变量定义
        // t_N现金变化_内部
        Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部 = new HashMap<MockCashChangingKey, BigDecimal>();
//        t_N现金变化_内部.putAll(t_N现金变化);
        // t_N资产变化_内部
        Map<String, MockSingleAssetChanging> t_N单资产变化_内部 = new HashMap<String, MockSingleAssetChanging>();
//        if (t_N单资产变化 != null) {
//            t_N单资产变化_内部.putAll(t_N单资产变化);
//        }
        // 变量.摊销重算map
        Map<Date, Boolean> 摊销重算map = new HashMap<Date, Boolean>();

        // 投管资产类型
        String tgAssetType = 资产基本信息.getTgAssetType();

        if (资产端t_N交易列表 == null) {
            资产端t_N交易列表 = new ArrayList<>();
        }

        // 各资产估值的返回值
        MockCalcAssetValuationResult mockCalcAssetValuationResult = new MockCalcAssetValuationResult();
        // 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.债券
        if (StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_债券.getValue())) {
            // 债券估值处理
            mockCalcAssetValuationResult = BondAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        // 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.基金 且 变量.基本信息.标的类金融产品Entity.收益标识 = 数据字典.收益标识.货币型
        // or 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.计划类资产(货币型）
        else if ((StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_基金.getValue())
                && StringUtil.equals(资产基本信息.getFinProduct().getProfitType(), DictCodeList.收益类型_货币型.getValue()))
                || StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_计划类资产_货币型.getValue())) {
            // 货币类估值处理
            mockCalcAssetValuationResult = CcyAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        // 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.基金 且 变量.基本信息.标的类金融产品Entity.收益标识 = 数据字典.收益标识.净值型
        // or 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.计划类资产(净值型)
        else if ((StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_基金.getValue())
                && StringUtil.equals(资产基本信息.getFinProduct().getProfitType(), DictCodeList.收益类型_净值型.getValue()))
                || StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_计划类资产_净值型.getValue())) {
            // 净值类估值处理
            mockCalcAssetValuationResult = NetAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //TODO 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.计划类资产(利率型) or 项目类资产 or 模拟资产
        // or 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.存款
        else if (StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_计划类资产_利率型.getValue())
                || StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_项目类资产.getValue())
                || StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_存款.getValue())
                || StringUtil.equals(tgAssetType, RealTimeValCommonUtil.资产类型_模拟资产)
        ) {
            // 固收类估值处理
            mockCalcAssetValuationResult = ProjectAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        // 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.质押式回购 or 拆借
        // or 变量.基本信息.投管资产大类 = 数据字典.投管资产类型.买断式回购
        else if (StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_质押式回购.getValue())
                || StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_买断式回购.getValue())
                || StringUtil.equals(tgAssetType, DictCodeList.平安资产类型_拆借.getValue())) {
            // 拆借回购类估值处理
            mockCalcAssetValuationResult = LendRepoValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.债券借贷
        else if (StringUtil.equals(tgAssetType, DictCodeListExt2.平安资产类型_债券借贷.getValue())) {
            // 债券借贷估值处理
            mockCalcAssetValuationResult = BondLendAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.利率互换
        else if (StringUtil.equals(tgAssetType, DictCodeListExt2.平安资产类型_利率互换.getValue())) {
            // 利率互换估值处理
            mockCalcAssetValuationResult = RateAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.股票
        else if (StringUtil.equals(tgAssetType, DictCodeListExt2.平安资产类型_股票.getValue())) {
            // 股票估值处理
            mockCalcAssetValuationResult = StockAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.外汇即期
        else if (StringUtil.equals(tgAssetType, DictCodeListExt2.平安资产类型_外汇即期.getValue())) {
            // 外汇即期估值处理
            mockCalcAssetValuationResult = WtFxSpotexAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.外汇远期
        else if (StringUtil.equals(tgAssetType, DictCodeListExt2.平安资产类型_外汇远期.getValue())) {
            // 外汇远期估值处理
            mockCalcAssetValuationResult = WtForwexAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.外汇掉期
        else if (StringUtil.equals(tgAssetType, DictCodeListExt2.平安资产类型_外汇掉期.getValue())) {
            // 外汇掉期估值处理
            mockCalcAssetValuationResult = WtFxSwapAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }
        //变量.基本信息.投管资产大类 = 数据字典.投管资产类型.期权
        else if (StringUtil.equals(tgAssetType, DictCodeListExt3.平安资产类型_期权.getValue())) {
            // 期权估值处理
            mockCalcAssetValuationResult = OptionAssetValuationService.assetValuationProcess(资产基本信息, 投资目的, 组合基本信息dto, 组合资产估值Result,
                    资产端交易列表, 资产端t_N交易列表,
                    测算开始日, 测算结束日, 基准日期, 汇率行情列表, t_N现金变化_内部, t_N单资产变化_内部, 摊销重算map,
                    行情map, 现金流计划表map, T_N计划, 资产端交易map, 交易排序规则);
        }

        // 出参.处理后组合资产估值扩展列表
        if(mockCalcAssetValuationResult!=null&&mockCalcAssetValuationResult.getMockPtlSecValuationDtoList()!=null)
        处理后组合资产估值拓展.addAll(mockCalcAssetValuationResult.getMockPtlSecValuationDtoList());
        // T+N资产变化汇总处理
        RealTimeValCommonUtil.资产变化合并处理T_N(t_N资产变化, t_N单资产变化_内部, 资产基本信息.getFinprodId(), 投资目的);
        // T+N现金变化汇总处理：
        RealTimeValCommonUtil.现金变化合并处理T_N(t_N现金变化, t_N现金变化_内部);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param t_N现金变化
     * @param 划款类交易
     * @param 基准日期
     * @see
     * @since 1.0
     */
    public static void execute划款交易(Map<MockCashChangingKey, BigDecimal> t_N现金变化, MockTrdProductDealDto 划款类交易, String 基准日期) {
        // TODO 保证金用于可转债申购确认时，业务用保证金金额买入债券，保证金金额持仓需要扣减
        if (StringUtil.equals(划款类交易.getPs(), DictCodeListExt1.交易方向_保证金划款.getValue())) {
            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化, 基准日期, DictCodeList.是否_否.getValue(),
                    new MockCashChangingKey(划款类交易.getTradeDate(),RealTimeValCommonUtil.现金类型_保证金, 划款类交易.getCcy()), 划款类交易.getTradeAmt());
            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化, 基准日期, 划款类交易.getCashProcessedOrNot(),
                    new MockCashChangingKey(划款类交易.getTradeDate(),RealTimeValCommonUtil.现金类型_再投, 划款类交易.getCcy()), 划款类交易.getTradeAmt() == null ? BigDecimal.ZERO : 划款类交易.getTradeAmt().negate());
        } else if (StringUtil.equals(划款类交易.getPs(), DictCodeListExt1.交易方向_保证金回款.getValue())) {
            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化, 基准日期, DictCodeList.是否_否.getValue(),
                    new MockCashChangingKey(划款类交易.getTradeDate(),RealTimeValCommonUtil.现金类型_保证金, 划款类交易.getCcy()), 划款类交易.getTradeAmt() == null ? BigDecimal.ZERO : 划款类交易.getTradeAmt().negate());
            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化, 基准日期, 划款类交易.getCashProcessedOrNot(),
                    new MockCashChangingKey(划款类交易.getTradeDate(),RealTimeValCommonUtil.现金类型_再投, 划款类交易.getCcy()), 划款类交易.getTradeAmt());
        }
    }

    /**
     * 买入交易处理
     *
     * @param mockTrdProductDealDto
     * @param 组合资产估值
     * @param 基准日期
     * @param t_N单资产变化_内部
     * @param t_N现金变化_内部
     * @param 交割日
     */
    public static void 买入(MockTrdProductDealDto mockTrdProductDealDto,
                          MockPtlSecValuationDto 组合资产估值, String 基准日期,
                          Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                          Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                          Date 交割日) {
        组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), mockTrdProductDealDto.getPrinAmt()));
        组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
        组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getIntAmt()));
        组合资产估值.setAccrualCost(ArithmeticUtil.doAdd(组合资产估值.getAccrualCost(), mockTrdProductDealDto.getPrinAmt()));
        组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
        组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getIntAmt()));

        if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {

            组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),
                    mockTrdProductDealDto.getTradeAmt()));
            MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
            资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt());
            RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(交割日), 资产变动信息);
        }
        RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                new MockCashChangingKey(交割日,
                        RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                mockTrdProductDealDto.getTradeAmt().negate());
    }

    /**
     * 卖出交易处理
     *
     * @param mockTrdProductDealDto
     * @param 组合资产估值
     * @param 基准日期
     * @param t_N单资产变化_内部
     * @param t_N现金变化_内部
     * @param 交割日
     */
    public static void 卖出(MockTrdProductDealDto mockTrdProductDealDto,
                          MockPtlSecValuationDto 组合资产估值, String 基准日期,
                          Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                          Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                          Date 交割日) {

        BigDecimal 成本 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                ArithmeticUtil.divide(组合资产估值.getAccrualCost(), 组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
        BigDecimal 公允价值变动 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                ArithmeticUtil.divide(组合资产估值.getTdyFloatIngpl(), 组合资产估值.getShareAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));

        组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), 成本));
        组合资产估值.setTdyFloatIngpl(ArithmeticUtil.subtract(组合资产估值.getTdyFloatIngpl(), 公允价值变动));
        组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(),
                mockTrdProductDealDto.getShareAmt()));
        组合资产估值.setAccrualCost(ArithmeticUtil.subtract(组合资产估值.getAccrualCost(), 成本));
        组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(),
                mockTrdProductDealDto.getShareAmt()));

        BigDecimal 利息总额 = mockTrdProductDealDto.getIntAmt();

        if (!StringUtil.isNotEmpty(组合资产估值.getAccrualAmt()) &&
                组合资产估值.getAccrualAmt().compareTo(new BigDecimal(0)) == 0) {
            利息总额 = new BigDecimal(0);
        }
        组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), 利息总额));
        组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), 利息总额));

        if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
            组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(),
                    mockTrdProductDealDto.getTradeAmt()));
            MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
            资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
            RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(交割日), 资产变动信息);
        }
        RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                new MockCashChangingKey(交割日,
                        RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                mockTrdProductDealDto.getTradeAmt());
    }

    /**
     * 申请_确认:交易方向_申购\交易方向_认购\交易方向_申购确认
     * 交易方向_认购确认\交易方向_转换申购确认\
     * 交易方向_赎回确认\交易方向_转换赎回确认
     *
     * @param mockTrdProductDealDto
     * @param 组合资产估值
     * @param 基准日期
     * @param t_N单资产变化_内部
     * @param t_N现金变化_内部
     * @param 交割日
     */
    public static void 申请_确认(MockTrdProductDealDto mockTrdProductDealDto,
                             MockPtlSecValuationDto 组合资产估值, String 基准日期,
                             Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                             Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                             Date 交割日) {
        if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_申购.getValue())
                || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt2.交易方向_认购.getValue())) {

            组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));

            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                    new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                    mockTrdProductDealDto.getTradeAmt().negate());
        }
        if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_申购确认.getValue())
                || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_认购确认.getValue())
                || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_转换申购确认.getValue())) {
            组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), mockTrdProductDealDto.getPrinAmt()));
            组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getIntAmt()));
            组合资产估值.setAccrualCost(ArithmeticUtil.doAdd(组合资产估值.getAccrualCost(), mockTrdProductDealDto.getPrinAmt()));
            组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getIntAmt()));

            组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(),
                    mockTrdProductDealDto.getTradeAmt()));
        }
        if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_赎回确认.getValue())
                || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_转换赎回确认.getValue())) {
            BigDecimal 成本 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                    ArithmeticUtil.divide(组合资产估值.getAccrualCost(), 组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));
            BigDecimal 公允价值变动 = ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                    ArithmeticUtil.divide(组合资产估值.getTdyFloatIngpl(), 组合资产估值.getShareAmt(), RealTimeValCommonUtil.AMOUNT_SCALE));

            组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), 成本));
            组合资产估值.setTdyFloatIngpl(ArithmeticUtil.subtract(组合资产估值.getTdyFloatIngpl(), 公允价值变动));
            组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(),
                    mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualCost(ArithmeticUtil.subtract(组合资产估值.getAccrualCost(), 成本));
            组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(),
                    mockTrdProductDealDto.getShareAmt()));

            BigDecimal 利息总额 = mockTrdProductDealDto.getIntAmt();

            if (!StringUtil.isNotEmpty(组合资产估值.getAccrualAmt()) &&
                    组合资产估值.getAccrualAmt().compareTo(new BigDecimal(0)) == 0) {
                利息总额 = new BigDecimal(0);
            }
            组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), 利息总额));
            组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), 利息总额));

            组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
        }
    }

    /**
     * 现金分红
     *
     * @param mockTrdProductDealDto
     * @param 组合资产估值
     * @param 基准日期
     * @param t_N单资产变化_内部
     * @param t_N现金变化_内部
     * @param 交割日
     */
    public static void 现金分红(MockTrdProductDealDto mockTrdProductDealDto,
                            MockPtlSecValuationDto 组合资产估值, String 基准日期,
                            Map<String, MockSingleAssetChanging> t_N单资产变化_内部,
                            Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                            Date 交割日) {
        组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getFpriceAmt()));
        组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getFpriceAmt()));
        if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
            组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(),
                    mockTrdProductDealDto.getTradeAmt()));
            MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
            资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
            RealTimeValCommonUtil.单资产变化赋值T_N(t_N单资产变化_内部, DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
        }
        RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(),
                        RealTimeValCommonUtil.现金类型_再投, mockTrdProductDealDto.getCcy()),
                mockTrdProductDealDto.getTradeAmt());
    }

    /**
     * 红利再投_份额强增_份额强减 交易处理
     *
     * @param mockTrdProductDealDto
     * @param 组合资产估值
     */
    public static void 红利再投_份额强增_份额强减(MockTrdProductDealDto mockTrdProductDealDto,
                                      MockPtlSecValuationDto 组合资产估值) {
        if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_红利再投.getValue())) {
            组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualCost(ArithmeticUtil.doAdd(组合资产估值.getAccrualCost(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
        }
        if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_份额强增.getValue())) {
            组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
        }
        if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_份额强减.getValue())) {
            组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
            组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
        }

    }

    /**
     * 公允价值变动
     *
     * @param 组合资产估值
     * @param 资产基本信息
     * @param 日期
     */
    public static void 公允价值变动(MockPtlSecValuationDto 组合资产估值, MockAssetInfoDto 资产基本信息, String 日期) {
        BigDecimal 市价 = null;
        for (MockQuotationInfoDto x : 资产基本信息.getMockQuotationInfoDtoList()) {
            if (DateUtil.compareDate(x.getQuotationDate(), DateUtil.parse(日期)) == 0) {
                市价 = x.getMarketPrice();
            }
        }
        if (StringUtil.isNotEmpty(市价)) {
            组合资产估值.setTdyFloatIngpl(ArithmeticUtil.subtract(
                    ArithmeticUtil.multiply(组合资产估值.getShareAmt(), 市价),
                    组合资产估值.getDscCostAmt()));
        }
        if (!StringUtil.isNotEmpty(组合资产估值.getShareAmt()) ||
                new BigDecimal(0).compareTo(组合资产估值.getShareAmt()) == 0) {
            组合资产估值.setTdyFloatIngpl(new BigDecimal(0));
        }
    }

    /**
     * add by xr  贴现债需特殊处理，因全价已包含利息，所以资产价值需加利息金额
     * @param 组合资产估值
     * @param 币种
     * @param 汇率行情列表
     * @param 投资目的
     * @param 资产基本信息
     */
    // 方法 7.资产价值计算
    public static void 资产价值计算2(MockPtlSecValuationDto 组合资产估值,
                              String 币种,
                              List<MockMstExchargeQuoteDto> 汇率行情列表, String 投资目的,MockAssetInfoDto 资产基本信息) {
        RealTimeValCommonUtil.calc资产价值2(组合资产估值, 币种, 汇率行情列表,投资目的,资产基本信息);
    }

}
