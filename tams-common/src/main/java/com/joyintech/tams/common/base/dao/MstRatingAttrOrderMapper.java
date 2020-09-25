package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstRatingAttrOrder;
import com.joyintech.tams.common.base.entity.MstRatingAttrOrderKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstRatingAttrOrderMapper {
    int deleteByPrimaryKey(MstRatingAttrOrderKey key);

    int insert(MstRatingAttrOrder record);

    int insertSelective(MstRatingAttrOrder record);

    int insertBasicByBasicInfo(MstRatingAttrOrder record);

    MstRatingAttrOrder selectByPrimaryKey(MstRatingAttrOrderKey key);

    int updateByPrimaryKeySelective(MstRatingAttrOrder record);

    int updateByPrimaryKey(MstRatingAttrOrder record);
}