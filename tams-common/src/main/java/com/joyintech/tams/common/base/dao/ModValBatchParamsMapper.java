package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModValBatchParams;
import com.joyintech.tams.common.base.entity.ModValBatchParamsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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