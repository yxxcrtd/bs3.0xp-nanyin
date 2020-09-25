package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstSharePrice;
import com.joyintech.tams.common.base.entity.MstSharePriceKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstSharePriceMapper {
    int deleteByPrimaryKey(MstSharePriceKey key);

    int insert(MstSharePrice record);

    int insertSelective(MstSharePrice record);

    int insertBasicByBasicInfo(MstSharePrice record);

    MstSharePrice selectByPrimaryKey(MstSharePriceKey key);

    int updateByPrimaryKeySelective(MstSharePrice record);

    int updateByPrimaryKey(MstSharePrice record);
}