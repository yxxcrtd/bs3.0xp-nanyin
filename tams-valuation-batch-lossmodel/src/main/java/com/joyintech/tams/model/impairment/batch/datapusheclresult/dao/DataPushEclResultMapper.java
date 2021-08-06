package com.joyintech.tams.model.impairment.batch.datapusheclresult.dao;

import com.joyintech.tams.model.impairment.batch.datapusheclresult.dto.AssetLossResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 数据推送批处理DAO
 */
@Repository
public interface DataPushEclResultMapper {


    /**
     * @Description:根据跑批日，获取资产减值结果列表信息
     * @auther: yuzj
     * @date: 2021/7/20
     * @param: plateDate:跑批日
     * @return: 资产减值结果列表
     */
    List<AssetLossResult> selectEclResultList(@Param("plateDate") Date plateDate,@Param("finprodIdList") List<String> finprodIdList);


}
