package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.ModelDictCode;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao.AssetImpairmentStageJudgeModelMapper;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.*;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import com.joyintech.tams.valuation.common.dto.modelDto.ModRepayAndInterestDto;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 资产减值阶段判断批处理
 */
@Service
public class AssetImpairmentStageJudgeModelReaderService {
    private static final Logger logger = LoggerFactory.getLogger(AssetImpairmentStageJudgeModelReaderService.class);
    /**
     * Mapper
     */
    @Autowired
    private AssetImpairmentStageJudgeModelMapper assetImpairmentStageJudgeModelMapper;

    @Autowired
    private ModLogsService modLogsService;

    // 日志key
    private final String LOG_KEY = "logkey";

    /**
     * 资产减值阶段判断模型-reader
     *
     * @param params
     * @return
     */
    public List<AssetImpairmentStageJudgeModelReaderEntity> createReaderList(Map<String, Object> params,StepExecution stepExecution) {
        List<AssetImpairmentStageJudgeModelReaderEntity> list = new ArrayList<AssetImpairmentStageJudgeModelReaderEntity>();
        List<ModEclResultProcess> eclList = new ArrayList<ModEclResultProcess>();
        Date plateDate = (Date) params.get("PLATE_DATE");//平台日
        List<String> finprodIdList = (List<String>) params.get("finprodIdList");
        Map<String, Object> param = new HashMap<>();
        param.put("batchDate", plateDate);
        param.put("finprodIdList",finprodIdList);

        eclList = assetImpairmentStageJudgeModelMapper.assetImpairmentStageJudgeList(param);

        for (ModEclResultProcess item : eclList) {
            AssetImpairmentStageJudgeModelReaderEntity entity = new AssetImpairmentStageJudgeModelReaderEntity();
            entity.setModEclResultProcess(item);
            entity.setModCashFlowList(getCashFlowPlanInfoByFinprodId(item.getProdId()));
            list.add(entity);
        }

        if(list == null || list.size()==0){
            SysBatchExecInfo sysBatchExecInfo = (SysBatchExecInfo) params.get("BATCH_INFO");
            // 取不到满足条件的数据
            for(int i=0;i<finprodIdList.size();i++){
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(finprodIdList.get(i));
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
            }
        }
        return list;
    }

    /**
     * 获取减值阶段参数
     *
     * @return
     */
    public List<ModStageParaDto> getModStageParaDtoList() {
        return assetImpairmentStageJudgeModelMapper.modStageParaList();
    }

    // 组装日志上下文信息
    private void editLogContextInfo(StepExecution stepExecution,ModValBatchLogResult logrst){
        if(stepExecution.getJobExecution().getExecutionContext().get(LOG_KEY) != null){
            List<ModValBatchLogResult> mvbLogRst = (List<ModValBatchLogResult>)stepExecution.getJobExecution().getExecutionContext().get(LOG_KEY);
            mvbLogRst.add(logrst);
            stepExecution.getJobExecution().getExecutionContext().put(LOG_KEY,mvbLogRst);
        }else{
            List<ModValBatchLogResult> mvbLogRst = new ArrayList<>();// 异常信息定义
            mvbLogRst.add(logrst);
            stepExecution.getJobExecution().getExecutionContext().put(LOG_KEY,mvbLogRst);
        }
    }

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


        String finStage;  // 减值阶段
        String itemKey = "P3_01_04";
        String itemValue;
        BigDecimal prinOverdueDay = BigDecimal.ZERO;  // 本金逾期天数
        BigDecimal intOverdueDay = BigDecimal.ZERO;  // 利息逾期天数

        SysBatchExecInfo sysBatchExecInfo = (SysBatchExecInfo) param.get("BATCH_INFO");

        //第1步骤：key：三阶段_绝对_风险五级分类   P3_01_04
        try {
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                List<String> riskList = Arrays.asList(itemValue.split(","));
                if (StringUtil.isEmpty(resultProcess.getRiskLevelFive())) {
                    // 警告	取不到风险五级分类	【批处理名称】：资产无风险五级分类
                    ModValBatchLogResult logrst = new ModValBatchLogResult();
                    logrst.setFinprodId(resultProcess.getAssetId());
                    logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                    logrst.setTaskId(sysBatchExecInfo.getTaskId());
                    logrst.setMsg("【资产减值阶段判断批处理】：资产无风险五级分类");
                    logrst.setType(ModelDictCodeList.批处理执行结果_警告.getValue());
                    editLogContextInfo(stepExecution,logrst);
                } else if (StringUtil.isNotEmpty(resultProcess.getRiskLevelFive()) && riskList.contains(resultProcess.getRiskLevelFive())) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 警告,未配置三阶段判定风险五级分类标准；【批处理名称】：未配置三阶段风险五级分类标准
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置三阶段风险五级分类标准");
                logrst.setType(ModelDictCodeList.批处理执行结果_警告.getValue());
                editLogContextInfo(stepExecution,logrst);
            }
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        //第2步骤：key：三阶段_绝对_公司评级信息低于（含）P3_01_03
        try {
            itemKey = "P3_01_03";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                if (StringUtil.isEmpty(resultProcess.getResultCurrent())) {
                    // 失败	取不到当前时点的评级	【批处理名称】：无当前时点的评级
                    ModValBatchLogResult logrst = new ModValBatchLogResult();
                    logrst.setFinprodId(resultProcess.getAssetId());
                    logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                    logrst.setTaskId(sysBatchExecInfo.getTaskId());
                    logrst.setMsg("【资产减值阶段判断批处理】：无当前时点的评级");
                    logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                    editLogContextInfo(stepExecution,logrst);
                    return null;
                } else if (StringUtil.isNotEmpty(resultProcess.getResultCurrent()) && getPoorRating(itemValue, resultProcess.getResultCurrent()) >= 0) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 失败	未配置三阶段判定评级标准	【批处理名称】：未配置三阶段评级标准
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置三阶段评级标准");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            }
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 查询存续期交易列表
        List<ModRepayAndInterestDto> hbList = new ArrayList<ModRepayAndInterestDto>(); // 还本
        List<ModRepayAndInterestDto> fxList = new ArrayList<ModRepayAndInterestDto>(); // 付息
        try {
            List<ModRepayAndInterestDto> hbfxList = assetImpairmentStageJudgeModelMapper.assetImpairmentStageJudgeT2322List(resultProcess.getAssetId());
            if (hbfxList != null && hbfxList.size() > 0) {
                for (ModRepayAndInterestDto hbfxdto : hbfxList) {
                    if (hbfxdto.getDealSide().equals(ModelDictCodeList.交易方向_还本到账.getValue())) {
                        hbList.add(hbfxdto);
                    } else {
                        fxList.add(hbfxdto);
                    }
                }
            }
        }catch(Exception e){
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 第3步骤：key：三阶段_绝对_本金逾期超过天数（含）P3_01_01
        try {
            itemKey = "P3_01_01";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                for (ModCashFlowDto cashFlowDto : item.getModCashFlowList()) {
                    if (DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getTradeDate()) > 0 &&
                            DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getDecDate()) < 0
                    ) {
                        boolean flag = false; // 是否已支付
                        for (ModRepayAndInterestDto hb : hbList) {
                            if (DateUtil.compareDate(hb.getPlanEndDate(), cashFlowDto.getMdate()) == 0) {
                                BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), hb.getTradeDate()));
                                prinOverdueDay = ArithmeticUtil.subtract(daynum, prinOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : prinOverdueDay;
                                flag = true;
                                continue;
                            }
                            if (flag == false) {
                                BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), resultProcess.getDecDate()));
                                prinOverdueDay = ArithmeticUtil.subtract(daynum, prinOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : prinOverdueDay;
                            }
                        }
                    }
                }

                if (prinOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    resultProcess.setPrinOverdueDay(prinOverdueDay);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 失败	未配置三阶段判定逾期参数	【批处理名称】：未配置三阶段逾期参数
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置三阶段逾期参数");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            }
        } catch (Exception e) {
            // 失败	判断不出逾期天数	【批处理名称】：无法判断逾期天数
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：无法判断逾期天数");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 第4步骤：key：三阶段_绝对_利息逾期超过天数（含）P3_01_02
        try {
            itemKey = "P3_01_02";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                for (ModCashFlowDto cashFlowDto : item.getModCashFlowList()) {
                    if (DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getTradeDate()) > 0 &&
                            DateUtil.compareDate(cashFlowDto.getPayDate(), resultProcess.getDecDate()) < 0
                    ) {
                        boolean flag = false; // 是否已支付
                        for (ModRepayAndInterestDto hb : hbList) {
                            if (DateUtil.compareDate(hb.getPlanEndDate(), cashFlowDto.getMdate()) == 0) {
                                BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), hb.getTradeDate()));
                                intOverdueDay = ArithmeticUtil.subtract(daynum, intOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : intOverdueDay;
                                flag = true;
                                continue;
                            }
                            if (flag == false) {
                                BigDecimal daynum = BigDecimal.valueOf(DateUtil.getBetweenDayNumber(cashFlowDto.getPayDate(), resultProcess.getDecDate()));
                                intOverdueDay = ArithmeticUtil.subtract(daynum, intOverdueDay).compareTo(BigDecimal.ZERO) > 0 ? daynum : intOverdueDay;
                            }
                        }
                    }
                }

                if (intOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                    finStage = ModelDictCodeList.阶段_买入后出现减值_P3.getValue();// 三阶段
                    resultProcess.setFinStage(finStage);
                    resultProcess.setIntOverdueDay(intOverdueDay);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 失败	未配置三阶段判定逾期参数	【批处理名称】：未配置三阶段逾期参数
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置三阶段逾期参数");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            }
        } catch (Exception e) {
            // 失败	判断不出逾期天数	【批处理名称】：无法判断逾期天数
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：无法判断逾期天数");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 第5步骤：key：二阶段_绝对_风险五级分类 P2_01_04
        itemKey = "P2_01_04";
        try {
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isNotEmpty(itemValue)) {
                List<String> riskList = Arrays.asList(itemValue.split(","));
                if (StringUtil.isEmpty(resultProcess.getRiskLevelFive())) {
                    // 警告	取不到风险五级分类	【批处理名称】：资产无风险五级分类
                    modLogsService.warn(resultProcess.getAssetId(), sysBatchExecInfo.getBatchExedetailSeqNo(), sysBatchExecInfo.getTaskId(), "【资产减值阶段判断批处理】：资产无风险五级分类");
                } else if (StringUtil.isNotEmpty(resultProcess.getRiskLevelFive()) && riskList.contains(resultProcess.getRiskLevelFive())) {
                    finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            } else {
                // 警告,未配置二阶段判定风险五级分类标准；【批处理名称】：未配置二阶段风险五级分类标准
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置二阶段风险五级分类标准");
                logrst.setType(ModelDictCodeList.批处理执行结果_警告.getValue());
                editLogContextInfo(stepExecution,logrst);
            }
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 第6步骤：key：二阶段_绝对_公司评级信息低于（含）P2_01_03
        itemKey = "P2_01_03";
        try {
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isEmpty(itemValue)) {
                // 失败	未配置二阶段判定评级标准	【批处理名称】：未配置二阶段评级标准
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置二阶段评级标准");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            } else if (StringUtil.isNotEmpty(itemValue) && getPoorRating(itemValue, resultProcess.getResultCurrent()) >= 0) {
                finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                resultProcess.setFinStage(finStage);
                rstDto.setModEclResultProcess(resultProcess);
                return rstDto;
            }
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        //第7步骤：key：二阶段_绝对_本金逾期超过天数（含）P2_01_01
        try {
            itemKey = "P2_01_01";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isEmpty(itemValue)) {
                // 失败	未配置二阶段判定逾期参数	【批处理名称】：未配置二阶段逾期参数
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置二阶段逾期参数");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            } else if (StringUtil.isNotEmpty(itemValue) && prinOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                resultProcess.setFinStage(finStage);
                rstDto.setModEclResultProcess(resultProcess);
                return rstDto;
            }
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 第8步骤：key：二阶段_绝对_利息逾期超过天数（含）P2_01_02
        try {
            itemKey = "P2_01_02";
            itemValue = (String) param.get(itemKey);
            if (StringUtil.isEmpty(itemValue)) {
                // 失败	未配置二阶段判定逾期参数	【批处理名称】：未配置二阶段逾期参数
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置二阶段逾期参数");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            } else if (StringUtil.isNotEmpty(itemValue) && intOverdueDay.compareTo(BigDecimal.valueOf(Long.valueOf(itemValue))) >= 0) {
                finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                resultProcess.setFinStage(finStage);
                rstDto.setModEclResultProcess(resultProcess);
                return rstDto;
            }
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }

        // 第9步骤：key：二阶段_相对_公司评级信息下调级数 P2_02_05
        try {
            itemKey = "P2_02_05";
            itemValue = (String) param.get(itemKey);
            if(StringUtil.isEmpty(itemValue)){
                // 失败	未配置减值阶段判定评级下迁标准	【批处理名称】：未配置减值阶段判定评级下迁标准
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(resultProcess.getAssetId());
                logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(sysBatchExecInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】：未配置减值阶段判定评级下迁标准");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
                return null;
            }else{
                if (resultProcess.getResultDeal() == null) {
                    // 失败	取不到买入时点评级	【批处理名称】：【机构名称/资产代码】买入时点无评级信息
                    ModValBatchLogResult logrst = new ModValBatchLogResult();
                    logrst.setFinprodId(resultProcess.getAssetId());
                    logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
                    logrst.setTaskId(sysBatchExecInfo.getTaskId());
                    String gradeOrgName = SysDataUtil.getNameFromDataDict(ModelDictCode.评级机构, item.getModEclResultProcess().getGradeOrgId());
                    logrst.setMsg("【资产减值阶段判断批处理】：【"+gradeOrgName+"/"+item.getAssetId()+"】买入时点无评级信息");
                    logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                    editLogContextInfo(stepExecution,logrst);
                    return null;
                }else if (resultProcess.getResultDeal() != null && resultProcess.getResultCurrent() != null &&
                        getPoorRating(resultProcess.getResultDeal(), resultProcess.getResultCurrent()) > Integer.valueOf(itemValue)
                ) {
                    finStage = ModelDictCodeList.阶段_买入后风险显著提升_P2.getValue();// 二阶段
                    resultProcess.setFinStage(finStage);
                    rstDto.setModEclResultProcess(resultProcess);
                    return rstDto;
                }
            }
            return rstDto;
        } catch (Exception e) {
            ModValBatchLogResult logrst = new ModValBatchLogResult();
            logrst.setFinprodId(resultProcess.getAssetId());
            logrst.setBatchExecSeqno(sysBatchExecInfo.getBatchExedetailSeqNo());
            logrst.setTaskId(sysBatchExecInfo.getTaskId());
            logrst.setMsg("【资产减值阶段判断批处理】：计算错误");
            logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            editLogContextInfo(stepExecution,logrst);
            return null;
        }
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
     * 评级差(返回值 > 0 则，r1评级高；=0则评级相同，<0，则r2评级高)
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
        return rs - ra;
    }

}
