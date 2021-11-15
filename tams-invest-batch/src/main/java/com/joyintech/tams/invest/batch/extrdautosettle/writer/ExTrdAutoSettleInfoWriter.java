package com.joyintech.tams.invest.batch.extrdautosettle.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.ExTrdAutoSettleInfoDto;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.ExTrdAutoSettleInfoResult;
import com.joyintech.tams.invest.batch.mproddivreindealgen.dto.MProdDivReinDealGenResult;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class ExTrdAutoSettleInfoWriter extends BaseWriter<NeedToWriteEntity> {

    @Autowired
    private JoyinRestTemplate restTemplate;

    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
        @SuppressWarnings("unchecked")
        List<ExTrdAutoSettleInfoResult> resultList = (List<ExTrdAutoSettleInfoResult>)items;
        if(CollectionUtils.isEmpty(resultList)){
            return;
        }
        for(ExTrdAutoSettleInfoResult result : resultList){
            List<ExTrdAutoSettleInfoDto> autoSettleInfoDtos = result.getAutoSettleInfoDtos();
            if(CollectionUtils.isEmpty(autoSettleInfoDtos)){
                continue;
            }
            for(ExTrdAutoSettleInfoDto autoSettleInfoDto : autoSettleInfoDtos){
                restTemplate.postForObject(
                        ServiceApplication.TRADE_SERVICE_NAME + "/ManualSettle/settle", autoSettleInfoDto,
                        Integer.class);
                restTemplate.postForObject(
                        ServiceApplication.TRADE_SERVICE_NAME + "/ManualSettle/settleCash", autoSettleInfoDto,
                        Integer.class);
            }
        }
    }
}
