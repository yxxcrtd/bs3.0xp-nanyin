package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModStandardGradeMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface ModStandardGradeMappingMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(ModStandardGradeMapping record);

    int insertSelective(ModStandardGradeMapping record);

    int insertBasicByBasicInfo(ModStandardGradeMapping record);

    ModStandardGradeMapping selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(ModStandardGradeMapping record);

    int updateByPrimaryKey(ModStandardGradeMapping record);
}