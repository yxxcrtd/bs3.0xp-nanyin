/*
 * 文件名：TrdCommandsCollection002Service.java
 * 版权：Copyright 2018-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：王贵奇
 * 修改时间：2019年12月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.tams.invest.trade.investtrade.commandscollection.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.trade.service.TrdCommonBaseService;
import com.joyintech.tams.invest.trade.bondlend.dto.FinCashPlanDto;
import com.joyintech.tams.invest.trade.bondlend.dto.SecInfoModelDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection001Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection002Dto;
import com.joyintech.tams.invest.trade.investtrade.commandscollection.dto.TrdCommandsCollection003Dto;

/**
 *指令汇总002Service
 * @author 王贵奇
 * @version 1.0 2019年12月31日
 * @see TrdCommandsCollection002Service
 * @since 1.0
 */
@Service
public class TrdCommandsCollection002Service extends CommonExportService<TrdCommandsCollection002Dto>  {

    @Autowired
    private TrdCommonBaseService trdCommonBaseService;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * 查询委托详情列表
     * @param assetType 资产大类
     * @param dto 汇总指令
     * @return List<TrdCommandsCollection002Dto> 委托详情列表
     * @see
     * @since 1.0
     */
    public List<TrdCommandsCollection002Dto> selectEntrustList(String assetType, TrdCommandsCollection001Dto dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user", LoginUserUtil.getUserName());
        param.put("assetType", assetType);
        param.put("finprodId", dto.getFinprodId());
        param.put("ps", dto.getPs());
        param.put("tradeMarket", dto.getTradeMarket());
        param.put("tradePlat", dto.getTradePlat());
        param.put("settleSpeed", dto.getSettleSpeed());
        param.put("secAcctId", dto.getSecAcctId());
        param.put("chlId", dto.getChlId());
        param.put("dealMode", dto.getDealMode());
        param.put("board", dto.getBoard());
        param.put("counterId", dto.getCounterId());
        param.put("purchModle", dto.getPurchModle());
        param.put("purchType", dto.getPurchType());
        if(StringUtil.equals(assetType, DictCodeList.平安资产类型_质押式回购.getValue()))
            param.put("repoType", dto.getType6());
        if(StringUtil.equals(assetType, DictCodeList.平安资产类型_拆借.getValue()))
            param.put("bankLendingType", dto.getType6());
        param.put("termDays", dto.getTermDays());
        param.put("pledgeFinprod", dto.getPledgeFinprod());
        param.put("PS_T04", DictCodeList.交易方向_申购.getValue());
        param.put("PS_T49", DictCodeListExt1.交易方向_债转股.getValue());
        param.put("sysDate", DateUtil.format(DateUtil.platDateFromCache()));
        param.put("POSITION_TYPE_E01",DictCodeListExt1.持仓类型_委托指令可用.getValue());
        param.put("entrustStatus", DictCodeList.委托状态_已接收.getValue());
        param.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        param.put("PINAN_ASSET_TYPE_F02", DictCodeList.平安资产类型_基金.getValue());
        param.put("PINAN_ASSET_TYPE_F03", DictCodeList.平安资产类型_计划类资产_利率型.getValue());
        param.put("PINAN_ASSET_TYPE_F05", DictCodeList.平安资产类型_存款.getValue());
        param.put("PINAN_ASSET_TYPE_F06", DictCodeList.平安资产类型_项目类资产.getValue());
        param.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        param.put("PINAN_ASSET_TYPE_F08", DictCodeList.平安资产类型_拆借.getValue());
        param.put("PINAN_ASSET_TYPE_F10", DictCodeList.平安资产类型_买断式回购.getValue());
        param.put("PINAN_ASSET_TYPE_F11", DictCodeListExt2.平安资产类型_股票.getValue());
        param.put("PINAN_ASSET_TYPE_F17", DictCodeListExt2.平安资产类型_一级市场债.getValue());
        param.put("TYPE6_B14", DictCodeList.WIND一级分类_含债券一级分类等_可转债.getValue());
        param.put("TYPE6_B15", DictCodeList.WIND一级分类_含债券一级分类等_可交换债.getValue());
        List<TrdCommandsCollection002Dto> resultDtoList =  restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandsCollection/selectEntrustList", param,
                TrdCommandsCollection002Dto.class);

        //查询计算全价总额的委托数据
        if ("F01".equals(assetType)){
            if (resultDtoList.size()>0){
                for (TrdCommandsCollection002Dto cdto :resultDtoList){
                    if(StringUtil.isNotEmpty(cdto.getPrice())){
                        cdto.setCalflag("price");
                        calcAmt(cdto);
                        cdto.setFpriceAmt(cdto.getUnitFprice());
                    }
                }
            }
        }
        return resultDtoList;
    }
    

    /**
     * 委托质押券详情查询
     * @param entrustId 委托代码
     * @return List<TrdCommandsCollection003Dto> 委托质押券详情
     * @see
     * @since 1.0
     */
    public List<TrdCommandsCollection003Dto> selectPledgeList(TrdCommandsCollection001Dto dto) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("user", LoginUserUtil.getUserName());
        param.put("ps", dto.getPs());
        param.put("tradeMarket", dto.getTradeMarket());
        param.put("settleSpeed", dto.getSettleSpeed());
        param.put("termDays", dto.getTermDays());
        param.put("repoType", dto.getType6());
        param.put("pledgeFinprod", dto.getPledgeFinprod());
        param.put("entrustStatus", DictCodeList.委托状态_已接收.getValue());
        param.put("PINAN_ASSET_TYPE_F01", DictCodeList.平安资产类型_债券.getValue());
        param.put("PINAN_ASSET_TYPE_F07", DictCodeList.平安资产类型_质押式回购.getValue());
        return restTemplate.postForList(
            ServiceApplication.TRADE_SERVICE_NAME + "/TrdCommandsCollection/selectPledgeList", param,
            TrdCommandsCollection003Dto.class);
    }

    /**
     * 校验指令是否已经转交易到流程中
     * @param dto
     */
    public void checkEtrInFlow(TrdCommandsCollection001Dto dto) {
        if (dto != null && !StringUtil.isEmptyList(dto.getEntrustList())){
            List<TrdCommandsCollection002Dto> dtoList = dto.getEntrustList();
            for (TrdCommandsCollection002Dto entity: dtoList){
                List<WflTrdProductDealAdd> list = trdCommonBaseService.checkEtrToTrdInFlow(entity.getEntrustId());
                if (!StringUtil.isEmptyList(list)){
                    throw new RuntimeException("MSG:存在流程中数据:" + entity.getEntrustId());
                }
            }
        }
    }

    /**
     *
     * 净价、行权收益率、到期收益率联动
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void calcAmt(TrdCommandsCollection002Dto dto) {
        if (dto == null || dto.getVdate() == null || dto.getMdate() == null
                || DateUtil.compareDate(dto.getVdate(), dto.getMdate()) != 0) {
            TrdCommandsCollection002Dto nullDto = new TrdCommandsCollection002Dto();
            try {
                nullDto.setPrice(dto.getPrice());
                nullDto.setExerYield(dto.getExerYield());
                nullDto.setMatureYield(dto.getMatureYield());
                BeanUtil.copyProperties(dto, nullDto);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        dto.setSettleDate(dto.getVdate());

        /*if(StringUtil.equals(DictCodeList.清算速度_T_1.getValue(), dto.getSettleSpreed())){
        	List<String> holiday = dateCalService.getDealCalendarHoliday(dto.getVdate(),
        			DateUtil.addDays(dto.getVdate(), 31),dto.getCalendarId());
        	Date tradingDay = dateCalService.calculationTradingDay(dto.getVdate(), 1, holiday, "1");
        	dto.setSettleDate(tradingDay);
        }*/
        //update by zhangnian 20200212  start  债券二级指令改造
        if(StringUtil.isNotEmpty(dto.getSettleSpreed())){
            if(Integer.parseInt(dto.getSettleSpreed())!=0){
                List<String> holiday = dateCalService.getDealCalendarHoliday(dto.getVdate(),
                        DateUtil.addDays(dto.getVdate(), 31),dto.getCalendarId());
                Date tradingDay = dateCalService.calculationTradingDay(dto.getVdate(), Integer.parseInt(dto.getSettleSpreed()), holiday, "1");
                dto.setSettleDate(tradingDay);
            }
        }
        //update by zhangnian 20200212  end
        if (DateUtil.compareDate(dto.getSettleDate(), dto.getFinvdate()) < 0) {
            dto.setSettleDate(dto.getFinvdate());
        }

        // 如果交易场所为上交所、深交所以外的情况
        boolean isJiaoYiSuo = true;
        if (!StringUtil.equals(DictCodeListExt1.交易场所_上海证券交易所.getValue(), dto.getTradeMarket())
                && !StringUtil.equals(DictCodeListExt1.交易场所_深圳证券交易所.getValue(), dto.getTradeMarket())) {
            // 应计利息根据后台估值核算中前一个自然日的数据计算，等于后台的应计利息总额/后台的债券持仓总数量
            this.getUnitIntFromValution(dto);
            isJiaoYiSuo = false;
        }

//        dto.setPrinAmt(dto.getTradeAmt());

        final int SCALE = 12;

        //到期收益率
        dto.setMatureYield(ArithmeticUtil.divide(dto.getMatureYield(), 100, 14));
        //行权收益率
        dto.setExerYield(ArithmeticUtil.divide(dto.getExerYield(), 100, 14));

        YieldByPriceCalculator yieldByPriceCalculator = new YieldByPriceCalculator();
        PriceByYieldCalculator priceByYieldCalculator = new PriceByYieldCalculator();
        YieldPriceInParameter yieldPriceInParameter = null;
        PriceYieldInParameter priceYieldInParameter = null;
        YieldPriceOutParameter yieldPriceOutParameter = null;
        PriceYieldOutParameter priceYieldOutParameter = null;

        Map<String, Object> tempParamMap = new HashMap<String, Object>();
        tempParamMap.put("finprodId", dto.getFinprodId());
        tempParamMap.put("ps", dto.getPs());
        // b.获取计息区间投资端利率报价信息（债券的利率重置信息）
//         List<BondsIntReset> bondsIntResetList = transactionBond001Mapper.getInvRateQuo(dto);
        List<FinCashCalcRule> finCashCalcRuleList = restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/selectBondsIntResetList",
                tempParamMap, FinCashCalcRule.class);

        // c.获取债券的付息计划信息
        List<FinCashPlanDto> finCashPlanList = restTemplate.postForList(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getFinCashPlan",
                tempParamMap, FinCashPlanDto.class);
        List<SecIntSchedule> invScheduleList = new ArrayList<>();
        List<FinCashPlanDto> intSchList = new ArrayList<FinCashPlanDto>();
        for (FinCashPlanDto item : finCashPlanList) {
            if (StringUtil.equals(item.getCashType1(),
                    DictCodeListExt1.现金流一级类型_利息.getValue())
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
        SecInfoModelDto secInfoModelDto = restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getSecInfoModel",
                tempParamMap, SecInfoModelDto.class);
        SecInfoModel secInfoModel = this.toSecInfoModel(secInfoModelDto);
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
            if (StringUtil.equals(item.getCashType1(),
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
            //多重利率则计算按浮动利率算
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }
        yieldPriceInParameter = new YieldPriceInParameter();
        yieldPriceInParameter.setCalDate(toLocalDate(dto.getSettleDate()));
        yieldPriceInParameter.setSecInfoModel(secInfoModel);
        yieldPriceInParameter.setSecIntScheduleList(invScheduleList);
        yieldPriceInParameter.setSecPrinScheduleList(secPrinScheduleList);
        yieldPriceInParameter.setPaperIrResetList(paperIrResetList);
        priceYieldInParameter = new PriceYieldInParameter();
        priceYieldInParameter.setCalDate(toLocalDate(dto.getSettleDate()));
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
            } else {
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
        } else {
            priceYieldInParameter.setNextIntMethod(NextIntCalMethod.使用计算日最新最新票面计算);
            yieldPriceInParameter.setNextIntMethod(NextIntCalMethod.使用计算日最新最新票面计算);

            yieldPriceInParameter.setCurrIntMethod(CurrIntCalMethod.使用区间起始日前最新票面利率计算);
            priceYieldInParameter.setCurrIntMethod(CurrIntCalMethod.使用区间起始日前最新票面利率计算);
        }
        // 百元面值应计利息
        BigDecimal accurInt = BigDecimal.ZERO;
        // 交易方式为“一级市场”时，单位应计利息=应计利息总额=0
        if (StringUtil.equals(dto.getTradeMarketMode(),
                DictCodeListExt1.交易市场_一级市场.getValue())) {
            dto.setIntAmt(BigDecimal.ZERO);
            dto.setUnitInt(accurInt);
        }
        // 交易方式为“二级市场”时，单位应计利息 =e取得的百元面值应计利息/100*a取得的当前债券面额
        else if (StringUtil.equals(dto.getTradeMarketMode(),
                DictCodeListExt1.交易市场_二级市场.getValue())) {
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

            //交易所债券特殊处理start----------------------------------------------------------------
            Date tempSettleDate = dto.getSettleDate();
            // 如果交易场所为上交所、深交所的情况
            if (isJiaoYiSuo) {
                // 付息债、贴现债、零息债，交割日+1
//                if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), secInfoModelDto.getBondsType())
//                    || StringUtil.equals(DictCodeList.息票品种_贴现.getValue(), secInfoModelDto.getBondsType())
//                    || StringUtil.equals(DictCodeList.息票品种_零息.getValue(), secInfoModelDto.getBondsType())) {
                if (!StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())
                        && !StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_赎回.getValue())) {
                    tempSettleDate = DateUtil.addDay(tempSettleDate, 1);
                }
//                }
            }
            //交易所债券特殊处理end----------------------------------------------------------------

            // a.当前债券面额取得（根据还本计划计算）
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("finprodId", dto.getFinprodId());
//            paraMap.put("settleDate", DateUtil.format(dto.getSettleDate()));
            paraMap.put("settleDate", DateUtil.format(tempSettleDate));
            BigDecimal curSecAmt = restTemplate.postForObject(
                    ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getCurSecAmt",
                    paraMap, BigDecimal.class);

            AccurIntCalInParameter accurIntCalInParameter = new AccurIntCalInParameter();
            // 计算日
//            accurIntCalInParameter.setCalDate(this.toLocalDate(dto.getSettleDate()));
            accurIntCalInParameter.setCalDate(this.toLocalDate(tempSettleDate));
            // 债券基本资料
            accurIntCalInParameter.setSecInfoModel(secInfoModel);
            // 付息计划
            accurIntCalInParameter.setSecIntScheduleList(invScheduleList);
            // 利率重置信息
            accurIntCalInParameter.setPaperIrResetList(paperIrResetList);

            //交易所债券特殊处理start----------------------------------------------------------------
            AccurIntCalOutParameter out = null;
            // 如果交易场所为上交所、深交所的情况
            if (isJiaoYiSuo) {
                // 付息债、贴现债、零息债，交割日+1
//                if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), secInfoModelDto.getBondsType())
//                    || StringUtil.equals(DictCodeList.息票品种_贴现.getValue(), secInfoModelDto.getBondsType())
//                    || StringUtil.equals(DictCodeList.息票品种_零息.getValue(), secInfoModelDto.getBondsType())) {
                // 付息计划截止日对应的利息
                Map<String, BigDecimal> intMap = new HashMap<>();
                if(null != invScheduleList && !invScheduleList.isEmpty()) {
                    for(SecIntSchedule intSche : invScheduleList) {
                        intMap.put(intSche.getActualCutoffDate().toString(), intSche.getAmount());
                    }
                }

                if(intMap.containsKey(accurIntCalInParameter.getCalDate().toString()) &&
                        !((Variety.贴现.equals(secInfoModel.getBondsType())) || Variety.零息.equals(secInfoModel.getBondsType()))) {
                    out = new AccurIntCalOutParameter();
                    out.setInterest(intMap.get(accurIntCalInParameter.getCalDate().toString()).multiply(new BigDecimal("100")
                    ).divide(curSecAmt, 12, RoundingMode.HALF_UP));
                    accurIntCalInParameter.setCalDate(accurIntCalInParameter.getCalDate());
                    AccurIntForSecheduleCalculator calculator2 = CalculatorFactory.createAccurIntForSecheduleCalculator();
                    if (accurIntCalInParameter.getPaperIrResetList() != null && accurIntCalInParameter.getPaperIrResetList().size() > 1) {
                        accurIntCalInParameter.getSecInfoModel().setIntRateType(IntRateType.浮动利率);
                    }
                    out = calculator2.calculate(accurIntCalInParameter);
                }
//                }
            }
            //交易所债券特殊处理end----------------------------------------------------------------

            if (out != null) {
                accurInt = ArithmeticUtil.round(out.getInterest(), SCALE, RoundingMode.HALF_UP);
            }
            else {
                // 从计算工厂中创建应计利息计算器
                AccurIntCalculator calc = CalculatorFactory.createAccurIntCalculator();
                // 计算利息
                AccurIntCalOutParameter accurIntCalOutParameter = calc.calculate(accurIntCalInParameter);
                accurInt = ArithmeticUtil.round(accurIntCalOutParameter.getInterest(), SCALE, RoundingMode.HALF_UP);
            }

            // 单位应计利息
            if (dto.getUnitInt() == null) {
                if (isJiaoYiSuo) {
                    dto.setUnitInt(ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt), new BigDecimal(100),
                            Integer.parseInt(StringUtils.isEmpty(CacheUtil.getSysParam(
                                    "SEC_INT_SCALE_EXCHANGE")) ? "8" : CacheUtil.getSysParam(
                                    "SEC_INT_SCALE_EXCHANGE")), RoundingMode.HALF_UP));
                }
                else {
                    dto.setUnitInt(ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt), new BigDecimal(100),
                            Integer.parseInt(StringUtils.isEmpty(CacheUtil.getSysParam(
                                    "SEC_INT_SCALE_BANK")) ? "12" : CacheUtil.getSysParam(
                                    "SEC_INT_SCALE_BANK")), RoundingMode.HALF_UP));
                }
            }
//            // 应计利息总额 = 单位应计利息 * 券面总额/100
//            dto.setIntAmt(ArithmeticUtil.divide(ArithmeticUtil.multiply(dto.getUnitInt(), dto.getPrinAmt()),
//                new BigDecimal(100), SCALE));
        }
        try {
            // 单位净价
            if (StringUtil.equals(dto.getCalflag(), "price")) {
                // 单位全价 = 单位净价 + 单位应计利息
                dto.setUnitFprice(ArithmeticUtil.doAdd(dto.getPrice(), dto.getUnitInt()));
                // 全价总额 = 单位全价 * 券面总额/100
                dto.setFpriceAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(dto.getPrice(), dto.getEntrustNum()),
                        new BigDecimal(100), SCALE));
            }
            switch (dto.getCalflag()) {
                // 单位净价
                case "price":
                    // 全价总额
                case "fpriceAmt":
                    // 全价总额==>到期收益率
                    yieldPriceInParameter.setFullPrice(
                            dto.getUnitFprice()/* dto.getFpriceAmt() */);
                    yieldPriceOutParameter = yieldByPriceCalculator.calculate(
                            yieldPriceInParameter);
                    dto.setMatureYield(yieldPriceOutParameter.getMatYield());
                    if (secInfoModelDto.getNextExeDate() != null) {
                        // 全价总额==>行权收益率
                        yieldPriceInParameter.setNextExeDate(
                                toLocalDate(secInfoModelDto.getNextExeDate()));
                        yieldPriceInParameter.setNextExePrice(
                                secInfoModelDto.getNextExercisePrice());
                        if (yieldPriceInParameter.getNextExePrice() != null) {
                            yieldPriceOutParameter = yieldByPriceCalculator.calculate(
                                    yieldPriceInParameter);
                            dto.setExerYield(yieldPriceOutParameter.getExeYield());
                        }
                        else {
                            dto.setExerYield(null);
                        }
                    }
                    break;
                // 到期收益率
                case "matureYield":
                    // 到期收益率==>全价总额
                    priceYieldInParameter.setMatYield(dto.getMatureYield());
                    priceYieldOutParameter = priceByYieldCalculator.calculate(
                            priceYieldInParameter);
                    // dto.setFpriceAmt(priceYieldOutParameter.getFullPrice());
                    dto.setUnitFprice(priceYieldOutParameter.getFullPrice());
                    // 全价总额==>行权收益率
                    if (secInfoModelDto.getNextExeDate() != null) {
                        yieldPriceInParameter.setFullPrice(
                                dto.getUnitFprice()/* dto.getFpriceAmt() */);
                        yieldPriceInParameter.setNextExeDate(
                                toLocalDate(secInfoModelDto.getNextExeDate()));
                        yieldPriceInParameter.setNextExePrice(
                                secInfoModelDto.getNextExercisePrice());
                        if (yieldPriceInParameter.getNextExePrice() != null) {
                            yieldPriceOutParameter = yieldByPriceCalculator.calculate(
                                    yieldPriceInParameter);
                            dto.setExerYield(yieldPriceOutParameter.getMatYield());
                        }
                        else {
                            dto.setExerYield(null);
                        }
                    }
                    break;
                // 行权收益率
                case "exerYield":
                    // 行权收益率==>全价总额
                    if (secInfoModelDto.getNextExeDate() != null) {
//                        priceYieldInParameter.setMatYield(dto.getMatureYield());
                        priceYieldInParameter.setMatYield(dto.getExerYield());//行权收益
                        yieldPriceInParameter.setNextExeDate(
                                toLocalDate(secInfoModelDto.getNextExeDate()));
                        yieldPriceInParameter.setNextExePrice(
                                secInfoModelDto.getNextExercisePrice());
                        priceYieldOutParameter = priceByYieldCalculator.calculate(
                                priceYieldInParameter);
                        dto.setFpriceAmt(priceYieldOutParameter.getFullPrice());
                        dto.setUnitFprice(priceYieldOutParameter.getFullPrice());
                        // 全价总额==>到期收益率
                        yieldPriceInParameter.setFullPrice(dto.getFpriceAmt());
                        yieldPriceOutParameter = yieldByPriceCalculator.calculate(
                                yieldPriceInParameter);
                        dto.setMatureYield(yieldPriceOutParameter.getMatYield());
                    }
                    break;
                default:
                    break;
            }
            // 到期收益率 或 行权收益率
            if (StringUtil.equals(dto.getCalflag(), "matureYield")
                    || StringUtil.equals(dto.getCalflag(), "exerYield")) {
                // 单位全价 = 全价总额 * 100 / 券面总额
                // dto.setUnitFprice(ArithmeticUtil.doDiv(
                // ArithmeticUtil.doMul(dto.getFpriceAmt(), new BigDecimal(100)),
                // dto.getPrinAmt(), 12));
                // 全价总额 = 单位全价 * 券面总额/100
                dto.setFpriceAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(dto.getUnitFprice(), dto.getEntrustNum()),
                        new BigDecimal(100), SCALE));
                // 单位净价 = 单位全价 - 单位应计利息
                dto.setPrice(ArithmeticUtil.doSub(dto.getUnitFprice(), dto.getUnitInt()));
                // 净价总额 = 单位净价 * 券面总额/100
//                dto.setCpriceAmt(ArithmeticUtil.divide(
//                    ArithmeticUtil.multiply(dto.getUnitCprice(), dto.getPrinAmt()),
//                    new BigDecimal(100), SCALE));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (dto.getMatureYield() != null) {
            dto.setMatureYield(ArithmeticUtil.multiply(dto.getMatureYield(), new BigDecimal(100)));
        }
        if (dto.getExerYield() != null) {
            dto.setExerYield(ArithmeticUtil.multiply(dto.getExerYield(), new BigDecimal(100)));
        }
    }

    /**
     * 转换数据
     *
     * @param date 日期
     * @return LocalDate
     * @see
     * @since 1.0
     */
    private LocalDate toLocalDate(Date date) {
        if (date != null) {
            return LocalDate.parse(DateUtil.format(date));
        }
        return null;
    }

    /**
     * 应计利息根据后台估值核算中前一个自然日的数据计算，等于后台的应计利息总额/后台的债券持仓总数量，
     * 如取不到估值核算中前一个自然日的数据，计算方式见算法文档；
     * 画面.应计利息=PTL_SEC_VALUTION.TDY_INTINCEXP/PTL_SEC_VALUTION.SHARE_AMT
     * （PTL_SEC_VALUTION.日期 = 画面.交割日-1 ，取不到，取最大日期对应数据）
     *
     * @param dto 参数
     * @return 应计利息
     * @see
     * @since 1.0
     */
    private void getUnitIntFromValution(TrdCommandsCollection002Dto dto) {
        // 应计利息根据后台估值核算中前一个自然日的数据计算，等于后台的应计利息总额/后台的债券持仓总数量，如取不到估值核算中前一个自然日的数据，计算方式见算法文档；
        // 画面.应计利息=PTL_SEC_VALUTION.TDY_INTINCEXP/PTL_SEC_VALUTION.SHARE_AMT  （PTL_SEC_VALUTION.日期 = 画面.交割日-1 ，取不到，取最大日期对应数据）
        Map<String, Object> paraMapForIntAmt = new HashMap<String, Object>();
        paraMapForIntAmt.put("settleDate", DateUtil.format(dto.getSettleDate()));
        paraMapForIntAmt.put("portfolioId", dto.getPortfolioId());
        paraMapForIntAmt.put("finprodId", dto.getFinprodId());
        paraMapForIntAmt.put("invAim", dto.getInvAim());
        paraMapForIntAmt.put("secAcctId", dto.getChlId());
        BigDecimal 应计利息 = restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getIntAmtFromValution",
                paraMapForIntAmt, BigDecimal.class);
        if (应计利息 != null) {
            dto.setUnitInt(应计利息);
        }
    }

    /**
     * 转换数据
     *
     * @param secInfoModelDto 债券基本信息SecInfoModelDto
     * @return 债券基本信息SecInfoModel
     * @see
     * @since 1.0
     */
    private SecInfoModel toSecInfoModel(SecInfoModelDto secInfoModelDto) {
        SecInfoModel secInfoModel = new SecInfoModel();

        secInfoModel.setStartDate(toLocalDate(secInfoModelDto.getStartDate()));
        secInfoModel.setEndDate(toLocalDate(secInfoModelDto.getEndDate()));
        secInfoModel.setFirstPayDate(toLocalDate(secInfoModelDto.getFirstPayDate()));
        // 利率类型
        if (StringUtil.equals(DictCodeList.利率类型_固定利率.getValue(),
                secInfoModelDto.getIntRateType())) {
            secInfoModel.setIntRateType(IntRateType.固定利率);
        }
        else {
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }

        if (StringUtil.isNotBlank(secInfoModelDto.getBasis())) {
            String basis = StringUtil.splitToList(GlobalConstant.COMMA, secInfoModelDto.getBasis()).get(0);
            DictCodeList basisDict = DictCodeList.from(DictCode.计息基础, basis);
            if (basisDict != null){
                switch (basisDict) {
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
                }
                if (basisDict.equals(DictCodeList.计息基础_A_A_平均分配)) {
                    secInfoModel.setAllocationType(AllocationType.平均分配);
                }
                else {
                    secInfoModel.setAllocationType(AllocationType.按实际天数分配);
                }
            }else {
                DictCodeListExt3 basisDict3 = DictCodeListExt3.from(DictCode.计息基础, basis);
                if (basisDict3 != null){
                    switch (basisDict3) {
                        case 计息基础_30U_360:
                            secInfoModel.setBasis(Basis._30U_360);
                            break;
                    }

                }
            }
        }
        DictCodeList bondsTypeDict = DictCodeList.from(DictCode.息票品种,
                secInfoModelDto.getBondsType());
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

        // 一体化对应etliu start
//        if (StringUtil.equals(DictCodeList.推算方法_起息日向后.getValue(),
        if (StringUtil.equals(DictCodeList.推算方式_起息日向后.getValue(),
                secInfoModelDto.getCalMethod())) {
            secInfoModel.setCalMethod(SecIntSchCalMethod.起息日向后);
        }
//        else if (StringUtil.equals(DictCodeList.推算方法_到期日向前.getValue(),
        else if (StringUtil.equals(DictCodeList.推算方式_到期日向前.getValue(),
                secInfoModelDto.getCalMethod())) {
            secInfoModel.setCalMethod(SecIntSchCalMethod.到期日向前);
        }
        else {
            secInfoModel.setCalMethod(SecIntSchCalMethod.首次付息日);
        }

//        if (StringUtil.equals(DictCodeList.支付频率_付息频率_月付.getValue(),
        if (StringUtil.equals(DictCodeList.频率_月.getValue(),
                secInfoModelDto.getYearIntRate())) {
            secInfoModel.setYearIntRate(IntFrequency.月付);
        }
//        else if (StringUtil.equals(DictCodeList.支付频率_付息频率_季付.getValue(),
        else if (StringUtil.equals(DictCodeList.频率_季.getValue(),
                secInfoModelDto.getYearIntRate())) {
            secInfoModel.setYearIntRate(IntFrequency.季付);
        }
//        else if (StringUtil.equals(DictCodeList.支付频率_付息频率_半年付.getValue(),
        else if (StringUtil.equals(DictCodeList.频率_半年.getValue(),
                // 一体化对应etliu end
                secInfoModelDto.getYearIntRate())) {
            secInfoModel.setYearIntRate(IntFrequency.半年付);
        }
        else {
            secInfoModel.setYearIntRate(IntFrequency.年付);
        }

        secInfoModel.setIssuePrice(secInfoModelDto.getIssuePrice());

        return secInfoModel;
    }

    public boolean isNotFomartSec(List<FinCashPlanDto> secIntScheduleList, SecInfoModelDto secInfoModelDto) {
        // 根据【实际起始日】排序付息计划
        Collections.sort(secIntScheduleList, new Comparator<FinCashPlanDto>() {
            @Override
            public int compare(FinCashPlanDto o1, FinCashPlanDto o2) {
                return o1.getVdateUnadjust().compareTo(o2.getVdateUnadjust());
            }
        });
        if (StringUtil.equals(DictCodeList.频率_月.getValue(),
                secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 1);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else if (StringUtil.equals(DictCodeList.频率_季.getValue(),
                secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 3);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else if (StringUtil.equals(DictCodeList.频率_半年.getValue(),
                secInfoModelDto.getYearIntRate())) {
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
}
