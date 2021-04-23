package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.CfetsStatusDatadictConvert;
import org.springframework.stereotype.Repository;

@Repository
public interface CfetsStatusDatadictConvertMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(CfetsStatusDatadictConvert record);

    int insertSelective(CfetsStatusDatadictConvert record);

    int insertBasicByBasicInfo(CfetsStatusDatadictConvert record);

    CfetsStatusDatadictConvert selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(CfetsStatusDatadictConvert record);

    int updateByPrimaryKey(CfetsStatusDatadictConvert record);
}