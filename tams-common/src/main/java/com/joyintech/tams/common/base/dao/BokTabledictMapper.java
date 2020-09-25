package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokTabledict;
import com.joyintech.tams.common.base.entity.BokTabledictKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokTabledictMapper {
    int deleteByPrimaryKey(BokTabledictKey key);

    int insert(BokTabledict record);

    int insertSelective(BokTabledict record);

    int insertBasicByBasicInfo(BokTabledict record);

    BokTabledict selectByPrimaryKey(BokTabledictKey key);

    int updateByPrimaryKeySelective(BokTabledict record);

    int updateByPrimaryKey(BokTabledict record);
}