package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ModAssetGradeConfig;
import com.joyintech.tams.common.base.entity.ModAssetGradeSpecialConfig;
import com.joyintech.tams.common.base.entity.ModBatchLogs;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao.AssetImpairmentStageJudgeModelMapper;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.*;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.valuation.common.dto.modelDto.ModCashFlowDto;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import com.joyintech.tams.valuation.component.batch.service.ModLogsService;
import com.joyintech.tams.valuation.component.common.constant.ValComponentConstant;
import com.joyintech.tams.valuation.model.common.rateconfig.service.RateConfigCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ImpairmentCommonService impairmentCommonService;

    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;
    @Autowired
    private RateConfigCommonService rateConfigCommonService;

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
        param.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        // 减值方案系统参数
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        param.put("lossType", lossType);
        // 普华方案只查询买入交易的数据
        eclList = assetImpairmentStageJudgeModelMapper.assetImpairmentStageJudgeList(param);
        //2、查询外部机构评级结果，映射评级机构对应的内部评级
        // 2.1 查询所有的评级配置数据
        // 定义 变量.特殊资产评级配置
        Map<String, ModAssetGradeSpecialConfig> sepcialConfigMap = rateConfigCommonService.listAssetSpecialGradeConfigs();
        // 定义 变量.资产大类评级配置
        Map<String, ModAssetGradeConfig> configMap = rateConfigCommonService.listAssetGradeConfigs();

        for (ModEclResultProcess item : eclList) {
            AssetImpairmentStageJudgeModelReaderEntity entity = new AssetImpairmentStageJudgeModelReaderEntity();
            entity.setModEclResultProcess(item);
            entity.setModCashFlowList(getCashFlowPlanInfoByFinprodId(item.getAssetId()));
            // 特殊资产评级配置不为空时
            if(sepcialConfigMap.get(item.getAssetId()) != null){
                entity.setModAssetGradeSpecialConfig(sepcialConfigMap.get(item.getAssetId()));
            }
            // 资产评级配置：资产类型+资产细分类
            else if(configMap.get(item.getModelAssetType()+item.getModelAssetDetailType()) != null){
                entity.setModAssetGradeConfig(configMap.get(item.getModelAssetType()+item.getModelAssetDetailType()));
            }
            // 资产评级配置：资产类型
            else if(configMap.get(item.getModelAssetType()) != null){
                entity.setModAssetGradeConfig(configMap.get(item.getModelAssetType()));
            }
            list.add(entity);
        }

        if(list == null || list.size()==0){
            SysBatchExecInfo sysBatchExecInfo = (SysBatchExecInfo) params.get("BATCH_INFO");
            String msg = "【资产减值阶段判断批处理】：计算错误";
            // 取不到满足条件的数据
            finprodIdList.forEach(x->{
                modLogsService.error((Map<String, ModBatchLogs>)stepExecution.getJobExecution().getExecutionContext().get(ValComponentConstant.MODEL_LOGS),x, msg);
            });
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

    /**
     * 获取资产现金流计划集合
     * @param finprodId
     * @return
     */
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

}
