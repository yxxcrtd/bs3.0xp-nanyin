package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelReaderEntity;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelResultDto;
import org.springframework.stereotype.Service;

@Service
public class AssetImpairmentStageJudgeModelDelProcessor extends BaseProcessor<AssetImpairmentStageJudgeModelReaderEntity, AssetImpairmentStageJudgeModelResultDto> {
    /**
     *  批处理删除
     * @param item 读取到的dto
     * @return 处理转换结果
     * @throws Exception 处理异常
     */
    @Override
    public AssetImpairmentStageJudgeModelResultDto process(AssetImpairmentStageJudgeModelReaderEntity item) throws Exception {
        AssetImpairmentStageJudgeModelResultDto resultDto = new AssetImpairmentStageJudgeModelResultDto();
        resultDto.setProdId("减值模型资产减值阶段判断批处理删除！");
        return resultDto;
    }
}
