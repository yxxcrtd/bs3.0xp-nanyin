package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.dto.FxImixErrorInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxImixErrorInfoMapper {

    List<FxImixErrorInfoDto> selectPageData(Map para);

    List<SelectOptionDto> getApplerrorcode(Map para);

    int deleteByPrimaryKey(String seqno);

    int insert(FxImixErrorInfoDto record);

    int insertSelective(FxImixErrorInfoDto record);

    FxImixErrorInfoDto selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FxImixErrorInfoDto record);

    int updateByPrimaryKey(FxImixErrorInfoDto record);
}