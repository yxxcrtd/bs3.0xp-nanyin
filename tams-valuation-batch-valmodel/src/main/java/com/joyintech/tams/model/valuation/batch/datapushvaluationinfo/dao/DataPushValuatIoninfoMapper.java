package com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dao;

import com.joyintech.tams.model.valuation.batch.datapushvaluationinfo.dto.ValuationBatchSynDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.AssetsMakePriceResultDto;
import com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto.ValBondValuationInfoResultDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据推送批处理DAO
 */
@Repository
public interface DataPushValuatIoninfoMapper {


    /**
     * @Description:根据跑批日，获取估值行情列表信息
     * @auther: yuzj
     * @date: 2021/7/13
     * @param: batchDateStr:跑批日
     * @return: 估值行情列表
     */
    List<ValuationBatchSynDto> selectValuationInfoList(@Param("batchDateStr")String batchDateStr,@Param("finprodIdList") List<String> finprodIdList);


}
