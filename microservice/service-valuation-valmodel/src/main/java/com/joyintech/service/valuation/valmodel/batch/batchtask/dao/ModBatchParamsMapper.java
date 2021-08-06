package com.joyintech.service.valuation.valmodel.batch.batchtask.dao;

import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.ModValBatchParams;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.ModValBatchParamsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModBatchParamsMapper {
    int countByExample(ModValBatchParamsExample example);

    int deleteByExample(ModValBatchParamsExample example);

    int insert(ModValBatchParams record);

    int insertSelective(ModValBatchParams record);

    int insertBasicByBasicInfo(ModValBatchParams record);

    List<ModValBatchParams> selectByExample(ModValBatchParamsExample example);

    int updateByExampleSelective(@Param("record") ModValBatchParams record, @Param("example") ModValBatchParamsExample example);

    int updateByExample(@Param("record") ModValBatchParams record, @Param("example") ModValBatchParamsExample example);
}