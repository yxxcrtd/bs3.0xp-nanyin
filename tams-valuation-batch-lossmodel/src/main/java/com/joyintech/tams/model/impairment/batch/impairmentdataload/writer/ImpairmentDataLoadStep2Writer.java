package com.joyintech.tams.model.impairment.batch.impairmentdataload.writer;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ModEclResultProcessMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ResultDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.dto.modelDto.ModEclResultProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImpairmentDataLoadStep2Writer extends BaseWriter<ImpairmentDataLoadStep1ResultDto> {
    /**
     * Mapper
     */
    @Autowired
    private ModEclResultProcessMapper modEclResultProcessMapper;

    @Override
    public void write(List<? extends ImpairmentDataLoadStep1ResultDto> items) throws Exception {
        List<ImpairmentDataLoadStep1ResultDto> resultList = (List<ImpairmentDataLoadStep1ResultDto>) items;
        //清除缓存中评级信息数据
        if (CacheUtil.getAppCache(GlobalConstant.RATING_INFO, Map.class) != null) {
            CacheUtil.clearAppCache(GlobalConstant.RATING_INFO);
        }
        List<ModEclResultProcess> listModEclResultProcess = new ArrayList<ModEclResultProcess>();
        if (resultList != null && resultList.size() > 0) {
            for (ImpairmentDataLoadStep1ResultDto dto : resultList) {
                modEclResultProcessMapper.updateByPrimaryKeySelective(dto.getModEclResultProcess());
            }
        }
    }
}
