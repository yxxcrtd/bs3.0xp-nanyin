package com.joyintech.tams.model.valuation.batch.binarytreemodel.service;

import com.joyintech.calculation.CalculatorFactory;
import com.joyintech.calculation.bonds.PaperIrReset;
import com.joyintech.calculation.bonds.SecIntSchedule;
import com.joyintech.calculation.bonds.SecPrinSchedule;
import com.joyintech.calculation.bonds.cash.ForwadCashCalculator;
import com.joyintech.calculation.bonds.constant.*;
import com.joyintech.calculation.shell.AccurIntForBookCalculator;
import com.joyintech.calculation.shell.YieldByPriceCalculator;
import com.joyintech.calculation.shell.param.*;
import com.joyintech.calculation.util.BasicUtil;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ModCurveResult;
import com.joyintech.tams.common.base.entity.ModRiskPremium;
import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dao.BinaryTreeModelMapper;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.BinaryTreeModelReaderEntity;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.BinaryTreeModelResultDto;
import com.joyintech.tams.model.valuation.batch.calculation.entity.KeyPointDto;
import com.joyintech.tams.model.valuation.batch.calculation.shell.calculator.TargetRateCalculator;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.inputparam.TargetRateInputParameter;
import com.joyintech.tams.model.valuation.batch.calculation.shell.param.outparam.TargetRateOutputParameter;
import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.MstBondQuotationInfoDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.CommonErrorParams;
import com.joyintech.tams.valuation.common.dto.modelDto.*;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BinaryTreeModelProcessorService {
    /**
     * 应收款流水处理Mapper
     */
    @Autowired
    private BinaryTreeModelMapper binaryTreeModelMapper;

    @Autowired
    private ModLogsService modLogsService;

    private static final Logger logger = LoggerFactory.getLogger(BinaryTreeModelProcessorService.class);
    /**
     * 二叉树模型-reader
     *
     * @param params
     * @return
     */
    public BinaryTreeModelResultDto excute(BinaryTreeModelReaderEntity entity, Map<String, Object> params, StepExecution stepExecution) {
        //封装最终计算结果
        BinaryTreeModelResultDto result = new BinaryTreeModelResultDto();

        //2.处理参数
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        //变量.估值日 = 批处理任务执行信息.发生日
        Date platDate = (Date) params.get("PLATE_DATE");


        //3.取Job上下文的错误日志信息，并增加新得错误信息
        boolean check = checkParam(entity, stepExecution, batchExecuteInfo);
        if(!check) return result;//check不通过，不做后续计算

        //初始化封装计算的资产估值结果
        ModCurveResult modCurveResult = new ModCurveResult();
        ModRiskPremium modRiskPremium = new ModRiskPremium();
        MstBondQuotationInfoDto mstBondQuoTation = new MstBondQuotationInfoDto();
//        result.setModCurveResult(modCurveResult);
//        result.setModRiskPremium(modRiskPremium);

        //4.计算波动率
        //变量.股票收盘价序列列表 = 资产信息.股票行情
        BigDecimal stockVolatility = calaStockVolatility(entity.getModStockMarketInfoList());

        //5.计算二叉树步长
        // 计算剩余期限
        // 推算计算日前的完整计息年度的个数，以及计算日所在计息年度的年化剩余期限
        ForwadCashCalculator forwadCashCalculator = CalculatorFactory.createForwadCashCalculator();
        BigDecimal encashYears = forwadCashCalculator.calcTerm(BasicUtil.dateToLocal(platDate),
                BasicUtil.dateToLocal(entity.getMdate()), BasicUtil.dateToLocal(entity.getVdate()),
                BasicUtil.dateToLocal(entity.getMdate()), null, SecIntSchCalMethod.起息日向后);
        //变量.步长  = 变量.年化期限/资产信息.二叉树步数
        BigDecimal binaryTreeLength = ArithmeticUtil.divide(encashYears, entity.getModBintreeConfig().getBintreeStep(), 4, RoundingMode.HALF_UP);

        //6.计算二叉树上升倍数和下降倍数
        //变量.上升倍数 = Math.pow（E,sqrt(变量.步长，1/变量.标的股票波动率)）
        BigDecimal upNum = BigDecimal.valueOf(Math.pow(Math.E, ArithmeticUtil.multiply(stockVolatility, BigDecimal.valueOf(Math.sqrt(binaryTreeLength.doubleValue()))).doubleValue()));
        //变量.下降倍数 = 1/变量.上升倍数
        BigDecimal downNum = ArithmeticUtil.divide(BigDecimal.ONE, upNum, 12, RoundingMode.HALF_UP);

        //7.转股期限
        //变量.转股期限 = 0
        BigDecimal stockLimit = BigDecimal.ZERO;
        //IF 转股起始日 > 跑批日
        if (entity.getConVdate().compareTo(platDate) > 0) {
            //	变量.转股期限 = （资产信息.转股起始日 - 变量.估值日）/变量.计息基础天数/变量.步长
            BigDecimal dateNum = forwadCashCalculator.calcTerm(BasicUtil.dateToLocal(platDate),
                    BasicUtil.dateToLocal(entity.getConVdate()), BasicUtil.dateToLocal(entity.getVdate()),
                    BasicUtil.dateToLocal(entity.getConVdate()), null, SecIntSchCalMethod.起息日向后);
            stockLimit = ArithmeticUtil.divide(dateNum, binaryTreeLength, 0, RoundingMode.HALF_UP);
        }

        //8.生成二叉树
        BigDecimal rootStockPrice = BigDecimal.ZERO;
        if (ModelDictCodeList.模型_上市状态_上市.getValue().equals(entity.getModBintreeConfig().getStockBordsttus())) {
            rootStockPrice = entity.getModStockMarketInfoList().get(entity.getModStockMarketInfoList().size() - 1).getClosePrice();
        } else if (ModelDictCodeList.模型_上市状态_未上市.getValue().equals(entity.getModBintreeConfig().getStockBordsttus())
                && ModelDictCodeList.模型_市场乘数方法_市盈率法.getValue().equals(entity.getModBintreeConfig().getMarketMulti())) {
            //变量.根节点股票价格 = 变量.股票收盘价序列列表.get(size()-1).市盈率*资产信息.标的公司每股收益
            rootStockPrice = entity.getModStockMarketInfoList().get(entity.getModStockMarketInfoList().size() - 1).getPe();
            rootStockPrice = ArithmeticUtil.multiply(rootStockPrice, entity.getModBintreeConfig().getFinprodProit());
        } else if (ModelDictCodeList.模型_上市状态_未上市.getValue().equals(entity.getModBintreeConfig().getStockBordsttus())
                && ModelDictCodeList.模型_市场乘数方法_市净率法.getValue().equals(entity.getModBintreeConfig().getMarketMulti())) {
            //变量.根节点股票价格 = 变量.股票收盘价序列列表.get(size()-1).市净率*资产信息.标的公司每股净资产
            rootStockPrice = entity.getModStockMarketInfoList().get(entity.getModStockMarketInfoList().size() - 1).getPb();
            rootStockPrice = ArithmeticUtil.multiply(rootStockPrice, entity.getModBintreeConfig().getFinprodNetasset());
        }

        //9.调用【二叉树生成】
        StockInfoDto[][] binTree = createBinaryTree(entity.getModBintreeConfig().getBintreeStep(), rootStockPrice, upNum, downNum, stockLimit, entity.getConPrice());

        //10.判定最终节点总价值
        quaryFinalAllValue(binTree, entity);

        //11.中间节点的总价值
        quaryMidAllValue(binTree, entity, upNum, downNum, platDate, binaryTreeLength, encashYears, modRiskPremium);
        //12.估值全价
        //变量.估值全价 = 变量.二叉树二维数据[0][0].总价值
        BigDecimal valueAllPrice = binTree[0][0].getAllValue().setScale(8, RoundingMode.HALF_UP);
        //13.调用【算法包.计算百元面值的债券的利息-记账用】
        BigDecimal interest = getCalculate(entity, platDate).getInterest();
        //14.变量.估值净价 = 变量.估值全价 - 变量.单位应计利息
        BigDecimal valueNetPrice = ArithmeticUtil.subtract(valueAllPrice, interest).setScale(8, RoundingMode.HALF_UP);

        //封装结果
        modCurveResult.setSeqno(IDGenerator.generate());
        modCurveResult.setFinprodId(entity.getFinprodId());
        modCurveResult.setCdate(platDate);
        modCurveResult.setValModel(ModelDictCodeList.模型_估值模型名称_二叉树.getValue());
        modCurveResult.setFullPrice(valueAllPrice);
        modCurveResult.setNetPrice(valueNetPrice);

        modRiskPremium.setSeqno(IDGenerator.generate());
        modRiskPremium.setSecTypeModel(DictCodeListExt1.证券品种_债券.getValue());
        modRiskPremium.setFinprodId(entity.getFinprodId());
        modRiskPremium.setFinprodName(entity.getFinprodAbbr());
        modRiskPremium.setModMethod(ModelDictCodeList.模型_估值模型名称_二叉树.getValue());


        mstBondQuoTation.setSecId(entity.getFinprodMarketId()); //市场代码
        mstBondQuoTation.setValueDate(platDate);//估值日
        mstBondQuoTation.setValueSource(DictCodeListExt1.估值来源_模型法_MOD.getValue());//估值来源
        mstBondQuoTation.setPrice(valueAllPrice); //估值全价
        mstBondQuoTation.setNetprice(valueNetPrice);//估值净价
        mstBondQuoTation.setInputType(DictCodeListExt1.录入方式_批处理生成.getValue()); //录入方式 待定 测试 interf
        mstBondQuoTation.setCreateUser("batch"); //
        mstBondQuoTation.setUpdateUser("batch");
        mstBondQuoTation.setCreateTime(DateUtil.now());
        mstBondQuoTation.setUpdateTime(DateUtil.now());

        result.setModCurveResult(modCurveResult);
        result.setModRiskPremium(modRiskPremium);
        result.setMstBondQuoTation(mstBondQuoTation);
        return result;
    }


    /**
     * 校验参数
     * @param entity
     * @param stepExecution
     * @param batchExecuteInfo
     * @return
     */
    private boolean checkParam(BinaryTreeModelReaderEntity entity, StepExecution stepExecution, SysBatchExecInfo batchExecuteInfo) {
        boolean checkResult = true;
        List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get("ERRORS");
        if (errors == null) {
            errors = new ArrayList<CommonErrorParams>();
        }
        //【批处理名称】：未配置二叉树模型参数
        if (entity.getModBintreeConfig() == null || StringUtil.isEmpty(entity.getModBintreeConfig().getFinprodId())) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entity.getFinprodId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【二叉树模型批处理】：未配置二叉树模型参数");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            checkResult = false;
        }
        //【批处理名称】：当天无股票行情
        if (entity.getModStockMarketInfoList() == null || entity.getModStockMarketInfoList().size() == 0) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entity.getFinprodId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【二叉树模型批处理】：当天无股票行情");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            checkResult = false;
        }
        //【批处理名称】：当天无收益率曲线行情
        if (entity.getModYieldCurveInfoList() == null || entity.getModYieldCurveInfoList().size() == 0) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entity.getFinprodId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【二叉树模型批处理】：当天无收益率曲线行情");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_警告.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            checkResult = false;
        }
        return checkResult;
    }

    public AccurIntCalOutParameter getCalculate(BinaryTreeModelReaderEntity entity, Date platDate) {
        // 组装参数
        AccurIntCalInParameter parameter = getCalInParam(entity, platDate);
        // 计算百元面值的债券的利息
        AccurIntForBookCalculator calculator2 = CalculatorFactory.createAccurIntForBookCalculator();
        AccurIntCalOutParameter output2 = calculator2.calculate(parameter);
        output2.setInterest(ArithmeticUtil.divide(ArithmeticUtil.multiply(output2.getInterest(), parameter.getPaperAmt()), new BigDecimal(100), 12, RoundingMode.HALF_UP));
        return output2;
    }

    /**
     * 算法包.计算百元面值的债券的利息-记账用传参封装
     * @param entity
     * @param platDate
     * @return
     */
    private AccurIntCalInParameter getCalInParam(BinaryTreeModelReaderEntity entity, Date platDate) {
        //变量.现金流计算规则  = 资产信息.现金流计算规则.get（0）
        ModCashFlowCalRuleDto modCashFlowCalRule = entity.getModCashFlowCalRuleList().get(0);
        //变量.现金流支付规则  = 资产信息.现金流支付规则.get（0）
        ModCashFlowPayRulesDto modCashFlowPayRule = entity.getModCashFlowPayRulesList().get(0);
        AccurIntCalInParameter parameter = new AccurIntCalInParameter();
        // 计算日
        parameter.setCalDate(BasicUtil.dateToLocal(platDate));
        // 资产信息
        SecInfoModel secInfoModel = new SecInfoModel();
        // 债券起息日
        secInfoModel.setStartDate(BasicUtil.dateToLocal(entity.getVdate()));
        // 债券到期日
        secInfoModel.setEndDate(BasicUtil.dateToLocal(entity.getMdate()));
        // 债券首次付息日
        secInfoModel.setFirstPayDate(BasicUtil.dateToLocal(modCashFlowPayRule.getFirstPayDate()));
        // 发行价
        secInfoModel.setIssuePrice(entity.getIssuePrice());
        // 债券类型
        Variety bondsType = null;
        if (DictCodeList.息票品种_贴现.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.贴现;
        }
        else if (DictCodeList.息票品种_零息.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.零息;
        }
        else if (DictCodeList.息票品种_附息.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.附息;
        }
        else if (DictCodeList.息票品种_利随本清.getValue().equals(entity.getCouponSpecies())) {
            bondsType = Variety.利随本清;
        }
        secInfoModel.setBondsType(bondsType);
        // 计息基础
        Basis basis = null;
        String basisString = modCashFlowCalRule.getBasis();
        if (DictCodeList.计息基础_A_360.getValue().equals(basisString)) {
            basis = Basis.A_360;
        }
        else if (DictCodeList.计息基础_A_365.getValue().equals(basisString)) {
            basis = Basis.A_365;
        }
        else if (DictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)
                || DictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            basis = Basis.A_A;
        }
        else if (DictCodeList.计息基础_A_365F.getValue().equals(basisString)) {
            basis = Basis.A_365F;
        }
        else if (DictCodeList.计息基础_30_360.getValue().equals(basisString)) {
            basis = Basis._30_360;
        }
//        else if (DictCodeList.计息基础_30U_360.getValue().equals(basisString)) {
//            basis = Basis._30U_360;
//        }

        secInfoModel.setBasis(basis);
        // 利率类型
        IntRateType intRateType = IntRateType.固定利率;
        secInfoModel.setIntRateType(intRateType);
        // 利息分配方式
        AllocationType allocationType = null;
        if (DictCodeList.计息基础_A_A_平均分配.getValue().equals(basisString)) {
            allocationType = AllocationType.平均分配;
        }
        else if (DictCodeList.计息基础_A_A_按实际天数分配.getValue().equals(basisString)) {
            allocationType = AllocationType.按实际天数分配;
        }
        else {
            allocationType = AllocationType.按实际天数分配;
        }
        secInfoModel.setAllocationType(allocationType);
        // 付息计划推算方式
        SecIntSchCalMethod secIntSchCalMethod = null;
        String secIntSchCalMethodString = modCashFlowPayRule.getCalcRule();
        if (DictCodeList.推算方式_起息日向后.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.起息日向后;
        }
        else if (DictCodeList.推算方式_到期日向前.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.到期日向前;
        }
        else if (DictCodeList.推算方式_首次支付日.getValue().equals(secIntSchCalMethodString)) {
            secIntSchCalMethod = SecIntSchCalMethod.首次付息日;
        }
        secInfoModel.setCalMethod(secIntSchCalMethod);
        // 付息频率
        IntFrequency yearIntRate = null;
        String yearIntRateString = modCashFlowPayRule.getPayCycle();
        if (DictCodeList.频率_月.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.月付;
        }
        else if (DictCodeList.频率_季.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.季付;
        }
        else if (DictCodeList.频率_年.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.年付;
        }
        else if (DictCodeList.频率_半年.getValue().equals(yearIntRateString)) {
            yearIntRate = IntFrequency.半年付;
        }
        else {
            yearIntRate = IntFrequency.年付;
        }
        secInfoModel.setYearIntRate(yearIntRate);
        parameter.setSecInfoModel(secInfoModel);

        // 付息计划列表
        List<SecIntSchedule> invScheduleList = new ArrayList<>();
        List<ModCashFlowDto> intSchList = new ArrayList<ModCashFlowDto>();
        for (ModCashFlowDto item : entity.getModCashFlowList()) {
            if (StringUtil.equals(item.getCashType2(), DictCodeListExt1.现金流二级类型_利息.getValue())
                    && item.getVdateUnadjust() != null) {
                SecIntSchedule schedule = new SecIntSchedule();
                schedule.setTheoryStartDate(toLocalDate(item.getVdateUnadjust()));
                schedule.setTheoryCutoffDate(toLocalDate(item.getMdateUnadjust()));
                schedule.setActualStartDate(toLocalDate(item.getVdate()));
                schedule.setActualCutoffDate(toLocalDate(item.getMdate()));
                schedule.setAmount(item.getIntAmt());
                schedule.setCorpus(new BigDecimal(100));
                invScheduleList.add(schedule);
                intSchList.add(item);
            }
        }
        parameter.setSecIntScheduleList(invScheduleList);

        // 债券利率重置信息
        List<PaperIrReset> paperIrResetList = entity.getModCashFlowCalRuleList().stream().map(item -> {
            PaperIrReset ret = new PaperIrReset();
            // BeanUtil.copyPropertiesIgnoreNull(ret, item);
            ret.setBaseRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            ret.setParRate(item.getYield());
            ret.setParRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            return ret;
        }).collect(Collectors.toList());
        // 利率重置信息按照票面利率生效日排序
        Collections.sort(paperIrResetList, new Comparator<PaperIrReset>() {
            @Override
            public int compare(PaperIrReset o1, PaperIrReset o2) {
                return o1.getParRateEffDate().compareTo(o2.getParRateEffDate());
            }
        });
        if (paperIrResetList != null && paperIrResetList.size() > 0) {
            paperIrResetList.get(0).setBaseRateEffDate(BasicUtil.dateToLocal(entity.getVdate()));
        }
        if (paperIrResetList != null && paperIrResetList.size() > 1) {
            // 多重利率则计算按浮动利率算
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }
        parameter.setPaperIrResetList(paperIrResetList);

        // 当前面值
        BigDecimal currentValue = getCurrentValue(entity, platDate);
        parameter.setPaperAmt(currentValue);
        return parameter;
    }

    /**
     * 获取当前面值
     * @param entity
     * @param platDate
     * @return
     */
    private BigDecimal getCurrentValue(BinaryTreeModelReaderEntity entity, Date platDate) {
        BigDecimal currentValue = BigDecimal.valueOf(100);
        //已还本面值
        BigDecimal printValue = BigDecimal.ZERO;
        for (ModCashFlowDto dto : entity.getModCashFlowList()){
            //If 明细.现金流类型 = 数据字典.现金流类型.中间还本 or 到期还本 and 明细.调整后的计算结束日 <= 跑批日
            if (StringUtil.equals(dto.getCashType2(), DictCodeListExt1.现金流二级类型_本金.getValue())
                    && dto.getMdate().compareTo(platDate) <= 0) {
                //变量.已还本面值 = 变量.已还本面值 + 明细.计划支付本金
                printValue = ArithmeticUtil.doAdd(printValue, dto.getPrinAmt());
            }
        }
        //变量.当前面值 = 变量.当前面值 - 变量.已还本面值
        currentValue = ArithmeticUtil.subtract(currentValue, printValue);
        return currentValue;
    }
    /**
     * 中间节点的总价值
     * @param binTree
     * @param entity
     * @param upNum
     * @param downNum
     * @param platDate
     * @param binaryTreeLength
     */
    private void quaryMidAllValue(StockInfoDto[][] binTree, BinaryTreeModelReaderEntity entity, BigDecimal upNum,
                                  BigDecimal downNum, Date platDate, BigDecimal binaryTreeLength, BigDecimal encashYears, ModRiskPremium premium) {
        //变量.现金流支付规则  = 资产信息.现金流支付规则.get（0）
        ModCashFlowPayRulesDto modCashFlowPayRule = entity.getModCashFlowPayRulesList().get(0);
        //变量.现金流计算规则  = 资产信息.现金流计算规则.get（0）
        ModCashFlowCalRuleDto modCashFlowCalRule = entity.getModCashFlowCalRuleList().get(0);

        //7.1 估值日的无风险利率
        //变量.估值日收益率曲线 = 资产信息.曲线行情
        List<ModYieldCurveInfoDto> yieldList = entity.getModYieldCurveInfoList();
        //变量.买入全价 =0
        BigDecimal buyAllPrice = BigDecimal.ZERO;
        //变量.当天是否存在买入交易 = 数据字典.是否.否
        String todayBuyFlg = ModelDictCodeList.是否_否.getValue();
        //变量.基准日
        Date baseDate = null;
        //插值计算
        TargetRateInputParameter input = new TargetRateInputParameter();
        input.setTargetTerm(encashYears);
        BigDecimal cdateYield = BigDecimal.ZERO;
        if (yieldList != null && yieldList.size() > 0) {
            List<KeyPointDto> keyPointDtoList = new ArrayList<>();
            for (ModYieldCurveInfoDto dto : yieldList) {
                KeyPointDto keyPointDto = new KeyPointDto();
                keyPointDto.setKeyPoint(dto.getKeyPoint());
                keyPointDto.setYield(dto.getYield());
                keyPointDtoList.add(keyPointDto);
            }
            input.setKeyPointDtoList(keyPointDtoList);
            TargetRateCalculator trCalculator = com.joyintech.tams.model.valuation.batch.calculation.CalculatorFactory.createTargetRateCalculator();
            TargetRateOutputParameter troutput = trCalculator.calculate(input);
            //变量.估值日无风险利率 = 返回.收益率
            cdateYield = troutput.getTargerYield();
        }
//        if (cdateYield != null) {
//            logger.info("估值日无风险利率：" + cdateYield.doubleValue());
//        }
        //7.2 计算股价上升的概率和下降的概率
        //变量.估价上升概率 =（ pow（E，（变量.估值日无风险利率-变量.股票连续股息率）*变量.步长）-变量.下降倍数）/(变量.上升倍数 - 变量.下降倍数)
        BigDecimal upProbability = ArithmeticUtil.multiply(ArithmeticUtil.subtract(cdateYield, entity.getModBintreeConfig().getSmoothsection()), binaryTreeLength);
        upProbability = ArithmeticUtil.subtract(BigDecimal.valueOf(Math.pow(Math.E, upProbability.doubleValue())), downNum);
        upProbability = ArithmeticUtil.divide(upProbability, ArithmeticUtil.subtract(upNum, downNum), 12, RoundingMode.HALF_UP);

        //变量.估价下降概率 = 1- 变量.估计上升概率
        BigDecimal downProbability = ArithmeticUtil.subtract(BigDecimal.ONE, upProbability);
//        if (upProbability != null) {
//            logger.info("估价上升概率：" + upProbability.doubleValue());
//            logger.info("估价下降概率：" + downProbability.doubleValue());
//        }
        //7.3 估值日债券到期收益率计算
        //①到期收益率计算
        //IF 资产信息.最近买入日 = 跑批日
        if (platDate.equals(entity.getLastTradeInDate())) {
            //	变量.当天是否存在买入交易 = 是
            todayBuyFlg = ModelDictCodeList.是否_是.getValue();
        }
        if (ModelDictCodeList.是否_是.getValue().equals(todayBuyFlg)) {
            //变量.买入全价 = 资产信息.最近买入交易日交易均价
            buyAllPrice = entity.getLastTradeAvgPrice();
            //变量.基准日 = 资产信息.最近买入交易发生日
            baseDate = entity.getLastTradeInDate();
        } else {
            //资产定价数据查询
            Map<String, Object> params = new HashMap<>();
            params.put("finprodId", entity.getFinprodId());
            params.put("baseDate", platDate);
            params.put("modMethod", ModelDictCodeList.模型_估值模型名称_二叉树.getValue());
            ModRiskPremium modRiskPremium = binaryTreeModelMapper.findModRiskPremium(params);
            if (modRiskPremium != null) {
                //变量.买入全价 = 返回.资产定价数据.买入全价
                buyAllPrice = modRiskPremium.getbFullprice();
                //变量.基准日 = 返回.资产定价数据.基准日
                baseDate = modRiskPremium.getBasedate();
            } else {
                //变量.买入全价 = 资产信息.最近买入交易日交易均价
                buyAllPrice = entity.getLastTradeAvgPrice();
                //变量.基准日 = 资产信息.最近买入交易发生日
                baseDate = entity.getLastTradeInDate();
            }
        }
        //【算法包到期收益率计算】
        //变量.到期收益率折现前 = 返回.到期收益率
        BigDecimal yieldByPriceBefore = calaYieldByPriceCalculator(entity, buyAllPrice, baseDate).getMatYield();
//        if (yieldByPriceBefore != null) {
//            logger.info("到期收益率折现前：" + yieldByPriceBefore.doubleValue());
//        }

        //②支付频率
        int calcRule = 0;
        if (StringUtil.equals(DictCodeList.频率_月.getValue(), modCashFlowPayRule.getPayCycle())) {
            calcRule = 12;
        } else if (StringUtil.equals(DictCodeList.频率_季.getValue(), modCashFlowPayRule.getPayCycle())) {
            calcRule = 4;
        } else if (StringUtil.equals(DictCodeList.频率_半年.getValue(), modCashFlowPayRule.getPayCycle())) {
            calcRule = 2;
        } else if (StringUtil.equals(DictCodeList.频率_年.getValue(), modCashFlowPayRule.getPayCycle())
                || StringUtil.equals(DictCodeList.频率_到期一次.getValue(), modCashFlowPayRule.getPayCycle())
                || StringUtil.equals(DictCodeList.频率_不规则.getValue(), modCashFlowPayRule.getPayCycle())) {
            calcRule = 1;
        }
        //变量.到期收益率 = 变量.到期收益率折现前 * Math.pow（Math.E, 1 + 变量.到期收益率/变量.频率）
        BigDecimal yieldByPrice = ArithmeticUtil.doAdd(BigDecimal.ONE, ArithmeticUtil.divide(yieldByPriceBefore, BigDecimal.valueOf(calcRule), 12, RoundingMode.HALF_UP));
        yieldByPrice = BigDecimal.valueOf(Math.log(yieldByPrice.doubleValue()));
        yieldByPrice = ArithmeticUtil.multiply(BigDecimal.valueOf(calcRule), yieldByPrice);
//        if (yieldByPrice != null) {
//            logger.info("到期收益率：" + yieldByPrice.doubleValue());
//        }
        //7.4 票面利率
        //	变量.票面利率 = 变量.现金流计算规则.生效利率
        BigDecimal yield = modCashFlowCalRule.getYield();

        //7.5 当前面值
        BigDecimal currentValue = getCurrentValue(entity, platDate);

        //7.6 中间节点面值计算
        int bintreeStep = entity.getModBintreeConfig().getBintreeStep().intValue();
        for (int n = bintreeStep - 1; n >= 0; n--) {
            for (int i = bintreeStep - 1; i >= 0; i--) {
                if (i > n) {
                    continue;
                }
                if (binTree[n][i].getStockFlg() == 1) {
                    //变量.二叉树二维数据[n][i].股权价值 = 变量.二叉树二维数据[n][i].股票价格
                    binTree[n][i].setStockValue(binTree[n][i].getStockPrice());
                } else if (binTree[n][i].getStockFlg() == 0) {
                    //变量.二叉树二维数据[n][i].债权价值 = 变量.当前面值*（pow（Math.E，变量.票面利率* 变量.步长））+变量.二叉树二维数据[n+1][i].债权价值 *变量.股价上升概率*pow（Math.E，-1*变量.到期收益率* 变量.步长）+
                    //变量.二叉树二维数据[n+1][i+1].债权价值 *变量.股价下降概率*pow（Math.E，-1*变量.到期收益率* 变量.步长）
                    BigDecimal val1 = ArithmeticUtil.multiply(currentValue, ArithmeticUtil.subtract(BigDecimal.valueOf(Math.exp(ArithmeticUtil.multiply(yield, binaryTreeLength).doubleValue())), BigDecimal.ONE));
                    BigDecimal val2 = ArithmeticUtil.multiply(binTree[n+1][i].getBondValue(), upProbability, BigDecimal.valueOf(Math.exp(ArithmeticUtil.multiply(BigDecimal.valueOf(-1), yieldByPrice, binaryTreeLength).doubleValue())));
                    BigDecimal val3 = ArithmeticUtil.multiply(binTree[n+1][i+1].getBondValue(), downProbability, BigDecimal.valueOf(Math.exp(ArithmeticUtil.multiply(BigDecimal.valueOf(-1), yieldByPrice, binaryTreeLength).doubleValue())));
                    binTree[n][i].setBondValue(ArithmeticUtil.doAdd(val1, val2, val3).setScale(12, RoundingMode.HALF_UP));

                    //变量.二叉树二维数据[n][i].股权价值 = 变量.二叉树二维数据[n+1][i].股权价值 *变量.股价上升概率*pow（Math.E，-1*变量.估值日无风险利率* 变量.步长）+
                    //变量.二叉树二维数据[n+1][i+1].股权价值 *变量.股价下降概率*pow（Math.E，-1*变量.估值日无风险利率* 变量.步长）
                    BigDecimal sto1 = ArithmeticUtil.multiply(binTree[n+1][i].getStockValue(), upProbability, BigDecimal.valueOf(Math.exp(ArithmeticUtil.multiply(BigDecimal.valueOf(-1), cdateYield, binaryTreeLength).doubleValue())));
                    BigDecimal sto2 = ArithmeticUtil.multiply(binTree[n+1][i+1].getStockValue(), downProbability, BigDecimal.valueOf(Math.exp(ArithmeticUtil.multiply(BigDecimal.valueOf(-1), cdateYield, binaryTreeLength).doubleValue())));
                    binTree[n][i].setStockValue(ArithmeticUtil.doAdd(sto1, sto2).setScale(12, RoundingMode.HALF_UP));

                }
                if (n == 0 && i == 0 && binTree[n][i].getStockValue() != null) {
                    BigDecimal stockValue = binTree[n][i].getStockValue();
                    stockValue = ArithmeticUtil.multiply(stockValue, ArithmeticUtil.divide(currentValue, entity.getConPrice(), 12, RoundingMode.HALF_UP));
                    binTree[n][i].setStockValue(stockValue);
                }
                //变量.二叉树二维数据[n][i].总价值  = 变量.二叉树二维数据[n][i].股权价值 + 变量.二叉树二维数据[n][i].债权价值
                binTree[n][i].setAllValue(ArithmeticUtil.doAdd(binTree[n][i].getBondValue(), binTree[n][i].getStockValue()));

//                //测试信息打印
//                if (binTree[n][i].getStockValue() != null) {
//                    logger.info("股权价值["+ n + "][" + i + "]" + binTree[n][i].getStockValue().doubleValue());
//                }
//                if (binTree[n][i].getBondValue() != null) {
//                    logger.info("债权价值["+ n + "][" + i + "]" + binTree[n][i].getBondValue().doubleValue());
//                }
//                if (binTree[n][i].getAllValue() != null) {
//                    logger.info("总价值["+ n + "][" + i + "]" + binTree[n][i].getAllValue().doubleValue());
//                }
//                //测试信息打印
            }
        }
        //最终基准日
        premium.setBasedate(baseDate);
        //买入全价
        premium.setbFullprice(buyAllPrice);
    }


    /**
     * 算法包到期收益率计算
     * @param entity
     * @param buyAllPrice
     * @param baseDate
     * @return
     */
    private YieldPriceOutParameter calaYieldByPriceCalculator(BinaryTreeModelReaderEntity entity, BigDecimal buyAllPrice, Date baseDate) {
        //变量.现金流计算规则  = 资产信息.现金流计算规则.get（0）
        ModCashFlowCalRuleDto modCashFlowCalRule = entity.getModCashFlowCalRuleList().get(0);
        //变量.现金流支付规则  = 资产信息.现金流支付规则.get（0）
        ModCashFlowPayRulesDto modCashFlowPayRule = entity.getModCashFlowPayRulesList().get(0);

        YieldPriceInParameter yieldPriceInParameter = new YieldPriceInParameter();
        SecInfoModel secInfoModel = new SecInfoModel();

        secInfoModel.setStartDate(toLocalDate(entity.getVdate()));
        secInfoModel.setEndDate(toLocalDate(entity.getMdate()));
        secInfoModel.setFirstPayDate(toLocalDate(modCashFlowPayRule.getFirstPayDate()));
        // 利率类型
        secInfoModel.setIntRateType(IntRateType.固定利率);

        if (StringUtil.isNotBlank(modCashFlowCalRule.getBasis())) {
            String basis = modCashFlowCalRule.getBasis();
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
//            else if(StringUtil.equals(basis,DictCodeList.计息基础_30U_360.getValue())){
//                secInfoModel.setBasis(Basis._30U_360);
//            }
            if (StringUtil.equals(basis,DictCodeList.计息基础_A_A_平均分配.getValue())) {
                secInfoModel.setAllocationType(AllocationType.平均分配);
            }
            else {
                secInfoModel.setAllocationType(AllocationType.按实际天数分配);
            }
        }
        DictCodeList bondsTypeDict = DictCodeList.from(DictCode.息票品种, entity.getCouponSpecies());
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

        if (StringUtil.equals(DictCodeList.推算方式_起息日向后.getValue(), modCashFlowPayRule.getCalcRule())) {
            secInfoModel.setCalMethod(SecIntSchCalMethod.起息日向后);
        }
        else if (StringUtil.equals(DictCodeList.推算方式_到期日向前.getValue(), modCashFlowPayRule.getCalcRule())) {
            secInfoModel.setCalMethod(SecIntSchCalMethod.到期日向前);
        }
        else {
            secInfoModel.setCalMethod(SecIntSchCalMethod.首次付息日);
        }
        if (StringUtil.equals(DictCodeList.频率_月.getValue(), modCashFlowPayRule.getPayCycle())) {
            secInfoModel.setYearIntRate(IntFrequency.月付);
        }
        else if (StringUtil.equals(DictCodeList.频率_季.getValue(), modCashFlowPayRule.getPayCycle())) {
            secInfoModel.setYearIntRate(IntFrequency.季付);
        }
        else if (StringUtil.equals(DictCodeList.频率_半年.getValue(), modCashFlowPayRule.getPayCycle())) {
            secInfoModel.setYearIntRate(IntFrequency.半年付);
        }
        // 一体化对应etliu end
        else {
            secInfoModel.setYearIntRate(IntFrequency.年付);
        }

        secInfoModel.setIssuePrice(entity.getIssuePrice());

        //付息计划
        List<SecIntSchedule> invScheduleList = new ArrayList<>();
        List<ModCashFlowDto> intSchList = new ArrayList<ModCashFlowDto>();
        for (ModCashFlowDto item : entity.getModCashFlowList()) {
            if (StringUtil.equals(item.getCashType2(), DictCodeListExt1.现金流二级类型_利息.getValue())
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
        //还本计划
        List<SecPrinSchedule> secPrinScheduleList = new ArrayList<SecPrinSchedule>();
        for (ModCashFlowDto item : entity.getModCashFlowList()) {
            if (StringUtil.equals(item.getCashType2(), DictCodeListExt1.现金流二级类型_本金.getValue())) {
                SecPrinSchedule schedule = new SecPrinSchedule();
                schedule.setAmount(item.getIntAmt());
                schedule.setFlag(StringUtil.equals(item.getIsPayInt(), DictCodeList.是否_是.getValue()));
                schedule.setPrincipal(item.getPrinAmt());
                schedule.setInterestEndDate(toLocalDate(item.getMdate()));
                secPrinScheduleList.add(schedule);
            }
        }
        //利率信息
        List<PaperIrReset> paperIrResetList = entity.getModCashFlowCalRuleList().stream().map(item -> {
            PaperIrReset ret = new PaperIrReset();
            // BeanUtil.copyPropertiesIgnoreNull(ret, item);
            ret.setBaseRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            ret.setParRate(item.getYield());
            ret.setParRateEffDate(BasicUtil.dateToLocal(item.getEffDate()));
            return ret;
        }).collect(Collectors.toList());
        // 利率重置信息按照票面利率生效日排序
        Collections.sort(paperIrResetList, new Comparator<PaperIrReset>() {
            @Override
            public int compare(PaperIrReset o1, PaperIrReset o2) {
                return o1.getParRateEffDate().compareTo(o2.getParRateEffDate());
            }
        });
        if (paperIrResetList != null && paperIrResetList.size() > 0) {
            paperIrResetList.get(0).setBaseRateEffDate(BasicUtil.dateToLocal(entity.getVdate()));
        }
        if (paperIrResetList != null && paperIrResetList.size() > 1) {
            // 多重利率则计算按浮动利率算
            secInfoModel.setIntRateType(IntRateType.浮动利率);
        }
        yieldPriceInParameter.setSecInfoModel(secInfoModel);
        yieldPriceInParameter.setCalDate(toLocalDate(baseDate));

        yieldPriceInParameter.setSecIntScheduleList(invScheduleList);
        yieldPriceInParameter.setSecPrinScheduleList(secPrinScheduleList);
        yieldPriceInParameter.setPaperIrResetList(paperIrResetList);
        yieldPriceInParameter.setCashScheme(FlowCashScheme.付息计划推算计息年度);
        yieldPriceInParameter.setDisScheme(DiscountScheme.以剩余年限为单位);
        if (StringUtil.equals(DictCodeList.息票品种_附息.getValue(), entity.getCouponSpecies())) {
            if (isNotFomartSec(intSchList, modCashFlowPayRule)) {
                yieldPriceInParameter.setCashScheme(FlowCashScheme.理论付息区间);
                yieldPriceInParameter.setDisScheme(DiscountScheme.以付息区间为单位);
            }
            else {
                yieldPriceInParameter.setCashScheme(FlowCashScheme.付息计划推算计息年度);
                yieldPriceInParameter.setDisScheme(DiscountScheme.以剩余年限为单位);
            }
        }
        yieldPriceInParameter.setCalMethod(secInfoModel.getCalMethod());

        // 后续区间利息计算方案
        if (IntRateType.浮动利率.equals(secInfoModel.getIntRateType())) {
            yieldPriceInParameter.setNextIntMethod(NextIntCalMethod.按实际利率重置分段计息计算);
            yieldPriceInParameter.setCurrIntMethod(CurrIntCalMethod.按实际利率重置分段计息计算);
        }
        else {
            yieldPriceInParameter.setNextIntMethod(NextIntCalMethod.使用计算日最新最新票面计算);
            yieldPriceInParameter.setCurrIntMethod(CurrIntCalMethod.使用区间起始日前最新票面利率计算);
        }

        yieldPriceInParameter.setFullPrice(buyAllPrice);
        YieldByPriceCalculator yieldByPriceCalculator = new YieldByPriceCalculator();
        YieldPriceOutParameter yieldPriceOutParameter = yieldByPriceCalculator.calculate(yieldPriceInParameter);
        return yieldPriceOutParameter;
    }

    public static boolean isNotFomartSec(List<ModCashFlowDto> secIntScheduleList, ModCashFlowPayRulesDto modCashFlowPayRule) {
        // 根据【实际起始日】排序付息计划
        Collections.sort(secIntScheduleList, (o1, o2) -> o1.getVdateUnadjust().compareTo(o2.getVdateUnadjust()));

        if (StringUtil.equals(DictCodeList.频率_月.getValue(), modCashFlowPayRule.getPayCycle())) {
            for (ModCashFlowDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 1);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else if (StringUtil.equals(DictCodeList.频率_季.getValue(), modCashFlowPayRule.getPayCycle())) {
            for (ModCashFlowDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 3);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else if (StringUtil.equals(DictCodeList.频率_半年.getValue(), modCashFlowPayRule.getPayCycle())) {
            for (ModCashFlowDto she : secIntScheduleList) {
                Date mDate = DateUtil.addMonthDate(she.getVdateUnadjust(), 6);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        else {
            for (ModCashFlowDto she : secIntScheduleList) {
                Date mDate = DateUtil.addYear(she.getVdateUnadjust(), 1);
                if (mDate.compareTo(she.getMdateUnadjust()) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判定最终节点总价值
     * @param binTree
     * @param entity
     */
    private void quaryFinalAllValue(StockInfoDto[][] binTree, BinaryTreeModelReaderEntity entity) {
        //组装到期的本息和
        //变量.到期本息和  = SUM（输入.现金流计划中MDATE = 资产信息.到期日的记录的计划现金流）
        BigDecimal mdateCash = BigDecimal.ZERO;
        if (entity.getModCashFlowList() != null && entity.getModCashFlowList().size() > 0) {
            for (ModCashFlowDto dto : entity.getModCashFlowList()) {
                if (dto.getMdate().equals(entity.getMdate())) {
                    mdateCash = ArithmeticUtil.doAdd(mdateCash, dto.getCashAmt());
                }
            }
        }
        //二叉树步数
        BigDecimal bintreeStep = entity.getModBintreeConfig().getBintreeStep();
        for (int i = bintreeStep.intValue(); i>=0; i--) {
            if (binTree[bintreeStep.intValue()][i].getStockFlg() == 1) {
                //变量.二叉树二维数据[资产信息.二叉树步数][i].股票价值 = 变量.二叉树二维数据[资产信息.二叉树步数][i].股票价格
                binTree[bintreeStep.intValue()][i].setStockValue(binTree[bintreeStep.intValue()][i].getStockPrice());
            } else if (binTree[bintreeStep.intValue()][i].getStockFlg() == 0) {
                //变量.二叉树二维数据[资产信息.二叉树步数][i].债券价值 = 变量.到期本息和
                binTree[bintreeStep.intValue()][i].setBondValue(mdateCash);
            }
            //变量.二叉树二维数据[资产信息.二叉树步数][i].总价值 = 变量.二叉树二维数据[资产信息.二叉树步数][i].股票价值 + 变量.二叉树二维数据[资产信息.二叉树步数][i].债券价值
            binTree[bintreeStep.intValue()][i].setAllValue(ArithmeticUtil.doAdd(binTree[bintreeStep.intValue()][i].getStockValue(), binTree[bintreeStep.intValue()][i].getBondValue()));
//            //测试信息打印
//            if (binTree[bintreeStep.intValue()][i].getStockValue() != null) {
//                logger.info("股权价值["+ bintreeStep.intValue() + "][" + i + "]" + binTree[bintreeStep.intValue()][i].getStockValue().doubleValue());
//            }
//            if (binTree[bintreeStep.intValue()][i].getBondValue() != null) {
//                logger.info("债权价值["+ bintreeStep.intValue() + "][" + i + "]" + binTree[bintreeStep.intValue()][i].getBondValue().doubleValue());
//            }
//            if (binTree[bintreeStep.intValue()][i].getAllValue() != null) {
//                logger.info("总价值["+ bintreeStep.intValue() + "][" + i + "]" + binTree[bintreeStep.intValue()][i].getAllValue().doubleValue());
//            }
        }
    }

    /**
     * 二叉树生成
     * @param bintreeStep 二叉树步数
     * @param rootStockPrice 根节点股票价格
     * @param upNum 上升倍数
     * @param downNum 下降倍数
     * @param stockLimit 转股期限
     * @param conPrice 转股价
     * @return
     */
    private StockInfoDto[][] createBinaryTree(BigDecimal bintreeStep, BigDecimal rootStockPrice,
                                              BigDecimal upNum, BigDecimal downNum, BigDecimal stockLimit,
                                              BigDecimal conPrice) {

        //定义股票二维数组
        StockInfoDto[][] binTree = new StockInfoDto[bintreeStep.intValue() + 1][bintreeStep.intValue() + 1];
        for (int i = 0; i < bintreeStep.intValue() + 1; i++) {
            for (int j = 0; j < bintreeStep.intValue() + 1; j++) {
                binTree[i][j] = new StockInfoDto();
            }
        }
        //binTree[0][0].股票价格 = 输入.根节点股票价格
        binTree[0][0].setStockPrice(rootStockPrice);
        //if 输入.转股期限=0 and binTree[0][0].股票价格>输入.转股价
        if (stockLimit.compareTo(BigDecimal.ZERO) == 0 && binTree[0][0].getStockPrice().compareTo(conPrice) > 0) {
            binTree[0][0].setStockFlg(1);
        } else {
            binTree[0][0].setStockFlg(0);
        }
        for (int n = 1; n <= bintreeStep.intValue(); n++) {
            for (int i = 0; i <= bintreeStep.intValue(); i++) {
                if(i<n && i>=0) {
                    //binTree[n][i].股票价格 = binTree[n-1][i]*输入.上升倍数
                    binTree[n][i].setStockPrice(ArithmeticUtil.multiply(binTree[n-1][i].getStockPrice(), upNum));
                }
                if (i<=n && i>=1) {
                    //binTree[n][i].股票价格 = binTree[n-1][i-1]*输入.下降倍数
                    binTree[n][i].setStockPrice(ArithmeticUtil.multiply(binTree[n-1][i-1].getStockPrice(), downNum));
                }
                if ((i<n && i>=0) || (i<=n && i>=1)) {
                    if (n >= stockLimit.intValue() && binTree[n][i].getStockPrice().compareTo(conPrice) > 0) {
                        binTree[n][i].setStockFlg(1);
                    } else {
                        binTree[n][i].setStockFlg(0);
                    }
                }
            }
        }
        return binTree;
    }

    /**
     * 计算标的股票波动率
     * @param listStock
     * @return
     */
    private BigDecimal calaStockVolatility(List<MstSharePrice> listStock) {
        //变量.股票收盘价序列列表 = 资产信息.股票行情
        if (listStock == null || listStock.size() == 0) {
            return null;
        }
        //股票收益率序列
        List<StockYieldsDto> stockYieldsList = new ArrayList<StockYieldsDto>();
        for (int i = 1; i < listStock.size(); i++) {
            StockYieldsDto stockYieldsDto = new StockYieldsDto();
            stockYieldsDto.setCdate(listStock.get(i).getDealDate());
            //股票收益率序列.股票对数 = ln（变量.股票收盘价序列列表[i].收盘价/变量.股票收盘价序列列表[i-1].收盘价）
            BigDecimal stockLogarithm = ArithmeticUtil.divide(listStock.get(i).getClosePrice(), listStock.get(i - 1).getClosePrice(), 12, RoundingMode.HALF_UP);
            stockLogarithm = BigDecimal.valueOf(Math.log(stockLogarithm.doubleValue())).setScale(12, RoundingMode.HALF_UP);
            stockYieldsDto.setStockLogarithm(stockLogarithm);
            stockYieldsList.add(stockYieldsDto);
        }

        //调用【波动率计算方法】
        BigDecimal stockVolatility = calaVolatility(stockYieldsList);
        return stockVolatility;
    }

    private BigDecimal calaVolatility(List<StockYieldsDto> stockYieldsList) {
        //变量.日收益率均值 = 0
        BigDecimal yieldsMeanValue = BigDecimal.ZERO;
        //变量.波动率 = 0
        BigDecimal volatility = BigDecimal.ZERO;

        //调用共通方法：均值计算
        yieldsMeanValue = calaMeanValue(stockYieldsList);

        for (StockYieldsDto dto : stockYieldsList) {
            //变量.波动率 = 变量.波动率 +（区间日收益率列表.日收益率 - 变量.日收益率均值）*（区间日收益率列表.日收益率 - 变量.日收益率均值）
            BigDecimal equals = ArithmeticUtil.subtract(dto.getStockLogarithm(), yieldsMeanValue);
            volatility = ArithmeticUtil.doAdd(volatility, ArithmeticUtil.multiply(equals, equals));
        }
        //变量.波动率 = sqrt（变量.波动率/区间日收益率列表.size（）-1）
        if (stockYieldsList.size() > 1) {
            volatility = ArithmeticUtil.divide(volatility, BigDecimal.valueOf(stockYieldsList.size() - 1), 12, RoundingMode.HALF_UP);
        } else {
            volatility = BigDecimal.ZERO;
        }
        volatility = BigDecimal.valueOf(Math.sqrt(volatility.doubleValue())).setScale(12, RoundingMode.HALF_UP);

        //调用方法 年化标准差
        BigDecimal annualizedVolatility = calaAnnualizedStandardDeviation(volatility, new BigDecimal(252));
        return annualizedVolatility;
    }

    /**
     * 计算年化标准差
     *
     * @param standardDeviation
     * @param cycles
     * @return
     */
    private BigDecimal calaAnnualizedStandardDeviation(BigDecimal standardDeviation, BigDecimal cycles) {
        //年化波动率 = sqrt（周期数）* 参数.标准差
        BigDecimal volatility = BigDecimal.valueOf(Math.sqrt(cycles.doubleValue())).setScale(12, RoundingMode.HALF_UP);
        volatility = ArithmeticUtil.multiply(volatility, standardDeviation).setScale(12, RoundingMode.HALF_UP);
        return volatility;
    }


    /**
     * 均值计算
     *
     * @param stockYieldsList
     * @return
     */
    private BigDecimal calaMeanValue(List<StockYieldsDto> stockYieldsList) {
        BigDecimal meanValue = BigDecimal.ZERO;
        for (StockYieldsDto dto : stockYieldsList) {
            //变量.均值 = 变量.均值 +区间列表.收益率
            meanValue = ArithmeticUtil.doAdd(meanValue, dto.getStockLogarithm());
        }
        //变量.均值 = 变量.均值/区间列表.size（）
        meanValue = ArithmeticUtil.divide(meanValue, BigDecimal.valueOf(stockYieldsList.size()), 12, RoundingMode.HALF_UP);
        return meanValue;
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

}
