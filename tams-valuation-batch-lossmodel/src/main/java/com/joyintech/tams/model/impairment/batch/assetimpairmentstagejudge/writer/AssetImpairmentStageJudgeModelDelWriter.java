package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dao.AssetImpairmentStageJudgeModelMapper;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelResultDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssetImpairmentStageJudgeModelDelWriter extends BaseWriter<AssetImpairmentStageJudgeModelResultDto> {
    /**
     * Mapper
     */
    @Autowired
    private AssetImpairmentStageJudgeModelMapper assetImpairmentStageJudgeModelMapper;
    @Autowired
    private ModParamsService modParamsService;

    @Override
    public void write(List<? extends AssetImpairmentStageJudgeModelResultDto> items) throws Exception {
        Map<String,Object> preParams = getParams();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)preParams.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }

        // 设置此task的序列任务公用的params参数
        Map<String, Object> params = new HashMap<>();
        params.put("PLATE_DATE",(Date)getParams().get("PLATE_DATE"));
        params.put("finprodIdList", finprodIdList);
        // 删除
        assetImpairmentStageJudgeModelMapper.deleteResult(params);
    }
}
