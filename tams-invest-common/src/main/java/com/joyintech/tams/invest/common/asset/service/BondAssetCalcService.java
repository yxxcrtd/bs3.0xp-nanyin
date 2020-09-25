package com.joyintech.tams.invest.common.asset.service;

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.SecPrinSchedule;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.AccurIntForSecheduleCalculator;
import com.joyintech.calculation.shell.PriceByYieldCalculator;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.*;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.asset.dto.BondAssetCalcInputDto;
import com.joyintech.tams.invest.common.asset.dto.FinCashPlanDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: 2.1.2
 * @description:
 * @author: 强成西
 * @create: 2019-12-28 15:51
 **/
@Service
public class BondAssetCalcService extends BaseService {

    public BigDecimal 计算债券单位利息(BondAssetCalcInputDto bondAssetCalcInputDto) throws Exception {
        // 如果交易场所为上交所、深交所以外的情况
        boolean isJiaoYiSuo = true;
        if (!StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), bondAssetCalcInputDto.getTradeMarket())
            && !StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), bondAssetCalcInputDto.getTradeMarket())) {
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
        Map<String, Object> tempParamMap = new HashMap<String, Object>();
        tempParamMap.put("finprodId", bondAssetCalcInputDto.getFinprodId());

        // b.获取计息区间投资端利率报价信息（债券的利率重置信息）
        // List<BondsIntReset> bondsIntResetList = transactionBond001Mapper.getInvRateQuo(dto);
        List<FinCashCalcRule> finCashCalcRuleList = restTemplate.postForList(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/selectBondsIntResetList", tempParamMap,
            FinCashCalcRule.class);

        // c.获取债券的付息计划信息
        List<FinCashPlanDto> finCashPlanList = restTemplate.postForList(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getFinCashPlan", tempParamMap, FinCashPlanDto.class);
        List<SecIntSchedule> invScheduleList = new ArrayList<>();
        List<FinCashPlanDto> intSchList = new ArrayList<FinCashPlanDto>();
        for (FinCashPlanDto item : finCashPlanList) {
            if (StringUtil.equals(item.getCashType1(), DictCodeListExt1.现金流一级类型_利息.getValue())
                && item.getVdateUnadjust() != null) {
                SecIntSchedule schedule = new SecIntSchedule();
                schedule.setTheoryStartDate(toLocalDate(item.getVdateUnadjust()));
                schedule.setTheoryCutoffDate(toLocalDate(item.getMdateUnadjust()));
                schedule.setActualStartDate(toLocalDate(item.getVdate()));
                schedule.setActualCutoffDate(toLocalDate(item.getMdate()));
                schedule.setAmount(item.getIntAmt());
                invScheduleList.add(schedule);
                intSchList.add(item);
            }
        }

        // d.获取债券基本资料信息
        // 计算行权收益率，非行权时，取大于当日的行权日
        tempParamMap.put("ps", bondAssetCalcInputDto.getPs());
        SecInfoModelDto secInfoModelDto = restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getSecInfoModel", tempParamMap, SecInfoModelDto.class);
        SecInfoModel secInfoModel = toSecInfoModel(secInfoModelDto);
        // 如果交易场所为上交所、深交所的情况
        if (isJiaoYiSuo) {
            // 付息债的计息基础使用A_365F
            if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), secInfoModelDto.getBondsType())) {
                secInfoModel.setBasis(Basis.A_365F);
            }
        }

        // e.获取债券的还本计划信息
        List<SecPrinSchedule> secPrinScheduleList = new ArrayList<SecPrinSchedule>();
        for (FinCashPlanDto item : finCashPlanList) {
            if (StringUtil.equals(item.getCashType1(), DictCodeListExt1.现金流一级类型_本金.getValue())) {
                SecPrinSchedule schedule = new SecPrinSchedule();
                schedule.setAmount(item.getIntAmt());
                schedule.setFlag(StringUtil.equals(item.getIsPayInt(), DictCodeList.是否_是.getValue()));
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
            paperIrResetList.get(0).setBaseRateEffDate(BasicUtil.dateToLocal(secInfoModelDto.getStartDate()));
        }
        if (paperIrResetList != null && paperIrResetList.size() > 1) {
            // 多重利率则计算按浮动利率算
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }
        yieldPriceInParameter = new YieldPriceInParameter();
        yieldPriceInParameter.setCalDate(toLocalDate(bondAssetCalcInputDto.getSettleDate()));
        yieldPriceInParameter.setSecInfoModel(secInfoModel);
        yieldPriceInParameter.setSecIntScheduleList(invScheduleList);
        yieldPriceInParameter.setSecPrinScheduleList(secPrinScheduleList);
        yieldPriceInParameter.setPaperIrResetList(paperIrResetList);
        priceYieldInParameter = new PriceYieldInParameter();
        priceYieldInParameter.setCalDate(toLocalDate(bondAssetCalcInputDto.getSettleDate()));
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
        // 交易所债券特殊处理start----------------------------------------------------------------
        Date tempSettleDate = bondAssetCalcInputDto.getSettleDate();
        // a.当前债券面额取得（根据还本计划计算）
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("finprodId", bondAssetCalcInputDto.getFinprodId());
        // paraMap.put("settleDate", DateUtil.format(dto.getSettleDate()));
        paraMap.put("settleDate", DateUtil.format(tempSettleDate));
        BigDecimal curSecAmt = restTemplate.postForObject(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getCurSecAmt", paraMap, BigDecimal.class);

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
            // if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), secInfoModelDto.getBondsType())
            // || StringUtil.equals(DictCodeList.息票品种_贴现.getValue(), secInfoModelDto.getBondsType())
            // || StringUtil.equals(DictCodeList.息票品种_零息.getValue(), secInfoModelDto.getBondsType())) {
            // 付息计划截止日对应的利息
            Map<String, BigDecimal> intMap = new HashMap<>();
            if (null != invScheduleList && !invScheduleList.isEmpty()) {
                for (SecIntSchedule intSche : invScheduleList) {
                    intMap.put(intSche.getActualCutoffDate().toString(), intSche.getAmount());
                }
            }

            if (intMap.containsKey(accurIntCalInParameter.getCalDate().toString())
                && !((Variety.贴现.equals(secInfoModel.getBondsType())) || Variety.零息.equals(secInfoModel.getBondsType()))) {
                out = new AccurIntCalOutParameter();
                out.setInterest(intMap.get(accurIntCalInParameter.getCalDate().toString()).multiply(new BigDecimal("100")).divide(
                    curSecAmt, 12, RoundingMode.HALF_UP));
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
            SecIntSchedule secIntSche = getSecIntSchedule(accurIntCalInParameter.getCalDate(), accurIntCalInParameter.getSecIntScheduleList());
            if (secIntSche == null) {
                throw new Exception("交易日期不属于债券的任何一个区间。");
            }
            AccurIntCalOutParameter accurIntCalOutParameter = calc.calculate(accurIntCalInParameter);
            accurInt = ArithmeticUtil.round(accurIntCalOutParameter.getInterest(), SCALE, RoundingMode.HALF_UP);
        }

        // 单位应计利息
        if (bondAssetCalcInputDto.getUnitInt() == null) {
            if (isJiaoYiSuo) {
                bondAssetCalcInputDto.setUnitInt(ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt),
                    new BigDecimal(100),
                    Integer.parseInt(StringUtils.isEmpty(
                        CacheUtil.getSysParam("SEC_INT_SCALE_EXCHANGE")) ? "8" : CacheUtil.getSysParam("SEC_INT_SCALE_EXCHANGE")),
                    RoundingMode.HALF_UP));
            }
            else {
                bondAssetCalcInputDto.setUnitInt(
                    ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt), new BigDecimal(100),
                        Integer.parseInt(StringUtils.isEmpty(
                            CacheUtil.getSysParam("SEC_INT_SCALE_BANK")) ? "12" : CacheUtil.getSysParam("SEC_INT_SCALE_BANK")),
                        RoundingMode.HALF_UP));
            }
        }
        return bondAssetCalcInputDto.getUnitInt();
    }

    /**
     * 获取计算日所在的付息计划
     * @param calDate 计算日
     * @param secIntScheduleList 付息计划列表
     * @return 付息计划
     */
    private SecIntSchedule getSecIntSchedule(LocalDate calDate, List<SecIntSchedule> secIntScheduleList) {
        if(null != secIntScheduleList) {
            for(SecIntSchedule sec : secIntScheduleList) {
                if(BasicUtil.dateBefore(calDate, sec.getActualCutoffDate())
                        && BasicUtil.dateAfter(calDate, sec.getActualStartDate())) {
                    return sec;
                }
            }
        }
        return null;
    }

    /**
     * 转换数据
     *
     * @param date 日期
     * @return LocalDate
     * @see
     * @since 1.0
     */
    public static LocalDate toLocalDate(Date date) {
        if (date != null) {
            return LocalDate.parse(DateUtil.format(date));
        }
        return null;
    }

    public static boolean isNotFomartSec(List<FinCashPlanDto> secIntScheduleList, SecInfoModelDto secInfoModelDto) {
        // 根据【实际起始日】排序付息计划
        Collections.sort(secIntScheduleList, (o1, o2) -> o1.getVdateUnadjust().compareTo(o2.getVdateUnadjust()));

        if (StringUtil.equals(DictCodeList.频率_月.getValue(), secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 1);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else if (StringUtil.equals(DictCodeList.频率_季.getValue(), secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 3);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else if (StringUtil.equals(DictCodeList.频率_半年.getValue(), secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 6);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addYear(she.getVdateUnadjust(), 1);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 转换数据
     *
     * @param secInfoModelDto 债券基本信息SecInfoModelDto
     * @return 债券基本信息SecInfoModel
     * @see
     * @since 1.0
     */
    public static SecInfoModel toSecInfoModel(SecInfoModelDto secInfoModelDto) {
        SecInfoModel secInfoModel = new SecInfoModel();

        secInfoModel.setStartDate(toLocalDate(secInfoModelDto.getStartDate()));
        secInfoModel.setEndDate(toLocalDate(secInfoModelDto.getEndDate()));
        secInfoModel.setFirstPayDate(toLocalDate(secInfoModelDto.getFirstPayDate()));
        // 利率类型
        if (StringUtil.equals(DictCodeList.利率类型_固定利率.getValue(), secInfoModelDto.getIntRateType())) {
            secInfoModel.setIntRateType(IntRateType.固定利率);
        }
        else {
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }

        if (StringUtil.isNotBlank(secInfoModelDto.getBasis())) {
            String basis = StringUtil.splitToList(GlobalConstant.COMMA, secInfoModelDto.getBasis()).get(0);
           /* DictCodeList basisDict = DictCodeList.from(DictCode.计息基础, basis);*/
            if(StringUtil.equals(basis,DictCodeList.计息基础_30_360.getValue())){
                secInfoModel.setBasis(Basis._30_360);
            }
            else if(StringUtil.equals(basis,DictCodeList.计息基础_A_360.getValue())){
                secInfoModel.setBasis(Basis.A_360);
            }
            else if(StringUtil.equals(basis,DictCodeList.计息基础_A_365.getValue())){
                secInfoModel.setBasis(Basis.A_365);
            }
            else if(StringUtil.equals(basis,DictCodeList.计息基础_A_365F.getValue())){
                secInfoModel.setBasis(Basis.A_365F);
            }
            else if(StringUtil.equals(basis,DictCodeList.计息基础_A_A.getValue())
            ||StringUtil.equals(basis,DictCodeList.计息基础_A_A_平均分配.getValue())
            ||StringUtil.equals(basis,DictCodeList.计息基础_A_A_按实际天数分配.getValue())){
                secInfoModel.setBasis(Basis.A_A);
            }
            else if(StringUtil.equals(basis,DictCodeListExt3.计息基础_30U_360.getValue())){
                secInfoModel.setBasis(Basis._30U_360);
            }
            /*switch (basisDict) {
                case 计息基础_30_360:
                    secInfoModel.setBasis(Basis._30_360);
                    break;
                case 计息基础_A_360:
                    secInfoModel.setBasis(Basis.A_360);
                    break;
                case 计息基础_A_365:
                    secInfoModel.setBasis(Basis.A_365);
                    break;
                case 计息基础_A_365F:
                    secInfoModel.setBasis(Basis.A_365F);
                    break;
                case 计息基础_A_A:
                case 计息基础_A_A_平均分配:
                case 计息基础_A_A_按实际天数分配:
                    secInfoModel.setBasis(Basis.A_A);
                    break;
            }*/
            /*if (basisDict.equals(DictCodeList.计息基础_A_A_平均分配)) {
                secInfoModel.setAllocationType(AllocationType.平均分配);
            }
            else {
                secInfoModel.setAllocationType(AllocationType.按实际天数分配);
            }*/
            if (StringUtil.equals(basis,DictCodeList.计息基础_A_A_平均分配.getValue())) {
                secInfoModel.setAllocationType(AllocationType.平均分配);
            }
            else {
                secInfoModel.setAllocationType(AllocationType.按实际天数分配);
            }
        }
        DictCodeList bondsTypeDict = DictCodeList.from(DictCode.息票品种, secInfoModelDto.getBondsType());
        switch (bondsTypeDict) {
            case 息票品种_零息:
                secInfoModel.setBondsType(Variety.零息);
                break;
            case 息票品种_利随本清:
                secInfoModel.setBondsType(Variety.利随本清);
                break;
            case 息票品种_贴现:
                secInfoModel.setBondsType(Variety.贴现);
                break;
            case 息票品种_附息:
                secInfoModel.setBondsType(Variety.附息);
                break;
        }

        // 一体化对应etliu
        // if (StringUtil.equals(DictCodeList.推算方法_起息日向后.getValue(),
        if (StringUtil.equals(DictCodeList.推算方式_起息日向后.getValue(), secInfoModelDto.getCalMethod())) {
            secInfoModel.setCalMethod(SecIntSchCalMethod.起息日向后);
        }
        // 一体化对应etliu
        // else if (StringUtil.equals(DictCodeList.推算方法_到期日向前.getValue(),
        else if (StringUtil.equals(DictCodeList.推算方式_到期日向前.getValue(), secInfoModelDto.getCalMethod())) {
            secInfoModel.setCalMethod(SecIntSchCalMethod.到期日向前);
        }
        else {
            secInfoModel.setCalMethod(SecIntSchCalMethod.首次付息日);
        }

        // 一体化对应etliu start
        // if (StringUtil.equals(DictCodeList.支付频率_付息频率_月付.getValue(),
        // secInfoModelDto.getYearIntRate())) {
        // secInfoModel.setYearIntRate(IntFrequency.月付);
        // }
        // else if (StringUtil.equals(DictCodeList.支付频率_付息频率_季付.getValue(),
        // secInfoModelDto.getYearIntRate())) {
        // secInfoModel.setYearIntRate(IntFrequency.季付);
        // }
        // else if (StringUtil.equals(DictCodeList.支付频率_付息频率_半年付.getValue(),
        // secInfoModelDto.getYearIntRate())) {
        // secInfoModel.setYearIntRate(IntFrequency.半年付);
        // }
        if (StringUtil.equals(DictCodeList.频率_月.getValue(), secInfoModelDto.getYearIntRate())) {
            secInfoModel.setYearIntRate(IntFrequency.月付);
        }
        else if (StringUtil.equals(DictCodeList.频率_季.getValue(), secInfoModelDto.getYearIntRate())) {
            secInfoModel.setYearIntRate(IntFrequency.季付);
        }
        else if (StringUtil.equals(DictCodeList.频率_半年.getValue(), secInfoModelDto.getYearIntRate())) {
            secInfoModel.setYearIntRate(IntFrequency.半年付);
        }
        // 一体化对应etliu end
        else {
            secInfoModel.setYearIntRate(IntFrequency.年付);
        }

        secInfoModel.setIssuePrice(secInfoModelDto.getIssuePrice());

        return secInfoModel;
    }
}
