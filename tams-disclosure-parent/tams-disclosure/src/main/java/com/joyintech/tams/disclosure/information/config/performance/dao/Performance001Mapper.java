package com.joyintech.tams.disclosure.information.config.performance.dao;


import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.hugeinfopro.dto.HugeInfoPro001Dto;
import com.joyintech.tams.disclosure.information.config.performance.dto.Performance001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface Performance001Mapper {

//    List<Performance001Dto> select(Map<String, Object> pageParam);

    List<Performance001Dto> select1(Map<String, Object> pageParam);

    int deleteByKey(Performance001Dto dto);

    List<SelectOptionDto> getProdIdlist(Map map);

    int insert(Performance001Dto dto);

    List<Performance001Dto> selectByPrimaryKey(Map map);

    int updateByKey(Performance001Dto dto);


    List<Performance001Dto> selectPerformanceInfoList(@Param("list") List<Performance001Dto> list);

    List<Performance001Dto> selectPerformanceByProdId(Map<String, Object> map);


    List<ProcessMark> getWorklowData(Performance001Dto dto);

    int update(Performance001Dto dto1);
}
