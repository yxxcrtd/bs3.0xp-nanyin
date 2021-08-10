package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao.AssetImpairmentStageJudgeModelMapper;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelResultDto;
import com.joyintech.tams.model.impairment.batch.common.utils.DataHandleUtil;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchLogResult;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssetImpairmentStageJudgeModelWriter extends BaseWriter<AssetImpairmentStageJudgeModelResultDto> implements StepExecutionListener {
    /**
     * Mapper
     */
    @Autowired
    private AssetImpairmentStageJudgeModelMapper assetImpairmentStageJudgeModelMapper;
    // 日志key
    private final String LOG_KEY = "logkey";

    private StepExecution stepExecution;

    @Override
    public void write(List<? extends AssetImpairmentStageJudgeModelResultDto> items) throws Exception {
        List<AssetImpairmentStageJudgeModelResultDto> rstList = (List<AssetImpairmentStageJudgeModelResultDto>) items;
        List<ModEclResultProcess> rstProcessList = new ArrayList<ModEclResultProcess>();
        for (AssetImpairmentStageJudgeModelResultDto item : rstList) {
            rstProcessList.add(item.getModEclResultProcess());
        }

        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)getParams().get("BATCH_INFO");

        // 插入定义一个固定大小插入存放集合
        List<List<ModEclResultProcess>> list = DataHandleUtil.splitList(rstProcessList);
        try {
            for (List<ModEclResultProcess> listInsert : list) {
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    assetImpairmentStageJudgeModelMapper.insertResult(listInsert);
                }
            }

            for (ModEclResultProcess item:rstProcessList) {
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(item.getAssetId());
                logrst.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(batchExecuteInfo.getTaskId());
                logrst.setMsg("【资产减值阶段判断批处理】:执行批处理【" + item.getAssetId()+ "】成功结束！");
                logrst.setType(ModelDictCodeList.批处理执行结果_正常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
            }

        }catch (Exception e){
            for (ModEclResultProcess item:rstProcessList) {
                ModValBatchLogResult logrst = new ModValBatchLogResult();
                logrst.setFinprodId(item.getAssetId());
                logrst.setBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
                logrst.setTaskId(batchExecuteInfo.getTaskId());
                logrst.setMsg("计算错误");
                logrst.setType(ModelDictCodeList.批处理执行结果_异常结束.getValue());
                editLogContextInfo(stepExecution,logrst);
            }
        }
    }

    // 组装日志上下文信息
    private void editLogContextInfo(StepExecution stepExecution, ModValBatchLogResult logrst){
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

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
