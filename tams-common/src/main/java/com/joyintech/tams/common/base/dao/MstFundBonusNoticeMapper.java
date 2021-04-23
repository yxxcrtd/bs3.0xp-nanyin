package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstFundBonusNotice;
import com.joyintech.tams.common.base.entity.MstFundBonusNoticeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstFundBonusNoticeMapper {
    int deleteByPrimaryKey(MstFundBonusNoticeKey key);

    int insert(MstFundBonusNotice record);

    int insertSelective(MstFundBonusNotice record);

    int insertBasicByBasicInfo(MstFundBonusNotice record);

    MstFundBonusNotice selectByPrimaryKey(MstFundBonusNoticeKey key);

    int updateByPrimaryKeySelective(MstFundBonusNotice record);

    int updateByPrimaryKey(MstFundBonusNotice record);
}