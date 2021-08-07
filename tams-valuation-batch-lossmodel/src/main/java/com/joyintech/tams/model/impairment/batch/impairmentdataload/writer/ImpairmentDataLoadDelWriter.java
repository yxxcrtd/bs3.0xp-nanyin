package com.joyintech.tams.model.impairment.batch.impairmentdataload.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImpairmentDataLoadDelWriter extends BaseWriter<ImpairmentDataLoadStep1ResultDto> {

    /**
     * Mapper
     */
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;
    @Autowired
    private ModParamsService modParamsService;
    @Override
    public void write(List<? extends ImpairmentDataLoadStep1ResultDto> items) throws Exception {
        Map<String,Object> preParams = getParams();
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)preParams.get("BATCH_INFO");
        //跑批日
        Date platDate = (Date)preParams.get("PLATE_DATE");
        Map<String,Object> params = new HashMap<>();
        params.put("searchDate", platDate);
        params.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        params.put("batchExedetailSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        impairmentDataLoadMapper.deleteModEclResultProcess(params);

    }
}
