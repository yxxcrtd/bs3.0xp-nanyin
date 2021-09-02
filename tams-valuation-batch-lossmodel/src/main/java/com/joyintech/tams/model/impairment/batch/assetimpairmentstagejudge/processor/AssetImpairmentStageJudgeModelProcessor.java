package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelReaderEntity;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelResultDto;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service.AssetImpairmentStageJudgeModelProcessorService;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service.AssetImpairmentStageJudgeModelReaderService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetImpairmentStageJudgeModelProcessor extends BaseProcessor<AssetImpairmentStageJudgeModelReaderEntity, AssetImpairmentStageJudgeModelResultDto> implements StepExecutionListener {
    /**
     *  资产减值阶段判断批处理Service
     */
    @Autowired
    private AssetImpairmentStageJudgeModelProcessorService assetImpairmentStageJudgeModelProcessorService;

    private StepExecution stepExecution;
    /**
     *  处理dto reade -> result
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public AssetImpairmentStageJudgeModelResultDto process(AssetImpairmentStageJudgeModelReaderEntity item) throws Exception {
        return assetImpairmentStageJudgeModelProcessorService.execute(item,getParams(),stepExecution);
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
