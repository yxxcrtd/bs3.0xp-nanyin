package com.joyintech.tams.valuation.component.batch.dao;

import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParamsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModValBatchParamsMapper {
    int countByExample(ModValBatchParamsExample example);

    int deleteByExample(ModValBatchParamsExample example);

    int insert(ModValBatchParams record);

    int insertSelective(ModValBatchParams record);

    int insertBasicByBasicInfo(ModValBatchParams record);

    List<ModValBatchParams> selectByExample(ModValBatchParamsExample example);

    int updateByExampleSelective(@Param("record") ModValBatchParams record, @Param("example") ModValBatchParamsExample example);

    int updateByExample(@Param("record") ModValBatchParams record, @Param("example") ModValBatchParamsExample example);
}