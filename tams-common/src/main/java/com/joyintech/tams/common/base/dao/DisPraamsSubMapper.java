package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisPraamsSub;
import com.joyintech.tams.common.base.entity.DisPraamsSubKey;
import org.springframework.stereotype.Repository;

@Repository
public interface DisPraamsSubMapper {
    int deleteByPrimaryKey(DisPraamsSubKey key);

    int insert(DisPraamsSub record);

    int insertSelective(DisPraamsSub record);

    int insertBasicByBasicInfo(DisPraamsSub record);

    DisPraamsSub selectByPrimaryKey(DisPraamsSubKey key);

    int updateByPrimaryKeySelective(DisPraamsSub record);

    int updateByPrimaryKey(DisPraamsSub record);
}