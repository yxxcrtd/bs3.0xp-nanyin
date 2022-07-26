package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepFeeTrd;
import org.springframework.stereotype.Repository;

@Repository
public interface RepFeeTrdMapper {
    int deleteByPrimaryKey(RepFeeTrd record);

    int insert(RepFeeTrd record);

    int insertSelective(RepFeeTrd record);

    int insertBasicByBasicInfo(RepFeeTrd record);

    RepFeeTrd selectByPrimaryKey(RepFeeTrd record);

    int updateByPrimaryKeySelective(RepFeeTrd record);

    int updateByPrimaryKey(RepFeeTrd record);
}
