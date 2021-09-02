package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModCurveResult;
import org.springframework.stereotype.Repository;

@Repository
public interface ModCurveResultMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ModCurveResult record);

    int insertSelective(ModCurveResult record);

    int insertBasicByBasicInfo(ModCurveResult record);

    ModCurveResult selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ModCurveResult record);

    int updateByPrimaryKey(ModCurveResult record);
}