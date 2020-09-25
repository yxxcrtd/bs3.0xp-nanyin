package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfTrustPara;
import com.joyintech.tams.common.base.entity.InfTrustParaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InfTrustParaMapper {
    int deleteByPrimaryKey(InfTrustParaKey key);

    int insert(InfTrustPara record);

    int insertSelective(InfTrustPara record);

    int insertBasicByBasicInfo(InfTrustPara record);

    InfTrustPara selectByPrimaryKey(InfTrustParaKey key);

    int updateByPrimaryKeySelective(InfTrustPara record);

    int updateByPrimaryKey(InfTrustPara record);
}