package com.joyintech.service.disclosure.information.config.proelementadj.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.disclosure.information.config.proelementadj.dto.ProElementAdjDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProElementAdjMapper {
    List<ProElementAdjDto> select(Map<String, Object> pageParam);

    ProElementAdjDto selectByPrimaryKey(String prodRegistNo);

    int deleteByKey(String prodRegistNo);

    int insert(ProElementAdjDto proElementAdjDto);

    int updateByKey(ProElementAdjDto dto);

    List<SelectOptionDto> getRegistNo(Map<String, String> map);

    String selectByProdRegistNo(Map<String, Object> map);
}
