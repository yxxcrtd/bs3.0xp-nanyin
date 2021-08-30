package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao.AssetImpairmentStageJudgeModelMapper;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.*;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
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

    @Autowired
    private ImpairmentCommonService impairmentCommonService;

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
        param.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        // 减值方案系统参数
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        // 普华方案只查询买入交易的数据
        if (DictCodeListExt3.减值方案类型_普华.getValue().equals(lossType)) {
            param.put("ps", DictCodeList.交易方向_买入.getValue());
        }
        eclList = assetImpairmentStageJudgeModelMapper.assetImpairmentStageJudgeList(param);


        for (ModEclResultProcess item : eclList) {
            AssetImpairmentStageJudgeModelReaderEntity entity = new AssetImpairmentStageJudgeModelReaderEntity();
            entity.setModEclResultProcess(item);
            entity.setModCashFlowList(getCashFlowPlanInfoByFinprodId(item.getProdId()));
            list.add(entity);
        }

        if(list == null || list.size()==0){
            SysBatchExecInfo sysBatchExecInfo = (SysBatchExecInfo) params.get("BATCH_INFO");
            String msg = "【资产减值阶段判断批处理】：计算错误";
            // 取不到满足条件的数据
            for(int i=0;i<finprodIdList.size();i++){
                impairmentCommonService.editLogContextInfo(stepExecution, sysBatchExecInfo,
                    finprodIdList.get(i), msg,ModelDictCodeList.批处理执行结果_异常结束.getValue());
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
