package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinProductValuationPara;
import com.joyintech.tams.common.base.entity.FinProductValuationParaKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinProductValuationParaMapper {
    int deleteByPrimaryKey(FinProductValuationParaKey key);

    int insert(FinProductValuationPara record);

    int insertSelective(FinProductValuationPara record);

    int insertBasicByBasicInfo(FinProductValuationPara record);

    FinProductValuationPara selectByPrimaryKey(FinProductValuationParaKey key);

    int updateByPrimaryKeySelective(FinProductValuationPara record);

    int updateByPrimaryKey(FinProductValuationPara record);
}