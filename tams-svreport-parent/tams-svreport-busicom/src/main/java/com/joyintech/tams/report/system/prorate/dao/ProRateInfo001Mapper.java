package com.joyintech.tams.report.system.prorate.dao;

import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.report.system.prorate.dto.ProRateInfo001Dto;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProRateInfo001Mapper {

    List<ProRateInfo001Dto> queryForPage(Map<String, Object> param);

    List<ChbPrdBaseinfo> getProdinfo(Map<String, Object> param);

    List<SysDataDict> getSysDataDict(Map<String, Object> param);

    List<Integer> selectPrdFeeData(List<ProRateInfo001Dto> dtoList);
}
