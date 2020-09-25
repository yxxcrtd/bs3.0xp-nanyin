package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.NonStandardMaint;
import com.joyintech.tams.common.base.entity.NonStandardMaintKey;
import org.springframework.stereotype.Repository;

@Repository
public interface NonStandardMaintMapper {
    int deleteByPrimaryKey(NonStandardMaintKey key);

    int insert(NonStandardMaint record);

    int insertSelective(NonStandardMaint record);

    int insertBasicByBasicInfo(NonStandardMaint record);

    NonStandardMaint selectByPrimaryKey(NonStandardMaintKey key);

    int updateByPrimaryKeySelective(NonStandardMaint record);

    int updateByPrimaryKey(NonStandardMaint record);
}