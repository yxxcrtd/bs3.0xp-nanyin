package com.joyintech.tams.invest.batch.overduesecpositionforward.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardResultDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OverdueSecPositionForwardDelWriter extends BaseWriter<OverdueSecPositionForwardResultDto> {
    /**
     *
     */
    @Autowired
    private OverdueSecPositionForwardDelService overdueSecPositionForwardDelService;
    @Override
    public void write(List<? extends OverdueSecPositionForwardResultDto> items) throws Exception {
        Map<String,Object> preParams = getParams();
        preParams.put("INPUT_TYPE_PCLSC", DictCodeListExt1.录入方式_批处理生成.getValue());
//        不包含TASK_ID 时处理
        if(!preParams.containsKey("TASK_ID")){
            preParams.put("TASK_ID","11003");
        }
//        删除应收款持仓流水
        overdueSecPositionForwardDelService.removeReceivablesPositionFlow(preParams);
//        删除组合持仓流水
        overdueSecPositionForwardDelService.removeSecPositionFlow(preParams);
//       删除委托持仓流水
        overdueSecPositionForwardDelService.removeEtrSecPositionFlow(preParams);
    }
}
