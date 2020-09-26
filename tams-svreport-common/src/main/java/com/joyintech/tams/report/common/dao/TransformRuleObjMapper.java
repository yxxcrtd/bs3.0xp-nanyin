package com.joyintech.tams.report.common.dao;

import java.util.List;

import com.joyintech.tams.report.common.dto.ChbTransformRuleObjDto;



public interface TransformRuleObjMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbTransformRuleObjDto record);

    int insertSelective(ChbTransformRuleObjDto record);

    ChbTransformRuleObjDto selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbTransformRuleObjDto record);

    int updateByPrimaryKey(ChbTransformRuleObjDto record);
    List<ChbTransformRuleObjDto> selectAllDataByTableId(String tableid);
    
}