package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinInitrateDatadict;
import org.springframework.stereotype.Repository;

@Repository
public interface FinInitrateDatadictMapper {
    int deleteByPrimaryKey(String ownDept);

    int insert(FinInitrateDatadict record);

    int insertSelective(FinInitrateDatadict record);

    int insertBasicByBasicInfo(FinInitrateDatadict record);

    FinInitrateDatadict selectByPrimaryKey(String ownDept);

    int updateByPrimaryKeySelective(FinInitrateDatadict record);

    int updateByPrimaryKey(FinInitrateDatadict record);
}