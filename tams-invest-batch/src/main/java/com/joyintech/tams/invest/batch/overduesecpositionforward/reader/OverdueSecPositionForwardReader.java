package com.joyintech.tams.invest.batch.overduesecpositionforward.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.batch.overduesecpositionforward.dto.OverdueSecPositionForwardDto;
import com.joyintech.tams.invest.batch.overduesecpositionforward.service.OverdueSecPositionForwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OverdueSecPositionForwardReader extends BaseReader <OverdueSecPositionForwardDto>{
    /**
     *  逾期资产结转批处理Service
     */
    @Autowired
    private OverdueSecPositionForwardService overdueSecPositionForwardService;
    @Override
    public List<OverdueSecPositionForwardDto> createList() {
    	 Map<String, Object> paras = new HashMap<>();
    	 paras.putAll(getParams());
    	 paras.put("ischl", true);
    	 //add by mashukai mashukai 20201210 TISHKBS-1664 在项目类资产付息日进行付息后，撤销付息交易，在应收款项查询界面查不到数据 start
         paras.put("chlFlag", CacheUtil.getSysParam("CHL_MODEL_TYPE"));
        //add by mashukai mashukai 20201210 TISHKBS-1664 在项目类资产付息日进行付息后，撤销付息交易，在应收款项查询界面查不到数据 end
         List<OverdueSecPositionForwardDto> result = overdueSecPositionForwardService.findPendingData(paras);
         paras.put("ischl", false);
         result.addAll(overdueSecPositionForwardService.findPendingData(paras));
         return result;
    }
}
