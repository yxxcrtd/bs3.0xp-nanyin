package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModAssetGradeSpecialConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModAssetGradeSpecialConfigMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModAssetGradeSpecialConfig record);

    int insertSelective(ModAssetGradeSpecialConfig record);

    int insertBasicByBasicInfo(ModAssetGradeSpecialConfig record);

    ModAssetGradeSpecialConfig selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ModAssetGradeSpecialConfig record);

    int updateByPrimaryKey(ModAssetGradeSpecialConfig record);
}