package com.joyintech.tams.model.impairment.batch.eadcalculation.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dao.EadCalculationBatchMapper;
import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadCalculationReaderDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EadCalculationDelWriter extends BaseWriter<EadCalculationReaderDto> {

    /**
     * Mapper
     */
    @Autowired
    private EadCalculationBatchMapper eadCalculationBatchMapper;
    @Autowired
    private ModParamsService modParamsService;

    @Override
    public void write(List<? extends EadCalculationReaderDto> items) throws Exception {
        Map<String,Object> preParams = getParams();
        //跑批日
        Date platDate = (Date)preParams.get("PLATE_DATE");
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
        Map<String,Object> params = new HashMap<>();
        params.put("decDate", platDate);
        params.put("process", ModelDictCodeList.模型_减值过程_EAD计算.getValue());
        params.put("finprodIdList",finprodIdList);
        eadCalculationBatchMapper.deleteModelResultByDateAndEad(params);
    }
}
