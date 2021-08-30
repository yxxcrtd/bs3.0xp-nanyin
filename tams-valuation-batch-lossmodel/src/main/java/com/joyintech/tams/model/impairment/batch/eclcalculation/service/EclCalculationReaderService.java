package com.joyintech.tams.model.impairment.batch.eclcalculation.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dao.EclCalculationMapper;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.EclCalculationReaderDto;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        // 定义 变量.减值结果过程Map集合
        Map<String, List<ModEclResultProcess>> modEclResultProcessMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            for (ModEclResultProcess dto : list) {
                // 定义 变量.key = 资产代码 + 账套代码
                String key = dto.getAssetId() + "|" + dto.getProdId();
                // map集合无key对应value值
                if (modEclResultProcessMap.get(key) == null) {
                    // 放入map集合中
                    modEclResultProcessMap.put(key, Arrays.asList(dto));
                }
                else {
                    // 获取对应value值
                    List<ModEclResultProcess> listProcess = modEclResultProcessMap.get(key);
                    // 增加dto
                    listProcess.add(dto);
                    // 更新Map
                    modEclResultProcessMap.put(key, listProcess);
                }
            }
            for (String key : modEclResultProcessMap.keySet()) {
                EclCalculationReaderDto rstDto = new EclCalculationReaderDto();
                rstDto.setAssetId(key.split("|")[0]);
                rstDto.setProdId(key.split("|")[1]);
                rstDto.setModEclResultProcessList(modEclResultProcessMap.get(key));
                rstList.add(rstDto);
            }
        }
        return rstList;
    }

}
