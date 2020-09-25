package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dao;

import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmeninfo.dto.FxImixMenInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxImixMenInfoMapper {

    List<FxImixMenInfoDto> selectPageData(Map para);

    int deleteByPrimaryKey(String seqno);

    int insert(FxImixMenInfoDto record);

    int insertSelective(FxImixMenInfoDto record);

    FxImixMenInfoDto selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FxImixMenInfoDto record);

    int updateByPrimaryKey(FxImixMenInfoDto record);
}