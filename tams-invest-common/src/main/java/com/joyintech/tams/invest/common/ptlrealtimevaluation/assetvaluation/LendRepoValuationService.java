/*
 * 文件名：BondAssetValuationService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.ptlrealtimevaluation.assetvaluation;

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.BokTradeOrder;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCalcAssetValuationResult;
import com.joyintech.tams.invest.common.dto.mock.assetvaluation.MockCashChangingKey;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockMstExchargeQuoteDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlPortfolioDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockPtlSecValuationDto;
import com.joyintech.tams.invest.common.dto.mock.portfolio.MockSingleAssetChanging;
import com.joyintech.tams.invest.common.dto.mock.trade.MockTrdProductDealDto;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.RealTimeValCommonUtil;
import com.joyintech.tams.invest.common.ptlrealtimevaluation.dao.RealTimeValCommonMapper;
import com.joyintech.tams.valuation.common.constants.ValDictCodeList;

import java.math.BigDecimal;
import java.util.*;

import static com.joyintech.tams.constant.DictCodeList.回购费用处理方式_计入成本;

/**
 * 拆借回购类估值 〈功能详细描述〉
 *
 * @author admin
 * @version 1.0 2020年4月17日
 * @see LendRepoValuationService
 * @since 1.0
 */
public class LendRepoValuationService extends BaseAssetValuationService {

    private RealTimeValCommonMapper realTimeValCommonMapper;

    /**
     * 拆借回购类估值 处理
     *
     * @param 资产基本信息
     * @param 投资目的
     * @param 组合基本信息dto
     * @param 组合资产估值
     * @param 资产端交易列表
     * @param 资产端t_N交易列表
     * @param 测算开始日
     * @param 测算结束日
     * @param 基准日期
     * @param 汇率行情列表
     * @param t_N现金变化_内部
     * @param t_N资产变化_内部
     * @param 摊销重算map
     * @param 行情map
     * @param 现金流计划表map
     * @param T_N计划
     * @param 资产端交易map
     * @param 交易排序规则
     */
    public static MockCalcAssetValuationResult assetValuationProcess(MockAssetInfoDto 资产基本信息, String 投资目的, MockPtlPortfolioDto 组合基本信息dto,
                                             MockPtlSecValuationDto 组合资产估值, List<MockTrdProductDealDto> 资产端交易列表,
                                             List<MockTrdProductDealDto> 资产端t_N交易列表,
                                             String 测算开始日, String 测算结束日, String 基准日期,
                                             List<MockMstExchargeQuoteDto> 汇率行情列表, Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                                             Map<String, MockSingleAssetChanging> t_N资产变化_内部, Map<Date, Boolean> 摊销重算map,
                                             Map<String, MockQuotationInfoDto> 行情map,
                                             Map<String, List<MockFinCashPlanDto>> 现金流计划表map,
                                             List<MockFinCashPlanDto> T_N计划,
                                             Map<String, List<MockTrdProductDealDto>> 资产端交易map, List<BokTradeOrder> 交易排序规则) {

		MockCalcAssetValuationResult result = new MockCalcAssetValuationResult();
		
        计划处理T_N(资产端t_N交易列表, T_N计划, 资产基本信息, 组合资产估值, 投资目的);

        交易处理T_N(资产端t_N交易列表, 基准日期, 资产基本信息, t_N资产变化_内部, t_N现金变化_内部, 摊销重算map);

        String 测算日期 = 测算开始日;

        while (DateUtil.compareDate(测算日期, 测算结束日) <= 0) {
            try{
                组合资产估值.setCdate(DateUtil.parse(测算日期));
                资产变化处理T_N(t_N资产变化_内部, 测算日期, 组合资产估值);
                List<MockTrdProductDealDto> 存续期交易列表 = generate交易类标的存续期交易(测算日期,资产基本信息,组合资产估值,资产端交易map.get(测算日期));
                交易处理(存续期交易列表, 资产端交易map.get(测算日期)
                        , 测算日期, 组合资产估值, 投资目的, 基准日期, 资产基本信息
                        , t_N资产变化_内部, t_N现金变化_内部, 摊销重算map, 交易排序规则);
                计提(资产基本信息, 组合资产估值, 测算日期, 资产端交易map.get(测算日期));
    //            摊销();
    //            公允价值变动();
                资产价值计算(组合资产估值, 组合基本信息dto.getCcy(), 汇率行情列表);
                // 组合资产估值
                MockPtlSecValuationDto 组合资产估值_处理后 = new MockPtlSecValuationDto();
                BeanUtil.copyPropertiesIgnoreNull(组合资产估值_处理后, 组合资产估值);
                if (result.getMockPtlSecValuationDtoList() == null) {
                    result.setMockPtlSecValuationDtoList(new ArrayList<>());
                }
                result.getMockPtlSecValuationDtoList().add(组合资产估值_处理后);
                测算日期 = DateUtil.addDay(测算日期, 1);
            }catch (Exception e){
                getLogger().error(测算日期+"拆借回购估值测算报错==========",e);
                return result;
            }
        }

		return result;

    }

    /**
     * 交易处理
     *
     * @param 存续期交易列表
     * @param 资产端交易列表
     * @param 测算日期
     * @param 组合资产估值
     * @param 投资目的
     * @param 基准日期
     * @param 资产基本信息
     * @param t_N资产变化_内部
     * @param t_N现金变化_内部
     * @param 摊销重算map
     */
    public static void 交易处理(List<MockTrdProductDealDto> 存续期交易列表, List<MockTrdProductDealDto> 资产端交易列表
            , String 测算日期, MockPtlSecValuationDto 组合资产估值, String 投资目的, String 基准日期, MockAssetInfoDto 资产基本信息
            , Map<String, MockSingleAssetChanging> t_N资产变化_内部, Map<MockCashChangingKey, BigDecimal> t_N现金变化_内部,
                            Map<Date, Boolean> 摊销重算map, List<BokTradeOrder> 交易排序规则) {

        //按照交易方向排序 
//        RealTimeValCommonUtil realTimeValCommonUtil = SpringContextUtil.getBean(RealTimeValCommonUtil.class);
         List<MockTrdProductDealDto> 存续期交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(存续期交易列表,
                交易排序规则, DictCodeList.业务类型_拆借交易.getValue()).getAssetTradeList();

        String  现金类型 = RealTimeValCommonUtil.现金类型_融资;
        if (StringUtil.equals(组合资产估值.getAssetLiabilitiesMark(), RealTimeValCommonUtil.资产负债标识_资产)) {
            现金类型 = RealTimeValCommonUtil.现金类型_再投;
        }
        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();

        //存续期处理
        for (MockTrdProductDealDto mockTrdProductDealDto : 存续期交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_到期.getValue())) {
                组合资产估值.setShareAmt(BigDecimal.ZERO);
                组合资产估值.setAccrualAmt(BigDecimal.ZERO);
                组合资产估值.setDscCostAmt(BigDecimal.ZERO);

                if ((StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                        && StringUtil.isNotEmpty(mockTrdProductDealDto.getPayDate())
                        && DateUtil.compareDate(mockTrdProductDealDto.getPayDate(), DateUtil.parse(测算日期)) != 0) {
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setAccrualAmount(组合资产估值.getAccrualAmt().negate());
                    资产变动信息.setAccrualCost(组合资产估值.getAccrualCost().negate());
                    资产变动信息.setAccrualInterest(组合资产估值.getAccrualInt().negate());

                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getPayDate()), 资产变动信息);
                } else {
                    组合资产估值.setAccrualCost(BigDecimal.ZERO);
                    组合资产估值.setTdyIntincexp(BigDecimal.ZERO);
                    组合资产估值.setAccrualInt(BigDecimal.ZERO);
                }
            }
            RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, DictCodeList.是否_否.getValue(),
                    new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()),
                    mockTrdProductDealDto.getTradeAmt());
        }
        Map<String,Object> paraMap =new HashMap<>();
        paraMap.put("paramCode",ValDictCodeList.账套配置表参数项_银行间质押式回购费用处理方式.getValue());
        paraMap.put("booksetId",组合资产估值.getPortfolioId());//组合代码
        RealTimeValCommonMapper realTimeValCommonMapper = SpringContextUtil.getBean(
                RealTimeValCommonMapper.class);
        List<String> paraCodeList = realTimeValCommonMapper.getValBokBooksetExt(paraMap);//获取回购费用是否计入成本开关

        //交易处理
        List<MockTrdProductDealDto> 资产端交易列表_排序 = RealTimeValCommonUtil.sortTrdDealDtoByPs(资产端交易列表,
                交易排序规则, DictCodeList.业务类型_拆借交易.getValue()).getAssetTradeList();
        for (MockTrdProductDealDto mockTrdProductDealDto : 资产端交易列表_排序) {
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
                //回购交易 费用是否计入成本 ADD BY XR Start
                BigDecimal 交易成本=BigDecimal.ZERO;
                //计算净值时使用  --新增字段
                BigDecimal 交易成本无费用=BigDecimal.ZERO;
                if(paraCodeList!=null &&  paraCodeList.get(0).equals(回购费用处理方式_计入成本.getValue())
                && (StringUtil.equals(mockTrdProductDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
                        ||StringUtil.equals(mockTrdProductDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())
                        ||StringUtil.equals(mockTrdProductDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
                        || StringUtil.equals(mockTrdProductDealDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())
                )

                ){
                     交易成本 = ArithmeticUtil.doAdd(mockTrdProductDealDto.getPrinAmt(), mockTrdProductDealDto.getFeeAmt());
                    if (StringUtil.equals(组合资产估值.getAssetLiabilitiesMark(), RealTimeValCommonUtil.资产负债标识_负债)) {
                        交易成本 = ArithmeticUtil.subtract(mockTrdProductDealDto.getPrinAmt(), mockTrdProductDealDto.getFeeAmt());
                    }
                }else{
                    交易成本 = mockTrdProductDealDto.getPrinAmt();
                    if (StringUtil.equals(组合资产估值.getAssetLiabilitiesMark(), RealTimeValCommonUtil.资产负债标识_负债)) {
                        交易成本 = mockTrdProductDealDto.getPrinAmt();
                    }
                }
                交易成本无费用= mockTrdProductDealDto.getPrinAmt();
                //回购交易 费用是否计入成本 ADD BY XR end
                组合资产估值.setDscCostAmtNoFeeamt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmtNoFeeamt(), 交易成本无费用));
                组合资产估值.setDscCostAmt(ArithmeticUtil.doAdd(组合资产估值.getDscCostAmt(), 交易成本));
                组合资产估值.setShareAmt(ArithmeticUtil.doAdd(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
                组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), mockTrdProductDealDto.getIntAmt()));

                if ((StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                        && StringUtil.isNotEmpty(mockTrdProductDealDto.getPayDate())
                        && DateUtil.compareDate(mockTrdProductDealDto.getPayDate(), DateUtil.parse(测算日期)) != 0) {
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setAccrualAmount(mockTrdProductDealDto.getShareAmt());
                    资产变动信息.setAccrualCost(mockTrdProductDealDto.getPrinAmt());
                    资产变动信息.setAccrualInterest(mockTrdProductDealDto.getIntAmt());

                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getPayDate()), 资产变动信息);
                } else {
                    组合资产估值.setAccrualAmt(ArithmeticUtil.doAdd(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
                    组合资产估值.setAccrualCost(ArithmeticUtil.doAdd(组合资产估值.getAccrualCost(), 交易成本));
                    组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), mockTrdProductDealDto.getIntAmt()));
                }
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.subtract(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt());
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), RealTimeValCommonUtil.现金类型_再投, 组合资产估值.getCcy()),
                        RealTimeValCommonUtil.资产负债标识_资产.equals(组合资产估值.getAssetLiabilitiesMark()) ?
                                mockTrdProductDealDto.getTradeAmt().negate() :
                                mockTrdProductDealDto.getTradeAmt());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_卖出.getValue())) {
                组合资产估值.setDscCostAmt(ArithmeticUtil.subtract(组合资产估值.getDscCostAmt(), mockTrdProductDealDto.getPrinAmt()));
                组合资产估值.setShareAmt(ArithmeticUtil.subtract(组合资产估值.getShareAmt(), mockTrdProductDealDto.getShareAmt()));
                BigDecimal 利息总额 = mockTrdProductDealDto.getIntAmt();
                if (!StringUtil.isNotEmpty(利息总额) || 利息总额.compareTo(BigDecimal.ZERO) == 0) {
                    利息总额 = ArithmeticUtil.divide(ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(), 组合资产估值.getAccrualInt()),
                            组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE);
                }
                组合资产估值.setTdyIntincexp(利息总额);
                if ((StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                        && StringUtil.isNotEmpty(mockTrdProductDealDto.getPayDate())
                        && DateUtil.compareDate(mockTrdProductDealDto.getPayDate(), DateUtil.parse(测算日期)) != 0) {
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setAccrualAmount(mockTrdProductDealDto.getShareAmt().negate());
                    资产变动信息.setAccrualCost(mockTrdProductDealDto.getPrinAmt().negate());
                    资产变动信息.setAccrualInterest(mockTrdProductDealDto.getIntAmt().negate());

                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getPayDate()), 资产变动信息);
                } else {
                    组合资产估值.setAccrualAmt(ArithmeticUtil.subtract(组合资产估值.getAccrualAmt(), mockTrdProductDealDto.getShareAmt()));
                    BigDecimal 计提成本 = ArithmeticUtil.divide(ArithmeticUtil.multiply(mockTrdProductDealDto.getShareAmt(),
                            组合资产估值.getAccrualCost()), 组合资产估值.getAccrualAmt(), RealTimeValCommonUtil.AMOUNT_SCALE);

                    组合资产估值.setAccrualCost(ArithmeticUtil.subtract(组合资产估值.getAccrualCost(), 计提成本));
                    组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), 利息总额));
                }
                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }
                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()), mockTrdProductDealDto.getTradeAmt());

            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_到期.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_提前终止.getValue())
                    || StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue())) {
                组合资产估值.setDscCostAmt(BigDecimal.ZERO);
                组合资产估值.setShareAmt(BigDecimal.ZERO);
                组合资产估值.setTdyIntincexp(BigDecimal.ZERO);

                if ((StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                        && StringUtil.isNotEmpty(mockTrdProductDealDto.getPayDate())
                        && DateUtil.compareDate(mockTrdProductDealDto.getPayDate(), DateUtil.parse(测算日期)) != 0) {
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setAccrualAmount(组合资产估值.getAccrualAmt().negate());
                    资产变动信息.setAccrualCost(组合资产估值.getAccrualCost().negate());
                    资产变动信息.setAccrualInterest(组合资产估值.getAccrualInt().negate());

                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getPayDate()), 资产变动信息);
                } else {
                    组合资产估值.setAccrualAmt(BigDecimal.ZERO);
                    组合资产估值.setAccrualCost(BigDecimal.ZERO);
                    组合资产估值.setAccrualInt(BigDecimal.ZERO);
                }

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
            }

            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_还本到账.getValue())) {
                组合资产估值.setDscCostAmt(BigDecimal.ZERO);
                组合资产估值.setShareAmt(BigDecimal.ZERO);
                if ((StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue()))
                        && StringUtil.isNotEmpty(mockTrdProductDealDto.getPayDate())
                        && DateUtil.compareDate(mockTrdProductDealDto.getPayDate(), DateUtil.parse(测算日期)) != 0) {
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setAccrualAmount(组合资产估值.getAccrualAmt().negate());
                    资产变动信息.setAccrualCost(组合资产估值.getAccrualCost().negate());

                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getPayDate()), 资产变动信息);
                } else {
                    组合资产估值.setAccrualAmt(BigDecimal.ZERO);
                    组合资产估值.setAccrualCost(BigDecimal.ZERO);
                }

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
            }
            if (StringUtil.equals(mockTrdProductDealDto.getPs(), DictCodeList.交易方向_收息到账.getValue())) {

                BigDecimal 折算后到期本息和 = ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(组合资产估值.getAccrualAmt(), finTradeProduct.getmTradeAmt()),
                        finTradeProduct.getPrin(), RealTimeValCommonUtil.AMOUNT_SCALE);
                BigDecimal 利息总额 = ArithmeticUtil.subtract(折算后到期本息和, 组合资产估值.getAccrualCost());
                组合资产估值.setTdyIntincexp(ArithmeticUtil.subtract(组合资产估值.getTdyIntincexp(),利息总额));
                组合资产估值.setAccrualInt(ArithmeticUtil.subtract(组合资产估值.getAccrualInt(),利息总额));

                if (DateUtil.compareDate(mockTrdProductDealDto.getTradeDate(), mockTrdProductDealDto.getSettleDate()) != 0) {
                    组合资产估值.setDelayPayAmt(ArithmeticUtil.doAdd(组合资产估值.getDelayPayAmt(), mockTrdProductDealDto.getTradeAmt()));
                    MockSingleAssetChanging 资产变动信息 = new MockSingleAssetChanging();
                    资产变动信息.setDelayPayAmt(mockTrdProductDealDto.getTradeAmt().negate());
                    RealTimeValCommonUtil.单资产变化赋值T_N(t_N资产变化_内部,
                            DateUtil.format(mockTrdProductDealDto.getSettleDate()), 资产变动信息);
                }

                RealTimeValCommonUtil.现金变化赋值T_N(t_N现金变化_内部, 基准日期, mockTrdProductDealDto.getCashProcessedOrNot(),
                        new MockCashChangingKey(mockTrdProductDealDto.getSettleDate(), 现金类型, 组合资产估值.getCcy()),
                        mockTrdProductDealDto.getTradeAmt());
            }

        }

    }


    /**
     * 计提
     *
     * @param 资产基本信息
     * @param 组合资产估值
     * @param 测算日期
     */
    public static void 计提(MockAssetInfoDto 资产基本信息, MockPtlSecValuationDto 组合资产估值, String 测算日期,List<MockTrdProductDealDto> 资产端交易列表) {
        FinTradeProduct finTradeProduct = 资产基本信息.getFinTradeProduct();
        FinProduct finProduct=资产基本信息.getFinProduct();//前台将回购交易费用封装在issueAmt字段上，取改字段
        Date 开始日 = finTradeProduct.getVdate();
        Date 结束日 = finTradeProduct.getMdate();
        if (StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
            开始日 = finTradeProduct.getVpayDate() == null ? 开始日 : finTradeProduct.getVpayDate();
            结束日 = finTradeProduct.getMpayDate() == null ? 结束日 : finTradeProduct.getMpayDate();
        }
        // 变量.开始日 <= 变量.测算日期 <= 变量.结束日
        if (DateUtil.compareDate(DateUtil.format(开始日), 测算日期) <= 0
                && DateUtil.compareDate(DateUtil.format(结束日), 测算日期) > 0) {
//            BigDecimal 折算后到期本息和 = ArithmeticUtil.divide(
//                    ArithmeticUtil.multiply(组合资产估值.getAccrualAmt(), finTradeProduct.getmTradeAmt()),
//                    finTradeProduct.getPrin(), RealTimeValCommonUtil.AMOUNT_SCALE);
//            BigDecimal 利息总额 = ArithmeticUtil.subtract(折算后到期本息和, 组合资产估值.getAccrualCost());
            //获取回购是否将费用计入成本开关
            //调用共通设计->【详细设计-账务类共通方法设计.xlsx】->账套参数查询
            //2.3.质押式回购费用处理方式
            Map<String,Object> paraMap =new HashMap<>();
            paraMap.put("paramCode",ValDictCodeList.账套配置表参数项_银行间质押式回购费用处理方式.getValue());
            paraMap.put("booksetId",组合资产估值.getPortfolioId());//组合代码
            RealTimeValCommonMapper realTimeValCommonMapper = SpringContextUtil.getBean(
                    RealTimeValCommonMapper.class);
            List<String> paraCodeList = realTimeValCommonMapper.getValBokBooksetExt(paraMap);//获取回购费用是否计入成本开关
            BigDecimal intAmt=ArithmeticUtil.subtract(
                    finTradeProduct.getmTradeAmt(),finTradeProduct.getmPrinAmt());
            BigDecimal feeAmt=finProduct.getIssueAmt();//获取回购交易费用
            组合资产估值.setRepFeeamt(feeAmt);//应计入成本的回购交易费用
          if((StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue(),finTradeProduct.getFinprodType2())
                  || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),finTradeProduct.getFinprodType2())
                  || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue(),finTradeProduct.getFinprodType2())
                  || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue(),finTradeProduct.getFinprodType2()))
                  && paraCodeList!=null &&  paraCodeList.get(0).equals(回购费用处理方式_计入成本.getValue())
          ){
              if(StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue(),finTradeProduct.getFinprodType2())
              || StringUtil.equals(DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue(),finTradeProduct.getFinprodType2())){//逆回购计提成本-费用
                  intAmt = ArithmeticUtil.subtract(intAmt, feeAmt);
              }else{//正回购计提成本-费用
                  intAmt = ArithmeticUtil.doAdd(intAmt, feeAmt);
              }
          }

            BigDecimal 利息总额 = ArithmeticUtil.divide(
                    ArithmeticUtil.multiply(组合资产估值.getAccrualAmt(), intAmt),
                    finTradeProduct.getPrin(), RealTimeValCommonUtil.AMOUNT_SCALE);
            Long 计提天数 = DateUtil.getBetweenDayNumber(开始日, 结束日);
            if (DateUtil.compareDate(DateUtil.addDay(测算日期,1),DateUtil.format(结束日))==0) {
                if (StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())
                        || StringUtil.equals(finTradeProduct.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
                    组合资产估值.setTdyIntincexp(BigDecimal.ZERO);
                    组合资产估值.setAccrualInt(BigDecimal.ZERO);
                }else{
                    组合资产估值.setTdyIntincexp(利息总额);
                    组合资产估值.setAccrualInt(利息总额);
                }
            } else {
                BigDecimal 当日利息 = ArithmeticUtil.divide(利息总额, 计提天数.intValue(), RealTimeValCommonUtil.AMOUNT_SCALE);
                组合资产估值.setTdyIntincexp(ArithmeticUtil.doAdd(组合资产估值.getTdyIntincexp(), 当日利息));
                组合资产估值.setAccrualInt(ArithmeticUtil.doAdd(组合资产估值.getAccrualInt(), 当日利息));
            }
        }
    }
}
