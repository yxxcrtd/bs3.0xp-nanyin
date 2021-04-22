package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdMinpriceConfigDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdMinpriceConfigDetailMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(TrdMinpriceConfigDetail record);

    int insertSelective(TrdMinpriceConfigDetail record);

    int insertBasicByBasicInfo(TrdMinpriceConfigDetail record);

    TrdMinpriceConfigDetail selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(TrdMinpriceConfigDetail record);

    int updateByPrimaryKey(TrdMinpriceConfigDetail record);
}