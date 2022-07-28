package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepFloatFee;
import org.springframework.stereotype.Repository;

@Repository
public interface RepFloatFeeMapper {
    int deleteByPrimaryKey(RepFloatFee record);

    int insert(RepFloatFee record);

    int insertSelective(RepFloatFee record);

    int insertBasicByBasicInfo(RepFloatFee record);

    RepFloatFee selectByPrimaryKey(RepFloatFee record);

    int updateByPrimaryKeySelective(RepFloatFee record);

    int updateByPrimaryKey(RepFloatFee record);
}
