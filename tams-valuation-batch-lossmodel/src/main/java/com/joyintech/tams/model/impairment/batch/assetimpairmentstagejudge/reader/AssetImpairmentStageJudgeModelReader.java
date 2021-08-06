package com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.AssetImpairmentStageJudgeModelReaderEntity;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModStageParaDto;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.service.AssetImpairmentStageJudgeModelReaderService;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModParamsService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetImpairmentStageJudgeModelReader extends BaseReader <AssetImpairmentStageJudgeModelReaderEntity> implements StepExecutionListener {
    /**
     *  资产减值阶段判断批处理Service
     */
    @Autowired
    private AssetImpairmentStageJudgeModelReaderService assetImpairmentStageJudgeModelReaderService;
    @Autowired
    private ModParamsService modParamsService;

    private StepExecution stepExecution;

    @Override
    public List<AssetImpairmentStageJudgeModelReaderEntity> createList() {
        List<ModStageParaDto> rstlist = modStageParaList();
        // 查询减值阶段参数的map 9个
        for (ModStageParaDto item:rstlist) {
            getParams().put(item.getItem(),item.getItemValue());
        }

        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)getParams().get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        getParams().put("finprodIdList", finprodIdList);
        List<AssetImpairmentStageJudgeModelReaderEntity> result = assetImpairmentStageJudgeModelReaderService.createReaderList(getParams(),stepExecution);
        return result;
    }

    /**
     * 减值阶段查询条件集合
     * @return
     */
    private List<ModStageParaDto> modStageParaList(){
        List<ModStageParaDto> rst = assetImpairmentStageJudgeModelReaderService.getModStageParaDtoList();
        if(rst == null || rst.size()==0){
            return new ArrayList<>();
        }
        return rst;
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
