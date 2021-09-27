package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.common.base.entity.ModAssetGradeSpecialConfig;
import com.joyintech.tams.common.base.entity.ModBatchLogs;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao.AssetImpairmentStageJudgeModelMapper;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelReaderEntity;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelResultDto;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.valuation.common.dto.modelDto.ModAssetInfoDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRepayAndInterestDto;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.tams.valuation.model.common.rateinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.valuation.model.common.rateinfo.service.RateInfoCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AssetImpairmentStageJudgeModelProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(AssetImpairmentStageJudgeModelReaderService.class);
    /**
     * Mapper
     */
    @Autowired
    private AssetImpairmentStageJudgeModelMapper assetImpairmentStageJudgeModelMapper;

    @Autowired
    private ModLogsService modLogsService;

    @Autowired
    private ImpairmentCommonService impairmentCommonService;

    @Autowired
    private RateInfoCommonService rateInfoCommonService;

    /**
     * 处理
     *
     * @param param
     * @return
     */
    public AssetImpairmentStageJudgeModelResultDto execute(AssetImpairmentStageJudgeModelReaderEntity item, Map<String, Object> param, StepExecution stepExecution) {
        AssetImpairmentStageJudgeModelResultDto rstDto = new AssetImpairmentStageJudgeModelResultDto();
        ModEclResultProcess resultProcess = new ModEclResultProcess();
        ModEclResultProcess readerProcess = item.getModEclResultProcess();
        // 目标对象转换
        BeanUtil.copyPropertiesIgnoreNull(resultProcess, readerProcess);
        resultProcess.setSeqno(IDGenerator.generate());
        resultProcess.setProcess(ModelDictCodeList.模型_减值过程_减值阶段判断.getValue()); // 减值阶段判断
        resultProcess.setFinStage(ModelDictCodeList.阶段_初始买入时_P1.getValue());
        rstDto.setModEclResultProcess(resultProcess);

        // 定义 变量.日志消息内容
        String msg ="";
        String finStage;  // 减值阶段
        String itemKey = "P3_01_04";
        String itemValue;
        BigDecimal prinOverdueDay = BigDecimal.ZERO;  // 本金逾期天数
        BigDecimal intOverdueDay = BigDecimal.ZERO;  // 利息逾期天数

        SysBatchExecInfo sysBatchExecInfo = (SysBatchExecInfo) param.get("BATCH_INFO");
        // 定义 变量.是否未做过风险五级分类判断，用于风险五级分类防止重复提示信息
        boolean fiveRiskFlag = true;
        //第1步骤：key：三阶段_绝对_风险五级分类   P3_01_04
        // 回购类资产无风险五级分类
        if(!DictCodeListExt1.证券品种_回购.getValue().equals(readerProcess.getModelAssetType())){
            try {
                itemValue = (String) param.get(itemKey);
                if (StringUtil.isNotEmpty(itemValue)) {
                    List<String> riskList = Arrays.asList(itemValue.split(","));
                    if (StringUtil.isEmpty(resultProcess.getRiskLevelFive())) {
                        // 警告	取不到风险五级分类	【批处理名称】：资产无风险五级分类
                        if(resultProcess.getTradeId() != null){
                            msg = "【交易编号 - "+resultProcess.getTradeId() + "：资产减值阶段判断批处理】：资产缺少风险五级分类属性.";
                        }
                        else{
                            msg = "【资产减值阶段判断批处理】：资产缺少风险五级分类属性.";
                        }
                        modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
                        fiveRiskFlag = false;
                    } else if (StringUtil.isNotEmpty(resultProcess.getRiskLevelFive()) && riskList.contains(resultProcess.getRiskLevelFive())) {
                        finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                        resultProcess.setFinStage(finStage);
                        rstDto.setModEclResultProcess(resultProcess);
                        return rstDto;
                    }
                } else {
                    // 警告,未配置三阶段判定风险五级分类标准；【批处理名称】：未配置三阶段风险五级分类标准
                    modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置三阶段风险五级分类标准.");
                }
            } catch (Exception e) {
                modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：三阶段资产无风险五级分类判断异常.");
                return null;
            }
        }

        //第2步骤：key：三阶段_绝对_公司评级信息低于（含）P3_01_03
        try {
            itemKey = "P3_01_03";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                if (StringUtil.isEmpty(resultProcess.getResultCurrent())) {
                    // 失败	取不到当前时点的评级	【批处理名称】：无当前时点的评级
                    modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：无当前时点的评级.");
                } else if (StringUtil.isNotEmpty(resultProcess.getResultCurrent()) && getPoorRating(itemValue, resultProcess.getResultCurrent()) <= 0) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 失败	未配置三阶段判定评级标准	【批处理名称】：未配置三阶段评级标准
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置三阶段评级标准.");
            }
        } catch (Exception e) {
            modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：三阶段评级标准判断异常.");
            return null;
        }

        // 查询存续期交易列表
        List<ModRepayAndInterestDto> hbList = new ArrayList<ModRepayAndInterestDto>(); // 还本
        List<ModRepayAndInterestDto> fxList = new ArrayList<ModRepayAndInterestDto>(); // 付息
        List<ModRepayAndInterestDto> hbfxList = assetImpairmentStageJudgeModelMapper.assetImpairmentStageJudgeT2322List(resultProcess.getAssetId(), resultProcess.getDecDate());
        if (hbfxList != null && hbfxList.size() > 0) {
            for (ModRepayAndInterestDto hbfxdto : hbfxList) {
                if (hbfxdto.getDealSide().equals(DictCodeList.交易方向_还本到账.getValue())) {
                    hbList.add(hbfxdto);
                } else {
                    fxList.add(hbfxdto);
                }
            }
        }

        // 第3步骤：key：三阶段_绝对_本金逾期超过天数（含）P3_01_01
        try {
            itemKey = "P3_01_01";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                for (ModCashFlowDto cashFlowDto : item.getModCashFlowList()) {
                    if (DictCodeListExt1.现金流二级类型_本金.getValue().equals(cashFlowDto.getCashType2()) &&
                            DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getTradeDate()) > 0 &&
                            DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getDecDate()) < 0
                    ) {
                        boolean flag = false; // 是否已支付
                        for (ModRepayAndInterestDto hb : hbList) {
                            if (DateUtil.compareDate(hb.getPlanEndDate(), cashFlowDto.getMdate()) == 0) {
                                BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), hb.getTradeDate()));
                                prinOverdueDay = ArithmeticUtil.subtract(daynum, prinOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : prinOverdueDay;
                                flag = true;
                            }
                        }
                        if (!flag) {
                            BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), resultProcess.getDecDate()));
                            prinOverdueDay = ArithmeticUtil.subtract(daynum, prinOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : prinOverdueDay;
                        }
                    }
                }
                resultProcess.setPrinOverdueDay(prinOverdueDay);
                if (prinOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 失败	未配置三阶段判定逾期参数	【批处理名称】：未配置三阶段逾期参数
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置三阶段本金逾期参数.");
            }
        } catch (Exception e) {
            // 失败：逾期天数判断异常
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：本金逾期天数判断异常.");
            return null;
        }

        // 第4步骤：key：三阶段_绝对_利息逾期超过天数（含）P3_01_02
        try {
            itemKey = "P3_01_02";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                for (ModCashFlowDto cashFlowDto : item.getModCashFlowList()) {
                    if (DictCodeListExt1.现金流二级类型_利息.getValue().equals(cashFlowDto.getCashType2()) &&
                            DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getTradeDate()) > 0 &&
                            DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getDecDate()) < 0
                    ) {
                        boolean flag = false; // 是否已支付
                        for (ModRepayAndInterestDto hb : fxList) {
                            if (DateUtil.compareDate(hb.getPlanEndDate(), cashFlowDto.getMdate()) == 0) {
                                BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), hb.getTradeDate()));
                                intOverdueDay = ArithmeticUtil.subtract(daynum, intOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : intOverdueDay;
                                flag = true;
                            }
                        }
                        if (!flag) {
                            BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), resultProcess.getDecDate()));
                            intOverdueDay = ArithmeticUtil.subtract(daynum, intOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : intOverdueDay;
                        }
                    }
                }
                // 利息逾期天数
                resultProcess.setIntOverdueDay(intOverdueDay);
                if (intOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 失败	未配置三阶段判定逾期参数	【批处理名称】：未配置三阶段利息逾期参数
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置三阶段利息逾期参数.");
            }
        } catch (Exception e) {
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：利息逾期天数判断异常.");
            return null;
        }

        // 第5步骤：key：二阶段_绝对_风险五级分类 P2_01_04
        // 回购类资产无风险五级分类
        if(!DictCodeListExt1.证券品种_回购.getValue().equals(readerProcess.getModelAssetType())){
            itemKey = "P2_01_04";
            try {
                itemValue = (String) param.get(itemKey);
                // 是否存在指标，且资产是否有风险五级分类
                if (StringUtil.isNotEmpty(itemValue)) {
                    List<String> riskList = Arrays.asList(itemValue.split(","));
                    //
                    if (StringUtil.isEmpty(resultProcess.getRiskLevelFive()) && fiveRiskFlag) {
                        if(resultProcess.getTradeId() != null){
                            msg = "【交易编号 - "+resultProcess.getTradeId() + "：资产减值阶段判断批处理】：资产缺少风险五级分类属性.";
                        }
                        else{
                            msg = "【资产减值阶段判断批处理】：资产缺少风险五级分类属性.";
                        }
                        // 警告	取不到风险五级分类	【批处理名称】：资产无风险五级分类
                        modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
                    } else if (StringUtil.isNotEmpty(resultProcess.getRiskLevelFive()) && riskList.contains(resultProcess.getRiskLevelFive())) {
                        finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                        resultProcess.setFinStage(finStage);
                        rstDto.setModEclResultProcess(resultProcess);
                        return rstDto;
                    }
                } else {
                    // 警告,未配置二阶段判定风险五级分类标准；【批处理名称】：未配置二阶段风险五级分类标准
                    modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置二阶段风险五级分类标准.");
                }
            } catch (Exception e) {
                modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：二阶段风险五级分类标准判断异常.");
                return null;
            }
        }


        // 第6.1步骤：key：二阶段_绝对_公司评级信息低于（含）P2_01_03
        itemKey = "P2_01_03";
        try {
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isEmpty(itemValue)) {
                // 失败	未配置二阶段判定评级标准	【批处理名称】：未配置二阶段评级标准
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置二阶段评级标准.");
            } else if (StringUtil.isNotEmpty(itemValue) && getPoorRating(itemValue, resultProcess.getResultCurrent()) <= 0) {
                finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                resultProcess.setFinStage(finStage);
                rstDto.setModEclResultProcess(resultProcess);
                return rstDto;
            }
        } catch (Exception e) {
            // 失败	未配置二阶段判定评级标准	【批处理名称】：未配置二阶段评级标准
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：二阶段评级标准判断异常.");
            return null;
        }

        //第7步骤：key：二阶段_绝对_本金逾期超过天数（含）P2_01_01
        try {
            itemKey = "P2_01_01";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isEmpty(itemValue)) {
                // 失败	未配置二阶段判定逾期参数	【批处理名称】：未配置二阶段逾期参数
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置二阶段逾期参数.");
            } else if (StringUtil.isNotEmpty(itemValue) && prinOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                resultProcess.setFinStage(finStage);
                rstDto.setModEclResultProcess(resultProcess);
                return rstDto;
            }
        } catch (Exception e) {
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：二阶段评级标准判断异常.");
            return null;
        }

        // 第8步骤：key：二阶段_绝对_利息逾期超过天数（含）P2_01_02
        try {
            itemKey = "P2_01_02";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isEmpty(itemValue)) {
                // 失败	未配置二阶段判定逾期参数	【批处理名称】：未配置二阶段逾期参数
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置二阶段逾期参数.");
            } else if (StringUtil.isNotEmpty(itemValue) && intOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                resultProcess.setFinStage(finStage);
                rstDto.setModEclResultProcess(resultProcess);
                return rstDto;
            }
        } catch (Exception e) {
            // 失败	未配置二阶段判定逾期参数	【批处理名称】：未配置二阶段逾期参数
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：二阶段评级标准判断异常.");
            return null;
        }

        // 第9步骤：key：二阶段_相对_公司评级信息下调级数 P2_02_05
        try {
            itemKey = "P2_02_05";
            itemValue = (String) param.get(itemKey);
            if(StringUtil.isEmpty(itemValue)){
                // 失败	未配置减值阶段判定评级下迁标准	【批处理名称】：未配置减值阶段判定评级下迁标准
                modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), "【资产减值阶段判断批处理】：未配置减值阶段判定评级下迁标准.");
            }else{
                if (resultProcess.getResultDeal() == null) {
                    // 失败	取不到买入时点评级	【批处理名称】：【机构名称/资产代码】买入时点无评级信息
                    String gradeOrgName = SysDataUtil.getNameFromDataDict(ModelDictCode.评级机构, item.getModEclResultProcess().getGradeOrgId());
                    msg = "【资产减值阶段判断批处理】：【"+gradeOrgName+"/"+item.getAssetId()+"】买入时点无评级信息";
                    modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
                } else if (resultProcess.getResultDeal() != null && resultProcess.getResultCurrent() != null &&
                        getPoorRating(resultProcess.getResultCurrent(), resultProcess.getResultDeal()) >= Integer.valueOf(itemValue)) {
                    finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            }
        } catch (Exception e) {
            msg = "【资产减值阶段判断批处理】：减值阶段判定评级下迁标准判断异常";
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
            return null;
        }

        // 第10步骤：债项类资产阶段判断，债项评级在“C”以上（不含），主体评级在“C” 级以下（含）的债券
        try {
            if(DictCodeListExt1.证券品种_债券.getValue().equals(resultProcess.getModelAssetType())){
                ModAssetInfoDto modAssetInfoDto = new ModAssetInfoDto();
                BeanUtil.copyPropertiesIgnoreNull(modAssetInfoDto, item);
                modAssetInfoDto.setSecType(resultProcess.getModelAssetType());
                modAssetInfoDto.setFinprodId(resultProcess.getAssetId());
                modAssetInfoDto.setSecTypeDetail(resultProcess.getModelAssetDetailType());
                modAssetInfoDto.setFinprodMarketId(resultProcess.getMarketId());
                // 定义 变量.债项评级信息
                ViewRatingInfoDto ratingInfo1 = null;
                // 定义 变量.主体评级信息
                ViewRatingInfoDto ratingInfo2 = null;
                if(item.getModAssetGradeSpecialConfig()!= null){
                    // 特殊资产评级配置
                    ModAssetGradeSpecialConfig specialConfig = item.getModAssetGradeSpecialConfig();
                    specialConfig.setoType(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                    // 查询对应债项评级信息
                    ratingInfo1  = rateInfoCommonService.getRatingInfoBySpecialConfig(modAssetInfoDto,specialConfig,resultProcess.getDecDate());
                    specialConfig.setoType(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                    // 查询对应主体评级信息
                    ratingInfo2  = rateInfoCommonService.getRatingInfoBySpecialConfig(modAssetInfoDto,specialConfig,resultProcess.getDecDate());
                }
                else if(item.getModAssetGradeConfig() != null){
                    // 特殊资产评级配置
                    ModAssetGradeConfig modConfig = item.getModAssetGradeConfig();
                    // 查询对应债项评级信息
                    modConfig.setoType(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                    ratingInfo1 = rateInfoCommonService.getRatingInfoByTypeConfig(modAssetInfoDto,modConfig,resultProcess.getDecDate());
                    // 查询对应主体评级信息
                    modConfig.setoType(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                    ratingInfo2 = rateInfoCommonService.getRatingInfoByTypeConfig(modAssetInfoDto,modConfig,resultProcess.getDecDate());
                }
                else{
                    // 失败	未查询到对应资产评级配置
                    msg = "【资产减值阶段判断批处理】：【"+item.getAssetId()+"】未查询到对应资产评级配置";
                    modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
                }
                if(ratingInfo1 != null &&ratingInfo2 != null){
                    //债项评级在“C”以上（不含），主体评级在“C” 级以下（含）的债券
                    if(this.getPoorRating(DictCodeListExt3.内部评级_C.getValue(),ratingInfo1.getRating())>0
                            && this.getPoorRating(DictCodeListExt3.内部评级_C.getValue(),ratingInfo2.getRating())<=0
                    ){
                        finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                        resultProcess.setFinStage(finStage);
                        rstDto.setModEclResultProcess(resultProcess);
                        return rstDto;
                    }
                }
                else{
                    if(ratingInfo1 == null){
                        // 失败	未查询到对应资产评级配置
                        msg = "【资产减值阶段判断批处理】：未查询到资产债项评级结果";
                        modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
                    }
                    if(ratingInfo2 == null){
                        // 失败	未查询到对应资产评级配置
                        msg = "【资产减值阶段判断批处理】：未查询到资产主体评级结果";
                        modLogsService.warn((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
                    }
                }
            }
        } catch (Exception e) {
            msg = "【资产减值阶段判断批处理】：债项类资产债项评级、主体评级判断异常";
            modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),resultProcess.getAssetId(), msg);
            return null;
        }
        return rstDto;
    }



    /**
     * 获取资产现金流计划集合
     * @param finprodId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<ModCashFlowDto> getCashFlowPlanInfoByFinprodId(String finprodId){
        try{
            if(StringUtil.isEmpty(finprodId)){
                logger.info("资产代码不能为空！");
                throw new Exception("资产代码不能为空!");
            }
            return assetImpairmentStageJudgeModelMapper.getCashFlowPlanInfoByFinprodId(finprodId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 评级差(返回值 > 0 则，r2评级高；=0则评级相同，<0，则r1评级高)
     * @param r1
     * @param r2
     * @return
     */
    public int getPoorRating(String r1, String r2) {
        if (StringUtil.isEmpty(r1) || StringUtil.isEmpty(r2)) {
            return 0;
        }
        int ra = Integer.valueOf(r1.replaceAll("^(0+)", ""));
        int rs = Integer.valueOf(r2.replaceAll("^(0+)", ""));
        return ra - rs;
    }

}
