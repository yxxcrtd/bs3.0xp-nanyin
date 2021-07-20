/*
 * 文件名：EtrIbBondinfo002Service.java
 */

package com.joyintech.tams.invest.entrust.etribbondinfo.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.*;
//import com.joyintech.tams.invest.interf.b45.service.Send005Service;
//import com.joyintech.tams.invest.interf.b45.service.Send007Service;
//import com.joyintech.tams.invest.interf.b45.service.SendService;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.SecPrinSchedule;
import com.joyintech.calculation.bonds.constant.AllocationType;
import com.joyintech.calculation.bonds.constant.Basis;
import com.joyintech.calculation.bonds.constant.CurrIntCalMethod;
import com.joyintech.calculation.bonds.constant.DiscountScheme;
import com.joyintech.calculation.bonds.constant.FlowCashScheme;
import com.joyintech.calculation.bonds.constant.IntFrequency;
import com.joyintech.calculation.bonds.constant.IntRateType;
import com.joyintech.calculation.bonds.constant.NextIntCalMethod;
import com.joyintech.calculation.bonds.constant.SecIntSchCalMethod;
import com.joyintech.calculation.bonds.constant.Variety;
import com.joyintech.calculation.shell.AccurIntCalculator;
import com.joyintech.calculation.shell.AccurIntForSecheduleCalculator;
import com.joyintech.calculation.shell.PriceByYieldCalculator;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.AccurIntCalInParameter;
import com.joyintech.calculation.shell.param.AccurIntCalOutParameter;
import com.joyintech.calculation.shell.param.PriceYieldInParameter;
import com.joyintech.calculation.shell.param.PriceYieldOutParameter;
import com.joyintech.calculation.shell.param.SecInfoModel;
import com.joyintech.calculation.shell.param.YieldPriceInParameter;
import com.joyintech.calculation.shell.param.YieldPriceOutParameter;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.EtrIbBondinfo002Dto;
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.FinCashPlanDto;
import com.joyintech.tams.invest.entrust.etribbondinfo.dto.SecInfoModelDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.group.BasePtlSecPositionInfoService;


/**
 * 债券投资委托画面端service 〈功能详细描述〉
 */
@Service
public class EtrIbBondinfo002Service extends CommonExportService<EtrIbBondinfo002Dto> implements BaseProcessService<EtrIbBondinfo002Dto> {
    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * IPlatformDate
     */
    @Autowired(required = false)
    private IPlatformDate iPlatformDate;

    @Autowired
    private EtrCommonService etrCommonService;

    /**
     * 资产实际持仓
     */
    @Autowired
    private BasePtlSecPositionInfoService basePtlSecPositionInfoService;
    
    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    /**
     * b45接口
     */
//    @Autowired
//    private SendService sendService;
//
//    @Autowired
//    private Send005Service send005Service;
//
//    @Autowired
//    private Send007Service send007Service;

    /**
     * 获取新建界面的资产代码联想下拉框框
     *
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrIbBondinfo002/getfinprodIdForAdd"
                , param,
                SelectOptionDto.class);

    }

    /**
     * 判断是否含权债
     *
     * @param finprodId 金融产品代码
     * @see
     * @since 1.0
     */
    public boolean isRight(String finprodId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("finprodId", finprodId);

        return restTemplate.postForObject(
                ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/isRight", paramMap,
                Boolean.class);
    }

    /**
     * 校验方法 <功能详细描述>
     *
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrIbBondinfo002Dto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/check", dto,
                ValidMessage.class);
    }

    /**
     * 工作流完成方法
     */
    @Override
    public void doComplete(EtrIbBondinfo002Dto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate = DateUtil.platDateFromCache();
        dto.setEntrustDate(sysDate);
        dto.setTradeMarketMode(DictCodeListExt1.交易市场_二级市场.getValue());
        basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/doComplete", dto,
                Integer.class);
        //发送数据到b45
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("entrustId", dto.getEntrustId());
//        String ps = dto.getPs();
//
//        if (DictCodeList.交易方向_买入.getValue().equals(ps)
//                || DictCodeList.交易方向_卖出.getValue().equals(ps)) {
//            sendService.sendToB45(param, send005Service);
//        }
//        if (DictCodeList.交易方向_行权_回售.getValue().equals(ps)) {
//            sendService.sendToB45(param, send007Service);
//        }
    }

    @Override
    public void doDelete(String workflowId) {
        doDeleteFlow(workflowId);
    }

    private void doDeleteFlow(String workflowId) {
        Map<String, Object> param = new HashMap<>();
        param.put("workflowId", workflowId);
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/doDeleteEtrFlow", param,
                Boolean.class);
    }

    /**
     * 流程发起时调用
     */
    @Override
    public void doInsert(EtrIbBondinfo002Dto dto, OperationType type) {
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/doInsert", dto,
                Integer.class);
    }

    /**
     * 流程修改时调用
     */
    @Override
    public void doUpdate(EtrIbBondinfo002Dto dto) {
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/doUpdate", dto,
                Integer.class);
        if (!StringUtil.equals("revoke", dto.getOperateType()) && !StringUtil.equals("delete", dto.getOperateType())) {
            ProcessMark newEntity;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(EtrIbBondinfo002Dto.class, true);
                // 工作流ID
                newEntity.setWorkflowId(dto.getWorkflowId());
                newEntity.setStatus(dto.getStatus());
                newEntity.setBusinessData(JsonUtils.object2Json(dto));
                // 更新业务表数据
                // 操作留痕表
                WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改/双击查看详情查询信息 <功能详细描述>
     *
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrIbBondinfo002Dto selectByKey(String entrustId, String finprodId, String portfolioId) {
        if (!StringUtil.isNotEmpty(entrustId)) {
            entrustId = "";
        }
        if (!StringUtil.isNotEmpty(finprodId)) {
            finprodId = "";
        }
        if (!StringUtil.isNotEmpty(portfolioId)) {
            portfolioId = "";
        }
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrIbBondinfo002/selectByKey?entrustId=" + entrustId + "&finprodId=" + finprodId + "&portfolioId=" + portfolioId,
                EtrIbBondinfo002Dto.class);
    }

    /**
     * 可用面值计算
     * <功能详细描述>
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getAvailableNum(String finprodId) {
        Date sysDate = DateUtil.platDateFromCache();
        Map<String, Object> map = new HashMap<>();
        map.put("finprodId", finprodId);
        map.put("sysdate", DateUtil.format(sysDate));
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/getAvailableNum", map,
                BigDecimal.class);
    }

    /**
     * 净价、行权收益率、到期收益率联动
     * <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    public void calcAmt(EtrIbBondinfo002Dto dto) {

        if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_0.getValue())) {
            dto.setSettleSpeed((long) 0);
        }
        if (StringUtil.equals(dto.getSettleSpreed(),
                DictCodeList.清算速度_T_1.getValue())) {
            dto.setSettleSpeed((long) 1);
        }

        /**
         * 前台券面总额单位万元，计算时单位为元
         */
        dto.setEntrustAmt(dto.getEntrustNum().multiply(new BigDecimal(100)));

        if (dto == null || dto.getVdate() == null || dto.getMdate() == null
                || DateUtil.compareDate(dto.getVdate(), dto.getMdate()) != 0) {
            EtrIbBondinfo002Dto nullDto = new EtrIbBondinfo002Dto();
            try {
                nullDto.setPrice(dto.getPrice());
                nullDto.setExerYield(dto.getExerYield());
                nullDto.setMatureYield(dto.getMatureYield());
                BeanUtil.copyProperties(dto, nullDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        dto.setSettleDate(dto.getVdate());

        //2021-01-29,SettleSpreed改SettleSpeed
        if (StringUtil.isNotEmpty(dto.getSettleSpeed())) {
            if ((int) dto.getSettleSpeed() != 0) {
                List<String> holiday = dateCalService.getDealCalendarHoliday(dto.getVdate(),
                        DateUtil.addDays(dto.getVdate(), 31), dto.getCalendarId());
                Date tradingDay = dateCalService.calculationTradingDay(dto.getVdate(), (int) dto.getSettleSpeed(), holiday, "1");
                dto.setSettleDate(tradingDay);
            }
        }

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

            //交易所债券特殊处理start----------------------------------------------------------------
            Date tempSettleDate = dto.getSettleDate();
            // 如果交易场所为上交所、深交所的情况
            if (isJiaoYiSuo) {
                if (!StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())
                        && !StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_赎回.getValue())) {
                    tempSettleDate = DateUtil.addDay(tempSettleDate, 1);
                }
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
                Map<String, BigDecimal> intMap = new HashMap<>();
                if (null != invScheduleList && !invScheduleList.isEmpty()) {
                    for (SecIntSchedule intSche : invScheduleList) {
                        intMap.put(intSche.getActualCutoffDate().toString(), intSche.getAmount());
                    }
                }

                if (intMap.containsKey(accurIntCalInParameter.getCalDate().toString()) &&
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
            } else {
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
                } else {
                    dto.setUnitInt(ArithmeticUtil.divide(ArithmeticUtil.multiply(accurInt, curSecAmt), new BigDecimal(100),
                            Integer.parseInt(StringUtils.isEmpty(CacheUtil.getSysParam(
                                    "SEC_INT_SCALE_BANK")) ? "12" : CacheUtil.getSysParam(
                                    "SEC_INT_SCALE_BANK")), RoundingMode.HALF_UP));
                }
            }
        }
        try {
            // 单位净价
            if (StringUtil.equals(dto.getCalflag(), "price")) {
                // 单位全价 = 单位净价 + 单位应计利息
                dto.setUnitFprice(ArithmeticUtil.doAdd(dto.getPrice(), dto.getUnitInt()));
                // 全价总额 = 单位全价 * 券面总额/100
                dto.setFpriceAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(dto.getUnitFprice(), dto.getEntrustNum()),
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
                        } else {
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
                        } else {
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
                dto.setFpriceAmt(ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(dto.getUnitFprice(), dto.getEntrustNum()),
                        new BigDecimal(100), SCALE));
                // 单位净价 = 单位全价 - 单位应计利息
                dto.setPrice(ArithmeticUtil.doSub(dto.getUnitFprice(), dto.getUnitInt()));
            }
        } catch (Exception e) {
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
        } else {
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }

        if (StringUtil.isNotBlank(secInfoModelDto.getBasis())) {
            String basis = StringUtil.splitToList(GlobalConstant.COMMA, secInfoModelDto.getBasis()).get(0);
            DictCodeList basisDict = DictCodeList.from(DictCode.计息基础, basis);
            if (basisDict != null) {
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
                } else {
                    secInfoModel.setAllocationType(AllocationType.按实际天数分配);
                }
            } else {
                DictCodeListExt3 basisDict3 = DictCodeListExt3.from(DictCode.计息基础, basis);
                if (basisDict3 != null) {
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
        } else {
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
        } else {
            secInfoModel.setYearIntRate(IntFrequency.年付);
        }

        secInfoModel.setIssuePrice(secInfoModelDto.getIssuePrice());

        return secInfoModel;
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
    private void getUnitIntFromValution(EtrIbBondinfo002Dto dto) {
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

    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        EtrIbBondinfo002Dto dto = this.getBussinessEntity(entity);
        CommonBusinessService commonBusinessService = SpringContextUtil.getBean(CommonBusinessService.class);
        if (taskId != null) {
            try {
                if (dto.isImportFlow()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put(BussinessConstant.是否需要上级审批, true);
                    return param;
                }
                BigDecimal amount = dto.getEntrustNum();
                if (amount == null) {
                    amount = dto.getEntrustAmt();
                }
                Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), dto.getEntrustNum(), LoginUserUtil.getUserName(),
                        BussinessDictCodeList.平安资产类型_债券.getValue(), dto.getAssetType1(), dto.getTradeMarket(), dto.getPs()
                        , dto.getFinprodId(), entity.getWorkflowId()
                );
                // add by zhangrui1 风险审批修改 start
                params.put("riskTrialRetDto", dto.getRiskTrialRetDto());
                // add by zhangrui1 风险审批修改 end
                return commonBusinessService.是否需要上级审批_委托用(params);
            } catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        } else {
            return null;
        }
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
        } else if (StringUtil.equals(DictCodeList.频率_季.getValue(),
                secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 3);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        } else if (StringUtil.equals(DictCodeList.频率_半年.getValue(),
                secInfoModelDto.getYearIntRate())) {
            for (FinCashPlanDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 6);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        } else {
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
     * 查询行权价
     *
     * @author wangguiqi
     */
    public BigDecimal getExercisePricebyPara(Map<String, Object> paramMap) {
        paramMap.put("sysdate", iPlatformDate.getSysDateStr());
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/getExercisePricebyPara", paramMap,
                BigDecimal.class);
    }

    @Override
    public void doBack(EtrIbBondinfo002Dto entity) {
//    	baseEtrCashPositionCommService.entrustCloseExecute(entity.getEntrustId());
        restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrIbBondinfo002/doBack", entity,
                Integer.class);
    }
}
