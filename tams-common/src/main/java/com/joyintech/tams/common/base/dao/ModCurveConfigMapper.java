package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModCurveConfig;
import com.joyintech.tams.common.base.entity.ModCurveConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModCurveConfigMapper {
    int countByExample(ModCurveConfigExample example);

    int deleteByExample(ModCurveConfigExample example);

    int insert(ModCurveConfig record);

    int insertSelective(ModCurveConfig record);

    int insertBasicByBasicInfo(ModCurveConfig record);

    List<ModCurveConfig> selectByExample(ModCurveConfigExample example);

    int updateByExampleSelective(@Param("record") ModCurveConfig record, @Param("example") ModCurveConfigExample example);

    int updateByExample(@Param("record") ModCurveConfig record, @Param("example") ModCurveConfigExample example);
}