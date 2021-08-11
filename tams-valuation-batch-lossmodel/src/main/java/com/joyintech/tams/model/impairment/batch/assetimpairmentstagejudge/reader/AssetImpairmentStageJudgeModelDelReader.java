package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelReaderEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetImpairmentStageJudgeModelDelReader extends BaseReader <AssetImpairmentStageJudgeModelReaderEntity>{
    @Override
    public List<AssetImpairmentStageJudgeModelReaderEntity> createList() {
        List<AssetImpairmentStageJudgeModelReaderEntity> result = new ArrayList<AssetImpairmentStageJudgeModelReaderEntity>();
        result.add(new AssetImpairmentStageJudgeModelReaderEntity());
        return result;
    }
}
