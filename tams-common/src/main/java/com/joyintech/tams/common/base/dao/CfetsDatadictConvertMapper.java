package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CfetsDatadictConvert;
import org.springframework.stereotype.Repository;

@Repository
public interface CfetsDatadictConvertMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CfetsDatadictConvert record);

    int insertSelective(CfetsDatadictConvert record);

    int insertBasicByBasicInfo(CfetsDatadictConvert record);

    CfetsDatadictConvert selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CfetsDatadictConvert record);

    int updateByPrimaryKey(CfetsDatadictConvert record);
}