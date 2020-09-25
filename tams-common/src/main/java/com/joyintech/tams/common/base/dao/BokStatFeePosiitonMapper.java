package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokStatFeePosiiton;
import com.joyintech.tams.common.base.entity.BokStatFeePosiitonKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokStatFeePosiitonMapper {
    int deleteByPrimaryKey(BokStatFeePosiitonKey key);

    int insert(BokStatFeePosiiton record);

    int insertSelective(BokStatFeePosiiton record);

    int insertBasicByBasicInfo(BokStatFeePosiiton record);

    BokStatFeePosiiton selectByPrimaryKey(BokStatFeePosiitonKey key);

    int updateByPrimaryKeySelective(BokStatFeePosiiton record);

    int updateByPrimaryKey(BokStatFeePosiiton record);
}