package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfCmsTcmsInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfCmsTcmsInfoMapper {
    int deleteByPrimaryKey(String putoutno);

    int insert(InterfCmsTcmsInfo record);

    int insertSelective(InterfCmsTcmsInfo record);

    int insertBasicByBasicInfo(InterfCmsTcmsInfo record);

    InterfCmsTcmsInfo selectByPrimaryKey(String putoutno);

    int updateByPrimaryKeySelective(InterfCmsTcmsInfo record);

    int updateByPrimaryKey(InterfCmsTcmsInfo record);
}