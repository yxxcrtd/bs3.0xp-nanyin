package com.joyintech.tams.disclosure.information.config.proelementadj.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.disclosure.information.config.prodfeedealInterf.dto.SvrProdFeeDealInterfDto;
import com.joyintech.tams.disclosure.information.config.proelementadj.dto.ProElementAdj001Dto;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Repository
public interface ProElementAdjMapper {
    List<ProElementAdj001Dto> select(Map<String, Object> pageParam);

    ProElementAdj001Dto selectByPrimaryKey(String prodRegistNo);

    int deleteByKey(String prodRegistNo);

    int insert(ProElementAdj001Dto proElementAdjDto);

    int updateByKey(ProElementAdj001Dto dto);

    List<SelectOptionDto> getRegistNo(Map<String, Object> map);

    String selectByProdRegistNo(Map<String, Object> map);

    List<SelectOptionDto> getProdId(Map<String, Object> map);

    List<SelectOptionDto> getRegistNoList(Map<String, String> map);

    List<SelectOptionDto> getProdIdlist(Map<String, String> map);

    List<SelectOptionDto> getAdjF(Map<String, Object> map);

    List<ProElementAdj001Dto> selectProElementAdjByKey(@Param("list") List<ProElementAdj001Dto> list);

    ProElementAdj001Dto selectData(Map<String, Object> param);
}
