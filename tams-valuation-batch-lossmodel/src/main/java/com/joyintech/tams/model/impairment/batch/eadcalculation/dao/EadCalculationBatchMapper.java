package com.joyintech.tams.model.impairment.batch.eadcalculation.dao;

import com.joyintech.tams.model.impairment.batch.eadcalculation.dto.EadModEclResultProcess;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * EAD计算批处理
 * @author zhangfei
 * @date 2021年7月14日11:06:37
 */
@Repository
public interface EadCalculationBatchMapper {
    // 删除跑批日减值过程表
    int deleteModelResultByDateAndEad(Map<String, Object> param);

    //查询跑批日买入交易列表
    List<EadModEclResultProcess> selectTradeInByDate(Map<String, Object> param);

    //查询跑批日卖出交易列表
    List<EadModEclResultProcess> selectTradeOutByDate(Map<String, Object> param);

    //  查询存续期交易列表
    List<ModEclResultProcess> selectDurationTradeByAssetId(Map<String, Object> param);

}
