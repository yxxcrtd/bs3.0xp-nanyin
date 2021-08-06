package com.joyintech.tams.model.impairment.batch.eclcalculation.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.CommonErrorParams;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModLogsService;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.*;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EclCalculationProcessorService {

    @Autowired
    private EclCalculationMapper eclCalculationMapper;

    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;

    @Autowired
    private ModLogsService modLogsService;

    /**
     * 减值模型数据加载-processor
     *
     * @param params
     * @return
     */
    public EclCalculationResultDto excute(EclCalculationReaderDto entity, Map<String, Object> params, StepExecution stepExecution) {
        EclCalculationResultDto result = new EclCalculationResultDto();
        ModConfigUseage modConfigUseage = new ModConfigUseage();
        ModEclResultProcess modEclResultProcess = new ModEclResultProcess();
        ModEclResult modEclResult = new ModEclResult();
        result.setModConfigUseage(modConfigUseage);
        result.setModEclResultProcess(modEclResultProcess);
        result.setModEclResult(modEclResult);
        ModEclResultProcess entityDto = entity.getModEclResultProcess();
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<CommonErrorParams> errors = (List<CommonErrorParams>) stepExecution.getJobExecution().getExecutionContext().get("ERRORS");
        if (errors == null) {
            errors = new ArrayList<CommonErrorParams>();
        }
        //1、查询每一笔交易数据对应的LGD值
        Map<String, Object> para = new HashMap<>();
        try {
            para.put("MODEL_DEC_CONFIG_TYPE_06", ModelDictCodeList.模型_减值配置类型_特殊资产LGD配置.getValue());
            para.put("MODEL_DEC_CONFIG_TYPE_05", ModelDictCodeList.模型_减值配置类型_资产大类LGD配置.getValue());
            para.put("type8", entityDto.getType8());
            para.put("type12", entityDto.getType12());
            para.put("isContainPledge", entityDto.getIsContainPledge());
            para.put("MODEL_MAPPING_TYPE_01", ModelDictCodeList.模型_映射类型_资产分类映射.getValue());
            para.put("finprodId", entityDto.getAssetId());
            List<ModLgdConfig> configList = eclCalculationMapper.selectModLgdConfig(para);

            //项目类资产判断不出是否含有抵质押物
            if (ModelDictCodeList.模型_证券品种_项目类资产.getValue().equals(entityDto.getType8())
                    && StringUtil.isEmpty(entityDto.getIsContainPledge())) {
                CommonErrorParams commonErrorParams = new CommonErrorParams();
                commonErrorParams.setFinprodId(entityDto.getAssetId());
                commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
                commonErrorParams.setErrorMsg("【ECL计算批处理】：无法判断是否含有合格抵质押物");
                commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                errors.add(commonErrorParams);
                stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            }
            if (configList != null && configList.size() > 0) {
                //交易信息.LGD = LGD参数Dto.LGD
                entityDto.setLgd(configList.get(0).getLgd());
                //定义 配置流水号 = LGD参数Dto.流水号
                String seqno = configList.get(0).getSeqno();
                //定义 配置类型 = LGD参数Dto.配置类型
                String configType = configList.get(0).getAssetType();

                //2、计算ECL值
                //交易信息.前瞻前ECL预期信用损失 = 交易信息.前瞻前PD违约概率% * 交易信息.EAD * 交易信息.LGD
                entityDto.setPreProspectiveEcl(ArithmeticUtil.multiply(entityDto.getPreProspectivePd(), entityDto.getEad(), entityDto.getLgd()));
                //交易信息.前瞻后ECL预期信用损失 = 交易信息.前瞻后PD违约概率% * 交易信息.EAD * 交易信息.LGD
                entityDto.setProspectiveEcl(ArithmeticUtil.multiply(entityDto.getProspectivePd(), entityDto.getEad(), entityDto.getLgd()));

                //4.1 判断使用的参数是否已存在减值配置使用情况表
                para.put("configSeqno", seqno);
                para.put("configType", configType);
                int resultCount= impairmentDataLoadMapper.selectModConfigUseageCount(para);
                if (resultCount == 0) {
                    modConfigUseage.setSeqno(IDGenerator.generate());
                    modConfigUseage.setUseProcess(ModelDictCodeList.模型_减值过程_ECL计算.getValue());
                    modConfigUseage.setConfigSeqno(seqno);
                    modConfigUseage.setConfigType(configType);
                }

            } else {
                CommonErrorParams commonErrorParams = new CommonErrorParams();
                commonErrorParams.setFinprodId(entityDto.getAssetId());
                commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
                commonErrorParams.setErrorMsg("【ECL计算批处理】：资产未配置LGD参数");
                commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                errors.add(commonErrorParams);
                stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            }
            //3.3 设置每一笔交易的资产类型
            para.put("type9", entityDto.getType9());
            para.put("type10", entityDto.getType10());
            para.put("type11", entityDto.getType11());
            String input2 = eclCalculationMapper.selectInput2(para);

            //封装结果集
            quaryResultParam(entityDto, modEclResultProcess, modEclResult, input2, platDate);

        } catch (Exception e) {
            CommonErrorParams commonErrorParams = new CommonErrorParams();
            commonErrorParams.setFinprodId(entityDto.getAssetId());
            commonErrorParams.setBatchSeqNo(batchExecuteInfo.getBatchExedetailSeqNo());
            commonErrorParams.setErrorMsg("【ECL计算批处理】：计算错误");
            commonErrorParams.setErrorType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
            errors.add(commonErrorParams);
            stepExecution.getJobExecution().getExecutionContext().put("ERRORS", errors);
            e.getMessage();
        }
        return result;
    }

    /**
     * 封装结果集
     * @param entity
     * @param modEclResultProcess
     * @param modEclResult
     * @param input2
     * @param platDate
     */
    private void quaryResultParam(ModEclResultProcess entity, ModEclResultProcess modEclResultProcess,
                                  ModEclResult modEclResult, String input2, Date platDate) {
        //减值结果过程表
        BeanUtil.copyPropertiesIgnoreNull(modEclResultProcess, entity);
        //序列号								自动生成
        modEclResultProcess.setSeqno(IDGenerator.generate());
        //减值日期								跑批日
        modEclResultProcess.setDecDate(platDate);
        //过程								“05 ECL计算”，此批处理主要加载数据。
        modEclResultProcess.setProcess(ModelDictCodeList.模型_减值过程_ECL计算.getValue());

        //资产减值结果表
        BeanUtil.copyPropertiesIgnoreNull(modEclResult, entity);
        //序列号								自动生成
        modEclResult.setSeqno(IDGenerator.generate());
        //资产类型
        modEclResult.setAssetType(input2);
        //减值阶段 交易信息DTO.减值阶段
        modEclResult.setStage(entity.getFinStage());
        //数据来源
        modEclResult.setDataSource(ModelDictCodeList.模型数据来源_模型.getValue());
        //业务编号
        modEclResult.setBussNo(entity.getTradeId());
    }
}
