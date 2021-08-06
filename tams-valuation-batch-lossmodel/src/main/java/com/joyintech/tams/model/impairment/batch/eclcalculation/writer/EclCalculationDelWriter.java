package com.joyintech.tams.model.impairment.batch.eclcalculation.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModParamsService;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationResultDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EclCalculationDelWriter extends BaseWriter<EclCalculationResultDto> {

    /**
     * Mapper
     */
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;

    @Autowired
    private EclCalculationMapper eclCalculationMapper;
    @Autowired
    private ModParamsService modParamsService;
    @Override
    public void write(List<? extends EclCalculationResultDto> items) throws Exception {
        Map<String,Object> preParams = getParams();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)preParams.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //跑批日
        Date platDate = (Date)preParams.get("PLATE_DATE");
        //1. 删除减值结果过程表数据
        Map<String,Object> params = new HashMap<>();
        params.put("searchDate", platDate);
        params.put("process", ModelDictCodeList.模型_减值过程_ECL计算.getValue());
        params.put("batchExedetailSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        impairmentDataLoadMapper.deleteModEclResultProcess(params);
        //2. 删除资产减值结果表
        params.put("dataSource", ModelDictCodeList.模型数据来源_模型.getValue());
        eclCalculationMapper.deleteModEclResult(params);

    }
}
