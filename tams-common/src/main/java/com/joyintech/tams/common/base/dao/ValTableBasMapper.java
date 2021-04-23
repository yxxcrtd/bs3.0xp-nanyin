package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ValTableBas;
import com.joyintech.tams.common.base.entity.ValTableBasKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ValTableBasMapper {
    int deleteByPrimaryKey(ValTableBasKey key);

    int insert(ValTableBas record);

    int insertSelective(ValTableBas record);

    int insertBasicByBasicInfo(ValTableBas record);

    ValTableBas selectByPrimaryKey(ValTableBasKey key);

    int updateByPrimaryKeySelective(ValTableBas record);

    int updateByPrimaryKey(ValTableBas record);
}