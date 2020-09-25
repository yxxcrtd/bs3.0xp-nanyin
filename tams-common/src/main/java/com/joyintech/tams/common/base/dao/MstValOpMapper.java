package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstValOp;
import com.joyintech.tams.common.base.entity.MstValOpKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstValOpMapper {
    int deleteByPrimaryKey(MstValOpKey key);

    int insert(MstValOp record);

    int insertSelective(MstValOp record);

    int insertBasicByBasicInfo(MstValOp record);

    MstValOp selectByPrimaryKey(MstValOpKey key);

    int updateByPrimaryKeySelective(MstValOp record);

    int updateByPrimaryKey(MstValOp record);
}