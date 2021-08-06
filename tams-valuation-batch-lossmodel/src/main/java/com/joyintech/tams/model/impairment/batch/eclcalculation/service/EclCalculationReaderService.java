package com.joyintech.tams.model.impairment.batch.eclcalculation.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.model.impairment.batch.assetimpairmentstagejudge.dto.ModEclResultProcess;
import com.joyintech.tams.model.impairment.batch.batchtask.service.ModParamsService;
import com.joyintech.tams.model.impairment.batch.common.constants.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EclCalculationReaderService {

    @Autowired
    private EclCalculationMapper eclCalculationMapper;
    @Autowired
    private ModParamsService modParamsService;
    /**
     * 二叉树模型-reader
     * @param params
     * @return
     */
    public List<EclCalculationReaderDto> createReaderList(Map<String, Object> params) {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        //1.读取需要进行处理的资产
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("searchDate", platDate);
        para.put("process", ModelDictCodeList.模型_减值过程_PD计算.getValue());
        para.put("ps", ModelDictCodeList.交易方向_买入.getValue());
        para.put("batchExedetailSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        //1.获取理需要处理的每一笔交易数据（计划做在数据加载批处理中）
        List<ModEclResultProcess> list = eclCalculationMapper.selectModEclResultProcessForDeal(para);
        List<EclCalculationReaderDto> rstList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (ModEclResultProcess dto : list) {
                EclCalculationReaderDto rstDto = new EclCalculationReaderDto();
                rstDto.setModEclResultProcess(dto);
                rstList.add(rstDto);
            }
        }
        return rstList;
    }

}
