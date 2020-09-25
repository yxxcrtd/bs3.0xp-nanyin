package com.joyintech.service.asset.option.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.option.dto.FinOptionTargetDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FinOptionTargetMapper {


    public List<SuggestDto>getFinprodcodeList(@Param("input")String input);

    List<FinOptionTargetDto> selectForPage(Map<String, Object> param);


    public FinOptionTargetDto getFinprodcodebyId(@Param("targetId") String targetId);

    public int save(FinOptionTargetDto dto);

    public int update(FinOptionTargetDto dto);

    public int delete(FinOptionTargetDto dto);
}
