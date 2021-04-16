package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstShareBonusNotice;
import com.joyintech.tams.common.base.entity.MstShareBonusNoticeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstShareBonusNoticeMapper {
    int deleteByPrimaryKey(MstShareBonusNoticeKey key);

    int insert(MstShareBonusNotice record);

    int insertSelective(MstShareBonusNotice record);

    int insertBasicByBasicInfo(MstShareBonusNotice record);

    MstShareBonusNotice selectByPrimaryKey(MstShareBonusNoticeKey key);

    int updateByPrimaryKeySelective(MstShareBonusNotice record);

    int updateByPrimaryKey(MstShareBonusNotice record);
}