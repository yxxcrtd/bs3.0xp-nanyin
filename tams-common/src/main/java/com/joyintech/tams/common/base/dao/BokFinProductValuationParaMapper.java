package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokFinProductValuationPara;
import com.joyintech.tams.common.base.entity.BokFinProductValuationParaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface BokFinProductValuationParaMapper {
    int deleteByPrimaryKey(BokFinProductValuationParaKey key);

    int insert(BokFinProductValuationPara record);

    int insertSelective(BokFinProductValuationPara record);

    int insertBasicByBasicInfo(BokFinProductValuationPara record);

    BokFinProductValuationPara selectByPrimaryKey(BokFinProductValuationParaKey key);

    int updateByPrimaryKeySelective(BokFinProductValuationPara record);

    int updateByPrimaryKey(BokFinProductValuationPara record);
}