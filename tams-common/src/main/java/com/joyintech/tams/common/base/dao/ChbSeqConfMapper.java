package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSeqConf;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSeqConfMapper {
    int insert(ChbSeqConf record);

    int insertSelective(ChbSeqConf record);

    int insertBasicByBasicInfo(ChbSeqConf record);
}