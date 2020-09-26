package com.joyintech.tams.report.errormessage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.errormessage.dto.ErrorMessage001;

public interface ErrorMessage001Mapper {
  int deleteByPrimaryKey(String dataid);

  int insert(ErrorMessage001 record);

  List<ErrorMessage001> selectByPrimaryKey(String dataid);

  List<ErrorMessage001> selectAll();

  int updateByPrimaryKey(ErrorMessage001 record);
  
  List<ErrorMessage001> queryPage(Map<String,Object> selectParam);
  
  List<SelectOptionDto> selectInterfTypeSelects(@Param("prodId")String prodId);
}