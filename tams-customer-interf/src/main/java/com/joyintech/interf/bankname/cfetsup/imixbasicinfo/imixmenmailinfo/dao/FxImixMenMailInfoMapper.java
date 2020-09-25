package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.dao;

import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixmenmailinfo.dto.FxImixMenMailInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxImixMenMailInfoMapper {

    List<FxImixMenMailInfoDto> selectPageData(Map para);

    int deleteByPrimaryKey(String seqno);

    int insert(FxImixMenMailInfoDto record);

    int insertSelective(FxImixMenMailInfoDto record);

    FxImixMenMailInfoDto selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(FxImixMenMailInfoDto record);

    int updateByPrimaryKey(FxImixMenMailInfoDto record);
}