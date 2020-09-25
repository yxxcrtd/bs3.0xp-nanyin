package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.dto.FxImixBondInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FxImixBondInfo001Mapper {

    List<SelectOptionDto> querySecId(Map<String, Object> map);

    List<FxImixBondInfoDto> selectForPage(Map<String,Object> map);


}
