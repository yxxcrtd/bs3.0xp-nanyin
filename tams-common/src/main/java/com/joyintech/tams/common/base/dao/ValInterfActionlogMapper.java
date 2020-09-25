package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValInterfActionlog;
import org.springframework.stereotype.Repository;

@Repository
public interface ValInterfActionlogMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ValInterfActionlog record);

    int insertSelective(ValInterfActionlog record);

    int insertBasicByBasicInfo(ValInterfActionlog record);

    ValInterfActionlog selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ValInterfActionlog record);

    int updateByPrimaryKeyWithBLOBs(ValInterfActionlog record);

    int updateByPrimaryKey(ValInterfActionlog record);
}