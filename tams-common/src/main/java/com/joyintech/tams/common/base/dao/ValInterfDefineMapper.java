package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterfDefine;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterfDefineMapper {
    int deleteByPrimaryKey(String interfId);

    int insert(ValInterfDefine record);

    int insertSelective(ValInterfDefine record);

    int insertBasicByBasicInfo(ValInterfDefine record);

    ValInterfDefine selectByPrimaryKey(String interfId);

    int updateByPrimaryKeySelective(ValInterfDefine record);

    int updateByPrimaryKey(ValInterfDefine record);
}